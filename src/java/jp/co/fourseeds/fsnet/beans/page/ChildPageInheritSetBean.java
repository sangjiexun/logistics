package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �q�R���e���c�֏����Đݒ�t�H�[��Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 �V�K�쐬
 *
 **/
public class ChildPageInheritSetBean extends BaseBean{

	private static final long serialVersionUID = 1L;

	// �y�[�WID
	private String pageId;
	// �^�C�g����
	private String title;
	// �O���[�v�p��
	private String isGroupInherit;
	// ���[�U�p��
	private String isUserInherit;
	// ���F�Ҍp��
	private String isProxyInherit;
	// �R���e���c�敪(0:�^���̂݁A1:�\��̂݁A2:����)-----�q�R���e���c���擾�Ŏg�p
	private String pageDivision;

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsGroupInherit() {
		return isGroupInherit;
	}
	public void setIsGroupInherit(String isGroupInherit) {
		this.isGroupInherit = isGroupInherit;
	}
	public String getIsUserInherit() {
		return isUserInherit;
	}
	public void setIsUserInherit(String isUserInherit) {
		this.isUserInherit = isUserInherit;
	}
	public String getIsProxyInherit() {
		return isProxyInherit;
	}
	public void setIsProxyInherit(String isProxyInherit) {
		this.isProxyInherit = isProxyInherit;
	}
	public String getPageDivision() {
		return pageDivision;
	}
	public void setPageDivision(String pageDivision) {
		this.pageDivision = pageDivision;
	}
}