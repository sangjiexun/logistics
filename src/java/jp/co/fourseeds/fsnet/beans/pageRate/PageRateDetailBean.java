package jp.co.fourseeds.fsnet.beans.pageRate;

import java.util.Date;

import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;

/**
 * �R���e���c�]����񖾍�Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016/02/03 �V�K�쐬1.1
 * @version 1.1.0 : 2017/10/12�C���g��minaosi
 *
 **/
public class PageRateDetailBean extends PageRateItemBean {

	private static final long serialVersionUID = 1L;
	
	/** ���[�U�[ID */
	private String userId;
	
	/** ���� */
	private String userName;
	
	/** �R�����g���� */
	private String commentOrderBy;
	
	/** �R�����g���e */
	private String commentInfo;
	
	/** �R�����g���eCSV */
	private String commentInfoCsv;
	
	/** �R�����g���� */
	private String commentDate;
	
	/** �R�����g����CSV�p */
	private Date commentDateCsv;
	
	/** ����ID */
	private String belong;
	
	/** �������� */
	private String belongName;
	
	/**
	 * @return the commentDateCsv
	 */
	public Date getCommentDateCsv() {
		return commentDateCsv;
	}

	/**
	 * @param commentDateCsv the commentDateCsv to set
	 */
	public void setCommentDateCsv(Date commentDateCsv) {
		this.commentDateCsv = commentDateCsv;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the commentOrderBy
	 */
	public String getCommentOrderBy() {
		return commentOrderBy;
	}

	/**
	 * @param commentOrderBy the commentOrderBy to set
	 */
	public void setCommentOrderBy(String commentOrderBy) {
		this.commentOrderBy = commentOrderBy;
	}

	/**
	 * @return the commentInfo
	 */
	public String getCommentInfo() {
		return commentInfo;
	}

	/**
	 * @param commentInfo the commentInfo to set
	 */
	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}

	/**
	 * @return the commentInfoCsv
	 */
	public String getCommentInfoCsv() {
		return commentInfoCsv;
	}

	/**
	 * @param commentInfoCsv the commentInfoCsv to set
	 */
	public void setCommentInfoCsv(String commentInfoCsv) {
		this.commentInfoCsv = commentInfoCsv;
	}

	/**
	 * @return the commentDate
	 */
	public String getCommentDate() {
		return commentDate;
	}

	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	/**
	 * @return the belong
	 */
	public String getBelong() {
		return belong;
	}

	/**
	 * @param belong the belong to set
	 */
	public void setBelong(String belong) {
		this.belong = belong;
	}

	/**
	 * @return the belongName
	 */
	public String getBelongName() {
		return belongName;
	}

	/**
	 * @param belongName the belongName to set
	 */
	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

}