-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : mar. 13 fév. 2024 à 19:27
-- Version du serveur : 5.7.39
-- Version de PHP : 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `iagea_ism_2024`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

CREATE TABLE `agence` (
  `id_ag` int(11) NOT NULL,
  `numero_ag` varchar(25) NOT NULL,
  `adresse_ag` varchar(25) NOT NULL,
  `tel_ag` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id_ag`, `numero_ag`, `adresse_ag`, `tel_ag`) VALUES
(1, 'AG001', 'Fass', '33 800 10 10'),
(2, 'AG002', 'Point E', '33 800 10 11'),
(3, 'AG003', 'Fass Louveau', '33 800 10 12'),
(4, 'AG004', 'Ouest Foire ', '331001014');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `nom_client` varchar(25) NOT NULL,
  `prenom_client` varchar(25) NOT NULL,
  `tel_client` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `nom_client`, `prenom_client`, `tel_client`) VALUES
(1, 'BWW', 'BBW', '771001010'),
(3, 'Wane', 'Hawa Birane ', '771001011'),
(4, 'Wane', 'Coudy Ly', '771001012');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `id_cpte` int(11) NOT NULL,
  `numero_cpte` varchar(25) NOT NULL,
  `solde_cpte` double NOT NULL,
  `frais_cpte` double DEFAULT NULL,
  `type_cpte` tinyint(4) NOT NULL,
  `taux_cpte` int(11) DEFAULT NULL,
  `agence_id` int(11) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id_cpte`, `numero_cpte`, `solde_cpte`, `frais_cpte`, `type_cpte`, `taux_cpte`, `agence_id`, `client_id`) VALUES
(5, 'CPT001', 100000, 200, 0, NULL, 1, 1),
(6, 'CPT002', 1000000, NULL, 1, 0, 1, 1),
(7, 'CPT003', 500000, 500, 0, NULL, 2, 3),
(8, 'CPT004', 400000, NULL, 1, 0, 1, 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `agence`
--
ALTER TABLE `agence`
  ADD PRIMARY KEY (`id_ag`),
  ADD UNIQUE KEY `numero_ag` (`numero_ag`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id_client`),
  ADD UNIQUE KEY `tel_client` (`tel_client`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`id_cpte`),
  ADD UNIQUE KEY `numero_cpte` (`numero_cpte`),
  ADD KEY `agence_id` (`agence_id`),
  ADD KEY `client_id` (`client_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `agence`
--
ALTER TABLE `agence`
  MODIFY `id_ag` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id_client` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `id_cpte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`agence_id`) REFERENCES `agence` (`id_ag`),
  ADD CONSTRAINT `compte_ibfk_2` FOREIGN KEY (`client_id`) REFERENCES `client` (`id_client`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
