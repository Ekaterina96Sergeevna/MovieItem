package com.hfad.movieitem;

import android.app.Application;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//класс который существует всегда
public class OtusApp extends Application {
    //доступ к сервису со всего приложения
    public MoviesService moviesService;
    private static OtusApp instance;

    @Override
    public void onCreate() {
        super.onCreate(); //  при закрытии приложения класс не умирает

        instance = this;

        initRetrofit();
    }

    public static OtusApp getInstance(){
        return instance;
    }

    private void initRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //важен порядок Interceptors
                .addInterceptor(new Interceptor() {

                    //передаем в Interceptor Header c token
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain
                                .request()
                                .newBuilder()
                                .addHeader("X-Auth-Token", "547ijfktj45")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(httpLoggingInterceptor) // add interceptor, see in Logcat
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://my-json-server.typicode.com/Ekaterina96Sergeevna/MyPlaceholder/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //создание сервиса
        moviesService = retrofit.create(MoviesService.class);
    }
}
