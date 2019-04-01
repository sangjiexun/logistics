package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 * @author shangw
 * @version 1.0
 * @function The form bean for user search page.
 */
public class ContentSearchResultBean extends BaseBean {

	/** The Field serialVersionUID */
	private static final long serialVersionUID = 2071133075509114905L;

	/** �y�[�WID */
	private String pageId = null;

	/** ���J�J�n���t */
	private String startDate = null;

	/** ���J�I�����t */
	private String endDate = null;

	/** �^�C�g���� */
	private String title = null;

	/** �t�@�C��URL(�t�@�C�����ƃA�h���X) */
	private String htmlFilePath = null;

	/** ���喼�� */
	private String departmentName = null;

	/** �Ώۖ��� */
	private String userName = null;

	/** �X�ܖ��� */
	private String storeName = null;

	/** �X���敪 */
	private String stManage = null;

	/** �{���� */
	private String readedDate = null;

	/** ���ǐ� */
	private String notReadedCount = null;

	/** The Field mailSendFlag */
	private String mailSendFlag = null;

	/** The Field userId */
	private String userId = null;
	/** �_�E�����[�h */
	private String downloadFlag = null;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the mailSendFlag
	 */
	public String getMailSendFlag() {
		return mailSendFlag;
	}

	/**
	 * @param mailSendFlag
	 *            the mailSendFlag to set
	 */
	public void setMailSendFlag(String mailSendFlag) {
		this.mailSendFlag = mailSendFlag;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the notReadedCount
	 */
	public String getNotReadedCount() {
		return notReadedCount;
	}

	/**
	 * @param notReadedCount
	 *            the notReadedCount to set
	 */
	public void setNotReadedCount(String notReadedCount) {
		this.notReadedCount = notReadedCount;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId
	 *            the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the readedDate
	 */
	public String getReadedDate() {
		return readedDate;
	}

	/**
	 * @param readedDate
	 *            the readedDate to set
	 */
	public void setReadedDate(String readedDate) {
		this.readedDate = readedDate;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the stManage
	 */
	public String getStManage() {
		return stManage;
	}

	/**
	 * @param stManage
	 *            the stManage to set
	 */
	public void setStManage(String stManage) {
		this.stManage = stManage;
	}

	/**
	 * @return the storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName
	 *            the storeName to set
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the htmlFilePath
	 */
	public String getHtmlFilePath() {
		return htmlFilePath;
	}

	/**
	 * @param htmlFilePath
	 *            the htmlFilePath to set
	 */
	public void setHtmlFilePath(String htmlFilePath) {
		this.htmlFilePath = htmlFilePath;
	}
	/**
	 * @return the downloadFlag
	 */
	public String getDownloadFlag() {
		return downloadFlag;
	}
	/**
	 * @param downloadFlag
	 *            the downloadFlag to set
	 */
	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

}
