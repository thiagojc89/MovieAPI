package com.thiagojc89.franfodao

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    fun display(string: String){
        println(">>>>> $string")
    }
    val apiKey = "3bd23754eab8b7f3bfb1f5a8955e3fe4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = MainAdapter()
        fetchGetConfiguration()
        fetchJson()
        display("Thiago Cavalcante")
    }

//    fun fetchGetConfiguration(){
//        val urlConfig = "https://api.themoviedb.org/3/configuration?api_key=3bd23754eab8b7f3bfb1f5a8955e3fe4"
//        val requestConfig = Request.Builder().url(urlConfig).build()
//        val client = OkHttpClient()
//        client.newCall(requestConfig).enqueue(object: Callback{
//            override fun onResponse(call: Call?, response: Response?) {
//                val body = response?.body()?.string()
//                val gson = GsonBuilder().create()
//                val apiConfig = gson.fromJson(body, ApiConfig::class .java)
//
//            }
//            override fun onFailure(call: Call?, e: IOException?) {
//                println("Failed request Call")
//            }
//
//        })
//    }
//    fun fetchJson(){
//        val urlApiRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey"
//        val requestTopRated = Request.Builder().url(urlApiRated).build()
//        val client = OkHttpClient()
//        client.newCall(requestTopRated).enqueue(object: Callback{
//            override fun onResponse(call: Call?, response: Response?) {
//                val body = response?.body()?.string()
//                val gson = GsonBuilder().create()
//                val homeFeed = gson.fromJson(body, Homefeed::class .java)
//
//                runOnUiThread {
//                    recyclerView_main.adapter = MainAdapter(homeFeed)
//                }
//
//            }
//
//            override fun onFailure(call: Call?, e: IOException?) {
//                println("Failed request Call")
//            }
//        })
//    }

}
//class ApiConfig(val images :List<ImgConfig>)
//class ImgConfig(val base_url: String, val secure_base_url: String, val backdrop_sizes: List<BackDrop>)
//class BackDrop(var x: List<String>? = null)

class Homefeed(val results :List<MovieList>)
class MovieList(val id: Int, val video: Boolean,
                val title: String, val poster_path: String,
                val backdrop_path: String, val release_date: Date)
