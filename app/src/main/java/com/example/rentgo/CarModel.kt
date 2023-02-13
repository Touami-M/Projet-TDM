package com.example.rentgo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class CarModel: ViewModel() {

    var cars = listOf<Car>()
}