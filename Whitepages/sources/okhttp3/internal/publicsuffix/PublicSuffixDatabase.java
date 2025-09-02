package okhttp3.internal.publicsuffix;

import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;

public final class PublicSuffixDatabase {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final char EXCEPTION_MARKER = '!';
    private static final List<String> PREVAILING_RULE = CollectionsKt.listOf("*");
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private static final byte[] WILDCARD_LABEL = {(byte) 42};
    /* access modifiers changed from: private */
    public static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private byte[] publicSuffixExceptionListBytes;
    /* access modifiers changed from: private */
    public byte[] publicSuffixListBytes;
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public static final /* synthetic */ byte[] access$getPublicSuffixListBytes$p(PublicSuffixDatabase publicSuffixDatabase) {
        byte[] bArr = publicSuffixDatabase.publicSuffixListBytes;
        if (bArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("publicSuffixListBytes");
        }
        return bArr;
    }

    public final String getEffectiveTldPlusOne(String str) {
        int size;
        int size2;
        Intrinsics.checkNotNullParameter(str, "domain");
        String unicode = IDN.toUnicode(str);
        Intrinsics.checkNotNullExpressionValue(unicode, "unicodeDomain");
        List<String> splitDomain = splitDomain(unicode);
        List<String> findMatchingRule = findMatchingRule(splitDomain);
        if (splitDomain.size() == findMatchingRule.size() && findMatchingRule.get(0).charAt(0) != '!') {
            return null;
        }
        if (findMatchingRule.get(0).charAt(0) == '!') {
            size = splitDomain.size();
            size2 = findMatchingRule.size();
        } else {
            size = splitDomain.size();
            size2 = findMatchingRule.size() + 1;
        }
        return SequencesKt.joinToString$default(SequencesKt.drop(CollectionsKt.asSequence(splitDomain(str)), size - size2), ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final List<String> splitDomain(String str) {
        List<String> split$default = StringsKt.split$default((CharSequence) str, new char[]{'.'}, false, 0, 6, (Object) null);
        return Intrinsics.areEqual((Object) (String) CollectionsKt.last((List) split$default), (Object) "") ? CollectionsKt.dropLast(split$default, 1) : split$default;
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00d8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> findMatchingRule(java.util.List<java.lang.String> r18) {
        /*
            r17 = this;
            r0 = r17
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.listRead
            boolean r1 = r1.get()
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0018
            java.util.concurrent.atomic.AtomicBoolean r1 = r0.listRead
            boolean r1 = r1.compareAndSet(r2, r3)
            if (r1 == 0) goto L_0x0018
            r17.readTheListUninterruptibly()
            goto L_0x0025
        L_0x0018:
            java.util.concurrent.CountDownLatch r1 = r0.readCompleteLatch     // Catch:{ InterruptedException -> 0x001e }
            r1.await()     // Catch:{ InterruptedException -> 0x001e }
            goto L_0x0025
        L_0x001e:
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
        L_0x0025:
            byte[] r1 = r0.publicSuffixListBytes
            if (r1 == 0) goto L_0x002b
            r1 = r3
            goto L_0x002c
        L_0x002b:
            r1 = r2
        L_0x002c:
            if (r1 == 0) goto L_0x011a
            int r1 = r18.size()
            byte[][] r4 = new byte[r1][]
            r5 = r2
        L_0x0035:
            if (r5 >= r1) goto L_0x005d
            r6 = r18
            java.lang.Object r7 = r6.get(r5)
            java.lang.String r7 = (java.lang.String) r7
            java.nio.charset.Charset r8 = java.nio.charset.StandardCharsets.UTF_8
            java.lang.String r9 = "UTF_8"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r9)
            if (r7 == 0) goto L_0x0055
            byte[] r7 = r7.getBytes(r8)
            java.lang.String r8 = "(this as java.lang.String).getBytes(charset)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            r4[r5] = r7
            int r5 = r5 + r3
            goto L_0x0035
        L_0x0055:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.String"
            r1.<init>(r2)
            throw r1
        L_0x005d:
            r5 = r2
        L_0x005e:
            java.lang.String r6 = "publicSuffixListBytes"
            r7 = 0
            if (r5 >= r1) goto L_0x0075
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r8 = Companion
            byte[] r9 = r0.publicSuffixListBytes
            if (r9 != 0) goto L_0x006c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x006c:
            java.lang.String r8 = r8.binarySearch(r9, r4, r5)
            if (r8 == 0) goto L_0x0073
            goto L_0x0076
        L_0x0073:
            int r5 = r5 + r3
            goto L_0x005e
        L_0x0075:
            r8 = r7
        L_0x0076:
            if (r1 <= r3) goto L_0x009a
            java.lang.Object r5 = r4.clone()
            byte[][] r5 = (byte[][]) r5
            int r9 = r5.length
            int r9 = r9 - r3
            r10 = r2
        L_0x0081:
            if (r10 >= r9) goto L_0x009a
            byte[] r11 = WILDCARD_LABEL
            r5[r10] = r11
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r11 = Companion
            byte[] r12 = r0.publicSuffixListBytes
            if (r12 != 0) goto L_0x0090
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x0090:
            java.lang.String r11 = r11.binarySearch(r12, r5, r10)
            if (r11 == 0) goto L_0x0098
            r5 = r11
            goto L_0x009b
        L_0x0098:
            int r10 = r10 + r3
            goto L_0x0081
        L_0x009a:
            r5 = r7
        L_0x009b:
            if (r5 == 0) goto L_0x00b6
            int r1 = r1 - r3
            r6 = r2
        L_0x009f:
            if (r6 >= r1) goto L_0x00b6
            okhttp3.internal.publicsuffix.PublicSuffixDatabase$Companion r9 = Companion
            byte[] r10 = r0.publicSuffixExceptionListBytes
            if (r10 != 0) goto L_0x00ac
            java.lang.String r11 = "publicSuffixExceptionListBytes"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
        L_0x00ac:
            java.lang.String r9 = r9.binarySearch(r10, r4, r6)
            if (r9 == 0) goto L_0x00b4
            r7 = r9
            goto L_0x00b6
        L_0x00b4:
            int r6 = r6 + r3
            goto L_0x009f
        L_0x00b6:
            r1 = 46
            if (r7 == 0) goto L_0x00d8
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 33
            r4.append(r5)
            r4.append(r7)
            java.lang.String r8 = r4.toString()
            char[] r9 = new char[r3]
            r9[r2] = r1
            r12 = 6
            r13 = 0
            r10 = 0
            r11 = 0
            java.util.List r1 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r8, (char[]) r9, (boolean) r10, (int) r11, (int) r12, (java.lang.Object) r13)
            return r1
        L_0x00d8:
            if (r8 != 0) goto L_0x00df
            if (r5 != 0) goto L_0x00df
            java.util.List<java.lang.String> r1 = PREVAILING_RULE
            return r1
        L_0x00df:
            if (r8 == 0) goto L_0x00f2
            char[] r7 = new char[r3]
            r7[r2] = r1
            r10 = 6
            r11 = 0
            r4 = 0
            r9 = 0
            r6 = r8
            r8 = r4
            java.util.List r4 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r6, (char[]) r7, (boolean) r8, (int) r9, (int) r10, (java.lang.Object) r11)
            if (r4 == 0) goto L_0x00f2
            goto L_0x00f6
        L_0x00f2:
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
        L_0x00f6:
            if (r5 == 0) goto L_0x0109
            char[] r12 = new char[r3]
            r12[r2] = r1
            r15 = 6
            r16 = 0
            r13 = 0
            r14 = 0
            r11 = r5
            java.util.List r1 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r11, (char[]) r12, (boolean) r13, (int) r14, (int) r15, (java.lang.Object) r16)
            if (r1 == 0) goto L_0x0109
            goto L_0x010d
        L_0x0109:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x010d:
            int r2 = r4.size()
            int r3 = r1.size()
            if (r2 <= r3) goto L_0x0118
            goto L_0x0119
        L_0x0118:
            r4 = r1
        L_0x0119:
            return r4
        L_0x011a:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Unable to load publicsuffixes.gz resource from the classpath."
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.findMatchingRule(java.util.List):java.util.List");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0027 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readTheListUninterruptibly() {
        /*
            r5 = this;
            r0 = 0
        L_0x0001:
            r5.readTheList()     // Catch:{ InterruptedIOException -> 0x0027, IOException -> 0x0010 }
            if (r0 == 0) goto L_0x000d
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x000d:
            return
        L_0x000e:
            r1 = move-exception
            goto L_0x002c
        L_0x0010:
            r1 = move-exception
            okhttp3.internal.platform.Platform$Companion r2 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x000e }
            okhttp3.internal.platform.Platform r2 = r2.get()     // Catch:{ all -> 0x000e }
            java.lang.String r3 = "Failed to read public suffix list"
            r4 = 5
            r2.log(r3, r4, r1)     // Catch:{ all -> 0x000e }
            if (r0 == 0) goto L_0x0026
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x0026:
            return
        L_0x0027:
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x000e }
            r0 = 1
            goto L_0x0001
        L_0x002c:
            if (r0 == 0) goto L_0x0035
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L_0x0035:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheListUninterruptibly():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0047, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004a, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readTheList() throws java.io.IOException {
        /*
            r4 = this;
            java.lang.Class<okhttp3.internal.publicsuffix.PublicSuffixDatabase> r0 = okhttp3.internal.publicsuffix.PublicSuffixDatabase.class
            java.lang.String r1 = "publicsuffixes.gz"
            java.io.InputStream r0 = r0.getResourceAsStream(r1)
            if (r0 == 0) goto L_0x004b
            okio.GzipSource r1 = new okio.GzipSource
            okio.Source r0 = okio.Okio.source((java.io.InputStream) r0)
            r1.<init>(r0)
            okio.BufferedSource r0 = okio.Okio.buffer((okio.Source) r1)
            int r1 = r0.readInt()     // Catch:{ all -> 0x0044 }
            long r1 = (long) r1     // Catch:{ all -> 0x0044 }
            byte[] r1 = r0.readByteArray(r1)     // Catch:{ all -> 0x0044 }
            int r2 = r0.readInt()     // Catch:{ all -> 0x0044 }
            long r2 = (long) r2     // Catch:{ all -> 0x0044 }
            byte[] r2 = r0.readByteArray(r2)     // Catch:{ all -> 0x0044 }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0044 }
            r3 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r3)
            monitor-enter(r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x0041 }
            r4.publicSuffixListBytes = r1     // Catch:{ all -> 0x0041 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0041 }
            r4.publicSuffixExceptionListBytes = r2     // Catch:{ all -> 0x0041 }
            monitor-exit(r4)
            java.util.concurrent.CountDownLatch r0 = r4.readCompleteLatch
            r0.countDown()
            return
        L_0x0041:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0044:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.readTheList():void");
    }

    public final void setListBytes(byte[] bArr, byte[] bArr2) {
        Intrinsics.checkNotNullParameter(bArr, "publicSuffixListBytes");
        Intrinsics.checkNotNullParameter(bArr2, "publicSuffixExceptionListBytes");
        this.publicSuffixListBytes = bArr;
        this.publicSuffixExceptionListBytes = bArr2;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final PublicSuffixDatabase get() {
            return PublicSuffixDatabase.instance;
        }

        /* access modifiers changed from: private */
        public final String binarySearch(byte[] bArr, byte[][] bArr2, int i) {
            int i2;
            int i3;
            boolean z;
            int and;
            byte[] bArr3 = bArr;
            byte[][] bArr4 = bArr2;
            int length = bArr3.length;
            int i4 = 0;
            while (i4 < length) {
                int i5 = (i4 + length) / 2;
                while (i5 > -1 && bArr3[i5] != ((byte) 10)) {
                    i5--;
                }
                int i6 = i5 + 1;
                int i7 = 1;
                while (true) {
                    i2 = i6 + i7;
                    if (bArr3[i2] == ((byte) 10)) {
                        break;
                    }
                    i7++;
                }
                int i8 = i2 - i6;
                int i9 = i;
                boolean z2 = false;
                int i10 = 0;
                int i11 = 0;
                while (true) {
                    if (z2) {
                        i3 = 46;
                        z = false;
                    } else {
                        boolean z3 = z2;
                        i3 = Util.and(bArr4[i9][i10], 255);
                        z = z3;
                    }
                    and = i3 - Util.and(bArr3[i6 + i11], 255);
                    if (and == 0) {
                        i11++;
                        i10++;
                        if (i11 == i8) {
                            break;
                        } else if (bArr4[i9].length != i10) {
                            z2 = z;
                        } else if (i9 == bArr4.length - 1) {
                            break;
                        } else {
                            i9++;
                            z2 = true;
                            i10 = -1;
                        }
                    } else {
                        break;
                    }
                }
                if (and >= 0) {
                    if (and <= 0) {
                        int i12 = i8 - i11;
                        int length2 = bArr4[i9].length - i10;
                        int length3 = bArr4.length;
                        for (int i13 = i9 + 1; i13 < length3; i13++) {
                            length2 += bArr4[i13].length;
                        }
                        if (length2 >= i12) {
                            if (length2 <= i12) {
                                Charset charset = StandardCharsets.UTF_8;
                                Intrinsics.checkNotNullExpressionValue(charset, "UTF_8");
                                return new String(bArr3, i6, i8, charset);
                            }
                        }
                    }
                    i4 = i2 + 1;
                }
                length = i5;
            }
            return null;
        }
    }
}
