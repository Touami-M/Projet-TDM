package com.example.rentgo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class RentModel : ViewModel() {

    var rents = listOf<Rent>()
}