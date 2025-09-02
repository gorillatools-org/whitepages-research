package com.wpconnect;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.facebook.CallbackManager;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.devsupport.StackTraceHelper;
import com.salesforce.marketingcloud.notifications.NotificationManager;
import com.salesforce.marketingcloud.notifications.NotificationMessage;
import com.salesforce.marketingcloud.sfmcsdk.InitializationStatus;
import io.branch.rnbranch.RNBranchModule;
import java.util.Random;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MainApplication extends Application implements ReactApplication {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final CallbackManager callbackManager = CallbackManager.Factory.create();
    private final ReactNativeHost reactNativeHost = new MainApplication$reactNativeHost$1(this);

    public static final CallbackManager getCallbackManager() {
        return Companion.getCallbackManager();
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CallbackManager getCallbackManager() {
            return MainApplication.callbackManager;
        }
    }

    public ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private final PendingIntent createDeepLinkPendingIntent(Context context, String str) {
        int nextInt = new Random().nextInt();
        if (str == null || str.length() == 0) {
            Log.i("DEEPLINK", "no url");
            PendingIntent activity = PendingIntent.getActivity(context, nextInt, new Intent(context, MainActivity.class), 201326592);
            Intrinsics.checkNotNull(activity);
            return activity;
        }
        Log.i("DEEPLINK", "url: " + str);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setFlags(268435456);
        PendingIntent activity2 = PendingIntent.getActivity(context, nextInt, intent, 201326592);
        Intrinsics.checkNotNull(activity2);
        return activity2;
    }

    public void onCreate() {
        super.onCreate();
        RNBranchModule.enableLogging();
        RNBranchModule.getAutoInstance(this);
        MainApplication$$ExternalSyntheticLambda0 mainApplication$$ExternalSyntheticLambda0 = new MainApplication$$ExternalSyntheticLambda0(this);
        new MainApplication$$ExternalSyntheticLambda1(this);
        new MainApplication$$ExternalSyntheticLambda2
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: CONSTRUCTOR  (r1v1 ? I:com.wpconnect.MainApplication$$ExternalSyntheticLambda2) =  call: com.wpconnect.MainApplication$$ExternalSyntheticLambda2.<init>():void type: CONSTRUCTOR in method: com.wpconnect.MainApplication.onCreate():void, dex: classes2.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
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
            Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r1v1 ?
            	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
            	... 29 more
            */
        /*
            this = this;
            super.onCreate()
            io.branch.rnbranch.RNBranchModule.enableLogging()
            io.branch.rnbranch.RNBranchModule.getAutoInstance(r6)
            com.wpconnect.MainApplication$$ExternalSyntheticLambda0 r0 = new com.wpconnect.MainApplication$$ExternalSyntheticLambda0
            r0.<init>(r6)
            com.wpconnect.MainApplication$$ExternalSyntheticLambda1 r1 = new com.wpconnect.MainApplication$$ExternalSyntheticLambda1
            r1.<init>(r6)
            com.wpconnect.MainApplication$$ExternalSyntheticLambda2 r1 = new com.wpconnect.MainApplication$$ExternalSyntheticLambda2
            r1.<init>()
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdk$Companion r1 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdk.Companion
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig$Companion r2 = com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig.Companion
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig$Builder r2 = new com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig$Builder
            r2.<init>()
            com.salesforce.marketingcloud.MarketingCloudConfig$Companion r3 = com.salesforce.marketingcloud.MarketingCloudConfig.Companion
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.builder()
            java.lang.String r4 = "b5650ee4-4b2d-4b0f-9ae6-f7b03767dfd5"
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.setApplicationId(r4)
            java.lang.String r4 = "DahJuUoX91NhXFynj1oaQM6C"
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.setAccessToken(r4)
            java.lang.String r4 = "154653447684"
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.setSenderId(r4)
            java.lang.String r4 = "https://mcn7296wjn72t99f2m-1zrx52gxm.device.marketingcloudapis.com/"
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.setMarketingCloudServerUrl(r4)
            int r4 = com.wpconnect.R.drawable.ic_notification
            com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions r4 = com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions.create((int) r4)
            java.lang.String r5 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.setNotificationCustomizationOptions(r4)
            r4 = 1
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r3 = r3.setAnalyticsEnabled(r4)
            com.salesforce.marketingcloud.MarketingCloudConfig$Builder r0 = r3.setUrlHandler(r0)
            android.content.Context r3 = r6.getApplicationContext()
            java.lang.String r5 = "getApplicationContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            com.salesforce.marketingcloud.MarketingCloudConfig r0 = r0.build(r3)
            r2.setPushModuleConfig(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            com.salesforce.marketingcloud.sfmcsdk.SFMCSdkModuleConfig r0 = r2.build()
            com.wpconnect.MainApplication$$ExternalSyntheticLambda3 r2 = new com.wpconnect.MainApplication$$ExternalSyntheticLambda3
            r2.<init>()
            r1.configure(r6, r0, r2)
            com.google.firebase.crashlytics.FirebaseCrashlytics r0 = com.google.firebase.crashlytics.FirebaseCrashlytics.getInstance()
            r0.setCrashlyticsCollectionEnabled((boolean) r4)
            com.facebook.react.soloader.OpenSourceMergedSoMapping r0 = com.facebook.react.soloader.OpenSourceMergedSoMapping.INSTANCE
            com.facebook.soloader.SoLoader.init((android.content.Context) r6, (com.facebook.soloader.ExternalSoMapping) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wpconnect.MainApplication.onCreate():void");
    }

    /* access modifiers changed from: private */
    public static final PendingIntent onCreate$lambda$1(MainApplication mainApplication, Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "url");
        Intrinsics.checkNotNullParameter(str2, "<unused var>");
        Log.i("URLHANDLER", "url: " + str);
        PendingIntent createDeepLinkPendingIntent = mainApplication.createDeepLinkPendingIntent(context, str);
        try {
            createDeepLinkPendingIntent.send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
        return createDeepLinkPendingIntent;
    }

    /* access modifiers changed from: private */
    public static final PendingIntent onCreate$lambda$2(MainApplication mainApplication, Context context, NotificationMessage notificationMessage) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationMessage, StackTraceHelper.MESSAGE_KEY);
        return mainApplication.createDeepLinkPendingIntent(context, notificationMessage.url());
    }

    /* access modifiers changed from: private */
    public static final String onCreate$lambda$3(Context context, NotificationMessage notificationMessage) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationMessage, "<unused var>");
        return NotificationManager.createDefaultNotificationChannel(context);
    }

    /* access modifiers changed from: private */
    public static final Unit onCreate$lambda$5(InitializationStatus initializationStatus) {
        Intrinsics.checkNotNullParameter(initializationStatus, "status");
        Log.e("TAG", "STATUS " + initializationStatus);
        if (initializationStatus.getStatus() == 1) {
            Log.e("TAG", "STATUS SUCCESS");
        }
        return Unit.INSTANCE;
    }
}
