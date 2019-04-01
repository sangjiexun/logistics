package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c�]���A�C�e�����Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/2 �V�K�쐬
 *
 **/
public class PageCommentRateBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// �y�[�WID
	private String pageId;
	// ���ڏ���
	private String commentOrderBy;
	// ���ږ���
	private String userId;
	// SEQUENCE
	private String commentInfo;
	// �]����
	private String commentDate;
	// �]��
	private String singleCommentCount;
	// �]��
	private String userName;
	// �]��
	private String commentFlag;
	// �]��
	private String commentUserFlag;
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getCommentOrderBy() {
		return commentOrderBy;
	}
	public void setCommentOrderBy(String commentOrderBy) {
		this.commentOrderBy = commentOrderBy;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getSingleCommentCount() {
		return singleCommentCount;
	}
	public void setSingleCommentCount(String singleCommentCount) {
		this.singleCommentCount = singleCommentCount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCommentFlag() {
		return commentFlag;
	}
	public void setCommentFlag(String commentFlag) {
		this.commentFlag = commentFlag;
	}
	public String getCommentUserFlag() {
		return commentUserFlag;
	}
	public void setCommentUserFlag(String commentUserFlag) {
		this.commentUserFlag = commentUserFlag;
	}
}