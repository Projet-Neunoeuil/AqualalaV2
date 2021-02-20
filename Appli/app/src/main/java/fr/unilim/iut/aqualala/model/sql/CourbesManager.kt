package fr.unilim.iut.aqualala.model.sql

import android.os.Handler
import android.os.Looper
import com.jjoe64.graphview.series.DataPoint
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.concurrent.Executors

class CourbesManager : ManagerAbstract() {

    fun obtenirCourbe(sql : String) : ArrayList<DataPoint> {
        var ps : PreparedStatement = connection.prepareStatement(sql)
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
    fun obtenirCourbeJournee(): ArrayList<DataPoint> {
        return obtenirCourbe("SELECT value FROM LastDay ORDER BY hour ASC")
    }
    fun obtenirCourbeSemaine(): ArrayList<DataPoint> {
        return obtenirCourbe("SELECT value FROM LastWeek ORDER BY day ASC")
    }
    fun obtenirCourbeMois(): ArrayList<DataPoint> {
        return obtenirCourbe("SELECT value FROM LastMonth ORDER BY day ASC")
    }

    fun obtenirCourbeAnnee(): ArrayList<DataPoint> {
        return obtenirCourbe("SELECT value FROM LastYear ORDER BY month ASC")
    }
}