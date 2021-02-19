package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Time
import java.util.*

class ParametreManager(val connection: Connection) {
    fun obtenirParametres(): Parametres {
        val ps : PreparedStatement = connection.prepareStatement("SELECT minTemp, maxTemp, whiteTime, blueTime, waterLevel, periodGetTemp, periodChangeWater FROM Parameters")
        val rs = ps.executeQuery()
        val tempMin = rs.getDouble("minTemp")
        val tempMax = rs.getDouble("maxTemp")
        val heureBlanc = rs.getTimestamp("whiteTime")
        val heureBleu = rs.getTimestamp("blueTime")
        val niveauEau = rs.getBoolean("waterLevel")
        val intervalTemp = rs.getInt("periodGetTemp")
        val intervalChangementEau = rs.getInt("periodChangeWater")
        ps.close()
        return Parametres(tempMin, tempMax, heureBlanc, heureBleu, niveauEau, intervalTemp, intervalChangementEau)
    }

    fun enregistrerParametresTemperature(tempMin : Double, tempMax : Double, periode : Int): Boolean {
        val ps : PreparedStatement = connection.prepareStatement("UPDATE Parameters SET minTemp=?, maxTemp=?, periodGetTemp=?")
        ps.setDouble(1,tempMin)
        ps.setDouble(2, tempMax)
        ps.setInt(3,periode)

        val reussi = ps.execute()
        ps.close()
        return reussi
    }

    fun enregistrerParametresEclairage(heureBlanc : String, heureBleu : String): Boolean {
        val ps : PreparedStatement = connection.prepareStatement("UPDATE Parameters SET whiteTime=?, blueTime=?")
        ps.setTime(1, Time.valueOf(heureBlanc))
        ps.setTime(2, Time.valueOf(heureBleu))

        val reussi = ps.execute()
        ps.close()
        return reussi
    }

    fun enregistrerParametresEau(periode: Int): Boolean {
        val ps : PreparedStatement = connection.prepareStatement("UPDATE Parameters SET periodGetChangeWater=?")
        ps.setInt(1, periode)

        val reussi = ps.execute()
        ps.close()
        return reussi
    }

    fun obtenirParametresTemperature():Array<Double?>{
        var ps: PreparedStatement =connection.prepareStatement("SELECT minTemp, maxTemp,periodGetTemp FROM Parametres;")

        val rs=ps.executeQuery()
        val minMaxPeriodeTemperature: Array<Double?> = arrayOfNulls<Double>(3)
        minMaxPeriodeTemperature[0]=rs.getDouble("minTemp")
        minMaxPeriodeTemperature[1]=rs.getDouble("maxTemp")
        minMaxPeriodeTemperature[1]=rs.getDouble("periodGetTemp")
        return minMaxPeriodeTemperature
    }

    fun obtenirParametresLumiere():Array<Date?>{
        var ps: PreparedStatement =connection.prepareStatement("SELECT whiteTime, blueTime FROM Parametres;")

        val rs=ps.executeQuery()
        val blancNoirTemps: Array<Date?> = arrayOfNulls<Date>(2)
        blancNoirTemps[0]=rs.getTimestamp("minTemp")
        blancNoirTemps[1]=rs.getTimestamp("maxTemp")
        return blancNoirTemps
    }

    fun obtenirParametresEau():Int{
        var ps: PreparedStatement = connection.prepareStatement("SELECT frequenceChangeWater FROM Parameters ;")

        val rs=ps.executeQuery()
        val frequenceChangeWater=rs.getInt("frequenceChangeWater")
        ps.close()
        return frequenceChangeWater
    }

}
