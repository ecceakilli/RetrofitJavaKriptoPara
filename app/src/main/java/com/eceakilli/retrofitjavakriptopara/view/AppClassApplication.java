package com.eceakilli.retrofitjavakriptopara.view;

import android.app.Application;

import com.eceakilli.retrofitjavakriptopara.service.ApiClient;
import com.eceakilli.retrofitjavakriptopara.service.CryptoAPI;

public class AppClassApplication extends Application {

    static AppClassApplication instance;
    CryptoAPI cryptoAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        instance =this;
        cryptoAPI= ApiClient.getClient().create(CryptoAPI.class);


    }
    public synchronized static AppClassApplication getInstance(){

        return instance;
    }


    public CryptoAPI getCryptoAPI() {

        return cryptoAPI;
    }
}
