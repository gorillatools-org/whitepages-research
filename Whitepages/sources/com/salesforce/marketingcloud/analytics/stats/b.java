package com.salesforce.marketingcloud.analytics.stats;

import com.salesforce.marketingcloud.g;
import java.util.Date;
import org.json.JSONException;

public class b {
    public static final int f = 100;
    public static final int g = 101;
    public static final int h = 102;
    public static final int i = 103;
    public static final int j = 104;
    public static final int k = 105;
    public static final int l = 106;
    public static final int m = 107;
    d a;
    private Integer b;
    private int c;
    private Date d;
    private boolean e;

    private b(Integer num, int i2, Date date, d dVar, boolean z) {
        this.b = num;
        this.c = i2;
        this.d = date;
        this.a = dVar;
        this.e = z;
    }

    public static b a(int i2, Date date, d dVar, boolean z) {
        return new b((Integer) null, i2, date, dVar, z);
    }

    public Integer b() {
        return this.b;
    }

    public d c() {
        return this.a;
    }

    public int d() {
        return this.c;
    }

    public boolean e() {
        return this.e;
    }

    public void a(Date date) {
        int time = (int) (((double) (date.getTime() - this.d.getTime())) / 1000.0d);
        if (time <= 0) {
            time = 1;
        }
        try {
            this.a.b(time);
            this.e = true;
        } catch (JSONException e2) {
            g.b(c.k, e2, "Unable to finalize event [%d]", Integer.valueOf(this.c));
        }
    }

    public static b a(int i2, int i3, Date date, d dVar, boolean z) {
        return new b(Integer.valueOf(i2), i3, date, dVar, z);
    }

    public Date a() {
        return this.d;
    }

    public void a(boolean z) {
        this.e = z;
    }
}
