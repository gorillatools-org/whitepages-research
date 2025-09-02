package org.objectweb.asm;

public abstract class AnnotationVisitor {
    protected final int api;
    protected AnnotationVisitor av;

    public AnnotationVisitor(int i) {
        this(i, (AnnotationVisitor) null);
    }

    public AnnotationVisitor(int i, AnnotationVisitor annotationVisitor) {
        if (i == 262144 || i == 327680) {
            this.api = i;
            this.av = annotationVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public abstract void visit(String str, Object obj);

    public abstract AnnotationVisitor visitAnnotation(String str, String str2);

    public abstract AnnotationVisitor visitArray(String str);

    public abstract void visitEnd();

    public abstract void visitEnum(String str, String str2, String str3);
}
