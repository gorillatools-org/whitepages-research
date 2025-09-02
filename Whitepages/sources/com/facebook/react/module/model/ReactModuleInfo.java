package com.facebook.react.module.model;

import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReactModuleInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean canOverrideExistingModule;
    private final String className;
    private final boolean isCxxModule;
    private final boolean isTurboModule;
    private final String name;
    private final boolean needsEagerInit;

    public static final boolean classIsTurboModule(Class<?> cls) {
        return Companion.classIsTurboModule(cls);
    }

    public ReactModuleInfo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "className");
        this.name = str;
        this.className = str2;
        this.canOverrideExistingModule = z;
        this.needsEagerInit = z2;
        this.isCxxModule = z3;
        this.isTurboModule = z4;
    }

    public final String name() {
        return this.name;
    }

    public final String className() {
        return this.className;
    }

    public final boolean canOverrideExistingModule() {
        return this.canOverrideExistingModule;
    }

    public final boolean needsEagerInit() {
        return this.needsEagerInit;
    }

    public final boolean isCxxModule() {
        return this.isCxxModule;
    }

    public final boolean isTurboModule() {
        return this.isTurboModule;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ReactModuleInfo(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this(str, str2, z, z2, z4, z5);
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "className");
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean classIsTurboModule(Class<?> cls) {
            Intrinsics.checkNotNullParameter(cls, "clazz");
            return TurboModule.class.isAssignableFrom(cls);
        }
    }
}
