package fr.unilim.iut.aqualala.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.R
import java.sql.SQLException
import java.util.concurrent.Executors

class AsyncParametresEclairageControlleurRecupererDonnees constructor(var parametreEclairage: ParametreEclairage, var whiteTime: Spinner, var blueTime : Spinner, var errParamTemp: TextView, var context: Context){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requeteParametre = "SELECT whiteTime, blueTime FROM Parameters ;"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            try {
                var resultatRecupParametre= bd.recupererDonnees(requeteParametre)

                if (resultatRecupParametre.next()) {
                    parametreEclairage.whiteTime= resultatRecupParametre.getString("whiteTime")
                    parametreEclairage.blueTime= resultatRecupParametre.getString("blueTime")
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
                    errParamTemp!!.text = erreurDesDonnees
                    Log.d("ERREUR", erreurDesDonnees)
                }
                //afficher la température sinon
                else {
                    lierViewAvecParametresTemperature(parametreEclairage)
                }
            }
        }
    }

    private fun lierViewAvecParametresTemperature(parametreEclairage: ParametreEclairage) {
        whiteTime.setSelection(Arrays().listeHeure.indexOf(parametreEclairage.recupererHeuresEtMinutes(parametreEclairage.whiteTime)))
        blueTime.setSelection(Arrays().listeHeure.indexOf(parametreEclairage.recupererHeuresEtMinutes(parametreEclairage.blueTime)))
    }
}