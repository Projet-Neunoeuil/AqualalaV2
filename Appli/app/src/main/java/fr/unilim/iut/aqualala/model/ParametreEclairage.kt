package fr.unilim.iut.aqualala.model

class ParametreEclairage constructor(var whiteTime: String, var blueTime : String) {
    init {
        println("Prametres de Eclairage")
        println("whiteTime = $whiteTime" )
        println("blueTime = $blueTime")
    }

    fun recupererHeuresEtMinutes(temps: String) : String{
        //hh:mm:ss
        val heureMinute=temps.split(":")
        return "${heureMinute[0]}:${heureMinute[1]}"
    }
}