package dev.jdvila.marvelunlimitedmark01.network;

import retrofit2.Converter;
import retrofit2.Retrofit;

public class RetrofitSingleton {
    private static Retrofit retrofitInstance;

    private RetrofitSingleton() {
    }

    public static Retrofit getRetrofitInstance() throws InstantiationException {
        if (null == retrofitInstance) {
            throw new InstantiationException("Error - Retrofit Instance not Initialized!");
        }
        return retrofitInstance;
    }

    public static void init(String baseUrl, Converter.Factory factory) throws InstantiationException {
        if (null == retrofitInstance) {
            retrofitInstance = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(factory).build();
            return;
        }
        throw new InstantiationException("Error - Retrofit Instance already Initialized!");
    }
}
