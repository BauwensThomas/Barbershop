// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : DatabaseConnection.java
// PACKAGE : dao

// FR : Cette classe représente une connexion à la base de données.
// EN : This class represents a database connection.
// PT : Esta classe representa uma conexao com a base de dados.

// AUTHOR : Bauwens Thomas
// STATUS : DONE

package dao;

// FR : On importe les classes nécessaires pour gérer les connexions à la base de données
// EN : We import the necessary classes to manage database connections
// PT : Importamos as classes necessárias para gerenciar conexões com a base de dados
import java.sql.Connection;
import java.sql.DriverManager;

// FR : Singleton — une seule connexion à la BD pour toute l'application
// EN : Singleton — a single database connection for the entire application
// PT : Singleton — uma única conexão com a base de dados para toda a aplicação

// FR : Cette classe gère la connexion à la base de données PostgreSQL utilisée par l'application.
// EN : This class manages the connection to the PostgreSQL database used by the application.
// PT : Esta classe gerencia a conexão com o banco de dados PostgreSQL usado pela aplicação.
public class DatabaseConnection {

    // FR : URL de connexion à la base de données, nom d'utilisateur et mot de passe
    // EN : Database connection URL, username and password
    // PT : URL de conexão com a base de dados, nome de usuário e senha
    private static final String URL  = "jdbc:postgresql://localhost:5432/barbershop";
    private static final String USER = "postgres";
    private static final String PASS = "Thomas";

    // FR : L'instance unique de la connexion    
    // EN : The single instance of the connection
    // PT : A única instância da conexão
    private static Connection instance = null;

    // FR : Constructeur privé pour empêcher l'instanciation directe
    // EN : Private constructor to prevent direct instantiation
    // PT : Construtor privado para impedir a instanciação direta
    private DatabaseConnection() {}

    // FR : Méthode pour obtenir la connexion à la base de données
    // EN : Method to get the database connection
    // PT : Método para obter a conexão com a base de dados
    public static Connection getConnection() throws Exception {
        if (instance == null || instance.isClosed()) {
            instance = DriverManager.getConnection(URL, USER, PASS);
        }
        return instance;
    }

    // FR : Ferme proprement la connexion quand on a fini
    // EN : Properly closes the connection when we're done
    // PT : Fecha adequadamente a conexão quando terminamos
    public static void closeConnection() throws Exception {
        if (instance != null && !instance.isClosed()) {
            instance.close();
        }
    }
}