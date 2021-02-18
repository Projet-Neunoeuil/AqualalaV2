package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView

class MainMenu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        /* Bouton température */
        val btnTemperature = findViewById<ImageButton>(R.id.imageThermo)
        val btnTexteTemperature = findViewById<TextView>(R.id.temperatureTexte)
        val btnEclaiage = findViewById<ImageButton>(R.id.imageLumiere)
        val btnTexteEclairage = findViewById<TextView>(R.id.lumiereTexte)

        btnTemperature.setOnClickListener(this)
        btnTexteTemperature.setOnClickListener(this)
        btnEclaiage.setOnClickListener(this)
        btnTexteEclairage.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.imageThermo, R.id.temperatureTexte -> {
                val intent = Intent(this@MainMenu, TemperatureControlleur::class.java)
                startActivity(intent)
            }
            R.id.imageLumiere, R.id.lumiereTexte->{
                val intent = Intent(this@MainMenu, ParametresControlleur::class.java)
                startActivity(intent)
            }
        }
    }
}
