-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-02-2022 a las 16:07:01
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `productos`
--
CREATE DATABASE IF NOT EXISTS `productos` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `productos`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `precios`
--

CREATE TABLE `precios` (
  `id` int(3) NOT NULL,
  `item` varchar(100) NOT NULL,
  `precio` int(5) NOT NULL,
  `precioOferta` int(5) NOT NULL,
  `saldo` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `precios`
--

INSERT INTO `precios` (`id`, `item`, `precio`, `precioOferta`, `saldo`) VALUES
(1, 'Silla', 100, 40, 'Si'),
(2, 'Mesa', 600, 550, 'No'),
(3, 'Armario', 450, 420, 'Si'),
(4, 'prueba', 999, 998, 'Si'),
(5, 'Silla', 100, 40, 'Si'),
(6, 'Mesa', 600, 550, 'No'),
(7, 'Armario', 450, 420, 'Si'),
(8, 'Silla', 100, 40, 'Si'),
(9, 'Mesa', 600, 550, 'No'),
(10, 'Armario', 450, 420, 'Si'),
(11, 'Silla', 100, 40, 'Si'),
(12, 'patata', 2, 1, 'No');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `precios`
--
ALTER TABLE `precios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `precios`
--
ALTER TABLE `precios`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
