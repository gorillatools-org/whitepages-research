package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.systrace.Systrace;
import com.google.firebase.perf.FirebasePerformance;

@DoNotStrip
final class IntBufferBatchMountItem implements BatchMountItem {
    static final int INSTRUCTION_CREATE = 2;
    static final int INSTRUCTION_DELETE = 4;
    static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    static final int INSTRUCTION_INSERT = 8;
    static final int INSTRUCTION_REMOVE = 16;
    static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    static final int INSTRUCTION_UPDATE_OVERFLOW_INSET = 1024;
    static final int INSTRUCTION_UPDATE_PADDING = 512;
    static final int INSTRUCTION_UPDATE_PROPS = 32;
    static final int INSTRUCTION_UPDATE_STATE = 64;
    static final String TAG = "IntBufferBatchMountItem";
    private final int mCommitNumber;
    private final int[] mIntBuffer;
    private final int mIntBufferLen;
    private final Object[] mObjBuffer;
    private final int mObjBufferLen;
    private final int mSurfaceId;

    IntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        this.mSurfaceId = i;
        this.mCommitNumber = i2;
        this.mIntBuffer = iArr;
        this.mObjBuffer = objArr;
        this.mIntBufferLen = iArr.length;
        this.mObjBufferLen = objArr.length;
    }

    private void beginMarkers(String str) {
        Systrace.beginSection(0, "IntBufferBatchMountItem::" + str);
        int i = this.mCommitNumber;
        if (i > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, (String) null, i);
        }
    }

    private void endMarkers() {
        int i = this.mCommitNumber;
        if (i > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, (String) null, i);
        }
        Systrace.endSection(0);
    }

    public void execute(MountingManager mountingManager) {
        int i;
        int i2;
        long j;
        int i3;
        int i4;
        int i5;
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(TAG, "Skipping batch of MountItems; no SurfaceMountingManager found for [%d].", Integer.valueOf(this.mSurfaceId));
        } else if (surfaceManager.isStopped()) {
            FLog.e(TAG, "Skipping batch of MountItems; was stopped [%d].", Integer.valueOf(this.mSurfaceId));
        } else {
            if (ReactNativeFeatureFlags.enableFabricLogs()) {
                FLog.d(TAG, "Executing IntBufferBatchMountItem on surface [%d]", (Object) Integer.valueOf(this.mSurfaceId));
            }
            beginMarkers("mountViews");
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.mIntBufferLen) {
                int[] iArr = this.mIntBuffer;
                int i8 = i6 + 1;
                int i9 = iArr[i6];
                int i10 = i9 & -2;
                if ((i9 & 1) != 0) {
                    int i11 = iArr[i8];
                    i8 = i6 + 2;
                    i = i11;
                } else {
                    i = 1;
                }
                long j2 = 0;
                Systrace.beginSection(0, "IntBufferBatchMountItem::mountInstructions::" + nameForInstructionString(i10), new String[]{"numInstructions", String.valueOf(i)}, 2);
                int i12 = i7;
                i6 = i8;
                int i13 = 0;
                while (i13 < i) {
                    if (i10 == 2) {
                        String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i12]);
                        int[] iArr2 = this.mIntBuffer;
                        int i14 = iArr2[i6];
                        Object[] objArr = this.mObjBuffer;
                        ReadableMap readableMap = (ReadableMap) objArr[i12 + 1];
                        int i15 = i12 + 3;
                        StateWrapper stateWrapper = (StateWrapper) objArr[i12 + 2];
                        i12 += 4;
                        int i16 = i6 + 2;
                        i2 = i13;
                        surfaceManager.createView(fabricComponentName, i14, readableMap, stateWrapper, (EventEmitterWrapper) objArr[i15], iArr2[i6 + 1] == 1);
                        i6 = i16;
                    } else {
                        i2 = i13;
                        if (i10 == 4) {
                            surfaceManager.deleteView(this.mIntBuffer[i6]);
                            i6++;
                        } else if (i10 == 8) {
                            int[] iArr3 = this.mIntBuffer;
                            int i17 = iArr3[i6];
                            int i18 = i6 + 2;
                            i6 += 3;
                            surfaceManager.addViewAt(iArr3[i6 + 1], i17, iArr3[i18]);
                        } else if (i10 == 16) {
                            int[] iArr4 = this.mIntBuffer;
                            int i19 = i6 + 2;
                            i6 += 3;
                            surfaceManager.removeViewAt(iArr4[i6], iArr4[i6 + 1], iArr4[i19]);
                        } else {
                            if (i10 == 32) {
                                i4 = i6 + 1;
                                i5 = i12 + 1;
                                surfaceManager.updateProps(this.mIntBuffer[i6], (ReadableMap) this.mObjBuffer[i12]);
                            } else if (i10 == 64) {
                                i4 = i6 + 1;
                                i5 = i12 + 1;
                                surfaceManager.updateState(this.mIntBuffer[i6], (StateWrapper) this.mObjBuffer[i12]);
                            } else {
                                if (i10 == 128) {
                                    int[] iArr5 = this.mIntBuffer;
                                    j = 0;
                                    surfaceManager.updateLayout(iArr5[i6], iArr5[i6 + 1], iArr5[i6 + 2], iArr5[i6 + 3], iArr5[i6 + 4], iArr5[i6 + 5], iArr5[i6 + 6], iArr5[i6 + 7]);
                                    i6 += 8;
                                } else {
                                    j = 0;
                                    if (i10 == 512) {
                                        int[] iArr6 = this.mIntBuffer;
                                        i3 = i6 + 5;
                                        surfaceManager.updatePadding(iArr6[i6], iArr6[i6 + 1], iArr6[i6 + 2], iArr6[i6 + 3], iArr6[i6 + 4]);
                                    } else if (i10 == 1024) {
                                        int[] iArr7 = this.mIntBuffer;
                                        i3 = i6 + 5;
                                        surfaceManager.updateOverflowInset(iArr7[i6], iArr7[i6 + 1], iArr7[i6 + 2], iArr7[i6 + 3], iArr7[i6 + 4]);
                                    } else if (i10 == 256) {
                                        surfaceManager.updateEventEmitter(this.mIntBuffer[i6], (EventEmitterWrapper) this.mObjBuffer[i12]);
                                        i6++;
                                        i12++;
                                    } else {
                                        throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i10 + " at index: " + i6);
                                    }
                                    i6 = i3;
                                }
                                i13 = i2 + 1;
                                j2 = j;
                            }
                            i6 = i4;
                            i12 = i5;
                        }
                    }
                    j = 0;
                    i13 = i2 + 1;
                    j2 = j;
                }
                Systrace.endSection(j2);
                i7 = i12;
            }
            endMarkers();
        }
    }

    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public boolean isBatchEmpty() {
        return this.mIntBufferLen == 0;
    }

    public String toString() {
        int i;
        int i2;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("IntBufferBatchMountItem [surface:%d]:\n", new Object[]{Integer.valueOf(this.mSurfaceId)}));
            int i3 = 0;
            int i4 = 0;
            while (i3 < this.mIntBufferLen) {
                int[] iArr = this.mIntBuffer;
                int i5 = i3 + 1;
                int i6 = iArr[i3];
                int i7 = i6 & -2;
                int i8 = 1;
                if ((i6 & 1) != 0) {
                    i8 = iArr[i5];
                    i5 = i3 + 2;
                }
                i3 = i5;
                int i9 = 0;
                while (true) {
                    if (i9 < i8) {
                        if (i7 == 2) {
                            String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i4]);
                            i4 += 4;
                            int i10 = i3 + 1;
                            i3 += 2;
                            sb.append(String.format("CREATE [%d] - layoutable:%d - %s\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), Integer.valueOf(this.mIntBuffer[i10]), fabricComponentName}));
                        } else if (i7 == 4) {
                            sb.append(String.format("DELETE [%d]\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3])}));
                            i3++;
                        } else if (i7 == 8) {
                            int i11 = i3 + 2;
                            i3 += 3;
                            sb.append(String.format("INSERT [%d]->[%d] @%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), Integer.valueOf(this.mIntBuffer[i3 + 1]), Integer.valueOf(this.mIntBuffer[i11])}));
                        } else if (i7 == 16) {
                            int i12 = i3 + 2;
                            i3 += 3;
                            sb.append(String.format("REMOVE [%d]->[%d] @%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), Integer.valueOf(this.mIntBuffer[i3 + 1]), Integer.valueOf(this.mIntBuffer[i12])}));
                        } else {
                            String str = "<hidden>";
                            if (i7 == 32) {
                                i = i4 + 1;
                                Object obj = this.mObjBuffer[i4];
                                if (FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
                                    str = obj != null ? obj.toString() : "<null>";
                                }
                                i2 = i3 + 1;
                                sb.append(String.format("UPDATE PROPS [%d]: %s\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), str}));
                            } else if (i7 == 64) {
                                i = i4 + 1;
                                StateWrapper stateWrapper = (StateWrapper) this.mObjBuffer[i4];
                                if (FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
                                    str = stateWrapper != null ? stateWrapper.toString() : "<null>";
                                }
                                i2 = i3 + 1;
                                sb.append(String.format("UPDATE STATE [%d]: %s\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), str}));
                            } else if (i7 == 128) {
                                int[] iArr2 = this.mIntBuffer;
                                int i13 = iArr2[i3];
                                int i14 = iArr2[i3 + 1];
                                int i15 = iArr2[i3 + 2];
                                int i16 = iArr2[i3 + 3];
                                int i17 = iArr2[i3 + 4];
                                int i18 = iArr2[i3 + 5];
                                int i19 = i3 + 7;
                                int i20 = iArr2[i3 + 6];
                                i3 += 8;
                                sb.append(String.format("UPDATE LAYOUT [%d]->[%d]: x:%d y:%d w:%d h:%d displayType:%d layoutDirection: %d\n", new Object[]{Integer.valueOf(i14), Integer.valueOf(i13), Integer.valueOf(i15), Integer.valueOf(i16), Integer.valueOf(i17), Integer.valueOf(i18), Integer.valueOf(i20), Integer.valueOf(iArr2[i19])}));
                            } else if (i7 == 512) {
                                int i21 = i3 + 4;
                                i3 += 5;
                                sb.append(String.format("UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), Integer.valueOf(this.mIntBuffer[i3 + 1]), Integer.valueOf(this.mIntBuffer[i3 + 2]), Integer.valueOf(this.mIntBuffer[i3 + 3]), Integer.valueOf(this.mIntBuffer[i21])}));
                            } else if (i7 == 1024) {
                                int i22 = i3 + 4;
                                i3 += 5;
                                sb.append(String.format("UPDATE OVERFLOWINSET [%d]: left:%d top:%d right:%d bottom:%d\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3]), Integer.valueOf(this.mIntBuffer[i3 + 1]), Integer.valueOf(this.mIntBuffer[i3 + 2]), Integer.valueOf(this.mIntBuffer[i3 + 3]), Integer.valueOf(this.mIntBuffer[i22])}));
                            } else if (i7 == 256) {
                                i4++;
                                sb.append(String.format("UPDATE EVENTEMITTER [%d]\n", new Object[]{Integer.valueOf(this.mIntBuffer[i3])}));
                                i3++;
                            } else {
                                String str2 = TAG;
                                FLog.e(str2, "String so far: " + sb.toString());
                                throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i7 + " at index: " + i3);
                            }
                            i3 = i2;
                            i4 = i;
                        }
                        i9++;
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            FLog.e(TAG, "Caught exception trying to print", (Throwable) e);
            StringBuilder sb2 = new StringBuilder();
            for (int i23 = 0; i23 < this.mIntBufferLen; i23++) {
                sb2.append(this.mIntBuffer[i23]);
                sb2.append(", ");
            }
            FLog.e(TAG, sb2.toString());
            for (int i24 = 0; i24 < this.mObjBufferLen; i24++) {
                String str3 = TAG;
                Object obj2 = this.mObjBuffer[i24];
                FLog.e(str3, obj2 != null ? obj2.toString() : "null");
            }
            return "";
        }
    }

    private static String nameForInstructionString(int i) {
        if (i == 2) {
            return "CREATE";
        }
        if (i == 4) {
            return FirebasePerformance.HttpMethod.DELETE;
        }
        if (i == 8) {
            return "INSERT";
        }
        if (i == 16) {
            return "REMOVE";
        }
        if (i == 32) {
            return "UPDATE_PROPS";
        }
        if (i == 64) {
            return "UPDATE_STATE";
        }
        if (i == 128) {
            return "UPDATE_LAYOUT";
        }
        if (i == 512) {
            return "UPDATE_PADDING";
        }
        if (i == 1024) {
            return "UPDATE_OVERFLOW_INSET";
        }
        if (i == 256) {
            return "UPDATE_EVENT_EMITTER";
        }
        return "UNKNOWN";
    }
}
