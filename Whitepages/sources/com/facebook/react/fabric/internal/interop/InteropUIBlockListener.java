package com.facebook.react.fabric.internal.interop;

import com.facebook.react.bridge.UIManager;
import com.facebook.react.bridge.UIManagerListener;
import com.facebook.react.fabric.interop.UIBlock;
import com.facebook.react.fabric.interop.UIBlockViewResolver;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class InteropUIBlockListener implements UIManagerListener {
    private final List<UIBlock> afterUIBlocks = new ArrayList();
    private final List<UIBlock> beforeUIBlocks = new ArrayList();

    public void didScheduleMountItems(UIManager uIManager) {
        Intrinsics.checkNotNullParameter(uIManager, "uiManager");
    }

    public final List<UIBlock> getBeforeUIBlocks$ReactAndroid_release() {
        return this.beforeUIBlocks;
    }

    public final List<UIBlock> getAfterUIBlocks$ReactAndroid_release() {
        return this.afterUIBlocks;
    }

    public final synchronized void prependUIBlock(UIBlock uIBlock) {
        Intrinsics.checkNotNullParameter(uIBlock, "block");
        this.beforeUIBlocks.add(uIBlock);
    }

    public final synchronized void addUIBlock(UIBlock uIBlock) {
        Intrinsics.checkNotNullParameter(uIBlock, "block");
        this.afterUIBlocks.add(uIBlock);
    }

    public void willMountItems(UIManager uIManager) {
        Intrinsics.checkNotNullParameter(uIManager, "uiManager");
        if (!this.beforeUIBlocks.isEmpty()) {
            for (UIBlock uIBlock : this.beforeUIBlocks) {
                if (uIManager instanceof UIBlockViewResolver) {
                    uIBlock.execute((UIBlockViewResolver) uIManager);
                }
            }
            this.beforeUIBlocks.clear();
        }
    }

    public void didMountItems(UIManager uIManager) {
        Intrinsics.checkNotNullParameter(uIManager, "uiManager");
        if (!this.afterUIBlocks.isEmpty()) {
            for (UIBlock uIBlock : this.afterUIBlocks) {
                if (uIManager instanceof UIBlockViewResolver) {
                    uIBlock.execute((UIBlockViewResolver) uIManager);
                }
            }
            this.afterUIBlocks.clear();
        }
    }

    public void didDispatchMountItems(UIManager uIManager) {
        Intrinsics.checkNotNullParameter(uIManager, "uiManager");
        didMountItems(uIManager);
    }

    public void willDispatchViewUpdates(UIManager uIManager) {
        Intrinsics.checkNotNullParameter(uIManager, "uiManager");
        willMountItems(uIManager);
    }
}
