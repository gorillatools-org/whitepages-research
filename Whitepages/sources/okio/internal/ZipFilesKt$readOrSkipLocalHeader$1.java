package okio.internal;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import okio.BufferedSource;

final class ZipFilesKt$readOrSkipLocalHeader$1 extends Lambda implements Function2 {
    final /* synthetic */ Ref$ObjectRef $createdAtMillis;
    final /* synthetic */ Ref$ObjectRef $lastAccessedAtMillis;
    final /* synthetic */ Ref$ObjectRef $lastModifiedAtMillis;
    final /* synthetic */ BufferedSource $this_readOrSkipLocalHeader;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ZipFilesKt$readOrSkipLocalHeader$1(BufferedSource bufferedSource, Ref$ObjectRef ref$ObjectRef, Ref$ObjectRef ref$ObjectRef2, Ref$ObjectRef ref$ObjectRef3) {
        super(2);
        this.$this_readOrSkipLocalHeader = bufferedSource;
        this.$lastModifiedAtMillis = ref$ObjectRef;
        this.$lastAccessedAtMillis = ref$ObjectRef2;
        this.$createdAtMillis = ref$ObjectRef3;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i, long j) {
        if (i == 21589) {
            long j2 = 1;
            if (j >= 1) {
                byte readByte = this.$this_readOrSkipLocalHeader.readByte();
                boolean z = false;
                boolean z2 = (readByte & 1) == 1;
                boolean z3 = (readByte & 2) == 2;
                if ((readByte & 4) == 4) {
                    z = true;
                }
                BufferedSource bufferedSource = this.$this_readOrSkipLocalHeader;
                if (z2) {
                    j2 = 5;
                }
                if (z3) {
                    j2 += 4;
                }
                if (z) {
                    j2 += 4;
                }
                if (j >= j2) {
                    if (z2) {
                        this.$lastModifiedAtMillis.element = Long.valueOf(((long) bufferedSource.readIntLe()) * 1000);
                    }
                    if (z3) {
                        this.$lastAccessedAtMillis.element = Long.valueOf(((long) this.$this_readOrSkipLocalHeader.readIntLe()) * 1000);
                    }
                    if (z) {
                        this.$createdAtMillis.element = Long.valueOf(((long) this.$this_readOrSkipLocalHeader.readIntLe()) * 1000);
                        return;
                    }
                    return;
                }
                throw new IOException("bad zip: extended timestamp extra too short");
            }
            throw new IOException("bad zip: extended timestamp extra too short");
        }
    }
}
