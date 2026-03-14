// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : DatabaseConnection.java
// PACKAGE : dao

// FR : Cette classe représente une connexion à la base de données.
// EN : This class represents a database connection.
// PT : Esta classe representa uma conexao com a base de dados.

// AUTHOR : Bauwens Thomas
// STATUS : DONE

package dao;

import java.sql.Connection;
import java.sql.DriverManager;

// FR : Singleton — une seule connexion à la BDD pour toute l'application
public class DatabaseConnection {

    // --- Informations de connexion ---
    // jdbc:postgresql  ->  on utilise le driver PostgreSQL (le .jar dans lib/)
    // localhost        ->  la BDD tourne sur notre propre machine
    // 5432             ->  port par défaut de PostgreSQL
    // barbershop       ->  nom de ta base de données
    private static final String URL  = "jdbc:postgresql://localhost:5432/barbershop";
    private static final String USER = "postgres";
    private static final String PASS = "Thomas";

    // FR : instance garde en mémoire la connexion ouverte
    // "static" = partagée par toute l'application, pas recréée à chaque appel
    private static Connection instance = null;

    // FR : Constructeur privé = personne ne peut faire "new DatabaseConnection()"
    // C'est le principe du Singleton : une seule instance possible
    private DatabaseConnection() {}

    // FR : Seule façon d'obtenir la connexion
    // Si elle n'existe pas encore (ou si elle a été fermée) -> on en crée une nouvelle
    // Sinon -> on retourne celle qui existe déjà
    public static Connection getConnection() throws Exception {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL, USER, PASS);
        }
        return instance;
    }

    // FR : Ferme proprement la connexion quand on a fini
    public static void closeConnection() throws Exception {
        if (instance != null && !instance.isClosed()) {
            instance.close();
        }
    }
}