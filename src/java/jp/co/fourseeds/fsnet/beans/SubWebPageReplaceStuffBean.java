package jp.co.fourseeds.fsnet.beans;


import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * SRecBean
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.16 ©Ό΅Ξ
 */
public class SubWebPageReplaceStuffBean extends BaseBean {

	private static final long serialVersionUID = 5906995637865185563L;
	
	/** ]ΖυR[h*/
	private String userId;
	
	/** ]ΖυΌ*/
	private String userName;
	
	/** υ[U[Id*/
	private String searchUserName;
	
	/** οΠId*/
	private String conditionCompanyId;
	
	/** Id*/
	private String conditionUnificationId;
	
	/** ΖId*/
	private String conditionBusinessId;
	
	/** cΖId*/
	private String conditionSalesId;
	
	/** οΠΌΜ*/
	private String conditionCompanyName;
	
	/** ΌΜ*/
	private String conditionUnificationName;
	
	/** ΖΌΜ*/
	private String conditionBusinessName;
	
	/** cΖΌΜ*/
	private String conditionSalesName;
	
	/** οΠXg*/
	private List<LabelValueBean> companyList;
	
	/** Xg*/
	private List<LabelValueBean> unificationList;
	
	/** ΖXg*/
	private List<LabelValueBean> businessList;
	
	/** cΖXg*/
	private List<LabelValueBean> salesList;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getConditionCompanyId() {
		return conditionCompanyId;
	}

	public void setConditionCompanyId(String conditionCompanyId) {
		this.conditionCompanyId = conditionCompanyId;
	}

	public String getConditionUnificationId() {
		return conditionUnificationId;
	}

	public void setConditionUnificationId(String conditionUnificationId) {
		this.conditionUnificationId = conditionUnificationId;
	}

	public String getConditionBusinessId() {
		return conditionBusinessId;
	}

	public void setConditionBusinessId(String conditionBusinessId) {
		this.conditionBusinessId = conditionBusinessId;
	}

	public String getConditionSalesId() {
		return conditionSalesId;
	}

	public void setConditionSalesId(String conditionSalesId) {
		this.conditionSalesId = conditionSalesId;
	}

	public List<LabelValueBean> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<LabelValueBean> companyList) {
		this.companyList = companyList;
	}

	public List<LabelValueBean> getUnificationList() {
		return unificationList;
	}

	public void setUnificationList(List<LabelValueBean> unificationList) {
		this.unificationList = unificationList;
	}

	public List<LabelValueBean> getBusinessList() {
		return businessList;
	}

	public void setBusinessList(List<LabelValueBean> businessList) {
		this.businessList = businessList;
	}

	public List<LabelValueBean> getSalesList() {
		return salesList;
	}

	public void setSalesList(List<LabelValueBean> salesList) {
		this.salesList = salesList;
	}

	public String getConditionCompanyName() {
		return conditionCompanyName;
	}

	public void setConditionCompanyName(String conditionCompanyName) {
		this.conditionCompanyName = conditionCompanyName;
	}

	public String getConditionUnificationName() {
		return conditionUnificationName;
	}

	public void setConditionUnificationName(String conditionUnificationName) {
		this.conditionUnificationName = conditionUnificationName;
	}

	public String getConditionBusinessName() {
		return conditionBusinessName;
	}

	public void setConditionBusinessName(String conditionBusinessName) {
		this.conditionBusinessName = conditionBusinessName;
	}

	public String getConditionSalesName() {
		return conditionSalesName;
	}

	public void setConditionSalesName(String conditionSalesName) {
		this.conditionSalesName = conditionSalesName;
	}
	
}