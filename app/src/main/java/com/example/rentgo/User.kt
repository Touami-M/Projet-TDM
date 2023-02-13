package com.example.rentgo

import java.math.BigInteger


data class User(
    var telephone:Int,
    var password:String,
    var name:String,
    var credit_card_num:BigInteger,
    var driver_license_doc:String,
)
