package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;

/**
 * �T�C�g��������ʍ��ځi���������Ȃǁj���i�[
 * 
 * @author 
 * @version 1.0
 * @function The form bean for search page.
 */
public class SiteSearchFormBean extends BaseBean {

	private static final long serialVersionUID = 7611032090331762173L;

	/** ���������F�����L�[ */
	private String keyWord = null;

	/** ���������F�y�[�W���̕\������ */
	private String perPageNo = "10";		//�f�t�H���g�l�́u10�v

	/** ���������F�\�[�g�� */
	private String sort = "";

	/** �������ʃ��X�g */
	private List<SiteSearchResultBean> searchPageList = null;
	
	/** �������ʁF���v���� */
	private int total;

	/** �������ʁF�����|���鎞�� */
	private String usedTime = null;
	
	/** �������ʁF�J�����g�y�[�W�� */
	private String searchPageNo = "1";		//�����l�́u1�v
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getPerPageNo() {
		return perPageNo;
	}
	public void setPerPageNo(String perPageNo) {
		this.perPageNo = perPageNo;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(String usedTime) {
		this.usedTime = usedTime;
	}
	public List<SiteSearchResultBean> getSearchPageList() {
		return searchPageList;
	}
	public void setSearchPageList(List<SiteSearchResultBean> searchPageList) {
		this.searchPageList = searchPageList;
	}
	public String getSearchPageNo() {
		return searchPageNo;
	}
	public void setSearchPageNo(String searchPageNo) {
		this.searchPageNo = searchPageNo;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
