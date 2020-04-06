package com.ugurkuyu.coinrank.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getApiClient(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v1/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

}