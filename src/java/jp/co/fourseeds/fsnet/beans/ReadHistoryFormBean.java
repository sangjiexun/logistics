package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 * �{���������FormBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.18 �V�K�쐬
 */
public class ReadHistoryFormBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	/** �����{���J�n�� */
	private String searchStartDate;
	
	/** �����{���I����*/
	private String searchEndDate;
	
	/** �����^�C�g�� */
	private String searchTitle;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**������������������������A��������Bean������������������������*/
	/** �y�[�WID */
	private String pageId;
	
	/** �{���J�n�� */
	private String startDate;
	
	/** �{���I���� */
	private String endDate;
	
	/** �^�C�g�� */
	private String title;
	
	/** �t�@�C��URL(�t�@�C�����ƃA�h���X) */
	private String htmlFileUrl;
	
	/** �{���t���O�F���ǎ҂̂�/�S�� */
	private String readFlag;
	
	/** �Ώێ�ID */
	private String userId;
	
	/** �ΏێҖ� */
	private String userName;
	
	/** ���� */
	private String belong;
	
	/** �g�D�敪 */
	private String original;

	/** �{���� */
	private String readedDate;
	
	/** �_�E�����[�h�t���O */
	private String downloadFlag;
	
	/** ���ۃt���O */
	private String mailSendFlag;
	
	/** ���ǎҐ� */
	private String unReadCount;
	
	/** �Ώۃy�[�WURL */
	private String linkUrl;
	
	/** ���[�����M���O */
	private String sendMailLog;
	
	/** ���[���A�h���X */
	private String mailAddress;
	
	/** �]���惁�[���A�h���X */
	private String trmailAddress;
	
	/** ���[���\�� */
	private String mailTitle;
	
	/** ���[�����e */
	private String mailContent;

	public String getSearchStartDate() {
		return searchStartDate;
	}

	public void setSearchStartDate(String searchStartDate) {
		this.searchStartDate = searchStartDate;
	}

	public String getSearchEndDate() {
		return searchEndDate;
	}

	public void setSearchEndDate(String searchEndDate) {
		this.searchEndDate = searchEndDate;
	}

	public String getSearchTitle() {
		return searchTitle;
	}

	public void setSearchTitle(String searchTitle) {
		this.searchTitle = searchTitle;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

	public String getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(String readFlag) {
		this.readFlag = readFlag;
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
	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getReadedDate() {
		return readedDate;
	}

	public void setReadedDate(String readedDate) {
		this.readedDate = readedDate;
	}

	public String getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public String getMailSendFlag() {
		return mailSendFlag;
	}

	public void setMailSendFlag(String mailSendFlag) {
		this.mailSendFlag = mailSendFlag;
	}

	public String getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(String unReadCount) {
		this.unReadCount = unReadCount;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getSendMailLog() {
		return sendMailLog;
	}

	public void setSendMailLog(String sendMailLog) {
		this.sendMailLog = sendMailLog;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getTrmailAddress() {
		return trmailAddress;
	}

	public void setTrmailAddress(String trmailAddress) {
		this.trmailAddress = trmailAddress;
	}

	public String getMailTitle() {
		return mailTitle;
	}

	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}

	public String getMailContent() {
		return mailContent;
	}

	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
}
