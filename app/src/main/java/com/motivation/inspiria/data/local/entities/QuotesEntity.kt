package com.motivation.inspiria.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_quotes")
data class QuotesEntity(
    @PrimaryKey
    val id:Int,
    val quote:String,
    val author:String,
    val category:String,
    val date:Int,
    val month:String,
    val favorite:Int = 0
)