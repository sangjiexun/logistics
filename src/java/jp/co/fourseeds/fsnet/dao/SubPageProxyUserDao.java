package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.fourseeds.fsnet.beans.SubPageProxyUserFormBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * ���F�ҋ@�\Dao�����N���X
 * 
 * Created: 2015/09/22
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
public class SubPageProxyUserDao extends BaseDao {
	
	/**
	 * ���F�҃��X�g�擾
	 * @param formBean
	 *        �t�H�[��
	 * @return
	 *        ���F�҃��X�g
	 * */
	public List getSubProxyUserList(SubPageProxyUserFormBean formBean)  {
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�[����
		param.put("PARA_USER_NAME", formBean.getUserSearchName());
		param.put("PARA_COMPANY_ID", formBean.getSearchCompanyId());
		param.put("PARA_UNIFICATION_ID", formBean.getSearchUnificationId());
		param.put("PARA_BUSINESS_ID", formBean.getSearchBusinessId());
		param.put("PARA_SALES_ID", formBean.getSearchSalesId());
		param.put("PARA_SEARCH_ORGANIZATION_ID", formBean.getSearchOrganizationId());
		param.put("PARA_SEARCH_PEMPLOYEE_ID", formBean.getSearchPemployeeId());
		
		if (StringUtil.isBlank(formBean.getUserSearchName())) {
			// ��ʏ����y���� �z���݂��Ȃ��̏ꍇ
			param.put("PARA_FLAG", "1");
		} else {
			param.put("PARA_FLAG", "0");
		}
		return this.sqlSessionTemplate.selectList("subPageProxyUser.searchSubProxyUserList", param);
	}
	
	/**
	 * ���[�U�L�������擾
	 * @param userId
	 */
	public List<ProxyUserBean> getProxyMailList(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", StringUtil.isEmpty(userId)? "''" : userId);
		return this.sqlSessionTemplate.selectList("subPageProxyUser.searchProxyMailList", param);
	}
	
}