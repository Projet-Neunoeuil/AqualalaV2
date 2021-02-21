package fr.unilim.iut.aqualala

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat

class ParametresControlleur : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_controlleur)

        val btnParamTemp = findViewById<Button>(R.id.btnParamTemp)
        val btnParamEclair = findViewById<Button>(R.id.btnParamEclair)
        val btnParamEau = findViewById<Button>(R.id.btnParamEau)
        btnParamEau.setOnClickListener(this)
        btnParamTemp.setOnClickListener(this)
        btnParamEclair.setOnClickListener(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        btnNeunoeil.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.neunoeil -> {
                val intent = Intent(this@ParametresControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnParamTemp -> {
                val intent = Intent(this@ParametresControlleur, ParametresTemperatureControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnParamEclair -> {
                val intent = Intent(this@ParametresControlleur, ParametresEclairageControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.btnParamEau -> {
                val intent = Intent(this@ParametresControlleur, ParametresEauControlleur::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}