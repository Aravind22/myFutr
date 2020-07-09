package com.myfutr.myfutr;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers("Content-Type: application/json")
    @POST("signin")
    Call<userModel> savePost(@Body userModel user);

    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<Token> signup(@Body userModel user);

    @Headers("Content-Type: application/json")
    @POST("get_questions")
    Call<List<Question>> getQuestions(@Body Question question);

    @Headers("Content-Type: application/json")
    @POST("saveResult")
    Call<Token> saveResponse(@Body userModel question);

    @Headers("Content-Type: application/json")
    @POST("sendMail")
    Call<Token> sendResp(@Body email em);

}
