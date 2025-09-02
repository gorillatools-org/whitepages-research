package com.reactnativecommunity.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule;

@ReactModule(name = "RNCClipboard")
public class ClipboardModule extends NativeClipboardModuleSpec {
    public static final String CLIPBOARD_TEXT_CHANGED = "RNCClipboard_TEXT_CHANGED";
    public static final String MIMETYPE_HEIC = "image/heic";
    public static final String MIMETYPE_HEIF = "image/heif";
    public static final String MIMETYPE_JPEG = "image/jpeg";
    public static final String MIMETYPE_JPG = "image/jpg";
    public static final String MIMETYPE_PNG = "image/png";
    public static final String MIMETYPE_WEBP = "image/webp";
    public static final String NAME = "RNCClipboard";
    private ClipboardManager.OnPrimaryClipChangedListener listener = null;
    /* access modifiers changed from: private */
    public ReactApplicationContext reactContext;

    @ReactMethod
    public void addListener(String str) {
    }

    public void removeListeners(double d) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public void setStrings(ReadableArray readableArray) {
    }

    public ClipboardModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.reactContext = reactApplicationContext;
    }

    public String getName() {
        return NAME;
    }

    private ClipboardManager getClipboardService() {
        return (ClipboardManager) this.reactContext.getSystemService("clipboard");
    }

    @ReactMethod
    public void getString(Promise promise) {
        try {
            ClipData primaryClip = getClipboardService().getPrimaryClip();
            if (primaryClip != null) {
                if (primaryClip.getItemCount() >= 1) {
                    ClipData.Item itemAt = primaryClip.getItemAt(0);
                    promise.resolve("" + itemAt.getText());
                    return;
                }
            }
            promise.resolve("");
        } catch (Exception e) {
            promise.reject((Throwable) e);
        }
    }

    public void getStrings(Promise promise) {
        promise.reject("Clipboard:getStrings", "getStrings is not supported on Android");
    }

    public void getImagePNG(Promise promise) {
        promise.reject("Clipboard:getImagePNG", "getImagePNG is not supported on Android");
    }

    public void getImageJPG(Promise promise) {
        promise.reject("Clipboard:getImageJPG", "getImageJPG is not supported on Android");
    }

    public void setImage(String str, Promise promise) {
        promise.reject("Clipboard:setImage", "setImage is not supported on Android");
    }

    @ReactMethod
    public void setString(String str) {
        try {
            getClipboardService().setPrimaryClip(ClipData.newPlainText((CharSequence) null, str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void hasString(Promise promise) {
        boolean z;
        try {
            ClipData primaryClip = getClipboardService().getPrimaryClip();
            if (primaryClip != null) {
                z = true;
                if (primaryClip.getItemCount() >= 1) {
                    promise.resolve(Boolean.valueOf(z));
                }
            }
            z = false;
            promise.resolve(Boolean.valueOf(z));
        } catch (Exception e) {
            promise.reject((Throwable) e);
        }
    }

    public void hasImage(Promise promise) {
        promise.reject("Clipboard:hasImage", "hasImage is not supported on Android");
    }

    public void hasURL(Promise promise) {
        promise.reject("Clipboard:hasURL", "hasURL is not supported on Android");
    }

    public void hasNumber(Promise promise) {
        promise.reject("Clipboard:hasNumber", "hasNumber is not supported on Android");
    }

    public void hasWebURL(Promise promise) {
        promise.reject("Clipboard:hasWebURL", "hasWebURL is not supported on Android");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0094, code lost:
        r5 = 65535;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0097, code lost:
        if (r5 == 0) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        if (r5 == 1) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009b, code lost:
        if (r5 == 2) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009d, code lost:
        if (r5 == 3) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x009f, code lost:
        if (r5 == 4) goto L_0x00b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00a1, code lost:
        if (r5 == 5) goto L_0x00a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00a3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00a8, code lost:
        if (android.os.Build.VERSION.SDK_INT <= 29) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00aa, code lost:
        r0.compress(com.reactnativecommunity.clipboard.ClipboardModule$$ExternalSyntheticApiModelOutline0.m(), 100, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b2, code lost:
        r0.compress(android.graphics.Bitmap.CompressFormat.WEBP, 100, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00b8, code lost:
        r0.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00be, code lost:
        r0.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c3, code lost:
        r13.resolve("data:" + r4 + ";base64," + android.util.Base64.encodeToString(r3.toByteArray(), 0));
     */
    @com.facebook.react.bridge.ReactMethod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getImage(com.facebook.react.bridge.Promise r13) {
        /*
            r12 = this;
            android.content.ClipboardManager r0 = r12.getClipboardService()
            boolean r1 = r0.hasPrimaryClip()
            java.lang.String r2 = ""
            if (r1 != 0) goto L_0x0011
            r13.resolve(r2)
            goto L_0x00f1
        L_0x0011:
            android.content.ClipDescription r1 = r0.getPrimaryClipDescription()
            java.lang.String r3 = "text/plain"
            boolean r1 = r1.hasMimeType(r3)
            if (r1 == 0) goto L_0x0022
            r13.resolve(r2)
            goto L_0x00f1
        L_0x0022:
            android.content.ClipData r0 = r0.getPrimaryClip()
            if (r0 == 0) goto L_0x00ee
            r1 = 0
            android.content.ClipData$Item r0 = r0.getItemAt(r1)
            android.net.Uri r0 = r0.getUri()
            if (r0 == 0) goto L_0x00ee
            com.facebook.react.bridge.ReactApplicationContext r3 = r12.reactContext
            android.content.ContentResolver r3 = r3.getContentResolver()
            java.lang.String r4 = r3.getType(r0)
            if (r4 == 0) goto L_0x00ee
            android.graphics.Bitmap r0 = android.provider.MediaStore.Images.Media.getBitmap(r3, r0)     // Catch:{ IOException -> 0x005f }
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x005f }
            r3.<init>()     // Catch:{ IOException -> 0x005f }
            int r5 = r4.hashCode()     // Catch:{ IOException -> 0x005f }
            r6 = 3
            r7 = 4
            r8 = 5
            r9 = 1
            r10 = 2
            switch(r5) {
                case -1487464693: goto L_0x008a;
                case -1487464690: goto L_0x0080;
                case -1487394660: goto L_0x0076;
                case -1487018032: goto L_0x006c;
                case -879264467: goto L_0x0062;
                case -879258763: goto L_0x0055;
                default: goto L_0x0054;
            }     // Catch:{ IOException -> 0x005f }
        L_0x0054:
            goto L_0x0094
        L_0x0055:
            java.lang.String r5 = "image/png"
            boolean r5 = r4.equals(r5)     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0094
            r5 = r10
            goto L_0x0095
        L_0x005f:
            r0 = move-exception
            goto L_0x00e8
        L_0x0062:
            java.lang.String r5 = "image/jpg"
            boolean r5 = r4.equals(r5)     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0094
            r5 = r9
            goto L_0x0095
        L_0x006c:
            java.lang.String r5 = "image/webp"
            boolean r5 = r4.equals(r5)     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0094
            r5 = r8
            goto L_0x0095
        L_0x0076:
            java.lang.String r5 = "image/jpeg"
            boolean r5 = r4.equals(r5)     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0094
            r5 = r1
            goto L_0x0095
        L_0x0080:
            java.lang.String r5 = "image/heif"
            boolean r5 = r4.equals(r5)     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0094
            r5 = r7
            goto L_0x0095
        L_0x008a:
            java.lang.String r5 = "image/heic"
            boolean r5 = r4.equals(r5)     // Catch:{ IOException -> 0x005f }
            if (r5 == 0) goto L_0x0094
            r5 = r6
            goto L_0x0095
        L_0x0094:
            r5 = -1
        L_0x0095:
            r11 = 100
            if (r5 == 0) goto L_0x00be
            if (r5 == r9) goto L_0x00be
            if (r5 == r10) goto L_0x00b8
            if (r5 == r6) goto L_0x00b8
            if (r5 == r7) goto L_0x00b8
            if (r5 == r8) goto L_0x00a4
            return
        L_0x00a4:
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ IOException -> 0x005f }
            r6 = 29
            if (r5 <= r6) goto L_0x00b2
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.WEBP_LOSSLESS     // Catch:{ IOException -> 0x005f }
            r0.compress(r5, r11, r3)     // Catch:{ IOException -> 0x005f }
            goto L_0x00c3
        L_0x00b2:
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.WEBP     // Catch:{ IOException -> 0x005f }
            r0.compress(r5, r11, r3)     // Catch:{ IOException -> 0x005f }
            goto L_0x00c3
        L_0x00b8:
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ IOException -> 0x005f }
            r0.compress(r5, r11, r3)     // Catch:{ IOException -> 0x005f }
            goto L_0x00c3
        L_0x00be:
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ IOException -> 0x005f }
            r0.compress(r5, r11, r3)     // Catch:{ IOException -> 0x005f }
        L_0x00c3:
            byte[] r0 = r3.toByteArray()     // Catch:{ IOException -> 0x005f }
            java.lang.String r0 = android.util.Base64.encodeToString(r0, r1)     // Catch:{ IOException -> 0x005f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x005f }
            r1.<init>()     // Catch:{ IOException -> 0x005f }
            java.lang.String r3 = "data:"
            r1.append(r3)     // Catch:{ IOException -> 0x005f }
            r1.append(r4)     // Catch:{ IOException -> 0x005f }
            java.lang.String r3 = ";base64,"
            r1.append(r3)     // Catch:{ IOException -> 0x005f }
            r1.append(r0)     // Catch:{ IOException -> 0x005f }
            java.lang.String r0 = r1.toString()     // Catch:{ IOException -> 0x005f }
            r13.resolve(r0)     // Catch:{ IOException -> 0x005f }
            goto L_0x00ee
        L_0x00e8:
            r13.reject((java.lang.Throwable) r0)
            r0.printStackTrace()
        L_0x00ee:
            r13.resolve(r2)
        L_0x00f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.clipboard.ClipboardModule.getImage(com.facebook.react.bridge.Promise):void");
    }

    @ReactMethod
    public void setListener() {
        try {
            ClipboardManager clipboardService = getClipboardService();
            AnonymousClass1 r1 = new ClipboardManager.OnPrimaryClipChangedListener() {
                public void onPrimaryClipChanged() {
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) ClipboardModule.this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(ClipboardModule.CLIPBOARD_TEXT_CHANGED, (Object) null);
                }
            };
            this.listener = r1;
            clipboardService.addPrimaryClipChangedListener(r1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ReactMethod
    public void removeListener() {
        if (this.listener != null) {
            try {
                getClipboardService().removePrimaryClipChangedListener(this.listener);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
