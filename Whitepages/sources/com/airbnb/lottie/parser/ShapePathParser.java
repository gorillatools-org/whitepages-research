package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;
import com.airbnb.lottie.model.content.ShapePath;
import com.airbnb.lottie.parser.moshi.JsonReader;

abstract class ShapePathParser {
    static JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "ks", "hd");

    static ShapePath parse(JsonReader jsonReader, LottieComposition lottieComposition) {
        String str = null;
        int i = 0;
        boolean z = false;
        AnimatableShapeValue animatableShapeValue = null;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
            } else if (selectName == 1) {
                i = jsonReader.nextInt();
            } else if (selectName == 2) {
                animatableShapeValue = AnimatableValueParser.parseShapeData(jsonReader, lottieComposition);
            } else if (selectName != 3) {
                jsonReader.skipValue();
            } else {
                z = jsonReader.nextBoolean();
            }
        }
        return new ShapePath(str, i, animatableShapeValue, z);
    }
}
