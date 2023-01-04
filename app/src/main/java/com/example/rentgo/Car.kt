package com.example.rentgo

import java.io.Serializable

data class Car(
    var carImage: Int = 0,
    var marque: String = "",
    var price: String = "",
    var availability: Boolean,
    /*var latitude:Double,
    var longitude:Double,*/
): Serializable {}
