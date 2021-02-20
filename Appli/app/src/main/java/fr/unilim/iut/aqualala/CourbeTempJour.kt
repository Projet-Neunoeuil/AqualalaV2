package fr.unilim.iut.aqualala

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.sql.Connecteur
import fr.unilim.iut.aqualala.model.sql.CourbesManager
import java.sql.Connection
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class CourbeTempJour : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // Si le téléphone est compatible alors
            window.navigationBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du bas en orange
            window.statusBarColor = ContextCompat.getColor(this, R.color.orange); // Changer la barre du haut en orange
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courbe_temp_jour)
        val graph : GraphView = findViewById(R.id.graph)
        graph.title = "Evolution de la température pendant les 24 dernières heures"
        graph.viewport.isScalable = true
        graph.viewport.setMaxX(24.0)
        graph.visibility = View.VISIBLE
        var exec = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        exec.execute{

            var listeTemp : ArrayList<DataPoint> = CourbesManager().obtenirCourbeJournee()

            handler.post {
                var dataArr = arrayOfNulls<DataPoint>(listeTemp.size)
                dataArr = listeTemp.toArray(dataArr)
                var series : LineGraphSeries<DataPoint?> = LineGraphSeries(dataArr)
                series.isDrawDataPoints = true
                series.thickness = 8
                series.setOnDataPointTapListener { series, dataPoint ->
                    Toast.makeText(
                        getMyActivity(),
                        "La température est de ${dataPoint.y}°C",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                graph.addSeries(series)
            }
        }
    }

    private fun getMyActivity(): Context? {
        return this;
    }
}