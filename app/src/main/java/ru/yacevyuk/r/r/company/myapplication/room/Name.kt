package ru.yacevyuk.r.r.company.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Name(var name: String){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}