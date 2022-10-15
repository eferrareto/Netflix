package com.example.netflix.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.netflix.model.Category
import com.example.netflix.model.Movie
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class CategoryTask (private val callback: Callback){

    private val handler = Handler(Looper.getMainLooper())
    private val executer = Executors.newSingleThreadExecutor()

    interface Callback{
        fun onResult(categories : List<Category>)
    }

    fun execute(url: String) {


        executer.execute {

            var urlConection: HttpsURLConnection? = null
            var buffer: BufferedInputStream? = null
            var stream: InputStream? = null

            try {

                val request = URL(url) // abrir uma URL
                urlConection = request.openConnection() as HttpsURLConnection // Abrir uma conexão
                urlConection.readTimeout = 2000
                urlConection.connectTimeout = 2000

                val statusCode: Int = urlConection.responseCode

                if (statusCode > 400) {
                    throw IOException("Erro")
                }

                stream = urlConection.inputStream
                buffer = BufferedInputStream(stream)

                //val jsonAsString = stream.bufferedReader().use { it.readText() }

                buffer = BufferedInputStream(stream)

                val jsonAsString = toString(buffer)

                val categories = toCategory(jsonAsString)



                handler.post{
                    callback.onResult(categories)
                }

            } catch (e: IOException) {
                Log.e("teste", "ola")
            } finally {

                urlConection?.disconnect()
                stream?.close()
                buffer?.close()

            }
        }
    }

    private fun toString(stream : InputStream) : String{
        val bytes = ByteArray(1024)
        val baos = ByteArrayOutputStream()
        var read : Int
        while (true){
            read = stream.read(bytes)
            if (read <= 0){
                break
            }
            baos.write(bytes, 0, read)
        }
        return String(baos.toByteArray())
    }

    private fun toCategory(jsonAsString: String): List<Category> {

        val categories = mutableListOf<Category>()

        val jsonRoot = JSONObject(jsonAsString)
        val jsonCategories = jsonRoot.getJSONArray("category")

        for (i in 0 until jsonCategories.length()) {

            val jsonCategorie = jsonCategories.getJSONObject(i)

            val title = jsonCategorie.getString("title")
            val jsonMovies = jsonCategorie.getJSONArray("movie")

            val movies = mutableListOf<Movie>()

            for (j in 0 until jsonMovies.length()) {

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