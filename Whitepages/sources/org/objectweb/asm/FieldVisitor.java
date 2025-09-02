package org.objectweb.asm;

public abstract class FieldVisitor {
    protected final int api;
    protected FieldVisitor fv;

    public FieldVisitor(int i) {
        this(i, (FieldVisitor) null);
    }

    public FieldVisitor(int i, FieldVisitor fieldVisitor) {
        if (i == 262144 || i == 327680) {
            this.api = i;
            this.fv = fieldVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public abstract AnnotationVisitor visitAnnotation(String str, boolean z);

    public abstract void visitAttribute(Attribute attribute);

    public abstract void visitEnd();

    public abstract AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z);
}
