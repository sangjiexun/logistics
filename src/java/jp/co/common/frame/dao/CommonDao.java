package jp.co.common.frame.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.dao.CommonDao;

@SuppressWarnings("rawtypes")
@Component
public class CommonDao extends BaseDao{

	/**
	 * DB�f�[�^�̍쐬�����擾
	 */
	public BaseBean getDbCommonInfo(String tableName, String idKey, Object idValue) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tableName);
		param.put("PARA_ID_KEY", idKey);
		param.put("PARA_ID_VALUE", idValue);
		
		List rtn = this.sqlSessionTemplate.selectList("common.getDbCommonInfo", param);
		if (rtn != null && rtn.size() > 0) {
			return (BaseBean)rtn.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * �����}�X�^��SelectBox�A�C�e���擾
	 */
	public List<LabelValueBean> getDepartmentList() {
		return this.sqlSessionTemplate.selectList("common.getDepartmentList");
	}
	
	/**
	 * ���X�g�擾
	 * @param param
	 *           ��������
	 * @return
	 *           ���X�g���e
	 */
	public List<LabelValueBean> getDivisionMaster(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getDivisionMaster", param);
	}
	
	/**
	 * �敪���̂��擾
	 * @param param
	 *           ��������
	 * @return
	 *           �敪����
	 */
	public String getDivisionName(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectOne("common.getDivisionName", param);
	}
	
	/**
	 * �\���敪���̂��擾
	 * @param param
	 *           ��������
	 * @return
	 *           �敪����
	 */
	public String getDispDivisionName(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectOne("common.getDispDivisionName", param);
	}
	
	/**
	 * �敪���擾
	 * @param param
	 *           ��������
	 * @return
	 *           �敪
	 */
	public String getDivision(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectOne("common.getDivision", param);
	}
	
	/**
	 * ���[�U�������擾
	 * @param param
	 *           ��������
	 * @return
	 *           �敪����
	 */
	public String getUserName(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectOne("common.getUserName", param);
	}
	
	/**
	 * ���[�U��񌟍��̋��ʃ��\�b�h
	 * �ďo��
	 * �P�j�����O���[�v�ڍ׉��
	 * �Q�j�X�܃O���[�v�ڍ׉��
	 * �R�j�g�b�v�O���[�v�ڍ׉��
	 * 
	 * 
	 * �������iV_NXXA00P�j�������Ƃ��Ĉ��n��
	 * @param ��������
	 * @return �敪?�Ƌ敪���e
	 */
	public List<Map<String, String>> getUserList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getUserList", param);
	}
	
	/**
	 * ���L���[�U��񌟍��̋��ʃ��\�b�h
	 * �ďo��
	 * �P�j�l�g�b�v�O���[�v�̋��L�Ҍ���
	 * �R�j�X�܃O���[�v�̋��L���[�U����
	 * 
	 * @param ��������
	 * @return �敪?�Ƌ敪���e
	 */
	public List<Map<String, String>> getShareUserList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getShareUserList", param);
	}

	/**
	 * �����O���[�v���X�g���擾
	 * @param groupTableName ���������O���[�v�e�[�u����
	 * @return �����O���[�v���X�g
	 */
	public List<LabelValueBean> getDeptGroupList_ForTop(String groupTableName, String deptGroupName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_GROUP_TABLE", groupTableName);
		param.put("PARA_DEPARTMENT_GROUP_NAME", deptGroupName);
		return this.sqlSessionTemplate.selectList("common.getDeptGroupList_ForTop", param);
	}

	/**
	 * �X�܃O���[�v���X�g���擾
	 * @param groupTableName �����X�܃O���[�v�e�[�u����
	 * @param role ����
	 * @param userId ���[�UID
	 * @return �X�܃O���[�v���X�g
	 */
	public List<LabelValueBean> getStoreGroupList_ForTop(String storesql, String groupTableName, String role, String userId, String storeGroupName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STORE_GROUP_TABLE", groupTableName);
		param.put("PARA_ROLE", role);
		param.put("PARA_USER_ID", userId);
		param.put("PARA_STORE_GROUP_NAME", storeGroupName);
		return this.sqlSessionTemplate.selectList(storesql, param);
	}

	/**
	 * ���[�U�O���[�v���X�g���擾
	 * @param groupTableName �������[�U�O���[�v�e�[�u����
	 * @return ���[�U�O���[�v���X�g
	 */
	public List<LabelValueBean> getUserGroupList_ForTop(String groupTableName, String userGroupName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_GROUP_TABLE", groupTableName);
		param.put("PARA_USER_GROUP_NAME", userGroupName);
		return this.sqlSessionTemplate.selectList("common.getUserGroupList_ForTop", param);
	}
	
	/**
	 * ��Ѓ��X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCompanyList() {
		return this.sqlSessionTemplate.selectList("common.getCompanyList");
	}
	
	/**
	 * �������X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getUnificationList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getUnificationList", param);
	}
	
	/**
	 * ���ƃ��X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getBusinessList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getBusinessList", param);
	}
	
	/**
	 * �c�ƃ��X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getSalesList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getSalesList", param);
	}
	
	/**
	 * �e��}�X�^���X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCommonList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getCommonList", param);
	}
	
	/**
	 * ���[�U��񌟍��̋��ʃ��\�b�h
	 * �ďo��
	 * �P�j�l�p�g�b�v�O���[�v�o�^�E�X�V��ʂŃ��[�U����
	 * �Q�j�R���e���c�o�^�E�X�V��ʂŌ��J����l�T�u���
	 * �R�j���[�U���T�u���
	 * �S�j���[�U�O���[�v�o�^�E�X�V��ʂŃ��[�U����
	 * 
	 * �������iV_NXXA00P�j
	 * @param ��������
	 * @return �敪?�Ƌ敪���e
	 */
	public List<Map<String, String>> getUserListByCondition(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("common.getUserListByCondition", param);
	}
}