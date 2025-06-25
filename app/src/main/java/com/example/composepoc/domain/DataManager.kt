package com.example.composepoc.domain

import android.content.Context
import com.example.composepoc.R
import com.example.composepoc.domain.model.DummyArray
import com.google.gson.Gson

object DataManager {

    var data: Array<DummyArray> = emptyArray()

    fun loadAssetFromFile(context: Context) {
        val gson = Gson()
        val abc = context.resources.openRawResource(R.raw.dummy_array).bufferedReader()
            .use { it.readText() }
        data = gson.fromJson(abc, Array<DummyArray>::class.java)
    }
}