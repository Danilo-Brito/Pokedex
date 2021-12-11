package com.danilobrito.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilobrito.pokedex.data.repository.PokemonRepository
import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonViewModel(private val repository: PokemonRepository): ViewModel() {

    val pokemonResponse: MutableLiveData<NetworkResult<PokemonResponse>> = MutableLiveData()

    fun getPokemon() = viewModelScope.launch {
            try {
                val response = repository.getPokemon()
                pokemonResponse.value = handleResponse(response)

            } catch (e: Exception){
                pokemonResponse.value = NetworkResult.Error("Pokemons not found")
            }
        }

    private fun handleResponse(response: Response<PokemonResponse>): NetworkResult<PokemonResponse>? {
        when{
            response.isSuccessful -> {
                val pokemon = response.body()
                return NetworkResult.Success(pokemon!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }
    }
}