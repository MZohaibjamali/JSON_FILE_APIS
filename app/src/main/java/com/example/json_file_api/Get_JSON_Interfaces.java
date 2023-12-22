package com.example.json_file_api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Get_JSON_Interfaces {

    @GET("users")
    Call<ArrayList<JSON_USER_ITEM_MZ>> getUser1();



}
