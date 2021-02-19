package fr.unilim.iut.aqualala.model

import fr.unilim.iut.aqualala.config.*

class Arrays {
    val listeTemperature : ArrayList<String> = ArrayList()
    val listeHeure : ArrayList<String> = ArrayList()
    val listeDelai : ArrayList<String> = ArrayList()
    val listePeriodeChangeWater: ArrayList<String> = ArrayList()
    init {
        for (i in PLUS_BASSE_TEMPERATURE..PLUS_HAUTE_TEMPERATURE) {
            listeTemperature.add(i.toString())
        }
        for (i in MINIMUM_RAFRACHIR_PAGE_TEMPERATURE..MAXMUM_RAFRACHIR_PAGE_TEMPERATURE) {
            listeDelai.add(i.toString())
        }
        for(i in MINIMUM_FREQUENCE_CHANGEMENT_EAU..MAXIMUM_FREQUENCE_CHANGEMENT_EAU){
            listePeriodeChangeWater.add(i.toString())
        }

        for(i in 0..9){
            listeHeure.add("0$i:00")
            listeHeure.add("0$i:30")
        }
        for(i in 10..23){
            listeHeure.add("$i:00")
            listeHeure.add("$i:30")
        }
        /*//Matin
        listeHeure.add("00:00"); listeHeure.add("00:30")
        listeHeure.add("01:00"); listeHeure.add("01:30")
        listeHeure.add("02:00"); listeHeure.add("02:30")
        listeHeure.add("03:00"); listeHeure.add("03:30")
        listeHeure.add("04:00"); listeHeure.add("04:30")
        listeHeure.add("05:00"); listeHeure.add("05:30")
        listeHeure.add("06:00"); listeHeure.add("06:30")
        listeHeure.add("07:00"); listeHeure.add("07:30")
        listeHeure.add("08:00"); listeHeure.add("08:30")
        listeHeure.add("09:00"); listeHeure.add("09:30")
        listeHeure.add("10:00"); listeHeure.add("10:30")
        listeHeure.add("11:00"); listeHeure.add("11:30")
        listeHeure.add("12:00"); listeHeure.add("12:30")
        //Après Midi
        listeHeure.add("13:00"); listeHeure.add("13:30")
        listeHeure.add("14:00"); listeHeure.add("14:30")
        listeHeure.add("15:00"); listeHeure.add("15:30")
        listeHeure.add("16:00"); listeHeure.add("16:30")
        listeHeure.add("17:00"); listeHeure.add("17:30")
        listeHeure.add("18:00"); listeHeure.add("18:30")
        listeHeure.add("19:00"); listeHeure.add("19:30")
        listeHeure.add("20:00"); listeHeure.add("20:30")
        listeHeure.add("21:00"); listeHeure.add("21:30")
        listeHeure.add("22:00"); listeHeure.add("22:30")
        listeHeure.add("23:00"); listeHeure.add("23:30")*/
    }
}