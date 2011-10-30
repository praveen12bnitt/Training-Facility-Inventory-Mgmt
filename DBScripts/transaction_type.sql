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
