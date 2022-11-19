package ru.yacevyuk.r.r.company.myapplication.ui.post.adapter

import ru.yacevyuk.r.r.company.myapplication.db.PostEntityDao
import ru.yacevyuk.r.r.company.myapplication.ui.post.restapi.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.yacevyuk.r.r.company.myapplication.ui.post.models.Post

class PostsRepository(
    private val postService: PostService,
    private val postEntityDao: PostEntityDao
) {


    suspend fun getAll(): Flow<List<Post>?> {
        return channelFlow {
            send(fromLocalCache())
            fromRemove().collectLatest {
                send(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun fromRemove(): Flow<List<Post>?> {
        return callbackFlow {

            val callBack = object : Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    trySend(response.body())
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    trySend(null)
                }
            }
            postService.getAll().enqueue(callBack)
            awaitClose{
                cancel()
            }
        }
    }

    private suspend fun fromLocalCache(): List<Post> {
        return postEntityDao.getAll()
    }


}