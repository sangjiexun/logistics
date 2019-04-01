package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupBean;
import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupDetailBean;

/**
 * �l�p�g�b�v�O���[�v���@�\Dao�����N���X
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2016/02/02
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/02/02		    NTS        	       �쐬
 *
 **/
@Repository
public class PersonalTopGroupDao extends BaseDao {

	/**
	 * �l�p�g�b�v�O���[�v�������
	 * @param personalTopGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<PersonalTopGroupBean> getPersTopGroupList(PersonalTopGroupBean personalTopGroupBean, String strOrderBy, LoginUserBean loginUser) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �l�p�g�b�v�O���[�v����
		param.put("PARA_TOP_GROUP_NAME", personalTopGroupBean.getSearchTopGroupName());
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		// ����
		param.put("PARA_ROLE", loginUser.getRole());
		// ���O�C�����[�UID
		param.put("PARA_USER_ID", loginUser.getUserId());
		return this.sqlSessionTemplate.selectList("personalTopGroup.getPersTopGroupList", param);
	}

	/**
	 * �l�p�g�b�v�O���[�vID�ɂ���āA�l�p�g�b�v�O���[�v�����擾����B
	 * @param personalTopGroupId�@�l�p�g�b�v�O���[�vID
	 * @return�@��������
	 */
	public PersonalTopGroupBean getPersTopGroupHeader(String personalTopGroupId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupId);
		return this.sqlSessionTemplate.selectOne("personalTopGroup.getPersTopGroupHeader", param);
	}

	/**
	 * �l�p�g�b�v�O���[�vID�ɂ���āA�l�p�g�b�v�O���[�v��������L���̂݃��[�U���X�g�����擾����B
	 * @param personalTopGroupId�@�l�p�g�b�v�O���[�vID
	 * @return�@��������
	 */
	public List<PersonalTopGroupDetailBean> getPersTopGroupDetail_PersUser(String personalTopGroupId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupId);
		return this.sqlSessionTemplate.selectList("personalTopGroup.getPersTopGroupDetail_PersUser", param);
	}

	/**
	 * �l�p�g�b�v�O���[�vID�ɂ���āA�l�p�g�b�v�O���[�v�̋��L�ҏ����擾����B
	 * @param personalTopGroupId�@�l�p�g�b�v�O���[�vID
	 * @return�@��������
	 */
	public List<PersonalTopGroupDetailBean> getPersTopGroupDetail_ShareUser(String personalTopGroupId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupId);
		return this.sqlSessionTemplate.selectList("personalTopGroup.getPersTopGroupDetail_ShareUser", param);
	}
	
	/**
	 * �l�p���[�U���X�g����_���폜����B
	 * @param userId
	 * @param personalTopGroupBean
	 */
	public void updatePersUserGroupInvalid(String userId, PersonalTopGroupBean personalTopGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());
		param.put("PARA_UPDATE_BY", userId);
		// �g�b�v�O���[�v���׏��e�[�u����_���폜����B
		this.sqlSessionTemplate.update("personalTopGroup.updatePersUserGroupDetailInvalid", param);
		// �g�b�v�O���[�v�w�b�_�[���e�[�u����_���폜����B
		this.sqlSessionTemplate.update("personalTopGroup.updatePersUserGroupHeaderInvalid", param);
	}

	/**
	 * ���L�Ώێ҂�_���폜����B
	 * @param userId
	 * @param personalTopGroupBean
	 */
	public void updateTopGroupShareInvalid(String userId, PersonalTopGroupBean personalTopGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());
		param.put("PARA_UPDATE_BY", userId);
		// �g�b�v�O���[�v�w�b�_�[���e�[�u����_���폜����B
		this.sqlSessionTemplate.update("personalTopGroup.updateTopGroupShareInvalid", param);
	}
	
	/**
	 * �g�b�v�O���[�v�̋��L�Ώێҏ��o�^
	 * @param shareUserBean 
	 * 
	 */
	public void insertShareUser(PersonalTopGroupDetailBean shareUserBean) {
		this.sqlSessionTemplate.insert("personalTopGroup.insertShareUser", shareUserBean);
	}

	/**
	 * �l���[�U���X�g���𕨗��폜����
	 * @param param 
	 * 
	 */
	public void deletePersUserGroupInvalid(Map<String, Object> param) {
		// �l���[�U���׏��𕨗��폜����B
		this.sqlSessionTemplate.delete("personalTopGroup.deletePersUserGroupDetail", param);
		// �l���[�U�w�b�_�[���𕨗��폜����B
		this.sqlSessionTemplate.delete("personalTopGroup.deletePersUserGroupHeader", param);
	}

	/**
	 * ���L�ҏ��𕨗��폜����
	 * @param param 
	 * 
	 */
	public void deleteTopGroupShareInvalid(Map<String, Object> param) {
		// ���L�ҏ��𕨗��폜����B
		this.sqlSessionTemplate.delete("personalTopGroup.deleteTopGroupShare", param);
	}
}