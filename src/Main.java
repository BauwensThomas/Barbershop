// Project : BarberShop - Gestion des rendez-vous d'un salon de coiffure

// FILE : Main.java
// PACKAGE : main

// FR : Cette classe représente le point d'entrée de l'application.
// EN : This class represents the entry point of the application.
// PT : Esta classe representa o ponto de entrada da aplicacao.

// AUTHOR : Bauwens Thomas
// STATUS : IN_PROGRESS

import dao.DatabaseConnection;
import java.sql.Connection;

public class Main {
        public static void main(String[] args) {
                System.out.println("BarberShop v1.0 - Starting application...");
         
                try {
                        // FR : Test de la connexion à la base de données
                        // EN : Testing database connection
                        // PT : Testando a conexao com o banco de dados
                        Connection c = DatabaseConnection.getConnection();
                        System.out.println("Connection OK : " + !c.isClosed());
                } catch (Exception e) {
                        System.err.println("Erreur de connexion : " + e.getMessage());
                }
        }
}