package com.salesforce.marketingcloud.messages.iam;

import android.os.Parcel;
import android.os.Parcelable;
import com.salesforce.marketingcloud.messages.iam.InAppMessage;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

public class j implements Parcelable {
    public static final Parcelable.Creator<j> CREATOR = new a();
    public static final String e = "buttonClicked";
    public static final String f = "dismissed";
    public static final String g = "autoDismissed";
    public static final String h = "unknown";
    final String a;
    final InAppMessage.Button b;
    final long c;
    final Date d;

    class a implements Parcelable.Creator<j> {
        a() {
        }

        /* renamed from: a */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }

        /* renamed from: a */
        public j[] newArray(int i) {
            return new j[i];
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @interface b {
    }

    protected j(Parcel parcel) {
        this.a = parcel.readString();
        this.b = (InAppMessage.Button) parcel.readParcelable(InAppMessage.Button.class.getClassLoader());
        this.c = parcel.readLong();
        this.d = new Date(parcel.readLong());
    }

    static j a(Date date, long j) {
        return new j(g, date, j, (InAppMessage.Button) null);
    }

    static j e() {
        return new j(h, new Date(), -1, (InAppMessage.Button) null);
    }

    public long b() {
        return this.c;
    }

    public Date c() {
        return this.d;
    }

    public String d() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeParcelable(this.b, i);
        parcel.writeLong(this.c);
        parcel.writeLong(this.d.getTime());
    }

    private j(String str, Date date, long j, InAppMessage.Button button) {
        this.a = str;
        this.c = j;
        this.b = button;
        this.d = date;
    }

    static j a(Date date, long j, InAppMessage.Button button) {
        return new j(e, date, j, button);
    }

    static j b(Date date, long j) {
        return new j("dismissed", date, j, (InAppMessage.Button) null);
    }

    public InAppMessage.Button a() {
        return this.b;
    }
}
