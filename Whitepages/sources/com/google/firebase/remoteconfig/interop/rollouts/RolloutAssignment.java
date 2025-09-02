package com.google.firebase.remoteconfig.interop.rollouts;

import com.google.auto.value.AutoValue;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import com.google.firebase.remoteconfig.interop.rollouts.AutoValue_RolloutAssignment;
import org.json.JSONException;
import org.json.JSONObject;

@AutoValue
@Encodable
public abstract class RolloutAssignment {
    private static final String PARAMETER_KEY = "parameterKey";
    private static final String PARAMETER_VALUE = "parameterValue";
    public static final DataEncoder ROLLOUT_ASSIGNMENT_JSON_ENCODER = new JsonDataEncoderBuilder().configureWith(AutoRolloutAssignmentEncoder.CONFIG).build();
    private static final String ROLLOUT_ID = "rolloutId";
    private static final String TEMPLATE_VERSION = "templateVersion";
    private static final String VARIANT_ID = "variantId";

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract RolloutAssignment build();

        public abstract Builder setParameterKey(String str);

        public abstract Builder setParameterValue(String str);

        public abstract Builder setRolloutId(String str);

        public abstract Builder setTemplateVersion(long j);

        public abstract Builder setVariantId(String str);
    }

    public abstract String getParameterKey();

    public abstract String getParameterValue();

    public abstract String getRolloutId();

    public abstract long getTemplateVersion();

    public abstract String getVariantId();

    public static RolloutAssignment create(JSONObject jSONObject) throws JSONException {
        return builder().setRolloutId(jSONObject.getString("rolloutId")).setVariantId(jSONObject.getString("variantId")).setParameterKey(jSONObject.getString(PARAMETER_KEY)).setParameterValue(jSONObject.getString(PARAMETER_VALUE)).setTemplateVersion(jSONObject.getLong("templateVersion")).build();
    }

    public static RolloutAssignment create(String str) throws JSONException {
        return create(new JSONObject(str));
    }

    public static Builder builder() {
        return new AutoValue_RolloutAssignment.Builder();
    }
}
