package com.salesforce.marketingcloud.storage;

import android.annotation.SuppressLint;
import com.salesforce.marketingcloud.messages.Message;
import com.salesforce.marketingcloud.util.c;
import java.util.List;

@SuppressLint({"UnknownNullness"})
public interface k {
    int a(String str);

    int a(String str, int i);

    Message a(String str, c cVar);

    List<Message> a(c cVar);

    void a(Message message, c cVar) throws Exception;

    List<Message> b(c cVar);

    int e(int i);
}
