package fr.unilim.iut.aqualala

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        setContentView(R.layout.parametres_temperature_controlleur)
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        btnNeunoeil.setOnClickListener(this)
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
                if(parametreTemperature.minTemp >= parametreTemperature.maxTemp) {
                    var tmp = parametreTemperature.maxTemp
                    parametreTemperature.maxTemp =parametreTemperature.minTemp
                    parametreTemperature.minTemp = tmp
                    Toast.makeText(this, "Les données ont été inversée pour rester cohérentes", Toast.LENGTH_SHORT).show()
                }
                var asyncParametresTemperatureControlleurEnvoyerDonnees = AsyncParametresTemperatureControlleurEnvoyerDonnees(parametreTemperature, errParamTemp, this)
                asyncParametresTemperatureControlleurEnvoyerDonnees.execute()

                if(errParamTemp.text == "") {
                    Toast.makeText(this, "Les données ont bien été enregistrée !", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ParametresTemperatureControlleur, ParametresControlleur::class.java)
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed(Runnable { startActivity(intent) }, 3000)
                } else
                {
                    Toast.makeText(this, errParamTemp.text, Toast.LENGTH_LONG).show()
                }
            }
            R.id.btnRetourTemp -> {
                val intent = Intent(this@ParametresTemperatureControlleur, ParametresControlleur::class.java)
                startActivity(intent)
            }
            R.id.neunoeil -> {
                val intent = Intent(this@ParametresTemperatureControlleur, MainMenu::class.java)
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