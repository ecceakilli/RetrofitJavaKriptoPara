package com.eceakilli.retrofitjavakriptopara.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {


    //serializedname çekecejin json verisnin ismi ile birebir aynı olmalı


    @SerializedName("currency")
    public String currency;

    @SerializedName("price")
    public String price;
}
