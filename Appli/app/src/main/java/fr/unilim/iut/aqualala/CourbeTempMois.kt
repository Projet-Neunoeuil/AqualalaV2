package fr.unilim.iut.aqualala

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
import androidx.core.content.ContextCompat
import com.jjoe64.graphview.GraphView
import fr.unilim.iut.aqualala.model.sql.CourbesManager
import java.util.concurrent.Executors

class CourbeTempMois : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor =
                ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor =
                ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courbe_temp_mois)
        val graph: GraphView = findViewById(R.id.graph)
        val btnRetour : Button = findViewById(R.id.btnRetour)
        val neunoeil : ImageButton = findViewById(R.id.neunoeil)
        btnRetour.setOnClickListener(this)
        neunoeil.setOnClickListener(this)
        graph.visibility = View.VISIBLE
        var exec = Executors.newSingleThreadExecutor()
        exec.execute {

            CourbesManager().obtenirCourbeMois(graph)

        }
    }

    override fun onClick(view : View) {
        when (view.id) {
            R.id.btnRetour -> {
                val intent = Intent(this@CourbeTempMois, CourbesTempMenu::class.java)
                startActivity(intent)
                finish()
            }
            R.id.neunoeil -> {
                val intent = Intent(this@CourbeTempMois, MainMenu::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}