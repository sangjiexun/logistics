package jp.co.fourseeds.fsnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.fourseeds.fsnet.beans.HotPageFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.HotPageDao;

/**
 * Sub���C�ɓ���T�[�r�X�����N���X
 * 
 * File Name: HotPageService.java 
 * Created: 2016/01/06
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0      2016/01/06          NTS            �쐬
 *
 **/
@Component
public class HotPageService extends BaseBusinessService{

	@Autowired
	private HotPageDao hotPageDao;
	
	/**
	 * ���C�ɓ���f�[�^�擾
	 * @param 
	 *            �Ȃ�
	 * @return 
	 *            ���C�ɓ���f�[�^���X�g
	 */
	public List<HotPageFormBean> getHotPageFile() {
		return hotPageDao.getHotPageFile(super.getLoginUserBean().getUserId());
	}
	
	/**
	 * ���C�ɓ���R���e���c���o�^
	 * @param formBean
	 *            �t�H�[���r��
	 * @return 
	 *            �Ȃ�
	 */
	private void insertHotPage(HotPageFormBean formBean) {
		// Max�z�񏇂��擾����B
		String maxOrderBy = hotPageDao.getMaxOrderBy(formBean, super.getLoginUserBean().getUserId());
		// Max�z�񏇂�����܂���ꍇ
		if (StringUtil.isEmpty(maxOrderBy)) {
			// �����l��ݒ肷��B
			formBean.setOrderBy("1");
		} else {
			// Max�z�� + 1
			formBean.setOrderBy(String.valueOf(Long.valueOf(maxOrderBy) + 1));
		}
		hotPageDao.insertHotPage(formBean, super.getLoginUserBean().getUserId());
	}
	
	/**
	 * ���C�ɓ�����o�^
	 * @param formBean
	 *            �t�H�[���r��
	 * @return 
	 *            �Ȃ�
	 */
	private void insertHotPageFile(HotPageFormBean formBean) {
		// Max���C�ɓ���t�H���_ID���擾����B
		String maxHotPageId = hotPageDao.getMaxHotPageId(formBean, super.getLoginUserBean().getUserId());
		// Max���C�ɓ���t�H���_ID������܂���ꍇ
		if (StringUtil.isEmpty(maxHotPageId)) {
			// �����l��ݒ肷��B
			formBean.setHotPageId("100001");
		} else {
			// Max���C�ɓ���t�H���_ID + 1
			formBean.setHotPageId(String.valueOf(Long.valueOf(maxHotPageId) + 1));
		}
		hotPageDao.insertHotPageFile(formBean, super.getLoginUserBean().getUserId());
	}
	
	/**
	 * ���C�ɓ��薾�׃f�[�^�擾
	 * @param detailBean
	 *            ���C�ɓ���r��
	 * @return 
	 *            ���C�ɓ��薾�׃f�[�^���X�g
	 */
	public List<HotPageFormBean> getHotPageDetail(HotPageFormBean detailBean) {
		// ���C�ɓ��薾�׃f�[�^���擾����B
		return hotPageDao.getHotPageDetail(detailBean,super.getLoginUserBean());
	}
	
	/**
	 * ���C�ɓ��薾�׃f�[�^�폜
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @return 
	 *            �Ȃ�
	 */
	public void deleteHotPageFile (HotPageFormBean formBean) {
		// ���C�ɓ�������폜����B
		hotPageDao.updateHotPageFileInvalid(formBean,super.getLoginUserBean().getUserId());
		// ���C�ɓ���R���e���c�����폜����B
		hotPageDao.updateHotPageInvalid(formBean,super.getLoginUserBean().getUserId());
	}
	
	/**
	 * ���C�ɓ��薾�׃f�[�^�폜
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @return 
	 *            �Ȃ�
	 */
	public void updateHotPage (HotPageFormBean formBean) {
		// ���C�ɓ���t�H���_�����X�V����B
		hotPageDao.updateHotPageFileTitle(formBean,super.getLoginUserBean().getUserId());
		// ���C�ɓ�������폜����B
		hotPageDao.deleteHotPageAll(formBean,super.getLoginUserBean().getUserId());
		// �z��Index������
		int orderIdx = 0;
		// ���׃f�[�^�����[�v�B
		for (HotPageFormBean bean: formBean.getHotPageList()) {
			// �폜�t���O���`�F�b�N���Ȃ��ꍇ
			if (StringUtil.isEmpty(bean.getDeleteFlag())) {
				// �z�񏇂�ݒ肷��B
				bean.setOrderBy(String.valueOf(orderIdx++));
				// ���C�ɓ���R���e���c����o�^����B
				hotPageDao.insertHotPage(bean,super.getLoginUserBean().getUserId());
			}
		}
	}
	
	/**
	 * ���C�ɓ��薾�׃f�[�^�X�V
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @return 
	 *            �Ȃ�* 
	 * */
	public void modifyHotPage(HotPageFormBean formBean) {
		// �V�K�ꍇ
		if(StringUtil.isEmpty(formBean.getHotPageId())){
			// ���C�ɓ�����o�^
			insertHotPageFile(formBean);
		}
		// ���C�ɓ���R���e���c���폜
		hotPageDao.deleteHotPageSingle(formBean, super.getLoginUserBean().getUserId());
		// ���C�ɓ���R���e���c���o�^
		insertHotPage(formBean);
	}
	
	
	/**
	 * ���׃f�[�^���擾����B
	 * @param 
	 *          �Ȃ�
	 * @return 
	 *          �Ȃ�
	 * */
	public void searchHotPage(HotPageFormBean formBean) {
		// ���C�ɓ���f�[�^�擾
		List<HotPageFormBean> fileList = getHotPageFile();
		// ���C�ɓ��胊�X�g���[�v
		for (HotPageFormBean detail:fileList) {
			// ���C�ɓ���t�H���_���Ƃ��C�ɓ�������擾����
			List<HotPageFormBean> detailList = getHotPageDetail(detail);
			// �{������L���ȃR���e���c���O���[�v�ɑ��݂���ꍇ
			if (detailList != null && detailList.size() > 0) {
				// ���׃��X�g��ݒ肷��B
				detail.setHotPageList(detailList);
			}
		}
		// ��L�擾�̃��X�g�̓t�H�[���ɐݒ肷��B
		formBean.setHotPageList(fileList);
	}
	
}