package net.minidev.asm;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class BeansAccessBuilder {
    private static String METHOD_ACCESS_NAME = Type.getInternalName(BeansAccess.class);
    final String accessClassName;
    final String accessClassNameInternal;
    final Accessor[] accs;
    final String className;
    final String classNameInternal;
    final HashMap convMtds = new HashMap();
    Class exeptionClass = NoSuchFieldException.class;
    final DynamicClassLoader loader;
    final Class type;

    public BeansAccessBuilder(Class cls, Accessor[] accessorArr, DynamicClassLoader dynamicClassLoader) {
        this.type = cls;
        this.accs = accessorArr;
        this.loader = dynamicClassLoader;
        String name = cls.getName();
        this.className = name;
        if (name.startsWith("java.")) {
            this.accessClassName = "net.minidev.asm." + name + "AccAccess";
        } else {
            this.accessClassName = name.concat("AccAccess");
        }
        this.accessClassNameInternal = this.accessClassName.replace('.', '/');
        this.classNameInternal = name.replace('.', '/');
    }

    public void addConversion(Iterable iterable) {
        if (iterable != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                addConversion((Class) it.next());
            }
        }
    }

    public void addConversion(Class cls) {
        if (cls != null) {
            for (Method method : cls.getMethods()) {
                if ((method.getModifiers() & 8) != 0) {
                    Class[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].equals(Object.class)) {
                        Class<?> returnType = method.getReturnType();
                        if (!returnType.equals(Void.TYPE)) {
                            this.convMtds.put(returnType, method);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x027c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Class bulid() {
        /*
            r25 = this;
            r0 = r25
            org.objectweb.asm.ClassWriter r8 = new org.objectweb.asm.ClassWriter
            r9 = 1
            r8.<init>(r9)
            net.minidev.asm.Accessor[] r1 = r0.accs
            int r1 = r1.length
            r2 = 10
            r10 = 0
            if (r1 <= r2) goto L_0x0012
            r11 = r9
            goto L_0x0013
        L_0x0012:
            r11 = r10
        L_0x0013:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Lnet/minidev/asm/BeansAccess<L"
            r1.<init>(r2)
            java.lang.String r2 = r0.classNameInternal
            r1.append(r2)
            java.lang.String r2 = ";>;"
            r1.append(r2)
            java.lang.String r5 = r1.toString()
            java.lang.String r4 = r0.accessClassNameInternal
            java.lang.String r6 = METHOD_ACCESS_NAME
            r7 = 0
            r2 = 50
            r3 = 33
            r1 = r8
            r1.visit(r2, r3, r4, r5, r6, r7)
            r5 = 0
            r6 = 0
            r2 = 1
            java.lang.String r3 = "<init>"
            java.lang.String r4 = "()V"
            org.objectweb.asm.MethodVisitor r1 = r1.visitMethod(r2, r3, r4, r5, r6)
            r1.visitCode()
            r7 = 25
            r1.visitVarInsn(r7, r10)
            java.lang.String r2 = METHOD_ACCESS_NAME
            r12 = 183(0xb7, float:2.56E-43)
            java.lang.String r13 = "<init>"
            java.lang.String r14 = "()V"
            r1.visitMethodInsn(r12, r2, r13, r14)
            r15 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r15)
            r1.visitMaxs(r9, r9)
            r1.visitEnd()
            r2 = 1
            java.lang.String r3 = "set"
            java.lang.String r4 = "(Ljava/lang/Object;ILjava/lang/Object;)V"
            r1 = r8
            org.objectweb.asm.MethodVisitor r1 = r1.visitMethod(r2, r3, r4, r5, r6)
            r1.visitCode()
            net.minidev.asm.Accessor[] r2 = r0.accs
            int r3 = r2.length
            r6 = 21
            r5 = 2
            r4 = 14
            if (r3 == 0) goto L_0x00e5
            int r3 = r2.length
            if (r3 <= r4) goto L_0x00b6
            r1.visitVarInsn(r6, r5)
            net.minidev.asm.Accessor[] r2 = r0.accs
            int r2 = r2.length
            org.objectweb.asm.Label[] r3 = net.minidev.asm.ASMUtil.newLabels(r2)
            org.objectweb.asm.Label r2 = new org.objectweb.asm.Label
            r2.<init>()
            int r4 = r3.length
            int r4 = r4 - r9
            r1.visitTableSwitchInsn(r10, r4, r2, r3)
            net.minidev.asm.Accessor[] r4 = r0.accs
            int r6 = r4.length
            r12 = r10
            r16 = r12
        L_0x0092:
            if (r12 < r6) goto L_0x0098
            r1.visitLabel(r2)
            goto L_0x00e5
        L_0x0098:
            r7 = r4[r12]
            int r17 = r16 + 1
            r10 = r3[r16]
            r1.visitLabel(r10)
            boolean r10 = r7.isWritable()
            if (r10 != 0) goto L_0x00ab
            r1.visitInsn(r15)
            goto L_0x00ae
        L_0x00ab:
            r0.internalSetFiled(r1, r7)
        L_0x00ae:
            int r12 = r12 + 1
            r16 = r17
            r7 = 25
            r10 = 0
            goto L_0x0092
        L_0x00b6:
            int r2 = r2.length
            org.objectweb.asm.Label[] r2 = net.minidev.asm.ASMUtil.newLabels(r2)
            net.minidev.asm.Accessor[] r3 = r0.accs
            int r4 = r3.length
            r6 = 0
            r7 = 0
        L_0x00c0:
            if (r6 < r4) goto L_0x00c3
            goto L_0x00e5
        L_0x00c3:
            r10 = r3[r6]
            r12 = r2[r7]
            r0.ifNotEqJmp(r1, r5, r7, r12)
            r0.internalSetFiled(r1, r10)
            r10 = r2[r7]
            r1.visitLabel(r10)
            r20 = 0
            r21 = 0
            r17 = 3
            r18 = 0
            r19 = 0
            r16 = r1
            r16.visitFrame(r17, r18, r19, r20, r21)
            int r7 = r7 + r9
            int r6 = r6 + 1
            goto L_0x00c0
        L_0x00e5:
            java.lang.Class r2 = r0.exeptionClass
            if (r2 == 0) goto L_0x00ee
            r0.throwExIntParam(r1, r2)
        L_0x00ec:
            r2 = 0
            goto L_0x00f2
        L_0x00ee:
            r1.visitInsn(r15)
            goto L_0x00ec
        L_0x00f2:
            r1.visitMaxs(r2, r2)
            r1.visitEnd()
            r6 = 0
            r7 = 0
            r2 = 1
            java.lang.String r3 = "get"
            java.lang.String r4 = "(Ljava/lang/Object;I)Ljava/lang/Object;"
            r1 = r8
            r10 = 14
            r12 = r5
            r5 = r6
            r15 = 21
            r6 = r7
            org.objectweb.asm.MethodVisitor r1 = r1.visitMethod(r2, r3, r4, r5, r6)
            r1.visitCode()
            net.minidev.asm.Accessor[] r2 = r0.accs
            int r3 = r2.length
            r6 = 192(0xc0, float:2.69E-43)
            r4 = 176(0xb0, float:2.47E-43)
            if (r3 != 0) goto L_0x012a
            r21 = 0
            r22 = 0
            r18 = 3
            r19 = 0
            r20 = 0
            r17 = r1
            r17.visitFrame(r18, r19, r20, r21, r22)
        L_0x0126:
            r9 = 182(0xb6, float:2.55E-43)
            goto L_0x01e2
        L_0x012a:
            int r3 = r2.length
            if (r3 <= r10) goto L_0x01d4
            r1.visitVarInsn(r15, r12)
            net.minidev.asm.Accessor[] r2 = r0.accs
            int r2 = r2.length
            org.objectweb.asm.Label[] r3 = net.minidev.asm.ASMUtil.newLabels(r2)
            org.objectweb.asm.Label r10 = new org.objectweb.asm.Label
            r10.<init>()
            int r2 = r3.length
            int r2 = r2 - r9
            r15 = 0
            r1.visitTableSwitchInsn(r15, r2, r10, r3)
            net.minidev.asm.Accessor[] r15 = r0.accs
            int r2 = r15.length
            r12 = 0
            r17 = 0
        L_0x0148:
            if (r12 < r2) goto L_0x015d
            r1.visitLabel(r10)
            r21 = 0
            r22 = 0
            r18 = 3
            r19 = 0
            r20 = 0
            r17 = r1
            r17.visitFrame(r18, r19, r20, r21, r22)
            goto L_0x0126
        L_0x015d:
            r5 = r15[r12]
            int r23 = r17 + 1
            r7 = r3[r17]
            r1.visitLabel(r7)
            r21 = 0
            r22 = 0
            r18 = 3
            r19 = 0
            r20 = 0
            r17 = r1
            r17.visitFrame(r18, r19, r20, r21, r22)
            boolean r7 = r5.isReadable()
            if (r7 != 0) goto L_0x0184
            r1.visitInsn(r9)
            r1.visitInsn(r4)
            r9 = 182(0xb6, float:2.55E-43)
            goto L_0x01c9
        L_0x0184:
            r7 = 25
            r1.visitVarInsn(r7, r9)
            java.lang.String r7 = r0.classNameInternal
            r1.visitTypeInsn(r6, r7)
            java.lang.Class r7 = r5.getType()
            org.objectweb.asm.Type r7 = org.objectweb.asm.Type.getType((java.lang.Class) r7)
            boolean r17 = r5.isPublic()
            if (r17 == 0) goto L_0x01ae
            java.lang.String r6 = r0.classNameInternal
            java.lang.String r5 = r5.getName()
            java.lang.String r9 = r7.getDescriptor()
            r4 = 180(0xb4, float:2.52E-43)
            r1.visitFieldInsn(r4, r6, r5, r9)
            r9 = 182(0xb6, float:2.55E-43)
            goto L_0x01c1
        L_0x01ae:
            java.lang.reflect.Method r4 = r5.getter
            java.lang.String r4 = org.objectweb.asm.Type.getMethodDescriptor(r4)
            java.lang.String r6 = r0.classNameInternal
            java.lang.reflect.Method r5 = r5.getter
            java.lang.String r5 = r5.getName()
            r9 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r9, r6, r5, r4)
        L_0x01c1:
            net.minidev.asm.ASMUtil.autoBoxing(r1, r7)
            r4 = 176(0xb0, float:2.47E-43)
            r1.visitInsn(r4)
        L_0x01c9:
            int r12 = r12 + 1
            r17 = r23
            r4 = 176(0xb0, float:2.47E-43)
            r6 = 192(0xc0, float:2.69E-43)
            r9 = 1
            goto L_0x0148
        L_0x01d4:
            r9 = 182(0xb6, float:2.55E-43)
            int r2 = r2.length
            org.objectweb.asm.Label[] r2 = net.minidev.asm.ASMUtil.newLabels(r2)
            net.minidev.asm.Accessor[] r3 = r0.accs
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x01e0:
            if (r5 < r4) goto L_0x0375
        L_0x01e2:
            java.lang.Class r2 = r0.exeptionClass
            if (r2 == 0) goto L_0x01ed
            r0.throwExIntParam(r1, r2)
            r2 = 0
            r4 = 176(0xb0, float:2.47E-43)
            goto L_0x01f7
        L_0x01ed:
            r2 = 1
            r1.visitInsn(r2)
            r4 = 176(0xb0, float:2.47E-43)
            r1.visitInsn(r4)
            r2 = 0
        L_0x01f7:
            r1.visitMaxs(r2, r2)
            r1.visitEnd()
            java.lang.String r10 = "(Ljava/lang/Object;)Z"
            java.lang.String r12 = "equals"
            java.lang.String r15 = "java/lang/String"
            if (r11 != 0) goto L_0x027a
            r5 = 0
            r6 = 0
            r2 = 1
            java.lang.String r3 = "set"
            java.lang.String r18 = "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/Object;)V"
            r1 = r8
            r9 = r4
            r4 = r18
            r9 = 182(0xb6, float:2.55E-43)
            org.objectweb.asm.MethodVisitor r1 = r1.visitMethod(r2, r3, r4, r5, r6)
            r1.visitCode()
            net.minidev.asm.Accessor[] r2 = r0.accs
            int r2 = r2.length
            org.objectweb.asm.Label[] r2 = net.minidev.asm.ASMUtil.newLabels(r2)
            net.minidev.asm.Accessor[] r3 = r0.accs
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x0225:
            if (r5 < r4) goto L_0x023d
            java.lang.Class r2 = r0.exeptionClass
            if (r2 == 0) goto L_0x0230
            r0.throwExStrParam(r1, r2)
        L_0x022e:
            r2 = 0
            goto L_0x0236
        L_0x0230:
            r2 = 177(0xb1, float:2.48E-43)
            r1.visitInsn(r2)
            goto L_0x022e
        L_0x0236:
            r1.visitMaxs(r2, r2)
            r1.visitEnd()
            goto L_0x027a
        L_0x023d:
            r16 = 177(0xb1, float:2.48E-43)
            r7 = r3[r5]
            r24 = r3
            r3 = 2
            r9 = 25
            r1.visitVarInsn(r9, r3)
            java.lang.String r3 = r7.fieldName
            r1.visitLdcInsn(r3)
            r3 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r3, r15, r12, r10)
            r3 = r2[r6]
            r9 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r9, r3)
            r0.internalSetFiled(r1, r7)
            r3 = r2[r6]
            r1.visitLabel(r3)
            r21 = 0
            r22 = 0
            r18 = 3
            r19 = 0
            r20 = 0
            r17 = r1
            r17.visitFrame(r18, r19, r20, r21, r22)
            r3 = 1
            int r6 = r6 + r3
            int r5 = r5 + 1
            r3 = r24
            r9 = 182(0xb6, float:2.55E-43)
            goto L_0x0225
        L_0x027a:
            if (r11 != 0) goto L_0x0336
            r5 = 0
            r6 = 0
            r2 = 1
            java.lang.String r3 = "get"
            java.lang.String r4 = "(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;"
            r1 = r8
            org.objectweb.asm.MethodVisitor r1 = r1.visitMethod(r2, r3, r4, r5, r6)
            r1.visitCode()
            net.minidev.asm.Accessor[] r2 = r0.accs
            int r2 = r2.length
            org.objectweb.asm.Label[] r2 = net.minidev.asm.ASMUtil.newLabels(r2)
            net.minidev.asm.Accessor[] r3 = r0.accs
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x0297:
            if (r5 < r4) goto L_0x02b4
            java.lang.Class r2 = r0.exeptionClass
            if (r2 == 0) goto L_0x02a2
            r0.throwExStrParam(r1, r2)
        L_0x02a0:
            r7 = 0
            goto L_0x02ac
        L_0x02a2:
            r2 = 1
            r1.visitInsn(r2)
            r2 = 176(0xb0, float:2.47E-43)
            r1.visitInsn(r2)
            goto L_0x02a0
        L_0x02ac:
            r1.visitMaxs(r7, r7)
            r1.visitEnd()
            goto L_0x0336
        L_0x02b4:
            r7 = 0
            r9 = r3[r5]
            r7 = 2
            r11 = 25
            r1.visitVarInsn(r11, r7)
            java.lang.String r7 = r9.fieldName
            r1.visitLdcInsn(r7)
            r7 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r7, r15, r12, r10)
            r7 = r2[r6]
            r22 = r3
            r3 = 153(0x99, float:2.14E-43)
            r1.visitJumpInsn(r3, r7)
            r7 = 1
            r1.visitVarInsn(r11, r7)
            java.lang.String r7 = r0.classNameInternal
            r11 = 192(0xc0, float:2.69E-43)
            r1.visitTypeInsn(r11, r7)
            java.lang.Class r7 = r9.getType()
            org.objectweb.asm.Type r7 = org.objectweb.asm.Type.getType((java.lang.Class) r7)
            boolean r16 = r9.isPublic()
            if (r16 == 0) goto L_0x02fb
            java.lang.String r3 = r0.classNameInternal
            java.lang.String r9 = r9.getName()
            java.lang.String r11 = r7.getDescriptor()
            r24 = r4
            r4 = 180(0xb4, float:2.52E-43)
            r1.visitFieldInsn(r4, r3, r9, r11)
            goto L_0x0310
        L_0x02fb:
            r24 = r4
            java.lang.reflect.Method r3 = r9.getter
            java.lang.String r3 = org.objectweb.asm.Type.getMethodDescriptor(r3)
            java.lang.String r4 = r0.classNameInternal
            java.lang.reflect.Method r9 = r9.getter
            java.lang.String r9 = r9.getName()
            r11 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r11, r4, r9, r3)
        L_0x0310:
            net.minidev.asm.ASMUtil.autoBoxing(r1, r7)
            r3 = 176(0xb0, float:2.47E-43)
            r1.visitInsn(r3)
            r3 = r2[r6]
            r1.visitLabel(r3)
            r20 = 0
            r21 = 0
            r17 = 3
            r18 = 0
            r19 = 0
            r16 = r1
            r16.visitFrame(r17, r18, r19, r20, r21)
            int r6 = r6 + 1
            int r5 = r5 + 1
            r3 = r22
            r4 = r24
            goto L_0x0297
        L_0x0336:
            r5 = 0
            r6 = 0
            r2 = 1
            java.lang.String r3 = "newInstance"
            java.lang.String r4 = "()Ljava/lang/Object;"
            r1 = r8
            org.objectweb.asm.MethodVisitor r1 = r1.visitMethod(r2, r3, r4, r5, r6)
            r1.visitCode()
            r2 = 187(0xbb, float:2.62E-43)
            java.lang.String r3 = r0.classNameInternal
            r1.visitTypeInsn(r2, r3)
            r2 = 89
            r1.visitInsn(r2)
            java.lang.String r2 = r0.classNameInternal
            r7 = 183(0xb7, float:2.56E-43)
            r1.visitMethodInsn(r7, r2, r13, r14)
            r2 = 176(0xb0, float:2.47E-43)
            r1.visitInsn(r2)
            r9 = 1
            r10 = 2
            r1.visitMaxs(r10, r9)
            r1.visitEnd()
            r8.visitEnd()
            byte[] r1 = r8.toByteArray()
            net.minidev.asm.DynamicClassLoader r2 = r0.loader
            java.lang.String r3 = r0.accessClassName
            java.lang.Class r1 = r2.defineClass(r3, r1)
            return r1
        L_0x0375:
            r7 = 183(0xb7, float:2.56E-43)
            r9 = 1
            r10 = 2
            r12 = 192(0xc0, float:2.69E-43)
            r16 = 177(0xb1, float:2.48E-43)
            r15 = r3[r5]
            r7 = r2[r6]
            r0.ifNotEqJmp(r1, r10, r6, r7)
            r7 = 25
            r1.visitVarInsn(r7, r9)
            java.lang.String r7 = r0.classNameInternal
            r1.visitTypeInsn(r12, r7)
            java.lang.Class r7 = r15.getType()
            org.objectweb.asm.Type r7 = org.objectweb.asm.Type.getType((java.lang.Class) r7)
            boolean r17 = r15.isPublic()
            if (r17 == 0) goto L_0x03ae
            java.lang.String r9 = r0.classNameInternal
            java.lang.String r15 = r15.getName()
            java.lang.String r10 = r7.getDescriptor()
            r12 = 180(0xb4, float:2.52E-43)
            r1.visitFieldInsn(r12, r9, r15, r10)
            r12 = 182(0xb6, float:2.55E-43)
            goto L_0x03c5
        L_0x03ae:
            r12 = 180(0xb4, float:2.52E-43)
            java.lang.reflect.Method r9 = r15.getter
            if (r9 == 0) goto L_0x03e8
            java.lang.String r9 = org.objectweb.asm.Type.getMethodDescriptor(r9)
            java.lang.String r10 = r0.classNameInternal
            java.lang.reflect.Method r15 = r15.getter
            java.lang.String r15 = r15.getName()
            r12 = 182(0xb6, float:2.55E-43)
            r1.visitMethodInsn(r12, r10, r15, r9)
        L_0x03c5:
            net.minidev.asm.ASMUtil.autoBoxing(r1, r7)
            r7 = 176(0xb0, float:2.47E-43)
            r1.visitInsn(r7)
            r9 = r2[r6]
            r1.visitLabel(r9)
            r21 = 0
            r22 = 0
            r18 = 3
            r19 = 0
            r20 = 0
            r17 = r1
            r17.visitFrame(r18, r19, r20, r21, r22)
            int r6 = r6 + 1
            int r5 = r5 + 1
            r9 = r12
            goto L_0x01e0
        L_0x03e8:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "no Getter for field "
            r2.<init>(r3)
            java.lang.String r3 = r15.getName()
            r2.append(r3)
            java.lang.String r3 = " in class "
            r2.append(r3)
            java.lang.String r3 = r0.className
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: net.minidev.asm.BeansAccessBuilder.bulid():java.lang.Class");
    }

    private void internalSetFiled(MethodVisitor methodVisitor, Accessor accessor) {
        MethodVisitor methodVisitor2 = methodVisitor;
        Accessor accessor2 = accessor;
        methodVisitor2.visitVarInsn(25, 1);
        methodVisitor2.visitTypeInsn(192, this.classNameInternal);
        methodVisitor2.visitVarInsn(25, 3);
        Type type2 = Type.getType(accessor.getType());
        Class type3 = accessor.getType();
        String internalName = Type.getInternalName(type3);
        Method method = (Method) this.convMtds.get(type3);
        if (method != null) {
            methodVisitor2.visitMethodInsn(184, Type.getInternalName(method.getDeclaringClass()), method.getName(), Type.getMethodDescriptor(method));
        } else if (accessor.isEnum()) {
            Label label = new Label();
            methodVisitor2.visitJumpInsn(198, label);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitMethodInsn(182, "java/lang/Object", "toString", "()Ljava/lang/String;");
            methodVisitor2.visitMethodInsn(184, internalName, "valueOf", "(Ljava/lang/String;)L" + internalName + ";");
            methodVisitor2.visitVarInsn(58, 3);
            methodVisitor2.visitLabel(label);
            methodVisitor.visitFrame(3, 0, (Object[]) null, 0, (Object[]) null);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitTypeInsn(192, this.classNameInternal);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitTypeInsn(192, internalName);
        } else if (type3.equals(String.class)) {
            Label label2 = new Label();
            methodVisitor2.visitJumpInsn(198, label2);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitMethodInsn(182, "java/lang/Object", "toString", "()Ljava/lang/String;");
            methodVisitor2.visitVarInsn(58, 3);
            methodVisitor2.visitLabel(label2);
            methodVisitor.visitFrame(3, 0, (Object[]) null, 0, (Object[]) null);
            methodVisitor2.visitVarInsn(25, 1);
            methodVisitor2.visitTypeInsn(192, this.classNameInternal);
            methodVisitor2.visitVarInsn(25, 3);
            methodVisitor2.visitTypeInsn(192, internalName);
        } else {
            methodVisitor2.visitTypeInsn(192, internalName);
        }
        if (accessor.isPublic()) {
            methodVisitor2.visitFieldInsn(181, this.classNameInternal, accessor.getName(), type2.getDescriptor());
        } else {
            methodVisitor2.visitMethodInsn(182, this.classNameInternal, accessor2.setter.getName(), Type.getMethodDescriptor(accessor2.setter));
        }
        methodVisitor2.visitInsn(177);
    }

    private void throwExIntParam(MethodVisitor methodVisitor, Class cls) {
        String internalName = Type.getInternalName(cls);
        methodVisitor.visitTypeInsn(187, internalName);
        methodVisitor.visitInsn(89);
        methodVisitor.visitLdcInsn("mapping " + this.className + " failed to map field:");
        methodVisitor.visitVarInsn(21, 2);
        methodVisitor.visitMethodInsn(184, "java/lang/Integer", "toString", "(I)Ljava/lang/String;");
        methodVisitor.visitMethodInsn(182, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;");
        methodVisitor.visitMethodInsn(183, internalName, "<init>", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(191);
    }

    private void throwExStrParam(MethodVisitor methodVisitor, Class cls) {
        String internalName = Type.getInternalName(cls);
        methodVisitor.visitTypeInsn(187, internalName);
        methodVisitor.visitInsn(89);
        methodVisitor.visitLdcInsn("mapping " + this.className + " failed to map field:");
        methodVisitor.visitVarInsn(25, 2);
        methodVisitor.visitMethodInsn(182, "java/lang/String", "concat", "(Ljava/lang/String;)Ljava/lang/String;");
        methodVisitor.visitMethodInsn(183, internalName, "<init>", "(Ljava/lang/String;)V");
        methodVisitor.visitInsn(191);
    }

    private void ifNotEqJmp(MethodVisitor methodVisitor, int i, int i2, Label label) {
        methodVisitor.visitVarInsn(21, i);
        if (i2 == 0) {
            methodVisitor.visitJumpInsn(154, label);
        } else if (i2 == 1) {
            methodVisitor.visitInsn(4);
            methodVisitor.visitJumpInsn(160, label);
        } else if (i2 == 2) {
            methodVisitor.visitInsn(5);
            methodVisitor.visitJumpInsn(160, label);
        } else if (i2 == 3) {
            methodVisitor.visitInsn(6);
            methodVisitor.visitJumpInsn(160, label);
        } else if (i2 == 4) {
            methodVisitor.visitInsn(7);
            methodVisitor.visitJumpInsn(160, label);
        } else if (i2 == 5) {
            methodVisitor.visitInsn(8);
            methodVisitor.visitJumpInsn(160, label);
        } else if (i2 >= 6) {
            methodVisitor.visitIntInsn(16, i2);
            methodVisitor.visitJumpInsn(160, label);
        } else {
            throw new RuntimeException("non supported negative values");
        }
    }
}
