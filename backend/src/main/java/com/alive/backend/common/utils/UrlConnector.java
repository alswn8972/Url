package com.alive.backend.common.utils;



import java.io.IOException;
import java.net.*;

public class UrlConnector {
    public static int getUrlStatusCode(String urlAddress) {
        int stateCode = 0;
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            if(urlConnection instanceof HttpURLConnection){
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.setConnectTimeout(2000);
                httpURLConnection.connect();

                stateCode = httpURLConnection.getResponseCode();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stateCode;
    }
}
