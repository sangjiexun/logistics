
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

import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupBean;
import jp.co.fourseeds.fsnet.beans.userGroup.UserGroupDetailBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;
import jp.co.fourseeds.fsnet.dao.UserGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * ���[�U�O���[�v���@�\�T�[�r�X�����N���X
 * 
 * File Name: DeptGroupService.java 
 * Created: 2015/12/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/04		    NTS        	       �쐬
 * 1.1      2017/11/17          NTS            �������C��
 **/
@Component
public class UserGroupService extends BaseBusinessService{

	@Autowired
	private UserGroupDao userGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * ���[�U�O���[�v�������
	 * @param userGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<UserGroupBean> getUserGroupList(UserGroupBean userGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "userGroup.getUserGroupList_Open";
		}else{
			if("0".equals(userGroupBean.getSearchOriginType())){
				// �S��
				searchSql = "userGroup.getUserGroupList_All";
			}else if("1".equals(userGroupBean.getSearchOriginType())){
				// ���ݗL���Ȃ��̑S��
				searchSql = "userGroup.getUserGroupList_Valid";
			}else if("2".equals(userGroupBean.getSearchOriginType())){
				// �\����̂�
				searchSql = "userGroup.getUserGroupList_Rsv";
			}
		}
		
		return userGroupDao.getUserGroupList(searchSql, userGroupBean, strOrderBy);
	}
	
	/**
	 *  ���[�U�O���[�vID�ƌ����e�[�u���ɂ���āA ���[�U�O���[�v���� ���[�U�O���[�v��������L���̂� ���[�U�����擾����B
	 * @param userGroupBean�@��ʓ��͒l
	 * @return�@��������
	 */
	public UserGroupBean getUserGroupInfo(UserGroupBean userGroupBean) {
		// ���[�U�O���[�vID
		String userGroupId = userGroupBean.getSearchUserGroupId();
		// ���[�U�O���[�v��񌟍��e�[�u��
		String userGroupTable = "";
		// �V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			userGroupTable = "V_USER_GROUP_OPEN";
		} else {
			// ���J�̏ꍇ
			if("1".equals(userGroupBean.getEditFlag())){
				userGroupTable = "V_USER_GROUP_OPEN";
			// �\��̏ꍇ
			} else {
				userGroupTable = "V_USER_GROUP_RESERVE";
			}
		}
		UserGroupBean userGroupInfo = userGroupDao.getUserGroupHeader(userGroupId, userGroupTable);
		if(userGroupInfo != null){
			List<UserGroupDetailBean> userGroupDetailList = userGroupDao.getUserGroupDetail(userGroupId, userGroupTable);
			userGroupInfo.setUserList(userGroupDetailList);
		}
		return userGroupInfo;
	}

	/**
	 * ���[�U�O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewUserGroupId() {
		return userGroupDao.getNewUserGroupId();
	}

	/**
	 * �o�^�σf�[�^�Ƃ̃��[�U�O���[�v���̏d���f�[�^���擾
	 * 
	 */
	public boolean checkUserGroupNmRepeat(UserGroupBean userGroupBean, String eventType) {
		int count = userGroupDao.getUserGroupNmCheckInfo(userGroupBean, eventType);
		// �o�^�σf�[�^�Ƃ̃��[�U�O���[�v���̏d���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * ���[�U�O���[�v���o�^
	 * @param userGroupBean 
	 */
	public void insertUserGroup(UserGroupBean userGroupBean) {
		
		// ���������[�U�O���[�v�w�b�_�[���o�^
		userGroupDao.insertUserGroup(userGroupBean);

		// ���[�UID
		String[] userId = StringUtil.split(userGroupBean.getUserId(),",");
		for(int i = 0; i < userId.length; i++){
			UserGroupDetailBean userGroupDetailBean = new UserGroupDetailBean();
			// ���[�U�[�O���[�vID
			userGroupDetailBean.setUserGroupId(userGroupBean.getUserGroupId());
			// ���[�U�[�O���[�v���f�\�����
			userGroupDetailBean.setUgRefDate(userGroupBean.getUgRefDate());
			// ���[�U�[ID
			userGroupDetailBean.setUserId(userId[i]);
			// ���[�U�[�O���[�v�ڍדo�^�������ݒ�
			userGroupDetailBean.setCreateBy(userGroupBean.getCreateBy());
			userGroupDetailBean.setCreateDate(userGroupBean.getCreateDate());
			userGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
			userGroupDetailBean.setUpdateDate(userGroupBean.getUpdateDate());
			// ���[�U�[�O���[�v�̖��׏��o�^
			userGroupDao.insertUserGroupDetail(userGroupDetailBean);
		}
	}

	/**
	 * ���[�U�O���[�v���X�V
	 * @param userGroupBean 
	 * @throws ParseException 
	 */
	public void updateUserGroupInfo(UserGroupBean userGroupBean) throws ParseException {
		
		// ���J���[�U�O���[�v���̍쐬�҂ƍ쐬���t���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("V_USER_GROUP_OPEN", "USER_GROUP_ID", userGroupBean.getUserGroupId());
		// ���J���[�U�O���[�v���̍쐬�҂ƍ쐬���t���Ȃ��ꍇ�A�\�񃆁[�U�O���[�v���̎擾
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_USER_GROUP_RESERVE", "USER_GROUP_ID", userGroupBean.getUserGroupId());
		} 
		// �o�^�������ݒ�
		userGroupBean.setCreateBy(createInfo.getCreateBy());
		userGroupBean.setCreateDate(createInfo.getCreateDate());
		userGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		userGroupBean.setUpdateDate(new Date());
		
		// �V�X�e�����t
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// ���f�\�����
		Date dgRefDate = StringUtil.parseTheDate(userGroupBean.getUgRefDate(), "yyyy/MM/dd");
		// ��ʓ��͂��ꂽ���f�\��� > �V�X�e�����t�̏ꍇ
		if(dateNow.before(dgRefDate)){
			// ���[�U�O���[�v�̗\������擾����
			UserGroupBean userGroupInfo =  userGroupDao.getUserGroupHeader(userGroupBean.getUserGroupId(), "V_USER_GROUP_RESERVE");
			// ���[�U�O���[�v�̗\���񑶍݂���ꍇ�A���[�U�O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(userGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_USER_GROUP_ID", userGroupInfo.getUserGroupId());			// ���[�U�O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupInfo.getUgRefDate());	// ���[�U�O���[�v���f�\�����
				
				// ���[�U�O���[�v���i�\��j�𕨗��폜����B
				userGroupDao.deleteUserGroupInfo(param);
			}
		// ��ʓ��͂��ꂽ���f�\��� <= �V�X�e�����t�̏ꍇ
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());				// ���[�U�O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// ���[�U�O���[�v���f�\�����
			// ���[�U�O���[�v���i�����j�𕨗��폜����B
			userGroupDao.deleteUserGroupInfo(param);
		}
		// ���[�U�O���[�v���o�^
		this.insertUserGroup(userGroupBean);
	}

	/**
	 * ���[�U�O���[�v���폜
	 * @param userGroupBean 
	 */
	public void deleteUserGroup(UserGroupBean userGroupBean) {
		// �����e�[�u���敪�@1:���J�@2:�\��
		String editFlag = userGroupBean.getEditFlag();
		// �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\��
		String originType = userGroupBean.getOriginType();
		// �폜�f�[�^�͗\��ł���ꍇ
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_USER_GROUP_ID", userGroupBean.getUserGroupId());				// ���[�U�O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupBean.getUgRefDate());		// ���[�U�O���[�v���f�\�����

			// ���[�U�O���[�v���i�\��j�𕨗��폜����B
			userGroupDao.deleteUserGroupInfo(param);
			// �폜�f�[�^�ɑ��ւ�����J�f�[�^���Ȃ��ꍇ�A�g�b�v�O���[�v���׏��e�[�u����_���폜����B
			if("2".equals(originType)){
				topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), userGroupBean.getUserGroupId(), "U");
			}
		// �폜�f�[�^�͌��J�ł���ꍇ
		} else {
			// ���[�U�O���[�v�̗\������擾����
			UserGroupBean userGroupInfo = userGroupDao.getUserGroupHeader(userGroupBean.getUserGroupId(), "V_USER_GROUP_RESERVE");
			// ���[�U�O���[�v�̗\���񑶍݂���ꍇ�A���[�U�O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(userGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				// ���[�U�O���[�vID
				param.put("PARA_USER_GROUP_ID", userGroupInfo.getUserGroupId());			
				// ���[�U�O���[�v���f�\�����
				param.put("PARA_REFLECTION_SCHEDULE_DATE", userGroupInfo.getUgRefDate());	
				// ���[�U�O���[�v���i�\��j�𕨗��폜����B
				userGroupDao.deleteUserGroupInfo(param);
			}
			// ���[�U�O���[�v���e�[�u����_���폜����B
			userGroupDao.updateUserGroupInfoInvalid(getLoginUserBean().getUserId(), userGroupBean);
			// �g�b�v�O���[�v���׏��e�[�u�����폜����B
			topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), userGroupBean.getUserGroupId(), "U");
		}
	}
}