CREATE TABLE `item` (
  `ITEM_ID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `ITEM_DESC` varchar(200) DEFAULT NULL,
  `REQUIRES_PROCESSING` varchar(10) DEFAULT 'Y',
  `ITEM_NUMBER` varchar(100) DEFAULT 'ITMNO1',
  `PRICE` float DEFAULT '2.1',
  PRIMARY KEY (`ITEM_ID`)
) ;


-- Item Attribute table
CREATE TABLE `ITEM_ATTRIBUTE` (
  `ATTR_ID` int(11) NOT NULL,
  `ATTR_NAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ATTR_ID`)
) ;

INSERT INTO `ITEM_ATTRIBUTE` (`ATTR_ID`,`ATTR_NAME`) VALUES (1,'COLOR');
INSERT INTO `ITEM_ATTRIBUTE` (`ATTR_ID`,`ATTR_NAME`) VALUES (2,'SIZE');

-- Item Attribute values table

CREATE TABLE `ITEM_ATTRIBUTE_VALUES` (
  `ATTR_VALUE_ID` int(11) NOT NULL,
  `ATTR_VALUE` varchar(20) NOT NULL,
  PRIMARY KEY (`ATTR_VALUE_ID`)
);

-- Item attribute mappings

CREATE TABLE `ITEM_ATTR_MAPPING` (
  `MAPPING_ID` int(11) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `ATTR_ID` varchar(45) NOT NULL,
  `ATTR_VALUE_ID` varchar(45) NOT NULL,
  PRIMARY KEY (`MAPPING_ID`)
);


-- Location table
CREATE TABLE `LOCATION` (
  `LOCATION_ID` int(11) NOT NULL,
  `LOCATION_NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`LOCATION_ID`)
);

INSERT INTO `LOCATION` (`LOCATION_ID`,`LOCATION_NAME`) VALUES (-1,'External Location');
INSERT INTO `LOCATION` (`LOCATION_ID`,`LOCATION_NAME`) VALUES (1,'Equipment');
INSERT INTO `LOCATION` (`LOCATION_ID`,`LOCATION_NAME`) VALUES (2,'Uniform ');
INSERT INTO `LOCATION` (`LOCATION_ID`,`LOCATION_NAME`) VALUES (3,'Gym');
INSERT INTO `LOCATION` (`LOCATION_ID`,`LOCATION_NAME`) VALUES (4,'Main Warehouse');
INSERT INTO `LOCATION` (`LOCATION_ID`,`LOCATION_NAME`) VALUES (5,'Laundry');

-- Transaction Type table
CREATE TABLE `TRANSACTION_TYPE` (
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `LOCATION_ID` int(11) DEFAULT NULL,
  `TRANSACTION_TYPE_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_TYPE`),
  CONSTRAINT `locationFk` FOREIGN KEY (`LOCATION_ID`) REFERENCES `LOCATION` (`LOCATION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;


INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`,`LOCATION_ID`,`TRANSACTION_TYPE_DESC`) VALUES ('ISSUE_UNIFORM_STAFF',2,'Issue Uniform to Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`,`LOCATION_ID`,`TRANSACTION_TYPE_DESC`) VALUES ('ISSUE_UNIFORM_STUDENT',2,'Issue Uniform to Trainee');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`,`LOCATION_ID`,`TRANSACTION_TYPE_DESC`) VALUES ('REPORT_MISSING_UNIFORM_STAFF',2,'Report missing uniform by Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`,`LOCATION_ID`,`TRANSACTION_TYPE_DESC`) VALUES ('REPORT_MISSING_UNIFORM_STUDENT',2,'Report missing uniform by Trainee');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`,`LOCATION_ID`,`TRANSACTION_TYPE_DESC`) VALUES ('RETURN_UNIFORM_STUDENT',2,'Uniform returns by Students');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`,`LOCATION_ID`,`TRANSACTION_TYPE_DESC`) VALUES ('TRANSFER_INVENTORY',-1,'Transfer inventory from one location to other');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('RETURN_UNIFORM_STAFF', 2, 'Unifrom returns by Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ACCEPT_FROM_LAUNDRY_UNIFORM', 2, 'Receive items into Uniform from Laundry after cleaning');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ACCEPT_FROM_LAUNDRY_EQUIPMENT', 1, 'Receive items into Equipment from Laundry after cleaning');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ACCEPT_FROM_LAUNDRY_GYM', 3, 'Receive itesm into Gym from Laundry after cleaning');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ISSUE_EQUIPMENT_STUDENT', 1, 'Issue equipment to Trainee');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ISSUE_EQUIPMENT_STAFF', 1, 'Issue equipment to Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ISSUE_GYM_STUDENT', 3, 'Issue Gym items to Trainee');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('ISSUE_GYM_STAFF', 3, 'Issue gym items to Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('RETURN_EQUIPMENT_STUDENT', 1, 'Equipment returns by Trainee');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('RETURN_EQUIPMENT_STAFF', 1, 'Equipment returns by Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('RETURN_GYM_STUDENT', 3, 'Gym items return by Trainee');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('RETURN_GYM_STAFF', 3, 'Gym items return by Staff');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('EXCHANGE_UNIFORM_STUDENT', 2, 'Exchange uniform by Student');
INSERT INTO `TRANSACTION_TYPE` (`TRANSACTION_TYPE`, `LOCATION_ID`, `TRANSACTION_TYPE_DESC`) VALUES ('EXCHANGE_UNIFORM_STAFF', 2, 'Exchange uniform by Staff');


-- Transaction item mapping

CREATE TABLE `TRANSACTION_ITEM_MAPPING` (
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `ITEM_ID` int(11) NOT NULL,
  `UI_WEIGHT` int(11) NOT NULL,
  PRIMARY KEY (`TRANSACTION_TYPE`,`ITEM_ID`),
  CONSTRAINT `transFk` FOREIGN KEY (`TRANSACTION_TYPE`) REFERENCES `TRANSACTION_TYPE` (`TRANSACTION_TYPE`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `item_fk` FOREIGN KEY (`ITEM_ID`) REFERENCES `ITEM` (`ITEM_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


