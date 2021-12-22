package com.danilobrito.pokedex.ui.view.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
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
import kotlinx.android.synthetic.main.stats_pokemon.*
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
        index_detail.text = pokemonDetail.id.toString()
        glideUtil.loadImage(pokemonUrl, image_detail, context)
        glideUtil.setCardColor(pokemonUrl, header_detail, image_detail, context)
        name_detail.text = pokemonDetail.name

        val typeColor_1st = pokemonDetail.types[0].type.name
        typeColor(typeColor_1st, type1_detail)
        type1_detail.text = typeColor_1st

        if (pokemonDetail.types.size > 1) {
            val typeColor_2nd = pokemonDetail.types[1].type.name
            typeColor(typeColor_2nd, type2_detail)
            type2_detail.text = typeColor_2nd
        }

        weight_detail.text = pokemonDetail.weight
        height_detail.text = pokemonDetail.height

        progressBar(pokemonDetail)
    }

    private fun progressBar(pokemonDetail: PokemonDetail) {
        hp_progressBar.max = 200
        atk_progressBar.max = 200
        sp_atk_progressBar.max = 200
        df_progressBar.max = 200
        sp_df_progressBar.max = 200
        speed_progressBar.max = 200

        val hp = pokemonDetail.stats[0].base_stat
        val atk = pokemonDetail.stats[1].base_stat
        val def = pokemonDetail.stats[2].base_stat
        val spAtk = pokemonDetail.stats[3].base_stat
        val spDef = pokemonDetail.stats[4].base_stat
        val speed = pokemonDetail.stats[5].base_stat


        hp_value.text = hp.toString()
        atk_value.text = atk.toString()
        spAtk_value.text = spAtk.toString()
        def_value.text = def.toString()
        spDef_value.text = spDef.toString()
        speed_value.text = speed.toString()

        animateProgress(hp_progressBar, hp)
        animateProgress(atk_progressBar, atk)
        animateProgress(sp_atk_progressBar, spAtk)
        animateProgress(df_progressBar, def)
        animateProgress(sp_df_progressBar, spDef)
        animateProgress(speed_progressBar, speed)

        setColorProgress(hp_progressBar, hp)
        setColorProgress(atk_progressBar, atk)
        setColorProgress(sp_atk_progressBar, spAtk)
        setColorProgress(df_progressBar, def)
        setColorProgress(sp_df_progressBar, spDef)
        setColorProgress(speed_progressBar, speed)
    }

    private fun setColorProgress(progressBar: ProgressBar?, value: Int) {
        if (value >= 100) {
            progressBar?.progressTintList = context?.resources?.getColorStateList(R.color.grass)!!
        }
    }

    private fun animateProgress(progressBar: ProgressBar?, baseStat: Int) {
        ObjectAnimator.ofInt(progressBar, "progress", baseStat)
            .setDuration(2000)
            .start()
    }

    private fun typeColor(color: String, type_detail: AppCompatButton) {
        typeColor.setColorType(color,type_detail, context)
    }
}