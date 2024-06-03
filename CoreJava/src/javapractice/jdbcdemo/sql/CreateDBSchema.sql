CREATE DATABASE mysql_jdbc;

USE mysql_jdbc;

CREATE TABLE candidate (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL,
  dob date NOT NULL,
  phone varchar(20) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE skill (
  id int NOT NULL AUTO_INCREMENT,
  skill_name varchar(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE candidate_skill (
  candidate_id int NOT NULL,
  skill_id int NOT NULL,
  PRIMARY KEY (candidate_id,skill_id),
  KEY skill_id (skill_id),
  CONSTRAINT fk_candidate_skill_candidate FOREIGN KEY (candidate_id) REFERENCES candidate (id),
  CONSTRAINT fk_candidate_skill_skill FOREIGN KEY (skill_id) REFERENCES skill (id)
);

DELIMITER $$

CREATE PROCEDURE get_candidate_skill(IN candidate_id INT)
BEGIN
	SELECT candidate.id, first_name, last_name, skill.skill_name AS skill 
	FROM candidate
	INNER JOIN candidate_skill ON candidate.id = candidate_skill.candidate_id
	INNER JOIN skill ON skill.id = candidate_skill.skill_id
	WHERE candidate.id = candidate_id;
END $$

DELIMITER ;