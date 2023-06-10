package com.dicoding.ternakku.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import com.dicoding.ternakku.data.retrofit.roomdatabase.FavoriteDisease

class FavoriteDiffCallback(private val mOldFavList: List<FavoriteDisease>, private val mNewFavList: List<FavoriteDisease>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavList[oldItemPosition].id == mNewFavList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFav = mOldFavList[oldItemPosition]
        val newFav = mNewFavList[newItemPosition]
        return oldFav.name == newFav.name &&
               oldFav.detail == newFav.detail
    }

}