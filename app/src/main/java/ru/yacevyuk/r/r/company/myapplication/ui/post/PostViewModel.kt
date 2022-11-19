package ru.yacevyuk.r.r.company.myapplication.ui.post


import android.app.Application
import androidx.lifecycle.*
import ru.yacevyuk.r.r.company.myapplication.db.DbConnection
import ru.yacevyuk.r.r.company.myapplication.db.PostEntityDao
import ru.yacevyuk.r.r.company.myapplication.ui.post.models.Post
import ru.yacevyuk.r.r.company.myapplication.ui.post.restapi.PostService
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yacevyuk.r.r.company.myapplication.ui.post.adapter.PostsRepository

class PostViewModel(application: Application) : AndroidViewModel(application) {


   private val postService: PostService
   private val postEntityDao: PostEntityDao
   init {
      val retrofit = Retrofit.Builder()
         .baseUrl("https://jsonplaceholder.typicode.com")
         .addConverterFactory(GsonConverterFactory.create())
         .build()
      postService = retrofit.create(PostService::class.java)

      postEntityDao = DbConnection.getDatabase(application.applicationContext).postDao();
   }

   private val postsRepository = PostsRepository(postService, postEntityDao)



   var posts: MutableLiveData<List<Post>?> = MutableLiveData()
   init {
      viewModelScope.launch {
         postsRepository.getAll().collectLatest {
            posts.value = it
         }
      }
   }

   fun saveInCache(){
      viewModelScope.launch {
         posts.value?.let { postEntityDao.insert(it) }
      }
   }



}