package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * ���[�U���̖{��Bean
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2017/09/06		    NTS        	       �V�K�쐬
 *
 **/

public class SubUserDeptBean extends BaseBean {

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
	
	/** �����{��ID*/
	private String searchDeptId;
	
	/** �����{����*/
	private String searchDeptName;
	
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
	
	/** �����R�[�h*/
	private String DeptId;
	
	/** ������*/
	private String DeptName;
	
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

	public String getSearchDeptId() {
		return searchDeptId;
	}

	public void setSearchDeptId(String searchDeptId) {
		this.searchDeptId = searchDeptId;
	}

	public String getSearchDeptName() {
		return searchDeptName;
	}

	public void setSearchDeptName(String searchDeptName) {
		this.searchDeptName = searchDeptName;
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

	public String getDeptId() {
		return DeptId;
	}

	public void setDeptId(String deptId) {
		DeptId = deptId;
	}

	public String getDeptName() {
		return DeptName;
	}

	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	
}
