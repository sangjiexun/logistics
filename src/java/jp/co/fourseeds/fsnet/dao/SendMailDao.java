package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.ContentSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * �Ј������@�\Dao�����N���X
 * 
 * File Name: UserDao.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		    NTS        	       �쐬
 *
 **/
@Repository
public class SendMailDao extends BaseDao {

	/**
	 * �Ј��������ʂ��擾
	 * @param param�@��������
	 * @param from�@�����J�n
	 * @param length�@�������R�[�h��
	 * @return�@��������
	 */
	public List getMailAddCount (ContentSearchResultBean notReadedInfo) {
		String userid = StringUtil.nullToBlank(notReadedInfo.getUserId());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userid);
		
		return this.sqlSessionTemplate.selectList("mailCountSearch.getMailAddCount", param);
	}
}