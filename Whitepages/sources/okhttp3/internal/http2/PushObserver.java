package okhttp3.internal.http2;

import java.io.IOException;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

public interface PushObserver {
    public static final PushObserver CANCEL = new Companion.PushObserverCancel();
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException;

    boolean onHeaders(int i, List<Header> list, boolean z);

    boolean onRequest(int i, List<Header> list);

    void onReset(int i, ErrorCode errorCode);

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static final class PushObserverCancel implements PushObserver {
            public boolean onHeaders(int i, List<Header> list, boolean z) {
                Intrinsics.checkNotNullParameter(list, "responseHeaders");
                return true;
            }

            public boolean onRequest(int i, List<Header> list) {
                Intrinsics.checkNotNullParameter(list, "requestHeaders");
                return true;
            }

            public void onReset(int i, ErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            }

            public boolean onData(int i, BufferedSource bufferedSource, int i2, boolean z) throws IOException {
                Intrinsics.checkNotNullParameter(bufferedSource, "source");
                bufferedSource.skip((long) i2);
                return true;
            }
        }
    }
}
