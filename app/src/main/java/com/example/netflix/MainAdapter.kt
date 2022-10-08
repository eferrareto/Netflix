package com.example.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Movie

class MainAdapter(val movies : List<Movie>) : RecyclerView.Adapter<MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}
    class MainViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(movie: Movie){
            val txtTest : TextView = itemView.findViewById(R.id.textView)
            txtTest.text = movie.coverUrl
        }
    }
