package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c���J����l���Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 �V�K�쐬
 *
 **/
public class AuthUserBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// �y�[�WID
	private String pageId;
	// ���[�UID
	private String userId;
	// �K�{�{���敪
	private String necessityReadFlag;
	// ���[�U����
	private String userName;
	// ���[�U�폜�敪
	private String userDeleteFlag;
	// ���[�U�[���[���t���O
	private String userMailFlag;
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
	public String getNecessityReadFlag() {
		return necessityReadFlag;
	}
	public void setNecessityReadFlag(String necessityReadFlag) {
		this.necessityReadFlag = necessityReadFlag;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDeleteFlag() {
		return userDeleteFlag;
	}
	public void setUserDeleteFlag(String userDeleteFlag) {
		this.userDeleteFlag = userDeleteFlag;
	}
	public String getUserMailFlag() {
		return userMailFlag;
	}
	public void setUserMailFlag(String userMailFlag) {
		this.userMailFlag = userMailFlag;
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