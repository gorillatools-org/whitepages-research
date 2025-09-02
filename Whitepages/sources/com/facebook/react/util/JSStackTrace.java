package com.facebook.react.util;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.devsupport.StackTraceHelper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;

public final class JSStackTrace {
    public static final String COLUMN_KEY = "column";
    private static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");
    public static final String FILE_KEY = "file";
    public static final JSStackTrace INSTANCE = new JSStackTrace();
    public static final String LINE_NUMBER_KEY = "lineNumber";
    public static final String METHOD_NAME_KEY = "methodName";

    private JSStackTrace() {
    }

    public static final String format(String str, ReadableArray readableArray) {
        Intrinsics.checkNotNullParameter(str, StackTraceHelper.MESSAGE_KEY);
        Intrinsics.checkNotNullParameter(readableArray, StackTraceHelper.STACK_KEY);
        StringBuilder sb = new StringBuilder(str);
        sb.append(", stack:\n");
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            if (map != null) {
                sb.append(map.getString("methodName"));
                sb.append("@");
                sb.append(INSTANCE.parseFileId(map));
                if (!map.hasKey("lineNumber") || map.isNull("lineNumber") || map.getType("lineNumber") != ReadableType.Number) {
                    sb.append(-1);
                } else {
                    sb.append(map.getInt("lineNumber"));
                }
                if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                    sb.append(":");
                    sb.append(map.getInt("column"));
                }
                sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final String parseFileId(ReadableMap readableMap) {
        String string;
        if (!readableMap.hasKey("file") || readableMap.isNull("file") || readableMap.getType("file") != ReadableType.String || (string = readableMap.getString("file")) == null) {
            return "";
        }
        Matcher matcher = FILE_ID_PATTERN.matcher(string);
        if (!matcher.find()) {
            return "";
        }
        String group = matcher.group(1);
        return group + ":";
    }
}
