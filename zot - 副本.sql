--
-- Database: `zot`
--

CREATE TABLE IF NOT EXISTS `t_zot_employ` (
  `employ_id` varchar(26) NOT NULL,
  `employ_name` varchar(128) NOT NULL,
  `phoneno` varchar(128) NOT NULL,
  `wechatno` bigint(20) NOT NULL,
  `super_employ_id` varchar(26) NOT NULL,
  `sex` char(1) NOT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `order_id` varchar(80) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `status` timestamp NULL DEFAULT NULL,
  `cust_id` varchar(128) DEFAULT NULL,
  `service_object_id` varchar(128) DEFAULT NULL,
  `eval_desc_type` int(11) DEFAULT NULL,
  `eval_desc` text,
  `eval_result` text,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `t_zot_work_order_detail` (
  `order_detail_id` varchar(80) NOT NULL DEFAULT '',
  `order_id` varchar(128) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `service_time` timestamp NULL DEFAULT NULL,
  `over_time` timestamp NULL DEFAULT NULL, 
  `order_pro_desc` text,
  `employ_id` varchar(26) DEFAULT NULL,
  `remind_time` timestamp NULL DEFAULT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  `order_type` char(5) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_customer`
--

CREATE TABLE IF NOT EXISTS `t_zot_customer` (
  `cust_id` varchar(128) NOT NULL,
  `cust_name` varchar(128) DEFAULT NULL,
  `wechatno` varchar(30) NOT NULL,
  `wechatname` varchar(128) DEFAULT NULL,
  `phoneno` varchar(20) DEFAULT NULL,
  `carno` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,  
  `subtime` timestamp NULL DEFAULT NULL,
  `calsubtime` timestamp NULL DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Indexes for dumped tables
--
ALTER TABLE `t_zot_employ`
  ADD PRIMARY KEY (`employ_id`);

--
-- Indexes for table `t_zot_service`
--
ALTER TABLE `t_zot_service`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `t_zot_work_order`
--
ALTER TABLE `t_zot_work_order`
  ADD PRIMARY KEY (`order_id`);

  
ALTER TABLE `t_zot_work_order_detail`
  ADD PRIMARY KEY (`order_detail_id`);
--
-- Indexes for table `t_zot_xing_wechatsub`
--
ALTER TABLE `t_zot_customer`
  ADD PRIMARY KEY (`cust_id`);

