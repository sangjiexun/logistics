
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

import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupBean;
import jp.co.fourseeds.fsnet.beans.personalTopGroup.PersonalTopGroupDetailBean;
import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.DeptGroupDao;
import jp.co.fourseeds.fsnet.dao.PersonalTopGroupDao;
import jp.co.fourseeds.fsnet.dao.StoreGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;
import jp.co.fourseeds.fsnet.dao.UserGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * �l�p�g�b�v�O���[�v���@�\�T�[�r�X�����N���X
 * 
 * File Name: TopGroupService.java 
 * Created: 2016/01/19
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/02/02		    NTS        	       �쐬
 *
 **/
@Component
public class PersonalTopGroupService extends BaseBusinessService{

	@Autowired
	private PersonalTopGroupDao personalTopGroupDao;
	
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
	 * �l�p�g�b�v�O���[�v�������
	 * @param personalTopGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<PersonalTopGroupBean> getPersTopGroupList(PersonalTopGroupBean personalTopGroupBean, String strOrderBy) {
		return personalTopGroupDao.getPersTopGroupList(personalTopGroupBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 *  �l�p�g�b�v�O���[�vID�ɂ���āA �l�p�g�b�v�O���[�v���� �g�b�v�O���[�v��������L���̂݃O���[�v�����擾����B
	 * @param personalTopGroupBean�@��ʓ��͒l
	 * @return�@��������
	 */
	public PersonalTopGroupBean getPersTopGroupInfo(PersonalTopGroupBean personalTopGroupBean) {
		// �l�p�g�b�v�O���[�vID
		String personalTopGroupId = personalTopGroupBean.getSearchTopGroupId();
		// �g�b�v�O���[�v��񌟍��e�[�u��
		String topGroupTable = "V_LEADING_GROUP_OPEN";
		// �����O���[�v��񌟍��e�[�u��
		String deptGroupTable = "V_DEPARTMENT_GROUP_OPEN";
		// �X�܃O���[�v��񌟍��e�[�u��
		String storeGroupTable = "V_STORE_GROUP_OPEN";
		// �l�p�g�b�v�O���[�v�����擾����
		PersonalTopGroupBean personalTopGroupInfo = personalTopGroupDao.getPersTopGroupHeader(personalTopGroupId);
		if(personalTopGroupInfo != null){
			// �l�p�g�b�v�O���[�v��������L���̂ݕ����O���[�v�����擾����
			List<TopGroupDetailBean> deptGroupDetailList = topGroupDao.getTopGroupDetail_DeptGroup(personalTopGroupId, topGroupTable ,deptGroupTable);
			// �l�p�g�b�v�O���[�v��������L���̂ݓX�܃O���[�v�����擾����
			List<TopGroupDetailBean> storeGroupDetailList = topGroupDao.getTopGroupDetail_StoreGroup(personalTopGroupId, topGroupTable, storeGroupTable, getLoginUserBean());
			// �l�p�g�b�v�O���[�v��������L���̂݃��[�U���X�g�����擾����B
			List<PersonalTopGroupDetailBean> userDetailList = personalTopGroupDao.getPersTopGroupDetail_PersUser(personalTopGroupId);
			// �l�p�g�b�v�O���[�v�̋��L�ҏ����擾����B
			List<PersonalTopGroupDetailBean> shareUserDetailList = personalTopGroupDao.getPersTopGroupDetail_ShareUser(personalTopGroupId);
			
			personalTopGroupInfo.setDeptGroupList(deptGroupDetailList);
			personalTopGroupInfo.setStoreGroupList(storeGroupDetailList);
			personalTopGroupInfo.setUserList(userDetailList);
			personalTopGroupInfo.setShareUserList(shareUserDetailList);
		}
		
		return personalTopGroupInfo;
	}

	/**
	 * �l�p�g�b�v�O���[�v���o�^
	 * @param personalTopGroupBean 
	 * @throws ParseException 
	 */
	public void insertPersTopGroup(PersonalTopGroupBean personalTopGroupBean) throws ParseException {
		// �������g�b�v�O���[�v�w�b�_�[���o�^
		topGroupDao.insertTopGroupHeader(personalTopGroupBean);
		// �T�u�����O���[�vID
		String[] deptGroupId = StringUtil.split(personalTopGroupBean.getDeptGroupId(),",");
		for(int i = 0; i < deptGroupId.length; i++){
			TopGroupDetailBean subDeptGroupBean = new TopGroupDetailBean();
			// �g�b�v�O���[�vID
			subDeptGroupBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
			// �T�u�O���[�v���f�\�����
			subDeptGroupBean.setLgRefDate(personalTopGroupBean.getLgRefDate());
			// �T�u�O���[�vID
			subDeptGroupBean.setSubGroupId(deptGroupId[i]);
			// �T�u�O���[�v�敪
			subDeptGroupBean.setSubGroupFlag("D");
			// �g�b�v�O���[�v�ڍדo�^�������ݒ�
			subDeptGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			subDeptGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			subDeptGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subDeptGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// �������g�b�v�O���[�v�̖��׏��o�^
			topGroupDao.insertTopGroupDetail(subDeptGroupBean);
		}
		
		// �T�u�X�܃O���[�vID
		String[] storeGroupId = StringUtil.split(personalTopGroupBean.getStoreGroupId(),",");
		for(int i = 0; i < storeGroupId.length; i++){
			TopGroupDetailBean subStoreGroupBean = new TopGroupDetailBean();
			// �g�b�v�O���[�vID
			subStoreGroupBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
			// �T�u�O���[�v���f�\�����
			subStoreGroupBean.setLgRefDate(personalTopGroupBean.getLgRefDate());
			// �T�u�O���[�vID
			subStoreGroupBean.setSubGroupId(storeGroupId[i]);
			// �T�u�O���[�v�敪
			subStoreGroupBean.setSubGroupFlag("S");
			// �g�b�v�O���[�v�ڍדo�^�������ݒ�
			subStoreGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			subStoreGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			subStoreGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subStoreGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// �������g�b�v�O���[�v�̖��׏��o�^
			topGroupDao.insertTopGroupDetail(subStoreGroupBean);
		}
		
		//�l���[�U���X�g�����͂��ꂽ�ꍇ
		if(!StringUtil.isBlank(personalTopGroupBean.getUserId())){
			// ���[�UID
			String[] userId = StringUtil.split(personalTopGroupBean.getUserId(),",");
			// ���[�U�O���[�vID���̔Ԃ���
			String persUserGroupId = userGroupDao.getNewUserGroupId();
			TopGroupDetailBean subUserGroupBean = new TopGroupDetailBean();
			// �g�b�v�O���[�vID
			subUserGroupBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
			// �T�u�O���[�v���f�\�����
			subUserGroupBean.setLgRefDate(personalTopGroupBean.getLgRefDate());
			// �T�u�O���[�vID
			subUserGroupBean.setSubGroupId(persUserGroupId);
			// �T�u�O���[�v�敪
			subUserGroupBean.setSubGroupFlag("U");
			// �g�b�v�O���[�v�ڍדo�^�������ݒ�
			subUserGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			subUserGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			subUserGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			subUserGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// �������g�b�v�O���[�v�̖��׏��o�^
			topGroupDao.insertTopGroupDetail(subUserGroupBean);
			
			// ���[�U�O���[�vID���̔Ԃ���
			UserGroupBean userGroupBean = new UserGroupBean();
			userGroupBean.setUserGroupId(persUserGroupId);
			userGroupBean.setUserGroupName(personalTopGroupBean.getTopGroupName());
			userGroupBean.setUserGroupIntro("");
			userGroupBean.setUgRefDate(personalTopGroupBean.getLgRefDate());
			userGroupBean.setPersonalGroupFlag("1");
			// �o�^�������ݒ�
			userGroupBean.setCreateBy(personalTopGroupBean.getCreateBy());
			userGroupBean.setCreateDate(personalTopGroupBean.getCreateDate());
			userGroupBean.setUpdateBy(getLoginUserBean().getUserId());
			userGroupBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
			// ���������[�U�O���[�v�w�b�_�[���o�^
			userGroupDao.insertUserGroup(userGroupBean);
			
			for(int i = 0; i < userId.length; i++){
				UserGroupDetailBean userGroupDetailBean = new UserGroupDetailBean();
				// ���[�U�O���[�vID
				userGroupDetailBean.setUserGroupId(userGroupBean.getUserGroupId());
				// ���[�U�O���[�v���f�\�����
				userGroupDetailBean.setUgRefDate(userGroupBean.getUgRefDate());
				// ���[�UID
				userGroupDetailBean.setUserId(userId[i]);
				// ���[�U�O���[�v�ڍדo�^�������ݒ�
				userGroupDetailBean.setCreateBy(userGroupBean.getCreateBy());
				userGroupDetailBean.setCreateDate(userGroupBean.getCreateDate());
				userGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
				userGroupDetailBean.setUpdateDate(userGroupBean.getUpdateDate());
				// ���������[�U�O���[�v�̖��׏��o�^
				userGroupDao.insertUserGroupDetail(userGroupDetailBean);
			}
		}
		
		// ���L�Ώێ҃��X�g���擾
		List<PersonalTopGroupDetailBean> shareUserList = personalTopGroupBean.getShareUserList();
		if(!StringUtil.isBlank(shareUserList)){
			for(int i = 0; i < shareUserList.size(); i++){
				PersonalTopGroupDetailBean shareUserBean = shareUserList.get(i);
				// �g�b�v�O���[�vID
				shareUserBean.setTopGroupId(personalTopGroupBean.getTopGroupId());
				// ���L�Ώێҏڍדo�^�������ݒ�
				shareUserBean.setCreateBy(personalTopGroupBean.getCreateBy());
				shareUserBean.setCreateDate(personalTopGroupBean.getCreateDate());
				shareUserBean.setUpdateBy(getLoginUserBean().getUserId());
				shareUserBean.setUpdateDate(personalTopGroupBean.getUpdateDate());
				// �������g�b�v�O���[�v�̋��L�Ώێҏ��o�^
				personalTopGroupDao.insertShareUser(shareUserBean);
			}
		}
	}

	/**
	 * �l�p�g�b�v�O���[�v���X�V
	 * @param personalTopGroupBean 
	 * @throws ParseException 
	 */
	public void updatePerTopGroupInfo(PersonalTopGroupBean personalTopGroupBean) throws ParseException {
		
		// ���J�g�b�v�O���[�v���̍쐬�҂ƍ쐬���t���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("V_LEADING_GROUP_OPEN", "TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());
		// �o�^�������ݒ�
		personalTopGroupBean.setCreateBy(createInfo.getCreateBy());
		personalTopGroupBean.setCreateDate(createInfo.getCreateDate());
		personalTopGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		personalTopGroupBean.setUpdateDate(new Date());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_ID", personalTopGroupBean.getTopGroupId());					// �g�b�v�O���[�vID
		param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// �g�b�v�O���[�v���f�\�����
		// �l���[�U���X�g���𕨗��폜����
		personalTopGroupDao.deletePersUserGroupInvalid(param);
		// ���L�ҏ��𕨗��폜����
		personalTopGroupDao.deleteTopGroupShareInvalid(param);
		// �g�b�v�O���[�v���i�����j�𕨗��폜����B
		topGroupDao.deleteTopGroupInfo(param);
		// �g�b�v�O���[�v���o�^
		this.insertPersTopGroup(personalTopGroupBean);
	}

	/**
	 * �l�p�g�b�v�O���[�v���폜
	 * @param personalTopGroupBean 
	 */
	public void deletePersTopGroup(PersonalTopGroupBean personalTopGroupBean) {
		// �g�b�v�O���[�v���e�[�u����_���폜����B
		topGroupDao.updateTopGroupInfoInvalid(getLoginUserBean().getUserId(), personalTopGroupBean);
		// �l�p���[�U���X�g����_���폜����
		personalTopGroupDao.updatePersUserGroupInvalid(getLoginUserBean().getUserId(), personalTopGroupBean);
		// ���L�Ώێ҂�_���폜����B
		personalTopGroupDao.updateTopGroupShareInvalid(getLoginUserBean().getUserId(), personalTopGroupBean);
		// �R���e���c�{�������g�b�v�O���[�v����_���폜����B
		topGroupDao.updateAuthLeadingGroupInvalid(getLoginUserBean().getUserId(), personalTopGroupBean.getTopGroupId());
		// �R���e���c�{�������g�b�v�O���[�v�\����𕨗��폜����B
		topGroupDao.deleteAuthLeadingGroupRsv(personalTopGroupBean.getTopGroupId());
	}

	/**
	 * ���̑w�O���[�v�����擾
	 * @param subGroupId 
	 * @param subGroupFlag 
	 */
	@SuppressWarnings("rawtypes")
	public List getSubGroupList(String subGroupId, String subGroupFlag) {
		List subGroupList = new ArrayList();
		
		// �����O���[�v�����̏ꍇ
		if("D".equals(subGroupFlag)){
			subGroupList = deptGroupDao.getDeptGroupDetail(subGroupId, "V_DEPARTMENT_GROUP_OPEN");
		// �X�܃O���[�v�����̏ꍇ
		} else if ("S".equals(subGroupFlag)){
			subGroupList = storeGroupDao.getStoreGroupDetail(subGroupId, "V_STORE_GROUP_OPEN", "");
		}
		return subGroupList;
	}
}