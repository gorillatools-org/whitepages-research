package androidx.datastore.preferences.protobuf;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.datastore.preferences.protobuf.Internal;
import androidx.datastore.preferences.protobuf.WireFormat;
import androidx.datastore.preferences.protobuf.Writer;
import com.facebook.react.uimanager.drawable.InsetBoxShadowDrawableKt;
import com.facebook.react.uimanager.drawable.OutsetBoxShadowDrawableKt;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.fido.u2f.api.common.RegisterRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

final class MessageSchema implements Schema {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private static final Unsafe UNSAFE = UnsafeUtil.getUnsafe();
    private final int[] buffer;
    private final int checkInitializedCount;
    private final MessageLite defaultInstance;
    private final ExtensionSchema extensionSchema;
    private final boolean hasExtensions;
    private final int[] intArray;
    private final ListFieldSchema listFieldSchema;
    private final boolean lite;
    private final MapFieldSchema mapFieldSchema;
    private final int maxFieldNumber;
    private final int minFieldNumber;
    private final NewInstanceSchema newInstanceSchema;
    private final Object[] objects;
    private final int repeatedFieldOffsetStart;
    private final ProtoSyntax syntax;
    private final UnknownFieldSchema unknownFieldSchema;
    private final boolean useCachedSizeField;

    private static boolean isEnforceUtf8(int i) {
        return (i & 536870912) != 0;
    }

    private static boolean isRequired(int i) {
        return (i & 268435456) != 0;
    }

    private static long offset(int i) {
        return (long) (i & 1048575);
    }

    private static int type(int i) {
        return (i & 267386880) >>> 20;
    }

    private MessageSchema(int[] iArr, Object[] objArr, int i, int i2, MessageLite messageLite, ProtoSyntax protoSyntax, boolean z, int[] iArr2, int i3, int i4, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema unknownFieldSchema2, ExtensionSchema extensionSchema2, MapFieldSchema mapFieldSchema2) {
        MessageLite messageLite2 = messageLite;
        ExtensionSchema extensionSchema3 = extensionSchema2;
        this.buffer = iArr;
        this.objects = objArr;
        this.minFieldNumber = i;
        this.maxFieldNumber = i2;
        this.lite = messageLite2 instanceof GeneratedMessageLite;
        this.syntax = protoSyntax;
        this.hasExtensions = extensionSchema3 != null && extensionSchema3.hasExtensions(messageLite);
        this.useCachedSizeField = z;
        this.intArray = iArr2;
        this.checkInitializedCount = i3;
        this.repeatedFieldOffsetStart = i4;
        this.newInstanceSchema = newInstanceSchema2;
        this.listFieldSchema = listFieldSchema2;
        this.unknownFieldSchema = unknownFieldSchema2;
        this.extensionSchema = extensionSchema3;
        this.defaultInstance = messageLite2;
        this.mapFieldSchema = mapFieldSchema2;
    }

