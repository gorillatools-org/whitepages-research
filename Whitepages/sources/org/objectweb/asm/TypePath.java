package org.objectweb.asm;

public class TypePath {
    byte[] a;
    int b;

    TypePath(byte[] bArr, int i) {
        this.a = bArr;
        this.b = i;
    }

    public int getLength() {
        return this.a[this.b];
    }

    public int getStep(int i) {
        return this.a[this.b + (i * 2) + 1];
    }

    public int getStepArgument(int i) {
        return this.a[this.b + (i * 2) + 2];
    }

    public String toString() {
        char c;
        int length = getLength();
        StringBuffer stringBuffer = new StringBuffer(length * 2);
        for (int i = 0; i < length; i++) {
            int step = getStep(i);
            if (step == 0) {
                c = '[';
            } else if (step == 1) {
                c = '.';
            } else if (step == 2) {
                c = '*';
            } else if (step != 3) {
                c = '_';
            } else {
                stringBuffer.append(getStepArgument(i));
                c = ';';
            }
            stringBuffer.append(c);
        }
        return stringBuffer.toString();
    }
}
