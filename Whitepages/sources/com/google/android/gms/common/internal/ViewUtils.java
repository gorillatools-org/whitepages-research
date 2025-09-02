package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ViewUtils {
    private ViewUtils() {
    }

    @KeepForSdk
    public static String getXmlAttributeString(String str, String str2, Context context, AttributeSet attributeSet, boolean z, boolean z2, String str3) {
        String attributeValue = attributeSet == null ? null : attributeSet.getAttributeValue(str, str2);
        if (attributeValue != null && attributeValue.startsWith("@string/") && z) {
            String substring = attributeValue.substring(8);
            String packageName = context.getPackageName();
            TypedValue typedValue = new TypedValue();
            try {
                Resources resources = context.getResources();
                resources.getValue(packageName + ":string/" + substring, typedValue, true);
            } catch (Resources.NotFoundException unused) {
                Log.w(str3, "Could not find resource for " + str2 + ": " + attributeValue);
            }
            CharSequence charSequence = typedValue.string;
            if (charSequence != null) {
                attributeValue = charSequence.toString();
            } else {
                String obj = typedValue.toString();
                Log.w(str3, "Resource " + str2 + " was not a string: " + obj);
            }
        }
        if (z2 && attributeValue == null) {
            Log.w(str3, "Required XML attribute \"" + str2 + "\" missing");
        }
        return attributeValue;
    }
}
