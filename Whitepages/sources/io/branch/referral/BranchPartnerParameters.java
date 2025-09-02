package io.branch.referral;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class BranchPartnerParameters {
    private static final Pattern HEXADECIMAL_PATTERN = Pattern.compile("\\p{XDigit}+");
    private final ConcurrentHashMap partnerParameters = new ConcurrentHashMap();

    /* access modifiers changed from: package-private */
    public void clearAllParameters() {
        this.partnerParameters.clear();
    }

    /* access modifiers changed from: package-private */
    public ConcurrentHashMap parametersForPartner(String str) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.partnerParameters.get(str);
        if (concurrentHashMap != null) {
            return concurrentHashMap;
        }
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();
        this.partnerParameters.put(str, concurrentHashMap2);
        return concurrentHashMap2;
    }

    private void addParameterWithName(String str, String str2, String str3) {
        parametersForPartner(str3).put(str, str2);
    }

    /* access modifiers changed from: package-private */
    public void addFacebookParameter(String str, String str2) {
        if (isSha256Hashed(str2)) {
            addParameterWithName(str, str2, "fb");
        } else {
            BranchLogger.w("Invalid partner parameter passed. Value must be a SHA 256 hash.");
        }
    }

    /* access modifiers changed from: package-private */
    public void addSnapParameter(String str, String str2) {
        if (isSha256Hashed(str2)) {
            addParameterWithName(str, str2, "snap");
        } else {
            BranchLogger.w("Invalid partner parameter passed. Value must be a SHA 256 hash.");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isSha256Hashed(String str) {
        return str != null && str.length() == 64 && isHexadecimal(str);
    }

    /* access modifiers changed from: package-private */
    public boolean isHexadecimal(String str) {
        if (str == null) {
            return false;
        }
        if (str.length() == 0) {
            return true;
        }
        return HEXADECIMAL_PATTERN.matcher(str).matches();
    }

    /* access modifiers changed from: package-private */
    public ConcurrentHashMap allParams() {
        return this.partnerParameters;
    }
}
