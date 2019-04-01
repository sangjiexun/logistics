package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

public class HotPageDetailBean extends BaseBean {
	
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 2479865448514900978L;
	
	/**
	 * �y�[�WID
	 * */
	private String pageId;
	
	/**
	 * �^�C�g����
	 * */
	private String title;
	
	/**
	 * SUB�y�b�WID
	 * */
	private String hotPageId;
	
	/**
	 * SUB�y�b�W��
	 * */
	private String hotPageTitle;
	
	/**
	 * �z��
	 * */
	private String orderBy;
	
	/**
	 * ���J�J�n���t
	 * */
	private String startDate;
	
	/**
	 * ���J�I�����t
	 * */
	private String endDate;
	
	/**
	 * �f�[�^�\���t���O�i�P�F�\���@�O�F�\�����Ȃ��j
	 * */
	private String showFlag;
	
	/**
	 * �t�@�C��URL(�t�@�C�����ƃA�h���X)
	 * */
	private String htmlFileUrl;
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the hotPageId
	 */
	public String getHotPageId() {
		return hotPageId;
	}

	/**
	 * @param hotPageId the hotPageId to set
	 */
	public void setHotPageId(String hotPageId) {
		this.hotPageId = hotPageId;
	}

	/**
	 * @return the hotPageName
	 */
	public String getHotPageTitle() {
		return hotPageTitle;
	}

	/**
	 * @param hotPageName the hotPageName to set
	 */
	public void setHotPageTitle(String hotPageName) {
		this.hotPageTitle = hotPageName;
	}

	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param orderBy the orderBy to set
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the showFlag
	 */
	public String getShowFlag() {
		return showFlag;
	}

	/**
	 * @param showFlag the showFlag to set
	 */
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	/**
	 * @return the htmlFileUrl
	 */
	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	/**
	 * @param htmlFileUrl the htmlFileUrl to set
	 */
	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

}
