package com.example.netflix.util

import com.example.netflix.model.Category
import com.example.netflix.model.Movie
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask {
    fun execute(url: String) {
        val executer = Executors.newSingleThreadExecutor()

        executer.execute {
            try {


                val request = URL(url) // abrir uma URL
                    val urlConection =
                    request.openConnection() as HttpsURLConnection // Abrir uma conexão
                urlConection.readTimeout = 2000
                urlConection.connectTimeout = 2000

                val statusCode: Int = urlConection.responseCode
                if (statusCode > 400) {
                    throw IOException("Erro")
                }

                val stream = urlConection.inputStream
                val jsonAsString = stream.bufferedReader().use { it.readText() }
                val categories = toCategory(jsonAsString)


            } catch (e: IOException) {

            }
        }
    }
    private fun toCategory(jsonAsString: String): List<Category>{
        val categories = mutableListOf<Category>()

        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("category")
        for (i in 0 until jsonCategories.length()){

            val jsonCategorie = jsonCategories.getJSONObject(i)

            val title = jsonCategorie.getString("title")
            val jsonMovies = jsonCategorie.getJSONArray("movie")

            val movies = mutableListOf<Movie>()
            for (j in 0 until jsonMovies.length()){

                val jsonMovie = jsonMovies.getJSONObject(j)

                val id = jsonMovie.getInt("id")
                val coverUrl = jsonMovie.getString("cover_url")
                movies.add(Movie(id, coverUrl))
            }
            categories.add(Category(title, movies))

        }

        return categories
    }
}