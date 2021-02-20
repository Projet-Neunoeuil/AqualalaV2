package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import java.sql.*
import java.util.*
import java.util.Date

class ParametreManager: ManagerAbstract(){
    var tempMin = 0.00
    var tempMax = 0.00
    var heureBlanc = Timestamp.valueOf("0000-00-00 00:00:00")
    var heureBleu = Timestamp.valueOf("0000-00-00 00:00:00")
    var niveauEau = false
    var intervalTemp = 0
    var intervalChangementEau = 0

    fun obtenirParametres(): Parametres {
        val ps : PreparedStatement = connection.prepareStatement("SELECT minTemp, maxTemp, whiteTime, blueTime, waterLevel, periodGetTemp, periodChangeWater FROM Parameters")
        var rs : ResultSet

        try {
           rs = ps.executeQuery()
            if (rs.next()) {
                tempMin = rs.getDouble("minTemp")
                tempMax = rs.getDouble("maxTemp")
                heureBlanc = rs.getTimestamp("whiteTime")
                heureBleu = rs.getTimestamp("blueTime")
                niveauEau = rs.getBoolean("waterLevel")
                intervalTemp = rs.getInt("periodGetTemp")
                intervalChangementEau = rs.getInt("periodChangeWater")
            }
            rs.close()
        } catch (e: Exception) {
            println(e.toString())
        } catch (e: SQLException) {
            println(e.toString())
        } catch (e: ClassNotFoundException) {
            println(e.toString())
        }
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
