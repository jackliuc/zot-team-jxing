/**
 * 服务对象
 */
package com.zot.xing.dao.subscribe;

/**
 * @author jack
 *
 */
public class ServiceBO {
	/**
	 * 服务id
	 **/
	private String service_id = null;
	/**
	 * 服务名称
	 */
	private String service_name = null;
	/**
	 * 服务描述
	 */
	private String service_des = null;
	/**
	 * 单位消耗时长
	 */
	private Long cost_time = null;
	/**
	 * 单位服务并行数
	 */
	private Long current_cnt = null;
	/**
	 * 服务类型...
	 **/
	private Integer serviceType = 0;
	
	private Long avg_time = null;
	
	private Long price = null;

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_des() {
		return service_des;
	}

	public void setService_des(String service_des) {
		this.service_des = service_des;
	}

	public Long getCost_time() {
		return cost_time;
	}

	public void setCost_time(Long cost_time) {
		this.cost_time = cost_time;
	}

	public Long getCurrent_cnt() {
		return current_cnt;
	}

	public void setCurrent_cnt(Long current_cnt) {
		this.current_cnt = current_cnt;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Long getAvg_time() {
		return Math.round(cost_time*0.1/current_cnt);
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	
}
