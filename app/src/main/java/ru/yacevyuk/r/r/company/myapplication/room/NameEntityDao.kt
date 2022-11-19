package ru.yacevyuk.r.r.company.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NameEntityDao {

    @Query("select * from Name")
    fun getAll(): LiveData<List<Name>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(name: Name)

    @Delete
    suspend fun delete(name: Name)

}