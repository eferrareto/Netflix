package com.example.netflix

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Category
import com.example.netflix.model.Movie
import com.example.netflix.util.CategoryTask

class MainActivity : AppCompatActivity(), CategoryTask.Callback  {

    private lateinit var recycler : RecyclerView
    private val categories = mutableListOf<Category>()
    private lateinit var adapter : CategorieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_item)



        recycler = findViewById(R.id.rvMain)

        adapter = CategorieAdapter(categories)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        CategoryTask(this).execute("https://api.tiagoaguiar.co/netflixapp/home?apiKey=72a9a30a-a43c-46b8-a128-34e9beea0d6c")

    }


    override fun onResult(categories: List<Category>) {
        Log.e("teste", "ola")
        this.categories.clear()
        this.categories.addAll(categories)
        adapter.notifyDataSetChanged()
    }


}