package jp.co.fourseeds.fsnet.beans.page;

import java.util.List;

/**
 * �q�R���e���c�֏����Đݒ�t�H�[��Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 �V�K�쐬
 *
 **/
public class ChildPageFormBean {

	// �y�[�WID�i�e�y�[�WID��\���j
	private String pageId;
	// �u1�v�̏ꍇ�A�q���Onload�ŉ�ʕ���
	private String isClose;
	// �O���[�v�p�����		COVER�F�u�������@ADD�F�ǉ�
	private String groupInheritType;
	// ���[�U�[�p�����		COVER�F�u�������@ADD�F�ǉ�
	private String userInheritType;
	// ���F�Ҍp�����		COVER�F�u�������@ADD�F�ǉ�
	private String proxyInheritType;
	// �R���e���c�{�������O���[�v���(���J����O���[�v)���X�g
	private List<AuthGroupBean> authGroupList;
	// �R���e���c�{���������[�U���(���J����l)���X�g
	private List<AuthUserBean> authUserList;
	// �R���e���c�X�V��s�҃��X�g(���F��)
	private List<ProxyUserBean> proxyUserList;
	// �q�R���e���c�p���ݒ胊�X�g
	private List<ChildPageInheritSetBean> inheritSetList;

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getIsClose() {
		return isClose;
	}
	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}
	public List<AuthGroupBean> getAuthGroupList() {
		return authGroupList;
	}
	public void setAuthGroupList(List<AuthGroupBean> authGroupList) {
		this.authGroupList = authGroupList;
	}
	public List<AuthUserBean> getAuthUserList() {
		return authUserList;
	}
	public void setAuthUserList(List<AuthUserBean> authUserList) {
		this.authUserList = authUserList;
	}
	public List<ProxyUserBean> getProxyUserList() {
		return proxyUserList;
	}
	public void setProxyUserList(List<ProxyUserBean> proxyUserList) {
		this.proxyUserList = proxyUserList;
	}
	public List<ChildPageInheritSetBean> getInheritSetList() {
		return inheritSetList;
	}
	public void setInheritSetList(List<ChildPageInheritSetBean> inheritSetList) {
		this.inheritSetList = inheritSetList;
	}
	public String getGroupInheritType() {
		return groupInheritType;
	}
	public void setGroupInheritType(String groupInheritType) {
		this.groupInheritType = groupInheritType;
	}
	public String getUserInheritType() {
		return userInheritType;
	}
	public void setUserInheritType(String userInheritType) {
		this.userInheritType = userInheritType;
	}
	public String getProxyInheritType() {
		return proxyInheritType;
	}
	public void setProxyInheritType(String proxyInheritType) {
		this.proxyInheritType = proxyInheritType;
	}
}