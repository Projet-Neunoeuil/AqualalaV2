package fr.unilim.iut.aqualala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class ParametresControlleur : AppCompatActivity(), View.OnClickListener  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parametres_controlleur)

        val btnParamTemp = findViewById<Button>(R.id.btnParamTemp)
        val btnParamEclair = findViewById<Button>(R.id.btnParamEclair)
        btnParamTemp.setOnClickListener(this)
        btnParamEclair.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnParamTemp -> {
                val intent = Intent(this@ParametresControlleur, ParametresTemperatureControlleur::class.java)
                startActivity(intent)
            }
            R.id.btnParamEclair -> {
                val intent = Intent(this@ParametresControlleur, ParametresEclairageControlleur::class.java)
                startActivity(intent)
            }
        }
    }
}