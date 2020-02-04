package com.example.oblig1

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oblig1.recyclerview.DatabaseItem
import java.util.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        asyncTask()
        startMain()
    }

    private fun startMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    fun asyncTask(){
        val loadAsync = LoadDatabaseAsync(applicationContext, MainActivity.databaseItems)
        loadAsync.execute()
    }
    class LoadDatabaseAsync(private val context: Context, items: ArrayList<DatabaseItem?>) : AsyncTask<Any?, Any?, Any?>() {
        override fun doInBackground(objects: Array<Any?>): Any? {
            MainActivity.databaseItems = DatabaseHandler.getQuizItems(context)
            return MainActivity.databaseItems
        }

        override fun onPostExecute(o: Any?) {
            MainActivity.databaseDownloaded = true
        }

        init {
            items.clear()
        }
    }



}
