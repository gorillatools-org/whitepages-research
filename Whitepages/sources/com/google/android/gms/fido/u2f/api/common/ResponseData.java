package com.google.android.gms.fido.u2f.api.common;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import org.json.JSONObject;

@Deprecated
public abstract class ResponseData extends AbstractSafeParcelable implements ReflectedParcelable {
    public abstract JSONObject toJsonObject();
}
