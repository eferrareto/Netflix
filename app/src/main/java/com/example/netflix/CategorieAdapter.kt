package com.example.netflix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.model.Category

class CategorieAdapter(val categorie: List<Category>, private val onItemClickListener:  (Int) -> Unit):
    RecyclerView.Adapter<CategorieAdapter.CategorieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategorieViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategorieViewHolder, position: Int) {
        val cate = categorie[position]
        holder.bind(cate, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return categorie.size
    }


    inner class CategorieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categorie: Category, onItemClickListener: (Int) -> Unit) {
            val txtTitle: TextView = itemView.findViewById(R.id.txt_title)
            val rvCategory: RecyclerView = itemView.findViewById(R.id.rv_category)

            txtTitle.text = categorie.name
            // criando a recyclerView de novo
            rvCategory.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            rvCategory.adapter = MovieAdapter(categorie.movie, R.layout.movie_item, onItemClickListener)
        }
    }
}