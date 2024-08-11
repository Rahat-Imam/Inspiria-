package com.motivation.inspiria.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.motivation.inspiria.data.local.dao.QuotesDao
import com.motivation.inspiria.data.local.entities.QuotesEntity


@Database(
    entities = [QuotesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuotesDb : RoomDatabase() {
    abstract fun quotesDao(): QuotesDao

    companion object {
        fun getDatabase(context: Context): QuotesDb {
            return Room.databaseBuilder(context, QuotesDb::class.java, "quotes.db")
                .createFromAsset("quotes_db.db")
                .build()
        }
    }
}