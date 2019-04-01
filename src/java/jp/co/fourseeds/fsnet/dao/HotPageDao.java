package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.fourseeds.fsnet.beans.HotPageFormBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * Sub���C�ɓ���DAO�����N���X
 * 
 * File Name: HotPageDao.java 
 * Created: 2016/01/06
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0      2016/01/06          NTS            �쐬
 *
 **/
@Repository
public class HotPageDao extends BaseDao {

	/**
	 * ���C�ɓ���f�[�^�擾
	 * @param currentUserId
	 *            ���O�C�����[�U�[ID
	 * @return 
	 *            ���C�ɓ���f�[�^���X�g
	 */
	public List<HotPageFormBean> getHotPageFile (String currentUserId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", currentUserId);
		// SUB���C�ɓ���f�[�^�擾SQL
		return this.sqlSessionTemplate.selectList("hotPage.getHotPageFile", param);
	}
	
	/**
	 * MAX���C�ɓ���t�H���_ID���擾
	 * @param formBean
	 *            �t�H�[���r��
	 * @param  userId
	 *            ���[�U�[ID
	 * @return 
	 *             MAX���C�ɓ���t�H���_ID
	 */
	public String getMaxHotPageId(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// SUB���C�ɓ���f�[�^�擾SQL
		return this.sqlSessionTemplate.selectOne("hotPage.getMaxHotPageId", param);
	}
	
	/**
	 * MAX�z�񏇂��擾
	 * @param formBean
	 *            �t�H�[���r��
	 * @param  userId
	 *            ���[�U�[ID
	 * @return 
	 *            MAX�z��
	 */
	public String getMaxOrderBy(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUB���C�ɓ���f�[�^�擾SQL
		return this.sqlSessionTemplate.selectOne("hotPage.getMaxOrderBy", param);
	}
	
	/**
	 * ���C�ɓ���R���e���c���_���폜 
	 * @param formBean
	 *            �t�H�[���r��
	 * @param  userId
	 *            ���[�U�[ID
	 * @return 
	 *            �Ȃ�
	 */
	public void updateHotPageInvalid(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// �y�[�WID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.update("hotPage.updateHotPageInvalid", param);
	}
	
	/**
	 * ���C�ɓ���R���e���c���o�^
	 * @param formBean
	 *            �t�H�[���r��
	 * @param  userId
	 *            ���[�U�[ID
	 * @return 
	 *            �Ȃ�
	 */
	public void insertHotPage(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// �y�[�WID
		param.put("PARA_PAGE_ID", formBean.getPageId());
		// �y�[�W�^�C�g����
		param.put("PARA_TITLE", formBean.getTitle());
		// �y�[�W�^�C�g����
		param.put("PARA_ORDER_BY", formBean.getOrderBy());
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.insert("hotPage.insertHotPage", param);
	}
	
	/**
	 * ���C�ɓ�����o�^
	 * @param formBean
	 *            �t�H�[���r��
	 * @param  userId
	 *            ���[�U�[ID
	 * @return 
	 *            �Ȃ�
	 */
	public void insertHotPageFile(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// ���C�ɓ���t�H���_��
		param.put("PARA_HOT_PAGE_TITLE", formBean.getHotPageTitle());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.insert("hotPage.insertHotPageFile", param);
	}
	
	/**
	 * ���C�ɓ��薾�׃f�[�^�擾
	 * @param detailBean
	 *            ���C�ɓ���r��
	 * @param loginUser
	 *            ���O�C�����[�U�[�r��
	 * @return 
	 *            ���C�ɓ��薾�׃f�[�^���X�g
	 */
	public List<HotPageFormBean> getHotPageDetail(HotPageFormBean detailBean, LoginUserBean loginUser) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", loginUser.getUserId());
		// �V�X�e�����p�敪
		param.put("PARA_ROLE", loginUser.getRole());
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", detailBean.getHotPageId());
		// �g�b�v�O���b�v���X�g
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		// SUB���C�ɓ���f�[�^�擾SQL
		return this.sqlSessionTemplate.selectList("hotPage.getHotPageDetail", param);
	}
	
	/**
	 * ���C�ɓ�������폜����
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @param userId
	 *            ���O�C�����[�U�[ID
	 * @return 
	 *            ���C�ɓ��薾�׃f�[�^���X�g
	 */
	public void updateHotPageFileInvalid(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.update("hotPage.updateHotPageFileInvalid", param);
	}
	
	/**
	 * ���C�ɓ���t�H���_�����X�V����B
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @param userId
	 *            ���O�C�����[�U�[ID
	 * @return 
	 *            ���C�ɓ��薾�׃f�[�^���X�g
	 */
	public void updateHotPageFileTitle(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_TITLE", formBean.getHotPageTitle());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.update("hotPage.updateHotPageFileTitle", param);
	}
	
	/**
	 * �t�H���_ID�ʂ��C�ɓ�����^�C�g�����폜����B
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @param userId
	 *            ���O�C�����[�U�[ID
	 * @return 
	 *            ���C�ɓ��薾�׃f�[�^���X�g
	 */
	public void deleteHotPageAll(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// ���C�ɓ���t�H���_ID
		param.put("PARA_HOT_PAGE_ID", formBean.getHotPageId());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.update("hotPage.deleteHotPage", param);
	}
	
	/**
	 * �y�[�WID�ʂ��C�ɓ�����^�C�g�����폜����B
	 * @param formBean
	 *            ���C�ɓ���r��
	 * @param userId
	 *            ���O�C�����[�U�[ID
	 * @return 
	 *            ���C�ɓ��薾�׃f�[�^���X�g
	 */
	public void deleteHotPageSingle(HotPageFormBean formBean, String userId) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// ���O�C�����[�U�[
		param.put("PARA_USER_ID", userId);
		// �y�[�WID
		param.put("PARA_PAGE_ID", formBean.getPageId());
		// SUB���C�ɓ���f�[�^�擾SQL
		this.sqlSessionTemplate.update("hotPage.deleteHotPage", param);
	}
	
}