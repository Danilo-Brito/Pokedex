package com.danilobrito.pokedex.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danilobrito.pokedex.R
import com.danilobrito.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(
    private val context: Context,
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    var pokemon = emptyList<Pokemon>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                UserListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemon[position]

        holder.name.text = pokemon.name
        Glide.with(context)
            .load(pokemon.getImageUrl())
            .centerCrop()
            .into(holder.image)


//        Glide.with(this).load(url)
//            .listener(GlidePalette.with(url)
//                .use(GlidePalette.Profile.MUTED_DARK)
//                .intoBackground(textView)
//                .intoTextColor(textView)
//
//                .use(GlidePalette.Profile.VIBRANT)
//                .intoBackground(titleView, GlidePalette.Swatch.RGB)
//                .intoTextColor(titleView, GlidePalette.Swatch.BODY_TEXT_COLOR)
//                .crossfade(true)
//            );
//        .into(imageView)
    }

    override fun getItemCount() = pokemon.size

    inner class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.name
        val image = itemView.image
        val cardView = itemView.cardView
    }

    inner class UserListDiffCallback(
        private val oldList: List<Pokemon>,
        private val newList: List<Pokemon>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name.equals(newList[newItemPosition].name)
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return true
        }
    }
}