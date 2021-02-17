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
        btnTemperature.setOnClickListener(this)
        btnTexteTemperature.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.imageThermo, R.id.temperatureTexte -> {
                val intent = Intent(this@MainMenu, TemperatureControlleur::class.java)
                startActivity(intent)
            }
        }
    }
}
