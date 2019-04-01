/**
 * �X�܃O���[�vBean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.08 �V�K�쐬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 �������C��
 */

package jp.co.fourseeds.fsnet.beans.storeGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class StoreGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	/** �����X�܃O���[�vid*/
	private String searchStoreGroupId;
	
	/** �����X�܃O���[�v��*/
	private String searchStoreGroupName;
	
	/** �����敪*/
	private String searchOriginType;
	
	/** �����e�[�u���敪�@1:���J�@2:�\�� */
	private String editFlag;
	
	/** �������[�U��*/
	private String searchUserName;
	
	/** ��������Id*/
	private String searchDepartmentId;
	
	/** �����X�ܖ�*/
	private String searchStoreName;
	
	/** �����X��Id*/
	private String searchStoreId;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**������������������������A��������Bean������������������������*/
	/** �X�܃O���[�vID */
	private String storeGroupId;
	
	/** �X�܃O���[�v�� */
	private String storeGroupName;
	
	/** �X�܃O���[�v�Љ� */
	private String storeGroupIntro;
	
	/**���f�\�����*/
	private String sgRefDate;
	
	/** �X��Id From */
	private String conditionStoreIdF;
	
	/** �X��Id To */
	private String conditionStoreIdT;
	
	/** �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\�� */
	private String originType;
	
	/** ���L���[�U���X�g */
	private List<StoreGroupDetailBean> shareUserList;
	
	/** �X�܃��X�g */
	private List<StoreGroupDetailBean> storeList;
	
	/** �X�܃��X�g */
	private List<StoreGroupDetailBean> storeDetailList;
	
	/** �X��ID */
	private String storeId;
	
	/** �X�ܖ��� */
	private String storeName;
	
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
	
	/** �ҏW��ʂ̓X�܋敪���X�g*/
	private List<LabelValueBean> storeDivisionList;
	
	/** �l�p_�ėpFLAG */
	private String grouptype;
	
	/** �X�܋敪Id*/
	private String shareConditionStoreDivisionId;
	
	/** �X�܋敪*/
	private String conditionStoreDivisionName;
	
	/** �X�܋敪FLAG*/
	private String conditionFcFlag;
	
	/** �X�܋敪FLAG:�X�܃��X�g��������*/
	private String conditionFcFlag2;
	
	/** ���L���[�U�� */
	private String shareUserName;
	
	/** ���L���Id*/
	private String shareConditionCompanyId;
	
	/** ���L����Id*/
	private String shareConditionUnificationId;
	
	/** ���L���ƕ�Id*/
	private String shareConditionBusinessId;
	
	/** ���L�c�ƕ�Id*/
	private String shareConditionSalesId;
	
	/** ���Id*/
	private String conditionCompanyId;
	
	/** ����Id*/
	private String conditionUnificationId;
	
	/** ���ƕ�Id*/
	private String conditionBusinessId;
	
	/** �c�ƕ�Id*/
	private String conditionSalesId;
	
	/** �ҏW�\�t���O */
	private String authEditFlag;
	
	/** �쐬�Җ� */
	private String createUserName;
	
	/** �ڍ׉�ʂ̒��ŁA�ҏW�ƍ폜�{�^���\������ */
	private String btnDisplayRight;
	
	/**�C��flag**/
	private String groupFlag;
	
	/** �ҏW��ʂ̎Ј��敪���X�g*/
	private List<LabelValueBean> pemployeeList;
	
	/** �Ј��敪Id*/
	private String conditionPemployeeId;

	/** �Ј��敪Name*/
	private String conditionPemployeeName;
	
	/** LEVEL */
	private int level;

	public String getSearchStoreGroupId() {
		return searchStoreGroupId;
	}

	public void setSearchStoreGroupId(String searchStoreGroupId) {
		this.searchStoreGroupId = searchStoreGroupId;
	}

	public String getSearchStoreGroupName() {
		return searchStoreGroupName;
	}

	public void setSearchStoreGroupName(String searchStoreGroupName) {
		this.searchStoreGroupName = searchStoreGroupName;
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

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
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

	public String getStoreGroupIntro() {
		return storeGroupIntro;
	}

	public void setStoreGroupIntro(String storeGroupIntro) {
		this.storeGroupIntro = storeGroupIntro;
	}

	public String getSgRefDate() {
		return sgRefDate;
	}

	public void setSgRefDate(String sgRefDate) {
		this.sgRefDate = sgRefDate;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
	}

	public List<StoreGroupDetailBean> getShareUserList() {
		return shareUserList;
	}

	public void setShareUserList(List<StoreGroupDetailBean> shareUserList) {
		this.shareUserList = shareUserList;
	}

	public List<StoreGroupDetailBean> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreGroupDetailBean> storeList) {
		this.storeList = storeList;
	}

	public List<StoreGroupDetailBean> getStoreDetailList() {
		return storeDetailList;
	}

	public void setStoreDetailList(List<StoreGroupDetailBean> storeDetailList) {
		this.storeDetailList = storeDetailList;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public List<LabelValueBean> getStoreDivisionList() {
		return storeDivisionList;
	}

	public void setStoreDivisionList(List<LabelValueBean> storeDivisionList) {
		this.storeDivisionList = storeDivisionList;
	}

	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getShareConditionStoreDivisionId() {
		return shareConditionStoreDivisionId;
	}

	public void setShareConditionStoreDivisionId(String shareConditionStoreDivisionId) {
		this.shareConditionStoreDivisionId = shareConditionStoreDivisionId;
	}

	public String getConditionStoreDivisionName() {
		return conditionStoreDivisionName;
	}

	public void setConditionStoreDivisionName(String conditionStoreDivisionName) {
		this.conditionStoreDivisionName = conditionStoreDivisionName;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
	}

	public String getSearchStoreName() {
		return searchStoreName;
	}

	public void setSearchStoreName(String searchStoreName) {
		this.searchStoreName = searchStoreName;
	}

	public String getSearchStoreId() {
		return searchStoreId;
	}

	public void setSearchStoreId(String searchStoreId) {
		this.searchStoreId = searchStoreId;
	}

	public String getConditionFcFlag() {
		return conditionFcFlag;
	}

	public void setConditionFcFlag(String conditionFcFlag) {
		this.conditionFcFlag = conditionFcFlag;
	}

	public String getConditionFcFlag2() {
		return conditionFcFlag2;
	}

	public void setConditionFcFlag2(String conditionFcFlag2) {
		this.conditionFcFlag2 = conditionFcFlag2;
	}

	public String getConditionStoreIdF() {
		return conditionStoreIdF;
	}

	public void setConditionStoreIdF(String conditionStoreIdF) {
		this.conditionStoreIdF = conditionStoreIdF;
	}

	public String getConditionStoreIdT() {
		return conditionStoreIdT;
	}

	public void setConditionStoreIdT(String conditionStoreIdT) {
		this.conditionStoreIdT = conditionStoreIdT;
	}

	public String getShareUserName() {
		return shareUserName;
	}

	public void setShareUserName(String shareUserName) {
		this.shareUserName = shareUserName;
	}

	public String getShareConditionCompanyId() {
		return shareConditionCompanyId;
	}

	public void setShareConditionCompanyId(String shareConditionCompanyId) {
		this.shareConditionCompanyId = shareConditionCompanyId;
	}

	public String getShareConditionUnificationId() {
		return shareConditionUnificationId;
	}

	public void setShareConditionUnificationId(String shareConditionUnificationId) {
		this.shareConditionUnificationId = shareConditionUnificationId;
	}

	public String getShareConditionBusinessId() {
		return shareConditionBusinessId;
	}

	public void setShareConditionBusinessId(String shareConditionBusinessId) {
		this.shareConditionBusinessId = shareConditionBusinessId;
	}

	public String getShareConditionSalesId() {
		return shareConditionSalesId;
	}

	public void setShareConditionSalesId(String shareConditionSalesId) {
		this.shareConditionSalesId = shareConditionSalesId;
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
	
	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
	}

	public String getConditionPemployeeName() {
		return conditionPemployeeName;
	}

	public void setConditionPemployeeName(String conditionPemployeeName) {
		this.conditionPemployeeName = conditionPemployeeName;
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
	
}