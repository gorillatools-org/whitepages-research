package com.airbnb.lottie.parser;

import com.airbnb.lottie.parser.moshi.JsonReader;

interface ValueParser {
    Object parse(JsonReader jsonReader, float f);
}
