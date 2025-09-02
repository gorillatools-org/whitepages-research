package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

interface zai {
    Object zaa(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException;
}
