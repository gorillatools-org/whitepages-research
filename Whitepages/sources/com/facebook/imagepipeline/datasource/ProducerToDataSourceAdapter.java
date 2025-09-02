package com.facebook.imagepipeline.datasource;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProducerToDataSourceAdapter extends AbstractProducerToDataSourceAdapter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    public /* synthetic */ ProducerToDataSourceAdapter(Producer producer, SettableProducerContext settableProducerContext, RequestListener2 requestListener2, DefaultConstructorMarker defaultConstructorMarker) {
        this(producer, settableProducerContext, requestListener2);
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DataSource create(Producer producer, SettableProducerContext settableProducerContext, RequestListener2 requestListener2) {
            Intrinsics.checkNotNullParameter(producer, "producer");
            Intrinsics.checkNotNullParameter(settableProducerContext, "settableProducerContext");
            Intrinsics.checkNotNullParameter(requestListener2, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            return new ProducerToDataSourceAdapter(producer, settableProducerContext, requestListener2, (DefaultConstructorMarker) null);
        }
    }

    private ProducerToDataSourceAdapter(Producer producer, SettableProducerContext settableProducerContext, RequestListener2 requestListener2) {
        super(producer, settableProducerContext, requestListener2);
    }
}
