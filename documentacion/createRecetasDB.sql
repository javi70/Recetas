-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.25 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para recetas
DROP DATABASE IF EXISTS `recetas`;
CREATE DATABASE IF NOT EXISTS `recetas` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */;
USE `recetas`;


-- Volcando estructura para tabla recetas.categorias
DROP TABLE IF EXISTS `categorias`;
CREATE TABLE IF NOT EXISTS `categorias` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla recetas.categorias: ~6 rows (aproximadamente)
DELETE FROM `categorias`;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` (`id`, `nombre`) VALUES
	(1, 'Entrante'),
	(2, 'Primero'),
	(3, 'Segundo'),
	(4, 'Postre'),
	(5, 'Bebida'),
	(6, 'Digestivo');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;


-- Volcando estructura para tabla recetas.ingredientes
DROP TABLE IF EXISTS `ingredientes`;
CREATE TABLE IF NOT EXISTS `ingredientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='Tabla de ingredientes';

-- Volcando datos para la tabla recetas.ingredientes: ~208 rows (aproximadamente)
DELETE FROM `ingredientes`;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` (`id`, `nombre`) VALUES
	(1, 'Aguacates'),
	(2, 'Ajo picado'),
	(3, 'Ajos'),
	(4, 'Albahaca'),
	(5, 'Alcachofas'),
	(6, 'Alitas pollo'),
	(7, 'Almejas chilenas'),
	(8, 'Alubiada '),
	(9, 'Alubias blancas'),
	(10, 'Alubias rojas'),
	(11, 'Anacardos'),
	(12, 'Anchoas'),
	(13, 'Anillos de calamar'),
	(14, 'Arroz'),
	(15, 'Arroz bomba'),
	(16, 'Atún'),
	(17, 'Avecrem carne'),
	(18, 'Avecrem pescado'),
	(19, 'Avecrem pollo'),
	(20, 'Avecrem verdura'),
	(21, 'Azúcar'),
	(22, 'Azúcar avainillado'),
	(23, 'Azucar glass'),
	(24, 'Bacon lonchas'),
	(25, 'Bacon taquitos'),
	(26, 'Bicarbonato'),
	(27, 'Bizcocho para la mou'),
	(28, 'Café'),
	(29, 'Calabacin'),
	(30, 'Calabaza'),
	(31, 'Calamares'),
	(32, 'Canal curado de ovej'),
	(33, 'Canela molida'),
	(34, 'Canela rama'),
	(35, 'Canelones'),
	(36, 'Caramelo líquido'),
	(37, 'Carne picada'),
	(38, 'Cebolla'),
	(39, 'Cerveza'),
	(40, 'Chalotas'),
	(41, 'Champiñones'),
	(42, 'Champis lata'),
	(43, 'Chorizo pamplona'),
	(44, 'Chorizo sarta'),
	(45, 'Chorizos cocinar'),
	(46, 'Chuletillas cordero'),
	(47, 'Chuleton de buey'),
	(48, 'Cilantro'),
	(49, 'Coca cola'),
	(50, 'Coliflor'),
	(51, 'Colorante paella'),
	(52, 'Concentrado limón'),
	(53, 'Conejo'),
	(54, 'Costillas'),
	(55, 'Cuajo'),
	(56, 'Curry'),
	(57, 'Empanadillas'),
	(58, 'Eneldo'),
	(59, 'Ensaladilla'),
	(60, 'Escencia de vainilla'),
	(61, 'Espaguetis'),
	(62, 'Espárragos'),
	(63, 'Espárragos verdes'),
	(64, 'Espinacas'),
	(65, 'Estofado de vaca'),
	(66, 'Fideos'),
	(67, 'Filetes de carne de '),
	(68, 'Filetes de pechuga d'),
	(69, 'Flan'),
	(70, 'Foie Grass'),
	(71, 'Frutos secos variado'),
	(72, 'Fuet'),
	(73, 'Galletas normales'),
	(74, 'Gambas'),
	(75, 'Garbanzos'),
	(76, 'Gelatina de limón'),
	(77, 'Gelatinas'),
	(78, 'Granada'),
	(79, 'Guindilla picada'),
	(80, 'Guisantes'),
	(81, 'Guisantes lata'),
	(82, 'Habas'),
	(83, 'Harina'),
	(84, 'Harina de fuerza'),
	(85, 'Harina de maíz'),
	(86, 'Hígado'),
	(87, 'Huevos'),
	(88, 'Jamon serrano'),
	(89, 'Jamón taquitos'),
	(90, 'Jamoncitos'),
	(91, 'Judías'),
	(92, 'Ketchup'),
	(93, 'Langostinos cocidos'),
	(94, 'Lasaña'),
	(95, 'Laurel'),
	(96, 'Leche'),
	(97, 'Lechuga'),
	(98, 'Lentejas'),
	(99, 'Levadura fresca'),
	(100, 'Levadura harina'),
	(101, 'Levadura pan'),
	(102, 'Limones'),
	(103, 'Lomo de cerdo'),
	(104, 'Lomo embuchado'),
	(105, 'Macarrones'),
	(106, 'Maiz '),
	(107, 'Maiz en lata'),
	(108, 'Mantequilla'),
	(109, 'Margarina'),
	(110, 'Masa brisa'),
	(111, 'Masa de hojaldre'),
	(112, 'Masa empanadas'),
	(113, 'Mayonesa'),
	(114, 'Mazorcas de maíz'),
	(115, 'Menestra'),
	(116, 'Merluza'),
	(117, 'Morcilla'),
	(118, 'Moscatel'),
	(119, 'Mousse de quesos'),
	(120, 'Muslo y contramuslo '),
	(121, 'Naranjas'),
	(122, 'Nata líquida'),
	(123, 'Nata president'),
	(124, 'Natillas'),
	(125, 'Nueces'),
	(126, 'Nuez moscada'),
	(127, 'Nuggets'),
	(128, 'Noras pim rojos'),
	(129, 'Obleas empanadillas'),
	(130, 'Orégano'),
	(131, 'Palitos de pescado'),
	(132, 'Pan bimbo'),
	(133, 'Pan de hamburguesas'),
	(134, 'Pan rallado'),
	(135, 'Pan tostado'),
	(136, 'Panga para pastel de'),
	(137, 'Paninis'),
	(138, 'Pasas'),
	(139, 'Pasitas'),
	(140, 'Pasta de ensalada'),
	(141, 'Pasta de letras'),
	(142, 'Pastel fresco'),
	(143, 'Pastel oreo'),
	(144, 'Patatas'),
	(145, 'Paté'),
	(146, 'Pavo'),
	(147, 'Pechuga pavo'),
	(148, 'Pechugas de pavo'),
	(149, 'Pepino'),
	(150, 'Perejil'),
	(151, 'Pescado'),
	(152, 'Pescado para horno'),
	(153, 'Pescado para vaporer'),
	(154, 'Piccolinos'),
	(155, 'Pimentón'),
	(156, 'Pimienta blanca'),
	(157, 'Pimienta negra'),
	(158, 'Pimientitos'),
	(159, 'Pimiento rojo'),
	(160, 'Pimientos rojos'),
	(161, 'Pimientos verdes'),
	(162, 'Pizza'),
	(163, 'Pollo para paella'),
	(164, 'Preparado para paell'),
	(165, 'Puerros'),
	(166, 'Puré patatas'),
	(167, 'Queso de burgos'),
	(168, 'Queso de untar'),
	(169, 'Queso mozarella'),
	(170, 'Queso parmesano'),
	(171, 'Queso polvo'),
	(172, 'Queso raciones'),
	(173, 'Queso sandwich'),
	(174, 'Remolacha'),
	(175, 'Romero'),
	(176, 'Ron'),
	(177, 'Roti'),
	(178, 'Sal'),
	(179, 'Salchichas'),
	(180, 'Salchichas blancas'),
	(181, 'Salvia'),
	(182, 'Sazonador carne'),
	(183, 'Sazonador ensalada'),
	(184, 'Sazonador pescado'),
	(185, 'Semáforo'),
	(186, 'Servilletas'),
	(187, 'Sésamo'),
	(188, 'Setas'),
	(189, 'Sidra'),
	(190, 'Soja'),
	(191, 'Sopa de marisco'),
	(192, 'Taco de Jamón'),
	(193, 'Tallarines'),
	(194, 'Tempura'),
	(195, 'Tomate frito'),
	(196, 'Tomate triturado'),
	(197, 'Tomates'),
	(198, 'Tomillo'),
	(199, 'Tortellinis'),
	(200, 'Txaka'),
	(201, 'Vinagre'),
	(202, 'Vino blanco'),
	(203, 'Vodka'),
	(204, 'Yogures  griegos'),
	(205, 'Zanahorias'),
	(206, 'Zumo piña'),
	(207, 'Aceite'),
	(209, 'borrar');
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;


-- Volcando estructura para tabla recetas.ingredientesreceta
DROP TABLE IF EXISTS `ingredientesreceta`;
CREATE TABLE IF NOT EXISTS `ingredientesreceta` (
  `id_receta` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  `cantidad` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id_receta`,`id_ingrediente`),
  KEY `fk_ingredientesreceta_ingredientes1_idx` (`id_ingrediente`),
  CONSTRAINT `fk_ingredientesreceta_ingredientes1` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingredientes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ingredientesreceta_recetas1` FOREIGN KEY (`id_receta`) REFERENCES `recetas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla recetas.ingredientesreceta: ~11 rows (aproximadamente)
