package com.google.firebase.remoteconfig.interop.rollouts;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;
import com.google.firebase.encoders.config.Configurator;
import com.google.firebase.encoders.config.EncoderConfig;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import java.io.IOException;

public final class AutoRolloutAssignmentEncoder implements Configurator {
    public static final int CODEGEN_VERSION = 2;
    public static final Configurator CONFIG = new AutoRolloutAssignmentEncoder();

    private AutoRolloutAssignmentEncoder() {
    }

    public void configure(EncoderConfig<?> encoderConfig) {
        RolloutAssignmentEncoder rolloutAssignmentEncoder = RolloutAssignmentEncoder.INSTANCE;
        encoderConfig.registerEncoder((Class<U>) RolloutAssignment.class, (ObjectEncoder<? super U>) rolloutAssignmentEncoder);
        encoderConfig.registerEncoder((Class<U>) AutoValue_RolloutAssignment.class, (ObjectEncoder<? super U>) rolloutAssignmentEncoder);
    }

    private static final class RolloutAssignmentEncoder implements ObjectEncoder<RolloutAssignment> {
        static final RolloutAssignmentEncoder INSTANCE = new RolloutAssignmentEncoder();
        private static final FieldDescriptor PARAMETERKEY_DESCRIPTOR = FieldDescriptor.of("parameterKey");
        private static final FieldDescriptor PARAMETERVALUE_DESCRIPTOR = FieldDescriptor.of("parameterValue");
        private static final FieldDescriptor ROLLOUTID_DESCRIPTOR = FieldDescriptor.of(ConfigContainer.ROLLOUT_METADATA_ID);
        private static final FieldDescriptor TEMPLATEVERSION_DESCRIPTOR = FieldDescriptor.of(RemoteConfigConstants.ResponseFieldKey.TEMPLATE_VERSION_NUMBER);
        private static final FieldDescriptor VARIANTID_DESCRIPTOR = FieldDescriptor.of("variantId");

        private RolloutAssignmentEncoder() {
        }

        public void encode(RolloutAssignment rolloutAssignment, ObjectEncoderContext objectEncoderContext) throws IOException {
            objectEncoderContext.add(ROLLOUTID_DESCRIPTOR, (Object) rolloutAssignment.getRolloutId());
            objectEncoderContext.add(VARIANTID_DESCRIPTOR, (Object) rolloutAssignment.getVariantId());
            objectEncoderContext.add(PARAMETERKEY_DESCRIPTOR, (Object) rolloutAssignment.getParameterKey());
            objectEncoderContext.add(PARAMETERVALUE_DESCRIPTOR, (Object) rolloutAssignment.getParameterValue());
            objectEncoderContext.add(TEMPLATEVERSION_DESCRIPTOR, rolloutAssignment.getTemplateVersion());
        }
    }
}
