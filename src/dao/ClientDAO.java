// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : ClientDAO.java
// PACKAGE : dao

// FR : Cette classe gère les opérations CRUD des clients en base de données.
// EN : This class handles CRUD operations for clients in the database.
// PT : Esta classe gerencia as operacoes CRUD dos clientes no banco de dados.

// AUTHOR : Bauwens Thomas
// STATUS : DONE

package dao;

// FR : On importe les classes nécessaires pour manipuler les clients et gérer les requêtes SQL
// EN : We import the necessary classes to manipulate clients and manage SQL queries
// PT : Importamos as classes necessárias para manipular clientes e gerenciar consultas SQL
import model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// FR : Cette classe gère les opérations CRUD (Create, Read, Update, Delete) pour les clients dans la base de données.
// EN : This class manages CRUD (Create, Read, Update, Delete) operations for clients in the database.
// PT : Esta classe gerencia as operações CRUD (Create, Read, Update, Delete) para os clientes no banco de dados.
public class ClientDAO {

    // FR : Connexion à la base de données
    // EN : Database connection
    // PT : Conexão com o banco de dados
    private Connection connection;

    // FR : Constructeur : reçoit la connexion active
    // EN : Constructor : receives the active connection
    // PT : Construtor : recebe a conexão ativa
    public ClientDAO(Connection connection) {
        this.connection = connection;
    }

    // FR : Sauvegarder un nouveau client dans la BD
    // EN : Save a new client to the database
    // PT : Salvar um novo cliente no banco de dados
    public boolean save(Client client) throws Exception {

        // FR : Requête d'insertion avec les 4 champs du client
        // EN : Insertion query with the 4 client fields
        // PT : Consulta de inserção com os 4 campos do cliente
        String sql = "INSERT INTO clients (nom, prenom, telephone, email) VALUES (?,?,?,?)";

        // fr : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection des valeurs dans la requête
            // EN : Injection of values into the query
            // PT : Injeção dos valores na consulta
            stmt.setString(1, client.getNom());
            stmt.setString(2, client.getPrenom());
            stmt.setString(3, client.getTelephone());
            stmt.setString(4, client.getEmail());

            // FR : Exécution et retour vrai si au moins une ligne insérée
            // EN : Execution and return true if at least one row is inserted
            // PT : Execução e retorno verdadeiro se pelo menos uma linha for inserida
            return stmt.executeUpdate() > 0;
        }
    }

    // FR : Retourne tous les clients
    // EN : Return all clients
    // PT : Retorna todos os clientes
    public List<Client> findAll() throws Exception {

        // FR : Liste qui contiendra les résultats
        // EN : List that will contain the results
        // PT : Lista que conterá os resultados
        List<Client> clients = new ArrayList<>();

        // FR : Sélection de tous les clients, triés par nom puis prénom
        // EN : Selection of all clients, ordered by name then first name
        // PT : Seleção de todos os clientes, ordenados por nome e depois por primeiro nome
        String sql = "SELECT * FROM clients ORDER BY nom, prenom";

        // FR : Exécution de la requête et parcours des résultats
        // EN : Execution of the query and traversal of the results
        // PT : Execução da consulta e travessia dos resultados
        try (Statement stmt = connection.createStatement();
             ResultSet rs   = stmt.executeQuery(sql)) {

            // FR : Parcours de chaque ligne retournée
            // EN : Traversal of each returned row
            // PT : Travessia de cada linha retornada
            while (rs.next()) {

                // FR : Création d'un objet Client depuis les données de la ligne
                // EN : Creation of a Client object from the row data
                // PT : Criação de um objeto Client a partir dos dados da linha
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("email")
                ));
            }
        }
        return clients;
    }

    // FR : Retourne un client par son id
    // EN : Return a client by their id
    // PT : Retorna um cliente pelo seu id
    public Client findById(int id) throws Exception {

        // FR : Recherche du client avec l'id exact
        // EN : Search for the client with the exact id
        // PT : Busca do cliente com o id exato
        String sql = "SELECT * FROM clients WHERE id = ?";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de l'id dans la requête
            // EN : Injection of the id in the query
            // PT : Injeção do id na consulta
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            // FR : Si une ligne est trouvée, on retourne le client correspondant
            // EN : If a row is found, we return the corresponding client
            // PT : Se uma linha for encontrada, retornamos o cliente correspondente
            if (rs.next()) {
                return new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("email")
                );
            }
        }
        return null;
    }

    // FR : Retourne les clients dont le nom correspond à la recherche
    // EN : Return clients matching the given name
    // PT : Retorna clientes com o nome correspondente
    public List<Client> findByNom(String nom) throws Exception {

        // FR : Liste qui contiendra les résultats
        // EN : List that will contain the results
        // PT : Lista que conterá os resultados
        List<Client> clients = new ArrayList<>();

        // FR : Recherche des clients dont le nom contient la chaîne de recherche (case-insensitive)
        // EN : Search for clients whose name contains the search string (case-insensitive)
        // PT : Busca por clientes cujo nome contém a string de pesquisa (case-insensitive)
        String sql = "SELECT * FROM clients WHERE nom ILIKE ?";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection du nom avec les wildcards %nom%
            // EN : Injection of the name with the wildcards %nom%
            // PT : Injeção do nome com os curingas %nom%
            stmt.setString(1, "%" + nom + "%");
            ResultSet rs = stmt.executeQuery();

            // FR : Parcours de chaque client trouvé
            // EN : Parsing each found client
            // PT : Percorrendo cada cliente encontrado
            while (rs.next()) {
                clients.add(new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("email")
                ));
            }
        }
        return clients;
    }

    // FR : Supprime un client par son id
    // EN : Delete a client by their id
    // PT : Exclui um cliente pelo seu id
    public boolean delete(int id) throws Exception {

        // FR : Suppression du client avec l'id exact
        // EN : Deletion of the client with the exact id
        // PT : Exclusão do cliente com o id exato
        String sql = "DELETE FROM clients WHERE id = ?";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de l'id dans la requête
            // EN : Injection of the id in the query
            // PT : Injeção do id na consulta
            stmt.setInt(1, id);

            // FR : Retour vrai si au moins une ligne supprimée
            // EN : Return true if at least one row is deleted
            // PT : Retorna verdadeiro se ao menos uma linha for excluída
            return stmt.executeUpdate() > 0;
        }
    }
}