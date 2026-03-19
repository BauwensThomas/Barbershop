// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : Service.java
// PACKAGE : model

// FR : Cette classe représente un service de l'application.
// EN : This class represents a service in the application.
// PT : Esta classe representa um servico na aplicacao.

// AUTHOR : Bauwens Thomas
// STATUS : DONE

package model;

// FR : La classe Service contient les informations sur un service proposé par le salon de coiffure, telles que son nom, sa durée en minutes et son prix.
// EN : The Service class contains information about a service offered by the hair salon, such as its name, duration in minutes, and price.
// PT : A classe Service contém informações sobre um serviço oferecido pelo salão de cabeleireiro, como seu nome, duração em minutos e preço.
public class Service {
     private int id;
     private String nomService;
     private int dureeMinutes;
     private double prix;

    // FR : Constructeur de la classe Service sans l'id, utilisé pour créer un nouveau service avant de l'insérer dans la base de données.
    // EN : Constructor of the Service class without the id, used to create a new service before inserting it into the database.
    // PT : Construtor da classe Service sem o id, usado para criar um novo serviço antes de inseri-lo no banco de dados.
     public Service(String nomService, int dureeMinutes, double prix) {
        this.nomService = nomService;
        this.dureeMinutes = dureeMinutes;
        this.prix = prix;
    }

    // FR : Constructeur de la classe Service avec l'id, utilisé pour créer un objet Service à partir des données récupérées de la base de données.
    // EN : Constructor of the Service class with the id, used to create a Service object from the data retrieved from the database.
    // PT : Construtor da classe Service com o id, usado para criar um objeto Service a partir dos dados recuperados do banco de dados.
    public Service(int id, String nomService, int dureeMinutes, double prix) {
        this.id = id;
        this.nomService = nomService;
        this.dureeMinutes = dureeMinutes;
        this.prix = prix;
    }

    // FR : Getters pour les propriétés de la classe Service (les setters ne sont pas nécessaires car les propriétés sont définies lors de la création de l'objet et ne sont pas modifiées par la suite).
    // EN : Getters for the properties of the Service class (setters are not necessary because the properties are defined when the object is created and are not modified afterwards).
    // PT : Getters para as propriedades da classe Service (os setters não são necessários porque as propriedades são definidas quando o objeto é criado e não são modificadas posteriormente).
    public int getId() {
        return id;
    }

    // FR : Getters pour les propriétés de la classe Service (les setters ne sont pas nécessaires car les propriétés sont définies lors de la création de l'objet et ne sont pas modifiées par la suite).
    // EN : Getters for the properties of the Service class (setters are not necessary because the properties are defined when the object is created and are not modified afterwards).
    // PT : Getters para as propriedades da classe Service (os setters não são necessários porque as propriedades são definidas quando o objeto é criado e não são modificadas posteriormente).
    public String getNomService() {
        return nomService;
    }

    // FR : Getters pour les propriétés de la classe Service (les setters ne sont pas nécessaires car les propriétés sont définies lors de la création de l'objet et ne sont pas modifiées par la suite).
    // EN : Getters for the properties of the Service class (setters are not necessary because the properties are defined when the object is created and are not modified afterwards).
    // PT : Getters para as propriedades da classe Service (os setters não são necessários porque as propriedades são definidas quando o objeto é criado e não são modificadas posteriormente).
    public int getDureeMinutes() {
        return dureeMinutes;
    }

    // FR : Getters pour les propriétés de la classe Service (les setters ne sont pas nécessaires car les propriétés sont définies lors de la création de l'objet et ne sont pas modifiées par la suite).
    // EN : Getters for the properties of the Service class (setters are not necessary because the properties are defined when the object is created and are not modified afterwards).
    // PT : Getters para as propriedades da classe Service (os setters não são necessários porque as propriedades são definidas quando o objeto é criado e não são modificadas posteriormente).
    public double getPrix() {
        return prix;
    }

    // FR : toString pour afficher les informations d'un service de manière lisible.
    // EN : toString to display the information of a service in a readable way.
    // PT : toString para exibir as informações de um serviço de maneira legível.
    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", nomService='" + nomService + '\'' +
                ", dureeMinutes=" + dureeMinutes +
                ", prix=" + prix +
                '}';
    }
}
