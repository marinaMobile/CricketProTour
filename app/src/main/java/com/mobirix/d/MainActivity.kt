package com.mobirix.d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobirix.d.data.model.Teams
import com.mobirix.d.data.remote.ServNews
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getTeams()
    }

    private fun getTeams() {
        val news: Call<Teams> = ServNews.newsInst.getArticle()

        news.enqueue(object: Callback<Teams>
        {
            override fun onResponse(call: Call<Teams>, response: Response<Teams>) {

                val team: Teams? = response.body()
                Log.d("onResponse", "Ok")

                if(team!=null) {
                    newsRv.adapter = RecAdapter(this@MainActivity, team.data)
                    newsRv.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<Teams>, t: Throwable) {
                Log.d("onFailure", "Bug")
            }
        })
    }
}