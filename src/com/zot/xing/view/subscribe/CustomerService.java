package com.zot.xing.view.subscribe;

import java.util.Date;

import org.apache.log4j.Logger;

import com.zot.manage.service.card.CardService;
import com.zot.manage.vo.checkin.CustomerInfoVO;
import com.zot.util.IdGen;
import com.zot.util.StringUtils;
import com.zot.wechat.msg.Constant;
import com.zot.xing.dao.card.CardBO;
import com.zot.xing.dao.subscribe.CarAS;
import com.zot.xing.dao.subscribe.CarASImpl;
import com.zot.xing.dao.subscribe.CarBO;
import com.zot.xing.dao.subscribe.CustomerAS;
import com.zot.xing.dao.subscribe.CustomerASImpl;
import com.zot.xing.dao.subscribe.CustomerBO;

public class CustomerService {
	private static Logger logger = Logger.getLogger(CustomerService.class);
	
	/**
	 * 创建客户，包含客户基本信息，车辆信息，以及会员卡信息，同时还包含会员卡充值日志信息
	 * @param custVO
	 */
	public static void createCustomer(CustomerInfoVO custVO)
	{
		//创建客户基本信息
		CustomerAS custAS = new CustomerASImpl();
		CustomerBO customer = getCustomerBO(custVO);
		custAS.addCustomer(customer);
		
		//创建车辆信息
		CarBO car = getCarBO(custVO, customer.getCustId());
		CarAS carAS = new CarASImpl();
		carAS.addCar(car);
		
		//判断是否要创建会员卡
		if (StringUtils.isNotEmpty(custVO.getClasscode())
			&& !Constant.ALL.equals(custVO.getClasscode()))
		{
			CardBO card = getCardBO(custVO, customer.getCustId());
			CardService.addCard(card);
		}
	}
	
	/**
	 * 根据微信号查询客户id，如果客户不存在，则自动创建后返回custId
	 * @param wechatno 微信号
	 * @param phoneno 手机号
	 * @return 客户id
	 */
	public static CustomerBO queryOrCreateCustIdByWechatno(String wechatno, String phoneno)
	{
		logger.debug(wechatno+","+phoneno);

		CustomerAS custAS = new CustomerASImpl();
		CustomerBO customer = custAS.queryCustomerByWechatno(wechatno);
		if (customer == null)
		{
			logger.debug("customer is not exist, create customer by wechatno, phoneno.");
			customer = addCustomer(wechatno, phoneno);
		}
		
		logger.debug("return custid is:" + customer.getCustId());
		return customer;
	}
	
	private static CustomerBO addCustomer(String wechatno, String phoneno)
	{
		CustomerBO customer = getCustomerBOByWechatno(wechatno, phoneno);
		CustomerAS custAS = new CustomerASImpl();
		custAS.addCustomer(customer);
		
		return customer;
	}
	
	private static CustomerBO getCustomerBOByWechatno(String wechatno, String phoneno)
	{
		CustomerBO customer = new CustomerBO();
		
		customer.setCustId(IdGen.genCustId());
		customer.setWechatno(wechatno);
		customer.setPhoneno(phoneno);
		customer.setSubTime(new Date());
		
		return customer;
	}
	
	private static CardBO getCardBO(CustomerInfoVO custVO, String custId)
	{
		CardBO card = new CardBO();
		card.setCardNo(IdGen.genCardId());
		card.setBalance(custVO.getRechargeAmt());
		card.setClassCode(custVO.getClasscode());
		card.setCreateTime(new Date());
		card.setCustId(custId);
		
		return card;
	}

	private static CarBO getCarBO(CustomerInfoVO custVO, String custId)
	{
		CarBO car = new CarBO();
		car.setBrand(custVO.getCarBrand());
		car.setCarno(custVO.getCarno());
		car.setCustId(custId);
		car.setCreateTime(new Date());
		
		return car;
	}
	
	private static CustomerBO getCustomerBO(CustomerInfoVO custVO)
	{
		CustomerBO customer = new CustomerBO();
		
		customer.setCustId(IdGen.genCustId());
		customer.setSubTime(new Date());
		customer.setWechatno(custVO.getWechatno());
		customer.setPhoneno(custVO.getPhoneno());
		customer.setSex(custVO.getSex());
		customer.setAddress(custVO.getAddress());
		customer.setCustName(custVO.getCustname());
		customer.setRemark(custVO.getRemark());
		customer.setWechatno(" ");//字段不能为空，故设置为空格
		
		return customer;
	}
}
