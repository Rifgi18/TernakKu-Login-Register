package com.dicoding.ternakku.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.ternakku.ListPenyakitAdapter
import com.dicoding.ternakku.ListPenyakitAdapterLocal
import com.dicoding.ternakku.data.retrofit.Disease
import com.dicoding.ternakku.data.retrofit.roomdatabase.FavoriteDisease
import com.dicoding.ternakku.databinding.ActivityFavoriteBinding
import com.dicoding.ternakku.ui.detail.DetailViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: FavoriteAdapter
    private lateinit var viewModel: FavoriteViewModel
    private val list = ArrayList<FavoriteDisease>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteAdapter(this)
        binding.rvFavorite.layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.setHasFixedSize(true)
        binding.rvFavorite.adapter = adapter

        viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]

        viewModel.getFavoriteDiseases()?.observe(this) {
            adapter.setListFav(it)
        }
    }

    private fun getListPenyakit(deseases: List<FavoriteDisease>): ArrayList<FavoriteDisease> {
        val listDesease = ArrayList<FavoriteDisease>()
        for (desease in deseases) {
            val userMapped = FavoriteDisease(
                desease.id,
                desease.name,
                desease.detail,
                desease.handle
            )
            listDesease.add(userMapped)
        }
        return listDesease
    }
}