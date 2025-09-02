package com.facebook.react.defaults;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.proguard.annotations.DoNotStripAny;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactPackageTurboModuleManagerDelegate;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.runtime.cxxreactpackage.CxxReactPackage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@DoNotStripAny
public final class DefaultTurboModuleManagerDelegate extends ReactPackageTurboModuleManagerDelegate {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public /* synthetic */ DefaultTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List list, List list2, DefaultConstructorMarker defaultConstructorMarker) {
        this(reactApplicationContext, list, list2);
    }

    @DoNotStrip
    public static final native HybridData initHybrid(List<? extends CxxReactPackage> list);

    private DefaultTurboModuleManagerDelegate(ReactApplicationContext reactApplicationContext, List<? extends ReactPackage> list, List<? extends CxxReactPackage> list2) {
        super(reactApplicationContext, list, Companion.initHybrid(list2));
    }

    /* access modifiers changed from: protected */
    public HybridData initHybrid() {
        throw new UnsupportedOperationException("DefaultTurboModuleManagerDelegate.initHybrid() must never be called!");
    }

    public static final class Builder extends ReactPackageTurboModuleManagerDelegate.Builder {
        private final List<Function1> cxxReactPackageProviders = new ArrayList();

        /* access modifiers changed from: private */
        public static final List addCxxReactPackage$lambda$1$lambda$0(Function0 function0, ReactApplicationContext reactApplicationContext) {
            Intrinsics.checkNotNullParameter(reactApplicationContext, "<unused var>");
            return CollectionsKt.listOf(function0.invoke());
        }

        public final Builder addCxxReactPackage(Function0 function0) {
            Intrinsics.checkNotNullParameter(function0, "provider");
            this.cxxReactPackageProviders.add(new DefaultTurboModuleManagerDelegate$Builder$$ExternalSyntheticLambda1(function0));
            return this;
        }

        /* access modifiers changed from: private */
        public static final List addCxxReactPackage$lambda$3$lambda$2(Function1 function1, ReactApplicationContext reactApplicationContext) {
            Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
            return CollectionsKt.listOf(function1.invoke(reactApplicationContext));
        }

        public final Builder addCxxReactPackage(Function1 function1) {
            Intrinsics.checkNotNullParameter(function1, "provider");
            this.cxxReactPackageProviders.add(new DefaultTurboModuleManagerDelegate$Builder$$ExternalSyntheticLambda0(function1));
            return this;
        }

        public final Builder addCxxReactPackages(Function1 function1) {
            Intrinsics.checkNotNullParameter(function1, "provider");
            this.cxxReactPackageProviders.add(function1);
            return this;
        }

        /* access modifiers changed from: protected */
        public DefaultTurboModuleManagerDelegate build(ReactApplicationContext reactApplicationContext, List<? extends ReactPackage> list) {
            Intrinsics.checkNotNullParameter(reactApplicationContext, "context");
            Intrinsics.checkNotNullParameter(list, "packages");
            ArrayList arrayList = new ArrayList();
            for (Function1 invoke : this.cxxReactPackageProviders) {
                CollectionsKt.addAll((Collection) arrayList, (Iterable) invoke.invoke(reactApplicationContext));
            }
            return new DefaultTurboModuleManagerDelegate(reactApplicationContext, list, arrayList, (DefaultConstructorMarker) null);
        }
    }

    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @DoNotStrip
        public final HybridData initHybrid(List<? extends CxxReactPackage> list) {
            return DefaultTurboModuleManagerDelegate.initHybrid(list);
        }

        private Companion() {
        }
    }

    static {
        DefaultSoLoader.Companion.maybeLoadSoLibrary();
    }
}
