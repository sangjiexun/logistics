package jp.co.fourseeds.fsnet.beans.test;

import java.util.Collections;
import java.util.List;

public class PagingCommon<T> {//pagingCommon
	public List<TestBean> gridModel = Collections.emptyList();  
    private Integer rows = 0;  		//�y�[�W���ɕ\������ 
    private Integer page = 0;  		//���O�y�[�W��
    private Integer record = 0;  	//������
    private Integer total = 0;  	//���y�[�W��
    private String sort_key;  		//�\�[�g����
    private String asc_desc;  		//�\�[�g�~���E����
	/**
 	 * �y�[�W���O�������s��
 	 * ���O�y�[�W���@���y�[�W���@�������̐ݒ�
 	 * @return ����
 	 */	
    public void paging(List<TestBean> resultList) {  //pagingModel
        try {  
        	
            int from = rows * (page - 1);  
            int length = rows; 
            
            record = resultList.size();
            int to = from + length > record? record: from + length;
            
            gridModel = resultList.subList(from, to);
            
            total = (int) Math.ceil((double) record / (double) rows);

        } catch (Exception e) {  
            e.printStackTrace();  
//            this.addActionError(e.getMessage());  
        }  
    }
	public List<TestBean> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<TestBean> gridModel) {
		this.gridModel = gridModel;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getSort_key() {
		return sort_key;
	}
	public void setSort_key(String sort_key) {
		this.sort_key = sort_key;
	}
	public String getAsc_desc() {
		return asc_desc;
	}
	public void setAsc_desc(String asc_desc) {
		this.asc_desc = asc_desc;
	}
}
