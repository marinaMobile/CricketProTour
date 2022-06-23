package com.mobirix.d.data.remote

import com.mobirix.d.data.model.Teams
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val url = "https://cricket.sportmonks.com/api/v2.0/"
const val key = "JhZQsZSjBI4ql5aQH8rpOAsbCn9q30bxZRI5e1juq4gsGBTYXRsmx7CfvfY1"

interface ApiService {

    @GET("teams?api_token=$key")

    fun getArticle() : Call<Teams>
}
object ServNews {
    val newsInst: ApiService

    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInst = retrofit.create(ApiService::class.java)
    }
}