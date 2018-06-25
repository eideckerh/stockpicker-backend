-- MySQL dump 10.16  Distrib 10.3.7-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: stockpicker
-- ------------------------------------------------------
-- Server version	10.3.7-MariaDB-1:10.3.7+maria~jessie

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `stockpicker`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `stockpicker` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `stockpicker`;

--
-- Table structure for table `api_key`
--

DROP TABLE IF EXISTS `api_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `api_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api_key` varchar(255) DEFAULT NULL,
  `last_use` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `api_key`
--

LOCK TABLES `api_key` WRITE;
/*!40000 ALTER TABLE `api_key` DISABLE KEYS */;
INSERT INTO `api_key` VALUES (1,'T8EQOGB1QDY9PMLB',NULL),(2,'5QCBPQGU0688AM5A',NULL),(3,'E6LHNUU1IFXJWZPB',NULL);
/*!40000 ALTER TABLE `api_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbol`
--

DROP TABLE IF EXISTS `symbol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol_key` varchar(255) DEFAULT NULL,
  `symbol_name` varchar(255) DEFAULT NULL,
  `type_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcmihplls521x1qp5kqjvygkhd` (`type_id`)
) ENGINE=MyISAM AUTO_INCREMENT=537 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbol`
--

LOCK TABLES `symbol` WRITE;
/*!40000 ALTER TABLE `symbol` DISABLE KEYS */;
INSERT INTO `symbol` VALUES (1,'1ST','FirstBlood',2),(2,'2GIVE','GiveCoin',2),(3,'808','808Coin',2),(4,'ABT','ArcBlock',2),(5,'ABY','ArtByte',2),(6,'AC','AsiaCoin',2),(7,'ACT','Achain',2),(8,'ADA','Cardano',2),(9,'ADT','adToken',2),(10,'ADX','AdEx',2),(11,'AE','Aeternity',2),(12,'AEON','Aeon',2),(13,'AGI','SingularityNET',2),(14,'AGRS','IDNI-Agoras',2),(15,'AI','POLY-AI',2),(16,'AID','AidCoin',2),(17,'AION','Aion',2),(18,'AIR','AirToken',2),(19,'AKY','Akuya-Coin',2),(20,'ALIS','ALIS',2),(21,'AMBER','AmberCoin',2),(22,'AMP','Synereo',2),(23,'ANC','Anoncoin',2),(24,'ANT','Aragon',2),(25,'APPC','AppCoins',2),(26,'APX','APX-Ventures',2),(27,'ARDR','Ardor',2),(28,'ARK','Ark',2),(29,'ARN','Aeron',2),(30,'AST','AirSwap',2),(31,'ATB','ATBCoin',2),(32,'ATM','ATMChain',2),(33,'ATS','Authorship',2),(34,'AUR','Auroracoin',2),(35,'AVT','Aventus',2),(36,'B3','B3Coin',2),(37,'BAT','Basic-Attention-Token',2),(38,'BAY','BitBay',2),(39,'BBR','Boolberry',2),(40,'BCAP','BCAP',2),(41,'BCC','BitConnect',2),(42,'BCD','Bitcoin-Diamond',2),(43,'BCH','Bitcoin-Cash',2),(44,'BCN','Bytecoin',2),(45,'BCPT','BlockMason-Credit-Protocol-Token',2),(46,'BCX','BitcoinX',2),(47,'BCY','BitCrystals',2),(48,'BDL','Bitdeal',2),(49,'BEE','Bee-Token',2),(50,'BELA','BelaCoin',2),(51,'BET','DAO-Casino',2),(52,'BFT','BF-Token',2),(53,'BIS','Bismuth',2),(54,'BITB','BitBean',2),(55,'BITBTC','BitBTC',2),(56,'BITCNY','BitCNY',2),(57,'BITEUR','BitEUR',2),(58,'BITGOLD','BitGOLD',2),(59,'BITSILVER','BitSILVER',2),(60,'BITUSD','BitUSD',2),(61,'BIX','Bibox-Token',2),(62,'BLITZ','Blitzcash',2),(63,'BLK','Blackcoin',2),(64,'BLN','Bolenum',2),(65,'BLOCK','Blocknet',2),(66,'BLZ','Bluzelle',2),(67,'BMC','Blackmoon-Crypto',2),(68,'BNB','Binance-Coin',2),(69,'BNT','Bancor-Network-Token',2),(70,'BNTY','Bounty0x',2),(71,'BOST','BoostCoin',2),(72,'BOT','Bodhi',2),(73,'BQ','bitqy',2),(74,'BRD','Bread',2),(75,'BRK','Breakout-Coin',2),(76,'BRX','Breakout-Stake',2),(77,'BTA','Bata',2),(78,'BTC','Bitcoin',2),(79,'BTCD','BitcoinDark',2),(80,'BTCP','Bitcoin-Private',2),(81,'BTG','Bitcoin-Gold',2),(82,'BTM','Bitmark',2),(83,'BTS','BitShares',2),(84,'BTSR','BTSR',2),(85,'BTX','Bitcore',2),(86,'BURST','Burstcoin',2),(87,'BUZZ','BuzzCoin',2),(88,'BYC','Bytecent',2),(89,'BYTOM','Bytom',2),(90,'C20','Crypto20',2),(91,'CANN','CannabisCoin',2),(92,'CAT','BlockCAT',2),(93,'CCRB','CryptoCarbon',2),(94,'CDT','Blox',2),(95,'CFI','Cofound-it',2),(96,'CHAT','ChatCoin',2),(97,'CHIPS','Chips',2),(98,'CLAM','Clams',2),(99,'CLOAK','CloakCoin',2),(100,'CMP','Compcoin',2),(101,'CMT','CyberMiles',2),(102,'CND','Cindicator',2),(103,'CNX','Cryptonex',2),(104,'COFI','CoinFi',2),(105,'COSS','COSS',2),(106,'COVAL','Circuits-Of-Value',2),(107,'CRBIT','CreditBIT',2),(108,'CREA','CreativeCoin',2),(109,'CREDO','Credo',2),(110,'CRW','Crown',2),(111,'CSNO','BitDice',2),(112,'CTR','Centra',2),(113,'CTXC','Cortex',2),(114,'CURE','CureCoin',2),(115,'CVC','Civic',2),(116,'DAI','Dai',2),(117,'DAR','Darcrus',2),(118,'DASH','Dash',2),(119,'DATA','DATAcoin',2),(120,'DAY','Chronologic',2),(121,'DBC','DeepBrain-Chain',2),(122,'DBIX','DubaiCoin',2),(123,'DCN','Dentacoin',2),(124,'DCR','Decred',2),(125,'DCT','DECENT',2),(126,'DDF','Digital-Developers-Fund',2),(127,'DENT','Dent',2),(128,'DFS','DFSCoin',2),(129,'DGB','DigiByte',2),(130,'DGC','Digitalcoin',2),(131,'DGD','DigixDAO',2),(132,'DICE','Etheroll',2),(133,'DLT','Agrello-Delta',2),(134,'DMD','Diamond',2),(135,'DMT','DMarket',2),(136,'DNT','district0x',2),(137,'DOGE','DogeCoin',2),(138,'DOPE','DopeCoin',2),(139,'DRGN','Dragonchain',2),(140,'DTA','Data',2),(141,'DTB','Databits',2),(142,'DYN','Dynamic',2),(143,'EAC','EarthCoin',2),(144,'EBST','eBoost',2),(145,'EBTC','eBTC',2),(146,'ECC','ECC',2),(147,'ECN','E-coin',2),(148,'EDG','Edgeless',2),(149,'EDO','Eidoo',2),(150,'EFL','Electronic-Gulden',2),(151,'EGC','EverGreenCoin',2),(152,'EKT','EDUCare',2),(153,'ELA','Elastos',2),(154,'ELEC','Electrify.Asia',2),(155,'ELF','aelf',2),(156,'ELIX','Elixir',2),(157,'EMB','Embercoin',2),(158,'EMC','Emercoin',2),(159,'EMC2','Einsteinium',2),(160,'ENG','Enigma',2),(161,'ENJ','Enjin-Coin',2),(162,'ENRG','EnergyCoin',2),(163,'EOS','EOS',2),(164,'EOT','EOT-Token',2),(165,'EQT','EquiTrader',2),(166,'ERC','EuropeCoin',2),(167,'ETC','Ethereum-Classic',2),(168,'ETH','Ethereum',2),(169,'ETHD','Ethereum-Dark',2),(170,'ETHOS','Ethos',2),(171,'ETN','Electroneum',2),(172,'ETP','Metaverse-Entropy',2),(173,'ETT','EncryptoTel',2),(174,'EVE','Devery',2),(175,'EVX','Everex',2),(176,'EXCL','ExclusiveCoin',2),(177,'EXP','Expanse',2),(178,'FCT','Factom',2),(179,'FLDC','FoldingCoin',2),(180,'FLO','FlorinCoin',2),(181,'FLT','FlutterCoin',2),(182,'FRST','FirstCoin',2),(183,'FTC','Feathercoin',2),(184,'FUEL','Etherparty',2),(185,'FUN','FunFair',2),(186,'GAM','Gambit',2),(187,'GAME','GameCredits',2),(188,'GAS','Gas',2),(189,'GBG','Golos Gold',2),(190,'GBX','GoByte',2),(191,'GBYTE','Byteball',2),(192,'GCR','GCRCoin',2),(193,'GEO','GeoCoin',2),(194,'GLD','GoldCoin',2),(195,'GNO','Gnosis-Token',2),(196,'GNT','Golem-Tokens',2),(197,'GOLOS','Golos',2),(198,'GRC','Gridcoin',2),(199,'GRS','Groestlcoin',2),(200,'GRWI','Growers-International',2),(201,'GTC','Game',2),(202,'GTO','Gifto',2),(203,'GUP','Guppy',2),(204,'GVT','Genesis-Vision',2),(205,'GXS','GXShares',2),(206,'HBN','HoboNickels',2),(207,'HEAT','HEAT',2),(208,'HMQ','Humaniq',2),(209,'HPB','High-Performance-Blockchain',2),(210,'HSR','Hshare',2),(211,'HUSH','Hush',2),(212,'HVN','Hive',2),(213,'HXX','HexxCoin',2),(214,'ICN','ICONOMI',2),(215,'ICX','ICON',2),(216,'IFC','Infinitecoin',2),(217,'IFT','investFeed',2),(218,'IGNIS','Ignis',2),(219,'INCNT','Incent',2),(220,'IND','Indorse-Token',2),(221,'INF','InfChain',2),(222,'INK','Ink',2),(223,'INS','INS-Ecosystem',2),(224,'INSTAR','Insights-Network',2),(225,'INT','Internet-Node-Token',2),(226,'INXT','Internxt',2),(227,'IOC','IOCoin',2),(228,'ION','ION',2),(229,'IOP','Internet-of-People',2),(230,'IOST','IOStoken',2),(231,'IOTA','IOTA',2),(232,'IOTX','IoTeX',2),(233,'IQT','Iquant-Chain',2),(234,'ITC','IoT-Chain',2),(235,'IXC','iXcoin',2),(236,'IXT','InsureX',2),(237,'J8T','JET8',2),(238,'JNT','Jibrel-Network',2),(239,'KCS','KuCoin',2),(240,'KICK','KickCoin',2),(241,'KIN','KIN',2),(242,'KMD','Komodo',2),(243,'KNC','Kyber-Network',2),(244,'KORE','KoreCoin',2),(245,'LBC','LBRY-Credits',2),(246,'LCC','Litecoin-Cash',2),(247,'LEND','EthLend',2),(248,'LEV','Leverj',2),(249,'LGD','Legends-Room',2),(250,'LINDA','Linda',2),(251,'LINK','ChainLink',2),(252,'LKK','Lykke',2),(253,'LMC','LoMoCoin',2),(254,'LOCI','LOCIcoin',2),(255,'LOOM','Loom-Token',2),(256,'LRC','Loopring',2),(257,'LSK','Lisk',2),(258,'LTC','Litecoin',2),(259,'LUN','Lunyr',2),(260,'MAID','MaidSafeCoin',2),(261,'MANA','Decentraland',2),(262,'MAX','Maxcoin',2),(263,'MBRS','Embers',2),(264,'MCAP','MCAP',2),(265,'MCO','Monaco',2),(266,'MDA','Moeda-Loyalty-Points',2),(267,'MEC','Megacoin',2),(268,'MED','MediBlock',2),(269,'MEME','Memetic',2),(270,'MER','Mercury',2),(271,'MGC','MergeCoin',2),(272,'MGO','MobileGo',2),(273,'MINEX','Minex',2),(274,'MINT','Mintcoin',2),(275,'MITH','Mithril',2),(276,'MKR','Maker',2),(277,'MLN','Melon',2),(278,'MNE','Minereum',2),(279,'MNX','MinexCoin',2),(280,'MOD','Modum',2),(281,'MONA','MonaCoin',2),(282,'MRT','Miners-Reward-Token',2),(283,'MSP','Mothership',2),(284,'MTH','Monetha',2),(285,'MTN','MedToken',2),(286,'MUE','MonetaryUnit',2),(287,'MUSIC','Musicoin',2),(288,'MYB','MyBit-Token',2),(289,'MYST','Mysterium',2),(290,'MZC','Mazacoin',2),(291,'NAMO','Namocoin',2),(292,'NANO','Nano',2),(293,'NAS','Nebulas-Token',2),(294,'NAV','Nav-Coin',2),(295,'NBT','NuBits',2),(296,'NCASH','Nucleus-Vision',2),(297,'NDC','NeverDie-Coin',2),(298,'NEBL','Neblio',2),(299,'NEO','NEO',2),(300,'NEOS','NeosCoin',2),(301,'NET','Nimiq',2),(302,'NLC2','NoLimitCoin',2),(303,'NLG','Gulden',2),(304,'NMC','Namecoin',2),(305,'NMR','Numeraire',2),(306,'NOBL','NobleCoin',2),(307,'NOTE','DNotes',2),(308,'NPXS','Pundi-X-Token',2),(309,'NSR','NuShares',2),(310,'NTO','Fujinto',2),(311,'NULS','Nuls',2),(312,'NVC','Novacoin',2),(313,'NXC','Nexium',2),(314,'NXS','Nexus',2),(315,'NXT','Nxt',2),(316,'OAX','openANX',2),(317,'OBITS','Obits',2),(318,'OCL','Oceanlab',2),(319,'OCN','Odyssey',2),(320,'ODEM','ODEM',2),(321,'ODN','Obsidian',2),(322,'OF','OFCOIN',2),(323,'OK','OKCash',2),(324,'OMG','OmiseGo',2),(325,'OMNI','Omni',2),(326,'ONION','DeepOnion',2),(327,'ONT','Ontology',2),(328,'OPT','Opus',2),(329,'OST','Simple-Token',2),(330,'PART','Particl',2),(331,'PASC','PascalCoin',2),(332,'PAY','TenX',2),(333,'PBL','Pebbles',2),(334,'PBT','Primalbase-Token',2),(335,'PFR','Payfair',2),(336,'PING','CryptoPing',2),(337,'PINK','Pinkcoin',2),(338,'PIVX','PIVX',2),(339,'PIX','Lampix',2),(340,'PLBT','Polybius',2),(341,'PLR','Pillar',2),(342,'PLU','Pluton',2),(343,'POA','POA-Network',2),(344,'POE','Poet',2),(345,'POLY','Polymath',2),(346,'POSW','PoSW-Coin',2),(347,'POT','PotCoin',2),(348,'POWR','Power-Ledger',2),(349,'PPC','Peercoin',2),(350,'PPT','Populous',2),(351,'PPY','Peerplays',2),(352,'PRG','Paragon-Coin',2),(353,'PRL','Oyster-Pearl',2),(354,'PRO','Propy',2),(355,'PST','Primas',2),(356,'PTC','Pesetacoin',2),(357,'PTOY','Patientory',2),(358,'PURA','Pura',2),(359,'QASH','QASH',2),(360,'QAU','Quantum',2),(361,'QLC','Qlink',2),(362,'QRK','Quark',2),(363,'QRL','Quantum-Resistant-Ledger',2),(364,'QSP','Quantstamp',2),(365,'QTL','Quatloo',2),(366,'QTUM','Qtum',2),(367,'QWARK','Qwark',2),(368,'R','Revain',2),(369,'RADS','Radium',2),(370,'RAIN','Condensate',2),(371,'RBIES','Rubies',2),(372,'RBX','Ripto-Bux',2),(373,'RBY','RubyCoin',2),(374,'RCN','Ripio-Credit-Network',2),(375,'RDD','ReddCoin',2),(376,'RDN','Raiden-Network-Token',2),(377,'REC','Regalcoin',2),(378,'RED','Redcoin',2),(379,'REP','Augur',2),(380,'REQ','Request-Network',2),(381,'RHOC','RChain',2),(382,'RIC','Riecoin',2),(383,'RISE','Rise',2),(384,'RLC','RLC-Token',2),(385,'RLT','RouletteToken',2),(386,'RPX','Red-Pulse',2),(387,'RRT','Recovery-Right-Tokens',2),(388,'RUFF','Ruff',2),(389,'RUP','Rupee',2),(390,'RVT','Rivetz',2),(391,'SAFEX','SafeExchangeCoin',2),(392,'SALT','Salt',2),(393,'SAN','Santiment-Network-Token',2),(394,'SBD','Steem-Dollars',2),(395,'SBTC','Super-Bitcoin',2),(396,'SC','Siacoin',2),(397,'SEELE','Seele',2),(398,'SEQ','Sequence',2),(399,'SHIFT','SHIFT',2),(400,'SIB','SIBCoin',2),(401,'SIGMA','SIGMAcoin',2),(402,'SIGT','Signatum',2),(403,'SJCX','Storjcoin-X',2),(404,'SKIN','SkinCoin',2),(405,'SKY','Skycoin',2),(406,'SLR','SolarCoin',2),(407,'SLS','SaluS',2),(408,'SMART','SmartCash',2),(409,'SMT','SmartMesh',2),(410,'SNC','SunContract',2),(411,'SNGLS','SingularDTV',2),(412,'SNM','SONM',2),(413,'SNRG','Synergy',2),(414,'SNT','Status-Network-Token',2),(415,'SOC','All-Sports',2),(416,'SOUL','Phantasma',2),(417,'SPANK','SpankChain',2),(418,'SPC','SpaceChain',2),(419,'SPHR','Sphere',2),(420,'SPR','SpreadCoin',2),(421,'SRN','Sirin-Labs-Token',2),(422,'START','Startcoin',2),(423,'STEEM','Steem',2),(424,'STK','STK-Token',2),(425,'STORJ','Storj',2),(426,'STORM','Storm',2),(427,'STQ','Storiqa',2),(428,'STRAT','Stratis',2),(429,'STX','Stox',2),(430,'SUB','Substratum',2),(431,'SWFTC','SwftCoin',2),(432,'SWIFT','Bitswift',2),(433,'SWT','Swarm-City',2),(434,'SYNX','Syndicate',2),(435,'SYS','SysCoin',2),(436,'TAAS','Taas',2),(437,'TAU','Lamden',2),(438,'TCC','The-ChampCoin',2),(439,'TFL','True-Flip',2),(440,'THC','HempCoin',2),(441,'THETA','Theta-Token',2),(442,'TIME','Time',2),(443,'TIX','Blocktix',2),(444,'TKN','TokenCard',2),(445,'TKR','Trackr',2),(446,'TKS','Tokes',2),(447,'TNB','Time-New-Bank',2),(448,'TNT','Tierion',2),(449,'TOA','ToaCoin',2),(450,'TRAC','OriginTrail',2),(451,'TRC','Terracoin',2),(452,'TRCT','Tracto',2),(453,'TRIG','Triggers',2),(454,'TRST','Trustcoin',2),(455,'TRUE','TrueChain',2),(456,'TRUST','TrustPlus',2),(457,'TRX','Tronix',2),(458,'TUSD','TrueUSD',2),(459,'TX','TransferCoin',2),(460,'UBQ','Ubiq',2),(461,'UKG','UnikoinGold',2),(462,'ULA','Ulatech',2),(463,'UNB','UnbreakableCoin',2),(464,'UNITY','SuperNET',2),(465,'UNO','Unobtanium',2),(466,'UNY','Unity-Ingot',2),(467,'UP','UpToken',2),(468,'URO','Uro',2),(469,'USDT','Tether',2),(470,'UTK','UTrust',2),(471,'VEE','BLOCKv',2),(472,'VEN','VeChain',2),(473,'VERI','Veritaseum',2),(474,'VIA','Viacoin',2),(475,'VIB','Viberate',2),(476,'VIBE','Vibe',2),(477,'VIVO','VIVO',2),(478,'VOISE','Voise',2),(479,'VOX','Voxels',2),(480,'VPN','VPNCoin',2),(481,'VRC','Vericoin',2),(482,'VRM','Verium',2),(483,'VRS','Veros',2),(484,'VSL','vSlice',2),(485,'VTC','Vertcoin',2),(486,'VTR','vTorrent',2),(487,'WABI','WaBi',2),(488,'WAN','Wanchain',2),(489,'WAVES','Waves',2),(490,'WAX','Wax-Token',2),(491,'WCT','Waves-Community',2),(492,'WDC','WorldCoin',2),(493,'WGO','WavesGo',2),(494,'WGR','Wagerr',2),(495,'WINGS','Wings',2),(496,'WPR','WePower',2),(497,'WTC','Walton',2),(498,'WTT','Giga-Watt-Token',2),(499,'XAS','Asch',2),(500,'XAUR','Xaurum',2),(501,'XBC','Bitcoin-Plus',2),(502,'XBY','XtraBYtes',2),(503,'XCN','Cryptonite',2),(504,'XCP','Counterparty',2),(505,'XDN','DigitalNote',2),(506,'XEL','Elastic',2),(507,'XEM','NEM',2),(508,'XID','Sphere-Identity',2),(509,'XLM','Stellar',2),(510,'XMG','Magi',2),(511,'XMR','Monero',2),(512,'XMT','Metal',2),(513,'XMY','Myriadcoin',2),(514,'XPM','Primecoin',2),(515,'XRL','Rialto',2),(516,'XRP','Ripple',2),(517,'XSPEC','Spectrecoin',2),(518,'XST','Stealthcoin',2),(519,'XTZ','Tezos',2),(520,'XUC','Exchange-Union',2),(521,'XVC','Vcash',2),(522,'XVG','Verge',2),(523,'XWC','WhiteCoin',2),(524,'XZC','ZCoin',2),(525,'XZR','ZrCoin',2),(526,'YEE','Yee',2),(527,'YOYOW','YOYOW',2),(528,'ZCC','ZcCoin',2),(529,'ZCL','Zclassic',2),(530,'ZCO','Zebi',2),(531,'ZEC','Zcash',2),(532,'ZEN','ZenCash',2),(533,'ZET','Zetacoin',2),(534,'ZIL','Zilliqa',2),(535,'ZLA','Zilla',2),(536,'ZRX','0x',2);
/*!40000 ALTER TABLE `symbol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `symbol_type`
--

DROP TABLE IF EXISTS `symbol_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `symbol_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_key` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `symbol_type`
--

LOCK TABLES `symbol_type` WRITE;
/*!40000 ALTER TABLE `symbol_type` DISABLE KEYS */;
INSERT INTO `symbol_type` VALUES (2,'DIGITAL_CURRENCY','Digital Currency');
/*!40000 ALTER TABLE `symbol_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'\0','max@mustermann.de','Max','Mustermann','$2a$10$CB4vCQ4kfnfVucYfyvubVuIMyYqsJALhYtPYHgmzWd/HgbmBygtR6','USER','test'),(3,'','admin@mustermann.de','Max','Admin','$2a$10$CB4vCQ4kfnfVucYfyvubVuIMyYqsJALhYtPYHgmzWd/HgbmBygtR6','ADMIN','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-25 17:33:44
