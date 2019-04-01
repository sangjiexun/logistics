package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c���Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/25 �V�K�쐬
 *
 **/
public class PageBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	// �y�[�WID
	private String pageId;
	// �e�y�[�WID
	private String pPageId;
	// �^�C�g����
	private String title;
	// ���J�J�n���t
	private String startDate;
	// ���J�I�����t
	private String endDate;
	// ���J�J�n����t���O
	private String startdateOpenFlag;
	// �t�@�C��URL(�t�@�C�����ƃA�h���X)
	private String htmlFileUrl;
	// �\�����J�t���O
	private String reserveConfirmFlag;
	// �\����J���
	private String reserveConfirmDate;
	// ����J�t���O
	private String confirmFlag;
	// ���J���
	private String confirmDate;
	// ������J���
	private String firstConfirmDate;
	// �������J����
	private String openDate;
	// �������J��ID
	private String openUser;
	// �\���t���O
	private String displayFlag;
	// �����N�t���O
	private String linkFlag;
	// ��ʃt���O
	private String htmlFlag;
	// �z��
	private String orderBy;
	// �t�@�C���g���q
	private String fileSuffix;
	// �_�E�����[�h�s�t���O
	private String denyDownloadFlag;
	// ���������N�\���s�t���O
	private String denyLinkfileFlag;
	// �_�E�����[�h�p�X���[�h
	private String downloadPassword;
	// �y�[�WKEY
	private String pageKey;
	// �V������\���t���O
	private String newUndisplayFlag;
	// ���J����t��X�V�t���O
	private String confirmNoupdateFlag;
	// �V�K�\���ێ��t���O
	private String newKeepFlag;
	// ���J��
	private String openNum;
	// ���J�ێ��ҏW�t���O
	private String onEditFlag;
	// �Ώێ�
	private String userDivision;	
	// ���M����
	private String sourceDepartment;
	// �R���e���c�]���t���O
	private String evaluationFlag;
	// �]���Ҏ����\���t���O
	private String evaluatorDisplayFlag;
	// �O����J�]���Ҏ����\���t���O
	private String prevEvaluatorDisplayFlag;
	// �]���҃R�����g�ҏW�t���O
	private String commentEditFlag;
	// �������ڕ]���t���O
	private String pluralEvaluationFlag;
	// �O�񕡐����ڕ]���t���O
	private String prevPluralEvaluationFlag;
	// �]�����N���A����t���O
	private String evaluationClearFlag;
	// �R���e���c
	private String content;

	// ���������������������������������������e���v���[�g�p�J�n��������������������������������������������
	// �e���v���[�g�y�[�WID
	private String templatePageId;
	// �e���v���[�g�쐬��ID
	private String templateCreateBy;
	// ���������������������������������������e���v���[�g�p�I����������������������������������������������
	
	// �ȍ~�̕ϐ���TBL�ɑ��݂��Ă��Ȃ�
	// �R���e���c�敪(0:�^���̂݁A1:�\��̂݁A2:����)-----�q�R���e���c���擾�Ŏg�p
	private String pageDivision;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getPPageId() {
		return pPageId;
	}

	public void setPPageId(String pPageId) {
		this.pPageId = pPageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getStartdateOpenFlag() {
		return startdateOpenFlag;
	}

	public void setStartdateOpenFlag(String startdateOpenFlag) {
		this.startdateOpenFlag = startdateOpenFlag;
	}

	public String getHtmlFileUrl() {
		return htmlFileUrl;
	}

	public void setHtmlFileUrl(String htmlFileUrl) {
		this.htmlFileUrl = htmlFileUrl;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getFirstConfirmDate() {
		return firstConfirmDate;
	}

	public void setFirstConfirmDate(String firstConfirmDate) {
		this.firstConfirmDate = firstConfirmDate;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getOpenUser() {
		return openUser;
	}

	public void setOpenUser(String openUser) {
		this.openUser = openUser;
	}

	public String getDisplayFlag() {
		return displayFlag;
	}

	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}

	public String getLinkFlag() {
		return linkFlag;
	}

	public void setLinkFlag(String linkFlag) {
		this.linkFlag = linkFlag;
	}

	public String getHtmlFlag() {
		return htmlFlag;
	}

	public void setHtmlFlag(String htmlFlag) {
		this.htmlFlag = htmlFlag;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getDenyDownloadFlag() {
		return denyDownloadFlag;
	}

	public void setDenyDownloadFlag(String denyDownloadFlag) {
		this.denyDownloadFlag = denyDownloadFlag;
	}

	public String getDenyLinkfileFlag() {
		return denyLinkfileFlag;
	}

	public void setDenyLinkfileFlag(String denyLinkfileFlag) {
		this.denyLinkfileFlag = denyLinkfileFlag;
	}

	public String getDownloadPassword() {
		return downloadPassword;
	}

	public void setDownloadPassword(String downloadPassword) {
		this.downloadPassword = downloadPassword;
	}

	public String getPageKey() {
		return pageKey;
	}

	public void setPageKey(String pageKey) {
		this.pageKey = pageKey;
	}

	public String getNewUndisplayFlag() {
		return newUndisplayFlag;
	}

	public void setNewUndisplayFlag(String newUndisplayFlag) {
		this.newUndisplayFlag = newUndisplayFlag;
	}

	public String getConfirmNoupdateFlag() {
		return confirmNoupdateFlag;
	}

	public void setConfirmNoupdateFlag(String confirmNoupdateFlag) {
		this.confirmNoupdateFlag = confirmNoupdateFlag;
	}

	public String getNewKeepFlag() {
		return newKeepFlag;
	}

	public void setNewKeepFlag(String newKeepFlag) {
		this.newKeepFlag = newKeepFlag;
	}

	public String getOpenNum() {
		return openNum;
	}

	public void setOpenNum(String openNum) {
		this.openNum = openNum;
	}

	public String getOnEditFlag() {
		return onEditFlag;
	}

	public void setOnEditFlag(String onEditFlag) {
		this.onEditFlag = onEditFlag;
	}

	public String getUserDivision() {
		return userDivision;
	}

	public void setUserDivision(String userDivision) {
		this.userDivision = userDivision;
	}

	public String getSourceDepartment() {
		return sourceDepartment;
	}

	public void setSourceDepartment(String sourceDepartment) {
		this.sourceDepartment = sourceDepartment;
	}

	public String getEvaluationFlag() {
		return evaluationFlag;
	}

	public void setEvaluationFlag(String evaluationFlag) {
		this.evaluationFlag = evaluationFlag;
	}

	public String getEvaluatorDisplayFlag() {
		return evaluatorDisplayFlag;
	}

	public void setEvaluatorDisplayFlag(String evaluatorDisplayFlag) {
		this.evaluatorDisplayFlag = evaluatorDisplayFlag;
	}

	public String getPrevEvaluatorDisplayFlag() {
		return prevEvaluatorDisplayFlag;
	}

	public void setPrevEvaluatorDisplayFlag(String prevEvaluatorDisplayFlag) {
		this.prevEvaluatorDisplayFlag = prevEvaluatorDisplayFlag;
	}

	public String getCommentEditFlag() {
		return commentEditFlag;
	}

	public void setCommentEditFlag(String commentEditFlag) {
		this.commentEditFlag = commentEditFlag;
	}

	public String getPluralEvaluationFlag() {
		return pluralEvaluationFlag;
	}

	public void setPluralEvaluationFlag(String pluralEvaluationFlag) {
		this.pluralEvaluationFlag = pluralEvaluationFlag;
	}

	public String getPrevPluralEvaluationFlag() {
		return prevPluralEvaluationFlag;
	}

	public void setPrevPluralEvaluationFlag(String prevPluralEvaluationFlag) {
		this.prevPluralEvaluationFlag = prevPluralEvaluationFlag;
	}

	public String getEvaluationClearFlag() {
		return evaluationClearFlag;
	}

	public void setEvaluationClearFlag(String evaluationClearFlag) {
		this.evaluationClearFlag = evaluationClearFlag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPageDivision() {
		return pageDivision;
	}

	public void setPageDivision(String pageDivision) {
		this.pageDivision = pageDivision;
	}

	public String getReserveConfirmFlag() {
		return reserveConfirmFlag;
	}

	public void setReserveConfirmFlag(String reserveConfirmFlag) {
		this.reserveConfirmFlag = reserveConfirmFlag;
	}

	public String getReserveConfirmDate() {
		return reserveConfirmDate;
	}

	public void setReserveConfirmDate(String reserveConfirmDate) {
		this.reserveConfirmDate = reserveConfirmDate;
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