--
-- Database: `zot`
--
DROP TABLE t_zot_card;
CREATE TABLE IF NOT EXISTS `t_zot_card` (
  `cust_id` varchar(26) NOT NULL,
  `card_no` varchar(26) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `card_type` char(1) NOT NULL,
  `balance` int(10) NOT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_card`
  ADD PRIMARY KEY (`card_no`);

DROP TABLE t_zot_card_log;
CREATE TABLE IF NOT EXISTS `t_zot_card_log` (
  `id` varchar(26) NOT NULL,
  `card_no` varchar(26) NOT NULL,
  `oper_id` varchar(26) NOT NULL,
  `use_date` timestamp NOT NULL,
  `use_balance` int(10) NOT NULL,
  `before_balance` int(10) NOT NULL,
  `after_balance` int(10) NOT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_card_log`
  ADD PRIMARY KEY (`id`);

DROP TABLE t_zot_employ;
CREATE TABLE IF NOT EXISTS `t_zot_employ` (
  `employ_id` varchar(26) NOT NULL,
  `employ_name` varchar(128) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `phoneno` varchar(128) NOT NULL,
  `wechatno` bigint(20) NOT NULL,
  `super_employ_id` varchar(26) NOT NULL,
  `sex` char(1) NOT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_employ`
  ADD PRIMARY KEY (`employ_id`);

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_service`
--

DROP TABLE t_zot_service;
CREATE TABLE IF NOT EXISTS `t_zot_service` (
  `service_id` char(5) NOT NULL DEFAULT '',
  `service_name` varchar(128) NOT NULL,
  `service_des` varchar(128) NOT NULL,
  `cost_time` bigint(20) NOT NULL,
  `current_cnt` bigint(20) NOT NULL,
  `servicetype` int(11) NOT NULL,
  `price` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_service`
  ADD PRIMARY KEY (`service_id`);

--
-- 转存表中的数据 `t_zot_service`
--

INSERT INTO `t_zot_service` (`service_id`, `service_name`, `service_des`, `cost_time`, `current_cnt`, `servicetype`, `price`) VALUES
('10001', '快洗', '', 25, 2, 0, 29),
('10002', '精洗', '', 40, 1, 0, 59),
('10003', '美容', '', 60, 1, 0, 129),
('10006', '喷漆', '', 120, 1, 0, 199),
('10008', '保养', '', 30, 1, 0, 299),
('18001', '充值', '', 10, 1, 0, 500);

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_work_order`
--

DROP TABLE t_zot_work_order;
CREATE TABLE IF NOT EXISTS `t_zot_work_order` (
  `order_id` varchar(80) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `order_time` timestamp NULL DEFAULT NULL,
  `remind_time` timestamp NULL DEFAULT NULL,
  `status` char(1) NOT NULL,
  `arrived` char(1) NOT NULL DEFAULT 'N',
  `cust_id` varchar(128) DEFAULT NULL,
  `carno` varchar(20) DEFAULT NULL,
  `phoneno` varchar(20) DEFAULT NULL,
  `paytype` char(1) DEFAULT NULL,
  `amount` float(10) DEFAULT NULL,
  `service_object_id` varchar(128) DEFAULT NULL,
  `eval_desc_type` int(11) DEFAULT NULL,
  `eval_desc` text,
  `eval_result` text,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_work_order`
  ADD PRIMARY KEY (`order_id`);
 
ALTER TABLE `t_zot_work_order` 
  ADD INDEX work_order_index_status ( `status`);
  
ALTER TABLE `t_zot_work_order` 
  ADD INDEX work_order_index_cust_id ( `cust_id`);
  
ALTER TABLE `t_zot_work_order` 
  ADD INDEX work_order_index_carno ( `carno`);

DROP TABLE t_zot_work_order_detail;
CREATE TABLE IF NOT EXISTS `t_zot_work_order_detail` (
  `order_id` varchar(128) DEFAULT NULL,
  `order_type` char(5) DEFAULT NULL,
  `status` char(1) NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `service_time` timestamp NULL DEFAULT NULL,
  `over_time` timestamp NULL DEFAULT NULL, 
  `order_pro_desc` text,
  `employ_id` varchar(26) DEFAULT NULL,
  `amount` float(10) DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_work_order_detail`
  ADD PRIMARY KEY (`order_id`, `order_type`);
  
ALTER TABLE `t_zot_work_order_detail` 
  ADD INDEX work_order_detail_index_overtime ( `over_time`);

-- --------------------------------------------------------

--
-- 表的结构 `t_zot_customer`
--

DROP TABLE t_zot_customer;
CREATE TABLE IF NOT EXISTS `t_zot_customer` (
  `cust_id` varchar(128) NOT NULL,
  `cust_name` varchar(128) DEFAULT NULL,
  `wechatno` varchar(30) NOT NULL,
  `wechatname` varchar(128) DEFAULT NULL,
  `phoneno` varchar(20) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(128) DEFAULT NULL,
  `address` varchar(256) DEFAULT NULL,  
  `subtime` timestamp NULL DEFAULT NULL,
  `calsubtime` timestamp NULL DEFAULT NULL,
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_customer`
  ADD PRIMARY KEY (`cust_id`);
 
 --
-- 表的结构 `t_zot_car`
--

DROP TABLE t_zot_car;
CREATE TABLE IF NOT EXISTS `t_zot_car` (
  `cust_id` varchar(128) NOT NULL,
  `carno` varchar(20) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `brand` varchar(128) DEFAULT NULL,
  `cartype` varchar(20) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,  
  `remark` varchar(512) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_zot_car`
  ADD PRIMARY KEY (`carno`);
  
DROP TABLE T_ZOT_COST;
CREATE TABLE IF NOT EXISTS `T_ZOT_COST`
(
	`COST_ID` VARCHAR(50) NOT NULL PRIMARY KEY,
	`CREATE_TIME` TIMESTAMP,
	`COST_TIME` TIMESTAMP,
	-- 0：支出，1：收入
	`COST_TYPE` VARCHAR(20) NOT NULL, 
	-- 收入-0：取现；支出-1：日常餐饮；2：耗材；3：办公用品；4：设备：5：员工福利；6：运费；7：其他
	`COST_SUBTYPE` VARCHAR(20) NOT NULL, 
	`COST_AMOUNT` FLOAT(10,1),
	-- 经手人：员工Id
	`COST_OPERATOR` VARCHAR(20),
	-- 余额，店内卡的余额
	`BALANCE` FLOAT(10,1), 
	-- 备注说明
	`REMARK` VARCHAR(512) 
);

