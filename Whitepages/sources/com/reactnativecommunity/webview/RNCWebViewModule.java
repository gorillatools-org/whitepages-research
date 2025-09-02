package com.reactnativecommunity.webview;

import android.app.DownloadManager;
import android.net.Uri;
import android.webkit.ValueCallback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "RNCWebViewModule")
public class RNCWebViewModule extends ReactContextBaseJavaModule {
    private final RNCWebViewModuleImpl mRNCWebViewModuleImpl;

    public RNCWebViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mRNCWebViewModuleImpl = new RNCWebViewModuleImpl(reactApplicationContext);
    }

    @ReactMethod
    public void isFileUploadSupported(Promise promise) {
        promise.resolve(Boolean.valueOf(this.mRNCWebViewModuleImpl.isFileUploadSupported()));
    }

    @ReactMethod
    public void shouldStartLoadWithLockIdentifier(boolean z, double d) {
        this.mRNCWebViewModuleImpl.shouldStartLoadWithLockIdentifier(z, d);
    }

    public void startPhotoPickerIntent(ValueCallback<Uri> valueCallback, String str) {
        this.mRNCWebViewModuleImpl.startPhotoPickerIntent(str, valueCallback);
    }

    public boolean startPhotoPickerIntent(ValueCallback<Uri[]> valueCallback, String[] strArr, boolean z, boolean z2) {
        return this.mRNCWebViewModuleImpl.startPhotoPickerIntent(strArr, z, valueCallback, z2);
    }

    public void setDownloadRequest(DownloadManager.Request request) {
        this.mRNCWebViewModuleImpl.setDownloadRequest(request);
    }

    public void downloadFile(String str) {
        this.mRNCWebViewModuleImpl.downloadFile(str);
    }

    public boolean grantFileDownloaderPermissions(String str, String str2) {
        return this.mRNCWebViewModuleImpl.grantFileDownloaderPermissions(str, str2);
    }

    public String getName() {
        return "RNCWebViewModule";
    }
}
