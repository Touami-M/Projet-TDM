package com.example.rentgo

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel

class SpecificationModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    val specifications: List<Specification>

    fun loadData(): List<Specification> {
        val specficatiosnNamesTab = context.resources.getStringArray(R.array.specififcationsNames)
        val specficatiosnValuesTab = context.resources.getStringArray(R.array.specififcationsValues)
        val imagesTab = arrayOf(R.drawable.speed_icon,R.drawable.seat_icon,R.drawable.color_icon,R.drawable.motor_icon)
        val list = mutableListOf<Specification>()
        for (i in imagesTab.indices) {
            list.add(Specification(
                specifiationIcon = imagesTab[i],
                specifiationName = specficatiosnNamesTab[i],
                specificationValue = specficatiosnValuesTab[i],
            )
            )
        }
        return  list
    }

    init {
        this.specifications = loadData()
    }
}

class Resget1()