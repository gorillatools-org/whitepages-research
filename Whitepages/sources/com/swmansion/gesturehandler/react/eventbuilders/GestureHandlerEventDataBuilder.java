package com.swmansion.gesturehandler.react.eventbuilders;

import com.facebook.react.bridge.WritableMap;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.swmansion.gesturehandler.core.GestureHandler;
import kotlin.jvm.internal.Intrinsics;

public abstract class GestureHandlerEventDataBuilder {
    private final int handlerTag;
    private final int numberOfPointers;
    private final int pointerType;
    private final int state;

    public GestureHandlerEventDataBuilder(GestureHandler gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "handler");
        this.numberOfPointers = gestureHandler.getNumberOfPointers();
        this.handlerTag = gestureHandler.getTag();
        this.state = gestureHandler.getState();
        this.pointerType = gestureHandler.getPointerType();
    }

    public void buildEventData(WritableMap writableMap) {
        Intrinsics.checkNotNullParameter(writableMap, "eventData");
        writableMap.putInt("numberOfPointers", this.numberOfPointers);
        writableMap.putInt("handlerTag", this.handlerTag);
        writableMap.putInt(RemoteConfigConstants.ResponseFieldKey.STATE, this.state);
        writableMap.putInt("pointerType", this.pointerType);
    }
}
