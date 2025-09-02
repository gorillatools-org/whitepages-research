package com.facebook.internal;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import com.facebook.FacebookException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class FacebookDialogFragment extends DialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private Dialog innerDialog;

    public final void setInnerDialog(Dialog dialog) {
        this.innerDialog = dialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initDialog$facebook_common_release();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: android.os.Bundle} */
    /* JADX WARNING: type inference failed for: r5v0 */
    /* JADX WARNING: type inference failed for: r5v6 */
    /* JADX WARNING: type inference failed for: r5v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initDialog$facebook_common_release() {
        /*
            r6 = this;
            android.app.Dialog r0 = r6.innerDialog
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            androidx.fragment.app.FragmentActivity r0 = r6.getActivity()
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            android.content.Intent r1 = r0.getIntent()
            java.lang.String r2 = "intent"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            android.os.Bundle r1 = com.facebook.internal.NativeProtocol.getMethodArgumentsFromIntent(r1)
            r2 = 0
            if (r1 == 0) goto L_0x0022
            java.lang.String r3 = "is_fallback"
            boolean r2 = r1.getBoolean(r3, r2)
        L_0x0022:
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.String"
            java.lang.String r4 = "FacebookDialogFragment"
            r5 = 0
            if (r2 != 0) goto L_0x0060
            if (r1 == 0) goto L_0x0032
            java.lang.String r2 = "action"
            java.lang.String r2 = r1.getString(r2)
            goto L_0x0033
        L_0x0032:
            r2 = r5
        L_0x0033:
            if (r1 == 0) goto L_0x003b
            java.lang.String r5 = "params"
            android.os.Bundle r5 = r1.getBundle(r5)
        L_0x003b:
            boolean r1 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r2)
            if (r1 == 0) goto L_0x004a
            java.lang.String r1 = "Cannot start a WebDialog with an empty/missing 'actionName'"
            com.facebook.internal.Utility.logd((java.lang.String) r4, (java.lang.String) r1)
            r0.finish()
            return
        L_0x004a:
            com.facebook.internal.WebDialog$Builder r1 = new com.facebook.internal.WebDialog$Builder
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r3)
            r1.<init>(r0, r2, r5)
            com.facebook.internal.FacebookDialogFragment$$ExternalSyntheticLambda0 r0 = new com.facebook.internal.FacebookDialogFragment$$ExternalSyntheticLambda0
            r0.<init>(r6)
            com.facebook.internal.WebDialog$Builder r0 = r1.setOnCompleteListener(r0)
            com.facebook.internal.WebDialog r0 = r0.build()
            goto L_0x00a2
        L_0x0060:
            if (r1 == 0) goto L_0x0068
            java.lang.String r2 = "url"
            java.lang.String r5 = r1.getString(r2)
        L_0x0068:
            boolean r1 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r5)
            if (r1 == 0) goto L_0x0077
            java.lang.String r1 = "Cannot start a fallback WebDialog with an empty/missing 'url'"
            com.facebook.internal.Utility.logd((java.lang.String) r4, (java.lang.String) r1)
            r0.finish()
            return
        L_0x0077:
            kotlin.jvm.internal.StringCompanionObject r1 = kotlin.jvm.internal.StringCompanionObject.INSTANCE
            java.lang.String r1 = com.facebook.FacebookSdk.getApplicationId()
            java.lang.Object[] r1 = new java.lang.Object[]{r1}
            r2 = 1
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r2)
            java.lang.String r2 = "fb%s://bridge/"
            java.lang.String r1 = java.lang.String.format(r2, r1)
            java.lang.String r2 = "format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.facebook.internal.FacebookWebFallbackDialog$Companion r2 = com.facebook.internal.FacebookWebFallbackDialog.Companion
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r3)
            com.facebook.internal.FacebookWebFallbackDialog r0 = r2.newInstance(r0, r5, r1)
            com.facebook.internal.FacebookDialogFragment$$ExternalSyntheticLambda1 r1 = new com.facebook.internal.FacebookDialogFragment$$ExternalSyntheticLambda1
            r1.<init>(r6)
            r0.setOnCompleteListener(r1)
        L_0x00a2:
            r6.innerDialog = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FacebookDialogFragment.initDialog$facebook_common_release():void");
    }

    /* access modifiers changed from: private */
    public static final void initDialog$lambda$0(FacebookDialogFragment facebookDialogFragment, Bundle bundle, FacebookException facebookException) {
        Intrinsics.checkNotNullParameter(facebookDialogFragment, "this$0");
        facebookDialogFragment.onCompleteWebDialog(bundle, facebookException);
    }

    /* access modifiers changed from: private */
    public static final void initDialog$lambda$1(FacebookDialogFragment facebookDialogFragment, Bundle bundle, FacebookException facebookException) {
        Intrinsics.checkNotNullParameter(facebookDialogFragment, "this$0");
        facebookDialogFragment.onCompleteWebFallbackDialog(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dialog = this.innerDialog;
        if (dialog == null) {
            onCompleteWebDialog((Bundle) null, (FacebookException) null);
            setShowsDialog(false);
            Dialog onCreateDialog = super.onCreateDialog(bundle);
            Intrinsics.checkNotNullExpressionValue(onCreateDialog, "super.onCreateDialog(savedInstanceState)");
            return onCreateDialog;
        }
        Intrinsics.checkNotNull(dialog, "null cannot be cast to non-null type android.app.Dialog");
        return dialog;
    }

    public void onResume() {
        super.onResume();
        Dialog dialog = this.innerDialog;
        if (dialog instanceof WebDialog) {
            Intrinsics.checkNotNull(dialog, "null cannot be cast to non-null type com.facebook.internal.WebDialog");
            ((WebDialog) dialog).resize();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        if ((this.innerDialog instanceof WebDialog) && isResumed()) {
            Dialog dialog = this.innerDialog;
            Intrinsics.checkNotNull(dialog, "null cannot be cast to non-null type com.facebook.internal.WebDialog");
            ((WebDialog) dialog).resize();
        }
    }

    public void onDestroyView() {
        Dialog dialog = getDialog();
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage((Message) null);
        }
        super.onDestroyView();
    }

    private final void onCompleteWebDialog(Bundle bundle, FacebookException facebookException) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intent intent = activity.getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "fragmentActivity.intent");
            activity.setResult(facebookException == null ? -1 : 0, NativeProtocol.createProtocolResultIntent(intent, bundle, facebookException));
            activity.finish();
        }
    }

    private final void onCompleteWebFallbackDialog(Bundle bundle) {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent();
            if (bundle == null) {
                bundle = new Bundle();
            }
            intent.putExtras(bundle);
            activity.setResult(-1, intent);
            activity.finish();
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
