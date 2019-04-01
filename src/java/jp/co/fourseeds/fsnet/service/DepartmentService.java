package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentDutyBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentFormBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentLinkBean;
import jp.co.fourseeds.fsnet.beans.department.DepartmentBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DepartmentDao;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.CommonDao;
import jp.co.common.frame.service.BaseBusinessService;

/**
 * �������@�\�T�[�r�X�����N���X
 * 
 * File Name: DepartmentService.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		    NTS        	       �쐬
 *�@1.1      2017/11/21          NTS			       �������C�� 
 *
 **/
@SuppressWarnings(value={"rawtypes","unchecked"})
@Component
public class DepartmentService extends BaseBusinessService{

	@Autowired
	private DepartmentDao departmentDao;

	@Autowired
	private CommonDao commonDao;
	
	List objs = new ArrayList();
	
	/**
	 * ���喼�̂ƃV�X�e�����p�敪�ɂ���āA����������
	 * @param param�@��������
	 * @return�@��������
	 */
	public List<DepartmentBean> getDeptList(DepartmentFormBean formBean, String strOrderBy) {
		
		List<DepartmentBean> result = null;
		//�@�����p�p�����^��ݒ�
		//�@��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ORDER_BY", strOrderBy);
		//�@����ID
		param.put("PARA_DEPARTMENT_ID", formBean.getSearchDepartmentId());
		//�@������
		param.put("PARA_DEPARTMENT_NAME", formBean.getSearchDepartmentName());
		//�@�\���t���O
		param.put("PARA_SHOWFLAG_ID", formBean.getSearchShowFlag());
		//�@���ID
		param.put("PARA_COMPANY_ID", formBean.getSearchCompanyId());
		//�@����ID
		param.put("PARA_UNIFICATION_ID", formBean.getSearchUnificationId());
		//�@����ID
		param.put("PARA_BUSINESS_ID", formBean.getSearchBusinessId());
		//�@�c�ƕ�ID
		param.put("PARA_SALES_ID", formBean.getSearchSalesId());
		//�@ �V�X�e�����p�敪	
		param.put("PARA_ROLE", getLoginUserBean().getRole());
		
		result = departmentDao.getDeptList(param);
		return result;
	}

	/**
	 * ����ID�ɂ���āA�������̂����
	 * @param departmentId
	 *            ����ID
	 */
	public DepartmentBean getDept_Open(String departmentId) {
		return departmentDao.getDept_Open(departmentId);
	}
	
	/**
	 * ����ID�ɂ���āA����������
	 * @param departmentId
	 *            ����ID
	 */
	public DepartmentBean getDeptInfo(String departmentId) {
		return departmentDao.getDeptInfo(departmentId);
	}
	
	/**
	 * ����ID�ɂ���āA����E���������
	 * @param departmentId
	 *            ����ID
	 */
	public List getDeptDutyList(String departmentId) {

		List<DepartmentDutyBean> list = new ArrayList<DepartmentDutyBean>();
		List temp = new ArrayList();
		temp = departmentDao.getDeptDutyList(departmentId);
		HashMap map = new HashMap();
		List orderList = new ArrayList();
		for (int i = 0; i < temp.size(); i++) {
			DepartmentDutyBean duty = (DepartmentDutyBean) temp.get(i);
			DepartmentDutyBean tempDuty = (DepartmentDutyBean) map.get(duty.getDutyDescription());
			if (tempDuty != null) {
//				String dutyDesc = duty.getDutyDescription();
//				String temDesc = tempDuty.getDutyDescription();
				String name = tempDuty.getUserName();
				String id = tempDuty.getUserId();
				name = name + "," + duty.getKanziSei() + duty.getKanziMei();
				id = id + "," + duty.getUserId();
				tempDuty.setUserName(name);
				tempDuty.setUserId(id);
				map.put(duty.getDutyDescription(), tempDuty);

			} else {
				String name = duty.getKanziSei() + duty.getKanziMei();
				duty.setUserName(name);
				map.put(duty.getDutyDescription(), duty);
				orderList.add(duty.getDutyDescription());
			}
		}

		for (int i = 0; i < orderList.size(); i++) {
			String strKey = (String) orderList.get(i);
			DepartmentDutyBean tempDuty = (DepartmentDutyBean) map.get(strKey);
			list.add(tempDuty);
		}

		return list;
	}
	
	/**
	 * ����ID�ɂ���āA���僊���N�������
	 * @param departmentId
	 *            ����ID
	 */
	public List getDeptLinkList(String departmentId) {
		return departmentDao.getDeptLinkList(departmentId);
	}
	
	/**
	 * ��������X�V
	 * @param departmentAddForm
	 *            ����ǉ��t�H�[��
	 */
	public void updateDepartment(DepartmentFormBean formBean) throws Exception {
		LoginUserBean loginUser = super.getLoginUserBean();
		formBean.setRole(loginUser.getRole());
		String departmentId = formBean.getDepartmentId();
		// ���J�������̍쐬�҂ƍ쐬���t���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("V_NUUE00P", "UEBMCD", departmentId);
		
		formBean.setRole(loginUser.getRole());
		formBean.setUpdateBy(loginUser.getUserId());
		// ����}�X�^����ύX
		departmentDao.updateDepartmentMaster(formBean);

		// ����E������ύX
		updateLinkAndDuty(formBean, createInfo);
	}

	/**
	 *����E�����ƕ��僊���N���f�[�^�X�V
	 * */
	private void updateLinkAndDuty(DepartmentFormBean formBean, BaseBean createInfo) {
		
		String departmentId = formBean.getDepartmentId();
		// �����폜
		departmentDao.deleteDepartmentDuty(departmentId);
		departmentDao.deleteDepartmentLink(departmentId);
		
		insertLinkAndDuty(formBean, createInfo);
	}
	

	/**
	 *����E�����ƕ��僊���N���f�[�^�X�V
	 * */
	private void insertLinkAndDuty(DepartmentFormBean formBean, BaseBean createInfo) {
		
		LoginUserBean loginUser = super.getLoginUserBean();
		String departmentId = formBean.getDepartmentId();
		
		// �P�D����E������ǉ�
		List<DepartmentDutyBean> dutyList = formBean.getDutyList();
		DepartmentDutyBean departmentDutyBean; 
		if (dutyList != null) {
			for (int i = 0; i < dutyList.size(); i++) {
				departmentDutyBean = dutyList.get(i);
				departmentDutyBean.setDepartmentId(departmentId);

				String strUserId = StringUtil.nullToBlank(departmentDutyBean.getUserId());
				String userId[] = strUserId.split(",");
				for (int j = 0; j < userId.length; j++) {
					departmentDutyBean.setUserId(userId[j]);
					departmentDao.insertDepartmentDuty(departmentDutyBean, loginUser, createInfo);
				}
			}
		}
		
		// �Q�D���僊���N����ǉ�
		List<DepartmentLinkBean> urlList = formBean.getUrlList();
		if (urlList != null) {
			for (int i = 0; i < urlList.size(); i++) {
				departmentDao.insertDepartmentLink(departmentId, urlList.get(i), loginUser, createInfo);
			}
		}
	}
}