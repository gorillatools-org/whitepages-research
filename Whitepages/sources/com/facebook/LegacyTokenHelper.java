package com.facebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.facebook.internal.Logger;
import java.util.ArrayList;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class LegacyTokenHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = LegacyTokenHelper.class.getSimpleName();
    private final SharedPreferences cache;
    private final String cacheKey;

    public LegacyTokenHelper(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "context");
        str = (str == null || str.length() == 0) ? "com.facebook.SharedPreferencesTokenCachingStrategy.DEFAULT_KEY" : str;
        this.cacheKey = str;
        Context applicationContext = context.getApplicationContext();
        SharedPreferences sharedPreferences = (applicationContext != null ? applicationContext : context).getSharedPreferences(str, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ey, Context.MODE_PRIVATE)");
        this.cache = sharedPreferences;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LegacyTokenHelper(Context context, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : str);
    }

    public final Bundle load() {
        Bundle bundle = new Bundle();
        for (String next : this.cache.getAll().keySet()) {
            try {
                Intrinsics.checkNotNullExpressionValue(next, "key");
                deserializeKey(next, bundle);
            } catch (JSONException e) {
                Logger.Companion companion = Logger.Companion;
                LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                String str = TAG;
                Intrinsics.checkNotNullExpressionValue(str, "TAG");
                companion.log(loggingBehavior, 5, str, "Error reading cached value for key: '" + next + "' -- " + e);
                return null;
            }
        }
        return bundle;
    }

    public final void clear() {
        this.cache.edit().clear().apply();
    }

    private final void deserializeKey(String str, Bundle bundle) {
        String str2;
        String string;
        String string2 = this.cache.getString(str, "{}");
        if (string2 != null) {
            Intrinsics.checkNotNullExpressionValue(string2, "checkNotNull(cache.getString(key, \"{}\"))");
            JSONObject jSONObject = new JSONObject(string2);
            String string3 = jSONObject.getString("valueType");
            if (string3 != null) {
                int i = 0;
                switch (string3.hashCode()) {
                    case -1573317553:
                        if (string3.equals("stringList")) {
                            JSONArray jSONArray = jSONObject.getJSONArray("value");
                            int length = jSONArray.length();
                            ArrayList arrayList = new ArrayList(length);
                            while (i < length) {
                                Object obj = jSONArray.get(i);
                                if (obj == JSONObject.NULL) {
                                    str2 = null;
                                } else {
                                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                                    str2 = (String) obj;
                                }
                                arrayList.add(i, str2);
                                i++;
                            }
                            bundle.putStringArrayList(str, arrayList);
                            return;
                        }
                        return;
                    case -1383386164:
                        if (string3.equals("bool[]")) {
                            JSONArray jSONArray2 = jSONObject.getJSONArray("value");
                            int length2 = jSONArray2.length();
                            boolean[] zArr = new boolean[length2];
                            while (i < length2) {
                                zArr[i] = jSONArray2.getBoolean(i);
                                i++;
                            }
                            bundle.putBooleanArray(str, zArr);
                            return;
                        }
                        return;
                    case -1374008726:
                        if (string3.equals("byte[]")) {
                            JSONArray jSONArray3 = jSONObject.getJSONArray("value");
                            int length3 = jSONArray3.length();
                            byte[] bArr = new byte[length3];
                            while (i < length3) {
                                bArr[i] = (byte) jSONArray3.getInt(i);
                                i++;
                            }
                            bundle.putByteArray(str, bArr);
                            return;
                        }
                        return;
                    case -1361632968:
                        if (string3.equals("char[]")) {
                            JSONArray jSONArray4 = jSONObject.getJSONArray("value");
                            int length4 = jSONArray4.length();
                            char[] cArr = new char[length4];
                            for (int i2 = 0; i2 < length4; i2++) {
                                String string4 = jSONArray4.getString(i2);
                                if (string4 != null && string4.length() == 1) {
                                    cArr[i2] = string4.charAt(0);
                                }
                            }
                            bundle.putCharArray(str, cArr);
                            return;
                        }
                        return;
                    case -1325958191:
                        if (string3.equals("double")) {
                            bundle.putDouble(str, jSONObject.getDouble("value"));
                            return;
                        }
                        return;
                    case -1097129250:
                        if (string3.equals("long[]")) {
                            JSONArray jSONArray5 = jSONObject.getJSONArray("value");
                            int length5 = jSONArray5.length();
                            long[] jArr = new long[length5];
                            while (i < length5) {
                                jArr[i] = jSONArray5.getLong(i);
                                i++;
                            }
                            bundle.putLongArray(str, jArr);
                            return;
                        }
                        return;
                    case -891985903:
                        if (string3.equals("string")) {
                            bundle.putString(str, jSONObject.getString("value"));
                            return;
                        }
                        return;
                    case -766441794:
                        if (string3.equals("float[]")) {
                            JSONArray jSONArray6 = jSONObject.getJSONArray("value");
                            int length6 = jSONArray6.length();
                            float[] fArr = new float[length6];
                            while (i < length6) {
                                fArr[i] = (float) jSONArray6.getDouble(i);
                                i++;
                            }
                            bundle.putFloatArray(str, fArr);
                            return;
                        }
                        return;
                    case 104431:
                        if (string3.equals("int")) {
                            bundle.putInt(str, jSONObject.getInt("value"));
                            return;
                        }
                        return;
                    case 3029738:
                        if (string3.equals("bool")) {
                            bundle.putBoolean(str, jSONObject.getBoolean("value"));
                            return;
                        }
                        return;
                    case 3039496:
                        if (string3.equals("byte")) {
                            bundle.putByte(str, (byte) jSONObject.getInt("value"));
                            return;
                        }
                        return;
                    case 3052374:
                        if (string3.equals("char") && (string = jSONObject.getString("value")) != null && string.length() == 1) {
                            bundle.putChar(str, string.charAt(0));
                            return;
                        }
                        return;
                    case 3118337:
                        if (string3.equals("enum")) {
                            try {
                                Class cls = Class.forName(jSONObject.getString("enumType"));
                                Intrinsics.checkNotNull(cls, "null cannot be cast to non-null type java.lang.Class<out kotlin.Enum<*>>");
                                bundle.putSerializable(str, Enum.valueOf(cls, jSONObject.getString("value")));
                                return;
                            } catch (ClassNotFoundException | IllegalArgumentException unused) {
                                return;
                            }
                        } else {
                            return;
                        }
                    case 3327612:
                        if (string3.equals("long")) {
                            bundle.putLong(str, jSONObject.getLong("value"));
                            return;
                        }
                        return;
                    case 97526364:
                        if (string3.equals("float")) {
                            bundle.putFloat(str, (float) jSONObject.getDouble("value"));
                            return;
                        }
                        return;
                    case 100361105:
                        if (string3.equals("int[]")) {
                            JSONArray jSONArray7 = jSONObject.getJSONArray("value");
                            int length7 = jSONArray7.length();
                            int[] iArr = new int[length7];
                            while (i < length7) {
                                iArr[i] = jSONArray7.getInt(i);
                                i++;
                            }
                            bundle.putIntArray(str, iArr);
                            return;
                        }
                        return;
                    case 109413500:
                        if (string3.equals("short")) {
                            bundle.putShort(str, (short) jSONObject.getInt("value"));
                            return;
                        }
                        return;
                    case 1359468275:
                        if (string3.equals("double[]")) {
                            JSONArray jSONArray8 = jSONObject.getJSONArray("value");
                            int length8 = jSONArray8.length();
                            double[] dArr = new double[length8];
                            while (i < length8) {
                                dArr[i] = jSONArray8.getDouble(i);
                                i++;
                            }
                            bundle.putDoubleArray(str, dArr);
                            return;
                        }
                        return;
                    case 2067161310:
                        if (string3.equals("short[]")) {
                            JSONArray jSONArray9 = jSONObject.getJSONArray("value");
                            int length9 = jSONArray9.length();
                            short[] sArr = new short[length9];
                            while (i < length9) {
                                sArr[i] = (short) jSONArray9.getInt(i);
                                i++;
                            }
                            bundle.putShortArray(str, sArr);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        } else {
            throw new IllegalStateException("Required value was null.");
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean hasTokenInformation(Bundle bundle) {
            String string;
            if (bundle == null || (string = bundle.getString("com.facebook.TokenCachingStrategy.Token")) == null || string.length() == 0 || bundle.getLong("com.facebook.TokenCachingStrategy.ExpirationDate", 0) == 0) {
                return false;
            }
            return true;
        }

        public final String getToken(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            return bundle.getString("com.facebook.TokenCachingStrategy.Token");
        }

        public final Date getExpirationDate(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            return getDate(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate");
        }

        public final AccessTokenSource getSource(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            if (bundle.containsKey("com.facebook.TokenCachingStrategy.AccessTokenSource")) {
                return (AccessTokenSource) bundle.getSerializable("com.facebook.TokenCachingStrategy.AccessTokenSource");
            }
            return bundle.getBoolean("com.facebook.TokenCachingStrategy.IsSSO") ? AccessTokenSource.FACEBOOK_APPLICATION_WEB : AccessTokenSource.WEB_VIEW;
        }

        public final Date getLastRefreshDate(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            return getDate(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate");
        }

        public final String getApplicationId(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            return bundle.getString("com.facebook.TokenCachingStrategy.ApplicationId");
        }

        private final Date getDate(Bundle bundle, String str) {
            if (bundle == null) {
                return null;
            }
            long j = bundle.getLong(str, Long.MIN_VALUE);
            if (j == Long.MIN_VALUE) {
                return null;
            }
            return new Date(j);
        }
    }
}
