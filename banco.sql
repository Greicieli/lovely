-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: lovely
-- ------------------------------------------------------
-- Server version	5.6.21

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `idAdmin` bigint(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(80) DEFAULT NULL,
  `senha` varchar(80) DEFAULT NULL,
  `fk_idFuncionario` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`idAdmin`),
  UNIQUE KEY `idAdmin_UNIQUE` (`idAdmin`),
  KEY `fk_idFuncionario` (`fk_idFuncionario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda`
--

DROP TABLE IF EXISTS `agenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda` (
  `idAgendamento` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `hora` date DEFAULT NULL,
  `procedimento` varchar(80) DEFAULT NULL,
  `idCliente` bigint(10) unsigned NOT NULL,
  `idFuncionario` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`idAgendamento`),
  KEY `idCliente` (`idCliente`),
  KEY `idFuncionario` (`idFuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda`
--

LOCK TABLES `agenda` WRITE;
/*!40000 ALTER TABLE `agenda` DISABLE KEYS */;
INSERT INTO `agenda` VALUES (2,'2015-06-28','1970-01-01','Cortar Cabelo',1,1),(3,'2016-05-09','1970-01-01','manicure',1,1),(4,'2015-01-10','1970-01-01','manicure',1,1),(5,'2015-03-11','1970-01-01','Corte',3,4),(6,'2015-06-10','1969-12-31','cortar cabelo',3,4),(7,'2016-10-17','1970-01-01','manicure',2,1),(8,'2015-10-17','1970-01-01','manicure',1,1),(9,'2015-10-17','1970-01-01','manicure',1,1);
/*!40000 ALTER TABLE `agenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) DEFAULT NULL,
  `telefone` varchar(80) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `endereco` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Maria','48-3039.7643','maria@gmail.com','Florianópolis'),(2,'Joana','48-3039.7600','joana@gmail.com','Florianópolis'),(3,'Jessica','3232665','j@lovely.com','Fpolis');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `idFuncionario` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) DEFAULT NULL,
  `funcao` varchar(80) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE KEY `idFuncionario_UNIQUE` (`idFuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'Luana','Manicure','luana@lovely.com.br'),(2,'Jane','Cabelereira','jane@lovely.com.br'),(3,'Luiza','Gerente','luiza@lovely.com.br'),(4,'Fernades','Barbeiro','fernandes@lovely.com.br'),(6,'Greici','manicure','greici@gmail.com');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `idProduto` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `descricao` varchar(80) NOT NULL,
  `valor` float NOT NULL,
  `quantidade` int(10) NOT NULL,
  `fk_idTipoDeProduto` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`idProduto`),
  KEY `fk_idTipoDeProduto` (`fk_idTipoDeProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'Shampoo para cabelos seco',25,9,2),(2,'fress',29.5,5,2),(3,'fress',29.5,6,3);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipodeproduto`
--

DROP TABLE IF EXISTS `tipodeproduto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodeproduto` (
  `idTipoDeProduto` bigint(10) unsigned NOT NULL,
  `tipoDeProduto` varchar(80) NOT NULL,
  PRIMARY KEY (`idTipoDeProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipodeproduto`
--

LOCK TABLES `tipodeproduto` WRITE;
/*!40000 ALTER TABLE `tipodeproduto` DISABLE KEYS */;
INSERT INTO `tipodeproduto` VALUES (2,'Shampoo'),(3,'condicionador');
/*!40000 ALTER TABLE `tipodeproduto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-23 21:29:55
