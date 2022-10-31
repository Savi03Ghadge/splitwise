CREATE TABLE `Friend` (
  `friend_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `amount_to_pay` decimal(4,0) DEFAULT NULL,
  `amount_paid` decimal(4,0) DEFAULT NULL,
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;