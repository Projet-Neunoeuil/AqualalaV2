package fr.unilim.iut.aqualala;


import android.os.*
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.config.TIME_SECOND_REFRESH
import fr.unilim.iut.aqualala.model.AsyncTache
import fr.unilim.iut.aqualala.model.Temperature
import java.sql.SQLException

/**
* The class linked to the Temperature xml
* @since 1.0.2
**/

class TemperatureController : AppCompatActivity() {
    var temperature = Temperature(0.0, "", false)
    lateinit var valeurView: TextView
    lateinit var commentaireView: TextView
    lateinit var tempsView: TextView
    lateinit var msgErreurView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperature)

        valeurView = findViewById(R.id.temperatureValeur)
        commentaireView = findViewById(R.id.commentaireTemperature)
        tempsView = findViewById(R.id.temps)
        msgErreurView = findViewById(R.id.erreur)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
                window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
                window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        var at = AsyncTache(temperature, msgErreurView, valeurView, tempsView, commentaireView, this)
        var handler : Handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                at.execute()
                handler.postDelayed(this, TIME_SECOND_REFRESH)
            }
        }
        runnable.run()

    }
}

