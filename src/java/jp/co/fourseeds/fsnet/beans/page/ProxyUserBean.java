package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c���F�ҏ��Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/03 �V�K�쐬
 *
 **/
public class ProxyUserBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// SEQUENCE
	private String sequence;
	// �y�[�WID
	private String pageId;
	// �X�V��s���[�UID
	private String proxyUserId;
	// �X�V��s���[�U����
	private String proxyUserName;
	// �X�V��s���[�U���[���t���O
	private String proxyUserMailFlag;
	// �폜�敪
	private String proxyDeleteFlag;
	
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
	public String getProxyUserId() {
		return proxyUserId;
	}
	public void setProxyUserId(String proxyUserId) {
		this.proxyUserId = proxyUserId;
	}
	public String getProxyUserName() {
		return proxyUserName;
	}
	public void setProxyUserName(String proxyUserName) {
		this.proxyUserName = proxyUserName;
	}
	public String getProxyDeleteFlag() {
		return proxyDeleteFlag;
	}
	public void setProxyDeleteFlag(String proxyDeleteFlag) {
		this.proxyDeleteFlag = proxyDeleteFlag;
	}
	public String getProxyUserMailFlag() {
		return proxyUserMailFlag;
	}
	public void setProxyUserMailFlag(String proxyUserMailFlag) {
		this.proxyUserMailFlag = proxyUserMailFlag;
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
