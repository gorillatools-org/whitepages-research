package com.airbnb.lottie.network;

import java.net.HttpURLConnection;
import java.net.URL;

public class DefaultLottieNetworkFetcher implements LottieNetworkFetcher {
    public LottieFetchResult fetchSync(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.connect();
        return new DefaultLottieFetchResult(httpURLConnection);
    }
}
