package com.myselfapp.myselfapp.network.Request;

import com.myselfapp.myselfapp.config.MySelfConfig;
import com.myselfapp.myselfapp.network.Response.BaseResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Army on 2016/11/8
 */

public class BaseRequest {
    private ClientService clientService;

    public interface ClientService {
        @GET("users/{user}/repos")
        Call<List<BaseResponse>> listRepos(@Path("user") String user);
        // you can add some other meathod
    }

    public void get(){
       Retrofit retrofit= new Retrofit.Builder().baseUrl(MySelfConfig.PRODUCT_HOST).build();
         clientService = retrofit.create(ClientService.class);
        Call<List<BaseResponse>> login = clientService.listRepos("user");
        login.enqueue(new Callback<List<BaseResponse>>() {
            @Override
            public void onResponse(Call<List<BaseResponse>> call, Response<List<BaseResponse>> response) {

            }

            @Override
            public void onFailure(Call<List<BaseResponse>> call, Throwable t) {

            }
        });
    }
}
