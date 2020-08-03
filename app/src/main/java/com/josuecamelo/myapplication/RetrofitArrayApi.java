package com.josuecamelo.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jaink on 14-09-2017.
 */

public interface RetrofitArrayApi {

    @GET("wp-json/wp/v2/posts")
    Call<List<WPPost>> getPostInfo();
    /// to make call to dynamic URL
    //  @GET
    //  Call<List<WPPost>> getPostInfo(@Url String url);
    //
    @GET("wp-json/wp/v2/video")
    Call<List<WPPostVideos>> getPostVideos();
}
