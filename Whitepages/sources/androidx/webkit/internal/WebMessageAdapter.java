package androidx.webkit.internal;

import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebMessagePortCompat;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;

public abstract class WebMessageAdapter implements WebMessageBoundaryInterface {
    public static WebMessageCompat webMessageCompatFromBoundaryInterface(WebMessageBoundaryInterface webMessageBoundaryInterface) {
        return new WebMessageCompat(webMessageBoundaryInterface.getData(), toWebMessagePortCompats(webMessageBoundaryInterface.getPorts()));
    }

    private static WebMessagePortCompat[] toWebMessagePortCompats(InvocationHandler[] invocationHandlerArr) {
        WebMessagePortCompat[] webMessagePortCompatArr = new WebMessagePortCompat[invocationHandlerArr.length];
        for (int i = 0; i < invocationHandlerArr.length; i++) {
            webMessagePortCompatArr[i] = new WebMessagePortImpl(invocationHandlerArr[i]);
        }
        return webMessagePortCompatArr;
    }
}
