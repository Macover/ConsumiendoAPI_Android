package com.example.api_1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConsumeFact {

    @GET("fact")
    Call<CatFact> getFacts();
}
