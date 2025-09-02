package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.Arrays;
import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

public final class ServerProtocol {
    public static final ServerProtocol INSTANCE = new ServerProtocol();
    private static final String TAG = ServerProtocol.class.getName();

    private ServerProtocol() {
    }

    public static final String getDefaultAPIVersion() {
        return "v16.0";
    }

    public static final Collection getErrorsProxyAuthDisabled() {
        return CollectionsKt.listOf("service_disabled", "AndroidAuthKillSwitchException");
    }

    public static final Collection getErrorsUserCanceled() {
        return CollectionsKt.listOf("access_denied", "OAuthAccessDeniedException");
    }

    public static final String getErrorConnectionFailure() {
        return "CONNECTION_FAILURE";
    }

    public static final String getDialogAuthority() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("m.%s", Arrays.copyOf(new Object[]{FacebookSdk.getFacebookDomain()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String getGamingDialogAuthority() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%s", Arrays.copyOf(new Object[]{FacebookSdk.getFacebookGamingDomain()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String getInstagramDialogAuthority() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("m.%s", Arrays.copyOf(new Object[]{FacebookSdk.getInstagramDomain()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String getGraphUrlBase() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("https://graph.%s", Arrays.copyOf(new Object[]{FacebookSdk.getGraphDomain()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String getGraphVideoUrlBase() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("https://graph-video.%s", Arrays.copyOf(new Object[]{FacebookSdk.getGraphDomain()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String getFacebookGraphUrlBase() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("https://graph.%s", Arrays.copyOf(new Object[]{FacebookSdk.getFacebookDomain()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }

    public static final String getGraphUrlBaseForSubdomain(String str) {
        Intrinsics.checkNotNullParameter(str, "subdomain");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("https://graph.%s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        return format;
    }
}
