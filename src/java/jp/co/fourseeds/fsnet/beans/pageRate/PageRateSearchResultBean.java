package jp.co.fourseeds.fsnet.beans.pageRate;

import jp.co.common.frame.beans.BaseBean;

/**
 * �]���󋵊m�FRESULTBEAN
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.04 �V�K�쐬
 *
 **/
public class PageRateSearchResultBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** �������RESULT���e�J�n */
	/** �y�[�WID */
	private String pageId = null;

	/** ���J�J�n���t */
	private String startDate = null;

	/** ���J�I�����t */
	private String endDate = null;

	/** �^�C�g���� */
	private String title = null;
	
	/** �R���e���c�{���Ґ� */
	private String readerCount = null;

	/** �R�����g�� */
	private String commentCount = null;
	
	/** ���� */
	private String evaluationName;
	
	/** ���ڐ� */
	private String evaluationcount;
	
	/** ����SEQ */
	private String sequence;
	
	/** ���ڂP */
	private String evaluationName1;
	
	/** ���ڂP�� */
	private String evaluationcount1;
	
	/** ���ڂPSEQ */
	private String sequence1;
	
	/** ����2 */
	private String evaluationName2;
	
	/** ����2�� */
	private String evaluationcount2;
	
	/** ����2SEQ */
	private String sequence2;
	
	/** ����3 */
	private String evaluationName3;
	
	/** ����3�� */
	private String evaluationcount3;
	
	/** ����3SEQ */
	private String sequence3;
	
	/** ����4 */
	private String evaluationName4;
	
	/** ����4�� */
	private String evaluationcount4;
	
	/** ����4SEQ */
	private String sequence4;
	
	/** ����5 */
	private String evaluationName5;
	
	/** ����5�� */
	private String evaluationcount5;
	
	/** ����5SEQ */
	private String sequence5;
	
	/** ����6 */
	private String evaluationName6;
	
	/** ����6�� */
	private String evaluationcount6;
	
	/** ����6SEQ */
	private String sequence6;
	
	/** �]���Ҏ����\���t���O */
	private String evaluatorDisplayFlag;
	
	/** �������ڕ]���t���O */
	private String pluralEvaluationFlag;

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReaderCount() {
		return readerCount;
	}

	public void setReaderCount(String readerCount) {
		this.readerCount = readerCount;
	}

	public String getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	public String getEvaluationName() {
		return evaluationName;
	}

	public void setEvaluationName(String evaluationName) {
		this.evaluationName = evaluationName;
	}

	public String getEvaluationcount() {
		return evaluationcount;
	}

	public void setEvaluationcount(String evaluationcount) {
		this.evaluationcount = evaluationcount;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getEvaluationName1() {
		return evaluationName1;
	}

	public void setEvaluationName1(String evaluationName1) {
		this.evaluationName1 = evaluationName1;
	}

	public String getEvaluationcount1() {
		return evaluationcount1;
	}

	public void setEvaluationcount1(String evaluationcount1) {
		this.evaluationcount1 = evaluationcount1;
	}

	public String getSequence1() {
		return sequence1;
	}

	public void setSequence1(String sequence1) {
		this.sequence1 = sequence1;
	}

	public String getEvaluationName2() {
		return evaluationName2;
	}

	public void setEvaluationName2(String evaluationName2) {
		this.evaluationName2 = evaluationName2;
	}

	public String getEvaluationcount2() {
		return evaluationcount2;
	}

	public void setEvaluationcount2(String evaluationcount2) {
		this.evaluationcount2 = evaluationcount2;
	}

	public String getSequence2() {
		return sequence2;
	}

	public void setSequence2(String sequence2) {
		this.sequence2 = sequence2;
	}

	public String getEvaluationName3() {
		return evaluationName3;
	}

	public void setEvaluationName3(String evaluationName3) {
		this.evaluationName3 = evaluationName3;
	}

	public String getEvaluationcount3() {
		return evaluationcount3;
	}

	public void setEvaluationcount3(String evaluationcount3) {
		this.evaluationcount3 = evaluationcount3;
	}

	public String getSequence3() {
		return sequence3;
	}

	public void setSequence3(String sequence3) {
		this.sequence3 = sequence3;
	}

	public String getEvaluationName4() {
		return evaluationName4;
	}

	public void setEvaluationName4(String evaluationName4) {
		this.evaluationName4 = evaluationName4;
	}

	public String getEvaluationcount4() {
		return evaluationcount4;
	}

	public void setEvaluationcount4(String evaluationcount4) {
		this.evaluationcount4 = evaluationcount4;
	}

	public String getSequence4() {
		return sequence4;
	}

	public void setSequence4(String sequence4) {
		this.sequence4 = sequence4;
	}

	public String getEvaluationName5() {
		return evaluationName5;
	}

	public void setEvaluationName5(String evaluationName5) {
		this.evaluationName5 = evaluationName5;
	}

	public String getEvaluationcount5() {
		return evaluationcount5;
	}

	public void setEvaluationcount5(String evaluationcount5) {
		this.evaluationcount5 = evaluationcount5;
	}

	public String getSequence5() {
		return sequence5;
	}

	public void setSequence5(String sequence5) {
		this.sequence5 = sequence5;
	}

	public String getEvaluationName6() {
		return evaluationName6;
	}

	public void setEvaluationName6(String evaluationName6) {
		this.evaluationName6 = evaluationName6;
	}

	public String getEvaluationcount6() {
		return evaluationcount6;
	}

	public void setEvaluationcount6(String evaluationcount6) {
		this.evaluationcount6 = evaluationcount6;
	}

	public String getSequence6() {
		return sequence6;
	}

	public void setSequence6(String sequence6) {
		this.sequence6 = sequence6;
	}

	public String getEvaluatorDisplayFlag() {
		return evaluatorDisplayFlag;
	}

	public void setEvaluatorDisplayFlag(String evaluatorDisplayFlag) {
		this.evaluatorDisplayFlag = evaluatorDisplayFlag;
	}

	public String getPluralEvaluationFlag() {
		return pluralEvaluationFlag;
	}

	public void setPluralEvaluationFlag(String pluralEvaluationFlag) {
		this.pluralEvaluationFlag = pluralEvaluationFlag;
	}

}
