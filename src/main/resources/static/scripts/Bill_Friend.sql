CREATE TABLE `Bill_Friend` (
  `bill_id` bigint(20) NOT NULL,
  `friend_id` bigint(20) NOT NULL,
  PRIMARY KEY (`bill_id`,`friend_id`),
  KEY `friend_id` (`friend_id`),
  CONSTRAINT `bill_friend_ibfk_1` FOREIGN KEY (`bill_id`) REFERENCES `Bill` (`bill_id`),
  CONSTRAINT `bill_friend_ibfk_2` FOREIGN KEY (`friend_id`) REFERENCES `Friend` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;