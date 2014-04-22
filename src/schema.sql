CREATE DATABASE  IF NOT EXISTS `pong2` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pong2`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: athens-db    Database: pong2
-- ------------------------------------------------------
-- Server version	5.0.90-community-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `type` varchar(20) NOT NULL,
  `uses_teams` tinyint(4) NOT NULL default '0',
  `max_players` smallint(6) default NULL,
  `min_players` smallint(6) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `type_UNIQUE` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (1,'Ping Pong','PING_PONG',0,2,2),(2,'Ping Pong Doubles','PING_PONG_DOUBLES',1,4,4),(3,'Foosball','FOOSBALL',0,NULL,NULL),(4,'Foosball Doubles','FOOSBALL_DOUBLES',1,4,4);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league`
--

DROP TABLE IF EXISTS `league`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `abbr` varchar(10) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `group_owner_to_player_fk_idx` (`owner_id`),
  KEY `group_creator_to_player_fk_idx` (`creator_id`),
  CONSTRAINT `group_creator_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `group_owner_to_player_fk` FOREIGN KEY (`owner_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league`
--

LOCK TABLES `league` WRITE;
/*!40000 ALTER TABLE `league` DISABLE KEYS */;
INSERT INTO `league` VALUES (1,'Performance Matters','PMI',1,'2014-04-13 01:01:06',1);
/*!40000 ALTER TABLE `league` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league_games`
--

