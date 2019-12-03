package com.example.coroutinesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinesapp.DetailActivity.Companion.COIN_EXTRA_KEY
import kotlinx.coroutines.*

class ListActivity : AppCompatActivity(), CoinViewHolder.ClickCoinItemListener {

    var job : Job? = null

    lateinit var adapterRv: CoinsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lm = LinearLayoutManager(this)
            adapterRv = CoinsAdapter(this)
            findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = lm
            adapter = adapterRv
        }

        val service = createCoinService()
        job = CoroutineScope(Dispatchers.IO).launch {
            val res = service.getCoins()
            if(res.isSuccessful){
                withContext(Dispatchers.Main){
                    updateUI(res.body()?.data?.coins ?: listOf())
                }
            }

        }

    }


    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    fun updateUI(coins: List<Coin>){

         adapterRv.add(coins)
    }

    override fun onClick(coin: Coin) {

        val intent = Intent(this, DetailActivity::class.java )
        intent.putExtra(COIN_EXTRA_KEY, coin)
        startActivity(intent)
    }

}
