package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.WebPageReplaceBean;
/**
 * Web�S����ID�����ւ����@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1      2017/09/11          NTS            �������Ή�
 * 
 **/
@Repository
public class WebPageReplaceDao extends BaseDao {

	/**
	 * �Ј��R���e���c�֘A�����擾
	 * @param webPageReplaceBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<WebPageReplaceBean> getPageList(WebPageReplaceBean webPageReplaceBean, String strOrderBy) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID",webPageReplaceBean.getSearchUserId());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("webPageReplace.getPageList", param);
	}

	/**
	 * �Ј��R���e���c�֘A�����폜
	 * @param webPageReplaceBean
	 * @param param
	 * @return List
	 */
	public void deleteReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean) {
		// �X�V������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_REPLACE_SEARCHUSER_ID",webPageReplaceBean.getReplaceSearchUserId());
		param.put("PARA_SEARCH_USER_ID",webPageReplaceBean.getSearchUserId());
		
		//�@DB���猟�����ʂ��擾
		this.sqlSessionTemplate.delete("webPageReplace.deleteAuthUser", param);
		this.sqlSessionTemplate.delete("webPageReplace.deleteAuthUserReserve",param);
		this.sqlSessionTemplate.delete("webPageReplace.deleteProxyUser",param);
		this.sqlSessionTemplate.delete("webPageReplace.deleteProxyUserReserve",param);
	}
	
	/**
	 * �Ј��R���e���c�֘A�����X�V
	 * @param webPageReplaceBean
	 * @param param
	 * @return List
	 */
	public void updateReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		// �X�V������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_REPLACE_SEARCHUSER_ID",webPageReplaceBean.getReplaceSearchUserId());
		param.put("PARA_UPDATE_BY", userId);
		param.put("PARA_SEARCH_USER_ID",webPageReplaceBean.getSearchUserId());
		
		//�@DB���猟�����ʂ��擾
		this.sqlSessionTemplate.update("webPageReplace.updateAuthUser", param);
		this.sqlSessionTemplate.update("webPageReplace.updateAuthUserReserve",param);
		this.sqlSessionTemplate.update("webPageReplace.updateProxyUser",param);
		this.sqlSessionTemplate.update("webPageReplace.updateProxyUserReserve",param);
	}
	
	/**
	 * �Ј��R���e���c�֘A����ǉ�
	 * @param webPageReplaceBean
	 * @param param
	 * @return List
	 */
	public void addReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		// �X�V������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_REPLACE_SEARCHUSER_ID",webPageReplaceBean.getReplaceSearchUserId());
		param.put("PARA_UPDATE_BY", userId);
		param.put("PARA_SEARCH_USER_ID",webPageReplaceBean.getSearchUserId());
		
		//�@DB���猟�����ʂ��擾
		this.sqlSessionTemplate.insert("webPageReplace.insertAuthUser", param);
		this.sqlSessionTemplate.insert("webPageReplace.insertAuthUserReserve",param);
		this.sqlSessionTemplate.insert("webPageReplace.insertProxyUser",param);
		this.sqlSessionTemplate.insert("webPageReplace.insertProxyUserReserve",param);
	}
}
