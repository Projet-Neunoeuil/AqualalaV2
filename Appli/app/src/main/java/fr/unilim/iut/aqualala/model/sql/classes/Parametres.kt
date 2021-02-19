package fr.unilim.iut.aqualala.model.sql.classes

import java.text.SimpleDateFormat
import java.util.*

class Parametres (val minTemp : Double, val maxTemp : Double, val tempsBlanc : Date, val tempsBleu : Date, val waterLevel : Boolean, val periodeGetTemp : Int, val periodeChangeWater : Int) {
    init{
        println("Parametres de température")
        println("minTemp: $minTemp")
        println("maxTemp: $maxTemp")
        println("periodeGetTemp: $periodeGetTemp")
        println("Parametres d'éclairage")
        println("tempsBlanc: $tempsBlanc")
        println("tempsBleu: $tempsBleu")
        println("Parametres d'eau")
        println("waterLevel: $waterLevel")
        println("periodeChangeWater: $periodeChangeWater")
    }

    fun recupererHeureMinute(date:Date) : String{
        return SimpleDateFormat("HH:mm").format(date)
    }
}
