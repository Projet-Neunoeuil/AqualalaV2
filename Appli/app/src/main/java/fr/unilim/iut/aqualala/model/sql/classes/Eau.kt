package fr.unilim.iut.aqualala.model.sql.classes

import java.text.SimpleDateFormat
import java.util.*

class Eau (var derniereDateChangementEau:Date) {
    init {
        println("Dernière date de changement d'eau: $derniereDateChangementEau")
    }

    fun recupererJourMoisAnnee() : String{
        return SimpleDateFormat("dd/MM/yyyy").format(derniereDateChangementEau)
    }

    fun prochainChangementEau(frequenceChangementEau:Int) : String{
        var calendar = Calendar.getInstance() // Création d'u objet calendrier
        calendar.time = derniereDateChangementEau // attribution de la bonne date au calendrier
        calendar.add(Calendar.DATE, frequenceChangementEau) // Ajout de frequenceChangementEau en jours
        var newDate = calendar.time // retour à une date
        return SimpleDateFormat("dd/MM/yyyy").format(newDate) // Date revoyée formatée
    }
}