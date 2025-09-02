package com.google.android.gms.cloudmessaging;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Objects;

@SafeParcelable.Class(creator = "CloudMessageCreator")
public final class CloudMessage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CloudMessage> CREATOR = new zza();
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    @SafeParcelable.Field(id = 1)
    final Intent zza;
    private Map zzb;

    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }

    @SafeParcelable.Constructor
    @KeepForSdk
    public CloudMessage(@SafeParcelable.Param(id = 1) Intent intent) {
        this.zza = intent;
    }

    private static int zzb(String str) {
        if (Objects.equals(str, "high")) {
            return 1;
        }
        return Objects.equals(str, "normal") ? 2 : 0;
    }

    public String getCollapseKey() {
        return this.zza.getStringExtra(Constants.MessagePayloadKeys.COLLAPSE_KEY);
    }

    public synchronized Map<String, String> getData() {
        try {
            if (this.zzb == null) {
                Bundle extras = this.zza.getExtras();
                ArrayMap arrayMap = new ArrayMap();
                if (extras != null) {
                    for (String next : extras.keySet()) {
                        Object obj = extras.get(next);
                        if (obj instanceof String) {
                            String str = (String) obj;
                            if (!next.startsWith(Constants.MessagePayloadKeys.RESERVED_PREFIX) && !next.equals(Constants.MessagePayloadKeys.FROM) && !next.equals("message_type") && !next.equals(Constants.MessagePayloadKeys.COLLAPSE_KEY)) {
                                arrayMap.put(next, str);
                            }
                        }
                    }
                }
                this.zzb = arrayMap;
            }
        } finally {
            while (true) {
            }
        }
        return this.zzb;
    }

    public String getFrom() {
        return this.zza.getStringExtra(Constants.MessagePayloadKeys.FROM);
    }

    public Intent getIntent() {
        return this.zza;
    }

    public String getMessageId() {
        String stringExtra = this.zza.getStringExtra(Constants.MessagePayloadKeys.MSGID);
        return stringExtra == null ? this.zza.getStringExtra(Constants.MessagePayloadKeys.MSGID_SERVER) : stringExtra;
    }

    public String getMessageType() {
        return this.zza.getStringExtra("message_type");
    }

    public int getOriginalPriority() {
        String stringExtra = this.zza.getStringExtra(Constants.MessagePayloadKeys.ORIGINAL_PRIORITY);
        if (stringExtra == null) {
            stringExtra = this.zza.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return zzb(stringExtra);
    }

    public int getPriority() {
        String stringExtra = this.zza.getStringExtra(Constants.MessagePayloadKeys.DELIVERED_PRIORITY);
        if (stringExtra == null) {
            if (Objects.equals(this.zza.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_REDUCED_V19), "1")) {
                return 2;
            }
            stringExtra = this.zza.getStringExtra(Constants.MessagePayloadKeys.PRIORITY_V19);
        }
        return zzb(stringExtra);
    }

    public byte[] getRawData() {
        return this.zza.getByteArrayExtra(Constants.MessagePayloadKeys.RAW_DATA);
    }

    public String getSenderId() {
        return this.zza.getStringExtra(Constants.MessagePayloadKeys.SENDER_ID);
    }

    public long getSentTime() {
        Bundle extras = this.zza.getExtras();
        Object obj = extras != null ? extras.get(Constants.MessagePayloadKeys.SENT_TIME) : null;
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Long.parseLong((String) obj);
        } catch (NumberFormatException unused) {
            Log.w("CloudMessage", "Invalid sent time: ".concat(String.valueOf(obj)));
            return 0;
        }
    }

    public String getTo() {
        return this.zza.getStringExtra(Constants.MessagePayloadKeys.TO);
    }

    public int getTtl() {
        Bundle extras = this.zza.getExtras();
        Object obj = extras != null ? extras.get(Constants.MessagePayloadKeys.TTL) : null;
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (!(obj instanceof String)) {
            return 0;
        }
        try {
            return Integer.parseInt((String) obj);
        } catch (NumberFormatException unused) {
            Log.w("CloudMessage", "Invalid TTL: ".concat(String.valueOf(obj)));
            return 0;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    /* access modifiers changed from: package-private */
    public final Integer zza() {
        if (this.zza.hasExtra(Constants.MessagePayloadKeys.PRODUCT_ID)) {
            return Integer.valueOf(this.zza.getIntExtra(Constants.MessagePayloadKeys.PRODUCT_ID, 0));
        }
        return null;
    }
}
