package com.example.json_file_api;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApi_ModalInterFace {

    @GET("posts")
    Call<PostApi_ModalClass> getPosts();

    @GET("posts/{id}")
    Call<PostApi_ModalClass> getPostById(@Path("id") String id);

    @POST("posts")
    Call<PostApi_ModalClass> postUser(@Body PostApi_ModalClass body);



}
