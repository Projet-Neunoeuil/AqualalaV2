package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import fr.unilim.iut.aqualala.model.*

class ParametresEclairageControlleur : AppCompatActivity(), View.OnClickListener   {
    lateinit var whiteTime : Spinner
    lateinit var blueTime : Spinner
    lateinit var errParamEclair: TextView
    var parametreEclair = ParametreEclairage("8:00:00","17:30:00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_eclairage_controlleur)

        val btnValiderEclair = findViewById<Button>(R.id.btnValiderEclair)
        val btnRetourEclair = findViewById<Button>(R.id.btnRetourEclair)
        val array = Arrays()
        val listeHeure = array.listeHeure
        var adapterHeure : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listeHeure)
        adapterHeure.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        initialiserAvecView()
        whiteTime.adapter = adapterHeure
        blueTime.adapter = adapterHeure

        var asyncParametresEclairageControlleurRecupererDonnees = AsyncParametresEclairageControlleurRecupererDonnees(parametreEclair, whiteTime, blueTime, errParamEclair, this)
        asyncParametresEclairageControlleurRecupererDonnees.execute()
        btnValiderEclair.setOnClickListener(this)
        btnRetourEclair.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderEclair -> {
                parametreEclair.whiteTime=whiteTime.selectedItem.toString()
                parametreEclair.blueTime=blueTime.selectedItem.toString()
                var asyncParametresEclairageControlleurEnvoyerDonnees = AsyncParametresEclairageControlleurEnvoyerDonnees(parametreEclair, errParamEclair, this)
                asyncParametresEclairageControlleurEnvoyerDonnees.execute()
                Toast.makeText(this, "Les données ont bien été enregistrée !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ParametresEclairageControlleur, ParametresControlleur::class.java)
                startActivity(intent)
            }
            R.id.btnRetourEclair -> {
                val intent = Intent(this@ParametresEclairageControlleur, ParametresControlleur::class.java)
                startActivity(intent)
            }
        }
    }
    fun initialiserAvecView(){
        whiteTime = findViewById(R.id.heureBlanc)
        blueTime = findViewById(R.id.heureBleu)
        errParamEclair = findViewById(R.id.errParamEclair)
    }
}