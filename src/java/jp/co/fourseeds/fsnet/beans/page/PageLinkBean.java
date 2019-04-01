package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c�����N���Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/30 �V�K�쐬
 *
 **/
public class PageLinkBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// SEQUENCE
	private String sequence;
	// �y�[�WID
	private String pageId;
	// �����N�L�[���[�h
	private String linkName;
	// �����NURL
	private String linkUrl;
	// �����N�폜
	private String linkDeleteFlag;
	// ���������������������������������������ȉ��̓R���e���c�폜�p�J�n
	// ���M�t���O�i�R���e���c�폜�p�j
	private boolean sendFlag;
	// ���J�J�n���t
	private String startDate;
	// ���J�I�����t
	private String endDate;
	// �^�C�g����
	private String title;
	private String createUserName;
	private String updateUserName;
	private String pageLocation;
	private String dateFlag;
	// ���������������������������������������ȉ��̓R���e���c�폜�p�I��
	
	// ���������������������������������������ȉ��̓e���v���[�g�p�J�n
	// �e���v���[�g�y�[�WID
	private String templatePageId;
	// �e���v���[�g�쐬�҃��[�UID
	private String templateCreateBy;
	
	// ���������������������������������������ȉ��̓e���v���[�g�p�I��
	
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkDeleteFlag() {
		return linkDeleteFlag;
	}
	public void setLinkDeleteFlag(String linkDeleteFlag) {
		this.linkDeleteFlag = linkDeleteFlag;
	}
	public boolean isSendFlag() {
		return sendFlag;
	}
	public void setSendFlag(boolean sendFlag) {
		this.sendFlag = sendFlag;
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
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getDateFlag() {
		return dateFlag;
	}
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
	public String getTemplatePageId() {
		return templatePageId;
	}
	public void setTemplatePageId(String templatePageId) {
		this.templatePageId = templatePageId;
	}
	public String getTemplateCreateBy() {
		return templateCreateBy;
	}
	public void setTemplateCreateBy(String templateCreateBy) {
		this.templateCreateBy = templateCreateBy;
	}
	
}