package br.com.romariomenezes.services;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Romário Menezes on 31/07/2018.
 */

public class AlunoServices {

    public Retrofit getRetrofit(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder().baseUrl("https://androidmba.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
    }

}
