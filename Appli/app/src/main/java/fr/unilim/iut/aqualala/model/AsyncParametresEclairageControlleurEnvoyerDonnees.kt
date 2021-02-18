package fr.unilim.iut.aqualala.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import java.sql.SQLException
import java.util.concurrent.Executors

class AsyncParametresEclairageControlleurEnvoyerDonnees constructor(
    var parametreEclairage: ParametreEclairage,
    var errParamTemp: TextView,
    var context: Context
){
    var executor = Executors.newSingleThreadExecutor()
    var bd = Connexion()
    var requeteParametre = "UPDATE Parameters SET whiteTime= CAST('${parametreEclairage.whiteTime}' AS TIME), blueTime= CAST('${parametreEclairage.blueTime}' AS TIME) ;"
    var erreurDesDonnees = ""
    val handler = Handler(Looper.getMainLooper())

    fun execute() {
        executor.execute {
            try {
                Log.d("sql",requeteParametre)
                bd.connectionBD().execute(requeteParametre)
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