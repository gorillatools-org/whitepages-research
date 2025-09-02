package com.facebook.soloader;

public interface ExternalSoMapping {
    void invokeJniOnload(String str);

    String mapLibName(String str);
}
