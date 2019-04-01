package jp.co.fourseeds.fsnet.beans.pageRate;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;
/**
 * �]���󋵊m�F���׃t�H�[��BEAN
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.04 �V�K�쐬
 *
 **/
public class PageRateDetailFormBean extends BaseBean {
	
	private static final long serialVersionUID = -7715997010417294705L;

	/** �y�[�W�\������ */
	private String searchRowNum;
	
	/** �y�[�WID */
	private String pageId;
	
	/** �]������ */
	private String evaluationRadioFlag;
	
	/** AND���� */
	private String pluralEvaluationFlag;
	
	/** And�����`�F�b�N�{�b�N�X */
	private String evaluationFlag;
	
	/** SEQUENCE */
	private String evaluationSequence;
	
	/** �R�����g���L�[���[�h */
	private String commentKeyWord;
	
	/** �]���Ҏ����\���t���O */
	private String evaluatorDisplayFlag;
	
	/** �^�C�g�� */
	private String title;

	/** �R���e���c�{���Ґ� */
	private String readerCount;

	/** �R�����g�� */
	private String commentCount;
	
	/** �R�����g��񃊃X�g */
	private List<PageRateDetailBean> commentList;
	
	/** �]���󋵏ڍ׉�ʃR���e���c��񃊃X�g */
	private List<PageRateSearchResultBean> detailSearchResultsList;
	
	/** �]�����X�g */
	private List<PageRateDetailBean> evaluationContentsList;
	
	/** �]���ڍד��v���X�g(�]���ڍ�CSV�o�͗p) */
	private List<PageRateDetailBean> evaluationCountList;
	
	/** �R�����gCSV �P:�o�͉O�F�o�͕s�� */
	private String commentCsvFlag;
	
	/** �]��CSV �P:�o�͉O�F�o�͕s�� */
	private String evaluationCsvFlag;
	
	/**
	 * @return the commonCsvFlag
	 */
	public String getCommentCsvFlag() {
		return commentCsvFlag;
	}

	/**
	 * @param commonCsvFlag the commonCsvFlag to set
	 */
	public void setCommentCsvFlag(String commentCsvFlag) {
		this.commentCsvFlag = commentCsvFlag;
	}

	/**
	 * @return the evaluationCsvFlag
	 */
	public String getEvaluationCsvFlag() {
		return evaluationCsvFlag;
	}

	/**
	 * @param evaluationCsvFlag the evaluationCsvFlag to set
	 */
	public void setEvaluationCsvFlag(String evaluationCsvFlag) {
		this.evaluationCsvFlag = evaluationCsvFlag;
	}

	/**
	 * @return the readerCount
	 */
	public String getReaderCount() {
		return readerCount;
	}

	/**
	 * @param readerCount the readerCount to set
	 */
	public void setReaderCount(String readerCount) {
		this.readerCount = readerCount;
	}

	/**
	 * @return the commentCount
	 */
	public String getCommentCount() {
		return commentCount;
	}

	/**
	 * @param commentCount the commentCount to set
	 */
	public void setCommentCount(String commentCount) {
		this.commentCount = commentCount;
	}

	/**
	 * @return the evaluatorDisplayFlag
	 */
	public String getEvaluatorDisplayFlag() {
		return evaluatorDisplayFlag;
	}

	/**
	 * @param evaluatorDisplayFlag the evaluatorDisplayFlag to set
	 */
	public void setEvaluatorDisplayFlag(String evaluatorDisplayFlag) {
		this.evaluatorDisplayFlag = evaluatorDisplayFlag;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the evaluationContentsList
	 */
	public List<PageRateDetailBean> getEvaluationContentsList() {
		return evaluationContentsList;
	}

	/**
	 * @param evaluationContentsList the evaluationContentsList to set
	 */
	public void setEvaluationContentsList(List<PageRateDetailBean> evaluationContentsList) {
		this.evaluationContentsList = evaluationContentsList;
	}

	/**
	 * @return the evaluationCountList
	 */
	public List<PageRateDetailBean> getEvaluationCountList() {
		return evaluationCountList;
	}

	/**
	 * @param evaluationCountList the evaluationCountList to set
	 */
	public void setEvaluationCountList(List<PageRateDetailBean> evaluationCountList) {
		this.evaluationCountList = evaluationCountList;
	}

	/**
	 * @return the commentList
	 */
	public List<PageRateDetailBean> getCommentList() {
		return commentList;
	}

	/**
	 * @return the detailSearchResultsList
	 */
	public List<PageRateSearchResultBean> getDetailSearchResultsList() {
		return detailSearchResultsList;
	}

	/**
	 * @param detailSearchResultsList the detailSearchResultsList to set
	 */
	public void setDetailSearchResultsList(List<PageRateSearchResultBean> detailSearchResultsList) {
		this.detailSearchResultsList = detailSearchResultsList;
	}

	/**
	 * @param commentList the commentList to set
	 */
	public void setCommentList(List<PageRateDetailBean> commentList) {
		this.commentList = commentList;
	}

	/**
	 * @return the evaluationFlag
	 */
	public String getEvaluationFlag() {
		return evaluationFlag;
	}

	/**
	 * @param evaluationFlag the evaluationFlag to set
	 */
	public void setEvaluationFlag(String evaluationFlag) {
		this.evaluationFlag = evaluationFlag;
	}

	/**
	 * @return the evaluationSequence
	 */
	public String getEvaluationSequence() {
		return evaluationSequence;
	}

	/**
	 * @param evaluationSequence the evaluationSequence to set
	 */
	public void setEvaluationSequence(String evaluationSequence) {
		this.evaluationSequence = evaluationSequence;
	}

	/**
	 * @return the searchRowNum
	 */
	public String getSearchRowNum() {
		return searchRowNum;
	}

	/**
	 * @return the evaluationRadioFlag
	 */
	public String getEvaluationRadioFlag() {
		return evaluationRadioFlag;
	}

	/**
	 * @param evaluationRadioFlag the evaluationRadioFlag to set
	 */
	public void setEvaluationRadioFlag(String evaluationRadioFlag) {
		this.evaluationRadioFlag = evaluationRadioFlag;
	}

	/**
	 * @return the pluralEvaluationFlag
	 */
	public String getPluralEvaluationFlag() {
		return pluralEvaluationFlag;
	}

	/**
	 * @param pluralEvaluationFlag the pluralEvaluationFlag to set
	 */
	public void setPluralEvaluationFlag(String pluralEvaluationFlag) {
		this.pluralEvaluationFlag = pluralEvaluationFlag;
	}

	/**
	 * @param searchRowNum the searchRowNum to set
	 */
	public void setSearchRowNum(String searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	/**
	 * @return the pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId the pageId to set
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the commentKeyWord
	 */
	public String getCommentKeyWord() {
		return commentKeyWord;
	}

	/**
	 * @param commentKeyWord the commentKeyWord to set
	 */
	public void setCommentKeyWord(String commentKeyWord) {
		this.commentKeyWord = commentKeyWord;
	}
	
}
