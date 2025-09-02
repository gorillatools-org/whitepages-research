package com.salesforce.marketingcloud.sfmcsdk.components.events;

import com.salesforce.marketingcloud.sfmcsdk.SFMCSdk;
import com.salesforce.marketingcloud.sfmcsdk.components.logging.SFMCSdkLogger;
import com.salesforce.marketingcloud.storage.db.k;
import java.util.Map;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

public abstract class Event {
    private final Category category = Category.ENGAGEMENT;
    public final String id;
    private final Producer producer = Producer.SFMC_SDK;

    public enum Category {
        APPLICATION,
        ENGAGEMENT,
        IDENTITY,
        SYSTEM
    }

    public enum Producer {
        APP,
        SFMC_SDK,
        PUSH,
        CDP
    }

    public abstract Map<String, Object> attributes();

    public abstract String name();

    public Event() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        this.id = uuid;
    }

    public Producer getProducer() {
        return this.producer;
    }

    public Category getCategory() {
        return this.category;
    }

    public final void track() {
        SFMCSdk.Companion.track(this);
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", name());
        jSONObject.put("id", this.id);
        jSONObject.put("producer", getProducer());
        jSONObject.put("category", getCategory());
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry entry : attributes().entrySet()) {
            try {
                Object value = entry.getValue();
                if (value instanceof SFMCSdkEvent) {
                    jSONObject2.put((String) entry.getKey(), ((SFMCSdkEvent) entry.getValue()).toJson());
                } else {
                    boolean z = true;
                    if (!(value instanceof Number ? true : value instanceof String ? true : value instanceof Character)) {
                        z = value instanceof Boolean;
                    }
                    if (z) {
                        jSONObject2.put((String) entry.getKey(), entry.getValue());
                    } else {
                        jSONObject2.put((String) entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception unused) {
                SFMCSdkLogger sFMCSdkLogger = SFMCSdkLogger.INSTANCE;
                String name = jSONObject2.getClass().getName();
                Intrinsics.checkNotNullExpressionValue(name, "this::class.java.name");
                sFMCSdkLogger.w(name, new Event$toJson$1$1$1$1(entry));
            }
        }
        Unit unit = Unit.INSTANCE;
        jSONObject.put(k.a.h, jSONObject2);
        return jSONObject;
    }
}
