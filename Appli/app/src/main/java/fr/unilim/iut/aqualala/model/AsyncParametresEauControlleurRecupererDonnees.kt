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

class AsyncParametresEauControlleurRecupererDonnees constructor(var parametreEau: ParametreEau, var dernierDataChangementEau: Spinner, var frequenceChangementEau : Spinner, var errParamTemp: TextView, var context: Context){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requeteEau = "SELECT lastChange FROM Water ORDER BY lastChange DESC;"
    var requeteParametreEau = "SELECT frequenceChangeWater FROM Parameters ;"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            try {
                var resultatRecupWater= bd.recupererDonnees(requeteEau)
                var resultatRecupParametre= bd.recupererDonnees(requeteParametreEau)

                if (resultatRecupWater.next()) parametreEau.dernierDateChangementEau= resultatRecupWater.getString("lastChange")
                if (resultatRecupParametre.next()) parametreEau.frequenceChangementEau= resultatRecupParametre.getInt("frequenceChangeWater")
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
                    lierViewAvecParametresTemperature(parametreEau)
                }
            }
        }
    }

    private fun lierViewAvecParametresTemperature(parametreEau: ParametreEau) {
        parametreEau.prochainChangementEau()
        whiteTime.post {
            whiteTime.setSelection(Arrays().listeHeure.indexOf(parametreEclairage.recupererHeuresEtMinutes(parametreEclairage.whiteTime)))
        }
        println("Heure blanche = ${Arrays().listeHeure.indexOf(parametreEclairage.recupererHeuresEtMinutes(parametreEclairage.whiteTime))}")
        //whiteTime.setSelection(16, true)
        blueTime.setSelection(Arrays().listeHeure.indexOf(parametreEclairage.recupererHeuresEtMinutes(parametreEclairage.blueTime)))
    }
}