package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

/**
 * ���J����O���[�v�@�\Dao�����N���X
 * 
 * Created: 2016/01/12
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/12		    NTS        	       �쐬
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class SubPageAuthGroupDao extends BaseDao {
	
	/**
	 * �g�b�v�O���[�v�����擾
	 * @param topGroupName
	 *        �O���[�v����
	 * @return
	 *        �O���[�v���X�g
	 */
	public List searchCommTopGroupList(String topGroupName)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_NAME", topGroupName);
		return this.sqlSessionTemplate.selectList("subPageAuthGroup.searchCommTopGroupList", param);
	}
	
	/**
	 * �e���v���[�g�l�p�O���[�v
	 * @param groupName
	 *        �O���[�v����
	 * @param currentUserId
	 *        ���O�C�����[�U�[
	 * @param role
	 *        ����
	 * @return
	 *        �O���[�v���X�g
	 */
	public List searchPersTopGroupList(String groupName, String currentUserId, String role) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_NAME", groupName);
		param.put("PARA_CURRENT_USER_ID", currentUserId);
		param.put("PARA_ROLE", role);
		return this.sqlSessionTemplate.selectList("subPageAuthGroup.searchPersTopGroupList", param);
	}
	
}