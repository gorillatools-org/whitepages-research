package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.EncodingException;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ValueEncoderContext;
import java.io.IOException;

class ProtobufValueEncoderContext implements ValueEncoderContext {
    private boolean encoded = false;
    private FieldDescriptor field;
    private final ProtobufDataEncoderContext objEncoderCtx;
    private boolean skipDefault = false;

    ProtobufValueEncoderContext(ProtobufDataEncoderContext protobufDataEncoderContext) {
        this.objEncoderCtx = protobufDataEncoderContext;
    }

    /* access modifiers changed from: package-private */
    public void resetContext(FieldDescriptor fieldDescriptor, boolean z) {
        this.encoded = false;
        this.field = fieldDescriptor;
        this.skipDefault = z;
    }

    private void checkNotUsed() {
        if (!this.encoded) {
            this.encoded = true;
            return;
        }
        throw new EncodingException("Cannot encode a second value in the ValueEncoderContext");
    }

    public ValueEncoderContext add(String str) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, (Object) str, this.skipDefault);
        return this;
    }

    public ValueEncoderContext add(float f) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, f, this.skipDefault);
        return this;
    }

    public ValueEncoderContext add(double d) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, d, this.skipDefault);
        return this;
    }

    public ValueEncoderContext add(int i) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, i, this.skipDefault);
        return this;
    }

    public ValueEncoderContext add(long j) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, j, this.skipDefault);
        return this;
    }

    public ValueEncoderContext add(boolean z) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, z, this.skipDefault);
        return this;
    }

    public ValueEncoderContext add(byte[] bArr) throws IOException {
        checkNotUsed();
        this.objEncoderCtx.add(this.field, (Object) bArr, this.skipDefault);
        return this;
    }
}
