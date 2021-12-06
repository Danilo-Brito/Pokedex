package com.danilobrito.pokedex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.repository.PokemonRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonViewModel(private val repository: PokemonRepository): ViewModel() {

    val myResponse: MutableLiveData<Response<PokemonResponse>> = MutableLiveData()

    fun getPokemon(){
        viewModelScope.launch {
            val response = repository.getPokemon()
            myResponse.value = response
        }
    }
}