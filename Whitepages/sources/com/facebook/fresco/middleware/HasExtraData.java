package com.facebook.fresco.middleware;

import java.util.Map;

public interface HasExtraData {
    Object getExtra(String str);

    Map getExtras();

    void putExtra(String str, Object obj);

    void putExtras(Map map);
}
