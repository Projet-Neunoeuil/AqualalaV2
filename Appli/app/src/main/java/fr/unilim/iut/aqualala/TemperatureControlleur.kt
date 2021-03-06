package fr.unilim.iut.aqualala;

import android.app.NotificationManager
import android.content.Intent
import android.os.*
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.Notification
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import fr.unilim.iut.aqualala.model.sql.TemperatureManager
import fr.unilim.iut.aqualala.model.sql.classes.Parametres
import fr.unilim.iut.aqualala.model.sql.classes.Temperature
import java.sql.Connection
import java.util.concurrent.Executors

/**
* The class linked to the Temperature xml
* @since 1.0.2
**/

class TemperatureControlleur : AppCompatActivity(), View.OnClickListener {
    lateinit var temperature : Temperature
    lateinit var valeurView: TextView
    lateinit var commentaireView: TextView
    lateinit var tempsView: TextView
    lateinit var msgErreurView: TextView
    lateinit var connection : Connection
    lateinit var btnNeunoeil : ImageButton
    lateinit var btn_courbes : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperature)
        initView()

        btnNeunoeil.setOnClickListener(this)
        btn_courbes.setOnClickListener(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
                window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
                window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }

        var handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                Executors.newSingleThreadExecutor().execute {
                    temperature = TemperatureManager().obtenirDerniereTemperature()
                    var parametresTemperature = ParametreManager().obtenirParametres()
                    val periode = parametresTemperature.periodeGetTemp
                    handler.post {
                        lierViewAvecTemperature(temperature, parametresTemperature)
                        handler.postDelayed(this, periode*200*1000.toLong()) //délai en miliseconde : 1000ms = 1s
                    }
                }
            }
        }
        runnable.run()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            (WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        )
    }

    fun initView(){
        valeurView = findViewById(R.id.temperatureValeur)
        commentaireView = findViewById(R.id.commentaireTemperature)
        tempsView = findViewById(R.id.temps)
        msgErreurView = findViewById(R.id.erreur)
        btnNeunoeil = findViewById(R.id.neunoeil)
        btn_courbes = findViewById(R.id.btn_courbes)
    }

    private fun lierViewAvecTemperature(temperature: Temperature, parametre: Parametres) {
        valeurView!!.text = temperature.valeur.toString() + "°C"
        tempsView!!.text = "Température enregistrée à " + temperature.recupererHeureMinute()
        commentaireView!!.text = temperature.commentaireSurLaValiditeTemperature(parametre)
        changerCouleurTexte(temperature, parametre)
    }

    private fun changerCouleurTexte(temperature: Temperature, parametre: Parametres) {
        if(temperature.obtenirValiditeEau(parametre)==0) {
            commentaireView!!.setTextColor(ContextCompat.getColor(this, R.color.vert))
            valeurView!!.setTextColor(ContextCompat.getColor(this, R.color.vert))
        } else {
            commentaireView!!.setTextColor(ContextCompat.getColor(this, R.color.rose_pastel))
            valeurView!!.setTextColor(ContextCompat.getColor(this, R.color.rose_pastel))
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_courbes -> {
                val intent = Intent(this@TemperatureControlleur, CourbesTempMenu::class.java)
                startActivity(intent)
                finish()
            }
            R.id.neunoeil -> {
                val intent = Intent(this@TemperatureControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

