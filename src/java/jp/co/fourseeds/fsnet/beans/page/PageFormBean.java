
package jp.co.fourseeds.fsnet.beans.page;

import java.util.List;

import jp.co.common.frame.beans.LabelValueBean;

import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * �R���e���c�t�H�[��Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/27 �V�K�쐬
 *
 **/
public class PageFormBean extends PageBean {

	private static final long serialVersionUID = 1L;

	// �R���e���c�����N���(�����N�Y�t)���X�g
	private List<PageLinkBean> pageLinkList;
	// �R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
	private List<PageAttachmentBean> pageAttachmentList;
	// �R���e���c�{���������[�U���(���J����l)���X�g
	private List<AuthUserBean> authUserList;
	// �R���e���c�{�������O���[�v���(���J����O���[�v)���X�g
	private List<AuthGroupBean> authGroupList;
	// �R���e���c�X�V��s��(���F��)���X�g
	private List<ProxyUserBean> proxyUserList;
	// �ΏێґI���敪
	private String userType;
	// �Ώێ҃h���b�v�_�E�����X�g
	private List<LabelValueBean> userDivisionDropList;
	// ���M�����h���b�v�_�E�����X�g
	private List<LabelValueBean> sourceDeptDropList;
	// �Ώێғ���
	private String userDivisionR;
	// �R���e���c�]���A�C�e����񃊃X�g
	private List<PageRateItemBean> pageRateItemBeanList;
	// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)���X�g
	private List<PageCommentAdminBean> pageCommentAdminOptionList;
	// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)���[�UID
	private String commentAdminOptionUserId;
	// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)���[�U����
	private String commentAdminOptionUserName;
	// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)���X�g
	private List<PageCommentAdminBean> pageCommentAdminList;
	// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)���[�UID
	private String commentAdminUserId;
	// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)���[�U����
	private String commentAdminUserName;
	// �R���e���c�i�����N���܂ށj
	private String contents;
	// �y�[�W�\���t���O�u�R���e���c�o�^�FADD�A�R���e���c�X�V�FUPD�A�R���e���c���J:OPEN�v
	private String pageViewFlag;
	// �R���e���c�z�u��A�Ⴆ��[HOME]-[�ē�/�ʒB]-[�R���e���c�z�u��\��] 
	private String pageLocation;
	// �q�R���e���c�֏����Đݒ���
	private ChildPageFormBean childPageFormBean;
	// �u1�ȊO(���J��)�A1(�\��)�v��\��
	private String updateradio;
	// �]���Ҏ����\���t���O(�R���e���c���)
	private String evaluatorDisplayFlagPage;
	// �O�񕡐����ڕ]���t���O(�R���e���c���)
	private String prevPluralEvaluationFlagPage;
	// ���J�m�F���t��X�V�t���O(���J���t�ύX�Ȃ�)�\���t���O
	private String viewConfirmNoupdateFlag;
	// �u�V�K�v�\���ێ��\���t���O
	private String viewNewKeepFlag;
	// ��ʑJ�ڌ��敪
	private String originType;
	// �q�R���e���c���f���O
	private List<String> updateChildLog;
	// ���R���e���c�͗\��@OR�@�^����W��
	private String isReserve;
	// �e���v���[�g���݃t���O
	private String templateExistFlag;
	// �쐬���[�U����
	private String createUserName;
	// �����t�@�C���̃����N�\���s��
	private String showFlag;
	// �u�������J�v�A�u���[�����M�v���쐧��t���O
	private String openEnableFlag;
	// �Y�t�t�@�C������t���O
	private String downloadAccessFlag;
	// �X�V����
	private String editEnableFlag;
	// ���N�G�X�g��ContextPath��ێ�
	private String contextPath;
	// ���N�G�X�g��serverName��ێ�
	private String serverName;
	// child���݃t���O
	private String childExistFlag;
	// �R���e���c�]���t���O(�R���e���c���)
	private String evaluationFlagPage;
	// �R���e���c�]���\���t���O
	private String showHyoukaFlag;
	// �R���e���c�R�����g���v���
	private int commentCount;
	// �]���ُ탁�b�Z�[�W
	private String hyoukaErrorMessage;
	// �]�������敪
	private String searchCommentFlag;
	// �]���ҏW�敪
	private String editCommentFlag;
	//
	private List<PageCommentRateBean> commentList;
	//
	private String pencilEditFlag;
	//
	private String addCommentInfo;
	//
	private String commentUserId;
	//
	private String commentOrderBy;
	//
	private String commentFlag;
	// �R���e���c�Y�t�t�@�C�����X�g
	private String downloadAttchmentList;
	// �^�C�g�����i�ҏW�j
	private String titles;
	// �����Y�t���݃t���O
	private String attachmentExistFlag;
	// �e�y�[�W���݃t���O
	private String ppageExsitsFlg;
	// �������J�t���O
	private String futureOpenFlag;
	// �u0�F(�\��̂ݍ폜)�A1(�\��y�ь��J���̃R���e���c���폜)�v��\��
	private String deleteradio;
	// ���̃R���e���c��URL
	private String pageUrl;
	// ���C���t�@�C��
	private String mainFile;
	// �R���e���c�t�@�C��URL
	private String pageFileUrl;
	// ���M�����h���b�v�_�E�����X�g
	private String sourceDeptNm;
	// �m�[�hID
	private String nodeId;
	// HTML�쐬�C�x���g
	private String createHtmlEvent;
	// �R���e���c�폜�p���[�����O
	private String deleteMailLog;
	// �u�e���v���[�g����v�敪
	private String isTemplateFrom;
	// �e���v���[�g�̃P�[�X�i���M���������݂��Ȃ��j
	private String templateNoExistDepartmentId;
	// �z�u��ݒ�t���O
	private String pageLocationSetFlag;
	
	public List<PageLinkBean> getPageLinkList() {
		return pageLinkList;
	}
	public void setPageLinkList(List<PageLinkBean> pageLinkList) {
		this.pageLinkList = pageLinkList;
	}
	public List<PageAttachmentBean> getPageAttachmentList() {
		return pageAttachmentList;
	}
	public void setPageAttachmentList(List<PageAttachmentBean> pageAttachmentList) {
		this.pageAttachmentList = pageAttachmentList;
	}
	public List<AuthUserBean> getAuthUserList() {
		return authUserList;
	}
	public void setAuthUserList(List<AuthUserBean> authUserList) {
		this.authUserList = authUserList;
	}
	public List<AuthGroupBean> getAuthGroupList() {
		return authGroupList;
	}
	public void setAuthGroupList(List<AuthGroupBean> authGroupList) {
		this.authGroupList = authGroupList;
	}
	public List<ProxyUserBean> getProxyUserList() {
		return proxyUserList;
	}
	public void setProxyUserList(List<ProxyUserBean> proxyUserList) {
		this.proxyUserList = proxyUserList;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public List<LabelValueBean> getUserDivisionDropList() {
		return userDivisionDropList;
	}
	public void setUserDivisionDropList(List<LabelValueBean> userDivisionDropList) {
		this.userDivisionDropList = userDivisionDropList;
	}
	public List<LabelValueBean> getSourceDeptDropList() {
		return sourceDeptDropList;
	}
	public void setSourceDeptDropList(List<LabelValueBean> sourceDeptDropList) {
		this.sourceDeptDropList = sourceDeptDropList;
	}
	public String getUserDivisionR() {
		return userDivisionR;
	}
	public void setUserDivisionR(String userDivisionR) {
		this.userDivisionR = userDivisionR;
	}
	public List<PageRateItemBean> getPageRateItemBeanList() {
		return pageRateItemBeanList;
	}
	public void setPageRateItemBeanList(List<PageRateItemBean> pageRateItemBeanList) {
		this.pageRateItemBeanList = pageRateItemBeanList;
	}
	public List<PageCommentAdminBean> getPageCommentAdminOptionList() {
		return pageCommentAdminOptionList;
	}
	public void setPageCommentAdminOptionList(List<PageCommentAdminBean> pageCommentAdminOptionList) {
		this.pageCommentAdminOptionList = pageCommentAdminOptionList;
	}
	public String getCommentAdminOptionUserId() {
		return commentAdminOptionUserId;
	}
	public void setCommentAdminOptionUserId(String commentAdminOptionUserId) {
		this.commentAdminOptionUserId = commentAdminOptionUserId;
	}
	public String getCommentAdminOptionUserName() {
		return commentAdminOptionUserName;
	}
	public void setCommentAdminOptionUserName(String commentAdminOptionUserName) {
		this.commentAdminOptionUserName = commentAdminOptionUserName;
	}
	public List<PageCommentAdminBean> getPageCommentAdminList() {
		return pageCommentAdminList;
	}
	public void setPageCommentAdminList(List<PageCommentAdminBean> pageCommentAdminList) {
		this.pageCommentAdminList = pageCommentAdminList;
	}
	public String getCommentAdminUserId() {
		return commentAdminUserId;
	}
	public void setCommentAdminUserId(String commentAdminUserId) {
		this.commentAdminUserId = commentAdminUserId;
	}
	public String getCommentAdminUserName() {
		return commentAdminUserName;
	}
	public void setCommentAdminUserName(String commentAdminUserName) {
		this.commentAdminUserName = commentAdminUserName;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPageViewFlag() {
		return pageViewFlag;
	}
	public void setPageViewFlag(String pageViewFlag) {
		this.pageViewFlag = pageViewFlag;
	}
	public String getPageLocation() {
		return pageLocation;
	}
	public void setPageLocation(String pageLocation) {
		this.pageLocation = pageLocation;
	}
	public ChildPageFormBean getChildPageFormBean() {
		return childPageFormBean;
	}
	public void setChildPageFormBean(ChildPageFormBean childPageFormBean) {
		this.childPageFormBean = childPageFormBean;
	}
	public String getUpdateradio() {
		return updateradio;
	}
	public void setUpdateradio(String updateradio) {
		this.updateradio = updateradio;
	}
	public String getEvaluatorDisplayFlagPage() {
		return evaluatorDisplayFlagPage;
	}
	public void setEvaluatorDisplayFlagPage(String evaluatorDisplayFlagPage) {
		this.evaluatorDisplayFlagPage = evaluatorDisplayFlagPage;
	}
	public String getViewConfirmNoupdateFlag() {
		return viewConfirmNoupdateFlag;
	}
	public void setViewConfirmNoupdateFlag(String viewConfirmNoupdateFlag) {
		this.viewConfirmNoupdateFlag = viewConfirmNoupdateFlag;
	}
	public String getViewNewKeepFlag() {
		return viewNewKeepFlag;
	}
	public void setViewNewKeepFlag(String viewNewKeepFlag) {
		this.viewNewKeepFlag = viewNewKeepFlag;
	}
	public String getOriginType() {
		return originType;
	}
	public void setOriginType(String originType) {
		this.originType = originType;
	}
	public List<String> getUpdateChildLog() {
		return updateChildLog;
	}
	public void setUpdateChildLog(List<String> updateChildLog) {
		this.updateChildLog = updateChildLog;
	}
	public String getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	public String getTemplateExistFlag() {
		return templateExistFlag;
	}
	public void setTemplateExistFlag(String templateExistFlag) {
		this.templateExistFlag = templateExistFlag;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}
	public String getOpenEnableFlag() {
		return openEnableFlag;
	}
	public void setOpenEnableFlag(String openEnableFlag) {
		this.openEnableFlag = openEnableFlag;
	}
	public String getDownloadAccessFlag() {
		return downloadAccessFlag;
	}
	public void setDownloadAccessFlag(String downloadAccessFlag) {
		this.downloadAccessFlag = downloadAccessFlag;
	}
	public String getEditEnableFlag() {
		return editEnableFlag;
	}
	public void setEditEnableFlag(String editEnableFlag) {
		this.editEnableFlag = editEnableFlag;
	}
	public String getContextPath() {
		return contextPath;
	}
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	public String getChildExistFlag() {
		return childExistFlag;
	}
	public void setChildExistFlag(String childExistFlag) {
		this.childExistFlag = childExistFlag;
	}
	public String getPrevPluralEvaluationFlagPage() {
		return prevPluralEvaluationFlagPage;
	}
	public void setPrevPluralEvaluationFlagPage(String prevPluralEvaluationFlagPage) {
		this.prevPluralEvaluationFlagPage = prevPluralEvaluationFlagPage;
	}
	public String getEvaluationFlagPage() {
		return evaluationFlagPage;
	}
	public void setEvaluationFlagPage(String evaluationFlagPage) {
		this.evaluationFlagPage = evaluationFlagPage;
	}
	public String getShowHyoukaFlag() {
		return showHyoukaFlag;
	}
	public void setShowHyoukaFlag(String showHyoukaFlag) {
		this.showHyoukaFlag = showHyoukaFlag;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getHyoukaErrorMessage() {
		return hyoukaErrorMessage;
	}
	public void setHyoukaErrorMessage(String hyoukaErrorMessage) {
		this.hyoukaErrorMessage = hyoukaErrorMessage;
	}
	public String getSearchCommentFlag() {
		return searchCommentFlag;
	}
	public void setSearchCommentFlag(String searchCommentFlag) {
		this.searchCommentFlag = searchCommentFlag;
	}
	public String getEditCommentFlag() {
		return editCommentFlag;
	}
	public void setEditCommentFlag(String editCommentFlag) {
		this.editCommentFlag = editCommentFlag;
	}
	public List<PageCommentRateBean> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<PageCommentRateBean> commentList) {
		this.commentList = commentList;
	}
	public String getPencilEditFlag() {
		return pencilEditFlag;
	}
	public void setPencilEditFlag(String pencilEditFlag) {
		this.pencilEditFlag = pencilEditFlag;
	}
	public String getAddCommentInfo() {
		return addCommentInfo;
	}
	public void setAddCommentInfo(String addCommentInfo) {
		this.addCommentInfo = addCommentInfo;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentOrderBy() {
		return commentOrderBy;
	}
	public void setCommentOrderBy(String commentOrderBy) {
		this.commentOrderBy = commentOrderBy;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getAttachmentExistFlag() {
		return attachmentExistFlag;
	}
	public void setAttachmentExistFlag(String attachmentExistFlag) {
		this.attachmentExistFlag = attachmentExistFlag;
	}
	public String getDownloadAttchmentList() {
		return downloadAttchmentList;
	}
	public void setDownloadAttchmentList(String downloadAttchmentList) {
		this.downloadAttchmentList = downloadAttchmentList;
	}
	public String getTitles() {
		return titles;
	}
	public void setTitles(String titles) {
		this.titles = titles;
	}
	public String getPpageExsitsFlg() {
		return ppageExsitsFlg;
	}
	public void setPpageExsitsFlg(String ppageExsitsFlg) {
		this.ppageExsitsFlg = ppageExsitsFlg;
	}
	public String getFutureOpenFlag() {
		return futureOpenFlag;
	}
	public void setFutureOpenFlag(String futureOpenFlag) {
		this.futureOpenFlag = futureOpenFlag;
	}
	public String getDeleteradio() {
		return deleteradio;
	}
	public void setDeleteradio(String deleteradio) {
		this.deleteradio = deleteradio;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getMainFile() {
		return mainFile;
	}
	public void setMainFile(String mainFile) {
		this.mainFile = mainFile;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getPageFileUrl() {
		return pageFileUrl;
	}
	public void setPageFileUrl(String pageFileUrl) {
		this.pageFileUrl = pageFileUrl;
	}
	public String getSourceDeptNm() {
		return sourceDeptNm;
	}
	public void setSourceDeptNm(String sourceDeptNm) {
		this.sourceDeptNm = sourceDeptNm;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getCreateHtmlEvent() {
		return createHtmlEvent;
	}
	public void setCreateHtmlEvent(String createHtmlEvent) {
		this.createHtmlEvent = createHtmlEvent;
	}
	public String getDeleteMailLog() {
		return deleteMailLog;
	}
	public void setDeleteMailLog(String deleteMailLog) {
		this.deleteMailLog = deleteMailLog;
	}
	public String getIsTemplateFrom() {
		return isTemplateFrom;
	}
	public void setIsTemplateFrom(String isTemplateFrom) {
		this.isTemplateFrom = isTemplateFrom;
	}
	public String getTemplateNoExistDepartmentId() {
		return templateNoExistDepartmentId;
	}
	public void setTemplateNoExistDepartmentId(String templateNoExistDepartmentId) {
		this.templateNoExistDepartmentId = templateNoExistDepartmentId;
	}
	public String getPageLocationSetFlag() {
		return pageLocationSetFlag;
	}
	public void setPageLocationSetFlag(String pageLocationSetFlag) {
		this.pageLocationSetFlag = pageLocationSetFlag;
	}
	
}