package fr.unilim.iut.aqualala

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.*
import fr.unilim.iut.aqualala.model.sql.Connecteur
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import java.sql.Connection
import java.util.concurrent.Executors

class ParametresEclairageControlleur : AppCompatActivity(), View.OnClickListener   {
    lateinit var whiteTime : Spinner
    lateinit var blueTime : Spinner
    lateinit var errParamEclair: TextView
    lateinit var parametreManager : ParametreManager
    lateinit var connection : Connection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_eclairage_controlleur)
        connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }

        val btnValiderEclair = findViewById<Button>(R.id.btnValiderEclair)
        val btnRetourEclair = findViewById<Button>(R.id.btnRetourEclair)
        val array = Arrays()
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        btnNeunoeil.setOnClickListener(this)
        val listeHeure = array.listeHeure
        var adapterHeureBleu : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listeHeure)
        adapterHeureBleu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var adapterHeureBlanc : ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, listeHeure)
        adapterHeureBlanc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        initialiserAvecView()
        whiteTime.adapter = adapterHeureBlanc

        parametreManager = ParametreManager()
        blueTime.adapter = adapterHeureBleu
        btnValiderEclair.setOnClickListener(this)
        btnRetourEclair.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderEclair -> {
                var bool = parametreManager.enregistrerParametresEclairage(whiteTime.selectedItem.toString(), blueTime.selectedItem.toString())
                if(bool) {
                    Toast.makeText(this, "Les données ont bien été enregistrée !", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@ParametresEclairageControlleur, ParametresControlleur::class.java)
                    startActivity(intent)
                } else
                {
                    Toast.makeText(this, "Erreur lors de l'insertion des données", Toast.LENGTH_LONG).show()
                }

            }
            R.id.btnRetourEclair -> {
                val intent = Intent(this@ParametresEclairageControlleur, ParametresControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.neunoeil -> {
                val intent = Intent(this@ParametresEclairageControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    fun initialiserAvecView(){
        whiteTime = findViewById(R.id.heureBlanc)
        blueTime = findViewById(R.id.derniereDate)
        errParamEclair = findViewById(R.id.errParamEclair)
    }
}