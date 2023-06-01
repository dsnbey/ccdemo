package com.example.ccdemo;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientForCloud {

    private static final String BASE_URL = "https://europe-central2-cs102projectcc.cloudfunctions.net/";

    private static volatile CloudApiService instance;

    public static synchronized CloudApiService createService() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://europe-central2-cs102projectcc.cloudfunctions.net/")
                    .client(buildOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

            instance = retrofit.create(CloudApiService.class);
        }
        return instance;
    }

    private static OkHttpClient buildOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

    }
}