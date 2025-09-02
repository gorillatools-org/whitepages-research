package com.RNAppleAuthentication;

import android.webkit.JavascriptInterface;
import com.RNAppleAuthentication.SignInWithAppleResult;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public final class FormInterceptorInterface {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String JS_TO_INJECT = "function parseForm(form){\n\n    var values = '';\n    for(var i=0 ; i< form.elements.length; i++){\n        values +=\n            form.elements[i].name +\n            '=' +\n            form.elements[i].value +\n            '|'\n    }\n    FormInterceptorInterface.processFormData(values);\n}\n\n\nfor(var i=0 ; i< document.forms.length ; i++){\n    parseForm(document.forms[i]);\n}";
    private final Function1 callback;
    private final String expectedState;

    public FormInterceptorInterface(String str, Function1 function1) {
        Intrinsics.checkNotNullParameter(str, "expectedState");
        Intrinsics.checkNotNullParameter(function1, "callback");
        this.expectedState = str;
        this.callback = function1;
    }

    @JavascriptInterface
    public final void processFormData(String str) {
        String str2;
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Intrinsics.checkNotNullParameter(str, "formData");
        Iterable split$default = StringsKt.split$default((CharSequence) str, new String[]{"|"}, false, 0, 6, (Object) null);
        Iterator it = split$default.iterator();
        while (true) {
            str2 = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (StringsKt.startsWith$default((String) obj, "id_token", false, 2, (Object) null)) {
                break;
            }
        }
        String str3 = (String) obj;
        Iterator it2 = split$default.iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it2.next();
            if (StringsKt.startsWith$default((String) obj2, "code", false, 2, (Object) null)) {
                break;
            }
        }
        String str4 = (String) obj2;
        Iterator it3 = split$default.iterator();
        while (true) {
            if (!it3.hasNext()) {
                obj3 = null;
                break;
            }
            obj3 = it3.next();
            if (StringsKt.startsWith$default((String) obj3, RemoteConfigConstants.ResponseFieldKey.STATE, false, 2, (Object) null)) {
                break;
            }
        }
        String str5 = (String) obj3;
        Iterator it4 = split$default.iterator();
        while (true) {
            if (!it4.hasNext()) {
                obj4 = null;
                break;
            }
            obj4 = it4.next();
            if (StringsKt.startsWith$default((String) obj4, "user", false, 2, (Object) null)) {
                break;
            }
        }
        String str6 = (String) obj4;
        if (str5 == null || (str4 == null && str3 == null && str6 == null)) {
            this.callback.invoke(SignInWithAppleResult.Cancel.INSTANCE);
            return;
        }
        String substringAfter$default = StringsKt.substringAfter$default(str5, "=", (String) null, 2, (Object) null);
        String substringAfter$default2 = str4 != null ? StringsKt.substringAfter$default(str4, "=", (String) null, 2, (Object) null) : null;
        String substringAfter$default3 = str3 != null ? StringsKt.substringAfter$default(str3, "=", (String) null, 2, (Object) null) : null;
        if (str6 != null) {
            str2 = StringsKt.substringAfter$default(str6, "=", (String) null, 2, (Object) null);
        }
        if (Intrinsics.areEqual((Object) substringAfter$default, (Object) this.expectedState)) {
            Function1 function1 = this.callback;
            if (substringAfter$default2 == null) {
                substringAfter$default2 = "";
            }
            if (substringAfter$default3 == null) {
                substringAfter$default3 = "";
            }
            if (str2 == null) {
                str2 = "";
            }
            function1.invoke(new SignInWithAppleResult.Success(substringAfter$default2, substringAfter$default3, substringAfter$default, str2));
            return;
        }
        this.callback.invoke(new SignInWithAppleResult.Failure(new IllegalArgumentException("state does not match")));
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getJS_TO_INJECT() {
            return FormInterceptorInterface.JS_TO_INJECT;
        }
    }
}
