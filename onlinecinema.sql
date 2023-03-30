-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinecinema
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_cast`
--

DROP TABLE IF EXISTS `tb_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_cast` (
  `cast_id` int NOT NULL AUTO_INCREMENT,
  `cast_name` varchar(255) NOT NULL,
  `cast_character` varchar(255) DEFAULT NULL,
  `cast_profile_img` varchar(255) NOT NULL,
  PRIMARY KEY (`cast_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_cast`
--

LOCK TABLES `tb_cast` WRITE;
/*!40000 ALTER TABLE `tb_cast` DISABLE KEYS */;
INSERT INTO `tb_cast` VALUES (27,'Wu Renlin','Ma Youtie / 马有铁','/vQlvZz8mOH1S4rRubOIx3HMkFCY.jpg'),(28,'Hai Qing','Cao Guiying / 曹贵英','/9QKqKPmT1vhjXpbrPkAhCprbf3R.jpg'),(29,'Yang Guangrui','张永福儿子','/eHr9VbXabX0xlyUuaOv3klCUzF9.jpg'),(30,'Lei Jiayin','仲达 / 陆小谷','/vlYri4zkSMLRXzw7icuPsm5Fwxb.jpg'),(31,'Zhang Xiaofei','金好','/9PNUlzUS7bn5p6OsRnkSCzUI1FV.jpg'),(32,'Zhang Youhao','陆小谷 / 仲达','/vvmA0Rtjipp7x2iQmrxUlpVS9zU.jpg'),(33,'Sha Yi','陆小谷爸爸','/az62F1W1itfZnaU5PAJbISioGVF.jpg'),(34,'Liu Mintao','陆小谷妈妈','/wWL0NqTUsCxa14Uuywsv55ZsykB.jpg'),(35,'Allison Williams','Gemma','/yBolxMiZL1EjmNogPzTAuT85qad.jpg'),(36,'Violet McGraw','Cady','/ymLn1LhODokVzOMsovXWeA2OtLK.jpg'),(37,'Jenna Davis','M3GAN (voice)','/mdGNqspZzjZd26Wn0tyQPWI0RIz.jpg'),(38,'Amie Donald','M3GAN','/rYMbQYfL0GHQHWywmiBBHcdqSvy.jpg'),(39,'Jen Van Epps','Tess','/pI18BVf1c6frUMoXj2bV82NEiLE.jpg'),(40,'Gerard Butler','Brodie Torrance','/rTO5opVC3Gs6hPYAxWSP9eEjogi.jpg'),(41,'Mike Colter','Louis Gaspare','/rTcfLDlcQ78tVVUDHSFJYiodJtb.jpg'),(42,'Yoson An','Dele','/uKvJ15U5XJuvGMx4uXmimcAd0Qt.jpg'),(43,'Tony Goldwyn','Scarsdale','/m4CJX4QwjZsdYJKe8OJamLRnlQh.jpg'),(44,'Daniella Pineda','Bonnie','/o8h5qbyy8A0zKTRN57YOlQOafyQ.jpg'),(45,'Kate Winslet','Rose Dewitt Bukater','/5QKFvhIqKbI7adpG5TZBVZDv0hF.jpg'),(46,'Leonardo DiCaprio','Jack Dawson','/wo2hJpn04vbtmh0B9utCFdsQhxM.jpg'),(47,'Billy Zane','Cal Hockley','/7CBwxqE00aZAAEBaRkapylgdi15.jpg'),(48,'Gloria Stuart','Old Rose','/9aG7UUX3PWIGGf1KRY5TsBSoNz9.jpg'),(49,'Kathy Bates','Molly Brown','/3kYBqLRvsAjOK8RQvCI7PKUA3DP.jpg'),(50,'Donnie Yen','Qiao Feng / Xiao Feng','/hTlhrrZMj8hZVvD17j4KyAFWBHc.jpg'),(51,'Yukee Chen','A Zhu','/zRPQ7suBiQoCUEzt9BHtzKkxSAx.jpg'),(52,'Liu Yase','A Zi','/2UBsASN6G37pjZtjl0Zse6S74y9.jpg'),(53,'Kara Hui','Ruan Xingzhu','/6OV9kM62Y7M7EswtkpCThs0QAxg.jpg'),(54,'Wu Yue','Murong Fu','/hRePA3mh9mfAqX7SJ4rGxrNrxT7.jpg'),(55,'Alice Orr-Ewing','Laura','/jOtE1FZvzjpWctXEV5ephzgFXaT.jpg'),(56,'Joe Anderson','Lucifer','/x0Fz2mIMU4Prk7I43pn87Fyno40.jpg'),(57,'Peter Mensah','Archangel Michael','/t94TFc6f71AUmZFqdaQfjr7LTRp.jpg'),(58,'Spencer Wilding','Beast of the Ground','/7sSwsMl9RHVIwewwkdHtTV3kCGn.jpg'),(59,'James Faulkner','Cardinal Vincini','/wMDpbctF9cAL5nYnSL6AcXUTG5K.jpg'),(60,'Liam Neeson','Oskar Schindler','/bboldwqSC6tdw2iL6631c98l2Mn.jpg'),(61,'Ben Kingsley','Itzhak Stern','/vQtBqpF2HDdzbfXHDzR4u37i1Ac.jpg'),(62,'Ralph Fiennes','Amon Goeth','/tJr9GcmGNHhLVVEH3i7QYbj6hBi.jpg'),(63,'Caroline Goodall','Emilie Schindler','/4cagGtMqACvkuw6Llq8Li8UJ1AR.jpg'),(64,'Jonathan Sagall','Poldek Pfefferberg','/waxNDsgfw7CXXO3LH8EdKi8z7VV.jpg'),(65,'Song Kang-ho','Kim Ki-taek','/dyWUKQlNyr7SUAjo58VZXvPODkr.jpg'),(66,'Lee Sun-kyun','Park Dong-ik','/p8ryrexuoVqHA9qyC3HDXnlasvD.jpg'),(67,'Cho Yeo-jeong','Yeon-kyo','/n7YWOoquBL9g3qEwQ2zvrQSW96L.jpg'),(68,'Choi Woo-shik','Ki-woo','/bIdt6LrqMpGQtGTZ1gmji6eGzTH.jpg'),(69,'Park So-dam','Ki-jung','/gaDnEiMD5PClT9ARg1bSFyexbor.jpg'),(70,'Ha Jung-woo','Gang-rim','/alHcDyLYbc6C2X9yOHw8mNHZVGu.jpg'),(71,'Ju Ji-hoon','Hae Won-maek','/7PYfUrBBXhYv5PIsTalJhjbRteg.jpg'),(72,'Kim Hyang-gi','Deok-choon','/qAfGNnSzuSRVISUtLssokeHtoWy.jpg'),(73,'Ma Dong-seok','Sung-ju / Household God','/zt1vx7FesNA4x6mTZtyzu2uco8E.jpg'),(74,'Kim Dong-wook','Kim Su-hong','/kaPpIz331j9sWGEs9IwCWGqoUa.jpg'),(75,'Joaquin Phoenix','Arthur Fleck / Joker','/1UzIGSKFH0A9ouwnMwQQWUiqV2s.jpg'),(76,'Robert De Niro','Murray Franklin','/9lspzfj4PC5Z0WgSAbUqItfS19E.jpg'),(77,'Zazie Beetz','Sophie Dumond','/ijrT4pvALvxU0gphea4YxDnDh6e.jpg'),(78,'Frances Conroy','Penny Fleck','/aJRQAkO24L6bH8qkkE5Iv1nA3gf.jpg'),(79,'Brett Cullen','Thomas Wayne','/4P6TsRcnr9MRbXlCdHitulGM5LT.jpg'),(80,'Mads Mikkelsen','Lucas','/ntwPvV4GKGGHO3I7LcHMwhXfsw9.jpg'),(81,'Thomas Bo Larsen','Theo','/3j2Iq6wkpgNwKz1H2kkywKB6go.jpg'),(82,'Annika Wedderkopp','Klara','/o0vCTKao4TfkOxA6jxW4LnIS2Ev.jpg'),(83,'Lasse Fogelstrøm','Marcus','/jtFqRNMikzydxPo1qW5RT2mIpCK.jpg'),(84,'Susse Wold','Grethe','/bSO3hhok9sdJDoRDHGAJbfafMTk.jpg'),(85,'Zhou Dongyu','Chen Nian','/vD2vVvIIx8ygzkhUQZJLkvYT1jr.jpg'),(86,'Jackson Yee','Xiao Bei (Liu Beishan)','/d7y9zz1e5PDstH9PaLFXSXKaXIC.jpg'),(87,'Yin Fang','Zheng Ye','/z5NJCOusV04kVzWXGPqsdUBeMGV.jpg'),(88,'Huang Jue','Lao Yang','/9J3u6fOIflN2fDo65Wzf30WQCWc.jpg'),(89,'Wu Yue','Chen Nian\'s mother','/xPYiYvIYYsivmt0ngctcz7jruXb.jpg'),(90,'Leslie Cheung','Xiao Douzi','/6H8gTq1QCB0bD9CGDhrIJITiGVX.jpg'),(91,'Zhang Feng Yi','Duan Xiaolou','/l3PtQzEU84l7B9XA6SitAGIIJqH.jpg'),(92,'Gong Li','Juxian','/uP3Fs3QUsGBxhNqGFWMnN1Z2l8z.jpg'),(93,'Jiang Wenli','Yan Hong','/34dJE4iDo6cAeJffqHtYlDFptbl.jpg'),(94,'Ge You','Master Yuan Si Qing','/s0JXQhCmKUmtxiuI2rjH8hfE470.jpg'),(95,'Song Kang-ho','Sang-hyeon','/dyWUKQlNyr7SUAjo58VZXvPODkr.jpg'),(96,'Gang Dong-won','Dong-soo','/xGPT8rgvWuK8Qh7BKNd7fhKs8Sk.jpg'),(97,'Bae Doona','Su-jin','/4NrnwPEeOWWfgyoxIXQybBDtJ5d.jpg'),(98,'IU','So-young','/xxYawgFO1woBRveH7WL9D1BxB4W.jpg'),(99,'Lee Joo-young','Detective Lee','/qItCRSPDO5VvAgueUHNEbg86ZMz.jpg');
/*!40000 ALTER TABLE `tb_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_category`
--

DROP TABLE IF EXISTS `tb_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category` VALUES (1,'院线热映'),(2,'公益放映'),(3,'欧美大片'),(4,'励志电影');
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_comment`
--

