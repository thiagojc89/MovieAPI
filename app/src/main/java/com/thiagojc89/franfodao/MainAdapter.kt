package com.thiagojc89.franfodao

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_content_row.view.*

class MainAdapter(val homeFeed: Homefeed): RecyclerView.Adapter<CustomViewHolder>(){

    override fun getItemCount(): Int {
        return homeFeed.results.count()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_content_row,parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val results = homeFeed.results.get(position)
        holder?.view?.textView_video_title?.text = results.title
        val urlImageOriginal = "https://image.tmdb.org/t/p/w500/"
        val imagem = holder?.view.imageViewPoster
        Picasso.with(holder.view.context)
                .load(urlImageOriginal+results.poster_path)
                .into(imagem)
    }
}

class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
}