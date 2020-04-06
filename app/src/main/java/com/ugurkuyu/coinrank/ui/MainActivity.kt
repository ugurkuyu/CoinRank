package com.ugurkuyu.coinrank.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ugurkuyu.coinrank.R
import com.ugurkuyu.coinrank.paging.CoinPagedListAdapter
import com.ugurkuyu.coinrank.paging.CoinViewModel
import com.ugurkuyu.coinrank.model.Coins
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoinPagedListAdapter.OnCoinClickListener {

    private lateinit var adapter: CoinPagedListAdapter
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupUI()

        adapter = CoinPagedListAdapter(this, this)

        main_recyclerview.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)

        viewModel.coinPagedList.observe(this, Observer {
            adapter.submitList(it)
            main_recyclerview.adapter = adapter
            main_recyclerview.addItemDecoration(
                DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
            )
        })

    }

    private fun setupUI() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
    }

    override fun onCoinClickListener(coins: Coins) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("extra_coin_result", coins)
        startActivity(intent)
    }
}
