package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import fr.unilim.iut.aqualala.model.sql.classes.Temperature
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.Time
import java.util.*

class TemperatureManager (val connection: Connection) {
    fun obtenirDerniereTemperature(): Temperature {
        var ps : PreparedStatement = connection.prepareStatement("SELECT value, time FROM Temperature ORDER BY time DESC")
        var rs = ps.executeQuery()
        var valeur = 0.0
        var enregistrement = Date()
        if(rs.next()) {
            valeur = rs.getDouble("value")
            enregistrement = rs.getTimestamp("time")
            ps.close()
        }

        return Temperature(valeur, enregistrement)
    }
}