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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = MainAdapter()

        fetchJson()
        display("Thiago Cavalcante")
    }
    fun fetchJson(){
        val url = "https://api.themoviedb.org/3/movie/top_rated?api_key=3bd23754eab8b7f3bfb1f5a8955e3fe4"
        val requestTopRated = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(requestTopRated).enqueue(object: Callback{
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val homeApp = gson.fromJson(body, maskApp::class .java)

                }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed request Call")
            }
        })
    }

}

class maskApp(val results :List<movieList>)
class movieList(val vote_count:Int, val id: Int, val video: Boolean, val vote_averege: Float, val title: String, val popularity: Float,
                val poster_path: String, val original_language: String, val original_title: String, val genre_kids: genreKids,
                val backdrop_path: String, val adult: Boolean, val overview: String, val release_date: Date)
class genreKids(val genreKidsList: Int)