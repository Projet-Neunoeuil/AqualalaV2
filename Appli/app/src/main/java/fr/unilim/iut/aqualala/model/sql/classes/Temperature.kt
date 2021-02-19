package fr.unilim.iut.aqualala.model.sql.classes

import fr.unilim.iut.aqualala.config.*
import java.text.Format
import java.text.SimpleDateFormat
import java.util.*

class Temperature (val valeur : Double, val date : Date) {
    init{
        println("Température")
        println("Valeur: $valeur")
        println("Date: $date")
    }

    fun obtenirChaleurEau(parametres: Parametres): Int {
        when(valeur){
            in 10.0..parametres.minTemp -> return BASSE
            in parametres.maxTemp..50.0 -> return HAUTE
            else -> return IDEALE
        }
    }
    fun renvoyerCommentaire(estValideOuPas:Int):String {
        when(estValideOuPas){
            IDEALE->return "La température est idéale"
            BASSE->return "La température est anormalement basse"
            else->return "La température est anormalement élevé"
        }
    }
    fun recupererHeureMinute() : String{
        return SimpleDateFormat("HH:mm").format(date)
    }
}