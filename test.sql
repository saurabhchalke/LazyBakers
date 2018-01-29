-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 29, 2018 at 05:03 AM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `addrees_id` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `house_no` int(11) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`addrees_id`, `city`, `house_no`, `pincode`, `state`, `street`, `user`, `user_id`) VALUES
(1, 'Bangalore', 410, '576104', 'Karanataka', 'Gear Road', 1, 1),
(2, 'Bangalore', 420, '576104', 'Karnataka', 'MG Road', 2, 2),
(3, 'Bangalore', 440, '576104', 'Karnataka', 'BBB Road', 3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `base`
--

CREATE TABLE `base` (
  `base_id` int(11) NOT NULL,
  `base_name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `base`
--

INSERT INTO `base` (`base_id`, `base_name`, `price`) VALUES
(1, 'Regular', 20),
(2, 'Fresh Pan', 30),
(3, 'Thin Crust', 30),
(4, 'Cheese Burst', 40),
(5, 'Double Cheese Burst', 40),
(6, 'LB Special', 40);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `bill_id` int(11) NOT NULL,
  `bill_amount` float DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `feedback` int(11) NOT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `offer_type` varchar(255) DEFAULT NULL,
  `stage` varchar(255) DEFAULT NULL,
  `tax` float NOT NULL,
  `timestamp_millis` bigint(20) DEFAULT NULL,
  `total_items` int(11) DEFAULT NULL,
  `address` int(11) DEFAULT NULL,
  `coupon` varchar(255) DEFAULT NULL,
  `user` bigint(20) DEFAULT NULL,
  `address_addrees_id` int(11) DEFAULT NULL,
  `coupon_coupon_code` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `bill_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`bill_id`, `bill_amount`, `comments`, `feedback`, `mobile_number`, `offer_type`, `stage`, `tax`, `timestamp_millis`, `total_items`, `address`, `coupon`, `user`, `address_addrees_id`, `coupon_coupon_code`, `user_id`, `bill_date`) VALUES
(1, 445.29, 'None', 5, NULL, 'None', 'Order received', 30.86, NULL, 10, NULL, NULL, NULL, NULL, 'test', NULL, '2018-01-28 12:02:20'),
(2, 445.29, 'None', 5, NULL, 'None', 'Order received', 30.86, NULL, 10, NULL, NULL, NULL, NULL, 'test', NULL, '2018-01-28 12:21:58'),
(3, 445.29, 'None', 5, NULL, 'None', 'Order received', 30.86, NULL, 10, NULL, NULL, NULL, NULL, 'test', NULL, '2018-01-28 12:23:08');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `priority` int(11) NOT NULL,
  `parent_category` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `coupon`
--

CREATE TABLE `coupon` (
  `coupon_code` varchar(255) NOT NULL,
  `max_amount` float DEFAULT NULL,
  `offer_perc` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coupon`
--

INSERT INTO `coupon` (`coupon_code`, `max_amount`, `offer_perc`) VALUES
('test', 34.2, 3.4);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `customer_email` varchar(255) DEFAULT NULL,
  `customer_first_name` varchar(255) DEFAULT NULL,
  `customer_last_name` varchar(255) DEFAULT NULL,
  `mobilenumber` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pizza`
--

CREATE TABLE `pizza` (
  `pizza_id` int(11) NOT NULL,
  `customized` bit(1) NOT NULL,
  `pizza_desc` varchar(255) DEFAULT NULL,
  `pizza_name` varchar(255) DEFAULT NULL,
  `price` float NOT NULL,
  `size` varchar(255) DEFAULT NULL,
  `base` int(11) DEFAULT NULL,
  `base_base_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pizza`
--

INSERT INTO `pizza` (`pizza_id`, `customized`, `pizza_desc`, `pizza_name`, `price`, `size`, `base`, `base_base_id`) VALUES
(1, b'0', 'This is the basic pizza for your hungry tummy!', 'Regular Bite', 149, 'Regular', 1, 1),
(2, b'0', 'Well, this will be the dream of your cheesy dream.', 'Cheesy Fantasy', 299, 'Medium', 5, 5),
(3, b'0', 'This one is for a family treat.', 'FamJam', 399, 'Large', 2, 2),
(4, b'0', 'This one is a special from our Lazy kitchen.', 'Lazy Boy', 499, 'Medium', 6, 6),
(5, b'0', 'Freshness is in air, and pizza too.', 'Freshgie', 149, 'Regular', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `pizza_order`
--

CREATE TABLE `pizza_order` (
  `order_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `quantity` int(11) NOT NULL,
  `total` float NOT NULL,
  `bill` int(11) DEFAULT NULL,
  `pizza` int(11) DEFAULT NULL,
  `bill_bill_id` int(11) DEFAULT NULL,
  `pizza_pizza_id` int(11) DEFAULT NULL,
  `pizza_topping` int(11) NOT NULL,
  `topping` int(11) DEFAULT NULL,
  `topping_topping_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pizza_order`
--

INSERT INTO `pizza_order` (`order_id`, `price`, `quantity`, `total`, `bill`, `pizza`, `bill_bill_id`, `pizza_pizza_id`, `pizza_topping`, `topping`, `topping_topping_id`) VALUES
(1, 260, 1, 260, NULL, NULL, 1, 2, 1, NULL, NULL),
(2, 260, 1, 260, NULL, NULL, 2, 1, 2, NULL, NULL),
(3, 21.44, 1, 21.44, NULL, NULL, 3, 1, 3, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pizza_topping`
--

CREATE TABLE `pizza_topping` (
  `pizza_topping` int(11) NOT NULL,
  `pizza` int(11) DEFAULT NULL,
  `topping` int(11) DEFAULT NULL,
  `pizza_pizza_id` int(11) DEFAULT NULL,
  `topping_topping_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pizza_topping`
--

INSERT INTO `pizza_topping` (`pizza_topping`, `pizza`, `topping`, `pizza_pizza_id`, `topping_topping_id`) VALUES
(1, 5, 2, 5, 2),
(2, 5, 4, 5, 4),
(3, 2, 6, 2, 6),
(4, 3, 1, 3, 3),
(5, 3, 5, 3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `topping`
--

CREATE TABLE `topping` (
  `topping_id` int(11) NOT NULL,
  `price` float NOT NULL,
  `stock` int(11) NOT NULL,
  `topping_name` varchar(255) DEFAULT NULL,
  `vegetarian` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `topping`
--

INSERT INTO `topping` (`topping_id`, `price`, `stock`, `topping_name`, `vegetarian`) VALUES
(1, 50, 100, 'Tomato', b'1'),
(2, 50, 100, 'Capsicum', b'1'),
(3, 50, 100, 'Onion', b'1'),
(4, 50, 100, 'Mushroom', b'1'),
(5, 50, 100, 'Corn', b'1'),
(6, 50, 100, 'Cheese', b'1'),
(7, 60, 100, 'Chicken Tikka', b'0'),
(8, 60, 100, 'Chicken Sausage', b'0'),
(9, 60, 100, 'Chicken Peri Peri', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `current_login_at` datetime DEFAULT NULL,
  `current_login_ip` varchar(255) DEFAULT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `last_login_at` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL,
  `mobilenumber` varchar(255) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `role` int(11) NOT NULL,
  `createdAt` date DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  `currentLoginAt` datetime DEFAULT NULL,
  `currentLoginIp` varchar(255) DEFAULT NULL,
  `lastLoginAt` datetime DEFAULT NULL,
  `lastLoginIp` varchar(255) DEFAULT NULL,
  `loginCount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `created_at`, `updated_at`, `current_login_at`, `current_login_ip`, `display_name`, `email`, `enabled`, `last_login_at`, `last_login_ip`, `login_count`, `mobilenumber`, `password`, `role`, `createdAt`, `updatedAt`, `currentLoginAt`, `currentLoginIp`, `lastLoginAt`, `lastLoginIp`, `loginCount`) VALUES
(1, '2018-01-29', '2018-01-29 09:10:20', '2018-01-29 09:10:20', '0:0:0:0:0:0:0:1', 'Saurabh Chalke', 'saurabhchalke@gmail.com', b'1', '2018-01-29 09:10:03', '0:0:0:0:0:0:0:1', 1, NULL, '$2a$10$hKHyEbprzMpop84prJlRKO8soz54XTuevE6DMLvwpwj/2jmKPqFnW', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '2018-01-29', '2018-01-29 09:11:30', '2018-01-29 09:11:30', '0:0:0:0:0:0:0:1', 'Rahul Pareek', 'r.k@gmail.com', b'1', NULL, NULL, 0, NULL, '$2a$10$3aeKZgZHkBMzho6/0v39t.vPHF5/LWBCq9YKFrMaGhCJLcO0rQtri', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '2018-01-29', '2018-01-29 09:12:11', '2018-01-29 09:12:11', '0:0:0:0:0:0:0:1', 'Siddharth Koti', 's.k@gmail.com', b'1', NULL, NULL, 0, NULL, '$2a$10$/UMBflK6W5B8s04xCm4/oul/5QIcydigaPYkRmYp5c/D39vUtgaRe', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`addrees_id`),
  ADD KEY `FK_m0cmdw9324e5ydmrectvlq4an` (`user`),
  ADD KEY `FK_33hg5keygw64femiy5lgd4y9t` (`user_id`);

--
-- Indexes for table `base`
--
ALTER TABLE `base`
  ADD PRIMARY KEY (`base_id`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`bill_id`),
  ADD KEY `FK_517th2irt3s86ws5cx9plidjl` (`address`),
  ADD KEY `FK_s1pajs8gvougak7cxkasc2jx1` (`coupon`),
  ADD KEY `FK_kj1rpum2gsgkjwljy7lddm4lt` (`user`),
  ADD KEY `FK_e6ict93m36tfaqvbuymtiw4mm` (`address_addrees_id`),
  ADD KEY `FK_2t5e4olkyq4v10lunqys3xarg` (`coupon_coupon_code`),
  ADD KEY `FK_6knjxj9wkc5wy5ydw33153rxv` (`user_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_idx` (`name`),
  ADD KEY `priority_idx` (`priority`),
  ADD KEY `parentCategory_idx` (`parent_category`);

--
-- Indexes for table `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`coupon_code`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `UK_qy5hqprdvx8o3dcidcfmf17x4` (`customer_email`),
  ADD UNIQUE KEY `UK_6m4it62t97e08ghrtcpnupfgg` (`mobilenumber`);

--
-- Indexes for table `pizza`
--
ALTER TABLE `pizza`
  ADD PRIMARY KEY (`pizza_id`),
  ADD KEY `FK_217mr4wn60n9chqd547qqghr8` (`base`),
  ADD KEY `FK_jbd6jadvxu8fs0ejiojs53wlc` (`base_base_id`);

--
-- Indexes for table `pizza_order`
--
ALTER TABLE `pizza_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK_kyyk2pg5r5b5vhjv2xhfmovi7` (`bill`),
  ADD KEY `FK_cl39tfupxl1rm5f2igro2fccu` (`pizza`),
  ADD KEY `FK_nqd7h5jtpnxn08j5sp01swy52` (`bill_bill_id`),
  ADD KEY `FK_gsedk0n0ima7x1nke37phin08` (`pizza_pizza_id`),
  ADD KEY `FK_abcxxig6ako7hme4n2ix5jf6u` (`topping`),
  ADD KEY `FK_76wp06u5oht08j0whaiyq78bg` (`topping_topping_id`);

--
-- Indexes for table `pizza_topping`
--
ALTER TABLE `pizza_topping`
  ADD PRIMARY KEY (`pizza_topping`),
  ADD KEY `FK_7nhirctondte4bsl1sa9w0jln` (`pizza`),
  ADD KEY `FK_bneqq52l7on116q0u6ekl4xfw` (`topping`),
  ADD KEY `FK_b6wrsvxpfbpe2vmi974by0vkg` (`pizza_pizza_id`),
  ADD KEY `FK_52lxk8blfbludxem77n664cuk` (`topping_topping_id`);

--
-- Indexes for table `topping`
--
ALTER TABLE `topping`
  ADD PRIMARY KEY (`topping_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email_idx` (`email`),
  ADD KEY `displayName_idx` (`display_name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `addrees_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `base`
--
ALTER TABLE `base`
  MODIFY `base_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pizza`
--
ALTER TABLE `pizza`
  MODIFY `pizza_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `pizza_order`
--
ALTER TABLE `pizza_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pizza_topping`
--
ALTER TABLE `pizza_topping`
  MODIFY `pizza_topping` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `topping`
--
ALTER TABLE `topping`
  MODIFY `topping_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK_33hg5keygw64femiy5lgd4y9t` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_m0cmdw9324e5ydmrectvlq4an` FOREIGN KEY (`user`) REFERENCES `user` (`id`);

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `FK_2t5e4olkyq4v10lunqys3xarg` FOREIGN KEY (`coupon_coupon_code`) REFERENCES `coupon` (`coupon_code`),
  ADD CONSTRAINT `FK_517th2irt3s86ws5cx9plidjl` FOREIGN KEY (`address`) REFERENCES `address` (`addrees_id`),
  ADD CONSTRAINT `FK_6knjxj9wkc5wy5ydw33153rxv` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_e6ict93m36tfaqvbuymtiw4mm` FOREIGN KEY (`address_addrees_id`) REFERENCES `address` (`addrees_id`),
  ADD CONSTRAINT `FK_kj1rpum2gsgkjwljy7lddm4lt` FOREIGN KEY (`user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_s1pajs8gvougak7cxkasc2jx1` FOREIGN KEY (`coupon`) REFERENCES `coupon` (`coupon_code`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_lhvwmxrdrjma5forcymeuolqn` FOREIGN KEY (`parent_category`) REFERENCES `category` (`id`);

--
-- Constraints for table `pizza`
--
ALTER TABLE `pizza`
  ADD CONSTRAINT `FK_217mr4wn60n9chqd547qqghr8` FOREIGN KEY (`base`) REFERENCES `base` (`base_id`),
  ADD CONSTRAINT `FK_jbd6jadvxu8fs0ejiojs53wlc` FOREIGN KEY (`base_base_id`) REFERENCES `base` (`base_id`);

--
-- Constraints for table `pizza_order`
--
ALTER TABLE `pizza_order`
  ADD CONSTRAINT `FK_76wp06u5oht08j0whaiyq78bg` FOREIGN KEY (`topping_topping_id`) REFERENCES `topping` (`topping_id`),
  ADD CONSTRAINT `FK_abcxxig6ako7hme4n2ix5jf6u` FOREIGN KEY (`topping`) REFERENCES `topping` (`topping_id`),
  ADD CONSTRAINT `FK_cl39tfupxl1rm5f2igro2fccu` FOREIGN KEY (`pizza`) REFERENCES `pizza` (`pizza_id`),
  ADD CONSTRAINT `FK_gsedk0n0ima7x1nke37phin08` FOREIGN KEY (`pizza_pizza_id`) REFERENCES `pizza` (`pizza_id`),
  ADD CONSTRAINT `FK_kyyk2pg5r5b5vhjv2xhfmovi7` FOREIGN KEY (`bill`) REFERENCES `bill` (`bill_id`),
  ADD CONSTRAINT `FK_nqd7h5jtpnxn08j5sp01swy52` FOREIGN KEY (`bill_bill_id`) REFERENCES `bill` (`bill_id`);

--
-- Constraints for table `pizza_topping`
--
ALTER TABLE `pizza_topping`
  ADD CONSTRAINT `FK_52lxk8blfbludxem77n664cuk` FOREIGN KEY (`topping_topping_id`) REFERENCES `topping` (`topping_id`),
  ADD CONSTRAINT `FK_7nhirctondte4bsl1sa9w0jln` FOREIGN KEY (`pizza`) REFERENCES `pizza` (`pizza_id`),
  ADD CONSTRAINT `FK_b6wrsvxpfbpe2vmi974by0vkg` FOREIGN KEY (`pizza_pizza_id`) REFERENCES `pizza` (`pizza_id`),
  ADD CONSTRAINT `FK_bneqq52l7on116q0u6ekl4xfw` FOREIGN KEY (`topping`) REFERENCES `topping` (`topping_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
