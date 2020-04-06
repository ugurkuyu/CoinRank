package com.ugurkuyu.coinrank.paging

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ugurkuyu.coinrank.R
import com.ugurkuyu.coinrank.model.Coins
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import de.hdodenhof.circleimageview.CircleImageView

class CoinPagedListAdapter(var context: Context, var onCoinClickListener: OnCoinClickListener) :
    PagedListAdapter<Coins, CoinPagedListAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val coinName = view.findViewById<TextView>(R.id.item_coin_name)
        private val coinImage = view.findViewById<CircleImageView>(R.id.item_coin_image)
        private val coinPrice = view.findViewById<TextView>(R.id.item_coin_price)
        private val coinSymbol = view.findViewById<TextView>(R.id.item_coin_symbol)
        private var coinAllTimeHigh = view.findViewById<TextView>(R.id.item_coin_top_price)

        fun bind(context: Context, coins: Coins) {
            coinName.text = coins.name
            coinSymbol.text = coins.symbol

            if (coins.color != null) {

                val colorLength = coins.color?.length
                if (colorLength!! == 7) {
                    coinName.setTextColor(Color.parseColor(coins.color))
                    coinAllTimeHigh.setTextColor(Color.parseColor(coins.color))
                } else {
                    coinName.setTextColor(Color.BLACK)
                    coinAllTimeHigh.setTextColor(Color.BLACK)
                }

            }

            GlideToVectorYou.justLoadImage(
                context as Activity?,
                Uri.parse(coins.iconUrl),
                coinImage
            )
            coinAllTimeHigh.text = "%.2f".format(coins.allTimeHigh.price.toBigDecimal())
            coinPrice.text = "%.2f".format(coins.price.toBigDecimal())
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coins, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = getItem(position)
        coin?.let {
            holder.bind(context, it)
        }

        holder.itemView.setOnClickListener {
            onCoinClickListener.onCoinClickListener(coin!!)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Coins>() {
            override fun areItemsTheSame(oldItem: Coins, newItem: Coins): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Coins, newItem: Coins): Boolean =
                oldItem.name == newItem.name
        }
    }

    interface OnCoinClickListener {
        fun onCoinClickListener(coins: Coins)
    }
}