DROP SCHEMA IF EXISTS `phraseupdb`;

CREATE SCHEMA `phraseupdb` CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `phraseupdb`;

-- -----------------------------------------------------
-- Table `phraseupdb`.`users`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `phraseupdb`.`users`;

CREATE TABLE `users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL,
  `language` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;


-- -----------------------------------------------------
-- Table `phraseupdb`.`chats`
-- -----------------------------------------------------

DROP TABLE IF EXISTS`phraseupdb`.`chats`;

CREATE  TABLE IF NOT EXISTS `phraseupdb`.`chats` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `language` varchar(30) NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
)
ENGINE = InnoDB AUTO_INCREMENT=1;

-- -----------------------------------------------------
-- Table `phraseupdb`.`chat_line`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `phraseupdb`.`messages`;

CREATE  TABLE IF NOT EXISTS `phraseupdb`.`messages` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `chat_id` INT UNSIGNED NOT NULL ,
  `sender_id` INT UNSIGNED NOT NULL ,
  `text` TEXT NOT NULL ,
  `sent_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `active_for` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) ,
  KEY `fk_chat_id` (`chat_id`),
  CONSTRAINT `fk_chat_id` FOREIGN KEY (`chat_id`) REFERENCES `chats` (`id`),
  KEY `fk_sender_id` (`sender_id`),
  CONSTRAINT `fk_sender_id` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`)
)
ENGINE = InnoDB auto_increment=1;
