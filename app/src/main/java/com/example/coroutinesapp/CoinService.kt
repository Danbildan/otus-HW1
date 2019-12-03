package com.example.coroutinesapp

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.Serializable

interface CoinService {

    @GET("coins")
    suspend fun getCoins(@Query("limit") limit: Int = 50): Response<CoinsData>
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Coins(val coins : List<Coin>?)

@JsonIgnoreProperties(ignoreUnknown = true)
data class CoinsData(val data : Coins)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Coin(var id: Int,
                var symbol: String,
                var name: String,
                var iconUrl: String,
                var price: String,
                var change: Double
) : Serializable

fun createCoinService(): CoinService{
    val  client =   OkHttpClient.Builder().build()
    var retrofit = Retrofit.Builder()
        .baseUrl("https://api.coinranking.com/v1/public/")
        .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
        .client(client)
        .build()

    return retrofit.create(CoinService::class.java)
}