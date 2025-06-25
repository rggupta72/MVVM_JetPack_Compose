package com.example.composepoc.domain

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.example.composepoc.R
import com.example.composepoc.domain.model.DummyArray
import com.google.gson.Gson

object DataManager {
    val currentPage = mutableStateOf(Page.DASHBOARD)
    var data: Array<DummyArray> = emptyArray()
    val isDataLoad = mutableStateOf(false)
    fun loadAssetFromFile(context: Context) {
        val gson = Gson()
        val abc = context.resources.openRawResource(R.raw.dummy_array).bufferedReader()
            .use { it.readText() }
        data = gson.fromJson(abc, Array<DummyArray>::class.java)
        isDataLoad.value = true
    }

    fun switchPages() {
        if (currentPage.value == Page.DASHBOARD) {
            currentPage.value = Page.DUMMYARRAY
        } else {
            currentPage.value = Page.DASHBOARD
        }
    }

}

enum class Page {
    DASHBOARD,
    DUMMYARRAY
}