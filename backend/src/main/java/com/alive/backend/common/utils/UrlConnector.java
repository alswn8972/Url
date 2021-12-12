package com.alive.backend.common.utils;



import java.io.IOException;
import java.io.InputStream;
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

    public static URL getFinalURL(URL url) throws IOException {
        try {

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setInstanceFollowRedirects(false);
            con.connect();

            // Header에서 Status Code를 뽑는다.
            int resCode = con.getResponseCode();
            System.out.println(resCode);
            // http코드가 301(영구이동), 302(임시 이동), 303(기타 위치 보기) 이면 또다시 이 함수를 태운다. 재귀함수.
            if (resCode == HttpURLConnection.HTTP_SEE_OTHER || resCode == HttpURLConnection.HTTP_MOVED_PERM
                    || resCode == HttpURLConnection.HTTP_MOVED_TEMP) {

                String Location = con.getHeaderField("Location");
                if (Location.startsWith("/")) {
                    Location = url.getProtocol() + "://" + url.getHost() + Location;
                }
                System.out.println(Location);
                return getFinalURL(new URL(Location));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return url;

    }
}
