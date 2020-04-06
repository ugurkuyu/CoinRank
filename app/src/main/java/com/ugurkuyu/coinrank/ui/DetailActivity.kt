package com.ugurkuyu.coinrank.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ugurkuyu.coinrank.R
import com.ugurkuyu.coinrank.model.Coins
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupUI()

        val coins: Coins = intent.extras!!.getSerializable("extra_coin_result") as Coins

        detail_coin_name.text = coins.name
        detail_coin_website.text = coins.websiteUrl
        detail_coin_rank.text = coins.rank.toString()

        if (coins.color != null) {

            val colorLength = coins.color?.length
            if (colorLength!! == 7) {
                detail_coin_name.setTextColor(Color.parseColor(coins.color))
                detail_coin_price.setTextColor(Color.parseColor(coins.color))
                detail_coin_description.setTextColor(Color.parseColor(coins.color))
                detail_coin_rank.setTextColor(Color.parseColor(coins.color))
            } else {
                detail_coin_name.setTextColor(Color.BLACK)
                detail_coin_price.setTextColor(Color.BLACK)
                detail_coin_description.setTextColor(Color.BLACK)
                detail_coin_rank.setTextColor(Color.BLACK)
            }

        }

        detail_collapsing_toolbar_layout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        detail_collapsing_toolbar_layout.isTitleEnabled = true
        detail_collapsing_toolbar_layout.title = coins.name

        detail_coin_description.text = coins.description

        detail_coin_price.text = "%.2f".format(coins.price.toBigDecimal())


        GlideToVectorYou.justLoadImage(this, Uri.parse(coins.iconUrl), detail_coin_image)

        card_url.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(coins.websiteUrl)
            startActivity(intent)
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setupUI() {
        setSupportActionBar(detail_toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

    }
}
