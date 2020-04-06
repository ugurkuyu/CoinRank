package com.ugurkuyu.coinrank.paging

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ugurkuyu.coinrank.data.ApiClient
import com.ugurkuyu.coinrank.model.CoinResponse
import com.ugurkuyu.coinrank.model.Coins
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinDataSource : PageKeyedDataSource<Int, Coins>() {

    companion object {
        private val apiService by lazy { ApiClient.getApiClient() }
        var mOffset = 0
        const val mLimit = 10
    }


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Coins>
    ) {
        val call = apiService.getCoins(mOffset, mLimit)

        call.enqueue(object : Callback<CoinResponse> {
            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                Log.e("loadInitial", t.message!!)

            }

            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data.coins

                    responseItems.let {
                        callback.onResult(responseItems, null, mOffset + mLimit)
                    }
                }
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Coins>) {

        val call = apiService.getCoins(params.key, mLimit)

        call.enqueue(object : Callback<CoinResponse> {
            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                Log.e("loadAfter", t.message!!)
            }

            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data.coins
                    val key = params.key + mLimit
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Coins>) {

        val call = apiService.getCoins(params.key, mLimit)

        call.enqueue(object : Callback<CoinResponse> {
            override fun onFailure(call: Call<CoinResponse>, t: Throwable) {
                Log.e("loadBefore", t.message!!)
            }

            override fun onResponse(call: Call<CoinResponse>, response: Response<CoinResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()!!
                    val responseItems = apiResponse.data.coins
                    val key = if (params.key > 0) params.key - 1 else 0
                    responseItems.let {
                        callback.onResult(responseItems, key)
                    }
                }
            }
        })
    }
}