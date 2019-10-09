-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Ott 09, 2019 alle 14:55
-- Versione del server: 10.1.41-MariaDB-0+deb9u1
-- Versione PHP: 7.2.22-1+0~20190902.26+debian9~1.gbpd64eb7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `busy`
--

CREATE TABLE `busy` (
  `id` int(11) NOT NULL,
  `staff_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `busy`
--

INSERT INTO `busy` (`id`, `staff_id`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `Events`
--

CREATE TABLE `Events` (
  `id` int(11) NOT NULL,
  `menu` int(11) DEFAULT NULL,
  `chef_id` int(11) DEFAULT NULL,
  `event_date` varchar(255) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `summary_sheet_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Events`
--

INSERT INTO `Events` (`id`, `menu`, `chef_id`, `event_date`, `event_name`, `summary_sheet_id`) VALUES
(1, 1, 2, '01/10/2019', 'Primo evento', NULL),
(2, 3, 5, '01/10/2019', 'Secondo evento', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `MenuItems`
--

CREATE TABLE `MenuItems` (
  `id` int(11) NOT NULL,
  `menu` int(11) DEFAULT NULL,
  `section` int(11) DEFAULT '0',
  `description` tinytext,
  `recipe` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `MenuItems`
--

INSERT INTO `MenuItems` (`id`, `menu`, `section`, `description`, `recipe`, `position`) VALUES
(1, 3, 1, 'Voce 1', 1, 0),
(2, 3, 1, 'Voce 2', 1, 1),
(3, 3, 2, 'Voce 3', 1, 0),
(4, 3, 2, 'Voce 4', 1, 2),
(5, 3, 0, 'Voce 0', 1, 0),
(6, 3, 1, 'Voce 1.5', 1, 2),
(7, 3, 2, 'Voce 3.5', 1, 1),
(8, 3, 0, 'Voce 0.2', 1, 1),
(9, 3, 0, 'Voce 0.8', 1, 2),
(10, 3, 3, 'Voce 5', 1, 0),
(11, 3, 3, 'Voce 6', 1, 1),
(12, 3, 3, 'Voce 7', 1, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `Menus`
--

CREATE TABLE `Menus` (
  `id` int(11) NOT NULL,
  `title` tinytext NOT NULL,
  `menuowner` int(11) NOT NULL,
  `published` tinyint(1) DEFAULT NULL,
  `fingerFood` tinyint(1) DEFAULT NULL,
  `cookRequired` tinyint(1) DEFAULT NULL,
  `hotDishes` tinyint(1) DEFAULT NULL,
  `kitchenRequired` tinyint(1) DEFAULT NULL,
  `buffet` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Menus`
--

INSERT INTO `Menus` (`id`, `title`, `menuowner`, `published`, `fingerFood`, `cookRequired`, `hotDishes`, `kitchenRequired`, `buffet`) VALUES
(1, 'prova in uso', 3, 1, 1, 0, 0, 0, 1),
(2, 'prova non in uso', 2, 0, 0, 1, 1, 1, 0),
(3, 'prova struttura', 3, 0, 0, 0, 0, 0, 1),
(4, 'Ciao', 3, 0, 0, 0, 0, 0, 0),
(7, 'prova struttura', 3, 0, 0, 0, 0, 0, 1),
(8, 'prova in uso', 3, 0, 1, 0, 0, 0, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `Recipes`
--

CREATE TABLE `Recipes` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Recipes`
--

INSERT INTO `Recipes` (`id`, `name`, `type`) VALUES
(1, 'Salsa Tonnata', 'p'),
(2, 'Vitello Tonnato', 'r'),
(3, 'Vitello Tonnato all\'Antica', 'r'),
(4, 'Brodo di Manzo Ristretto', 'p'),
(5, 'Risotto alla Milanese', 'r'),
(6, 'Pesto Ligure', 'p'),
(7, 'Trofie avvantaggiate al pesto', 'r'),
(8, 'Orata al forno con olive', 'r'),
(9, 'Insalata russa', 'r'),
(10, 'Bagnet vert', 'p'),
(11, 'Acciughe al verde', 'r'),
(12, 'Agnolotti del plin', 'p'),
(13, 'Agnolotti al sugo d\'arrosto', 'r'),
(14, 'Agnolotti burro e salvia', 'r'),
(15, 'Brasato al barolo', 'r'),
(16, 'Panna cotta', 'r'),
(17, 'Tarte tatin', 'r');

-- --------------------------------------------------------

--
-- Struttura della tabella `rel_shift_busy`
--

CREATE TABLE `rel_shift_busy` (
  `id` int(11) NOT NULL,
  `shift_id` int(11) DEFAULT NULL,
  `busy_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `rel_shift_busy`
--

INSERT INTO `rel_shift_busy` (`id`, `shift_id`, `busy_id`) VALUES
(1, 1, 1),
(2, 3, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `rel_shift_staff`
--

CREATE TABLE `rel_shift_staff` (
  `id` int(11) NOT NULL,
  `shift_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `rel_shift_staff`
--

INSERT INTO `rel_shift_staff` (`id`, `shift_id`, `staff_id`) VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 3),
(4, 3, 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `rel_st_shift`
--

CREATE TABLE `rel_st_shift` (
  `id` int(11) NOT NULL,
  `st_id` int(11) DEFAULT NULL,
  `shift_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `rel_st_shift`
--

INSERT INTO `rel_st_shift` (`id`, `st_id`, `shift_id`) VALUES
(5, 11, 1),
(6, 11, 2),
(7, 12, 1),
(8, 13, 2),
(9, 14, 1),
(10, 15, 2),
(11, 16, 1),
(12, 16, 2),
(13, 17, 1),
(14, 18, 1),
(15, 19, 2),
(16, 20, 1),
(17, 20, 2),
(18, 21, 1),
(19, 22, 2),
(20, 27, 1),
(21, 28, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `rel_st_staff`
--

CREATE TABLE `rel_st_staff` (
  `id` int(11) NOT NULL,
  `st_id` int(11) DEFAULT NULL,
  `staff_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `rel_st_staff`
--

INSERT INTO `rel_st_staff` (`id`, `st_id`, `staff_id`) VALUES
(10, 10, 2),
(11, 10, 3),
(12, 11, 2),
(13, 11, 3),
(14, 12, 2),
(15, 13, 3),
(16, 14, 2),
(17, 15, 3),
(18, 16, 2),
(19, 16, 3),
(20, 17, 2),
(21, 18, 2),
(22, 19, 3),
(23, 20, 2),
(24, 20, 3),
(25, 21, 2),
(26, 22, 3),
(27, 27, 2),
(28, 28, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `rel_st_task`
--

CREATE TABLE `rel_st_task` (
  `id` int(11) NOT NULL,
  `task_id` int(11) DEFAULT NULL,
  `st_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `rel_st_task`
--

INSERT INTO `rel_st_task` (`id`, `task_id`, `st_id`) VALUES
(11, 7, 11),
(12, 8, 12),
(13, 9, 13),
(14, 10, 14),
(15, 11, 15),
(16, 12, 16),
(17, 13, 17),
(18, 14, 18),
(19, 15, 19),
(20, 16, 20),
(21, 17, 21),
(22, 18, 22),
(23, 19, 23),
(24, 20, 24),
(25, 21, 25),
(26, 22, 26),
(27, 23, 27),
(28, 24, 28),
(29, 25, 29);

-- --------------------------------------------------------

--
-- Struttura della tabella `Roles`
--

CREATE TABLE `Roles` (
  `id` varchar(1) NOT NULL,
  `role` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Roles`
--

INSERT INTO `Roles` (`id`, `role`) VALUES
('c', 'Cuoco'),
('h', 'Chef'),
('o', 'Organizzatore'),
('s', 'Servizio');

-- --------------------------------------------------------

--
-- Struttura della tabella `Sections`
--

CREATE TABLE `Sections` (
  `menu` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `name` tinytext,
  `position` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Sections`
--

INSERT INTO `Sections` (`menu`, `id`, `name`, `position`) VALUES
(3, 1, 'Primi', NULL),
(3, 2, 'Secondi', NULL),
(3, 3, 'Dessert', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `shifts`
--

CREATE TABLE `shifts` (
  `id` int(11) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `start` varchar(255) DEFAULT NULL,
  `end` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `shifts`
--

INSERT INTO `shifts` (`id`, `date`, `start`, `end`, `number`) VALUES
(1, '01/10/2019', '10:52', '11:52', 1),
(2, '01/10/2019', '12:52', '13:52', 2),
(3, '01/10/2019', '14:52', '15:52', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `shift_task`
--

CREATE TABLE `shift_task` (
  `id` int(11) NOT NULL,
  `summary_sheet_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `shift_task`
--

INSERT INTO `shift_task` (`id`, `summary_sheet_id`) VALUES
(11, 8),
(12, 8),
(13, 8),
(14, 9),
(15, 9),
(16, 9),
(17, 9),
(18, 10),
(19, 10),
(20, 10),
(21, 11),
(22, 11),
(23, 11),
(24, 11),
(25, 11),
(26, 11),
(27, 12),
(28, 12),
(29, 12);

-- --------------------------------------------------------

--
-- Struttura della tabella `staff`
--

CREATE TABLE `staff` (
  `id` int(11) NOT NULL,
  `cook` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `staff`
--

INSERT INTO `staff` (`id`, `cook`, `user_id`) VALUES
(1, 1, 6),
(2, 1, 7),
(3, 1, 8),
(4, 1, 9);

-- --------------------------------------------------------

--
-- Struttura della tabella `summary_sheets`
--

CREATE TABLE `summary_sheets` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `chef_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `summary_sheets`
--

INSERT INTO `summary_sheets` (`id`, `title`, `note`, `chef_id`) VALUES
(12, '', NULL, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `tasks`
--

CREATE TABLE `tasks` (
  `id` int(11) NOT NULL,
  `item_id` int(11) DEFAULT NULL,
  `estimated_time` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `tasks`
--

INSERT INTO `tasks` (`id`, `item_id`, `estimated_time`, `quantity`, `status`, `title`) VALUES
(1, 0, 0, 0, 0, NULL),
(2, 0, 0, 0, 0, NULL),
(3, 0, 0, 0, 0, NULL),
(4, 0, 0, 0, 0, NULL),
(5, 0, 0, 0, 0, NULL),
(6, 0, 0, 0, 0, NULL),
(7, 1, 20, 4, 0, NULL),
(8, 2, 10, 3, 0, NULL),
(9, 3, 30, 6, 0, NULL),
(10, 0, 12, 2, 0, NULL),
(11, 0, 13, 1, 0, NULL),
(12, 0, 30, 5, 0, NULL),
(13, 0, 10, 1, 0, NULL),
(14, 3, 20, 2, 0, NULL),
(15, 3, 29, 5, 0, NULL),
(16, 3, 10, 1, 0, NULL),
(17, 3, 12, 20, 0, NULL),
(18, 3, 60, 2, 0, NULL),
(19, 3, 0, 0, 0, NULL),
(20, 3, 0, 0, 0, NULL),
(21, 3, 0, 0, 0, NULL),
(22, 3, 0, 0, 0, NULL),
(23, 1, 0, 0, 0, NULL),
(24, 3, 0, 0, 0, NULL),
(25, 5, 0, 0, 0, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `UserRoles`
--

CREATE TABLE `UserRoles` (
  `user` int(11) NOT NULL,
  `role` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `UserRoles`
--

INSERT INTO `UserRoles` (`user`, `role`) VALUES
(1, 'o'),
(2, 'h'),
(2, 'c'),
(3, 'h'),
(4, 'o'),
(4, 'h'),
(5, 'c');

-- --------------------------------------------------------

--
-- Struttura della tabella `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `Users`
--

INSERT INTO `Users` (`id`, `name`) VALUES
(1, 'Marco'),
(2, 'Tony'),
(3, 'Viola'),
(4, 'Anna'),
(5, 'Giovanni'),
(6, 'Anna'),
(7, 'Roberto'),
(8, 'Federico'),
(9, 'Giuseppe');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `busy`
--
ALTER TABLE `busy`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Events`
--
ALTER TABLE `Events`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `MenuItems`
--
ALTER TABLE `MenuItems`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Menus`
--
ALTER TABLE `Menus`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Recipes`
--
ALTER TABLE `Recipes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indici per le tabelle `rel_shift_busy`
--
ALTER TABLE `rel_shift_busy`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `rel_shift_staff`
--
ALTER TABLE `rel_shift_staff`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `rel_st_shift`
--
ALTER TABLE `rel_st_shift`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `rel_st_staff`
--
ALTER TABLE `rel_st_staff`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `rel_st_task`
--
ALTER TABLE `rel_st_task`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Roles`
--
ALTER TABLE `Roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indici per le tabelle `Sections`
--
ALTER TABLE `Sections`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Sections_Menu_id_fk` (`menu`);

--
-- Indici per le tabelle `shifts`
--
ALTER TABLE `shifts`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `shift_task`
--
ALTER TABLE `shift_task`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `summary_sheets`
--
ALTER TABLE `summary_sheets`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `busy`
--
ALTER TABLE `busy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT per la tabella `Events`
--
ALTER TABLE `Events`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT per la tabella `MenuItems`
--
ALTER TABLE `MenuItems`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT per la tabella `Menus`
--
ALTER TABLE `Menus`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT per la tabella `Recipes`
--
ALTER TABLE `Recipes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT per la tabella `rel_shift_busy`
--
ALTER TABLE `rel_shift_busy`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT per la tabella `rel_shift_staff`
--
ALTER TABLE `rel_shift_staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT per la tabella `rel_st_shift`
--
ALTER TABLE `rel_st_shift`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT per la tabella `rel_st_staff`
--
ALTER TABLE `rel_st_staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT per la tabella `rel_st_task`
--
ALTER TABLE `rel_st_task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT per la tabella `Sections`
--
ALTER TABLE `Sections`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT per la tabella `shifts`
--
ALTER TABLE `shifts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT per la tabella `shift_task`
--
ALTER TABLE `shift_task`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT per la tabella `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT per la tabella `summary_sheets`
--
ALTER TABLE `summary_sheets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT per la tabella `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- AUTO_INCREMENT per la tabella `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
