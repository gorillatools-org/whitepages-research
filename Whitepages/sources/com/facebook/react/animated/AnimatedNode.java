package com.facebook.react.animated;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public abstract class AnimatedNode {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int DEFAULT_ANIMATED_NODE_CHILD_COUNT = 1;
    public static final int INITIAL_BFS_COLOR = 0;
    public int BFSColor;
    public int activeIncomingNodes;
    public List<AnimatedNode> children;
    public int tag = -1;

    public void onAttachedToNode(AnimatedNode animatedNode) {
        Intrinsics.checkNotNullParameter(animatedNode, "parent");
    }

    public void onDetachedFromNode(AnimatedNode animatedNode) {
        Intrinsics.checkNotNullParameter(animatedNode, "parent");
    }

    public abstract String prettyPrint();

    public void update() {
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final void addChild(AnimatedNode animatedNode) {
        Intrinsics.checkNotNullParameter(animatedNode, "child");
        List list = this.children;
        if (list == null) {
            list = new ArrayList(1);
            this.children = list;
        }
        list.add(animatedNode);
        animatedNode.onAttachedToNode(this);
    }

    public final void removeChild(AnimatedNode animatedNode) {
        Intrinsics.checkNotNullParameter(animatedNode, "child");
        List<AnimatedNode> list = this.children;
        if (list != null) {
            animatedNode.onDetachedFromNode(this);
            list.remove(animatedNode);
        }
    }

    public final String prettyPrintWithChildren() {
        String str;
        List<AnimatedNode> list = this.children;
        String joinToString$default = list != null ? CollectionsKt.joinToString$default(list, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null) : null;
        String prettyPrint = prettyPrint();
        if (joinToString$default == null || StringsKt.isBlank(joinToString$default)) {
            str = "";
        } else {
            str = " children: " + joinToString$default;
        }
        return prettyPrint + str;
    }
}
