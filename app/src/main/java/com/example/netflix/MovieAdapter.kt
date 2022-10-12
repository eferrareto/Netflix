package com.example.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Movie

class MovieAdapter(val movies: List<Movie>, @LayoutRes val layoutId: Int) :
    RecyclerView.Adapter<MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
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

class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie) {
        val imageCover: ImageView = itemView.findViewById(R.id.imgCover)

        /*
        imageCover.setImageResource(movie.coverUrl)
        */
    }
}
