package com.thiagojc89.franfodao

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {

    fun display(string: String){
        println(">>>>> $string")
    }
    val apiKey = "3bd23754eab8b7f3bfb1f5a8955e3fe4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter()
        fetchGetConfiguration()
        fetchJson()
        display("Thiago Cavalcante")
    }

    fun fetchGetConfiguration(){
        val urlConfig = "https://api.themoviedb.org/3/configuration?api_key=3bd23754eab8b7f3bfb1f5a8955e3fe4"

    }

    fun fetchJson(){
        val urlApiRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey"
        val requestTopRated = Request.Builder().url(urlApiRated).build()
        val client = OkHttpClient()
        client.newCall(requestTopRated).enqueue(object: Callback{
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, Homefeed::class .java)

                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homeFeed)
                }

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed request Call")
            }
        })
    }

}

class Homefeed(val results :List<movieList>)
class movieList(val id: Int, val video: Boolean,
                val title: String, val poster_path: String,
                val backdrop_path: String, val release_date: Date)
