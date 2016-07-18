package com.zot.manage.action.system;

import com.zot.xing.dao.offer.OfferBO;

public class OfferVO extends OfferBO
{
	private String cmission_value;//界面显示的提成额，不区分固定金额还是百分比
	private String servCatalogName;
	private String servTypeCodeName;
	private String cmissionModeName;
	public String getCmission_value() {
		return cmission_value;
	}
	public void setCmission_value(String cmission_value) {
		this.cmission_value = cmission_value;
	}
	public String getServCatalogName() {
		return servCatalogName;
	}
	public void setServCatalogName(String servCatalogName) {
		this.servCatalogName = servCatalogName;
	}
	public String getServTypeCodeName() {
		return servTypeCodeName;
	}
	public void setServTypeCodeName(String servTypeCodeName) {
		this.servTypeCodeName = servTypeCodeName;
	}
	public String getCmissionModeName() {
		return cmissionModeName;
	}
	public void setCmissionModeName(String cmissionModeName) {
		this.cmissionModeName = cmissionModeName;
	}
}
