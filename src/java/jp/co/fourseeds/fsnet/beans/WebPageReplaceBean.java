package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

public class WebPageReplaceBean extends BaseBean {

	private static final long serialVersionUID = 4388234923404188955L;
	
	/**������������������������A����Bean��������������������������������*/
	/** �����Ј��ԍ�*/
	private String searchUserId;
	
	/** ��������*/
	private String searchUserName;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**������������������������A��������Bean������������������������*/
	
	/** �����Ј��ԍ�*/
	private String replaceSearchUserId;
	
	/** �y�[�WID*/
	private String pageId;
	
	/** �^�C�g����*/
	private String title;
	
	/** ���J�J�n���t*/
	private String startDate;
	
	/** �Ώێ�*/
	private String userDivision;
	
	/** ���M����*/
	private String sourceDepartment;
	
	/** �\��R���e���c*/
	private String reserveFlag;
	
	/** �t�@�C��URL(�t�@�C�����ƃA�h���X) */
	private String htmlFileUrl;
	
	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
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

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getSourceDepartment() {
		return sourceDepartment;
	}

	public void setSourceDepartment(String sourceDepartment) {
		this.sourceDepartment = sourceDepartment;
	}

	public String getReserveFlag() {
		return reserveFlag;
	}

	public void setReserveFlag(String reserveFlag) {
		this.reserveFlag = reserveFlag;
	}

	public String getReplaceSearchUserId() {
		return replaceSearchUserId;
	}

	public void setReplaceSearchUserId(String replaceSearchUserId) {
		this.replaceSearchUserId = replaceSearchUserId;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}
	
}
