package com.danilobrito.pokedex.data.network

import com.danilobrito.pokedex.model.PokemonDetail
import com.danilobrito.pokedex.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemon(): Response<PokemonResponse>

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemonDetails(
        @Path("pokemonName") pokemonName: String
    ):Response<PokemonDetail>
}