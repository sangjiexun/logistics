package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.beans.SubPageProxyUserFormBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.SubPageProxyUserDao;
import jp.co.common.frame.service.BaseBusinessService;

/**
 * ���F�ҋ@�\�T�[�r�X�����N���X
 * 
 * Created: 2016/01/12
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@VersionWhen	  Who	  Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/12		 NTS	 		�쐬
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Component
public class SubPageProxyUserService extends BaseBusinessService{
	
	@Autowired
	private SubPageProxyUserDao proxyUserDao;
	
	/**
	 * ���F�҃��X�g�擾
	 * @param formBean
	 *        �t�H�[��
	 * @return
	 *        ���F�҃��X�g
	 * */
	public List getSubProxyUserList(SubPageProxyUserFormBean formBean) {
		return proxyUserDao.getSubProxyUserList(formBean);
	}
	
	/**
	 * ���[�U�L�������擾
	 * @param proxyUId
	 */
	@SuppressWarnings("unchecked")
	public List<ProxyUserBean> getProxyMailList(String proxyUId){
		List proxyMailList = new ArrayList();
		// �p�����[�^���[�U�[ID������ꍇ
		if (!StringUtil.isEmpty(proxyUId)) {
			// ���[�U�L�������擾
			proxyMailList = proxyUserDao.getProxyMailList(proxyUId);
		}
		return proxyMailList;
	}
}