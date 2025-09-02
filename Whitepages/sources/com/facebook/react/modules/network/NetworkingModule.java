package com.facebook.react.modules.network;

import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.network.OkHttpCallUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.google.firebase.perf.FirebasePerformance;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.ByteString;
import okio.GzipSource;
import okio.Okio;
import okio.Source;

@ReactModule(name = "Networking")
public final class NetworkingModule extends NativeNetworkingAndroidSpec {
    private static final int CHUNK_TIMEOUT_NS = 100000000;
    private static final String CONTENT_ENCODING_HEADER_NAME = "content-encoding";
    private static final String CONTENT_TYPE_HEADER_NAME = "content-type";
    private static final int MAX_CHUNK_SIZE_BETWEEN_FLUSHES = 8192;
    private static final String REQUEST_BODY_KEY_BASE64 = "base64";
    private static final String REQUEST_BODY_KEY_FORMDATA = "formData";
    private static final String REQUEST_BODY_KEY_STRING = "string";
    private static final String REQUEST_BODY_KEY_URI = "uri";
    private static final String TAG = "Networking";
    private static final String USER_AGENT_HEADER_NAME = "user-agent";
    private static CustomClientBuilder customClientBuilder;
    private final OkHttpClient mClient;
    private final ForwardingCookieHandler mCookieHandler;
    private final CookieJarContainer mCookieJarContainer;
    private final String mDefaultUserAgent;
    private final List<RequestBodyHandler> mRequestBodyHandlers;
    private final Set<Integer> mRequestIds;
    /* access modifiers changed from: private */
    public final List<ResponseHandler> mResponseHandlers;
    /* access modifiers changed from: private */
    public boolean mShuttingDown;
    private final List<UriHandler> mUriHandlers;

    @Deprecated
    public interface CustomClientBuilder extends CustomClientBuilder {
    }

    public interface RequestBodyHandler {
        boolean supports(ReadableMap readableMap);

        RequestBody toRequestBody(ReadableMap readableMap, String str);
    }

    public interface ResponseHandler {
        boolean supports(String str);

        WritableMap toResponseData(ResponseBody responseBody) throws IOException;
    }

    public interface UriHandler {
        WritableMap fetch(Uri uri) throws IOException;

        boolean supports(Uri uri, String str);
    }

    /* access modifiers changed from: private */
    public static boolean shouldDispatch(long j, long j2) {
        return j2 + 100000000 < j;
    }

    public void addListener(String str) {
    }

    public void removeListeners(double d) {
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient, List<NetworkInterceptorCreator> list) {
        super(reactApplicationContext);
        this.mCookieHandler = new ForwardingCookieHandler();
        this.mRequestIds = new HashSet();
        this.mRequestBodyHandlers = new ArrayList();
        this.mUriHandlers = new ArrayList();
        this.mResponseHandlers = new ArrayList();
        this.mShuttingDown = false;
        if (list != null) {
            OkHttpClient.Builder newBuilder = okHttpClient.newBuilder();
            for (NetworkInterceptorCreator create : list) {
                newBuilder.addNetworkInterceptor(create.create());
            }
            okHttpClient = newBuilder.build();
        }
        this.mClient = okHttpClient;
        if (okHttpClient.cookieJar() instanceof CookieJarContainer) {
            this.mCookieJarContainer = (CookieJarContainer) okHttpClient.cookieJar();
        } else {
            this.mCookieJarContainer = null;
        }
        this.mDefaultUserAgent = str;
    }

    NetworkingModule(ReactApplicationContext reactApplicationContext, String str, OkHttpClient okHttpClient) {
        this(reactApplicationContext, str, okHttpClient, (List<NetworkInterceptorCreator>) null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, (String) null, OkHttpClientProvider.createClient(reactApplicationContext), (List<NetworkInterceptorCreator>) null);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, List<NetworkInterceptorCreator> list) {
        this(reactApplicationContext, (String) null, OkHttpClientProvider.createClient(reactApplicationContext), list);
    }

    public NetworkingModule(ReactApplicationContext reactApplicationContext, String str) {
        this(reactApplicationContext, str, OkHttpClientProvider.createClient(reactApplicationContext), (List<NetworkInterceptorCreator>) null);
    }

    public static void setCustomClientBuilder(CustomClientBuilder customClientBuilder2) {
        customClientBuilder = customClientBuilder2;
    }

    private static void applyCustomBuilder(OkHttpClient.Builder builder) {
        CustomClientBuilder customClientBuilder2 = customClientBuilder;
        if (customClientBuilder2 != null) {
            customClientBuilder2.apply(builder);
        }
    }

