package com.reactnativecommunity.webview;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.salesforce.marketingcloud.b;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class RNCWebViewModuleImpl implements ActivityEventListener {
    protected static final ShouldOverrideUrlLoadingLock shouldOverrideUrlLoadingLock = new ShouldOverrideUrlLoadingLock();
    /* access modifiers changed from: private */
    public final ReactApplicationContext mContext;
    /* access modifiers changed from: private */
    public DownloadManager.Request mDownloadRequest;
    private ValueCallback mFilePathCallback;
    private ValueCallback mFilePathCallbackLegacy;
    private File mOutputImage;
    private File mOutputVideo;

    public boolean isFileUploadSupported() {
        return true;
    }

    public void onNewIntent(Intent intent) {
    }

    public RNCWebViewModuleImpl(ReactApplicationContext reactApplicationContext) {
        this.mContext = reactApplicationContext;
        reactApplicationContext.addActivityEventListener(this);
    }

    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if (this.mFilePathCallback != null || this.mFilePathCallbackLegacy != null) {
            File file = this.mOutputImage;
            boolean z = false;
            boolean z2 = file != null && file.length() > 0;
            File file2 = this.mOutputVideo;
            if (file2 != null && file2.length() > 0) {
                z = true;
            }
            if (i != 1) {
                if (i == 3) {
                    if (i2 != -1) {
                        this.mFilePathCallbackLegacy.onReceiveValue((Object) null);
                    } else if (z2) {
                        this.mFilePathCallbackLegacy.onReceiveValue(getOutputUri(this.mOutputImage));
                    } else if (z) {
                        this.mFilePathCallbackLegacy.onReceiveValue(getOutputUri(this.mOutputVideo));
                    } else {
                        this.mFilePathCallbackLegacy.onReceiveValue(intent.getData());
                    }
                }
            } else if (i2 != -1) {
                ValueCallback valueCallback = this.mFilePathCallback;
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object) null);
                }
            } else if (z2) {
                this.mFilePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.mOutputImage)});
            } else if (z) {
                this.mFilePathCallback.onReceiveValue(new Uri[]{getOutputUri(this.mOutputVideo)});
            } else {
                this.mFilePathCallback.onReceiveValue(getSelectedFiles(intent, i2));
            }
            File file3 = this.mOutputImage;
            if (file3 != null && !z2) {
                file3.delete();
            }
            File file4 = this.mOutputVideo;
            if (file4 != null && !z) {
                file4.delete();
            }
            this.mFilePathCallback = null;
            this.mFilePathCallbackLegacy = null;
            this.mOutputImage = null;
            this.mOutputVideo = null;
        }
    }

    protected static class ShouldOverrideUrlLoadingLock {
        private double nextLockIdentifier = 1.0d;
        private final HashMap shouldOverrideLocks = new HashMap();

        protected enum ShouldOverrideCallbackState {
            UNDECIDED,
            SHOULD_OVERRIDE,
            DO_NOT_OVERRIDE
        }

        protected ShouldOverrideUrlLoadingLock() {
        }

        public synchronized Pair getNewLock() {
            double d;
            AtomicReference atomicReference;
            d = this.nextLockIdentifier;
            this.nextLockIdentifier = 1.0d + d;
            atomicReference = new AtomicReference(ShouldOverrideCallbackState.UNDECIDED);
            this.shouldOverrideLocks.put(Double.valueOf(d), atomicReference);
            return new Pair(Double.valueOf(d), atomicReference);
        }

        public synchronized AtomicReference getLock(Double d) {
            return (AtomicReference) this.shouldOverrideLocks.get(d);
        }

        public synchronized void removeLock(Double d) {
            this.shouldOverrideLocks.remove(d);
        }
    }

    private enum MimeType {
        DEFAULT("*/*"),
        IMAGE("image"),
        VIDEO("video");
        
        /* access modifiers changed from: private */
        public final String value;

        private MimeType(String str) {
            this.value = str;
        }
    }

    private PermissionListener getWebviewFileDownloaderPermissionListener(final String str, final String str2) {
        return new PermissionListener() {
            public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                if (i != 1) {
                    return false;
                }
                if (iArr.length <= 0 || iArr[0] != 0) {
                    Toast.makeText(RNCWebViewModuleImpl.this.mContext, str2, 1).show();
                } else if (RNCWebViewModuleImpl.this.mDownloadRequest != null) {
                    RNCWebViewModuleImpl.this.downloadFile(str);
                }
                return true;
            }
        };
    }

    public void shouldStartLoadWithLockIdentifier(boolean z, double d) {
        ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState shouldOverrideCallbackState;
        AtomicReference lock = shouldOverrideUrlLoadingLock.getLock(Double.valueOf(d));
        if (lock != null) {
            synchronized (lock) {
                if (z) {
                    try {
                        shouldOverrideCallbackState = ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.DO_NOT_OVERRIDE;
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    shouldOverrideCallbackState = ShouldOverrideUrlLoadingLock.ShouldOverrideCallbackState.SHOULD_OVERRIDE;
                }
                lock.set(shouldOverrideCallbackState);
                lock.notify();
            }
        }
    }

    public Uri[] getSelectedFiles(Intent intent, int i) {
        if (intent == null) {
            return null;
        }
        if (intent.getClipData() != null) {
            int itemCount = intent.getClipData().getItemCount();
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = intent.getClipData().getItemAt(i2).getUri();
            }
            return uriArr;
        } else if (intent.getData() == null || i != -1) {
            return null;
        } else {
            return WebChromeClient.FileChooserParams.parseResult(i, intent);
        }
    }

    public void startPhotoPickerIntent(String str, ValueCallback valueCallback) {
        Intent videoIntent;
        Intent photoIntent;
        this.mFilePathCallbackLegacy = valueCallback;
        Activity currentActivity = this.mContext.getCurrentActivity();
        Intent createChooser = Intent.createChooser(getFileChooserIntent(str), "");
        ArrayList arrayList = new ArrayList();
        if (acceptsImages(str).booleanValue() && (photoIntent = getPhotoIntent()) != null) {
            arrayList.add(photoIntent);
        }
        if (acceptsVideo(str).booleanValue() && (videoIntent = getVideoIntent()) != null) {
            arrayList.add(videoIntent);
        }
        createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
        if (createChooser.resolveActivity(currentActivity.getPackageManager()) != null) {
            currentActivity.startActivityForResult(createChooser, 3);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
    }

    public boolean startPhotoPickerIntent(String[] strArr, boolean z, ValueCallback valueCallback, boolean z2) {
        Intent videoIntent;
        this.mFilePathCallback = valueCallback;
        Activity currentActivity = this.mContext.getCurrentActivity();
        ArrayList arrayList = new ArrayList();
        Intent intent = null;
        if (!needsCameraPermission()) {
            if (acceptsImages(strArr).booleanValue() && (intent = getPhotoIntent()) != null) {
                arrayList.add(intent);
            }
            if (acceptsVideo(strArr).booleanValue() && (videoIntent = getVideoIntent()) != null) {
                arrayList.add(videoIntent);
            }
        }
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        if (!z2) {
            intent2.putExtra("android.intent.extra.INTENT", getFileChooserIntent(strArr, z));
            intent2.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[0]));
            intent = intent2;
        }
        if (intent == null) {
            Log.w("RNCWebViewModule", "there is no Camera permission");
        } else if (intent.resolveActivity(currentActivity.getPackageManager()) != null) {
            currentActivity.startActivityForResult(intent, 1);
        } else {
            Log.w("RNCWebViewModule", "there is no Activity to handle this Intent");
        }
        return true;
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        this.mDownloadRequest = request;
    }

    public void downloadFile(String str) {
        try {
            ((DownloadManager) this.mContext.getSystemService("download")).enqueue(this.mDownloadRequest);
            Toast.makeText(this.mContext, str, 1).show();
        } catch (IllegalArgumentException | SecurityException e) {
            Log.w("RNCWebViewModule", "Unsupported URI, aborting download", e);
        }
    }

    public boolean grantFileDownloaderPermissions(String str, String str2) {
        Activity currentActivity = this.mContext.getCurrentActivity();
        if (Build.VERSION.SDK_INT > 28) {
            return true;
        }
        boolean z = ContextCompat.checkSelfPermission(currentActivity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
        if (!z) {
            getPermissionAwareActivity().requestPermissions(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1, getWebviewFileDownloaderPermissionListener(str, str2));
        }
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean needsCameraPermission() {
        Activity currentActivity = this.mContext.getCurrentActivity();
        try {
            if (!Arrays.asList(currentActivity.getPackageManager().getPackageInfo(currentActivity.getApplicationContext().getPackageName(), b.v).requestedPermissions).contains("android.permission.CAMERA") || ContextCompat.checkSelfPermission(currentActivity, "android.permission.CAMERA") == 0) {
                return false;
            }
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    public Intent getPhotoIntent() {
        Intent intent;
        Throwable e;
        try {
            File capturedFile = getCapturedFile(MimeType.IMAGE);
            this.mOutputImage = capturedFile;
            Uri outputUri = getOutputUri(capturedFile);
            intent = new Intent("android.media.action.IMAGE_CAPTURE");
            try {
                intent.putExtra("output", outputUri);
            } catch (IOException | IllegalArgumentException e2) {
                e = e2;
            }
        } catch (IOException | IllegalArgumentException e3) {
            intent = null;
            e = e3;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        }
        return intent;
    }

    public Intent getVideoIntent() {
        Intent intent;
        Throwable e;
        try {
            File capturedFile = getCapturedFile(MimeType.VIDEO);
            this.mOutputVideo = capturedFile;
            Uri outputUri = getOutputUri(capturedFile);
            intent = new Intent("android.media.action.VIDEO_CAPTURE");
            try {
                intent.putExtra("output", outputUri);
            } catch (IOException | IllegalArgumentException e2) {
                e = e2;
            }
        } catch (IOException | IllegalArgumentException e3) {
            intent = null;
            e = e3;
            Log.e("CREATE FILE", "Error occurred while creating the File", e);
            e.printStackTrace();
            return intent;
        }
        return intent;
    }

    private Intent getFileChooserIntent(String str) {
        String access$200 = str.isEmpty() ? MimeType.DEFAULT.value : str;
        if (str.matches("\\.\\w+")) {
            access$200 = getMimeTypeFromExtension(str.replace(".", ""));
        }
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(access$200);
        return intent;
    }

    private Intent getFileChooserIntent(String[] strArr, boolean z) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType(MimeType.DEFAULT.value);
        intent.putExtra("android.intent.extra.MIME_TYPES", getAcceptedMimeType(strArr));
        intent.putExtra("android.intent.extra.ALLOW_MULTIPLE", z);
        return intent;
    }

    private Boolean acceptsImages(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains(MimeType.IMAGE.value));
    }

    private Boolean acceptsImages(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, MimeType.DEFAULT.value).booleanValue() || arrayContainsString(acceptedMimeType, MimeType.IMAGE.value).booleanValue());
    }

    private Boolean acceptsVideo(String str) {
        if (str.matches("\\.\\w+")) {
            str = getMimeTypeFromExtension(str.replace(".", ""));
        }
        return Boolean.valueOf(str.isEmpty() || str.toLowerCase().contains(MimeType.VIDEO.value));
    }

    private Boolean acceptsVideo(String[] strArr) {
        String[] acceptedMimeType = getAcceptedMimeType(strArr);
        return Boolean.valueOf(arrayContainsString(acceptedMimeType, MimeType.DEFAULT.value).booleanValue() || arrayContainsString(acceptedMimeType, MimeType.VIDEO.value).booleanValue());
    }

    private Boolean arrayContainsString(String[] strArr, String str) {
        for (String contains : strArr) {
            if (contains.contains(str)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private String[] getAcceptedMimeType(String[] strArr) {
        if (noAcceptTypesSet(strArr).booleanValue()) {
            return new String[]{MimeType.DEFAULT.value};
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (str.matches("\\.\\w+")) {
                String mimeTypeFromExtension = getMimeTypeFromExtension(str.replace(".", ""));
                if (mimeTypeFromExtension != null) {
                    strArr2[i] = mimeTypeFromExtension;
                } else {
                    strArr2[i] = str;
                }
            } else {
                strArr2[i] = str;
            }
        }
        return strArr2;
    }

    private String getMimeTypeFromExtension(String str) {
        if (str != null) {
            return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
        }
        return null;
    }

    public Uri getOutputUri(File file) {
        String packageName = this.mContext.getPackageName();
        ReactApplicationContext reactApplicationContext = this.mContext;
        return FileProvider.getUriForFile(reactApplicationContext, packageName + ".fileprovider", file);
    }

    /* renamed from: com.reactnativecommunity.webview.RNCWebViewModuleImpl$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModuleImpl$MimeType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.reactnativecommunity.webview.RNCWebViewModuleImpl$MimeType[] r0 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.MimeType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModuleImpl$MimeType = r0
                com.reactnativecommunity.webview.RNCWebViewModuleImpl$MimeType r1 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.MimeType.IMAGE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$reactnativecommunity$webview$RNCWebViewModuleImpl$MimeType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.reactnativecommunity.webview.RNCWebViewModuleImpl$MimeType r1 = com.reactnativecommunity.webview.RNCWebViewModuleImpl.MimeType.VIDEO     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.reactnativecommunity.webview.RNCWebViewModuleImpl.AnonymousClass2.<clinit>():void");
        }
    }

    public File getCapturedFile(MimeType mimeType) {
        String str;
        String str2;
        int i = AnonymousClass2.$SwitchMap$com$reactnativecommunity$webview$RNCWebViewModuleImpl$MimeType[mimeType.ordinal()];
        if (i == 1) {
            String str3 = Environment.DIRECTORY_PICTURES;
            str = "image-";
            str2 = ".jpg";
        } else if (i != 2) {
            str = "";
            str2 = str;
        } else {
            String str4 = Environment.DIRECTORY_MOVIES;
            str = "video-";
            str2 = ".mp4";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(String.valueOf(System.currentTimeMillis()));
        sb.append(str2);
        return File.createTempFile(str, str2, this.mContext.getExternalFilesDir((String) null));
    }

    private Boolean noAcceptTypesSet(String[] strArr) {
        String str;
        boolean z = true;
        if (!(strArr.length == 0 || (strArr.length == 1 && (str = strArr[0]) != null && str.length() == 0))) {
            z = false;
        }
        return Boolean.valueOf(z);
    }

    private PermissionAwareActivity getPermissionAwareActivity() {
        Activity currentActivity = this.mContext.getCurrentActivity();
        if (currentActivity == null) {
            throw new IllegalStateException("Tried to use permissions API while not attached to an Activity.");
        } else if (currentActivity instanceof PermissionAwareActivity) {
            return (PermissionAwareActivity) currentActivity;
        } else {
            throw new IllegalStateException("Tried to use permissions API but the host Activity doesn't implement PermissionAwareActivity.");
        }
    }
}
