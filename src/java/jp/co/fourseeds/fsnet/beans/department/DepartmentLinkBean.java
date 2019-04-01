package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentLinkBean extends BaseBean {
	private static final long serialVersionUID = 1122334455987654338L;
	/** ����ID */
	private String departmentId;

	/** �����N���� */
	private String linkName;

	/** �����NURL */
	private String linkUrl;

	/** �����N�R���e���c */
	private String linkContent;

	/** �����N(�\���p) */
	private String link;
	
	private String chkUrlDelFlag;
	
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
	 * @return linkContent
	 */
	public String getLinkContent() {
		return linkContent;
	}

	/**
	 * @param linkContent
	 *            �ݒ肷�� linkContent
	 */
	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}

	/**
	 * @return linkName
	 */
	public String getLinkName() {
		return linkName;
	}

	/**
	 * @param linkName
	 *            �ݒ肷�� linkName
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * @return linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * @param linkUrl
	 *            �ݒ肷�� linkUrl
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link �ݒ肷�� link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	public String getChkUrlDelFlag() {
		return chkUrlDelFlag;
	}

	public void setChkUrlDelFlag(String chkUrlDelFlag) {
		this.chkUrlDelFlag = chkUrlDelFlag;
	}

	
}
