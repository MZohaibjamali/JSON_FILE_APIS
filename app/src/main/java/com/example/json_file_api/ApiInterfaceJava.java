package com.example.json_file_api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterfaceJava {

    @GET("users")
    Call<ArrayList<UserItemJava> > getUsers();

    @GET("todos/{userId}")
    Call<Todo> getTodoById(@Path("userId") String id);



}
