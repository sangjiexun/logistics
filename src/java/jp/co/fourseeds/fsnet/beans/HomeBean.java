package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 * @author NTS
 * @version 1.0.0 : 2015.11.24 �V�K�쐬
 */
public class HomeBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ���������Ώێ� */
	private String searchUserDivision;
	
	/** �Ώێ� */
	private String userDivision;
	
	/** �d�v�Ȃ��m�点�\���J�n�� */
	private String necessityDisplayStartDate;
	
	/** ��*/
	private String readStatus;
	
	/** �^�C�g�� */
	private String title;
	
	/** ���M���� */
	private String departmentName;
	
	/** ���J�� */
	private String confirmDate;
	
	/** �V�K/�X�V���*/
	private String newOldLable;
	
	/** �y�[�WID */
	private String pageId;
	
	/** �t�@�C��URL(�t�@�C�����ƃA�h���X) */
	private String htmlFileUrl;

	public String getSearchUserDivision() {
		return searchUserDivision;
	}

	public void setSearchUserDivision(String searchUserDivision) {
		this.searchUserDivision = searchUserDivision;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getNewOldLable() {
		return newOldLable;
	}

	public void setNewOldLable(String newOldLable) {
		this.newOldLable = newOldLable;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}
}
