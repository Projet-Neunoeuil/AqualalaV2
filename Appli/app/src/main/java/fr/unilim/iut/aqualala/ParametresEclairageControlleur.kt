package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import fr.unilim.iut.aqualala.model.*

class ParametresEclairageControlleur : AppCompatActivity(), View.OnClickListener   {
    lateinit var whiteTime : EditText
    lateinit var blueTime : EditText
    lateinit var errParamEclair: TextView
    var parametreEclair = ParametreEclairage("8:00:00","17:30:00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_eclairage_controlleur)

        val btnValiderEclair = findViewById<Button>(R.id.btnValiderTemp)
        val btnRetourEclair = findViewById<Button>(R.id.btnRetourTemp)

        initialiserAvecView()
        var asyncParametresEclairageControlleurRecupererDonnees = AsyncParametresEclairageControlleurRecupererDonnees(parametreEclair, whiteTime, blueTime, errParamEclair, this)
        asyncParametresEclairageControlleurRecupererDonnees.execute()
        btnValiderEclair.setOnClickListener(this)
        btnRetourEclair.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderTemp -> {
                parametreEclair.whiteTime=whiteTime.text.toString()
                parametreEclair.blueTime=blueTime.text.toString()
                var asyncParametresEclairageControlleurEnvoyerDonnees = AsyncParametresEclairageControlleurEnvoyerDonnees(parametreEclair, errParamEclair, this)
                asyncParametresEclairageControlleurEnvoyerDonnees.execute()
            }
            R.id.btnRetourTemp -> {
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