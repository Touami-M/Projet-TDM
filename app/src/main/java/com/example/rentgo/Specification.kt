package com.example.rentgo

import java.io.Serializable

class Specification(
    var specifiationIcon: Int = 0,
    var specifiationName: String ="",
    var specificationValue: String = ""
) : Serializable {}