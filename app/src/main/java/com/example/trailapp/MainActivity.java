package com.example.trailapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

   // https://api.weatherapi.com/v1/current.json?key=35c9f92ac5bf4df0811144140212307&q=varanasi&aqi=no


    //https://api.postalpincode.in/pincode/221001
    AutoCompleteTextView autoCompleteTextView;
    Toolbar toolbar;

    DatePickerDialog.OnDateSetListener setListener;

    EditText name,dob,number,address1,address2,pincode;
    TextView district,state;
    Button check,register;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.edt_txt_name);
        number = findViewById(R.id.edt_txt_mobile);
        dob=findViewById(R.id.edt_txt_dob);
        address1 = findViewById(R.id.edt_txt_addess1);
        address2 = findViewById(R.id.edt_txt_addess2);
        pincode = findViewById(R.id.edt_txt_pincode);
         district = findViewById(R.id.txt_District_auto);
         state = findViewById(R.id.txt_State_auto);
        check = findViewById(R.id.btn_check);
        register = findViewById(R.id.btn_register);

      pincode.addTextChangedListener(pincodeTextWatcher);


      check.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              checkPinCode(Integer.parseInt(pincode.getText().toString()));
          }
      });





        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        toolbar =  findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

       String [] options = {"Male","Female","Other"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.drop_down_item,options);

        autoCompleteTextView.setText("N/A",false);

        autoCompleteTextView.setAdapter(arrayAdapter);




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkAllField()){

                    Intent intent = new Intent(MainActivity.this,WeatherToday.class);
                    startActivity(intent);

                }
            }
        });


        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int Day = calendar.get(Calendar.DAY_OF_MONTH);

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,setListener,year,month,Day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                 datePickerDialog.show();

            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = Day+"/"+month+"/"+year;

                dob.setText(date);

            }
        };





    }

    private void checkPinCode(int pin) {

        WeatherApi weatherApi = Service2.getCheckApi();

        Call<List<CheckResponseModel>> callCheck = weatherApi.checkPincode(pin);

        callCheck.enqueue(new Callback<List<CheckResponseModel>>() {
            @Override
            public void onResponse(Call<List<CheckResponseModel>> call, Response<List<CheckResponseModel>> response) {

                if(response.code()==200){

                    if(response.body().get(0).getStatus().equals("Success")){

                        List<PostResponseModel> rList  = response.body().get(0).getPostOffice();


                        district.setText("");
                        district.setText(rList.get(0).getDistrict());

                        state.setText("");
                        state.setText(rList.get(0).getState());




                    }else{
                        Toast.makeText(MainActivity.this, "Please Enter valide pincode..", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.i("TAG","Error.....");
                }
            }

            @Override
            public void onFailure(Call<List<CheckResponseModel>> call, Throwable t) {

                Log.i("Pag","Error :"+t.getMessage());

            }
        });





    }

    private boolean checkAllField() {

        if(number.getText().toString().length()<10){
            Toast.makeText(this, "Please Enter Valid Mobile Number.", Toast.LENGTH_SHORT).show();
        }else if(name.getText().toString().length()==0){
            Toast.makeText(this, "Please Enter name.", Toast.LENGTH_SHORT).show();
        }else if(autoCompleteTextView.getText().toString() == "N/A"){
            Toast.makeText(this, "Please choose Gende.", Toast.LENGTH_SHORT).show();
        }else if(dob.getText().toString().length()<8){
            Toast.makeText(this, "Please Enter correct Dob: DD/MM/YYYY .", Toast.LENGTH_SHORT).show();
        }else if(address1.getText().toString().length()<3){
            Toast.makeText(this, "Please Enter correct Address.", Toast.LENGTH_SHORT).show();
        }else if(pincode.getText().toString().length()<6){
            Toast.makeText(this, "Please Enter valid Pincode.", Toast.LENGTH_SHORT).show();
        }else{
            return true;
        }


        return false;
    }

    private TextWatcher pincodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     String pin  =  pincode.getText().toString();

                     if(pin.length()==6){
                         check.setEnabled(true);
                     }

                     if(pin.length()<6){
                         check.setEnabled(false);
                     }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };




}