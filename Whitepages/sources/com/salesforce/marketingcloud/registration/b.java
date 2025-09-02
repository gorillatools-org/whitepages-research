package com.salesforce.marketingcloud.registration;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class b implements Map<String, String> {
    private final HashMap<String, String> a;
    private final TreeMap<String, String> b;

    b() {
        this.a = new HashMap<>();
        this.b = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    /* renamed from: a */
    public String get(Object obj) {
        String str = this.b.get(obj);
        if (str != null) {
            return this.a.get(str);
        }
        return null;
    }

    /* renamed from: b */
    public String remove(Object obj) {
        return this.a.remove(this.b.remove(obj));
    }

    public void clear() {
        this.a.clear();
        this.b.clear();
    }

    public boolean containsKey(Object obj) {
        return this.b.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.a.containsValue(obj);
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return this.a.entrySet();
    }

    public boolean isEmpty() {
        return this.a.isEmpty();
    }

    public Set<String> keySet() {
        return this.a.keySet();
    }

    public void putAll(Map<? extends String, ? extends String> map) {
        if (map != null && !map.isEmpty()) {
            for (Map.Entry next : map.entrySet()) {
                put((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    public int size() {
        return this.a.size();
    }

    public Collection<String> values() {
        return this.a.values();
    }

    b(Map<String, String> map) {
        this();
        if (map != null) {
            this.a.putAll(map);
            for (String next : this.a.keySet()) {
                this.b.put(next, next);
            }
        }
    }

    /* renamed from: a */
    public String put(String str, String str2) {
        String str3 = null;
        if (str == null) {
            return null;
        }
        String str4 = this.b.get(str);
        if (str4 != null) {
            str3 = this.a.remove(str4);
        }
        this.b.put(str, str);
        this.a.put(str, str2);
        return str3;
    }
}
