package com.example.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.netflix.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = mutableListOf<Movie>()
        for (i in 0 until 60){
            val movie = Movie("https: $i text")
            movies.add(movie)
        }

        recycler = findViewById(R.id.recyclerV)
        recycler.adapter = MainAdapter(movies)
        recycler.layoutManager = LinearLayoutManager(this)

    }



}