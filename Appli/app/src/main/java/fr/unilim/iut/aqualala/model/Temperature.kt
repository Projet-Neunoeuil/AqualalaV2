package fr.unilim.iut.aqualala.model

const val IDEALE = 0
const val BASSE = 1
const val HAUTE = 2

class Temperature constructor(var valeur: Double, var tempsMesure: String, var maxTemperature: Double, var minTemperature: Double, var periodeEnMinute: Int) {
    //afficher l'initialisation

    init {
        println("TEMPERATURE :")
        println("Température = $valeur")
        println("Temps = $tempsMesure")
        println("Maximum de température = $maxTemperature")
        println(" Minimum de température = $minTemperature")
        println(" Période de raffraichir de température = $periodeEnMinute")
    }

    fun estDansLaLimite():Int{
        when (valeur) {
            in  minTemperature..maxTemperature -> return IDEALE
            in  0.00..minTemperature -> return BASSE
            else -> return HAUTE
        }
    }

    //retourner la validité de la température
    fun commentaireSurLaValiditeTemperature(): String{
        when (estDansLaLimite()) {
            IDEALE  -> return "La température est idéale"
            BASSE -> return "La température est anormalement basse"
            else -> return "La température est anormalement élevée"
        }
    }

    //Changer le temps (aaaa-mm-jj hh:mm:ss) en (mm:ss)
    fun recupererHeuresEtMinutes() : String{
        //aaaa-mm-jj hh:mm:ss
        val dateTemps=tempsMesure.split(" ")
        val temps=dateTemps[1]
        //hh:mm:ss
        val heureMinute=temps.split(":")
        return "Température mesurée à ${heureMinute[0]}:${heureMinute[1]}"
    }
}