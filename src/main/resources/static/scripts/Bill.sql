CREATE TABLE `Bill` (
  `bill_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` decimal(20,0) DEFAULT NULL,
  `type` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`bill_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;