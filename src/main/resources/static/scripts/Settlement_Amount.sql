CREATE TABLE `Settlement_Amount` (
  `settlement_amount_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `to_be_paid_to` bigint(20) DEFAULT NULL,
  `value` decimal(4,0) DEFAULT NULL,
  `to_be_paid_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`settlement_amount_id`),
  KEY `to_be_paid_to` (`to_be_paid_to`),
  KEY `to_be_paid_by` (`to_be_paid_by`),
  CONSTRAINT `to_be_paid_by` FOREIGN KEY (`to_be_paid_by`) REFERENCES `Friend` (`friend_id`),
  CONSTRAINT `to_be_paid_to` FOREIGN KEY (`to_be_paid_to`) REFERENCES `Friend` (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
