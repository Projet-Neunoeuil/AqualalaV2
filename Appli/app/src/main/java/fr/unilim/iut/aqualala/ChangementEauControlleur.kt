package fr.unilim.iut.aqualala
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import fr.unilim.iut.aqualala.config.*
import fr.unilim.iut.aqualala.model.sql.Connecteur
import fr.unilim.iut.aqualala.model.sql.ParametreManager
import fr.unilim.iut.aqualala.model.sql.classes.Eau
import java.sql.Connection
import java.util.*

class ChangementEauControlleur : AppCompatActivity(), View.OnClickListener {
    lateinit var connection : Connection
    lateinit var eau: Eau
    lateinit var DerniereDateChangementEauView:TextView
    lateinit var ProchianeDateChangementEauView: TextView
    lateinit var derniereDateChangementEau:Date
    var frequenceChangementEau=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changement_eau)
        connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)

        DerniereDateChangementEauView= findViewById(R.id.derniereDateChangementEau)
        ProchianeDateChangementEauView = findViewById(R.id.prochainChangementEau)
        derniereDateChangementEau = ParametreManager().obtenirDateEau()
        frequenceChangementEau=ParametreManager().obtenirParametresEau()
        var boutonValide: Button =findViewById(R.id.btnValideChangementEau)

        eau=Eau(derniereDateChangementEau)
        DerniereDateChangementEauView.text=derniereDateChangementEau.toString()
        ProchianeDateChangementEauView.text=eau.prochainChangementEau(frequenceChangementEau)
        boutonValide.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnValideChangementEau->{
                ParametreManager().enregistrerEau(DerniereDateChangementEauView.text.toString())
                eau.derniereDateChangementEau=ParametreManager().obtenirDateEau()
                ProchianeDateChangementEauView.text=eau.prochainChangementEau(frequenceChangementEau)
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
                val mDate = "${annee}-${mois + 1}-${jour}"
                date.text = mDate
            },
            anneeAffichage, moisAffichage, jourAffichage
        )
        datePickerDialog.show()
    }

}