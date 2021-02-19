package fr.unilim.iut.aqualala.model.sql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class Connecteur {
    lateinit var connector : Connection

    @Throws(ClassNotFoundException::class, SQLException::class)
    fun connecter(
        hote: String,
        port: Int,
        nomBaseDeDonnee: String,
        utilisateur: String,
        motDePasseUtilisateur: String,
        reconnectionAutomatique: Boolean
    ): Connection {
        if (this.connected()) {
            return this.connector
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
        connector = DriverManager.getConnection(url, utilisateur, motDePasseUtilisateur)
        return this.connector
    }


    private fun connected(): Boolean {
        return try {
            this.connector != null && !this.connector.isClosed
        } catch (e: SQLException) {
            false
        }
    }
}