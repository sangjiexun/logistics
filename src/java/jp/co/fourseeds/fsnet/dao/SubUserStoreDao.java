package jp.co.fourseeds.fsnet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubUserStoreBean;

/**
 * ���[�U���̓X�܌����|�b�v�A�b�v�@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2017/09/06		    NTS        	       �V�K�쐬
 **/
@Repository
public class SubUserStoreDao extends BaseDao{

	/**
	 * �X�܏����擾
	 * @param param
	 * @return List
	 */
	public List<SubUserStoreBean> getStoreList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("subUserStore.getStoreList", param);
	}
}
