package com.google.firebase.installations.remote;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.text.TextUtils;
import android.util.JsonReader;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.heartbeatinfo.HeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseInstallationServiceClient {
    private static final String ACCEPT_HEADER_KEY = "Accept";
    private static final String API_KEY_HEADER = "x-goog-api-key";
    private static final String CACHE_CONTROL_DIRECTIVE = "no-cache";
    private static final String CACHE_CONTROL_HEADER_KEY = "Cache-Control";
    private static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String CREATE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations";
    private static final String DELETE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s";
    private static final Pattern EXPIRATION_TIMESTAMP_PATTERN = Pattern.compile("[0-9]+s");
    private static final String FIREBASE_INSTALLATIONS_API_DOMAIN = "firebaseinstallations.googleapis.com";
    private static final String FIREBASE_INSTALLATIONS_API_VERSION = "v1";
    private static final String FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG = "fire-installations-id";
    private static final String FIREBASE_INSTALLATION_AUTH_VERSION = "FIS_v2";
    private static final String FIS_TAG = "Firebase-Installations";
    private static final String GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s/authTokens:generate";
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final String HEART_BEAT_HEADER = "x-firebase-client";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final int MAX_RETRIES = 1;
    private static final int NETWORK_TIMEOUT_MILLIS = 10000;
    static final String PARSING_EXPIRATION_TIME_ERROR_MESSAGE = "Invalid Expiration Timestamp.";
    private static final String SDK_VERSION_PREFIX = "a:";
    private static final int TRAFFIC_STATS_CREATE_INSTALLATION_TAG = 32769;
    private static final int TRAFFIC_STATS_DELETE_INSTALLATION_TAG = 32770;
    private static final int TRAFFIC_STATS_FIREBASE_INSTALLATIONS_TAG = 32768;
    private static final int TRAFFIC_STATS_GENERATE_AUTH_TOKEN_TAG = 32771;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final String X_ANDROID_CERT_HEADER_KEY = "X-Android-Cert";
    private static final String X_ANDROID_IID_MIGRATION_KEY = "x-goog-fis-android-iid-migration-auth";
    private static final String X_ANDROID_PACKAGE_HEADER_KEY = "X-Android-Package";
    private final Context context;
    private final Provider<HeartBeatController> heartBeatProvider;
    private final RequestLimiter requestLimiter = new RequestLimiter();
    private boolean shouldServerErrorRetry;

    private static boolean isSuccessfulResponseCode(int i) {
        return i >= 200 && i < 300;
    }

    public FirebaseInstallationServiceClient(Context context2, Provider<HeartBeatController> provider) {
        this.context = context2;
        this.heartBeatProvider = provider;
    }

    public InstallationResponse createFirebaseInstallation(String str, String str2, String str3, String str4, String str5) throws FirebaseInstallationsException {
        InstallationResponse readCreateResponse;
        if (this.requestLimiter.isRequestAllowed()) {
            URL fullyQualifiedRequestUri = getFullyQualifiedRequestUri(String.format(CREATE_REQUEST_RESOURCE_NAME_FORMAT, new Object[]{str3}));
            int i = 0;
            while (i <= 1) {
                TrafficStats.setThreadStatsTag(TRAFFIC_STATS_CREATE_INSTALLATION_TAG);
                HttpURLConnection openHttpURLConnection = openHttpURLConnection(fullyQualifiedRequestUri, str);
                try {
                    openHttpURLConnection.setRequestMethod("POST");
                    openHttpURLConnection.setDoOutput(true);
                    if (str5 != null) {
                        openHttpURLConnection.addRequestProperty(X_ANDROID_IID_MIGRATION_KEY, str5);
                    }
                    writeFIDCreateRequestBodyToOutputStream(openHttpURLConnection, str2, str4);
                    int responseCode = openHttpURLConnection.getResponseCode();
                    this.requestLimiter.setNextRequestTime(responseCode);
                    if (isSuccessfulResponseCode(responseCode)) {
                        readCreateResponse = readCreateResponse(openHttpURLConnection);
                    } else {
                        logFisCommunicationError(openHttpURLConnection, str4, str, str3);
                        if (responseCode != 429) {
                            if (responseCode < 500 || responseCode >= 600) {
                                logBadConfigError();
                                readCreateResponse = InstallationResponse.builder().setResponseCode(InstallationResponse.ResponseCode.BAD_CONFIG).build();
                            }
                            openHttpURLConnection.disconnect();
                            TrafficStats.clearThreadStatsTag();
                            i++;
                        } else {
                            throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.TOO_MANY_REQUESTS);
                        }
                    }
                    openHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    return readCreateResponse;
                } catch (IOException | AssertionError unused) {
                } catch (Throwable th) {
                    openHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    throw th;
                }
            }
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    private void writeFIDCreateRequestBodyToOutputStream(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        writeRequestBodyToOutputStream(httpURLConnection, getJsonBytes(buildCreateFirebaseInstallationRequestBody(str, str2)));
    }

    private static byte[] getJsonBytes(JSONObject jSONObject) throws IOException {
        return jSONObject.toString().getBytes("UTF-8");
    }

    private static void writeRequestBodyToOutputStream(URLConnection uRLConnection, byte[] bArr) throws IOException {
        OutputStream outputStream = uRLConnection.getOutputStream();
        if (outputStream != null) {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
            try {
                gZIPOutputStream.write(bArr);
            } finally {
                try {
                    gZIPOutputStream.close();
                    outputStream.close();
                } catch (IOException unused) {
                }
            }
        } else {
            throw new IOException("Cannot send request to FIS servers. No OutputStream available.");
        }
    }

    private static JSONObject buildCreateFirebaseInstallationRequestBody(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("fid", str);
            jSONObject.put(RemoteConfigConstants.RequestFieldKey.APP_ID, str2);
            jSONObject.put("authVersion", FIREBASE_INSTALLATION_AUTH_VERSION);
            jSONObject.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "a:18.0.0");
            return jSONObject;
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
    }

    private void writeGenerateAuthTokenRequestBodyToOutputStream(HttpURLConnection httpURLConnection) throws IOException {
        writeRequestBodyToOutputStream(httpURLConnection, getJsonBytes(buildGenerateAuthTokenRequestBody()));
    }

    private static JSONObject buildGenerateAuthTokenRequestBody() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "a:18.0.0");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("installation", jSONObject);
            return jSONObject2;
        } catch (JSONException e) {
            throw new IllegalStateException(e);
        }
    }

    public void deleteFirebaseInstallation(String str, String str2, String str3, String str4) throws FirebaseInstallationsException {
        URL fullyQualifiedRequestUri = getFullyQualifiedRequestUri(String.format(DELETE_REQUEST_RESOURCE_NAME_FORMAT, new Object[]{str3, str2}));
        int i = 0;
        while (i <= 1) {
            TrafficStats.setThreadStatsTag(TRAFFIC_STATS_DELETE_INSTALLATION_TAG);
            HttpURLConnection openHttpURLConnection = openHttpURLConnection(fullyQualifiedRequestUri, str);
            try {
                openHttpURLConnection.setRequestMethod(FirebasePerformance.HttpMethod.DELETE);
                openHttpURLConnection.addRequestProperty("Authorization", "FIS_v2 " + str4);
                int responseCode = openHttpURLConnection.getResponseCode();
                if (!(responseCode == 200 || responseCode == 401)) {
                    if (responseCode != 404) {
                        logFisCommunicationError(openHttpURLConnection, (String) null, str, str3);
                        if (responseCode == 429) {
                            continue;
                        } else if (responseCode < 500 || responseCode >= 600) {
                            logBadConfigError();
                            throw new FirebaseInstallationsException("Bad config while trying to delete FID", FirebaseInstallationsException.Status.BAD_CONFIG);
                        }
                        i++;
                        openHttpURLConnection.disconnect();
                        TrafficStats.clearThreadStatsTag();
                    }
                }
                openHttpURLConnection.disconnect();
                TrafficStats.clearThreadStatsTag();
                return;
            } catch (IOException unused) {
            } catch (Throwable th) {
                openHttpURLConnection.disconnect();
                TrafficStats.clearThreadStatsTag();
                throw th;
            }
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    private URL getFullyQualifiedRequestUri(String str) throws FirebaseInstallationsException {
        try {
            return new URL(String.format("https://%s/%s/%s", new Object[]{FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, str}));
        } catch (MalformedURLException e) {
            throw new FirebaseInstallationsException(e.getMessage(), FirebaseInstallationsException.Status.UNAVAILABLE);
        }
    }

    public TokenResult generateAuthToken(String str, String str2, String str3, String str4) throws FirebaseInstallationsException {
        TokenResult readGenerateAuthTokenResponse;
        if (this.requestLimiter.isRequestAllowed()) {
            URL fullyQualifiedRequestUri = getFullyQualifiedRequestUri(String.format(GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT, new Object[]{str3, str2}));
            int i = 0;
            while (i <= 1) {
                TrafficStats.setThreadStatsTag(TRAFFIC_STATS_GENERATE_AUTH_TOKEN_TAG);
                HttpURLConnection openHttpURLConnection = openHttpURLConnection(fullyQualifiedRequestUri, str);
                try {
                    openHttpURLConnection.setRequestMethod("POST");
                    openHttpURLConnection.addRequestProperty("Authorization", "FIS_v2 " + str4);
                    openHttpURLConnection.setDoOutput(true);
                    writeGenerateAuthTokenRequestBodyToOutputStream(openHttpURLConnection);
                    int responseCode = openHttpURLConnection.getResponseCode();
                    this.requestLimiter.setNextRequestTime(responseCode);
                    if (isSuccessfulResponseCode(responseCode)) {
                        readGenerateAuthTokenResponse = readGenerateAuthTokenResponse(openHttpURLConnection);
                    } else {
                        logFisCommunicationError(openHttpURLConnection, (String) null, str, str3);
                        if (responseCode == 401 || responseCode == 404) {
                            readGenerateAuthTokenResponse = TokenResult.builder().setResponseCode(TokenResult.ResponseCode.AUTH_ERROR).build();
                        } else if (responseCode != 429) {
                            if (responseCode < 500 || responseCode >= 600) {
                                logBadConfigError();
                                readGenerateAuthTokenResponse = TokenResult.builder().setResponseCode(TokenResult.ResponseCode.BAD_CONFIG).build();
                            }
                            openHttpURLConnection.disconnect();
                            TrafficStats.clearThreadStatsTag();
                            i++;
                        } else {
                            throw new FirebaseInstallationsException("Firebase servers have received too many requests from this client in a short period of time. Please try again later.", FirebaseInstallationsException.Status.TOO_MANY_REQUESTS);
                        }
                    }
                    openHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    return readGenerateAuthTokenResponse;
                } catch (IOException | AssertionError unused) {
                } catch (Throwable th) {
                    openHttpURLConnection.disconnect();
                    TrafficStats.clearThreadStatsTag();
                    throw th;
                }
            }
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
        throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
    }

    private static void logBadConfigError() {
        Log.e(FIS_TAG, "Firebase Installations can not communicate with Firebase server APIs due to invalid configuration. Please update your Firebase initialization process and set valid Firebase options (API key, Project ID, Application ID) when initializing Firebase.");
    }

    private HttpURLConnection openHttpURLConnection(URL url, String str) throws FirebaseInstallationsException {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(NETWORK_TIMEOUT_MILLIS);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setReadTimeout(NETWORK_TIMEOUT_MILLIS);
            httpURLConnection.addRequestProperty(CONTENT_TYPE_HEADER_KEY, JSON_CONTENT_TYPE);
            httpURLConnection.addRequestProperty(ACCEPT_HEADER_KEY, JSON_CONTENT_TYPE);
            httpURLConnection.addRequestProperty(CONTENT_ENCODING_HEADER_KEY, GZIP_CONTENT_ENCODING);
            httpURLConnection.addRequestProperty(CACHE_CONTROL_HEADER_KEY, CACHE_CONTROL_DIRECTIVE);
            httpURLConnection.addRequestProperty(X_ANDROID_PACKAGE_HEADER_KEY, this.context.getPackageName());
            HeartBeatController heartBeatController = this.heartBeatProvider.get();
            if (heartBeatController != null) {
                try {
                    httpURLConnection.addRequestProperty(HEART_BEAT_HEADER, (String) Tasks.await(heartBeatController.getHeartBeatsHeader()));
                } catch (ExecutionException e) {
                    Log.w("ContentValues", "Failed to get heartbeats header", e);
                } catch (InterruptedException e2) {
                    Thread.currentThread().interrupt();
                    Log.w("ContentValues", "Failed to get heartbeats header", e2);
                }
            }
            httpURLConnection.addRequestProperty(X_ANDROID_CERT_HEADER_KEY, getFingerprintHashForPackage());
            httpURLConnection.addRequestProperty(API_KEY_HEADER, str);
            return httpURLConnection;
        } catch (IOException unused) {
            throw new FirebaseInstallationsException("Firebase Installations Service is unavailable. Please try again later.", FirebaseInstallationsException.Status.UNAVAILABLE);
        }
    }

    private InstallationResponse readCreateResponse(HttpURLConnection httpURLConnection) throws AssertionError, IOException {
        InputStream inputStream = httpURLConnection.getInputStream();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, UTF_8));
        TokenResult.Builder builder = TokenResult.builder();
        InstallationResponse.Builder builder2 = InstallationResponse.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("name")) {
                builder2.setUri(jsonReader.nextString());
            } else if (nextName.equals("fid")) {
                builder2.setFid(jsonReader.nextString());
            } else if (nextName.equals("refreshToken")) {
                builder2.setRefreshToken(jsonReader.nextString());
            } else if (nextName.equals("authToken")) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String nextName2 = jsonReader.nextName();
                    if (nextName2.equals("token")) {
                        builder.setToken(jsonReader.nextString());
                    } else if (nextName2.equals("expiresIn")) {
                        builder.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(jsonReader.nextString()));
                    } else {
                        jsonReader.skipValue();
                    }
                }
                builder2.setAuthToken(builder.build());
                jsonReader.endObject();
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        jsonReader.close();
        inputStream.close();
        return builder2.setResponseCode(InstallationResponse.ResponseCode.OK).build();
    }

    private TokenResult readGenerateAuthTokenResponse(HttpURLConnection httpURLConnection) throws AssertionError, IOException {
        InputStream inputStream = httpURLConnection.getInputStream();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, UTF_8));
        TokenResult.Builder builder = TokenResult.builder();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("token")) {
                builder.setToken(jsonReader.nextString());
            } else if (nextName.equals("expiresIn")) {
                builder.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(jsonReader.nextString()));
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        jsonReader.close();
        inputStream.close();
        return builder.setResponseCode(TokenResult.ResponseCode.OK).build();
    }

    private String getFingerprintHashForPackage() {
        try {
            Context context2 = this.context;
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context2, context2.getPackageName());
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            Log.e("ContentValues", "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ContentValues", "No such package: " + this.context.getPackageName(), e);
            return null;
        }
    }

    static long parseTokenExpirationTimestamp(String str) {
        Preconditions.checkArgument(EXPIRATION_TIMESTAMP_PATTERN.matcher(str).matches(), PARSING_EXPIRATION_TIME_ERROR_MESSAGE);
        if (str == null || str.length() == 0) {
            return 0;
        }
        return Long.parseLong(str.substring(0, str.length() - 1));
    }

    private static void logFisCommunicationError(HttpURLConnection httpURLConnection, String str, String str2, String str3) {
        String readErrorResponse = readErrorResponse(httpURLConnection);
        if (!TextUtils.isEmpty(readErrorResponse)) {
            Log.w(FIS_TAG, readErrorResponse);
            Log.w(FIS_TAG, availableFirebaseOptions(str, str2, str3));
        }
    }

    private static String availableFirebaseOptions(String str, String str2, String str3) {
        String str4;
        if (TextUtils.isEmpty(str)) {
            str4 = "";
        } else {
            str4 = ", " + str;
        }
        return String.format("Firebase options used while communicating with Firebase server APIs: %s, %s%s", new Object[]{str2, str3, str4});
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0048 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String readErrorResponse(java.net.HttpURLConnection r5) {
        /*
            java.io.InputStream r0 = r5.getErrorStream()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.BufferedReader r2 = new java.io.BufferedReader
            java.io.InputStreamReader r3 = new java.io.InputStreamReader
            java.nio.charset.Charset r4 = UTF_8
            r3.<init>(r0, r4)
            r2.<init>(r3)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            r0.<init>()     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
        L_0x0019:
            java.lang.String r3 = r2.readLine()     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            if (r3 == 0) goto L_0x002a
            r0.append(r3)     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            r3 = 10
            r0.append(r3)     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            goto L_0x0019
        L_0x0028:
            r5 = move-exception
            goto L_0x0044
        L_0x002a:
            java.lang.String r3 = "Error when communicating with the Firebase Installations server API. HTTP response: [%d %s: %s]"
            int r4 = r5.getResponseCode()     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            java.lang.String r5 = r5.getResponseMessage()     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            java.lang.Object[] r5 = new java.lang.Object[]{r4, r5, r0}     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            java.lang.String r5 = java.lang.String.format(r3, r5)     // Catch:{ IOException -> 0x0048, all -> 0x0028 }
            r2.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            return r5
        L_0x0044:
            r2.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            throw r5
        L_0x0048:
            r2.close()     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.remote.FirebaseInstallationServiceClient.readErrorResponse(java.net.HttpURLConnection):java.lang.String");
    }
}
