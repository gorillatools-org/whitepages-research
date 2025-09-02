package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.parser.moshi.JsonReader;

public class DropShadowEffectParser {
    private static final JsonReader.Options DROP_SHADOW_EFFECT_NAMES = JsonReader.Options.of("ef");
    private static final JsonReader.Options INNER_EFFECT_NAMES = JsonReader.Options.of("nm", "v");
    private AnimatableColorValue color;
    private AnimatableFloatValue direction;
    private AnimatableFloatValue distance;
    private AnimatableFloatValue opacity;
    private AnimatableFloatValue radius;

    /* access modifiers changed from: package-private */
    public DropShadowEffect parse(JsonReader jsonReader, LottieComposition lottieComposition) {
        AnimatableFloatValue animatableFloatValue;
        AnimatableFloatValue animatableFloatValue2;
        AnimatableFloatValue animatableFloatValue3;
        AnimatableFloatValue animatableFloatValue4;
        while (jsonReader.hasNext()) {
            if (jsonReader.selectName(DROP_SHADOW_EFFECT_NAMES) != 0) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    maybeParseInnerEffect(jsonReader, lottieComposition);
                }
                jsonReader.endArray();
            }
        }
        AnimatableColorValue animatableColorValue = this.color;
        if (animatableColorValue == null || (animatableFloatValue = this.opacity) == null || (animatableFloatValue2 = this.direction) == null || (animatableFloatValue3 = this.distance) == null || (animatableFloatValue4 = this.radius) == null) {
            return null;
        }
        return new DropShadowEffect(animatableColorValue, animatableFloatValue, animatableFloatValue2, animatableFloatValue3, animatableFloatValue4);
    }

    private void maybeParseInnerEffect(JsonReader jsonReader, LottieComposition lottieComposition) {
        jsonReader.beginObject();
        String str = "";
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(INNER_EFFECT_NAMES);
            if (selectName != 0) {
                if (selectName == 1) {
                    str.hashCode();
                    char c = 65535;
                    switch (str.hashCode()) {
                        case 353103893:
                            if (str.equals("Distance")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 397447147:
                            if (str.equals("Opacity")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 1041377119:
                            if (str.equals("Direction")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 1379387491:
                            if (str.equals("Shadow Color")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 1383710113:
                            if (str.equals("Softness")) {
                                c = 4;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            this.distance = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                            break;
                        case 1:
                            this.opacity = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
                            break;
                        case 2:
                            this.direction = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
                            break;
                        case 3:
                            this.color = AnimatableValueParser.parseColor(jsonReader, lottieComposition);
                            break;
                        case 4:
                            this.radius = AnimatableValueParser.parseFloat(jsonReader, lottieComposition);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                } else {
                    jsonReader.skipName();
                    jsonReader.skipValue();
                }
            } else {
                str = jsonReader.nextString();
            }
        }
        jsonReader.endObject();
    }
}
