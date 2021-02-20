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
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.Arrays
import fr.unilim.iut.aqualala.model.sql.Connecteur
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import java.sql.Connection
import java.util.concurrent.Executors

class ParametresTemperatureControlleur : AppCompatActivity(), View.OnClickListener  {
    lateinit var minTemp : Spinner
    lateinit var maxTemp : Spinner
    lateinit var periode: Spinner
    lateinit var errParamTemp: TextView
    lateinit var parametreManager : ParametreManager
    lateinit var connection : Connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        setContentView(R.layout.parametres_temperature_controlleur)
        connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)
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

        parametreManager = ParametreManager()



        initialiserAvecView()
        minTemp.adapter = adapterMinTemp
        maxTemp.adapter = adapterMaxTemp
        periode.adapter = adapterPeriode

        btnValiderTemp.setOnClickListener(this)
        btnRetourTemp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderTemp -> {
                var bool : Boolean = false;
                if(minTemp.selectedItem.toString().toDouble() >= maxTemp.selectedItem.toString().toDouble()) {
                    errParamTemp.text = "Erreur, données inversée, veuillez recommencer"
                } else
                {
                    bool = parametreManager.enregistrerParametresTemperature(minTemp.selectedItem.toString().toDouble(), maxTemp.selectedItem.toString().toDouble(), periode.selectedItem.toString().toInt())
                }


                if(bool) {
                    Toast.makeText(this, "Les données ont bien été enregistrée !", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ParametresTemperatureControlleur, ParametresControlleur::class.java)
                    val handler = Handler(Looper.getMainLooper())
                    handler.postDelayed(Runnable { startActivity(intent) }, 3000)
                } else
                {
                    Toast.makeText(this,"Erreur lors de l'insertion des données", Toast.LENGTH_LONG).show()
                }
            }
            R.id.btnRetourTemp -> {
                val intent = Intent(this@ParametresTemperatureControlleur, ParametresControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.neunoeil -> {
                val intent = Intent(this@ParametresTemperatureControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
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