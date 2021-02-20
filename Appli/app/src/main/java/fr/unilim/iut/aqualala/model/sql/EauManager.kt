package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Eau
import java.sql.Connection
import java.sql.Date
import java.sql.PreparedStatement

class EauManager: ManagerAbstract() {
    fun obtenirDernierDateChangementEau(): Eau {
        val ps :PreparedStatement = connection.prepareStatement("SELECT lastChange FROM Water ORDER BY lastChange DESC;")

        val rs = ps.executeQuery()
        val derniereDateChangementEau=rs.getTimestamp("lastChange")
        return Eau(derniereDateChangementEau)
    }
}