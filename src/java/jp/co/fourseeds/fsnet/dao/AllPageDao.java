package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.AllPageBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * �S�R���e���c�@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/17		    NTS        	       �쐬
 *
 **/
@Repository
public class AllPageDao extends BaseDao {
	
	/**
	 * �S�R���e���c�������
	 * @param param ��������
	 * @return List ��������
	 */
	public List<AllPageBean> getAllPageList(LoginUserBean loginUser, String strOrderBy) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		// �X�V/�V�K�ݒ�����擾
		String update_date = FsPropertyUtil.getStringProperty("update.date");
		
		// ����������ݒ�
		param.put("PARA_UPDATE_DATE", update_date);
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_ORDER_BY", strOrderBy);

		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("allPage.getAllPageList", param);
	}
}