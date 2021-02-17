package fr.unilim.iut.aqualala.model

import fr.unilim.iut.aqualala.config.*
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class Connexion constructor(){
    /**
     * TODO Description
     *
     * @return
     */
    fun connectionBD() : Statement {
        Class.forName("com.mysql.jdbc.Driver").newInstance()
        var conn = DriverManager.getConnection(
                //jdbc:mysql://<IP>:<port>/<nom de la base>
                "jdbc:mysql://${ADRESSE_DB}:${PORT_DB}/${NOM_DB}",
                NOM_UTILISATEUR,
                MOT_DE_PASSE
        )
        return conn.createStatement()
    }

    /**
     * Execute a SQL query on the database
     *
     * @param requete  the SQL query
     * @return A resultSet containing the query results
     */
    fun recupererDonnees(requete: String): ResultSet {
        val rs = connectionBD().executeQuery(requete)
        connectionBD().close()
        return rs
    }
}
