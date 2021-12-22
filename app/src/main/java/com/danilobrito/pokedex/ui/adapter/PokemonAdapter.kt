package com.danilobrito.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.model.Pokemon
import com.danilobrito.pokedex.model.PokemonResponse
import com.danilobrito.pokedex.ui.adapter.extension.PokemonItemClick
import com.danilobrito.pokedex.util.GlideUtil
import com.danilobrito.pokedex.util.PokemonDiffCallback
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val context: Context,
    private val pokemonClick: PokemonItemClick
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemon = emptyList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val glideUtil = GlideUtil()
        val pokemon = pokemon[position]

        holder.name.text = pokemon.name
        val imageUrl = pokemon.getImageUrl()
        val image = holder.image
        val cardView = holder.cardView

        glideUtil.loadImage(imageUrl, image, context)

        glideUtil.setCardColor(imageUrl, cardView, image, context)

        cardView.setOnClickListener {
            pokemonClick.onClick(pokemon.name, pokemon.getImageUrl())
        }
    }

    override fun getItemCount() = pokemon.size

    fun setData(newData: PokemonResponse) {
        val pokemonDiffUtil = PokemonDiffCallback(pokemon, newData.results)
        val diffUtilsResult = DiffUtil.calculateDiff(pokemonDiffUtil)
        pokemon = newData.results
        diffUtilsResult.dispatchUpdatesTo(this)
    }

    private fun toast(name: String) {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
    }

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.name_detail
        val image = itemView.image_detail
        val cardView = itemView.cardView
    }
}