package com.ugurkuyu.coinrank.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ugurkuyu.coinrank.model.Coins

class CoinDataSourceFactory : DataSource.Factory<Int, Coins>() {

     val coinLiveDataSource = MutableLiveData<CoinDataSource>()
    override fun create(): DataSource<Int, Coins> {
        val coinDataSource = CoinDataSource()
        coinLiveDataSource.postValue(coinDataSource)
        return coinDataSource
    }
}