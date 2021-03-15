package fr.unilim.iut.aqualala.model.sql

import android.os.Handler
import android.os.Looper
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.concurrent.Executors
import kotlin.jvm.Throws

class Connecteur {
    /* private var connector: Connection? = null

    @Throws (SQLException::class, ClassNotFoundException::class)
    fun connecter(
        hote: String,
        port: Int,
        nomBaseDeDonnee: String,
        utilisateur: String,
        motDePasseUtilisateur: String,
        reconnectionAutomatique: Boolean
    ): Connection {
        if (this.connected()) {
            return this.connector!!
        }
        val url = StringBuilder()
            .append("jdbc:")
            .append("mysql")
            .append("://")
            .append(hote)
            .append(":")
            .append(port)
            .append("/")
            .append(nomBaseDeDonnee)
            .append("?autoreconnect=")
            .append(reconnectionAutomatique)
            .toString()
        Class.forName("com.mysql.jdbc.Driver")
        this.connector = DriverManager.getConnection(url, utilisateur, motDePasseUtilisateur)

        return this.connector!!
    }


    private fun connected(): Boolean {
        return try {
            this.connector != null && !this.connector?.isClosed!!
        } catch (e: SQLException) {
            false
        }
    } */
}