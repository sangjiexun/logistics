package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupBean;
import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupDetailBean;

/**
 * �X�܃O���[�v���@�\Dao�����N���X
 * 
 * File Name: StoreGroupDao.java 
 * Created: 2016/01/08
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/08		    NTS        	       �쐬
 * 1.1		2017/12/05			NTS			�������C��
 *
 **/
@Repository
public class StoreGroupDao extends BaseDao {

	/**
	 *  �X�܃O���[�v���̂ɂ���āA�X�܃O���[�v�������
	 * @param loginUser ���[�U
	 * @param searchSql�@����sql
	 * @param storeGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<StoreGroupBean> getStoreGroupList(String searchSql, StoreGroupBean storeGroupBean, String strOrderBy, LoginUserBean loginUser) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�v����
		param.put("PARA_STORE_GROUP_NAME", storeGroupBean.getSearchStoreGroupName());
		// ����
		param.put("PARA_ROLE", loginUser.getRole());
		// ���O�C�����[�UID
		param.put("PARA_USER_ID", loginUser.getUserId());
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}
	
	/**
	 * �X�܃O���[�vID�ƌ����e�[�u���ɂ���āA�X�܃O���[�v�����擾����B
	 * @param storeGroupId�@�X�܃O���[�vID
	 * @param storeGroupTable�@�����X�܃O���[�v�e�[�u��
	 * @return�@��������
	 */
	public StoreGroupBean getStoreGroupHeader(String storeGroupId, String storeGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// �X�܃O���[�v��񌟍��e�[�u��
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		return this.sqlSessionTemplate.selectOne("storeGroup.getStoreGroupHeader", param);
	}
	
