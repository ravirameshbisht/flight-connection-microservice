CREATE TABLE IF NOT EXISTS `airport` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `airport_code` varchar(3) NOT NULL,
  `airport_name` varchar(50) NOT NULL,
  `city_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
);