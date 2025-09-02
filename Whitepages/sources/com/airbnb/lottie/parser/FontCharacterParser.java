package com.airbnb.lottie.parser;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.FontCharacter;
import com.airbnb.lottie.model.content.ShapeGroup;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;

abstract class FontCharacterParser {
    private static final JsonReader.Options DATA_NAMES = JsonReader.Options.of("shapes");
    private static final JsonReader.Options NAMES = JsonReader.Options.of("ch", "size", "w", "style", "fFamily", Constants.ScionAnalytics.MessageType.DATA_MESSAGE);

    static FontCharacter parse(JsonReader jsonReader, LottieComposition lottieComposition) {
        ArrayList arrayList = new ArrayList();
        jsonReader.beginObject();
        double d = 0.0d;
        String str = null;
        String str2 = null;
        char c = 0;
        double d2 = 0.0d;
        while (jsonReader.hasNext()) {
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                c = jsonReader.nextString().charAt(0);
            } else if (selectName == 1) {
                d2 = jsonReader.nextDouble();
            } else if (selectName == 2) {
                d = jsonReader.nextDouble();
            } else if (selectName == 3) {
                str = jsonReader.nextString();
            } else if (selectName == 4) {
                str2 = jsonReader.nextString();
            } else if (selectName != 5) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    if (jsonReader.selectName(DATA_NAMES) != 0) {
                        jsonReader.skipName();
                        jsonReader.skipValue();
                    } else {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            arrayList.add((ShapeGroup) ContentModelParser.parse(jsonReader, lottieComposition));
                        }
                        jsonReader.endArray();
                    }
                }
                jsonReader.endObject();
            }
        }
        jsonReader.endObject();
        return new FontCharacter(arrayList, c, d2, d, str, str2);
    }
}
