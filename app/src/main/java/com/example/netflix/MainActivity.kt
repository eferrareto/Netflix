package com.example.netflix

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Category
import com.example.netflix.model.Movie
import com.example.netflix.util.CategoryTask

class MainActivity : AppCompatActivity() {

    private lateinit var recycler : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_item)

        val categories = mutableListOf<Category>()

        recycler = findViewById(R.id.rvMain)
        recycler.adapter = CategorieAdapter(categories)
        recycler.layoutManager = LinearLayoutManager(this)

        CategoryTask().execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=72a9a30a-a43c-46b8-a128-34e9beea0d6c")

    }



}