package com.example.rentgo

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class BrandModel (application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    val brands: List<Brand>

    fun loadData():List<Brand> {
        val brandsNamesTab = context.resources.getStringArray(R.array.brandsNames)
        val imagesTab = arrayOf(R.drawable.mercedes_icon,R.drawable.audi_icon,R.drawable.bmw_icon,R.drawable.renault_icon,R.drawable.nissan_icon)
        val list = mutableListOf<Brand>()
        for (i in imagesTab.indices) {
            list.add(Brand(
                brandImage = imagesTab[i],
                brandName = brandsNamesTab[i]
            ))
        }
        return  list
    }

    init {
        this.brands = loadData()
    }
}
