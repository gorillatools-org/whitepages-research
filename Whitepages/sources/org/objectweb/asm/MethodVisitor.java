package org.objectweb.asm;

public abstract class MethodVisitor {
    protected final int api;
    protected MethodVisitor mv;

    public MethodVisitor(int i) {
        this(i, (MethodVisitor) null);
    }

    public MethodVisitor(int i, MethodVisitor methodVisitor) {
        if (i == 262144 || i == 327680) {
            this.api = i;
            this.mv = methodVisitor;
            return;
        }
        throw new IllegalArgumentException();
    }

    public abstract AnnotationVisitor visitAnnotation(String str, boolean z);

    public abstract AnnotationVisitor visitAnnotationDefault();

    public abstract void visitAttribute(Attribute attribute);

    public abstract void visitCode();

    public abstract void visitEnd();

    public abstract void visitFieldInsn(int i, String str, String str2, String str3);

    public abstract void visitFrame(int i, int i2, Object[] objArr, int i3, Object[] objArr2);

    public abstract void visitIincInsn(int i, int i2);

    public abstract void visitInsn(int i);

    public abstract AnnotationVisitor visitInsnAnnotation(int i, TypePath typePath, String str, boolean z);

    public abstract void visitIntInsn(int i, int i2);

    public abstract void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr);

    public abstract void visitJumpInsn(int i, Label label);

    public abstract void visitLabel(Label label);

    public abstract void visitLdcInsn(Object obj);

    public abstract void visitLineNumber(int i, Label label);

    public abstract void visitLocalVariable(String str, String str2, String str3, Label label, Label label2, int i);

    public abstract AnnotationVisitor visitLocalVariableAnnotation(int i, TypePath typePath, Label[] labelArr, Label[] labelArr2, int[] iArr, String str, boolean z);

    public abstract void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr);

    public abstract void visitMaxs(int i, int i2);

    public void visitMethodInsn(int i, String str, String str2, String str3) {
        if (this.api >= 327680) {
            visitMethodInsn(i, str, str2, str3, i == 185);
            return;
        }
        MethodVisitor methodVisitor = this.mv;
        if (methodVisitor != null) {
            methodVisitor.visitMethodInsn(i, str, str2, str3);
        }
    }

    public abstract void visitMethodInsn(int i, String str, String str2, String str3, boolean z);

    public abstract void visitMultiANewArrayInsn(String str, int i);

    public abstract void visitParameter(String str, int i);

    public abstract AnnotationVisitor visitParameterAnnotation(int i, String str, boolean z);

    public abstract void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr);

    public abstract AnnotationVisitor visitTryCatchAnnotation(int i, TypePath typePath, String str, boolean z);

    public abstract void visitTryCatchBlock(Label label, Label label2, Label label3, String str);

    public abstract AnnotationVisitor visitTypeAnnotation(int i, TypePath typePath, String str, boolean z);

    public abstract void visitTypeInsn(int i, String str);

    public abstract void visitVarInsn(int i, int i2);
}
