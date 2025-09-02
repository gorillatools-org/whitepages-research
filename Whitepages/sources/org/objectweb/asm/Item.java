package org.objectweb.asm;

final class Item {
    int a;
    int b;
    int c;
    long d;
    String g;
    String h;
    String i;
    int j;
    Item k;

    Item() {
    }

    Item(int i2) {
        this.a = i2;
    }

    Item(int i2, Item item) {
        this.a = i2;
        this.b = item.b;
        this.c = item.c;
        this.d = item.d;
        this.g = item.g;
        this.h = item.h;
        this.i = item.i;
        this.j = item.j;
    }

    /* access modifiers changed from: package-private */
    public void a(double d2) {
        this.b = 6;
        this.d = Double.doubleToRawLongBits(d2);
        this.j = Integer.MAX_VALUE & (this.b + ((int) d2));
    }

    /* access modifiers changed from: package-private */
    public void a(float f) {
        this.b = 4;
        this.c = Float.floatToRawIntBits(f);
        this.j = Integer.MAX_VALUE & (this.b + ((int) f));
    }

    /* access modifiers changed from: package-private */
    public void a(int i2) {
        this.b = 3;
        this.c = i2;
        this.j = (3 + i2) & Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, int i3) {
        this.b = 33;
        this.c = i2;
        this.j = i3;
    }

    /* access modifiers changed from: package-private */
    public void a(int i2, String str, String str2, String str3) {
        int hashCode;
        int hashCode2;
        int hashCode3;
        this.b = i2;
        this.g = str;
        this.h = str2;
        this.i = str3;
        if (i2 != 1) {
            if (i2 == 12) {
                hashCode2 = str.hashCode();
                hashCode3 = str2.hashCode();
            } else if (!(i2 == 16 || i2 == 30)) {
                if (i2 == 7) {
                    this.c = 0;
                } else if (i2 != 8) {
                    hashCode2 = str.hashCode() * str2.hashCode();
                    hashCode3 = str3.hashCode();
                }
            }
            hashCode = hashCode2 * hashCode3;
            this.j = (i2 + hashCode) & Integer.MAX_VALUE;
        }
        hashCode = str.hashCode();
        this.j = (i2 + hashCode) & Integer.MAX_VALUE;
    }

    /* access modifiers changed from: package-private */
    public void a(long j2) {
        this.b = 5;
        this.d = j2;
        this.j = Integer.MAX_VALUE & (5 + ((int) j2));
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2, int i2) {
        this.b = 18;
        this.d = (long) i2;
        this.g = str;
        this.h = str2;
        this.j = Integer.MAX_VALUE & ((i2 * str.hashCode() * this.h.hashCode()) + 18);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0052, code lost:
        if (r9.d != r8.d) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(org.objectweb.asm.Item r9) {
        /*
            r8 = this;
            int r0 = r8.b
            r1 = 1
            if (r0 == r1) goto L_0x0096
            r2 = 12
            r3 = 0
            if (r0 == r2) goto L_0x007f
            r2 = 16
            if (r0 == r2) goto L_0x0096
            r2 = 18
            if (r0 == r2) goto L_0x0060
            switch(r0) {
                case 3: goto L_0x0057;
                case 4: goto L_0x0057;
                case 5: goto L_0x004c;
                case 6: goto L_0x004c;
                case 7: goto L_0x0096;
                case 8: goto L_0x0096;
                default: goto L_0x0015;
            }
        L_0x0015:
            switch(r0) {
                case 30: goto L_0x0096;
                case 31: goto L_0x0039;
                case 32: goto L_0x004c;
                default: goto L_0x0018;
            }
        L_0x0018:
            java.lang.String r0 = r9.g
            java.lang.String r2 = r8.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0037
            java.lang.String r0 = r9.h
            java.lang.String r2 = r8.h
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0037
            java.lang.String r9 = r9.i
            java.lang.String r0 = r8.i
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0037
            goto L_0x0038
        L_0x0037:
            r1 = r3
        L_0x0038:
            return r1
        L_0x0039:
            int r0 = r9.c
            int r2 = r8.c
            if (r0 != r2) goto L_0x004a
            java.lang.String r9 = r9.g
            java.lang.String r0 = r8.g
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x004a
            goto L_0x004b
        L_0x004a:
            r1 = r3
        L_0x004b:
            return r1
        L_0x004c:
            long r4 = r9.d
            long r6 = r8.d
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x0055
            goto L_0x0056
        L_0x0055:
            r1 = r3
        L_0x0056:
            return r1
        L_0x0057:
            int r9 = r9.c
            int r0 = r8.c
            if (r9 != r0) goto L_0x005e
            goto L_0x005f
        L_0x005e:
            r1 = r3
        L_0x005f:
            return r1
        L_0x0060:
            long r4 = r9.d
            long r6 = r8.d
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto L_0x007d
            java.lang.String r0 = r9.g
            java.lang.String r2 = r8.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x007d
            java.lang.String r9 = r9.h
            java.lang.String r0 = r8.h
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x007d
            goto L_0x007e
        L_0x007d:
            r1 = r3
        L_0x007e:
            return r1
        L_0x007f:
            java.lang.String r0 = r9.g
            java.lang.String r2 = r8.g
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0094
            java.lang.String r9 = r9.h
            java.lang.String r0 = r8.h
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0094
            goto L_0x0095
        L_0x0094:
            r1 = r3
        L_0x0095:
            return r1
        L_0x0096:
            java.lang.String r9 = r9.g
            java.lang.String r0 = r8.g
            boolean r9 = r9.equals(r0)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objectweb.asm.Item.a(org.objectweb.asm.Item):boolean");
    }
}
