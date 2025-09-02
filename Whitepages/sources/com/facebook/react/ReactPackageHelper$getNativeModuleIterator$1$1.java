package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReactPackageHelper$getNativeModuleIterator$1$1 implements Iterator<ModuleHolder>, KMappedMarker {
    final /* synthetic */ List<NativeModule> $nativeModules;
    private int position;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    ReactPackageHelper$getNativeModuleIterator$1$1(List<? extends NativeModule> list) {
        this.$nativeModules = list;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    public ModuleHolder next() {
        List<NativeModule> list = this.$nativeModules;
        int i = this.position;
        this.position = i + 1;
        return new ModuleHolder(list.get(i));
    }

    public boolean hasNext() {
        return this.position < this.$nativeModules.size();
    }
}
