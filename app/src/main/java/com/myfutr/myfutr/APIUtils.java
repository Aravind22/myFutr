package com.myfutr.myfutr;

public class APIUtils {
        public static final String BASE_URL = "https://myfutr.herokuapp.com/";
//      public static final String BASE_URL = "http://10.0.2.2:4000/";

    public static APIService getAPIService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
