package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c�]���A�C�e�����Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/2 �V�K�쐬
 *
 **/
public class PageUserCommentBean extends BaseBean {

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
}