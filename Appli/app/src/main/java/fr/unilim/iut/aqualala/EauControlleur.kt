package fr.unilim.iut.aqualala
import android.app.DatePickerDialog
import android.app.NotificationManager
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import fr.unilim.iut.aqualala.model.sql.TemperatureManager
import fr.unilim.iut.aqualala.model.sql.classes.Eau
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

class EauControlleur : AppCompatActivity(), View.OnClickListener {
    lateinit var eau: Eau
    lateinit var DerniereDateChangementEauView:TextView
    lateinit var ProchianeDateChangementEauView: TextView
    lateinit var derniereDateChangementEau:Date
    lateinit var boutonValide: Button
    var frequenceChangementEau=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eau)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        initialiserAvecView()
        var handler = Handler(Looper.getMainLooper())
        val runnable: Runnable = object : Runnable {
            override fun run() {
                Executors.newSingleThreadExecutor().execute {
                    derniereDateChangementEau=ParametreManager().obtenirDateEau()
                    frequenceChangementEau=ParametreManager().obtenirParametresEau()
                    eau=Eau(derniereDateChangementEau)
                    handler.post {
                        mettreValeurParDefaut()
                    }
                }
            }
        }
        runnable.run()
        val btnNeunoeil = findViewById<ImageButton>(R.id.neunoeil)
        val btnMenu = findViewById<TextView>(R.id.btnMenu)
        btnMenu.setOnClickListener(this)
        btnNeunoeil.setOnClickListener(this)
        boutonValide.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValiderChangementEau->{
                var reussi:Boolean
                Executors.newSingleThreadExecutor().execute {
                    ParametreManager().enregistrerEau(changeEnAnneeMoisJour())
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this,"Le changement d'eau a bien été enregistré !", Toast.LENGTH_SHORT).show()
                        Executors.newSingleThreadExecutor().execute {
                            val intent = Intent(this@EauControlleur, EauControlleur::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
            }
            R.id.neunoeil, R.id.btnMenu -> {
                val intent = Intent(this@EauControlleur, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    fun viewSelectionerDate(view: View) {
        val calandrier = Calendar.getInstance()
        var anneeAffichage = calandrier[Calendar.YEAR]
        var moisAffichage = calandrier[Calendar.MONTH]
        var jourAffichage = calandrier[Calendar.DAY_OF_MONTH]
        var date: TextView = findViewById(R.id.derniereDateChangementEau)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, annee, mois, jour ->
                anneeAffichage = annee
                moisAffichage = mois
                jourAffichage = jour
                val mDate = "${jour}/${mois + 1}/${annee}"
                date.text = mDate
            },
            anneeAffichage, moisAffichage, jourAffichage
        )
        datePickerDialog.show()
    }

    fun initialiserAvecView(){
        boutonValide =findViewById(R.id.btnValiderChangementEau)
        DerniereDateChangementEauView= findViewById(R.id.derniereDateChangementEau)
        ProchianeDateChangementEauView = findViewById(R.id.prochainChangementEau)
    }

    fun mettreValeurParDefaut(){
        DerniereDateChangementEauView.text=eau.recupererJourMoisAnnee()
        ProchianeDateChangementEauView.text=eau.prochainChangementEau(frequenceChangementEau)
    }

    fun changeEnAnneeMoisJour():String{
        val tableauDate=DerniereDateChangementEauView.text.split("/")
        val jour= tableauDate[0].toInt()
        val mois= tableauDate[1].toInt()
        val annee= tableauDate[2].toInt()
        return "$annee-$mois-$jour"
    }

}