DROP TABLE IF EXISTS `tb_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `comment_user_id` int NOT NULL,
  `comment_news_id` int NOT NULL,
  `comment_content` text NOT NULL,
  `comment_create_time` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `comment_user_id_idx` (`comment_user_id`),
  KEY `comment_news_id_idx` (`comment_news_id`),
  CONSTRAINT `comment_news_id` FOREIGN KEY (`comment_news_id`) REFERENCES `tb_news` (`news_id`),
  CONSTRAINT `comment_user_id` FOREIGN KEY (`comment_user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_comment`
--

LOCK TABLES `tb_comment` WRITE;
/*!40000 ALTER TABLE `tb_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_crew`
--

DROP TABLE IF EXISTS `tb_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_crew` (
  `crew_id` int NOT NULL AUTO_INCREMENT,
  `crew_name` varchar(255) NOT NULL,
  `crew_profile_img` varchar(255) NOT NULL,
  `crew_job` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`crew_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_crew`
--

LOCK TABLES `tb_crew` WRITE;
/*!40000 ALTER TABLE `tb_crew` DISABLE KEYS */;
INSERT INTO `tb_crew` VALUES (5,'Li Ruijun','/5Eo2AAmRtKdH5D1QWJBEJBzttWN.jpg','Director'),(6,'Su Lun','/tdBGa2zaeqeojWWUwkb21rzzCG6.jpg','Director'),(7,'Gerard Johnstone','/cdvpfGKHLchtOtoN8gtVWhSHabN.jpg','Director'),(8,'Jean-François Richet','/PipS4XiDbV0hDfhydX9p8PkqZh.jpg','Director'),(9,'James Cameron','/9NAZnTjBQ9WcXAQEzZpKy4vdQto.jpg','Director'),(10,'Donnie Yen','/hTlhrrZMj8hZVvD17j4KyAFWBHc.jpg','Director'),(11,'Steven Spielberg','/tZxcg19YQ3e8fJ0pOs7hjlnmmr6.jpg','Director'),(12,'Bong Joon-ho','/tKLJBqbdH6HFj2QxLA5o8Zk7IVs.jpg','Director'),(13,'Kim Yong-hwa','/7Y7v7kInxRFLYYr6zNib8E3CcXR.jpg','Director'),(14,'Todd Phillips','/A6FPht87DiqXzp456WjakLi2AtP.jpg','Director'),(15,'Thomas Vinterberg','/1lChKBPVLqbn7Kl8lHghybBVzgw.jpg','Director'),(16,'Derek Tsang','/aEh2IH7r37idxR1xMXtugOOrtBw.jpg','Director'),(17,'Chen Kaige','/8XLZnrIzTjtee1eGOkxGWMWo8SE.jpg','Director'),(18,'Hirokazu Kore-eda','/oIDlmHZZj6jgrPy6onX9igENYOi.jpg','Director'),(20,'liuye','/4P6TsRcnr9MRbXlCdHitulGM5LT.jpg',NULL),(21,'LiuYE','/4NrnwPEeOWWfgyoxIXQybBDtJ5d.jpg',NULL);
/*!40000 ALTER TABLE `tb_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_movie`
--

DROP TABLE IF EXISTS `tb_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `movie_name` text NOT NULL,
  `movie_overview` text NOT NULL,
  `movie_lang` varchar(45) NOT NULL,
  `movie_tagline` text NOT NULL,
  `movie_area` varchar(255) NOT NULL,
  `movie_runtime` int NOT NULL,
  `movie_status` int NOT NULL,
  `movie_release_time` varchar(45) NOT NULL,
  `movie_genres` text NOT NULL,
  `movie_price` decimal(10,2) NOT NULL,
  `movie_type` int NOT NULL DEFAULT '0',
  `movie_poster` text NOT NULL,
  `movie_preview_url` text NOT NULL,
  `movie_play_url` text NOT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `movie_category_idx` (`movie_type`),
  CONSTRAINT `movie_category` FOREIGN KEY (`movie_type`) REFERENCES `tb_category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_movie`
