package com.facebook.imagepipeline.listener;

import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ForwardingRequestListener2 implements RequestListener2 {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List requestListeners;

    public ForwardingRequestListener2(Set set) {
        if (set == null) {
            this.requestListeners = new ArrayList();
            return;
        }
        ArrayList arrayList = new ArrayList(set.size());
        this.requestListeners = arrayList;
        CollectionsKt.filterNotNullTo(set, arrayList);
    }

    public void onProducerEvent(ProducerContext producerContext, String str, String str2) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        Intrinsics.checkNotNullParameter(str2, "producerEventName");
        for (RequestListener2 onProducerEvent : this.requestListeners) {
            try {
                onProducerEvent.onProducerEvent(producerContext, str, str2);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onIntermediateChunkStart", (Throwable) e);
            }
        }
    }

    public void onProducerFinishWithCancellation(ProducerContext producerContext, String str, Map map) {
        for (RequestListener2 onProducerFinishWithCancellation : this.requestListeners) {
            try {
                onProducerFinishWithCancellation.onProducerFinishWithCancellation(producerContext, str, map);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onProducerFinishWithCancellation", (Throwable) e);
            }
        }
    }

    public void onProducerFinishWithFailure(ProducerContext producerContext, String str, Throwable th, Map map) {
        for (RequestListener2 onProducerFinishWithFailure : this.requestListeners) {
            try {
                onProducerFinishWithFailure.onProducerFinishWithFailure(producerContext, str, th, map);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onProducerFinishWithFailure", (Throwable) e);
            }
        }
    }

    public void onProducerFinishWithSuccess(ProducerContext producerContext, String str, Map map) {
        for (RequestListener2 onProducerFinishWithSuccess : this.requestListeners) {
            try {
                onProducerFinishWithSuccess.onProducerFinishWithSuccess(producerContext, str, map);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onProducerFinishWithSuccess", (Throwable) e);
            }
        }
    }

    public void onProducerStart(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        for (RequestListener2 onProducerStart : this.requestListeners) {
            try {
                onProducerStart.onProducerStart(producerContext, str);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onProducerStart", (Throwable) e);
            }
        }
    }

    public void onRequestCancellation(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        for (RequestListener2 onRequestCancellation : this.requestListeners) {
            try {
                onRequestCancellation.onRequestCancellation(producerContext);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onRequestCancellation", (Throwable) e);
            }
        }
    }

    public void onRequestFailure(ProducerContext producerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(th, "throwable");
        for (RequestListener2 onRequestFailure : this.requestListeners) {
            try {
                onRequestFailure.onRequestFailure(producerContext, th);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onRequestFailure", (Throwable) e);
            }
        }
    }

    public void onRequestStart(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        for (RequestListener2 onRequestStart : this.requestListeners) {
            try {
                onRequestStart.onRequestStart(producerContext);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onRequestStart", (Throwable) e);
            }
        }
    }

    public void onRequestSuccess(ProducerContext producerContext) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        for (RequestListener2 onRequestSuccess : this.requestListeners) {
            try {
                onRequestSuccess.onRequestSuccess(producerContext);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onRequestSuccess", (Throwable) e);
            }
        }
    }

    public void onUltimateProducerReached(ProducerContext producerContext, String str, boolean z) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        for (RequestListener2 onUltimateProducerReached : this.requestListeners) {
            try {
                onUltimateProducerReached.onUltimateProducerReached(producerContext, str, z);
            } catch (Exception e) {
                FLog.e("ForwardingRequestListener2", "InternalListener exception in " + "onProducerFinishWithSuccess", (Throwable) e);
            }
        }
    }

    public boolean requiresExtraMap(ProducerContext producerContext, String str) {
        Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        Intrinsics.checkNotNullParameter(str, "producerName");
        Iterable<RequestListener2> iterable = this.requestListeners;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (RequestListener2 requiresExtraMap : iterable) {
            if (requiresExtraMap.requiresExtraMap(producerContext, str)) {
                return true;
            }
        }
        return false;
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