    public void initialize() {
        CookieJarContainer cookieJarContainer = this.mCookieJarContainer;
        if (cookieJarContainer != null) {
            cookieJarContainer.setCookieJar(new JavaNetCookieJar(this.mCookieHandler));
        }
    }

    public void invalidate() {
        this.mShuttingDown = true;
        cancelAllRequests();
        this.mCookieHandler.destroy();
        CookieJarContainer cookieJarContainer = this.mCookieJarContainer;
        if (cookieJarContainer != null) {
            cookieJarContainer.removeCookieJar();
        }
        this.mRequestBodyHandlers.clear();
        this.mResponseHandlers.clear();
        this.mUriHandlers.clear();
    }

    public void addUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.add(uriHandler);
    }

    public void addRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.add(requestBodyHandler);
    }

    public void addResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.add(responseHandler);
    }

    public void removeUriHandler(UriHandler uriHandler) {
        this.mUriHandlers.remove(uriHandler);
    }

    public void removeRequestBodyHandler(RequestBodyHandler requestBodyHandler) {
        this.mRequestBodyHandlers.remove(requestBodyHandler);
    }

    public void removeResponseHandler(ResponseHandler responseHandler) {
        this.mResponseHandlers.remove(responseHandler);
    }

    public void sendRequest(String str, String str2, double d, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, double d2, boolean z2) {
        int i = (int) d;
        try {
            sendRequestInternal(str, str2, i, readableArray, readableMap, str3, z, (int) d2, z2);
        } catch (Throwable th) {
            Throwable th2 = th;
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to send url request: ");
            String str4 = str2;
            sb.append(str2);
            FLog.e("Networking", sb.toString(), th2);
            ResponseUtil.onRequestError(getReactApplicationContextIfActiveOrWarn(), i, th2.getMessage(), th2);
        }
    }

    public void sendRequestInternal(String str, String str2, int i, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, int i2, boolean z2) {
        RequestBodyHandler requestBodyHandler;
        RequestBody requestBody;
        Charset charset;
        final ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        try {
            Uri parse = Uri.parse(str2);
            for (UriHandler next : this.mUriHandlers) {
                if (next.supports(parse, str3)) {
                    ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, i, next.fetch(parse));
                    ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i);
                    return;
                }
            }
            try {
                Request.Builder url = new Request.Builder().url(str2);
                if (i != 0) {
                    url.tag(Integer.valueOf(i));
                }
                OkHttpClient.Builder newBuilder = this.mClient.newBuilder();
                applyCustomBuilder(newBuilder);
                if (!z2) {
                    newBuilder.cookieJar(CookieJar.NO_COOKIES);
                }
                if (z) {
                    newBuilder.addNetworkInterceptor(new NetworkingModule$$ExternalSyntheticLambda0(this, str3, reactApplicationContextIfActiveOrWarn, i));
                }
                if (i2 != this.mClient.callTimeoutMillis()) {
                    newBuilder.callTimeout((long) i2, TimeUnit.MILLISECONDS);
                }
                OkHttpClient build = newBuilder.build();
                Headers extractHeaders = extractHeaders(readableArray, readableMap);
                if (extractHeaders == null) {
                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Unrecognized headers format", (Throwable) null);
                    return;
                }
                String str4 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
                String str5 = extractHeaders.get(CONTENT_ENCODING_HEADER_NAME);
                url.headers(extractHeaders);
                if (readableMap != null) {
                    Iterator<RequestBodyHandler> it = this.mRequestBodyHandlers.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        requestBodyHandler = it.next();
                        if (requestBodyHandler.supports(readableMap)) {
                            break;
                        }
                    }
                }
                requestBodyHandler = null;
                if (readableMap != null) {
                    Locale locale = Locale.ROOT;
                    if (!str.toLowerCase(locale).equals("get") && !str.toLowerCase(locale).equals("head")) {
                        if (requestBodyHandler != null) {
                            requestBody = requestBodyHandler.toRequestBody(readableMap, str4);
                        } else if (readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
                            if (str4 == null) {
                                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Payload is set but no content-type header specified", (Throwable) null);
                                return;
                            }
                            String string = readableMap.getString(REQUEST_BODY_KEY_STRING);
                            MediaType parse2 = MediaType.parse(str4);
                            if (RequestBodyUtil.isGzipEncoding(str5)) {
                                requestBody = RequestBodyUtil.createGzip(parse2, string);
                                if (requestBody == null) {
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Failed to gzip request body", (Throwable) null);
                                    return;
                                }
                            } else {
                                if (parse2 == null) {
                                    charset = StandardCharsets.UTF_8;
                                } else {
                                    charset = parse2.charset(StandardCharsets.UTF_8);
                                }
                                requestBody = RequestBody.create(parse2, string.getBytes(charset));
                            }
                        } else if (readableMap.hasKey(REQUEST_BODY_KEY_BASE64)) {
                            if (str4 == null) {
                                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Payload is set but no content-type header specified", (Throwable) null);
                                return;
                            } else {
                                requestBody = RequestBody.create(MediaType.parse(str4), ByteString.decodeBase64(readableMap.getString(REQUEST_BODY_KEY_BASE64)));
                            }
                        } else if (readableMap.hasKey("uri")) {
                            if (str4 == null) {
                                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Payload is set but no content-type header specified", (Throwable) null);
                                return;
                            }
                            String string2 = readableMap.getString("uri");
                            InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string2);
                            if (fileInputStream == null) {
                                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Could not retrieve file for uri " + string2, (Throwable) null);
                                return;
                            }
                            requestBody = RequestBodyUtil.create(MediaType.parse(str4), fileInputStream);
                        } else if (readableMap.hasKey(REQUEST_BODY_KEY_FORMDATA)) {
                            if (str4 == null) {
                                str4 = "multipart/form-data";
                            }
                            MultipartBody.Builder constructMultipartBody = constructMultipartBody(readableMap.getArray(REQUEST_BODY_KEY_FORMDATA), str4, i);
                            if (constructMultipartBody != null) {
                                requestBody = constructMultipartBody.build();
                            } else {
                                return;
                            }
                        } else {
                            requestBody = RequestBodyUtil.getEmptyBody(str);
                        }
                        url.method(str, wrapRequestBodyWithProgressEmitter(requestBody, i));
                        addRequest(i);
                        final int i3 = i;
                        final String str6 = str3;
                        final boolean z3 = z;
                        build.newCall(url.build()).enqueue(new Callback() {
                            public void onFailure(Call call, IOException iOException) {
                                String str;
                                if (!NetworkingModule.this.mShuttingDown) {
                                    NetworkingModule.this.removeRequest(i3);
                                    if (iOException.getMessage() != null) {
                                        str = iOException.getMessage();
                                    } else {
                                        str = "Error while executing request: " + iOException.getClass().getSimpleName();
                                    }
                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i3, str, iOException);
                                }
                            }

                            public void onResponse(Call call, Response response) throws IOException {
                                if (!NetworkingModule.this.mShuttingDown) {
                                    NetworkingModule.this.removeRequest(i3);
                                    ResponseUtil.onResponseReceived(reactApplicationContextIfActiveOrWarn, i3, response.code(), NetworkingModule.translateHeaders(response.headers()), response.request().url().toString());
                                    try {
                                        ResponseBody body = response.body();
                                        if ("gzip".equalsIgnoreCase(response.header("Content-Encoding")) && body != null) {
                                            GzipSource gzipSource = new GzipSource(body.source());
                                            String header = response.header("Content-Type");
                                            body = ResponseBody.create(header != null ? MediaType.parse(header) : null, -1, Okio.buffer((Source) gzipSource));
                                        }
                                        for (ResponseHandler responseHandler : NetworkingModule.this.mResponseHandlers) {
                                            if (responseHandler.supports(str6)) {
                                                ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, i3, responseHandler.toResponseData(body));
                                                ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i3);
                                                return;
                                            }
                                        }
                                        if (z3) {
                                            if (str6.equals("text")) {
                                                NetworkingModule.this.readWithProgress(i3, body);
                                                ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i3);
                                                return;
                                            }
                                        }
                                        String str = "";
                                        if (str6.equals("text")) {
                                            try {
                                                str = body.string();
                                            } catch (IOException e) {
                                                if (!response.request().method().equalsIgnoreCase(FirebasePerformance.HttpMethod.HEAD)) {
                                                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i3, e.getMessage(), e);
                                                }
                                            }
                                        } else if (str6.equals(NetworkingModule.REQUEST_BODY_KEY_BASE64)) {
                                            str = Base64.encodeToString(body.bytes(), 2);
                                        }
                                        ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, i3, str);
                                        ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i3);
                                    } catch (IOException e2) {
                                        ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i3, e2.getMessage(), e2);
                                    }
                                }
                            }
                        });
                    }
                }
                requestBody = RequestBodyUtil.getEmptyBody(str);
                url.method(str, wrapRequestBodyWithProgressEmitter(requestBody, i));
                addRequest(i);
                final int i32 = i;
                final String str62 = str3;
                final boolean z32 = z;
                build.newCall(url.build()).enqueue(new Callback() {
                    public void onFailure(Call call, IOException iOException) {
                        String str;
                        if (!NetworkingModule.this.mShuttingDown) {
                            NetworkingModule.this.removeRequest(i32);
                            if (iOException.getMessage() != null) {
                                str = iOException.getMessage();
                            } else {
                                str = "Error while executing request: " + iOException.getClass().getSimpleName();
                            }
                            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i32, str, iOException);
                        }
                    }

                    public void onResponse(Call call, Response response) throws IOException {
                        if (!NetworkingModule.this.mShuttingDown) {
                            NetworkingModule.this.removeRequest(i32);
                            ResponseUtil.onResponseReceived(reactApplicationContextIfActiveOrWarn, i32, response.code(), NetworkingModule.translateHeaders(response.headers()), response.request().url().toString());
                            try {
                                ResponseBody body = response.body();
                                if ("gzip".equalsIgnoreCase(response.header("Content-Encoding")) && body != null) {
                                    GzipSource gzipSource = new GzipSource(body.source());
                                    String header = response.header("Content-Type");
                                    body = ResponseBody.create(header != null ? MediaType.parse(header) : null, -1, Okio.buffer((Source) gzipSource));
                                }
                                for (ResponseHandler responseHandler : NetworkingModule.this.mResponseHandlers) {
                                    if (responseHandler.supports(str62)) {
                                        ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, i32, responseHandler.toResponseData(body));
                                        ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i32);
                                        return;
                                    }
                                }
                                if (z32) {
                                    if (str62.equals("text")) {
                                        NetworkingModule.this.readWithProgress(i32, body);
                                        ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i32);
                                        return;
                                    }
                                }
                                String str = "";
                                if (str62.equals("text")) {
                                    try {
                                        str = body.string();
                                    } catch (IOException e) {
                                        if (!response.request().method().equalsIgnoreCase(FirebasePerformance.HttpMethod.HEAD)) {
                                            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i32, e.getMessage(), e);
                                        }
                                    }
                                } else if (str62.equals(NetworkingModule.REQUEST_BODY_KEY_BASE64)) {
                                    str = Base64.encodeToString(body.bytes(), 2);
                                }
                                ResponseUtil.onDataReceived(reactApplicationContextIfActiveOrWarn, i32, str);
                                ResponseUtil.onRequestSuccess(reactApplicationContextIfActiveOrWarn, i32);
                            } catch (IOException e2) {
                                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i32, e2.getMessage(), e2);
                            }
                        }
                    }
                });
            } catch (Exception e) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, e.getMessage(), (Throwable) null);
            }
        } catch (IOException e2) {
            ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, e2.getMessage(), e2);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Response lambda$sendRequestInternal$0(final String str, final ReactApplicationContext reactApplicationContext, final int i, Interceptor.Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        return proceed.newBuilder().body(new ProgressResponseBody(proceed.body(), new ProgressListener() {
            long last = System.nanoTime();

            public void onProgress(long j, long j2, boolean z) {
                long nanoTime = System.nanoTime();
                if ((z || NetworkingModule.shouldDispatch(nanoTime, this.last)) && !str.equals("text")) {
                    ResponseUtil.onDataReceivedProgress(reactApplicationContext, i, j, j2);
                    this.last = nanoTime;
                }
            }
        })).build();
    }

    private RequestBody wrapRequestBodyWithProgressEmitter(RequestBody requestBody, final int i) {
        if (requestBody == null) {
            return null;
        }
        final ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        return RequestBodyUtil.createProgressRequest(requestBody, new ProgressListener() {
            long last = System.nanoTime();

            public void onProgress(long j, long j2, boolean z) {
                long nanoTime = System.nanoTime();
                if (z || NetworkingModule.shouldDispatch(nanoTime, this.last)) {
                    ResponseUtil.onDataSend(reactApplicationContextIfActiveOrWarn, i, j, j2);
                    this.last = nanoTime;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void readWithProgress(int i, ResponseBody responseBody) throws IOException {
        long j;
        Charset charset;
        long j2 = -1;
        try {
            ProgressResponseBody progressResponseBody = (ProgressResponseBody) responseBody;
            j = progressResponseBody.totalBytesRead();
            try {
                j2 = progressResponseBody.contentLength();
            } catch (ClassCastException unused) {
            }
        } catch (ClassCastException unused2) {
            j = -1;
        }
        if (responseBody.contentType() == null) {
            charset = StandardCharsets.UTF_8;
        } else {
            charset = responseBody.contentType().charset(StandardCharsets.UTF_8);
        }
        ProgressiveStringDecoder progressiveStringDecoder = new ProgressiveStringDecoder(charset);
        InputStream byteStream = responseBody.byteStream();
        try {
            byte[] bArr = new byte[8192];
            ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
            while (true) {
                int read = byteStream.read(bArr);
                if (read != -1) {
                    ResponseUtil.onIncrementalDataReceived(reactApplicationContextIfActiveOrWarn, i, progressiveStringDecoder.decodeNext(bArr, read), j, j2);
                } else {
                    return;
                }
            }
        } finally {
            byteStream.close();
        }
    }

    private synchronized void addRequest(int i) {
        this.mRequestIds.add(Integer.valueOf(i));
    }

    /* access modifiers changed from: private */
    public synchronized void removeRequest(int i) {
        this.mRequestIds.remove(Integer.valueOf(i));
    }

    private synchronized void cancelAllRequests() {
        try {
            for (Integer intValue : this.mRequestIds) {
                cancelRequest(intValue.intValue());
            }
            this.mRequestIds.clear();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static WritableMap translateHeaders(Headers headers) {
        Bundle bundle = new Bundle();
        for (int i = 0; i < headers.size(); i++) {
            String name = headers.name(i);
            if (bundle.containsKey(name)) {
                bundle.putString(name, bundle.getString(name) + ", " + headers.value(i));
            } else {
                bundle.putString(name, headers.value(i));
            }
        }
        return Arguments.fromBundle(bundle);
    }

    public void abortRequest(double d) {
        int i = (int) d;
        cancelRequest(i);
        removeRequest(i);
    }

    private void cancelRequest(int i) {
        OkHttpCallUtil.cancelTag(this.mClient, Integer.valueOf(i));
    }

    @ReactMethod
    public void clearCookies(com.facebook.react.bridge.Callback callback) {
        this.mCookieHandler.clearCookies(callback);
    }

    private MultipartBody.Builder constructMultipartBody(ReadableArray readableArray, String str, int i) {
        MediaType mediaType;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MediaType.parse(str));
        ReactApplicationContext reactApplicationContextIfActiveOrWarn = getReactApplicationContextIfActiveOrWarn();
        int size = readableArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ReadableMap map = readableArray.getMap(i2);
            Headers extractHeaders = extractHeaders(map.getArray("headers"), (ReadableMap) null);
            if (extractHeaders == null) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Missing or invalid header format for FormData part.", (Throwable) null);
                return null;
            }
            String str2 = extractHeaders.get(CONTENT_TYPE_HEADER_NAME);
            if (str2 != null) {
                mediaType = MediaType.parse(str2);
                extractHeaders = extractHeaders.newBuilder().removeAll(CONTENT_TYPE_HEADER_NAME).build();
            } else {
                mediaType = null;
            }
            if (map.hasKey(REQUEST_BODY_KEY_STRING)) {
                builder.addPart(extractHeaders, RequestBody.create(mediaType, map.getString(REQUEST_BODY_KEY_STRING)));
            } else if (!map.hasKey("uri")) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Unrecognized FormData part.", (Throwable) null);
            } else if (mediaType == null) {
                ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Binary FormData part needs a content-type header.", (Throwable) null);
                return null;
            } else {
                String string = map.getString("uri");
                InputStream fileInputStream = RequestBodyUtil.getFileInputStream(getReactApplicationContext(), string);
                if (fileInputStream == null) {
                    ResponseUtil.onRequestError(reactApplicationContextIfActiveOrWarn, i, "Could not retrieve file for uri " + string, (Throwable) null);
                    return null;
                }
                builder.addPart(extractHeaders, RequestBodyUtil.create(mediaType, fileInputStream));
            }
        }
        return builder;
    }

    private Headers extractHeaders(ReadableArray readableArray, ReadableMap readableMap) {
        String str;
        if (readableArray == null) {
            return null;
        }
        Headers.Builder builder = new Headers.Builder();
        int size = readableArray.size();
        int i = 0;
        while (i < size) {
            ReadableArray array = readableArray.getArray(i);
            if (array != null && array.size() == 2) {
                String stripHeaderName = HeaderUtil.stripHeaderName(array.getString(0));
                String string = array.getString(1);
                if (!(stripHeaderName == null || string == null)) {
                    builder.addUnsafeNonAscii(stripHeaderName, string);
                    i++;
                }
            }
            return null;
        }
        if (builder.get(USER_AGENT_HEADER_NAME) == null && (str = this.mDefaultUserAgent) != null) {
            builder.add(USER_AGENT_HEADER_NAME, str);
        }
        if (readableMap == null || !readableMap.hasKey(REQUEST_BODY_KEY_STRING)) {
            builder.removeAll(CONTENT_ENCODING_HEADER_NAME);
        }
        return builder.build();
    }
}
