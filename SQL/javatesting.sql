-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 18 mai 2018 à 14:54
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `javatesting`
--

-- --------------------------------------------------------

--
-- Structure de la table `ami`
--

DROP TABLE IF EXISTS `ami`;
CREATE TABLE IF NOT EXISTS `ami` (
  `id_user` int(11) NOT NULL,
  `id_ami` int(11) NOT NULL,
  PRIMARY KEY (`id_user`,`id_ami`),
  KEY `forenKey2` (`id_ami`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ami`
--

INSERT INTO `ami` (`id_user`, `id_ami`) VALUES
(352, 351);

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `expediteur` int(20) NOT NULL,
  `destinataire` int(20) NOT NULL,
  `contenu` text NOT NULL,
  KEY `forenKey52` (`destinataire`),
  KEY `forenKey53` (`expediteur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `message`
--

INSERT INTO `message` (`expediteur`, `destinataire`, `contenu`) VALUES
(351, 355, 'Hello coco'),
(351, 356, 'Kkqkqdkqdk');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(150) NOT NULL,
  `prenom` varchar(150) NOT NULL,
  `pseudo` varchar(150) NOT NULL,
  `ismod` tinyint(1) NOT NULL,
  `level` int(11) NOT NULL,
  `anneenaissance` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`)
) ENGINE=InnoDB AUTO_INCREMENT=412 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `pseudo`, `ismod`, `level`, `anneenaissance`) VALUES
(351, 'LE DEVEDEC', 'Eric', 'riko74', 1, 2, '1982'),
(352, 'BIAGI', 'Alexandre', 'BigBoy', 0, 0, '1968'),
(353, 'DUPON', 'Jean', 'The Cat', 0, 0, '1945'),
(354, 'PIG', 'Peppa', 'Petit cochon', 0, 0, '1999'),
(355, 'BOND', 'Jams', '251Hys', 0, 0, '1765'),
(356, 'PIGNION', 'François', 'PF2008', 0, 0, '2008'),
(357, 'FILLION', 'Charles', 'Fcha74', 1, 1, '1972'),
(361, 'yto', 'eee', '1452', 0, 0, '1268'),
(363, 'COCO', 'jean', 'j152PM', 0, 0, '1854'),
(408, 'COOC', 'kososd', '148sds', 1, 2, '1458'),
(409, 'hjqfhjqf', 'qfqfq', 'qflko45', 0, 0, '1452');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ami`
--
ALTER TABLE `ami`
  ADD CONSTRAINT `forenKey1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `forenKey2` FOREIGN KEY (`id_ami`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `forenKey52` FOREIGN KEY (`destinataire`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `forenKey53` FOREIGN KEY (`expediteur`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
