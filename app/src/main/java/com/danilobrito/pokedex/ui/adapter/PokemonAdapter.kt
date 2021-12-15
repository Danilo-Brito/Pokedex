package com.danilobrito.pokedex.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.model.*
import com.danilobrito.pokedex.util.PokemonDiffCallback
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val context: Context,
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    private var pokemon = emptyList<Pokemon>()
    private var detail = emptyList<Types>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemon[position]

            holder.name.text = pokemon.name
            val imageUrl = pokemon.getImageUrl()
            val image = holder.image
            val cardView = holder.cardView

        loadImage(imageUrl, image)
        setCardColor(imageUrl, cardView, image)
    }

    private fun setCardColor(
        imageUrl: String,
        cardView: MaterialCardView,
        image: AppCompatImageView
    ) {
        Glide.with(context).load(imageUrl)
            .listener(
                GlidePalette.with(imageUrl)
                    .use(BitmapPalette.Profile.MUTED_LIGHT)
                    .intoCallBack { palette ->
                        val rgb = palette?.dominantSwatch?.rgb
                        if (rgb != null) {
                            cardView.setCardBackgroundColor(rgb)
                        }
                    }.crossfade(true)
            ).into(image)
    }

    private fun loadImage(
        imageUrl: String,
        image: AppCompatImageView
    ) {
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .into(image)
    }

    override fun getItemCount() = pokemon.size

    fun setData(newData: PokemonResponse) {
        val pokemonDiffUtil = PokemonDiffCallback(pokemon, newData.results)
        val diffUtilsResult = DiffUtil.calculateDiff(pokemonDiffUtil)
        pokemon = newData.results
        diffUtilsResult.dispatchUpdatesTo(this)
    }

    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val image = itemView.image
        val cardView = itemView.cardView
    }
}