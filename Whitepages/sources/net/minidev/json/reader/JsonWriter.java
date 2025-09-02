package net.minidev.json.reader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONAwareEx;
import net.minidev.json.JSONStreamAware;
import net.minidev.json.JSONStreamAwareEx;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

public class JsonWriter {
    public static final JsonWriterI EnumWriter = new JsonWriterI() {
        public void writeJSONString(Enum enumR, Appendable appendable, JSONStyle jSONStyle) {
            jSONStyle.writeString(appendable, enumR.name());
        }
    };
    public static final JsonWriterI JSONIterableWriter = new JsonWriterI() {
        public void writeJSONString(Iterable iterable, Appendable appendable, JSONStyle jSONStyle) {
            jSONStyle.arrayStart(appendable);
            boolean z = true;
            for (Object next : iterable) {
                if (z) {
                    jSONStyle.arrayfirstObject(appendable);
                    z = false;
                } else {
                    jSONStyle.arrayNextElm(appendable);
                }
                if (next == null) {
                    appendable.append("null");
                } else {
                    JSONValue.writeJSONString(next, appendable, jSONStyle);
                }
                jSONStyle.arrayObjectEnd(appendable);
            }
            jSONStyle.arrayStop(appendable);
        }
    };
    public static final JsonWriterI JSONJSONAwareExWriter = new JsonWriterI() {
        public void writeJSONString(JSONAwareEx jSONAwareEx, Appendable appendable, JSONStyle jSONStyle) {
            appendable.append(jSONAwareEx.toJSONString(jSONStyle));
        }
    };
    public static final JsonWriterI JSONJSONAwareWriter = new JsonWriterI() {
        public void writeJSONString(JSONAware jSONAware, Appendable appendable, JSONStyle jSONStyle) {
            appendable.append(jSONAware.toJSONString());
        }
    };
    public static final JsonWriterI JSONMapWriter = new JsonWriterI() {
        public void writeJSONString(Map map, Appendable appendable, JSONStyle jSONStyle) {
            jSONStyle.objectStart(appendable);
            boolean z = true;
            for (Map.Entry entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null || !jSONStyle.ignoreNull()) {
                    if (z) {
                        jSONStyle.objectFirstStart(appendable);
                        z = false;
                    } else {
                        jSONStyle.objectNext(appendable);
                    }
                    JsonWriter.writeJSONKV(entry.getKey().toString(), value, appendable, jSONStyle);
                }
            }
            jSONStyle.objectStop(appendable);
        }
    };
    public static final JsonWriterI JSONStreamAwareExWriter = new JsonWriterI() {
        public void writeJSONString(JSONStreamAwareEx jSONStreamAwareEx, Appendable appendable, JSONStyle jSONStyle) {
            jSONStreamAwareEx.writeJSONString(appendable, jSONStyle);
        }
    };
    public static final JsonWriterI JSONStreamAwareWriter = new JsonWriterI() {
        public void writeJSONString(JSONStreamAwareEx jSONStreamAwareEx, Appendable appendable, JSONStyle jSONStyle) {
            jSONStreamAwareEx.writeJSONString(appendable);
        }
    };
    public static final JsonWriterI arrayWriter = new ArrayWriter();
    public static final JsonWriterI beansWriter = new BeansWriter();
    public static final JsonWriterI beansWriterASM = new BeansWriterASM();
    public static final JsonWriterI toStringWriter = new JsonWriterI() {
        public void writeJSONString(Object obj, Appendable appendable, JSONStyle jSONStyle) {
            appendable.append(obj.toString());
        }
    };
    private ConcurrentHashMap data = new ConcurrentHashMap();
    private LinkedList writerInterfaces = new LinkedList();

    public JsonWriter() {
        init();
    }

    static class WriterByInterface {
        public Class _interface;
        public JsonWriterI _writer;

        public WriterByInterface(Class cls, JsonWriterI jsonWriterI) {
            this._interface = cls;
            this._writer = jsonWriterI;
        }
    }

    public JsonWriterI getWriterByInterface(Class cls) {
        Iterator it = this.writerInterfaces.iterator();
        while (it.hasNext()) {
            WriterByInterface writerByInterface = (WriterByInterface) it.next();
            if (writerByInterface._interface.isAssignableFrom(cls)) {
                return writerByInterface._writer;
            }
        }
        return null;
    }

    public JsonWriterI getWrite(Class cls) {
        return (JsonWriterI) this.data.get(cls);
    }

    public void init() {
        registerWriter(new JsonWriterI() {
            public void writeJSONString(String str, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.writeString(appendable, str);
            }
        }, String.class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(Double d, Appendable appendable, JSONStyle jSONStyle) {
                if (d.isInfinite()) {
                    appendable.append("null");
                } else {
                    appendable.append(d.toString());
                }
            }
        }, Double.class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(Date date, Appendable appendable, JSONStyle jSONStyle) {
                appendable.append('\"');
                JSONValue.escape(date.toString(), appendable, jSONStyle);
                appendable.append('\"');
            }
        }, Date.class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(Float f, Appendable appendable, JSONStyle jSONStyle) {
                if (f.isInfinite()) {
                    appendable.append("null");
                } else {
                    appendable.append(f.toString());
                }
            }
        }, Float.class);
        JsonWriterI jsonWriterI = toStringWriter;
        registerWriter(jsonWriterI, Integer.class, Long.class, Byte.class, Short.class, BigInteger.class, BigDecimal.class);
        registerWriter(jsonWriterI, Boolean.class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(int[] iArr, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (int i : iArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Integer.toString(i));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, int[].class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(short[] sArr, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (short s : sArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Short.toString(s));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, short[].class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(long[] jArr, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (long j : jArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Long.toString(j));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, long[].class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(float[] fArr, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (float f : fArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Float.toString(f));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, float[].class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(double[] dArr, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (double d : dArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Double.toString(d));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, double[].class);
        registerWriter(new JsonWriterI() {
            public void writeJSONString(boolean[] zArr, Appendable appendable, JSONStyle jSONStyle) {
                jSONStyle.arrayStart(appendable);
                boolean z = false;
                for (boolean z2 : zArr) {
                    if (z) {
                        jSONStyle.objectNext(appendable);
                    } else {
                        z = true;
                    }
                    appendable.append(Boolean.toString(z2));
                }
                jSONStyle.arrayStop(appendable);
            }
        }, boolean[].class);
        registerWriterInterface(JSONStreamAwareEx.class, JSONStreamAwareExWriter);
        registerWriterInterface(JSONStreamAware.class, JSONStreamAwareWriter);
        registerWriterInterface(JSONAwareEx.class, JSONJSONAwareExWriter);
        registerWriterInterface(JSONAware.class, JSONJSONAwareWriter);
        registerWriterInterface(Map.class, JSONMapWriter);
        registerWriterInterface(Iterable.class, JSONIterableWriter);
        registerWriterInterface(Enum.class, EnumWriter);
        registerWriterInterface(Number.class, jsonWriterI);
    }

    public void registerWriterInterfaceLast(Class cls, JsonWriterI jsonWriterI) {
        this.writerInterfaces.addLast(new WriterByInterface(cls, jsonWriterI));
    }

    public void registerWriterInterface(Class cls, JsonWriterI jsonWriterI) {
        registerWriterInterfaceLast(cls, jsonWriterI);
    }

    public void registerWriter(JsonWriterI jsonWriterI, Class... clsArr) {
        for (Class put : clsArr) {
            this.data.put(put, jsonWriterI);
        }
    }

    public static void writeJSONKV(String str, Object obj, Appendable appendable, JSONStyle jSONStyle) {
        if (str == null) {
            appendable.append("null");
        } else if (!jSONStyle.mustProtectKey(str)) {
            appendable.append(str);
        } else {
            appendable.append('\"');
            JSONValue.escape(str, appendable, jSONStyle);
            appendable.append('\"');
        }
        jSONStyle.objectEndOfKey(appendable);
        if (obj instanceof String) {
            jSONStyle.writeString(appendable, (String) obj);
        } else {
            JSONValue.writeJSONString(obj, appendable, jSONStyle);
        }
        jSONStyle.objectElmStop(appendable);
    }
}
