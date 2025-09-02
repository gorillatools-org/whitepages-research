package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;

public interface RNSVGMarkerManagerInterface<T extends View> {
    void setAlign(T t, String str);

    void setClipPath(T t, String str);

    void setClipRule(T t, int i);

    void setColor(T t, Integer num);

    void setDisplay(T t, String str);

    void setFill(T t, Dynamic dynamic);

    void setFillOpacity(T t, float f);

    void setFillRule(T t, int i);

    void setFilter(T t, String str);

    void setFont(T t, Dynamic dynamic);

    void setFontSize(T t, Dynamic dynamic);

    void setFontWeight(T t, Dynamic dynamic);

    void setMarkerEnd(T t, String str);

    void setMarkerHeight(T t, Dynamic dynamic);

    void setMarkerMid(T t, String str);

    void setMarkerStart(T t, String str);

    void setMarkerUnits(T t, String str);

    void setMarkerWidth(T t, Dynamic dynamic);

    void setMask(T t, String str);

    void setMatrix(T t, ReadableArray readableArray);

    void setMeetOrSlice(T t, int i);

    void setMinX(T t, float f);

    void setMinY(T t, float f);

    void setName(T t, String str);

    void setOpacity(T t, float f);

    void setOrient(T t, String str);

    void setPointerEvents(T t, String str);

    void setPropList(T t, ReadableArray readableArray);

    void setRefX(T t, Dynamic dynamic);

    void setRefY(T t, Dynamic dynamic);

    void setResponsible(T t, boolean z);

    void setStroke(T t, Dynamic dynamic);

    void setStrokeDasharray(T t, Dynamic dynamic);

    void setStrokeDashoffset(T t, float f);

    void setStrokeLinecap(T t, int i);

    void setStrokeLinejoin(T t, int i);

    void setStrokeMiterlimit(T t, float f);

    void setStrokeOpacity(T t, float f);

    void setStrokeWidth(T t, Dynamic dynamic);

    void setVbHeight(T t, float f);

    void setVbWidth(T t, float f);

    void setVectorEffect(T t, int i);
}
