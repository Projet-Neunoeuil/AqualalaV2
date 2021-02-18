package fr.unilim.iut.aqualala

import android.content.Intent
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

        val btnCourbeTempJour = findViewById<Button>(R.id.btnCourbeTempJour)
        val btnCourbeTempSemaine = findViewById<Button>(R.id.btnCourbeTempSemaine)
        btnCourbeTempJour.setOnClickListener(this)
        btnCourbeTempSemaine.setOnClickListener(this)
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
            }
            R.id.btnCourbeTempSemaine -> {
                val intent = Intent(this@CourbesTempMenu, CourbeTempSemaine::class.java)
                startActivity(intent)
            }
            R.id.neunoeil -> {
                val intent = Intent(this@CourbesTempMenu, MainMenu::class.java)
                startActivity(intent)
            }
        }
    }
}