-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: roller_coaster
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `announcement`
--

DROP TABLE IF EXISTS `announcement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `announcement` (
  `announcement_id` int NOT NULL AUTO_INCREMENT,
  `date` time DEFAULT NULL,
  `text` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`announcement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcement`
--

LOCK TABLES `announcement` WRITE;
/*!40000 ALTER TABLE `announcement` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment` (
  `appointment_id` char(10) NOT NULL,
  `event_name` varchar(30) NOT NULL,
  `user_id` int NOT NULL,
  `valid_date` date NOT NULL,
  PRIMARY KEY (`appointment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment`
--

LOCK TABLES `appointment` WRITE;
/*!40000 ALTER TABLE `appointment` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `balance`
--

DROP TABLE IF EXISTS `balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balance` (
  `user_id` int NOT NULL,
  `balance` float DEFAULT '0',
  `quickPass` int NOT NULL DEFAULT '3',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `balance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `useraccount` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance`
--

LOCK TABLES `balance` WRITE;
/*!40000 ALTER TABLE `balance` DISABLE KEYS */;
/*!40000 ALTER TABLE `balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `comment_content` longtext,
  `comment_time` datetime DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_name` varchar(70) NOT NULL,
  `event_introduction` varchar(400) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `event_location` varchar(45) DEFAULT NULL,
  `event_remain_positions` int DEFAULT NULL,
  `event_image` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`event_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facility`
--

DROP TABLE IF EXISTS `facility`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facility` (
  `facility_name` varchar(70) NOT NULL,
  `facility_status` enum('repairing','normal') DEFAULT NULL,
  `facility_introduction` varchar(128) DEFAULT NULL,
  `facility_open_time` time DEFAULT NULL,
  `facility_close_time` time DEFAULT NULL,
  `queue_status` int DEFAULT NULL,
  `facility_image` varchar(500) DEFAULT NULL,
  `rating` float DEFAULT '3',
  `rating_people` int DEFAULT '1',
  PRIMARY KEY (`facility_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility`
--

LOCK TABLES `facility` WRITE;
/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
/*!40000 ALTER TABLE `facility` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password`
--

DROP TABLE IF EXISTS `password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password` (
  `user_id` int NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password`
--

LOCK TABLES `password` WRITE;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
/*!40000 ALTER TABLE `password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quickpass`
--

DROP TABLE IF EXISTS `quickpass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quickpass` (
  `quickpass_id` char(10) NOT NULL,
  `start_time` time DEFAULT NULL,
  `facility_name` varchar(70) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`quickpass_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quickpass`
--

LOCK TABLES `quickpass` WRITE;
/*!40000 ALTER TABLE `quickpass` DISABLE KEYS */;
/*!40000 ALTER TABLE `quickpass` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_ID` char(10) NOT NULL,
  `user_id` int NOT NULL,
  `status` enum('used','unused') NOT NULL,
  `price` float DEFAULT '0',
  `valid_date` date NOT NULL,
  `ticket_type` enum('adult','student','child') DEFAULT 'adult',
  PRIMARY KEY (`ticket_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Type`
--

DROP TABLE IF EXISTS `Type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Type` (
  `ticket_type` enum('adult','student','child','quickPass') NOT NULL,
  `ticket_price` float DEFAULT NULL,
  PRIMARY KEY (`ticket_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Type`
--

LOCK TABLES `Type` WRITE;
/*!40000 ALTER TABLE `Type` DISABLE KEYS */;
/*!40000 ALTER TABLE `Type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userAccount`
--

DROP TABLE IF EXISTS `userAccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userAccount` (
  `phone_number` varchar(11) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_gender` enum('male','female') DEFAULT NULL,
  `user_age` int DEFAULT NULL,
  `role` enum('visitor','manager') NOT NULL,
  `user_id` int NOT NULL AUTO_INCREMENT,
  `third_party_id` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  UNIQUE KEY `third_party_id_UNIQUE` (`third_party_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=866 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userAccount`
--

LOCK TABLES `userAccount` WRITE;
/*!40000 ALTER TABLE `userAccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `userAccount` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-26 23:25:55
