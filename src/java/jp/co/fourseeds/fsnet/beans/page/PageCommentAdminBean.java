package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c�R�����g�Ǘ��ҏ��Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/02 �V�K�쐬
 *
 **/
public class PageCommentAdminBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// �y�[�WID
	private String pageId;
	// ���[�UID
	private String userId;
	// ���[�U����
	private String userName;
	
	// ���������������������������������������ȉ��̓e���v���[�g�p�J�n
	// �e���v���[�g�y�[�WID
	private String templatePageId;
	// �e���v���[�g�쐬�҃��[�UID
	private String templateCreateBy;
	
	// ���������������������������������������ȉ��̓e���v���[�g�p�I��
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
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