package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;

public class SubPageProxyUserFormBean extends BaseBean {
	
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 2479865448514900978L;
	
	/**
	 * �������[�U�[����
	 * */
	private String userSearchName;
	
	/** �����g�D�敪*/
	private String searchOrganizationId;

	/** �����g�D�敪��*/
	private String searchOrganizationName;
	
	/** �����]�ƈ��敪*/
	private String searchPemployeeId;
	
	/** �����]�ƈ��敪��*/
	private String searchPemployeeName;
	
	/**�g�D���X�g*/
	private List<LabelValueBean> organizationList;
	
	/**�]�ƈ��敪���X�g*/
	private List<LabelValueBean> pemployeeList;

	/** ��Ѓ��X�g*/
	private List<LabelValueBean> companyList;
	
	/** �������X�g*/
	private List<LabelValueBean> unificationList;
	
	/** ���ƃ��X�g*/
	private List<LabelValueBean> businessList;
	
	/** �c�ƕ����X�g*/
	private List<LabelValueBean> salesList;
	
	/** �������͉��ID */
	private String searchCompanyId;
	
	/** �������͓���ID */
	private String searchUnificationId;
	
	/** �������͎���ID */
	private String searchBusinessId;
	
	/** �������͉c�ƕ�ID */
	private String searchSalesId;
	
	/**
	 * �������ʂ̃��[�U�[���X�g
	 * */
	private List<AuthUserBean> userList;
	
	/**
	 * TEMP���[�U�[
	 * */
	private String tempProxyList;
	
	/**
	 * ���F���[�����X�g
	 * */
	private List<ProxyUserBean> proxyMailList;
	
	/**
	 * ���F���[�U�[ID
	 * */
	private String proxyUid;
	
	/**
	 * ���[������
	 * */
	private String countMail;
	
	/** �������͉�Ж��� */
	private String searchCompanyName;
	
	/** �������͓������� */
	private String searchUnificationName;
	
	/** �������͎��Ɩ��� */
	private String searchBusinessName;
	
	/** �������͉c�ƕ����� */
	private String searchSalesName;

	/**
	 * @return the userSearchName
	 */
	public String getUserSearchName() {
		return userSearchName;
	}

	/**
	 * @param userSearchName the userSearchName to set
	 */
	public void setUserSearchName(String userSearchName) {
		this.userSearchName = userSearchName;
	}
	
	public String getSearchOrganizationId() {
		return searchOrganizationId;
	}

	public void setSearchOrganizationId(String searchOrganizationId) {
		this.searchOrganizationId = searchOrganizationId;
	}

	public String getSearchPemployeeId() {
		return searchPemployeeId;
	}

	public void setSearchPemployeeId(String searchPemployeeId) {
		this.searchPemployeeId = searchPemployeeId;
	}

	public List<LabelValueBean> getOrganizationList() {
		return organizationList;
	}

	public void setOrganizationList(List<LabelValueBean> organizationList) {
		this.organizationList = organizationList;
	}

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
	}

	/**
	 * @return the userList
	 */
	public List<AuthUserBean> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<AuthUserBean> userList) {
		this.userList = userList;
	}

	/**
	 * @return the proxyMailList
	 */
	public List<ProxyUserBean> getProxyMailList() {
		return proxyMailList;
	}

	/**
	 * @param proxyMailList the proxyMailList to set
	 */
	public void setProxyMailList(List<ProxyUserBean> proxyMailList) {
		this.proxyMailList = proxyMailList;
	}

	/**
	 * @return the proxyUid
	 */
	public String getProxyUid() {
		return proxyUid;
	}

	/**
	 * @param proxyUid the proxyUid to set
	 */
	public void setProxyUid(String proxyUid) {
		this.proxyUid = proxyUid;
	}

	/**
	 * @return the countMail
	 */
	public String getCountMail() {
		return countMail;
	}

	/**
	 * @param countMail the countMail to set
	 */
	public void setCountMail(String countMail) {
		this.countMail = countMail;
	}

	/**
	 * @return the tempProxyList
	 */
	public String getTempProxyList() {
		return tempProxyList;
	}

	/**
	 * @param tempProxyList the tempProxyList to set
	 */
	public void setTempProxyList(String tempProxyList) {
		this.tempProxyList = tempProxyList;
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

	public String getSearchCompanyId() {
		return searchCompanyId;
	}

	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}

	public String getSearchUnificationId() {
		return searchUnificationId;
	}

	public void setSearchUnificationId(String searchUnificationId) {
		this.searchUnificationId = searchUnificationId;
	}

	public String getSearchBusinessId() {
		return searchBusinessId;
	}

	public void setSearchBusinessId(String searchBusinessId) {
		this.searchBusinessId = searchBusinessId;
	}

	public String getSearchSalesId() {
		return searchSalesId;
	}

	public void setSearchSalesId(String searchSalesId) {
		this.searchSalesId = searchSalesId;
	}

	public String getSearchCompanyName() {
		return searchCompanyName;
	}

	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}

	public String getSearchUnificationName() {
		return searchUnificationName;
	}

	public void setSearchUnificationName(String searchUnificationName) {
		this.searchUnificationName = searchUnificationName;
	}

	public String getSearchBusinessName() {
		return searchBusinessName;
	}

	public void setSearchBusinessName(String searchBusinessName) {
		this.searchBusinessName = searchBusinessName;
	}

	public String getSearchSalesName() {
		return searchSalesName;
	}

	public void setSearchSalesName(String searchSalesName) {
		this.searchSalesName = searchSalesName;
	}

	public String getSearchOrganizationName() {
		return searchOrganizationName;
	}

	public void setSearchOrganizationName(String searchOrganizationName) {
		this.searchOrganizationName = searchOrganizationName;
	}

	public String getSearchPemployeeName() {
		return searchPemployeeName;
	}

	public void setSearchPemployeeName(String searchPemployeeName) {
		this.searchPemployeeName = searchPemployeeName;
	}
	
}
