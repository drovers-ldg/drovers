SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `accounts`;
CREATE TABLE `accounts` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account_name` varchar(24) NOT NULL,
  `account_password` varchar(24) NOT NULL,
  `gm` tinyint(1) unsigned zerofill NOT NULL DEFAULT '0',
  `online` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `accounts` VALUES ('1', 'admin', 'test', '3', '');
INSERT INTO `accounts` VALUES ('2', 'test', 'test', '0', '');