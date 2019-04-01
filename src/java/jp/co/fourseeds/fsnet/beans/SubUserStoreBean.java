package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * ���[�U���̓X��Bean
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2017/09/06		    NTS        	       �V�K�쐬
 *
 **/

public class SubUserStoreBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	
	/** �������ID*/
	private String searchCompanyId;
	
	/** �����c��Ж�*/
	private String searchCompanyName;
	
	/** ��������ID*/
	private String searchUnificationId;
	
	/** ����������*/
	private String searchUnificationName;
	
	/** ��������ID*/
	private String searchBusinessId;
	
	/** �������Ɩ�*/
	private String searchBusinessName;
	
	/** �����c�ƕ�ID*/
	private String searchSalesId;
	
	/** �����c�ƕ���*/
	private String searchSalesName;
	
	/** �����X��ID*/
	private String searchStoreId;
	
	/** �����X�ܖ�*/
	private String searchStoreName;
	
	/** �����X�܋敪*/
	private String searchFCFlag;
	
	/** �����X�܋敪��*/
	private String searchFCName;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;

	/**�������X�g*/
	private List<LabelValueBean> companyList;
	
	/**�������X�g*/
	private List<LabelValueBean> unificationList;
	
	/**���ƃ��X�g*/
	private List<LabelValueBean> businessList;
	
	/**�c�ƕ����X�g*/
	private List<LabelValueBean> salesList;
	
	/**�g�D���X�g*/
	private List<LabelValueBean> storeAttrList;
	
	/** �����R�[�h*/
	private String StoreId;
	
	/** ������*/
	private String StoreName;
	
	/**������������������������A��������Bean������������������������*/

	public String getSearchCompanyId() {
		return searchCompanyId;
	}

	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}

	public String getSearchCompanyName() {
		return searchCompanyName;
	}

	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}


	public String getSearchUnificationId() {
		return searchUnificationId;
	}

	public void setSearchUnificationId(String searchUnificationId) {
		this.searchUnificationId = searchUnificationId;
	}

	public String getSearchUnificationName() {
		return searchUnificationName;
	}

	public void setSearchUnificationName(String searchUnificationName) {
		this.searchUnificationName = searchUnificationName;
	}

	public String getSearchBusinessId() {
		return searchBusinessId;
	}

	public void setSearchBusinessId(String searchBusinessId) {
		this.searchBusinessId = searchBusinessId;
	}

	public String getSearchBusinessName() {
		return searchBusinessName;
	}

	public void setSearchBusinessName(String searchBusinessName) {
		this.searchBusinessName = searchBusinessName;
	}

	public String getSearchSalesId() {
		return searchSalesId;
	}

	public void setSearchSalesId(String searchSalesId) {
		this.searchSalesId = searchSalesId;
	}

	public String getSearchSalesName() {
		return searchSalesName;
	}

	public void setSearchSalesName(String searchSalesName) {
		this.searchSalesName = searchSalesName;
	}

	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	public int getSearchRowNum() {
		return searchRowNum;
	}

	public void setSearchRowNum(int searchRowNum) {
		this.searchRowNum = searchRowNum;
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

	public String getSearchStoreId() {
		return searchStoreId;
	}

	public void setSearchStoreId(String searchStoreId) {
		this.searchStoreId = searchStoreId;
	}

	public String getSearchStoreName() {
		return searchStoreName;
	}

	public void setSearchStoreName(String searchStoreName) {
		this.searchStoreName = searchStoreName;
	}

	public String getSearchFCFlag() {
		return searchFCFlag;
	}

	public void setSearchFCFlag(String searchFCFlag) {
		this.searchFCFlag = searchFCFlag;
	}

	public String getSearchFCName() {
		return searchFCName;
	}

	public void setSearchFCName(String searchFCName) {
		this.searchFCName = searchFCName;
	}

	public List<LabelValueBean> getStoreAttrList() {
		return storeAttrList;
	}

	public void setStoreAttrList(List<LabelValueBean> storeAttrList) {
		this.storeAttrList = storeAttrList;
	}

	public String getStoreId() {
		return StoreId;
	}

	public void setStoreId(String storeId) {
		StoreId = storeId;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}
	
}