-- Inventory table--Transaction table...so no data to fill

CREATE TABLE `INVENTORY` (
  `SKU_CODE` varchar(30) NOT NULL,
  `LOCATION_ID` int(11) NOT NULL,
  `AVAILABLE_QTY` int(11) NOT NULL,
  `ISSUED_QTY` int(11) NOT NULL,
  `UNUSABLE_QTY` int(11) NOT NULL,
  PRIMARY KEY (`SKU_CODE`,`LOCATION_ID`),
  CONSTRAINT `locfk` FOREIGN KEY (`LOCATION_ID`) REFERENCES `LOCATION` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

ALTER TABLE INVENTORY add(MISSING_QTY int(11), DAMAGED_QTY int(11));




-- transaction_trace table--Transaction table...so no data to fill

CREATE TABLE `TRANSACTION_TRACE` (
  `TRANSACTION_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_DTTM` timestamp NOT NULL,
  `LOCATION_ID` int(11) NOT NULL,
  `TRANSACTION_TYPE` varchar(45) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `TRAINEE_ID` int(11) DEFAULT NULL,
  `STAFF_ID` int(11) DEFAULT NULL,
  `SOURCE_LOCATION_ID` int(11) DEFAULT NULL,
  `REFERENCE_TRANSACTION_ID` int(11) DEFAULT NULL,
  `CLOSED` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_ID`),
  CONSTRAINT `parentFk` FOREIGN KEY (`REFERENCE_TRANSACTION_ID`) REFERENCES `TRANSACTION_TRACE` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `typeFk` FOREIGN KEY (`TRANSACTION_TYPE`) REFERENCES `TRANSACTION_TYPE` (`TRANSACTION_TYPE`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


-- transaction_details table--Transaction table...so no data to fill

CREATE TABLE `TRANSACTION_DETAILS` (
  `TRANSACTION_DETAILS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKU_CODE` varchar(45) NOT NULL,
  `QUANTITY` int(11) NOT NULL,
  `TRANSACTION_ID` int(11) NOT NULL,
  PRIMARY KEY (`TRANSACTION_DETAILS_ID`),
  CONSTRAINT `trasnFk` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `TRANSACTION_TRACE` (`TRANSACTION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

ALTER TABLE TRANSACTION_DETAILS ADD(REASON_CODE VARCHAR(50));


CREATE TABLE `EXCHANGE_SKU_RECORD` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ISSUED_SKU` varchar(45) NOT NULL,
  `EXCHANGED_SKU` varchar(45) NOT NULL,
  `DTTM` varchar(45) NOT NULL,
  `TRANSACTION_DETAILS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `TRANS_DTLS_PK` FOREIGN KEY (`TRANSACTION_DETAILS_ID`) REFERENCES `TRANSACTION_DETAILS` (`TRANSACTION_DETAILS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- User table
CREATE TABLE `USERS` (
  `USER_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USERNAME` VARCHAR(45) NOT NULL,
  `PASSWORD` VARCHAR(45) NOT NULL,
  `ENABLED` tinyint(1) NOT NULL,
  PRIMARY KEY (`USER_ID`)
);



-- user_roles table

CREATE TABLE `USER_ROLES` (
  `USER_ROLE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT(10) UNSIGNED NOT NULL,
  `AUTHORITY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  CONSTRAINT `FK_user_roles` FOREIGN KEY (`USER_ID`) REFERENCES `USERS` (`USER_ID`)
) ;


INSERT INTO USERS (USERNAME,PASSWORD, ENABLED)
VALUES ('user1', '123456', TRUE);

INSERT INTO USER_ROLES (USER_ID,AUTHORITY)
VALUES (1, 'ROLE_ADMIN');


CREATE TABLE `CLASS` (
  `CLASS_NAME` varchar(50) NOT NULL,
  `CLASS_DESC` varchar(100) NOT NULL,
  `CLASS_START_DATE` varchar(25) NOT NULL,
  `CLASS_END_DATE` varchar(25) NOT NULL,
  `COMMENT`	varchar(500) NULL,
  PRIMARY KEY (`CLASS_NAME`)
);


CREATE TABLE `CLASS_PRODUCT` (
  `CLASS_NAME` varchar(45) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  PRIMARY KEY (`CLASS_NAME`,`PRODUCT_ID`)
);



CREATE TABLE `CLASS_STAFF` (
  `CLASS_NAME` varchar(45) NOT NULL,
  `STAFF_ID` int(11) NOT NULL,
  PRIMARY KEY (`CLASS_NAME`,`STAFF_ID`)
);





-- trainee table

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
  CONSTRAINT `FK_TR_CLS` FOREIGN KEY (`CLASS_NAME`) REFERENCES `CLASS` (`CLASS_NAME`)
) ;


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
) ;


CREATE TABLE `PRODUCT` (
  `PRODUCT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRODUCT_NAME` varchar(100) NOT NULL,
  `PRODUCT_DESC` varchar(250) NOT NULL,
  `LOCATION_ID` int(11) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`)
  
) ;



CREATE TABLE `PRODUCT_DETAILS` (
  `PRODUCT_DETAILS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SKU_CODE` varchar(45) DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRODUCT_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRODUCT_DETAILS_ID`),
  CONSTRAINT `PRODUCT_ID_FK` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `PRODUCT` (`PRODUCT_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;


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
) ;

alter table LAUNDRY_TRACKING add WASHER_BUGGY_WEIGHT int(11) DEFAULT NULL;

CREATE TABLE `LAUNDRY` (
  `LAUNDRY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LAUNDRY_TYPE` CHAR(1) DEFAULT NULL,
  `CREATEDDTTM` timestamp NULL DEFAULT NULL,
  `LASTUPDATEDTTM` timestamp NULL DEFAULT NULL,
  `UNIT_NO` varchar(50) DEFAULT NULL,
  `CLIENT_GROUP` varchar(50) DEFAULT NULL,
  `CLIENT_SUB_GROUP` varchar(50) DEFAULT NULL,
  `TOTAL_WEIGHT` int(11) DEFAULT NULL,
  `BUGGY_WEIGHT` int(11) DEFAULT NULL,
  `WEIGHT` int(11) DEFAULT NULL,
  `COMMENTS` varchar(256) DEFAULT NULL,
  `ITEMS_WASHED` varchar(256) DEFAULT NULL,
   PRIMARY KEY (`LAUNDRY_ID`)
) ;


CREATE TABLE `ROLES` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(100) NOT NULL,
  `ROLE_DESC` varchar(250) NOT NULL,
  
  PRIMARY KEY (`ROLE_ID`)
  
) ;

INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (1,'ROLE_WHSE','Role to admin Warehouse');
INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (2,'ROLE_UNIFORM','Role to admin Uniform division');
INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (3,'ROLE_TEG','Role to admin TEG');
INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (4,'ROLE_GYM','Role to admin GYM');
INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (5,'ROLE_LAUNDRY','Role to admin laundry');
INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (6,'ROLE_ADMIN','Role to adminster');
INSERT INTO `ROLES` (`ROLE_ID`,`ROLE_NAME`,`ROLE_DESC`) VALUES (7,'ROLE_REPORT','Role for Reports');

CREATE TABLE `REASON_CODE` (
  `REASON_ID` int(11) NOT NULL,
  `REASON_CODE` varchar(60) NOT NULL,
  `REASON_CODE_DESC` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`REASON_ID`)
) ;

INSERT INTO `REASON_CODE` (`REASON_ID`,`REASON_CODE`,`REASON_CODE_DESC`) VALUES (1,'ITEM_MISSING','Item Missing');
INSERT INTO `REASON_CODE` (`REASON_ID`,`REASON_CODE`,`REASON_CODE_DESC`) VALUES (2,'ITEM_LOST','Item Lost');



