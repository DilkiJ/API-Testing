package com.hit.apitestingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    TextView txt;

    EditText username, password, email, profilepicture, address, hospital, mobilenumber, specialization;
    Button reg_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        email = findViewById(R.id.editText3);
        address = findViewById(R.id.editText4);
        profilepicture = findViewById(R.id.editText5);
        hospital = findViewById(R.id.editText6);
        mobilenumber = findViewById(R.id.editText7);
        specialization = findViewById(R.id.editText8);

        reg_user = findViewById(R.id.reg_user);


        reg_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(createRequest());
            }
        });

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://run.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        MyAPICall myAPICall = retrofit.create(MyAPICall.class);
        Call<DataModel> call = myAPICall.getData();

        call.enqueue(new Callback<DataModel>() {
            @Override
            public void onResponse(Call<DataModel> call, Response<DataModel> response) {

                //checking for the response
                if (response.code() != 200){
                    txt.setText("Check the connection");
                    return;
                }

                //Get the data into text view
                String jsony = "";

                jsony = "ID= " + response.body().getId() +
                        "\nUser Id " + response.body().getUserId()+
                        "\nTitle " + response.body().getTitle() +
                        "\nCompleted " + response.body().isCompleted();

                txt.append(jsony);
            }

            @Override
            public void onFailure(Call<DataModel> call, Throwable t) {

            }
        });
    }

    public UserRequest createRequest(){
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(username.getText().toString());
        userRequest.setPassword(password.getText().toString());
        userRequest.setEmail(email.getText().toString());
        userRequest.setProfilepicture(profilepicture.getText().toString());
        userRequest.setAddress(address.getText().toString());
        userRequest.setHospital(hospital.getText().toString());
        userRequest.setMobilenumber(mobilenumber.getText().toString());
        userRequest.setSpecialization(specialization.getText().toString());

        return userRequest;
    }


    public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getUserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Saving Successfully",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this,"Request Failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Request Failed" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


}