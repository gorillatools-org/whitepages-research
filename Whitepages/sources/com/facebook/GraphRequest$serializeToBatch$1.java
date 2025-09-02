package com.facebook;

import com.facebook.GraphRequest;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class GraphRequest$serializeToBatch$1 implements GraphRequest.KeyValueSerializer {
    final /* synthetic */ ArrayList $keysAndValues;

    GraphRequest$serializeToBatch$1(ArrayList arrayList) {
        this.$keysAndValues = arrayList;
    }

    public void writeString(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(str2, "value");
        ArrayList arrayList = this.$keysAndValues;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%s=%s", Arrays.copyOf(new Object[]{str, URLEncoder.encode(str2, "UTF-8")}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        arrayList.add(format);
    }
}
