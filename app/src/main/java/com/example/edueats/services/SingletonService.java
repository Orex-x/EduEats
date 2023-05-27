package com.example.edueats.services;

import com.example.edueats.adapters.BasketAdapter;
import com.example.edueats.models.Client;


public class SingletonService {

    public static Client mainClient;

    public static int sumOrder;

    public static void clear(){
        mainClient = null;
    }

}
