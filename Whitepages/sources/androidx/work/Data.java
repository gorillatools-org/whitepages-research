package androidx.work;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Data {
    public static final Data EMPTY = new Builder().build();
    private static final String TAG = Logger.tagWithPrefix("Data");
    Map mValues;

    public Data(Data data) {
        this.mValues = new HashMap(data.mValues);
    }

    public Data(Map map) {
        this.mValues = new HashMap(map);
    }

    public boolean getBoolean(String str, boolean z) {
        Object obj = this.mValues.get(str);
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
    }

    public byte[] getByteArray(String str) {
        Object obj = this.mValues.get(str);
        if (obj instanceof Byte[]) {
            return convertToPrimitiveArray((Byte[]) obj);
        }
        return null;
    }

    public int getInt(String str, int i) {
        Object obj = this.mValues.get(str);
        return obj instanceof Integer ? ((Integer) obj).intValue() : i;
    }

    public String getString(String str) {
        Object obj = this.mValues.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public Map getKeyValueMap() {
        return Collections.unmodifiableMap(this.mValues);
    }

    public int size() {
        return this.mValues.size();
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0078 A[SYNTHETIC, Splitter:B:31:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x008f A[SYNTHETIC, Splitter:B:41:0x008f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArrayInternal(androidx.work.Data r5) {
        /*
            java.lang.String r0 = "Error in Data#toByteArray: "
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            r2 = 0
            java.io.ObjectOutputStream r3 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x006c }
            r3.<init>(r1)     // Catch:{ IOException -> 0x006c }
            int r2 = r5.size()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r3.writeInt(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.util.Map r5 = r5.mValues     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.util.Set r5 = r5.entrySet()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
        L_0x001e:
            boolean r2 = r5.hasNext()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            if (r2 == 0) goto L_0x0041
            java.lang.Object r2 = r5.next()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.Object r4 = r2.getKey()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r3.writeUTF(r4)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.Object r2 = r2.getValue()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r3.writeObject(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            goto L_0x001e
        L_0x003b:
            r5 = move-exception
            r2 = r3
            goto L_0x008d
        L_0x003e:
            r5 = move-exception
            r2 = r3
            goto L_0x006d
        L_0x0041:
            r3.close()     // Catch:{ IOException -> 0x0045 }
            goto L_0x004b
        L_0x0045:
            r5 = move-exception
            java.lang.String r2 = TAG
            android.util.Log.e(r2, r0, r5)
        L_0x004b:
            r1.close()     // Catch:{ IOException -> 0x004f }
            goto L_0x0055
        L_0x004f:
            r5 = move-exception
            java.lang.String r2 = TAG
            android.util.Log.e(r2, r0, r5)
        L_0x0055:
            int r5 = r1.size()
            r0 = 10240(0x2800, float:1.4349E-41)
            if (r5 > r0) goto L_0x0062
            byte[] r5 = r1.toByteArray()
            return r5
        L_0x0062:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240 bytes when serialized"
            r5.<init>(r0)
            throw r5
        L_0x006a:
            r5 = move-exception
            goto L_0x008d
        L_0x006c:
            r5 = move-exception
        L_0x006d:
            java.lang.String r3 = TAG     // Catch:{ all -> 0x006a }
            android.util.Log.e(r3, r0, r5)     // Catch:{ all -> 0x006a }
            byte[] r5 = r1.toByteArray()     // Catch:{ all -> 0x006a }
            if (r2 == 0) goto L_0x0082
            r2.close()     // Catch:{ IOException -> 0x007c }
            goto L_0x0082
        L_0x007c:
            r2 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r0, r2)
        L_0x0082:
            r1.close()     // Catch:{ IOException -> 0x0086 }
            goto L_0x008c
        L_0x0086:
            r1 = move-exception
            java.lang.String r2 = TAG
            android.util.Log.e(r2, r0, r1)
        L_0x008c:
            return r5
        L_0x008d:
            if (r2 == 0) goto L_0x0099
            r2.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x0099
        L_0x0093:
            r2 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r0, r2)
        L_0x0099:
            r1.close()     // Catch:{ IOException -> 0x009d }
            goto L_0x00a3
        L_0x009d:
            r1 = move-exception
            java.lang.String r2 = TAG
            android.util.Log.e(r2, r0, r1)
        L_0x00a3:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.toByteArrayInternal(androidx.work.Data):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0058 A[SYNTHETIC, Splitter:B:27:0x0058] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x006d A[SYNTHETIC, Splitter:B:36:0x006d] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0062=Splitter:B:31:0x0062, B:16:0x003b=Splitter:B:16:0x003b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.work.Data fromByteArray(byte[] r7) {
        /*
            java.lang.String r0 = "Error in Data#fromByteArray: "
            int r1 = r7.length
            r2 = 10240(0x2800, float:1.4349E-41)
            if (r1 > r2) goto L_0x0082
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r7)
            r7 = 0
            java.io.ObjectInputStream r3 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x004f, ClassNotFoundException -> 0x004a, all -> 0x0046 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x004f, ClassNotFoundException -> 0x004a, all -> 0x0046 }
            int r7 = r3.readInt()     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x002d }
        L_0x001b:
            if (r7 <= 0) goto L_0x0031
            java.lang.String r4 = r3.readUTF()     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x002d }
            java.lang.Object r5 = r3.readObject()     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x002d }
            r1.put(r4, r5)     // Catch:{ IOException -> 0x002f, ClassNotFoundException -> 0x002d }
            int r7 = r7 + -1
            goto L_0x001b
        L_0x002b:
            r7 = move-exception
            goto L_0x006b
        L_0x002d:
            r7 = move-exception
            goto L_0x0051
        L_0x002f:
            r7 = move-exception
            goto L_0x0051
        L_0x0031:
            r3.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x003b
        L_0x0035:
            r7 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r0, r7)
        L_0x003b:
            r2.close()     // Catch:{ IOException -> 0x003f }
            goto L_0x0065
        L_0x003f:
            r7 = move-exception
            java.lang.String r2 = TAG
            android.util.Log.e(r2, r0, r7)
            goto L_0x0065
        L_0x0046:
            r1 = move-exception
            r3 = r7
            r7 = r1
            goto L_0x006b
        L_0x004a:
            r3 = move-exception
        L_0x004b:
            r6 = r3
            r3 = r7
            r7 = r6
            goto L_0x0051
        L_0x004f:
            r3 = move-exception
            goto L_0x004b
        L_0x0051:
            java.lang.String r4 = TAG     // Catch:{ all -> 0x002b }
            android.util.Log.e(r4, r0, r7)     // Catch:{ all -> 0x002b }
            if (r3 == 0) goto L_0x0062
            r3.close()     // Catch:{ IOException -> 0x005c }
            goto L_0x0062
        L_0x005c:
            r7 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r0, r7)
        L_0x0062:
            r2.close()     // Catch:{ IOException -> 0x003f }
        L_0x0065:
            androidx.work.Data r7 = new androidx.work.Data
            r7.<init>((java.util.Map) r1)
            return r7
        L_0x006b:
            if (r3 == 0) goto L_0x0077
            r3.close()     // Catch:{ IOException -> 0x0071 }
            goto L_0x0077
        L_0x0071:
            r1 = move-exception
            java.lang.String r3 = TAG
            android.util.Log.e(r3, r0, r1)
        L_0x0077:
            r2.close()     // Catch:{ IOException -> 0x007b }
            goto L_0x0081
        L_0x007b:
            r1 = move-exception
            java.lang.String r2 = TAG
            android.util.Log.e(r2, r0, r1)
        L_0x0081:
            throw r7
        L_0x0082:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "Data cannot occupy more than 10240 bytes when serialized"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.Data.fromByteArray(byte[]):androidx.work.Data");
    }

    public boolean equals(Object obj) {
        boolean z;
        if (this == obj) {
            return true;
        }
        if (obj == null || Data.class != obj.getClass()) {
            return false;
        }
        Data data = (Data) obj;
        Set<String> keySet = this.mValues.keySet();
        if (!keySet.equals(data.mValues.keySet())) {
            return false;
        }
        for (String str : keySet) {
            Object obj2 = this.mValues.get(str);
            Object obj3 = data.mValues.get(str);
            if (obj2 == null || obj3 == null) {
                if (obj2 == obj3) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
            } else if (!(obj2 instanceof Object[]) || !(obj3 instanceof Object[])) {
                z = obj2.equals(obj3);
                continue;
            } else {
                z = Arrays.deepEquals((Object[]) obj2, (Object[]) obj3);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.mValues.hashCode() * 31;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Data {");
        if (!this.mValues.isEmpty()) {
            for (String str : this.mValues.keySet()) {
                sb.append(str);
                sb.append(" : ");
                Object obj = this.mValues.get(str);
                if (obj instanceof Object[]) {
                    sb.append(Arrays.toString((Object[]) obj));
                } else {
                    sb.append(obj);
                }
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public static Boolean[] convertPrimitiveBooleanArray(boolean[] zArr) {
        Boolean[] boolArr = new Boolean[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            boolArr[i] = Boolean.valueOf(zArr[i]);
        }
        return boolArr;
    }

    public static Byte[] convertPrimitiveByteArray(byte[] bArr) {
        Byte[] bArr2 = new Byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = Byte.valueOf(bArr[i]);
        }
        return bArr2;
    }

    public static Integer[] convertPrimitiveIntArray(int[] iArr) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        return numArr;
    }

    public static Long[] convertPrimitiveLongArray(long[] jArr) {
        Long[] lArr = new Long[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            lArr[i] = Long.valueOf(jArr[i]);
        }
        return lArr;
    }

    public static Float[] convertPrimitiveFloatArray(float[] fArr) {
        Float[] fArr2 = new Float[fArr.length];
        for (int i = 0; i < fArr.length; i++) {
            fArr2[i] = Float.valueOf(fArr[i]);
        }
        return fArr2;
    }

    public static Double[] convertPrimitiveDoubleArray(double[] dArr) {
        Double[] dArr2 = new Double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            dArr2[i] = Double.valueOf(dArr[i]);
        }
        return dArr2;
    }

    public static byte[] convertToPrimitiveArray(Byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = bArr[i].byteValue();
        }
        return bArr2;
    }

    public static final class Builder {
        private Map mValues = new HashMap();

        public Builder putBoolean(String str, boolean z) {
            this.mValues.put(str, Boolean.valueOf(z));
            return this;
        }

        public Builder putInt(String str, int i) {
            this.mValues.put(str, Integer.valueOf(i));
            return this;
        }

        public Builder putString(String str, String str2) {
            this.mValues.put(str, str2);
            return this;
        }

        public Builder putAll(Map map) {
            for (Map.Entry entry : map.entrySet()) {
                put((String) entry.getKey(), entry.getValue());
            }
            return this;
        }

        public Builder put(String str, Object obj) {
            if (obj == null) {
                this.mValues.put(str, (Object) null);
            } else {
                Class<?> cls = obj.getClass();
                if (cls == Boolean.class || cls == Byte.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == String.class || cls == Boolean[].class || cls == Byte[].class || cls == Integer[].class || cls == Long[].class || cls == Float[].class || cls == Double[].class || cls == String[].class) {
                    this.mValues.put(str, obj);
                } else if (cls == boolean[].class) {
                    this.mValues.put(str, Data.convertPrimitiveBooleanArray((boolean[]) obj));
                } else if (cls == byte[].class) {
                    this.mValues.put(str, Data.convertPrimitiveByteArray((byte[]) obj));
                } else if (cls == int[].class) {
                    this.mValues.put(str, Data.convertPrimitiveIntArray((int[]) obj));
                } else if (cls == long[].class) {
                    this.mValues.put(str, Data.convertPrimitiveLongArray((long[]) obj));
                } else if (cls == float[].class) {
                    this.mValues.put(str, Data.convertPrimitiveFloatArray((float[]) obj));
                } else if (cls == double[].class) {
                    this.mValues.put(str, Data.convertPrimitiveDoubleArray((double[]) obj));
                } else {
                    throw new IllegalArgumentException("Key " + str + "has invalid type " + cls);
                }
            }
            return this;
        }

        public Data build() {
            Data data = new Data(this.mValues);
            Data.toByteArrayInternal(data);
            return data;
        }
    }
}
