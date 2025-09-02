package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.BlurEffect;
import com.airbnb.lottie.parser.moshi.JsonReader;

abstract class BlurEffectParser {
    private static final JsonReader.Options BLUR_EFFECT_NAMES = JsonReader.Options.of("ef");
    private static final JsonReader.Options INNER_BLUR_EFFECT_NAMES = JsonReader.Options.of("ty", "v");

    static BlurEffect parse(JsonReader jsonReader, LottieComposition lottieComposition) {
        BlurEffect blurEffect = null;
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(BLUR_EFFECT_NAMES) != 0) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    BlurEffect maybeParseInnerEffect = maybeParseInnerEffect(jsonReader, lottieComposition);
                    if (maybeParseInnerEffect != null) {
                        blurEffect = maybeParseInnerEffect;
                    }
                }
                jsonReader.endArray();
            }
        }
        return blurEffect;
    }

    private static BlurEffect maybeParseInnerEffect(JsonReader jsonReader, LottieComposition lottieComposition) {
        jsonReader.beginObject();
        BlurEffect blurEffect = null;
        while (true) {
            boolean z = false;
            while (true) {
                if (jsonReader.hasNext()) {
                    int selectName = jsonReader.selectName(INNER_BLUR_EFFECT_NAMES);
                    if (selectName != 0) {
                        if (selectName != 1) {
                            jsonReader.skipName();
                            jsonReader.skipValue();
                        } else if (z) {
                            blurEffect = new BlurEffect(AnimatableValueParser.parseFloat(jsonReader, lottieComposition));
                        } else {
                            jsonReader.skipValue();
                        }
                    } else if (jsonReader.nextInt() == 0) {
                        z = true;
                    }
                } else {
                    jsonReader.endObject();
                    return blurEffect;
                }
            }
        }
    }
}
