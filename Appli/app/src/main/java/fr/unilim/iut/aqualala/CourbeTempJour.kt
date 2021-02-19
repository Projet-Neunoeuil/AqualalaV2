package fr.unilim.iut.aqualala

import android.content.Context
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.sql.DriverManager
import java.util.*
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class CourbeTempJour : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_courbe_temp_jour)
        val graph : GraphView = findViewById(R.id.graph)
        graph.title = "Evolution de la température pendant les 24 dernières heures"
        graph.viewport.isScalable = true
        graph.viewport.setMaxX(24.0)
        graph.visibility = View.VISIBLE
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        var exec = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())
        exec.execute{
            var conn = DriverManager.getConnection(
                //jdbc:mysql://<IP>:<port>/<nom de la base>
                "jdbc:mysql://xiangyu-an.fr:3306/Application",
                "Appli",
                "#M0td3p@553"
            )
            var x = 0.0
            var datalist : ArrayList<DataPoint> = ArrayList()
            var dateOf : HashMap<String, String> = HashMap()
            val rs = conn.createStatement().executeQuery("SELECT value FROM HourlyAverage ORDER BY hour ASC")
            while(rs.next()) {
                var value = rs.getDouble("value")
                datalist.add(DataPoint(x, value))
                x += 1
            }
            handler.post {
                conn.close()
                var dataArr = arrayOfNulls<DataPoint>(datalist.size)
                dataArr = datalist.toArray(dataArr)
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