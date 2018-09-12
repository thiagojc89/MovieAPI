package com.thiagojc89.franfodao

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Button
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_content_row.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val apiKey = "3bd23754eab8b7f3bfb1f5a8955e3fe4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = GridLayoutManager(this, 2)

    }

    fun movieDescription(view: View){
        val intent = Intent(this, movieDescription()::class.java)
        startActivity(intent)
    }

    fun fetchJson(view: View) {
        view.visibility = View.GONE
        recyclerView_main.visibility = View.VISIBLE
        val urlApiRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey"
        val requestTopRated = Request.Builder().url(urlApiRated).build()
        val client = OkHttpClient()

        client.newCall(requestTopRated).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, Homefeed::class.java)

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

class Homefeed(val results: List<MovieList>)
class MovieList(val id: Int, val title: String, val poster_path: String)
