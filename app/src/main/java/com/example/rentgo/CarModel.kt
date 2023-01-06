package com.example.rentgo

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class CarModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    val cars: List<Car>

    fun loadData():List<Car> {
        val marquesTab = context.resources.getStringArray(R.array.marque)
        val pricesTab = context.resources.getStringArray(R.array.price)
        val imagesTab = arrayOf(R.drawable.car_image,R.drawable.car_image,R.drawable.car_image,R.drawable.car_image,R.drawable.car_image)
        val detailsTab = context.resources.getStringArray(R.array.detailsCars)
        val list = mutableListOf<Car>()
        for (i in imagesTab.indices) {
            list.add(Car(
                carImage = imagesTab[i],
                marque = marquesTab[i],
                price = pricesTab[i],
                availability = false,
                detail = detailsTab[i]
            ))
        }
        return  list
    }

    init {
        this.cars = loadData()
    }
}

class Resget()