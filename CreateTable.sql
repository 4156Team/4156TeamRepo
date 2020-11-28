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
INSERT INTO `comment` VALUES (1,1,'Alice','test comment','2020-11-26 18:01:06'),(2,1,'Alice','test comment','2020-11-26 18:02:40'),(6,1,'Alice','test comment','2020-11-26 18:27:44'),(8,1,'Alice','test comment','2020-11-26 18:28:24'),(10,1,'Alice','test comment','2020-11-26 18:28:53'),(12,1,'Alice','test comment','2020-11-26 18:29:49'),(25,1,'Alice','test comment','2020-11-26 18:47:30'),(28,1,'Alice','test comment','2020-11-26 18:57:54'),(31,1,'Alice','test comment','2020-11-26 19:00:10'),(32,3,'Aria','comment controller test','2020-11-26 19:31:15'),(40,1,'Alice','test comment','2020-11-26 20:14:23'),(41,3,'Mike','test comment','2020-11-26 21:16:12');
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
INSERT INTO `event` VALUES ('Adventurous Friends Exploration','Adventure Isle comes to life when the Arbori Musicians, Chip ‘n’ Dale and their adventurous friends explore the far reaches of Adventure Isle.','10:00:00','21:00:00','Adventure Isle',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-adventurous-friends-exploration-hero.jpg'),('Avengers Assemble at the E-Stage','Throughout the day, this dynamic group of super heroes assembles on the Pepsi E-Stage, creating a perfect opportunity to capture a heroic photo.','10:00:00','21:00:00','Tomorrowland',NULL,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-avengers-assemble-at-the-e-stage-hero-new.jpg'),('Avengers Training Initiative','Think you have what it takes to be one of Earth’s Mightiest Heroes? Members of the Avengers and their field agents will be assembling prospective recruits at the Pepsi E-Stage in Tomorrowland for this heroic interactive experience.','10:05:00','21:00:00','Tomorrowland',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-avengers-training-initiative-hero-v2.jpg'),('Bai Ling Storytelling','One troupe of storytellers will share a beloved Disney Story with humor, songs, and puppetry.','10:00:00','21:00:00','Fantasyland',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-bai-ling-storytelling-hero.jpg'),('Eye of the Storm: Captain Jack’s Stunt Spectacular','Catch Captain Jack in action—with high-energy stunts, side-splitting comedy and a swashbuckling finale you’ve got to see to believe!','11:10:00','21:00:00','Treasure Cove',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-eye-of-storm-captain-jacks-spectacular-hero-new.jpg'),('Fantasy Festival','Approach the day with wonder and joy in a special dance with our Disney friends and traveling players.','10:00:00','21:00:00','Fantasyland',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-fantasy-festival-hero.jpg'),('Frozen: A Sing-Along Celebration','Become part of the music and magic as you join Anna, Elsa, Kristoff, and Olaf for an interactive performance where you get to sing along.','11:30:00','21:00:00','Fantasyland',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-frozen-sing-along-celebration-hero-formal-v1.jpg'),('Ignite the Dream - A Nighttime Spectacular of Magic and Light','Ignite the Dream - A Nighttime Spectacular of Magic and Light is currently in trial operations. During this period we may make operational adjustments, and appreciate your understanding and cooperation.\nSee Enchanted Storybook Castle transformed when Disney magic lights up the night with stunning special effects!','20:00:00','21:00:00','Gardens of Imagination',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-ignite-dream-nighttime-spectacular-hero-new-again.jpg'),('Mickey’s Storybook Express','Mickey’s Storybook Express is currently in trial operations. During this period we may make operational adjustments, and appreciate your understanding and cooperation.\nAll aboard for a train-themed parade that’s loaded with fantasy, fun and music—led by Mickey Mouse himself!','15:30:00','21:00:00','Gardens of Imagination',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-mickeys-storybook-express-hero-new.jpg'),('Photo Walls in Tomorrowland','Snap a Photo in front of the Photo Walls in Tomorrowland.','10:00:00','21:00:00','Tomorrowland',NULL,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-marvel-campaign-photo-wall-hero.jpg'),('Pirates Roaming','In Treasure Cove, you will find frivolity, mayhem and personality as Pirates go about their daily lives.','10:00:00','21:00:00','Treasure Cove',NULL,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-pirates-roaming-hero.jpg'),('Selfie Spot with Jack Sparrow at the Sparrow’s Nest','Dare to become a pirate in this port town overflowing with notorious Caribbean pirates, and be sure to say “Ahoy Matey” if you run into the one-and-only Captain Jack Sparrow!','10:00:00','21:00:00','Treasure Cove',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-jack-sparrow-treasure-cove-hero-11.jpg'),('Selfie Spot with Mickey at the Gardens of Imagination','Get ready for your selfie! Visit an enchanted gallery exhibit before a meeting with the world-famous Mickey Mouse!\n(Selfie Spots with Disney Characters are currently in trial operations. Please follow onsite Cast Members’ instructions during this experience. To provide a safe and enjoyable experience for all guests, the use of selfie sticks is not allowed in Shanghai Disneyland.)','10:00:00','17:30:00','Gardens of Imagination',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-meet-mickey-mouse-new-hero.jpg'),('Selfie Spot with Minnie Mouse and Friends at Mickey Avenue','Enter the magical realm of Shanghai Disneyland and and catch a glimpse of Minnie Mouse and all the residents of Mickey Avenue!','10:00:00','21:00:00','Mickey Avenue',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-minnie-mouse-friends-hero-new.jpg'),('Selfie Spot with the Ant-Man and the Wasp at Marvel Universe','A heroic encounter!','10:00:00','21:00:00','Gardens of Imagination',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-selfie-spot-with-the-antman-and-the-wasp-hero.jpg'),('Selfie Spot with your Favorite Toys at The Meeting Post','Yee-haw! Capture a selfie with a few of your Toy Story pals at a photo-filled Character Encounter in Disney·Pixar Toy Story Land.','10:00:00','21:00:00','Disney•Pixar Toy Story Land',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-meeting-post-hero-new.jpg'),('Shanghai Disneyland Band','Get in the swing as this lively ensemble welcomes you to Mickey Avenue with a high-spirited mix of music and fun.','10:00:00','21:00:00','Mickey Avenue',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-shanghai-disneyland-band-hero-new.jpg'),('Shanghai Disneyland Hotel Lobby Pianist','A pianist plays enchanting melodies on this magnificent grand piano surrounded by breathtaking elegance.','10:00:00','21:00:00','Shanghai Disneyland Hotel',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-shanghai-disneyland-hotel-lobby-pianist-hero.jpg'),('Tai Chi with Character','Join Donald Duck and Chip ’n’ Dale to practice balance and harmony in the fun-filled Melody Garden. This interactive character experience features traditional costumes and music.','10:00:00','21:00:00','Gardens of Imagination',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-tai-chi-with-character-hero.jpg'),('Traveling Troubadours','A delightful group of musicians, the Travelling Troubadours, join a spirited songstress to sing and play Disney tunes. The combination of traditional Chinese instruments with accordion is particularly charming in Fantasyland.','10:00:00','21:00:00','Fantasyland',NULL,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-traveling-troubadours-hero.jpg'),('Voodoo Alley Fight','Near Doubloon Market, a disagreement breaks out. With clashing swords, you never know who will become a part of the action in this surprising vignette!','10:00:00','21:00:00','Treasure Cove',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-ent-voodoo-alley-fight-hero.jpg'),('Woody’s Rescue Patrol','See Woody and the gang patrol Toy Story Land on a mission to find lost toys.','10:00:00','21:00:00','Disney•Pixar Toy Story Land',NULL,'https://secure.cdn1.wdpromedia.cn/resize/mwImage/1/630/354/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-woodys-rescue-patrol-hero.jpg');
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
INSERT INTO `facility` VALUES ('“Once Upon a Time” Adventure','normal','Interactive, Indoor','10:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Alice in Wonderland Maze','normal','Outdoor','09:00:00','18:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Buzz Lightyear Planet Rescue','normal','Dark, Interactive, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Camp Discovery','repairing','Outdoor','24:00:00','24:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Dumbo the Flying Elephant','normal','Spinning, Outdoor, Rider Switch','09:00:00','18:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Enchanted Storybook Castle','normal','Indoor','10:00:00','09:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Explorer Canoes','normal','Shanghai Disneyland, Treasure Cove','09:00:00','15:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Fantasia Carousel','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Garden of the Twelve Friends','normal','Outdoor','10:00:00','09:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Giant Donald Duck','normal','Outdoor','10:00:00','09:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Hunny Pot Spin','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Jet Packs','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Marvel Universe','normal','Interactive, Indoor','10:00:00','18:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Peter Pan’s Flight','normal','Dark, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Pirates of the Caribbean Battle for the Sunken Treasure','normal','Thrill Rides, Small Drops, Dark, Loud, Scary, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Rex’s Racer','normal','Thrill Rides, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn3.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-att-rex-racer-hero-v3.jpg',3,1),('Roaring Rapids','normal','Thrill Rides, Water Rides, Big Drops, Dark, Loud, Scary, Outdoor, Rider Switch','09:00:00','19:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-att-roaring-rapids-hero-v3.jpg',3,1),('Selfie Spot with Disney Jungle Characters','normal','Interactive, Outdoor','10:30:00','17:00:00',NULL,'https://secure.cdn2.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-char-characters-jungle-friends-adventure-isle-hero-new.jpg',3,1),('Selfie Spot with Mickey','normal','Interactive, Indoor','10:00:00','17:30:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Selfie Spot with Princesses','normal','Interactive, Outdoor','09:30:00','17:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Seven Dwarfs Mine Train','normal','Thrill Rides, Big Drops, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Shipwreck Shore','normal','Interactive, Outdoor','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Slinky Dog Spin','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Soaring Over the Horizon','normal','Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/resize/mwImage/1/340/192/75/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/en-cn/system/images/shdr-att-soaring-hero-new.jpg',3,1),('Stitch Encounter','normal','Interactive, Indoor','10:00:00','19:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('The Many Adventures of Winnie the Pooh','normal','Dark, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('TRON Lightcycle Power Run – Presented by Chevrolet','normal','Thrill Rides, Big Drops, Dark, Indoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('TRON Realm, Chevrolet Digital Challenge','normal','Interactive','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Voyage to the Crystal Grotto','normal','Slow Rides, Outdoor, Rider Switch','09:00:00','19:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1),('Woody’s Roundup','normal','Spinning, Outdoor, Rider Switch','09:00:00','20:00:00',NULL,'https://secure.cdn4.wdpromedia.cn/media/pep/live/media/site/img/content/finder/thumbnail/a3c58f-attractions.png',3,1);
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
INSERT INTO `password` VALUES (12,'Sn0e1BRHTkAzrCnMuGU9mw=='),(15,'CY9rzUYh03PK3k6DJie09g=='),(20,'12345'),(49,'gdyb21LQTcIANtvYMT7QVQ=='),(50,'gdyb21LQTcIANtvYMT7QVQ=='),(58,'12345'),(94,'RtBF/1GQ9uqTc52mwKoZvA=='),(95,'RtBF/1GQ9uqTc52mwKoZvA=='),(105,'RtBF/1GQ9uqTc52mwKoZvA=='),(106,'RtBF/1GQ9uqTc52mwKoZvA=='),(127,'RtBF/1GQ9uqTc52mwKoZvA=='),(128,'RtBF/1GQ9uqTc52mwKoZvA=='),(138,'RtBF/1GQ9uqTc52mwKoZvA=='),(139,'RtBF/1GQ9uqTc52mwKoZvA=='),(160,'RtBF/1GQ9uqTc52mwKoZvA=='),(161,'RtBF/1GQ9uqTc52mwKoZvA=='),(171,'RtBF/1GQ9uqTc52mwKoZvA=='),(172,'RtBF/1GQ9uqTc52mwKoZvA=='),(193,'RtBF/1GQ9uqTc52mwKoZvA=='),(194,'RtBF/1GQ9uqTc52mwKoZvA=='),(204,'RtBF/1GQ9uqTc52mwKoZvA=='),(205,'RtBF/1GQ9uqTc52mwKoZvA=='),(226,'RtBF/1GQ9uqTc52mwKoZvA=='),(227,'RtBF/1GQ9uqTc52mwKoZvA=='),(237,'RtBF/1GQ9uqTc52mwKoZvA=='),(238,'RtBF/1GQ9uqTc52mwKoZvA=='),(259,'RtBF/1GQ9uqTc52mwKoZvA=='),(260,'RtBF/1GQ9uqTc52mwKoZvA=='),(270,'RtBF/1GQ9uqTc52mwKoZvA=='),(271,'RtBF/1GQ9uqTc52mwKoZvA=='),(292,'RtBF/1GQ9uqTc52mwKoZvA=='),(293,'RtBF/1GQ9uqTc52mwKoZvA=='),(303,'RtBF/1GQ9uqTc52mwKoZvA=='),(304,'RtBF/1GQ9uqTc52mwKoZvA=='),(325,'RtBF/1GQ9uqTc52mwKoZvA=='),(326,'RtBF/1GQ9uqTc52mwKoZvA=='),(336,'RtBF/1GQ9uqTc52mwKoZvA=='),(337,'RtBF/1GQ9uqTc52mwKoZvA=='),(346,'12345'),(382,'RtBF/1GQ9uqTc52mwKoZvA=='),(383,'RtBF/1GQ9uqTc52mwKoZvA=='),(384,'RtBF/1GQ9uqTc52mwKoZvA=='),(394,'RtBF/1GQ9uqTc52mwKoZvA=='),(395,'RtBF/1GQ9uqTc52mwKoZvA=='),(400,'RtBF/1GQ9uqTc52mwKoZvA=='),(407,'RtBF/1GQ9uqTc52mwKoZvA=='),(408,'RtBF/1GQ9uqTc52mwKoZvA=='),(417,'12345'),(426,'RtBF/1GQ9uqTc52mwKoZvA=='),(427,'RtBF/1GQ9uqTc52mwKoZvA=='),(436,'RtBF/1GQ9uqTc52mwKoZvA=='),(437,'RtBF/1GQ9uqTc52mwKoZvA=='),(446,'12345'),(455,'RtBF/1GQ9uqTc52mwKoZvA=='),(456,'RtBF/1GQ9uqTc52mwKoZvA=='),(465,'RtBF/1GQ9uqTc52mwKoZvA=='),(466,'RtBF/1GQ9uqTc52mwKoZvA=='),(484,'RtBF/1GQ9uqTc52mwKoZvA=='),(485,'RtBF/1GQ9uqTc52mwKoZvA=='),(494,'RtBF/1GQ9uqTc52mwKoZvA=='),(495,'RtBF/1GQ9uqTc52mwKoZvA=='),(504,'12345'),(513,'RtBF/1GQ9uqTc52mwKoZvA=='),(514,'RtBF/1GQ9uqTc52mwKoZvA=='),(523,'RtBF/1GQ9uqTc52mwKoZvA=='),(524,'RtBF/1GQ9uqTc52mwKoZvA=='),(542,'RtBF/1GQ9uqTc52mwKoZvA=='),(543,'RtBF/1GQ9uqTc52mwKoZvA=='),(557,'12345'),(609,'RtBF/1GQ9uqTc52mwKoZvA=='),(610,'RtBF/1GQ9uqTc52mwKoZvA=='),(611,'RtBF/1GQ9uqTc52mwKoZvA=='),(692,'12345'),(696,'RtBF/1GQ9uqTc52mwKoZvA=='),(697,'RtBF/1GQ9uqTc52mwKoZvA=='),(698,'RtBF/1GQ9uqTc52mwKoZvA=='),(723,'12345'),(725,'RtBF/1GQ9uqTc52mwKoZvA=='),(726,'RtBF/1GQ9uqTc52mwKoZvA=='),(727,'RtBF/1GQ9uqTc52mwKoZvA=='),(754,'RtBF/1GQ9uqTc52mwKoZvA=='),(755,'RtBF/1GQ9uqTc52mwKoZvA=='),(756,'RtBF/1GQ9uqTc52mwKoZvA=='),(763,'12345'),(783,'RtBF/1GQ9uqTc52mwKoZvA=='),(784,'RtBF/1GQ9uqTc52mwKoZvA=='),(790,'RtBF/1GQ9uqTc52mwKoZvA=='),(791,'RtBF/1GQ9uqTc52mwKoZvA=='),(812,'RtBF/1GQ9uqTc52mwKoZvA=='),(813,'RtBF/1GQ9uqTc52mwKoZvA=='),(819,'RtBF/1GQ9uqTc52mwKoZvA=='),(820,'RtBF/1GQ9uqTc52mwKoZvA=='),(849,'RtBF/1GQ9uqTc52mwKoZvA=='),(850,'RtBF/1GQ9uqTc52mwKoZvA=='),(860,'RtBF/1GQ9uqTc52mwKoZvA=='),(861,'RtBF/1GQ9uqTc52mwKoZvA==');
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
INSERT INTO `userAccount` VALUES ('123456789','Yu','male',22,'manager',12,NULL,NULL);
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
