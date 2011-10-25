
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
