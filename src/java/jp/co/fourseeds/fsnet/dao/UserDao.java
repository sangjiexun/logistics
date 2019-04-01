package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.UserBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;

/**
 * ���[�U���@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/27		    NTS        	       �쐬
 * 1.1		2017/09/05			NTS			�������C��
 *
 **/
@Repository
public class UserDao extends BaseDao {
	
	/**
	 * ���[�U�����擾
	 * @param userBean
	 * @param strOrderBy
	 * @return List
	 */
	public List<UserBean> getUserList(UserBean userBean, String strOrderBy, LoginUserBean loginUser) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		// ����
		param.put("PARA_ROLE", loginUser.getRole());
		// �Ј��ԍ�
		param.put("PARA_USER_ID", userBean.getSearchUserId());
		// ����
		param.put("PARA_USER_NAME", userBean.getSearchUserName());
		// �X��
		param.put("PARA_STORE_NAME", userBean.getSearchStoreName());
		// �{��
		param.put("PARA_DEPT_NAME", userBean.getSearchDeptName());
		// �]�ƈ��敪
		param.put("PARA_PEMPLOYEE_ID", userBean.getSearchPemployeeId());
		// �\���t���O
		param.put("PARA_SHOWFLAG_ID", userBean.getSearchShowFlag());
		// �\�[�g����
		param.put("PARA_ORDER_BY", strOrderBy);
		// �]�ƈ��敪
		param.put("USER_DIVISION_KEYCODE", ConstantContainer.USER_DIVISION_KEYCODE);
		// �X�܋敪
		param.put("STORE_DIVISION_KEYCODE", ConstantContainer.STORE_DIVISION_KEYCODE);
		// ��s�̏ꍇ�A�u�A���o�C�g�i��s�j�v�ŕ\��
		param.put("PARTTIME_NAME", "�i��s�j");
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("user.getUserList", param);
	}
	
	/**
	 * ���[�U�ڍ׏����擾
	 * @param userBean
	 * @return UserBean
	 */
	public UserBean getUserDetailInfo(UserBean userBean) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		// �Ј��ԍ�
		param.put("PARA_USER_ID", userBean.getUserId());
		// �]�ƈ��敪
		param.put("USER_DIVISION_KEYCODE", ConstantContainer.USER_DIVISION_KEYCODE);
		// �]�ƈ��敪
		param.put("SEX_KEYCODE", ConstantContainer.SEX_KEYCODE);
		// �V�X�e�����p�敪
		param.put("ROLE", ConstantContainer.ROLE);
		// ��Ћ敪
		param.put("COMPANY_KEYCODE", ConstantContainer.COMPANY_KEYCODE);
		// �����敪
		param.put("UNIFICATION_KEYCODE", ConstantContainer.UNIFICATION_KEYCODE);
		// ���Ƌ敪
		param.put("BUSINESS_KEYCODE", ConstantContainer.BUSINESS_KEYCODE);
		// �c�ƕ��敪
		param.put("SALES_KEYCODE", ConstantContainer.SALES_KEYCODE);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("user.getUserDetailInfo", param);
	}
	
	/**
	 * �C���[�W�����擾
	 * @param userBean
	 * @return UserBean
	 */
	public byte[] getImgDat(String userId) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("user.getImgDat", param);
	}
	
	/**
	 * ���[�U�C���[�W�������擾
	 * @param userBean
	 * @return ��������
	 */
	public int getImgCount(UserBean userBean) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userBean.getUserId());
		
		//�@DB���猟�����ʂ��擾
		return (Integer)this.sqlSessionTemplate.selectOne("user.getImgCount", param);
	}
	
	/**
	 * ���[�U�C���[�W����o�^
	 * @param userBean
	 */
	public void insertUserImgMaster(UserBean userBean, LoginUserBean loginUser) {
		// �쐬�ҍX�V�҂�ݒ�
		setUserInfo(userBean, loginUser);
		
		this.sqlSessionTemplate.insert("user.insertUserImgMaster", userBean);
	}
	
	/**
	 * ���[�U�C���[�W�����X�V
	 * @param userBean
	 */
	public void updateUserImgMaster(UserBean userBean, LoginUserBean loginUser) {
		// �쐬�ҍX�V�҂�ݒ�
		setUserInfo(userBean, loginUser);
		
		this.sqlSessionTemplate.insert("user.updateUserImgMaster", userBean);
	}
	
	/**
	 * ���[�U�����X�V
	 * @param userBean
	 */
	public void updateUserMaster(UserBean userBean, LoginUserBean loginUser) {
		// �X�V�҂�ݒ�
		setUserInfo(userBean, loginUser);
		userBean.setLoginRoleId(loginUser.getRole());
		
		this.sqlSessionTemplate.update("user.updateUserMaster", userBean);
	}
	
	/**
	 * ���[�U�O���[�v���׏������̕ύX���[�U�����폜
	 * @param userBean
	 */
	public void deleteUserGroup(UserBean userBean) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userBean.getUserId());
		param.put("PARA_OLD_ROLE", userBean.getOldRoleId());
		param.put("USER_GROUP_ROLE", ConstantContainer.USER_GROUP_ROLE);
		this.sqlSessionTemplate.delete("user.deleteUserGroup", param);
	}
	
	/**
	 * ���[�U�O���[�v���擾
	 * @param userBean
	 */
	public UserBean saerchUserGroupInfo(UserBean userBean) {
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ROLE", userBean.getRoleId());
		param.put("USER_GROUP_ROLE", ConstantContainer.USER_GROUP_ROLE);
		return this.sqlSessionTemplate.selectOne("user.searchUserGroup", param);
	}
	
	/**
	 * ���[�U�O���[�v���X�V
	 * @param userBean
	 */
	public void insertUserGroupInfo(UserBean userBean, LoginUserBean loginUser) {
		// �쐬�ҍX�V�҂�ݒ�
		setUserInfo(userBean, loginUser);
		
		this.sqlSessionTemplate.insert("user.insertUserGroup", userBean);
	}
	
	/**
	 * �쐬�ҍX�V�҂�ݒ�
	 * @param userBean
	 */
	public void setUserInfo(UserBean userBean, LoginUserBean loginUser) {
		userBean.setCreateBy(loginUser.getUserId());
		userBean.setUpdateBy(loginUser.getUserId());
	}
}