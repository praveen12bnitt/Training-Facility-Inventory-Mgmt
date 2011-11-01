CREATE SCHEMA `invtmgmt` ;

USE invtmgmt;

-- Item Table and data
CREATE TABLE `item` (
  `ITEM_ID` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `ITEM_DESC` varchar(200) DEFAULT NULL,
  `REQUIRES_PROCESSING` varchar(10) DEFAULT 'Y',
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (1,'ATF Student  Investigator Jacket','ATF Student  Investigator Jacket');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (2,'ATF Student Investigator Polo Shirt','ATF Student Investigator Polo Shirt');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (3,'ATF Student Investigator Sweatshirt','ATF Student Investigator Sweatshirt');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (4,'ATF Tactical 5.11 Cargo Pants (Men''s)','ATF Tactical 5.11 Cargo Pants (Men''s)');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (5,'ATF Tactical 5.11 Cargo Pants (Women''s)','ATF Tactical 5.11 Cargo Pants (Women''s)');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (6,'Athletic Supporter','Athletic Supporter');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (7,'Belt Buckle','Belt Buckle');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (8,'BDU Pants','BDU Pants');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (9,'BDU Shorts','BDU Shorts');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (10,'BOP BDU Pants','BOP BDU Pants');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (11,'BOP Belt','BOP Belt');
INSERT INTO `item` (`ITEM_ID`,`NAME`,`ITEM_DESC`) VALUES (12,'BOP Belt Buckle','BOP Belt Buckle');

-- Item Attribute table
CREATE TABLE `item_attribute` (
  `ATTR_ID` int(11) NOT NULL,
  `ATTR_NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ATTR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `item_attribute` (`ATTR_ID`,`ATTR_NAME`) VALUES (1,'COLOR');
INSERT INTO `item_attribute` (`ATTR_ID`,`ATTR_NAME`) VALUES (2,'SIZE');

-- Item Attribute values table

CREATE TABLE `item_attribute_values` (
  `ATTR_VALUE_ID` int(11) NOT NULL,
  `ATTR_VALUE` varchar(20) NOT NULL,
  PRIMARY KEY (`ATTR_VALUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (1,'Black');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (2,'White');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (3,'Grey');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (4,'Brass');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (5,'Navy Blue');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (6,'Khaki');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (7,'Dark Blue');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (8,'Blue');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (9,'Dark Blue');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (10,'Maroon');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (11,'S');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (12,'M');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (13,'L');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (14,'XL');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (15,'2X');
INSERT INTO `item_attribute_values` (`ATTR_VALUE_ID`,`ATTR_VALUE`) VALUES (16,'3X');

-- Item attribute mappings

CREATE TABLE `item_attr_mapping` (
  `MAPPING_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `ATTR_ID` varchar(45) NOT NULL,
  `ATTR_VALUE_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`MAPPING_ID`),
  KEY `ITEM_ID` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (1,1,'1','1');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (2,1,'2','11');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (3,1,'2','12');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (4,1,'2','13');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (5,1,'2','14');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (6,1,'2','15');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (7,1,'2','16');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (8,2,'1','2');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (9,2,'2','11');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (10,2,'2','12');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (11,2,'2','13');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (12,2,'2','14');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (13,2,'2','15');
INSERT INTO `item_attr_mapping` (`MAPPING_ID`,`ITEM_ID`,`ATTR_ID`,`ATTR_VALUE_ID`) VALUES (14,2,'2','16');

-- Location table
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL,
  `LOCATION_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `location` (`location_id`,`LOCATION_NAME`) VALUES (1,'Equipment');
INSERT INTO `location` (`location_id`,`LOCATION_NAME`) VALUES (2,'Uniform ');
INSERT INTO `location` (`location_id`,`LOCATION_NAME`) VALUES (3,'Gym');
INSERT INTO `location` (`location_id`,`LOCATION_NAME`) VALUES (4,'Main Warehouse');
INSERT INTO `location` (`location_id`,`LOCATION_NAME`) VALUES (5,'Laundry');

-- Transaction Type table
CREATE TABLE `transaction_type` (
  `transaction_type` varchar(45) NOT NULL,
  `location_id` int(11) DEFAULT NULL,
  `transaction_type_desc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`transaction_type`),
  KEY `locationFk` (`location_id`),
  CONSTRAINT `locationFk` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `transaction_type` (`transaction_type`,`location_id`,`transaction_type_desc`) VALUES ('ISSUE_UNIFORM_STAFF',2,'Issue Uniform to Staff');
INSERT INTO `transaction_type` (`transaction_type`,`location_id`,`transaction_type_desc`) VALUES ('ISSUE_UNIFORM_STUDENT',2,'Issue Uniform to Students');
INSERT INTO `transaction_type` (`transaction_type`,`location_id`,`transaction_type_desc`) VALUES ('RETURN_UNIFORM_STUDENT',2,'Uniform returns by Students');

-- Transaction item mapping

CREATE TABLE `transaction_item_mapping` (
  `transaction_type` varchar(45) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `UI_WEIGHT` int(11) NOT NULL,
  PRIMARY KEY (`transaction_type`,`ITEM_ID`),
  KEY `item_fk` (`ITEM_ID`),
  KEY `trans_type_fk` (`transaction_type`),
  KEY `transFk` (`transaction_type`),
  CONSTRAINT `transFk` FOREIGN KEY (`transaction_type`) REFERENCES `transaction_type` (`transaction_type`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `item_fk` FOREIGN KEY (`ITEM_ID`) REFERENCES `item` (`ITEM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `transaction_item_mapping` (`transaction_type`,`ITEM_ID`,`UI_WEIGHT`) VALUES ('ISSUE_UNIFORM_STUDENT',1,1);
INSERT INTO `transaction_item_mapping` (`transaction_type`,`ITEM_ID`,`UI_WEIGHT`) VALUES ('ISSUE_UNIFORM_STUDENT',2,1);

-- Inventory table--Transaction table...so no data to fill

CREATE TABLE `inventory` (
  `SKU_CODE` varchar(30) NOT NULL,
  `LOCATION_ID` int(11) NOT NULL,
  `AVAILABLE_QTY` int(11) NOT NULL,
  `UNUSABLE_QTY` int(11) NOT NULL,
  PRIMARY KEY (`SKU_CODE`,`LOCATION_ID`),
  KEY `locfk` (`LOCATION_ID`),
  CONSTRAINT `locfk` FOREIGN KEY (`LOCATION_ID`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- transaction_trace table--Transaction table...so no data to fill

CREATE TABLE `transaction_trace` (
  `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DTTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LOCATION_ID` int(11) NOT NULL,
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `EMPLOYEE_ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `STAFF_ID` int(11) DEFAULT NULL,
  `SOURCE_LOCATION_ID` int(11) DEFAULT NULL,
  `REFERENCE_TRANSACTION_ID` int(11) DEFAULT NULL,
  `CLOSED` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  KEY `typeFk` (`TRANSACTION_TYPE`),
  KEY `parentFk` (`REFERENCE_TRANSACTION_ID`),
  CONSTRAINT `parentFk` FOREIGN KEY (`REFERENCE_TRANSACTION_ID`) REFERENCES `transaction_trace` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `typeFk` FOREIGN KEY (`TRANSACTION_TYPE`) REFERENCES `transaction_type` (`transaction_type`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- transaction_details table--Transaction table...so no data to fill

CREATE TABLE `transaction_details` (
  `TRANSACTION_DETAILS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKU_CODE` varchar(45) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `TRANSACTION_ID` int(11) NOT NULL,
  PRIMARY KEY (`TRANSACTION_DETAILS_ID`),
  KEY `trasnFk` (`TRANSACTION_ID`),
  CONSTRAINT `trasnFk` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transaction_trace` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- User table
CREATE TABLE `users` (
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- user_roles table

CREATE TABLE `user_roles` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FK_user_roles` (`USER_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO users (USER_ID, USERNAME,PASSWORD, ENABLED)
VALUES (100, 'user1', '123456', TRUE);

INSERT INTO user_roles (USER_ROLE_ID, USER_ID,AUTHORITY)
VALUES (1, 100, 'ROLE_USER');




