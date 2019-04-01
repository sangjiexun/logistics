package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

@SuppressWarnings(value={"rawtypes"})
public class SubDeptUserBean extends BaseBean {
	
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 5405738970767660445L;

	/** ��Ѓ��X�g*/
	private List<LabelValueBean> companyList;
	
	/** �������X�g*/
	private List<LabelValueBean> unificationList;
	
	/** ���ƃ��X�g*/
	private List<LabelValueBean> businessList;
	
	/** �c�ƕ����X�g*/
	private List<LabelValueBean> salesList;
	
	/**�g�D���X�g*/
	private List<LabelValueBean> organizationList;
	
	/**�]�ƈ��敪���X�g*/
	private List<LabelValueBean> pemployeeList;

	/** �����g�D�敪*/
	private String searchOrganizationId;

	/** �����g�D�敪��*/
	private String searchOrganizationName;
	
	/** �����]�ƈ��敪*/
	private String searchPemployeeId;
	
	/** �����]�ƈ��敪��*/
	private String searchPemployeeName;
	
	/** �������͉��ID */
	private String searchCompanyId;
	
	/** �������͓���ID */
	private String searchUnificationId;
	
	/** �������͎���ID */
	private String searchBusinessId;
	
	/** �������͉c�ƕ�ID */
	private String searchSalesId;
	
	/** ������Ж�*/
	private String searchCompanyName;
	
	/** ����������*/
	private String searchUnificationName;
	
	/** �������Ɩ�*/
	private String searchBusinessName;
	
	/** �����c�ƕ���*/
	private String searchSalesName;
	
	/** �c�ƕ����X�g*/
	private List userSearchList;
	
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

	public List getUserSearchList() {
		return userSearchList;
	}

	public void setUserSearchList(List userSearchList) {
		this.userSearchList = userSearchList;
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
