package com.salesforce.marketingcloud.storage;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.analytics.b;
import com.salesforce.marketingcloud.messages.Region;
import com.salesforce.marketingcloud.util.c;
import java.util.List;

@SuppressLint({"UnknownNullness"})
public interface a {
    int a();

    int a(int i);

    int a(String[] strArr);

    void a(b bVar, c cVar) throws Exception;

    int b(int i);

    int b(b bVar, c cVar) throws Exception;

    List<b> b(Region region, c cVar);

    List<b> c(c cVar);

    boolean c(int i);

    int d();

    int e();

    int g(int i);

    List<b> g(c cVar);

    List<b> h(c cVar);

    List<b> i(c cVar);

    List<b> o(c cVar);
}
