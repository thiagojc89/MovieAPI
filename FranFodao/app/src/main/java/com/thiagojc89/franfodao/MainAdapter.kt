package com.thiagojc89.franfodao

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.movie_content_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>(){

    val videoTitles = listOf("uno","dos","treze","catorze")
    override fun getItemCount(): Int {
        return videoTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.movie_content_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val videoTitle = videoTitles.get(position)
        holder?.view?.textView_video_title?.text = videoTitle

    }
}
class CustomViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
}
