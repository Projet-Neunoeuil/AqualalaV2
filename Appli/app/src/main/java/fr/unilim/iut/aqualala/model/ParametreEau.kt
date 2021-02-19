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
        return "${MoisJour[1]}-${MoisJour[2]}"
    }


}