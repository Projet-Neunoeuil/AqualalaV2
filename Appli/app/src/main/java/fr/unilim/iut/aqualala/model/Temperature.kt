package fr.unilim.iut.aqualala.model

class Temperature (var valeur: Double, var tempsMesure: String, var maxTemperature: Double, var minTemperature: Double) {
    //afficher l'initialisation

    init {
        //C'est très rare qu'on utilise Log.d dans la méthode init, on utilise souvent println -----to Marie
        println("TEMPERATURE :")
        println("Température = $valeur")
        println("Temps = $tempsMesure")
        println("Maximum de température = $maxTemperature")
        println(" Minimum de température = $minTemperature")
    }

    fun estDansLaLimite():Int{
        when (valeur) {
            in  minTemperature..maxTemperature -> return 0
            in  0.00..minTemperature -> return 1
            else -> return 2
        }
    }

    //retourner la validité de la température
    fun commentaireSurLaValiditeTemperature(): String{
        when (estDansLaLimite()) {
            0  -> return "La température est idéale"
            1 -> return "La température est anormalement basse"
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
        return "Température mesurée à ${heureMinute[0]} : ${heureMinute[1]}"
    }
}