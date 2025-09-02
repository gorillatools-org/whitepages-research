package com.facebook.react.views.debuggingoverlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class DebuggingOverlay extends View {
    private final Paint highlightedElementsPaint;
    private List<RectF> highlightedElementsRectangles;
    private final HashMap<Integer, Runnable> traceUpdateIdToCleanupRunnableMap = new HashMap<>();
    private final Paint traceUpdatePaint;
    private final HashMap<Integer, TraceUpdate> traceUpdatesToDisplayMap = new HashMap<>();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DebuggingOverlay(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint();
        this.traceUpdatePaint = paint;
        Paint paint2 = new Paint();
        this.highlightedElementsPaint = paint2;
        this.highlightedElementsRectangles = new ArrayList();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(6.0f);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(-859248897);
    }

    public final void setTraceUpdates(List<TraceUpdate> list) {
        Intrinsics.checkNotNullParameter(list, "traceUpdates");
        for (TraceUpdate next : list) {
            int id = next.getId();
            if (this.traceUpdateIdToCleanupRunnableMap.containsKey(Integer.valueOf(id))) {
                UiThreadUtil.removeOnUiThread(this.traceUpdateIdToCleanupRunnableMap.get(Integer.valueOf(id)));
                this.traceUpdateIdToCleanupRunnableMap.remove(Integer.valueOf(id));
            }
            this.traceUpdatesToDisplayMap.put(Integer.valueOf(id), next);
        }
        invalidate();
    }

    public final void setHighlightedElementsRectangles(List<RectF> list) {
        Intrinsics.checkNotNullParameter(list, "elementsRectangles");
        this.highlightedElementsRectangles = list;
        invalidate();
    }

    public final void clearElementsHighlights() {
        this.highlightedElementsRectangles.clear();
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        for (TraceUpdate next : this.traceUpdatesToDisplayMap.values()) {
            Intrinsics.checkNotNullExpressionValue(next, "next(...)");
            TraceUpdate traceUpdate = next;
            this.traceUpdatePaint.setColor(traceUpdate.getColor());
            canvas.drawRect(traceUpdate.getRectangle(), this.traceUpdatePaint);
            int id = traceUpdate.getId();
            DebuggingOverlay$$ExternalSyntheticLambda0 debuggingOverlay$$ExternalSyntheticLambda0 = new DebuggingOverlay$$ExternalSyntheticLambda0(this, id);
            if (!this.traceUpdateIdToCleanupRunnableMap.containsKey(Integer.valueOf(id))) {
                this.traceUpdateIdToCleanupRunnableMap.put(Integer.valueOf(id), debuggingOverlay$$ExternalSyntheticLambda0);
                UiThreadUtil.runOnUiThread(debuggingOverlay$$ExternalSyntheticLambda0, 2000);
            }
        }
        for (RectF drawRect : this.highlightedElementsRectangles) {
            canvas.drawRect(drawRect, this.highlightedElementsPaint);
        }
    }

    /* access modifiers changed from: private */
    public static final void onDraw$lambda$0(DebuggingOverlay debuggingOverlay, int i) {
        debuggingOverlay.traceUpdatesToDisplayMap.remove(Integer.valueOf(i));
        debuggingOverlay.traceUpdateIdToCleanupRunnableMap.remove(Integer.valueOf(i));
        debuggingOverlay.invalidate();
    }
}
