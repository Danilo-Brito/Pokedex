package com.danilobrito.pokedex.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.util.Constants
import com.danilobrito.pokedex.util.Constants.Companion.POKEMON_NAME
import com.danilobrito.pokedex.util.Constants.Companion.POKEMON_URL
import com.danilobrito.pokedex.util.NetworkResult
import com.danilobrito.pokedex.viewmodel.PokemonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

class PokemonDetailFragment : Fragment() {

    private val pokemomUrl: String by lazy {
        arguments?.getString(POKEMON_URL)
            ?: throw IllegalArgumentException("Not found url")
    }

    private val pokemomName: String by lazy {
        arguments?.getString(POKEMON_NAME)
            ?: throw IllegalArgumentException("Not found name")
    }

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPokemonDetail(pokemomName)
    }

    private fun requestPokemonDetail(name: String) {
        viewModel.getPokemonDetail(name)
        viewModel.pokemonResponseDetails.observe(viewLifecycleOwner, { response ->
            when(response) {
                is NetworkResult.Success -> {
                    response.data?.let { toast(it.name) }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun toast(name: String){
        Toast.makeText(context, "Success $name", Toast.LENGTH_SHORT ).show()
    }
}