package fr.unilim.iut.aqualala

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainMenu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }

        /* Bouton température */
        val btnTemperature = findViewById<ImageButton>(R.id.imageThermo)
        val btnTexteTemperature = findViewById<TextView>(R.id.temperatureTexte)
        val btnEclaiage = findViewById<ImageButton>(R.id.imageLumiere)
        val btnTexteEclairage = findViewById<TextView>(R.id.lumiereTexte)
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        btnNeunoeil.setOnClickListener(this)

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
            R.id.neunoeil-> {
                val intent = Intent(this@MainMenu, MainMenu::class.java)
                startActivity(intent)
            }
        }
    }
}
