package net.minidev.json.writer;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONAware;
import net.minidev.json.JSONAwareEx;
import net.minidev.json.JSONObject;

public class JsonReader {
    public JsonReaderI DEFAULT = new DefaultMapper(this);
    public JsonReaderI DEFAULT_ORDERED = new DefaultMapperOrdered(this);
    private final ConcurrentHashMap cache;

    public JsonReader() {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(100);
        this.cache = concurrentHashMap;
        concurrentHashMap.put(Date.class, BeansMapper.MAPPER_DATE);
        concurrentHashMap.put(int[].class, ArraysMapper.MAPPER_PRIM_INT);
        concurrentHashMap.put(Integer[].class, ArraysMapper.MAPPER_INT);
        concurrentHashMap.put(short[].class, ArraysMapper.MAPPER_PRIM_INT);
        concurrentHashMap.put(Short[].class, ArraysMapper.MAPPER_INT);
        concurrentHashMap.put(long[].class, ArraysMapper.MAPPER_PRIM_LONG);
        concurrentHashMap.put(Long[].class, ArraysMapper.MAPPER_LONG);
        concurrentHashMap.put(byte[].class, ArraysMapper.MAPPER_PRIM_BYTE);
        concurrentHashMap.put(Byte[].class, ArraysMapper.MAPPER_BYTE);
        concurrentHashMap.put(char[].class, ArraysMapper.MAPPER_PRIM_CHAR);
        concurrentHashMap.put(Character[].class, ArraysMapper.MAPPER_CHAR);
        concurrentHashMap.put(float[].class, ArraysMapper.MAPPER_PRIM_FLOAT);
        concurrentHashMap.put(Float[].class, ArraysMapper.MAPPER_FLOAT);
        concurrentHashMap.put(double[].class, ArraysMapper.MAPPER_PRIM_DOUBLE);
        concurrentHashMap.put(Double[].class, ArraysMapper.MAPPER_DOUBLE);
        concurrentHashMap.put(boolean[].class, ArraysMapper.MAPPER_PRIM_BOOL);
        concurrentHashMap.put(Boolean[].class, ArraysMapper.MAPPER_BOOL);
        concurrentHashMap.put(JSONAwareEx.class, this.DEFAULT);
        concurrentHashMap.put(JSONAware.class, this.DEFAULT);
        concurrentHashMap.put(JSONArray.class, this.DEFAULT);
        concurrentHashMap.put(JSONObject.class, this.DEFAULT);
    }
}
