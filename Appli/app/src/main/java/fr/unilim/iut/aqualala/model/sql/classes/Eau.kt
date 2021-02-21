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
        var jour=SimpleDateFormat("dd").format(derniereDateChangementEau).toInt()
        var mois=SimpleDateFormat("MM").format(derniereDateChangementEau).toInt()
        var annee=SimpleDateFormat("yyyy").format(derniereDateChangementEau).toInt()
        var prochainJour = jour+frequenceChangementEau
        var nombreJourFevrier=28
        if (annee%4==0) nombreJourFevrier=29

        when (mois){
            1,3,5,7,8,10,12 -> {  //Les mois avec 3 jours
                if (prochainJour > 31 ){
                    mois++
                    if (mois>12){
                        mois=1
                        annee++
                    }
                    prochainJour-=31
                }
            }
            4,6,9,11->{
                if (prochainJour > 30 ){
                    mois++
                    prochainJour-=30
                }
            }
            else->{
                if (prochainJour > nombreJourFevrier ){
                    mois++
                    prochainJour-=nombreJourFevrier
                }
            }
        }
        return "$prochainJour/$mois/$annee"
    }
}