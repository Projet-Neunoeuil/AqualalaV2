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
    }
}