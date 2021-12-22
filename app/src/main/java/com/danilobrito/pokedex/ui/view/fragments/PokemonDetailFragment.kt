package com.danilobrito.pokedex.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.model.PokemonDetail
import com.danilobrito.pokedex.util.Constants.Companion.POKEMON_NAME
import com.danilobrito.pokedex.util.Constants.Companion.POKEMON_URL
import com.danilobrito.pokedex.util.GlideUtil
import com.danilobrito.pokedex.util.NetworkResult
import com.danilobrito.pokedex.util.TypeColor
import com.danilobrito.pokedex.viewmodel.PokemonViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import kotlinx.android.synthetic.main.item_type_pokemon.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailFragment : Fragment() {

    private val pokemonUrl: String by lazy {
        arguments?.getString(POKEMON_URL)
            ?: throw IllegalArgumentException("Not found url")
    }

    private val pokemonName: String by lazy {
        arguments?.getString(POKEMON_NAME)
            ?: throw IllegalArgumentException("Not found name")
    }

    private val context by lazy {
        activity ?: throw IllegalArgumentException("Not found context")
    }

    private val viewModel: PokemonViewModel by viewModel()
    private val glideUtil = GlideUtil()
    private val typeColor = TypeColor()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPokemonDetail(pokemonName)

    }

    override fun onResume() {
        super.onResume()
        arrow_detail.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun requestPokemonDetail(name: String) {
        viewModel.getPokemonDetail(name)
        viewModel.pokemonResponseDetails.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let { bindView(it) }
                }

                is NetworkResult.Error -> {
                    Toast.makeText(context, response.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun bindView(pokemonDetail: PokemonDetail) {
        val typeColor_1st = pokemonDetail.types[0].type.name

        index_detail.text = pokemonDetail.id.toString()
        glideUtil.loadImage(pokemonUrl, image_detail, context)
        glideUtil.setCardColor(pokemonUrl, header_detail, image_detail, context)
        name_detail.text = pokemonDetail.name
        type1_detail.text = typeColor_1st
        typeColor(typeColor_1st)

        if (pokemonDetail.types.size > 1) {
            val typeColor_2nd = pokemonDetail.types[1].type.name
            type2_detail.text = typeColor_2nd
            typeColor(typeColor_2nd)
        }

        weight_detail.text = pokemonDetail.weight
        height_detail.text = pokemonDetail.height

//        toast(pokemonDetail.name)
    }

    private fun typeColor(color: String) {
        typeColor.setColorType(color, type1_detail, context)
    }

    fun toast(name: String) {
        Toast.makeText(context, "Success $name", Toast.LENGTH_SHORT).show()
    }
}