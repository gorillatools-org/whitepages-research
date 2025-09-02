package com.google.android.datatransport.runtime;

import android.support.v4.media.session.MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics;
import com.google.android.datatransport.runtime.firebase.transport.GlobalMetrics;
import com.google.android.datatransport.runtime.firebase.transport.LogEventDropped;
import com.google.android.datatransport.runtime.firebase.transport.LogSourceMetrics;
import com.google.android.datatransport.runtime.firebase.transport.StorageMetrics;
import com.google.android.datatransport.runtime.firebase.transport.TimeWindow;
import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.encoders.proto.AtProtobuf;

public final class AutoProtoEncoderDoNotUseEncoder implements Configurator {
    public static final Configurator CONFIG = new AutoProtoEncoderDoNotUseEncoder();

    private AutoProtoEncoderDoNotUseEncoder() {
    }

    public void configure(EncoderConfig encoderConfig) {
        encoderConfig.registerEncoder(ProtoEncoderDoNotUse.class, ProtoEncoderDoNotUseEncoder.INSTANCE);
        encoderConfig.registerEncoder(ClientMetrics.class, ClientMetricsEncoder.INSTANCE);
        encoderConfig.registerEncoder(TimeWindow.class, TimeWindowEncoder.INSTANCE);
        encoderConfig.registerEncoder(LogSourceMetrics.class, LogSourceMetricsEncoder.INSTANCE);
        encoderConfig.registerEncoder(LogEventDropped.class, LogEventDroppedEncoder.INSTANCE);
        encoderConfig.registerEncoder(GlobalMetrics.class, GlobalMetricsEncoder.INSTANCE);
        encoderConfig.registerEncoder(StorageMetrics.class, StorageMetricsEncoder.INSTANCE);
    }

    private static final class ProtoEncoderDoNotUseEncoder implements ObjectEncoder {
        private static final FieldDescriptor CLIENTMETRICS_DESCRIPTOR = FieldDescriptor.of("clientMetrics");
        static final ProtoEncoderDoNotUseEncoder INSTANCE = new ProtoEncoderDoNotUseEncoder();

        private ProtoEncoderDoNotUseEncoder() {
        }

        public /* bridge */ /* synthetic */ void encode(Object obj, Object obj2) {
            MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver$$ExternalSyntheticThrowCCEIfNotNull0.m(obj);
            encode((ProtoEncoderDoNotUse) null, (ObjectEncoderContext) obj2);
        }

        public void encode(ProtoEncoderDoNotUse protoEncoderDoNotUse, ObjectEncoderContext objectEncoderContext) {
            throw null;
        }
    }

    private static final class ClientMetricsEncoder implements ObjectEncoder {
        private static final FieldDescriptor APPNAMESPACE_DESCRIPTOR = FieldDescriptor.builder("appNamespace").withProperty(AtProtobuf.builder().tag(4).build()).build();
        private static final FieldDescriptor GLOBALMETRICS_DESCRIPTOR = FieldDescriptor.builder("globalMetrics").withProperty(AtProtobuf.builder().tag(3).build()).build();
        static final ClientMetricsEncoder INSTANCE = new ClientMetricsEncoder();
        private static final FieldDescriptor LOGSOURCEMETRICS_DESCRIPTOR = FieldDescriptor.builder("logSourceMetrics").withProperty(AtProtobuf.builder().tag(2).build()).build();
        private static final FieldDescriptor WINDOW_DESCRIPTOR = FieldDescriptor.builder("window").withProperty(AtProtobuf.builder().tag(1).build()).build();

        private ClientMetricsEncoder() {
        }

        public void encode(ClientMetrics clientMetrics, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(WINDOW_DESCRIPTOR, (Object) clientMetrics.getWindowInternal());
            objectEncoderContext.add(LOGSOURCEMETRICS_DESCRIPTOR, (Object) clientMetrics.getLogSourceMetricsList());
            objectEncoderContext.add(GLOBALMETRICS_DESCRIPTOR, (Object) clientMetrics.getGlobalMetricsInternal());
            objectEncoderContext.add(APPNAMESPACE_DESCRIPTOR, (Object) clientMetrics.getAppNamespace());
        }
    }

