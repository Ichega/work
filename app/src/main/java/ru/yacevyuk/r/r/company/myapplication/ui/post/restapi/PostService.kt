package ru.yacevyuk.r.r.company.myapplication.ui.post.restapi

import ru.yacevyuk.r.r.company.myapplication.ui.post.models.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostService {
    @GET("posts/")
    fun getAll(): Call<List<Post>>
}