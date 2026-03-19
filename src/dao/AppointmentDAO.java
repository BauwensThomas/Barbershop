// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : AppointmentDAO.java
// PACKAGE : dao

// FR : Cette classe représente les opérations SQL pour la table appointments.
// EN : This class represents the SQL operations for the appointments table.
// PT : Esta classe representa as operacoes SQL para a tabela appointments.

// AUTHOR : Bauwens Thomas
// STATUS : DONE

package dao;

// FR : On importe les classes nécessaires pour gérer les rendez-vous et les connexions à la base de données
// EN : We import the necessary classes to manage appointments and database connections
// PT : Importamos as classes necessárias para gerenciar compromissos e conexões com o banco de dados
import model.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Statement;

// FR : Cette classe gère les opérations de création, lecture, mise à jour et suppression (CRUD) pour les rendez-vous.
// EN : This class manages the create, read, update and delete (CRUD) operations for appointments.
// PT : Esta classe gerencia as operações de criação, leitura, atualização e exclusão (CRUD) para compromissos.
public class AppointmentDAO {

    // FR : Connexion à la base de données
    // EN : Database connection
    // PT : Conexão com o banco de dados
    private Connection connection;

    // FR : Constructeur : reçoit la connexion active
    public AppointmentDAO(Connection connection) {
        this.connection = connection;
    }

