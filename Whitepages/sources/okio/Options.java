package okio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Options extends AbstractList implements RandomAccess {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    public static final Options of(ByteString... byteStringArr) {
        return Companion.of(byteStringArr);
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (!(obj instanceof ByteString)) {
            return false;
        }
        return contains((ByteString) obj);
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains(byteString);
    }

    public final /* bridge */ int indexOf(Object obj) {
        if (!(obj instanceof ByteString)) {
            return -1;
        }
        return indexOf((ByteString) obj);
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf(byteString);
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        if (!(obj instanceof ByteString)) {
            return -1;
        }
        return lastIndexOf((ByteString) obj);
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf(byteString);
    }

    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    public final int[] getTrie$okio() {
        return this.trie;
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    public int getSize() {
        return this.byteStrings.length;
    }

    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Options of(ByteString... byteStringArr) {
            ByteString[] byteStringArr2 = byteStringArr;
            Intrinsics.checkNotNullParameter(byteStringArr2, "byteStrings");
            int i = 0;
            if (byteStringArr2.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, (DefaultConstructorMarker) null);
            }
            List mutableList = ArraysKt.toMutableList(byteStringArr);
            CollectionsKt.sort(mutableList);
            ArrayList arrayList = new ArrayList(byteStringArr2.length);
            for (ByteString byteString : byteStringArr2) {
                arrayList.add(-1);
            }
            Integer[] numArr = (Integer[]) arrayList.toArray(new Integer[0]);
            List mutableListOf = CollectionsKt.mutableListOf(Arrays.copyOf(numArr, numArr.length));
            int length = byteStringArr2.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                mutableListOf.set(CollectionsKt.binarySearch$default(mutableList, byteStringArr2[i2], 0, 0, 6, (Object) null), Integer.valueOf(i3));
                i2++;
                i3++;
            }
            if (((ByteString) mutableList.get(0)).size() > 0) {
                int i4 = 0;
                while (i4 < mutableList.size()) {
                    ByteString byteString2 = (ByteString) mutableList.get(i4);
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (i6 < mutableList.size()) {
                        ByteString byteString3 = (ByteString) mutableList.get(i6);
                        if (!byteString3.startsWith(byteString2)) {
                            continue;
                            break;
                        } else if (byteString3.size() == byteString2.size()) {
                            throw new IllegalArgumentException(("duplicate option: " + byteString3).toString());
                        } else if (((Number) mutableListOf.get(i6)).intValue() > ((Number) mutableListOf.get(i4)).intValue()) {
                            mutableList.remove(i6);
                            mutableListOf.remove(i6);
                        } else {
                            i6++;
                        }
                    }
                    i4 = i5;
                }
                Buffer buffer = new Buffer();
                buildTrieRecursive$default(this, 0, buffer, 0, mutableList, 0, 0, mutableListOf, 53, (Object) null);
                int[] iArr = new int[((int) getIntCount(buffer))];
                while (!buffer.exhausted()) {
                    iArr[i] = buffer.readInt();
                    i++;
                }
                Object[] copyOf = Arrays.copyOf(byteStringArr2, byteStringArr2.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
                return new Options((ByteString[]) copyOf, iArr, (DefaultConstructorMarker) null);
            }
            throw new IllegalArgumentException("the empty byte string is not a supported option");
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) {
            int i5;
            long j2 = (i4 & 1) != 0 ? 0 : j;
            int i6 = (i4 & 4) != 0 ? 0 : i;
            int i7 = (i4 & 16) != 0 ? 0 : i2;
            if ((i4 & 32) != 0) {
                i5 = list.size();
            } else {
                i5 = i3;
            }
            companion.buildTrieRecursive(j2, buffer, i6, list, i7, i5, list2);
        }

        private final void buildTrieRecursive(long j, Buffer buffer, int i, List list, int i2, int i3, List list2) {
            int i4;
            int i5;
            int i6;
            int i7;
            Buffer buffer2;
            Buffer buffer3 = buffer;
            int i8 = i;
            List list3 = list;
            int i9 = i2;
            int i10 = i3;
            List list4 = list2;
            if (i9 < i10) {
                int i11 = i9;
                while (i11 < i10) {
                    if (((ByteString) list3.get(i11)).size() >= i8) {
                        i11++;
                    } else {
                        throw new IllegalArgumentException("Failed requirement.");
                    }
                }
                ByteString byteString = (ByteString) list.get(i2);
                ByteString byteString2 = (ByteString) list3.get(i10 - 1);
                int i12 = -1;
                if (i8 == byteString.size()) {
                    int intValue = ((Number) list4.get(i9)).intValue();
                    int i13 = i9 + 1;
                    i4 = i13;
                    i5 = intValue;
                    byteString = (ByteString) list3.get(i13);
                } else {
                    i4 = i9;
                    i5 = -1;
                }
                if (byteString.getByte(i8) != byteString2.getByte(i8)) {
                    int i14 = 1;
                    for (int i15 = i4 + 1; i15 < i10; i15++) {
                        if (((ByteString) list3.get(i15 - 1)).getByte(i8) != ((ByteString) list3.get(i15)).getByte(i8)) {
                            i14++;
                        }
                    }
                    long intCount = j + getIntCount(buffer3) + ((long) 2) + ((long) (i14 * 2));
                    buffer3.writeInt(i14);
                    buffer3.writeInt(i5);
                    for (int i16 = i4; i16 < i10; i16++) {
                        byte b = ((ByteString) list3.get(i16)).getByte(i8);
                        if (i16 == i4 || b != ((ByteString) list3.get(i16 - 1)).getByte(i8)) {
                            buffer3.writeInt((int) b & 255);
                        }
                    }
                    Buffer buffer4 = new Buffer();
                    while (i4 < i10) {
                        byte b2 = ((ByteString) list3.get(i4)).getByte(i8);
                        int i17 = i4 + 1;
                        int i18 = i17;
                        while (true) {
                            if (i18 >= i10) {
                                i6 = i10;
                                break;
                            } else if (b2 != ((ByteString) list3.get(i18)).getByte(i8)) {
                                i6 = i18;
                                break;
                            } else {
                                i18++;
                            }
                        }
                        if (i17 == i6 && i8 + 1 == ((ByteString) list3.get(i4)).size()) {
                            buffer3.writeInt(((Number) list4.get(i4)).intValue());
                            i7 = i6;
                            buffer2 = buffer4;
                        } else {
                            buffer3.writeInt(((int) (intCount + getIntCount(buffer4))) * i12);
                            i7 = i6;
                            buffer2 = buffer4;
                            buildTrieRecursive(intCount, buffer4, i8 + 1, list, i4, i6, list2);
                        }
                        buffer4 = buffer2;
                        i4 = i7;
                        i12 = -1;
                    }
                    buffer3.writeAll(buffer4);
                    return;
                }
                int min = Math.min(byteString.size(), byteString2.size());
                int i19 = 0;
                int i20 = i8;
                while (i20 < min && byteString.getByte(i20) == byteString2.getByte(i20)) {
                    i19++;
                    i20++;
                }
                long intCount2 = j + getIntCount(buffer3) + ((long) 2) + ((long) i19) + 1;
                buffer3.writeInt(-i19);
                buffer3.writeInt(i5);
                int i21 = i19 + i8;
                while (i8 < i21) {
                    buffer3.writeInt((int) byteString.getByte(i8) & 255);
                    i8++;
                }
                if (i4 + 1 != i10) {
                    Buffer buffer5 = new Buffer();
                    buffer3.writeInt(((int) (getIntCount(buffer5) + intCount2)) * -1);
                    buildTrieRecursive(intCount2, buffer5, i21, list, i4, i3, list2);
                    buffer3.writeAll(buffer5);
                } else if (i21 == ((ByteString) list3.get(i4)).size()) {
                    buffer3.writeInt(((Number) list4.get(i4)).intValue());
                } else {
                    throw new IllegalStateException("Check failed.");
                }
            } else {
                throw new IllegalArgumentException("Failed requirement.");
            }
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / ((long) 4);
        }
    }
}
