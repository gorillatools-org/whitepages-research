package com.facebook.react.devsupport;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.modules.debug.FpsDebugFrameCallback;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class FpsView extends FrameLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int UPDATE_INTERVAL_MS = 500;
    private final FPSMonitorRunnable fpsMonitorRunnable = new FPSMonitorRunnable();
    /* access modifiers changed from: private */
    public final FpsDebugFrameCallback frameCallback;
    private final TextView textView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FpsView(ReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNull(reactContext);
        View.inflate(reactContext, R.layout.fps_view, this);
        View findViewById = findViewById(R.id.fps_text);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
        this.textView = (TextView) findViewById;
        this.frameCallback = new FpsDebugFrameCallback(reactContext);
        setCurrentFPS(0.0d, 0.0d, 0, 0);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.frameCallback.reset();
        FpsDebugFrameCallback.start$default(this.frameCallback, 0.0d, 1, (Object) null);
        this.fpsMonitorRunnable.start();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.frameCallback.stop();
        this.fpsMonitorRunnable.stop();
    }

    /* access modifiers changed from: private */
    public final void setCurrentFPS(double d, double d2, int i, int i2) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "UI: %.1f fps\n%d dropped so far\n%d stutters (4+) so far\nJS: %.1f fps", Arrays.copyOf(new Object[]{Double.valueOf(d), Integer.valueOf(i), Integer.valueOf(i2), Double.valueOf(d2)}, 4));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        this.textView.setText(format);
        FLog.d(ReactConstants.TAG, format);
    }

    private final class FPSMonitorRunnable implements Runnable {
        private boolean shouldStop;
        private int total4PlusFrameStutters;
        private int totalFramesDropped;

        public FPSMonitorRunnable() {
        }

        public void run() {
            if (!this.shouldStop) {
                this.totalFramesDropped += FpsView.this.frameCallback.getExpectedNumFrames() - FpsView.this.frameCallback.getNumFrames();
                this.total4PlusFrameStutters += FpsView.this.frameCallback.get4PlusFrameStutters();
                FpsView fpsView = FpsView.this;
                fpsView.setCurrentFPS(fpsView.frameCallback.getFps(), FpsView.this.frameCallback.getJsFPS(), this.totalFramesDropped, this.total4PlusFrameStutters);
                FpsView.this.frameCallback.reset();
                FpsView.this.postDelayed(this, 500);
            }
        }

        public final void start() {
            this.shouldStop = false;
            FpsView.this.post(this);
        }

        public final void stop() {
            this.shouldStop = true;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
