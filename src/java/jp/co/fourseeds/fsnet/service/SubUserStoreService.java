package jp.co.fourseeds.fsnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SubUserStoreBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.dao.SubUserStoreDao;

/**
 * ���[�U���̓X�܌����|�b�v�A�b�v�@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2017/09/06		    NTS        	       �V�K�쐬
 *
 **/
@Component
public class SubUserStoreService extends BaseBusinessService{
	@Autowired
	private SubUserStoreDao subUserStoreDao;
	
	/**
	 * �X�܏����擾
	 * @param SubUserStoreBean
	 * @param strOrderBy
	 * @return ��������
	 */
	public List<SubUserStoreBean> getStoreList(SubUserStoreBean bean, String strOrderBy, LoginUserBean LoginUserBean) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		// �V�X�e�����p�敪
		param.put("PARA_LOGIN_ROLE", LoginUserBean.getRole());
		// �X�܃R�[�h
		param.put("PARA_STORE_ID", bean.getSearchStoreId());
		//�X�ܖ�
		param.put("PARA_STORE_NAME", bean.getSearchStoreName());
		// ��ЃR�[�h
		param.put("PARA_COMPANY_ID", bean.getSearchCompanyId());
		// �����R�[�h
		param.put("PARA_UNIFICATION_ID", bean.getSearchUnificationId());
		// ���ƃR�[�h
		param.put("PARA_BUSINESS_ID", bean.getSearchBusinessId());
		// �c�ƕ��R�[�h
		param.put("PARA_SALES_ID", bean.getSearchSalesId());
		// �X�܋敪
		param.put("PARA_FC_FLAG", bean.getSearchFCFlag());
		// �g�D�敪:���c
		param.put("PARA_USER_DIRECT", ConstantContainer.USER_DIRECT);
		// �\�[�g����
		param.put("PARA_ORDER_BY", strOrderBy);
		
		return subUserStoreDao.getStoreList(param);
	}
}
