package com.danilobrito.pokedex.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.ui.PokemonItemClick
import com.danilobrito.pokedex.ui.adapter.PokemonAdapter
import com.danilobrito.pokedex.util.Constants.Companion.POKEMON_NAME
import com.danilobrito.pokedex.util.Constants.Companion.POKEMON_URL
import com.danilobrito.pokedex.util.NetworkResult
import com.danilobrito.pokedex.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokedex.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.IllegalArgumentException

class PokedexFragment : Fragment(), PokemonItemClick {

    private val pokemonAdapter by lazy {
        context?.let {
            PokemonAdapter(context = it, pokemonClick = this)
        } ?: throw IllegalArgumentException("Context error")
    }

    private val viewModel: PokemonViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokedex, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        requestPokemon()
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = pokemonAdapter
    }

    private fun requestPokemon() {
        viewModel.getPokemon()
        viewModel.pokemonResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { pokemonAdapter.setData(it) }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onClick(name: String, imageUrl: String) {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        val pokemonDetailFragment = PokemonDetailFragment()
        val data = Bundle()
        data.putString(POKEMON_NAME, name)
        data.putString(POKEMON_URL, imageUrl)
        pokemonDetailFragment.arguments = data
        transaction?.replace(R.id.activity_container, pokemonDetailFragment)
            ?.addToBackStack(null)
            ?.commit()
    }


}