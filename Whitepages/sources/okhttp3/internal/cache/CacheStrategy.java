package okhttp3.internal.cache;

import com.facebook.react.views.image.ReactImageView;
import com.salesforce.marketingcloud.sfmcsdk.components.http.NetworkManager;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;

public final class CacheStrategy {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Response cacheResponse;
    private final Request networkRequest;

    public CacheStrategy(Request request, Response response) {
        this.networkRequest = request;
        this.cacheResponse = response;
    }

    public final Request getNetworkRequest() {
        return this.networkRequest;
    }

    public final Response getCacheResponse() {
        return this.cacheResponse;
    }

    public static final class Factory {
        private int ageSeconds = -1;
        private final Response cacheResponse;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private final long nowMillis;
        private long receivedResponseMillis;
        private final Request request;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;

        public Factory(long j, Request request2, Response response) {
            Intrinsics.checkNotNullParameter(request2, "request");
            this.nowMillis = j;
            this.request = request2;
            this.cacheResponse = response;
            if (response != null) {
                this.sentRequestMillis = response.sentRequestAtMillis();
                this.receivedResponseMillis = response.receivedResponseAtMillis();
                Headers headers = response.headers();
                int size = headers.size();
                for (int i = 0; i < size; i++) {
                    String name = headers.name(i);
                    String value = headers.value(i);
                    if (StringsKt.equals(name, "Date", true)) {
                        this.servedDate = DatesKt.toHttpDateOrNull(value);
                        this.servedDateString = value;
                    } else if (StringsKt.equals(name, "Expires", true)) {
                        this.expires = DatesKt.toHttpDateOrNull(value);
                    } else if (StringsKt.equals(name, "Last-Modified", true)) {
                        this.lastModified = DatesKt.toHttpDateOrNull(value);
                        this.lastModifiedString = value;
                    } else if (StringsKt.equals(name, "ETag", true)) {
                        this.etag = value;
                    } else if (StringsKt.equals(name, "Age", true)) {
                        this.ageSeconds = Util.toNonNegativeInt(value, -1);
                    }
                }
            }
        }

        public final Request getRequest$okhttp() {
            return this.request;
        }

        private final boolean isFreshnessLifetimeHeuristic() {
            Response response = this.cacheResponse;
            Intrinsics.checkNotNull(response);
            return response.cacheControl().maxAgeSeconds() == -1 && this.expires == null;
        }

        public final CacheStrategy compute() {
            CacheStrategy computeCandidate = computeCandidate();
            return (computeCandidate.getNetworkRequest() == null || !this.request.cacheControl().onlyIfCached()) ? computeCandidate : new CacheStrategy((Request) null, (Response) null);
        }

