// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : Main.java
// PACKAGE : main

// FR : Cette classe représente le point d'entrée de l'application.
// EN : This class represents the entry point of the application.
// PT : Esta classe representa o ponto de entrada da aplicacao.

// AUTHOR : Bauwens Thomas
// STATUS : IN_PROGRESS

// FR : On importe les classes nécessaires pour gérer les clients et la connexion à la base de données
// EN : We import the necessary classes to manage clients and database connection
// PT : Importamos as classes necessárias para gerenciar clientes e conexão com a base de dados

import dao.ClientDAO;
import dao.DatabaseConnection;
import model.Client;

import java.sql.Connection;
import java.util.List;

// FR : Classe principale de l'application
// EN : Main class of the application
// PT : Classe principal da aplicação
public class Main {

    // FR : Point d'entrée de l'application.
    // EN : Application entry point.
    // PT : Ponto de entrada da aplicacao.
    public static void main(String[] args) {
        System.out.println("BarberShop v1.0 - Starting application...");

// FR : Connection null au début, on la récupérera dans le try-catch
// EN : Connection is null at the beginning, we will get it in the try-catch
// PT : Conexão é nula no início, vamos obtê-la no try-catch
        Connection conn = null;

        try {
            // FR : Récupération de la connexion à la base de données
            // EN : Getting the database connection
            // PT : Obtendo a conexão com o banco de dados
            conn = DatabaseConnection.getConnection();
            System.out.println("Connection OK : " + !conn.isClosed());

            // FR : Instanciation du DAO pour les opérations sur les clients
            // EN : Instantiating the DAO for client operations
            // PT : Instanciando o DAO para operações com clientes
            ClientDAO clientDAO = new ClientDAO(conn);

            // FR : Création d'un nouveau client à insérer en base
            // EN : Creating a new client to insert into the database
            // PT : Criando um novo cliente para inserir no banco de dados
            Client nouveau = new Client("Dupont", "Pierre", "06 99 88 77 66", null);

// FR : Tentative d'insertion du client en base, avec gestion des doublons sur le numéro de téléphone
// EN : Attempt to insert the client into the database, with duplicate phone number handling
// PT : Tentativa de inserir o cliente no banco de dados, com tratamento de número de telefone duplicado
            try {
                boolean sauvegarde = clientDAO.save(nouveau);
                if (sauvegarde) {
                    System.out.println("Client ajouté : " + nouveau);
                }
            } catch (Exception e) {
                // FR : Doublon détecté sur le numéro de téléphone
                // EN : Duplicate detected on phone number
                // PT : Duplicado detectado no número de telefone
                if (e.getMessage().contains("clients_telephone_key")) {
                    System.out.println("Client non ajouté : numéro de téléphone déjà existant.");
                } else {
                    System.err.println("Erreur lors de l'ajout : " + e.getMessage());
                }
            }

            // FR : Récupération et affichage de tous les clients en base
            // EN : Retrieving and displaying all clients in the database
            // PT : Recuperando e exibindo todos os clientes no banco de dados
            List<Client> clients = clientDAO.findAll();
            clients.forEach(c -> System.out.println("  -> " + c));

        } catch (Exception e) {
            // FR : Gestion des erreurs de connexion ou d'opération SQL
            // EN : Handling of connection or SQL operation errors
            // PT : Tratamento de erros de conexão ou operação SQL
            System.err.println("Erreur : " + e.getMessage());

        } finally {
            // FR : Fermeture de la connexion — exécuté même en cas d'erreur
            // EN : Closing the connection — executed even in case of error
            // PT : Fechando a conexão — executado mesmo em caso de erro
            try {
                DatabaseConnection.closeConnection();
                System.out.println("BD : Connection fermée.");
            } catch (Exception e) {
                System.err.println("Erreur fermeture connexion : " + e.getMessage());
            }
        }
    }
}
