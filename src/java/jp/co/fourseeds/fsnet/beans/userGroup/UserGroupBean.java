/**
 * ���[�U�O���[�vBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.04 �V�K�쐬
 */

package jp.co.fourseeds.fsnet.beans.userGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class UserGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	/** �����g�D�敪*/
	private String searchOrganizationId;
	
	/** �����]�ƈ��敪*/
	private String searchPemployeeId;
	
	/** �������[�U�O���[�vid*/
	private String searchUserGroupId;
	
	/** �������[�U�O���[�v��*/
	private String searchUserGroupName;
	
	/** �����敪*/
	private String searchOriginType;
	
	/** �����e�[�u���敪�@1:���J�@2:�\�� */
	private String editFlag;
	
	/** �������[�U��*/
	private String searchUserName;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**������������������������A��������Bean������������������������*/
	/** ���[�U�O���[�vID */
	private String userGroupId;
	
	/** ���[�U�O���[�v�� */
	private String userGroupName;
	
	/** ���[�U�O���[�v�Љ� */
	private String userGroupIntro;
	
	/**���f�\�����*/
	private String ugRefDate;
	
	/** �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\�� */
	private String originType;
	
	/** ���[�U���X�g */
	private List<UserGroupDetailBean> userList;
	
	/** ���[�UID */
	private String userId;
	
	/** ���[�U���� */
	private String userName;
	
	/** �l�O���[�v�t���O*/
	private String personalGroupFlag;
	
	/** ����Id*/
	private String conditionUnificationId;
	
	/** ���ƕ�Id*/
	private String conditionBusinessId;
	
	/** �c�ƕ�Id*/
	private String conditionSalesId;
	
	/** ���id*/
	private String conditionCompanyId;
	
	/** �쐬�Җ� */
	private String createUserName;
	
	/** ��Ѓ��X�g*/
	private List<LabelValueBean> companyList;
	
	/** �������X�g*/
	private List<LabelValueBean> unificationList;
	
	/** ���ƕ����X�g*/
	private List<LabelValueBean> businessList;
	
	/** �c�ƕ����X�g*/
	private List<LabelValueBean> salesList;
	
	/**�g�D���X�g*/
	private List<LabelValueBean> organizationList;
	
	/**�]�ƈ��敪���X�g*/
	private List<LabelValueBean> pemployeeList;

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

	public String getConditionCompanyId() {
		return conditionCompanyId;
	}

	public void setConditionCompanyId(String conditionCompanyId) {
		this.conditionCompanyId = conditionCompanyId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
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

	public String getSearchUserGroupId() {
		return searchUserGroupId;
	}

	public void setSearchUserGroupId(String searchUserGroupId) {
		this.searchUserGroupId = searchUserGroupId;
	}

	public String getSearchUserGroupName() {
		return searchUserGroupName;
	}

	public void setSearchUserGroupName(String searchUserGroupName) {
		this.searchUserGroupName = searchUserGroupName;
	}

	public String getSearchOriginType() {
		return searchOriginType;
	}

	public void setSearchOriginType(String searchOriginType) {
		this.searchOriginType = searchOriginType;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getUserGroupIntro() {
		return userGroupIntro;
	}

	public void setUserGroupIntro(String userGroupIntro) {
		this.userGroupIntro = userGroupIntro;
	}

	public String getUgRefDate() {
		return ugRefDate;
	}

	public void setUgRefDate(String ugRefDate) {
		this.ugRefDate = ugRefDate;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public List<UserGroupDetailBean> getUserList() {
		return userList;
	}

	public void setUserList(List<UserGroupDetailBean> userList) {
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

	public String getPersonalGroupFlag() {
		return personalGroupFlag;
	}

	public void setPersonalGroupFlag(String personalGroupFlag) {
		this.personalGroupFlag = personalGroupFlag;
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
}