        private final CacheStrategy computeCandidate() {
            String str;
            if (this.cacheResponse == null) {
                return new CacheStrategy(this.request, (Response) null);
            }
            if (this.request.isHttps() && this.cacheResponse.handshake() == null) {
                return new CacheStrategy(this.request, (Response) null);
            }
            if (!CacheStrategy.Companion.isCacheable(this.cacheResponse, this.request)) {
                return new CacheStrategy(this.request, (Response) null);
            }
            CacheControl cacheControl = this.request.cacheControl();
            if (cacheControl.noCache() || hasConditions(this.request)) {
                return new CacheStrategy(this.request, (Response) null);
            }
            CacheControl cacheControl2 = this.cacheResponse.cacheControl();
            long cacheResponseAge = cacheResponseAge();
            long computeFreshnessLifetime = computeFreshnessLifetime();
            if (cacheControl.maxAgeSeconds() != -1) {
                computeFreshnessLifetime = Math.min(computeFreshnessLifetime, TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds()));
            }
            long j = 0;
            long millis = cacheControl.minFreshSeconds() != -1 ? TimeUnit.SECONDS.toMillis((long) cacheControl.minFreshSeconds()) : 0;
            if (!cacheControl2.mustRevalidate() && cacheControl.maxStaleSeconds() != -1) {
                j = TimeUnit.SECONDS.toMillis((long) cacheControl.maxStaleSeconds());
            }
            if (!cacheControl2.noCache()) {
                long j2 = millis + cacheResponseAge;
                if (j2 < j + computeFreshnessLifetime) {
                    Response.Builder newBuilder = this.cacheResponse.newBuilder();
                    if (j2 >= computeFreshnessLifetime) {
                        newBuilder.addHeader("Warning", "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (cacheResponseAge > NetworkManager.MAX_SERVER_RETRY && isFreshnessLifetimeHeuristic()) {
                        newBuilder.addHeader("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new CacheStrategy((Request) null, newBuilder.build());
                }
            }
            String str2 = this.etag;
            if (str2 != null) {
                str = "If-None-Match";
            } else {
                if (this.lastModified != null) {
                    str2 = this.lastModifiedString;
                } else if (this.servedDate == null) {
                    return new CacheStrategy(this.request, (Response) null);
                } else {
                    str2 = this.servedDateString;
                }
                str = "If-Modified-Since";
            }
            Headers.Builder newBuilder2 = this.request.headers().newBuilder();
            Intrinsics.checkNotNull(str2);
            newBuilder2.addLenient$okhttp(str, str2);
            return new CacheStrategy(this.request.newBuilder().headers(newBuilder2.build()).build(), this.cacheResponse);
        }

        private final long computeFreshnessLifetime() {
            Response response = this.cacheResponse;
            Intrinsics.checkNotNull(response);
            CacheControl cacheControl = response.cacheControl();
            if (cacheControl.maxAgeSeconds() != -1) {
                return TimeUnit.SECONDS.toMillis((long) cacheControl.maxAgeSeconds());
            }
            Date date = this.expires;
            if (date != null) {
                Date date2 = this.servedDate;
                long time = date.getTime() - (date2 != null ? date2.getTime() : this.receivedResponseMillis);
                if (time > 0) {
                    return time;
                }
                return 0;
            } else if (this.lastModified == null || this.cacheResponse.request().url().query() != null) {
                return 0;
            } else {
                Date date3 = this.servedDate;
                long time2 = date3 != null ? date3.getTime() : this.sentRequestMillis;
                Date date4 = this.lastModified;
                Intrinsics.checkNotNull(date4);
                long time3 = time2 - date4.getTime();
                if (time3 > 0) {
                    return time3 / ((long) 10);
                }
                return 0;
            }
        }

        private final long cacheResponseAge() {
            Date date = this.servedDate;
            long j = 0;
            if (date != null) {
                j = Math.max(0, this.receivedResponseMillis - date.getTime());
            }
            int i = this.ageSeconds;
            if (i != -1) {
                j = Math.max(j, TimeUnit.SECONDS.toMillis((long) i));
            }
            long j2 = this.receivedResponseMillis;
            return j + (j2 - this.sentRequestMillis) + (this.nowMillis - j2);
        }

        private final boolean hasConditions(Request request2) {
            return (request2.header("If-Modified-Since") == null && request2.header("If-None-Match") == null) ? false : true;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isCacheable(Response response, Request request) {
            Intrinsics.checkNotNullParameter(response, "response");
            Intrinsics.checkNotNullParameter(request, "request");
            int code = response.code();
            if (!(code == 200 || code == 410 || code == 414 || code == 501 || code == 203 || code == 204)) {
                if (code != 307) {
                    if (!(code == 308 || code == 404 || code == 405)) {
                        switch (code) {
                            case ReactImageView.REMOTE_IMAGE_FADE_DURATION_MS:
                            case 301:
                                break;
                            case 302:
                                break;
                            default:
                                return false;
                        }
                    }
                }
                if (Response.header$default(response, "Expires", (String) null, 2, (Object) null) == null && response.cacheControl().maxAgeSeconds() == -1 && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                    return false;
                }
            }
            if (response.cacheControl().noStore() || request.cacheControl().noStore()) {
                return false;
            }
            return true;
        }
    }
}
