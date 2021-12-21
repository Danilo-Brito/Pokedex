package com.danilobrito.pokedex.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilobrito.pokedex.data.repository.PokemonRepository
import com.danilobrito.pokedex.model.PokemonDetail
import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.util.Constants.Companion.TAG_ERROR
import com.danilobrito.pokedex.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonViewModel(private val repository: PokemonRepository): ViewModel() {

    val pokemonResponse: MutableLiveData<NetworkResult<PokemonResponse>> = MutableLiveData()
    val pokemonResponseDetails: MutableLiveData<NetworkResult<PokemonDetail>> = MutableLiveData()

    fun getPokemon() = viewModelScope.launch {
            try {
                val response = repository.getPokemon()
                pokemonResponse.value = handleResponse(response)

            } catch (e: Exception){
                pokemonResponse.value = NetworkResult.Error("Pokemons not found")
            }
        }

    fun getPokemonDetail(pokemonName: String) = viewModelScope.launch {
        try {
            val response = repository.getPokemonDetail(name = pokemonName)
            pokemonResponseDetails.value = handleResponse(response)

        }catch (e: Exception){
            pokemonResponseDetails.value = NetworkResult.Error("Pokemon details not found")
            Log.e(TAG_ERROR, "$e")
        }
    }


    private fun <T> handleResponse(response: Response<T>): NetworkResult<T> {
        return when{
            response.isSuccessful -> {
                val pokemon = response.body()
                NetworkResult.Success(pokemon!!)
            }
            else -> {
                NetworkResult.Error(response.message())
            }
        }
    }
}