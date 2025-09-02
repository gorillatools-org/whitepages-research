package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@SafeParcelable.Class(creator = "WebImageCreator")
public final class WebImage extends AbstractSafeParcelable {
    public static final Parcelable.Creator<WebImage> CREATOR = new zah();
    @SafeParcelable.VersionField(id = 1)
    final int zaa;
    @SafeParcelable.Field(getter = "getUrl", id = 2)
    private final Uri zab;
    @SafeParcelable.Field(getter = "getWidth", id = 3)
    private final int zac;
    @SafeParcelable.Field(getter = "getHeight", id = 4)
    private final int zad;

    @SafeParcelable.Constructor
    WebImage(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Uri uri, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) int i3) {
        this.zaa = i;
        this.zab = uri;
        this.zac = i2;
        this.zad = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof WebImage)) {
            WebImage webImage = (WebImage) obj;
            return Objects.equal(this.zab, webImage.zab) && this.zac == webImage.zac && this.zad == webImage.zad;
        }
    }

    public int getHeight() {
        return this.zad;
    }

    public Uri getUrl() {
        return this.zab;
    }

    public int getWidth() {
        return this.zac;
    }

    public int hashCode() {
        return Objects.hashCode(this.zab, Integer.valueOf(this.zac), Integer.valueOf(this.zad));
    }

    @KeepForSdk
    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("url", this.zab.toString());
            jSONObject.put("width", this.zac);
            jSONObject.put("height", this.zad);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public String toString() {
        return String.format(Locale.US, "Image %dx%d %s", new Object[]{Integer.valueOf(this.zac), Integer.valueOf(this.zad), this.zab.toString()});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = this.zaa;
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, i2);
        SafeParcelWriter.writeParcelable(parcel, 2, getUrl(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getHeight());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public WebImage(Uri uri) throws IllegalArgumentException {
        this(uri, 0, 0);
    }

    public WebImage(Uri uri, int i, int i2) throws IllegalArgumentException {
        this(1, uri, i, i2);
        if (uri == null) {
            throw new IllegalArgumentException("url cannot be null");
        } else if (i < 0 || i2 < 0) {
            throw new IllegalArgumentException("width and height must not be negative");
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @com.google.android.gms.common.annotation.KeepForSdk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WebImage(org.json.JSONObject r5) throws java.lang.IllegalArgumentException {
        /*
            r4 = this;
            android.net.Uri r0 = android.net.Uri.EMPTY
            java.lang.String r1 = "url"
            boolean r2 = r5.has(r1)
            if (r2 == 0) goto L_0x0012
            java.lang.String r1 = r5.getString(r1)     // Catch:{ JSONException -> 0x0012 }
            android.net.Uri r0 = android.net.Uri.parse(r1)     // Catch:{ JSONException -> 0x0012 }
        L_0x0012:
            java.lang.String r1 = "width"
            r2 = 0
            int r1 = r5.optInt(r1, r2)
            java.lang.String r3 = "height"
            int r5 = r5.optInt(r3, r2)
            r4.<init>(r0, r1, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.images.WebImage.<init>(org.json.JSONObject):void");
    }
}
