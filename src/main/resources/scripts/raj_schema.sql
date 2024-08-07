-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: raj
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `body`
--

DROP TABLE IF EXISTS `body`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `body`
(
    `bodyid`         bigint                                                       NOT NULL AUTO_INCREMENT,
    `bodyimage`      varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT 'Name of the graphic file that represents the body in reports and web pages.',
    `name`           varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    `mission`        varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `bodyprecedence` double                                                       NOT NULL COMMENT 'Field for ordering in reports and web pages.  Stored as double to allow insertions of new bodies.',
    PRIMARY KEY (`bodyid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 53
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='An organizational unit at Mercy Ridge.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `office`
(
    `officeid`         bigint NOT NULL AUTO_INCREMENT,
    `title`            varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `officeprecedence` double                                                       DEFAULT NULL,
    `officebodyid`     bigint                                                       DEFAULT NULL,
    PRIMARY KEY (`officeid`),
    KEY `office_body_fk` (`officebodyid`),
    CONSTRAINT `office_body_fk` FOREIGN KEY (`officebodyid`) REFERENCES `body` (`bodyid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 151
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person`
(
    `personid`    bigint NOT NULL AUTO_INCREMENT,
    `first`       varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `last`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `email`       varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `phone`       varchar(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    `apt`         char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     DEFAULT NULL,
    `personimage` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`personid`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 179
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='A resident, staff member, or any other individual serving in some office at Mercy Ridge.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `pto`
--

DROP TABLE IF EXISTS `pto`;
/*!50001 DROP VIEW IF EXISTS `pto`*/;
SET @saved_cs_client = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `pto` AS
SELECT 1 AS `personid`,
       1 AS `first`,
       1 AS `last`,
       1 AS `email`,
       1 AS `phone`,
       1 AS `apt`,
       1 AS `start`,
       1 AS `end`,
       1 AS `ordinal`,
       1 AS `termpersonid`,
       1 AS `termofficeid`,
       1 AS `officeid`,
       1 AS `title`,
       1 AS `officeprecedence`,
       1 AS `officebodyid`,
       1 AS `bodyid`,
       1 AS `name`,
       1 AS `bodyprecedence`
        */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `term`
--

DROP TABLE IF EXISTS `term`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `term`
(
    `termpersonid` bigint NOT NULL,
    `termofficeid` bigint NOT NULL,
    `start`        date                                                     DEFAULT NULL,
    `end`          date                                                     DEFAULT NULL,
    `ordinal`      char(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`termpersonid`, `termofficeid`),
    KEY `term_office_fk2` (`termofficeid`),
    CONSTRAINT `term_office_fk2` FOREIGN KEY (`termofficeid`) REFERENCES `office` (`officeid`),
    CONSTRAINT `term_person_fk1` FOREIGN KEY (`termpersonid`) REFERENCES `person` (`personid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `pto`
--

/*!50001 DROP VIEW IF EXISTS `pto`*/;
/*!50001 SET @saved_cs_client = @@character_set_client */;
/*!50001 SET @saved_cs_results = @@character_set_results */;
/*!50001 SET @saved_col_connection = @@collation_connection */;
/*!50001 SET character_set_client = utf8mb4 */;
/*!50001 SET character_set_results = utf8mb4 */;
/*!50001 SET collation_connection = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM = UNDEFINED */ /*!50013 DEFINER =`rajadmin`@`%` SQL SECURITY DEFINER */ /*!50001 VIEW `pto` AS
select `person`.`personid`         AS `personid`,
       `person`.`first`            AS `first`,
       `person`.`last`             AS `last`,
       `person`.`email`            AS `email`,
       `person`.`phone`            AS `phone`,
       `person`.`apt`              AS `apt`,
       `term`.`start`              AS `start`,
       `term`.`end`                AS `end`,
       `term`.`ordinal`            AS `ordinal`,
       `term`.`termpersonid`       AS `termpersonid`,
       `term`.`termofficeid`       AS `termofficeid`,
       `office`.`officeid`         AS `officeid`,
       `office`.`title`            AS `title`,
       `office`.`officeprecedence` AS `officeprecedence`,
       `office`.`officebodyid`     AS `officebodyid`,
       `body`.`bodyid`             AS `bodyid`,
       `body`.`name`               AS `name`,
       `body`.`bodyprecedence`     AS `bodyprecedence`
from (((`term` join `office`) join `body`) join `person`)
where ((`office`.`officeid` = `term`.`termofficeid`) and
       (`body`.`bodyid` = `office`.`officebodyid`) and
       (`person`.`personid` = `term`.`termpersonid`))
order by `body`.`bodyprecedence`
        */;
/*!50001 SET character_set_client = @saved_cs_client */;
/*!50001 SET character_set_results = @saved_cs_results */;
/*!50001 SET collation_connection = @saved_col_connection */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 14:03:03
