package fr.unilim.iut.aqualala.model

import android.widget.EditText

class ParametreTemperature constructor(var minTemp: Double, var maxTemp: Double, var periode: Int) {
    init {
        println("Prametres de Température")
        println("minTemp = $minTemp °C" )
        println("maxTemp = $maxTemp °C")
        println("periode = $periode minute(s)")
        println("Temperature = $minTemp")
    }
}