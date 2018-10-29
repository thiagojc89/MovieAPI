package com.thiagojc89.franfodao

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.google.gson.GsonBuilder
import com.thiagojc89.franfodao.model.Remote.Homefeed
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun movieDescription(view: View) {
        val intent = Intent(this, MovieDescription()::class.java)
        startActivity(intent)
    }

    fun fetchJson(view: View) {
        view.visibility = View.GONE
        recyclerView_main.visibility = View.VISIBLE
        val urlApiRated = "https://api.themoviedb.org/3/movie/top_rated?api_key=${getString(R.string.api_key)}"
        val requestTopRated = Request.Builder().url(urlApiRated).build()
        val client = OkHttpClient()

        client.newCall(requestTopRated).enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()
                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, Homefeed::class.java)

                runOnUiThread {
                    recyclerView_main.apply {
                        layoutManager = GridLayoutManager(context, 2)
                        adapter = MainAdapter(homeFeed)
                    }
                }

            }

            override fun onFailure(call: Call?, e: IOException?) {
                println(getString(R.string.request_error))
            }
        })
    }

}
