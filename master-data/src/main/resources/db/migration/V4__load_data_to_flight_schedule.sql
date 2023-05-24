INSERT INTO `flight_schedule` (`flight_number`, `departure_airport_id`, `arrival_airport_id`, `departure_time`, `arrival_time`) VALUES
  ('501', (SELECT `id` FROM `airport` WHERE `airport_code` = 'BOM'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), '4:30', '6:00'),
  ('2137', (SELECT `id` FROM `airport` WHERE `airport_code` = 'BOM'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), '5:10', '6:50'),
  ('507', (SELECT `id` FROM `airport` WHERE `airport_code` = 'BOM'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), '15:35', '17:20'),
  ('503', (SELECT `id` FROM `airport` WHERE `airport_code` = 'BOM'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), '19:20', '21:00'),
  ('201', (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'JFK'), '8:30', '14:25'),
  ('203', (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'JFK'), '2:50', '8:50'),
  ('205', (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'JFK'), '9:45', '19:00'),
  ('211', (SELECT `id` FROM `airport` WHERE `airport_code` = 'DXB'), (SELECT `id` FROM `airport` WHERE `airport_code` = 'IAH'), '9:35', '16:50');