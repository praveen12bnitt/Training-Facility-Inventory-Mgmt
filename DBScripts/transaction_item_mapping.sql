
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
