package com.example.rentgo

import java.io.Serializable

data class Car(
    var id:Int,
    var marque: String = "",
    var model :String,
    var prix: String = "",
    var dispo: Int,
    var lat:Double,
    var longitude:Double,
    var couleur:String,
    var puissance:String,
    var année:Int,
    var seats:Int,
    var classe:String,
    var gearbox:String,
    var description:String,
    var photo_list: String,
    var photo1:String,
    var photo2:String,
    var photo3:String,
): Serializable
