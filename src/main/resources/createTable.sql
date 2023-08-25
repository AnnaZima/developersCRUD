-- liquibase formatted sql

CREATE TABLE specialty (
    id INT AUTO_INCREMENT PRIMARY KEY,
    spec_name VARCHAR(255) NOT NULL);

CREATE TABLE developers (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(255) DEFAULT NULL,
  last_name VARCHAR(255) DEFAULT NULL,
  specialty_id INT DEFAULT NULL,
  status VARCHAR(255) DEFAULT 'ACTIVE',
  FOREIGN KEY (specialty_id) REFERENCES specialty (id)
);

CREATE TABLE skills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(255) NOT NULL);

CREATE TABLE skill_set (
    developer_id INT NOT NULL,
    skill_id INT NOT NULL,
    FOREIGN KEY (developer_id) REFERENCES developers(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id),
    UNIQUE (developer_id, skill_id)
     );



