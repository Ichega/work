package ru.yacevyuk.r.r.company.myapplication.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.yacevyuk.r.r.company.myapplication.room.Name
import ru.yacevyuk.r.r.company.myapplication.room.NameEntityDao

import ru.yacevyuk.r.r.company.myapplication.ui.post.models.Post


@Database(entities = [Post::class, Name::class], version = 1)
abstract class DbConnection : RoomDatabase() {
    abstract fun postDao(): PostEntityDao
    abstract fun nameDao(): NameEntityDao

    companion object {
        private var INSTANCE: DbConnection? = null
        fun getDatabase(context: Context): DbConnection {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context,DbConnection::class.java, "database")
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}