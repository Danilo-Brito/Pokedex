package com.danilobrito.pokedex.di

import com.danilobrito.pokedex.data.network.PokemonApi
import com.danilobrito.pokedex.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val pokemonService by lazy{
        retrofit.create(PokemonApi::class.java)
    }
}