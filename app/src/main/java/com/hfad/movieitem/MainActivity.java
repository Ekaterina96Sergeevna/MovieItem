package com.hfad.movieitem;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<MovieItem> items = new ArrayList<>();

        View loader = findViewById(R.id.loader);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); //можно задать в xml разметке
        recyclerView.setAdapter(new MovieAdapter(items, LayoutInflater.from(this)));

        loader.setVisibility(View.VISIBLE); //показываем перед запросом loader
        // enqueue - ассинхронный метод, который позволяет залезть в интернет без блока UI Thread
        OtusApp.getInstance().moviesService.getMovies().enqueue(new Callback<List<MovieJson>>() {

            @Override
            public void onResponse(Call<List<MovieJson>> call, Response<List<MovieJson>> response) {
                //возвращает запросы, которые прошли (даже если не успешные)
                // коды можно посмотреть в интернете, что они означают
                // isSuccessful = 200..300
                if (response.isSuccessful()){
                    List<MovieJson> movieJsonList = response.body();
                    items.clear();
                    for (MovieJson movieJson : movieJsonList){
                        items.add(new MovieItem(movieJson));
                    }

                    recyclerView.getAdapter().notifyDataSetChanged();
                }
                else if (response.code() == 404){
                    Toast.makeText(MainActivity.this, "FAIL " + response.code(),
                            Toast.LENGTH_SHORT).show();
                }
                loader.setVisibility(View.GONE); //после запроса убираем loader


            }

            @Override
            public void onFailure(Call<List<MovieJson>> call, Throwable t) {
                loader.setVisibility(View.GONE);
                // метод вызывается если нет интернета
                // невалидный Json - ожидали int вернулся String
                // если exception
                Toast.makeText(MainActivity.this, "FAILURE " + t.getClass().getSimpleName(),
                        Toast.LENGTH_SHORT).show();
                if (t instanceof Exception){}
                t.printStackTrace();

            }
        });


//        items.add(new MovieItem("Hello!"));
//        items.add(new MovieItem("I "));
//        items.add(new MovieItem("try "));
//        items.add(new MovieItem("to be "));
//        items.add(new MovieItem("programmer!"));


        gsonSamples();
    }

    private void gsonSamples(){
        //инициализация Gson
        Gson gson = new Gson();
        new GsonBuilder()
                .serializeNulls() //по умолчанию Gson нулевые значения не сериализует
                .create(); // вместо new Gson(), можно вызывать различные функции для GsonBuilder
        Gson gson1 = new GsonBuilder()
                .serializeNulls()
                .create();

        //получение json-строки из объекта
        String jsonString = gson.toJson(new SimpleObject());
        Log.d("gson", jsonString);

        //собираем из строки объект
        SimpleObject simpleObject = gson.fromJson(jsonString, SimpleObject.class);
        Log.d("gson", simpleObject.toString());


    }

    public static class SimpleObject{
        public String name = "SimpleName";
        public int age = 5;
        public List<String> nicknames = new ArrayList<>();
        public NestedClass nestedClass = new NestedClass(); //вложенный класс

        public SimpleObject() {
            nicknames.add("Anton");
            nicknames.add("Karton");
            nicknames.add("Glupin");
        }

        @Override
        public String toString() {
            return "SimpleObject{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", nicknames=" + nicknames +
                    ", nestedClass=" + nestedClass +
                    '}';
        }

        public static class NestedClass{
            public String name = "SimpleNameNested";

            @Override
            public String toString() {
                return "NestedClass{" +
                        "name='" + name + '\'' +
                        '}';
            }
        }
    }
}