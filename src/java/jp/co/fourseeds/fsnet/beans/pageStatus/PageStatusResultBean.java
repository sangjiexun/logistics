/**
 * File Name	: UserSearchResult.java
 * Created Date	: 2007/04/06
 * COPYRIGHT(c)	: DHC
 */

package jp.co.fourseeds.fsnet.beans.pageStatus;

import jp.co.common.frame.beans.BaseBean;

public class PageStatusResultBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**�y�[�WID*/
	private String pageId = "";
	
	/**�e�y�[�WID*/
	private String pPageId = "";
	
	/**broths���X�g��p*/
	private String tail = "";

	/**�^�C�g����*/
	private String title = "";
	
	/** ����J�t���O */
	private String confirmFlag = "";
	
	/** �������J�J�n�t���O */
	private String futureFlag = "";
	
	/** �L�������؂�t���O */
	private String termFlag = "";
	
	/** �����N���ݒ�������̓����N��R���e���c�폜�ϔ���t���O */
	private String pageLinkFlag = "";
	
	/** ���J�����܂ܕҏW����t���O */
	private String reserveFlag = "";
	
	/** �w�胆�[�U���Y���R���e���c���{���ł��邩����t���O */
	private String inspFlag = "";
	
	/** �y�[�WKEY */
	private String pageKey = "";
	
	/** �쐬�� */
	private String createUserName = "";
	
	/** �ŏI�X�V�� */
	private String updateDateStr = "";
	
	/** �ŏI�X�V�� */
	private String updateUserName = "";
	
	/** ���J�I���� */
	private String openEndDate = "";
	
	/** �g�b�v�O���[�vID */
	private String topGroupId = "";
	
	/** �g�b�v�O���[�v�� */
	private String topGroupName = "";
	
	/** �T�u�O���[�v�h�c */
	private String subGroupId = "";
	
	/** �T�u�O���[�v���� */
	private String subGroupName = "";
	
	/** �T�u�O���[�v�敪*/ 
	private String subGroupFlag = "";
	
	/** �O���[�v�敪*/ 
	private String groupFlag = "";
	
	/** �Ј��ԍ� */
	private String userId = "";
	
	/** �Ј��� */
	private String userName = "";
	
	/** �K�{�{���敪 */
	private String necessityReadFlag = "";
	
	/** ����ID */
	private String departmentId = "";
	
	/** ���喼�� */
	private String departmentName = "";
	
	/** ���F��ID */
	private String authorizerId = "";
	
	/** ���F�� */
	private String authorizer = "";
	
	/** �L�[���[�h */
	private String linkName = "";
	
	/** �����N��^�C�g�� */
	private String linkTitle = "";
	
	/** �����N */
	private String linkUrl = "";
	
	/** �Y�t�t�@�C���� */
	private String attachmentName = "";
	
	/** ���z�u�� */
	private String realLocationPath = "";
	
	private String csvPath = "";
	
	public boolean equals(Object obj) {   
		if (obj instanceof PageStatusResultBean) {   
			PageStatusResultBean page = (PageStatusResultBean) obj;   
			return this.pageId.equals(page.getPageId()) && this.pPageId.equals(page.getpPageId()) && this.title.equals(page.getTitle())
					 && this.termFlag.equals(page.getTermFlag()) && this.pageLinkFlag.equals(page.getPageLinkFlag()) && this.confirmFlag.equals(page.getConfirmFlag())
					 && this.futureFlag.equals(page.getFutureFlag()) && this.reserveFlag.equals(page.getReserveFlag()) && this.inspFlag.equals(page.getInspFlag());
		}
		return super.equals(obj); 
	}
	
	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getpPageId() {
		return pPageId;
	}

	public void setpPageId(String pPageId) {
		this.pPageId = pPageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public String getFutureFlag() {
		return futureFlag;
	}

	public void setFutureFlag(String futureFlag) {
		this.futureFlag = futureFlag;
	}

	public String getTermFlag() {
		return termFlag;
	}

	public void setTermFlag(String termFlag) {
		this.termFlag = termFlag;
	}

	public String getPageLinkFlag() {
		return pageLinkFlag;
	}

	public void setPageLinkFlag(String pageLinkFlag) {
		this.pageLinkFlag = pageLinkFlag;
	}

	public String getReserveFlag() {
		return reserveFlag;
	}

	public void setReserveFlag(String reserveFlag) {
		this.reserveFlag = reserveFlag;
	}

	public String getInspFlag() {
		return inspFlag;
	}

	public void setInspFlag(String inspFlag) {
		this.inspFlag = inspFlag;
	}

	public String getTail() {
		return tail;
	}

	public void setTail(String tail) {
		this.tail = tail;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getTopGroupId() {
		return topGroupId;
	}

	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}

	public String getTopGroupName() {
		return topGroupName;
	}

	public void setTopGroupName(String topGroupName) {
		this.topGroupName = topGroupName;
	}

	public String getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}

	public String getSubGroupFlag() {
		return subGroupFlag;
	}

	public void setSubGroupFlag(String subGroupFlag) {
		this.subGroupFlag = subGroupFlag;
	}

	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
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

	public String getNecessityReadFlag() {
		return necessityReadFlag;
	}

	public void setNecessityReadFlag(String necessityReadFlag) {
		this.necessityReadFlag = necessityReadFlag;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAuthorizerId() {
		return authorizerId;
	}

	public void setAuthorizerId(String authorizerId) {
		this.authorizerId = authorizerId;
	}

	public String getAuthorizer() {
		return authorizer;
	}

	public void setAuthorizer(String authorizer) {
		this.authorizer = authorizer;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getRealLocationPath() {
		return realLocationPath;
	}

	public void setRealLocationPath(String realLocationPath) {
		this.realLocationPath = realLocationPath;
	}

	public String getOpenEndDate() {
		return openEndDate;
	}

	public void setOpenEndDate(String openEndDate) {
		this.openEndDate = openEndDate;
	}

	public String getCsvPath() {
		return csvPath;
	}

	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}
	
}