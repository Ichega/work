package ru.yacevyuk.r.r.company.myapplication.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ru.yacevyuk.r.r.company.myapplication.db.DbConnection
import ru.yacevyuk.r.r.company.myapplication.room.NameEntityDao

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

  private val nameDao: NameEntityDao = DbConnection.getDatabase(application).nameDao()
  val names = nameDao.getAll()

  }