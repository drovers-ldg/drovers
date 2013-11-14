SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `units`;
CREATE TABLE `units` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `player_id` int(10) NOT NULL,
  `name` varchar(24) NOT NULL DEFAULT 'Default_Name',
  `map_id` int(10) NOT NULL DEFAULT '-1',
  `x` int(10) NOT NULL DEFAULT '0',
  `y` int(10) NOT NULL DEFAULT '0',
  `z` int(10) NOT NULL DEFAULT '0',
  `body_id` int(10) unsigned NOT NULL DEFAULT '0',
  `code_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;