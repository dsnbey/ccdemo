package com.example.ccdemo;


import com.example.ccdemo.Model.VectorUpsertRequest;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface VectorDatabaseApiService {
    @Headers({
            "Api-Key: 0d6b6c99-ea18-4725-b036-a503901925cb",
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("vectors/upsert")
    Single<Object> upsertVectors(@Body VectorUpsertRequest request);

}
