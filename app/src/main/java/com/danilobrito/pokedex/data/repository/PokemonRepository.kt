package com.danilobrito.pokedex.data.repository

import com.danilobrito.pokedex.di.RetrofitInstance
import com.danilobrito.pokedex.model.Pokemon
import com.danilobrito.pokedex.model.PokemonDetail
import com.danilobrito.pokedex.model.PokemonResponse
import retrofit2.Response

class PokemonRepository {

    suspend fun getPokemon(): Response<PokemonResponse> {
        return RetrofitInstance.pokemonService.getPokemon()
    }

    suspend fun getPokemonDetail(results: List<Pokemon>): Response<PokemonDetail> {
        return RetrofitInstance.pokemonService.getPokemonDetails()
    }
}
