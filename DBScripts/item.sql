
CREATE TABLE `item` (
  `ITEM_ID` int(11) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `ITEM_DESC` varchar(200) DEFAULT NULL,
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
