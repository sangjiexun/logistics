package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * �R���e���c���[�U�[�]�����Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016/1/4 �V�K�쐬
 *
 **/
public class PageUserRateBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// �y�[�WID
	private String pageId;
	// ���[�UID
	private String userId;
	// ���ڏ���
	private String evaluationOrderBy;
	// SEQUENCE
	private String sequence;

	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEvaluationOrderBy() {
		return evaluationOrderBy;
	}
	public void setEvaluationOrderBy(String evaluationOrderBy) {
		this.evaluationOrderBy = evaluationOrderBy;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}