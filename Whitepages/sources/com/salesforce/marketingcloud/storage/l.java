package com.salesforce.marketingcloud.storage;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.util.c;
import java.util.List;

@SuppressLint({"UnknownNullness"})
public interface l {
    Region a(String str, c cVar);

    List<Region> a(int i, c cVar);

    void a(Region region, c cVar) throws Exception;

    void a(String str, boolean z);

    List<String> c(String str, int i);

    List<String> d(int i);

    int f(int i);

    void l();

    Region m(c cVar);
}
