package com.kdb2018.sasa.percent.api;

import com.kdb2018.sasa.percent.Keys;
import com.kdb2018.sasa.percent.model.kriptoModel.CryptoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CoinMarketApi {
    String key= Keys.coinKey;

    @Headers("X-CMC_PRO_API_KEY: "+key)
    @GET("/v1/cryptocurrency/listings/latest?")
    Call<CryptoModel> coinMarketListCall(@Query("limit") String page);


}

