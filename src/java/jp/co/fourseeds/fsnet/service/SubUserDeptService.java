package jp.co.fourseeds.fsnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SubUserDeptBean;
import jp.co.fourseeds.fsnet.dao.SubUserDeptDao;

/**
 * ���[�U���̏��������|�b�v�A�b�v�@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2017/09/06		    NTS        	       �V�K�쐬
 *
 **/
@Component
public class SubUserDeptService extends BaseBusinessService{
	@Autowired
	private SubUserDeptDao subUserDeptDao;
	
	/**
	 * �{�������擾
	 * @param SubUserDeptBean
	 * @param strOrderBy
	 * @return ��������
	 */
	public List<SubUserDeptBean> getStoreList(SubUserDeptBean bean, String strOrderBy, LoginUserBean LoginUserBean) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		// �����R�[�h
		param.put("PARA_DEPT_ID", bean.getSearchDeptId());
		// ������
		param.put("PARA_DEPT_NAME", bean.getSearchDeptName());
		// ��ЃR�[�h
		param.put("PARA_COMPANY_ID", bean.getSearchCompanyId());
		// �����R�[�h
		param.put("PARA_UNIFICATION_ID", bean.getSearchUnificationId());
		// ���ƃR�[�h
		param.put("PARA_BUSINESS_ID", bean.getSearchBusinessId());
		// �\�[�g����
		param.put("PARA_ORDER_BY", strOrderBy);
		
		return subUserDeptDao.getDeptList(param);
	}
}
