package fr.unilim.iut.aqualala

import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.Notification
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import java.util.concurrent.Executors

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

        /* Bouton éclairage */
        val btnEclaiage = findViewById<ImageButton>(R.id.imageParametre)
        val btnTexteEclairage = findViewById<TextView>(R.id.lumiereTexte)

        /* Bouton eau */
        val btnEau=findViewById<Button>(R.id.imageEau)
        var btnTexteEau=findViewById<TextView>(R.id.eauTexte)

        /* Bouton général */
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)



        btnNeunoeil.setOnClickListener(this)
        btnTemperature.setOnClickListener(this)
        btnTexteTemperature.setOnClickListener(this)
        btnEclaiage.setOnClickListener(this)
        btnTexteEclairage.setOnClickListener(this)
        btnEau.setOnClickListener(this)
        btnTexteEau.setOnClickListener(this)

        var notification =  Notification(
            NOTIFICATION_ID_EAU,
            CHANNEL_EAU,
            MainMenu::class.java,
            this.applicationContext
        )
        var handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                Executors.newSingleThreadExecutor().execute {
                    var parametresTemperature = ParametreManager().obtenirParametres()
                    handler.post {
                        notification.createNotificationChannel(
                            getString(R.string.channel_eau_name),
                            getString(R.string.channel_eau_description),
                            NotificationManager.IMPORTANCE_DEFAULT
                        )

                        if (!parametresTemperature.waterLevel) {
                            notification.sendNotification(
                                R.mipmap.neunoeil,
                                getString(R.string.eau_Alerte_Titre),
                                getString((R.string.description_Eau_Alerte)),
                                NotificationCompat.PRIORITY_DEFAULT
                            )
                        }
                        handler.postDelayed(this, parametresTemperature.periodeGetTemp*3*1000.toLong()) //délai en miliseconde : 1000ms = 1s
                    }
                }
            }
        }
        runnable.run()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.neunoeil-> {
                val intent = Intent(this@MainMenu, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
            R.id.imageThermo, R.id.temperatureTexte -> {
                val intent = Intent(this@MainMenu, TemperatureControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.imageParametre, R.id.lumiereTexte->{
                val intent = Intent(this@MainMenu, ParametresControlleur::class.java)
                startActivity(intent)
                finish()
            }
            R.id.imageEau,R.id.eauTexte->{
                val intent = Intent(this@MainMenu, EauControlleur::class.java)
                startActivity(intent)
                finish()
            }


        }
    }
}
