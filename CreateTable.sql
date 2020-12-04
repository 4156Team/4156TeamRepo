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
  `date` date DEFAULT NULL,
  `text` varchar(700) DEFAULT NULL,
  PRIMARY KEY (`announcement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=480 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance`
--

LOCK TABLES `balance` WRITE;
/*!40000 ALTER TABLE `balance` DISABLE KEYS */;
INSERT INTO `balance` VALUES (1336,0,3),(1393,0,3),(1453,0,3),(1693,0,3),(1757,0,3),(1803,0,3),(1812,124,3),(1815,0,3),(1817,10,6),(1845,0,3),(1847,124,3),(1862,0,3),(1863,0,3),(1864,100,3),(1869,124,3),(1872,0,3),(1881,10,6),(1909,0,3),(1911,124,3),(1926,0,3),(1927,0,3),(1928,100,3),(1933,124,3),(1936,0,3),(1945,10,6),(1973,0,3),(1975,124,3),(1990,0,3),(1991,0,3),(1992,100,3),(1997,124,3),(2000,0,3),(2009,10,6),(2040,0,3),(2042,124,3),(2052,124,3),(2055,0,3),(2067,0,3),(2068,0,3),(2069,100,3),(2073,10,6),(2104,0,3),(2106,124,3),(2116,124,3),(2119,0,3),(2131,0,3),(2132,0,3),(2133,100,3),(2137,10,6),(2168,0,3),(2170,124,3),(2180,124,3),(2183,0,3),(2195,0,3),(2196,0,3),(2197,100,3),(2201,10,6),(2232,0,3),(2234,124,3),(2244,124,3),(2247,0,3),(2259,0,3),(2260,0,3),(2261,100,3),(2265,10,6),(2296,0,3),(2298,124,3),(2308,124,3),(2311,0,3),(2323,0,3),(2324,0,3),(2325,100,3),(2329,10,6),(2360,0,3),(2362,124,3),(2372,124,3),(2375,0,3),(2387,0,3),(2388,0,3),(2389,100,3),(2393,10,6),(2424,0,3),(2426,124,3),(2436,124,3),(2439,0,3),(2451,0,3),(2452,0,3),(2453,100,3),(2457,10,6),(2488,0,3),(2490,124,3),(2500,124,3),(2503,0,3),(2515,0,3),(2516,0,3),(2517,100,3),(2521,10,6),(2552,0,3),(2554,124,3),(2564,124,3),(2567,0,3),(2579,0,3),(2580,0,3),(2581,100,3),(2585,10,6),(2616,0,3),(2618,124,3),(2628,124,3),(2631,0,3),(2643,0,3),(2644,0,3),(2645,100,3),(2649,10,6),(2680,0,3),(2682,124,3),(2692,124,3),(2695,0,3),(2707,0,3),(2708,0,3),(2709,100,3),(2713,10,6),(2744,0,3),(2746,124,3),(2756,124,3),(2759,0,3),(2771,0,3),(2772,0,3),(2773,100,3),(2777,10,6),(2808,0,3),(2810,124,3),(2820,124,3),(2823,0,3),(2835,0,3),(2836,0,3),(2837,100,3),(2841,10,6),(2872,0,3),(2874,124,3),(2884,124,3),(2887,0,3),(2899,0,3),(2900,0,3),(2901,100,3),(2905,10,6),(2936,0,3),(2938,124,3),(2948,124,3),(2951,0,3),(2963,0,3),(2964,0,3),(2965,100,3),(2969,10,6),(3000,0,3),(3002,124,3),(3012,124,3),(3015,0,3),(3027,0,3),(3028,0,3),(3029,100,3),(3033,10,6),(3064,0,3),(3066,124,3),(3076,124,3),(3079,0,3),(3091,0,3),(3092,0,3),(3093,100,3),(3097,10,6),(3128,0,3),(3130,124,3),(3140,124,3),(3143,0,3),(3155,0,3),(3156,0,3),(3157,100,3),(3161,10,6),(3192,0,3),(3194,124,3),(3204,124,3),(3207,0,3),(3219,0,3),(3220,0,3),(3221,100,3),(3225,10,6),(3244,0,3),(3256,0,3),(3258,124,3),(3268,124,3),(3271,0,3),(3283,0,3),(3284,0,3),(3285,100,3),(3313,0,3),(3339,0,3),(3341,124,3),(3351,124,3),(3354,0,3),(3366,0,3),(3367,0,3),(3368,100,3),(3392,0,3),(3418,0,3),(3420,124,3),(3430,124,3),(3433,0,3),(3446,0,3),(3447,0,3),(3448,100,3),(3472,0,3),(3498,0,3),(3500,124,3),(3510,124,3),(3513,0,3),(3526,0,3),(3527,0,3),(3528,100,3),(3552,0,3),(3593,0,3),(3594,124,3),(3601,0,3),(3625,0,3),(3627,0,3),(3628,100,3),(3652,0,3),(3693,0,3),(3694,124,3),(3701,0,3),(3725,0,3),(3727,0,3),(3728,100,3),(3759,0,3),(3778,0,3),(3787,0,3),(3788,100,3),(3795,0,3),(3796,124,3),(3803,0,3),(3866,0,3),(3875,0,3),(3877,124,3),(3888,0,3),(3895,0,3),(3896,100,3),(3903,0,3),(3904,124,3),(3911,0,3),(3966,0,3),(3975,0,3),(3977,124,3),(3988,0,3),(3995,0,3),(3996,100,3),(4003,0,3),(4004,124,3),(4011,0,3),(4083,0,3),(4092,0,3),(4094,124,3),(4111,0,3),(4112,0,3),(4113,100,3),(4120,0,3),(4121,124,3),(4128,0,3),(4183,0,3),(4192,0,3),(4194,124,3),(4211,0,3),(4212,0,3),(4213,100,3),(4220,0,3),(4221,124,3),(4228,0,3),(4286,0,3),(4295,0,3),(4297,124,3),(4314,0,3),(4315,0,3),(4316,100,3),(4323,0,3),(4324,124,3),(4331,0,3),(4397,0,3),(4406,0,3),(4408,124,3),(4425,0,3),(4426,0,3),(4427,100,3),(4434,0,3),(4435,124,3),(4442,0,3),(4497,0,3),(4506,0,3),(4508,124,3),(4525,0,3),(4526,0,3),(4527,100,3),(4534,0,3),(4535,124,3),(4542,0,3),(4595,0,3),(4637,0,3),(4646,0,3),(4664,0,3),(4665,124,3),(4727,0,3),(4736,0,3),(4738,124,3),(4755,0,3),(4756,0,3),(4757,100,3),(4764,0,3),(4765,124,3),(4772,0,3),(4820,0,3),(4861,0,3),(4862,124,3),(4869,0,3),(4894,0,3),(4895,0,3),(4896,100,3),(4920,0,3),(4961,0,3),(4962,124,3),(4969,0,3),(4994,0,3),(4995,0,3),(4996,100,3),(5005,0,3),(5007,124,3),(5037,0,3),(5046,0,3),(5048,124,3),(5065,0,3),(5066,0,3),(5067,100,3),(5074,0,3),(5075,124,3),(5082,0,3),(5130,0,3),(5157,0,3),(5159,124,3),(5171,0,3),(5172,124,3),(5179,0,3),(5204,0,3),(5205,0,3),(5206,100,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,'Alice','test comment','2020-11-26 18:01:06'),(2,1,'Alice','test comment','2020-11-26 18:02:40'),(6,1,'Alice','test comment','2020-11-26 18:27:44'),(8,1,'Alice','test comment','2020-11-26 18:28:24'),(10,1,'Alice','test comment','2020-11-26 18:28:53'),(12,1,'Alice','test comment','2020-11-26 18:29:49'),(25,1,'Alice','test comment','2020-11-26 18:47:30'),(28,1,'Alice','test comment','2020-11-26 18:57:54'),(31,1,'Alice','test comment','2020-11-26 19:00:10'),(32,3,'Aria','comment controller test','2020-11-26 19:31:15'),(40,1,'Alice','test comment','2020-11-26 20:14:23'),(41,3,'Mike','test comment','2020-11-26 21:16:12'),(46,3,'Mike','test comment','2020-11-27 19:50:03'),(47,1,'Alice','test comment','2020-11-27 19:50:03'),(51,3,'Mike','test comment','2020-11-27 19:50:29'),(52,1,'Alice','test comment','2020-11-27 19:50:29'),(56,3,'Mike','test comment','2020-11-27 19:56:53'),(57,1,'Alice','test comment','2020-11-27 19:56:53'),(61,3,'Mike','test comment','2020-11-27 20:04:06'),(62,1,'Alice','test comment','2020-11-27 20:04:06'),(66,3,'Mike','test comment','2020-11-27 20:09:55'),(67,1,'Alice','test comment','2020-11-27 20:09:55'),(71,3,'Mike','test comment','2020-11-28 15:48:02'),(72,1,'Alice','test comment','2020-11-28 15:48:02'),(76,3,'Mike','test comment','2020-11-28 18:16:03'),(77,1,'Alice','test comment','2020-11-28 18:16:03'),(81,3,'Mike','test comment','2020-11-28 18:17:49'),(82,1,'Alice','test comment','2020-11-28 18:17:49'),(86,3,'Mike','test comment','2020-11-28 18:18:15'),(87,1,'Alice','test comment','2020-11-28 18:18:15'),(91,3,'Mike','test comment','2020-11-28 18:41:46'),(92,1,'Alice','test comment','2020-11-28 18:41:46'),(96,3,'Mike','test comment','2020-11-28 18:58:50'),(97,1,'Alice','test comment','2020-11-28 18:58:50'),(101,3,'Mike','test comment','2020-12-01 11:43:02'),(102,1,'Alice','test comment','2020-12-01 11:43:02'),(106,3,'Mike','test comment','2020-12-01 11:44:52'),(107,1,'Alice','test comment','2020-12-01 11:44:52'),(111,3,'Mike','test comment','2020-12-01 17:29:49'),(112,1,'Alice','test comment','2020-12-01 17:29:49'),(116,3,'Mike','test comment','2020-12-01 17:30:57'),(117,1,'Alice','test comment','2020-12-01 17:30:57'),(121,3,'Mike','test comment','2020-12-01 17:47:44'),(122,1,'Alice','test comment','2020-12-01 17:47:44'),(126,3,'Mike','test comment','2020-12-01 17:48:09'),(127,1,'Alice','test comment','2020-12-01 17:48:09'),(131,3,'Mike','test comment','2020-12-01 18:13:17'),(132,1,'Alice','test comment','2020-12-01 18:13:17'),(136,3,'Mike','test comment','2020-12-01 18:14:19'),(137,1,'Alice','test comment','2020-12-01 18:14:19'),(141,3,'Mike','test comment','2020-12-01 19:14:32'),(142,1,'Alice','test comment','2020-12-01 19:14:32'),(146,3,'Mike','test comment','2020-12-03 00:46:09'),(147,1,'Alice','test comment','2020-12-03 00:46:09'),(151,3,'Mike','test comment','2020-12-03 16:13:30'),(152,1,'Alice','test comment','2020-12-03 16:13:30'),(156,3,'Mike','test comment','2020-12-03 16:15:08'),(157,1,'Alice','test comment','2020-12-03 16:15:08'),(161,3,'Mike','test comment','2020-12-03 16:34:05'),(162,1,'Alice','test comment','2020-12-03 16:34:05'),(166,3,'Mike','test comment','2020-12-03 16:56:22'),(167,1,'Alice','test comment','2020-12-03 16:56:22'),(171,3,'Mike','test comment','2020-12-03 19:14:31'),(172,1,'Alice','test comment','2020-12-03 19:14:31'),(176,3,'Mike','test comment','2020-12-03 19:37:05'),(177,1,'Alice','test comment','2020-12-03 19:37:05'),(181,3,'Mike','test comment','2020-12-03 19:39:34'),(182,1,'Alice','test comment','2020-12-03 19:39:34'),(186,3,'Mike','test comment','2020-12-03 19:48:57'),(187,1,'Alice','test comment','2020-12-03 19:48:57'),(191,3,'Mike','test comment','2020-12-03 19:53:20'),(192,1,'Alice','test comment','2020-12-03 19:53:20'),(196,3,'Mike','test comment','2020-12-03 19:59:08'),(197,1,'Alice','test comment','2020-12-03 19:59:08'),(201,3,'Mike','test comment','2020-12-03 20:23:03'),(202,1,'Alice','test comment','2020-12-03 20:23:03'),(206,3,'Mike','test comment','2020-12-03 20:28:28'),(207,1,'Alice','test comment','2020-12-03 20:28:28'),(211,3,'Mike','test comment','2020-12-03 20:30:27'),(212,1,'Alice','test comment','2020-12-03 20:30:27'),(216,3,'Mike','test comment','2020-12-03 23:30:33'),(217,1,'Alice','test comment','2020-12-03 23:30:34'),(221,3,'Mike','test comment','2020-12-03 23:34:02'),(222,1,'Alice','test comment','2020-12-03 23:34:02'),(226,3,'Mike','test comment','2020-12-03 23:37:55'),(227,1,'Alice','test comment','2020-12-03 23:37:55'),(231,3,'Mike','test comment','2020-12-03 23:40:22'),(232,1,'Alice','test comment','2020-12-03 23:40:22'),(236,3,'Mike','test comment','2020-12-03 23:42:20'),(237,1,'Alice','test comment','2020-12-03 23:42:20'),(241,3,'Mike','test comment','2020-12-03 23:53:24'),(242,1,'Alice','test comment','2020-12-03 23:53:24'),(246,3,'Mike','test comment','2020-12-04 00:00:18'),(247,1,'Alice','test comment','2020-12-04 00:00:18'),(251,3,'Mike','test comment','2020-12-04 00:38:43'),(252,1,'Alice','test comment','2020-12-04 00:38:43'),(256,3,'Mike','test comment','2020-12-04 00:41:32'),(257,1,'Alice','test comment','2020-12-04 00:41:32'),(261,3,'Mike','test comment','2020-12-04 10:40:21'),(262,1,'Alice','test comment','2020-12-04 10:40:21'),(266,3,'Mike','test comment','2020-12-04 10:47:41'),(267,1,'Alice','test comment','2020-12-04 10:47:41'),(271,3,'Mike','test comment','2020-12-04 18:14:31'),(272,1,'Alice','test comment','2020-12-04 18:14:31'),(276,3,'Mike','test comment','2020-12-04 18:16:45'),(277,1,'Alice','test comment','2020-12-04 18:16:45'),(281,3,'Mike','test comment','2020-12-04 18:20:21'),(282,1,'Alice','test comment','2020-12-04 18:20:21'),(286,3,'Mike','test comment','2020-12-04 18:22:30'),(287,1,'Alice','test comment','2020-12-04 18:22:30'),(291,3,'Mike','test comment','2020-12-04 18:23:41'),(292,1,'Alice','test comment','2020-12-04 18:23:42'),(296,3,'Mike','test comment','2020-12-04 19:30:29'),(297,1,'Alice','test comment','2020-12-04 19:30:29'),(301,3,'Mike','test comment','2020-12-04 19:56:25'),(302,1,'Alice','test comment','2020-12-04 19:56:25'),(306,3,'Mike','test comment','2020-12-04 19:58:10'),(307,1,'Alice','test comment','2020-12-04 19:58:10'),(311,3,'Mike','test comment','2020-12-04 20:03:02'),(312,1,'Alice','test comment','2020-12-04 20:03:02'),(316,3,'Mike','test comment','2020-12-04 20:04:51'),(317,1,'Alice','test comment','2020-12-04 20:04:51'),(321,3,'Mike','test comment','2020-12-04 20:15:37'),(322,1,'Alice','test comment','2020-12-04 20:15:37'),(327,3,'Mike','test comment','2020-12-04 20:24:04'),(328,1,'Alice','test comment','2020-12-04 20:24:04'),(332,3,'Mike','test comment','2020-12-04 20:24:51'),(333,1,'Alice','test comment','2020-12-04 20:24:51'),(337,3,'Mike','test comment','2020-12-04 20:28:16'),(338,1,'Alice','test comment','2020-12-04 20:28:16'),(342,3,'Mike','test comment','2020-12-04 20:31:08'),(343,1,'Alice','test comment','2020-12-04 20:31:08'),(347,3,'Mike','test comment','2020-12-04 20:38:36'),(348,1,'Alice','test comment','2020-12-04 20:38:36'),(352,3,'Mike','test comment','2020-12-04 20:40:57'),(353,1,'Alice','test comment','2020-12-04 20:40:57');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES ('Adventurous Friends Exploration','Adventure Isle comes to life when the Arbori Musicians, Chip ‘n’ Dale and their adventurous friends explore the far reaches of Adventure Isle.','10:00:00','21:00:00','Adventure Isle',1000,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-adventurous-friends-exploration-hero.jpg'),('Avengers Assemble at the E-Stage','Throughout the day, this dynamic group of super heroes assembles on the Pepsi E-Stage, creating a perfect opportunity to capture a heroic photo.','10:00:00','21:00:00','Tomorrowland',1000,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-avengers-assemble-at-the-e-stage-hero-new.jpg'),('Avengers Training Initiative','Think you have what it takes to be one of Earth’s Mightiest Heroes? Members of the Avengers and their field agents will be assembling prospective recruits at the Pepsi E-Stage in Tomorrowland for this heroic interactive experience.','10:05:00','21:00:00','Tomorrowland',1000,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-avengers-training-initiative-hero-v2.jpg'),('Bai Ling Storytelling','One troupe of storytellers will share a beloved Disney Story with humor, songs, and puppetry.','10:00:00','21:00:00','Fantasyland',1000,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-bai-ling-storytelling-hero.jpg'),('Eye of the Storm: Captain Jack’s Stunt Spectacular','Catch Captain Jack in action—with high-energy stunts, side-splitting comedy and a swashbuckling finale you’ve got to see to believe!','11:10:00','21:00:00','Treasure Cove',1000,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-eye-of-storm-captain-jacks-spectacular-hero-new.jpg'),('Fantasy Festival','Approach the day with wonder and joy in a special dance with our Disney friends and traveling players.','10:00:00','21:00:00','Fantasyland',1000,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-fantasy-festival-hero.jpg'),('Frozen: A Sing-Along Celebration','Become part of the music and magic as you join Anna, Elsa, Kristoff, and Olaf for an interactive performance where you get to sing along.','11:30:00','21:00:00','Fantasyland',1000,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-frozen-sing-along-celebration-hero-formal-v1.jpg'),('Ignite the Dream - A Nighttime Spectacular of Magic and Light','Ignite the Dream - A Nighttime Spectacular of Magic and Light is currently in trial operations. During this period we may make operational adjustments, and appreciate your understanding and cooperation.\nSee Enchanted Storybook Castle transformed when Disney magic lights up the night with stunning special effects!','20:00:00','21:00:00','Gardens of Imagination',1000,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-ignite-dream-nighttime-spectacular-hero-new-again.jpg'),('Mickey’s Storybook Express','Mickey’s Storybook Express is currently in trial operations. During this period we may make operational adjustments, and appreciate your understanding and cooperation.\nAll aboard for a train-themed parade that’s loaded with fantasy, fun and music—led by Mickey Mouse himself!','15:30:00','21:00:00','Gardens of Imagination',1000,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-mickeys-storybook-express-hero-new.jpg'),('Photo Walls in Tomorrowland','Snap a Photo in front of the Photo Walls in Tomorrowland.','10:00:00','21:00:00','Tomorrowland',1000,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-marvel-campaign-photo-wall-hero.jpg'),('Pirates Roaming','In Treasure Cove, you will find frivolity, mayhem and personality as Pirates go about their daily lives.','10:00:00','21:00:00','Treasure Cove',1000,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-pirates-roaming-hero.jpg'),('Selfie Spot with Jack Sparrow at the Sparrow’s Nest','Dare to become a pirate in this port town overflowing with notorious Caribbean pirates, and be sure to say “Ahoy Matey” if you run into the one-and-only Captain Jack Sparrow!','10:00:00','21:00:00','Treasure Cove',1000,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-jack-sparrow-treasure-cove-hero-11.jpg'),('Selfie Spot with Mickey at the Gardens of Imagination','Get ready for your selfie! Visit an enchanted gallery exhibit before a meeting with the world-famous Mickey Mouse!\n(Selfie Spots with Disney Characters are currently in trial operations. Please follow onsite Cast Members’ instructions during this experience. To provide a safe and enjoyable experience for all guests, the use of selfie sticks is not allowed in Shanghai Disneyland.)','10:00:00','17:30:00','Gardens of Imagination',1000,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-meet-mickey-mouse-new-hero.jpg'),('Selfie Spot with Minnie Mouse and Friends at Mickey Avenue','Enter the magical realm of Shanghai Disneyland and and catch a glimpse of Minnie Mouse and all the residents of Mickey Avenue!','10:00:00','21:00:00','Mickey Avenue',1000,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-minnie-mouse-friends-hero-new.jpg'),('Selfie Spot with the Ant-Man and the Wasp at Marvel Universe','A heroic encounter!','10:00:00','21:00:00','Gardens of Imagination',1000,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-selfie-spot-with-the-antman-and-the-wasp-hero.jpg'),('Selfie Spot with your Favorite Toys at The Meeting Post','Yee-haw! Capture a selfie with a few of your Toy Story pals at a photo-filled Character Encounter in Disney·Pixar Toy Story Land.','10:00:00','21:00:00','Disney•Pixar Toy Story Land',1000,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-meeting-post-hero-new.jpg'),('Shanghai Disneyland Band','Get in the swing as this lively ensemble welcomes you to Mickey Avenue with a high-spirited mix of music and fun.','10:00:00','21:00:00','Mickey Avenue',1000,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-shanghai-disneyland-band-hero-new.jpg'),('Shanghai Disneyland Hotel Lobby Pianist','A pianist plays enchanting melodies on this magnificent grand piano surrounded by breathtaking elegance.','10:00:00','21:00:00','Shanghai Disneyland Hotel',1000,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-shanghai-disneyland-hotel-lobby-pianist-hero.jpg'),('Tai Chi with Character','Join Donald Duck and Chip ’n’ Dale to practice balance and harmony in the fun-filled Melody Garden. This interactive character experience features traditional costumes and music.','10:00:00','21:00:00','Gardens of Imagination',1000,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-tai-chi-with-character-hero.jpg'),('Traveling Troubadours','A delightful group of musicians, the Travelling Troubadours, join a spirited songstress to sing and play Disney tunes. The combination of traditional Chinese instruments with accordion is particularly charming in Fantasyland.','10:00:00','21:00:00','Fantasyland',1000,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-traveling-troubadours-hero.jpg'),('ttt',NULL,NULL,NULL,NULL,1000,NULL),('ttt  ttt',NULL,NULL,NULL,NULL,1000,NULL),('Voodoo Alley Fight','Near Doubloon Market, a disagreement breaks out. With clashing swords, you never know who will become a part of the action in this surprising vignette!','10:00:00','21:00:00','Treasure Cove',1000,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-voodoo-alley-fight-hero.jpg'),('Woody’s Rescue Patrol','See Woody and the gang patrol Toy Story Land on a mission to find lost toys.','10:00:00','21:00:00','Disney•Pixar Toy Story Land',1000,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-woodys-rescue-patrol-hero.jpg');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facility`
--

LOCK TABLES `facility` WRITE;
/*!40000 ALTER TABLE `facility` DISABLE KEYS */;
INSERT INTO `facility` VALUES ('“Once Upon a Time” Adventure','normal','Interactive, Indoor','10:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',2.9,1),('Alice in Wonderland Maze','normal','Outdoor','09:00:00','18:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Buzz Lightyear Planet Rescue','normal','Dark, Interactive, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Camp Discovery','repairing','Outdoor','23:59:59','23:59:59',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Dumbo the Flying Elephant','normal','Spinning, Outdoor, Rider Switch','09:00:00','18:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Enchanted Storybook Castle','normal','Indoor','10:00:00','09:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Explorer Canoes','normal','Shanghai Disneyland, Treasure Cove','09:00:00','15:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Fantasia Carousel','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Garden of the Twelve Friends','normal','Outdoor','10:00:00','09:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Giant Donald Duck','normal','Outdoor','10:00:00','09:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Hunny Pot Spin','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Jet Packs','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Marvel Universe','normal','Interactive, Indoor','10:00:00','18:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Peter Pan’s Flight','normal','Dark, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Pirates of the Caribbean Battle for the Sunken Treasure','normal','Thrill Rides, Small Drops, Dark, Loud, Scary, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Rex’s Racer','normal','Thrill Rides, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-att-rex-racer-hero-v3.jpg',3,1),('Roaring Rapids','normal','Thrill Rides, Water Rides, Big Drops, Dark, Loud, Scary, Outdoor, Rider Switch','09:00:00','19:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-att-roaring-rapids-hero-v3.jpg',3,1),('Selfie Spot with Disney Jungle Characters','normal','Interactive, Outdoor','10:30:00','17:00:00',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-jungle-friends-adventure-isle-hero-new.jpg',3,1),('Selfie Spot with Mickey','normal','Interactive, Indoor','10:00:00','17:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Selfie Spot with Princesses','normal','Interactive, Outdoor','09:30:00','17:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Seven Dwarfs Mine Train','normal','Thrill Rides, Big Drops, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Shipwreck Shore','normal','Interactive, Outdoor','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Slinky Dog Spin','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Soaring Over the Horizon','normal','Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-att-soaring-hero-new.jpg',3,1),('Stitch Encounter','normal','Interactive, Indoor','10:00:00','19:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('The Many Adventures of Winnie the Pooh','normal','Dark, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('TRON Lightcycle Power Run – Presented by Chevrolet','normal','Thrill Rides, Big Drops, Dark, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('TRON Realm, Chevrolet Digital Challenge','normal','Interactive','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Voyage to the Crystal Grotto','normal','Slow Rides, Outdoor, Rider Switch','09:00:00','19:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Woody’s Roundup','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password`
--

LOCK TABLES `password` WRITE;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
INSERT INTO `password` VALUES (12,'Sn0e1BRHTkAzrCnMuGU9mw=='),(15,'CY9rzUYh03PK3k6DJie09g=='),(20,'12345'),(49,'gdyb21LQTcIANtvYMT7QVQ=='),(50,'gdyb21LQTcIANtvYMT7QVQ=='),(58,'12345'),(94,'RtBF/1GQ9uqTc52mwKoZvA=='),(95,'RtBF/1GQ9uqTc52mwKoZvA=='),(105,'RtBF/1GQ9uqTc52mwKoZvA=='),(106,'RtBF/1GQ9uqTc52mwKoZvA=='),(127,'RtBF/1GQ9uqTc52mwKoZvA=='),(128,'RtBF/1GQ9uqTc52mwKoZvA=='),(138,'RtBF/1GQ9uqTc52mwKoZvA=='),(139,'RtBF/1GQ9uqTc52mwKoZvA=='),(160,'RtBF/1GQ9uqTc52mwKoZvA=='),(161,'RtBF/1GQ9uqTc52mwKoZvA=='),(171,'RtBF/1GQ9uqTc52mwKoZvA=='),(172,'RtBF/1GQ9uqTc52mwKoZvA=='),(193,'RtBF/1GQ9uqTc52mwKoZvA=='),(194,'RtBF/1GQ9uqTc52mwKoZvA=='),(204,'RtBF/1GQ9uqTc52mwKoZvA=='),(205,'RtBF/1GQ9uqTc52mwKoZvA=='),(226,'RtBF/1GQ9uqTc52mwKoZvA=='),(227,'RtBF/1GQ9uqTc52mwKoZvA=='),(237,'RtBF/1GQ9uqTc52mwKoZvA=='),(238,'RtBF/1GQ9uqTc52mwKoZvA=='),(259,'RtBF/1GQ9uqTc52mwKoZvA=='),(260,'RtBF/1GQ9uqTc52mwKoZvA=='),(270,'RtBF/1GQ9uqTc52mwKoZvA=='),(271,'RtBF/1GQ9uqTc52mwKoZvA=='),(292,'RtBF/1GQ9uqTc52mwKoZvA=='),(293,'RtBF/1GQ9uqTc52mwKoZvA=='),(303,'RtBF/1GQ9uqTc52mwKoZvA=='),(304,'RtBF/1GQ9uqTc52mwKoZvA=='),(325,'RtBF/1GQ9uqTc52mwKoZvA=='),(326,'RtBF/1GQ9uqTc52mwKoZvA=='),(336,'RtBF/1GQ9uqTc52mwKoZvA=='),(337,'RtBF/1GQ9uqTc52mwKoZvA=='),(346,'12345'),(382,'RtBF/1GQ9uqTc52mwKoZvA=='),(383,'RtBF/1GQ9uqTc52mwKoZvA=='),(384,'RtBF/1GQ9uqTc52mwKoZvA=='),(394,'RtBF/1GQ9uqTc52mwKoZvA=='),(395,'RtBF/1GQ9uqTc52mwKoZvA=='),(400,'RtBF/1GQ9uqTc52mwKoZvA=='),(407,'RtBF/1GQ9uqTc52mwKoZvA=='),(408,'RtBF/1GQ9uqTc52mwKoZvA=='),(417,'12345'),(426,'RtBF/1GQ9uqTc52mwKoZvA=='),(427,'RtBF/1GQ9uqTc52mwKoZvA=='),(436,'RtBF/1GQ9uqTc52mwKoZvA=='),(437,'RtBF/1GQ9uqTc52mwKoZvA=='),(446,'12345'),(455,'RtBF/1GQ9uqTc52mwKoZvA=='),(456,'RtBF/1GQ9uqTc52mwKoZvA=='),(465,'RtBF/1GQ9uqTc52mwKoZvA=='),(466,'RtBF/1GQ9uqTc52mwKoZvA=='),(484,'RtBF/1GQ9uqTc52mwKoZvA=='),(485,'RtBF/1GQ9uqTc52mwKoZvA=='),(494,'RtBF/1GQ9uqTc52mwKoZvA=='),(495,'RtBF/1GQ9uqTc52mwKoZvA=='),(504,'12345'),(513,'RtBF/1GQ9uqTc52mwKoZvA=='),(514,'RtBF/1GQ9uqTc52mwKoZvA=='),(523,'RtBF/1GQ9uqTc52mwKoZvA=='),(524,'RtBF/1GQ9uqTc52mwKoZvA=='),(542,'RtBF/1GQ9uqTc52mwKoZvA=='),(543,'RtBF/1GQ9uqTc52mwKoZvA=='),(557,'12345'),(609,'RtBF/1GQ9uqTc52mwKoZvA=='),(610,'RtBF/1GQ9uqTc52mwKoZvA=='),(611,'RtBF/1GQ9uqTc52mwKoZvA=='),(692,'12345'),(696,'RtBF/1GQ9uqTc52mwKoZvA=='),(697,'RtBF/1GQ9uqTc52mwKoZvA=='),(698,'RtBF/1GQ9uqTc52mwKoZvA=='),(723,'12345'),(725,'RtBF/1GQ9uqTc52mwKoZvA=='),(726,'RtBF/1GQ9uqTc52mwKoZvA=='),(727,'RtBF/1GQ9uqTc52mwKoZvA=='),(754,'RtBF/1GQ9uqTc52mwKoZvA=='),(755,'RtBF/1GQ9uqTc52mwKoZvA=='),(756,'RtBF/1GQ9uqTc52mwKoZvA=='),(763,'12345'),(783,'RtBF/1GQ9uqTc52mwKoZvA=='),(784,'RtBF/1GQ9uqTc52mwKoZvA=='),(790,'RtBF/1GQ9uqTc52mwKoZvA=='),(791,'RtBF/1GQ9uqTc52mwKoZvA=='),(812,'RtBF/1GQ9uqTc52mwKoZvA=='),(813,'RtBF/1GQ9uqTc52mwKoZvA=='),(819,'RtBF/1GQ9uqTc52mwKoZvA=='),(820,'RtBF/1GQ9uqTc52mwKoZvA=='),(849,'RtBF/1GQ9uqTc52mwKoZvA=='),(850,'RtBF/1GQ9uqTc52mwKoZvA=='),(860,'RtBF/1GQ9uqTc52mwKoZvA=='),(861,'RtBF/1GQ9uqTc52mwKoZvA=='),(878,'12345'),(982,'RtBF/1GQ9uqTc52mwKoZvA=='),(983,'RtBF/1GQ9uqTc52mwKoZvA=='),(993,'RtBF/1GQ9uqTc52mwKoZvA=='),(994,'RtBF/1GQ9uqTc52mwKoZvA=='),(1021,'RtBF/1GQ9uqTc52mwKoZvA=='),(1022,'RtBF/1GQ9uqTc52mwKoZvA=='),(1032,'RtBF/1GQ9uqTc52mwKoZvA=='),(1033,'RtBF/1GQ9uqTc52mwKoZvA=='),(1103,'RtBF/1GQ9uqTc52mwKoZvA=='),(1104,'RtBF/1GQ9uqTc52mwKoZvA=='),(1105,'RtBF/1GQ9uqTc52mwKoZvA=='),(1106,'RtBF/1GQ9uqTc52mwKoZvA=='),(1129,'RtBF/1GQ9uqTc52mwKoZvA=='),(1130,'RtBF/1GQ9uqTc52mwKoZvA=='),(1140,'RtBF/1GQ9uqTc52mwKoZvA=='),(1141,'RtBF/1GQ9uqTc52mwKoZvA=='),(1181,'RtBF/1GQ9uqTc52mwKoZvA=='),(1182,'RtBF/1GQ9uqTc52mwKoZvA=='),(1183,'RtBF/1GQ9uqTc52mwKoZvA=='),(1184,'RtBF/1GQ9uqTc52mwKoZvA=='),(1207,'RtBF/1GQ9uqTc52mwKoZvA=='),(1208,'RtBF/1GQ9uqTc52mwKoZvA=='),(1218,'RtBF/1GQ9uqTc52mwKoZvA=='),(1219,'RtBF/1GQ9uqTc52mwKoZvA=='),(1259,'RtBF/1GQ9uqTc52mwKoZvA=='),(1260,'RtBF/1GQ9uqTc52mwKoZvA=='),(1261,'RtBF/1GQ9uqTc52mwKoZvA=='),(1262,'RtBF/1GQ9uqTc52mwKoZvA=='),(1296,'RtBF/1GQ9uqTc52mwKoZvA=='),(1304,'RtBF/1GQ9uqTc52mwKoZvA=='),(1314,'RtBF/1GQ9uqTc52mwKoZvA=='),(1315,'RtBF/1GQ9uqTc52mwKoZvA=='),(1353,'RtBF/1GQ9uqTc52mwKoZvA=='),(1361,'RtBF/1GQ9uqTc52mwKoZvA=='),(1371,'RtBF/1GQ9uqTc52mwKoZvA=='),(1372,'RtBF/1GQ9uqTc52mwKoZvA=='),(1398,'12345'),(1433,'RtBF/1GQ9uqTc52mwKoZvA=='),(1434,'RtBF/1GQ9uqTc52mwKoZvA=='),(1435,'RtBF/1GQ9uqTc52mwKoZvA=='),(1436,'RtBF/1GQ9uqTc52mwKoZvA=='),(1466,'RtBF/1GQ9uqTc52mwKoZvA=='),(1470,'RtBF/1GQ9uqTc52mwKoZvA=='),(1480,'RtBF/1GQ9uqTc52mwKoZvA=='),(1481,'RtBF/1GQ9uqTc52mwKoZvA=='),(1490,'12345'),(1526,'RtBF/1GQ9uqTc52mwKoZvA=='),(1530,'RtBF/1GQ9uqTc52mwKoZvA=='),(1540,'RtBF/1GQ9uqTc52mwKoZvA=='),(1541,'RtBF/1GQ9uqTc52mwKoZvA=='),(1613,'RtBF/1GQ9uqTc52mwKoZvA=='),(1614,'RtBF/1GQ9uqTc52mwKoZvA=='),(1615,'RtBF/1GQ9uqTc52mwKoZvA=='),(1616,'RtBF/1GQ9uqTc52mwKoZvA=='),(1673,'RtBF/1GQ9uqTc52mwKoZvA=='),(1674,'RtBF/1GQ9uqTc52mwKoZvA=='),(1675,'RtBF/1GQ9uqTc52mwKoZvA=='),(1676,'RtBF/1GQ9uqTc52mwKoZvA=='),(1733,'RtBF/1GQ9uqTc52mwKoZvA=='),(1734,'RtBF/1GQ9uqTc52mwKoZvA=='),(1735,'RtBF/1GQ9uqTc52mwKoZvA=='),(1736,'RtBF/1GQ9uqTc52mwKoZvA=='),(1770,'RtBF/1GQ9uqTc52mwKoZvA=='),(1774,'RtBF/1GQ9uqTc52mwKoZvA=='),(1784,'RtBF/1GQ9uqTc52mwKoZvA=='),(1785,'RtBF/1GQ9uqTc52mwKoZvA=='),(1804,'12345'),(1808,'12345'),(1816,'12345'),(1817,'12345'),(1818,'12345'),(1819,'12345'),(1820,'12345'),(1849,'RtBF/1GQ9uqTc52mwKoZvA=='),(1853,'RtBF/1GQ9uqTc52mwKoZvA=='),(1863,'RtBF/1GQ9uqTc52mwKoZvA=='),(1864,'RtBF/1GQ9uqTc52mwKoZvA=='),(1880,'12345'),(1881,'12345'),(1882,'12345'),(1883,'12345'),(1884,'12345'),(1913,'RtBF/1GQ9uqTc52mwKoZvA=='),(1917,'RtBF/1GQ9uqTc52mwKoZvA=='),(1927,'RtBF/1GQ9uqTc52mwKoZvA=='),(1928,'RtBF/1GQ9uqTc52mwKoZvA=='),(1944,'12345'),(1945,'12345'),(1946,'12345'),(1947,'12345'),(1948,'12345'),(1977,'RtBF/1GQ9uqTc52mwKoZvA=='),(1981,'RtBF/1GQ9uqTc52mwKoZvA=='),(1991,'RtBF/1GQ9uqTc52mwKoZvA=='),(1992,'RtBF/1GQ9uqTc52mwKoZvA=='),(2008,'12345'),(2009,'12345'),(2010,'12345'),(2011,'12345'),(2012,'12345'),(2068,'RtBF/1GQ9uqTc52mwKoZvA=='),(2069,'RtBF/1GQ9uqTc52mwKoZvA=='),(2070,'RtBF/1GQ9uqTc52mwKoZvA=='),(2071,'RtBF/1GQ9uqTc52mwKoZvA=='),(2072,'12345'),(2073,'12345'),(2074,'12345'),(2075,'12345'),(2076,'12345'),(2132,'RtBF/1GQ9uqTc52mwKoZvA=='),(2133,'RtBF/1GQ9uqTc52mwKoZvA=='),(2134,'RtBF/1GQ9uqTc52mwKoZvA=='),(2135,'RtBF/1GQ9uqTc52mwKoZvA=='),(2136,'12345'),(2137,'12345'),(2138,'12345'),(2139,'12345'),(2140,'12345'),(2196,'RtBF/1GQ9uqTc52mwKoZvA=='),(2197,'RtBF/1GQ9uqTc52mwKoZvA=='),(2198,'RtBF/1GQ9uqTc52mwKoZvA=='),(2199,'RtBF/1GQ9uqTc52mwKoZvA=='),(2200,'12345'),(2201,'12345'),(2202,'12345'),(2203,'12345'),(2204,'12345'),(2260,'RtBF/1GQ9uqTc52mwKoZvA=='),(2261,'RtBF/1GQ9uqTc52mwKoZvA=='),(2262,'RtBF/1GQ9uqTc52mwKoZvA=='),(2263,'RtBF/1GQ9uqTc52mwKoZvA=='),(2264,'12345'),(2265,'12345'),(2266,'12345'),(2267,'12345'),(2268,'12345'),(2324,'RtBF/1GQ9uqTc52mwKoZvA=='),(2325,'RtBF/1GQ9uqTc52mwKoZvA=='),(2326,'RtBF/1GQ9uqTc52mwKoZvA=='),(2327,'RtBF/1GQ9uqTc52mwKoZvA=='),(2328,'12345'),(2329,'12345'),(2330,'12345'),(2331,'12345'),(2332,'12345'),(2388,'RtBF/1GQ9uqTc52mwKoZvA=='),(2389,'RtBF/1GQ9uqTc52mwKoZvA=='),(2390,'RtBF/1GQ9uqTc52mwKoZvA=='),(2391,'RtBF/1GQ9uqTc52mwKoZvA=='),(2392,'12345'),(2393,'12345'),(2394,'12345'),(2395,'12345'),(2396,'12345'),(2452,'RtBF/1GQ9uqTc52mwKoZvA=='),(2453,'RtBF/1GQ9uqTc52mwKoZvA=='),(2454,'RtBF/1GQ9uqTc52mwKoZvA=='),(2455,'RtBF/1GQ9uqTc52mwKoZvA=='),(2456,'12345'),(2457,'12345'),(2458,'12345'),(2459,'12345'),(2460,'12345'),(2516,'RtBF/1GQ9uqTc52mwKoZvA=='),(2517,'RtBF/1GQ9uqTc52mwKoZvA=='),(2518,'RtBF/1GQ9uqTc52mwKoZvA=='),(2519,'RtBF/1GQ9uqTc52mwKoZvA=='),(2520,'12345'),(2521,'12345'),(2522,'12345'),(2523,'12345'),(2524,'12345'),(2580,'RtBF/1GQ9uqTc52mwKoZvA=='),(2581,'RtBF/1GQ9uqTc52mwKoZvA=='),(2582,'RtBF/1GQ9uqTc52mwKoZvA=='),(2583,'RtBF/1GQ9uqTc52mwKoZvA=='),(2584,'12345'),(2585,'12345'),(2586,'12345'),(2587,'12345'),(2588,'12345'),(2644,'RtBF/1GQ9uqTc52mwKoZvA=='),(2645,'RtBF/1GQ9uqTc52mwKoZvA=='),(2646,'RtBF/1GQ9uqTc52mwKoZvA=='),(2647,'RtBF/1GQ9uqTc52mwKoZvA=='),(2648,'12345'),(2649,'12345'),(2650,'12345'),(2651,'12345'),(2652,'12345'),(2708,'RtBF/1GQ9uqTc52mwKoZvA=='),(2709,'RtBF/1GQ9uqTc52mwKoZvA=='),(2710,'RtBF/1GQ9uqTc52mwKoZvA=='),(2711,'RtBF/1GQ9uqTc52mwKoZvA=='),(2712,'12345'),(2713,'12345'),(2714,'12345'),(2715,'12345'),(2716,'12345'),(2772,'RtBF/1GQ9uqTc52mwKoZvA=='),(2773,'RtBF/1GQ9uqTc52mwKoZvA=='),(2774,'RtBF/1GQ9uqTc52mwKoZvA=='),(2775,'RtBF/1GQ9uqTc52mwKoZvA=='),(2776,'12345'),(2777,'12345'),(2778,'12345'),(2779,'12345'),(2780,'12345'),(2836,'RtBF/1GQ9uqTc52mwKoZvA=='),(2837,'RtBF/1GQ9uqTc52mwKoZvA=='),(2838,'RtBF/1GQ9uqTc52mwKoZvA=='),(2839,'RtBF/1GQ9uqTc52mwKoZvA=='),(2840,'12345'),(2841,'12345'),(2842,'12345'),(2843,'12345'),(2844,'12345'),(2900,'RtBF/1GQ9uqTc52mwKoZvA=='),(2901,'RtBF/1GQ9uqTc52mwKoZvA=='),(2902,'RtBF/1GQ9uqTc52mwKoZvA=='),(2903,'RtBF/1GQ9uqTc52mwKoZvA=='),(2904,'12345'),(2905,'12345'),(2906,'12345'),(2907,'12345'),(2908,'12345'),(2964,'RtBF/1GQ9uqTc52mwKoZvA=='),(2965,'RtBF/1GQ9uqTc52mwKoZvA=='),(2966,'RtBF/1GQ9uqTc52mwKoZvA=='),(2967,'RtBF/1GQ9uqTc52mwKoZvA=='),(2968,'12345'),(2969,'12345'),(2970,'12345'),(2971,'12345'),(2972,'12345'),(3028,'RtBF/1GQ9uqTc52mwKoZvA=='),(3029,'RtBF/1GQ9uqTc52mwKoZvA=='),(3030,'RtBF/1GQ9uqTc52mwKoZvA=='),(3031,'RtBF/1GQ9uqTc52mwKoZvA=='),(3032,'12345'),(3033,'12345'),(3034,'12345'),(3035,'12345'),(3036,'12345'),(3092,'RtBF/1GQ9uqTc52mwKoZvA=='),(3093,'RtBF/1GQ9uqTc52mwKoZvA=='),(3094,'RtBF/1GQ9uqTc52mwKoZvA=='),(3095,'RtBF/1GQ9uqTc52mwKoZvA=='),(3096,'12345'),(3097,'12345'),(3098,'12345'),(3099,'12345'),(3100,'12345'),(3156,'RtBF/1GQ9uqTc52mwKoZvA=='),(3157,'RtBF/1GQ9uqTc52mwKoZvA=='),(3158,'RtBF/1GQ9uqTc52mwKoZvA=='),(3159,'RtBF/1GQ9uqTc52mwKoZvA=='),(3160,'12345'),(3161,'12345'),(3162,'12345'),(3163,'12345'),(3164,'12345'),(3220,'RtBF/1GQ9uqTc52mwKoZvA=='),(3221,'RtBF/1GQ9uqTc52mwKoZvA=='),(3222,'RtBF/1GQ9uqTc52mwKoZvA=='),(3223,'RtBF/1GQ9uqTc52mwKoZvA=='),(3224,'12345'),(3225,'12345'),(3226,'12345'),(3227,'12345'),(3228,'12345'),(3284,'RtBF/1GQ9uqTc52mwKoZvA=='),(3285,'RtBF/1GQ9uqTc52mwKoZvA=='),(3286,'RtBF/1GQ9uqTc52mwKoZvA=='),(3287,'RtBF/1GQ9uqTc52mwKoZvA=='),(3292,'12345'),(3293,'12345'),(3294,'12345'),(3295,'12345'),(3296,'12345'),(3297,'12345'),(3367,'RtBF/1GQ9uqTc52mwKoZvA=='),(3368,'RtBF/1GQ9uqTc52mwKoZvA=='),(3369,'RtBF/1GQ9uqTc52mwKoZvA=='),(3370,'RtBF/1GQ9uqTc52mwKoZvA=='),(3371,'12345'),(3372,'12345'),(3373,'12345'),(3374,'12345'),(3375,'12345'),(3376,'12345'),(3447,'RtBF/1GQ9uqTc52mwKoZvA=='),(3448,'RtBF/1GQ9uqTc52mwKoZvA=='),(3449,'RtBF/1GQ9uqTc52mwKoZvA=='),(3450,'RtBF/1GQ9uqTc52mwKoZvA=='),(3451,'12345'),(3452,'12345'),(3453,'12345'),(3454,'12345'),(3455,'12345'),(3456,'12345'),(3527,'RtBF/1GQ9uqTc52mwKoZvA=='),(3528,'RtBF/1GQ9uqTc52mwKoZvA=='),(3529,'RtBF/1GQ9uqTc52mwKoZvA=='),(3530,'RtBF/1GQ9uqTc52mwKoZvA=='),(3531,'12345'),(3532,'12345'),(3533,'12345'),(3534,'12345'),(3535,'12345'),(3536,'12345'),(3608,'12345'),(3627,'RtBF/1GQ9uqTc52mwKoZvA=='),(3628,'RtBF/1GQ9uqTc52mwKoZvA=='),(3629,'RtBF/1GQ9uqTc52mwKoZvA=='),(3630,'RtBF/1GQ9uqTc52mwKoZvA=='),(3631,'12345'),(3632,'12345'),(3633,'12345'),(3634,'12345'),(3635,'12345'),(3636,'12345'),(3708,'12345'),(3727,'RtBF/1GQ9uqTc52mwKoZvA=='),(3728,'RtBF/1GQ9uqTc52mwKoZvA=='),(3729,'RtBF/1GQ9uqTc52mwKoZvA=='),(3730,'RtBF/1GQ9uqTc52mwKoZvA=='),(3731,'12345'),(3732,'12345'),(3733,'12345'),(3734,'12345'),(3735,'12345'),(3736,'12345'),(3772,'RtBF/1GQ9uqTc52mwKoZvA=='),(3777,'RtBF/1GQ9uqTc52mwKoZvA=='),(3784,'gdyb21LQTcIANtvYMT7QVQ=='),(3787,'RtBF/1GQ9uqTc52mwKoZvA=='),(3788,'RtBF/1GQ9uqTc52mwKoZvA=='),(3804,'12345'),(3838,'12345'),(3839,'12345'),(3840,'12345'),(3841,'12345'),(3842,'12345'),(3843,'12345'),(3879,'RtBF/1GQ9uqTc52mwKoZvA=='),(3884,'RtBF/1GQ9uqTc52mwKoZvA=='),(3895,'RtBF/1GQ9uqTc52mwKoZvA=='),(3896,'RtBF/1GQ9uqTc52mwKoZvA=='),(3912,'12345'),(3938,'12345'),(3939,'12345'),(3940,'12345'),(3941,'12345'),(3942,'12345'),(3943,'12345'),(3979,'RtBF/1GQ9uqTc52mwKoZvA=='),(3984,'RtBF/1GQ9uqTc52mwKoZvA=='),(3995,'RtBF/1GQ9uqTc52mwKoZvA=='),(3996,'RtBF/1GQ9uqTc52mwKoZvA=='),(4012,'12345'),(4038,'RtBF/1GQ9uqTc52mwKoZvA=='),(4050,'RtBF/1GQ9uqTc52mwKoZvA=='),(4055,'12345'),(4056,'12345'),(4057,'12345'),(4058,'12345'),(4059,'12345'),(4060,'12345'),(4096,'RtBF/1GQ9uqTc52mwKoZvA=='),(4101,'RtBF/1GQ9uqTc52mwKoZvA=='),(4112,'RtBF/1GQ9uqTc52mwKoZvA=='),(4113,'RtBF/1GQ9uqTc52mwKoZvA=='),(4135,'12345'),(4155,'12345'),(4156,'12345'),(4157,'12345'),(4158,'12345'),(4159,'12345'),(4160,'12345'),(4196,'RtBF/1GQ9uqTc52mwKoZvA=='),(4201,'RtBF/1GQ9uqTc52mwKoZvA=='),(4212,'RtBF/1GQ9uqTc52mwKoZvA=='),(4213,'RtBF/1GQ9uqTc52mwKoZvA=='),(4235,'12345'),(4258,'12345'),(4259,'12345'),(4260,'12345'),(4261,'12345'),(4262,'12345'),(4263,'12345'),(4299,'RtBF/1GQ9uqTc52mwKoZvA=='),(4304,'RtBF/1GQ9uqTc52mwKoZvA=='),(4315,'RtBF/1GQ9uqTc52mwKoZvA=='),(4316,'RtBF/1GQ9uqTc52mwKoZvA=='),(4338,'12345'),(4369,'12345'),(4370,'12345'),(4371,'12345'),(4372,'12345'),(4373,'12345'),(4374,'12345'),(4410,'RtBF/1GQ9uqTc52mwKoZvA=='),(4415,'RtBF/1GQ9uqTc52mwKoZvA=='),(4426,'RtBF/1GQ9uqTc52mwKoZvA=='),(4427,'RtBF/1GQ9uqTc52mwKoZvA=='),(4449,'12345'),(4469,'12345'),(4470,'12345'),(4471,'12345'),(4472,'12345'),(4473,'12345'),(4474,'12345'),(4510,'RtBF/1GQ9uqTc52mwKoZvA=='),(4515,'RtBF/1GQ9uqTc52mwKoZvA=='),(4526,'RtBF/1GQ9uqTc52mwKoZvA=='),(4527,'RtBF/1GQ9uqTc52mwKoZvA=='),(4549,'12345'),(4567,'12345'),(4568,'12345'),(4569,'12345'),(4570,'12345'),(4571,'12345'),(4572,'12345'),(4608,'RtBF/1GQ9uqTc52mwKoZvA=='),(4609,'12345'),(4610,'12345'),(4611,'12345'),(4612,'12345'),(4613,'12345'),(4614,'12345'),(4655,'gdyb21LQTcIANtvYMT7QVQ=='),(4670,'12345'),(4699,'12345'),(4700,'12345'),(4701,'12345'),(4702,'12345'),(4703,'12345'),(4704,'12345'),(4740,'RtBF/1GQ9uqTc52mwKoZvA=='),(4745,'RtBF/1GQ9uqTc52mwKoZvA=='),(4756,'RtBF/1GQ9uqTc52mwKoZvA=='),(4757,'RtBF/1GQ9uqTc52mwKoZvA=='),(4779,'12345'),(4799,'12345'),(4800,'12345'),(4801,'12345'),(4802,'12345'),(4803,'12345'),(4804,'12345'),(4876,'12345'),(4895,'RtBF/1GQ9uqTc52mwKoZvA=='),(4896,'RtBF/1GQ9uqTc52mwKoZvA=='),(4897,'RtBF/1GQ9uqTc52mwKoZvA=='),(4898,'RtBF/1GQ9uqTc52mwKoZvA=='),(4899,'12345'),(4900,'12345'),(4901,'12345'),(4902,'12345'),(4903,'12345'),(4904,'12345'),(4976,'12345'),(4995,'RtBF/1GQ9uqTc52mwKoZvA=='),(4996,'RtBF/1GQ9uqTc52mwKoZvA=='),(4997,'RtBF/1GQ9uqTc52mwKoZvA=='),(4998,'RtBF/1GQ9uqTc52mwKoZvA=='),(5009,'12345'),(5010,'12345'),(5011,'12345'),(5012,'12345'),(5013,'12345'),(5014,'12345'),(5050,'RtBF/1GQ9uqTc52mwKoZvA=='),(5055,'RtBF/1GQ9uqTc52mwKoZvA=='),(5066,'RtBF/1GQ9uqTc52mwKoZvA=='),(5067,'RtBF/1GQ9uqTc52mwKoZvA=='),(5089,'12345'),(5109,'12345'),(5110,'12345'),(5111,'12345'),(5112,'12345'),(5113,'12345'),(5114,'12345'),(5186,'12345'),(5205,'RtBF/1GQ9uqTc52mwKoZvA=='),(5206,'RtBF/1GQ9uqTc52mwKoZvA=='),(5207,'RtBF/1GQ9uqTc52mwKoZvA=='),(5208,'RtBF/1GQ9uqTc52mwKoZvA==');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
  `description` varchar(200) DEFAULT NULL,
  `image_url` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ticket_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=5209 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userAccount`
--

LOCK TABLES `userAccount` WRITE;
/*!40000 ALTER TABLE `userAccount` DISABLE KEYS */;
INSERT INTO `userAccount` VALUES ('123456789','Yu','male',22,'manager',12,NULL,NULL),(NULL,NULL,NULL,NULL,'manager',1738,NULL,NULL);
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

-- Dump completed on 2020-12-04 20:43:47
