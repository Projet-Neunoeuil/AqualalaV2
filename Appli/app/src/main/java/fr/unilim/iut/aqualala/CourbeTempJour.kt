package fr.unilim.iut.aqualala


import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.content.ContextCompat
import com.jjoe64.graphview.GraphView
import fr.unilim.iut.aqualala.model.sql.CourbesManager
import java.util.concurrent.Executors

class CourbeTempJour : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor =
                ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor =
                ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courbe_temp_jour)
        val graph: GraphView = findViewById(R.id.graph)
        graph.visibility = View.VISIBLE
        var exec = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        exec.execute {

            CourbesManager().obtenirCourbeJournee(graph)

        }
    }
}