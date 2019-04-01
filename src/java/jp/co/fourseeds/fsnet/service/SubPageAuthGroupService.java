package jp.co.fourseeds.fsnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.beans.SubPageAuthGroupFormBean;
import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.dao.SubPageAuthGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * ���J����O���[�v�@�\�T�[�r�X�����N���X
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
@SuppressWarnings(value={"unchecked"})
@Component
public class SubPageAuthGroupService extends BaseBusinessService{
	
	@Autowired
	private SubPageAuthGroupDao authGroupDao;
	
	/**
	 * �������ʂ��擾
	 * @param formBean
	 *        �t�H�[��
	 * @return
	 *        �Ȃ�
	 * */
	public void search(SubPageAuthGroupFormBean formBean) {
		// �ėp
		if ("0".equals(formBean.getPersonalFlag())) {
			// �g�b�v�O���[�v�����擾
			List<AuthGroupBean> groupList = authGroupDao.searchCommTopGroupList(formBean.getGroupSearchName());
			// �擾�̃O���[�v���X�g��ݒ肷��B
			formBean.setGroupList(groupList);
		// �l�p�O���[�v
		} else if ("1".equals(formBean.getPersonalFlag())) {
			// �l�p�O���[�v���擾����B
			List<AuthGroupBean> groupList = authGroupDao.searchPersTopGroupList(
					formBean.getGroupSearchName(), super.getLoginUserBean().getUserId(),
					super.getLoginUserBean().getRole());
			// �擾�̃O���[�v���X�g��ݒ肷��B
			formBean.setGroupList(groupList);
		}
	}
}