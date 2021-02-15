package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Bouton température */
        val btnTemperature = findViewById<ImageButton>(R.id.imageThermo)
        btnTemperature.setOnClickListener {
            val intent = Intent(this@MainMenu, Temperature::class.java)
            startActivity(intent)
        }
    }
}
