package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import fr.unilim.iut.aqualala.model.Arrays
import fr.unilim.iut.aqualala.model.AsyncParametresTemperatureControlleurEnvoyerDonnees
import fr.unilim.iut.aqualala.model.AsyncParametresTemperatureControlleurRecupererDonnees
import fr.unilim.iut.aqualala.model.ParametreTemperature

class ParametresTemperatureControlleur : AppCompatActivity(), View.OnClickListener  {
    lateinit var minTemp : Spinner
    lateinit var maxTemp : Spinner
    lateinit var periode: Spinner
    lateinit var errParamTemp: TextView
    var parametreTemperature = ParametreTemperature(22.00,28.00,1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_temperature_controlleur)

        val btnValiderTemp = findViewById<Button>(R.id.btnValiderTemp)
        val btnRetourTemp = findViewById<Button>(R.id.btnRetourTemp)
        val array = Arrays()
        val listeTemperature = array.listeTemperature
        val listePeriode = array.listeDelai
        var adapterMinTemp : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listeTemperature)
        adapterMinTemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var adapterMaxTemp : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listeTemperature)
        adapterMaxTemp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var adapterPeriode : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listePeriode)
        adapterPeriode.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)



        initialiserAvecView()
        minTemp.adapter = adapterMinTemp
        maxTemp.adapter = adapterMaxTemp
        periode.adapter = adapterPeriode
        var asyncParametresTemperatureControlleurRecupererDonnees = AsyncParametresTemperatureControlleurRecupererDonnees(parametreTemperature, minTemp, maxTemp, periode, errParamTemp, this)
        asyncParametresTemperatureControlleurRecupererDonnees.execute()
        btnValiderTemp.setOnClickListener(this)
        btnRetourTemp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderTemp -> {
                parametreTemperature.minTemp=minTemp.selectedItem.toString().toDouble()
                parametreTemperature.maxTemp=maxTemp.selectedItem.toString().toDouble()
                parametreTemperature.periode=periode.selectedItem.toString().toInt()
                var asyncParametresTemperatureControlleurEnvoyerDonnees = AsyncParametresTemperatureControlleurEnvoyerDonnees(parametreTemperature, errParamTemp, this)
                asyncParametresTemperatureControlleurEnvoyerDonnees.execute()
                Toast.makeText(this, "Les données ont bien été enregistrée !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@ParametresTemperatureControlleur, ParametresControlleur::class.java)
                startActivity(intent)
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