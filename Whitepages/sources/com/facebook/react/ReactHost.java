package com.facebook.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.interfaces.TaskInterface;
import com.facebook.react.interfaces.fabric.ReactSurface;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public interface ReactHost {
    void addBeforeDestroyListener(Function0 function0);

    void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener);

    ReactSurface createSurface(Context context, String str, Bundle bundle);

    TaskInterface<Void> destroy(String str, Exception exc);

    TaskInterface<Void> destroy(String str, Exception exc, Function1 function1);

    ReactContext getCurrentReactContext();

    DevSupportManager getDevSupportManager();

    LifecycleState getLifecycleState();

    MemoryPressureRouter getMemoryPressureRouter();

    ReactQueueConfiguration getReactQueueConfiguration();

    void invalidate();

    void onActivityResult(Activity activity, int i, int i2, Intent intent);

    boolean onBackPressed();

    void onConfigurationChanged(Context context);

    void onHostDestroy();

    void onHostDestroy(Activity activity);

    void onHostLeaveHint(Activity activity);

    void onHostPause();

    void onHostPause(Activity activity);

    void onHostResume(Activity activity);

    void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler);

    void onNewIntent(Intent intent);

    void onWindowFocusChange(boolean z);

    TaskInterface<Void> reload(String str);

    void removeBeforeDestroyListener(Function0 function0);

    void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener);

    TaskInterface<Void> start();

    static /* synthetic */ TaskInterface destroy$default(ReactHost reactHost, String str, Exception exc, Function1 function1, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                new ReactHost$$ExternalSyntheticLambda0
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0008: CONSTRUCTOR  (r3v2 ? I:com.facebook.react.ReactHost$$ExternalSyntheticLambda0) =  call: com.facebook.react.ReactHost$$ExternalSyntheticLambda0.<init>():void type: CONSTRUCTOR in method: com.facebook.react.ReactHost.destroy$default(com.facebook.react.ReactHost, java.lang.String, java.lang.Exception, kotlin.jvm.functions.Function1, int, java.lang.Object):com.facebook.react.interfaces.TaskInterface, dex: classes.dex
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
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:142)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
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
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r3v2 ?
                    	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 41 more
                    */
                /*
                    if (r5 != 0) goto L_0x0010
                    r4 = r4 & 4
                    if (r4 == 0) goto L_0x000b
                    com.facebook.react.ReactHost$$ExternalSyntheticLambda0 r3 = new com.facebook.react.ReactHost$$ExternalSyntheticLambda0
                    r3.<init>()
                L_0x000b:
                    com.facebook.react.interfaces.TaskInterface r0 = r0.destroy(r1, r2, r3)
                    return r0
                L_0x0010:
                    java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
                    java.lang.String r1 = "Super calls with default arguments not supported in this target, function: destroy"
                    r0.<init>(r1)
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.ReactHost.destroy$default(com.facebook.react.ReactHost, java.lang.String, java.lang.Exception, kotlin.jvm.functions.Function1, int, java.lang.Object):com.facebook.react.interfaces.TaskInterface");
            }

            /* access modifiers changed from: private */
            static Unit destroy$lambda$0(boolean z) {
                return Unit.INSTANCE;
            }
        }
