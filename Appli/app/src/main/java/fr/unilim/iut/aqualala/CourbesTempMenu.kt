package fr.unilim.iut.aqualala

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.ContextCompat

class CourbesTempMenu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courbes_temp_menu)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val btnCourbeTempJour = findViewById<Button>(R.id.btnCourbeTempJour)
        val btnCourbeTempSemaine = findViewById<Button>(R.id.btnCourbeTempSemaine)
        val btnCourbeTempMois = findViewById<Button>(R.id.btnCourbeTempMois)
        val btnCourbeTempAn = findViewById<Button>(R.id.btnCourbeTempAn)
        val btnRetour = findViewById<Button>(R.id.btnRetour)
        btnRetour.setOnClickListener(this)
        btnCourbeTempJour.setOnClickListener(this)
        btnCourbeTempSemaine.setOnClickListener(this)
        btnCourbeTempMois.setOnClickListener(this)
        btnCourbeTempAn.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        btnNeunoeil.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnCourbeTempJour -> {
                val intent = Intent(this@CourbesTempMenu, CourbeTempJour::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnCourbeTempSemaine -> {
                val intent = Intent(this@CourbesTempMenu, CourbeTempSemaine::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnCourbeTempMois -> {
                val intent = Intent(this@CourbesTempMenu, CourbeTempMois::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnCourbeTempAn -> {
                val intent = Intent(this@CourbesTempMenu, CourbeTempAn::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnRetour -> {
                val intent = Intent(this@CourbesTempMenu, TemperatureControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.neunoeil -> {
                val intent = Intent(this@CourbesTempMenu, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}