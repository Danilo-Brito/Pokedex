package com.danilobrito.pokedex.retrofit

import com.danilobrito.pokedex.api.PokemonApi
import com.danilobrito.pokedex.util.Constants
import com.danilobrito.pokedex.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pokemonService by lazy{
        retrofit.create(PokemonApi::class.java)
    }
}