package com.facebook.soloader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class SoLoaderULErrorFactory {
    public static SoLoaderULError create(String str, UnsatisfiedLinkError unsatisfiedLinkError) {
        SoLoaderULError soLoaderULError;
        if (unsatisfiedLinkError.getMessage() != null && unsatisfiedLinkError.getMessage().contains("ELF")) {
            LogUtil.d("SoLoader", "Corrupted lib file detected");
            soLoaderULError = new SoLoaderCorruptedLibFileError(str, unsatisfiedLinkError.toString());
        } else if (corruptedLibName(str)) {
            LogUtil.d("SoLoader", "Corrupted lib name detected");
            soLoaderULError = new SoLoaderCorruptedLibNameError(str, "corrupted lib name: " + unsatisfiedLinkError.toString());
        } else {
            soLoaderULError = new SoLoaderULError(str, unsatisfiedLinkError.toString());
        }
        soLoaderULError.initCause(unsatisfiedLinkError);
        return soLoaderULError;
    }

    private static boolean corruptedLibName(String str) {
        Matcher matcher = Pattern.compile("\\P{ASCII}+").matcher(str);
        if (!matcher.find()) {
            return false;
        }
        String group = matcher.group();
        LogUtil.w("SoLoader", "Library name is corrupted, contains non-ASCII characters " + group);
        return true;
    }
}
