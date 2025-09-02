package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.facebook.react.common.assets.ReactFontManager;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.ComplianceData;
import com.google.android.datatransport.cct.internal.ExperimentIds;
import com.google.android.datatransport.cct.internal.ExternalPRequestContext;
import com.google.android.datatransport.cct.internal.ExternalPrivacyContext;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

final class CctTransportBackend implements TransportBackend {
    private final Context applicationContext;
    private final ConnectivityManager connectivityManager;
    private final DataEncoder dataEncoder;
    final URL endPoint;
    private final int readTimeout;
    private final Clock uptimeClock;
    private final Clock wallTimeClock;

    private static URL parseUrlOrThrow(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + str, e);
        }
    }

    CctTransportBackend(Context context, Clock clock, Clock clock2, int i) {
        this.dataEncoder = BatchedLogRequest.createDataEncoder();
        this.applicationContext = context;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.endPoint = parseUrlOrThrow(CCTDestination.DEFAULT_END_POINT);
        this.uptimeClock = clock2;
        this.wallTimeClock = clock;
        this.readTimeout = i;
    }

    CctTransportBackend(Context context, Clock clock, Clock clock2) {
        this(context, clock, clock2, 130000);
    }

    private static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    private static String getMccMncOrEmpty(Context context) {
        String simOperator = getTelephonyManager(context).getSimOperator();
        if (simOperator != null) {
            return simOperator;
        }
        return "";
    }

    private static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Logging.e("CctTransportBackend", "Unable to find version code for package", e);
            return -1;
        }
    }

    public EventInternal decorate(EventInternal eventInternal) {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return eventInternal.toBuilder().addMetadata("sdk-version", Build.VERSION.SDK_INT).addMetadata("model", Build.MODEL).addMetadata("hardware", Build.HARDWARE).addMetadata("device", Build.DEVICE).addMetadata("product", Build.PRODUCT).addMetadata("os-uild", Build.ID).addMetadata("manufacturer", Build.MANUFACTURER).addMetadata("fingerprint", Build.FINGERPRINT).addMetadata("tz-offset", getTzOffset()).addMetadata("net-type", getNetTypeValue(activeNetworkInfo)).addMetadata("mobile-subtype", getNetSubtypeValue(activeNetworkInfo)).addMetadata("country", Locale.getDefault().getCountry()).addMetadata("locale", Locale.getDefault().getLanguage()).addMetadata("mcc_mnc", getMccMncOrEmpty(this.applicationContext)).addMetadata("application_build", Integer.toString(getPackageVersionCode(this.applicationContext))).build();
    }

    private static int getNetTypeValue(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return NetworkConnectionInfo.NetworkType.NONE.getValue();
        }
        return networkInfo.getType();
    }

    private static int getNetSubtypeValue(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return NetworkConnectionInfo.MobileSubtype.UNKNOWN_MOBILE_SUBTYPE.getValue();
        }
        int subtype = networkInfo.getSubtype();
        if (subtype == -1) {
            return NetworkConnectionInfo.MobileSubtype.COMBINED.getValue();
        }
        if (NetworkConnectionInfo.MobileSubtype.forNumber(subtype) != null) {
            return subtype;
        }
        return 0;
    }

    private BatchedLogRequest getRequestBody(BackendRequest backendRequest) {
        LogEvent.Builder builder;
        HashMap hashMap = new HashMap();
        for (EventInternal eventInternal : backendRequest.getEvents()) {
            String transportName = eventInternal.getTransportName();
            if (!hashMap.containsKey(transportName)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(eventInternal);
                hashMap.put(transportName, arrayList);
            } else {
                ((List) hashMap.get(transportName)).add(eventInternal);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            EventInternal eventInternal2 = (EventInternal) ((List) entry.getValue()).get(0);
            LogRequest.Builder clientInfo = LogRequest.builder().setQosTier(QosTier.DEFAULT).setRequestTimeMs(this.wallTimeClock.getTime()).setRequestUptimeMs(this.uptimeClock.getTime()).setClientInfo(ClientInfo.builder().setClientType(ClientInfo.ClientType.ANDROID_FIREBASE).setAndroidClientInfo(AndroidClientInfo.builder().setSdkVersion(Integer.valueOf(eventInternal2.getInteger("sdk-version"))).setModel(eventInternal2.get("model")).setHardware(eventInternal2.get("hardware")).setDevice(eventInternal2.get("device")).setProduct(eventInternal2.get("product")).setOsBuild(eventInternal2.get("os-uild")).setManufacturer(eventInternal2.get("manufacturer")).setFingerprint(eventInternal2.get("fingerprint")).setCountry(eventInternal2.get("country")).setLocale(eventInternal2.get("locale")).setMccMnc(eventInternal2.get("mcc_mnc")).setApplicationBuild(eventInternal2.get("application_build")).build()).build());
            try {
                clientInfo.setSource(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused) {
                clientInfo.setSource((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (EventInternal eventInternal3 : (List) entry.getValue()) {
                EncodedPayload encodedPayload = eventInternal3.getEncodedPayload();
                Encoding encoding = encodedPayload.getEncoding();
                if (encoding.equals(Encoding.of("proto"))) {
                    builder = LogEvent.protoBuilder(encodedPayload.getBytes());
                } else if (encoding.equals(Encoding.of("json"))) {
                    builder = LogEvent.jsonBuilder(new String(encodedPayload.getBytes(), Charset.forName("UTF-8")));
                } else {
                    Logging.w("CctTransportBackend", "Received event of unsupported encoding %s. Skipping...", encoding);
                }
                builder.setEventTimeMs(eventInternal3.getEventMillis()).setEventUptimeMs(eventInternal3.getUptimeMillis()).setTimezoneOffsetSeconds(eventInternal3.getLong("tz-offset")).setNetworkConnectionInfo(NetworkConnectionInfo.builder().setNetworkType(NetworkConnectionInfo.NetworkType.forNumber(eventInternal3.getInteger("net-type"))).setMobileSubtype(NetworkConnectionInfo.MobileSubtype.forNumber(eventInternal3.getInteger("mobile-subtype"))).build());
                if (eventInternal3.getCode() != null) {
                    builder.setEventCode(eventInternal3.getCode());
                }
                if (eventInternal3.getProductId() != null) {
                    builder.setComplianceData(ComplianceData.builder().setPrivacyContext(ExternalPrivacyContext.builder().setPrequest(ExternalPRequestContext.builder().setOriginAssociatedProductId(eventInternal3.getProductId()).build()).build()).setProductIdOrigin(ComplianceData.ProductIdOrigin.EVENT_OVERRIDE).build());
                }
                if (!(eventInternal3.getExperimentIdsClear() == null && eventInternal3.getExperimentIdsEncrypted() == null)) {
                    ExperimentIds.Builder builder2 = ExperimentIds.builder();
                    if (eventInternal3.getExperimentIdsClear() != null) {
                        builder2.setClearBlob(eventInternal3.getExperimentIdsClear());
                    }
                    if (eventInternal3.getExperimentIdsEncrypted() != null) {
                        builder2.setEncryptedBlob(eventInternal3.getExperimentIdsEncrypted());
                    }
                    builder.setExperimentIds(builder2.build());
                }
                arrayList3.add(builder.build());
            }
            clientInfo.setLogEvents(arrayList3);
            arrayList2.add(clientInfo.build());
        }
        return BatchedLogRequest.create(arrayList2);
    }

    /* access modifiers changed from: private */
    public HttpResponse doSend(HttpRequest httpRequest) {
        OutputStream outputStream;
        GZIPOutputStream gZIPOutputStream;
        InputStream maybeUnGzip;
        Logging.i("CctTransportBackend", "Making request to: %s", httpRequest.url);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpRequest.url.openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(this.readTimeout);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", new Object[]{"3.3.0"}));
        httpURLConnection.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip");
        String str = httpRequest.apiKey;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            outputStream = httpURLConnection.getOutputStream();
            gZIPOutputStream = new GZIPOutputStream(outputStream);
            this.dataEncoder.encode(httpRequest.requestBody, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
            gZIPOutputStream.close();
            if (outputStream != null) {
                outputStream.close();
            }
            int responseCode = httpURLConnection.getResponseCode();
            Logging.i("CctTransportBackend", "Status Code: %d", Integer.valueOf(responseCode));
            Logging.d("CctTransportBackend", "Content-Type: %s", (Object) httpURLConnection.getHeaderField("Content-Type"));
            Logging.d("CctTransportBackend", "Content-Encoding: %s", (Object) httpURLConnection.getHeaderField("Content-Encoding"));
            if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                return new HttpResponse(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0);
            }
            if (responseCode != 200) {
                return new HttpResponse(responseCode, (URL) null, 0);
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                maybeUnGzip = maybeUnGzip(inputStream, httpURLConnection.getHeaderField("Content-Encoding"));
                HttpResponse httpResponse = new HttpResponse(responseCode, (URL) null, LogResponse.fromJson(new BufferedReader(new InputStreamReader(maybeUnGzip))).getNextRequestWaitMillis());
                if (maybeUnGzip != null) {
                    maybeUnGzip.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                return httpResponse;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
            throw th;
            throw th;
            throw th;
            throw th;
        } catch (ConnectException | UnknownHostException e) {
            Logging.e("CctTransportBackend", "Couldn't open connection, returning with 500", e);
            return new HttpResponse(500, (URL) null, 0);
        } catch (EncodingException | IOException e2) {
            Logging.e("CctTransportBackend", "Couldn't encode request, returning with 400", e2);
            return new HttpResponse(ReactFontManager.TypefaceStyle.NORMAL, (URL) null, 0);
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    private static InputStream maybeUnGzip(InputStream inputStream, String str) {
        return "gzip".equals(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    public BackendResponse send(BackendRequest backendRequest) {
        BatchedLogRequest requestBody = getRequestBody(backendRequest);
        URL url = this.endPoint;
        String str = null;
        if (backendRequest.getExtras() != null) {
            try {
                CCTDestination fromByteArray = CCTDestination.fromByteArray(backendRequest.getExtras());
                if (fromByteArray.getAPIKey() != null) {
                    str = fromByteArray.getAPIKey();
                }
                if (fromByteArray.getEndPoint() != null) {
                    url = parseUrlOrThrow(fromByteArray.getEndPoint());
                }
            } catch (IllegalArgumentException unused) {
                return BackendResponse.fatalError();
            }
        }
        try {
            HttpResponse httpResponse = (HttpResponse) Retries.retry(5, new HttpRequest(url, requestBody, str), new CctTransportBackend$$ExternalSyntheticLambda0(this), new CctTransportBackend$$ExternalSyntheticLambda1());
            int i = httpResponse.code;
            if (i == 200) {
                return BackendResponse.ok(httpResponse.nextRequestMillis);
            }
            if (i < 500) {
                if (i != 404) {
                    if (i == 400) {
                        return BackendResponse.invalidPayload();
                    }
                    return BackendResponse.fatalError();
                }
            }
            return BackendResponse.transientError();
        } catch (IOException e) {
            Logging.e("CctTransportBackend", "Could not make request to the backend", e);
            return BackendResponse.transientError();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HttpRequest lambda$send$0(HttpRequest httpRequest, HttpResponse httpResponse) {
        URL url = httpResponse.redirectUrl;
        if (url == null) {
            return null;
        }
        Logging.d("CctTransportBackend", "Following redirect to: %s", (Object) url);
        return httpRequest.withUrl(httpResponse.redirectUrl);
    }

    static long getTzOffset() {
        Calendar.getInstance();
        return (long) (TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000);
    }

    static final class HttpResponse {
        final int code;
        final long nextRequestMillis;
        final URL redirectUrl;

        HttpResponse(int i, URL url, long j) {
            this.code = i;
            this.redirectUrl = url;
            this.nextRequestMillis = j;
        }
    }

    static final class HttpRequest {
        final String apiKey;
        final BatchedLogRequest requestBody;
        final URL url;

        HttpRequest(URL url2, BatchedLogRequest batchedLogRequest, String str) {
            this.url = url2;
            this.requestBody = batchedLogRequest;
            this.apiKey = str;
        }

        /* access modifiers changed from: package-private */
        public HttpRequest withUrl(URL url2) {
            return new HttpRequest(url2, this.requestBody, this.apiKey);
        }
    }
}
