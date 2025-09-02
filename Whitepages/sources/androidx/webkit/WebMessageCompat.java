package androidx.webkit;

public class WebMessageCompat {
    private String mData;
    private WebMessagePortCompat[] mPorts;

    public WebMessageCompat(String str, WebMessagePortCompat[] webMessagePortCompatArr) {
        this.mData = str;
        this.mPorts = webMessagePortCompatArr;
    }

    public String getData() {
        return this.mData;
    }
}
