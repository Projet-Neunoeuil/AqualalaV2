package fr.unilim.iut.aqualala.model.sql

import fr.unilim.iut.aqualala.config.*
import java.sql.Connection

abstract class ManagerAbstract {

    val connection : Connection = Connecteur().connecter(ADRESSE_DB, PORT_DB, NOM_DB, NOM_UTILISATEUR, MOT_DE_PASSE, true)

}