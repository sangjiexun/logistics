package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubWebPageReplaceStuffBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * �T�u���(Web�S����ID�����ւ�_�Ј����ݒ�)�@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1      2017/09/11          NTS            �������Ή�
 * 
 **/
@Repository
public class SubWebPageReplaceStuffDao extends BaseDao {

	public List<SubWebPageReplaceStuffBean> getStuffList(SubWebPageReplaceStuffBean stuffBean, String strOrderBy, String popFromFlag) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �������� 
		param.put("PARA_USER_NAME", stuffBean.getSearchUserName());
		// ���ID
		param.put("PARA_COMPANY_ID", stuffBean.getConditionCompanyId());
		// ����ID
		param.put("PARA_UNIFICATION_ID", stuffBean.getConditionUnificationId());
		// ���ƕ�ID
		param.put("PARA_BUSINESS_ID", stuffBean.getConditionBusinessId());
		// �c�ƕ�ID
		param.put("PARA_SALES_ID", stuffBean.getConditionSalesId());
		
		if (StringUtil.isBlank(stuffBean.getSearchUserName())) {
			// ��ʏ����y���� �z���݂��Ȃ��̏ꍇ
			param.put("PARA_FLAG", "1");
		} else {
			param.put("PARA_FLAG", "0");
		}
		return this.sqlSessionTemplate.selectList("subWebPageReplaceStuff.getReplaceList", param);
		
	}
}