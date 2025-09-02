package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.markers.KMappedMarker;

public final class ReactPackageHelper$getNativeModuleIterator$$inlined$Iterable$1 implements Iterable<ModuleHolder>, KMappedMarker {
    final /* synthetic */ List $nativeModules$inlined;

    public ReactPackageHelper$getNativeModuleIterator$$inlined$Iterable$1(List list) {
        this.$nativeModules$inlined = list;
    }

    public Iterator<ModuleHolder> iterator() {
        return new ReactPackageHelper$getNativeModuleIterator$1$1(this.$nativeModules$inlined);
    }
}
