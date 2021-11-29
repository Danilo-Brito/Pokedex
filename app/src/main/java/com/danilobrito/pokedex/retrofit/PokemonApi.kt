package com.danilobrito.pokedex.retrofit

import com.danilobrito.pokedex.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    fun getPokemon(): Response<PokemonResponse>
}