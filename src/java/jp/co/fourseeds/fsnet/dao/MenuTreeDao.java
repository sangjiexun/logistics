package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MenuTreeBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;

/**
 * <p>
 * Menu��Tree���쐬����Dao
 * </p>
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 �V�K�쐬
 *
 **/
@Repository
public class MenuTreeDao extends BaseDao {

	/**
	 * �V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҂̏ꍇ�AMenu���쐬����N�G��
	 * 
	 */
	public List<MenuTreeBean> getMenuList() {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getMenuAllList", param);
	}
	
	/**
	 * profileMenu���쐬����N�G��
	 * 
	 */
	public MenuTreeBean getProfileInfo() {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectOne("menuTree.getProfileInfo", param);
	}
	
	/**
	 * �V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҈ȊO�̏ꍇ�AMenu���쐬����N�G��
	 * 
	 */
	public List<MenuTreeBean> getMenuList(String userId, List<String> groupList, String role) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_GROUP", CommonUtil.getGroupSql(groupList));
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getMenuList", param);
	}

	/**
	 * ���n���ꂽPageID�ɂ��A���ׂĂ̐ePageID�𒊏o���Ă���List���i�[����
	 */
	public String getAllParentPageList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGEID", pageId);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectOne("menuTree.getAllParentPageList", param);
	}
	
	/**
	 * �V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҈ȊO�̏ꍇ�ATree���쐬����
	 */
	public List<MenuTreeBean> getTreeListByRole(LoginUserBean loginUser, String parentPageId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", loginUser.getUserId());
		param.put("PARA_GROUP", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_PID", parentPageId);
		param.put("PARA_CONFIRM_FLAG", loginUser.getConfirmFlag());
		param.put("PARA_FETURE_FLAG", loginUser.getFetureFlag());
		param.put("PARA_TEMPLATE_FLAG", loginUser.getTemplateFlag());
		param.put("PARA_PAST_FLAG", loginUser.getPastFlag());
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getTreeListByRole", param);
	}

	/**
	 * �V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҂̏ꍇ�ATree���쐬����
	 */
	public List<MenuTreeBean> getTreeListNoRole(String parentPageId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PID", parentPageId);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getTreeListNoRole", param);
	}
		
	/**
	 * �e�y�[�WID�擾
	 */
	public String getParentPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGEID", pageId);

		return this.sqlSessionTemplate.selectOne("menuTree.getParentPageId", param);
	}

	/**
	 * �^���쐬���̃R���e���c
	 */
	public List<MenuTreeBean> getUnConfirmList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getUnConfirmList", param);
	}
	
	/**
	 * �\��쐬���̃R���e���c
	 */
	public List<MenuTreeBean> getUnConfirmListRsv(String userId, String role) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getUnConfirmListRsv", param);
	}

	/**
	 * �^�����J�҂��̃R���e���c
	 */
	public List<MenuTreeBean> getFutureList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getFutureList", param);
	}
	
	/**
	 * �\����J�҂��̃R���e���c
	 */
	public List<MenuTreeBean> getFutureListRsv(String userId, String role) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getFutureListRsv", param);
	}
	
	/**
	 * �e���v���[�g�R���e���c
	 */
	public List<MenuTreeBean> getTemplateList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getTemplateList", param);
	}
	
	/**
	 * �����؂�R���e���c
	 */
	public List<MenuTreeBean> getPastList(String userId, String role) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getPastList", param);
	}
	
	/**
	 * ���������N�J�ڂ��鎞�A���O�C�����[�U�[����PageID�ɂ��{�������`�F�b�N�p
	 */
	public List<MenuTreeBean> getPagesWithPermission(LoginUserBean loginUser, String pageId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_GROUP", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_CONFIRM_FLAG", loginUser.getConfirmFlag());
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getPagesWithPermission", param);
	}

	/**
	 * ���������N�w��Ɛe�R���e���c�w��p��Tree����
	 * 
	 */
	public List<MenuTreeBean> getSubTreeList(String pId, String urlFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PID", pId);
		param.put("PARA_SYSDATE", new Date());
		param.put("PARA_URLFLAG", urlFlag);

		return this.sqlSessionTemplate.selectList("menuTree.getSubTreeList", param);
	}
	
	/**
	 * ���������N�c���[�p�^�����J�҂��̃R���e���c
	 */
	public List<MenuTreeBean> getFutureListForSubTree() {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SYSDATE", new Date());

		return this.sqlSessionTemplate.selectList("menuTree.getFutureListForSubTree", param);
	}
}
