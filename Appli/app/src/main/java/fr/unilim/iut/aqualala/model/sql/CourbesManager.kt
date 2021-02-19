package fr.unilim.iut.aqualala.model.sql

import com.jjoe64.graphview.series.DataPoint
import java.sql.Connection
import java.sql.PreparedStatement

class CourbesManager (val connection : Connection) {
    fun obtenirCourbeJournee(): ArrayList<DataPoint> {
        var ps : PreparedStatement = connection.prepareStatement("SELECT value FROM LastDay ORDER BY hour ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var listeValeurs : ArrayList<DataPoint> = ArrayList()
        while (rs.next()) {
            var valeur = rs.getDouble("value")
            listeValeurs.add(DataPoint(x, valeur))
            x += 1
        }
        ps.close()
        return listeValeurs
    }
    fun obtenirCourbeSemaine(): ArrayList<DataPoint> {
        var ps : PreparedStatement = connection.prepareStatement("SELECT value FROM LastWeek ORDER BY day ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var listeValeurs : ArrayList<DataPoint> = ArrayList()
        while (rs.next()) {
            var valeur = rs.getDouble("value")
            listeValeurs.add(DataPoint(x, valeur))
            x += 1
        }
        ps.close()
        return listeValeurs
    }
    fun obtenirCourbeMois(): ArrayList<DataPoint> {
        var ps : PreparedStatement = connection.prepareStatement("SELECT value FROM LastMonth ORDER BY day ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var listeValeurs : ArrayList<DataPoint> = ArrayList()
        while (rs.next()) {
            var valeur = rs.getDouble("value")
            listeValeurs.add(DataPoint(x, valeur))
            x += 1
        }
        ps.close()
        return listeValeurs
    }

    fun obtenirCourbeAnnee(): ArrayList<DataPoint> {
        var ps : PreparedStatement = connection.prepareStatement("SELECT value FROM LastYear ORDER BY month ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var listeValeurs : ArrayList<DataPoint> = ArrayList()
        while (rs.next()) {
            var valeur = rs.getDouble("value")
            listeValeurs.add(DataPoint(x, valeur))
            x += 1
        }
        ps.close()
        return listeValeurs
    }
}