package fr.unilim.iut.aqualala.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.R
import java.sql.SQLException
import java.util.concurrent.Executors

class AsyncTemperature {
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    val requeteTempratureTemps = "SELECT value, time FROM Temperature ORDER BY time DESC ;"
    val requeteMaxMinTemprature = "SELECT  minTemp, maxTemp, period FROM Parameters ;"
    var erreurDesDonnees: String = ""
    val handler = Handler(Looper.getMainLooper())
    var temperature = Temperature(0.00,"00 00:00:00",0.00,0.00,1)

    fun execute() {
        executor.execute {
            try {
                var resultatRecupTempratureTemps = bd.recupererDonnees(requeteTempratureTemps)
                var resultatRecupMaxMinTemprature = bd.recupererDonnees(requeteMaxMinTemprature)

                if (resultatRecupTempratureTemps.next()) {
                    temperature.valeur = resultatRecupTempratureTemps.getDouble("value")
                    temperature.tempsMesure = resultatRecupTempratureTemps.getString("time")
                }

                if (resultatRecupMaxMinTemprature.next()) {
                    temperature.maxTemperature = resultatRecupMaxMinTemprature.getDouble("maxTemp")
                    temperature.minTemperature = resultatRecupMaxMinTemprature.getDouble("minTemp")
                    temperature.periodeEnMinute = resultatRecupMaxMinTemprature.getInt("period")
                }
            } catch (e: Exception) {
                erreurDesDonnees = e.toString()
            } catch (e: SQLException) {
                erreurDesDonnees = e.toString()
            } catch (e: ClassNotFoundException) {
                erreurDesDonnees = e.toString()
            }
        }
    }
}