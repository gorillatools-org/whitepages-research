package com.google.firebase.crashlytics.internal.settings;

import org.json.JSONObject;

interface SettingsSpiCall {
    JSONObject invoke(SettingsRequest settingsRequest, boolean z);
}