    private static final class TimeWindowEncoder implements ObjectEncoder {
        private static final FieldDescriptor ENDMS_DESCRIPTOR = FieldDescriptor.builder("endMs").withProperty(AtProtobuf.builder().tag(2).build()).build();
        static final TimeWindowEncoder INSTANCE = new TimeWindowEncoder();
        private static final FieldDescriptor STARTMS_DESCRIPTOR = FieldDescriptor.builder("startMs").withProperty(AtProtobuf.builder().tag(1).build()).build();

        private TimeWindowEncoder() {
        }

        public void encode(TimeWindow timeWindow, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(STARTMS_DESCRIPTOR, timeWindow.getStartMs());
            objectEncoderContext.add(ENDMS_DESCRIPTOR, timeWindow.getEndMs());
        }
    }

    private static final class LogSourceMetricsEncoder implements ObjectEncoder {
        static final LogSourceMetricsEncoder INSTANCE = new LogSourceMetricsEncoder();
        private static final FieldDescriptor LOGEVENTDROPPED_DESCRIPTOR = FieldDescriptor.builder("logEventDropped").withProperty(AtProtobuf.builder().tag(2).build()).build();
        private static final FieldDescriptor LOGSOURCE_DESCRIPTOR = FieldDescriptor.builder("logSource").withProperty(AtProtobuf.builder().tag(1).build()).build();

        private LogSourceMetricsEncoder() {
        }

        public void encode(LogSourceMetrics logSourceMetrics, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(LOGSOURCE_DESCRIPTOR, (Object) logSourceMetrics.getLogSource());
            objectEncoderContext.add(LOGEVENTDROPPED_DESCRIPTOR, (Object) logSourceMetrics.getLogEventDroppedList());
        }
    }

    private static final class LogEventDroppedEncoder implements ObjectEncoder {
        private static final FieldDescriptor EVENTSDROPPEDCOUNT_DESCRIPTOR = FieldDescriptor.builder("eventsDroppedCount").withProperty(AtProtobuf.builder().tag(1).build()).build();
        static final LogEventDroppedEncoder INSTANCE = new LogEventDroppedEncoder();
        private static final FieldDescriptor REASON_DESCRIPTOR = FieldDescriptor.builder("reason").withProperty(AtProtobuf.builder().tag(3).build()).build();

        private LogEventDroppedEncoder() {
        }

        public void encode(LogEventDropped logEventDropped, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(EVENTSDROPPEDCOUNT_DESCRIPTOR, logEventDropped.getEventsDroppedCount());
            objectEncoderContext.add(REASON_DESCRIPTOR, (Object) logEventDropped.getReason());
        }
    }

    private static final class GlobalMetricsEncoder implements ObjectEncoder {
        static final GlobalMetricsEncoder INSTANCE = new GlobalMetricsEncoder();
        private static final FieldDescriptor STORAGEMETRICS_DESCRIPTOR = FieldDescriptor.builder("storageMetrics").withProperty(AtProtobuf.builder().tag(1).build()).build();

        private GlobalMetricsEncoder() {
        }

        public void encode(GlobalMetrics globalMetrics, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(STORAGEMETRICS_DESCRIPTOR, (Object) globalMetrics.getStorageMetricsInternal());
        }
    }

    private static final class StorageMetricsEncoder implements ObjectEncoder {
        private static final FieldDescriptor CURRENTCACHESIZEBYTES_DESCRIPTOR = FieldDescriptor.builder("currentCacheSizeBytes").withProperty(AtProtobuf.builder().tag(1).build()).build();
        static final StorageMetricsEncoder INSTANCE = new StorageMetricsEncoder();
        private static final FieldDescriptor MAXCACHESIZEBYTES_DESCRIPTOR = FieldDescriptor.builder("maxCacheSizeBytes").withProperty(AtProtobuf.builder().tag(2).build()).build();

        private StorageMetricsEncoder() {
        }

        public void encode(StorageMetrics storageMetrics, ObjectEncoderContext objectEncoderContext) {
            objectEncoderContext.add(CURRENTCACHESIZEBYTES_DESCRIPTOR, storageMetrics.getCurrentCacheSizeBytes());
            objectEncoderContext.add(MAXCACHESIZEBYTES_DESCRIPTOR, storageMetrics.getMaxCacheSizeBytes());
        }
    }
}
