CREATE TABLE `category` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `attivo` bit(1) DEFAULT NULL,
    `data_creazione` datetime(6) DEFAULT NULL,
    `data_modifica` datetime(6) DEFAULT NULL,
     PRIMARY KEY (`id`)
);