CREATE TABLE IF NOT EXISTS  `flight_schedule` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `flight_number` varchar(30) NOT NULL,
  `departure_airport_id` BIGINT NOT NULL,
  `arrival_airport_id` BIGINT NOT NULL,
  `departure_time` time(0) NOT NULL,
  `arrival_time` time(0) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_flight_schedule_departure_airport` FOREIGN KEY (`departure_airport_id`) REFERENCES `airport` (`id`),
  CONSTRAINT `FK_flight_schedule_arrival_airport` FOREIGN KEY (`arrival_airport_id`) REFERENCES `airport` (`id`)
);