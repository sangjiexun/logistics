package jp.co.fourseeds.fsnet.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubUserDeptBean;

/**
 * ���[�U���̖{�������|�b�v�A�b�v�@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2017/09/06		    NTS        	       �V�K�쐬
 **/
@Repository
public class SubUserDeptDao extends BaseDao{

	/**
	 * �{�������擾
	 * @param param
	 * @return List
	 */
	public List<SubUserDeptBean> getDeptList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("subUserDept.getDeptList", param);
	}
}
