package com.example.coroutinesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val COIN_EXTRA_KEY = "COIN_EXTRA_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (intent.getSerializableExtra(COIN_EXTRA_KEY) as Coin).let {
            symbol.text = it.symbol
            name.text = it.name
            price.text = "${it.price}$"
            change.text = "${it.change}%"
            if(it.change > 0){
                changeImg.setImageResource(R.drawable.drop_up_black)
            }else{
                changeImg.setImageResource(R.drawable.drop_down)
            }
        }
    }
}