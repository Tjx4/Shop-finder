package com.entersekt.shopfinder.persistance.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.platform45.fx45.persistance.room.tables.pairHistory.PairHistoryDAO
import com.platform45.fx45.persistance.room.tables.pairHistory.PairHistoryTable

@Database(entities = [PairHistoryTable::class], version = 1, exportSchema = false)
 abstract class SFDb : RoomDatabase() {
    abstract val pairHistoryDAO: PairHistoryDAO

    companion object{
        @Volatile
        private var INSTANCE: SFDb? = null

        fun getInstance(context: Context): SFDb {
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(context.applicationContext, SFDb::class.java, "sf_db")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return  instance
            }
        }
    }
}