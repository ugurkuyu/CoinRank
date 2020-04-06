package com.ugurkuyu.coinrank.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ugurkuyu.coinrank.model.Coins

class CoinViewModel : ViewModel() {

    var coinPagedList: LiveData<PagedList<Coins>>
    private var liveDataSource: LiveData<CoinDataSource>

    init {
        val coinDataSourceFactory = CoinDataSourceFactory()
        liveDataSource = coinDataSourceFactory.coinLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(CoinDataSource.mLimit)
            .build()

        coinPagedList = LivePagedListBuilder(coinDataSourceFactory, config).build()
    }

}