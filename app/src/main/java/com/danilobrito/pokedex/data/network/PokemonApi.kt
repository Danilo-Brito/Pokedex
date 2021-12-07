package com.danilobrito.pokedex.data.network

import com.danilobrito.pokedex.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemon(): Response<PokemonResponse>
}