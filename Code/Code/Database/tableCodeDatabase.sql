-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema spacediv
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spacediv
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `spacediv` DEFAULT CHARACTER SET utf8 COLLATE utf8_estonian_ci ;
USE `spacediv` ;

-- -----------------------------------------------------
-- Table `spacediv`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spacediv`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NULL,
  `password` VARCHAR(100) NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `is_admin` TINYINT(1) NULL,
  `is_student` TINYINT(1) NULL,
  `is_super_admin` TINYINT(1) NULL,
  `is_sys_admin` TINYINT(1) NULL,
  `registered_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '		';


-- -----------------------------------------------------
-- Table `spacediv`.`apps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spacediv`.`apps` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `unique_url` VARCHAR(45) NULL,
  `registered_time` DATETIME NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`, `email`, `name`))
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `spacediv`.`institution`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spacediv`.`institution` (
  `institution_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `location` VARCHAR(45) NULL,
  `contact_name` VARCHAR(45) NULL,
  `contact_email` VARCHAR(45) NULL,
  `contact_phone_number` FLOAT NULL,
  PRIMARY KEY (`institution_id`),
  INDEX `super_user_idx` (`contact_email` ASC, `contact_name` ASC) VISIBLE,
  CONSTRAINT `super_user`
    FOREIGN KEY (`contact_email` , `contact_name`)
    REFERENCES `spacediv`.`apps` (`email` , `name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '	';


-- -----------------------------------------------------
-- Table `spacediv`.`admin_app`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spacediv`.`admin_app` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `admin_id` FLOAT NULL,
  `is_enabled_flag` TINYINT(1) NULL,
  `registered_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spacediv`.`user_app`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spacediv`.`user_app` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NULL,
  `is_enabled_flag` TINYINT(1) NULL,
  `registered_date` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `spacediv`.`user_admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `spacediv`.`user_admin` (
  `admin_id` INT NOT NULL,
  `user_id` INT NULL,
  `is_enabled_flag` TINYINT(1) NULL,
  `registered_date` DATETIME NULL,
  PRIMARY KEY (`admin_id`))
ENGINE = InnoDB
COMMENT = '	';

CREATE USER 'admin' IDENTIFIED BY '1234';

GRANT SELECT, INSERT, TRIGGER ON TABLE `spacediv`.* TO 'admin';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spacediv`.* TO 'admin';
GRANT EXECUTE ON ROUTINE `spacediv`.* TO 'admin';
CREATE USER 'super_user' IDENTIFIED BY '1234';

GRANT SELECT, INSERT, TRIGGER ON TABLE `spacediv`.* TO 'super_user';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spacediv`.* TO 'super_user';
GRANT EXECUTE ON ROUTINE `spacediv`.* TO 'super_user';
CREATE USER 'super_admin' IDENTIFIED BY '1234';

GRANT SELECT, INSERT, TRIGGER ON TABLE `spacediv`.* TO 'super_admin';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `spacediv`.* TO 'super_admin';
GRANT SELECT ON TABLE `spacediv`.* TO 'super_admin';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
