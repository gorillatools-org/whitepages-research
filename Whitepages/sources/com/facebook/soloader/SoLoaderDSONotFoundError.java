package com.facebook.soloader;

import android.content.Context;

public class SoLoaderDSONotFoundError extends SoLoaderULError {
    public SoLoaderDSONotFoundError(String str, String str2) {
        super(str, str2);
    }

    public static SoLoaderDSONotFoundError create(String str, Context context, SoSource[] soSourceArr) {
        StringBuilder sb = new StringBuilder("couldn't find DSO to load: ");
        sb.append(str);
        sb.append("\n\texisting SO sources: ");
        for (int i = 0; i < soSourceArr.length; i++) {
            sb.append("\n\t\tSoSource ");
            sb.append(i);
            sb.append(": ");
            sb.append(soSourceArr[i].toString());
        }
        if (context != null) {
            sb.append("\n\tNative lib dir: ");
            sb.append(context.getApplicationInfo().nativeLibraryDir);
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return new SoLoaderDSONotFoundError(str, sb.toString());
    }
}
