
package jp.co.fourseeds.fsnet.service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.CommonDao;

import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupBean;
import jp.co.fourseeds.fsnet.beans.deptGroup.DeptGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DeptGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * �����O���[�v���@�\�T�[�r�X�����N���X
 * 
 * File Name: DeptGroupService.java 
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
@Component
public class DeptGroupService extends BaseBusinessService{

	@Autowired
	private DeptGroupDao deptGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * �����O���[�v�������
	 * @param deptGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<DeptGroupBean> getDeptGroupList(DeptGroupBean deptGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "deptGroup.getDeptGroupList_Open";
		}else{
			if("0".equals(deptGroupBean.getSearchOriginType())){
				// �S��
				searchSql = "deptGroup.getDeptGroupList_All";
			}else if("1".equals(deptGroupBean.getSearchOriginType())){
				// ���ݗL���Ȃ��̑S��
				searchSql = "deptGroup.getDeptGroupList_Valid";
			}else if("2".equals(deptGroupBean.getSearchOriginType())){
				// �\����̂�
				searchSql = "deptGroup.getDeptGroupList_Rsv";
			}
		}
		
		return deptGroupDao.getDeptGroupList(searchSql, deptGroupBean, strOrderBy);
	}
	
	/**
	 * �����O���[�vID�ƌ����e�[�u���ɂ���āA�����O���[�v���ƕ����O���[�v��������L���̂ݕ��������擾����B
	 * @param deptGroupBean�@��ʓ��͒l
	 * @return�@��������
	 */
	public DeptGroupBean getDeptGroupInfo(DeptGroupBean deptGroupBean) {
		//�@�����O���[�vID
		String deptGroupId = deptGroupBean.getSearchDeptGroupId();
		// �����O���[�v��񌟍��e�[�u��
		String deptGroupTable = "";
		//�V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
		} else {
			//���J�̏ꍇ
			if("1".equals(deptGroupBean.getEditFlag())){
				deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
			//�\��̏ꍇ
			} else {
				deptGroupTable = "V_DEPARTMENT_GROUP_RESERVE";
			}
		}
		DeptGroupBean deptGroupInfo = deptGroupDao.getDeptGroupHeader(deptGroupId, deptGroupTable);
		if (deptGroupInfo != null) {
			List<DeptGroupDetailBean> deptGroupDetailList = deptGroupDao.getDeptGroupDetail(deptGroupId, deptGroupTable);
			List<DeptGroupDetailBean> departmentDetailList = deptGroupDao.getDeptDetail(deptGroupId, deptGroupTable);
			deptGroupInfo.setDepartmentList(deptGroupDetailList);
			deptGroupInfo.setDepartmentDetailList(departmentDetailList);
		}
		return deptGroupInfo;
	}
	
	/**
	 * ������������
	 * 
	 */
	public List<DeptGroupDetailBean> getDeptList(DeptGroupBean deptGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���
		param.put("PARA_COMPANY_ID", deptGroupBean.getConditionCompanyId());
		// ����
		param.put("PARA_UNIFICATION_ID", deptGroupBean.getConditionUnificationId());
		// ����
		param.put("PARA_BUSINESS_ID", deptGroupBean.getConditionBusinessId());
		// SS����R�[�h
		param.put("PARA_SSDEPT_ID", deptGroupBean.getSearchDepartmentId());
		// SS���喼
		param.put("PARA_SSDEPT_NAME", deptGroupBean.getSearchDepartmentName());
		
		List<DeptGroupDetailBean> getDeptList = null;
		// �������X�g���擾
		if (!StringUtil.isBlank(deptGroupBean.getSearchDepartmentId()) || !StringUtil.isBlank(deptGroupBean.getSearchDepartmentName())) {
			// ��ʏ����y SS�����R�[�h ��SS�������z���݂̏ꍇ
			getDeptList = deptGroupDao.getDeptList_right(param);
		} else {
			// ��ʏ����y ��ЃR�[�h  �z���݂��Ȃ��̏ꍇ
			if (StringUtil.isBlank(deptGroupBean.getConditionCompanyId())) {
				getDeptList = deptGroupDao.getDeptList_right(param);
			} else {
				getDeptList = deptGroupDao.getDeptList_left(param);
			}
		}
		
		return getDeptList;
	}

	/**
	 * �����O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewDeptGroupId() {
		return deptGroupDao.getNewDeptGroupId();
	}

	/**
	 * �o�^�σf�[�^�Ƃ̕������̏d���f�[�^���擾
	 * 
	 */
	public boolean checkDeptGroupNmRepeat(DeptGroupBean deptGroupBean, String eventType) {
		int count = deptGroupDao.getDeptGroupNmCheckInfo(deptGroupBean, eventType);
		// �o�^�σf�[�^�Ƃ̕������̏d���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * �����O���[�v���o�^
	 * @param deptGroupBean 
	 * @param loginUser 
	 */
	public void insertDeptGroup(DeptGroupBean deptGroupBean) {
		// �����������O���[�v�w�b�_�[���o�^
		deptGroupDao.insertDeptGroup(deptGroupBean);

		// ����ID
		String[] departmentId = StringUtil.split(deptGroupBean.getDepartmentId(),",");
		for(int i = 0; i < departmentId.length; i++){
			DeptGroupDetailBean deptGroupDetailBean = new DeptGroupDetailBean();
			// �����O���[�vID
			deptGroupDetailBean.setDeptGroupId(deptGroupBean.getDeptGroupId());
			// �����O���[�v���f�\�����
			deptGroupDetailBean.setDgRefDate(deptGroupBean.getDgRefDate());
			// ����ID
			deptGroupDetailBean.setDepartmentId(departmentId[i].substring(1, departmentId[i].length()));
			// �����t���O
			deptGroupDetailBean.setInputFlag(departmentId[i].substring(0, 1));
			// �����O���[�v�ڍדo�^�������ݒ�
			deptGroupDetailBean.setCreateBy(deptGroupBean.getCreateBy());
			deptGroupDetailBean.setCreateDate(deptGroupBean.getCreateDate());
			deptGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
			deptGroupDetailBean.setUpdateDate(deptGroupBean.getUpdateDate());
			// �����������O���[�v�̖��׏��o�^
			deptGroupDao.insertDeptGroupDetail(deptGroupDetailBean);
		}
		
		// ����X�܃O���[�v�����l�����폜
		deptGroupDao.deleteDepartmentStoreGroupUser(deptGroupBean);
		
		// ����X�܃O���[�v�����l�����o�^
		deptGroupDao.insertDepartmentStoreGroupUser(deptGroupBean);
	}

	/**
	 * �����O���[�v���X�V
	 * @param deptGroupBean 
	 * @param loginUser 
	 * @throws ParseException 
	 */
	public void updateDeptGroup(DeptGroupBean deptGroupBean) throws ParseException {
		
		// ���J�����O���[�v���̍쐬�҂ƍ쐬���t���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("V_DEPARTMENT_GROUP_OPEN", "DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		// ���J�����O���[�v���̍쐬�҂ƍ쐬���t���Ȃ��ꍇ�A�\�񕔏��O���[�v���̎擾
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_DEPARTMENT_GROUP_RESERVE", "DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());
		} 
		// �o�^�������ݒ�
		deptGroupBean.setCreateBy(createInfo.getCreateBy());
		deptGroupBean.setCreateDate(createInfo.getCreateDate());
		deptGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		deptGroupBean.setUpdateDate(new Date());
		// �V�X�e�����t
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// ���f�\�����
		Date dgRefDate = StringUtil.parseTheDate(deptGroupBean.getDgRefDate(), "yyyy/MM/dd");
		// ��ʓ��͂��ꂽ���f�\��� > �V�X�e�����t�̏ꍇ
		if(dateNow.before(dgRefDate)){
			// �����O���[�v�̗\������擾����
			DeptGroupBean deptGroupInfo =  deptGroupDao.getDeptGroupHeader(deptGroupBean.getDeptGroupId(), "V_DEPARTMENT_GROUP_RESERVE");
			// �����O���[�v�̗\���񑶍݂���ꍇ�A�����O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(deptGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();					// �����O���[�vID
				param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupInfo.getDeptGroupId());		// �����O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupInfo.getDgRefDate());	// �����O���[�v���f�\�����

				// �����O���[�v���i�\��j�𕨗��폜����B
				deptGroupDao.deleteDeptGroupInfo(param);
			}
		// ��ʓ��͂��ꂽ���f�\��� <= �V�X�e�����t�̏ꍇ
		} else {
			Map<String, Object> param = new HashMap<String, Object>();						// �폜����MAP
			param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());			// �����O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// �����O���[�v���f�\�����

			// �����O���[�v���i�����j�𕨗��폜����B
			deptGroupDao.deleteDeptGroupInfo(param);
		}
		// �����O���[�v���o�^
		this.insertDeptGroup(deptGroupBean);
	}

	/**
	 * �����O���[�v���폜
	 * @param deptGroupBean 
	 */
	public void deleteDeptGroup(DeptGroupBean deptGroupBean) {
		// �����e�[�u���敪�@1:���J�@2:�\��
		String editFlag = deptGroupBean.getEditFlag();
		// �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\��
		String originType = deptGroupBean.getOriginType();
		// �폜�f�[�^�͗\��ł���ꍇ
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();						// �����O���[�vID
			param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupBean.getDeptGroupId());			// �����O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupBean.getDgRefDate());		// �����O���[�v���f�\�����

			// �����O���[�v���i�\��j�𕨗��폜����B
			deptGroupDao.deleteDeptGroupInfo(param);
			// �폜�f�[�^�ɑ��ւ�����J�f�[�^���Ȃ��ꍇ�A�g�b�v�O���[�v���׏��e�[�u����_���폜����B
			if("2".equals(originType)){
				topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), deptGroupBean.getDeptGroupId(), "D");
			}
		// �폜�f�[�^�͌��J�ł���ꍇ
		} else {
			// �����O���[�v�̗\������擾����
			DeptGroupBean deptGroupInfo = deptGroupDao.getDeptGroupHeader(deptGroupBean.getDeptGroupId(), "V_DEPARTMENT_GROUP_RESERVE");
			// �����O���[�v�̗\���񑶍݂���ꍇ�A�����O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(deptGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();					// �����O���[�vID
				param.put("PARA_DEPARTMENT_GROUP_ID", deptGroupInfo.getDeptGroupId());		// �����O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", deptGroupInfo.getDgRefDate());	// �����O���[�v���f�\�����

				// �����O���[�v���i�\��j�𕨗��폜����B
				deptGroupDao.deleteDeptGroupInfo(param);
			}
			// ����O���[�v���e�[�u����_���폜����B
			deptGroupDao.updateDeptGroupInfoInvalid(getLoginUserBean().getUserId(), deptGroupBean);
			// �g�b�v�O���[�v���׏��e�[�u�����폜����B
			topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), deptGroupBean.getDeptGroupId(), "D");
		}
	}
	
	/**
	 * ���������N���N���b�N�A�K�w���x������
	 * @param deptGroupBean 
	 */
	public String getDeptLevel(Map<String, Object> param) {
		
		return deptGroupDao.getDeptLevel(param);
	}
	
	/**
	 * ��Ѓ����N���N���b�N�A������������
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		
		return deptGroupDao.getSecondDeptList(param);
	}
	
	/**
	 * ���������N���N���b�N�A�������ƌ���
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		
		return deptGroupDao.getThirdDeptList(param);
	}
	
	/**
	 * ���ƃ����N���N���b�N�A�����c�ƕ�����
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		
		return deptGroupDao.getFourthDeptList(param);
	}
}