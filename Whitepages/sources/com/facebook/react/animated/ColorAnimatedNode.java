package com.facebook.react.animated;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.views.view.ColorUtil;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ColorAnimatedNode extends AnimatedNode implements AnimatedNodeWithUpdateableConfig {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int aNodeId;
    private int bNodeId;
    private int gNodeId;
    private final NativeAnimatedNodesManager nativeAnimatedNodesManager;
    private ReadableMap nativeColor;
    private boolean nativeColorApplied;
    private int rNodeId;
    private final ReactApplicationContext reactApplicationContext;

    public ColorAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager2, ReactApplicationContext reactApplicationContext2) {
        Intrinsics.checkNotNullParameter(readableMap, "config");
        Intrinsics.checkNotNullParameter(nativeAnimatedNodesManager2, "nativeAnimatedNodesManager");
        Intrinsics.checkNotNullParameter(reactApplicationContext2, "reactApplicationContext");
        this.nativeAnimatedNodesManager = nativeAnimatedNodesManager2;
        this.reactApplicationContext = reactApplicationContext2;
        onUpdateConfig(readableMap);
    }

    public final int getColor() {
        tryApplyNativeColor();
        ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.rNodeId);
        ValueAnimatedNode valueAnimatedNode2 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.gNodeId);
        ValueAnimatedNode valueAnimatedNode3 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.bNodeId);
        ValueAnimatedNode valueAnimatedNode4 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.aNodeId);
        double d = 0.0d;
        double d2 = valueAnimatedNode != null ? valueAnimatedNode.nodeValue : 0.0d;
        double d3 = valueAnimatedNode2 != null ? valueAnimatedNode2.nodeValue : 0.0d;
        double d4 = valueAnimatedNode3 != null ? valueAnimatedNode3.nodeValue : 0.0d;
        if (valueAnimatedNode4 != null) {
            d = valueAnimatedNode4.nodeValue;
        }
        return ColorUtil.normalize(d2, d3, d4, d);
    }

    public void onUpdateConfig(ReadableMap readableMap) {
        if (readableMap != null) {
            this.rNodeId = readableMap.getInt("r");
            this.gNodeId = readableMap.getInt("g");
            this.bNodeId = readableMap.getInt("b");
            this.aNodeId = readableMap.getInt("a");
            this.nativeColor = readableMap.getMap("nativeColor");
            this.nativeColorApplied = false;
            tryApplyNativeColor();
            return;
        }
        this.rNodeId = 0;
        this.gNodeId = 0;
        this.bNodeId = 0;
        this.aNodeId = 0;
        this.nativeColor = null;
        this.nativeColorApplied = false;
    }

    public String prettyPrint() {
        int i = this.tag;
        int i2 = this.rNodeId;
        int i3 = this.gNodeId;
        int i4 = this.bNodeId;
        int i5 = this.aNodeId;
        return "ColorAnimatedNode[" + i + "]: r: " + i2 + "  g: " + i3 + " b: " + i4 + " a: " + i5;
    }

    private final void tryApplyNativeColor() {
        Context context;
        if (this.nativeColor != null && !this.nativeColorApplied && (context = getContext()) != null) {
            Integer color = ColorPropConverter.getColor(this.nativeColor, context);
            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.rNodeId);
            ValueAnimatedNode valueAnimatedNode2 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.gNodeId);
            ValueAnimatedNode valueAnimatedNode3 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.bNodeId);
            ValueAnimatedNode valueAnimatedNode4 = (ValueAnimatedNode) this.nativeAnimatedNodesManager.getNodeById(this.aNodeId);
            if (valueAnimatedNode != null) {
                Intrinsics.checkNotNull(color);
                valueAnimatedNode.nodeValue = (double) Color.red(color.intValue());
            }
            if (valueAnimatedNode2 != null) {
                Intrinsics.checkNotNull(color);
                valueAnimatedNode2.nodeValue = (double) Color.green(color.intValue());
            }
            if (valueAnimatedNode3 != null) {
                Intrinsics.checkNotNull(color);
                valueAnimatedNode3.nodeValue = (double) Color.blue(color.intValue());
            }
            if (valueAnimatedNode4 != null) {
                Intrinsics.checkNotNull(color);
                valueAnimatedNode4.nodeValue = ((double) Color.alpha(color.intValue())) / 255.0d;
            }
            this.nativeColorApplied = true;
        }
    }

    private final Context getContext() {
        Activity currentActivity = this.reactApplicationContext.getCurrentActivity();
        return currentActivity != null ? currentActivity : Companion.getContextHelper(this);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final Context getContextHelper(AnimatedNode animatedNode) {
            List<AnimatedNode> list = animatedNode.children;
            if (list == null) {
                return null;
            }
            Iterator<AnimatedNode> it = list.iterator();
            if (!it.hasNext()) {
                return null;
            }
            AnimatedNode next = it.next();
            if (!(next instanceof PropsAnimatedNode)) {
                return ColorAnimatedNode.Companion.getContextHelper(next);
            }
            View connectedView = ((PropsAnimatedNode) next).getConnectedView();
            if (connectedView != null) {
                return connectedView.getContext();
            }
            return null;
        }
    }
}
