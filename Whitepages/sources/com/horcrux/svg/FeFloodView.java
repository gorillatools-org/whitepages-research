package com.horcrux.svg;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FeFloodView extends FilterPrimitiveView {
    private static final Pattern regex = Pattern.compile("[0-9.-]+");
    public ReadableArray floodColor;
    public float floodOpacity = 1.0f;

    public FeFloodView(ReactContext reactContext) {
        super(reactContext);
    }

    public void setFloodColor(Dynamic dynamic) {
        if (dynamic == null || dynamic.isNull()) {
            this.floodColor = null;
            invalidate();
        } else if (dynamic.getType().equals(ReadableType.Map)) {
            setFloodColor(dynamic.asMap());
        } else {
            ReadableType type = dynamic.getType();
            int i = 0;
            if (type.equals(ReadableType.Number)) {
                this.floodColor = JavaOnlyArray.of(0, Integer.valueOf(dynamic.asInt()));
            } else if (type.equals(ReadableType.Array)) {
                this.floodColor = dynamic.asArray();
            } else {
                JavaOnlyArray javaOnlyArray = new JavaOnlyArray();
                javaOnlyArray.pushInt(0);
                Matcher matcher = regex.matcher(dynamic.asString());
                while (matcher.find()) {
                    double parseDouble = Double.parseDouble(matcher.group());
                    int i2 = i + 1;
                    if (i < 3) {
                        parseDouble /= 255.0d;
                    }
                    javaOnlyArray.pushDouble(parseDouble);
                    i = i2;
                }
                this.floodColor = javaOnlyArray;
            }
            invalidate();
        }
    }

    public void setFloodColor(ReadableMap readableMap) {
        if (readableMap == null) {
            this.floodColor = null;
            invalidate();
            return;
        }
        int i = readableMap.getInt("type");
        if (i == 0) {
            ReadableType type = readableMap.getType("payload");
            if (type.equals(ReadableType.Number)) {
                this.floodColor = JavaOnlyArray.of(0, Integer.valueOf(readableMap.getInt("payload")));
            } else if (type.equals(ReadableType.Map)) {
                this.floodColor = JavaOnlyArray.of(0, readableMap.getMap("payload"));
            }
        } else if (i == 1) {
            this.floodColor = JavaOnlyArray.of(1, readableMap.getString("brushRef"));
        } else {
            this.floodColor = JavaOnlyArray.of(Integer.valueOf(i));
        }
        invalidate();
    }

    public void setFloodOpacity(float f) {
        this.floodOpacity = f;
        invalidate();
    }

    public Bitmap applyFilter(HashMap hashMap, Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setFlags(129);
        paint.setStyle(Paint.Style.FILL);
        setupPaint(paint, this.floodOpacity, this.floodColor);
        canvas.drawPaint(paint);
        return createBitmap;
    }

    private void setupPaint(Paint paint, float f, ReadableArray readableArray) {
        int i;
        if (readableArray.getInt(0) == 0) {
            if (readableArray.size() == 2) {
                if (readableArray.getType(1) == ReadableType.Map) {
                    i = ColorPropConverter.getColor(readableArray.getMap(1), getContext()).intValue();
                } else {
                    i = readableArray.getInt(1);
                }
                paint.setColor((Math.round(((float) (i >>> 24)) * f) << 24) | (i & 16777215));
                return;
            }
            paint.setARGB((int) (readableArray.size() > 4 ? readableArray.getDouble(4) * ((double) f) * 255.0d : (double) (f * 255.0f)), (int) (readableArray.getDouble(1) * 255.0d), (int) (readableArray.getDouble(2) * 255.0d), (int) (readableArray.getDouble(3) * 255.0d));
        }
    }
}
