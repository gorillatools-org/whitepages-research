package com.google.firebase.crashlytics.ndk;

import android.content.res.AssetManager;

interface NativeApi {
    boolean initialize(String str, AssetManager assetManager);
}
