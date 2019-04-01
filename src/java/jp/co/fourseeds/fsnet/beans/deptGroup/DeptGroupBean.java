/**
 * �����O���[�vBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.25 �V�K�쐬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 �������C��
 */

package jp.co.fourseeds.fsnet.beans.deptGroup;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class DeptGroupBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	/** ���������O���[�vid*/
	private String searchDeptGroupId;
	
	/** ���������O���[�v��*/
	private String searchDeptGroupName;
	
	/** �����敪*/
	private String searchOriginType;
	
	/** �����e�[�u���敪�@1:���J�@2:�\�� */
	private String editFlag;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**������������������������A��������Bean������������������������*/
	/** �����O���[�vID */
	private String deptGroupId;
	
	/** �����O���[�v�� */
	private String deptGroupName;
	
	/** �����O���[�v�Љ� */
	private String deptGroupIntro;
	
	/**���f�\�����*/
	private String dgRefDate;
	
	/** �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\�� */
	private String originType;
	
	/** ���僊�X�g */
	private List<DeptGroupDetailBean> departmentList;
	
	/** ���僊�X�g */
	private List<DeptGroupDetailBean> departmentDetailList;
	
	/** ����ID */
	private String departmentId;
	
	/** ���喼�� */
	private String departmentName;
	
	/** SS����ID */
	private String searchDepartmentId;

	/** SS���喼�� */
	private String searchDepartmentName;
	
	/** ���Id*/
	private String conditionCompanyId;
	
	/** ����Id*/
	private String conditionUnificationId;
	
	/** ���ƕ�Id*/
	private String conditionBusinessId;
	
	/** �ҏW��ʂ̉�Ѓ��X�g*/
	private List<LabelValueBean> companyList;
	
	/** �ҏW��ʂ̓������X�g*/
	private List<LabelValueBean> unificationList;
	
	/** �ҏW��ʂ̎��ƕ����X�g*/
	private List<LabelValueBean> businessList;

	/** �ҏW��ʂ̎Ј��敪���X�g*/
	private List<LabelValueBean> pemployeeList;

	/** �Ј��敪Id*/
	private String conditionPemployeeId;

	/** �Ј��敪Name*/
	private String conditionPemployeeName;

	/** VALUE */
	private String value;
	
	/** LEVEL */
	private int level;
	
	/**�C��flag**/
	private String groupFlag;
	
	public String getSearchDeptGroupId() {
		return searchDeptGroupId;
	}

	public void setSearchDeptGroupId(String searchDeptGroupId) {
		this.searchDeptGroupId = searchDeptGroupId;
	}

	public String getSearchDeptGroupName() {
		return searchDeptGroupName;
	}

	public void setSearchDeptGroupName(String searchDeptGroupName) {
		this.searchDeptGroupName = searchDeptGroupName;
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

	public String getDeptGroupIntro() {
		return deptGroupIntro;
	}

	public void setDeptGroupIntro(String deptGroupIntro) {
		this.deptGroupIntro = deptGroupIntro;
	}

	public String getDgRefDate() {
		return dgRefDate;
	}

	public void setDgRefDate(String dgRefDate) {
		this.dgRefDate = dgRefDate;
	}

	public List<DeptGroupDetailBean> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<DeptGroupDetailBean> departmentList) {
		this.departmentList = departmentList;
	}

	public List<DeptGroupDetailBean> getDepartmentDetailList() {
		return departmentDetailList;
	}

	public void setDepartmentDetailList(List<DeptGroupDetailBean> departmentDetailList) {
		this.departmentDetailList = departmentDetailList;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getOriginType() {
		return originType;
	}

	public void setOriginType(String originType) {
		this.originType = originType;
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

	public String getSearchDepartmentId() {
		return searchDepartmentId;
	}

	public void setSearchDepartmentId(String searchDepartmentId) {
		this.searchDepartmentId = searchDepartmentId;
	}

	public String getSearchDepartmentName() {
		return searchDepartmentName;
	}

	public void setSearchDepartmentName(String searchDepartmentName) {
		this.searchDepartmentName = searchDepartmentName;
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

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}
