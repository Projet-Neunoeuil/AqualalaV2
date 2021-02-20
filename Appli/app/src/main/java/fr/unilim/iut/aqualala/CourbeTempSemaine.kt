package fr.unilim.iut.aqualala

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.jjoe64.graphview.GraphView
import fr.unilim.iut.aqualala.model.sql.CourbesManager
import java.util.concurrent.Executors

class CourbeTempSemaine : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courbe_temp_semaine)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor =
                ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor =
                ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        val graph: GraphView = findViewById(R.id.graph)
        graph.visibility = View.VISIBLE
        var exec = Executors.newSingleThreadExecutor()
        exec.execute {
            CourbesManager().obtenirCourbeSemaine(graph)
        }
    }

    private fun getMyActivity(): Context? {
        return this;
    }
}
