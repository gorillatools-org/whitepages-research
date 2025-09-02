package io.branch.referral;

public enum Defines$RequestPath {
    GetURL("v1/url"),
    GetApp("v1/app-link-settings"),
    RegisterInstall("v1/install"),
    RegisterOpen("v1/open"),
    ContentEvent("v1/content-events"),
    TrackStandardEvent("v2/event/standard"),
    TrackCustomEvent("v2/event/custom"),
    GetLATD("v1/cpid/latd"),
    QRCode("v1/qr-code");
    
    private final String key;

    private Defines$RequestPath(String str) {
        this.key = str;
    }

    public String getPath() {
        return this.key;
    }

    public String toString() {
        return this.key;
    }
}
