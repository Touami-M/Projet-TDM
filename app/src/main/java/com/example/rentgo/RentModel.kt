package com.example.rentgo

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class RentModel (application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext

    val rents: List<Rent>

    fun loadData():List<Rent> {
        val rentsMarquesTab = context.resources.getStringArray(R.array.marque)
        val imagesTab = arrayOf(R.drawable.car_image,R.drawable.car_image,R.drawable.car_image,R.drawable.car_image,R.drawable.car_image,R.drawable.car_image)
        val list = mutableListOf<Rent>()
        for (i in imagesTab.indices) {
            list.add(Rent(
                photo_list = imagesTab[i],
                marque = rentsMarquesTab[i],
                cost = 400.0,
                dateres = "1/1/2023",
                dateret = "2/1/2023",
                model = "",
                lat = 1.2,
                long = 2.3,
                photo1 = imagesTab[i],
                photo2 = imagesTab[i],
                photo3 = imagesTab[i],
                code_pin = 1234
            ))
        }
        return  list
    }

    init {
        this.rents = loadData()
    }
}