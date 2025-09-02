package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.res.ResourcesCompat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class ResourceDrawableIdHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String LOCAL_RESOURCE_SCHEME = "res";
    /* access modifiers changed from: private */
    public static final ResourceDrawableIdHelper resourceDrawableIdHelper = new ResourceDrawableIdHelper();
    private final Map<String, Integer> resourceDrawableIdMap = new HashMap();

    public static final ResourceDrawableIdHelper getInstance() {
        return Companion.getInstance();
    }

    private ResourceDrawableIdHelper() {
    }

    public final synchronized void clear() {
        this.resourceDrawableIdMap.clear();
    }

    public final int getResourceDrawableId(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (str == null || str.length() == 0) {
            return 0;
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String replace$default = StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null);
        try {
            return Integer.parseInt(replace$default);
        } catch (NumberFormatException unused) {
            synchronized (this) {
                Integer num = this.resourceDrawableIdMap.get(replace$default);
                return num != null ? num.intValue() : addDrawableId(context, replace$default);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private final int addDrawableId(Context context, String str) {
        int identifier = context.getResources().getIdentifier(str, "drawable", context.getPackageName());
        this.resourceDrawableIdMap.put(str, Integer.valueOf(identifier));
        return identifier;
    }

    public final Drawable getResourceDrawable(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        int resourceDrawableId = getResourceDrawableId(context, str);
        if (resourceDrawableId > 0) {
            return ResourcesCompat.getDrawable(context.getResources(), resourceDrawableId, (Resources.Theme) null);
        }
        return null;
    }

    public final Uri getResourceDrawableUri(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        int resourceDrawableId = getResourceDrawableId(context, str);
        if (resourceDrawableId > 0) {
            Uri build = new Uri.Builder().scheme(LOCAL_RESOURCE_SCHEME).path(String.valueOf(resourceDrawableId)).build();
            Intrinsics.checkNotNull(build);
            return build;
        }
        Uri uri = Uri.EMPTY;
        Intrinsics.checkNotNull(uri);
        return uri;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final ResourceDrawableIdHelper getInstance() {
            return ResourceDrawableIdHelper.resourceDrawableIdHelper;
        }

        public final ResourceDrawableIdHelper DEPRECATED$getInstance() {
            return getInstance();
        }
    }
}
