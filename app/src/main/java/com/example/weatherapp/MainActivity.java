package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.Retrofit.ApiClient;
import com.example.weatherapp.Retrofit.ApiInterface;
import com.example.weatherapp.Retrofit.Example;
import com.example.weatherapp.Retrofit.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    ImageView search;
    TextView tempText , descText , humidityText;
    EditText textField;
    List<Example> example;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        example = new ArrayList<>();
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),example);
        recyclerView.setAdapter(recyclerAdapter);

        search = findViewById(R.id.search);
        tempText = findViewById(R.id.tempText);
        descText = findViewById(R.id.descText);
        humidityText = findViewById(R.id.humidityText);
        textField = findViewById(R.id.textField);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getWeatherData(textField.getText().toString().trim());

            }
        });


    }

    private void getWeatherData(String name){

       ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(name);

        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                Log.i(TAG,"Response");
                Example example = response.body();
                Log.d("TAG","Response = "+example);
                recyclerAdapter.setMovieList(example);
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {

            }


        });

    }
}
