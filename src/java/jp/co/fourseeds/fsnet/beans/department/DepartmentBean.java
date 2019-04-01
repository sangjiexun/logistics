/**
 * File Name	: UserSearchResult.java
 * Created Date	: 2007/04/06
 * COPYRIGHT(c)	: DHC
 */

package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ����ID */
	private String departmentId;

	/** �e����ID */
	private String pDepartmentId;
	
	/** ���喼�� */
	private String departmentName;

	/** ����Љ� */
	private String departmentCont;

	/** ����L���I�����t */
	private String departmentEx;
	
	/** ����L���J�n���t */
	private String departmentSet;

	/** TEL */
	private String tel;

	/** FAX */
	private String fax;

	/** ��\���� */
	private String representativeNaisen;
	
	/** ORDER_BY */
	private String orderBy;
	
	/**�\���t���O�̃����e�i���X*/
	private String showFlag;
	
	/**���M����t���O�̃����e�i���X*/
	private String sourceDeptFlag;
	
	/** �e���喼�� */
	private String pDepartmentName;
	
	/** ��ЃR�[�h */
	private String companyId;
	
	/** ��Ж� */
	private String companyName;
	
	/** �����R�[�h */
	private String unificationId;
	
	/** ������ */
	private String unificationName;

	/** ���ƃR�[�h */
	private String businessId;

	/** ���Ɩ� */
	private String businessName;

	/** �c�ƕ��R�[�h */
	private String salesId;

	/** �c�ƕ��� */
	private String salesName;
	
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getSourceDeptFlag() {
		return sourceDeptFlag;
	}

	public void setSourceDeptFlag(String sourceDeptFlag) {
		this.sourceDeptFlag = sourceDeptFlag;
	}
	
	/**
	 * @return orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy �ݒ肷�� orderBy
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return departmentCont
	 */
	public String getDepartmentCont() {
		return departmentCont;
	}

	/**
	 * @param departmentCont
	 *            �ݒ肷�� departmentCont
	 */
	public void setDepartmentCont(String departmentCont) {
		this.departmentCont = departmentCont;
	}

	/**
	 * @return departmentEx
	 */
	public String getDepartmentEx() {
		return departmentEx;
	}

	/**
	 * @param departmentEx
	 *            �ݒ肷�� departmentEx
	 */
	public void setDepartmentEx(String departmentEx) {
		this.departmentEx = departmentEx;
	}

	/**
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            �ݒ肷�� departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            �ݒ肷�� departmentName
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            �ݒ肷�� fax
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return representativeNaisen
	 */
	public String getRepresentativeNaisen() {
		return representativeNaisen;
	}

	/**
	 * @param representativeNaisen
	 *            �ݒ肷�� representativeNaisen
	 */
	public void setRepresentativeNaisen(String representativeNaisen) {
		this.representativeNaisen = representativeNaisen;
	}

	/**
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel
	 *            �ݒ肷�� tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return pDepartmentId
	 */
	public String getPDepartmentId() {
		return pDepartmentId;
	}

	/**
	 * @param departmentId �ݒ肷�� pDepartmentId
	 */
	public void setPDepartmentId(String departmentId) {
		pDepartmentId = departmentId;
	}
	/**
	 * @return departmentSet
	 */
	public String getDepartmentSet() {
		return departmentSet;
	}
	/**
	 * @param departmentSet �ݒ肷�� departmentSet
	 */
	public void setDepartmentSet(String departmentSet) {
		this.departmentSet = departmentSet;
	}

	public String getpDepartmentId() {
		return pDepartmentId;
	}

	public void setpDepartmentId(String pDepartmentId) {
		this.pDepartmentId = pDepartmentId;
	}

	public String getPDepartmentName() {
		return pDepartmentName;
	}

	public void setPDepartmentName(String pDepartmentName) {
		this.pDepartmentName = pDepartmentName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnificationId() {
		return unificationId;
	}

	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}

	public String getUnificationName() {
		return unificationName;
	}

	public void setUnificationName(String unificationName) {
		this.unificationName = unificationName;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}
	
}
