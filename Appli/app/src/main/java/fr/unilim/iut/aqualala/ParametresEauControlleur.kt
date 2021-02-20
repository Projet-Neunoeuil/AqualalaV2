package fr.unilim.iut.aqualala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.Arrays
import fr.unilim.iut.aqualala.model.sql.Connecteur
import java.sql.Connection

class ParametresEauControlleur : AppCompatActivity(), View.OnClickListener {
    var frenquence=0
    lateinit var frequenceLigneDeroulante:Spinner
    lateinit var connection:Connection
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_eau_controlleur)
        connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)

        val btnValiderEclair = findViewById<Button>(R.id.btnValiderFrequence)
        val btnRetourEclair = findViewById<Button>(R.id.btnRetourEau)
        var array = Arrays()
        var listePeriodeChangementEau = array.listePeriodeChangeWater
        var adapterPeriodeChangementEau : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listePeriodeChangementEau)
        adapterPeriodeChangementEau.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        btnValiderEclair.setOnClickListener(this)
        btnRetourEclair.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btnValiderFrequence->{

            }
        }
    }
}