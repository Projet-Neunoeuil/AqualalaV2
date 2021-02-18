package fr.unilim.iut.aqualala.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.R
import java.sql.SQLException
import java.util.concurrent.Executors

class AsyncParametresTemperatureControlleurRecupererDonnees constructor(var parametreTemperature: ParametreTemperature, var minTemp: EditText, var maxTemp : EditText, var periode : EditText, var errParamTemp: TextView, var context: Context){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requeteParametre = "SELECT minTemp, maxTemp, period FROM Parameters ;"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            try {
                var resultatRecupParametre= bd.recupererDonnees(requeteParametre)

                if (resultatRecupParametre.next()) {
                    parametreTemperature.maxTemp= resultatRecupParametre.getDouble("maxTemp")
                    parametreTemperature.minTemp = resultatRecupParametre.getDouble("minTemp")
                    parametreTemperature.periode=resultatRecupParametre.getInt("period")
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
                    lierViewAvecParametresTemperature(parametreTemperature)
                }
            }
        }
    }

    private fun lierViewAvecParametresTemperature(parametreTemperature: ParametreTemperature) {
        minTemp!!.setText(parametreTemperature.minTemp.toString())
        maxTemp!!.setText(parametreTemperature.maxTemp.toString())
        periode!!.setText(parametreTemperature.periode.toString())
    }
}