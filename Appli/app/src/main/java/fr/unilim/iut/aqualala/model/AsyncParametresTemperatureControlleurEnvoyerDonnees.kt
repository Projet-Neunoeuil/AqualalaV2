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

class AsyncParametresTemperatureControlleurEnvoyerDonnees constructor(var parametreTemperature: ParametreTemperature, var errParamTemp: TextView, var context: Context){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requeteParametre = "UPDATE Parameters SET minTemp=${parametreTemperature.minTemp}, maxTemp=${parametreTemperature.maxTemp}, period=${parametreTemperature.periode} ;"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            try {
                bd.connectionBD().executeUpdate(requeteParametre)
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
            }
        }
    }
}