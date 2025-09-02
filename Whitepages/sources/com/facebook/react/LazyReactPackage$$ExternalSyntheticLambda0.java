package com.facebook.react;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final /* synthetic */ class LazyReactPackage$$ExternalSyntheticLambda0 implements Iterable {
    public final /* synthetic */ LazyReactPackage f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ LazyReactPackage$$ExternalSyntheticLambda0(LazyReactPackage lazyReactPackage, List list, Map map) {
        this.f$0 = lazyReactPackage;
        this.f$1 = list;
        this.f$2 = map;
    }

    public final Iterator iterator() {
        return this.f$0.lambda$getNativeModuleIterator$0(this.f$1, this.f$2);
    }
}