DELETE FROM `ingredientesreceta`;
/*!40000 ALTER TABLE `ingredientesreceta` DISABLE KEYS */;
INSERT INTO `ingredientesreceta` (`id_receta`, `id_ingrediente`, `cantidad`) VALUES
	(29, 1, '2'),
	(29, 38, '1'),
	(29, 48, '1 cuch'),
	(29, 178, '1 cuch'),
	(29, 197, '1'),
	(30, 87, '1'),
	(30, 178, '1 pizca'),
	(30, 207, '1 poco'),
	(32, 2, '1'),
	(40, 3, '1 diente'),
	(40, 8, '2 kilos');
/*!40000 ALTER TABLE `ingredientesreceta` ENABLE KEYS */;


-- Volcando estructura para tabla recetas.recetas
DROP TABLE IF EXISTS `recetas`;
CREATE TABLE IF NOT EXISTS `recetas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_tipo_cocina` int(11) NOT NULL,
  `tiempo` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `preparacion` longtext COLLATE utf8_spanish_ci NOT NULL,
  `fotografia` varchar(250) COLLATE utf8_spanish_ci NOT NULL DEFAULT 'receta.jpg',
  PRIMARY KEY (`id`,`id_categoria`,`id_tipo_cocina`),
  KEY `fk_recetas_categorias1_idx` (`id_categoria`),
  KEY `fk_recetas_tiposcocina1_idx` (`id_tipo_cocina`),
  CONSTRAINT `fk_recetas_categorias1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recetas_tiposcocina1` FOREIGN KEY (`id_tipo_cocina`) REFERENCES `tiposcocina` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla recetas.recetas: ~16 rows (aproximadamente)
