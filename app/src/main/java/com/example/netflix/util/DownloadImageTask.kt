package com.example.netflix.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.netflix.model.Category
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class DownloadImageTask(private val callback: Callback) {

    private val handler = Handler(Looper.getMainLooper())
    private val executer = Executors.newSingleThreadExecutor()

    interface Callback{
        fun onResult(bitmap: Bitmap)
    }

    fun execute(url : String){
        executer.execute{
            var urlConection: HttpsURLConnection? = null
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
                val bitmap = BitmapFactory.decodeStream(stream)

                handler.post{
                    callback.onResult(bitmap)
                }
            } catch (e: IOException){
                Log.e("teste", "ola")
            } finally {
                urlConection?.disconnect()
                stream?.close()
            }
        }
    }
}