    static MessageSchema newSchema(Class cls, MessageInfo messageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema unknownFieldSchema2, ExtensionSchema extensionSchema2, MapFieldSchema mapFieldSchema2) {
        if (messageInfo instanceof RawMessageInfo) {
            return newSchemaForRawMessageInfo((RawMessageInfo) messageInfo, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
        }
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(messageInfo);
        return newSchemaForMessageInfo((StructuralMessageInfo) null, newInstanceSchema2, listFieldSchema2, unknownFieldSchema2, extensionSchema2, mapFieldSchema2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:118:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0264  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0267  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.datastore.preferences.protobuf.MessageSchema newSchemaForRawMessageInfo(androidx.datastore.preferences.protobuf.RawMessageInfo r31, androidx.datastore.preferences.protobuf.NewInstanceSchema r32, androidx.datastore.preferences.protobuf.ListFieldSchema r33, androidx.datastore.preferences.protobuf.UnknownFieldSchema r34, androidx.datastore.preferences.protobuf.ExtensionSchema r35, androidx.datastore.preferences.protobuf.MapFieldSchema r36) {
        /*
            java.lang.String r0 = r31.getStringInfo()
            int r1 = r0.length()
            r2 = 0
            char r3 = r0.charAt(r2)
            r5 = 55296(0xd800, float:7.7486E-41)
            if (r3 < r5) goto L_0x001d
            r3 = 1
        L_0x0013:
            int r6 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x001e
            r3 = r6
            goto L_0x0013
        L_0x001d:
            r6 = 1
        L_0x001e:
            int r3 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x003d
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x002a:
            int r9 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x003a
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            int r3 = r3 << r8
            r6 = r6 | r3
            int r8 = r8 + 13
            r3 = r9
            goto L_0x002a
        L_0x003a:
            int r3 = r3 << r8
            r6 = r6 | r3
            r3 = r9
        L_0x003d:
            if (r6 != 0) goto L_0x004e
            int[] r6 = EMPTY_INT_ARRAY
            r10 = r2
            r11 = r10
            r12 = r11
            r13 = r12
            r15 = r13
            r17 = r15
            r16 = r6
            r6 = r17
            goto L_0x0158
        L_0x004e:
            int r6 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x006d
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r8 = 13
        L_0x005a:
            int r9 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x006a
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            int r6 = r6 << r8
            r3 = r3 | r6
            int r8 = r8 + 13
            r6 = r9
            goto L_0x005a
        L_0x006a:
            int r6 = r6 << r8
            r3 = r3 | r6
            r6 = r9
        L_0x006d:
            int r8 = r6 + 1
            char r6 = r0.charAt(r6)
            if (r6 < r5) goto L_0x008c
            r6 = r6 & 8191(0x1fff, float:1.1478E-41)
            r9 = 13
        L_0x0079:
            int r10 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x0089
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            int r8 = r8 << r9
            r6 = r6 | r8
            int r9 = r9 + 13
            r8 = r10
            goto L_0x0079
        L_0x0089:
            int r8 = r8 << r9
            r6 = r6 | r8
            r8 = r10
        L_0x008c:
            int r9 = r8 + 1
            char r8 = r0.charAt(r8)
            if (r8 < r5) goto L_0x00ab
            r8 = r8 & 8191(0x1fff, float:1.1478E-41)
            r10 = 13
        L_0x0098:
            int r11 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00a8
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            int r9 = r9 << r10
            r8 = r8 | r9
            int r10 = r10 + 13
            r9 = r11
            goto L_0x0098
        L_0x00a8:
            int r9 = r9 << r10
            r8 = r8 | r9
            r9 = r11
        L_0x00ab:
            int r10 = r9 + 1
            char r9 = r0.charAt(r9)
            if (r9 < r5) goto L_0x00ca
            r9 = r9 & 8191(0x1fff, float:1.1478E-41)
            r11 = 13
        L_0x00b7:
            int r12 = r10 + 1
            char r10 = r0.charAt(r10)
            if (r10 < r5) goto L_0x00c7
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            int r10 = r10 << r11
            r9 = r9 | r10
            int r11 = r11 + 13
            r10 = r12
            goto L_0x00b7
        L_0x00c7:
            int r10 = r10 << r11
            r9 = r9 | r10
            r10 = r12
        L_0x00ca:
            int r11 = r10 + 1
            char r10 = r0.charAt(r10)
            if (r10 < r5) goto L_0x00e9
            r10 = r10 & 8191(0x1fff, float:1.1478E-41)
            r12 = 13
        L_0x00d6:
            int r13 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x00e6
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            int r11 = r11 << r12
            r10 = r10 | r11
            int r12 = r12 + 13
            r11 = r13
            goto L_0x00d6
        L_0x00e6:
            int r11 = r11 << r12
            r10 = r10 | r11
            r11 = r13
        L_0x00e9:
            int r12 = r11 + 1
            char r11 = r0.charAt(r11)
            if (r11 < r5) goto L_0x0108
            r11 = r11 & 8191(0x1fff, float:1.1478E-41)
            r13 = 13
        L_0x00f5:
            int r14 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x0105
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r13
            r11 = r11 | r12
            int r13 = r13 + 13
            r12 = r14
            goto L_0x00f5
        L_0x0105:
            int r12 = r12 << r13
            r11 = r11 | r12
            r12 = r14
        L_0x0108:
            int r13 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r5) goto L_0x0127
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            r14 = 13
        L_0x0114:
            int r15 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0124
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            int r13 = r13 << r14
            r12 = r12 | r13
            int r14 = r14 + 13
            r13 = r15
            goto L_0x0114
        L_0x0124:
            int r13 = r13 << r14
            r12 = r12 | r13
            r13 = r15
        L_0x0127:
            int r14 = r13 + 1
            char r13 = r0.charAt(r13)
            if (r13 < r5) goto L_0x0148
            r13 = r13 & 8191(0x1fff, float:1.1478E-41)
            r15 = 13
        L_0x0133:
            int r16 = r14 + 1
            char r14 = r0.charAt(r14)
            if (r14 < r5) goto L_0x0144
            r14 = r14 & 8191(0x1fff, float:1.1478E-41)
            int r14 = r14 << r15
            r13 = r13 | r14
            int r15 = r15 + 13
            r14 = r16
            goto L_0x0133
        L_0x0144:
            int r14 = r14 << r15
            r13 = r13 | r14
            r14 = r16
        L_0x0148:
            int r15 = r13 + r11
            int r15 = r15 + r12
            int[] r12 = new int[r15]
            int r15 = r3 * 2
            int r15 = r15 + r6
            r6 = r3
            r16 = r12
            r17 = r13
            r3 = r14
            r12 = r8
            r13 = r9
        L_0x0158:
            sun.misc.Unsafe r8 = UNSAFE
            java.lang.Object[] r9 = r31.getObjects()
            androidx.datastore.preferences.protobuf.MessageLite r14 = r31.getDefaultInstance()
            java.lang.Class r14 = r14.getClass()
            int r2 = r10 * 3
            int[] r2 = new int[r2]
            int r10 = r10 * 2
            java.lang.Object[] r10 = new java.lang.Object[r10]
            int r19 = r17 + r11
            r21 = r17
            r22 = r19
            r11 = 0
            r20 = 0
        L_0x0177:
            if (r3 >= r1) goto L_0x03bc
            int r23 = r3 + 1
            char r3 = r0.charAt(r3)
            if (r3 < r5) goto L_0x019f
            r3 = r3 & 8191(0x1fff, float:1.1478E-41)
            r7 = r23
            r23 = 13
        L_0x0187:
            int r24 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 < r5) goto L_0x0199
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            int r7 = r7 << r23
            r3 = r3 | r7
            int r23 = r23 + 13
            r7 = r24
            goto L_0x0187
        L_0x0199:
            int r7 = r7 << r23
            r3 = r3 | r7
            r7 = r24
            goto L_0x01a1
        L_0x019f:
            r7 = r23
        L_0x01a1:
            int r23 = r7 + 1
            char r7 = r0.charAt(r7)
            if (r7 < r5) goto L_0x01c7
            r7 = r7 & 8191(0x1fff, float:1.1478E-41)
            r4 = r23
            r23 = 13
        L_0x01af:
            int r25 = r4 + 1
            char r4 = r0.charAt(r4)
            if (r4 < r5) goto L_0x01c1
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            int r4 = r4 << r23
            r7 = r7 | r4
            int r23 = r23 + 13
            r4 = r25
            goto L_0x01af
        L_0x01c1:
            int r4 = r4 << r23
            r7 = r7 | r4
            r4 = r25
            goto L_0x01c9
        L_0x01c7:
            r4 = r23
        L_0x01c9:
            r5 = r7 & 255(0xff, float:3.57E-43)
            r25 = r1
            r1 = r7 & 1024(0x400, float:1.435E-42)
            if (r1 == 0) goto L_0x01d6
            int r1 = r11 + 1
            r16[r11] = r20
            r11 = r1
        L_0x01d6:
            r1 = 51
            r27 = r11
            if (r5 < r1) goto L_0x027f
            int r1 = r4 + 1
            char r4 = r0.charAt(r4)
            r11 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r11) goto L_0x0205
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r29 = 13
        L_0x01eb:
            int r30 = r1 + 1
            char r1 = r0.charAt(r1)
            if (r1 < r11) goto L_0x0200
            r1 = r1 & 8191(0x1fff, float:1.1478E-41)
            int r1 = r1 << r29
            r4 = r4 | r1
            int r29 = r29 + 13
            r1 = r30
            r11 = 55296(0xd800, float:7.7486E-41)
            goto L_0x01eb
        L_0x0200:
            int r1 = r1 << r29
            r4 = r4 | r1
            r1 = r30
        L_0x0205:
            int r11 = r5 + -51
            r29 = r1
            r1 = 9
            if (r11 == r1) goto L_0x0234
            r1 = 17
            if (r11 != r1) goto L_0x0212
            goto L_0x0234
        L_0x0212:
            r1 = 12
            if (r11 != r1) goto L_0x0241
            androidx.datastore.preferences.protobuf.ProtoSyntax r1 = r31.getSyntax()
            androidx.datastore.preferences.protobuf.ProtoSyntax r11 = androidx.datastore.preferences.protobuf.ProtoSyntax.PROTO2
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L_0x0226
            r1 = r7 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x0241
        L_0x0226:
            int r1 = r20 / 3
            int r1 = r1 * 2
            r11 = 1
            int r1 = r1 + r11
            int r11 = r15 + 1
            r15 = r9[r15]
            r10[r1] = r15
        L_0x0232:
            r15 = r11
            goto L_0x0241
        L_0x0234:
            int r1 = r20 / 3
            int r1 = r1 * 2
            r11 = 1
            int r1 = r1 + r11
            int r11 = r15 + 1
            r15 = r9[r15]
            r10[r1] = r15
            goto L_0x0232
        L_0x0241:
            int r4 = r4 * 2
            r1 = r9[r4]
            boolean r11 = r1 instanceof java.lang.reflect.Field
            if (r11 == 0) goto L_0x024e
            java.lang.reflect.Field r1 = (java.lang.reflect.Field) r1
        L_0x024b:
            r30 = r12
            goto L_0x0257
        L_0x024e:
            java.lang.String r1 = (java.lang.String) r1
            java.lang.reflect.Field r1 = reflectField(r14, r1)
            r9[r4] = r1
            goto L_0x024b
        L_0x0257:
            long r11 = r8.objectFieldOffset(r1)
            int r1 = (int) r11
            int r4 = r4 + 1
            r11 = r9[r4]
            boolean r12 = r11 instanceof java.lang.reflect.Field
            if (r12 == 0) goto L_0x0267
            java.lang.reflect.Field r11 = (java.lang.reflect.Field) r11
            goto L_0x026f
        L_0x0267:
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = reflectField(r14, r11)
            r9[r4] = r11
        L_0x026f:
            long r11 = r8.objectFieldOffset(r11)
            int r4 = (int) r11
            r26 = r13
            r23 = r15
            r12 = r29
            r15 = r0
            r0 = r4
            r4 = 0
            goto L_0x037a
        L_0x027f:
            r30 = r12
            int r1 = r15 + 1
            r11 = r9[r15]
            java.lang.String r11 = (java.lang.String) r11
            java.lang.reflect.Field r11 = reflectField(r14, r11)
            r12 = 9
            if (r5 == r12) goto L_0x0293
            r12 = 17
            if (r5 != r12) goto L_0x0298
        L_0x0293:
            r26 = r13
            r13 = 1
            goto L_0x0302
        L_0x0298:
            r12 = 27
            if (r5 == r12) goto L_0x02a0
            r12 = 49
            if (r5 != r12) goto L_0x02a4
        L_0x02a0:
            r26 = r13
            r13 = 1
            goto L_0x02f6
        L_0x02a4:
            r12 = 12
            if (r5 == r12) goto L_0x02da
            r12 = 30
            if (r5 == r12) goto L_0x02da
            r12 = 44
            if (r5 != r12) goto L_0x02b1
            goto L_0x02da
        L_0x02b1:
            r12 = 50
            if (r5 != r12) goto L_0x02d1
            int r12 = r21 + 1
            r16[r21] = r20
            int r21 = r20 / 3
            int r21 = r21 * 2
            int r26 = r15 + 2
            r1 = r9[r1]
            r10[r21] = r1
            r1 = r7 & 2048(0x800, float:2.87E-42)
            if (r1 == 0) goto L_0x02d5
            int r21 = r21 + 1
            int r1 = r15 + 3
            r15 = r9[r26]
            r10[r21] = r15
            r21 = r12
        L_0x02d1:
            r26 = r13
        L_0x02d3:
            r13 = 1
            goto L_0x030d
        L_0x02d5:
            r21 = r12
            r1 = r26
            goto L_0x02d1
        L_0x02da:
            androidx.datastore.preferences.protobuf.ProtoSyntax r12 = r31.getSyntax()
            r26 = r13
            androidx.datastore.preferences.protobuf.ProtoSyntax r13 = androidx.datastore.preferences.protobuf.ProtoSyntax.PROTO2
            if (r12 == r13) goto L_0x02e8
            r12 = r7 & 2048(0x800, float:2.87E-42)
            if (r12 == 0) goto L_0x02d3
        L_0x02e8:
            int r12 = r20 / 3
            int r12 = r12 * 2
            r13 = 1
            int r12 = r12 + r13
            int r15 = r15 + 2
            r1 = r9[r1]
            r10[r12] = r1
        L_0x02f4:
            r1 = r15
            goto L_0x030d
        L_0x02f6:
            int r12 = r20 / 3
            int r12 = r12 * 2
            int r12 = r12 + r13
            int r15 = r15 + 2
            r1 = r9[r1]
            r10[r12] = r1
            goto L_0x02f4
        L_0x0302:
            int r12 = r20 / 3
            int r12 = r12 * 2
            int r12 = r12 + r13
            java.lang.Class r15 = r11.getType()
            r10[r12] = r15
        L_0x030d:
            long r11 = r8.objectFieldOffset(r11)
            int r11 = (int) r11
            r12 = r7 & 4096(0x1000, float:5.74E-42)
            if (r12 == 0) goto L_0x0363
            r12 = 17
            if (r5 > r12) goto L_0x0363
            int r12 = r4 + 1
            char r4 = r0.charAt(r4)
            r15 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r15) goto L_0x0340
            r4 = r4 & 8191(0x1fff, float:1.1478E-41)
            r23 = 13
        L_0x0329:
            int r24 = r12 + 1
            char r12 = r0.charAt(r12)
            if (r12 < r15) goto L_0x033b
            r12 = r12 & 8191(0x1fff, float:1.1478E-41)
            int r12 = r12 << r23
            r4 = r4 | r12
            int r23 = r23 + 13
            r12 = r24
            goto L_0x0329
        L_0x033b:
            int r12 = r12 << r23
            r4 = r4 | r12
            r12 = r24
        L_0x0340:
            int r23 = r6 * 2
            int r24 = r4 / 32
            int r23 = r23 + r24
            r13 = r9[r23]
            boolean r15 = r13 instanceof java.lang.reflect.Field
            if (r15 == 0) goto L_0x0352
            java.lang.reflect.Field r13 = (java.lang.reflect.Field) r13
        L_0x034e:
            r15 = r0
            r23 = r1
            goto L_0x035b
        L_0x0352:
            java.lang.String r13 = (java.lang.String) r13
            java.lang.reflect.Field r13 = reflectField(r14, r13)
            r9[r23] = r13
            goto L_0x034e
        L_0x035b:
            long r0 = r8.objectFieldOffset(r13)
            int r0 = (int) r0
            int r4 = r4 % 32
            goto L_0x036b
        L_0x0363:
            r15 = r0
            r23 = r1
            r0 = 1048575(0xfffff, float:1.469367E-39)
            r12 = r4
            r4 = 0
        L_0x036b:
            r1 = 18
            if (r5 < r1) goto L_0x0379
            r1 = 49
            if (r5 > r1) goto L_0x0379
            int r1 = r22 + 1
            r16[r22] = r11
            r22 = r1
        L_0x0379:
            r1 = r11
        L_0x037a:
            int r11 = r20 + 1
            r2[r20] = r3
            int r3 = r20 + 2
            r13 = r7 & 512(0x200, float:7.175E-43)
            if (r13 == 0) goto L_0x0387
            r13 = 536870912(0x20000000, float:1.0842022E-19)
            goto L_0x0388
        L_0x0387:
            r13 = 0
        L_0x0388:
            r28 = r6
            r6 = r7 & 256(0x100, float:3.59E-43)
            if (r6 == 0) goto L_0x0391
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            goto L_0x0392
        L_0x0391:
            r6 = 0
        L_0x0392:
            r6 = r6 | r13
            r7 = r7 & 2048(0x800, float:2.87E-42)
            if (r7 == 0) goto L_0x039a
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x039b
        L_0x039a:
            r7 = 0
        L_0x039b:
            r6 = r6 | r7
            int r5 = r5 << 20
            r5 = r5 | r6
            r1 = r1 | r5
            r2[r11] = r1
            int r20 = r20 + 3
            int r1 = r4 << 20
            r0 = r0 | r1
            r2[r3] = r0
            r3 = r12
            r0 = r15
            r15 = r23
            r1 = r25
            r13 = r26
            r11 = r27
            r6 = r28
            r12 = r30
            r5 = 55296(0xd800, float:7.7486E-41)
            goto L_0x0177
        L_0x03bc:
            r30 = r12
            r26 = r13
            androidx.datastore.preferences.protobuf.MessageSchema r0 = new androidx.datastore.preferences.protobuf.MessageSchema
            androidx.datastore.preferences.protobuf.MessageLite r13 = r31.getDefaultInstance()
            androidx.datastore.preferences.protobuf.ProtoSyntax r14 = r31.getSyntax()
            r15 = 0
            r8 = r0
            r9 = r2
            r11 = r30
            r12 = r26
            r18 = r19
            r19 = r32
            r20 = r33
            r21 = r34
            r22 = r35
            r23 = r36
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.newSchemaForRawMessageInfo(androidx.datastore.preferences.protobuf.RawMessageInfo, androidx.datastore.preferences.protobuf.NewInstanceSchema, androidx.datastore.preferences.protobuf.ListFieldSchema, androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, androidx.datastore.preferences.protobuf.MapFieldSchema):androidx.datastore.preferences.protobuf.MessageSchema");
    }

    private static Field reflectField(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    static MessageSchema newSchemaForMessageInfo(StructuralMessageInfo structuralMessageInfo, NewInstanceSchema newInstanceSchema2, ListFieldSchema listFieldSchema2, UnknownFieldSchema unknownFieldSchema2, ExtensionSchema extensionSchema2, MapFieldSchema mapFieldSchema2) {
        throw null;
    }

    public Object newInstance() {
        return this.newInstanceSchema.newInstance(this.defaultInstance);
    }

    public boolean equals(Object obj, Object obj2) {
        int length = this.buffer.length;
        for (int i = 0; i < length; i += 3) {
            if (!equals(obj, obj2, i)) {
                return false;
            }
        }
        if (!this.unknownFieldSchema.getFromMessage(obj).equals(this.unknownFieldSchema.getFromMessage(obj2))) {
            return false;
        }
        if (this.hasExtensions) {
            return this.extensionSchema.getExtensions(obj).equals(this.extensionSchema.getExtensions(obj2));
        }
        return true;
    }

    private boolean equals(Object obj, Object obj2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (!arePresentForEquals(obj, obj2, i) || Double.doubleToLongBits(UnsafeUtil.getDouble(obj, offset)) != Double.doubleToLongBits(UnsafeUtil.getDouble(obj2, offset))) {
                    return false;
                }
                return true;
            case 1:
                if (!arePresentForEquals(obj, obj2, i) || Float.floatToIntBits(UnsafeUtil.getFloat(obj, offset)) != Float.floatToIntBits(UnsafeUtil.getFloat(obj2, offset))) {
                    return false;
                }
                return true;
            case 2:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getLong(obj, offset) != UnsafeUtil.getLong(obj2, offset)) {
                    return false;
                }
                return true;
            case 3:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getLong(obj, offset) != UnsafeUtil.getLong(obj2, offset)) {
                    return false;
                }
                return true;
            case 4:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getInt(obj, offset) != UnsafeUtil.getInt(obj2, offset)) {
                    return false;
                }
                return true;
            case 5:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getLong(obj, offset) != UnsafeUtil.getLong(obj2, offset)) {
                    return false;
                }
                return true;
            case 6:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getInt(obj, offset) != UnsafeUtil.getInt(obj2, offset)) {
                    return false;
                }
                return true;
            case 7:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getBoolean(obj, offset) != UnsafeUtil.getBoolean(obj2, offset)) {
                    return false;
                }
                return true;
            case 8:
                if (!arePresentForEquals(obj, obj2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset))) {
                    return false;
                }
                return true;
            case 9:
                if (!arePresentForEquals(obj, obj2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset))) {
                    return false;
                }
                return true;
            case 10:
                if (!arePresentForEquals(obj, obj2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset))) {
                    return false;
                }
                return true;
            case 11:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getInt(obj, offset) != UnsafeUtil.getInt(obj2, offset)) {
                    return false;
                }
                return true;
            case 12:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getInt(obj, offset) != UnsafeUtil.getInt(obj2, offset)) {
                    return false;
                }
                return true;
            case 13:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getInt(obj, offset) != UnsafeUtil.getInt(obj2, offset)) {
                    return false;
                }
                return true;
            case 14:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getLong(obj, offset) != UnsafeUtil.getLong(obj2, offset)) {
                    return false;
                }
                return true;
            case 15:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getInt(obj, offset) != UnsafeUtil.getInt(obj2, offset)) {
                    return false;
                }
                return true;
            case 16:
                if (!arePresentForEquals(obj, obj2, i) || UnsafeUtil.getLong(obj, offset) != UnsafeUtil.getLong(obj2, offset)) {
                    return false;
                }
                return true;
            case 17:
                if (!arePresentForEquals(obj, obj2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset))) {
                    return false;
                }
                return true;
            case 18:
            case 19:
            case 20:
            case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE /*21*/:
            case 22:
            case ConnectionResult.API_DISABLED /*23*/:
            case ConnectionResult.API_DISABLED_FOR_CONNECTION /*24*/:
            case 25:
            case 26:
            case 27:
            case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION /*28*/:
            case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION /*29*/:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 50:
                return SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset));
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /*65*/:
            case 66:
            case 67:
            case 68:
                if (!isOneofCaseEqual(obj, obj2, i) || !SchemaUtil.safeEquals(UnsafeUtil.getObject(obj, offset), UnsafeUtil.getObject(obj2, offset))) {
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016b, code lost:
        r2 = (r2 * 53) + r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002e, code lost:
        r2 = r2 + r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0229, code lost:
        r1 = r1 + 3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int hashCode(java.lang.Object r9) {
        /*
            r8 = this;
            int[] r0 = r8.buffer
            int r0 = r0.length
            r1 = 0
            r2 = r1
        L_0x0005:
            if (r1 >= r0) goto L_0x022d
            int r3 = r8.typeAndOffsetAt(r1)
            int r4 = r8.numberAt(r1)
            long r5 = offset(r3)
            int r3 = type(r3)
            r7 = 37
            switch(r3) {
                case 0: goto L_0x0219;
                case 1: goto L_0x020d;
                case 2: goto L_0x0201;
                case 3: goto L_0x01f5;
                case 4: goto L_0x01ed;
                case 5: goto L_0x01e1;
                case 6: goto L_0x01d9;
                case 7: goto L_0x01cd;
                case 8: goto L_0x01bf;
                case 9: goto L_0x01b4;
                case 10: goto L_0x01a8;
                case 11: goto L_0x01a0;
                case 12: goto L_0x0198;
                case 13: goto L_0x0190;
                case 14: goto L_0x0184;
                case 15: goto L_0x017c;
                case 16: goto L_0x0170;
                case 17: goto L_0x0161;
                case 18: goto L_0x0155;
                case 19: goto L_0x0155;
                case 20: goto L_0x0155;
                case 21: goto L_0x0155;
                case 22: goto L_0x0155;
                case 23: goto L_0x0155;
                case 24: goto L_0x0155;
                case 25: goto L_0x0155;
                case 26: goto L_0x0155;
                case 27: goto L_0x0155;
                case 28: goto L_0x0155;
                case 29: goto L_0x0155;
                case 30: goto L_0x0155;
                case 31: goto L_0x0155;
                case 32: goto L_0x0155;
                case 33: goto L_0x0155;
                case 34: goto L_0x0155;
                case 35: goto L_0x0155;
                case 36: goto L_0x0155;
                case 37: goto L_0x0155;
                case 38: goto L_0x0155;
                case 39: goto L_0x0155;
                case 40: goto L_0x0155;
                case 41: goto L_0x0155;
                case 42: goto L_0x0155;
                case 43: goto L_0x0155;
                case 44: goto L_0x0155;
                case 45: goto L_0x0155;
                case 46: goto L_0x0155;
                case 47: goto L_0x0155;
                case 48: goto L_0x0155;
                case 49: goto L_0x0155;
                case 50: goto L_0x0149;
                case 51: goto L_0x0133;
                case 52: goto L_0x0121;
                case 53: goto L_0x010f;
                case 54: goto L_0x00fd;
                case 55: goto L_0x00ef;
                case 56: goto L_0x00dd;
                case 57: goto L_0x00cf;
                case 58: goto L_0x00bd;
                case 59: goto L_0x00a9;
                case 60: goto L_0x0098;
                case 61: goto L_0x0087;
                case 62: goto L_0x007a;
                case 63: goto L_0x006d;
                case 64: goto L_0x0060;
                case 65: goto L_0x004f;
                case 66: goto L_0x0042;
                case 67: goto L_0x0031;
                case 68: goto L_0x001e;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0229
        L_0x001e:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
        L_0x002e:
            int r2 = r2 + r3
            goto L_0x0229
        L_0x0031:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0042:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x002e
        L_0x004f:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0060:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x002e
        L_0x006d:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x002e
        L_0x007a:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x002e
        L_0x0087:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x0098:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            int r2 = r2 * 53
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x00a9:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x00bd:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            boolean r3 = oneofBooleanAt(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashBoolean(r3)
            goto L_0x002e
        L_0x00cf:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x002e
        L_0x00dd:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x00ef:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            int r3 = oneofIntAt(r9, r5)
            goto L_0x002e
        L_0x00fd:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x010f:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            long r3 = oneofLongAt(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0121:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            float r3 = oneofFloatAt(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x002e
        L_0x0133:
            boolean r3 = r8.isOneofPresent(r9, r4, r1)
            if (r3 == 0) goto L_0x0229
            int r2 = r2 * 53
            double r3 = oneofDoubleAt(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0149:
            int r2 = r2 * 53
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x0155:
            int r2 = r2 * 53
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x0161:
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            if (r3 == 0) goto L_0x016b
            int r7 = r3.hashCode()
        L_0x016b:
            int r2 = r2 * 53
            int r2 = r2 + r7
            goto L_0x0229
        L_0x0170:
            int r2 = r2 * 53
            long r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getLong(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x017c:
            int r2 = r2 * 53
            int r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getInt(r9, r5)
            goto L_0x002e
        L_0x0184:
            int r2 = r2 * 53
            long r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getLong(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0190:
            int r2 = r2 * 53
            int r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getInt(r9, r5)
            goto L_0x002e
        L_0x0198:
            int r2 = r2 * 53
            int r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getInt(r9, r5)
            goto L_0x002e
        L_0x01a0:
            int r2 = r2 * 53
            int r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getInt(r9, r5)
            goto L_0x002e
        L_0x01a8:
            int r2 = r2 * 53
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x01b4:
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            if (r3 == 0) goto L_0x016b
            int r7 = r3.hashCode()
            goto L_0x016b
        L_0x01bf:
            int r2 = r2 * 53
            java.lang.Object r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r9, r5)
            java.lang.String r3 = (java.lang.String) r3
            int r3 = r3.hashCode()
            goto L_0x002e
        L_0x01cd:
            int r2 = r2 * 53
            boolean r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getBoolean(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashBoolean(r3)
            goto L_0x002e
        L_0x01d9:
            int r2 = r2 * 53
            int r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getInt(r9, r5)
            goto L_0x002e
        L_0x01e1:
            int r2 = r2 * 53
            long r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getLong(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x01ed:
            int r2 = r2 * 53
            int r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getInt(r9, r5)
            goto L_0x002e
        L_0x01f5:
            int r2 = r2 * 53
            long r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getLong(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0201:
            int r2 = r2 * 53
            long r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getLong(r9, r5)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x020d:
            int r2 = r2 * 53
            float r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getFloat(r9, r5)
            int r3 = java.lang.Float.floatToIntBits(r3)
            goto L_0x002e
        L_0x0219:
            int r2 = r2 * 53
            double r3 = androidx.datastore.preferences.protobuf.UnsafeUtil.getDouble(r9, r5)
            long r3 = java.lang.Double.doubleToLongBits(r3)
            int r3 = androidx.datastore.preferences.protobuf.Internal.hashLong(r3)
            goto L_0x002e
        L_0x0229:
            int r1 = r1 + 3
            goto L_0x0005
        L_0x022d:
            int r2 = r2 * 53
            androidx.datastore.preferences.protobuf.UnknownFieldSchema r0 = r8.unknownFieldSchema
            java.lang.Object r0 = r0.getFromMessage(r9)
            int r0 = r0.hashCode()
            int r2 = r2 + r0
            boolean r0 = r8.hasExtensions
            if (r0 == 0) goto L_0x024b
            int r2 = r2 * 53
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r8.extensionSchema
            androidx.datastore.preferences.protobuf.FieldSet r9 = r0.getExtensions(r9)
            int r9 = r9.hashCode()
            int r2 = r2 + r9
        L_0x024b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.hashCode(java.lang.Object):int");
    }

    public void mergeFrom(Object obj, Object obj2) {
        checkMutable(obj);
        obj2.getClass();
        for (int i = 0; i < this.buffer.length; i += 3) {
            mergeSingleField(obj, obj2, i);
        }
        SchemaUtil.mergeUnknownFields(this.unknownFieldSchema, obj, obj2);
        if (this.hasExtensions) {
            SchemaUtil.mergeExtensions(this.extensionSchema, obj, obj2);
        }
    }

    private void mergeSingleField(Object obj, Object obj2, int i) {
        int typeAndOffsetAt = typeAndOffsetAt(i);
        long offset = offset(typeAndOffsetAt);
        int numberAt = numberAt(i);
        switch (type(typeAndOffsetAt)) {
            case 0:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putDouble(obj, offset, UnsafeUtil.getDouble(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 1:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putFloat(obj, offset, UnsafeUtil.getFloat(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 2:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 3:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 4:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 5:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 6:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 7:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putBoolean(obj, offset, UnsafeUtil.getBoolean(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 8:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 9:
                mergeMessage(obj, obj2, i);
                return;
            case 10:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 11:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 12:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 13:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 14:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 15:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putInt(obj, offset, UnsafeUtil.getInt(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 16:
                if (isFieldPresent(obj2, i)) {
                    UnsafeUtil.putLong(obj, offset, UnsafeUtil.getLong(obj2, offset));
                    setFieldPresent(obj, i);
                    return;
                }
                return;
            case 17:
                mergeMessage(obj, obj2, i);
                return;
            case 18:
            case 19:
            case 20:
            case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE /*21*/:
            case 22:
            case ConnectionResult.API_DISABLED /*23*/:
            case ConnectionResult.API_DISABLED_FOR_CONNECTION /*24*/:
            case 25:
            case 26:
            case 27:
            case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION /*28*/:
            case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION /*29*/:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
                this.listFieldSchema.mergeListsAt(obj, obj2, offset);
                return;
            case 50:
                SchemaUtil.mergeMap(this.mapFieldSchema, obj, obj2, offset);
                return;
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
                if (isOneofPresent(obj2, numberAt, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setOneofPresent(obj, numberAt, i);
                    return;
                }
                return;
            case 60:
                mergeOneofMessage(obj, obj2, i);
                return;
            case 61:
            case 62:
            case 63:
            case 64:
            case RegisterRequest.U2F_V1_CHALLENGE_BYTE_LENGTH /*65*/:
            case 66:
            case 67:
                if (isOneofPresent(obj2, numberAt, i)) {
                    UnsafeUtil.putObject(obj, offset, UnsafeUtil.getObject(obj2, offset));
                    setOneofPresent(obj, numberAt, i);
                    return;
                }
                return;
            case 68:
                mergeOneofMessage(obj, obj2, i);
                return;
            default:
                return;
        }
    }

    private void mergeMessage(Object obj, Object obj2, int i) {
        if (isFieldPresent(obj2, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(obj2, offset);
            if (object != null) {
                Schema messageFieldSchema = getMessageFieldSchema(i);
                if (!isFieldPresent(obj, i)) {
                    if (!isMutable(object)) {
                        unsafe.putObject(obj, offset, object);
                    } else {
                        Object newInstance = messageFieldSchema.newInstance();
                        messageFieldSchema.mergeFrom(newInstance, object);
                        unsafe.putObject(obj, offset, newInstance);
                    }
                    setFieldPresent(obj, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, offset);
                if (!isMutable(object2)) {
                    Object newInstance2 = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance2, object2);
                    unsafe.putObject(obj, offset, newInstance2);
                    object2 = newInstance2;
                }
                messageFieldSchema.mergeFrom(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + obj2);
        }
    }

    private void mergeOneofMessage(Object obj, Object obj2, int i) {
        int numberAt = numberAt(i);
        if (isOneofPresent(obj2, numberAt, i)) {
            long offset = offset(typeAndOffsetAt(i));
            Unsafe unsafe = UNSAFE;
            Object object = unsafe.getObject(obj2, offset);
            if (object != null) {
                Schema messageFieldSchema = getMessageFieldSchema(i);
                if (!isOneofPresent(obj, numberAt, i)) {
                    if (!isMutable(object)) {
                        unsafe.putObject(obj, offset, object);
                    } else {
                        Object newInstance = messageFieldSchema.newInstance();
                        messageFieldSchema.mergeFrom(newInstance, object);
                        unsafe.putObject(obj, offset, newInstance);
                    }
                    setOneofPresent(obj, numberAt, i);
                    return;
                }
                Object object2 = unsafe.getObject(obj, offset);
                if (!isMutable(object2)) {
                    Object newInstance2 = messageFieldSchema.newInstance();
                    messageFieldSchema.mergeFrom(newInstance2, object2);
                    unsafe.putObject(obj, offset, newInstance2);
                    object2 = newInstance2;
                }
                messageFieldSchema.mergeFrom(object2, object);
                return;
            }
            throw new IllegalStateException("Source subfield " + numberAt(i) + " is present but null: " + obj2);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x034d, code lost:
        r12 = r12 + r0;
        r15 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0602, code lost:
        r11 = r11 + 3;
        r0 = r14;
        r1 = r16;
        r10 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b0, code lost:
        r12 = r12 + r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01bc, code lost:
        r1 = (r1 + r2) + r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getSerializedSize(java.lang.Object r19) {
        /*
            r18 = this;
            r6 = r18
            r7 = r19
            sun.misc.Unsafe r8 = UNSAFE
            r10 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r10
            r1 = 0
            r11 = 0
            r12 = 0
        L_0x000d:
            int[] r2 = r6.buffer
            int r2 = r2.length
            if (r11 >= r2) goto L_0x060c
            int r2 = r6.typeAndOffsetAt(r11)
            int r3 = type(r2)
            int r13 = r6.numberAt(r11)
            int[] r4 = r6.buffer
            int r5 = r11 + 2
            r4 = r4[r5]
            r5 = r4 & r10
            r14 = 17
            r15 = 1
            if (r3 > r14) goto L_0x0042
            if (r5 == r0) goto L_0x0038
            if (r5 != r10) goto L_0x0031
            r1 = 0
            goto L_0x0037
        L_0x0031:
            long r0 = (long) r5
            int r0 = r8.getInt(r7, r0)
            r1 = r0
        L_0x0037:
            r0 = r5
        L_0x0038:
            int r4 = r4 >>> 20
            int r4 = r15 << r4
            r14 = r0
            r16 = r1
            r17 = r4
            goto L_0x0047
        L_0x0042:
            r14 = r0
            r16 = r1
            r17 = 0
        L_0x0047:
            long r1 = offset(r2)
            androidx.datastore.preferences.protobuf.FieldType r0 = androidx.datastore.preferences.protobuf.FieldType.DOUBLE_LIST_PACKED
            int r0 = r0.id()
            if (r3 < r0) goto L_0x005c
            androidx.datastore.preferences.protobuf.FieldType r0 = androidx.datastore.preferences.protobuf.FieldType.SINT64_LIST_PACKED
            int r0 = r0.id()
            if (r3 > r0) goto L_0x005c
            goto L_0x005d
        L_0x005c:
            r5 = 0
        L_0x005d:
            r4 = 0
            r9 = 0
            switch(r3) {
                case 0: goto L_0x05e9;
                case 1: goto L_0x05d1;
                case 2: goto L_0x05b6;
                case 3: goto L_0x059b;
                case 4: goto L_0x0580;
                case 5: goto L_0x056a;
                case 6: goto L_0x0552;
                case 7: goto L_0x053c;
                case 8: goto L_0x0513;
                case 9: goto L_0x04f4;
                case 10: goto L_0x04d7;
                case 11: goto L_0x04bc;
                case 12: goto L_0x04a1;
                case 13: goto L_0x048a;
                case 14: goto L_0x0474;
                case 15: goto L_0x0459;
                case 16: goto L_0x043e;
                case 17: goto L_0x041d;
                case 18: goto L_0x0410;
                case 19: goto L_0x0403;
                case 20: goto L_0x03f6;
                case 21: goto L_0x03e9;
                case 22: goto L_0x03dc;
                case 23: goto L_0x03cf;
                case 24: goto L_0x03c2;
                case 25: goto L_0x03b6;
                case 26: goto L_0x03aa;
                case 27: goto L_0x039a;
                case 28: goto L_0x038e;
                case 29: goto L_0x0381;
                case 30: goto L_0x0375;
                case 31: goto L_0x0369;
                case 32: goto L_0x035d;
                case 33: goto L_0x0351;
                case 34: goto L_0x0342;
                case 35: goto L_0x0324;
                case 36: goto L_0x0306;
                case 37: goto L_0x02e8;
                case 38: goto L_0x02ca;
                case 39: goto L_0x02ac;
                case 40: goto L_0x028e;
                case 41: goto L_0x0270;
                case 42: goto L_0x0252;
                case 43: goto L_0x0234;
                case 44: goto L_0x0217;
                case 45: goto L_0x01fa;
                case 46: goto L_0x01dd;
                case 47: goto L_0x01c0;
                case 48: goto L_0x01a0;
                case 49: goto L_0x0190;
                case 50: goto L_0x0180;
                case 51: goto L_0x0172;
                case 52: goto L_0x0166;
                case 53: goto L_0x0156;
                case 54: goto L_0x0146;
                case 55: goto L_0x0136;
                case 56: goto L_0x012a;
                case 57: goto L_0x011e;
                case 58: goto L_0x0112;
                case 59: goto L_0x00f4;
                case 60: goto L_0x00e1;
                case 61: goto L_0x00d0;
                case 62: goto L_0x00c1;
                case 63: goto L_0x00b2;
                case 64: goto L_0x00a5;
                case 65: goto L_0x009a;
                case 66: goto L_0x008b;
                case 67: goto L_0x007c;
                case 68: goto L_0x0064;
                default: goto L_0x0063;
            }
        L_0x0063:
            goto L_0x0079
        L_0x0064:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r1)
            androidx.datastore.preferences.protobuf.MessageLite r0 = (androidx.datastore.preferences.protobuf.MessageLite) r0
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeGroupSize(r13, r0, r1)
        L_0x0078:
            int r12 = r12 + r0
        L_0x0079:
            r15 = 0
            goto L_0x0602
        L_0x007c:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            long r0 = oneofLongAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSInt64Size(r13, r0)
            goto L_0x0078
        L_0x008b:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = oneofIntAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSInt32Size(r13, r0)
            goto L_0x0078
        L_0x009a:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSFixed64Size(r13, r9)
            goto L_0x0078
        L_0x00a5:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            r0 = 0
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSFixed32Size(r13, r0)
        L_0x00b0:
            int r12 = r12 + r1
            goto L_0x0079
        L_0x00b2:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = oneofIntAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeEnumSize(r13, r0)
            goto L_0x0078
        L_0x00c1:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = oneofIntAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32Size(r13, r0)
            goto L_0x0078
        L_0x00d0:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r1)
            androidx.datastore.preferences.protobuf.ByteString r0 = (androidx.datastore.preferences.protobuf.ByteString) r0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x0078
        L_0x00e1:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r1)
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeMessage(r13, r0, r1)
            goto L_0x0078
        L_0x00f4:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r1)
            boolean r1 = r0 instanceof androidx.datastore.preferences.protobuf.ByteString
            if (r1 == 0) goto L_0x010a
            androidx.datastore.preferences.protobuf.ByteString r0 = (androidx.datastore.preferences.protobuf.ByteString) r0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x0078
        L_0x010a:
            java.lang.String r0 = (java.lang.String) r0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeStringSize(r13, r0)
            goto L_0x0078
        L_0x0112:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeBoolSize(r13, r15)
            goto L_0x0078
        L_0x011e:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            r0 = 0
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeFixed32Size(r13, r0)
            goto L_0x00b0
        L_0x012a:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeFixed64Size(r13, r9)
            goto L_0x0078
        L_0x0136:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = oneofIntAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeInt32Size(r13, r0)
            goto L_0x0078
        L_0x0146:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            long r0 = oneofLongAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt64Size(r13, r0)
            goto L_0x0078
        L_0x0156:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            long r0 = oneofLongAt(r7, r1)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeInt64Size(r13, r0)
            goto L_0x0078
        L_0x0166:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeFloatSize(r13, r4)
            goto L_0x0078
        L_0x0172:
            boolean r0 = r6.isOneofPresent(r7, r13, r11)
            if (r0 == 0) goto L_0x0079
            r0 = 0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeDoubleSize(r13, r0)
            goto L_0x0078
        L_0x0180:
            androidx.datastore.preferences.protobuf.MapFieldSchema r0 = r6.mapFieldSchema
            java.lang.Object r1 = r8.getObject(r7, r1)
            java.lang.Object r2 = r6.getMapFieldDefaultEntry(r11)
            int r0 = r0.getSerializedSize(r13, r1, r2)
            goto L_0x0078
        L_0x0190:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeGroupList(r13, r0, r1)
            goto L_0x0078
        L_0x01a0:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeSInt64ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x01b4
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x01b4:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
        L_0x01bc:
            int r1 = r1 + r2
            int r1 = r1 + r0
            goto L_0x00b0
        L_0x01c0:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeSInt32ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x01d4
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x01d4:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x01dd:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed64ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x01f1
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x01f1:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x01fa:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed32ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x020e
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x020e:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0217:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeEnumListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x022b
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x022b:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0234:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeUInt32ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x0248
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x0248:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0252:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeBoolListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x0266
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x0266:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0270:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed32ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x0284
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x0284:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x028e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed64ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x02a2
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x02a2:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x02ac:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeInt32ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x02c0
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x02c0:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x02ca:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeUInt64ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x02de
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x02de:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x02e8:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeInt64ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x02fc
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x02fc:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0306:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed32ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x031a
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x031a:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0324:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed64ListNoTag(r0)
            if (r0 <= 0) goto L_0x0079
            boolean r1 = r6.useCachedSizeField
            if (r1 == 0) goto L_0x0338
            long r1 = (long) r5
            r8.putInt(r7, r1, r0)
        L_0x0338:
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeTagSize(r13)
            int r2 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32SizeNoTag(r0)
            goto L_0x01bc
        L_0x0342:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            r3 = 0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeSInt64List(r13, r0, r3)
        L_0x034d:
            int r12 = r12 + r0
            r15 = r3
            goto L_0x0602
        L_0x0351:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeSInt32List(r13, r0, r3)
            goto L_0x034d
        L_0x035d:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed64List(r13, r0, r3)
            goto L_0x034d
        L_0x0369:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed32List(r13, r0, r3)
            goto L_0x034d
        L_0x0375:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeEnumList(r13, r0, r3)
            goto L_0x034d
        L_0x0381:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeUInt32List(r13, r0, r3)
            goto L_0x0078
        L_0x038e:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeByteStringList(r13, r0)
            goto L_0x0078
        L_0x039a:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeMessageList(r13, r0, r1)
            goto L_0x0078
        L_0x03aa:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeStringList(r13, r0)
            goto L_0x0078
        L_0x03b6:
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            r3 = 0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeBoolList(r13, r0, r3)
            goto L_0x034d
        L_0x03c2:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed32List(r13, r0, r3)
            goto L_0x034d
        L_0x03cf:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed64List(r13, r0, r3)
            goto L_0x034d
        L_0x03dc:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeInt32List(r13, r0, r3)
            goto L_0x034d
        L_0x03e9:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeUInt64List(r13, r0, r3)
            goto L_0x034d
        L_0x03f6:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeInt64List(r13, r0, r3)
            goto L_0x034d
        L_0x0403:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed32List(r13, r0, r3)
            goto L_0x034d
        L_0x0410:
            r3 = 0
            java.lang.Object r0 = r8.getObject(r7, r1)
            java.util.List r0 = (java.util.List) r0
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeFixed64List(r13, r0, r3)
            goto L_0x0078
        L_0x041d:
            r0 = r18
            r9 = r1
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r9)
            androidx.datastore.preferences.protobuf.MessageLite r0 = (androidx.datastore.preferences.protobuf.MessageLite) r0
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeGroupSize(r13, r0, r1)
            goto L_0x0078
        L_0x043e:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            long r0 = r8.getLong(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSInt64Size(r13, r0)
            goto L_0x0078
        L_0x0459:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            int r0 = r8.getInt(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSInt32Size(r13, r0)
            goto L_0x0078
        L_0x0474:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSFixed64Size(r13, r9)
            goto L_0x0078
        L_0x048a:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            r0 = 0
            int r1 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeSFixed32Size(r13, r0)
            goto L_0x00b0
        L_0x04a1:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            int r0 = r8.getInt(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeEnumSize(r13, r0)
            goto L_0x0078
        L_0x04bc:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            int r0 = r8.getInt(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt32Size(r13, r0)
            goto L_0x0078
        L_0x04d7:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r9)
            androidx.datastore.preferences.protobuf.ByteString r0 = (androidx.datastore.preferences.protobuf.ByteString) r0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x0078
        L_0x04f4:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r9)
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r11)
            int r0 = androidx.datastore.preferences.protobuf.SchemaUtil.computeSizeMessage(r13, r0, r1)
            goto L_0x0078
        L_0x0513:
            r9 = r1
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            java.lang.Object r0 = r8.getObject(r7, r9)
            boolean r1 = r0 instanceof androidx.datastore.preferences.protobuf.ByteString
            if (r1 == 0) goto L_0x0534
            androidx.datastore.preferences.protobuf.ByteString r0 = (androidx.datastore.preferences.protobuf.ByteString) r0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeBytesSize(r13, r0)
            goto L_0x0078
        L_0x0534:
            java.lang.String r0 = (java.lang.String) r0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeStringSize(r13, r0)
            goto L_0x0078
        L_0x053c:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeBoolSize(r13, r15)
            goto L_0x0078
        L_0x0552:
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0079
            r15 = 0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeFixed32Size(r13, r15)
        L_0x0567:
            int r12 = r12 + r0
            goto L_0x0602
        L_0x056a:
            r15 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0602
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeFixed64Size(r13, r9)
            goto L_0x0567
        L_0x0580:
            r9 = r1
            r15 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0602
            int r0 = r8.getInt(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeInt32Size(r13, r0)
            goto L_0x0567
        L_0x059b:
            r9 = r1
            r15 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0602
            long r0 = r8.getLong(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeUInt64Size(r13, r0)
            goto L_0x0567
        L_0x05b6:
            r9 = r1
            r15 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0602
            long r0 = r8.getLong(r7, r9)
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeInt64Size(r13, r0)
            goto L_0x0567
        L_0x05d1:
            r15 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r9 = r4
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0602
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeFloatSize(r13, r9)
            goto L_0x0567
        L_0x05e9:
            r15 = 0
            r0 = r18
            r1 = r19
            r2 = r11
            r3 = r14
            r4 = r16
            r5 = r17
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0602
            r0 = 0
            int r0 = androidx.datastore.preferences.protobuf.CodedOutputStream.computeDoubleSize(r13, r0)
            goto L_0x0567
        L_0x0602:
            int r11 = r11 + 3
            r0 = r14
            r1 = r16
            r10 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x000d
        L_0x060c:
            androidx.datastore.preferences.protobuf.UnknownFieldSchema r0 = r6.unknownFieldSchema
            int r0 = r6.getUnknownFieldsSerializedSize(r0, r7)
            int r12 = r12 + r0
            boolean r0 = r6.hasExtensions
            if (r0 == 0) goto L_0x0622
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r6.extensionSchema
            androidx.datastore.preferences.protobuf.FieldSet r0 = r0.getExtensions(r7)
            int r0 = r0.getSerializedSize()
            int r12 = r12 + r0
        L_0x0622:
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.getSerializedSize(java.lang.Object):int");
    }

    private int getUnknownFieldsSerializedSize(UnknownFieldSchema unknownFieldSchema2, Object obj) {
        return unknownFieldSchema2.getSerializedSize(unknownFieldSchema2.getFromMessage(obj));
    }

    public void writeTo(Object obj, Writer writer) {
        if (writer.fieldOrder() == Writer.FieldOrder.DESCENDING) {
            writeFieldsInDescendingOrder(obj, writer);
        } else {
            writeFieldsInAscendingOrder(obj, writer);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02ba, code lost:
        r19 = r4;
        r20 = r11;
        r16 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0617, code lost:
        r15 = r15 + 3;
        r0 = r9;
        r1 = r16;
        r2 = r17;
        r11 = r20;
        r13 = 1048575;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0098, code lost:
        r20 = r11;
        r16 = r14;
     */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x0629  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0031  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeFieldsInAscendingOrder(java.lang.Object r22, androidx.datastore.preferences.protobuf.Writer r23) {
        /*
            r21 = this;
            r6 = r21
            r7 = r22
            r8 = r23
            boolean r0 = r6.hasExtensions
            if (r0 == 0) goto L_0x0022
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r6.extensionSchema
            androidx.datastore.preferences.protobuf.FieldSet r0 = r0.getExtensions(r7)
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0022
            java.util.Iterator r0 = r0.iterator()
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r10 = r0
            goto L_0x0024
        L_0x0022:
            r1 = 0
            r10 = 0
        L_0x0024:
            int[] r0 = r6.buffer
            int r11 = r0.length
            sun.misc.Unsafe r12 = UNSAFE
            r13 = 1048575(0xfffff, float:1.469367E-39)
            r0 = r13
            r2 = 0
            r15 = 0
        L_0x002f:
            if (r15 >= r11) goto L_0x0625
            int r3 = r6.typeAndOffsetAt(r15)
            int r5 = r6.numberAt(r15)
            int r4 = type(r3)
            r9 = 17
            if (r4 > r9) goto L_0x006a
            int[] r9 = r6.buffer
            int r16 = r15 + 2
            r9 = r9[r16]
            r14 = r9 & r13
            if (r14 == r0) goto L_0x005b
            if (r14 != r13) goto L_0x0051
            r17 = r1
            r2 = 0
            goto L_0x0059
        L_0x0051:
            r17 = r1
            long r0 = (long) r14
            int r0 = r12.getInt(r7, r0)
            r2 = r0
        L_0x0059:
            r0 = r14
            goto L_0x005d
        L_0x005b:
            r17 = r1
        L_0x005d:
            int r1 = r9 >>> 20
            r9 = 1
            int r1 = r9 << r1
            r9 = r0
            r18 = r1
            r14 = r17
        L_0x0067:
            r17 = r2
            goto L_0x0072
        L_0x006a:
            r17 = r1
            r9 = r0
            r14 = r17
            r18 = 0
            goto L_0x0067
        L_0x0072:
            if (r14 == 0) goto L_0x0091
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r6.extensionSchema
            int r0 = r0.extensionNumber(r14)
            if (r0 > r5) goto L_0x0091
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r6.extensionSchema
            r0.serializeExtension(r8, r14)
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x008f
            java.lang.Object r0 = r10.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r14 = r0
            goto L_0x0072
        L_0x008f:
            r14 = 0
            goto L_0x0072
        L_0x0091:
            long r2 = offset(r3)
            switch(r4) {
                case 0: goto L_0x05f8;
                case 1: goto L_0x05d8;
                case 2: goto L_0x05b8;
                case 3: goto L_0x0597;
                case 4: goto L_0x0576;
                case 5: goto L_0x0555;
                case 6: goto L_0x0534;
                case 7: goto L_0x0513;
                case 8: goto L_0x04f2;
                case 9: goto L_0x04cd;
                case 10: goto L_0x04aa;
                case 11: goto L_0x0489;
                case 12: goto L_0x0468;
                case 13: goto L_0x0447;
                case 14: goto L_0x0426;
                case 15: goto L_0x0405;
                case 16: goto L_0x03e4;
                case 17: goto L_0x03be;
                case 18: goto L_0x03ae;
                case 19: goto L_0x039e;
                case 20: goto L_0x038e;
                case 21: goto L_0x037e;
                case 22: goto L_0x036e;
                case 23: goto L_0x035e;
                case 24: goto L_0x034e;
                case 25: goto L_0x033e;
                case 26: goto L_0x032f;
                case 27: goto L_0x031c;
                case 28: goto L_0x030d;
                case 29: goto L_0x02fe;
                case 30: goto L_0x02ef;
                case 31: goto L_0x02e0;
                case 32: goto L_0x02d1;
                case 33: goto L_0x02c2;
                case 34: goto L_0x02ac;
                case 35: goto L_0x029c;
                case 36: goto L_0x028c;
                case 37: goto L_0x027c;
                case 38: goto L_0x026c;
                case 39: goto L_0x025c;
                case 40: goto L_0x024c;
                case 41: goto L_0x023c;
                case 42: goto L_0x022c;
                case 43: goto L_0x021c;
                case 44: goto L_0x020c;
                case 45: goto L_0x01fc;
                case 46: goto L_0x01ec;
                case 47: goto L_0x01dc;
                case 48: goto L_0x01cc;
                case 49: goto L_0x01b9;
                case 50: goto L_0x01b0;
                case 51: goto L_0x01a1;
                case 52: goto L_0x0192;
                case 53: goto L_0x0183;
                case 54: goto L_0x0174;
                case 55: goto L_0x0165;
                case 56: goto L_0x0156;
                case 57: goto L_0x0147;
                case 58: goto L_0x0138;
                case 59: goto L_0x0129;
                case 60: goto L_0x0116;
                case 61: goto L_0x0106;
                case 62: goto L_0x00f8;
                case 63: goto L_0x00ea;
                case 64: goto L_0x00dc;
                case 65: goto L_0x00ce;
                case 66: goto L_0x00c0;
                case 67: goto L_0x00b2;
                case 68: goto L_0x00a0;
                default: goto L_0x0098;
            }
        L_0x0098:
            r20 = r11
            r16 = r14
            r19 = 0
            goto L_0x0617
        L_0x00a0:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            java.lang.Object r0 = r12.getObject(r7, r2)
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeGroup(r5, r0, r1)
            goto L_0x0098
        L_0x00b2:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            long r0 = oneofLongAt(r7, r2)
            r8.writeSInt64(r5, r0)
            goto L_0x0098
        L_0x00c0:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            int r0 = oneofIntAt(r7, r2)
            r8.writeSInt32(r5, r0)
            goto L_0x0098
        L_0x00ce:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            long r0 = oneofLongAt(r7, r2)
            r8.writeSFixed64(r5, r0)
            goto L_0x0098
        L_0x00dc:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            int r0 = oneofIntAt(r7, r2)
            r8.writeSFixed32(r5, r0)
            goto L_0x0098
        L_0x00ea:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            int r0 = oneofIntAt(r7, r2)
            r8.writeEnum(r5, r0)
            goto L_0x0098
        L_0x00f8:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            int r0 = oneofIntAt(r7, r2)
            r8.writeUInt32(r5, r0)
            goto L_0x0098
        L_0x0106:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            java.lang.Object r0 = r12.getObject(r7, r2)
            androidx.datastore.preferences.protobuf.ByteString r0 = (androidx.datastore.preferences.protobuf.ByteString) r0
            r8.writeBytes(r5, r0)
            goto L_0x0098
        L_0x0116:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            java.lang.Object r0 = r12.getObject(r7, r2)
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeMessage(r5, r0, r1)
            goto L_0x0098
        L_0x0129:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            java.lang.Object r0 = r12.getObject(r7, r2)
            r6.writeString(r5, r0, r8)
            goto L_0x0098
        L_0x0138:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            boolean r0 = oneofBooleanAt(r7, r2)
            r8.writeBool(r5, r0)
            goto L_0x0098
        L_0x0147:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            int r0 = oneofIntAt(r7, r2)
            r8.writeFixed32(r5, r0)
            goto L_0x0098
        L_0x0156:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            long r0 = oneofLongAt(r7, r2)
            r8.writeFixed64(r5, r0)
            goto L_0x0098
        L_0x0165:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            int r0 = oneofIntAt(r7, r2)
            r8.writeInt32(r5, r0)
            goto L_0x0098
        L_0x0174:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            long r0 = oneofLongAt(r7, r2)
            r8.writeUInt64(r5, r0)
            goto L_0x0098
        L_0x0183:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            long r0 = oneofLongAt(r7, r2)
            r8.writeInt64(r5, r0)
            goto L_0x0098
        L_0x0192:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            float r0 = oneofFloatAt(r7, r2)
            r8.writeFloat(r5, r0)
            goto L_0x0098
        L_0x01a1:
            boolean r0 = r6.isOneofPresent(r7, r5, r15)
            if (r0 == 0) goto L_0x0098
            double r0 = oneofDoubleAt(r7, r2)
            r8.writeDouble(r5, r0)
            goto L_0x0098
        L_0x01b0:
            java.lang.Object r0 = r12.getObject(r7, r2)
            r6.writeMapHelper(r8, r5, r0, r15)
            goto L_0x0098
        L_0x01b9:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.Schema r2 = r6.getMessageFieldSchema(r15)
            androidx.datastore.preferences.protobuf.SchemaUtil.writeGroupList(r0, r1, r8, r2)
            goto L_0x0098
        L_0x01cc:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r4 = 1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt64List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x01dc:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt32List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x01ec:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed64List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x01fc:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed32List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x020c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeEnumList(r0, r1, r8, r4)
            goto L_0x0098
        L_0x021c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt32List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x022c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeBoolList(r0, r1, r8, r4)
            goto L_0x0098
        L_0x023c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed32List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x024c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed64List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x025c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt32List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x026c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt64List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x027c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt64List(r0, r1, r8, r4)
            goto L_0x0098
        L_0x028c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFloatList(r0, r1, r8, r4)
            goto L_0x0098
        L_0x029c:
            r4 = 1
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeDoubleList(r0, r1, r8, r4)
            goto L_0x0098
        L_0x02ac:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r4 = 0
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt64List(r0, r1, r8, r4)
        L_0x02ba:
            r19 = r4
            r20 = r11
            r16 = r14
            goto L_0x0617
        L_0x02c2:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt32List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x02d1:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed64List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x02e0:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed32List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x02ef:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeEnumList(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x02fe:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt32List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x030d:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeBytesList(r0, r1, r8)
            goto L_0x0098
        L_0x031c:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.Schema r2 = r6.getMessageFieldSchema(r15)
            androidx.datastore.preferences.protobuf.SchemaUtil.writeMessageList(r0, r1, r8, r2)
            goto L_0x0098
        L_0x032f:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeStringList(r0, r1, r8)
            goto L_0x0098
        L_0x033e:
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            r4 = 0
            androidx.datastore.preferences.protobuf.SchemaUtil.writeBoolList(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x034e:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed32List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x035e:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed64List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x036e:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt32List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x037e:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt64List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x038e:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt64List(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x039e:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFloatList(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x03ae:
            r4 = 0
            int r0 = r6.numberAt(r15)
            java.lang.Object r1 = r12.getObject(r7, r2)
            java.util.List r1 = (java.util.List) r1
            androidx.datastore.preferences.protobuf.SchemaUtil.writeDoubleList(r0, r1, r8, r4)
            goto L_0x02ba
        L_0x03be:
            r4 = 0
            r0 = r21
            r1 = r22
            r16 = r14
            r13 = r2
            r2 = r15
            r3 = r9
            r19 = r4
            r4 = r17
            r20 = r11
            r11 = r5
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            java.lang.Object r0 = r12.getObject(r7, r13)
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeGroup(r11, r0, r1)
            goto L_0x0617
        L_0x03e4:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            long r0 = r12.getLong(r7, r13)
            r8.writeSInt64(r11, r0)
            goto L_0x0617
        L_0x0405:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            int r0 = r12.getInt(r7, r13)
            r8.writeSInt32(r11, r0)
            goto L_0x0617
        L_0x0426:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            long r0 = r12.getLong(r7, r13)
            r8.writeSFixed64(r11, r0)
            goto L_0x0617
        L_0x0447:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            int r0 = r12.getInt(r7, r13)
            r8.writeSFixed32(r11, r0)
            goto L_0x0617
        L_0x0468:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            int r0 = r12.getInt(r7, r13)
            r8.writeEnum(r11, r0)
            goto L_0x0617
        L_0x0489:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            int r0 = r12.getInt(r7, r13)
            r8.writeUInt32(r11, r0)
            goto L_0x0617
        L_0x04aa:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            java.lang.Object r0 = r12.getObject(r7, r13)
            androidx.datastore.preferences.protobuf.ByteString r0 = (androidx.datastore.preferences.protobuf.ByteString) r0
            r8.writeBytes(r11, r0)
            goto L_0x0617
        L_0x04cd:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            java.lang.Object r0 = r12.getObject(r7, r13)
            androidx.datastore.preferences.protobuf.Schema r1 = r6.getMessageFieldSchema(r15)
            r8.writeMessage(r11, r0, r1)
            goto L_0x0617
        L_0x04f2:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            java.lang.Object r0 = r12.getObject(r7, r13)
            r6.writeString(r11, r0, r8)
            goto L_0x0617
        L_0x0513:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            boolean r0 = booleanAt(r7, r13)
            r8.writeBool(r11, r0)
            goto L_0x0617
        L_0x0534:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            int r0 = r12.getInt(r7, r13)
            r8.writeFixed32(r11, r0)
            goto L_0x0617
        L_0x0555:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            long r0 = r12.getLong(r7, r13)
            r8.writeFixed64(r11, r0)
            goto L_0x0617
        L_0x0576:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            int r0 = r12.getInt(r7, r13)
            r8.writeInt32(r11, r0)
            goto L_0x0617
        L_0x0597:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            long r0 = r12.getLong(r7, r13)
            r8.writeUInt64(r11, r0)
            goto L_0x0617
        L_0x05b8:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            long r0 = r12.getLong(r7, r13)
            r8.writeInt64(r11, r0)
            goto L_0x0617
        L_0x05d8:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            float r0 = floatAt(r7, r13)
            r8.writeFloat(r11, r0)
            goto L_0x0617
        L_0x05f8:
            r20 = r11
            r16 = r14
            r19 = 0
            r13 = r2
            r11 = r5
            r0 = r21
            r1 = r22
            r2 = r15
            r3 = r9
            r4 = r17
            r5 = r18
            boolean r0 = r0.isFieldPresent(r1, r2, r3, r4, r5)
            if (r0 == 0) goto L_0x0617
            double r0 = doubleAt(r7, r13)
            r8.writeDouble(r11, r0)
        L_0x0617:
            int r15 = r15 + 3
            r0 = r9
            r1 = r16
            r2 = r17
            r11 = r20
            r13 = 1048575(0xfffff, float:1.469367E-39)
            goto L_0x002f
        L_0x0625:
            r17 = r1
        L_0x0627:
            if (r1 == 0) goto L_0x063e
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r6.extensionSchema
            r0.serializeExtension(r8, r1)
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L_0x063c
            java.lang.Object r0 = r10.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            r1 = r0
            goto L_0x0627
        L_0x063c:
            r1 = 0
            goto L_0x0627
        L_0x063e:
            androidx.datastore.preferences.protobuf.UnknownFieldSchema r0 = r6.unknownFieldSchema
            r6.writeUnknownInMessageTo(r0, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.writeFieldsInAscendingOrder(java.lang.Object, androidx.datastore.preferences.protobuf.Writer):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:161:0x058e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeFieldsInDescendingOrder(java.lang.Object r11, androidx.datastore.preferences.protobuf.Writer r12) {
        /*
            r10 = this;
            androidx.datastore.preferences.protobuf.UnknownFieldSchema r0 = r10.unknownFieldSchema
            r10.writeUnknownInMessageTo(r0, r11, r12)
            boolean r0 = r10.hasExtensions
            r1 = 0
            if (r0 == 0) goto L_0x0021
            androidx.datastore.preferences.protobuf.ExtensionSchema r0 = r10.extensionSchema
            androidx.datastore.preferences.protobuf.FieldSet r0 = r0.getExtensions(r11)
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L_0x0021
            java.util.Iterator r0 = r0.descendingIterator()
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0023
        L_0x0021:
            r0 = r1
            r2 = r0
        L_0x0023:
            int[] r3 = r10.buffer
            int r3 = r3.length
            int r3 = r3 + -3
        L_0x0028:
            if (r3 < 0) goto L_0x058c
            int r4 = r10.typeAndOffsetAt(r3)
            int r5 = r10.numberAt(r3)
        L_0x0032:
            if (r2 == 0) goto L_0x0050
            androidx.datastore.preferences.protobuf.ExtensionSchema r6 = r10.extensionSchema
            int r6 = r6.extensionNumber(r2)
            if (r6 <= r5) goto L_0x0050
            androidx.datastore.preferences.protobuf.ExtensionSchema r6 = r10.extensionSchema
            r6.serializeExtension(r12, r2)
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x004e
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            goto L_0x0032
        L_0x004e:
            r2 = r1
            goto L_0x0032
        L_0x0050:
            int r6 = type(r4)
            r7 = 0
            r8 = 1
            switch(r6) {
                case 0: goto L_0x0577;
                case 1: goto L_0x0565;
                case 2: goto L_0x0553;
                case 3: goto L_0x0541;
                case 4: goto L_0x052f;
                case 5: goto L_0x051d;
                case 6: goto L_0x050b;
                case 7: goto L_0x04f8;
                case 8: goto L_0x04e5;
                case 9: goto L_0x04ce;
                case 10: goto L_0x04b9;
                case 11: goto L_0x04a6;
                case 12: goto L_0x0493;
                case 13: goto L_0x0480;
                case 14: goto L_0x046d;
                case 15: goto L_0x045a;
                case 16: goto L_0x0447;
                case 17: goto L_0x0430;
                case 18: goto L_0x041d;
                case 19: goto L_0x040a;
                case 20: goto L_0x03f7;
                case 21: goto L_0x03e4;
                case 22: goto L_0x03d1;
                case 23: goto L_0x03be;
                case 24: goto L_0x03ab;
                case 25: goto L_0x0398;
                case 26: goto L_0x0385;
                case 27: goto L_0x036e;
                case 28: goto L_0x035b;
                case 29: goto L_0x0348;
                case 30: goto L_0x0335;
                case 31: goto L_0x0322;
                case 32: goto L_0x030f;
                case 33: goto L_0x02fc;
                case 34: goto L_0x02e9;
                case 35: goto L_0x02d6;
                case 36: goto L_0x02c3;
                case 37: goto L_0x02b0;
                case 38: goto L_0x029d;
                case 39: goto L_0x028a;
                case 40: goto L_0x0277;
                case 41: goto L_0x0264;
                case 42: goto L_0x0251;
                case 43: goto L_0x023e;
                case 44: goto L_0x022b;
                case 45: goto L_0x0218;
                case 46: goto L_0x0205;
                case 47: goto L_0x01f2;
                case 48: goto L_0x01df;
                case 49: goto L_0x01c8;
                case 50: goto L_0x01bb;
                case 51: goto L_0x01a8;
                case 52: goto L_0x0195;
                case 53: goto L_0x0182;
                case 54: goto L_0x016f;
                case 55: goto L_0x015c;
                case 56: goto L_0x0149;
                case 57: goto L_0x0136;
                case 58: goto L_0x0123;
                case 59: goto L_0x0110;
                case 60: goto L_0x00f9;
                case 61: goto L_0x00e4;
                case 62: goto L_0x00d1;
                case 63: goto L_0x00be;
                case 64: goto L_0x00ab;
                case 65: goto L_0x0098;
                case 66: goto L_0x0085;
                case 67: goto L_0x0072;
                case 68: goto L_0x005b;
                default: goto L_0x0059;
            }
        L_0x0059:
            goto L_0x0588
        L_0x005b:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            androidx.datastore.preferences.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeGroup(r5, r4, r6)
            goto L_0x0588
        L_0x0072:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeSInt64(r5, r6)
            goto L_0x0588
        L_0x0085:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeSInt32(r5, r4)
            goto L_0x0588
        L_0x0098:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeSFixed64(r5, r6)
            goto L_0x0588
        L_0x00ab:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeSFixed32(r5, r4)
            goto L_0x0588
        L_0x00be:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeEnum(r5, r4)
            goto L_0x0588
        L_0x00d1:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeUInt32(r5, r4)
            goto L_0x0588
        L_0x00e4:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            androidx.datastore.preferences.protobuf.ByteString r4 = (androidx.datastore.preferences.protobuf.ByteString) r4
            r12.writeBytes(r5, r4)
            goto L_0x0588
        L_0x00f9:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            androidx.datastore.preferences.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeMessage(r5, r4, r6)
            goto L_0x0588
        L_0x0110:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            r10.writeString(r5, r4, r12)
            goto L_0x0588
        L_0x0123:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            boolean r4 = oneofBooleanAt(r11, r6)
            r12.writeBool(r5, r4)
            goto L_0x0588
        L_0x0136:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeFixed32(r5, r4)
            goto L_0x0588
        L_0x0149:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeFixed64(r5, r6)
            goto L_0x0588
        L_0x015c:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = oneofIntAt(r11, r6)
            r12.writeInt32(r5, r4)
            goto L_0x0588
        L_0x016f:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeUInt64(r5, r6)
            goto L_0x0588
        L_0x0182:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = oneofLongAt(r11, r6)
            r12.writeInt64(r5, r6)
            goto L_0x0588
        L_0x0195:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            float r4 = oneofFloatAt(r11, r6)
            r12.writeFloat(r5, r4)
            goto L_0x0588
        L_0x01a8:
            boolean r6 = r10.isOneofPresent(r11, r5, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            double r6 = oneofDoubleAt(r11, r6)
            r12.writeDouble(r5, r6)
            goto L_0x0588
        L_0x01bb:
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            r10.writeMapHelper(r12, r5, r4, r3)
            goto L_0x0588
        L_0x01c8:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            androidx.datastore.preferences.protobuf.SchemaUtil.writeGroupList(r5, r4, r12, r6)
            goto L_0x0588
        L_0x01df:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x01f2:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0205:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0218:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x022b:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeEnumList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x023e:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0251:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeBoolList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0264:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x0277:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x028a:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt32List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x029d:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x02b0:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt64List(r5, r4, r12, r8)
            goto L_0x0588
        L_0x02c3:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFloatList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x02d6:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeDoubleList(r5, r4, r12, r8)
            goto L_0x0588
        L_0x02e9:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x02fc:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSInt32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x030f:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0322:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeSFixed32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0335:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeEnumList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0348:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x035b:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeBytesList(r5, r4, r12)
            goto L_0x0588
        L_0x036e:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            androidx.datastore.preferences.protobuf.SchemaUtil.writeMessageList(r5, r4, r12, r6)
            goto L_0x0588
        L_0x0385:
            int r5 = r10.numberAt(r3)
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeStringList(r5, r4, r12)
            goto L_0x0588
        L_0x0398:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeBoolList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x03ab:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x03be:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFixed64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x03d1:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt32List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x03e4:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeUInt64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x03f7:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeInt64List(r5, r4, r12, r7)
            goto L_0x0588
        L_0x040a:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeFloatList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x041d:
            int r5 = r10.numberAt(r3)
            long r8 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r8)
            java.util.List r4 = (java.util.List) r4
            androidx.datastore.preferences.protobuf.SchemaUtil.writeDoubleList(r5, r4, r12, r7)
            goto L_0x0588
        L_0x0430:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            androidx.datastore.preferences.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeGroup(r5, r4, r6)
            goto L_0x0588
        L_0x0447:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeSInt64(r5, r6)
            goto L_0x0588
        L_0x045a:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeSInt32(r5, r4)
            goto L_0x0588
        L_0x046d:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeSFixed64(r5, r6)
            goto L_0x0588
        L_0x0480:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeSFixed32(r5, r4)
            goto L_0x0588
        L_0x0493:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeEnum(r5, r4)
            goto L_0x0588
        L_0x04a6:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeUInt32(r5, r4)
            goto L_0x0588
        L_0x04b9:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            androidx.datastore.preferences.protobuf.ByteString r4 = (androidx.datastore.preferences.protobuf.ByteString) r4
            r12.writeBytes(r5, r4)
            goto L_0x0588
        L_0x04ce:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            androidx.datastore.preferences.protobuf.Schema r6 = r10.getMessageFieldSchema(r3)
            r12.writeMessage(r5, r4, r6)
            goto L_0x0588
        L_0x04e5:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            java.lang.Object r4 = androidx.datastore.preferences.protobuf.UnsafeUtil.getObject(r11, r6)
            r10.writeString(r5, r4, r12)
            goto L_0x0588
        L_0x04f8:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            boolean r4 = booleanAt(r11, r6)
            r12.writeBool(r5, r4)
            goto L_0x0588
        L_0x050b:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeFixed32(r5, r4)
            goto L_0x0588
        L_0x051d:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeFixed64(r5, r6)
            goto L_0x0588
        L_0x052f:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            int r4 = intAt(r11, r6)
            r12.writeInt32(r5, r4)
            goto L_0x0588
        L_0x0541:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeUInt64(r5, r6)
            goto L_0x0588
        L_0x0553:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            long r6 = longAt(r11, r6)
            r12.writeInt64(r5, r6)
            goto L_0x0588
        L_0x0565:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            float r4 = floatAt(r11, r6)
            r12.writeFloat(r5, r4)
            goto L_0x0588
        L_0x0577:
            boolean r6 = r10.isFieldPresent(r11, r3)
            if (r6 == 0) goto L_0x0588
            long r6 = offset(r4)
            double r6 = doubleAt(r11, r6)
            r12.writeDouble(r5, r6)
        L_0x0588:
            int r3 = r3 + -3
            goto L_0x0028
        L_0x058c:
            if (r2 == 0) goto L_0x05a3
            androidx.datastore.preferences.protobuf.ExtensionSchema r11 = r10.extensionSchema
            r11.serializeExtension(r12, r2)
            boolean r11 = r0.hasNext()
            if (r11 == 0) goto L_0x05a1
            java.lang.Object r11 = r0.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            r2 = r11
            goto L_0x058c
        L_0x05a1:
            r2 = r1
            goto L_0x058c
        L_0x05a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.writeFieldsInDescendingOrder(java.lang.Object, androidx.datastore.preferences.protobuf.Writer):void");
    }

    private void writeMapHelper(Writer writer, int i, Object obj, int i2) {
        if (obj != null) {
            writer.writeMap(i, this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)), this.mapFieldSchema.forMapData(obj));
        }
    }

    private void writeUnknownInMessageTo(UnknownFieldSchema unknownFieldSchema2, Object obj, Writer writer) {
        unknownFieldSchema2.writeTo(unknownFieldSchema2.getFromMessage(obj), writer);
    }

    public void mergeFrom(Object obj, Reader reader, ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.getClass();
        checkMutable(obj);
        mergeFromHelper(this.unknownFieldSchema, this.extensionSchema, obj, reader, extensionRegistryLite);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:70|69|164|165|(0)(0)|189|207|199) */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0291, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0124, code lost:
        r12 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0125, code lost:
        r13 = r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:164:0x05fe */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0604 A[Catch:{ all -> 0x0291 }] */
    /* JADX WARNING: Removed duplicated region for block: B:175:0x062b  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x066d A[LOOP:6: B:193:0x0669->B:195:0x066d, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0682  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema r20, androidx.datastore.preferences.protobuf.ExtensionSchema r21, java.lang.Object r22, androidx.datastore.preferences.protobuf.Reader r23, androidx.datastore.preferences.protobuf.ExtensionRegistryLite r24) {
        /*
            r19 = this;
            r8 = r19
            r7 = r20
            r15 = r22
            r0 = r23
            r6 = r24
            r17 = 0
            r9 = r17
            r18 = r9
        L_0x0010:
            int r2 = r23.getFieldNumber()     // Catch:{ all -> 0x005c }
            int r3 = r8.positionForFieldNumber(r2)     // Catch:{ all -> 0x005c }
            r10 = 0
            if (r3 >= 0) goto L_0x00c9
            r1 = 2147483647(0x7fffffff, float:NaN)
            if (r2 != r1) goto L_0x0041
            int r0 = r8.checkInitializedCount
            r4 = r18
        L_0x0024:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x003b
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r19
            r2 = r22
            r5 = r20
            r6 = r22
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x0024
        L_0x003b:
            if (r4 == 0) goto L_0x0040
            r7.setBuilderToMessage(r15, r4)
        L_0x0040:
            return
        L_0x0041:
            boolean r1 = r8.hasExtensions     // Catch:{ all -> 0x00c6 }
            if (r1 != 0) goto L_0x004a
            r5 = r21
            r12 = r17
            goto L_0x0053
        L_0x004a:
            androidx.datastore.preferences.protobuf.MessageLite r1 = r8.defaultInstance     // Catch:{ all -> 0x00c6 }
            r5 = r21
            java.lang.Object r1 = r5.findExtensionByNumber(r6, r1, r2)     // Catch:{ all -> 0x00c6 }
            r12 = r1
        L_0x0053:
            if (r12 == 0) goto L_0x007c
            if (r9 != 0) goto L_0x0061
            androidx.datastore.preferences.protobuf.FieldSet r1 = r21.getMutableExtensions(r22)     // Catch:{ all -> 0x005c }
            goto L_0x0062
        L_0x005c:
            r0 = move-exception
            r13 = r7
            r11 = r15
            goto L_0x0664
        L_0x0061:
            r1 = r9
        L_0x0062:
            r9 = r21
            r10 = r22
            r11 = r23
            r13 = r24
            r14 = r1
            r4 = r15
            r15 = r18
            r16 = r20
            java.lang.Object r18 = r9.parseExtension(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x0077 }
            r9 = r1
        L_0x0075:
            r15 = r4
            goto L_0x0010
        L_0x0077:
            r0 = move-exception
            r11 = r4
        L_0x0079:
            r13 = r7
            goto L_0x0664
        L_0x007c:
            r4 = r15
            boolean r1 = r7.shouldDiscardUnknownFields(r0)     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x008a
            boolean r1 = r23.skipField()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x009c
            goto L_0x0075
        L_0x008a:
            if (r18 != 0) goto L_0x0091
            java.lang.Object r1 = r7.getBuilderFromMessage(r4)     // Catch:{ all -> 0x0077 }
            goto L_0x0093
        L_0x0091:
            r1 = r18
        L_0x0093:
            boolean r2 = r7.mergeOneFieldFrom(r1, r0, r10)     // Catch:{ all -> 0x00c1 }
            r18 = r1
            if (r2 == 0) goto L_0x009c
            goto L_0x0075
        L_0x009c:
            int r0 = r8.checkInitializedCount
            r5 = r18
        L_0x00a0:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x00ba
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r19
            r2 = r22
            r11 = r4
            r4 = r5
            r5 = r20
            r6 = r22
            java.lang.Object r5 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            r4 = r11
            goto L_0x00a0
        L_0x00ba:
            r11 = r4
            if (r5 == 0) goto L_0x00c0
            r7.setBuilderToMessage(r11, r5)
        L_0x00c0:
            return
        L_0x00c1:
            r0 = move-exception
            r11 = r4
        L_0x00c3:
            r18 = r1
            goto L_0x0079
        L_0x00c6:
            r0 = move-exception
            r11 = r15
            goto L_0x0079
        L_0x00c9:
            r5 = r21
            r11 = r15
            int r4 = r8.typeAndOffsetAt(r3)     // Catch:{ all -> 0x00e0 }
            int r1 = type(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            switch(r1) {
                case 0: goto L_0x05ed;
                case 1: goto L_0x05db;
                case 2: goto L_0x05c9;
                case 3: goto L_0x05b7;
                case 4: goto L_0x05a5;
                case 5: goto L_0x0593;
                case 6: goto L_0x0581;
                case 7: goto L_0x056f;
                case 8: goto L_0x0565;
                case 9: goto L_0x0551;
                case 10: goto L_0x053f;
                case 11: goto L_0x052d;
                case 12: goto L_0x0518;
                case 13: goto L_0x0506;
                case 14: goto L_0x04f4;
                case 15: goto L_0x04e2;
                case 16: goto L_0x04d0;
                case 17: goto L_0x04bc;
                case 18: goto L_0x04ab;
                case 19: goto L_0x049a;
                case 20: goto L_0x0489;
                case 21: goto L_0x0478;
                case 22: goto L_0x0467;
                case 23: goto L_0x0456;
                case 24: goto L_0x0445;
                case 25: goto L_0x0434;
                case 26: goto L_0x042d;
                case 27: goto L_0x0419;
                case 28: goto L_0x0408;
                case 29: goto L_0x03f7;
                case 30: goto L_0x03d6;
                case 31: goto L_0x03c5;
                case 32: goto L_0x03b4;
                case 33: goto L_0x03a3;
                case 34: goto L_0x0392;
                case 35: goto L_0x0381;
                case 36: goto L_0x0370;
                case 37: goto L_0x035f;
                case 38: goto L_0x034e;
                case 39: goto L_0x033d;
                case 40: goto L_0x032c;
                case 41: goto L_0x031b;
                case 42: goto L_0x030a;
                case 43: goto L_0x02f9;
                case 44: goto L_0x02d8;
                case 45: goto L_0x02c7;
                case 46: goto L_0x02b6;
                case 47: goto L_0x02a5;
                case 48: goto L_0x0294;
                case 49: goto L_0x0279;
                case 50: goto L_0x0267;
                case 51: goto L_0x0253;
                case 52: goto L_0x023f;
                case 53: goto L_0x022b;
                case 54: goto L_0x0217;
                case 55: goto L_0x0203;
                case 56: goto L_0x01ef;
                case 57: goto L_0x01db;
                case 58: goto L_0x01c7;
                case 59: goto L_0x01bf;
                case 60: goto L_0x01ad;
                case 61: goto L_0x019d;
                case 62: goto L_0x018a;
                case 63: goto L_0x0174;
                case 64: goto L_0x0161;
                case 65: goto L_0x014e;
                case 66: goto L_0x013b;
                case 67: goto L_0x0128;
                case 68: goto L_0x0114;
                default: goto L_0x00d7;
            }     // Catch:{ InvalidWireTypeException -> 0x00e2 }
        L_0x00d7:
            if (r18 != 0) goto L_0x00dd
            java.lang.Object r18 = r7.getBuilderFromMessage(r11)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
        L_0x00dd:
            r1 = r18
            goto L_0x00e6
        L_0x00e0:
            r0 = move-exception
            goto L_0x0079
        L_0x00e2:
            r12 = r6
        L_0x00e3:
            r13 = r7
            goto L_0x05fe
        L_0x00e6:
            boolean r2 = r7.mergeOneFieldFrom(r1, r0, r10)     // Catch:{ InvalidWireTypeException -> 0x0111, all -> 0x010f }
            if (r2 != 0) goto L_0x010c
            int r0 = r8.checkInitializedCount
            r4 = r1
        L_0x00ef:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0106
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r19
            r2 = r22
            r5 = r20
            r6 = r22
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x00ef
        L_0x0106:
            if (r4 == 0) goto L_0x010b
            r7.setBuilderToMessage(r11, r4)
        L_0x010b:
            return
        L_0x010c:
            r18 = r1
            goto L_0x0124
        L_0x010f:
            r0 = move-exception
            goto L_0x00c3
        L_0x0111:
            r18 = r1
            goto L_0x00e2
        L_0x0114:
            java.lang.Object r1 = r8.mutableOneofMessageFieldForMerge(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.MessageLite r1 = (androidx.datastore.preferences.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.Schema r4 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r0.mergeGroupField(r1, r4, r6)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.storeOneofMessageField(r11, r2, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
        L_0x0124:
            r12 = r6
        L_0x0125:
            r13 = r7
            goto L_0x065c
        L_0x0128:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            long r14 = r23.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x013b:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            int r1 = r23.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x014e:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            long r14 = r23.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x0161:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            int r1 = r23.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x0174:
            int r1 = r23.readEnum()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x018a:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            int r1 = r23.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x019d:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.ByteString r1 = r23.readBytes()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x01ad:
            java.lang.Object r1 = r8.mutableOneofMessageFieldForMerge(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.MessageLite r1 = (androidx.datastore.preferences.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.Schema r4 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r0.mergeMessageField(r1, r4, r6)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.storeOneofMessageField(r11, r2, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x01bf:
            r8.readString(r11, r4, r0)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x01c7:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            boolean r1 = r23.readBool()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x01db:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            int r1 = r23.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x01ef:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            long r14 = r23.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x0203:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            int r1 = r23.readInt32()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x0217:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            long r14 = r23.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x022b:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            long r14 = r23.readInt64()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x023f:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            float r1 = r23.readFloat()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Float r1 = java.lang.Float.valueOf(r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x0253:
            long r12 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            double r14 = r23.readDouble()     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            java.lang.Double r1 = java.lang.Double.valueOf(r14)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r12, r1)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r8.setOneofPresent(r11, r2, r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            goto L_0x0124
        L_0x0267:
            java.lang.Object r4 = r8.getMapFieldDefaultEntry(r3)     // Catch:{ InvalidWireTypeException -> 0x00e2 }
            r1 = r19
            r2 = r22
            r5 = r24
            r12 = r6
            r6 = r23
            r1.mergeMap(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x00e3 }
            goto L_0x0125
        L_0x0279:
            r12 = r6
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x00e3 }
            androidx.datastore.preferences.protobuf.Schema r6 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x00e3 }
            r1 = r19
            r2 = r22
            r3 = r4
            r5 = r23
            r13 = r7
            r7 = r24
            r1.readGroupList(r2, r3, r5, r6, r7)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0291:
            r0 = move-exception
            goto L_0x0664
        L_0x0294:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x02a5:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x02b6:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x02c7:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x02d8:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r4 = r1.mutableListAt(r11, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readEnumList(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r5 = 0
            r1 = r22
            r3 = r4
            r4 = r5
            r5 = r18
            r6 = r20
            java.lang.Object r18 = androidx.datastore.preferences.protobuf.SchemaUtil.filterUnknownEnumList(r1, r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x02f9:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x030a:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x031b:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x032c:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x033d:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x034e:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x035f:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0370:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0381:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0392:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x03a3:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x03b4:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x03c5:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readSFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x03d6:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r4 = r1.mutableListAt(r11, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readEnumList(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r5 = 0
            r1 = r22
            r3 = r4
            r4 = r5
            r5 = r18
            r6 = r20
            java.lang.Object r18 = androidx.datastore.preferences.protobuf.SchemaUtil.filterUnknownEnumList(r1, r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x03f7:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readUInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0408:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readBytesList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0419:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.Schema r5 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r1 = r19
            r2 = r22
            r3 = r4
            r4 = r23
            r6 = r24
            r1.readMessageList(r2, r3, r4, r5, r6)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x042d:
            r12 = r6
            r13 = r7
            r8.readStringList(r11, r4, r0)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0434:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readBoolList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0445:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readFixed32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0456:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readFixed64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0467:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readInt32List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0478:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readUInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0489:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readInt64List(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x049a:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readFloatList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x04ab:
            r12 = r6
            r13 = r7
            androidx.datastore.preferences.protobuf.ListFieldSchema r1 = r8.listFieldSchema     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r2 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            java.util.List r1 = r1.mutableListAt(r11, r2)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.readDoubleList(r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x04bc:
            r12 = r6
            r13 = r7
            java.lang.Object r1 = r8.mutableMessageFieldForMerge(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.MessageLite r1 = (androidx.datastore.preferences.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.mergeGroupField(r1, r2, r12)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.storeMessageField(r11, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x04d0:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = r23.readSInt64()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putLong(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x04e2:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            int r4 = r23.readSInt32()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putInt(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x04f4:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = r23.readSFixed64()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putLong(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0506:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            int r4 = r23.readSFixed32()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putInt(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0518:
            r12 = r6
            r13 = r7
            int r1 = r23.readEnum()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.getEnumFieldVerifier(r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putInt(r11, r4, r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x052d:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            int r4 = r23.readUInt32()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putInt(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x053f:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.ByteString r4 = r23.readBytes()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putObject(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0551:
            r12 = r6
            r13 = r7
            java.lang.Object r1 = r8.mutableMessageFieldForMerge(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.MessageLite r1 = (androidx.datastore.preferences.protobuf.MessageLite) r1     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.Schema r2 = r8.getMessageFieldSchema(r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r0.mergeMessageField(r1, r2, r12)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.storeMessageField(r11, r3, r1)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0565:
            r12 = r6
            r13 = r7
            r8.readString(r11, r4, r0)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x056f:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            boolean r4 = r23.readBool()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putBoolean(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0581:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            int r4 = r23.readFixed32()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putInt(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x0593:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = r23.readFixed64()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putLong(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x05a5:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            int r4 = r23.readInt32()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putInt(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x05b7:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = r23.readUInt64()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putLong(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x05c9:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            long r4 = r23.readInt64()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putLong(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x05db:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            float r4 = r23.readFloat()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putFloat(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x05ed:
            r12 = r6
            r13 = r7
            long r1 = offset(r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            double r4 = r23.readDouble()     // Catch:{ InvalidWireTypeException -> 0x05fe }
            androidx.datastore.preferences.protobuf.UnsafeUtil.putDouble(r11, r1, r4)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            r8.setFieldPresent(r11, r3)     // Catch:{ InvalidWireTypeException -> 0x05fe }
            goto L_0x065c
        L_0x05fe:
            boolean r1 = r13.shouldDiscardUnknownFields(r0)     // Catch:{ all -> 0x0291 }
            if (r1 == 0) goto L_0x062b
            boolean r1 = r23.skipField()     // Catch:{ all -> 0x0291 }
            if (r1 != 0) goto L_0x065c
            int r0 = r8.checkInitializedCount
            r4 = r18
        L_0x060e:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0625
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r19
            r2 = r22
            r5 = r20
            r6 = r22
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x060e
        L_0x0625:
            if (r4 == 0) goto L_0x062a
            r13.setBuilderToMessage(r11, r4)
        L_0x062a:
            return
        L_0x062b:
            if (r18 != 0) goto L_0x0632
            java.lang.Object r1 = r13.getBuilderFromMessage(r11)     // Catch:{ all -> 0x0291 }
            goto L_0x0634
        L_0x0632:
            r1 = r18
        L_0x0634:
            boolean r2 = r13.mergeOneFieldFrom(r1, r0, r10)     // Catch:{ all -> 0x0661 }
            if (r2 != 0) goto L_0x065a
            int r0 = r8.checkInitializedCount
            r4 = r1
        L_0x063d:
            int r1 = r8.repeatedFieldOffsetStart
            if (r0 >= r1) goto L_0x0654
            int[] r1 = r8.intArray
            r3 = r1[r0]
            r1 = r19
            r2 = r22
            r5 = r20
            r6 = r22
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r0 = r0 + 1
            goto L_0x063d
        L_0x0654:
            if (r4 == 0) goto L_0x0659
            r13.setBuilderToMessage(r11, r4)
        L_0x0659:
            return
        L_0x065a:
            r18 = r1
        L_0x065c:
            r15 = r11
            r6 = r12
            r7 = r13
            goto L_0x0010
        L_0x0661:
            r0 = move-exception
            r18 = r1
        L_0x0664:
            int r1 = r8.checkInitializedCount
            r7 = r1
            r4 = r18
        L_0x0669:
            int r1 = r8.repeatedFieldOffsetStart
            if (r7 >= r1) goto L_0x0680
            int[] r1 = r8.intArray
            r3 = r1[r7]
            r1 = r19
            r2 = r22
            r5 = r20
            r6 = r22
            java.lang.Object r4 = r1.filterMapUnknownEnumValues(r2, r3, r4, r5, r6)
            int r7 = r7 + 1
            goto L_0x0669
        L_0x0680:
            if (r4 == 0) goto L_0x0685
            r13.setBuilderToMessage(r11, r4)
        L_0x0685:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.preferences.protobuf.MessageSchema.mergeFromHelper(androidx.datastore.preferences.protobuf.UnknownFieldSchema, androidx.datastore.preferences.protobuf.ExtensionSchema, java.lang.Object, androidx.datastore.preferences.protobuf.Reader, androidx.datastore.preferences.protobuf.ExtensionRegistryLite):void");
    }

    private Schema getMessageFieldSchema(int i) {
        int i2 = (i / 3) * 2;
        Schema schema = (Schema) this.objects[i2];
        if (schema != null) {
            return schema;
        }
        Schema schemaFor = Protobuf.getInstance().schemaFor((Class) this.objects[i2 + 1]);
        this.objects[i2] = schemaFor;
        return schemaFor;
    }

    private Object getMapFieldDefaultEntry(int i) {
        return this.objects[(i / 3) * 2];
    }

    private Internal.EnumVerifier getEnumFieldVerifier(int i) {
        MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(this.objects[((i / 3) * 2) + 1]);
        return null;
    }

    private Object mutableMessageFieldForMerge(Object obj, int i) {
        Schema messageFieldSchema = getMessageFieldSchema(i);
        long offset = offset(typeAndOffsetAt(i));
        if (!isFieldPresent(obj, i)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(obj, offset);
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeMessageField(Object obj, int i, Object obj2) {
        UNSAFE.putObject(obj, offset(typeAndOffsetAt(i)), obj2);
        setFieldPresent(obj, i);
    }

    private Object mutableOneofMessageFieldForMerge(Object obj, int i, int i2) {
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        if (!isOneofPresent(obj, i, i2)) {
            return messageFieldSchema.newInstance();
        }
        Object object = UNSAFE.getObject(obj, offset(typeAndOffsetAt(i2)));
        if (isMutable(object)) {
            return object;
        }
        Object newInstance = messageFieldSchema.newInstance();
        if (object != null) {
            messageFieldSchema.mergeFrom(newInstance, object);
        }
        return newInstance;
    }

    private void storeOneofMessageField(Object obj, int i, int i2, Object obj2) {
        UNSAFE.putObject(obj, offset(typeAndOffsetAt(i2)), obj2);
        setOneofPresent(obj, i, i2);
    }

    public void makeImmutable(Object obj) {
        if (isMutable(obj)) {
            if (obj instanceof GeneratedMessageLite) {
                GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
                generatedMessageLite.clearMemoizedSerializedSize();
                generatedMessageLite.clearMemoizedHashCode();
                generatedMessageLite.markImmutable();
            }
            int length = this.buffer.length;
            for (int i = 0; i < length; i += 3) {
                int typeAndOffsetAt = typeAndOffsetAt(i);
                long offset = offset(typeAndOffsetAt);
                int type = type(typeAndOffsetAt);
                if (type != 9) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(obj, numberAt(i), i)) {
                            getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(obj, offset));
                        }
                    } else {
                        switch (type) {
                            case 17:
                                break;
                            case 18:
                            case 19:
                            case 20:
                            case CommonStatusCodes.RECONNECTION_TIMED_OUT_DURING_UPDATE /*21*/:
                            case 22:
                            case ConnectionResult.API_DISABLED /*23*/:
                            case ConnectionResult.API_DISABLED_FOR_CONNECTION /*24*/:
                            case 25:
                            case 26:
                            case 27:
                            case OutsetBoxShadowDrawableKt.MIN_OUTSET_BOX_SHADOW_SDK_VERSION /*28*/:
                            case InsetBoxShadowDrawableKt.MIN_INSET_BOX_SHADOW_SDK_VERSION /*29*/:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.listFieldSchema.makeImmutableListAt(obj, offset);
                                continue;
                            case 50:
                                Unsafe unsafe = UNSAFE;
                                Object object = unsafe.getObject(obj, offset);
                                if (object != null) {
                                    unsafe.putObject(obj, offset, this.mapFieldSchema.toImmutable(object));
                                    break;
                                } else {
                                    continue;
                                }
                        }
                    }
                }
                if (isFieldPresent(obj, i)) {
                    getMessageFieldSchema(i).makeImmutable(UNSAFE.getObject(obj, offset));
                }
            }
            this.unknownFieldSchema.makeImmutable(obj);
            if (this.hasExtensions) {
                this.extensionSchema.makeImmutable(obj);
            }
        }
    }

    private final void mergeMap(Object obj, int i, Object obj2, ExtensionRegistryLite extensionRegistryLite, Reader reader) {
        long offset = offset(typeAndOffsetAt(i));
        Object object = UnsafeUtil.getObject(obj, offset);
        if (object == null) {
            object = this.mapFieldSchema.newMapField(obj2);
            UnsafeUtil.putObject(obj, offset, object);
        } else if (this.mapFieldSchema.isImmutable(object)) {
            Object newMapField = this.mapFieldSchema.newMapField(obj2);
            this.mapFieldSchema.mergeFrom(newMapField, object);
            UnsafeUtil.putObject(obj, offset, newMapField);
            object = newMapField;
        }
        reader.readMap(this.mapFieldSchema.forMutableMapData(object), this.mapFieldSchema.forMapMetadata(obj2), extensionRegistryLite);
    }

    private Object filterMapUnknownEnumValues(Object obj, int i, Object obj2, UnknownFieldSchema unknownFieldSchema2, Object obj3) {
        numberAt(i);
        if (UnsafeUtil.getObject(obj, offset(typeAndOffsetAt(i))) == null) {
            return obj2;
        }
        getEnumFieldVerifier(i);
        return obj2;
    }

    public final boolean isInitialized(Object obj) {
        int i;
        int i2;
        Object obj2 = obj;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.checkInitializedCount) {
            int i6 = this.intArray[i5];
            int numberAt = numberAt(i6);
            int typeAndOffsetAt = typeAndOffsetAt(i6);
            int i7 = this.buffer[i6 + 2];
            int i8 = i7 & 1048575;
            int i9 = 1 << (i7 >>> 20);
            if (i8 != i3) {
                if (i8 != 1048575) {
                    i4 = UNSAFE.getInt(obj2, (long) i8);
                }
                i = i4;
                i2 = i8;
            } else {
                i2 = i3;
                i = i4;
            }
            if (isRequired(typeAndOffsetAt) && !isFieldPresent(obj, i6, i2, i, i9)) {
                return false;
            }
            int type = type(typeAndOffsetAt);
            if (type != 9 && type != 17) {
                if (type != 27) {
                    if (type == 60 || type == 68) {
                        if (isOneofPresent(obj2, numberAt, i6) && !isInitialized(obj2, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                            return false;
                        }
                    } else if (type != 49) {
                        if (type == 50 && !isMapInitialized(obj2, typeAndOffsetAt, i6)) {
                            return false;
                        }
                    }
                }
                if (!isListInitialized(obj2, typeAndOffsetAt, i6)) {
                    return false;
                }
            } else if (isFieldPresent(obj, i6, i2, i, i9) && !isInitialized(obj2, typeAndOffsetAt, getMessageFieldSchema(i6))) {
                return false;
            }
            i5++;
            i3 = i2;
            i4 = i;
        }
        return !this.hasExtensions || this.extensionSchema.getExtensions(obj2).isInitialized();
    }

    private static boolean isInitialized(Object obj, int i, Schema schema) {
        return schema.isInitialized(UnsafeUtil.getObject(obj, offset(i)));
    }

    private boolean isListInitialized(Object obj, int i, int i2) {
        List list = (List) UnsafeUtil.getObject(obj, offset(i));
        if (list.isEmpty()) {
            return true;
        }
        Schema messageFieldSchema = getMessageFieldSchema(i2);
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (!messageFieldSchema.isInitialized(list.get(i3))) {
                return false;
            }
        }
        return true;
    }

    private boolean isMapInitialized(Object obj, int i, int i2) {
        Map forMapData = this.mapFieldSchema.forMapData(UnsafeUtil.getObject(obj, offset(i)));
        if (forMapData.isEmpty()) {
            return true;
        }
        if (this.mapFieldSchema.forMapMetadata(getMapFieldDefaultEntry(i2)).valueType.getJavaType() != WireFormat.JavaType.MESSAGE) {
            return true;
        }
        Schema schema = null;
        for (Object next : forMapData.values()) {
            if (schema == null) {
                schema = Protobuf.getInstance().schemaFor((Class) next.getClass());
            }
            if (!schema.isInitialized(next)) {
                return false;
            }
        }
        return true;
    }

    private void writeString(int i, Object obj, Writer writer) {
        if (obj instanceof String) {
            writer.writeString(i, (String) obj);
        } else {
            writer.writeBytes(i, (ByteString) obj);
        }
    }

    private void readString(Object obj, int i, Reader reader) {
        if (isEnforceUtf8(i)) {
            UnsafeUtil.putObject(obj, offset(i), reader.readStringRequireUtf8());
        } else if (this.lite) {
            UnsafeUtil.putObject(obj, offset(i), reader.readString());
        } else {
            UnsafeUtil.putObject(obj, offset(i), reader.readBytes());
        }
    }

    private void readStringList(Object obj, int i, Reader reader) {
        if (isEnforceUtf8(i)) {
            reader.readStringListRequireUtf8(this.listFieldSchema.mutableListAt(obj, offset(i)));
        } else {
            reader.readStringList(this.listFieldSchema.mutableListAt(obj, offset(i)));
        }
    }

    private void readMessageList(Object obj, int i, Reader reader, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readMessageList(this.listFieldSchema.mutableListAt(obj, offset(i)), schema, extensionRegistryLite);
    }

    private void readGroupList(Object obj, long j, Reader reader, Schema schema, ExtensionRegistryLite extensionRegistryLite) {
        reader.readGroupList(this.listFieldSchema.mutableListAt(obj, j), schema, extensionRegistryLite);
    }

    private int numberAt(int i) {
        return this.buffer[i];
    }

    private int typeAndOffsetAt(int i) {
        return this.buffer[i + 1];
    }

    private int presenceMaskAndOffsetAt(int i) {
        return this.buffer[i + 2];
    }

    private static boolean isMutable(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GeneratedMessageLite) {
            return ((GeneratedMessageLite) obj).isMutable();
        }
        return true;
    }

    private static void checkMutable(Object obj) {
        if (!isMutable(obj)) {
            throw new IllegalArgumentException("Mutating immutable message: " + obj);
        }
    }

    private static double doubleAt(Object obj, long j) {
        return UnsafeUtil.getDouble(obj, j);
    }

    private static float floatAt(Object obj, long j) {
        return UnsafeUtil.getFloat(obj, j);
    }

    private static int intAt(Object obj, long j) {
        return UnsafeUtil.getInt(obj, j);
    }

    private static long longAt(Object obj, long j) {
        return UnsafeUtil.getLong(obj, j);
    }

    private static boolean booleanAt(Object obj, long j) {
        return UnsafeUtil.getBoolean(obj, j);
    }

    private static double oneofDoubleAt(Object obj, long j) {
        return ((Double) UnsafeUtil.getObject(obj, j)).doubleValue();
    }

    private static float oneofFloatAt(Object obj, long j) {
        return ((Float) UnsafeUtil.getObject(obj, j)).floatValue();
    }

    private static int oneofIntAt(Object obj, long j) {
        return ((Integer) UnsafeUtil.getObject(obj, j)).intValue();
    }

    private static long oneofLongAt(Object obj, long j) {
        return ((Long) UnsafeUtil.getObject(obj, j)).longValue();
    }

    private static boolean oneofBooleanAt(Object obj, long j) {
        return ((Boolean) UnsafeUtil.getObject(obj, j)).booleanValue();
    }

    private boolean arePresentForEquals(Object obj, Object obj2, int i) {
        return isFieldPresent(obj, i) == isFieldPresent(obj2, i);
    }

    private boolean isFieldPresent(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return isFieldPresent(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private boolean isFieldPresent(Object obj, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = (long) (1048575 & presenceMaskAndOffsetAt);
        if (j == 1048575) {
            int typeAndOffsetAt = typeAndOffsetAt(i);
            long offset = offset(typeAndOffsetAt);
            switch (type(typeAndOffsetAt)) {
                case 0:
                    if (Double.doubleToRawLongBits(UnsafeUtil.getDouble(obj, offset)) != 0) {
                        return true;
                    }
                    return false;
                case 1:
                    if (Float.floatToRawIntBits(UnsafeUtil.getFloat(obj, offset)) != 0) {
                        return true;
                    }
                    return false;
                case 2:
                    if (UnsafeUtil.getLong(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 3:
                    if (UnsafeUtil.getLong(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 4:
                    if (UnsafeUtil.getInt(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 5:
                    if (UnsafeUtil.getLong(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 6:
                    if (UnsafeUtil.getInt(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 7:
                    return UnsafeUtil.getBoolean(obj, offset);
                case 8:
                    Object object = UnsafeUtil.getObject(obj, offset);
                    if (object instanceof String) {
                        return !((String) object).isEmpty();
                    }
                    if (object instanceof ByteString) {
                        return !ByteString.EMPTY.equals(object);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    if (UnsafeUtil.getObject(obj, offset) != null) {
                        return true;
                    }
                    return false;
                case 10:
                    return !ByteString.EMPTY.equals(UnsafeUtil.getObject(obj, offset));
                case 11:
                    if (UnsafeUtil.getInt(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 12:
                    if (UnsafeUtil.getInt(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 13:
                    if (UnsafeUtil.getInt(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 14:
                    if (UnsafeUtil.getLong(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 15:
                    if (UnsafeUtil.getInt(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 16:
                    if (UnsafeUtil.getLong(obj, offset) != 0) {
                        return true;
                    }
                    return false;
                case 17:
                    if (UnsafeUtil.getObject(obj, offset) != null) {
                        return true;
                    }
                    return false;
                default:
                    throw new IllegalArgumentException();
            }
        } else {
            if ((UnsafeUtil.getInt(obj, j) & (1 << (presenceMaskAndOffsetAt >>> 20))) != 0) {
                return true;
            }
            return false;
        }
    }

    private void setFieldPresent(Object obj, int i) {
        int presenceMaskAndOffsetAt = presenceMaskAndOffsetAt(i);
        long j = (long) (1048575 & presenceMaskAndOffsetAt);
        if (j != 1048575) {
            UnsafeUtil.putInt(obj, j, (1 << (presenceMaskAndOffsetAt >>> 20)) | UnsafeUtil.getInt(obj, j));
        }
    }

    private boolean isOneofPresent(Object obj, int i, int i2) {
        return UnsafeUtil.getInt(obj, (long) (presenceMaskAndOffsetAt(i2) & 1048575)) == i;
    }

    private boolean isOneofCaseEqual(Object obj, Object obj2, int i) {
        long presenceMaskAndOffsetAt = (long) (presenceMaskAndOffsetAt(i) & 1048575);
        return UnsafeUtil.getInt(obj, presenceMaskAndOffsetAt) == UnsafeUtil.getInt(obj2, presenceMaskAndOffsetAt);
    }

    private void setOneofPresent(Object obj, int i, int i2) {
        UnsafeUtil.putInt(obj, (long) (presenceMaskAndOffsetAt(i2) & 1048575), i);
    }

    private int positionForFieldNumber(int i) {
        if (i < this.minFieldNumber || i > this.maxFieldNumber) {
            return -1;
        }
        return slowPositionForFieldNumber(i, 0);
    }

    private int slowPositionForFieldNumber(int i, int i2) {
        int length = (this.buffer.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int numberAt = numberAt(i4);
            if (i == numberAt) {
                return i4;
            }
            if (i < numberAt) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
