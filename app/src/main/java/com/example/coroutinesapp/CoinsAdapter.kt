package com.example.coroutinesapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CoinsAdapter(val listener: CoinViewHolder.ClickCoinItemListener) : RecyclerView.Adapter<CoinViewHolder>(){

    val coins = ArrayList<Coin>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return CoinViewHolder(v)
    }

    override fun getItemCount(): Int {
        return coins.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coins[position], listener)
    }

    fun add(coins: List<Coin>, refresh: Boolean =true){

        if(refresh){
            this.coins.clear()
        }
        this.coins.addAll(coins)
        notifyDataSetChanged()

    }
}