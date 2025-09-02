package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.content.GradientStroke;
import com.airbnb.lottie.model.content.GradientType;
import com.airbnb.lottie.model.content.ShapeStroke;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.value.Keyframe;
import java.util.ArrayList;
import java.util.Collections;

abstract class GradientStrokeParser {
    private static final JsonReader.Options DASH_PATTERN_NAMES = JsonReader.Options.of("n", "v");
    private static final JsonReader.Options GRADIENT_NAMES = JsonReader.Options.of("p", "k");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "g", "o", "t", "s", "e", "w", "lc", "lj", "ml", "hd", "d");

    static GradientStroke parse(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str;
        AnimatableGradientColorValue animatableGradientColorValue;
        JsonReader jsonReader2 = jsonReader;
        LottieComposition lottieComposition2 = lottieComposition;
        ArrayList arrayList = new ArrayList();
        float f = 0.0f;
        String str2 = null;
        GradientType gradientType = null;
        AnimatableGradientColorValue animatableGradientColorValue2 = null;
        AnimatablePointValue animatablePointValue = null;
        AnimatablePointValue animatablePointValue2 = null;
        AnimatableFloatValue animatableFloatValue = null;
        ShapeStroke.LineCapType lineCapType = null;
        ShapeStroke.LineJoinType lineJoinType = null;
        AnimatableFloatValue animatableFloatValue2 = null;
        boolean z = false;
        AnimatableIntegerValue animatableIntegerValue = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader2.selectName(NAMES)) {
                case 0:
                    str2 = jsonReader.nextString();
                    continue;
                case 1:
                    str = str2;
                    jsonReader.beginObject();
                    int i = -1;
                    while (jsonReader.hasNext()) {
                        int selectName = jsonReader2.selectName(GRADIENT_NAMES);
                        if (selectName != 0) {
                            animatableGradientColorValue = animatableGradientColorValue2;
                            if (selectName != 1) {
                                jsonReader.skipName();
                                jsonReader.skipValue();
                            } else {
                                animatableGradientColorValue2 = AnimatableValueParser.parseGradientColor(jsonReader2, lottieComposition2, i);
                            }
                        } else {
                            animatableGradientColorValue = animatableGradientColorValue2;
                            i = jsonReader.nextInt();
                        }
                        animatableGradientColorValue2 = animatableGradientColorValue;
                    }
                    AnimatableGradientColorValue animatableGradientColorValue3 = animatableGradientColorValue2;
                    jsonReader.endObject();
                    break;
                case 2:
                    String str3 = str2;
                    animatableIntegerValue = AnimatableValueParser.parseInteger(jsonReader, lottieComposition);
                    continue;
                case 3:
                    str = str2;
                    gradientType = jsonReader.nextInt() == 1 ? GradientType.LINEAR : GradientType.RADIAL;
                    break;
                case 4:
                    String str4 = str2;
                    animatablePointValue = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
                    continue;
                case 5:
                    String str5 = str2;
                    animatablePointValue2 = AnimatableValueParser.parsePoint(jsonReader, lottieComposition);
                    continue;
                case 6:
                    String str6 = str2;
                    animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                    continue;
                case 7:
                    str = str2;
                    lineCapType = ShapeStroke.LineCapType.values()[jsonReader.nextInt() - 1];
                    break;
                case 8:
                    str = str2;
                    lineJoinType = ShapeStroke.LineJoinType.values()[jsonReader.nextInt() - 1];
                    break;
                case 9:
                    str = str2;
                    f = (float) jsonReader.nextDouble();
                    break;
                case 10:
                    z = jsonReader.nextBoolean();
                    continue;
                case 11:
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        String str7 = null;
                        AnimatableFloatValue animatableFloatValue3 = null;
                        while (jsonReader.hasNext()) {
                            int selectName2 = jsonReader2.selectName(DASH_PATTERN_NAMES);
                            if (selectName2 != 0) {
                                AnimatableFloatValue animatableFloatValue4 = animatableFloatValue2;
                                if (selectName2 != 1) {
                                    jsonReader.skipName();
                                    jsonReader.skipValue();
                                } else {
                                    animatableFloatValue3 = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                                }
                                animatableFloatValue2 = animatableFloatValue4;
                            } else {
                                AnimatableFloatValue animatableFloatValue5 = animatableFloatValue2;
                                str7 = jsonReader.nextString();
                            }
                        }
                        AnimatableFloatValue animatableFloatValue6 = animatableFloatValue2;
                        jsonReader.endObject();
                        if (str7.equals("o")) {
                            animatableFloatValue2 = animatableFloatValue3;
                        } else {
                            if (str7.equals("d") || str7.equals("g")) {
                                lottieComposition2.setHasDashPattern(true);
                                arrayList.add(animatableFloatValue3);
                            }
                            animatableFloatValue2 = animatableFloatValue6;
                        }
                    }
                    AnimatableFloatValue animatableFloatValue7 = animatableFloatValue2;
                    jsonReader.endArray();
                    if (arrayList.size() == 1) {
                        arrayList.add((AnimatableFloatValue) arrayList.get(0));
                    }
                    animatableFloatValue2 = animatableFloatValue7;
                    continue;
                default:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    continue;
            }
            str2 = str;
        }
        String str8 = str2;
        if (animatableIntegerValue == null) {
            animatableIntegerValue = new AnimatableIntegerValue(Collections.singletonList(new Keyframe(100)));
        }
        return new GradientStroke(str8, gradientType, animatableGradientColorValue2, animatableIntegerValue, animatablePointValue, animatablePointValue2, animatableFloatValue, lineCapType, lineJoinType, f, arrayList, animatableFloatValue2, z);
    }
}
