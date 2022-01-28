package com.hit.apitestingapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/user/register")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);
}