DELETE FROM `recetas`;
/*!40000 ALTER TABLE `recetas` DISABLE KEYS */;
INSERT INTO `recetas` (`id`, `nombre`, `id_categoria`, `id_tipo_cocina`, `tiempo`, `preparacion`, `fotografia`) VALUES
	(2, 'Paella', 2, 3, '60', '<p>Picar la verdura,...</p>', 'receta.jpg'),
	(4, 'Espaguetis', 2, 6, '25', 'cocer agua, ...', 'receta.jpg'),
	(5, 'Tortellinis', 2, 6, '15', 'asdasdasd', 'receta.jpg'),
	(8, 'Receta A', 1, 1, '120', 'a', '8_DSC03995.JPG'),
	(18, 'Receta B', 1, 1, '20', 'bas dasd ad ad ', 'DSC03975.JPG'),
	(19, 'Receta Z', 1, 1, '120', 'sadf asdfds fsdf sdaf', 'DSC03976.JPG'),
	(29, 'Guacamole', 1, 2, '5', 'picar todo y mezklarlo bien', '-1_'),
	(30, 'Huevo frito', 3, 1, '5', 'Calenmtar el aceite y freir el huevo salándolo después', '-1_'),
	(31, 'Cafesito', 1, 2, '1', 'asd asd asd', 'receta.jpg'),
	(32, 'Receta 32 con foto', 1, 1, '32', 'asdads ', '-1_DSC03999.JPG'),
	(33, 'asdasdasd ', 1, 1, '1', '', 'receta.jpg'),
	(37, '', 1, 1, '', '', 'receta.jpg'),
	(38, 'aaa', 1, 1, '0', '', 'receta.jpg'),
	(39, 'Nombre d e la receta', 1, 1, '0', '', 'receta.jpg'),
	(40, 'Receta con tiempo', 1, 1, '120', '', 'receta.jpg');
/*!40000 ALTER TABLE `recetas` ENABLE KEYS */;


-- Volcando estructura para tabla recetas.tiposcocina
DROP TABLE IF EXISTS `tiposcocina`;
CREATE TABLE IF NOT EXISTS `tiposcocina` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- Volcando datos para la tabla recetas.tiposcocina: ~6 rows (aproximadamente)
DELETE FROM `tiposcocina`;
/*!40000 ALTER TABLE `tiposcocina` DISABLE KEYS */;
INSERT INTO `tiposcocina` (`id`, `nombre`) VALUES
	(1, 'Vasca'),
	(2, 'Mexicana'),
	(3, 'Española'),
	(4, 'Asiatica'),
	(5, 'Euskaldun'),
	(6, 'Italiana');
/*!40000 ALTER TABLE `tiposcocina` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
