package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c���J����O���[�v���Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 �V�K�쐬
 *
 **/
public class AuthGroupBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// �y�[�WID
	private String pageId;
	// �g�b�v�O���[�vID
	private String topGroupId;
	// �K�{�{���敪
	private String necessityReadFlag;
	// �g�b�v�O���[�v����
	private String topGroupName;
	// �O���[�v�폜�敪
	private String groupDeleteFlag;
	// �O���[�v��ށu0:�ėp 1:�l�v
	private String topGroupType;
	//�@�l�O���[�v�쐬���[�UID
	private String personalCreateUserid;
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
	public String getTopGroupId() {
		return topGroupId;
	}
	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}
	public String getNecessityReadFlag() {
		return necessityReadFlag;
	}
	public void setNecessityReadFlag(String necessityReadFlag) {
		this.necessityReadFlag = necessityReadFlag;
	}
	public String getTopGroupName() {
		return topGroupName;
	}
	public void setTopGroupName(String topGroupName) {
		this.topGroupName = topGroupName;
	}
	public String getGroupDeleteFlag() {
		return groupDeleteFlag;
	}
	public void setGroupDeleteFlag(String groupDeleteFlag) {
		this.groupDeleteFlag = groupDeleteFlag;
	}
	public String getPersonalCreateUserid() {
		return personalCreateUserid;
	}
	public void setPersonalCreateUserid(String personalCreateUserid) {
		this.personalCreateUserid = personalCreateUserid;
	}
	public String getTopGroupType() {
		return topGroupType;
	}
	public void setTopGroupType(String topGroupType) {
		this.topGroupType = topGroupType;
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