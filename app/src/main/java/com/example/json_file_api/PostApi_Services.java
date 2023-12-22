package com.example.json_file_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostApi_Services extends AppCompatActivity {
    TextView TextForLogcat;
    EditText userID, id, title, body1;
    ProgressBar showLoader;
    Button refresherPostApi;
    String Url = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_api_services);
        TextForLogcat = findViewById(R.id.TextForLogcat);
        userID = findViewById(R.id.UserId);
        id = findViewById(R.id.Id);
        title = findViewById(R.id.title);
        body1 = findViewById(R.id.Body);
        refresherPostApi = findViewById(R.id.refresherPostApi);
        showLoader = findViewById(R.id.showLoader);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        PostApi_ModalInterFace postApiModalInterFace = retrofit.create(PostApi_ModalInterFace.class);


        refresherPostApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int inputUserId = 0;
                try {
                    String userInput = userID.getText().toString();
                    inputUserId = Integer.parseInt(userInput);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }


                int input_id = 0;
                try {
                    String userInput = id.getText().toString();
                    input_id = Integer.parseInt(userInput);

                } catch (NumberFormatException e) {

                    e.printStackTrace();
                }


                String input_title = title.getText().toString();
                String body = body1.getText().toString();

                int finalInputUserId = inputUserId;
                int finalInput_id = input_id;
                onPreExecute();
                postApiModalInterFace.postUser(new PostApi_ModalClass(finalInputUserId, finalInput_id, input_title, body)).enqueue(new Callback<PostApi_ModalClass>() {
                    @Override
                    public void onResponse(Call<PostApi_ModalClass> call, Response<PostApi_ModalClass> response) {
                        onPostExecute();
                       if(finalInputUserId != 0 && finalInput_id != 0){
                           String logcatText = "\nId: " + String.valueOf(response.body().id1) + "\nUserId: " + String.valueOf(response.body().userId) + "\nTitle: " + response.body().title + "\nBody: " + response.body().body + "\nFull Name: " + response.body().title + " " + response.body().body;
                           TextForLogcat.setText(logcatText);
                           Toast.makeText(PostApi_Services.this, "" + logcatText, Toast.LENGTH_SHORT).show();
                       }else {
                           Toast.makeText(PostApi_Services.this, "reFill the Form", Toast.LENGTH_SHORT).show();
                       }

                      //  Toast.makeText(PostApi_Services.this, response.body().title + "", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<PostApi_ModalClass> call, Throwable t) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onPostExecute();
                                Toast.makeText(PostApi_Services.this, "ErrorFound", Toast.LENGTH_SHORT).show();
                            }
                        }, 2000);

                    }
                });
            }
        });

    }

    private void onPreExecute() {
        showLoader.setVisibility(View.VISIBLE);
    }

    private void onPostExecute() {
        showLoader.setVisibility(View.INVISIBLE);
    }
}