package ru.yacevyuk.r.r.company.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.RoomDatabase
import ru.yacevyuk.r.r.company.myapplication.db.DbConnection

class NameRepository(private val userDao: NameEntityDao) {

    var name: LiveData<List<Name>> = userDao.getAll()

    fun getAll(): LiveData<List<Name>>{
        return name
    }

    suspend fun insert(name: Name) {
        userDao.insert(name)
    }
}




