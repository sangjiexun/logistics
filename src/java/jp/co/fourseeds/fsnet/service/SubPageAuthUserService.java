package jp.co.fourseeds.fsnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.dao.SubPageAuthUserDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.fourseeds.fsnet.beans.SubPageAuthUserFormBean;

/**
 * ���J����l�@�\�T�[�r�X�����N���X
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
public class SubPageAuthUserService extends BaseBusinessService{
	
	@Autowired
	private SubPageAuthUserDao subPageAuthUserDao;
	
	/**
	 * �������ʂ��擾
	 * @param formBean
	 *        �t�H�[��
	 * @return
	 *        �Ȃ�
	 * */
	public List search(SubPageAuthUserFormBean formBean) {
		return subPageAuthUserDao.getUserListByCondition(formBean);
	}
	
}