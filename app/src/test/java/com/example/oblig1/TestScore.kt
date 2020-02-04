package com.example.oblig1

import android.content.Context
import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.example.oblig1.recyclerview.DatabaseItem
import org.junit.Before
import org.junit.Test

class TestScore {

    lateinit var instrumentationContext: Context

    @Before
    fun setup() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun answerCorrectly(){
        val i1 = BitmapFactory.decodeResource(instrumentationContext.resources,
                R.drawable.ole)
        val item = DatabaseItem("jostein", i1, "_jostein")


    }


}