package com.facebook.appevents.ml;

import com.facebook.appevents.ml.ModelManager;
import com.facebook.internal.instrument.crashshield.CrashShieldHandler;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class Model {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Map mapping = MapsKt.hashMapOf(TuplesKt.to("embedding.weight", "embed.weight"), TuplesKt.to("dense1.weight", "fc1.weight"), TuplesKt.to("dense2.weight", "fc2.weight"), TuplesKt.to("dense3.weight", "fc3.weight"), TuplesKt.to("dense1.bias", "fc1.bias"), TuplesKt.to("dense2.bias", "fc2.bias"), TuplesKt.to("dense3.bias", "fc3.bias"));
    private final MTensor convs0Bias;
    private final MTensor convs0Weight;
    private final MTensor convs1Bias;
    private final MTensor convs1Weight;
    private final MTensor convs2Bias;
    private final MTensor convs2Weight;
    private final MTensor embedding;
    private final MTensor fc1Bias;
    private final MTensor fc1Weight;
    private final MTensor fc2Bias;
    private final MTensor fc2Weight;
    private final Map finalWeights;

    public /* synthetic */ Model(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    private Model(Map map) {
        Object obj = map.get("embed.weight");
        if (obj != null) {
            this.embedding = (MTensor) obj;
            Object obj2 = map.get("convs.0.weight");
            if (obj2 != null) {
                this.convs0Weight = Operator.transpose3D((MTensor) obj2);
                Object obj3 = map.get("convs.1.weight");
                if (obj3 != null) {
                    this.convs1Weight = Operator.transpose3D((MTensor) obj3);
                    Object obj4 = map.get("convs.2.weight");
                    if (obj4 != null) {
                        this.convs2Weight = Operator.transpose3D((MTensor) obj4);
                        Object obj5 = map.get("convs.0.bias");
                        if (obj5 != null) {
                            this.convs0Bias = (MTensor) obj5;
                            Object obj6 = map.get("convs.1.bias");
                            if (obj6 != null) {
                                this.convs1Bias = (MTensor) obj6;
                                Object obj7 = map.get("convs.2.bias");
                                if (obj7 != null) {
                                    this.convs2Bias = (MTensor) obj7;
                                    Object obj8 = map.get("fc1.weight");
                                    if (obj8 != null) {
                                        this.fc1Weight = Operator.transpose2D((MTensor) obj8);
                                        Object obj9 = map.get("fc2.weight");
                                        if (obj9 != null) {
                                            this.fc2Weight = Operator.transpose2D((MTensor) obj9);
                                            Object obj10 = map.get("fc1.bias");
                                            if (obj10 != null) {
                                                this.fc1Bias = (MTensor) obj10;
                                                Object obj11 = map.get("fc2.bias");
                                                if (obj11 != null) {
                                                    this.fc2Bias = (MTensor) obj11;
                                                    this.finalWeights = new HashMap();
                                                    for (String str : SetsKt.setOf(ModelManager.Task.MTML_INTEGRITY_DETECT.toKey(), ModelManager.Task.MTML_APP_EVENT_PREDICTION.toKey())) {
                                                        String str2 = str + ".weight";
                                                        String str3 = str + ".bias";
                                                        MTensor mTensor = (MTensor) map.get(str2);
                                                        MTensor mTensor2 = (MTensor) map.get(str3);
                                                        if (mTensor != null) {
                                                            this.finalWeights.put(str2, Operator.transpose2D(mTensor));
                                                        }
                                                        if (mTensor2 != null) {
                                                            this.finalWeights.put(str3, mTensor2);
                                                        }
                                                    }
                                                    return;
                                                }
                                                throw new IllegalStateException("Required value was null.");
                                            }
                                            throw new IllegalStateException("Required value was null.");
                                        }
                                        throw new IllegalStateException("Required value was null.");
                                    }
                                    throw new IllegalStateException("Required value was null.");
                                }
                                throw new IllegalStateException("Required value was null.");
                            }
                            throw new IllegalStateException("Required value was null.");
                        }
                        throw new IllegalStateException("Required value was null.");
                    }
                    throw new IllegalStateException("Required value was null.");
                }
                throw new IllegalStateException("Required value was null.");
            }
            throw new IllegalStateException("Required value was null.");
        }
        throw new IllegalStateException("Required value was null.");
    }

    public static final /* synthetic */ Map access$getMapping$cp() {
        Class<Model> cls = Model.class;
        if (CrashShieldHandler.isObjectCrashing(cls)) {
            return null;
        }
        try {
            return mapping;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, cls);
            return null;
        }
    }

    public final MTensor predictOnMTML(MTensor mTensor, String[] strArr, String str) {
        if (CrashShieldHandler.isObjectCrashing(this)) {
            return null;
        }
        try {
            Intrinsics.checkNotNullParameter(mTensor, "dense");
            Intrinsics.checkNotNullParameter(strArr, "texts");
            Intrinsics.checkNotNullParameter(str, "task");
            MTensor conv1D = Operator.conv1D(Operator.embedding(strArr, 128, this.embedding), this.convs0Weight);
            Operator.addmv(conv1D, this.convs0Bias);
            Operator.relu(conv1D);
            MTensor conv1D2 = Operator.conv1D(conv1D, this.convs1Weight);
            Operator.addmv(conv1D2, this.convs1Bias);
            Operator.relu(conv1D2);
            MTensor maxPool1D = Operator.maxPool1D(conv1D2, 2);
            MTensor conv1D3 = Operator.conv1D(maxPool1D, this.convs2Weight);
            Operator.addmv(conv1D3, this.convs2Bias);
            Operator.relu(conv1D3);
            MTensor maxPool1D2 = Operator.maxPool1D(conv1D, conv1D.getShape(1));
            MTensor maxPool1D3 = Operator.maxPool1D(maxPool1D, maxPool1D.getShape(1));
            MTensor maxPool1D4 = Operator.maxPool1D(conv1D3, conv1D3.getShape(1));
            Operator.flatten(maxPool1D2, 1);
            Operator.flatten(maxPool1D3, 1);
            Operator.flatten(maxPool1D4, 1);
            MTensor dense = Operator.dense(Operator.concatenate(new MTensor[]{maxPool1D2, maxPool1D3, maxPool1D4, mTensor}), this.fc1Weight, this.fc1Bias);
            Operator.relu(dense);
            MTensor dense2 = Operator.dense(dense, this.fc2Weight, this.fc2Bias);
            Operator.relu(dense2);
            Map map = this.finalWeights;
            MTensor mTensor2 = (MTensor) map.get(str + ".weight");
            Map map2 = this.finalWeights;
            MTensor mTensor3 = (MTensor) map2.get(str + ".bias");
            if (mTensor2 != null) {
                if (mTensor3 != null) {
                    MTensor dense3 = Operator.dense(dense2, mTensor2, mTensor3);
                    Operator.softmax(dense3);
                    return dense3;
                }
            }
            return null;
        } catch (Throwable th) {
            CrashShieldHandler.handleThrowable(th, this);
            return null;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Model build(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            Map parse = parse(file);
            if (parse == null) {
                return null;
            }
            try {
                return new Model(parse, (DefaultConstructorMarker) null);
            } catch (Exception unused) {
                return null;
            }
        }

        private final Map parse(File file) {
            Map parseModelWeights = Utils.parseModelWeights(file);
            if (parseModelWeights == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            Map access$getMapping$cp = Model.access$getMapping$cp();
            for (Map.Entry entry : parseModelWeights.entrySet()) {
                String str = (String) entry.getKey();
                if (access$getMapping$cp.containsKey(entry.getKey()) && (str = (String) access$getMapping$cp.get(entry.getKey())) == null) {
                    return null;
                }
                hashMap.put(str, entry.getValue());
            }
            return hashMap;
        }
    }
}
