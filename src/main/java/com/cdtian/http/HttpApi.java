package com.cdtian.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class HttpApi {
    public static String getRequest(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.addRequestProperty("user-agent", "Mozilla/5.0");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.connect();
        InputStream is = httpURLConnection.getInputStream();

        return handleInputStream(is);
    }

    public static String handleInputStream(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        int offSize = 0;
        int length = 1024;
        StringBuilder sb = new StringBuilder();
        while ((offSize = is.read(bytes, offSize, length)) != -1) {
            sb.append(new String(bytes));
        }
        return sb.toString();
    }

    public static String postRequestJSON(String requestUrl) throws IOException {
        URL url = new URL("https://reqres.in/api/users");

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        //JSON String need to be constructed for the specific resource.
        //We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
        String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println(code);

        return handleInputStream(con.getInputStream());
    }

    public static void main(String[] args) throws IOException {
        String response = HttpApi.getRequest("https://reqres.in/" +
                "api/users?page=2");
        System.out.println(response);

        String postResponse = HttpApi.postRequestJSON("https://reqres.in/api/login");
        System.out.println(postResponse);
    }
}
