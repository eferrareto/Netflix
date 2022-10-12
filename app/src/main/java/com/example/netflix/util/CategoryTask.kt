package com.example.netflix.util

import java.util.concurrent.Executors

class CategoryTask {
    fun execute(url: String){
        val executer = Executors.newSingleThreadExecutor()

        executer.execute{
            
        }
    }
}