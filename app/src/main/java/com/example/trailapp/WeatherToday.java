package com.example.trailapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherToday extends AppCompatActivity {

    EditText city;
    TextView temp_c,temp_f;
    TextView longi,latitu;
    ProgressDialog progressBar;
    Button result;
     Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_today);

        city = findViewById(R.id.edt_txt_City);
        temp_c = findViewById(R.id.txt_Temp_cent_auto);
        temp_f = findViewById(R.id.txt_Temp_Fah_auto);
        result = findViewById(R.id.btn_show_result);
        longi = findViewById(R.id.txt_Longitude_auto);
        latitu = findViewById(R.id.txt_Latitude_auto);
        progressBar = new ProgressDialog(this);
       toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);





        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.show();
                if(city.getText().toString().length()==0){
                    Toast.makeText(WeatherToday.this, "Please Enter Valide City Name !", Toast.LENGTH_SHORT).show();
                }else{
                    temp_c.setText("");
                    temp_f.setText("");
                    longi.setText("");
                    latitu.setText("");
                    callRetrofit(city.getText().toString());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,  menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case
                    R.id.menu_back:

                break;
        }

        return true;
    }

    private void callRetrofit(String city) {

    WeatherApi weatherApi = Service.getWeatherApi();

    Call<WeatherResponseModel> call = weatherApi.weatherSrch("35c9f92ac5bf4df0811144140212307",city,"no");

    call.enqueue(new Callback<WeatherResponseModel>() {
        @Override
        public void onResponse(Call<WeatherResponseModel> call, Response<WeatherResponseModel> response) {

            if(response.code()==200){

                Log.i("gap","response "+response.body().getCurrent().temp_c);

                WeatherResponseModel resultGet = response.body();

                progressBar.dismiss();

                temp_c.setText("");
                temp_c.setText(resultGet.getCurrent().temp_c+"");

                temp_f.setText("");
                temp_f.setText(resultGet.getCurrent().temp_f+"");

                longi.setText("");
                longi.setText(resultGet.getLocation().lon+"");

                latitu.setText("");
                latitu.setText(resultGet.getLocation().lat+"");

            }else{

                Log.i("gap","response wrong");
                Toast.makeText(WeatherToday.this, "Please Enter Valide City Name !",Toast.LENGTH_SHORT).show();

                 }
        }
        @Override
        public void onFailure(Call<WeatherResponseModel> call, Throwable t) {


        }
    });


    }

}