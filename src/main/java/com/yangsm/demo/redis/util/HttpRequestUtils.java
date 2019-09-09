package com.yangsm.demo.redis.util;


import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Description:
 *
 * @author xu
 * @date:2018/5/29
 * @Copyright (c) 2018 一碑科技
 */
public class HttpRequestUtils {
    public static HttpURLConnection getUrlConnection(String url, int bufferSize) throws Exception{
        URL target = new URL(url);
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是
        HttpURLConnection conn = (HttpURLConnection) target.openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        conn.setChunkedStreamingMode(bufferSize);
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("timenow", String.valueOf(System.currentTimeMillis()));
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);

        return conn;
    }

}
