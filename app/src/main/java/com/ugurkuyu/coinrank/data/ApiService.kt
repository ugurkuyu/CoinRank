package com.ugurkuyu.coinrank.data

import com.ugurkuyu.coinrank.BuildConfig
import com.ugurkuyu.coinrank.model.CoinResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("packageName: " + BuildConfig.APPLICATION_ID)
    @GET("coins")
    fun getCoins(@Query("offset") offset: Int, @Query("limit") limit: Int): Call<CoinResponse>

}