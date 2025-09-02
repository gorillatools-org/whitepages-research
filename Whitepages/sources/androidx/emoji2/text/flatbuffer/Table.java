package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

public abstract class Table {
    protected ByteBuffer bb;
    protected int bb_pos;
    Utf8 utf8 = Utf8.getDefault();
    private int vtable_size;
    private int vtable_start;

    /* access modifiers changed from: protected */
    public int __offset(int i) {
        if (i < this.vtable_size) {
            return this.bb.getShort(this.vtable_start + i);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int __indirect(int i) {
        return i + this.bb.getInt(i);
    }

    /* access modifiers changed from: protected */
    public int __vector_len(int i) {
        int i2 = i + this.bb_pos;
        return this.bb.getInt(i2 + this.bb.getInt(i2));
    }

    /* access modifiers changed from: protected */
    public int __vector(int i) {
        int i2 = i + this.bb_pos;
        return i2 + this.bb.getInt(i2) + 4;
    }

    /* access modifiers changed from: protected */
    public void __reset(int i, ByteBuffer byteBuffer) {
        this.bb = byteBuffer;
        if (byteBuffer != null) {
            this.bb_pos = i;
            int i2 = i - byteBuffer.getInt(i);
            this.vtable_start = i2;
            this.vtable_size = this.bb.getShort(i2);
            return;
        }
        this.bb_pos = 0;
        this.vtable_start = 0;
        this.vtable_size = 0;
    }
}
