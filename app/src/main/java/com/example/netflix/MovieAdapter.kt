package com.example.netflix

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Movie
import com.example.netflix.util.DownloadImageTask

class MovieAdapter(val movies: List<Movie>, @LayoutRes val layoutId: Int, private val onItemClickListener: (Int) -> Unit ) :
    RecyclerView.Adapter<MovieAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MainViewHolder(view,)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, onItemClickListener: (Int) -> Unit) {
            val imageCover: ImageView = itemView.findViewById(R.id.imgCover)

            imageCover.setOnClickListener {
                onItemClickListener(movie.id)
            }

            DownloadImageTask(object : DownloadImageTask.Callback {
                override fun onResult(bitmap: Bitmap) {
                    imageCover.setImageBitmap(bitmap)
                }
            }).execute(movie.coverUrl)

            /*
        imageCover.setImageResource(movie.coverUrl)
        */
        }
    }
}
