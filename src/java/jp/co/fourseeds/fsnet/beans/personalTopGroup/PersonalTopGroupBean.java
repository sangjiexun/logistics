/**
 * �g�b�v�O���[�vBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 �V�K�쐬
 */

package jp.co.fourseeds.fsnet.beans.personalTopGroup;

import java.util.List;

import jp.co.common.frame.beans.LabelValueBean;

import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupBean;

public class PersonalTopGroupBean extends TopGroupBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/

	/** �����g�D�敪*/
	private String searchOrganizationId;
	
	/** �����]�ƈ��敪*/
	private String searchPemployeeId;
	
	/**�g�D���X�g*/
	private List<LabelValueBean> organizationList;
	
	/**�]�ƈ��敪���X�g*/
	private List<LabelValueBean> pemployeeList;
	
	/** �������L���[�U��*/
	private String searchShareUserName;
	
	/** �������L���[�U�̕���Id*/
	private String searchShareDeptId;
	
	/** �������[�U��*/
	private String searchUserName;
	
	/** ��������Id*/
	private String searchDepartmentId;
	
	/**������������������������A��������Bean������������������������*/
	
	/** ���L���[�U���X�g */
	private List<PersonalTopGroupDetailBean> shareUserList;
	
	/** ���[�U���X�g */
	private List<PersonalTopGroupDetailBean> userList;
	
	/** ���[�UID */
	private String userId;
	
	/** ���[�U���� */
	private String userName;
	
	/** �쐬�Җ� */
	private String createUserName;
	
	/** �ҏW�\�t���O */
	private String authEditFlag;
	
	/** �ڍ׉�ʂ̒��ŁA�ҏW�ƍ폜�{�^���\������ */
	private String btnDisplayRight;
	
	/** �����X�ܖ�*/
	private String searchStoreGroupName;
	
	/** ����������*/
	private String searchDeptGroupName;
	
	/** ���Id*/
	private String searchShareCompanyId;
	
	/** ����Id*/
	private String searchShareUnificationId;
	
	/** ���ƕ�Id*/
	private String searchShareBusinessId;
	
	/** �c�ƕ�Id*/
	private String searchShareSalesId;
	
	/** ���id*/
	private String searchCompanyId;
	
	/** ����Id*/
	private String searchUnificationId;
	
	/** ���ƕ�Id*/
	private String searchBusinessId;
	
	/** �c�ƕ�Id*/
	private String searchSalesId;
	
	/** �����O���[�v�����p�Ј��敪*/
	private String conditionPemployeeId;
	
	/** �ҏW��ʂ̉�Ѓ��X�g*/
	private List<LabelValueBean> shareCompanyList;
	
	/** �ҏW��ʂ̓������X�g*/
	private List<LabelValueBean> shareUnificationList;
	
	/** �ҏW��ʂ̎��ƕ����X�g*/
	private List<LabelValueBean> shareBusinessList;
	
	/** �ҏW��ʂ̉c�ƕ����X�g*/
	private List<LabelValueBean> shareSalesList;
	
	/** �ҏW��ʂ̉�Ѓ��X�g*/
	private List<LabelValueBean> companyList;
	
	/** �ҏW��ʂ̓������X�g*/
	private List<LabelValueBean> unificationList;
	
	/** �ҏW��ʂ̎��ƕ����X�g*/
	private List<LabelValueBean> businessList;
	
	/** �ҏW��ʂ̉c�ƕ����X�g*/
	private List<LabelValueBean> salesList;
	
	/** LEVEL*/
	private int level;
	
	/** �Ј��敪*/
	private String searchConditionPemployeeId;
	
	/** �X�܋敪*/
	private String searchStoredivision;

	public String getSearchShareUserName() {
		return searchShareUserName;
	}

	public void setSearchShareUserName(String searchShareUserName) {
		this.searchShareUserName = searchShareUserName;
	}

	public String getSearchShareDeptId() {
		return searchShareDeptId;
	}

	public void setSearchShareDeptId(String searchShareDeptId) {
		this.searchShareDeptId = searchShareDeptId;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}

	public List<PersonalTopGroupDetailBean> getShareUserList() {
		return shareUserList;
	}

	public void setShareUserList(List<PersonalTopGroupDetailBean> shareUserList) {
		this.shareUserList = shareUserList;
	}

	public List<PersonalTopGroupDetailBean> getUserList() {
		return userList;
	}

	public void setUserList(List<PersonalTopGroupDetailBean> userList) {
		this.userList = userList;
	}

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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getBtnDisplayRight() {
		return btnDisplayRight;
	}

	public void setBtnDisplayRight(String btnDisplayRight) {
		this.btnDisplayRight = btnDisplayRight;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
	}

	public String getSearchStoreGroupName() {
		return searchStoreGroupName;
	}

	public void setSearchStoreGroupName(String searchStoreGroupName) {
		this.searchStoreGroupName = searchStoreGroupName;
	}

	public String getSearchDeptGroupName() {
		return searchDeptGroupName;
	}

	public void setSearchDeptGroupName(String searchDeptGroupName) {
		this.searchDeptGroupName = searchDeptGroupName;
	}

	public String getSearchShareCompanyId() {
		return searchShareCompanyId;
	}

	public void setSearchShareCompanyId(String searchShareCompanyId) {
		this.searchShareCompanyId = searchShareCompanyId;
	}

	public String getSearchShareUnificationId() {
		return searchShareUnificationId;
	}

	public void setSearchShareUnificationId(String searchShareUnificationId) {
		this.searchShareUnificationId = searchShareUnificationId;
	}

	public String getSearchShareBusinessId() {
		return searchShareBusinessId;
	}

	public void setSearchShareBusinessId(String searchShareBusinessId) {
		this.searchShareBusinessId = searchShareBusinessId;
	}

	public String getSearchShareSalesId() {
		return searchShareSalesId;
	}

	public void setSearchShareSalesId(String searchShareSalesId) {
		this.searchShareSalesId = searchShareSalesId;
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

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
	}

	public List<LabelValueBean> getShareCompanyList() {
		return shareCompanyList;
	}

	public void setShareCompanyList(List<LabelValueBean> shareCompanyList) {
		this.shareCompanyList = shareCompanyList;
	}

	public List<LabelValueBean> getShareUnificationList() {
		return shareUnificationList;
	}

	public void setShareUnificationList(List<LabelValueBean> shareUnificationList) {
		this.shareUnificationList = shareUnificationList;
	}

	public List<LabelValueBean> getShareBusinessList() {
		return shareBusinessList;
	}

	public void setShareBusinessList(List<LabelValueBean> shareBusinessList) {
		this.shareBusinessList = shareBusinessList;
	}

	public List<LabelValueBean> getShareSalesList() {
		return shareSalesList;
	}

	public void setShareSalesList(List<LabelValueBean> shareSalesList) {
		this.shareSalesList = shareSalesList;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSearchConditionPemployeeId() {
		return searchConditionPemployeeId;
	}

	public void setSearchConditionPemployeeId(String searchConditionPemployeeId) {
		this.searchConditionPemployeeId = searchConditionPemployeeId;
	}

	public String getSearchStoredivision() {
		return searchStoredivision;
	}

	public void setSearchStoredivision(String searchStoredivision) {
		this.searchStoredivision = searchStoredivision;
	}
	
}