/**
 * 订单对象
 */
package com.zot.xing.dao.subscribe;

import java.util.Date;

/**
 * @author jack
 * 爱行订单对象
 */
public class XingWorkOrderBO {
	/**
	 * 订单号
	 */
	private String id = null;
	/**
	 * 订单类型...
	 */
	private String order_type = null;
	/**
	 * 创建时间
	 * 
	 */
	private Date create_time = null;
	/**
	 * 服务时间
	 * 
	 */
	private Date service_time = null;
	
	/**
	 * 预定时间
	 * 
	 */
	private Date order_time = null;
	
	/**
	 * 完成时间
	 * 
	 */
	private Date over_time = null;
	/**
	 * 客户id
	 * 
	 */
	private String cust_id = null;
	/**
	 * 服务对象id
	 * 
	 */
	private String service_object_id = null;
	/**
	 * 评价类型：...
	 * 
	 */
	private Integer eval_desc_type = null;
	/**
	 * 评价内容
	 * 
	 */
	private String eval_desc = null;
	/**
	 * 服务项目描述
	 * 
	 */
	private String service_pro_desc = null;
	/**
	 * 服务人员名称
	 * 
	 */
	private String service_person_num = null;
	/**
	 * 提醒时间
	 * 
	 */
	private Date remind_time = null;
	/** 如果是投诉一定要有处理结果，并且向客户反馈
	  * 
	  */
	 private String eval_result = null;
	 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_type() {
		return order_type;
	}
	public void setOrder_type(String order_type) {
		this.order_type = order_type;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getService_time() {
		return service_time;
	}
	public void setService_time(Date service_time) {
		this.service_time = service_time;
	}
	public Date getOver_time() {
		return over_time;
	}
	public void setOver_time(Date over_time) {
		this.over_time = over_time;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getService_object_id() {
		return service_object_id;
	}
	public void setService_object_id(String service_object_id) {
		this.service_object_id = service_object_id;
	}
	public Integer getEval_desc_type() {
		return eval_desc_type;
	}
	public void setEval_desc_type(Integer eval_desc_type) {
		this.eval_desc_type = eval_desc_type;
	}
	public String getEval_desc() {
		return eval_desc;
	}
	public void setEval_desc(String eval_desc) {
		this.eval_desc = eval_desc;
	}
	public String getService_pro_desc() {
		return service_pro_desc;
	}
	public void setService_pro_desc(String service_pro_desc) {
		this.service_pro_desc = service_pro_desc;
	}
	public String getService_person_num() {
		return service_person_num;
	}
	public void setService_person_num(String service_person_num) {
		this.service_person_num = service_person_num;
	}
	public Date getRemind_time() {
		return remind_time;
	}
	public void setRemind_time(Date remind_time) {
		this.remind_time = remind_time;
	}
	public String getEval_result() {
		return eval_result;
	}
	public void setEval_result(String eval_result) {
		this.eval_result = eval_result;
	}
	public Date getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Date order_time) {
		this.order_time = order_time;
	}
	 
	 
}
