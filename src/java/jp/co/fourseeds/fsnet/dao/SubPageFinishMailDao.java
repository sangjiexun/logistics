package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * �R���e���c�쐬�����ʒm���[�����M�@�\Dao�N���X
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
@Repository
public class SubPageFinishMailDao extends BaseDao {
	
	/**
	 * �g�b�v�O���[�v�����擾
	 * @param topGroupName
	 *        �O���[�v����
	 * @return
	 *        �O���[�v���X�g
	 */
	public List<String> getUserMailAddress(Map<String, Object> param)  {
		return this.sqlSessionTemplate.selectList("subPageFinishMail.getUserMailAddress", param);
	}
	
	/**
	 * �Ј��]���惁�[���A�h���X���擾
	 * @param userIdList�@�Ј�ID���X�g
	 * @return�@��������
	 */
	public String[] getTrMailAddress(List<String> userIdList ) {
		
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID_LIST", CommonUtil.getGroupSql(userIdList));
		
		List<String> mailAddressList = this.sqlSessionTemplate.selectList("subPageFinishMail.getTrMailAddress", param);
		
		return mailAddressList.toArray(new String[mailAddressList.size()]);
	}
}