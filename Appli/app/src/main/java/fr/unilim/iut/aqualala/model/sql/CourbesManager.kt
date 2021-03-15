package fr.unilim.iut.aqualala.model.sql


import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jjoe64.graphview.DefaultLabelFormatter
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import fr.unilim.iut.aqualala.R
import java.sql.PreparedStatement
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CourbesManager : ManagerAbstract() {


    fun obtenirCourbeJournee(graph: GraphView) : GraphView{
        /*var ps : PreparedStatement = connection.prepareStatement("SELECT value, hour FROM LastDay ORDER BY hour ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var datalist: ArrayList<DataPoint> = ArrayList()
        var hourlist : HashMap<Double, Long> = HashMap()
        var maxY = 0.0
        while (rs.next()) {

            var heure: Date = rs.getTimestamp("hour")
            var value = rs.getDouble("value")
            datalist.add(DataPoint(x, value))
            hourlist[x] = heure.time
            if (value > maxY) {
                maxY = value
            }
            x += 1
        }
        var dataArray = arrayOfNulls<DataPoint>(datalist.size)
        dataArray = datalist.toArray(dataArray)
        var series : LineGraphSeries<DataPoint> = LineGraphSeries(dataArray)
        ps.close()
        val format = SimpleDateFormat("EEEE dd MMMM yyyy à HH:mm")
        series.isDrawDataPoints = true
        series.setOnDataPointTapListener { _, dataPoint ->
            Toast.makeText(
                graph.context,
                "La température moyenne était de ${dataPoint.y}°C le ${format.format(hourlist[dataPoint.x])}",
                Toast.LENGTH_SHORT
            ).show()
        }
        series.color = ContextCompat.getColor(graph.context, R.color.orange)
        graph.addSeries(series)
        var sdf = SimpleDateFormat("EEE HH:mm")
        graph.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    sdf.format(Date(hourlist[value]!!))
                } else {
                    super.formatLabel(value, isValueX)
                }
            }
        }
        graph.viewport.setMinX(datalist[0].x)
        graph.viewport.setMaxX(datalist[datalist.size - 1].x)
        graph.viewport.setMaxY(maxY)
        graph.viewport.maxXAxisSize = datalist.size.toDouble()
        var numberOfPoint = datalist.size
        if(datalist.size > 12) {
            numberOfPoint = datalist.size / 6
        }
        graph.gridLabelRenderer.numHorizontalLabels = numberOfPoint
        graph.viewport.isXAxisBoundsManual = true
        graph.title = "Evolution de la température sur les ${datalist.size} dernières heures"
        return graph */
        return graph
    }
    fun obtenirCourbeSemaine(graph : GraphView): GraphView{
        /*var ps : PreparedStatement = connection.prepareStatement("SELECT value, day FROM LastWeek ORDER BY day ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var datalist: ArrayList<DataPoint> = ArrayList()
        var hourlist : HashMap<Double, Long> = HashMap()
        var maxY = 0.0
        while (rs.next()) {

            var heure: Date = rs.getTimestamp("day")
            var value = rs.getDouble("value")
            datalist.add(DataPoint(x, value))
            hourlist[x] = heure.time
            if (value > maxY) {
                maxY = value
            }
            x += 1
        }
        var dataArray = arrayOfNulls<DataPoint>(datalist.size)
        dataArray = datalist.toArray(dataArray)
        var series : LineGraphSeries<DataPoint> = LineGraphSeries(dataArray)
        ps.close()
        val format = SimpleDateFormat("EEEE dd MMMM yyyy")
        series.isDrawDataPoints = true
        series.setOnDataPointTapListener { _, dataPoint ->
            Toast.makeText(
                graph.context,
                "La température moyenne était de ${dataPoint.y}°C le ${format.format(hourlist[dataPoint.x])}",
                Toast.LENGTH_SHORT
            ).show()
        }
        series.color = ContextCompat.getColor(graph.context, R.color.orange)
        graph.addSeries(series)
        var sdf = SimpleDateFormat("dd MMM")
        graph.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    sdf.format(Date(hourlist[value]!!))
                } else {
                    super.formatLabel(value, isValueX)
                }
            }
        }
        graph.viewport.setMinX(datalist[0].x)
        graph.viewport.setMaxX(datalist[datalist.size - 1].x)
        graph.viewport.setMaxY(maxY)
        graph.viewport.maxXAxisSize = datalist.size.toDouble()
        graph.gridLabelRenderer.numHorizontalLabels = datalist.size
        graph.viewport.isXAxisBoundsManual = true
        graph.title = "Evolution de la température sur les ${datalist.size} derniers jours"*/
        return graph
    }
    fun obtenirCourbeMois(graph : GraphView): GraphView {
        /*var ps : PreparedStatement = connection.prepareStatement("SELECT value, day FROM LastMonth ORDER BY day ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var datalist: ArrayList<DataPoint> = ArrayList()
        var hourlist : HashMap<Double, Long> = HashMap()
        var maxY = 0.0
        while (rs.next()) {

            var heure: Date = rs.getTimestamp("day")
            var value = rs.getDouble("value")
            datalist.add(DataPoint(x, value))
            hourlist[x] = heure.time
            if (value > maxY) {
                maxY = value
            }
            x += 1
        }
        var dataArray = arrayOfNulls<DataPoint>(datalist.size)
        dataArray = datalist.toArray(dataArray)
        var series : LineGraphSeries<DataPoint> = LineGraphSeries(dataArray)
        ps.close()
        val format = SimpleDateFormat("EEEE dd MMMM yyyy")
        series.isDrawDataPoints = true
        series.setOnDataPointTapListener { _, dataPoint ->
            Toast.makeText(
                graph.context,
                "La température moyenne était de ${dataPoint.y}°C le ${format.format(hourlist[dataPoint.x])}",
                Toast.LENGTH_SHORT
            ).show()
        }
        series.color = ContextCompat.getColor(graph.context, R.color.orange)
        graph.addSeries(series)
        var sdf = SimpleDateFormat("dd MMM")
        graph.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    sdf.format(Date(hourlist[value]!!))
                } else {
                    super.formatLabel(value, isValueX)
                }
            }
        }
        graph.viewport.setMinX(datalist[0].x)
        graph.viewport.setMaxX(datalist[datalist.size - 1].x)
        graph.viewport.setMaxY(maxY)
        graph.viewport.maxXAxisSize = datalist.size.toDouble()
        var numberOfPoint = datalist.size
        if(datalist.size > 30) {
            numberOfPoint = datalist.size / 5
        }
        graph.gridLabelRenderer.numHorizontalLabels = numberOfPoint
        graph.viewport.isXAxisBoundsManual = true
        graph.title = "Evolution de la température sur les ${datalist.size} derniers jours" */
        return graph
    }

    fun obtenirCourbeAnnee(graph : GraphView): GraphView {
        /*var ps : PreparedStatement = connection.prepareStatement("SELECT value, month FROM LastYear ORDER BY month ASC")
        var rs = ps.executeQuery()
        var x = 0.0
        var datalist: ArrayList<DataPoint> = ArrayList()
        var hourlist : HashMap<Double, Long> = HashMap()
        var maxY = 0.0
        while (rs.next()) {

            var heure: Date = rs.getTimestamp("month")
            var value = rs.getDouble("value")
            datalist.add(DataPoint(x, value))
            hourlist[x] = heure.time
            if (value > maxY) {
                maxY = value
            }
            x += 1
        }
        var dataArray = arrayOfNulls<DataPoint>(datalist.size)
        dataArray = datalist.toArray(dataArray)
        var series : LineGraphSeries<DataPoint> = LineGraphSeries(dataArray)
        ps.close()
        val format = SimpleDateFormat("MMMM yyyy")
        series.isDrawDataPoints = true
        series.setOnDataPointTapListener { _, dataPoint ->
            Toast.makeText(
                graph.context,
                "La température moyenne était de ${dataPoint.y}°C en ${format.format(hourlist[dataPoint.x])}",
                Toast.LENGTH_SHORT
            ).show()
        }
        series.color = ContextCompat.getColor(graph.context, R.color.orange)
        graph.addSeries(series)
        var sdf = SimpleDateFormat("MMM yyyy")
        graph.gridLabelRenderer.labelFormatter = object : DefaultLabelFormatter() {
            override fun formatLabel(value: Double, isValueX: Boolean): String {
                return if (isValueX) {
                    sdf.format(Date(hourlist[value]!!))
                } else {
                    super.formatLabel(value, isValueX)
                }
            }
        }
        graph.viewport.setMinX(datalist[0].x)
        graph.viewport.setMaxX(datalist[datalist.size - 1].x)
        graph.viewport.setMaxY(maxY)
        graph.viewport.maxXAxisSize = datalist.size.toDouble()
        var numberOfPoint = datalist.size
        if(datalist.size > 10) {
            numberOfPoint = datalist.size / 2
        }
        graph.gridLabelRenderer.numHorizontalLabels = numberOfPoint
        graph.viewport.isXAxisBoundsManual = true
        graph.title = "Evolution de la température sur les ${datalist.size} derniers mois"*/
        return graph
    }
}