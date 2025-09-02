package org.objectweb.asm;

public abstract class ClassVisitor {
    protected final int api;
    protected ClassVisitor cv;

    public ClassVisitor(int i) {
        this(i, (ClassVisitor) null);
    }

    public ClassVisitor(int i, ClassVisitor classVisitor) {
        if (i == 262144 || i == 327680) {
            this.api = i;
            this.cv = classVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public abstract void visit(int i, int i2, String str, String str2, String str3, String[] strArr);

    public abstract AnnotationVisitor visitAnnotation(String str, boolean z);

    public abstract void visitAttribute(Attribute attribute);

    public abstract void visitEnd();

    public abstract FieldVisitor visitField(int i, String str, String str2, String str3, Object obj);

    public abstract void visitInnerClass(String str, String str2, String str3, int i);

    public abstract MethodVisitor visitMethod(int i, String str, String str2, String str3, String[] strArr);

    public abstract void visitOuterClass(String str, String str2, String str3);

    public abstract void visitSource(String str, String str2);

    public abstract AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z);
}
