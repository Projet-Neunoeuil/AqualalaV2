package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.model.sql.classes.Eau
import java.text.SimpleDateFormat
import java.util.Date
import java.sql.PreparedStatement

class EauManager: ManagerAbstract() {
    var derniereDateChangementEau = Date()

    fun obtenirDernierDateChangementEau(): Eau {
        val ps :PreparedStatement = connection.prepareStatement("SELECT lastChange FROM Water ORDER BY lastChange DESC;")
        val rs = ps.executeQuery()

        if (rs.next()) {
            derniereDateChangementEau = rs.getTimestamp("lastChange")
        }
        return Eau(derniereDateChangementEau)
    }
}