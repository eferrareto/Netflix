package com.example.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Category
import com.example.netflix.model.Movie

class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_item)

        val categories = mutableListOf<Category>()

        for (j in 0 until 10){
            val movies = mutableListOf<Movie>()
            for (i in 0 until 15){
                val movie = Movie(R.drawable.movie)
                movies.add(movie)
            }
            val cate = Category("cat $j", movies)
            categories.add(cate)
        }



        recycler = findViewById(R.id.rvMain)
        recycler.adapter = CategorieAdapter(categories)
        recycler.layoutManager = LinearLayoutManager(this)

    }



}