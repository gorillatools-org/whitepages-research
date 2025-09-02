package com.facebook.internal;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.AccessToken;
import com.facebook.FacebookDialogException;
import com.facebook.FacebookException;
import com.facebook.FacebookGraphResponseException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphResponse;
import com.facebook.common.R$drawable;
import com.facebook.common.R$string;
import com.facebook.common.R$style;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import com.facebook.login.LoginTargetApp;
import com.facebook.react.uimanager.events.PointerEventHelper;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.salesforce.marketingcloud.UrlHandler;
import io.branch.rnbranch.RNBranchModule;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebDialog extends Dialog {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final int DEFAULT_THEME = R$style.com_facebook_activity_theme;
    /* access modifiers changed from: private */
    public static volatile int webDialogTheme;
    /* access modifiers changed from: private */
    public FrameLayout contentFrameLayout;
    /* access modifiers changed from: private */
    public ImageView crossImageView;
    /* access modifiers changed from: private */
    public String expectedRedirectUrl;
    /* access modifiers changed from: private */
    public boolean isDetached;
    private boolean isListenerCalled;
    /* access modifiers changed from: private */
    public boolean isPageFinished;
    private OnCompleteListener onCompleteListener;
    /* access modifiers changed from: private */
    public ProgressDialog spinner;
    private UploadStagingResourcesTask uploadTask;
    /* access modifiers changed from: private */
    public String url;
    private WebView webView;
    private WindowManager.LayoutParams windowParams;

    public interface OnCompleteListener {
        void onComplete(Bundle bundle, FacebookException facebookException);
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoginTargetApp.values().length];
            try {
                iArr[LoginTargetApp.INSTAGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public /* synthetic */ WebDialog(Context context, String str, Bundle bundle, int i, LoginTargetApp loginTargetApp, OnCompleteListener onCompleteListener2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, bundle, i, loginTargetApp, onCompleteListener2);
    }

    private final int getScaledSize(int i, float f, int i2, int i3) {
        int i4 = (int) (((float) i) / f);
        return (int) (((double) i) * (i4 <= i2 ? 1.0d : i4 >= i3 ? 0.5d : ((((double) (i3 - i4)) / ((double) (i3 - i2))) * 0.5d) + 0.5d));
    }

    protected static final void initDefaultTheme(Context context) {
        Companion.initDefaultTheme(context);
    }

    public final void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
        this.onCompleteListener = onCompleteListener2;
    }

    /* access modifiers changed from: protected */
    public final WebView getWebView() {
        return this.webView;
    }

    /* access modifiers changed from: protected */
    public final boolean isListenerCalled() {
        return this.isListenerCalled;
    }

    /* access modifiers changed from: protected */
    public final boolean isPageFinished() {
        return this.isPageFinished;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected WebDialog(Context context, String str) {
        this(context, str, Companion.getWebDialogTheme());
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "url");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private WebDialog(Context context, String str, int i) {
        super(context, i == 0 ? Companion.getWebDialogTheme() : i);
        this.expectedRedirectUrl = "fbconnect://success";
        this.url = str;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private WebDialog(Context context, String str, Bundle bundle, int i, LoginTargetApp loginTargetApp, OnCompleteListener onCompleteListener2) {
        super(context, i == 0 ? Companion.getWebDialogTheme() : i);
        Uri uri;
        String str2 = "fbconnect://success";
        this.expectedRedirectUrl = str2;
        bundle = bundle == null ? new Bundle() : bundle;
        str2 = Utility.isChromeOS(context) ? "fbconnect://chrome_os_success" : str2;
        this.expectedRedirectUrl = str2;
        bundle.putString("redirect_uri", str2);
        bundle.putString("display", PointerEventHelper.POINTER_TYPE_TOUCH);
        bundle.putString("client_id", FacebookSdk.getApplicationId());
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.ROOT, "android-%s", Arrays.copyOf(new Object[]{FacebookSdk.getSdkVersion()}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        bundle.putString("sdk", format);
        this.onCompleteListener = onCompleteListener2;
        if (!Intrinsics.areEqual((Object) str, (Object) FirebaseAnalytics.Event.SHARE) || !bundle.containsKey("media")) {
            if (WhenMappings.$EnumSwitchMapping$0[loginTargetApp.ordinal()] == 1) {
                uri = Utility.buildUri(ServerProtocol.getInstagramDialogAuthority(), "oauth/authorize", bundle);
            } else {
                String dialogAuthority = ServerProtocol.getDialogAuthority();
                uri = Utility.buildUri(dialogAuthority, FacebookSdk.getGraphApiVersion() + "/dialog/" + str, bundle);
            }
            this.url = uri.toString();
            return;
        }
        this.uploadTask = new UploadStagingResourcesTask(this, str, bundle);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(keyEvent, "event");
        if (i == 4) {
            WebView webView2 = this.webView;
            if (webView2 == null || webView2 == null || !webView2.canGoBack()) {
                cancel();
            } else {
                WebView webView3 = this.webView;
                if (webView3 != null) {
                    webView3.goBack();
                }
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void dismiss() {
        ProgressDialog progressDialog;
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.stopLoading();
        }
        if (!this.isDetached && (progressDialog = this.spinner) != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.dismiss();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        UploadStagingResourcesTask uploadStagingResourcesTask = this.uploadTask;
        if (uploadStagingResourcesTask != null) {
            if ((uploadStagingResourcesTask != null ? uploadStagingResourcesTask.getStatus() : null) == AsyncTask.Status.PENDING) {
                UploadStagingResourcesTask uploadStagingResourcesTask2 = this.uploadTask;
                if (uploadStagingResourcesTask2 != null) {
                    uploadStagingResourcesTask2.execute(new Void[0]);
                }
                ProgressDialog progressDialog = this.spinner;
                if (progressDialog != null) {
                    progressDialog.show();
                    return;
                }
                return;
            }
        }
        resize();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        UploadStagingResourcesTask uploadStagingResourcesTask = this.uploadTask;
        if (uploadStagingResourcesTask != null) {
            uploadStagingResourcesTask.cancel(true);
            ProgressDialog progressDialog = this.spinner;
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }

    public void onAttachedToWindow() {
        WindowManager.LayoutParams layoutParams;
        Window window;
        WindowManager.LayoutParams attributes;
        this.isDetached = false;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (Utility.mustFixWindowParamsForAutofill(context) && (layoutParams = this.windowParams) != null) {
            IBinder iBinder = null;
            if ((layoutParams != null ? layoutParams.token : null) == null) {
                if (layoutParams != null) {
                    Activity ownerActivity = getOwnerActivity();
                    layoutParams.token = (ownerActivity == null || (window = ownerActivity.getWindow()) == null || (attributes = window.getAttributes()) == null) ? null : attributes.token;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Set token on onAttachedToWindow(): ");
                WindowManager.LayoutParams layoutParams2 = this.windowParams;
                if (layoutParams2 != null) {
                    iBinder = layoutParams2.token;
                }
                sb.append(iBinder);
                Utility.logd("FacebookSDK.WebDialog", sb.toString());
            }
        }
        super.onAttachedToWindow();
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        Intrinsics.checkNotNullParameter(layoutParams, RNBranchModule.NATIVE_INIT_SESSION_FINISHED_EVENT_PARAMS);
        if (layoutParams.token == null) {
            this.windowParams = layoutParams;
        }
        super.onWindowAttributesChanged(layoutParams);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        this.spinner = progressDialog;
        progressDialog.requestWindowFeature(1);
        ProgressDialog progressDialog2 = this.spinner;
        if (progressDialog2 != null) {
            progressDialog2.setMessage(getContext().getString(R$string.com_facebook_loading));
        }
        ProgressDialog progressDialog3 = this.spinner;
        if (progressDialog3 != null) {
            progressDialog3.setCanceledOnTouchOutside(false);
        }
        ProgressDialog progressDialog4 = this.spinner;
        if (progressDialog4 != null) {
            progressDialog4.setOnCancelListener(new WebDialog$$ExternalSyntheticLambda1(this));
        }
        requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(getContext());
        resize();
        Window window = getWindow();
        if (window != null) {
            window.setGravity(17);
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setSoftInputMode(16);
        }
        createCrossImage();
        if (this.url != null) {
            ImageView imageView = this.crossImageView;
            if (imageView != null) {
                setUpWebView((imageView.getDrawable().getIntrinsicWidth() / 2) + 1);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        FrameLayout frameLayout = this.contentFrameLayout;
        if (frameLayout != null) {
            frameLayout.addView(this.crossImageView, new ViewGroup.LayoutParams(-2, -2));
        }
        FrameLayout frameLayout2 = this.contentFrameLayout;
        if (frameLayout2 != null) {
            setContentView(frameLayout2);
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$4(WebDialog webDialog, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(webDialog, "this$0");
        webDialog.cancel();
    }

    /* access modifiers changed from: protected */
    public final void setExpectedRedirectUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "expectedRedirectUrl");
        this.expectedRedirectUrl = str;
    }

    public Bundle parseResponseUri(String str) {
        Uri parse = Uri.parse(str);
        Bundle parseUrlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        parseUrlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
        return parseUrlQueryString;
    }

    public final void resize() {
        Object systemService = getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        Display defaultDisplay = ((WindowManager) systemService).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        int i3 = i < i2 ? i : i2;
        if (i < i2) {
            i = i2;
        }
        int min = Math.min(getScaledSize(i3, displayMetrics.density, 480, 800), displayMetrics.widthPixels);
        int min2 = Math.min(getScaledSize(i, displayMetrics.density, 800, 1280), displayMetrics.heightPixels);
        Window window = getWindow();
        if (window != null) {
            window.setLayout(min, min2);
        }
    }

    /* access modifiers changed from: protected */
    public final void sendSuccessToListener(Bundle bundle) {
        OnCompleteListener onCompleteListener2 = this.onCompleteListener;
        if (onCompleteListener2 != null && !this.isListenerCalled) {
            this.isListenerCalled = true;
            if (onCompleteListener2 != null) {
                onCompleteListener2.onComplete(bundle, (FacebookException) null);
            }
            dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public final void sendErrorToListener(Throwable th) {
        FacebookException facebookException;
        if (this.onCompleteListener != null && !this.isListenerCalled) {
            this.isListenerCalled = true;
            if (th instanceof FacebookException) {
                facebookException = (FacebookException) th;
            } else {
                facebookException = new FacebookException(th);
            }
            OnCompleteListener onCompleteListener2 = this.onCompleteListener;
            if (onCompleteListener2 != null) {
                onCompleteListener2.onComplete((Bundle) null, facebookException);
            }
            dismiss();
        }
    }

    public void cancel() {
        if (this.onCompleteListener != null && !this.isListenerCalled) {
            sendErrorToListener(new FacebookOperationCanceledException());
        }
    }

    private final void createCrossImage() {
        ImageView imageView = new ImageView(getContext());
        this.crossImageView = imageView;
        imageView.setOnClickListener(new WebDialog$$ExternalSyntheticLambda2(this));
        Drawable drawable = getContext().getResources().getDrawable(R$drawable.com_facebook_close);
        ImageView imageView2 = this.crossImageView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
        ImageView imageView3 = this.crossImageView;
        if (imageView3 != null) {
            imageView3.setVisibility(4);
        }
    }

    /* access modifiers changed from: private */
    public static final void createCrossImage$lambda$5(WebDialog webDialog, View view) {
        Intrinsics.checkNotNullParameter(webDialog, "this$0");
        webDialog.cancel();
    }

    /* access modifiers changed from: private */
    public final void setUpWebView(int i) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        WebDialog$setUpWebView$1 webDialog$setUpWebView$1 = new WebDialog$setUpWebView$1(getContext());
        this.webView = webDialog$setUpWebView$1;
        webDialog$setUpWebView$1.setVerticalScrollBarEnabled(false);
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.setHorizontalScrollBarEnabled(false);
        }
        WebView webView3 = this.webView;
        if (webView3 != null) {
            webView3.setWebViewClient(new DialogWebViewClient());
        }
        WebView webView4 = this.webView;
        WebSettings webSettings = null;
        WebSettings settings = webView4 != null ? webView4.getSettings() : null;
        if (settings != null) {
            settings.setJavaScriptEnabled(true);
        }
        WebView webView5 = this.webView;
        if (webView5 != null) {
            String str = this.url;
            if (str != null) {
                webView5.loadUrl(str);
            } else {
                throw new IllegalStateException("Required value was null.");
            }
        }
        WebView webView6 = this.webView;
        if (webView6 != null) {
            webView6.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        }
        WebView webView7 = this.webView;
        if (webView7 != null) {
            webView7.setVisibility(4);
        }
        WebView webView8 = this.webView;
        WebSettings settings2 = webView8 != null ? webView8.getSettings() : null;
        if (settings2 != null) {
            settings2.setSavePassword(false);
        }
        WebView webView9 = this.webView;
        if (webView9 != null) {
            webSettings = webView9.getSettings();
        }
        if (webSettings != null) {
            webSettings.setSaveFormData(false);
        }
        WebView webView10 = this.webView;
        if (webView10 != null) {
            webView10.setFocusable(true);
        }
        WebView webView11 = this.webView;
        if (webView11 != null) {
            webView11.setFocusableInTouchMode(true);
        }
        WebView webView12 = this.webView;
        if (webView12 != null) {
            webView12.setOnTouchListener(new WebDialog$$ExternalSyntheticLambda0());
        }
        linearLayout.setPadding(i, i, i, i);
        linearLayout.addView(this.webView);
        linearLayout.setBackgroundColor(-872415232);
        FrameLayout frameLayout = this.contentFrameLayout;
        if (frameLayout != null) {
            frameLayout.addView(linearLayout);
        }
    }

    /* access modifiers changed from: private */
    public static final boolean setUpWebView$lambda$7(View view, MotionEvent motionEvent) {
        if (view.hasFocus()) {
            return false;
        }
        view.requestFocus();
        return false;
    }

    private final class DialogWebViewClient extends WebViewClient {
        public DialogWebViewClient() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:35:0x00ae  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x00b4  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean shouldOverrideUrlLoading(android.webkit.WebView r6, java.lang.String r7) {
            /*
                r5 = this;
                java.lang.String r0 = "view"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r6 = "url"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r6)
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r0 = "Redirect URL: "
                r6.append(r0)
                r6.append(r7)
                java.lang.String r6 = r6.toString()
                java.lang.String r0 = "FacebookSDK.WebDialog"
                com.facebook.internal.Utility.logd((java.lang.String) r0, (java.lang.String) r6)
                android.net.Uri r6 = android.net.Uri.parse(r7)
                java.lang.String r0 = r6.getPath()
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L_0x003a
                java.lang.String r0 = "^/(v\\d+\\.\\d+/)??dialog/.*"
                java.lang.String r6 = r6.getPath()
                boolean r6 = java.util.regex.Pattern.matches(r0, r6)
                if (r6 == 0) goto L_0x003a
                r6 = r1
                goto L_0x003b
            L_0x003a:
                r6 = r2
            L_0x003b:
                com.facebook.internal.WebDialog r0 = com.facebook.internal.WebDialog.this
                java.lang.String r0 = r0.expectedRedirectUrl
                r3 = 2
                r4 = 0
                boolean r0 = kotlin.text.StringsKt.startsWith$default(r7, r0, r2, r3, r4)
                if (r0 == 0) goto L_0x00ce
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                android.os.Bundle r6 = r6.parseResponseUri(r7)
                java.lang.String r7 = "error"
                java.lang.String r7 = r6.getString(r7)
                if (r7 != 0) goto L_0x005d
                java.lang.String r7 = "error_type"
                java.lang.String r7 = r6.getString(r7)
            L_0x005d:
                java.lang.String r0 = "error_msg"
                java.lang.String r0 = r6.getString(r0)
                if (r0 != 0) goto L_0x006b
                java.lang.String r0 = "error_message"
                java.lang.String r0 = r6.getString(r0)
            L_0x006b:
                if (r0 != 0) goto L_0x0073
                java.lang.String r0 = "error_description"
                java.lang.String r0 = r6.getString(r0)
            L_0x0073:
                java.lang.String r2 = "error_code"
                java.lang.String r2 = r6.getString(r2)
                r3 = -1
                if (r2 == 0) goto L_0x0087
                boolean r4 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r2)
                if (r4 != 0) goto L_0x0087
                int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x0087 }
                goto L_0x0088
            L_0x0087:
                r2 = r3
            L_0x0088:
                boolean r4 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r7)
                if (r4 == 0) goto L_0x009c
                boolean r4 = com.facebook.internal.Utility.isNullOrEmpty((java.lang.String) r0)
                if (r4 == 0) goto L_0x009c
                if (r2 != r3) goto L_0x009c
                com.facebook.internal.WebDialog r7 = com.facebook.internal.WebDialog.this
                r7.sendSuccessToListener(r6)
                goto L_0x00cd
            L_0x009c:
                if (r7 == 0) goto L_0x00b4
                java.lang.String r6 = "access_denied"
                boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r6)
                if (r6 != 0) goto L_0x00ae
                java.lang.String r6 = "OAuthAccessDeniedException"
                boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r6)
                if (r6 == 0) goto L_0x00b4
            L_0x00ae:
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                r6.cancel()
                goto L_0x00cd
            L_0x00b4:
                r6 = 4201(0x1069, float:5.887E-42)
                if (r2 != r6) goto L_0x00be
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                r6.cancel()
                goto L_0x00cd
            L_0x00be:
                com.facebook.FacebookRequestError r6 = new com.facebook.FacebookRequestError
                r6.<init>(r2, r7, r0)
                com.facebook.internal.WebDialog r7 = com.facebook.internal.WebDialog.this
                com.facebook.FacebookServiceException r2 = new com.facebook.FacebookServiceException
                r2.<init>(r6, r0)
                r7.sendErrorToListener(r2)
            L_0x00cd:
                return r1
            L_0x00ce:
                java.lang.String r0 = "fbconnect://cancel"
                boolean r0 = kotlin.text.StringsKt.startsWith$default(r7, r0, r2, r3, r4)
                if (r0 == 0) goto L_0x00dc
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this
                r6.cancel()
                return r1
            L_0x00dc:
                if (r6 != 0) goto L_0x00fe
                java.lang.String r6 = "touch"
                boolean r6 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r7, (java.lang.CharSequence) r6, (boolean) r2, (int) r3, (java.lang.Object) r4)
                if (r6 == 0) goto L_0x00e7
                goto L_0x00fe
            L_0x00e7:
                com.facebook.internal.WebDialog r6 = com.facebook.internal.WebDialog.this     // Catch:{ ActivityNotFoundException -> 0x00fc }
                android.content.Context r6 = r6.getContext()     // Catch:{ ActivityNotFoundException -> 0x00fc }
                android.content.Intent r0 = new android.content.Intent     // Catch:{ ActivityNotFoundException -> 0x00fc }
                java.lang.String r3 = "android.intent.action.VIEW"
                android.net.Uri r7 = android.net.Uri.parse(r7)     // Catch:{ ActivityNotFoundException -> 0x00fc }
                r0.<init>(r3, r7)     // Catch:{ ActivityNotFoundException -> 0x00fc }
                r6.startActivity(r0)     // Catch:{ ActivityNotFoundException -> 0x00fc }
                goto L_0x00fd
            L_0x00fc:
                r1 = r2
            L_0x00fd:
                return r1
            L_0x00fe:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WebDialog.DialogWebViewClient.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            Intrinsics.checkNotNullParameter(webView, "view");
            Intrinsics.checkNotNullParameter(str, "description");
            Intrinsics.checkNotNullParameter(str2, "failingUrl");
            super.onReceivedError(webView, i, str, str2);
            WebDialog.this.sendErrorToListener(new FacebookDialogException(str, i, str2));
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            Intrinsics.checkNotNullParameter(webView, "view");
            Intrinsics.checkNotNullParameter(sslErrorHandler, "handler");
            Intrinsics.checkNotNullParameter(sslError, "error");
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            WebDialog.this.sendErrorToListener(new FacebookDialogException((String) null, -11, (String) null));
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            ProgressDialog access$getSpinner$p;
            Intrinsics.checkNotNullParameter(webView, "view");
            Intrinsics.checkNotNullParameter(str, "url");
            Utility.logd("FacebookSDK.WebDialog", "Webview loading URL: " + str);
            super.onPageStarted(webView, str, bitmap);
            if (!WebDialog.this.isDetached && (access$getSpinner$p = WebDialog.this.spinner) != null) {
                access$getSpinner$p.show();
            }
        }

        public void onPageFinished(WebView webView, String str) {
            ProgressDialog access$getSpinner$p;
            Intrinsics.checkNotNullParameter(webView, "view");
            Intrinsics.checkNotNullParameter(str, "url");
            super.onPageFinished(webView, str);
            if (!WebDialog.this.isDetached && (access$getSpinner$p = WebDialog.this.spinner) != null) {
                access$getSpinner$p.dismiss();
            }
            FrameLayout access$getContentFrameLayout$p = WebDialog.this.contentFrameLayout;
            if (access$getContentFrameLayout$p != null) {
                access$getContentFrameLayout$p.setBackgroundColor(0);
            }
            WebView webView2 = WebDialog.this.getWebView();
            if (webView2 != null) {
                webView2.setVisibility(0);
            }
            ImageView access$getCrossImageView$p = WebDialog.this.crossImageView;
            if (access$getCrossImageView$p != null) {
                access$getCrossImageView$p.setVisibility(0);
            }
            WebDialog.this.isPageFinished = true;
        }
    }

    public static class Builder {
        private AccessToken accessToken;
        private String action;
        private String applicationId;
        private Context context;
        private OnCompleteListener listener;
        private Bundle parameters;
        private int theme;

        public final Context getContext() {
            return this.context;
        }

        public final String getApplicationId() {
            return this.applicationId;
        }

        public final int getTheme() {
            return this.theme;
        }

        public final OnCompleteListener getListener() {
            return this.listener;
        }

        public final Bundle getParameters() {
            return this.parameters;
        }

        public Builder(Context context2, String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(str, UrlHandler.ACTION);
            AccessToken.Companion companion = AccessToken.Companion;
            this.accessToken = companion.getCurrentAccessToken();
            if (!companion.isCurrentAccessTokenActive()) {
                String metadataApplicationId = Utility.getMetadataApplicationId(context2);
                if (metadataApplicationId != null) {
                    this.applicationId = metadataApplicationId;
                } else {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
            }
            finishInit(context2, str, bundle);
        }

        public Builder(Context context2, String str, String str2, Bundle bundle) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(str2, UrlHandler.ACTION);
            this.applicationId = Validate.notNullOrEmpty(str == null ? Utility.getMetadataApplicationId(context2) : str, "applicationId");
            finishInit(context2, str2, bundle);
        }

        public final Builder setOnCompleteListener(OnCompleteListener onCompleteListener) {
            this.listener = onCompleteListener;
            return this;
        }

        public WebDialog build() {
            AccessToken accessToken2 = this.accessToken;
            if (accessToken2 != null) {
                Bundle bundle = this.parameters;
                String str = null;
                if (bundle != null) {
                    bundle.putString("app_id", accessToken2 != null ? accessToken2.getApplicationId() : null);
                }
                Bundle bundle2 = this.parameters;
                if (bundle2 != null) {
                    AccessToken accessToken3 = this.accessToken;
                    if (accessToken3 != null) {
                        str = accessToken3.getToken();
                    }
                    bundle2.putString("access_token", str);
                }
            } else {
                Bundle bundle3 = this.parameters;
                if (bundle3 != null) {
                    bundle3.putString("app_id", this.applicationId);
                }
            }
            Companion companion = WebDialog.Companion;
            Context context2 = this.context;
            if (context2 != null) {
                return companion.newInstance(context2, this.action, this.parameters, this.theme, this.listener);
            }
            throw new IllegalStateException("Required value was null.");
        }

        private final void finishInit(Context context2, String str, Bundle bundle) {
            this.context = context2;
            this.action = str;
            if (bundle != null) {
                this.parameters = bundle;
            } else {
                this.parameters = new Bundle();
            }
        }
    }

    private final class UploadStagingResourcesTask extends AsyncTask {
        private final String action;
        private Exception[] exceptions = new Exception[0];
        private final Bundle parameters;
        final /* synthetic */ WebDialog this$0;

        public /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            if (CrashShieldHandler.isObjectCrashing(this)) {
                return null;
            }
            try {
                return doInBackground((Void[]) objArr);
            } catch (Throwable th) {
                CrashShieldHandler.handleThrowable(th, this);
                return null;
            }
        }

        public /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
            if (!CrashShieldHandler.isObjectCrashing(this)) {
                try {
                    onPostExecute((String[]) obj);
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            }
        }

        public UploadStagingResourcesTask(WebDialog webDialog, String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(str, UrlHandler.ACTION);
            Intrinsics.checkNotNullParameter(bundle, "parameters");
            this.this$0 = webDialog;
            this.action = str;
            this.parameters = bundle;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:29|30|(2:33|31)|42|34) */
        /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
            r12 = r3.iterator();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x008d, code lost:
            if (r12.hasNext() != false) goto L_0x008f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x008f, code lost:
            ((com.facebook.GraphRequestAsyncTask) r12.next()).cancel(true);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0099, code lost:
            return null;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0085 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.String[] doInBackground(java.lang.Void... r12) {
            /*
                r11 = this;
                boolean r0 = com.facebook.internal.instrument.crashshield.CrashShieldHandler.isObjectCrashing(r11)
                r1 = 0
                if (r0 == 0) goto L_0x0008
                return r1
            L_0x0008:
                java.lang.String r0 = "p0"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)     // Catch:{ all -> 0x0050 }
                android.os.Bundle r12 = r11.parameters     // Catch:{ all -> 0x0050 }
                java.lang.String r0 = "media"
                java.lang.String[] r12 = r12.getStringArray(r0)     // Catch:{ all -> 0x0050 }
                if (r12 != 0) goto L_0x0018
                return r1
            L_0x0018:
                int r0 = r12.length     // Catch:{ all -> 0x0050 }
                java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ all -> 0x0050 }
                int r2 = r12.length     // Catch:{ all -> 0x0050 }
                java.lang.Exception[] r2 = new java.lang.Exception[r2]     // Catch:{ all -> 0x0050 }
                r11.exceptions = r2     // Catch:{ all -> 0x0050 }
                java.util.concurrent.CountDownLatch r2 = new java.util.concurrent.CountDownLatch     // Catch:{ all -> 0x0050 }
                int r3 = r12.length     // Catch:{ all -> 0x0050 }
                r2.<init>(r3)     // Catch:{ all -> 0x0050 }
                java.util.concurrent.ConcurrentLinkedQueue r3 = new java.util.concurrent.ConcurrentLinkedQueue     // Catch:{ all -> 0x0050 }
                r3.<init>()     // Catch:{ all -> 0x0050 }
                com.facebook.AccessToken$Companion r4 = com.facebook.AccessToken.Companion     // Catch:{ all -> 0x0050 }
                com.facebook.AccessToken r4 = r4.getCurrentAccessToken()     // Catch:{ all -> 0x0050 }
                r5 = 1
                int r6 = r12.length     // Catch:{ Exception -> 0x0085 }
                r7 = 0
            L_0x0034:
                if (r7 >= r6) goto L_0x0081
                boolean r8 = r11.isCancelled()     // Catch:{ Exception -> 0x0085 }
                if (r8 == 0) goto L_0x0053
                java.util.Iterator r12 = r3.iterator()     // Catch:{ Exception -> 0x0085 }
            L_0x0040:
                boolean r0 = r12.hasNext()     // Catch:{ Exception -> 0x0085 }
                if (r0 == 0) goto L_0x0052
                java.lang.Object r0 = r12.next()     // Catch:{ Exception -> 0x0085 }
                com.facebook.GraphRequestAsyncTask r0 = (com.facebook.GraphRequestAsyncTask) r0     // Catch:{ Exception -> 0x0085 }
                r0.cancel(r5)     // Catch:{ Exception -> 0x0085 }
                goto L_0x0040
            L_0x0050:
                r12 = move-exception
                goto L_0x009a
            L_0x0052:
                return r1
            L_0x0053:
                r8 = r12[r7]     // Catch:{ Exception -> 0x0085 }
                android.net.Uri r8 = android.net.Uri.parse(r8)     // Catch:{ Exception -> 0x0085 }
                boolean r9 = com.facebook.internal.Utility.isWebUri(r8)     // Catch:{ Exception -> 0x0085 }
                if (r9 == 0) goto L_0x0069
                java.lang.String r8 = r8.toString()     // Catch:{ Exception -> 0x0085 }
                r0[r7] = r8     // Catch:{ Exception -> 0x0085 }
                r2.countDown()     // Catch:{ Exception -> 0x0085 }
                goto L_0x007e
            L_0x0069:
                com.facebook.internal.WebDialog$UploadStagingResourcesTask$$ExternalSyntheticLambda0 r9 = new com.facebook.internal.WebDialog$UploadStagingResourcesTask$$ExternalSyntheticLambda0     // Catch:{ Exception -> 0x0085 }
                r9.<init>(r0, r7, r11, r2)     // Catch:{ Exception -> 0x0085 }
                java.lang.String r10 = "uri"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r10)     // Catch:{ Exception -> 0x0085 }
                com.facebook.GraphRequest r8 = com.facebook.share.internal.ShareInternalUtility.newUploadStagingResourceWithImageRequest((com.facebook.AccessToken) r4, (android.net.Uri) r8, (com.facebook.GraphRequest.Callback) r9)     // Catch:{ Exception -> 0x0085 }
                com.facebook.GraphRequestAsyncTask r8 = r8.executeAsync()     // Catch:{ Exception -> 0x0085 }
                r3.add(r8)     // Catch:{ Exception -> 0x0085 }
            L_0x007e:
                int r7 = r7 + 1
                goto L_0x0034
            L_0x0081:
                r2.await()     // Catch:{ Exception -> 0x0085 }
                return r0
            L_0x0085:
                java.util.Iterator r12 = r3.iterator()     // Catch:{ all -> 0x0050 }
            L_0x0089:
                boolean r0 = r12.hasNext()     // Catch:{ all -> 0x0050 }
                if (r0 == 0) goto L_0x0099
                java.lang.Object r0 = r12.next()     // Catch:{ all -> 0x0050 }
                com.facebook.GraphRequestAsyncTask r0 = (com.facebook.GraphRequestAsyncTask) r0     // Catch:{ all -> 0x0050 }
                r0.cancel(r5)     // Catch:{ all -> 0x0050 }
                goto L_0x0089
            L_0x0099:
                return r1
            L_0x009a:
                com.facebook.internal.instrument.crashshield.CrashShieldHandler.handleThrowable(r12, r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.WebDialog.UploadStagingResourcesTask.doInBackground(java.lang.Void[]):java.lang.String[]");
        }

        /* access modifiers changed from: private */
        public static final void doInBackground$lambda$0(String[] strArr, int i, UploadStagingResourcesTask uploadStagingResourcesTask, CountDownLatch countDownLatch, GraphResponse graphResponse) {
            Intrinsics.checkNotNullParameter(strArr, "$results");
            Intrinsics.checkNotNullParameter(uploadStagingResourcesTask, "this$0");
            Intrinsics.checkNotNullParameter(countDownLatch, "$latch");
            Intrinsics.checkNotNullParameter(graphResponse, "response");
            try {
                FacebookRequestError error = graphResponse.getError();
                String str = "Error staging photo.";
                if (error != null) {
                    String errorMessage = error.getErrorMessage();
                    if (errorMessage != null) {
                        str = errorMessage;
                    }
                    throw new FacebookGraphResponseException(graphResponse, str);
                }
                JSONObject jSONObject = graphResponse.getJSONObject();
                if (jSONObject != null) {
                    String optString = jSONObject.optString("uri");
                    if (optString != null) {
                        strArr[i] = optString;
                        countDownLatch.countDown();
                        return;
                    }
                    throw new FacebookException(str);
                }
                throw new FacebookException(str);
            } catch (Exception e) {
                uploadStagingResourcesTask.exceptions[i] = e;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String[] strArr) {
            if (!CrashShieldHandler.isObjectCrashing(this)) {
                try {
                    ProgressDialog access$getSpinner$p = this.this$0.spinner;
                    if (access$getSpinner$p != null) {
                        access$getSpinner$p.dismiss();
                    }
                    for (Exception exc : this.exceptions) {
                        if (exc != null) {
                            this.this$0.sendErrorToListener(exc);
                            return;
                        }
                    }
                    if (strArr == null) {
                        this.this$0.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                        return;
                    }
                    List asList = ArraysKt.asList(strArr);
                    if (asList.contains((Object) null)) {
                        this.this$0.sendErrorToListener(new FacebookException("Failed to stage photos for web dialog"));
                        return;
                    }
                    Utility.putJSONValueInBundle(this.parameters, "media", new JSONArray(asList));
                    this.this$0.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/dialog/" + this.action, this.parameters).toString();
                    ImageView access$getCrossImageView$p = this.this$0.crossImageView;
                    if (access$getCrossImageView$p != null) {
                        this.this$0.setUpWebView((access$getCrossImageView$p.getDrawable().getIntrinsicWidth() / 2) + 1);
                        return;
                    }
                    throw new IllegalStateException("Required value was null.");
                } catch (Throwable th) {
                    CrashShieldHandler.handleThrowable(th, this);
                }
            }
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: protected */
        public final void initDefaultTheme(Context context) {
            if (context != null) {
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                    if ((applicationInfo != null ? applicationInfo.metaData : null) != null && WebDialog.webDialogTheme == 0) {
                        setWebDialogTheme(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
        }

        public final WebDialog newInstance(Context context, String str, Bundle bundle, int i, OnCompleteListener onCompleteListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            WebDialog.initDefaultTheme(context);
            return new WebDialog(context, str, bundle, i, LoginTargetApp.FACEBOOK, onCompleteListener, (DefaultConstructorMarker) null);
        }

        public final WebDialog newInstance(Context context, String str, Bundle bundle, int i, LoginTargetApp loginTargetApp, OnCompleteListener onCompleteListener) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(loginTargetApp, "targetApp");
            WebDialog.initDefaultTheme(context);
            return new WebDialog(context, str, bundle, i, loginTargetApp, onCompleteListener, (DefaultConstructorMarker) null);
        }

        public final int getWebDialogTheme() {
            Validate.sdkInitialized();
            return WebDialog.webDialogTheme;
        }

        public final void setWebDialogTheme(int i) {
            if (i == 0) {
                i = WebDialog.DEFAULT_THEME;
            }
            WebDialog.webDialogTheme = i;
        }
    }
}
