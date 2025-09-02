package com.facebook.soloader;

import android.content.Context;

public interface RecoverableSoSource {
    SoSource recover(Context context);
}
