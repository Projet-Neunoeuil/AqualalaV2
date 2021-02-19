package fr.unilim.iut.aqualala.model.sql.classes

import java.text.SimpleDateFormat
import java.util.*
const val IDEALE = 0
const val BASSE = 1
const val HAUTE = 2
class Temperature (val valeur : Double, val date : Date) {

    fun obtenirChaleurEau(parametres: Parametres): Int {
        if(valeur < parametres.minTemp) return BASSE
        if(valeur > parametres.maxTemp) return HAUTE
        return IDEALE
    }
    fun recupererHeureMinute() : String{
        return SimpleDateFormat("HH:mm").format(date)
    }
}