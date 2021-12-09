package com.danilobrito.pokedex.util

import androidx.recyclerview.widget.DiffUtil
import com.danilobrito.pokedex.model.Pokemon

class UserListDiffCallback(
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