	/**
	 * �X�܃O���[�vID�ƌ����e�[�u���ɂ���āA�X�܃O���[�v��������L���̂ݓX�܏����擾����B
	 * @param storeGroupId�@�X�܃O���[�vID
	 * @param storeGroupTable�@�����X�܃O���[�v�e�[�u��
	 * @param storeFcDisplay�@�X�܋敪���́iFC�A���c�j�uY:���̕\���v
	 * @return�@��������
	 */
	public List<StoreGroupDetailBean> getStoreGroupDetail(String storeGroupId, String storeGroupTable, String storeFcDisplay) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// �X�܃O���[�v��񌟍��e�[�u��
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		// �X��FC�敪��
		param.put("PARA_STORE_FC_DISPLAY", storeFcDisplay);
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreGroupDetail", param);
	}
	
	/**
	 * �X�܃O���[�vID�ƌ����e�[�u���ɂ���āA�X�܃O���[�v�̓X�܏����擾����B
	 * @param storeGroupId�@�X�܃O���[�vID
	 * @return�@��������
	 */
	public List<StoreGroupDetailBean> getStoreDetail(String storeGroupId, String storeGroupTable, String storeFcDisplay) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// �X�܃O���[�v��񌟍��e�[�u��
		param.put("PARA_STORE_GROUP_TABLE", storeGroupTable);
		// �X��FC�敪��
		param.put("PARA_STORE_FC_DISPLAY", storeFcDisplay);
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreDetail", param);
	}
	
	/**
	 * �X�܃O���[�vID�ƌ����e�[�u���ɂ���āA�X�܃O���[�v��������L���̂݋��L�Ώێҏ����擾����B
	 * @param sgRefDate 
	 * @param storeGroupId�@�X�܃O���[�vID
	 * @return�@��������
	 */
	public List<StoreGroupDetailBean> getShareUserList(String storeGroupId, String sgRefDate) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupId);
		// ���f�\���
		param.put("PARA_REFLECTION_SCHEDULE_DATE", sgRefDate);
		return this.sqlSessionTemplate.selectList("storeGroup.getShareUserList", param);
	}
	
	/**
	 * �X�܃O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewStoreGroupId() {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("storeGroup.getNewStoreGroupId");
	}

	/**
	 * �o�^�σf�[�^�Ƃ̓X�܃O���[�v���̏d���f�[�^���擾
	 * @param storeGroupBean 
	 */
	public int getStoreGroupNmCheckInfo(StoreGroupBean storeGroupBean, String eventType) {
		// ��������
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_GROUP_NAME", storeGroupBean.getStoreGroupName());
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//�@DB���猟�����ʂ��擾
		return (Integer)this.sqlSessionTemplate.selectOne("storeGroup.getStoreGroupNm", param);
	}

	/**
	 * �X�܃O���[�v���o�^
	 * @param storeGroupBean 
	 * 
	 */
	public void insertStoreGroup(StoreGroupBean storeGroupBean) {
		this.sqlSessionTemplate.insert("storeGroup.insertStoreGroup", storeGroupBean);
	}

	/**
	 * �X�܃O���[�v�̓X�܏��o�^
	 * @param storeGroupDetailBean 
	 * 
	 */
	public void insertStoreGroupDetail(StoreGroupDetailBean storeGroupDetailBean) {
		this.sqlSessionTemplate.insert("storeGroup.insertStoreGroupDetail", storeGroupDetailBean);
	}
	
	/**
	 * �X�܃O���[�v�̋��L�Ώێҏ��o�^
	 * @param shareUserBean 
	 * 
	 */
	public void insertShareUser(StoreGroupDetailBean shareUserBean) {
		this.sqlSessionTemplate.insert("storeGroup.insertShareUser", shareUserBean);
	}

	/**
	 * �X�܃O���[�v���𕨗��폜����B
	 * @param param 
	 */
	public void deleteStoreGroupInfo(Map<String, Object> param) {
		// �X�܃O���[�v�w�b�_�[���i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("storeGroup.deleteStoreGroupHeader", param);
		// �X�܃O���[�v���׏����i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("storeGroup.deleteStoreGroupDetail", param);
		// �X�܃O���[�v���L�Ώێҁi�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("storeGroup.deleteStoreGroupShareUser", param);
	}

	/**
	 * �X�܃O���[�v���e�[�u����_���폜����B
	 * @param userId
	 * @param storeGroupBean
	 */
	public void updateStoreGroupInfoInvalid(String userId, StoreGroupBean storeGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		// �X�܃O���[�v���f�\�����
		param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupBean.getSgRefDate());
		// ���[�UID
		param.put("PARA_UPDATE_BY", userId);
		// �X�܃O���[�v�w�b�_�[���e�[�u����_���폜����B
		this.sqlSessionTemplate.update("storeGroup.updateStoreGroupHeaderInvalid", param);
		// �X�܃O���[�v���׏��e�[�u����_���폜����B
		this.sqlSessionTemplate.update("storeGroup.updateStoreGroupDetailInvalid", param);
		// �X�܃O���[�v���L�Ώێҁi�\��j��_���폜����B
		this.sqlSessionTemplate.update("storeGroup.updateStoreGroupShareUserInvalid", param);
	}

	/**
	 * �X�܌��������s�i�g�D�j
	 * @param param 
	 */
	public List<StoreGroupDetailBean> getStoreList_left(Map<String, Object> param) {
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreList_left", param);
	}
	
	/**
	 * �X�܌��������s
	 * @param param 
	 */
	public List<StoreGroupDetailBean> getStoreList_right(Map<String, Object> param) {
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("storeGroup.getStoreList_right", param);
	}
	
	/**
	 * ����X�܃O���[�v�����l�����o�^
	 * @param param 
	 */
	public void insertDepartmentStoreGroupUser(StoreGroupBean storeGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		this.sqlSessionTemplate.insert("storeGroup.insertDepartmentStoreGroupUser", param);
	}
	
	/**
	 * ����X�܃O���[�v�����l�����폜
	 * @param param 
	 */
	public void deleteDepartmentStoreGroupUser(StoreGroupBean storeGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		this.sqlSessionTemplate.delete("storeGroup.deleteDepartmentStoreGroupUser", param);
	}
	
	/**
	 * ���������N���N���b�N�A�K�w���x������
	 * @param param
	 * @return�@��������
	 */
	public String getDeptLevel(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("storeGroup.getDeptLevel", param);
	}
	
	/**
	 * ��Ѓ����N���N���b�N�A������������
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("storeGroup.getSecondDeptList", param);
	}
	
	/**
	 * ���������N���N���b�N�A�������ƌ���
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("storeGroup.getThirdDeptList", param);
	}
	
	/**
	 * ���ƃ����N���N���b�N�A�����c�ƕ�����
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("storeGroup.getFourthDeptList", param);
	}
	
	/**
	 * �c�ƕ������N���N���b�N�A�����X�܌���
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getFifthDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("storeGroup.getFifthDeptList", param);
	}
}