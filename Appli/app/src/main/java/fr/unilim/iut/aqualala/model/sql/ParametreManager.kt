package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Time
import java.util.*

class ParametreManager(val connection: Connection) {
    fun obtenirParametres(): Parametres {
        var ps : PreparedStatement = connection.prepareStatement("SELECT minTemp, maxTemp, whiteTime, blueTime, waterLevel, periodGetTemp, periodChangeWater FROM Parameters")
        var rs = ps.executeQuery()
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
        var ps : PreparedStatement = connection.prepareStatement("UPDATE Parameters SET minTemp=?, maxTemp=?, periodGetTemp=?")
        ps.setDouble(1,tempMin)
        ps.setDouble(2, tempMax)
        ps.setInt(3,periode)

        var reussi = ps.execute()
        ps.close()
        return reussi
    }

    fun enregistrerParametresEclairage(heureBlanc : String, heureBleu : String): Boolean {
        var ps : PreparedStatement = connection.prepareStatement("UPDATE Parameters SET whiteTime=?, blueTime=?")
        ps.setTime(1, Time.valueOf(heureBlanc))
        ps.setTime(2, Time.valueOf(heureBleu))

        var reussi = ps.execute()
        ps.close()
        return reussi
    }

    fun enregistrerParametresEau(periode: Int): Boolean {
        var ps : PreparedStatement = connection.prepareStatement("UPDATE Parameters SET periodGetChangeWater=?")
        ps.setInt(1, periode)

        var reussi = ps.execute()
        ps.close()
        return reussi
    }

}
