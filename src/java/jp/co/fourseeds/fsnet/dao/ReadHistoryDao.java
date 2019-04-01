package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.ReadHistoryFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * �{�������̊m�F�@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/18		    NTS        	       �쐬
 *
 **/
@Repository
public class ReadHistoryDao extends BaseDao {
	
	/**
	 * �z�M�Ҋm�F�����擾
	 * @param param ��������
	 * @return List ��������
	 */
	public List<ReadHistoryFormBean> getNecessityReadPageList(ReadHistoryFormBean readHistoryFormBean, String strOrderBy, LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		// �{���J�n��
		String startDate= readHistoryFormBean.getSearchStartDate();
		// �{���I����
		String endDate= readHistoryFormBean.getSearchEndDate();
		
		// ����������ݒ�
		// �{���J�n���w�肵�Ȃ��ꍇ�A�ŏ��̉{���J�n����ݒ�
		if (StringUtil.isEmpty(startDate)) {
			param.put("PARA_START_DATE", "19000101");
		// �{���J�n���w��̏ꍇ�A��ʂ� �{���J�n����ݒ�
		} else {
			param.put("PARA_START_DATE", startDate);
		}
		
		// �{���I�����w�肵�Ȃ��ꍇ�A�ő�̉{���I������ݒ�
		if (StringUtil.isEmpty(endDate)) {
			param.put("PARA_END_DATE", "29981231");
		// �{���I�����w��̏ꍇ�A��ʂ� �{���I������ݒ�
		} else {
			param.put("PARA_END_DATE", endDate);
		}
		param.put("PARA_TITLE", readHistoryFormBean.getSearchTitle());
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("readHistory.getNecessityReadPageList", param);
	}
	
	/**
	 * �{���Ǘ��ΏێҏƉ�����擾
	 * @param param ��������
	 * @return List ��������
	 */
	public List<ReadHistoryFormBean> getNecessityReadUserListByPage(ReadHistoryFormBean readHistoryFormBean, String strOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// ����������ݒ�
		param.put("PARA_PAGE_ID", readHistoryFormBean.getPageId());
		param.put("PARA_READ_FLAG", readHistoryFormBean.getReadFlag());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("readHistory.getNecessityReadUserListByPage", param);
	}
	
	/**
	 * ���[�U�̃��[���A�h���X���擾
	 * @param param ��������
	 * @return List ��������
	 */
	public String getUserMailAddress(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		
		//�@DB���猟�����ʂ��擾
		List<String> mailList = this.sqlSessionTemplate.selectList("readHistory.getUserMailAddress", param);
		if (StringUtil.isBlank(mailList) || mailList.size() ==0 ) {
			return null;
		} else {
			return mailList.get(0);
		}
	}
}