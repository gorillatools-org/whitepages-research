package com.google.firebase.encoders.proto;

import com.google.firebase.encoders.proto.Protobuf;
import java.lang.annotation.Annotation;

public final class AtProtobuf {
    private Protobuf.IntEncoding intEncoding = Protobuf.IntEncoding.DEFAULT;
    private int tag;

    public AtProtobuf tag(int i) {
        this.tag = i;
        return this;
    }

    public AtProtobuf intEncoding(Protobuf.IntEncoding intEncoding2) {
        this.intEncoding = intEncoding2;
        return this;
    }

    public static AtProtobuf builder() {
        return new AtProtobuf();
    }

    public Protobuf build() {
        return new ProtobufImpl(this.tag, this.intEncoding);
    }

    private static final class ProtobufImpl implements Protobuf {
        private final Protobuf.IntEncoding intEncoding;
        private final int tag;

        ProtobufImpl(int i, Protobuf.IntEncoding intEncoding2) {
            this.tag = i;
            this.intEncoding = intEncoding2;
        }

        public Class<? extends Annotation> annotationType() {
            return Protobuf.class;
        }

        public int tag() {
            return this.tag;
        }

        public Protobuf.IntEncoding intEncoding() {
            return this.intEncoding;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Protobuf)) {
                return false;
            }
            Protobuf protobuf = (Protobuf) obj;
            if (this.tag != protobuf.tag() || !this.intEncoding.equals(protobuf.intEncoding())) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (14552422 ^ this.tag) + (this.intEncoding.hashCode() ^ 2041407134);
        }

        public String toString() {
            return "@com.google.firebase.encoders.proto.Protobuf" + '(' + "tag=" + this.tag + "intEncoding=" + this.intEncoding + ')';
        }
    }
}
