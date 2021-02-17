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

class Async constructor(var temperature: Temperature, var msgErreurView : TextView, var valeurView : TextView, var tempsView : TextView, var commentaireView : TextView, var context: Context){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requeteTempratureTemps = "SELECT value, time FROM Temperature ORDER BY time DESC ;"
    var requeteMaxMinTemprature = "SELECT  minTemp, maxTemp FROM Parameters ;"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

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
                }
            } catch (e: Exception) {
                erreurDesDonnees = e.toString()
            } catch (e: SQLException) {
                erreurDesDonnees = e.toString()
            } catch (e: ClassNotFoundException) {
                erreurDesDonnees = e.toString()
            }
            handler.post {
                //afficher message erreur si erreur de récupération de données
                if (erreurDesDonnees !== "") {
                    msgErreurView!!.text = erreurDesDonnees
                    Log.d("ERREUR", erreurDesDonnees)
                }
                //afficher la température sinon
                else {
                    lierViewAvecTemperature(temperature)
                }
            }
        }
    }

    private fun lierViewAvecTemperature(temperature: Temperature) {
        valeurView!!.text = temperature.valeur.toString() + "°C"
        tempsView!!.text = temperature.recupererHeuresEtMinutes()
        commentaireView!!.text = temperature.commentaireSurLaValiditeTemperature()
        changerCouleurTexte(temperature)
    }

    private fun changerCouleurTexte(temperature: Temperature) {
        if(temperature.estDansLaLimite()==0) {
            commentaireView!!.setTextColor(ContextCompat.getColor(context, R.color.vert))
            valeurView!!.setTextColor(ContextCompat.getColor(context, R.color.vert))
        } else {
            commentaireView!!.setTextColor(ContextCompat.getColor(context, R.color.rose_pastel))
            valeurView!!.setTextColor(ContextCompat.getColor(context, R.color.rose_pastel))
        }
    }
}