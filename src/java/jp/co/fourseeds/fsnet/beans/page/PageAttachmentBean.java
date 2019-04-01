package jp.co.fourseeds.fsnet.beans.page;

import java.io.File;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c�Y�t�t�@�C�����Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 �V�K�쐬
 *
 **/
public class PageAttachmentBean extends BaseBean {
	
	private static final long serialVersionUID = 1L;

	// SEQUENCE
	private String sequence;
	// �y�[�WID
	private String pageId;
	// �Y�t�t�@�C������
	private String attachmentName;
	// �Y�t�t�@�C��URL(�t�@�C�����ƃA�h���X)
	private String attachmentFileUrl;
	// �Y�t�t�@�C���{��URL(�{�������N)
	private String attachmentPreviewUrl;
	// �z��
	private String orderBy;
	// �Y�t�t�@�C��
	private File attachment;
	// �Y�t�t�@�C���^�C�v 
	private String attachmentContentType;
	// �Y�t�t�@�C������
	private String attachmentFileName;
	// �폜�t���O
	private String fileDeleteFlag;
	// �A�b�v���[�h�t�@�C������
	private String attUploadFileName;
	// �ꎞ�ۑ��A�b�v���[�h�t�@�C������
	private String tempSaveUploadFileName;
	
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
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentFileUrl() {
		return attachmentFileUrl;
	}
	public void setAttachmentFileUrl(String attachmentFileUrl) {
		this.attachmentFileUrl = attachmentFileUrl;
	}
	public String getAttachmentPreviewUrl() {
		return attachmentPreviewUrl;
	}
	public void setAttachmentPreviewUrl(String attachmentPreviewUrl) {
		this.attachmentPreviewUrl = attachmentPreviewUrl;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getFileDeleteFlag() {
		return fileDeleteFlag;
	}
	public void setFileDeleteFlag(String fileDeleteFlag) {
		this.fileDeleteFlag = fileDeleteFlag;
	}
	public File getAttachment() {
		return attachment;
	}
	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}
	public String getAttachmentContentType() {
		return attachmentContentType;
	}
	public void setAttachmentContentType(String attachmentContentType) {
		this.attachmentContentType = attachmentContentType;
	}
	public String getAttachmentFileName() {
		return attachmentFileName;
	}
	public void setAttachmentFileName(String attachmentFileName) {
		this.attachmentFileName = attachmentFileName;
	}
	public String getAttUploadFileName() {
		return attUploadFileName;
	}
	public void setAttUploadFileName(String attUploadFileName) {
		this.attUploadFileName = attUploadFileName;
	}
	public String getTempSaveUploadFileName() {
		return tempSaveUploadFileName;
	}
	public void setTempSaveUploadFileName(String tempSaveUploadFileName) {
		this.tempSaveUploadFileName = tempSaveUploadFileName;
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