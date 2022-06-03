CREATE TABLE `role` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
     PRIMARY KEY (`id`)
);


CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `attivo` bit(1) DEFAULT NULL,
    `email` varchar(255) DEFAULT NULL,
    `nome` varchar(255) DEFAULT NULL,
    `cognome` varchar(255) DEFAULT NULL,
    `data_creazione` datetime(6) DEFAULT NULL,
    `data_modifica` datetime(6) DEFAULT NULL,
     PRIMARY KEY (`id`)
);

CREATE TABLE `user_role` (
    `utente_id` int NOT NULL,
    `ruolo_id` int NOT NULL,
     PRIMARY KEY (`utente_id`,`ruolo_id`),
    KEY `fk_utenti_ruolo` (`ruolo_id`),
    CONSTRAINT `FKh1v9q2iyl9ui2qrrpv5h1gslk` FOREIGN KEY (`utente_id`) REFERENCES `user` (`id`),
    CONSTRAINT `fk_utenti_ruolo` FOREIGN KEY (`ruolo_id`) REFERENCES `role` (`id`)
    );



INSERT INTO `role`
(id , name)
VALUES
    (1,'SUPERVISOR'),
    (2,'ADMIN'),
    (3,'USER');