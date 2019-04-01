package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.StoreBean;

/**
 * �X�܏��@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/21		    NTS        	       �쐬
 * 1.1		2017/09/06			NTS			     �������C��
 **/
@Repository
public class StoreDao extends BaseDao {
	
	/**
	 * �X�܏����擾
	 * @param storeBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<StoreBean> getStoreList(StoreBean storeBean, String strOrderBy, LoginUserBean loginUser) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_ORDER_BY", strOrderBy); 
		param.put("PARA_STORE_ID",storeBean.getSearchStoreId());
		param.put("PARA_STORE_NAME", storeBean.getSearchStoreName());
		param.put("PARA_COMPANY_ID",storeBean.getSearchCompanyId());
		param.put("PARA_UNIFICATION_ID", storeBean.getSearchUnificationId());
		param.put("PARA_BUSINESS_ID", storeBean.getSearchBusinessId());
		param.put("PARA_SALES_ID",storeBean.getSearchSalesId());
		param.put("PARA_SHOW_FLAG",storeBean.getShowFlag());
		param.put("PARA_FCFLAG",storeBean.getFcFlag());
		param.put("PARA_OTHER_FLAG",storeBean.getOtherFlag());
		param.put("PARA_SUPERVISOR_NAME",storeBean.getSupervisorName());
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("store.getStoreList", param);
	}
	
	/**
	 * �X�܏ڍ׏����擾
	 * @param storeBean
	 * @return StoreBean
	 */
	public StoreBean getStoreDetailInfo(StoreBean storeBean, LoginUserBean loginUser) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_ROLE", loginUser.getRole());
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("store.getStoreDetailInfo", param);
	}
	
	/**
	 * �Ɩ��ϑ�������J�����擾
	 * @param storeBean
	 * @return ��������
	 */
	public Map<String, String> getOpenConsignAccessionInfo(StoreBean storeBean, String consignAccessionFlag) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("store.getOpenConsignAccessionInfo", param);
	}
	
	/**
	 * �Ɩ��ϑ�����\������擾
	 * @param storeBean
	 * @return ��������
	 */
	public Map<String, String> getVailedConsignAccessionInfo(StoreBean storeBean, String consignAccessionFlag) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("store.getVailedConsignAccessionInfo", param);
	}
	
	/**
	 * �X�܏����擾
	 * @param storeBean
	 */
	public Map<String, String> getStoreMaster(StoreBean storeBean, String consignAccessionFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		
		return this.sqlSessionTemplate.selectOne("store.getStoreMaster", param);
	}
	
	/**
	 * �X�܏����폜
	 * @param storeBean
	 */
	public void deleteStoreMaster(StoreBean storeBean, String openVailedFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_OPEN_VAILED_FLAG", openVailedFlag);
		this.sqlSessionTemplate.delete("store.deleteStoreMaster", param);
	}
	
	
	/**
	 * �X�܏���ϗ��폜
	 * @param storeBean
	 */
	public void updateStoreFlag(StoreBean storeBean, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_UPDATE_BY", userId);
		this.sqlSessionTemplate.delete("store.updateStoreFlag", param);
	}
	
	/**
	 * �X�܏����X�V
	 * @param storeBean
	 */
	public void updateStoreMaster(StoreBean storeBean, String role, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SHOW_FLAG", storeBean.getShowFlag());
		param.put("PARA_UPDATE_BY", userId);
		param.put("PARA_STORE_ID", storeBean.getStoreId());
		param.put("PARA_ROLE", role);
		this.sqlSessionTemplate.update("store.updateStoreMaster", param);
	}
	
	/**
	 * �X�܏���o�^
	 * @param storeId �X��ID
	 * @param consignAccessionFlag �ϑ�����敪
	 * @param storeSet �L���J�n��
	 * @param storeEx �L���I����
	 * @param yconrc �I�[�i�[�R�[�h
	 * @param createDate �쐬����
	 * @param createBy �쐬���[�UID
	 * @param updateBy �X�V��
	 */
	public void insertStoreMaster(String storeId, String consignAccessionFlag, String storeSet, String storeEx,
			String yconrc, String createDate, String createBy, String updateBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_ID", storeId);
		param.put("PARA_CONSIGN_ACCESSION_FLAG", consignAccessionFlag);
		param.put("PARA_STORE_SET", storeSet);
		param.put("PARA_STORE_EX", storeEx);
		param.put("PARA_YCONRC", yconrc);
		param.put("PARA_CREATE_DATE", createDate);
		param.put("PARA_CREATE_BY", createBy);
		param.put("PARA_UPDATE_BY", updateBy);

		this.sqlSessionTemplate.insert("store.insertStoreMaster", param);
	}
}