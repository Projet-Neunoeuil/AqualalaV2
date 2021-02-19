package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import java.sql.Connection
import java.sql.PreparedStatement

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
        return Parametres(tempMin, tempMax, heureBlanc, heureBleu, niveauEau, intervalTemp, intervalChangementEau)
    }

}
