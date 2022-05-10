package com.kdb2018.sasa.percent.api;

import com.kdb2018.sasa.percent.model.hisseModel.HisseModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DovizApi {

    @GET
    Call<HisseModel> getUsers(@Url String url);


}

