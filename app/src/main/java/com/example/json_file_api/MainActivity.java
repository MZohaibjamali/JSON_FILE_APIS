package com.example.json_file_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String URL = "https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterfaceJava api = retrofit.create(ApiInterfaceJava.class);
        
        Call<ArrayList<UserItemJava>> call= api.getUsers();

        Call<Todo> todoCall = api.getTodoById("1");

        call.enqueue(new Callback<ArrayList<UserItemJava>>() {
            @Override
            public void onResponse(Call<ArrayList<UserItemJava>> call, Response<ArrayList<UserItemJava>> response) {
                for (UserItemJava u:response.body()) {
                Log.d("MyReuestMonter our user list",u.name );
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UserItemJava>> call, Throwable t) {
                Log.d("MyReuestMonter our user list",t.getLocalizedMessage());

            }
        });


        todoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                Todo td1 = response.body();
                Log.d("MyReuestMonter api response code: ",response.code()+"");
                Log.d("MyReuestMonter todo resonse date: ",td1.title);
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                Log.d("MyReuestMonter api response code: ",t.getLocalizedMessage());

            }
        });



    }




}