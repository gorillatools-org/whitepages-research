package com.google.android.play.core.review;

import android.content.Context;

public abstract class ReviewManagerFactory {
    public static ReviewManager create(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        return new zzd(new zzi(context));
    }
}