    // FR : Enregistre un nouveau rendez-vous dans la base de données.
    // EN : Saves a new appointment to the database.
    // PT : Salva um novo compromisso no banco de dados.
    public boolean save(Appointment appt) throws Exception {

        // FR : Requête d'insertion avec les 4 champs du rendez-vous
        // EN : Insertion query with the 4 appointment fields
        // PT : Consulta de inserção com os 4 campos do compromisso
        String sql = "INSERT INTO appointments (client_id, service_id, date_rdv, heure_rdv) VALUES (?, ?, ?, ?)";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de l'identifiant du client
            // EN : Injection of the client ID
            // PT : Injeção do ID do cliente
            stmt.setInt(1, appt.getClientId());

            // FR : Injection de l'identifiant du service
            // EN : Injection of the service ID
            // PT : Injeção do ID do serviço
            stmt.setInt(2, appt.getServiceId());

            // FR : Injection de la date du rendez-vous (conversion LocalDate -> Date SQL)
            // EN : Injection of the appointment date (conversion LocalDate -> SQL Date)
            // PT : Injeção da data do compromisso (conversão LocalDate -> Data SQL)
            stmt.setDate(3, Date.valueOf(appt.getDateRdv()));

            // FR : Injection de l'heure du rendez-vous (conversion LocalTime -> Time SQL)
            // EN : Injection of the appointment time (conversion LocalTime -> SQL Time)
            // PT : Injeção da hora do compromisso (conversão LocalTime -> Hora SQL)
            stmt.setTime(4, Time.valueOf(appt.getHeureRdv()));

            // FR : Exécution et retour vrai si au moins une ligne insérée
            // EN : Execution and return true if at least one row inserted
            // PT : Execução e retorno verdadeiro se ao menos uma linha inserida
            return stmt.executeUpdate() > 0;
        }
    }

    // FR : Récupère tous les rendez-vous de la base de données.
    // EN : Retrieves all appointments from the database.
    // PT : Recupera todos os compromissos do banco de dados.
    public List<Appointment> findAll() throws Exception {

        // FR : Liste qui contiendra tous les rendez-vous
        // EN : List that will contain all appointments
        // PT : Lista que conterá todos os compromissos
        List<Appointment> list = new ArrayList<>();

        // FR : Sélection de tous les rendez-vous, triés par date puis par heure
        // EN : Selection of all appointments, sorted by date then by time
        // PT : Seleção de todos os compromissos, ordenados por data e depois por hora
        String sql = "SELECT * FROM appointments ORDER BY date_rdv, heure_rdv";

        // FR : Exécution de la requête et parcours des résultats
        // EN : Execution of the query and parsing of the results
        // PT : Execução da consulta e análise dos resultados
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // FR : Parcours de chaque ligne retournée
            // EN : Parsing of each returned row
            // PT : Análise de cada linha retornada
            while (rs.next()) {

                // FR : Création d'un objet Appointment depuis les données de la ligne
                // EN : Creation of an Appointment object from the row data
                // PT : Criação de um objeto Appointment a partir dos dados da linha
                Appointment appt = new Appointment(
                        rs.getInt("client_id"),
                        rs.getInt("service_id"),
                        rs.getDate("date_rdv").toLocalDate(),    // conversion Date SQL -> LocalDate
                        rs.getTime("heure_rdv").toLocalTime()    // conversion Time SQL -> LocalTime
                );

                // FR : Injection du statut récupéré depuis la base
                // EN : Injection of the status retrieved from the database
                // PT : Injeção do status recuperado do banco de dados
                appt.setStatut(rs.getString("statut"));

                // FR : Ajout du rendez-vous à la liste
                list.add(appt);
            }
        }

        // FR : Retour de la liste complète
        // EN : Return of the complete list
        // PT : Retorno da lista completa
        return list;
    }

    // FR : Récupère les rendez-vous d'une date spécifique.
    // EN : Retrieves appointments for a specific date.
    // PT : Recupera compromissos para uma data específica.
    public List<Appointment> findByDate(LocalDate date) throws Exception {

        // FR : Liste qui contiendra les rendez-vous de la date demandée
        // EN : List that will contain the appointments for the requested date
        // PT : Lista que conterá os compromissos para a data solicitada
        List<Appointment> list = new ArrayList<>();

        // FR : Filtrage par date, triés par heure croissante
        // EN : Filtering by date, sorted by time ascending
        // PT : Filtragem por data, ordenados por hora crescente
        String sql = "SELECT * FROM appointments WHERE date_rdv = ? ORDER BY heure_rdv";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de la date (conversion LocalDate -> Date SQL)
            // EN : Injection of the date (conversion LocalDate -> Date SQL)
            // PT : Injeção da data (conversão LocalDate -> Date SQL)
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();

            // FR : Parcours de chaque rendez-vous trouvé
            // EN : Parsing of each found appointment
            // PT : Análise de cada compromisso encontrado
            while (rs.next()) {
                Appointment appt = new Appointment(
                        rs.getInt("client_id"),
                        rs.getInt("service_id"),
                        rs.getDate("date_rdv").toLocalDate(),
                        rs.getTime("heure_rdv").toLocalTime()
                );

                // FR : Injection du statut
                // EN : Injection of the status
                // PT : Injeção do status
                appt.setStatut(rs.getString("statut"));
                list.add(appt);
            }
        }

        // FR : Retour de la liste des rendez-vous pour cette date
        // EN : Return of the list of appointments for this date
        // PT : Retorno da lista de compromissos para esta data
        return list;
    }

    // FR : Récupère les rendez-vous d'un client spécifique.
    // EN : Retrieves appointments for a specific client.
    // PT : Recupera compromissos para um cliente específico.
    public List<Appointment> findByClient(int clientId) throws Exception {

        // FR : Liste qui contiendra les rendez-vous du client
        // EN : List that will contain the appointments for the requested client
        // PT : Lista que conterá os compromissos para o cliente solicitado
        List<Appointment> list = new ArrayList<>();

        // FR : Filtrage par client, triés du plus récent au plus ancien
        // EN : Filtering by client, sorted from newest to oldest
        // PT : Filtragem por cliente, ordenados do mais recente ao mais antigo
        String sql = "SELECT * FROM appointments WHERE client_id = ? ORDER BY date_rdv DESC, heure_rdv DESC";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de l'identifiant du client
            // EN : Injection of the client identifier
            // PT : Injeção do identificador do cliente
            stmt.setInt(1, clientId);
            ResultSet rs = stmt.executeQuery();

            // FR : Parcours de chaque rendez-vous trouvé
            // EN : Parsing of each found appointment
            // PT : Análise de cada compromisso encontrado
            while (rs.next()) {
                Appointment appt = new Appointment(
                        rs.getInt("client_id"),
                        rs.getInt("service_id"),
                        rs.getDate("date_rdv").toLocalDate(),
                        rs.getTime("heure_rdv").toLocalTime()
                );

                // FR : Injection du statut
                // EN : Injection of the status
                // PT : Injeção do status
                appt.setStatut(rs.getString("statut"));
                list.add(appt);
            }
        }

        // FR : Retour de la liste des rendez-vous du client
        // EN : Return of the list of appointments for the requested client
        // PT : Retorno da lista de compromissos para o cliente solicitado
        return list;
    }

    // FR : Met à jour le statut d'un rendez-vous.
    // EN : Updates the status of an appointment.
    // PT : Atualiza o status de um compromisso.
    public boolean updateStatut(int id, String statut) throws Exception {

        // FR : Requête de mise à jour du statut pour un id donné
        // EN : Update query for the status for a given id
        // PT : Consulta de atualização do status para um id dado
        String sql = "UPDATE appointments SET statut = ? WHERE id = ?";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection du nouveau statut
            // EN : Injection of the new status
            // PT : Injeção do novo status
            stmt.setString(1, statut);

            // FR : Injection de l'identifiant du rendez-vous à modifier
            // EN : Injection of the identifier of the appointment to modify
            // PT : Injeção do identificador do compromisso a ser modificado
            stmt.setInt(2, id);

            // FR : Retour vrai si au moins une ligne modifiée
            // EN : Return true if at least one row is modified
            // PT : Retorno verdadeiro se pelo menos uma linha for modificada
            return stmt.executeUpdate() > 0;
        }
    }

    // FR : Supprime un rendez-vous de la base de données.
    // EN : Deletes an appointment from the database.
    // PT : Exclui um compromisso do banco de dados.
    public boolean delete(int id) throws Exception {

        // FR : Suppression du rendez-vous avec l'id exact
        // EN : Deletion of the appointment with the exact id
        // PT : Exclusão do compromisso com o id exato
        String sql = "DELETE FROM appointments WHERE id = ?";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de l'identifiant du rendez-vous à supprimer
            // EN : Injection of the identifier of the appointment to delete
            // PT : Injeção do identificador do compromisso a ser excluído
            stmt.setInt(1, id);

            // FR : Retour vrai si au moins une ligne supprimée
            // EN : Return true if at least one row is deleted
            // PT : Retorno verdadeiro se pelo menos uma linha for excluída
            return stmt.executeUpdate() > 0;
        }
    }

    // FR : Vérifie s'il existe un conflit de rendez-vous pour une date et une heure données.
    // EN : Checks if there is an appointment conflict for a given date and time.
    // PT : Verifica se existe um conflito de compromisso para uma data e hora específicas.
    public boolean hasConflict(LocalDate dateRdv, LocalTime heureRdv) throws Exception {

        // FR : Compte les rendez-vous confirmés au même créneau
        // EN : Counts the confirmed appointments at the same time slot
        // PT : Conta os compromissos confirmados no mesmo intervalo de tempo
        String sql = "SELECT COUNT(*) FROM appointments WHERE date_rdv = ? AND heure_rdv = ? AND statut = 'CONFIRME'";

        // FR : Préparation de la requête pour éviter les injections SQL
        // EN : Preparing the query to prevent SQL injections
        // PT : Preparando a consulta para evitar injeções de SQL
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            // FR : Injection de la date à vérifier
            // EN : Injection of the date to check
            // PT : Injeção da data a ser verificada
            stmt.setDate(1, Date.valueOf(dateRdv));

            // FR : Injection de l'heure à vérifier
            // EN : Injection of the time to check
            // PT : Injeção da hora a ser verificada
            stmt.setTime(2, Time.valueOf(heureRdv));

            // FR : Exécution de la requête et récupération du résultat
            // EN : Execution of the query and retrieval of the result
            // PT : Execução da consulta e recuperação do resultado
            ResultSet rs = stmt.executeQuery();

            // FR : Si le compteur est supérieur à 0, il y a un conflit
            // EN : If the counter is greater than 0, there is a conflict
            // PT : Se o contador for maior que 0, há um conflito
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }

        // Aucun conflit détecté
        // EN : No conflict detected
        // PT : Nenhum conflito detectado
        return false;
    }
}