package fr.unilim.iut.aqualala.model

import android.util.Log
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.R
import fr.unilim.iut.aqualala.config.MIN_TEMP

class Temperature (var valeur: Double, var tempsMesure: String, var estDansLaLimite: Boolean) {
    //afficher l'initialisation
    init {
        Log.d("TEMPERATURE",
            "Température = $valeur\n" +
                "Temps = $tempsMesure\n" +
                "Validité de température = $estDansLaLimite")
    }

    //retourner la validité de la température
    fun commentaireSurLaValiditeTemperature(): String{
        if (!estDansLaLimite) {
            if (valeur < MIN_TEMP) return "La température est anormalement basse"
            else return "La température est anormalement élevée"
        }else return "La température est idéale"
    }

    fun recupererHeuresEtMinutes() : String{
        //aaaa-mm-jj hh:mm:ss
        val dateTemps=tempsMesure.split(" ")
        val temps=dateTemps[1]
        //hh:mm:ss
        val heureMinute=temps.split(":")
        return "Température mesurée à ${heureMinute[0]} : ${heureMinute[1]}"
    }

    /**
     * throws the color dependant of the temperature range
     *
     * @return a correct colorcode to set text color
     */
    fun couleurSelonValidite(): Int {
        if (estDansLaLimite) return R.color.vert
        return R.color.rose_pastel;
    }
}