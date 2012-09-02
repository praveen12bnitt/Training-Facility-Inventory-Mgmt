-- MySQL dump 10.13  Distrib 5.5.27, for osx10.6 (i386)
--
-- Host: localhost    Database: invtmgmt
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Current Database: `invtmgmt`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `invtmgmt` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `invtmgmt`;

--
-- Table structure for table `CLASS`
--

DROP TABLE IF EXISTS `CLASS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLASS` (
  `CLASS_NAME` varchar(50) NOT NULL,
  `CLASS_DESC` varchar(100) NOT NULL,
  `KIT_ID` int(11) DEFAULT NULL,
  `TRAINEE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`CLASS_NAME`),
  UNIQUE KEY `Unique` (`CLASS_NAME`),
  KEY `FK_CLASS_TRAINEE` (`TRAINEE_ID`),
  KEY `FK_CLASS_KIT` (`KIT_ID`),
  KEY `CLASS_NAME` (`CLASS_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLASS`
--

LOCK TABLES `CLASS` WRITE;
/*!40000 ALTER TABLE `CLASS` DISABLE KEYS */;
INSERT INTO `CLASS` VALUES ('a','a',NULL,NULL),('ARM','test class',1,1),('b','d',NULL,NULL),('c','c',NULL,NULL);
/*!40000 ALTER TABLE `CLASS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLASS_KIT_MAPPING`
--

DROP TABLE IF EXISTS `CLASS_KIT_MAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLASS_KIT_MAPPING` (
  `CLASS_NAME` varchar(25) NOT NULL,
  `KIT_ID` int(11) NOT NULL,
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLASS_KIT_MAPPING`
--

LOCK TABLES `CLASS_KIT_MAPPING` WRITE;
/*!40000 ALTER TABLE `CLASS_KIT_MAPPING` DISABLE KEYS */;
/*!40000 ALTER TABLE `CLASS_KIT_MAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INVENTORY`
--

DROP TABLE IF EXISTS `INVENTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INVENTORY` (
  `SKU_CODE` varchar(30) NOT NULL,
  `LOCATION_ID` int(11) NOT NULL,
  `AVAILABLE_QTY` int(11) NOT NULL,
  `ISSUED_QTY` int(11) NOT NULL,
  `UNUSABLE_QTY` int(11) NOT NULL,
  `MISSING_QTY` int(11) DEFAULT NULL,
  `DAMAGED_QTY` int(11) DEFAULT NULL,
  PRIMARY KEY (`SKU_CODE`,`LOCATION_ID`),
  KEY `locfk` (`LOCATION_ID`),
  CONSTRAINT `locfk` FOREIGN KEY (`LOCATION_ID`) REFERENCES `LOCATION` (`LOCATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INVENTORY`
--

LOCK TABLES `INVENTORY` WRITE;
/*!40000 ALTER TABLE `INVENTORY` DISABLE KEYS */;
/*!40000 ALTER TABLE `INVENTORY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEM`
--

DROP TABLE IF EXISTS `ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEM` (
  `ITEM_ID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `ITEM_DESC` varchar(200) DEFAULT NULL,
  `REQUIRES_PROCESSING` varchar(10) DEFAULT 'Y',
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEM`
--

LOCK TABLES `ITEM` WRITE;
/*!40000 ALTER TABLE `ITEM` DISABLE KEYS */;
INSERT INTO `ITEM` VALUES (1,'ATF Student  Investigator Jacket','ATF Student  Investigator Jacket','Y'),(2,'ATF Student Investigator Polo Shirt','ATF Student Investigator Polo Shirt','Y'),(3,'ATF Student Investigator Sweatshirt','ATF Student Investigator Sweatshirt','Y'),(4,'ATF Tactical 5.11 Cargo Pants (Men\'s)','ATF Tactical 5.11 Cargo Pants (Men\'s)','Y'),(5,'ATF Tactical 5.11 Cargo Pants (Women\'s)','ATF Tactical 5.11 Cargo Pants (Women\'s)','Y'),(6,'Athletic Supporter','Athletic Supporter','Y'),(7,'Belt Buckle','Belt Buckle','Y'),(8,'BDU Pants','BDU Pants','Y'),(9,'BDU Shorts','BDU Shorts','Y'),(10,'BOP BDU Pants','BOP BDU Pants','Y'),(11,'BOP Belt','BOP Belt','Y'),(12,'BOP Belt Buckle','BOP Belt Buckle','Y'),(13,'BOP Book Bag','BOP Book Bag','Y'),(14,'BOP Dress Shirt (Men\'s)','BOP Dress Shirt (Men\'s)','Y'),(15,'BOP Dress Shirt (Women\'s)','BOP Dress Shirt (Women\'s)','Y'),(16,'BOP Laundry Bag','BOP Laundry Bag','Y'),(17,'BOP Student Lightweight Jacket','BOP Student Lightweight Jacket','Y'),(18,'BOP Student Heavyweight Jacket','BOP Student Heavyweight Jacket','Y'),(19,'BOP Student Bus Polo Shirt','BOP Student Bus Polo Shirt','Y'),(20,'BOP Student Polo Shirt','BOP Student Polo Shirt','Y'),(21,'BOP Tie (Men\'s)','BOP Tie (Men\'s)','Y'),(22,'BOP Tie (Women\'s)','BOP Tie (Women\'s)','Y'),(23,'BOP Utility Pants (Men\'s)','BOP Utility Pants (Men\'s)','Y'),(24,'BOP Utility Pants (Women\'s)','BOP Utility Pants (Women\'s)','Y'),(25,'CBP Student Lightweight Jacket','CBP Student Lightweight Jacket','Y'),(26,'CBP AMB Student Heavyweight Jacket','CBP AMB Student Heavyweight Jacket','Y'),(27,'CBP AMB Student Polo Shirt','CBP AMB Student Polo Shirt','Y'),(28,'CBP Student Utility Shirt','CBP Student Utility Shirt','Y'),(29,'CITP Student Polo Shirt (Men\'s)','CITP Student Polo Shirt (Men\'s)','Y'),(30,'CITP Student Polo Shirt (Women\'s)','CITP Student Polo Shirt (Women\'s)','Y'),(31,'Coaches Shorts w/wo Loops','Coaches Shorts w/wo Loops','Y'),(32,'Nylon Shorts','Nylon Shorts','Y'),(33,'Dept of Treasury \"Special Agent\" IG for Tax Administration Student Polo Shirt (TIGTA)','Dept of Treasury \"Special Agent\" IG for Tax Administration Student Polo Shirt (TIGTA)','Y'),(34,'DMD Staff Polo Shirt','DMD Staff Polo Shirt','Y'),(35,'FAD Staff Polo Shirt','FAD Staff Polo Shirt','Y'),(36,'Fletc Polo (Jerzees)','Fletc Polo (Jerzees)','Y'),(37,'Fletc Polo Mens (Chestnut Hill)','Fletc Polo Mens (Chestnut Hill)','Y'),(38,'Fletc Polo Women (Chestnut Hill)','Fletc Polo Women (Chestnut Hill)','Y'),(39,'FLETC Instructor Cap','FLETC Instructor Cap','Y'),(40,'FLETC Instructor Heavyweight Jacket','FLETC Instructor Heavyweight Jacket','Y'),(41,'FLETC Instructor Lightweight Jacket','FLETC Instructor Lightweight Jacket','Y'),(42,'FLETC Instructor Polo Shirt','FLETC Instructor Polo Shirt','Y'),(43,'FLETC Instructor Sweatshirt','FLETC Instructor Sweatshirt','Y'),(44,'FLETC Intern Polo Shirts (Men\'s)','FLETC Intern Polo Shirts (Men\'s)','Y'),(45,'FLETC Intern Polo Shirts (Women\'s)','FLETC Intern Polo Shirts (Women\'s)','Y'),(47,'FLETC Staff Cap','FLETC Staff Cap','Y'),(48,'FLETC Staff Lightweight Jacket','FLETC Staff Lightweight Jacket','Y'),(49,'FLETC Staff Sweatshirt','FLETC Staff Sweatshirt','Y'),(50,'FLETC Student Lightweight Jacket','FLETC Student Lightweight Jacket','Y'),(51,'FLETC Basic Student Polo Shirt','FLETC Basic Student Polo Shirt','Y'),(52,'FLETC Advanced Student Sweatpants','FLETC Advanced Student Sweatpants','Y'),(53,'FLETC Basic Student Sweatpants','FLETC Basic Student Sweatpants','Y'),(54,'FLETC Advanced Student Sweatshirt','FLETC Advanced Student Sweatshirt','Y'),(55,'FLETC Basic Student Sweatshirt','FLETC Basic Student Sweatshirt','Y'),(56,'FLETC Advanced Student Gym Shorts','FLETC Advanced Student Gym Shorts','Y'),(57,'FLETC Basic Student Gym Shorts','FLETC Basic Student Gym Shorts','Y'),(58,'FLETC Advanced Student Gym Shirts','FLETC Advanced Student Gym Shirts','Y'),(59,'FLETC Basic Student Gym Shirts','FLETC Basic Student Gym Shirts','Y'),(60,'FLETC Inspection Staff Polo Shirts','FLETC Inspection Staff Polo Shirts','Y'),(61,'FMD Operations Staff Polo Shirt','FMD Operations Staff Polo Shirt','Y'),(62,'FMD Staff Welder\'s Long Sleeve Shirt','FMD Staff Welder\'s Long Sleeve Shirt','Y'),(63,'FMD Staff Welder\'s Short Sleeve Shirt','FMD Staff Welder\'s Short Sleeve Shirt','Y'),(64,'FMD Staff Welder\'s Utility Pants','FMD Staff Welder\'s Utility Pants','Y'),(65,'HHS OIG Student Polo Shirts','HHS OIG Student Polo Shirts','Y'),(66,'ICE Student Lightweight Jacket','ICE Student Lightweight Jacket','Y'),(67,'ICE Student \"Officer\" Polo Shirt','ICE Student \"Officer\" Polo Shirt','Y'),(68,'ICE Student \"Special Agent\" Polo Shirt','ICE Student \"Special Agent\" Polo Shirt','Y'),(69,'Instructor 5.11 Tac Lite (Men\'s)','Instructor 5.11 Tac Lite (Men\'s)','Y'),(70,'Instructor 5.11 Tac Lite (Women\'s)','Instructor 5.11 Tac Lite (Women\'s)','Y'),(71,'IRS CI Student Polo Shirt-NEW','IRS CI Student Polo Shirt-NEW','Y'),(72,'IRS CID Student Polo Shirt (Old Patch)','IRS CID Student Polo Shirt (Old Patch)','Y'),(73,'IRS Student Lightweight Jacket<NEW>','IRS Student Lightweight Jacket<NEW>','Y'),(74,'IRS Student Lightweight Jacket (Old Patch)','IRS Student Lightweight Jacket (Old Patch)','Y'),(75,'LMPT Student Cap','LMPT Student Cap','Y'),(76,'LMPT Student Heavyweight Jacket','LMPT Student Heavyweight Jacket','Y'),(77,'LMPT Student Raincoat','LMPT Student Raincoat','Y'),(78,'LMPT Student Raincoat Hood','LMPT Student Raincoat Hood','Y'),(79,'LMPT Student Utility Shirt (Men\'s)','LMPT Student Utility Shirt (Men\'s)','Y'),(80,'LMPT Student Utility Shirt (Women\'s)','LMPT Student Utility Shirt (Women\'s)','Y'),(81,'NCIS Student Lightweight Jacket','NCIS Student Lightweight Jacket','Y'),(82,'NCIS Student Lightweight Jacket (Old Patch) (HROTP)','NCIS Student Lightweight Jacket (Old Patch) (HROTP)','Y'),(83,'NCIS Student \"Special Agent\" Polo Shirt','NCIS Student \"Special Agent\" Polo Shirt','Y'),(84,'NCIS Student Polo Shirt (Old Patch) (HROTP)','NCIS Student Polo Shirt (Old Patch) (HROTP)','Y'),(85,'NCIS Student Sweatshirt','NCIS Student Sweatshirt','Y'),(86,'OIG \"IG Academy Special Agent\" Polo Shirt','OIG \"IG Academy Special Agent\" Polo Shirt','Y'),(87,'PMD Staff Polo Shirt (Men\'s)','PMD Staff Polo Shirt (Men\'s)','Y'),(88,'PMD Staff Polo Shirt (Women\'s)','PMD Staff Polo Shirt (Women\'s)','Y'),(89,'Shower Shoes','Shower Shoes','Y'),(90,'Socks (Men\'s & Women\'s)','Socks (Men\'s & Women\'s)','Y'),(91,'Socks (Men\'s)','Socks (Men\'s)','Y'),(92,'Socks (Women\'s)','Socks (Women\'s)','Y'),(93,'Sports Bra','Sports Bra','Y'),(94,'Swimtrunks (Men\'s)','Swimtrunks (Men\'s)','Y'),(95,'Swimsuit (Women\'s)','Swimsuit (Women\'s)','Y'),(96,'Tactical 5.11 Cargo Pants (Men\'s)','Tactical 5.11 Cargo Pants (Men\'s)','Y'),(97,'Tactical 5.11 Cargo Pants (Women\'s)','Tactical 5.11 Cargo Pants (Women\'s)','Y'),(98,'Tennis Shoes','Tennis Shoes','Y'),(99,'Towel','Towel','Y'),(100,'UPTP Student Utility Shirt-MEN','UPTP Student Utility Shirt-MEN','Y'),(101,'UPTP Student Utility Shirt-Women','UPTP Student Utility Shirt-Women','Y'),(102,'USCG Student Polo Shirt','USCG Student Polo Shirt','Y'),(103,'USCG Student Jacket','USCG Student Jacket','Y'),(104,'USMS Student Lightweight Jacket','USMS Student Lightweight Jacket','Y'),(105,'USMS Student Utility Shirt','USMS Student Utility Shirt','Y'),(106,'Utility Pants (Men\'s)','Utility Pants (Men\'s)','Y'),(107,'Utility Pants (Women\'s)','Utility Pants (Women\'s)','Y'),(108,'Web Belt','Web Belt','Y');
/*!40000 ALTER TABLE `ITEM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEM_ATTRIBUTE`
--

DROP TABLE IF EXISTS `ITEM_ATTRIBUTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEM_ATTRIBUTE` (
  `ATTR_ID` int(11) NOT NULL,
  `ATTR_NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ATTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEM_ATTRIBUTE`
--

LOCK TABLES `ITEM_ATTRIBUTE` WRITE;
/*!40000 ALTER TABLE `ITEM_ATTRIBUTE` DISABLE KEYS */;
INSERT INTO `ITEM_ATTRIBUTE` VALUES (1,'COLOR'),(2,'SIZE');
/*!40000 ALTER TABLE `ITEM_ATTRIBUTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEM_ATTRIBUTE_VALUES`
--

DROP TABLE IF EXISTS `ITEM_ATTRIBUTE_VALUES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEM_ATTRIBUTE_VALUES` (
  `ATTR_VALUE_ID` int(11) NOT NULL,
  `ATTR_VALUE` varchar(20) NOT NULL,
  PRIMARY KEY (`ATTR_VALUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEM_ATTRIBUTE_VALUES`
--

LOCK TABLES `ITEM_ATTRIBUTE_VALUES` WRITE;
/*!40000 ALTER TABLE `ITEM_ATTRIBUTE_VALUES` DISABLE KEYS */;
INSERT INTO `ITEM_ATTRIBUTE_VALUES` VALUES (1,'Black'),(2,'White'),(3,'Grey'),(4,'Brass'),(5,'Navy Blue'),(6,'Khaki'),(7,'Dark Blue'),(8,'Blue'),(9,'Dark Blue'),(10,'Maroon'),(11,'S'),(12,'M'),(13,'L'),(14,'XL'),(15,'2X'),(16,'3X'),(17,'28X30'),(18,'28X32'),(19,'28X34'),(20,'28X36'),(21,'30X30'),(22,'30X32'),(23,'30X34'),(24,'30X36'),(25,'32X30'),(26,'32X32'),(27,'32X34'),(28,'32X36'),(29,'34X30'),(30,'34X32'),(31,'34X34'),(32,'34X36'),(33,'36X30'),(34,'36X32'),(35,'36X34'),(36,'36X36'),(37,'38X30'),(38,'38X32'),(39,'38X34'),(40,'38X36'),(41,'40X30'),(42,'40X32'),(43,'40X34'),(44,'40X36'),(45,'42X30'),(46,'42X32'),(47,'42X34'),(48,'42X36'),(49,'44X28'),(50,'44X30'),(51,'44X32'),(52,'44X34'),(53,'44X36'),(54,'46X30'),(55,'46X32'),(56,'46X34'),(57,'46X36'),(58,'48X30'),(59,'48X32'),(60,'48X34'),(61,'50X30'),(62,'54X34'),(63,'54X36'),(64,'2X30'),(65,'4X30'),(66,'6X30'),(67,'8X30'),(68,'10X30'),(69,'12X30'),(70,'14X30'),(71,'14X32'),(72,'16X32'),(73,'N/A'),(74,'XS/S'),(75,'XS/R'),(76,'XS/L'),(77,'S/S'),(78,'S/R'),(79,'S/L'),(80,'S/XL'),(81,'M/S'),(82,'M/R'),(83,'M/L'),(84,'M/XL'),(85,'L/S'),(86,'L/R'),(87,'L/L'),(88,'L/XL'),(89,'XL/S'),(90,'XL/R'),(91,'XL/L'),(92,'XL/XL'),(93,'2X/S'),(94,'2X/R'),(95,'2X/L'),(96,'2X/XL'),(97,'3X/S'),(98,'3X/R'),(99,'3X/L'),(100,'3X/XL'),(101,'4X/S'),(102,'4X/R'),(103,'4X/L'),(104,'5X/S'),(105,'5X/R'),(106,'XS'),(107,'4X'),(108,'5X'),(109,'6X/R'),(110,'15'),(111,'15 1/2'),(112,'16'),(113,'16 1/2'),(114,'17'),(115,'17 1/2'),(116,'18'),(117,'18 1/2'),(118,'19'),(119,'20'),(120,'22'),(121,'24'),(122,'26'),(123,'28 1/2'),(124,'6'),(125,'8'),(126,'10'),(127,'12'),(128,'14'),(129,'6X'),(130,'7X'),(131,'8X'),(132,'28X28'),(133,'30X28'),(134,'32X28'),(135,'34X28'),(136,'36X28'),(137,'38X28'),(138,'40X28'),(139,'46X28'),(140,'48X28'),(141,'48X36'),(142,'50X28'),(143,'50X32'),(144,'50X34'),(145,'52X28'),(146,'52X30'),(147,'52X32'),(148,'52X34'),(149,'52X36'),(150,'54X28'),(151,'54X30'),(152,'54X32'),(153,'62X30'),(154,'6X28'),(155,'6X32'),(156,'6X34'),(157,'8X26'),(158,'8X28'),(159,'8X32'),(160,'8X34'),(161,'10X28'),(162,'10X32'),(163,'10X34'),(164,'12X26'),(165,'12X28'),(166,'12X32'),(167,'12X34'),(168,'14X28'),(169,'14X34'),(170,'16X28'),(171,'16X30'),(172,'16X34'),(173,'18X26'),(174,'18X28'),(175,'18X30'),(176,'18X32'),(177,'18X34'),(178,'20X26'),(179,'20X28'),(180,'20X30'),(181,'20X32'),(182,'20X34'),(183,'22X26'),(184,'22X28'),(185,'22X30'),(186,'22X32'),(187,'22X34'),(188,'24X28'),(189,'24X30'),(190,'24X32'),(191,'24X34'),(192,'UNK'),(193,'Sport Grey'),(194,'Navy'),(195,'Kelly Green'),(196,''),(197,'Light Blue'),(198,'Royal Blue'),(199,'Ash Grey'),(200,'Heather Blue'),(201,'Tan'),(202,'36UH'),(203,'40UH'),(204,'2/R'),(205,'2/L'),(206,'4/R'),(207,'4/L'),(208,'6/R'),(209,'6/L'),(210,'8/R'),(211,'8/L'),(212,'10/R'),(213,'10/L'),(214,'12/R'),(215,'12/L'),(216,'14/R'),(217,'14/L'),(218,'Dusty Blue'),(219,'M-6/7'),(220,'L-8/9'),(221,'XL-10/12'),(222,'2X-13/15'),(223,'10-13'),(224,'13-15'),(225,'81/2-11'),(226,'Blue/Gold'),(227,'9-11'),(228,'28UH'),(229,'36X26'),(230,'42X26'),(231,'46UH'),(232,'50UH'),(233,'52UH'),(234,'54UH'),(235,'4X32'),(236,'5'),(237,'5 1/2'),(238,'6 1/2'),(239,'7'),(240,'7 1/2'),(241,'8 1/2'),(242,'9'),(243,'9 1/2'),(244,'10 1/2'),(245,'11'),(246,'11 1/2'),(247,'12 1/2'),(248,'13'),(249,'13 1/2'),(250,'14 1/2'),(251,'27X50'),(252,'Rose'),(253,'28X29'),(254,'28X33'),(255,'29X28'),(256,'29X29'),(257,'29X30'),(258,'29X32'),(259,'29X33'),(260,'29X34'),(261,'29UH'),(262,'30X29'),(263,'30X31'),(264,'30X33'),(265,'30X35'),(266,'30UH'),(267,'31X28'),(268,'31X29'),(269,'31X30'),(270,'31X32'),(271,'31X33'),(272,'31X34'),(273,'31UH'),(274,'32X26'),(275,'32X29'),(276,'32UH'),(277,'33X26'),(278,'33X27'),(279,'33X28'),(280,'33X29'),(281,'33X30'),(282,'33X31'),(283,'33X32'),(284,'33X33'),(285,'33X34'),(286,'33X35'),(287,'33UH'),(288,'34UH'),(289,'38X26'),(290,'42X28'),(291,'42UH'),(292,'44X26'),(293,'44UH'),(294,'48UH'),(295,'56UH'),(296,'58UH'),(297,'60UH'),(298,'G12'),(299,'0X26'),(300,'0X27.5'),(301,'0X28'),(302,'0X30'),(303,'0X32'),(304,'1X27'),(305,'2X26'),(306,'2X28'),(307,'2X32'),(308,'3X30'),(309,'4X26'),(310,'4X28'),(311,'5X30'),(312,'6X26'),(313,'8UH'),(314,'10X33'),(315,'10UH'),(316,'12X29'),(317,'12X33'),(318,'12X36'),(319,'12UH'),(320,'14X29'),(321,'14X31'),(322,'14X33'),(323,'14UH'),(324,'16X33.5'),(325,'16UH'),(326,'18X33'),(327,'20UH'),(328,'22UH'),(329,'24UH'),(330,'26UH'),(331,'45\"'),(332,'60\"');
/*!40000 ALTER TABLE `ITEM_ATTRIBUTE_VALUES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEM_ATTR_MAPPING`
--

DROP TABLE IF EXISTS `ITEM_ATTR_MAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEM_ATTR_MAPPING` (
  `MAPPING_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `ATTR_ID` varchar(45) NOT NULL,
  `ATTR_VALUE_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`MAPPING_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEM_ATTR_MAPPING`
--

LOCK TABLES `ITEM_ATTR_MAPPING` WRITE;
/*!40000 ALTER TABLE `ITEM_ATTR_MAPPING` DISABLE KEYS */;
INSERT INTO `ITEM_ATTR_MAPPING` VALUES (1,1,'1','1'),(2,1,'2','11'),(3,1,'2','12'),(4,1,'2','13'),(5,1,'2','14'),(6,1,'2','15'),(7,1,'2','16'),(8,2,'1','2'),(9,2,'2','11'),(10,2,'2','12'),(11,2,'2','13'),(12,2,'2','14'),(13,2,'2','15'),(14,2,'2','16'),(15,3,'1','3'),(16,3,'2','11'),(17,3,'2','12'),(18,3,'2','13'),(19,3,'2','14'),(20,3,'2','15'),(21,3,'2','16'),(22,4,'1','1'),(23,4,'2','17'),(24,4,'2','18'),(25,4,'2','19'),(26,4,'2','20'),(27,4,'2','21'),(28,4,'2','22'),(29,4,'2','23'),(30,4,'2','24'),(31,4,'2','25'),(32,4,'2','26'),(33,4,'2','27'),(34,4,'2','28'),(35,4,'2','29'),(36,4,'2','30'),(37,4,'2','31'),(38,4,'2','32'),(39,4,'2','33'),(40,4,'2','34'),(41,4,'2','35'),(42,4,'2','36'),(43,4,'2','37'),(44,4,'2','38'),(45,4,'2','39'),(46,4,'2','40'),(47,4,'2','41'),(48,4,'2','42'),(49,4,'2','43'),(50,4,'2','44'),(51,4,'2','45'),(52,4,'2','46'),(53,4,'2','47'),(54,4,'2','48'),(55,4,'2','49'),(56,4,'2','50'),(57,4,'2','51'),(58,4,'2','52'),(59,4,'2','53'),(60,4,'2','54'),(61,4,'2','55'),(62,4,'2','56'),(63,4,'2','57'),(64,4,'2','58'),(65,4,'2','59'),(66,4,'2','60'),(67,4,'2','61'),(68,4,'2','62'),(69,4,'2','63'),(70,5,'1','1'),(71,5,'2','64'),(72,5,'2','65'),(73,5,'2','66'),(74,5,'2','67'),(75,5,'2','68'),(76,5,'2','69'),(77,5,'2','70'),(78,5,'2','71'),(79,5,'2','72'),(80,6,'1','2'),(81,6,'2','11'),(82,6,'2','12'),(83,6,'2','13'),(84,6,'2','14'),(85,6,'2','15'),(86,6,'2','16'),(87,7,'1','4'),(88,7,'2','73'),(89,8,'1','5'),(90,8,'2','74'),(91,8,'2','75'),(92,8,'2','76'),(93,8,'2','77'),(94,8,'2','78'),(95,8,'2','79'),(96,8,'2','80'),(97,8,'2','81'),(98,8,'2','82'),(99,8,'2','83'),(100,8,'2','84'),(101,8,'2','85'),(102,8,'2','86'),(103,8,'2','87'),(104,8,'2','88'),(105,8,'2','89'),(106,8,'2','90'),(107,8,'2','91'),(108,8,'2','92'),(109,8,'2','93'),(110,8,'2','94'),(111,8,'2','95'),(112,8,'2','96'),(113,8,'2','97'),(114,8,'2','98'),(115,8,'2','99'),(116,8,'2','100'),(117,8,'2','101'),(118,8,'2','102'),(119,8,'2','103'),(120,8,'2','104'),(121,8,'2','105'),(122,8,'1','6'),(123,9,'1','5'),(124,9,'2','106'),(125,9,'2','11'),(126,9,'2','12'),(127,9,'2','13'),(128,9,'2','14'),(129,9,'2','15'),(130,9,'2','16'),(131,9,'2','107'),(132,9,'2','108'),(133,10,'1','5'),(134,10,'2','77'),(135,10,'2','78'),(136,10,'2','79'),(137,10,'2','81'),(138,10,'2','82'),(139,10,'2','83'),(140,10,'2','84'),(141,10,'2','85'),(142,10,'2','86'),(143,10,'2','87'),(144,10,'2','88'),(145,10,'2','90'),(146,10,'2','91'),(147,10,'2','94'),(148,10,'2','95'),(149,10,'2','98'),(150,10,'2','99'),(151,10,'2','102'),(152,10,'2','103'),(153,10,'2','105'),(154,10,'2','109'),(155,11,'1','1'),(156,11,'2','73'),(157,11,'1','5'),(158,12,'1','4'),(159,12,'2','73'),(160,13,'1','5'),(161,13,'2','73'),(162,14,'1','2'),(163,14,'2','110'),(164,14,'2','111'),(165,14,'2','112'),(166,14,'2','113'),(167,14,'2','114'),(168,14,'2','115'),(169,14,'2','116'),(170,14,'2','117'),(171,14,'2','118'),(172,14,'2','119'),(173,14,'2','120'),(174,14,'2','121'),(175,14,'2','122'),(176,14,'2','123'),(177,15,'1','2'),(178,15,'2','124'),(179,15,'2','125'),(180,15,'2','126'),(181,15,'2','127'),(182,15,'2','128'),(183,15,'2','112'),(184,15,'2','116'),(185,15,'2','119'),(186,15,'2','120'),(187,15,'2','121'),(188,16,'1','9'),(189,16,'2','73'),(190,17,'1','5'),(191,17,'2','11'),(192,17,'2','12'),(193,17,'2','13'),(194,17,'2','14'),(195,17,'2','15'),(196,17,'2','16'),(197,17,'2','107'),(198,17,'2','108'),(199,18,'1','5'),(200,18,'2','11'),(201,18,'2','12'),(202,18,'2','13'),(203,18,'2','14'),(204,18,'2','15'),(205,18,'2','16'),(206,18,'2','107'),(207,18,'2','108'),(208,19,'1','8'),(209,19,'2','11'),(210,19,'2','12'),(211,19,'2','13'),(212,19,'2','14'),(213,19,'2','15'),(214,19,'2','16'),(215,19,'2','107'),(216,19,'2','108'),(217,19,'2','129'),(218,20,'1','8'),(219,20,'2','11'),(220,20,'2','12'),(221,20,'2','13'),(222,20,'2','14'),(223,20,'2','15'),(224,20,'2','16'),(225,20,'2','107'),(226,20,'2','108'),(227,20,'2','129'),(228,20,'2','130'),(229,20,'2','131'),(230,21,'1','9'),(231,21,'2','73'),(232,22,'1','9'),(233,22,'2','73'),(234,23,'1','5'),(235,23,'2','132'),(236,23,'2','17'),(237,23,'2','18'),(238,23,'2','19'),(239,23,'2','20'),(240,23,'2','133'),(241,23,'2','21'),(242,23,'2','22'),(243,23,'2','23'),(244,23,'2','24'),(245,23,'2','134'),(246,23,'2','25'),(247,23,'2','26'),(248,23,'2','27'),(249,23,'2','28'),(250,23,'2','135'),(251,23,'2','29'),(252,23,'2','30'),(253,23,'2','31'),(254,23,'2','32'),(255,23,'2','136'),(256,23,'2','33'),(257,23,'2','34'),(258,23,'2','35'),(259,23,'2','36'),(260,23,'2','137'),(261,23,'2','37'),(262,23,'2','38'),(263,23,'2','39'),(264,23,'2','40'),(265,23,'2','138'),(266,23,'2','41'),(267,23,'2','42'),(268,23,'2','43'),(269,23,'2','45'),(270,23,'2','46'),(271,23,'2','47'),(272,23,'2','48'),(273,23,'2','49'),(274,23,'2','50'),(275,23,'2','51'),(276,23,'2','52'),(277,23,'2','53'),(278,23,'2','139'),(279,23,'2','54'),(280,23,'2','55'),(281,23,'2','56'),(282,23,'2','57'),(283,23,'2','140'),(284,23,'2','58'),(285,23,'2','59'),(286,23,'2','60'),(287,23,'2','141'),(288,23,'2','142'),(289,23,'2','61'),(290,23,'2','143'),(291,23,'2','144'),(292,23,'2','145'),(293,23,'2','146'),(294,23,'2','147'),(295,23,'2','148'),(296,23,'2','149'),(297,23,'2','150'),(298,23,'2','151'),(299,23,'2','152'),(300,23,'2','62'),(301,23,'2','63'),(302,23,'2','153'),(303,24,'1','5'),(304,24,'2','154'),(305,24,'2','66'),(306,24,'2','155'),(307,24,'2','156'),(308,24,'2','157'),(309,24,'2','158'),(310,24,'2','67'),(311,24,'2','159'),(312,24,'2','160'),(313,24,'2','161'),(314,24,'2','68'),(315,24,'2','162'),(316,24,'2','163'),(317,24,'2','164'),(318,24,'2','165'),(319,24,'2','69'),(320,24,'2','166'),(321,24,'2','167'),(322,24,'2','168'),(323,24,'2','70'),(324,24,'2','71'),(325,24,'2','169'),(326,24,'2','170'),(327,24,'2','171'),(328,24,'2','72'),(329,24,'2','172'),(330,24,'2','173'),(331,24,'2','174'),(332,24,'2','175'),(333,24,'2','176'),(334,24,'2','177'),(335,24,'2','178'),(336,24,'2','179'),(337,24,'2','180'),(338,24,'2','181'),(339,24,'2','182'),(340,24,'2','183'),(341,24,'2','184'),(342,24,'2','185'),(343,24,'2','186'),(344,24,'2','187'),(345,24,'2','188'),(346,24,'2','189'),(347,24,'2','190'),(348,24,'2','191'),(349,25,'1','5'),(350,25,'2','11'),(351,25,'2','12'),(352,25,'2','13'),(353,25,'2','14'),(354,25,'2','15'),(355,25,'2','16'),(356,25,'2','107'),(357,25,'2','108'),(358,26,'1','1'),(359,26,'2','11'),(360,26,'2','12'),(361,26,'2','13'),(362,26,'2','14'),(363,26,'2','15'),(364,26,'2','16'),(365,27,'1','2'),(366,27,'2','11'),(367,27,'2','12'),(368,27,'2','13'),(369,27,'2','14'),(370,27,'2','15'),(371,27,'2','16'),(372,27,'2','107'),(373,28,'1','5'),(374,28,'2','11'),(375,28,'2','12'),(376,28,'2','13'),(377,28,'2','14'),(378,28,'2','15'),(379,28,'2','16'),(380,28,'2','107'),(381,28,'2','108'),(382,29,'1','5'),(383,29,'2','11'),(384,29,'2','12'),(385,29,'2','13'),(386,29,'2','14'),(387,29,'2','15'),(388,29,'2','16'),(389,29,'2','107'),(390,29,'2','108'),(391,30,'1','5'),(392,30,'2','11'),(393,31,'1','5'),(394,31,'2','11'),(395,31,'2','12'),(396,31,'2','13'),(397,31,'2','14'),(398,31,'2','192'),(399,32,'1','5'),(400,32,'2','11'),(401,32,'2','12'),(402,32,'2','13'),(403,32,'2','14'),(404,33,'1','5'),(405,33,'2','11'),(406,33,'2','12'),(407,33,'2','13'),(408,33,'2','14'),(409,33,'2','15'),(410,33,'2','16'),(411,33,'2','107'),(412,34,'1','3'),(413,34,'2','11'),(414,34,'2','12'),(415,34,'2','13'),(416,34,'2','14'),(417,34,'2','15'),(418,34,'2','16'),(419,35,'1','5'),(420,35,'2','13'),(421,35,'2','14'),(422,36,'1','193'),(423,36,'2','11'),(424,36,'2','12'),(425,36,'2','13'),(426,37,'1','3'),(427,37,'2','13'),(428,38,'1','3'),(429,38,'2','14'),(430,38,'2','15'),(431,38,'1','194'),(432,38,'2','13'),(433,39,'1','10'),(434,39,'2','73'),(435,40,'1','10'),(436,40,'2','11'),(437,40,'2','12'),(438,40,'2','13'),(439,40,'2','14'),(440,40,'2','15'),(441,40,'2','16'),(442,40,'2','107'),(443,40,'2','108'),(444,41,'1','10'),(445,41,'2','11'),(446,41,'2','12'),(447,41,'2','13'),(448,41,'2','14'),(449,41,'2','15'),(450,41,'2','16'),(451,41,'2','107'),(452,41,'2','108'),(453,42,'1','3'),(454,42,'2','11'),(455,42,'2','12'),(456,42,'2','13'),(457,42,'2','14'),(458,42,'2','15'),(459,42,'2','16'),(460,42,'2','107'),(461,42,'2','108'),(462,43,'1','10'),(463,43,'2','11'),(464,43,'2','12'),(465,43,'2','13'),(466,43,'2','14'),(467,43,'2','15'),(468,43,'2','16'),(469,43,'2','107'),(470,43,'2','108'),(471,44,'1','195'),(472,44,'2','11'),(473,44,'2','12'),(474,44,'2','13'),(475,44,'2','14'),(476,44,'2','15'),(477,44,'2','16'),(478,44,'2','107'),(479,44,'2','108'),(480,45,'1','195'),(481,45,'2','11'),(482,45,'2','12'),(483,45,'2','13'),(484,46,'1','196'),(485,46,'2','196'),(486,47,'1','5'),(487,47,'2','73'),(488,48,'1','5'),(489,48,'2','11'),(490,48,'2','12'),(491,48,'2','13'),(492,48,'2','14'),(493,48,'2','15'),(494,48,'2','16'),(495,48,'2','107'),(496,49,'1','5'),(497,49,'2','11'),(498,49,'2','12'),(499,49,'2','13'),(500,49,'2','14'),(501,49,'2','15'),(502,49,'2','16'),(503,49,'2','107'),(504,50,'1','5'),(505,50,'2','11'),(506,50,'2','12'),(507,50,'2','13'),(508,50,'2','14'),(509,50,'2','15'),(510,50,'2','16'),(511,50,'2','107'),(512,50,'2','108'),(513,51,'1','197'),(514,51,'2','11'),(515,51,'2','12'),(516,51,'2','13'),(517,51,'2','14'),(518,51,'2','15'),(519,51,'2','16'),(520,51,'2','107'),(521,51,'2','108'),(522,52,'1','5'),(523,52,'2','11'),(524,52,'2','12'),(525,52,'2','13'),(526,52,'2','14'),(527,52,'2','15'),(528,52,'2','16'),(529,52,'2','107'),(530,52,'2','108'),(531,52,'2','129'),(532,52,'2','130'),(533,53,'1','198'),(534,53,'2','11'),(535,53,'2','12'),(536,53,'2','13'),(537,53,'2','14'),(538,53,'2','15'),(539,53,'2','16'),(540,53,'2','107'),(541,53,'2','108'),(542,53,'2','129'),(543,53,'2','130'),(544,54,'1','199'),(545,54,'2','11'),(546,54,'2','12'),(547,54,'2','13'),(548,54,'2','14'),(549,54,'2','15'),(550,54,'2','16'),(551,54,'2','107'),(552,54,'2','108'),(553,54,'2','129'),(554,54,'2','130'),(555,55,'1','198'),(556,55,'2','11'),(557,55,'2','12'),(558,55,'2','13'),(559,55,'2','14'),(560,55,'2','15'),(561,55,'2','16'),(562,55,'2','107'),(563,55,'2','108'),(564,55,'2','129'),(565,55,'2','130'),(566,56,'1','5'),(567,56,'2','11'),(568,56,'2','12'),(569,56,'2','13'),(570,56,'2','14'),(571,56,'2','15'),(572,56,'2','16'),(573,56,'2','107'),(574,56,'2','108'),(575,57,'1','198'),(576,57,'2','11'),(577,57,'2','12'),(578,57,'2','13'),(579,57,'2','14'),(580,57,'2','15'),(581,57,'2','16'),(582,57,'2','107'),(583,57,'2','108'),(584,58,'1','199'),(585,58,'2','11'),(586,58,'2','12'),(587,58,'2','13'),(588,58,'2','14'),(589,58,'2','15'),(590,58,'2','16'),(591,58,'2','107'),(592,58,'2','108'),(593,59,'1','200'),(594,59,'2','11'),(595,59,'2','12'),(596,59,'2','13'),(597,59,'2','14'),(598,59,'2','15'),(599,59,'2','16'),(600,59,'2','107'),(601,59,'2','108'),(602,60,'1','201'),(603,60,'2','12'),(604,60,'2','13'),(605,60,'2','14'),(606,60,'2','15'),(607,61,'1','193'),(608,61,'2','11'),(609,61,'2','12'),(610,61,'2','13'),(611,61,'2','14'),(612,61,'2','15'),(613,61,'2','16'),(614,61,'2','107'),(615,62,'1','197'),(616,62,'2','13'),(617,62,'2','14'),(618,62,'2','15'),(619,63,'1','197'),(620,63,'2','14'),(621,63,'2','15'),(622,64,'1','5'),(623,64,'2','33'),(624,64,'2','202'),(625,64,'2','41'),(626,64,'2','203'),(627,65,'1','201'),(628,65,'2','11'),(629,65,'2','12'),(630,65,'2','13'),(631,65,'2','14'),(632,65,'2','15'),(633,65,'2','16'),(634,66,'1','5'),(635,66,'2','11'),(636,66,'2','12'),(637,66,'2','13'),(638,66,'2','14'),(639,66,'2','15'),(640,66,'2','16'),(641,66,'2','107'),(642,67,'1','5'),(643,67,'2','11'),(644,67,'2','12'),(645,67,'2','13'),(646,67,'2','14'),(647,67,'2','15'),(648,67,'2','16'),(649,67,'2','107'),(650,67,'2','108'),(651,68,'1','5'),(652,68,'2','11'),(653,68,'2','12'),(654,68,'2','13'),(655,68,'2','14'),(656,68,'2','15'),(657,68,'2','16'),(658,68,'2','107'),(659,68,'2','108'),(660,69,'1','6'),(661,69,'2','33'),(662,69,'2','34'),(663,69,'2','35'),(664,69,'2','36'),(665,69,'2','37'),(666,69,'2','38'),(667,69,'2','39'),(668,69,'2','40'),(669,69,'2','41'),(670,69,'2','42'),(671,69,'2','43'),(672,69,'2','44'),(673,69,'2','45'),(674,69,'2','46'),(675,69,'2','47'),(676,69,'2','48'),(677,69,'2','50'),(678,69,'2','51'),(679,69,'2','52'),(680,69,'2','53'),(681,69,'2','54'),(682,69,'2','55'),(683,69,'2','56'),(684,69,'2','57'),(685,69,'2','58'),(686,70,'1','6'),(687,70,'2','204'),(688,70,'2','205'),(689,70,'2','206'),(690,70,'2','207'),(691,70,'2','208'),(692,70,'2','209'),(693,70,'2','210'),(694,70,'2','211'),(695,70,'2','212'),(696,70,'2','213'),(697,70,'2','214'),(698,70,'2','215'),(699,70,'2','216'),(700,70,'2','217'),(701,71,'1','10'),(702,71,'2','11'),(703,71,'2','12'),(704,71,'2','13'),(705,71,'2','14'),(706,71,'2','15'),(707,71,'2','16'),(708,71,'2','107'),(709,71,'2','108'),(710,72,'1','10'),(711,72,'2','11'),(712,72,'2','12'),(713,72,'2','13'),(714,72,'2','14'),(715,72,'2','15'),(716,72,'2','16'),(717,72,'2','107'),(718,72,'2','108'),(719,73,'1','5'),(720,73,'2','11'),(721,73,'2','12'),(722,73,'2','13'),(723,73,'2','14'),(724,73,'2','15'),(725,73,'2','16'),(726,73,'2','107'),(727,73,'2','108'),(728,74,'1','5'),(729,74,'2','12'),(730,74,'2','13'),(731,74,'2','15'),(732,74,'2','16'),(733,75,'1','1'),(734,75,'2','73'),(735,76,'1','1'),(736,76,'2','11'),(737,76,'2','12'),(738,76,'2','13'),(739,76,'2','14'),(740,76,'2','15'),(741,76,'2','16'),(742,76,'2','107'),(743,77,'1','1'),(744,77,'2','11'),(745,77,'2','12'),(746,77,'2','13'),(747,77,'2','14'),(748,77,'2','15'),(749,77,'2','16'),(750,77,'2','107'),(751,77,'2','108'),(752,78,'1','1'),(753,78,'2','73'),(754,79,'1','3'),(755,79,'2','11'),(756,79,'2','12'),(757,79,'2','13'),(758,79,'2','14'),(759,79,'2','15'),(760,79,'2','16'),(761,79,'2','107'),(762,79,'2','108'),(763,80,'1','3'),(764,80,'2','11'),(765,80,'2','12'),(766,80,'2','13'),(767,80,'2','14'),(768,80,'2','15'),(769,80,'2','16'),(770,81,'1','5'),(771,81,'2','11'),(772,81,'2','12'),(773,81,'2','13'),(774,81,'2','14'),(775,81,'2','15'),(776,81,'2','16'),(777,82,'1','5'),(778,82,'2','11'),(779,82,'2','12'),(780,82,'2','13'),(781,82,'2','14'),(782,82,'2','15'),(783,82,'2','16'),(784,83,'1','201'),(785,83,'2','11'),(786,83,'2','12'),(787,83,'2','13'),(788,83,'2','14'),(789,83,'2','15'),(790,83,'2','16'),(791,84,'1','201'),(792,84,'2','11'),(793,84,'2','12'),(794,84,'2','13'),(795,84,'2','14'),(796,84,'2','15'),(797,84,'2','16'),(798,85,'1','5'),(799,85,'2','11'),(800,85,'2','12'),(801,85,'2','13'),(802,85,'2','14'),(803,85,'2','15'),(804,85,'2','16'),(805,86,'1','10'),(806,86,'2','11'),(807,86,'2','12'),(808,86,'2','13'),(809,86,'2','14'),(810,86,'2','15'),(811,86,'2','16'),(812,86,'2','107'),(813,87,'1','3'),(814,87,'2','11'),(815,87,'2','12'),(816,87,'2','13'),(817,87,'2','14'),(818,87,'2','15'),(819,87,'2','16'),(820,87,'1','5'),(821,88,'1','218'),(822,88,'2','11'),(823,88,'2','12'),(824,88,'2','13'),(825,88,'2','14'),(826,88,'2','15'),(827,88,'1','5'),(828,88,'1','2'),(829,89,'1','201'),(830,89,'2','219'),(831,89,'2','220'),(832,89,'2','221'),(833,89,'2','222'),(834,90,'1','2'),(835,90,'2','192'),(836,91,'1','2'),(837,91,'2','223'),(838,91,'2','224'),(839,92,'1','2'),(840,92,'2','225'),(841,92,'1','226'),(842,92,'2','227'),(843,93,'1','2'),(844,93,'2','11'),(845,93,'2','12'),(846,93,'2','13'),(847,93,'2','14'),(848,93,'2','15'),(849,93,'2','16'),(850,93,'2','107'),(851,94,'1','5'),(852,94,'2','11'),(853,94,'2','12'),(854,94,'2','13'),(855,94,'2','14'),(856,94,'2','15'),(857,94,'2','16'),(858,94,'2','107'),(859,95,'1','5'),(860,95,'2','124'),(861,95,'2','125'),(862,95,'2','126'),(863,95,'2','127'),(864,95,'2','128'),(865,95,'2','112'),(866,95,'2','116'),(867,95,'2','119'),(868,96,'1','6'),(869,96,'2','17'),(870,96,'2','18'),(871,96,'2','19'),(872,96,'2','20'),(873,96,'2','228'),(874,96,'2','21'),(875,96,'2','22'),(876,96,'2','23'),(877,96,'2','24'),(878,96,'2','25'),(879,96,'2','26'),(880,96,'2','27'),(881,96,'2','28'),(882,96,'2','29'),(883,96,'2','30'),(884,96,'2','31'),(885,96,'2','32'),(886,96,'2','229'),(887,96,'2','33'),(888,96,'2','34'),(889,96,'2','35'),(890,96,'2','36'),(891,96,'2','37'),(892,96,'2','38'),(893,96,'2','39'),(894,96,'2','40'),(895,96,'2','41'),(896,96,'2','42'),(897,96,'2','43'),(898,96,'2','44'),(899,96,'2','203'),(900,96,'2','230'),(901,96,'2','45'),(902,96,'2','46'),(903,96,'2','47'),(904,96,'2','48'),(905,96,'2','50'),(906,96,'2','51'),(907,96,'2','52'),(908,96,'2','53'),(909,96,'2','54'),(910,96,'2','55'),(911,96,'2','56'),(912,96,'2','57'),(913,96,'2','231'),(914,96,'2','58'),(915,96,'2','59'),(916,96,'2','60'),(917,96,'2','141'),(918,96,'2','61'),(919,96,'2','143'),(920,96,'2','144'),(921,96,'2','232'),(922,96,'2','146'),(923,96,'2','147'),(924,96,'2','148'),(925,96,'2','233'),(926,96,'2','151'),(927,96,'2','152'),(928,96,'2','62'),(929,96,'2','234'),(930,97,'1','6'),(931,97,'2','64'),(932,97,'2','65'),(933,97,'2','235'),(934,97,'2','66'),(935,97,'2','67'),(936,97,'2','68'),(937,97,'2','71'),(938,97,'2','169'),(939,98,'1','2'),(940,98,'2','236'),(941,98,'2','237'),(942,98,'2','124'),(943,98,'2','238'),(944,98,'2','239'),(945,98,'2','240'),(946,98,'2','125'),(947,98,'2','241'),(948,98,'2','242'),(949,98,'2','243'),(950,98,'2','126'),(951,98,'2','244'),(952,98,'2','245'),(953,98,'2','246'),(954,98,'2','127'),(955,98,'2','247'),(956,98,'2','248'),(957,98,'2','249'),(958,98,'2','128'),(959,98,'2','250'),(960,98,'2','110'),(961,98,'2','112'),(962,99,'1','197'),(963,99,'2','251'),(964,99,'1','252'),(965,100,'1','197'),(966,100,'2','106'),(967,100,'2','11'),(968,100,'2','12'),(969,100,'2','13'),(970,100,'2','14'),(971,100,'2','15'),(972,100,'2','16'),(973,100,'2','107'),(974,101,'1','197'),(975,101,'2','12'),(976,101,'2','13'),(977,102,'1','10'),(978,102,'2','11'),(979,102,'2','12'),(980,102,'2','13'),(981,102,'2','14'),(982,102,'2','15'),(983,102,'1','201'),(984,102,'2','16'),(985,103,'1','5'),(986,103,'2','11'),(987,103,'2','12'),(988,103,'2','13'),(989,103,'2','14'),(990,103,'2','15'),(991,103,'2','16'),(992,104,'1','5'),(993,104,'2','106'),(994,104,'2','11'),(995,104,'2','12'),(996,104,'2','13'),(997,104,'2','14'),(998,104,'2','15'),(999,104,'2','16'),(1000,105,'1','5');
/*!40000 ALTER TABLE `ITEM_ATTR_MAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LAUNDRY_TRACKING`
--

DROP TABLE IF EXISTS `LAUNDRY_TRACKING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LAUNDRY_TRACKING` (
  `LAUNDRY_TRACKING_ID` int(11) NOT NULL AUTO_INCREMENT,
  `IS_OPEN` varchar(10) DEFAULT NULL,
  `CODE` varchar(45) DEFAULT NULL,
  `CREATEDDTTM` timestamp NULL DEFAULT NULL,
  `CLOSEDDTTM` timestamp NULL DEFAULT NULL,
  `LASTUPDATEDTTM` timestamp NULL DEFAULT NULL,
  `WAS_MC_NO` int(11) DEFAULT NULL,
  `TSE_ROOM` int(11) DEFAULT NULL,
  `TOWELS` int(11) DEFAULT NULL,
  `GYM` int(11) DEFAULT NULL,
  `JOCK_SOCK_BRA` int(11) DEFAULT NULL,
  `UNIFORM` int(11) DEFAULT NULL,
  `REG_LAUNDRY` int(11) DEFAULT NULL,
  `DMD_0006_G` int(11) DEFAULT NULL,
  `FAD_0006_E` int(11) DEFAULT NULL,
  `CTD_0006_D` int(11) DEFAULT NULL,
  `ATF_SABT_0006_H` int(11) DEFAULT NULL,
  `PTD_0006_F` int(11) DEFAULT NULL,
  `USBOPB_0006_B` int(11) DEFAULT NULL,
  `FPS_0006_C` int(11) DEFAULT NULL,
  `DRYER_MC_NO` int(11) DEFAULT NULL,
  `WEIGHT_WITH_BUGGY` int(11) DEFAULT NULL,
  `WEIGHT_BUGGY` int(11) DEFAULT NULL,
  `DRYER_TEMP` int(11) DEFAULT NULL,
  `CLEANED_DRYER_FILTER` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`LAUNDRY_TRACKING_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LAUNDRY_TRACKING`
--

LOCK TABLES `LAUNDRY_TRACKING` WRITE;
/*!40000 ALTER TABLE `LAUNDRY_TRACKING` DISABLE KEYS */;
/*!40000 ALTER TABLE `LAUNDRY_TRACKING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOCATION`
--

DROP TABLE IF EXISTS `LOCATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOCATION` (
  `LOCATION_ID` int(11) NOT NULL,
  `LOCATION_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`LOCATION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOCATION`
--

LOCK TABLES `LOCATION` WRITE;
/*!40000 ALTER TABLE `LOCATION` DISABLE KEYS */;
INSERT INTO `LOCATION` VALUES (-1,'External Location'),(1,'Equipment'),(2,'Uniform '),(3,'Gym'),(4,'Main Warehouse'),(5,'Laundry'),(6,'Trainee');
/*!40000 ALTER TABLE `LOCATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT`
--

DROP TABLE IF EXISTS `PRODUCT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(100) NOT NULL,
  `PRODUCT_DESC` varchar(250) NOT NULL,
  `LOCATION_ID` int(11) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCT`
--

LOCK TABLES `PRODUCT` WRITE;
/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;
INSERT INTO `PRODUCT` VALUES (1,'Kit 1','test kit',2),(3,'kit1','testing',1),(4,'kit2','testing',1);
/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PRODUCT_ITEMS`
--

DROP TABLE IF EXISTS `PRODUCT_ITEMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PRODUCT_ITEMS` (
  `PRODUCT_ITEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  PRIMARY KEY (`PRODUCT_ITEM_ID`),
  KEY `prdItemFk` (`PRODUCT_ID`),
  CONSTRAINT `prdItemFk` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PRODUCT_ITEMS`
--

LOCK TABLES `PRODUCT_ITEMS` WRITE;
/*!40000 ALTER TABLE `PRODUCT_ITEMS` DISABLE KEYS */;
INSERT INTO `PRODUCT_ITEMS` VALUES (1,1,1),(2,1,2),(3,3,1),(4,3,2),(5,3,3),(6,4,1),(7,4,2),(8,4,3);
/*!40000 ALTER TABLE `PRODUCT_ITEMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REASON_CODE`
--

DROP TABLE IF EXISTS `REASON_CODE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REASON_CODE` (
  `REASON_ID` int(11) NOT NULL,
  `REASON_CODE` varchar(60) NOT NULL,
  `REASON_CODE_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`REASON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REASON_CODE`
--

LOCK TABLES `REASON_CODE` WRITE;
/*!40000 ALTER TABLE `REASON_CODE` DISABLE KEYS */;
INSERT INTO `REASON_CODE` VALUES (1,'ITEM_MISSING','Item Missing'),(2,'ITEM_LOST','Item Lost');
/*!40000 ALTER TABLE `REASON_CODE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROLES`
--

DROP TABLE IF EXISTS `ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ROLES` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(100) NOT NULL,
  `ROLE_DESC` varchar(250) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROLES`
--

LOCK TABLES `ROLES` WRITE;
/*!40000 ALTER TABLE `ROLES` DISABLE KEYS */;
INSERT INTO `ROLES` VALUES (1,'ROLE_WHSE','Role to admin Warehouse'),(2,'ROLE_UNIFORM','Role to admin Uniform division'),(3,'ROLE_TEG','Role to admin TEG'),(4,'ROLE_GYM','Role to admin GYM'),(5,'ROLE_LAUNDRY','Role to admin laundry'),(6,'ROLE_ADMIN','Role to adminster'),(7,'ROLE_REPORT','Role for Reports'),(8,'ROLE_CLASS','Role for Class');
/*!40000 ALTER TABLE `ROLES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STAFF`
--

DROP TABLE IF EXISTS `STAFF`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STAFF` (
  `STAFF_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) NOT NULL,
  `MIDDLE_NAME` varchar(45) DEFAULT NULL,
  `LAST_NAME` varchar(45) DEFAULT NULL,
  `DIVISION` varchar(45) DEFAULT NULL,
  `OFFICE_EXTENSION` varchar(45) DEFAULT NULL,
  `CREATEDDTTM` timestamp NULL DEFAULT NULL,
  `LASTUPDATEDTTM` timestamp NULL DEFAULT NULL,
  `ENABLED` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`STAFF_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STAFF`
--

LOCK TABLES `STAFF` WRITE;
/*!40000 ALTER TABLE `STAFF` DISABLE KEYS */;
/*!40000 ALTER TABLE `STAFF` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRAINEE`
--

DROP TABLE IF EXISTS `TRAINEE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRAINEE` (
  `TRAINEE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(100) NOT NULL,
  `LAST_NAME` varchar(100) NOT NULL,
  `MIDDLE_NAME` varchar(50) DEFAULT NULL,
  `CLASS_NAME` varchar(50) DEFAULT NULL,
  `ENABLED` varchar(1) DEFAULT 'Y',
  `CREATEDDTTM` timestamp NULL DEFAULT NULL,
  `LASTUPDATEDTTM` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TRAINEE_ID`),
  KEY `CLASS_NAME` (`CLASS_NAME`),
  CONSTRAINT `FK_TR_CLS` FOREIGN KEY (`CLASS_NAME`) REFERENCES `CLASS` (`CLASS_NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRAINEE`
--

LOCK TABLES `TRAINEE` WRITE;
/*!40000 ALTER TABLE `TRAINEE` DISABLE KEYS */;
INSERT INTO `TRAINEE` VALUES (12,'t','t','t','c','Y','2012-09-02 19:10:44','2012-09-02 19:10:44'),(13,'traine','trainee','trainee','a','Y','2012-09-02 19:12:00','2012-09-02 19:12:00'),(14,'trainees','skldjf','tainess','b','Y','2012-09-02 19:12:18','2012-09-02 19:12:18');
/*!40000 ALTER TABLE `TRAINEE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANSACTION_DETAILS`
--

DROP TABLE IF EXISTS `TRANSACTION_DETAILS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSACTION_DETAILS` (
  `TRANSACTION_DETAILS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKU_CODE` varchar(45) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `TRANSACTION_ID` int(11) NOT NULL,
  `REASON_CODE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_DETAILS_ID`),
  KEY `trasnFk` (`TRANSACTION_ID`),
  CONSTRAINT `trasnFk` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `TRANSACTION_TRACE` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANSACTION_DETAILS`
--

LOCK TABLES `TRANSACTION_DETAILS` WRITE;
/*!40000 ALTER TABLE `TRANSACTION_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRANSACTION_DETAILS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANSACTION_ITEM_MAPPING`
--

DROP TABLE IF EXISTS `TRANSACTION_ITEM_MAPPING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSACTION_ITEM_MAPPING` (
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `UI_WEIGHT` int(11) NOT NULL,
  PRIMARY KEY (`TRANSACTION_TYPE`,`ITEM_ID`),
  KEY `item_fk` (`ITEM_ID`),
  KEY `trans_type_fk` (`TRANSACTION_TYPE`),
  KEY `transFk` (`TRANSACTION_TYPE`),
  CONSTRAINT `item_fk` FOREIGN KEY (`ITEM_ID`) REFERENCES `ITEM` (`ITEM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `transFk` FOREIGN KEY (`TRANSACTION_TYPE`) REFERENCES `TRANSACTION_TYPE` (`TRANSACTION_TYPE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANSACTION_ITEM_MAPPING`
--

LOCK TABLES `TRANSACTION_ITEM_MAPPING` WRITE;
/*!40000 ALTER TABLE `TRANSACTION_ITEM_MAPPING` DISABLE KEYS */;
INSERT INTO `TRANSACTION_ITEM_MAPPING` VALUES ('ISSUE_UNIFORM_STUDENT',1,1),('ISSUE_UNIFORM_STUDENT',2,1);
/*!40000 ALTER TABLE `TRANSACTION_ITEM_MAPPING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANSACTION_TRACE`
--

DROP TABLE IF EXISTS `TRANSACTION_TRACE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSACTION_TRACE` (
  `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DTTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LOCATION_ID` int(11) NOT NULL,
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `TRAINEE_ID` int(11) DEFAULT NULL,
  `STAFF_ID` int(11) DEFAULT NULL,
  `SOURCE_LOCATION_ID` int(11) DEFAULT NULL,
  `REFERENCE_TRANSACTION_ID` int(11) DEFAULT NULL,
  `CLOSED` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  KEY `typeFk` (`TRANSACTION_TYPE`),
  KEY `parentFk` (`REFERENCE_TRANSACTION_ID`),
  CONSTRAINT `parentFk` FOREIGN KEY (`REFERENCE_TRANSACTION_ID`) REFERENCES `TRANSACTION_TRACE` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `typeFk` FOREIGN KEY (`TRANSACTION_TYPE`) REFERENCES `TRANSACTION_TYPE` (`TRANSACTION_TYPE`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANSACTION_TRACE`
--

LOCK TABLES `TRANSACTION_TRACE` WRITE;
/*!40000 ALTER TABLE `TRANSACTION_TRACE` DISABLE KEYS */;
/*!40000 ALTER TABLE `TRANSACTION_TRACE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TRANSACTION_TYPE`
--

DROP TABLE IF EXISTS `TRANSACTION_TYPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TRANSACTION_TYPE` (
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `LOCATION_ID` int(11) DEFAULT NULL,
  `TRANSACTION_TYPE_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_TYPE`),
  KEY `locationFk` (`LOCATION_ID`),
  CONSTRAINT `locationFk` FOREIGN KEY (`LOCATION_ID`) REFERENCES `LOCATION` (`LOCATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TRANSACTION_TYPE`
--

LOCK TABLES `TRANSACTION_TYPE` WRITE;
/*!40000 ALTER TABLE `TRANSACTION_TYPE` DISABLE KEYS */;
INSERT INTO `TRANSACTION_TYPE` VALUES ('ACCEPT_FROM_LAUNDRY_EQUIPMENT',1,'Receive items into Equipment from Laundry after cleaning'),('ACCEPT_FROM_LAUNDRY_GYM',3,'Receive itesm into Gym from Laundry after cleaning'),('ACCEPT_FROM_LAUNDRY_UNIFORM',2,'Receive items into Uniform from Laundry after cleaning'),('ISSUE_EQUIPMENT_STAFF',1,'Issue equipment to Staff'),('ISSUE_EQUIPMENT_STUDENT',1,'Issue equipment to Trainee'),('ISSUE_GYM_STAFF',3,'Issue gym items to Staff'),('ISSUE_GYM_STUDENT',3,'Issue Gym items to Trainee'),('ISSUE_UNIFORM_STAFF',2,'Issue Uniform to Staff'),('ISSUE_UNIFORM_STUDENT',2,'Issue Uniform to Trainee'),('REPORT_MISSING_UNIFORM_STAFF',2,'Report missing uniform by Staff'),('REPORT_MISSING_UNIFORM_STUDENT',2,'Report missing uniform by Trainee'),('RETURN_EQUIPMENT_STAFF',1,'Equipment returns by Staff'),('RETURN_EQUIPMENT_STUDENT',1,'Equipment returns by Trainee'),('RETURN_GYM_STAFF',3,'Gym items return by Staff'),('RETURN_GYM_STUDENT',3,'Gym items return by Trainee'),('RETURN_UNIFORM_STAFF',2,'Unifrom returns by Staff'),('RETURN_UNIFORM_STUDENT',2,'Uniform returns by Students'),('TRANSFER_INVENTORY',-1,'Transfer inventory from one location to other');
/*!40000 ALTER TABLE `TRANSACTION_TYPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
INSERT INTO `USERS` VALUES (102,'user1','123456',1);
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USER_ROLES`
--

DROP TABLE IF EXISTS `USER_ROLES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USER_ROLES` (
  `USER_ROLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(10) unsigned NOT NULL,
  `AUTHORITY` varchar(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USER_ROLES`
--

LOCK TABLES `USER_ROLES` WRITE;
/*!40000 ALTER TABLE `USER_ROLES` DISABLE KEYS */;
INSERT INTO `USER_ROLES` VALUES (2,102,'ROLE_WHSE'),(3,102,'ROLE_UNIFORM'),(4,102,'ROLE_TEG'),(5,102,'ROLE_GYM'),(6,102,'ROLE_LAUNDRY'),(7,102,'ROLE_ADMIN'),(8,102,'ROLE_REPORT'),(9,102,'ROLE_CLASS');
/*!40000 ALTER TABLE `USER_ROLES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-03  2:13:41
