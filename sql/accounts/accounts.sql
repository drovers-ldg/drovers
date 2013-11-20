SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account_name` varchar(24) NOT NULL,
  `account_password` varchar(24) NOT NULL,
  `gm` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `online` bit(1) NOT NULL DEFAULT b'0',
  `player_name` varchar(36) NOT NULL DEFAULT 'default player name',
  `map_id` int(10) unsigned NOT NULL DEFAULT '0',
  `units_id` int(10) unsigned NOT NULL DEFAULT '0',
  `recource_thorium` int(10) NOT NULL DEFAULT '0',
  `recource_metal` int(10) NOT NULL,
  `recource_money` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `accounts` VALUES ('1', 'admin', 'test', '3', '\0', 'Admin', '0', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('2', 'test', 'test', '0', '\0', 'Test', '0', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('3', 'user', 'test', '0', '\0', 'User', '0', '0', '0', '0', '0');
INSERT INTO `accounts` VALUES ('4', 'testaccount', 'pass', '0', '\0', 'Testaccount', '0', '0', '0', '0', '0');
