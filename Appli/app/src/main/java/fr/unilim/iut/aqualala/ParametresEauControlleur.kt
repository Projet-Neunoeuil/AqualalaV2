package fr.unilim.iut.aqualala

import android.content.Intent
import android.content.pm.ActivityInfo
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
import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import java.sql.Connection
import java.util.concurrent.Executors

class ParametresEauControlleur : AppCompatActivity(), View.OnClickListener {
    lateinit var frequenceLigneDeroulante:Spinner
    lateinit var parametreManager : ParametreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_eau_controlleur)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }

        //Bouton
        val btnValiderEclair = findViewById<Button>(R.id.btnValiderFrequence)
        val btnRetourEclair = findViewById<Button>(R.id.btnRetourEau)
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        val btnMenu = findViewById<TextView>(R.id.btnMenu)
        btnNeunoeil.setOnClickListener(this)
        btnMenu.setOnClickListener(this)
        //Récupérer la liste de période de changement d'eau
        var array = Arrays()
        var listePeriodeChangementEau = array.listePeriodeChangeWater
        //Initialiser le spinner
        frequenceLigneDeroulante=findViewById(R.id.frequenceChangeEau)
        var adapterPeriodeChangementEau : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listePeriodeChangementEau)
        adapterPeriodeChangementEau.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        frequenceLigneDeroulante.adapter=adapterPeriodeChangementEau

        Executors.newSingleThreadExecutor().execute {
            parametreManager = ParametreManager()
            Handler(Looper.getMainLooper()).post {
                Executors.newSingleThreadExecutor().execute {
                    var frequenceChangementEau=parametreManager.obtenirParametresEau()
                    mettreValeurParDefaut(frequenceChangementEau)
                }
            }
        }
        btnValiderEclair.setOnClickListener(this)
        btnRetourEclair.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btnValiderFrequence->{
                var reussi:Boolean
                Executors.newSingleThreadExecutor().execute {
                    reussi=parametreManager.enregistrerParametresEau(frequenceLigneDeroulante.selectedItem.toString().toInt())
                    Handler(Looper.getMainLooper()).post {
                        Executors.newSingleThreadExecutor().execute {
                            if(reussi){
                                runOnUiThread {
                                    Toast.makeText(this, "Les données ont bien été enregistrée !", Toast.LENGTH_SHORT).show()
                                }
                                val intent = Intent(this@ParametresEauControlleur, ParametresControlleur::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else Toast.makeText(this, "Erreur lors de l'insertion des données", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
            R.id.btnRetourEau->{
                val intent = Intent(this@ParametresEauControlleur, ParametresControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.neunoeil, R.id.btnMenu -> {
                val intent = Intent(this@ParametresEauControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun mettreValeurParDefaut(frequenceChangementEau: Int) {
        runOnUiThread {
            frequenceLigneDeroulante.setSelection(Arrays().listePeriodeChangeWater.indexOf(frequenceChangementEau.toString()))
        }
    }
}