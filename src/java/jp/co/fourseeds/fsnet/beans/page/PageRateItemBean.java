package jp.co.fourseeds.fsnet.beans.page;

import jp.co.common.frame.beans.BaseBean;

/**
 * Rec]ΏACeξρBean
 * 
 * @author NTS
 * @version 1.0.0 : 2015/12/2 VKμ¬
 *
 **/
public class PageRateItemBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// y[WID
	private String pageId;
	// ΪΤ
	private String evaluationOrderBy;
	// ΪΌΜ
	private String evaluationName;
	// SEQUENCE
	private String sequence;
	// ]Ώ
	private String evaluationCount;
	// ]Ώ
	private String evaluationFlag;
	
	public String getPageId() {
		return pageId;
	}
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
	public String getEvaluationOrderBy() {
		return evaluationOrderBy;
	}
	public void setEvaluationOrderBy(String evaluationOrderBy) {
		this.evaluationOrderBy = evaluationOrderBy;
	}
	public String getEvaluationName() {
		return evaluationName;
	}
	public void setEvaluationName(String evaluationName) {
		this.evaluationName = evaluationName;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getEvaluationCount() {
		return evaluationCount;
	}
	public void setEvaluationCount(String evaluationCount) {
		this.evaluationCount = evaluationCount;
	}
	public String getEvaluationFlag() {
		return evaluationFlag;
	}
	public void setEvaluationFlag(String evaluationFlag) {
		this.evaluationFlag = evaluationFlag;
	}
	
}