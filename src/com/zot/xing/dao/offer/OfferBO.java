package com.zot.xing.dao.offer;

import java.util.Date;

public class OfferBO 
{
	private String offerCode;
	private String offerName;
	private String servCatalog;
	private String servTypeCode;
	private Date createDate;
	private Date lstUpdDate;
	private Float price;
	private Float minPrice;
	private Integer cmission_mode;
	private Float cmission_amount;
	private Integer cmissin_percent;
	private String prod_code;
	private String remark;
	
	public String getOfferCode() {
		return offerCode;
	}
	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getServCatalog() {
		return servCatalog;
	}
	public void setServCatalog(String servCatalog) {
		this.servCatalog = servCatalog;
	}
	public String getServTypeCode() {
		return servTypeCode;
	}
	public void setServTypeCode(String servTypeCode) {
		this.servTypeCode = servTypeCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getCmission_mode() {
		return cmission_mode;
	}
	public void setCmission_mode(Integer cmission_mode) {
		this.cmission_mode = cmission_mode;
	}
	public Float getCmission_amount() {
		return cmission_amount;
	}
	public void setCmission_amount(Float cmission_amount) {
		this.cmission_amount = cmission_amount;
	}
	public Integer getCmissin_percent() {
		return cmissin_percent;
	}
	public void setCmissin_percent(Integer cmissin_percent) {
		this.cmissin_percent = cmissin_percent;
	}
	public String getProd_code() {
		return prod_code;
	}
	public void setProd_code(String prod_code) {
		this.prod_code = prod_code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
