package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupBean;
import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupDetailBean;

/**
 * �����O���[�v���@�\Dao�����N���X
 * 
 * File Name: DeptGroupDao.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/22		    NTS        	       �쐬
 * 1.1		2017/12/05			NTS			�������C��
 *
 **/
@Repository
public class DeptGroupDao extends BaseDao {

	/**
	 *  �����O���[�v���̂ɂ���āA�����O���[�v�������
	 * @param searchSql�@����sql
	 * @param deptGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<DeptGroupBean> getDeptGroupList(String searchSql, DeptGroupBean deptGroupBean, String strOrderBy) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �����O���[�v����
		param.put("PARA_DEPARTMENT_GROUP_NAME", deptGroupBean.getSearchDeptGroupName());
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		return this.sqlSessionTemplate.selectList(searchSql, param);
	}
	
	/**
	 * �����O���[�vID�ƌ����e�[�u���ɂ���āA�����O���[�v�����擾����B
	 * @param deptGroupId�@�����O���[�vID
	 * @param deptGroupTable�@���������O���[�v�e�[�u��
	 * @return�@��������
	 */
	public DeptGroupBean getDeptGroupHeader(String deptGroupId, String deptGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �����O���[�vID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupId);
		// �����O���[�v��񌟍��e�[�u��
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectOne("deptGroup.getDeptGroupHeader", param);
	}
	
	/**
	 * �����O���[�vID�ƌ����e�[�u���ɂ���āA�����O���[�v��������L���̂ݕ��������擾����B
	 * @param deptGroupId�@�����O���[�vID
	 * @param deptGroupTable�@���������O���[�v�e�[�u��
	 * @param departmentTable�@���������e�[�u��
	 * @return�@��������
	 */
	public List<DeptGroupDetailBean> getDeptGroupDetail(String deptGroupId, String deptGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �����O���[�vID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupId);
		// �����O���[�v��񌟍��e�[�u��
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptGroupDetail", param);
	}
	
	/**
	 * �����O���[�vID�ƌ����e�[�u���ɂ���āA�����O���[�v�̕��������擾����B
	 * @param deptGroupId�@�����O���[�vID
	 * @return�@��������
	 */
	public List<DeptGroupDetailBean> getDeptDetail(String deptGroupId, String deptGroupTable) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �����O���[�vID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupId);
		// �����O���[�v��񌟍��e�[�u��
		param.put("PARA_DEPARTMENT_GROUP_TABLE", deptGroupTable);
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptDetail", param);
	}
	
	/**
	 * ������������
	 * @param param
	 * @return�@��������
	 */
	public List<DeptGroupDetailBean> getDeptList_left(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptList_left", param);
	}

	/**
	 * ������������
	 * @param param
	 * @return�@��������
	 */
	public List<DeptGroupDetailBean> getDeptList_right(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("deptGroup.getDeptList_right", param);
	}
	
	/**
	 * �����O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewDeptGroupId() {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("deptGroup.getNewDeptGroupId");
	}

	/**
	 * �o�^�σf�[�^�Ƃ̕����O���[�v���̏d���f�[�^���擾
	 * @param deptGroupBean 
	 */
	public int getDeptGroupNmCheckInfo(DeptGroupBean deptGroupBean, String eventType) {
		// ��������
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_GROUP_NAME", deptGroupBean.getDeptGroupName());
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		param.put("PARA_EVENT_TYPE", eventType);
		//�@DB���猟�����ʂ��擾
		return (Integer)this.sqlSessionTemplate.selectOne("deptGroup.getDeptGroupNm", param);
	}

	/**
	 * �����O���[�v���o�^
	 * @param deptGroupBean 
	 * 
	 */
	public void insertDeptGroup(DeptGroupBean deptGroupBean) {
		this.sqlSessionTemplate.insert("deptGroup.insertDeptGroup", deptGroupBean);
	}

	/**
	 * �����O���[�v�̕������o�^
	 * @param deptGroupDetailBean 
	 * 
	 */
	public void insertDeptGroupDetail(DeptGroupDetailBean deptGroupDetailBean) {
		this.sqlSessionTemplate.insert("deptGroup.insertDeptGroupDetail", deptGroupDetailBean);
	}

	/**
	 * �����O���[�v���𕨗��폜����B
	 * @param deptGroupBean 
	 * @param deleteFlag reserve:�\����폜  both:�\��ƌ��J���폜
	 */
	public void deleteDeptGroupInfo(Map<String, Object> param) {
		// �����O���[�v�w�b�_�[���i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("deptGroup.deleteDeptGroupHeader", param);
		// �����O���[�v���׏����i�\��j�𕨗��폜����B
		this.sqlSessionTemplate.delete("deptGroup.deleteDeptGroupDetail", param);
	}

	/**
	 * ����O���[�v���e�[�u����_���폜����B
	 * @param userId
	 * @param deptGroupBean
	 */
	public void updateDeptGroupInfoInvalid(String userId, DeptGroupBean deptGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �����O���[�vID
		param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		// �����O���[�v���f�\�����
		param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupBean.getDgRefDate());
		// �X�V��
		param.put("PARA_UPDATE_BY", userId);
		// ����O���[�v�w�b�_�[���e�[�u����_���폜����B
		this.sqlSessionTemplate.update("deptGroup.updateDeptGroupHeaderInvalid", param);
		// ����O���[�v���׏��e�[�u����_���폜����B
		this.sqlSessionTemplate.update("deptGroup.updateDeptGroupDetailInvalid", param);
	}
	
	/**
	 * ����X�܃O���[�v�����l�����폜
	 * @param param 
	 */
	public void deleteDepartmentStoreGroupUser(DeptGroupBean deptGroupBean){
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_DEPT_GROUP_ID",deptGroupBean.getDeptGroupId());
		this.sqlSessionTemplate.delete("deptGroup.deleteDepartmentStoreGroupUser", param);
	}
	
	/**
	 * ����X�܃O���[�v�����l�����o�^
	 * @param param 
	 */
	public void insertDepartmentStoreGroupUser(DeptGroupBean deptGroupBean){
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�܃O���[�vID
		param.put("PARA_DEPT_GROUP_ID",deptGroupBean.getDeptGroupId());
		this.sqlSessionTemplate.insert("deptGroup.insertDepartmentStoreGroupUser", param);
	}
	
	/**
	 * ���������N���N���b�N�A�K�w���x������
	 * @param param
	 * @return�@��������
	 */
	public String getDeptLevel(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectOne("deptGroup.getDeptLevel", param);
	}
	
	/**
	 * ��Ѓ����N���N���b�N�A������������
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("deptGroup.getSecondDeptList", param);
	}
	
	/**
	 * ���������N���N���b�N�A�������ƌ���
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("deptGroup.getThirdDeptList", param);
	}
	
	/**
	 * ���ƃ����N���N���b�N�A�����c�ƕ�����
	 * @param param
	 * @return�@��������
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("deptGroup.getFourthDeptList", param);
	}
}