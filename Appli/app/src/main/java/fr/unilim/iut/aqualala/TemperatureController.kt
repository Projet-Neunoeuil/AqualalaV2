package fr.unilim.iut.aqualala;


import android.os.*
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.model.AsyncTache
import fr.unilim.iut.aqualala.model.Temperature
import java.sql.SQLException

/**
* The class linked to the Temperature xml
* @since 1.0.2
**/

class TemperatureController : AppCompatActivity() {
    var temperature = Temperature(0.0, "", false)
    lateinit var valeurView: TextView
    lateinit var commentaireView: TextView
    lateinit var tempsView: TextView
    lateinit var msgErreurView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperature)

        valeurView = findViewById(R.id.temperatureValeur)
        commentaireView = findViewById(R.id.commentaireTemperature)
        tempsView = findViewById(R.id.temps)
        msgErreurView = findViewById(R.id.erreur)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
                window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
                window.statusBarColor = ContextCompat.getColor(this,R.color.orange); // Changer la barre du haut en orange
        }

        Async().execute()
    }

    internal inner class Async : AsyncTask<Void?, Void?, Void?>() {
        var bd = Connexion()
        var requete = "SELECT value, time, inRange FROM Temperature ORDER BY time DESC"
        var erreurDesDonnees = ""

        override fun doInBackground(vararg params: Void?): Void? {
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
            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            //afficher message erreur si erreur de récupération de données
            if (erreurDesDonnees !== "") {
                msgErreurView!!.text = erreurDesDonnees
                Log.d("ERREUR",erreurDesDonnees)
            }
            //afficher la température sinon
            else {
                lierViewAvecTemperature(temperature)
            }
            super.onPostExecute(aVoid)
        }
    }

    private fun lierViewAvecTemperature(temperature: Temperature) {
        valeurView!!.text = temperature.valeur.toString() + " °C"
        tempsView!!.text = temperature.recupererHeuresEtMinutes()
        commentaireView!!.text = temperature.commentaireSurLaValiditeTemperature()
        changerCouleurTexte(temperature.couleurSelonValidite())
    }

    private fun changerCouleurTexte(couleurSelonValidite: Int) {
        commentaireView?.setTextColor(couleurSelonValidite)
    }
}

