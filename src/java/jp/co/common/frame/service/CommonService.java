package jp.co.common.frame.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.CommonDao;

@Component
public class CommonService {

	@Autowired
	private CommonDao commonDao;
	
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	
	/**
	 * ���X�g�擾
	 * @param fieldName
	 *           �t�B�[���h��
	 * @return
	 *           ���X�g���e
	 */
	public List<LabelValueBean> getDivisionMaster(String fieldName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", fieldName);
		return commonDao.getDivisionMaster(param);
	}
	
	/**
	 * �敪���̂��擾
	 * @param fieldName
	 *           �t�B�[���h��
	 * @param mailUserDivision
	 *           �敪
	 * @return
	 *           �敪����
	 */
	public String getDivisionName(String field,String mailUserDivision) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", field);
		param.put("PARA_DIVISION", mailUserDivision);
		return commonDao.getDivisionName(param);
	}
	
	/**
	 * �\���敪���̂��擾
	 * @param fieldName
	 *           �t�B�[���h��
	 * @param mailUserDivision
	 *           �敪
	 * @return
	 *           �敪����
	 */
	public String getDispDivisionName(String field, String mailUserDivision) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", field);
		param.put("PARA_DIVISION", mailUserDivision);
		return commonDao.getDispDivisionName(param);
	}
	
	/**
	 * �敪���擾
	 * @param fieldName
	 *           �t�B�[���h��
	 * @return
	 *           �敪
	 */
	public String getDivision(String field) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_FIELD_NAME", field);
		return commonDao.getDivision(param);
	}

	/**
	 * ���[�U���X�g���擾
	 * @param ��������
	 * @return ���[�U���X�g
	 */
	public List<Map<String, String>> getUserList(Map<String, Object> param) {
		return commonDao.getUserList(param);
	}
	
	/**
	 * ���L���[�U���X�g���擾
	 * @param ��������
	 * @return ���[�U���X�g
	 */
	public List<Map<String, String>> getShareUserList(Map<String, Object> param) {
		return commonDao.getShareUserList(param);
	}

	/**
	 * �����O���[�v���X�g���擾
	 * @param groupTableName ���������O���[�v�e�[�u����
	 * @return �����O���[�v���X�g
	 */
	public List<LabelValueBean> getDeptGroupList_ForTop(String groupTableName, String deptGroupName) {
		return commonDao.getDeptGroupList_ForTop(groupTableName,deptGroupName);
	}

	/**
	 * �X�܃O���[�v���X�g���擾
	 * @param groupTableName �����X�܃O���[�v�e�[�u����
	 * @param role ����
	 * @param userId ���[�UID
	 * @return �X�܃O���[�v���X�g
	 */
	public List<LabelValueBean> getStoreGroupList_ForTop(String storesql,String groupTableName, String role, String userId , String storeGroupName) {
		return commonDao.getStoreGroupList_ForTop(storesql,groupTableName, role, userId,storeGroupName);
	}

	/**
	 * ���[�U�O���[�v���X�g���擾
	 * @param groupTableName �������[�U�O���[�v�e�[�u����
	 * @return ���[�U�O���[�v���X�g
	 */
	public List<LabelValueBean> getUserGroupList_ForTop(String groupTableName, String userGroupName) {
		return commonDao.getUserGroupList_ForTop(groupTableName,userGroupName);
	}
	
	/**
	 * ��Ѓ��X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCompanyList() {
		return commonDao.getCompanyList();
	}
	
	/**
	 * �������X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getUnificationList(String companyId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_COMPANY_ID", companyId);
		return commonDao.getUnificationList(param);
	}
	
	/**
	 * ���ƃ��X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getBusinessList(String unificationId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_UNIFICATION_ID", unificationId);
		return commonDao.getBusinessList(param);
	}
	
	/**
	 * �c�ƃ��X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getSalesList(String businessId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_BUSINESS_ID", businessId);
		return commonDao.getSalesList(param);
	}
	
	/**
	 * �e��}�X�^���X�g���擾
	 * @param param
	 * @return
	 */
	public List<LabelValueBean> getCommonList(String keyCode) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_KEY_CODE", keyCode);
		return commonDao.getCommonList(param);
	}
	
	/**
	 * ���[�U���X�g���擾
	 * @param ��������
	 * @return ���[�U���X�g
	 */
	public List<Map<String, String>> getUserListByCondition(Map<String, Object> param) {
		return commonDao.getUserListByCondition(param);
	}
	
}
