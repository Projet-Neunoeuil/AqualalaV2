package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import fr.unilim.iut.aqualala.model.sql.classes.Temperature
import java.sql.Connection
import java.sql.PreparedStatement

class TemperatureManager (val connection: Connection) {
    fun obtenirDerniereTemperature(): Temperature {
        var ps : PreparedStatement = connection.prepareStatement("SELECT value, time FROM Temperature ORDER BY time DESC")

        var rs = ps.executeQuery()
        val valeur = rs.getDouble("value")
        val enregistrement = rs.getTimestamp("time")
        return Temperature(valeur, enregistrement)
    }
}