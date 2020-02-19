package com.example.oblig1

import android.content.Context
import android.os.AsyncTask
import com.example.oblig1.recyclerview.DatabaseItem
import java.util.*

class LoadDatabaseToMemory {

    class LoadDatabaseAsync internal constructor(private val context: Context, private val items: ArrayList<DatabaseItem>, private val callback: CallbackInterface) : AsyncTask<Any?, Any?, Any?>() {
        override fun doInBackground(objects: Array<Any?>): Any? {
            DatabaseHandler.getQuizItems(context, items)
            return null
        }

        override fun onPostExecute(o: Any?) {
            MainActivity.databaseDownloaded = true
            callback.databaseDownloaded()
        }

        init {
            items.clear()
        }
    }
}