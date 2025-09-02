package okio.internal;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$LongRef;
import okio.BufferedSource;

final class ZipFilesKt$readEntry$1 extends Lambda implements Function2 {
    final /* synthetic */ Ref$LongRef $compressedSize;
    final /* synthetic */ Ref$BooleanRef $hasZip64Extra;
    final /* synthetic */ Ref$LongRef $offset;
    final /* synthetic */ long $requiredZip64ExtraSize;
    final /* synthetic */ Ref$LongRef $size;
    final /* synthetic */ BufferedSource $this_readEntry;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ZipFilesKt$readEntry$1(Ref$BooleanRef ref$BooleanRef, long j, Ref$LongRef ref$LongRef, BufferedSource bufferedSource, Ref$LongRef ref$LongRef2, Ref$LongRef ref$LongRef3) {
        super(2);
        this.$hasZip64Extra = ref$BooleanRef;
        this.$requiredZip64ExtraSize = j;
        this.$size = ref$LongRef;
        this.$this_readEntry = bufferedSource;
        this.$compressedSize = ref$LongRef2;
        this.$offset = ref$LongRef3;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, long j) {
        if (i == 1) {
            Ref$BooleanRef ref$BooleanRef = this.$hasZip64Extra;
            if (!ref$BooleanRef.element) {
                ref$BooleanRef.element = true;
                if (j >= this.$requiredZip64ExtraSize) {
                    Ref$LongRef ref$LongRef = this.$size;
                    long j2 = ref$LongRef.element;
                    if (j2 == 4294967295L) {
                        j2 = this.$this_readEntry.readLongLe();
                    }
                    ref$LongRef.element = j2;
                    Ref$LongRef ref$LongRef2 = this.$compressedSize;
                    long j3 = 0;
                    ref$LongRef2.element = ref$LongRef2.element == 4294967295L ? this.$this_readEntry.readLongLe() : 0;
                    Ref$LongRef ref$LongRef3 = this.$offset;
                    if (ref$LongRef3.element == 4294967295L) {
                        j3 = this.$this_readEntry.readLongLe();
                    }
                    ref$LongRef3.element = j3;
                    return;
                }
                throw new IOException("bad zip: zip64 extra too short");
            }
            throw new IOException("bad zip: zip64 extra repeated");
        }
    }
}
