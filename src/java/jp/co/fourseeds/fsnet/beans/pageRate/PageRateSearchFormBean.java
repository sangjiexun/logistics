package jp.co.fourseeds.fsnet.beans.pageRate;

import jp.co.common.frame.beans.BaseBean;
/**
 * �]���󋵊m�F�t�H�[��BEAN
 * 
 * @author NTS
 * @version 1.0.0 : 2015.12.04 �V�K�쐬
 *
 **/
public class PageRateSearchFormBean extends BaseBean {
	
	private static final long serialVersionUID = -7715997010417294705L;

	/**���񌟍��t���O*/
	private String isFirstSearch = null;
	
	/**�y�[�W�\������*/
	private String searchRowNum = null;
	
	/** ���J�J�n���t */
	private String startDate = null;

	/** ���J�I�����t */
	private String endDate = null;

	/** �^�C�g���� */
	private String title = null;
	
	/** ���ݗL���ȃR���e���c�̂ݑΏۃt���O */
	private String hyoukaValidFlag = null;
	
	/** �������ʌ��� */
	private int resultCount;
	
	/** ��ʏ�ԃt���O�u��������ʁF0�v */
	private String statusFlag = null;
	
	/**
	 * @return the isFirstSearch
	 */
	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	/**
	 * @param isFirstSearch the isFirstSearch to set
	 */
	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	/**
	 * @return the searchRowNum
	 */
	public String getSearchRowNum() {
		return searchRowNum;
	}

	/**
	 * @param searchRowNum the searchRowNum to set
	 */
	public void setSearchRowNum(String searchRowNum) {
		this.searchRowNum = searchRowNum;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	 * @return the hyoukaValidFlag
	 */
	public String getHyoukaValidFlag() {
		return hyoukaValidFlag;
	}

	/**
	 * @param hyoukaValidFlag the hyoukaValidFlag to set
	 */
	public void setHyoukaValidFlag(String hyoukaValidFlag) {
		this.hyoukaValidFlag = hyoukaValidFlag;
	}

	/**
	 * @return the resultCount
	 */
	public int getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount the resultCount to set
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * @return the statusFlag
	 */
	public String getStatusFlag() {
		return statusFlag;
	}

	/**
	 * @param statusFlag the statusFlag to set
	 */
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
}
