package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupDetailBean;

/**
 * ���[�U�O���[�v���@�\Dao�����N���X
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/04		    NTS        	       �쐬
 * 1.1      2017/11/17          NTS            �������C��
 **/
@Repository
public class UserGroupDao extends BaseDao {

	/**
	 *  ���[�U�O���[�v���̂ɂ���āA���[�U�O���[�v�������
	 * @param searchSql�@����sql
	 * @param userGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<UserGroupBean> getUserGroupList(String searchSql, UserGroupBean userGroupBean, String strOrderBy) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�O���[�v����
		param.put("PARA_USER_GROUP_NAME", userGroupBean.getSearchUserGroupName());
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}
	
	/**
	 * ���[�U�O���[�vID�ƌ����e�[�u���ɂ���āA���[�U�O���[�v�����擾����B
	 * @param userGroupId�@���[�U�O���[�vID
	 * @param userGroupTable�@�������[�U�O���[�v�e�[�u��
	 * @return�@��������
	 */
	public UserGroupBean getUserGroupHeader(String userGroupId, String userGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�O���[�vID
		param.put("PARA_USER_GROUP_ID", userGroupId);
		// ���[�U�O���[�v��񌟍��e�[�u��
		param.put("PARA_USER_GROUP_TABLE", userGroupTable);
		return this.sqlSessionTemplate.selectOne("userGroup.getUserGroupHeader", param);
	}
	
	/**
	 * ���[�U�O���[�vID�ƌ����e�[�u���ɂ���āA���[�U�O���[�v��������L���̂݃��[�U�����擾����B
	 * @param userGroupId�@���[�U�O���[�vID
	 * @param userGroupTable�@�������[�U�O���[�v�e�[�u��
	 * @return�@��������
	 */
	public List<UserGroupDetailBean> getUserGroupDetail(String userGroupId, String userGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�O���[�vID
		param.put("PARA_USER_GROUP_ID", userGroupId);
		// ���[�U�O���[�v��񌟍��e�[�u��
		param.put("PARA_USER_GROUP_TABLE", userGroupTable);
		return this.sqlSessionTemplate.selectList("userGroup.getUserGroupDetail", param);
	}
	
	/**
	 * ���[�U�O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewUserGroupId() {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("userGroup.getNewUserGroupId");
	}

	/**
	 * �o�^�σf�[�^�Ƃ̃��[�U�O���[�v���̏d���f�[�^���擾
	 * @param userGroupBean 
	 */
	public int getUserGroupNmCheckInfo(UserGroupBean userGroupBean, String eventType) {
		// ��������
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_GROUP_NAME", userGroupBean.getUserGroupName());
		param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//�@DB���猟�����ʂ��擾
		return (Integer)this.sqlSessionTemplate.selectOne("userGroup.getUserGroupNm", param);
	}

	/**
	 * ���[�U�O���[�v���o�^
	 * @param userGroupBean 
	 * 
	 */
	public void insertUserGroup(UserGroupBean userGroupBean) {
		this.sqlSessionTemplate.insert("userGroup.insertUserGroup", userGroupBean);
	}

	/**
	 * ���[�U�O���[�v�̃��[�U���o�^
	 * @param userGroupDetailBean 
	 * 
	 */
	public void insertUserGroupDetail(UserGroupDetailBean userGroupDetailBean) {
		this.sqlSessionTemplate.insert("userGroup.insertUserGroupDetail", userGroupDetailBean);
	}

	/**
	 * ���[�U�O���[�v���𕨗��폜����B
	 * @param param 
	 */
	public void deleteUserGroupInfo(Map<String, Object> param) {
		// ���[�U�O���[�v�w�b�_�[���i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("userGroup.deleteUserGroupHeader", param);
		// ���[�U�O���[�v���׏����i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("userGroup.deleteUserGroupDetail", param);
	}

	/**
	 * ���[�U�O���[�v���e�[�u����_���폜����B
	 * @param userId
	 * @param userGroupBean
	 */
	public void updateUserGroupInfoInvalid(String userId, UserGroupBean userGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�O���[�vID
		param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());
		// ���[�U�O���[�v���f�\�����
		param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupBean.getUgRefDate());
		param.put("PARA_UPDATE_BY", userId);
		// ���[�U�O���[�v�w�b�_�[���e�[�u����_���폜����B
		this.sqlSessionTemplate.update("userGroup.updateUserGroupHeaderInvalid", param);
		// ���[�U�O���[�v���׏��e�[�u����_���폜����B
		this.sqlSessionTemplate.update("userGroup.updateUserGroupDetailInvalid", param);
	}
}