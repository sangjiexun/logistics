/**
 * �g�b�v�O���[�vBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 �V�K�쐬
 */

package jp.co.fourseeds.fsnet.beans.topGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;

public class TopGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	/** �����g�b�v�O���[�vid*/
	private String searchTopGroupId;
	
	/** �����g�b�v�O���[�v��*/
	private String searchTopGroupName;
	
	/** �����敪*/
	private String searchOriginType;
	
	/** �����e�[�u���敪�@1:���J�@2:�\�� */
	private String editFlag;
	
	/** �������̑w�O���[�vid*/
	private String searchSubGroupId;
	
	/** �������̑w�O���[�v�敪*/
	private String searchSubGroupFlag;
	
	/** �������̑w�O���[�v�s*/
	private String searchSubGroupRowNum;
	
	/** �������̑wid*/
	private String searchSubId;
	
	/** �����X�܃O���[�v�̃A���o�C�g�܂ރt���O*/
	private String searchPartTimeIncludeFlag;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**������������������������A��������Bean������������������������*/
	/** �g�b�v�O���[�vID */
	private String topGroupId;
	
	/** �g�b�v�O���[�v�� */
	private String topGroupName;
	
	/** �g�b�v�O���[�v�Љ� */
	private String topGroupIntro;
	
	/**���f�\�����*/
	private String lgRefDate;
	
	/** �l�O���[�v�쐬���[�UID */
	private String personalCreateUserId;
	
	/** �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\�� */
	private String originType;
	
	/**�����O���[�v���X�g*/
	private List<TopGroupDetailBean> deptGroupList;
	
	/**�����O���[�vID*/
	private String deptGroupId;
	
	/**�����O���[�v��*/
	private String deptGroupName;
	
	/**�X�܃O���[�v���X�g*/
	private List<TopGroupDetailBean> storeGroupList;
	
	/**�X�܃O���[�vID*/
	private String storeGroupId;
	
	/**�X�܃O���[�v��*/
	private String storeGroupName;
	
	/**���[�U�O���[�v���X�g*/
	private List<TopGroupDetailBean> userGroupList;
	
	/**���[�U�O���[�vID*/
	private String userGroupId;
	
	/**���[�U�O���[�v��*/
	private String userGroupName;
	
	/** �Ј��敪Id*/
	private String conditionPemployeeId;
	
	/** �����X�ܖ�*/
	private String searchStoreGroupName;
	
	/** ����������*/
	private String searchDeptGroupName;
	
	/** �������[�U?��*/
	private String searchUserGroupName;
	
	/** LEVEL*/
	private int level;
	
	/** �Ј��敪*/
	private String searchConditionPemployeeId;
	
	/** �X�܋敪*/
	private String searchStoredivision;

	public String getSearchTopGroupId() {
		return searchTopGroupId;
	}

	public void setSearchTopGroupId(String searchTopGroupId) {
		this.searchTopGroupId = searchTopGroupId;
	}

	public String getSearchTopGroupName() {
		return searchTopGroupName;
	}

	public void setSearchTopGroupName(String searchTopGroupName) {
		this.searchTopGroupName = searchTopGroupName;
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

	public String getTopGroupId() {
		return topGroupId;
	}

	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}

	public String getTopGroupName() {
		return topGroupName;
	}

	public void setTopGroupName(String topGroupName) {
		this.topGroupName = topGroupName;
	}

	public String getTopGroupIntro() {
		return topGroupIntro;
	}

	public void setTopGroupIntro(String topGroupIntro) {
		this.topGroupIntro = topGroupIntro;
	}

	public List<TopGroupDetailBean> getDeptGroupList() {
		return deptGroupList;
	}

	public void setDeptGroupList(List<TopGroupDetailBean> deptGroupList) {
		this.deptGroupList = deptGroupList;
	}

	public String getDeptGroupId() {
		return deptGroupId;
	}

	public void setDeptGroupId(String deptGroupId) {
		this.deptGroupId = deptGroupId;
	}

	public String getDeptGroupName() {
		return deptGroupName;
	}

	public void setDeptGroupName(String deptGroupName) {
		this.deptGroupName = deptGroupName;
	}

	public List<TopGroupDetailBean> getStoreGroupList() {
		return storeGroupList;
	}

	public void setStoreGroupList(List<TopGroupDetailBean> storeGroupList) {
		this.storeGroupList = storeGroupList;
	}

	public String getStoreGroupId() {
		return storeGroupId;
	}

	public void setStoreGroupId(String storeGroupId) {
		this.storeGroupId = storeGroupId;
	}

	public String getStoreGroupName() {
		return storeGroupName;
	}

	public void setStoreGroupName(String storeGroupName) {
		this.storeGroupName = storeGroupName;
	}

	public List<TopGroupDetailBean> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<TopGroupDetailBean> userGroupList) {
		this.userGroupList = userGroupList;
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

	public String getLgRefDate() {
		return lgRefDate;
	}

	public void setLgRefDate(String lgRefDate) {
		this.lgRefDate = lgRefDate;
	}

	public String getSearchSubGroupId() {
		return searchSubGroupId;
	}

	public void setSearchSubGroupId(String searchSubGroupId) {
		this.searchSubGroupId = searchSubGroupId;
	}

	public String getSearchSubGroupFlag() {
		return searchSubGroupFlag;
	}

	public void setSearchSubGroupFlag(String searchSubGroupFlag) {
		this.searchSubGroupFlag = searchSubGroupFlag;
	}

	public String getSearchSubId() {
		return searchSubId;
	}

	public void setSearchSubId(String searchSubId) {
		this.searchSubId = searchSubId;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public String getSearchSubGroupRowNum() {
		return searchSubGroupRowNum;
	}

	public void setSearchSubGroupRowNum(String searchSubGroupRowNum) {
		this.searchSubGroupRowNum = searchSubGroupRowNum;
	}

	public String getPersonalCreateUserId() {
		return personalCreateUserId;
	}

	public void setPersonalCreateUserId(String personalCreateUserId) {
		this.personalCreateUserId = personalCreateUserId;
	}

	public String getSearchPartTimeIncludeFlag() {
		return searchPartTimeIncludeFlag;
	}

	public void setSearchPartTimeIncludeFlag(String searchPartTimeIncludeFlag) {
		this.searchPartTimeIncludeFlag = searchPartTimeIncludeFlag;
	}

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
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

	public String getSearchUserGroupName() {
		return searchUserGroupName;
	}

	public void setSearchUserGroupName(String searchUserGroupName) {
		this.searchUserGroupName = searchUserGroupName;
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