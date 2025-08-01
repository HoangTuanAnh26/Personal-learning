-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th7 21, 2025 lúc 05:32 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `webpg2_2025`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `diary`
--

CREATE TABLE `diary` (
  `id` int(11) NOT NULL,
  `year` int(4) NOT NULL,
  `month` int(2) NOT NULL,
  `day` int(2) NOT NULL,
  `shour` int(2) NOT NULL,
  `smin` int(2) NOT NULL,
  `ehour` int(2) NOT NULL,
  `emin` int(2) NOT NULL,
  `field_id` int(11) NOT NULL,
  `work_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `diary`
--

INSERT INTO `diary` (`id`, `year`, `month`, `day`, `shour`, `smin`, `ehour`, `emin`, `field_id`, `work_id`, `user_id`) VALUES
(3, 2025, 6, 18, 10, 30, 22, 30, 2, 2, 4),
(4, 2025, 6, 18, 10, 36, 22, 37, 1, 2, 5),
(5, 2025, 6, 18, 11, 15, 23, 16, 2, 1, 5),
(6, 2025, 5, 31, 0, 30, 13, 30, 2, 2, 5),
(7, 2025, 6, 18, 3, 30, 15, 30, 2, 7, 5),
(9, 2025, 6, 18, 11, 31, 14, 31, 3, 3, 4),
(10, 2025, 6, 18, 2, 31, 14, 31, 3, 5, 4),
(11, 0, 0, 0, 0, 0, 0, 0, 3, 8, 6),
(12, 2025, 6, 19, 11, 54, 23, 54, 9, 8, 6),
(13, 2025, 6, 19, 9, 46, 12, 46, 3, 3, 6),
(14, 2025, 6, 19, 11, 4, 13, 4, 2, 2, 6),
(15, 2025, 6, 19, 3, 31, 17, 31, 9, 8, 6),
(16, 2025, 5, 21, 2, 30, 14, 30, 8, 7, 8),
(19, 2025, 7, 3, 2, 0, 13, 0, 8, 6, 4),
(21, 2025, 7, 6, 1, 4, 15, 4, 1, 1, 4),
(22, 2025, 7, 11, 7, 54, 19, 54, 8, 6, 6),
(24, 2025, 7, 16, 4, 56, 6, 56, 8, 7, 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `field`
--

CREATE TABLE `field` (
  `id` int(11) NOT NULL,
  `name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `field`
--

INSERT INTO `field` (`id`, `name`) VALUES
(1, '東京'),
(2, '大阪'),
(3, '京都'),
(4, '北海道'),
(5, '名古屋'),
(6, '福岡'),
(7, '奈良'),
(8, '神戸'),
(9, '広島'),
(10, ' 横浜');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `diary_id` int(11) NOT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `notes`
--

INSERT INTO `notes` (`id`, `diary_id`, `notes`) VALUES
(2, 11, ''),
(3, 12, 'hoc rot + dot tien'),
(4, 19, 'asdfghjkl');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `userID` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','user') NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `userID`, `password`, `role`) VALUES
(4, '01', '$2y$10$NZPYLQlTwEcMiDtbwdBvwuGWJwOcLmu.7ZBQz/t3hmvI0emPdN66G', 'user'),
(5, 'webpg2', '$2y$10$2M6zzZ.iZ74gU/0GZ1Bereet6ZVrQJLvSqukI8t/FrpMovQGFPWh2', 'user'),
(6, 'hoangnamtruong+hocrot+dottien', '$2y$10$t37o.Fyvp2ala.B3Oeo1A./cZiw5.O0CDCMCl4boFwr8hKe1COr/q', 'admin'),
(7, '02', '$2y$10$IT8bYeJyOOTapFjPbSPAIuMfMLqMhhsDi6tbgeO62ue0QcxvsTMgO', 'user'),
(8, '03', '$2y$10$cEjoocPIO7jqYGcejHyAwONSylRwlxe8FMhuwCQh3G/OZcRFIaNtS', 'user');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `work`
--

CREATE TABLE `work` (
  `id` int(11) NOT NULL,
  `shigoto` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `work`
--

INSERT INTO `work` (`id`, `shigoto`) VALUES
(1, 'ソフトウェアエンジニア'),
(2, '販売スタッフ'),
(3, '教師'),
(4, 'IT技術者'),
(5, '飲食店スタッフ'),
(6, '通訳者'),
(7, '工場作業員'),
(8, '配達ドライバー'),
(9, '事務員'),
(10, '看護師');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `work_logs`
--

CREATE TABLE `work_logs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `work_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `total_hours` decimal(5,2) NOT NULL,
  `field_id` int(11) DEFAULT NULL,
  `work_id` int(11) DEFAULT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `diary`
--
ALTER TABLE `diary`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `field_id` (`field_id`),
  ADD KEY `work_id` (`work_id`);

--
-- Chỉ mục cho bảng `field`
--
ALTER TABLE `field`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `diary_id` (`diary_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `userID` (`userID`);

--
-- Chỉ mục cho bảng `work`
--
ALTER TABLE `work`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `work_logs`
--
ALTER TABLE `work_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `field_id` (`field_id`),
  ADD KEY `work_id` (`work_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `diary`
--
ALTER TABLE `diary`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT cho bảng `field`
--
ALTER TABLE `field`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `work_logs`
--
ALTER TABLE `work_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `diary`
--
ALTER TABLE `diary`
  ADD CONSTRAINT `diary_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `diary_ibfk_2` FOREIGN KEY (`field_id`) REFERENCES `field` (`id`),
  ADD CONSTRAINT `diary_ibfk_3` FOREIGN KEY (`work_id`) REFERENCES `work` (`id`);

--
-- Các ràng buộc cho bảng `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`diary_id`) REFERENCES `diary` (`id`) ON DELETE CASCADE;

--
-- Các ràng buộc cho bảng `work_logs`
--
ALTER TABLE `work_logs`
  ADD CONSTRAINT `work_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `work_logs_ibfk_2` FOREIGN KEY (`field_id`) REFERENCES `field` (`id`) ON DELETE SET NULL,
  ADD CONSTRAINT `work_logs_ibfk_3` FOREIGN KEY (`work_id`) REFERENCES `work` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