--

LOCK TABLES `tb_movie` WRITE;
/*!40000 ALTER TABLE `tb_movie` DISABLE KEYS */;
INSERT INTO `tb_movie` VALUES (9,'隐入尘烟','影片讲述西北农村，两个被各自家庭抛弃的孤独个体，在日复一日的耕耘中相濡以沫的故事，武仁林与海清展现了一对底层农民夫妇从陌生到熟悉，从相知到相守的心路历程。','普通话','','中国',133,1,'2023-03-27','剧情,爱情',19.90,1,'/wJ8Jc6FC5wOaD8ifNQjXJf2x0DQ.jpg','10009preview.mp4','10009.m3u8'),(11,'梅根','“她不只是个玩具，而是这个家的一份子。”\r 梅根（M3GAN）是一个具备高度人工智能、栩栩如生的玩具人偶，被设计成小孩子最好的玩伴和令家长最安心的盟友。这款玩具人偶由一名玩具公司杰出的机器人专家洁玛（艾莉森·威廉姆斯 饰）精心设计，梅根能够借由聆听以及观察学习人类行为，并且成为她照顾的小孩的朋友、老师、玩伴和保护者。\r 当洁玛突然成为她失去双亲的八岁侄女凯蒂（维奥莱特·麦格劳 饰）的监护人之后，洁玛并不确定也没有准备好能够成为一个好的家长，加上她的工作压力非常巨大，于是就决定将她设计的人工智能玩具人偶梅根和凯蒂配对，试图解决照顾凯蒂这个孤儿和纾解她工作压力这两个问题，但是她的这个决定却造成无法想像的严重后果。','英语','友谊全面进化','美国',102,1,'2023-03-27','科幻,恐怖,喜剧',19.90,1,'/g2ZFHL0DXgqdjsFhAfkPXJLfzvK.jpg','10011preview.mp4','10011.m3u8'),(12,'飞机陷落','飞行员布罗迪·托伦斯完成了一项英勇的任务：在飞机被风暴摧毁的情况下，成功降落在战争敌对地区，之后却发现自己受到了武装海盗的威胁，他们想劫机并把乘客作为人质。在全世界都在寻找这架失踪的飞机时，布罗迪必须振作起来，在救援到来之前保护乘客的安全。','英语','一起生存或独自死亡。','美国',107,1,'2023-03-27','动作,冒险,惊悚',19.90,1,'/1dykJ9unmBup428ieku04S8j36c.jpg','10012preview.mp4','10012.m3u8'),(14,'天龙八部之乔峰传','北宋年间，丐帮帮主乔峰英雄侠义，受武林拥戴，却突遭指认为契丹人而众叛亲离。追寻身世，寻找仇人途中，乔峰与慕容家婢女阿朱相识相知，情愫互生。二人历经聚贤庄、雁门关、镜湖风波，直至阿朱因马夫人设计被乔峰错杀，乔峰终查出一切皆是慕容复为复辟燕国的阴谋，乔峰手刃慕容复，随后带着阿朱的遗愿孤身前往塞外。','普通话/粤语','','中国',132,1,'2023-03-27','动作,剧情',19.90,2,'/zr28ZaTpZeB7ACFVIoa4YuxFHgh.jpg','10014preview.mp4','10014.m3u8'),(16,'辛德勒的名单','1939年9月，二次世界大战方兴未艾，德军进驻波兰，将纳綷军队组成“精卫队”，下令重新安顿犹太人的户口。德商辛德勒值此战乱，一方面迎逢德军各级军官，另一方面则进行低价引进犹太劳工，并委任犹太工头以撒处理场内大小事。辛德勒虽藉机大发战争财，但对以撒却颇为仁厚而备受爱戴。“精卫队”司令阿蒙生性残暴，以枪杀劳工为乐，整个占领区如同炼狱般。1941年3月，德军将维斯图拉河以南归为犹太区，阿蒙更变本加厉，1944年4月甚至大规模展开焚尸行动，辛德勒看不惯如此行为便和以撒商议出一计，以所有资产行贿阿蒙，以捷克新厂需要大批劳工制作军火为由，列出一份长至千人的名单，运往家乡布蓝恩利兹躲藏，待德方发现时，大战结束了，辛德勒则仍以战争罪犯法办。','英语','','美国',195,1,'2023-03-27','剧情,历史,战争',19.90,2,'/kukYu89yda6NrifW5kpLoOTU7YH.jpg','10016preview.mp4','10016.m3u8'),(17,'寄生虫','《寄生虫》是由奉俊昊执导，宋康昊、李善均、赵茹珍、崔宇植、朴素丹主演的剧情片，于2019年5月30日在韩国上映。该片讲述了一家四口全是无业游民的爸爸金基泽成天游手好闲，直到积极向上的长子金基宇靠着伪造的文凭来到富豪朴社长的家应征家教，两个天差地别的家庭因而被卷入一连串的意外事件之中的故事。  基宇（崔宇植 饰）出生在一个贫穷的家庭之中，和妹妹基婷（朴素丹 饰）以及父母在狭窄的地下室里过着相依为命的日子。一天，基宇的同学上门拜访，他告诉基宇，自己在一个有钱人家里给他们的女儿做家教，太太是一个头脑简单出手又阔绰的女人，因为自己要出国留学，所以将家教的职位暂时转交给基宇。就这样，基宇来到了朴社长（李善均 饰）家中，并且见到了他的太太（赵汝贞 饰），没过多久，基宇的妹妹和父母也如同寄生虫一般的进入了朴社长家里工作。然而，他们的野心并没有止步于此，基宇更是和大小姐坠入了爱河。随着时间的推移，朴社长家里隐藏的秘密渐渐浮出了水面。','韩语','','韩国',132,1,'2023-03-27','喜剧,惊悚,剧情',19.90,2,'/nwTQE4vWe6NoFSEgxGu4hO9J4Tv.jpg','10017preview.mp4','10017.m3u8'),(19,'小丑','亚瑟·弗兰克是一名以小丑职业为生的普通人，患有精神疾病的他和母亲一同住在哥谭市的一座公寓里，幻想成为脱口秀演员的亚瑟为了这个目标而努力的生活着，但是现实却屡次击败他的梦想，亚瑟渐渐地变得越来越癫狂，某天在地铁上，亚瑟为了自保杀害了几名嘲笑他的人，同时，一个疯狂的想法在亚瑟心灵萌发……  在看似和平的哥谭市，即将发生翻天覆地的巨变。','英语','摆出一张笑脸吧','加拿大',122,1,'2023-03-27','犯罪,惊悚,剧情',19.90,3,'/kjNbXFfYLDUdFpfaMkzHbJ5Zq30.jpg','10019preview.mp4','10019.m3u8'),(20,'狩猎','　　刚刚和妻子离婚的卢卡斯目前在一家托儿所工作，心地善良个性温和的他很快就受到了同事和孩子们的喜爱，其中，一个名叫卡拉的早熟女孩对卢卡斯尤为的亲近。面对女孩幼稚 而单纯的示好，卢卡斯只能婉转的拒绝，可令他没有想到的是，这一举动将他的生活推向了风口浪尖。 卡拉报复性的谎言让卢卡斯背负起了性侵女童的罪名，一时间，这个好好先生成为了整个小镇排挤和压迫的对象。好友的愤怒，前妻的不信任，爱犬的死亡和陌生人的恶意让卢卡斯几近崩溃，而当小小的卡拉吐露真相之后，恶意却并没有随着卢卡斯的重获清白而划下句点。','丹麦语','','比利时',115,1,'2023-03-27','剧情',19.90,3,'/ou9Nno9SAdh8lw4LgSRz8LyOqWp.jpg','10020preview.mp4','10020.m3u8'),(21,'少年的你','一场高考前夕的校园意外，改变了两个少年的命运。陈念 性格内向，是学校里的优等生，努力复习、考上好大学是高三的她唯一的念头。同班同学的意外坠楼牵扯出一连串不为人知的故事，陈念也被一点点卷入其中…在她最孤独的时刻，一个叫小北 的少年闯入了她的世界…大多数人的18岁都是明媚、快乐的，而他们却在18岁这个夏天提前尝到了成人世界的漠然。一场秘而不宣的“战斗”正在上演，他们将一起守护少年的尊严  。','普通话','你保护世界，我保护你','中国',138,1,'2023-03-27','剧情',19.90,3,'/bjhoWPRihKSQNbqxvQgcEkOVN3j.jpg','10021preview.mp4','10021.m3u8'),(22,'霸王别姬','　　段小楼（张丰毅）与程蝶衣（张国荣）是一对打小一起长大的师兄弟，两人一个演生，一个饰旦，一向配合天衣无缝，尤其一出《霸王别姬》，更是誉满京城，为此，两人约定合演一辈子《霸王别姬》。但两人对戏剧与人生关系的理解有本质不同，段小楼深知戏非人生，程蝶衣则是人戏不分。段小楼在认为该成家立业之时迎娶了名妓菊仙（巩俐），致使程蝶衣认定菊仙是可耻的第三者，使段小楼做了叛徒，自此，三人围绕一出《霸王别姬》生出的爱恨情仇战开始随着时代风云的变迁不断升级，终酿成悲剧。','普通话','风华绝代','中国',171,1,'2023-03-27','剧情',19.90,4,'/l47ebuqU5gzBxBEJ0vQi1Ui3h8x.jpg','10022preview.mp4','10022.m3u8'),(23,'1234','1234','1234','1234','1234',1234,1,'2023-03-27','1234',123.00,1,'/2LJUlshZL9CxWcyH7CYUS1FFAx9.jpg','10023preview.mp4','10023.m3u8'),(25,'掮客','尚贤（宋康昊 饰）经营洗衣店，可惜生意淡静更负债累累。好兄弟东洙（姜栋元 饰）本身是孤儿，为“婴儿暂存箱”机构工作。在一个雨夜，他们竟秘密带走其中一个箱里的男婴。翌日，年轻的母亲素英（李知恩 饰）却找上门要求领回自己的孩子雨盛！本想报警揭发，但素英最终竟同意让两个“中间人”为儿子寻觅更好的父母，并决定跟随两人同行。另一边厢，刑警秀珍（裴斗娜 饰）联同后辈李刑警（李珠英 饰）原来一直跟踪着他们，暗中收集证据，决心拉人定案！剧情由暂存箱里的婴儿引发，牵连周遭5个关键人物，展开一段意想不到的独特旅程，触动最微妙的世故人情。','韩语','','韩国',129,1,'2023-03-27','剧情,喜剧',19.90,4,'/vAVcC1katFIbHFdFZ271DBgxAbQ.jpg','10025preview.mp4','10025.m3u8'),(27,'2','2','2','2','2',2,1,'2023-04-27','2',2.00,1,'/zt1vx7FesNA4x6mTZtyzu2uco8E.jpg','','');
/*!40000 ALTER TABLE `tb_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_movie_cast`
--

DROP TABLE IF EXISTS `tb_movie_cast`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_movie_cast` (
  `movie_cast_id` int NOT NULL AUTO_INCREMENT,
  `movie_cast_movie_id` int NOT NULL,
  `movie_cast_cast_id` int NOT NULL,
  PRIMARY KEY (`movie_cast_id`),
  KEY `t_m_c_movie_id_idx` (`movie_cast_movie_id`),
  KEY `t_mc_c_cast_id_idx` (`movie_cast_cast_id`),
  CONSTRAINT `t_m_c_movie_id` FOREIGN KEY (`movie_cast_movie_id`) REFERENCES `tb_movie` (`movie_id`),
  CONSTRAINT `t_mc_c_cast_id` FOREIGN KEY (`movie_cast_cast_id`) REFERENCES `tb_cast` (`cast_id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_movie_cast`
--

LOCK TABLES `tb_movie_cast` WRITE;
/*!40000 ALTER TABLE `tb_movie_cast` DISABLE KEYS */;
INSERT INTO `tb_movie_cast` VALUES (4,9,27),(5,9,28),(6,9,29),(12,11,35),(13,11,36),(14,11,37),(15,11,38),(16,11,39),(17,12,40),(18,12,41),(19,12,42),(20,12,43),(21,12,44),(27,14,50),(28,14,51),(29,14,52),(30,14,53),(31,14,54),(37,16,60),(38,16,61),(39,16,62),(40,16,63),(41,16,64),(42,17,65),(43,17,66),(44,17,67),(45,17,68),(46,17,69),(52,19,75),(53,19,76),(54,19,77),(55,19,78),(56,19,79),(57,20,80),(58,20,81),(59,20,82),(60,20,83),(61,20,84),(62,21,85),(63,21,86),(64,21,87),(65,21,88),(66,21,89),(67,22,90),(68,22,91),(69,22,92),(70,22,93),(71,22,94),(74,25,95),(75,25,96),(76,25,97),(77,25,98),(78,25,99),(99,23,31),(100,23,30);
/*!40000 ALTER TABLE `tb_movie_cast` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_movie_crew`
--

DROP TABLE IF EXISTS `tb_movie_crew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_movie_crew` (
  `movie_crew_id` int NOT NULL AUTO_INCREMENT,
  `movie_crew_movie_id` int NOT NULL,
  `movie_crew_crew_id` int NOT NULL,
  PRIMARY KEY (`movie_crew_id`),
  KEY `tmc_movie_id_idx` (`movie_crew_movie_id`),
  KEY `tmc_crew_id_idx` (`movie_crew_crew_id`),
  CONSTRAINT `tmc_crew_id` FOREIGN KEY (`movie_crew_crew_id`) REFERENCES `tb_crew` (`crew_id`),
  CONSTRAINT `tmc_movie_id` FOREIGN KEY (`movie_crew_movie_id`) REFERENCES `tb_movie` (`movie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_movie_crew`
--

LOCK TABLES `tb_movie_crew` WRITE;
/*!40000 ALTER TABLE `tb_movie_crew` DISABLE KEYS */;
INSERT INTO `tb_movie_crew` VALUES (1,9,5),(3,11,7),(4,12,8),(6,14,10),(7,16,11),(8,17,12),(10,19,14),(11,20,15),(12,21,16),(13,22,17),(16,25,18),(31,23,5),(32,23,17),(35,27,21);
/*!40000 ALTER TABLE `tb_movie_crew` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_news`
--

DROP TABLE IF EXISTS `tb_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_news` (
  `news_id` int NOT NULL AUTO_INCREMENT,
  `news_content` text NOT NULL,
  `news_createtime` datetime NOT NULL,
  `news_title` varchar(255) NOT NULL,
  `news_tagline` text,
  `news_creater_id` int NOT NULL,
  PRIMARY KEY (`news_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_news`
--

LOCK TABLES `tb_news` WRITE;
/*!40000 ALTER TABLE `tb_news` DISABLE KEYS */;
INSERT INTO `tb_news` VALUES (2,'<p style=\"text-align: justify;\"><img src=\"http://192.168.1.2:80/imgs/zt1vx7FesNA4x6mTZtyzu2uco8E.jpg\" alt=\"...\" data-href=\"\" style=\"\"></p><p style=\"text-align: justify;\"><br></p>','2023-02-26 21:43:16','wergrg1231232','123123123',1),(3,'<p>12312312312312312</p><p><img src=\"http://192.168.1.2:80/imgs/4P6TsRcnr9MRbXlCdHitulGM5LT.jpg\" alt=\"...\" data-href=\"\" style=\"\"/></p>','2023-02-27 12:56:37','123123','123123',1);
/*!40000 ALTER TABLE `tb_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `trade_no` varchar(64) NOT NULL,
  `alipay_trade_no` varchar(64) NOT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `user_id` int NOT NULL,
  `status` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `order_user_id_idx` (`user_id`),
  CONSTRAINT `order_user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (28,'2023227124748633085','2023022722001454430502018048',19.90,'2023-02-27 12:47:48','2023-02-27 12:49:01',2,2),(29,'2023227125202344585','2023022722001454430502017611',2.00,'2023-02-27 12:52:03','2023-02-27 12:52:46',1,2),(30,'2023316151125723925','2023031622001454430502027009',19.90,'2023-03-16 15:11:26','2023-03-16 15:11:47',1,2);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order_detail`
--

DROP TABLE IF EXISTS `tb_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_order_detail` (
  `order_detail_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `movie_id` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `num` int NOT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `aaa_idx` (`movie_id`),
  KEY `order_id_idx` (`order_id`),
  CONSTRAINT `aaa` FOREIGN KEY (`movie_id`) REFERENCES `tb_movie` (`movie_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `tb_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order_detail`
--

LOCK TABLES `tb_order_detail` WRITE;
/*!40000 ALTER TABLE `tb_order_detail` DISABLE KEYS */;
INSERT INTO `tb_order_detail` VALUES (24,28,22,19.90,1),(25,29,27,2.00,1),(26,30,9,19.90,1);
/*!40000 ALTER TABLE `tb_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ticket`
--

DROP TABLE IF EXISTS `tb_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_ticket` (
  `ticket_id` int NOT NULL AUTO_INCREMENT,
  `ticket_no` varchar(45) NOT NULL,
  `ticket_user_id` int NOT NULL,
  `ticket_movie_id` int NOT NULL,
  `ticket_expire_time` datetime NOT NULL,
  `ticket_create_time` datetime NOT NULL,
  PRIMARY KEY (`ticket_id`),
  UNIQUE KEY `ticket_no_UNIQUE` (`ticket_no`),
  KEY `ticket_user_id_idx` (`ticket_user_id`),
  KEY `ticket_movie_id_idx` (`ticket_movie_id`),
  CONSTRAINT `ticket_movie_id` FOREIGN KEY (`ticket_movie_id`) REFERENCES `tb_movie` (`movie_id`),
  CONSTRAINT `ticket_user_id` FOREIGN KEY (`ticket_user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ticket`
--

LOCK TABLES `tb_ticket` WRITE;
/*!40000 ALTER TABLE `tb_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_nickname` varchar(45) NOT NULL,
  `user_avator` varchar(255) NOT NULL,
  `user_sex` tinyint NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'1','1','5','zt1vx7FesNA4x6mTZtyzu2uco8E.jpg',2),(2,'2','2','2','zt1vx7FesNA4x6mTZtyzu2uco8E.jpg',1);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-29 13:53:01
