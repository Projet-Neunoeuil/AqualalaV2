package fr.unilim.iut.aqualala.model.sql.classes

import java.util.*

class Parametres (val minTemp : Double,
                  val maxTemp : Double,
                  val tempsBlanc : Date,
                  val tempsBleu : Date,
                  val waterLevel : Boolean,
                  val periodeGetTemp : Int,
                  val periodeChangeWater : Int
                  ) {
    // Si ya des opérations, les mettres ici sous forme de fonction
}
