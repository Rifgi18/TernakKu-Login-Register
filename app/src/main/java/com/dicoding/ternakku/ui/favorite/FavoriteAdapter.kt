package com.dicoding.ternakku.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.ternakku.MainActivity
import com.dicoding.ternakku.data.retrofit.roomdatabase.FavoriteDisease
import com.dicoding.ternakku.databinding.ListPenyakitBinding
import com.dicoding.ternakku.ui.detail.DetailActivity


class FavoriteAdapter(private val appCompatActivity: AppCompatActivity) : RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    private val listUser = ArrayList<FavoriteDisease>()
    fun setListFav(listUser: List<FavoriteDisease>) {
        val diffCallback = FavoriteDiffCallback(this.listUser, listUser)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listUser.clear()
        this.listUser.addAll(listUser)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class FavViewHolder(private val binding: ListPenyakitBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favUser: FavoriteDisease) {
            with(binding) {
                tvName.text = favUser.name
                tvDescription.text = favUser.detail
                cardView.setOnClickListener {
                    val intent = Intent(it.context, DetailActivity::class.java)
                    intent.putExtra(MainActivity.EXTRA_NAME, favUser)
                    it.context.startActivity(intent)
                }
            }

        }

    }

    override fun onCreateViewHolder(
        view: ViewGroup,
        viewType: Int
    ): FavViewHolder {
        val binding = ListPenyakitBinding.inflate(LayoutInflater.from(view.context), view, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val getFav = listUser[position]
        holder.bind(getFav)

        holder.itemView.setOnClickListener {
            val moveDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            moveDetail.putExtra(MainActivity.EXTRA_NAME, getFav.name)
            holder.itemView.context.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = listUser.size
}