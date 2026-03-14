-- Prject : Barber Shop Management System
-- FILE : database/schema.sql
-- FR : Script de création de la base de données
-- EN : Database schema creation script
-- PT : Script de criação do banco de dados

-- STATUS : In progress

-- Phase 1 : CREATE TABLE clients
CREATE TABLE clients (
    id          SERIAL          PRIMARY KEY,
    nom         VARCHAR(100)    NOT NULL,
    prenom      VARCHAR(100)    NOT NULL,
    telephone   VARCHAR(20)     NOT NULL UNIQUE,
    email       VARCHAR(150)    UNIQUE,
    created_at  TIMESTAMP       DEFAULT NOW()
);

-- Phase 1 : CREATE TABLE services
CREATE TABLE services (
    id              SERIAL          PRIMARY KEY,
    nom_service     VARCHAR(100)    NOT NULL UNIQUE,
    duree_minutes   INT             NOT NULL CHECK (duree_minutes > 0),
    prix            DECIMAL(6,2)    NOT NULL CHECK (prix >= 0)
);

-- Phase 1 : CREATE TABLE appointments
CREATE TABLE appointments (
    id          SERIAL      PRIMARY KEY,
    client_id   INT         NOT NULL,
    service_id  INT         NOT NULL,
    date_rdv    DATE        NOT NULL,
    heure_rdv   TIME        NOT NULL,
    statut      VARCHAR(20) DEFAULT 'CONFIRME' CHECK (statut IN ('CONFIRME', 'ANNULE', 'TERMINE')),
    created_at  TIMESTAMP   DEFAULT NOW(),
    FOREIGN KEY (client_id)  REFERENCES clients(id),
    FOREIGN KEY (service_id) REFERENCES services(id)
);

-- Phase 2 : INSERT INTO clients
INSERT INTO clients (nom, prenom, telephone, email) VALUES
('Dupont', 'Jean', '0612345678', 'jean.dupont@email.com'),
('Martin', 'Sophie', '0698765432', 'sophie.martin@email.com'),
('Silva', 'Carlos', '0611223344', 'carlos.silva@email.com'),
('Garcia', 'Maria', '0699556677', 'maria.garcia@email.com'),
('Smith', 'John', '0633445566', 'john.smith@email.com');

-- Phase 2 : INSERT INTO services
INSERT INTO services (nom_service, duree_minutes, prix) VALUES
('Coupe homme', 30, 15.00),
('Coupe + barbe', 45, 25.00),
('Barbe seule', 20, 10.00),
('Coupe enfant', 20, 10.00),
('Coupe femme', 60, 25.00);

-- Phase 2 : INSERT INTO appointments
INSERT INTO appointments (client_id, service_id, date_rdv, heure_rdv) VALUES
(1, 1, '2026-03-10', '10:00'),
(2, 2, '2026-03-10', '11:00'),
(3, 3, '2026-03-11', '14:30'),
(4, 4, '2026-03-12', '09:00'),
(5, 5, '2026-03-12', '15:00');