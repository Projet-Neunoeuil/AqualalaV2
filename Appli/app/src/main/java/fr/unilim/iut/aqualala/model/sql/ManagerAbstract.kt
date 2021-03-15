package fr.unilim.iut.aqualala.model.sql

import com.google.firebase.firestore.FirebaseFirestore
import fr.unilim.iut.aqualala.config.*
import java.sql.Connection

abstract class ManagerAbstract {
    //val connection : Connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)
    val db = FirebaseFirestore.getInstance()
    val temperatureDB = db.collection("Temperature")
    val parametreDB = db.collection("Parameters")
}