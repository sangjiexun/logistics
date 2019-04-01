package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;

/**
 * �g�b�v�O���[�v���@�\Dao�����N���X
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/22		    NTS        	       �쐬
 *
 **/
@Repository
public class TopGroupDao extends BaseDao {

	/**
	 * �g�b�v�O���[�v���׏��e�[�u����_���폜����B
	 * @param userId
	 * @param subGroupId
	 * @param subGroupFlag
	 */
	public void updateTopGroupDetailInvalid(String userId, String subGroupId, String subGroupFlag) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SUB_GROUP_ID", subGroupId);
		param.put("PARA_SUB_GROUP_FLAG", subGroupFlag);
		param.put("PARA_UPDATE_BY", userId);
		this.sqlSessionTemplate.delete("topGroup.updateTopGroupDetailInvalid", param);
	}
	
	/**
	 *  �g�b�v�O���[�v���̂ɂ���āA�g�b�v�O���[�v�������
	 * @param searchSql�@����sql
	 * @param topGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<TopGroupBean> getTopGroupList(String searchSql, TopGroupBean topGroupBean, String strOrderBy) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�O���[�v����
		param.put("PARA_TOP_GROUP_NAME", topGroupBean.getSearchTopGroupName());
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}

	/**
	 * �g�b�v�O���[�vID�ƌ����e�[�u���ɂ���āA�g�b�v�O���[�v�����擾����B
	 * @param topGroupId�@�g�b�v�O���[�vID
	 * @param topGroupTable�@�����g�b�v�O���[�v�e�[�u��
	 * @return�@��������
	 */
	public TopGroupBean getTopGroupHeader(String topGroupId, String topGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// �g�b�v�O���[�v��񌟍��e�[�u��
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		return this.sqlSessionTemplate.selectOne("topGroup.getTopGroupHeader", param);
	}

	/**
	 * �g�b�v�O���[�vID�ƌ����e�[�u���ɂ���āA�g�b�v�O���[�v��������L���̂ݕ����O���[�v�����擾����B
	 * @param topGroupId�@�g�b�v�O���[�vID
	 * @param topGroupTable�@�����g�b�v�O���[�v�e�[�u��
	 * @param deptGroupTable�@���������O���[�v�e�[�u��
	 * @return�@��������
	 */
	public List<TopGroupDetailBean> getTopGroupDetail_DeptGroup(String topGroupId, String topGroupTable, String deptGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// �g�b�v�O���[�v��񌟍��e�[�u��
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		// �����O���[�v��񌟍��e�[�u��
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectList("topGroup.getTopGroupDetail_DeptGroup", param);
	}
	
	/**
	 * �g�b�v�O���[�vID�ƌ����e�[�u���ɂ���āA�g�b�v�O���[�v��������L���̂ݓX�܃O���[�v�����擾����B
	 * @param topGroupId�@�g�b�v�O���[�vID
	 * @param topGroupTable�@�����g�b�v�O���[�v�e�[�u��
	 * @param storeGroupTable�@�����X�܃O���[�v�e�[�u��
	 * @param loginUser ���[�U
	 * @return�@��������
	 */
	public List<TopGroupDetailBean> getTopGroupDetail_StoreGroup(String topGroupId, String topGroupTable, String storeGroupTable, LoginUserBean loginUser) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// �g�b�v�O���[�v��񌟍��e�[�u��
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		// �X�܃O���[�v��񌟍��e�[�u��
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		// ����
		param.put("PARA_ROLE", loginUser.getRole());
		// ���O�C�����[�UID
		param.put("PARA_USER_ID", loginUser.getUserId());
		return this.sqlSessionTemplate.selectList("topGroup.getTopGroupDetail_StoreGroup", param);
	}
	
	/**
	 * �g�b�v�O���[�vID�ƌ����e�[�u���ɂ���āA�g�b�v�O���[�v��������L���̂݃��[�U�O���[�v�����擾����B
	 * @param topGroupId�@�g�b�v�O���[�vID
	 * @param topGroupTable�@�����g�b�v�O���[�v�e�[�u��
	 * @param userGroupTable�@�������[�U�O���[�v�e�[�u��
	 * @return�@��������
	 */
	public List<TopGroupDetailBean> getTopGroupDetail_UserGroup(String topGroupId, String topGroupTable, String userGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// �g�b�v�O���[�v��񌟍��e�[�u��
		param.put("PARA_LEADING_GROUP_TABLE", topGroupTable);
		// ���[�U�O���[�v��񌟍��e�[�u��
		param.put("PARA_USER_GROUP_TABLE", userGroupTable);
		return this.sqlSessionTemplate.selectList("topGroup.getTopGroupDetail_UserGroup", param);
	}

	/**
	 * �g�b�v�O���[�v���𕨗��폜����B
	 * @param param 
	 */
	public void deleteTopGroupInfo(Map<String, Object> param) {
		// �g�b�v�O���[�v�w�b�_�[���i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("topGroup.deleteTopGroupHeader", param);
		// �g�b�v�O���[�v���׏����i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("topGroup.deleteTopGroupDetail", param);
	}

	/**
	 * �g�b�v�O���[�v���e�[�u����_���폜����B
	 * @param userId
	 * @param topGroupBean
	 */
	public void updateTopGroupInfoInvalid(String userId, TopGroupBean topGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());
		// �g�b�v�O���[�v���f�\�����
		param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupBean.getLgRefDate());
		param.put("PARA_UPDATE_BY", userId);
		// �g�b�v�O���[�v�w�b�_�[���e�[�u����_���폜����B
		this.sqlSessionTemplate.update("topGroup.updateTopGroupHeaderInvalid", param);
		// �g�b�v�O���[�v���׏��e�[�u����_���폜����B
		this.sqlSessionTemplate.update("topGroup.updateTopGroupDetailInvalid_ForTop", param);
		
	}

	/**
	 * �R���e���c�{�������g�b�v�O���[�v����_���폜����
	 * @param userId
	 * @param topGroupId
	 */
	public void updateAuthLeadingGroupInvalid(String userId, String topGroupId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		param.put("PARA_UPDATE_BY", userId);
		// �R���e���c�{�������g�b�v�O���[�v����_���폜����
		this.sqlSessionTemplate.update("topGroup.updateAuthLeadingGroupInvalid", param);
	}

	/**
	 * �R���e���c�{�������g�b�v�O���[�v�\����𕨗��폜����B
	 * @param topGroupId
	 */
	public void deleteAuthLeadingGroupRsv(String topGroupId) {
		Map<String, Object> param = new HashMap<String, Object>();
		// �g�b�v�O���[�vID
		param.put("PARA_TOP_GROUP_ID", topGroupId);
		// �R���e���c�{�������g�b�v�O���[�v�\����𕨗��폜����B
		this.sqlSessionTemplate.delete("topGroup.deleteAuthLeadingGroupRsv", param);
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̃g�b�v�O���[�v���̏d���f�[�^���擾
	 * @param topGroupBean 
	 */
	public int getTopGroupNmCheckInfo(TopGroupBean topGroupBean, String eventType) {
		// ��������
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_NAME", topGroupBean.getTopGroupName());
		param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//�@DB���猟�����ʂ��擾
		return (Integer)this.sqlSessionTemplate.selectOne("topGroup.getTopGroupNm", param);
	}
	
	/**
	 * �g�b�v�O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewTopGroupId() {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("topGroup.getNewTopGroupId");
	}
	
	/**
	 * �g�b�v�O���[�v�w�b�_���o�^
	 * @param topGroupBean 
	 * 
	 */
	public void insertTopGroupHeader(TopGroupBean topGroupBean) {
		this.sqlSessionTemplate.insert("topGroup.insertTopGroupHeader", topGroupBean);
	}

	/**
	 * �g�b�v�O���[�v�̃T�u�O���[�v���o�^
	 * @param topGroupDetailBean 
	 * 
	 */
	public void insertTopGroupDetail(TopGroupDetailBean topGroupDetailBean) {
		this.sqlSessionTemplate.insert("topGroup.insertTopGroupDetail", topGroupDetailBean);
	}
}