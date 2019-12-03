package com.example.coroutinesapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoinViewHolder(item: View) : RecyclerView.ViewHolder(item) {

    interface ClickCoinItemListener{
        fun onClick(coin : Coin)
    }

    fun bind(coin: Coin, listener : ClickCoinItemListener){
        itemView.findViewById<TextView>(R.id.symbol).text = coin.symbol
        itemView.setOnClickListener{listener.onClick(coin)}
    }
}