package com.cdtian.linux.command;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CurlExample {
    public static void main(String[] args) {
        CurlExample curlExample = new CurlExample();
        try {
            curlExample.curlExampleOne();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void curlExampleOne() throws IOException {
        String command = "curl -X GET https://postman-echo.com/get?foo1=bar1&foo2=bar2";
        ProcessBuilder builder = new ProcessBuilder(command.split(" "));
        builder.directory(new File("/home"));
        Process process = builder.start();
        InputStream is = process.getInputStream();
        System.out.println(handleInputStream(is));
    }

    public String handleInputStream(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];
        int length = 0;
        StringBuilder sb = new StringBuilder();
        while ((length = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes));
        }
        return sb.toString();
    }

}
