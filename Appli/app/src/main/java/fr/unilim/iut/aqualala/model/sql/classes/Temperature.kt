package fr.unilim.iut.aqualala.model.sql.classes


import fr.unilim.iut.aqualala.config.*
import java.text.SimpleDateFormat
import java.util.*

class Temperature (val valeur : Double, val date : Date) {
    init{
        println("Température")
        println("Valeur: $valeur")
        println("Date: $date")
    }

    fun obtenirValiditeEau(parametres: Parametres): Int {
        if (valeur <= parametres.minTemp) return BASSE
        else if (valeur >= parametres.maxTemp) return HAUTE
        else return IDEALE
    }

     fun commentaireSurLaValiditeTemperature(parametres: Parametres): String {
        when(obtenirValiditeEau(parametres)) {
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