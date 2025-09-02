package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.systrace.FrescoSystrace;

public abstract class Fresco {
    private static final Class TAG = Fresco.class;
    private static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier = null;
    private static volatile boolean sIsInitialized = false;

    public static void initialize(Context context) {
        initialize(context, (ImagePipelineConfig) null, (DraweeConfig) null);
    }

    public static void initialize(Context context, ImagePipelineConfig imagePipelineConfig, DraweeConfig draweeConfig) {
        initialize(context, imagePipelineConfig, draweeConfig, true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0052, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006f, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007e, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(new com.facebook.soloader.nativeloader.SystemDelegate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x008d, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() == false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0094, code lost:
        if (com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing() != false) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0096, code lost:
        com.facebook.imagepipeline.systrace.FrescoSystrace.endSection();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0099, code lost:
        throw r2;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:19:0x0054, B:23:0x0063, B:27:0x0072, B:31:0x0081] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0054 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0072 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0081 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0081=Splitter:B:31:0x0081, B:27:0x0072=Splitter:B:27:0x0072, B:23:0x0063=Splitter:B:23:0x0063, B:19:0x0054=Splitter:B:19:0x0054} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void initialize(android.content.Context r2, com.facebook.imagepipeline.core.ImagePipelineConfig r3, com.facebook.drawee.backends.pipeline.DraweeConfig r4, boolean r5) {
        /*
            boolean r0 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r0 == 0) goto L_0x000b
            java.lang.String r0 = "Fresco#initialize"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r0)
        L_0x000b:
            boolean r0 = sIsInitialized
            if (r0 == 0) goto L_0x0017
            java.lang.Class r0 = TAG
            java.lang.String r1 = "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!"
            com.facebook.common.logging.FLog.w((java.lang.Class) r0, (java.lang.String) r1)
            goto L_0x001a
        L_0x0017:
            r0 = 1
            sIsInitialized = r0
        L_0x001a:
            com.facebook.imagepipeline.core.NativeCodeSetup.setUseNativeCode(r5)
            boolean r5 = com.facebook.soloader.nativeloader.NativeLoader.isInitialized()
            if (r5 != 0) goto L_0x009a
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x002e
            java.lang.String r5 = "Fresco.initialize->SoLoader.init"
            com.facebook.imagepipeline.systrace.FrescoSystrace.beginSection(r5)
        L_0x002e:
            java.lang.String r5 = "com.facebook.imagepipeline.nativecode.NativeCodeInitializer"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x0081, IllegalAccessException -> 0x0072, InvocationTargetException -> 0x0063, NoSuchMethodException -> 0x0054 }
            java.lang.String r0 = "init"
            java.lang.Class<android.content.Context> r1 = android.content.Context.class
            java.lang.Class[] r1 = new java.lang.Class[]{r1}     // Catch:{ ClassNotFoundException -> 0x0081, IllegalAccessException -> 0x0072, InvocationTargetException -> 0x0063, NoSuchMethodException -> 0x0054 }
            java.lang.reflect.Method r5 = r5.getMethod(r0, r1)     // Catch:{ ClassNotFoundException -> 0x0081, IllegalAccessException -> 0x0072, InvocationTargetException -> 0x0063, NoSuchMethodException -> 0x0054 }
            java.lang.Object[] r0 = new java.lang.Object[]{r2}     // Catch:{ ClassNotFoundException -> 0x0081, IllegalAccessException -> 0x0072, InvocationTargetException -> 0x0063, NoSuchMethodException -> 0x0054 }
            r1 = 0
            r5.invoke(r1, r0)     // Catch:{ ClassNotFoundException -> 0x0081, IllegalAccessException -> 0x0072, InvocationTargetException -> 0x0063, NoSuchMethodException -> 0x0054 }
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x009a
        L_0x004e:
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
            goto L_0x009a
        L_0x0052:
            r2 = move-exception
            goto L_0x0090
        L_0x0054:
            com.facebook.soloader.nativeloader.SystemDelegate r5 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0052 }
            r5.<init>()     // Catch:{ all -> 0x0052 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r5)     // Catch:{ all -> 0x0052 }
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x009a
            goto L_0x004e
        L_0x0063:
            com.facebook.soloader.nativeloader.SystemDelegate r5 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0052 }
            r5.<init>()     // Catch:{ all -> 0x0052 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r5)     // Catch:{ all -> 0x0052 }
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x009a
            goto L_0x004e
        L_0x0072:
            com.facebook.soloader.nativeloader.SystemDelegate r5 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0052 }
            r5.<init>()     // Catch:{ all -> 0x0052 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r5)     // Catch:{ all -> 0x0052 }
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x009a
            goto L_0x004e
        L_0x0081:
            com.facebook.soloader.nativeloader.SystemDelegate r5 = new com.facebook.soloader.nativeloader.SystemDelegate     // Catch:{ all -> 0x0052 }
            r5.<init>()     // Catch:{ all -> 0x0052 }
            com.facebook.soloader.nativeloader.NativeLoader.initIfUninitialized(r5)     // Catch:{ all -> 0x0052 }
            boolean r5 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r5 == 0) goto L_0x009a
            goto L_0x004e
        L_0x0090:
            boolean r3 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r3 == 0) goto L_0x0099
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x0099:
            throw r2
        L_0x009a:
            android.content.Context r2 = r2.getApplicationContext()
            if (r3 != 0) goto L_0x00a4
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize((android.content.Context) r2)
            goto L_0x00a7
        L_0x00a4:
            com.facebook.imagepipeline.core.ImagePipelineFactory.initialize((com.facebook.imagepipeline.core.ImagePipelineConfigInterface) r3)
        L_0x00a7:
            initializeDrawee(r2, r4)
            boolean r2 = com.facebook.imagepipeline.systrace.FrescoSystrace.isTracing()
            if (r2 == 0) goto L_0x00b3
            com.facebook.imagepipeline.systrace.FrescoSystrace.endSection()
        L_0x00b3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.Fresco.initialize(android.content.Context, com.facebook.imagepipeline.core.ImagePipelineConfig, com.facebook.drawee.backends.pipeline.DraweeConfig, boolean):void");
    }

    private static void initializeDrawee(Context context, DraweeConfig draweeConfig) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("Fresco.initializeDrawee");
        }
        PipelineDraweeControllerBuilderSupplier pipelineDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(context, draweeConfig);
        sDraweeControllerBuilderSupplier = pipelineDraweeControllerBuilderSupplier;
        SimpleDraweeView.initialize(pipelineDraweeControllerBuilderSupplier);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    public static PipelineDraweeControllerBuilder newDraweeControllerBuilder() {
        return sDraweeControllerBuilderSupplier.get();
    }

    public static ImagePipelineFactory getImagePipelineFactory() {
        return ImagePipelineFactory.getInstance();
    }

    public static ImagePipeline getImagePipeline() {
        return getImagePipelineFactory().getImagePipeline();
    }

    public static boolean hasBeenInitialized() {
        return sIsInitialized;
    }
}
