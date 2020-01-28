-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 27, 2020 at 09:49 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `partes_trabajo`
--

-- --------------------------------------------------------

--
-- Table structure for table `maquina`
--

CREATE TABLE `maquina` (
  `id` int(5) NOT NULL,
  `nombre` varchar(10) NOT NULL,
  `matricula` varchar(20) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `maquina`
--

INSERT INTO `maquina` (`id`, `nombre`, `matricula`, `activo`) VALUES
(1, 'TEST-01', '4534523QF', 1),
(2, 'TEST-02', 'SDFSDFSDFS323', 1),
(3, 'TEST-03', '324234AS', 1),
(4, 'MAQDES-01', 'DSFSDF342', 0),
(5, 'PLUTONIO', 'DFSDFS2', 1),
(6, 'MAQDES-02', 'ARARARARARA', 0);

-- --------------------------------------------------------

--
-- Table structure for table `parte`
--

CREATE TABLE `parte` (
  `id` int(5) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp(),
  `minutos` int(3) NOT NULL,
  `id_trabajador` int(5) NOT NULL,
  `id_tarea` int(5) NOT NULL,
  `id_maquina` int(5) DEFAULT NULL,
  `id_pedido` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parte`
--

INSERT INTO `parte` (`id`, `fecha`, `minutos`, `id_trabajador`, `id_tarea`, `id_maquina`, `id_pedido`) VALUES
(32, '2020-01-27 19:48:35', 60, 5, 7, 5, NULL),
(65, '2020-01-27 19:53:20', 20, 2, 2, 2, 1),
(66, '2020-01-27 19:53:20', 12, 4, 5, NULL, NULL),
(67, '2020-01-27 19:53:20', 455, 5, 2, 1, 1),
(68, '2020-01-27 19:53:20', 65, 2, 2, 2, NULL),
(69, '2020-01-27 19:53:20', 73, 1, 1, 2, 2),
(70, '2020-01-27 19:53:20', 23, 1, 4, 2, 2),
(71, '2020-01-27 19:53:20', 0, 2, 5, NULL, NULL),
(72, '2020-01-27 19:53:20', 34, 3, 3, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pedido`
--

CREATE TABLE `pedido` (
  `id` int(5) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pedido`
--

INSERT INTO `pedido` (`id`, `nombre`, `activo`) VALUES
(1, 'PEDIDO-01', 1),
(2, 'PEDIDO-02', 1),
(3, 'PEDIDO-CANCEL', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tarea`
--

CREATE TABLE `tarea` (
  `id` int(5) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `descripcion` varchar(200) DEFAULT NULL,
  `tipo` enum('PROD','MANT') NOT NULL DEFAULT 'PROD',
  `multi_maquina` tinyint(1) NOT NULL DEFAULT 0,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tarea`
--

INSERT INTO `tarea` (`id`, `nombre`, `descripcion`, `tipo`, `multi_maquina`, `activo`) VALUES
(1, 'TAREA-01', 'cojo un muelle y lo tiro por el retrete y ya son 840 muelles los que el retrete se ha tragado ', 'PROD', 0, 1),
(2, 'TAREA-02', 'Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T.', 'PROD', 0, 1),
(3, 'TAREA-MULTI', 'MULTI LOREM IPSUM', 'PROD', 1, 1),
(4, 'TAREA-INAC', 'ESTO YA NO SE HACE ABUELO', 'PROD', 0, 0),
(5, 'TAREA-UNICA', 'Una sola maquina', 'PROD', 0, 1),
(6, 'MANT-01', 'Prueba de mantenimiento', 'MANT', 0, 1),
(7, 'MANT-MAQ', 'Vamos a limpiar la maquina', 'MANT', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tarea_maquina`
--

CREATE TABLE `tarea_maquina` (
  `id` int(6) NOT NULL,
  `id_tarea` int(6) NOT NULL,
  `id_maquina` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tarea_maquina`
--

INSERT INTO `tarea_maquina` (`id`, `id_tarea`, `id_maquina`) VALUES
(1, 1, 2),
(2, 1, 3),
(9, 2, 1),
(8, 2, 3),
(4, 3, 1),
(5, 3, 2),
(3, 3, 4),
(6, 3, 5),
(7, 4, 6),
(10, 5, 1),
(11, 7, 1),
(12, 7, 2),
(13, 7, 3);

-- --------------------------------------------------------

--
-- Table structure for table `trabajador`
--

CREATE TABLE `trabajador` (
  `id` int(5) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `puesto` varchar(20) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trabajador`
--

INSERT INTO `trabajador` (`id`, `dni`, `nombre`, `apellidos`, `puesto`, `activo`) VALUES
(1, '234234234', 'Oso', 'Baloo', 'Rascador', 1),
(2, '3242343a', 'Big', 'Bad Wolf', 'Lobo', 0),
(3, '324234234', 'Captain', 'Hook', 'Capitan', 1),
(4, '242342323', 'Headless', 'Horseman', 'Jinete', 1),
(5, '2416823Y', 'Jack', 'Skellington', 'Chorprechas', 1),
(6, '902588334', 'Winnie', 'the Pooh', 'Miel', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `maquina`
--
ALTER TABLE `maquina`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indexes for table `parte`
--
ALTER TABLE `parte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_trabajador` (`id_trabajador`),
  ADD KEY `id` (`id`),
  ADD KEY `id_maquina` (`id_maquina`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_tarea` (`id_tarea`);

--
-- Indexes for table `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tarea`
--
ALTER TABLE `tarea`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indexes for table `tarea_maquina`
--
ALTER TABLE `tarea_maquina`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_maquina` (`id_maquina`),
  ADD KEY `id_tarea` (`id_tarea`,`id_maquina`);

--
-- Indexes for table `trabajador`
--
ALTER TABLE `trabajador`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `maquina`
--
ALTER TABLE `maquina`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `parte`
--
ALTER TABLE `parte`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=73;

--
-- AUTO_INCREMENT for table `pedido`
--
ALTER TABLE `pedido`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tarea`
--
ALTER TABLE `tarea`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tarea_maquina`
--
ALTER TABLE `tarea_maquina`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `parte`
--
ALTER TABLE `parte`
  ADD CONSTRAINT `parte_ibfk_1` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `parte_ibfk_2` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `parte_ibfk_3` FOREIGN KEY (`id_maquina`) REFERENCES `maquina` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `parte_ibfk_4` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tarea_maquina`
--
ALTER TABLE `tarea_maquina`
  ADD CONSTRAINT `tarea_maquina_ibfk_1` FOREIGN KEY (`id_maquina`) REFERENCES `maquina` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tarea_maquina_ibfk_2` FOREIGN KEY (`id_tarea`) REFERENCES `tarea` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
