package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import fr.unilim.iut.aqualala.model.AsyncParametresTemperatureControlleurEnvoyerDonnees
import fr.unilim.iut.aqualala.model.AsyncParametresTemperatureControlleurRecupererDonnees
import fr.unilim.iut.aqualala.model.ParametreTemperature

class ParametresTemperatureControlleur : AppCompatActivity(), View.OnClickListener  {
    lateinit var minTemp : EditText
    lateinit var maxTemp : EditText
    lateinit var periode: EditText
    lateinit var errParamTemp: TextView
    var parametreTemperature = ParametreTemperature(22.00,28.00,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_temperature_controlleur)

        val btnValiderTemp = findViewById<Button>(R.id.btnValiderTemp)
        val btnRetourTemp = findViewById<Button>(R.id.btnRetourTemp)

        initialiserAvecView()
        var AsyncParametresTemperatureControlleurRecupererDonnees = AsyncParametresTemperatureControlleurRecupererDonnees(parametreTemperature, minTemp, maxTemp, periode, errParamTemp, this)
        AsyncParametresTemperatureControlleurRecupererDonnees.execute()
        btnValiderTemp.setOnClickListener(this)
        btnRetourTemp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderTemp -> {
                parametreTemperature.minTemp=minTemp.text.toString().toDouble()
                parametreTemperature.maxTemp=maxTemp.text.toString().toDouble()
                parametreTemperature.periode=periode.text.toString().toInt()
                var AsyncParametresTemperatureControlleurEnvoyerDonnees = AsyncParametresTemperatureControlleurEnvoyerDonnees(parametreTemperature, errParamTemp, this)
                AsyncParametresTemperatureControlleurEnvoyerDonnees.execute()
            }
            R.id.btnRetourTemp -> {
                val intent = Intent(this@ParametresTemperatureControlleur, ParametresControlleur::class.java)
                startActivity(intent)
            }
        }
    }
    fun initialiserAvecView(){
        minTemp = findViewById(R.id.minTemp)
        maxTemp = findViewById(R.id.maxTemp)
        periode = findViewById(R.id.delaiRecup)
        errParamTemp = findViewById(R.id.errParamTemp)
    }
}