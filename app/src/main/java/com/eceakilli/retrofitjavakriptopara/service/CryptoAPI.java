package com.eceakilli.retrofitjavakriptopara.service;

import com.eceakilli.retrofitjavakriptopara.model.CryptoModel;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI
{
    //https://api.nomics.com/v1/prices?key=e847fcd9285ccce1f0e3580ead6d9e23d6ea4d95     ------veri linki

    @GET("prices?key=e847fcd9285ccce1f0e3580ead6d9e23d6ea4d95")
    Observable<List<CryptoModel>> getData();

   // Call<List<CryptoModel>> getData();
}
