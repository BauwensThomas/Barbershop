// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : Appointment.java
// PACKAGE : model

// FR : Cette classe représente un rendez-vous dans l'application.
// EN : This class represents an appointment in the application.
// PT : Esta classe representa um agendamento na aplicacao.

// AUTHOR : Bauwens Thomas
// STATUS : DONE

package model;

// FR : On importe les classes LocalDate et LocalTime pour gérer les dates et heures des rendez-vous.
// EN : We import the LocalDate and LocalTime classes to manage the dates and times of appointments.
// PT : Importamos as classes LocalDate e LocalTime para gerenciar as datas e horas
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

// FR : La classe Appointment contient les informations sur un rendez-vous, telles que l'identifiant du client, l'identifiant du service, la date et l'heure du rendez-vous, ainsi que le statut du rendez-vous (confirmé, annulé ou terminé).
// EN : The Appointment class contains information about an appointment, such as the client ID, service ID, appointment date and time, as well as the appointment status (confirmed, canceled, or completed).
// PT : A classe Appointment contém informações sobre um agendamento, como o ID do cliente, o ID do serviço, a data e hora do agendamento, bem como o status do agendamento (confirmado, cancelado ou concluído).
public class Appointment {
    private int id;
    private int clientId;
    private int serviceId;
    private LocalDate dateRdv;
    private LocalTime heureRdv;
    private String statut = "CONFIRME";
    private LocalDateTime createdAt;

    // FR : Constructeur pour créer un rendez-vous avec les informations nécessaires (clientId, serviceId, date et heure du rendez-vous). Le statut est initialisé à "CONFIRME" par défaut.
    // EN : Constructor to create an appointment with the necessary information (clientId, serviceId, appointment date and time). The status is initialized to "CONFIRME" by default.
    // PT : Construtor para criar um agendamento com as informações necessárias (clientId, serviceId, data e hora do agendamento). O status é inicializado como "CONFIRME" por padrão.
    public Appointment(int clientId, int serviceId, LocalDate dateRdv, LocalTime heureRdv) {
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.dateRdv = dateRdv;
        this.heureRdv = heureRdv;
    }

    // FR : Getters et setters pour accéder et modifier les informations du rendez-vous.
    // EN : Getters and setters to access and modify the appointment information.
    // PT : Getters e setters para acessar e modificar as informações do agendamento.
    public int getClientId() {
        return clientId;
    }

    // FR : Le setter pour le clientId est privé car l'identifiant du client ne doit pas être modifié après la création du rendez-vous.
    // EN : The setter for clientId is private because the client ID should not be modified after the appointment is created.
    // PT : O setter para clientId é privado porque o ID do cliente não deve ser modificado após a criação do agendamento.
    public int getId() {
        return id;
    }

    // FR : Le setter pour l'id est privé car l'id doit être géré par la base de données et ne doit pas être modifié manuellement.
    // EN : The setter for id is private because the id should be managed by the database and should not be modified manually.
    // PT : O setter para id é privado porque o id deve ser gerenciado pelo banco de dados e não deve ser modificado manualmente.
    public int getServiceId() {
        return serviceId;
    }

    // FR : Le setter pour le serviceId est privé car l'identifiant du service ne doit pas être modifié après la création du rendez-vous.
    // EN : The setter for serviceId is private because the service ID should not be modified after the appointment is created.
    // PT : O setter para serviceId é privado porque o ID do serviço não deve ser modificado após a criação do agendamento.
    public LocalDate getDateRdv() {
        return dateRdv;
    }

    // FR : Le setter pour la date du rendez-vous est privé car la date du rendez-vous ne doit pas être modifiée après sa création.
    // EN : The setter for the appointment date is private because the appointment date should not be modified after it is created.
    // PT : O setter para a data do agendamento é privado porque a data do agendamento não deve ser modificada após a criação.
    public LocalTime getHeureRdv() {
        return heureRdv;
    }

    // FR : Le setter pour l'heure du rendez-vous est privé car l'heure du rendez-vous ne doit pas être modifiée après sa création.
    // EN : The setter for the appointment time is private because the appointment time should not be modified after it is created.
    // PT : O setter para a hora do agendamento é privado porque a hora do agendamento não deve ser modificada após a criação.
    public String getStatut() {
        return statut;
    }

    // FR : Le setter pour le statut du rendez-vous permet de modifier le statut du rendez-vous (confirmé, annulé ou terminé). Il vérifie que le statut est valide avant de le modifier.
    // EN : The setter for the appointment status allows modifying the appointment status (confirmed, canceled, or completed). It checks that the status is valid before modifying it.
    // PT : O setter para o status do agendamento permite modificar o status do agendamento (confirmado, cancelado ou concluído). Ele verifica se o status é válido antes de modificá-lo.
    public void setStatut(String statut) {
        if (!statut.equals("CONFIRME") && !statut.equals("ANNULE") && !statut.equals("TERMINE")){
            throw new IllegalArgumentException("Statut invalide : " + statut);
        }
        this.statut = statut;
    }

    // FR : toString pour afficher les informations du rendez-vous de manière lisible.
    // EN : toString to display the appointment information in a readable way.
    // PT : toString para exibir as informações do agendamento de maneira legível.
    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", serviceId=" + serviceId +
                ", dateRdv=" + dateRdv +
                ", heureRdv=" + heureRdv +
                ", statut=" + statut +    // ← manquant
                '}';
    }
}
