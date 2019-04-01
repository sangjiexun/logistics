
package jp.co.fourseeds.fsnet.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.dao.CommonDao;

import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DeptGroupDao;
import jp.co.fourseeds.fsnet.dao.StoreGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;
import jp.co.fourseeds.fsnet.dao.UserGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * �g�b�v�O���[�v���@�\�T�[�r�X�����N���X
 * 
 * File Name: TopGroupService.java 
 * Created: 2016/01/19
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/19		    NTS        	       �쐬
 *�@1.0		2016/01/19		    NTS        	       �������C��
 **/
@Component
public class TopGroupService extends BaseBusinessService{

	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;
	
	@Autowired
	private DeptGroupDao deptGroupDao;
	
	@Autowired
	private StoreGroupDao storeGroupDao;
	
	@Autowired
	private UserGroupDao userGroupDao;

	/**
	 * �g�b�v�O���[�v�������
	 * @param topGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<TopGroupBean> getTopGroupList(TopGroupBean topGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "topGroup.getTopGroupList_Open";
		}else{
			if("0".equals(topGroupBean.getSearchOriginType())){
				// �S��
				searchSql = "topGroup.getTopGroupList_All";
			}else if("1".equals(topGroupBean.getSearchOriginType())){
				// ���ݗL���Ȃ��̑S��
				searchSql = "topGroup.getTopGroupList_Valid";
			}else if("2".equals(topGroupBean.getSearchOriginType())){
				// �\����̂�
				searchSql = "topGroup.getTopGroupList_Rsv";
			}
		}
		
		return topGroupDao.getTopGroupList(searchSql, topGroupBean, strOrderBy);
	}
	
	/**
	 *  �g�b�v�O���[�vID�ƌ����e�[�u���ɂ���āA �g�b�v�O���[�v���� �g�b�v�O���[�v��������L���̂݃O���[�v�����擾����B
	 * @param topGroupBean�@��ʓ��͒l
	 * @return�@��������
	 */
	public TopGroupBean getTopGroupInfo(TopGroupBean topGroupBean) {
		// �g�b�v�O���[�vID
		String topGroupId = topGroupBean.getSearchTopGroupId();
		// �g�b�v�O���[�v��񌟍��e�[�u��
		String topGroupTable = "";
		// �����O���[�v��񌟍��e�[�u��
		String deptGroupTable = "";
		// �X�܃O���[�v��񌟍��e�[�u��
		String storeGroupTable = "";
		// ���[�U�O���[�v��񌟍��e�[�u��
		String userGroupTable = "";
		// �V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			topGroupTable = "V_LEADING_GROUP_OPEN";
			deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
			storeGroupTable = "V_STORE_GROUP_OPEN";
			userGroupTable = "V_USER_GROUP_OPEN";
		} else {
			
			// ���J�̏ꍇ
			if("1".equals(topGroupBean.getEditFlag())){
				topGroupTable = "V_LEADING_GROUP_OPEN";
			// �\��̏ꍇ
			} else {
				topGroupTable = "V_LEADING_GROUP_RESERVE";
			}
			deptGroupTable = "V_DEPARTMENT_GROUP_ALL";
			storeGroupTable = "V_STORE_GROUP_ALL";
			userGroupTable = "V_USER_GROUP_ALL";
		}
		// �g�b�v�O���[�v�����擾����
		TopGroupBean topGroupInfo = topGroupDao.getTopGroupHeader(topGroupId, topGroupTable);
		if(topGroupInfo != null){
			// �g�b�v�O���[�v��������L���̂ݕ����O���[�v�����擾����
			List<TopGroupDetailBean> deptGroupDetailList = topGroupDao.getTopGroupDetail_DeptGroup(topGroupId, topGroupTable ,deptGroupTable);
			// �g�b�v�O���[�v��������L���̂ݓX�܃O���[�v�����擾����
			List<TopGroupDetailBean> storeGroupDetailList = topGroupDao.getTopGroupDetail_StoreGroup(topGroupId, topGroupTable, storeGroupTable, getLoginUserBean());
			// �g�b�v�O���[�v��������L���̂݃��[�U�O���[�v�����擾����
			List<TopGroupDetailBean> userGroupDetailList = topGroupDao.getTopGroupDetail_UserGroup(topGroupId, topGroupTable, userGroupTable);
			topGroupInfo.setDeptGroupList(deptGroupDetailList);
			topGroupInfo.setStoreGroupList(storeGroupDetailList);
			topGroupInfo.setUserGroupList(userGroupDetailList);
		}
		return topGroupInfo;
	}

	/**
	 * �g�b�v�O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewTopGroupId() {
		return topGroupDao.getNewTopGroupId();
	}

	/**
	 * �o�^�σf�[�^�Ƃ̃g�b�v�O���[�v���̏d���f�[�^���擾
	 * 
	 */
	public boolean checkTopGroupNmRepeat(TopGroupBean topGroupBean, String eventType) {
		int count = topGroupDao.getTopGroupNmCheckInfo(topGroupBean, eventType);
		// �o�^�σf�[�^�Ƃ̃g�b�v�O���[�v���̏d���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * �g�b�v�O���[�v���o�^
	 * @param userGroupBean 
	 */
	public void insertTopGroup(TopGroupBean topGroupBean) {
		
		// �������g�b�v�O���[�v�w�b�_�[���o�^
		topGroupDao.insertTopGroupHeader(topGroupBean);

		// �T�u�����O���[�vID
		String[] deptGroupId = StringUtil.split(topGroupBean.getDeptGroupId(),",");
		for(int i = 0; i < deptGroupId.length; i++){
			TopGroupDetailBean subDeptGroupBean = new TopGroupDetailBean();
			// �g�b�v�O���[�vID
			subDeptGroupBean.setTopGroupId(topGroupBean.getTopGroupId());
			// �T�u�O���[�v���f�\�����
			subDeptGroupBean.setLgRefDate(topGroupBean.getLgRefDate());
			// �T�u�O���[�vID
			subDeptGroupBean.setSubGroupId(deptGroupId[i]);
			// �T�u�O���[�v�敪
			subDeptGroupBean.setSubGroupFlag("D");
			// �g�b�v�O���[�v�ڍדo�^�������ݒ�
			subDeptGroupBean.setCreateBy(topGroupBean.getCreateBy());
			subDeptGroupBean.setCreateDate(topGroupBean.getCreateDate());
			subDeptGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subDeptGroupBean.setUpdateDate(topGroupBean.getUpdateDate());
			// �������g�b�v�O���[�v�̖��׏��o�^
			topGroupDao.insertTopGroupDetail(subDeptGroupBean);
		}
		
		// �T�u�X�܃O���[�vID
		String[] storeGroupId = StringUtil.split(topGroupBean.getStoreGroupId(),",");
		for(int i = 0; i < storeGroupId.length; i++){
			TopGroupDetailBean subStoreGroupBean = new TopGroupDetailBean();
			// �g�b�v�O���[�vID
			subStoreGroupBean.setTopGroupId(topGroupBean.getTopGroupId());
			// �T�u�O���[�v���f�\�����
			subStoreGroupBean.setLgRefDate(topGroupBean.getLgRefDate());
			// �T�u�O���[�vID
			subStoreGroupBean.setSubGroupId(storeGroupId[i]);
			// �T�u�O���[�v�敪
			subStoreGroupBean.setSubGroupFlag("S");
			// �g�b�v�O���[�v�ڍדo�^�������ݒ�
			subStoreGroupBean.setCreateBy(topGroupBean.getCreateBy());
			subStoreGroupBean.setCreateDate(topGroupBean.getCreateDate());
			subStoreGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subStoreGroupBean.setUpdateDate(topGroupBean.getUpdateDate());
			// �������g�b�v�O���[�v�̖��׏��o�^
			topGroupDao.insertTopGroupDetail(subStoreGroupBean);
		}
		
		// �T�u���[�U�O���[�vID
		String[] userGroupId = StringUtil.split(topGroupBean.getUserGroupId(),",");
		for(int i = 0; i < userGroupId.length; i++){
			TopGroupDetailBean subUserGroupBean = new TopGroupDetailBean();
			// �g�b�v�O���[�vID
			subUserGroupBean.setTopGroupId(topGroupBean.getTopGroupId());
			// �T�u�O���[�v���f�\�����
			subUserGroupBean.setLgRefDate(topGroupBean.getLgRefDate());
			// �T�u�O���[�vID
			subUserGroupBean.setSubGroupId(userGroupId[i]);
			// �T�u�O���[�v�敪
			subUserGroupBean.setSubGroupFlag("U");
			// �g�b�v�O���[�v�ڍדo�^�������ݒ�
			subUserGroupBean.setCreateBy(topGroupBean.getCreateBy());
			subUserGroupBean.setCreateDate(topGroupBean.getCreateDate());
			subUserGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subUserGroupBean.setUpdateDate(topGroupBean.getUpdateDate());
			// �������g�b�v�O���[�v�̖��׏��o�^
			topGroupDao.insertTopGroupDetail(subUserGroupBean);
		}
	}

	/**
	 * �g�b�v�O���[�v���X�V
	 * @param topGroupBean 
	 * @throws ParseException 
	 */
	public void updateTopGroupInfo(TopGroupBean topGroupBean) throws ParseException {
		
		// ���J�g�b�v�O���[�v���̍쐬�҂ƍ쐬���t���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("V_LEADING_GROUP_OPEN", "TOP_GROUP_ID", topGroupBean.getTopGroupId());
		// ���J�g�b�v�O���[�v���̍쐬�҂ƍ쐬���t���Ȃ��ꍇ�A�\��g�b�v�O���[�v���̎擾
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_LEADING_GROUP_RESERVE", "TOP_GROUP_ID", topGroupBean.getTopGroupId());
		} 
		// �o�^�������ݒ�
		topGroupBean.setCreateBy(createInfo.getCreateBy());
		topGroupBean.setCreateDate(createInfo.getCreateDate());
		topGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		topGroupBean.setUpdateDate(new Date());
		
		// �V�X�e�����t
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// ���f�\�����
		Date lgRefDate = StringUtil.parseTheDate(topGroupBean.getLgRefDate(), "yyyy/MM/dd");
		// ��ʓ��͂��ꂽ���f�\��� > �V�X�e�����t�̏ꍇ
		if(dateNow.before(lgRefDate)){
			// �g�b�v�O���[�v�̗\������擾����
			TopGroupBean topGroupInfo =  topGroupDao.getTopGroupHeader(topGroupBean.getTopGroupId(), "V_LEADING_GROUP_RESERVE");
			// �g�b�v�O���[�v�̗\���񑶍݂���ꍇ�A�g�b�v�O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(topGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_TOP_GROUP_ID", topGroupInfo.getTopGroupId());				// �g�b�v�O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupInfo.getLgRefDate());	// �g�b�v�O���[�v���f�\�����
				
				// �g�b�v�O���[�v���i�\��j�𕨗��폜����B
				topGroupDao.deleteTopGroupInfo(param);
			}
		// ��ʓ��͂��ꂽ���f�\��� <= �V�X�e�����t�̏ꍇ
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());					// �g�b�v�O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// �g�b�v�O���[�v���f�\�����
			// �g�b�v�O���[�v���i�����j�𕨗��폜����B
			topGroupDao.deleteTopGroupInfo(param);
		}
		// �g�b�v�O���[�v���o�^
		this.insertTopGroup(topGroupBean);
	}

	/**
	 * �g�b�v�O���[�v���폜
	 * @param topGroupBean 
	 */
	public void deleteTopGroup(TopGroupBean topGroupBean) {
		// �����e�[�u���敪�@1:���J�@2:�\��
		String editFlag = topGroupBean.getEditFlag();
		// �폜�f�[�^�͗\��ł���ꍇ
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_TOP_GROUP_ID", topGroupBean.getTopGroupId());					// �g�b�v�O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupBean.getLgRefDate());		// �g�b�v�O���[�v���f�\�����

			// �g�b�v�O���[�v���i�\��j�𕨗��폜����B
			topGroupDao.deleteTopGroupInfo(param);
		// �폜�f�[�^�͌��J�ł���ꍇ
		} else {
			// �g�b�v�O���[�v�̗\������擾����
			TopGroupBean topGroupInfo = topGroupDao.getTopGroupHeader(topGroupBean.getTopGroupId(), "V_LEADING_GROUP_RESERVE");
			// �g�b�v�O���[�v�̗\���񑶍݂���ꍇ�A�g�b�v�O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(topGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_TOP_GROUP_ID", topGroupInfo.getTopGroupId());				// �g�b�v�O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", topGroupInfo.getLgRefDate());	// �g�b�v�O���[�v���f�\�����

				// �g�b�v�O���[�v���i�\��j�𕨗��폜����B
				topGroupDao.deleteTopGroupInfo(param);
			}
			// �g�b�v�O���[�v���e�[�u����_���폜����B
			topGroupDao.updateTopGroupInfoInvalid(getLoginUserBean().getUserId(), topGroupBean);
			// �R���e���c�{�������g�b�v�O���[�v����_���폜����B
			topGroupDao.updateAuthLeadingGroupInvalid(getLoginUserBean().getUserId(), topGroupBean.getTopGroupId());
			// �R���e���c�{�������g�b�v�O���[�v�\����𕨗��폜����B
			topGroupDao.deleteAuthLeadingGroupRsv(topGroupBean.getTopGroupId());
		}
	}

	/**
	 * ���̑w�O���[�v�����擾
	 * @param subGroupId 
	 * @param subGroupFlag 
	 */
	@SuppressWarnings("rawtypes")
	public List getSubGroupList(String subGroupId, String subGroupFlag) {
		// ���[�U����
		String role = getLoginUserBean().getRole();
		List subGroupList = new ArrayList();
		// �����O���[�v�����̏ꍇ
		if("D".equals(subGroupFlag)){
			// �V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)�ꍇ
			if("1".equals(role)){
				subGroupList = deptGroupDao.getDeptGroupDetail(subGroupId, "V_DEPARTMENT_GROUP_ALL");
			} else {
				subGroupList = deptGroupDao.getDeptGroupDetail(subGroupId, "V_DEPARTMENT_GROUP_OPEN");
			}
		// �X�܃O���[�v�����̏ꍇ
		} else if ("S".equals(subGroupFlag)){
			// �V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)�ꍇ
			if("1".equals(role)){
				subGroupList = storeGroupDao.getStoreGroupDetail(subGroupId, "V_STORE_GROUP_ALL", "");
			} else {
				subGroupList = storeGroupDao.getStoreGroupDetail(subGroupId, "V_STORE_GROUP_OPEN", "");
			}
		// ���[�U�O���[�v�����̏ꍇ
		} else if ("U".equals(subGroupFlag)){
			// �V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)�ꍇ
			if("1".equals(role)){
				subGroupList = userGroupDao.getUserGroupDetail(subGroupId, "V_USER_GROUP_ALL");
			} else {
				subGroupList = userGroupDao.getUserGroupDetail(subGroupId, "V_USER_GROUP_OPEN");
			}
		}
		return subGroupList;
	}
	
	/**
	 * ���������N���N���b�N�A�K�w���x������
	 * @param deptGroupBean 
	 */
	public String getDeptLevel(Map<String, Object> param, String subGroupFlag) {
		String level = new String();
		
		if ("D".equals(subGroupFlag)) {
			level = deptGroupDao.getDeptLevel(param);
		} else if ("S".equals(subGroupFlag)){
			level = storeGroupDao.getDeptLevel(param);
		}
		
		return level;
	}
	
	/**
	 * ��Ѓ����N���N���b�N�A������������
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("D".equals(subGroupFlag)) {
			subGroupList = deptGroupDao.getSecondDeptList(param);
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getSecondDeptList(param);
		}
		
		return subGroupList;
	}
	
	/**
	 * ���������N���N���b�N�A�������ƌ���
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("D".equals(subGroupFlag)) {
			subGroupList = deptGroupDao.getThirdDeptList(param);
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getThirdDeptList(param);
		}
		
		return subGroupList;
	}
	
	/**
	 * ���ƃ����N���N���b�N�A�����c�ƕ�����
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("D".equals(subGroupFlag)) {
			subGroupList = deptGroupDao.getFourthDeptList(param);
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getFourthDeptList(param);
		}
		
		return subGroupList;
	}
	
	/**
	 * �c�ƕ������N���N���b�N�A�����X�܌���
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFifthDeptList(Map<String, Object> param, String subGroupFlag) {
		List<Map<String, String>> subGroupList = new ArrayList<Map<String, String>>();
		
		if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getFifthDeptList(param);
		}
		return subGroupList;
	}
}