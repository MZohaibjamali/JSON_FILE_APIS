package com.example.json_file_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Get_JSON_MZ extends AppCompatActivity {
    RecyclerView recyclerView;

    Button refresh, refreshPostApi , WebPageButton , iCamera , musicPlayer;

    ProgressBar pb;

    Recycler_Adapter adapter;
    String Url = "https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_json_mz);
        recyclerView = findViewById(R.id.recyclerView_GetJSON_adater);
        refresh = findViewById(R.id.refresh);
        adapter = new Recycler_Adapter(Get_JSON_MZ.this);
        pb = findViewById(R.id.showLoader);
        WebPageButton = findViewById(R.id.WebPageButton);
        refreshPostApi = findViewById(R.id.refreshPostApi);
        iCamera = findViewById(R.id.iCamera);
        musicPlayer = findViewById(R.id.musicPlayer);

        musicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Get_JSON_MZ.this , AudioPlayer.class);
                startActivity(intent);
            }
        });
        iCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Get_JSON_MZ.this ,Camera_Photo_Captured.class);
                startActivity(intent);
            }
        });

        WebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Get_JSON_MZ.this ,WebViewActivity.class);
                startActivity(intent);
            }
        });


        refreshPostApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Get_JSON_MZ.this, PostApi_Services.class);
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(Get_JSON_MZ.this, LinearLayoutManager.VERTICAL, false));

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Get_JSON_Interfaces get_json_interfaces = retrofit1.create(Get_JSON_Interfaces.class);


        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onPreExecute();
                get_json_interfaces.getUser1().enqueue(new Callback<ArrayList<JSON_USER_ITEM_MZ>>() {
                    @Override
                    public void onResponse(Call<ArrayList<JSON_USER_ITEM_MZ>> call1, Response<ArrayList<JSON_USER_ITEM_MZ>> response1) {
                        adapter.clearList();
                        recyclerView.setAdapter(adapter);
                        adapter.setList(response1.body());
                        onPostExecute();

                    }

                    @Override
                    public void onFailure(Call<ArrayList<JSON_USER_ITEM_MZ>> call1, Throwable t) {
                        Log.d("My_New_Error", t.getLocalizedMessage());

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onPostExecute();
                                Toast.makeText(Get_JSON_MZ.this, "ErrorFound", Toast.LENGTH_SHORT).show();
                            }
                        }, 2000);
                    }

                });

            }


        });

    }


    private void onPreExecute() {
        pb.setVisibility(View.VISIBLE);
    }

    private void onPostExecute() {
        pb.setVisibility(View.INVISIBLE);
    }

    private void run() {
        Thread th = new Thread();

    }
}