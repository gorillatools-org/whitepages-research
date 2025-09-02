package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class MultiPostprocessor implements Postprocessor {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<Postprocessor> postprocessors;

    public /* synthetic */ MultiPostprocessor(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    public static final Postprocessor from(List<? extends Postprocessor> list) {
        return Companion.from(list);
    }

    private MultiPostprocessor(List<? extends Postprocessor> list) {
        this.postprocessors = new LinkedList(list);
    }

    public String getName() {
        String joinToString$default = CollectionsKt.joinToString$default(this.postprocessors, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
        return "MultiPostProcessor (" + joinToString$default + ")";
    }

    public CacheKey getPostprocessorCacheKey() {
        Iterable<Postprocessor> iterable = this.postprocessors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Postprocessor postprocessorCacheKey : iterable) {
            arrayList.add(postprocessorCacheKey.getPostprocessorCacheKey());
        }
        return new MultiCacheKey(arrayList);
    }

    public CloseableReference process(Bitmap bitmap, PlatformBitmapFactory platformBitmapFactory) {
        Bitmap bitmap2;
        Intrinsics.checkNotNullParameter(bitmap, "sourceBitmap");
        Intrinsics.checkNotNullParameter(platformBitmapFactory, "bitmapFactory");
        CloseableReference closeableReference = null;
        try {
            CloseableReference closeableReference2 = null;
            for (Postprocessor next : this.postprocessors) {
                if (closeableReference2 != null) {
                    bitmap2 = (Bitmap) closeableReference2.get();
                    if (bitmap2 != null) {
                        closeableReference = next.process(bitmap2, platformBitmapFactory);
                        CloseableReference.closeSafely(closeableReference2);
                        closeableReference2 = closeableReference.clone();
                    }
                }
                bitmap2 = bitmap;
                closeableReference = next.process(bitmap2, platformBitmapFactory);
                CloseableReference.closeSafely(closeableReference2);
                closeableReference2 = closeableReference.clone();
            }
            if (closeableReference != null) {
                CloseableReference clone = closeableReference.clone();
                Intrinsics.checkNotNullExpressionValue(clone, "clone(...)");
                CloseableReference.closeSafely(closeableReference);
                return clone;
            }
            throw new IllegalStateException(("MultiPostprocessor returned null bitmap - Number of Postprocessors: " + this.postprocessors.size()).toString());
        } catch (Throwable th) {
            CloseableReference.closeSafely(closeableReference);
            throw th;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Postprocessor from(List<? extends Postprocessor> list) {
            Intrinsics.checkNotNullParameter(list, "postprocessors");
            int size = list.size();
            if (size == 0) {
                return null;
            }
            if (size != 1) {
                return new MultiPostprocessor(list, (DefaultConstructorMarker) null);
            }
            return (Postprocessor) list.get(0);
        }
    }
}
