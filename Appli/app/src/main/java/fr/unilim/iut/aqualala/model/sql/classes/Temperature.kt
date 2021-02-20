package fr.unilim.iut.aqualala.model.sql.classes


import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.sql.ParametreManager
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
            in PLUS_BASSE_TEMPERATURE.toDouble()..parametres.minTemp -> return BASSE
            in parametres.maxTemp..MAXMUM_RAFRACHIR_PAGE_TEMPERATURE.toDouble() -> return HAUTE
            else -> return IDEALE
        }
    }

     fun commentaireSurLaValiditeTemperature(parametres: Parametres): String {
        when(obtenirChaleurEau(parametres)) {
            BASSE -> return "La température est annormalement basse"
            HAUTE -> return "La temperature est annormalement Haute"
            IDEALE -> return "La temperature est idéale"
        }
        return "Erreur"
    }

    fun recupererHeureMinute() : String{
        return SimpleDateFormat("HH:mm").format(date)
    }
}