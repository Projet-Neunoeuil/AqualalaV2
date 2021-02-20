package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import fr.unilim.iut.aqualala.model.sql.classes.Temperature
import java.sql.*

class TemperatureManager : ManagerAbstract() {
    var valeur: Double = 0.00
    var enregistrement: Timestamp = Timestamp.valueOf("0000-00-00 00:00:00.0")

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