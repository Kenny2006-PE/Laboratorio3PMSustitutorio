package com.palacin.kenny.laboratoriocalificadosustitutorio.network


import com.palacin.kenny.laboratoriocalificadosustitutorio.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}
