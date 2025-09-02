package com.facebook.imagepipeline.nativecode;

import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.lang.reflect.InvocationTargetException;
import kotlin.jvm.internal.Intrinsics;

public final class NativeImageTranscoderFactory {
    public static final NativeImageTranscoderFactory INSTANCE = new NativeImageTranscoderFactory();

    private NativeImageTranscoderFactory() {
    }

    public static final ImageTranscoderFactory getNativeImageTranscoderFactory(int i, boolean z, boolean z2) {
        Class<NativeJpegTranscoderFactory> cls = NativeJpegTranscoderFactory.class;
        try {
            Class cls2 = Integer.TYPE;
            Class cls3 = Boolean.TYPE;
            NativeJpegTranscoderFactory newInstance = cls.getConstructor(new Class[]{cls2, cls3, cls3}).newInstance(new Object[]{Integer.valueOf(i), Boolean.valueOf(z), Boolean.valueOf(z2)});
            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type com.facebook.imagepipeline.transcoder.ImageTranscoderFactory");
            return newInstance;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e);
        } catch (SecurityException e2) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e4);
        } catch (IllegalAccessException e5) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e5);
        } catch (IllegalArgumentException e6) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e6);
        } catch (ClassNotFoundException e7) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e7);
        }
    }
}
