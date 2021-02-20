package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import fr.unilim.iut.aqualala.model.sql.classes.Temperature
import java.sql.*
import java.util.Date

class TemperatureManager : ManagerAbstract() {
    var valeur: Double = 0.00
    var enregistrement = Date()

    fun obtenirDerniereTemperature(): Temperature {
        val ps : PreparedStatement = connection.prepareStatement("SELECT value, time FROM Temperature ORDER BY time DESC")
        var rs : ResultSet

        try {
            rs = ps.executeQuery()
            if(rs.next()) {
                valeur = rs.getDouble("value")
                enregistrement = rs.getTimestamp("time")
            }
            rs.close()
        } catch (e: Exception) {
            println(e.toString())
        } catch (e: SQLException) {
            println(e.toString())
        } catch (e: ClassNotFoundException) {
            println(e.toString())
        }
        return Temperature(valeur, enregistrement)
    }
}