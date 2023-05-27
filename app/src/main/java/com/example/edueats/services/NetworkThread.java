package com.example.edueats.services;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkThread extends Thread {
    private String result;

    private String urlString, body, method;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public NetworkThread(String urlString, String body, String method) {
        this.urlString = urlString;
        this.body = body;
        this.method = method;
    }

    public String getResult() {
        return result;
    }

    String post(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            return res;
        }
    }

    @Override
    public void run() {
        try {
            result = post(urlString, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
