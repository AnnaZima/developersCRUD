-- liquibase formatted sql

-- changeset liquibase:1

CREATE TABLE `developers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `specialty_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  KEY `specialty_id_idx` (`specialty_id`),
  CONSTRAINT `specialty_id` FOREIGN KEY (`specialty_id`) REFERENCES `specialty` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

