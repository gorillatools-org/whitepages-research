package com.google.zxing.aztec.encoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import okhttp3.internal.ws.WebSocketProtocol;

public final class HighLevelEncoder {
    private static final int[][] CHAR_MAP;
    static final int[][] LATCH_TABLE = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};
    static final String[] MODE_NAMES = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};
    static final int[][] SHIFT_TABLE;
    private final byte[] text;

    static {
        int[] iArr = new int[2];
        iArr[1] = 256;
        iArr[0] = 5;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        CHAR_MAP = iArr2;
        iArr2[0][32] = 1;
        for (int i = 65; i <= 90; i++) {
            CHAR_MAP[0][i] = i - 63;
        }
        CHAR_MAP[1][32] = 1;
        for (int i2 = 97; i2 <= 122; i2++) {
            CHAR_MAP[1][i2] = i2 - 95;
        }
        CHAR_MAP[2][32] = 1;
        for (int i3 = 48; i3 <= 57; i3++) {
            CHAR_MAP[2][i3] = i3 - 46;
        }
        int[] iArr3 = CHAR_MAP[2];
        iArr3[44] = 12;
        iArr3[46] = 13;
        int[] iArr4 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, WebSocketProtocol.PAYLOAD_SHORT, 127};
        for (int i4 = 0; i4 < 28; i4++) {
            CHAR_MAP[3][iArr4[i4]] = i4;
        }
        int[] iArr5 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i5 = 0; i5 < 31; i5++) {
            int i6 = iArr5[i5];
            if (i6 > 0) {
                CHAR_MAP[4][i6] = i5;
            }
        }
        int[] iArr6 = new int[2];
        iArr6[1] = 6;
        iArr6[0] = 6;
        int[][] iArr7 = (int[][]) Array.newInstance(Integer.TYPE, iArr6);
        SHIFT_TABLE = iArr7;
        for (int[] fill : iArr7) {
            Arrays.fill(fill, -1);
        }
        int[][] iArr8 = SHIFT_TABLE;
        iArr8[0][4] = 0;
        int[] iArr9 = iArr8[1];
        iArr9[4] = 0;
        iArr9[0] = 28;
        iArr8[3][4] = 0;
        int[] iArr10 = iArr8[2];
        iArr10[4] = 0;
        iArr10[0] = 15;
    }

    public HighLevelEncoder(byte[] bArr) {
        this.text = bArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0045  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.common.BitArray encode() {
        /*
            r8 = this;
            com.google.zxing.aztec.encoder.State r0 = com.google.zxing.aztec.encoder.State.INITIAL_STATE
            java.util.List r0 = java.util.Collections.singletonList(r0)
            r1 = 0
            r2 = r1
        L_0x0008:
            byte[] r3 = r8.text
            int r4 = r3.length
            if (r2 >= r4) goto L_0x004c
            int r4 = r2 + 1
            int r5 = r3.length
            if (r4 >= r5) goto L_0x0015
            byte r5 = r3[r4]
            goto L_0x0016
        L_0x0015:
            r5 = r1
        L_0x0016:
            byte r3 = r3[r2]
            r6 = 13
            if (r3 == r6) goto L_0x0038
            r6 = 44
            r7 = 32
            if (r3 == r6) goto L_0x0034
            r6 = 46
            if (r3 == r6) goto L_0x0030
            r6 = 58
            if (r3 == r6) goto L_0x002c
        L_0x002a:
            r3 = r1
            goto L_0x003d
        L_0x002c:
            if (r5 != r7) goto L_0x002a
            r3 = 5
            goto L_0x003d
        L_0x0030:
            if (r5 != r7) goto L_0x002a
            r3 = 3
            goto L_0x003d
        L_0x0034:
            if (r5 != r7) goto L_0x002a
            r3 = 4
            goto L_0x003d
        L_0x0038:
            r3 = 10
            if (r5 != r3) goto L_0x002a
            r3 = 2
        L_0x003d:
            if (r3 <= 0) goto L_0x0045
            java.util.Collection r0 = updateStateListForPair(r0, r2, r3)
            r2 = r4
            goto L_0x0049
        L_0x0045:
            java.util.Collection r0 = r8.updateStateListForChar(r0, r2)
        L_0x0049:
            int r2 = r2 + 1
            goto L_0x0008
        L_0x004c:
            com.google.zxing.aztec.encoder.HighLevelEncoder$1 r1 = new com.google.zxing.aztec.encoder.HighLevelEncoder$1
            r1.<init>()
            java.lang.Object r0 = java.util.Collections.min(r0, r1)
            com.google.zxing.aztec.encoder.State r0 = (com.google.zxing.aztec.encoder.State) r0
            byte[] r1 = r8.text
            com.google.zxing.common.BitArray r0 = r0.toBitArray(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.aztec.encoder.HighLevelEncoder.encode():com.google.zxing.common.BitArray");
    }

    private Collection updateStateListForChar(Iterable iterable, int i) {
        LinkedList linkedList = new LinkedList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            updateStateForChar((State) it.next(), i, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private void updateStateForChar(State state, int i, Collection collection) {
        char c = (char) (this.text[i] & 255);
        boolean z = CHAR_MAP[state.getMode()][c] > 0;
        State state2 = null;
        for (int i2 = 0; i2 <= 4; i2++) {
            int i3 = CHAR_MAP[i2][c];
            if (i3 > 0) {
                if (state2 == null) {
                    state2 = state.endBinaryShift(i);
                }
                if (!z || i2 == state.getMode() || i2 == 2) {
                    collection.add(state2.latchAndAppend(i2, i3));
                }
                if (!z && SHIFT_TABLE[state.getMode()][i2] >= 0) {
                    collection.add(state2.shiftAndAppend(i2, i3));
                }
            }
        }
        if (state.getBinaryShiftByteCount() > 0 || CHAR_MAP[state.getMode()][c] == 0) {
            collection.add(state.addBinaryShiftChar(i));
        }
    }

    private static Collection updateStateListForPair(Iterable iterable, int i, int i2) {
        LinkedList linkedList = new LinkedList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            updateStateForPair((State) it.next(), i, i2, linkedList);
        }
        return simplifyStates(linkedList);
    }

    private static void updateStateForPair(State state, int i, int i2, Collection collection) {
        State endBinaryShift = state.endBinaryShift(i);
        collection.add(endBinaryShift.latchAndAppend(4, i2));
        if (state.getMode() != 4) {
            collection.add(endBinaryShift.shiftAndAppend(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(endBinaryShift.latchAndAppend(2, 16 - i2).latchAndAppend(2, 1));
        }
        if (state.getBinaryShiftByteCount() > 0) {
            collection.add(state.addBinaryShiftChar(i).addBinaryShiftChar(i + 1));
        }
    }

    private static Collection simplifyStates(Iterable iterable) {
        LinkedList linkedList = new LinkedList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            State state = (State) it.next();
            Iterator it2 = linkedList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    linkedList.add(state);
                    break;
                }
                State state2 = (State) it2.next();
                if (state2.isBetterThanOrEqualTo(state)) {
                    break;
                } else if (state.isBetterThanOrEqualTo(state2)) {
                    it2.remove();
                }
            }
        }
        return linkedList;
    }
}