DROP TABLE IF EXISTS `league_games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league_games` (
  `id` int(11) NOT NULL auto_increment,
  `league_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `sort_order` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `league_games_to_game_fk_idx` (`game_id`),
  KEY `leage_games_to_league_fk_idx` (`league_id`),
  CONSTRAINT `leage_games_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_games_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league_games`
--

LOCK TABLES `league_games` WRITE;
/*!40000 ALTER TABLE `league_games` DISABLE KEYS */;
INSERT INTO `league_games` VALUES (1,1,1,1),(2,1,2,2),(3,1,3,3);
/*!40000 ALTER TABLE `league_games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league_players`
--

DROP TABLE IF EXISTS `league_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league_players` (
  `league_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY  (`league_id`,`player_id`),
  KEY `group_players_to_player_fk_idx` (`player_id`),
  CONSTRAINT `league_players_to_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `league_players_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league_players`
--

LOCK TABLES `league_players` WRITE;
/*!40000 ALTER TABLE `league_players` DISABLE KEYS */;
INSERT INTO `league_players` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,17),(1,18),(1,19);
/*!40000 ALTER TABLE `league_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `player` (
  `id` int(11) NOT NULL auto_increment,
  `first_name` varchar(255) default NULL,
  `last_name` varchar(255) default NULL,
  `login` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `active_flag` tinyint(4) NOT NULL default '1',
  `super_user_flag` tinyint(4) NOT NULL default '0',
  `current_league` int(11) default NULL,
  `last_login_date` timestamp NULL default NULL,
  `create_date` timestamp NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`id`),
  KEY `player_current_group_to_group_fk_idx` (`current_league`),
  CONSTRAINT `player_current_league_to_league_fk` FOREIGN KEY (`current_league`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (1,'Jason','Ergle','ergle545','password','King Dingaling',1,1,1,'2014-04-21 23:19:39','2014-04-15 01:28:27'),(2,'Steven','Smith','steven.smith','password','Big Baldy',1,0,1,'2014-04-21 21:03:10','2014-04-15 01:28:27'),(3,'Kurt','Davis','kurt.davis','password','Mr Backspin',1,1,1,'2014-04-22 03:17:37','2014-04-15 01:28:27'),(4,'Brandon','Stewart','brandon.stewart','password','Little Baldy',1,0,1,'2014-04-18 17:07:09','2014-04-15 01:28:27'),(5,'James','Waters','james.waters','password','Boss Man',1,0,1,NULL,'2014-04-15 01:28:27'),(6,'Arun','Velgapalli','arun.velgapalli','password','CSX Sux',1,0,1,'2014-04-18 13:49:26','2014-04-15 01:28:27'),(7,'Nick','Coniglio','nick.coniglio','password','Data Man',1,0,1,'2014-04-15 12:39:17','2014-04-15 01:28:27'),(8,'Todd','Greenberg','todd.greenburg','password','Pony Master',1,0,1,'2014-04-21 17:28:44','2014-04-15 01:28:27'),(9,'Alex','Feliciano','alex.feliciano','password','css rulez',1,0,1,'2014-04-15 13:03:54','2014-04-15 01:28:27'),(10,'Randall','Stanley','randall.stanley','password','Chipolte',1,0,1,NULL,'2014-04-15 01:28:27'),(17,'Woody','Dillaha','woody','password','Big Man on Campus',1,0,1,NULL,'2014-04-15 01:28:27'),(18,'Pavan','Kumar','pavan.kumar','password','SELECT *',1,0,1,'2014-04-21 17:39:35','2014-04-15 01:28:27'),(19,'Kelly','Morrison','kelly.morrison','password','Athens Correctional League',1,0,1,'2014-04-15 13:55:56','2014-04-15 13:55:31');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(45) NOT NULL,
  `league_id` int(11) NOT NULL,
  `creator_id` int(11) NOT NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `captain_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `team_to_group_idx` (`league_id`),
  KEY `team_creator_to_player_fk_idx` (`creator_id`),
  KEY `team_captain_id_to_player_fk_idx` (`captain_id`),
  CONSTRAINT `team_captain_id_to_player_fk` FOREIGN KEY (`captain_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_creator_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (4,'Kurt & Jason',1,1,'2014-04-14 13:42:14',NULL),(5,'Steven & Arun',1,1,'2014-04-14 13:42:17',NULL),(6,'Steven & Jason',1,3,'2014-04-22 02:20:12',NULL),(7,'Arun & Kurt',1,3,'2014-04-22 02:20:12',NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_players`
--

DROP TABLE IF EXISTS `team_players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_players` (
  `team_id` int(11) NOT NULL,
  `player_id` int(11) NOT NULL,
  PRIMARY KEY  (`team_id`,`player_id`),
  KEY `team_players_to_player_fk_idx` (`player_id`),
  CONSTRAINT `team_players_to_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `team_players_to_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_players`
--

LOCK TABLES `team_players` WRITE;
/*!40000 ALTER TABLE `team_players` DISABLE KEYS */;
INSERT INTO `team_players` VALUES (4,1),(6,1),(5,2),(6,2),(4,3),(7,3),(5,6),(7,6);
/*!40000 ALTER TABLE `team_players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versus_entry`
--

DROP TABLE IF EXISTS `versus_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versus_entry` (
  `id` int(11) NOT NULL auto_increment,
  `match_id` int(11) NOT NULL,
  `player_id` int(11) default NULL,
  `team_id` int(11) default NULL,
  `score` decimal(6,0) NOT NULL default '0',
  PRIMARY KEY  (`id`),
  KEY `versus_entry_to_versus_match_idx` (`match_id`),
  KEY `versus_entry_to_player_idx` (`player_id`),
  KEY `versus_entry_to_team_idx` (`team_id`),
  CONSTRAINT `versus_entry_to_versus_match_fk` FOREIGN KEY (`match_id`) REFERENCES `versus_match` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `versus_entry_to_player_fk` FOREIGN KEY (`player_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `versus_entry_to_team_fk` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versus_entry`
--

LOCK TABLES `versus_entry` WRITE;
/*!40000 ALTER TABLE `versus_entry` DISABLE KEYS */;
INSERT INTO `versus_entry` VALUES (27,29,17,NULL,21),(28,30,3,NULL,21),(29,31,5,NULL,21),(30,32,2,NULL,22),(31,33,5,NULL,21),(32,34,1,NULL,21),(33,35,1,NULL,21),(34,36,3,NULL,21),(35,37,8,NULL,22),(36,38,3,NULL,11),(37,39,2,NULL,21),(38,40,1,NULL,21),(39,41,2,NULL,22),(40,42,6,NULL,21),(41,43,6,NULL,21),(42,44,18,NULL,21),(43,45,18,NULL,21),(45,47,8,NULL,21),(46,48,8,NULL,21),(47,29,3,NULL,16),(48,30,17,NULL,17),(49,31,6,NULL,19),(50,32,4,NULL,20),(51,33,1,NULL,19),(52,34,6,NULL,16),(53,35,6,NULL,19),(54,36,17,NULL,17),(55,37,5,NULL,20),(56,38,1,NULL,8),(57,39,3,NULL,9),(58,40,9,NULL,19),(59,41,4,NULL,20),(60,42,9,NULL,8),(61,43,9,NULL,18),(62,44,1,NULL,14),(63,45,9,NULL,15),(64,46,6,NULL,14),(65,47,5,NULL,16),(66,48,5,NULL,19),(67,46,1,NULL,21),(76,53,18,NULL,12),(77,53,4,NULL,21),(78,54,3,NULL,16),(79,54,4,NULL,21),(80,55,18,NULL,21),(81,55,8,NULL,14),(84,57,3,NULL,21),(85,57,6,NULL,8),(86,58,3,NULL,21),(87,58,6,NULL,14),(88,59,6,NULL,21),(89,59,9,NULL,7),(90,60,9,NULL,14),(91,60,6,NULL,21),(92,61,3,NULL,15),(93,61,4,NULL,21),(94,62,4,NULL,21),(95,62,3,NULL,12),(96,63,8,NULL,21),(97,63,5,NULL,14),(98,64,8,NULL,21),(99,64,5,NULL,15),(102,66,9,NULL,21),(103,66,1,NULL,19),(104,67,2,NULL,21),(105,67,4,NULL,7),(106,68,4,NULL,15),(107,68,2,NULL,21),(108,69,1,NULL,8),(109,69,3,NULL,21),(110,70,5,NULL,21),(111,70,1,NULL,8),(114,72,2,NULL,21),(115,72,3,NULL,5),(116,73,NULL,4,21),(117,73,NULL,5,17),(118,74,NULL,4,18),(119,74,NULL,5,21),(120,75,NULL,4,16),(121,75,NULL,5,21),(122,76,NULL,4,18),(123,76,NULL,5,21),(124,77,NULL,4,21),(125,77,NULL,5,11),(126,78,NULL,4,11),(127,78,NULL,5,21),(128,79,NULL,7,12),(129,79,NULL,6,21),(130,80,NULL,7,11),(131,80,NULL,6,21),(132,81,NULL,7,21),(133,81,NULL,6,17),(134,82,1,NULL,8),(135,82,2,NULL,21);
/*!40000 ALTER TABLE `versus_entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versus_match`
--

DROP TABLE IF EXISTS `versus_match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versus_match` (
  `id` int(11) NOT NULL auto_increment,
  `game_id` int(11) NOT NULL,
  `league_id` int(11) NOT NULL,
  `is_complete` tinyint(4) NOT NULL default '1',
  `title` varchar(45) default NULL,
  `match_date` timestamp NULL default NULL,
  `create_date` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `creator_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `match_creator_to_player_fk_idx` (`game_id`),
  KEY `match_to_group_fk_idx` (`league_id`),
  KEY `match_player_creator_to_player_fk_idx` (`creator_id`),
  CONSTRAINT `versus_match_to_game_fk` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `versus_match_to_league_fk` FOREIGN KEY (`league_id`) REFERENCES `league` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `versus_match_to_player_fk` FOREIGN KEY (`creator_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versus_match`
--

LOCK TABLES `versus_match` WRITE;
/*!40000 ALTER TABLE `versus_match` DISABLE KEYS */;
INSERT INTO `versus_match` VALUES (29,1,1,1,NULL,'2014-04-14 09:20:11','2014-04-14 09:20:11',1),(30,1,1,1,NULL,'2014-04-10 09:21:30','2014-04-14 09:21:30',1),(31,1,1,1,NULL,'2014-04-14 09:22:22','2014-04-14 09:22:22',1),(32,1,1,1,NULL,'2014-04-14 14:56:37','2014-04-14 14:56:37',1),(33,1,1,1,NULL,'2014-04-14 14:56:59','2014-04-14 14:56:59',1),(34,1,1,1,NULL,'2014-04-14 15:59:23','2014-04-14 15:59:24',1),(35,1,1,1,NULL,'2014-04-14 21:05:21','2014-04-14 21:05:21',1),(36,1,1,1,NULL,'2014-04-10 01:50:57','2014-04-15 01:50:57',3),(37,1,1,1,NULL,'2014-04-16 13:41:35','2014-04-16 13:41:35',1),(38,1,1,1,NULL,'2014-04-16 15:32:20','2014-04-16 15:32:20',1),(39,1,1,1,NULL,'2014-04-16 17:40:27','2014-04-16 17:40:27',3),(40,1,1,1,NULL,'2014-04-16 18:16:43','2014-04-16 18:16:43',1),(41,1,1,1,NULL,'2014-04-16 19:20:05','2014-04-16 19:20:05',1),(42,1,1,1,NULL,'2014-04-16 19:44:40','2014-04-16 19:44:40',3),(43,1,1,1,NULL,'2014-04-16 19:44:47','2014-04-16 19:44:47',3),(44,1,1,1,NULL,'2014-04-16 20:06:17','2014-04-16 20:06:17',1),(45,1,1,1,NULL,'2014-04-16 20:16:08','2014-04-16 20:16:08',1),(46,1,1,1,NULL,'2014-04-16 20:27:22','2014-04-16 20:27:22',1),(47,1,1,1,NULL,'2014-04-17 15:06:26','2014-04-17 15:06:26',8),(48,1,1,1,NULL,'2014-04-17 15:06:51','2014-04-17 15:06:51',8),(53,1,1,1,NULL,'2014-04-18 17:05:32','2014-04-18 17:05:32',4),(54,1,1,1,NULL,'2014-04-18 17:07:34','2014-04-18 17:07:34',4),(55,1,1,1,NULL,'2014-04-18 19:39:08','2014-04-18 19:39:08',8),(57,1,1,1,NULL,'2014-04-18 21:03:41','2014-04-18 21:03:41',3),(58,1,1,1,NULL,'2014-04-18 21:03:53','2014-04-18 21:03:53',3),(59,1,1,1,NULL,'2014-04-18 21:04:08','2014-04-18 21:04:08',3),(60,1,1,1,NULL,'2014-04-18 21:04:26','2014-04-18 21:04:26',3),(61,1,1,1,NULL,'2014-04-21 16:02:34','2014-04-21 16:02:34',1),(62,1,1,1,NULL,'2014-04-21 16:06:11','2014-04-21 16:06:11',1),(63,1,1,1,NULL,'2014-04-21 17:29:09','2014-04-21 17:29:09',8),(64,1,1,1,NULL,'2014-04-21 17:29:23','2014-04-21 17:29:23',8),(66,1,1,1,NULL,'2014-04-21 18:31:47','2014-04-21 18:31:47',1),(67,1,1,1,NULL,'2014-04-21 19:16:02','2014-04-21 19:16:02',2),(68,1,1,1,NULL,'2014-04-21 19:16:19','2014-04-21 19:16:19',2),(69,1,1,1,NULL,'2014-04-21 19:24:49','2014-04-21 19:24:49',1),(70,1,1,1,NULL,'2014-04-21 19:30:05','2014-04-21 19:30:05',1),(72,1,1,1,NULL,'2014-04-21 20:42:15','2014-04-21 20:42:15',3),(73,2,1,1,NULL,'2014-04-22 02:16:10','2014-04-22 02:16:10',3),(74,2,1,1,NULL,'2014-04-22 02:16:37','2014-04-22 02:16:37',3),(75,2,1,1,NULL,'2014-04-22 02:16:50','2014-04-22 02:16:50',3),(76,2,1,1,NULL,'2014-04-22 02:17:01','2014-04-22 02:17:01',3),(77,2,1,1,NULL,'2014-04-22 02:17:13','2014-04-22 02:17:13',3),(78,2,1,1,NULL,'2014-04-22 02:17:46','2014-04-22 02:17:46',3),(79,2,1,1,NULL,'2014-04-22 02:23:52','2014-04-22 02:23:52',3),(80,2,1,1,NULL,'2014-04-22 02:23:58','2014-04-22 02:23:58',3),(81,2,1,1,NULL,'2014-04-22 02:24:15','2014-04-22 02:24:15',3),(82,1,1,1,NULL,'2014-04-21 23:12:17','2014-04-21 23:12:17',1);
/*!40000 ALTER TABLE `versus_match` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-21 23:26:40
