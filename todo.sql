-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 04, 2024 at 10:37 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `todo`
--

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `todos`
--

CREATE TABLE `todos` (
  `id` bigint(20) NOT NULL,
  `completed` bit(1) NOT NULL,
  `description` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `todos`
--

INSERT INTO `todos` (`id`, `completed`, `description`, `title`) VALUES
(1, b'0', 'Build To Do app for intern Java Web', 'Learning Spring boot'),
(2, b'0', 'Build To Do app for intern Java Web', 'Learn react'),
(3, b'0', 'Learn java to do anything', 'Intern Java'),
(4, b'0', 'Learn java to do anything 1', 'Intern Java'),
(5, b'0', 'Learn java to do anything 2', 'Intern Java'),
(6, b'0', 'Learn java to do anything 3', 'Intern Java'),
(7, b'0', 'Learn java to do anything 4', 'Intern Java'),
(8, b'0', 'Learn java to do anything 5', 'Intern Java'),
(9, b'0', 'Learn java to do anything 6', 'Intern Java'),
(10, b'0', 'Learn java to do anything 7', 'Intern Java'),
(11, b'0', 'Learn java to do anything 8', 'Intern Java'),
(12, b'0', 'Learn java to do anything 9', 'Intern Java'),
(13, b'0', 'Learn java to do anything 10', 'Intern Java'),
(14, b'0', 'Learn java to do anything 11', 'Intern Java'),
(15, b'0', 'Learn java to do anything 12', 'Intern Java'),
(16, b'0', 'Learn java to do anything 13', 'Intern Java'),
(17, b'0', 'Learn java to do anything 14', 'Intern Java'),
(18, b'0', 'Learn java to do anything 15', 'Intern Java'),
(19, b'0', 'Learn java to do anything 16', 'Intern Java'),
(20, b'0', 'Learn java to do anything 17', 'Intern Java'),
(21, b'0', 'Learn java to do anything 18', 'Intern Java'),
(22, b'0', 'Learn java to do anything 18', 'Intern Java'),
(23, b'0', 'Learn java to do anything 20', 'Intern Java'),
(24, b'0', 'Learn java to do anything 21', 'Intern Java'),
(25, b'0', 'Learn java to do anything 22', 'Intern Java'),
(26, b'0', 'Learn java to do anything 23', 'Intern Java'),
(27, b'0', 'Learn java to do anything 24', 'Intern Java'),
(28, b'0', 'Learn java to do anything 25', 'Intern Java'),
(29, b'0', 'Learn java to do anything 26', 'Intern Java'),
(30, b'0', 'Learn java to do anything 27', 'Intern Java');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `name`, `password`, `username`) VALUES
(5, 'bdh6@gmail.com', 'Bui Dac Hien', '$2a$10$CGtN2WJovMqHU6PAZeR2HOCoUOscdAqC52gB0OQO7jNC6X/nqR18a', 'bdh6'),
(6, 'admin@gmail.com', 'BDH_ADMIN', '$2a$10$CGtN2WJovMqHU6PAZeR2HOCoUOscdAqC52gB0OQO7jNC6X/nqR18a', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(5, 2),
(6, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `todos`
--
ALTER TABLE `todos`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `todos`
--
ALTER TABLE `todos`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
