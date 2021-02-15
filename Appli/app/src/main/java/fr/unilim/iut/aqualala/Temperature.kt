package fr.unilim.iut.aqualala;


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

const val MIN_TEMP = 22
const val MAX_TEMP = 28

class Temperature : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperature)
    }
}

