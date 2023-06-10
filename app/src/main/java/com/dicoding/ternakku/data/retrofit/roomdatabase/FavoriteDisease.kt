package com.dicoding.ternakku.data.retrofit.roomdatabase

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "favorite_desease")
@Parcelize
data class FavoriteDisease (
    @PrimaryKey
    val id : Int,
    val name : String,
    val detail : String,
    val handle : String
):Parcelable