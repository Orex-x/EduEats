package com.example.edueats.services;

import android.annotation.SuppressLint;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiClient {

    private static final String _port = "81";
    private static final String _host = "176.124.206.111";

    public enum CRUD
    {
        create,
        get,
        update,
        delete
    }

    public enum Method
    {
        GET,
        POST,
        UPDATE,
        DELETE
    }

    @SuppressLint("DefaultLocale")
    public static <T> String generateURI(Class<T> clazz, CRUD crud) {
        return String.format("http://" + _host + ":" + _port + "/api/easydata/models/__default/sources/" + clazz.getSimpleName() + "/" + crud.toString());
    }

    public static <T> List<T> get(Class<T> clazz) {
        try {
            String uri = generateURI(clazz, CRUD.get);

            NetworkThread t = new NetworkThread(uri, "", Method.GET.toString());
            t.start();
            t.join();
            String jsonResponse = t.getResult();

            List<T> array = jsonArrayToList(jsonResponse, clazz);

            return array;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static <T> List<T> jsonArrayToList(String json, Class<T> elementClass) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType =
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, elementClass);
        return objectMapper.readValue(json, listType);
    }

    public static int create(Object obj) {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            String body = gson.toJson(obj);

            // Генерируем URI для создания объекта
            String uri = generateURI(obj.getClass(), CRUD.create);

            NetworkThread t = new NetworkThread(uri, body, Method.POST.toString());
            t.start();
            t.join();
            String jsonResponse = t.getResult();

            // Парсим ответ сервера и возвращаем ID созданного объекта
            JSONObject jsonObj = new JSONObject(jsonResponse);
            return jsonObj.getJSONObject("record").getInt("Id");
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return 0;
    }

    public static <T> int update(T obj) {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

            String body = gson.toJson(obj);

            String uri = generateURI(obj.getClass(), CRUD.update);

            NetworkThread t = new NetworkThread(uri, body, Method.UPDATE.toString());
            t.start();
            t.join();
            String jsonResponse = t.getResult();


            JsonObject jsonObj = gson.fromJson(jsonResponse, JsonObject.class);
            return jsonObj.get("record").getAsJsonObject().get("Id").getAsInt();
        } catch (Exception ex) {
            // Обработка ошибок
        }
        return 0;
    }

    public static <T> void delete(int id, Class<T> clazz) {
        try {
            String uri = generateURI(clazz, CRUD.delete);
            String body = "{Id: " + id + "}";

            NetworkThread t = new NetworkThread(uri, body, Method.DELETE.toString());
            t.start();
        } catch (Exception ex) {
            // Обработка ошибок
        }
    }


    public static <T> Map<String, Object> objectToMap(T obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        // Получаем все поля объекта
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // Делаем поля доступными для чтения
            field.setAccessible(true);
            // Получаем значение поля и добавляем его в Map
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }
}
