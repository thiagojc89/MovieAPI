package com.thiagojc89.franfodao

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

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
                println(body)

                }

            override fun onFailure(call: Call?, e: IOException?) {
                println("Failed request Call")
            }
        })
    }

}
