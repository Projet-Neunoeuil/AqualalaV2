package fr.unilim.iut.aqualala;

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.*
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.sql.Connecteur
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import fr.unilim.iut.aqualala.model.sql.TemperatureManager
import fr.unilim.iut.aqualala.model.sql.classes.BASSE
import fr.unilim.iut.aqualala.model.sql.classes.HAUTE
import fr.unilim.iut.aqualala.model.sql.classes.IDEALE
import fr.unilim.iut.aqualala.model.sql.classes.Temperature
import java.sql.Connection
import java.util.concurrent.Executors

/**
* The class linked to the Temperature xml
* @since 1.0.2
**/

class TemperatureControlleur : AppCompatActivity(), View.OnClickListener {
    private val notificationId = 1
    private val CHANNEL_ID = "1"
    lateinit var wakeLock: PowerManager.WakeLock
    lateinit var temperature : Temperature
    lateinit var valeurView: TextView
    lateinit var commentaireView: TextView
    lateinit var tempsView: TextView
    lateinit var msgErreurView: TextView
    lateinit var connection : Connection

    var periode=1

    override fun onCreate(savedInstanceState: Bundle?) {
        Executors.newSingleThreadExecutor().execute {
            connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)
            Handler(Looper.getMainLooper()).post {
                super.onCreate(savedInstanceState)
            }
        }


        setContentView(R.layout.temperature)
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        val btn_courbes = findViewById<Button>(R.id.btn_courbes)
        btnNeunoeil.setOnClickListener(this)
        btn_courbes.setOnClickListener(this)

        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::MyWakelockTag").apply {
                    acquire()
                }
            }

        initView()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
                window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
                window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        var handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {

                Executors.newSingleThreadExecutor().execute {
                    temperature = TemperatureManager(connection).obtenirDerniereTemperature()
                    var parametreManager = ParametreManager(connection)
                    Handler(Looper.getMainLooper()).post {
                        createNotificationChannel()
                        if (temperature.obtenirChaleurEau(ParametreManager(connection).obtenirParametres()) !=0) {
                            sendNotification(parametreManager)
                        }
                        lierViewAvecTemperature(temperature, parametreManager)
                        handler.postDelayed(this, periode*60*1000.toLong()) //délai en miliseconde : 1000ms = 1s
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

        wakeLock.release()
    }

    fun initView(){
        valeurView = findViewById(R.id.temperatureValeur)
        commentaireView = findViewById(R.id.commentaireTemperature)
        tempsView = findViewById(R.id.temps)
        msgErreurView = findViewById(R.id.erreur)
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(parametreManager: ParametreManager) {
        // Ouvrir l'activité à partir de la notification
        val intent = Intent(this, TemperatureControlleur::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        var notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.neunoeil)
            .setContentTitle(getString(R.string.alertTemperature))
            .setContentText(commentaireSurLaValiditeTemperature(temperature, parametreManager))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, notification.build())
        }
    }

    private fun commentaireSurLaValiditeTemperature(temperature: Temperature, parametreManager: ParametreManager): String {
        when(temperature.obtenirChaleurEau(parametreManager.obtenirParametres())) {
            BASSE -> return "La température est annormalement basse"
            HAUTE -> return "La temperature est annormalement Haute"
            IDEALE -> return "La temperature est idéale"
        }
        return "Erreur"
    }


    private fun lierViewAvecTemperature(temperature: Temperature, parametreManager: ParametreManager) {
        valeurView!!.text = temperature.valeur.toString() + "°C"
        tempsView!!.text = "La température enregistrée à " + temperature.recupererHeureMinute()
        commentaireView!!.text = commentaireSurLaValiditeTemperature(temperature, parametreManager)
        periode= parametreManager.obtenirParametres().periodeGetTemp
        changerCouleurTexte(temperature, parametreManager)
    }

    private fun changerCouleurTexte(temperature: Temperature, parametreManager: ParametreManager) {
        if(temperature.obtenirChaleurEau(parametreManager.obtenirParametres())==0) {
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
            }
            R.id.neunoeil -> {
                val intent = Intent(this@TemperatureControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}

