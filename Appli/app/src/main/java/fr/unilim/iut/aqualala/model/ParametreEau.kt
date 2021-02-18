package fr.unilim.iut.aqualala.model

class ParametreEau constructor(var dernierDateChangementEau:String, var frequenceChangementEau: Int ) {
    init {
        println("Paramètre changement d'eau")
        println("Le dernier changement d'eau: $dernierDateChangementEau")
        println("Le fréquence de changement d'eau: $frequenceChangementEau")
    }

    fun recupererMoisJour() : String{
        //aaaa-mm-jj
        val MoisJour=dernierDateChangementEau.split("-")
        return "${MoisJour[1]}:${MoisJour[2]}"
    }

    fun prochainChangementEau():String{
        val MoisJour=dernierDateChangementEau.split("-")
        var annee=MoisJour[0].toInt()
        var jour=MoisJour[2].toInt()
        var mois=MoisJour[1].toInt()
        val prochainJour = jour+frequenceChangementEau
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
                    jour-=31
                }
            }
            4,6,9,11->{
                if (prochainJour > 30 ){
                    mois++
                    jour-=30
                }
            }
            else->{
                if (prochainJour > nombreJourFevrier ){
                    mois++
                    jour-=nombreJourFevrier
                }
            }
        }
        return "$jour/$mois/$annee"
    }

}