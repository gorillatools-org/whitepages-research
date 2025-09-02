package com.wpconnect;

import android.content.Context;
import android.graphics.Paint;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CallerIdOverlayView extends FrameLayout {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MIN_SWIPE_VELOCITY = 1000;
    private final String DEFAULT_TEXT;
    private TextView displayText;
    private final boolean useModalBackground;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CallerIdOverlayView(Context context, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? false : z);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CallerIdOverlayView(Context context, boolean z) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.useModalBackground = z;
        this.DEFAULT_TEXT = "Loading...";
        LayoutInflater.from(context).inflate(R.layout.caller_id_overlay, this, true);
        this.displayText = (TextView) findViewById(R.id.caller_name);
        setBackgroundBasedOnStyle();
        setLayerType(2, (Paint) null);
        animateEntrance();
        setupSwipeToDismiss();
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final void setBackgroundBasedOnStyle() {
        int i;
        View findViewById = findViewById(R.id.caller_id_root);
        if (this.useModalBackground) {
            i = R.drawable.overlay_bg_with_background;
        } else {
            i = R.drawable.overlay_bg;
        }
        findViewById.setBackgroundResource(i);
    }

    private final void animateEntrance() {
        setAlpha(0.0f);
        animate().alpha(1.0f).setDuration(300).withEndAction(new CallerIdOverlayView$$ExternalSyntheticLambda1(this)).start();
    }

    /* access modifiers changed from: private */
    public static final void animateEntrance$lambda$0(CallerIdOverlayView callerIdOverlayView) {
        callerIdOverlayView.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public final void animateExit() {
        animate().alpha(0.0f).setDuration(200).withEndAction(new CallerIdOverlayView$$ExternalSyntheticLambda2(this)).start();
    }

    /* access modifiers changed from: private */
    public static final void animateExit$lambda$1(CallerIdOverlayView callerIdOverlayView) {
        Context context = callerIdOverlayView.getContext();
        CallerIdOverlayService callerIdOverlayService = context instanceof CallerIdOverlayService ? (CallerIdOverlayService) context : null;
        if (callerIdOverlayService != null) {
            callerIdOverlayService.hideOverlay();
        }
    }

    private final void setupSwipeToDismiss() {
        setOnTouchListener(new CallerIdOverlayView$$ExternalSyntheticLambda0(new GestureDetector(getContext(), new CallerIdOverlayView$setupSwipeToDismiss$gestureDetector$1(this))));
    }

    /* access modifiers changed from: private */
    public static final boolean setupSwipeToDismiss$lambda$2(GestureDetector gestureDetector, View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return false;
    }

    public final void setText() {
        this.displayText.setText(this.DEFAULT_TEXT);
        setVisibility(0);
    }

    public final void updateCallerIdDisplayedText(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        this.displayText.setText(str);
    }

    public final void displayCallerSpam() {
        TextView textView = new TextView(getContext());
        textView.setText("Likely Spam");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = (int) (((float) 2) * textView.getContext().getResources().getDisplayMetrics().density);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(14.0f);
        textView.setTextColor(ContextCompat.getColor(textView.getContext(), 17170455));
        textView.setVisibility(0);
        ((LinearLayout) ((LinearLayout) findViewById(R.id.caller_id_root)).findViewById(R.id.caller_id_text_container)).addView(textView);
    }
}
