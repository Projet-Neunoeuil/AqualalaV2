package fr.unilim.iut.aqualala.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.Connexion
import fr.unilim.iut.aqualala.R
import java.sql.SQLException
import java.util.concurrent.Executors

class Async constructor(var temperature: Temperature, var msgErreurView : TextView, var valeurView : TextView, var tempsView : TextView, var commentaireView : TextView, var context: Context){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requete = "SELECT value, time, inRange FROM Temperature ORDER BY time DESC"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            try {
                var resultatRecup = bd.recupererDonnees(requete)
                if (resultatRecup.next()) {
                    temperature.valeur = resultatRecup.getDouble("value")
                    temperature.tempsMesure = resultatRecup.getString("time")
                    temperature.estDansLaLimite = resultatRecup.getBoolean("inRange")
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

    private fun changerCouleurTexte(temp: Temperature) {
        if(temp.estDansLaLimite) {
            commentaireView!!.setTextColor(ContextCompat.getColor(context, R.color.vert))
            valeurView!!.setTextColor(ContextCompat.getColor(context, R.color.vert))
        } else {
            commentaireView!!.setTextColor(ContextCompat.getColor(context, R.color.rose_pastel))
            valeurView!!.setTextColor(ContextCompat.getColor(context, R.color.rose_pastel))
        }
    }
}