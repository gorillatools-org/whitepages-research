package com.facebook.react.defaults;

import android.content.Context;
import com.facebook.react.ReactHost;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JSBundleLoader;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.defaults.DefaultTurboModuleManagerDelegate;
import com.facebook.react.fabric.ComponentFactory;
import com.facebook.react.runtime.BindingsInstaller;
import com.facebook.react.runtime.JSCInstance;
import com.facebook.react.runtime.JSRuntimeFactory;
import com.facebook.react.runtime.ReactHostImpl;
import com.facebook.react.runtime.hermes.HermesInstance;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class DefaultReactHost {
    public static final DefaultReactHost INSTANCE = new DefaultReactHost();
    private static ReactHost reactHost;

    private DefaultReactHost() {
    }

    public static /* synthetic */ ReactHost getDefaultReactHost$default(Context context, List list, String str, String str2, String str3, boolean z, boolean z2, List list2, int i, Object obj) {
        List list3;
        int i2 = i & 4;
        String str4 = FirebaseAnalytics.Param.INDEX;
        String str5 = i2 != 0 ? str4 : str;
        if ((i & 8) == 0) {
            str4 = str2;
        }
        String str6 = (i & 16) != 0 ? null : str3;
        boolean z3 = (i & 32) != 0 ? true : z;
        boolean z4 = (i & 64) != 0 ? ReactBuildConfig.DEBUG : z2;
        if ((i & 128) != 0) {
            list3 = CollectionsKt.emptyList();
        } else {
            list3 = list2;
        }
        return getDefaultReactHost(context, list, str5, str4, str6, z3, z4, list3);
    }

    public static final ReactHost getDefaultReactHost(Context context, List<? extends ReactPackage> list, String str, String str2, String str3, boolean z, boolean z2, List<? extends Function1> list2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(list, "packageList");
        Intrinsics.checkNotNullParameter(str, "jsMainModulePath");
        Intrinsics.checkNotNullParameter(str2, "jsBundleAssetPath");
        List<? extends Function1> list3 = list2;
        Intrinsics.checkNotNullParameter(list3, "cxxReactPackageProviders");
        return getDefaultReactHost(context, list, str, str2, str3, z, z2, list3, new DefaultReactHost$$ExternalSyntheticLambda1(), (BindingsInstaller) null);
    }

    /* access modifiers changed from: private */
    public static final Unit getDefaultReactHost$lambda$0(Exception exc) {
        Intrinsics.checkNotNullParameter(exc, "it");
        throw exc;
    }

    public static /* synthetic */ ReactHost getDefaultReactHost$default(Context context, List list, String str, String str2, String str3, boolean z, boolean z2, List list2, Function1 function1, BindingsInstaller bindingsInstaller, int i, Object obj) {
        Function1 function12;
        int i2 = i;
        int i3 = i2 & 4;
        String str4 = FirebaseAnalytics.Param.INDEX;
        String str5 = i3 != 0 ? str4 : str;
        if ((i2 & 8) == 0) {
            str4 = str2;
        }
        BindingsInstaller bindingsInstaller2 = null;
        String str6 = (i2 & 16) != 0 ? null : str3;
        boolean z3 = (i2 & 32) != 0 ? true : z;
        boolean z4 = (i2 & 64) != 0 ? ReactBuildConfig.DEBUG : z2;
        List emptyList = (i2 & 128) != 0 ? CollectionsKt.emptyList() : list2;
        if ((i2 & 256) != 0) {
            new DefaultReactHost$$ExternalSyntheticLambda0
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0039: CONSTRUCTOR  (r8v3 ? I:com.facebook.react.defaults.DefaultReactHost$$ExternalSyntheticLambda0) =  call: com.facebook.react.defaults.DefaultReactHost$$ExternalSyntheticLambda0.<init>():void type: CONSTRUCTOR in method: com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost$default(android.content.Context, java.util.List, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, java.util.List, kotlin.jvm.functions.Function1, com.facebook.react.runtime.BindingsInstaller, int, java.lang.Object):com.facebook.react.ReactHost, dex: classes.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v3 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 34 more
                */
            /*
                r0 = r19
                r1 = r0 & 4
                java.lang.String r2 = "index"
                if (r1 == 0) goto L_0x000a
                r1 = r2
                goto L_0x000b
            L_0x000a:
                r1 = r11
            L_0x000b:
                r3 = r0 & 8
                if (r3 == 0) goto L_0x0010
                goto L_0x0011
            L_0x0010:
                r2 = r12
            L_0x0011:
                r3 = r0 & 16
                r4 = 0
                if (r3 == 0) goto L_0x0018
                r3 = r4
                goto L_0x0019
            L_0x0018:
                r3 = r13
            L_0x0019:
                r5 = r0 & 32
                if (r5 == 0) goto L_0x001f
                r5 = 1
                goto L_0x0020
            L_0x001f:
                r5 = r14
            L_0x0020:
                r6 = r0 & 64
                if (r6 == 0) goto L_0x0027
                boolean r6 = com.facebook.react.common.build.ReactBuildConfig.DEBUG
                goto L_0x0028
            L_0x0027:
                r6 = r15
            L_0x0028:
                r7 = r0 & 128(0x80, float:1.794E-43)
                if (r7 == 0) goto L_0x0031
                java.util.List r7 = kotlin.collections.CollectionsKt.emptyList()
                goto L_0x0033
            L_0x0031:
                r7 = r16
            L_0x0033:
                r8 = r0 & 256(0x100, float:3.59E-43)
                if (r8 == 0) goto L_0x003d
                com.facebook.react.defaults.DefaultReactHost$$ExternalSyntheticLambda0 r8 = new com.facebook.react.defaults.DefaultReactHost$$ExternalSyntheticLambda0
                r8.<init>()
                goto L_0x003f
            L_0x003d:
                r8 = r17
            L_0x003f:
                r0 = r0 & 512(0x200, float:7.175E-43)
                if (r0 == 0) goto L_0x0044
                goto L_0x0046
            L_0x0044:
                r4 = r18
            L_0x0046:
                r11 = r9
                r12 = r10
                r13 = r1
                r14 = r2
                r15 = r3
                r16 = r5
                r17 = r6
                r18 = r7
                r19 = r8
                r20 = r4
                com.facebook.react.ReactHost r0 = getDefaultReactHost(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost$default(android.content.Context, java.util.List, java.lang.String, java.lang.String, java.lang.String, boolean, boolean, java.util.List, kotlin.jvm.functions.Function1, com.facebook.react.runtime.BindingsInstaller, int, java.lang.Object):com.facebook.react.ReactHost");
        }

        /* access modifiers changed from: private */
        public static final Unit getDefaultReactHost$lambda$1(Exception exc) {
            Intrinsics.checkNotNullParameter(exc, "it");
            throw exc;
        }

        public static final ReactHost getDefaultReactHost(Context context, List<? extends ReactPackage> list, String str, String str2, String str3, boolean z, boolean z2, List<? extends Function1> list2, Function1 function1, BindingsInstaller bindingsInstaller) {
            JSBundleLoader createAssetLoader;
            Context context2 = context;
            String str4 = str2;
            String str5 = str3;
            List<? extends Function1> list3 = list2;
            Intrinsics.checkNotNullParameter(context, "context");
            List<? extends ReactPackage> list4 = list;
            Intrinsics.checkNotNullParameter(list, "packageList");
            String str6 = str;
            Intrinsics.checkNotNullParameter(str, "jsMainModulePath");
            Intrinsics.checkNotNullParameter(str4, "jsBundleAssetPath");
            Intrinsics.checkNotNullParameter(list3, "cxxReactPackageProviders");
            Intrinsics.checkNotNullParameter(function1, "exceptionHandler");
            if (reactHost == null) {
                if (str5 == null) {
                    createAssetLoader = JSBundleLoader.createAssetLoader(context, "assets://" + str4, true);
                } else if (StringsKt.startsWith$default(str5, "assets://", false, 2, (Object) null)) {
                    createAssetLoader = JSBundleLoader.createAssetLoader(context, str5, true);
                } else {
                    createAssetLoader = JSBundleLoader.createFileLoader(str3);
                }
                JSBundleLoader jSBundleLoader = createAssetLoader;
                JSRuntimeFactory hermesInstance = z ? new HermesInstance() : new JSCInstance();
                DefaultTurboModuleManagerDelegate.Builder builder = new DefaultTurboModuleManagerDelegate.Builder();
                for (Function1 addCxxReactPackage : list3) {
                    builder.addCxxReactPackage(addCxxReactPackage);
                }
                Intrinsics.checkNotNull(jSBundleLoader);
                DefaultReactHostDelegate defaultReactHostDelegate = new DefaultReactHostDelegate(str, jSBundleLoader, list, hermesInstance, bindingsInstaller, function1, builder);
                ComponentFactory componentFactory = new ComponentFactory();
                DefaultComponentsRegistry.register(componentFactory);
                reactHost = new ReactHostImpl(context, defaultReactHostDelegate, componentFactory, true, z2);
            }
            ReactHost reactHost2 = reactHost;
            Intrinsics.checkNotNull(reactHost2, "null cannot be cast to non-null type com.facebook.react.ReactHost");
            return reactHost2;
        }

        public static final ReactHost getDefaultReactHost(Context context, ReactNativeHost reactNativeHost) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(reactNativeHost, "reactNativeHost");
            if (reactNativeHost instanceof DefaultReactNativeHost) {
                return ((DefaultReactNativeHost) reactNativeHost).toReactHost$ReactAndroid_release(context);
            }
            throw new IllegalArgumentException("You can call getDefaultReactHost only with instances of DefaultReactNativeHost");
        }
    }
