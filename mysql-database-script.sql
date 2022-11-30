-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ATM
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ATM
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ATM` DEFAULT CHARACTER SET utf8 ;
USE `ATM` ;

-- -----------------------------------------------------
-- Table `ATM`.`Client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ATM`.`Client` (
  `client_id` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `balance` INT NULL,
  PRIMARY KEY (`client_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ATM`.`History`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ATM`.`History` (
  `history_id` INT NOT NULL AUTO_INCREMENT,
  `ct_id` INT NULL,
  `operation` VARCHAR(45) NULL,
  `date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`history_id`),
  INDEX `ct_id_idx` (`ct_id` ASC) ,
  CONSTRAINT `ct_id`
    FOREIGN KEY (`ct_id`)
    REFERENCES `ATM`.`Client` (`client_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
