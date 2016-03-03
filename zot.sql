-- phpMyAdmin SQL Dump
-- version 4.3.13
-- http://www.phpmyadmin.net
--
-- Host: wxjwjqhmauvs.rds.sae.sina.com.cn:11381
-- Generation Time: 2015-09-03 16:43:27
-- 服务器版本： 5.6.23-72.1-log
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `zot`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_service`
--

CREATE TABLE IF NOT EXISTS `t_zot_service` (
  `service_id` char(5) NOT NULL DEFAULT '',
  `service_name` varchar(128) NOT NULL,
  `service_des` varchar(128) NOT NULL,
  `cost_time` bigint(20) NOT NULL,
  `current_cnt` bigint(20) NOT NULL,
  `servicetype` int(11) NOT NULL,
  `price` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='服务表';

--
-- 转存表中的数据 `t_zot_service`
--

INSERT INTO `t_zot_service` (`service_id`, `service_name`, `service_des`, `cost_time`, `current_cnt`, `servicetype`, `price`) VALUES
('10001', '洗车', '', 30, 2, 0, 20),
('10002', '做漆', '', 45, 1, 0, 200),
('10003', '钣金', '', 120, 1, 0, 200),
('10006', '装潢', '', 120, 1, 0, 2000);

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_work_order`
--

CREATE TABLE IF NOT EXISTS `t_zot_work_order` (
  `id` varchar(80) NOT NULL DEFAULT '',
  `create_time` timestamp NULL DEFAULT NULL,
  `service_time` timestamp NULL DEFAULT NULL,
  `over_time` timestamp NULL DEFAULT NULL,
  `cust_id` varchar(128) DEFAULT NULL,
  `service_object_id` varchar(128) DEFAULT NULL,
  `eval_desc_type` int(11) DEFAULT NULL,
  `eval_desc` text,
  `service_pro_desc` text,
  `service_person_num` varchar(128) DEFAULT NULL,
  `remind_time` timestamp NULL DEFAULT NULL,
  `eval_result` text,
  `order_time` timestamp NULL DEFAULT NULL,
  `order_type` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_xing_wechatsub`
--

CREATE TABLE IF NOT EXISTS `t_zot_xing_wechatsub` (
  `wechatno` varchar(30) NOT NULL,
  `wechatname` varchar(128) DEFAULT NULL,
  `phoneno` varchar(20) DEFAULT NULL,
  `subtime` timestamp NULL DEFAULT NULL,
  `calsubtime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_zot_xing_wechatsub`
--

INSERT INTO `t_zot_xing_wechatsub` (`wechatno`, `wechatname`, `phoneno`, `subtime`, `calsubtime`) VALUES
('cf', NULL, NULL, '2015-08-30 07:58:39', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_zot_service`
--
ALTER TABLE `t_zot_service`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `t_zot_work_order`
--
ALTER TABLE `t_zot_work_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_zot_xing_wechatsub`
--
ALTER TABLE `t_zot_xing_wechatsub`
  ADD PRIMARY KEY (`wechatno`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
