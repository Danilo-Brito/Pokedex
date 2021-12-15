package com.danilobrito.pokedex.data.repository

import com.danilobrito.pokedex.di.RetrofitInstance
import com.danilobrito.pokedex.model.PokemonDetail
import com.danilobrito.pokedex.model.PokemonResponse
import retrofit2.Response

class PokemonRepository {

    suspend fun getPokemon(): Response<PokemonResponse> {
        return RetrofitInstance.pokemonService.getPokemon()
    }

    suspend fun getPokemonDetail(name: String): Response<PokemonDetail> {
        return RetrofitInstance.pokemonService.getPokemonDetails(name)
    }
}
