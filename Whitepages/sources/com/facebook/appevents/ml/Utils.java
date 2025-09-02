package com.facebook.appevents.ml;

import android.text.TextUtils;
import com.facebook.FacebookSdk;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import org.json.JSONArray;
import org.json.JSONObject;

public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final int[] vectorize(String str, int i) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "texts");
            int[] iArr = new int[i];
            String normalizeString = normalizeString(str);
            Charset forName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(forName, "forName(\"UTF-8\")");
            byte[] bytes = normalizeString.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            for (int i2 = 0; i2 < i; i2++) {
                if (i2 < bytes.length) {
                    iArr[i2] = bytes[i2] & 255;
                } else {
                    iArr[i2] = 0;
                }
            }
            return iArr;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final File getMlDir() {
        Class<Utils> cls = Utils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            File file = new File(FacebookSdk.getApplicationContext().getFilesDir(), "facebook_ml/");
            if (file.exists() || file.mkdirs()) {
                return file;
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public static final Map parseModelWeights(File file) {
        int i;
        File file2 = file;
        Class<Utils> cls = Utils.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(file2, "file");
            try {
                FileInputStream fileInputStream = new FileInputStream(file2);
                int available = fileInputStream.available();
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                byte[] bArr = new byte[available];
                dataInputStream.readFully(bArr);
                dataInputStream.close();
                if (available < 4) {
                    return null;
                }
                int i2 = 0;
                ByteBuffer wrap = ByteBuffer.wrap(bArr, 0, 4);
                wrap.order(ByteOrder.LITTLE_ENDIAN);
                int i3 = wrap.getInt();
                int i4 = i3 + 4;
                if (available < i4) {
                    return null;
                }
                JSONObject jSONObject = new JSONObject(new String(bArr, 4, i3, Charsets.UTF_8));
                JSONArray names = jSONObject.names();
                int length = names.length();
                String[] strArr = new String[length];
                for (int i5 = 0; i5 < length; i5++) {
                    strArr[i5] = names.getString(i5);
                }
                ArraysKt.sort(strArr);
                HashMap hashMap = new HashMap();
                int i6 = 0;
                while (i6 < length) {
                    String str = strArr[i6];
                    if (str == null) {
                        i = i2;
                    } else {
                        JSONArray jSONArray = jSONObject.getJSONArray(str);
                        int length2 = jSONArray.length();
                        int[] iArr = new int[length2];
                        int i7 = 1;
                        while (i2 < length2) {
                            int i8 = jSONArray.getInt(i2);
                            iArr[i2] = i8;
                            i7 *= i8;
                            i2++;
                        }
                        int i9 = i7 * 4;
                        int i10 = i4 + i9;
                        if (i10 > available) {
                            return null;
                        }
                        ByteBuffer wrap2 = ByteBuffer.wrap(bArr, i4, i9);
                        wrap2.order(ByteOrder.LITTLE_ENDIAN);
                        MTensor mTensor = new MTensor(iArr);
                        i = 0;
                        wrap2.asFloatBuffer().get(mTensor.getData(), 0, i7);
                        hashMap.put(str, mTensor);
                        i4 = i10;
                    }
                    i6++;
                    i2 = i;
                }
                return hashMap;
            } catch (Exception unused) {
                return null;
            }
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public final String normalizeString(String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(str, "str");
            int length = str.length() - 1;
            int i = 0;
            boolean z = false;
            while (true) {
                if (i > length) {
                    break;
                }
                boolean z2 = Intrinsics.compare((int) str.charAt(!z ? i : length), 32) <= 0;
                if (!z) {
                    if (!z2) {
                        z = true;
                    } else {
                        i++;
                    }
                } else if (!z2) {
                    break;
                } else {
                    length--;
                }
            }
            String join = TextUtils.join(" ", (String[]) new Regex("\\s+").split(str.subSequence(i, length + 1).toString(), 0).toArray(new String[0]));
            Intrinsics.checkNotNullExpressionValue(join, "join(\" \", strArray)");
            return join;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }
}
