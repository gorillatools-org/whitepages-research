package com.google.android.gms.common;

import com.google.android.gms.common.annotation.KeepForSdk;

public final class Scopes {
    public static final String APP_STATE = "https://www.googleapis.com/auth/appstate";
    public static final String CLOUD_SAVE = "https://www.googleapis.com/auth/datastoremobile";
    public static final String DRIVE_APPFOLDER = "https://www.googleapis.com/auth/drive.appdata";
    @KeepForSdk
    public static final String DRIVE_APPS = "https://www.googleapis.com/auth/drive.apps";
    public static final String DRIVE_FILE = "https://www.googleapis.com/auth/drive.file";
    @KeepForSdk
    public static final String DRIVE_FULL = "https://www.googleapis.com/auth/drive";
    public static final String EMAIL = "email";
    public static final String GAMES = "https://www.googleapis.com/auth/games";
    @KeepForSdk
    public static final String GAMES_LITE = "https://www.googleapis.com/auth/games_lite";
    @KeepForSdk
    public static final String LEGACY_USERINFO_EMAIL = "https://www.googleapis.com/auth/userinfo.email";
    @KeepForSdk
    public static final String LEGACY_USERINFO_PROFILE = "https://www.googleapis.com/auth/userinfo.profile";
    @KeepForSdk
    public static final String OPEN_ID = "openid";
    @Deprecated
    public static final String PLUS_LOGIN = "https://www.googleapis.com/auth/plus.login";
    public static final String PLUS_ME = "https://www.googleapis.com/auth/plus.me";
    public static final String PROFILE = "profile";

    private Scopes() {
    }
}
