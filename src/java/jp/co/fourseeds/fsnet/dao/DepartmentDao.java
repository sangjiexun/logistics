package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentDutyBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentFormBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentLinkBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentBean;

/**
 * �������@�\Dao�����N���X
 * 
 * File Name: DepartmentDao.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		    NTS        	       �쐬
 *�@1.1		2017/11/21		    NTS        	       �������C��
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class DepartmentDao extends BaseDao {

	/**
	 *  ���喼�̂ƃV�X�e�����p�敪�ɂ���āA�S�Ă����
	 * @param param�@��������
	 * @return�@��������
	 */
	public List<DepartmentBean> getDeptList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("department.getDeptList", param);
	}
	
	/**
	 * <p>
	 * ����ID�ɂ���āA����������
	 * </p>
	 * 
	 * @param departmentId
	 */
	public DepartmentBean getDeptInfo(String departmentId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		return this.sqlSessionTemplate.selectOne("department.getDeptInfo", param);
	}
	
	/**
	 * <p>
	 * ����ID�ɂ���āA�������̂����
	 * </p>
	 * 
	 * @param departmentId
	 */
	public DepartmentBean getDept_Open(String departmentId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		return this.sqlSessionTemplate.selectOne("department.getDept_Open", param);
	}
	
	/**
	 * ����ID�ɂ���āA����E���������
	 * @param departmentId
	 *            ����ID
	 */
	public List getDeptDutyList(String departmentId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);

		return this.sqlSessionTemplate.selectList("department.getDeptDutyList", param);
	}
	
	/**
	 * ����ID�ɂ���āA���僊���N�������
	 * 
	 * @param departmentId
	 *            ����ID
	 */
	public List getDeptLinkList(String departmentId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);

		return this.sqlSessionTemplate.selectList("department.getDeptLinkList", param);
	}
	
	/**
	 * ����ID�ɂ���āA�������ύX
	 * @param departmentUpdateForm
	 *            ����ύX�t�H�[��
	 */
	public void updateDepartmentMaster(DepartmentFormBean departmentFormBean) {
		this.sqlSessionTemplate.update("department.updateDepartmentMaster", departmentFormBean);
	}
	
	/**
	 * ����ID�ɂ���āA����E�������폜
	 * @param ����ID
	 */
	public void deleteDepartmentDuty(String departmentId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		this.sqlSessionTemplate.delete("department.deleteDepartmentDuty", param);
	}

	/**
	 * ����ID�ɂ���āA���僊���N�����폜
	 * @param ����ID
	 */
	public void deleteDepartmentLink(String departmentId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		
		this.sqlSessionTemplate.delete("department.deleteDepartmentLink", param);
	}
	
	/**
	 * ����E������ǉ�
	 * 
	 * @param departmentId
	 *            ����ID
	 * @param departmentDutyBean
	 *            ����E�����f��
	 * @param loginUser
	 *            ���O�C�����[�U
	 * 
	 */
	public boolean insertDepartmentDuty(DepartmentDutyBean departmentDutyBean, LoginUserBean loginUser, BaseBean createInfo) {
		
		Date nowDate = new Date();
		departmentDutyBean.setCreateDate(createInfo.getCreateDate());
		departmentDutyBean.setCreateBy(createInfo.getCreateBy());
		departmentDutyBean.setUpdateDate(nowDate);
		departmentDutyBean.setUpdateBy(loginUser.getUserId());
		
		int returnValue = this.sqlSessionTemplate.insert("department.insertDepartmentDuty", departmentDutyBean);
		if (returnValue > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ���僊���N����ǉ�
	 * 
	 * @param departmentId
	 *            ����ID
	 * @param departmentLinkBean
	 *            ���僊���N���f��
	 * @param loginUser
	 *            ���O�C�����[�U
	 * 
	 */
	public boolean insertDepartmentLink(String departmentId, DepartmentLinkBean departmentLinkBean, LoginUserBean loginUser, BaseBean createInfo) {
		
		departmentLinkBean.setDepartmentId(departmentId);
		
		Date nowDate = new Date();
		departmentLinkBean.setCreateDate(createInfo.getCreateDate());
		departmentLinkBean.setCreateBy(createInfo.getCreateBy());
		departmentLinkBean.setUpdateDate(nowDate);
		departmentLinkBean.setUpdateBy(loginUser.getUserId());
		int returnValue = this.sqlSessionTemplate.insert("department.insertDepartmentLink", departmentLinkBean);
		if (returnValue > 0) {
			return true;
		}
		
		return false;
	}
}