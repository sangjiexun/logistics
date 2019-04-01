
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

import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupBean;
import jp.co.fourseeds.fsnet.beans.storeGroup.StoreGroupDetailBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.StoreGroupDao;
import jp.co.fourseeds.fsnet.dao.TopGroupDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * �X�܃O���[�v���@�\�T�[�r�X�����N���X
 * 
 * File Name: StoreGroupService.java 
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
@Component
public class StoreGroupService extends BaseBusinessService{

	@Autowired
	private StoreGroupDao storeGroupDao;
	
	@Autowired
	private TopGroupDao topGroupDao;
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * �X�܃O���[�v�������
	 * @param storeGroupBean�@��ʓ��͒l
	 * @param strOrderBy�@�\�[�g
	 * @return�@��������
	 */
	public List<StoreGroupBean> getStoreGroupList(StoreGroupBean storeGroupBean, String strOrderBy) {
		String searchSql = "";
		// '1'(�V�X�e���Ǘ���)�ȊO�̏ꍇ
		if(!"1".equals(getLoginUserBean().getRole())){
			searchSql = "storeGroup.getStoreGroupList_Open";
		}else{
			if("0".equals(storeGroupBean.getSearchOriginType())){
				// �S��
				searchSql = "storeGroup.getStoreGroupList_All";
			}else if("1".equals(storeGroupBean.getSearchOriginType())){
				// ���ݗL���Ȃ��̑S��
				searchSql = "storeGroup.getStoreGroupList_Valid";
			}else if("2".equals(storeGroupBean.getSearchOriginType())){
				// �\����̂�
				searchSql = "storeGroup.getStoreGroupList_Rsv";
			}
		}
		
		return storeGroupDao.getStoreGroupList(searchSql, storeGroupBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * �X�܃O���[�vID�ƌ����e�[�u���ɂ���āA�X�܃O���[�v���ƓX�܃O���[�v��������L���̂ݓX�܏����擾����B
	 * @param storeGroupBean�@��ʓ��͒l
	 * @return ��������
	 */
	public StoreGroupBean getStoreGroupInfo(StoreGroupBean storeGroupBean) {
		// �X�܃O���[�vID
		String storeGroupId = storeGroupBean.getSearchStoreGroupId();
		// �X�܃O���[�v��񌟍��e�[�u��
		String storeGroupTable = "";
		// ���J�̏ꍇ
		if("1".equals(storeGroupBean.getEditFlag())){
			storeGroupTable = "V_STORE_GROUP_OPEN";
		// �\��̏ꍇ
		} else {
			storeGroupTable = "V_STORE_GROUP_RESERVE";
		}
		StoreGroupBean storeGroupInfo = storeGroupDao.getStoreGroupHeader(storeGroupId, storeGroupTable);
		if(storeGroupInfo != null){
			List<StoreGroupDetailBean> shareUserList = storeGroupDao.getShareUserList(storeGroupId, storeGroupInfo.getSgRefDate());
			List<StoreGroupDetailBean> storeList = storeGroupDao.getStoreGroupDetail(storeGroupId, storeGroupTable, "Y");
			List<StoreGroupDetailBean> storeDetailList = storeGroupDao.getStoreDetail(storeGroupId, storeGroupTable, "Y");
			storeGroupInfo.setShareUserList(shareUserList);
			storeGroupInfo.setStoreList(storeList);
			storeGroupInfo.setStoreDetailList(storeDetailList);
		}
		return storeGroupInfo;
	}
	
	/**
	 * �X�܃O���[�vID���̔Ԃ���
	 * 
	 */
	public String getNewStoreGroupId() {
		return storeGroupDao.getNewStoreGroupId();
	}

	/**
	 * �o�^�σf�[�^�Ƃ̓X�܃O���[�v���̏d���f�[�^���擾
	 * 
	 */
	public boolean checkStoreGroupNmRepeat(StoreGroupBean storeGroupBean, String eventType) {
		int count = storeGroupDao.getStoreGroupNmCheckInfo(storeGroupBean, eventType);
		// �o�^�σf�[�^�Ƃ̃��[�U�O���[�v���̏d���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}

	/**
	 * �X�܃O���[�v���o�^
	 * @param userGroupBean 
	 */
	public void insertStoreGroup(StoreGroupBean storeGroupBean) {
		// �������X�܃O���[�v�w�b�_�[���o�^
		storeGroupDao.insertStoreGroup(storeGroupBean);
		// �X�܃O���[�v�̖��׏��o�^
		// �X��ID
		String[] storeId = StringUtil.split(storeGroupBean.getStoreId(),",");
		for(int i = 0; i < storeId.length; i++){
			StoreGroupDetailBean storeGroupDetailBean = new StoreGroupDetailBean();
			// �X�܃O���[�vID
			storeGroupDetailBean.setStoreGroupId(storeGroupBean.getStoreGroupId());
			// �X��ID
			storeGroupDetailBean.setStoreId(storeId[i].substring(1, storeId[i].length()));
			// �����t���O
			storeGroupDetailBean.setInputFlag(storeId[i].substring(0, 1));
			// �X�܃O���[�v���f�\�����
			storeGroupDetailBean.setSgRefDate(storeGroupBean.getSgRefDate());
			// �l�O���[�v�t���O
			storeGroupDetailBean.setGrouptype(storeGroupBean.getGrouptype());
			// �X�܃O���[�v�ڍדo�^�������ݒ�
			storeGroupDetailBean.setCreateBy(storeGroupBean.getCreateBy());
			storeGroupDetailBean.setCreateDate(storeGroupBean.getCreateDate());
			storeGroupDetailBean.setUpdateBy(getLoginUserBean().getUserId());
			storeGroupDetailBean.setUpdateDate(storeGroupBean.getUpdateDate());
			// �������X�܃O���[�v�̖��׏��o�^
			storeGroupDao.insertStoreGroupDetail(storeGroupDetailBean);
		}
		// �l�O���[�v�t���O�͌l�̏ꍇ�A���L�Ώێҏ��o�^
		if (ConstantContainer.PERSONAL_KEYCODE.equals(storeGroupBean.getGrouptype())) {
			// ���L�Ώێ҃��X�g���擾
			List<StoreGroupDetailBean> shareUserList = storeGroupBean.getShareUserList();
			if(!StringUtil.isBlank(shareUserList)){
				for(int i = 0; i < shareUserList.size(); i++){
					StoreGroupDetailBean shareUserBean = shareUserList.get(i);
					// �X�܃O���[�vID
					shareUserBean.setStoreGroupId(storeGroupBean.getStoreGroupId());
					// �X�܃O���[�v���f�\�����
					shareUserBean.setSgRefDate(storeGroupBean.getSgRefDate());
					// ���L�Ώێҏڍדo�^�������ݒ�
					shareUserBean.setCreateBy(storeGroupBean.getCreateBy());
					shareUserBean.setCreateDate(storeGroupBean.getCreateDate());
					shareUserBean.setUpdateBy(getLoginUserBean().getUserId());
					shareUserBean.setUpdateDate(storeGroupBean.getUpdateDate());
					// �������X�܃O���[�v�̋��L�Ώێҏ��o�^
					storeGroupDao.insertShareUser(shareUserBean);
				}
			}
		}
		
		// ����X�܃O���[�v�����l�����폜
		storeGroupDao.deleteDepartmentStoreGroupUser(storeGroupBean);
		
		// ����X�܃O���[�v�����l�����o�^
		storeGroupDao.insertDepartmentStoreGroupUser(storeGroupBean);
	}

	/**
	 * �X�܃O���[�v���X�V
	 * @param storeGroupBean 
	 * @throws ParseException 
	 */
	public void updateStoreGroupInfo(StoreGroupBean storeGroupBean) throws ParseException {
		
		// ���J�X�܃O���[�v���̍쐬�҂ƍ쐬���t���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("V_STORE_GROUP_OPEN", "STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		// ���J�X�܃O���[�v���̍쐬�҂ƍ쐬���t���Ȃ��ꍇ�A�\��X�܃O���[�v���̎擾
		if(StringUtil.isBlank(createInfo)){
			createInfo = commonDao.getDbCommonInfo("V_STORE_GROUP_RESERVE", "STORE_GROUP_ID", storeGroupBean.getStoreGroupId());
		} 
		// �o�^�������ݒ�
		storeGroupBean.setCreateBy(createInfo.getCreateBy());
		storeGroupBean.setCreateDate(createInfo.getCreateDate());
		storeGroupBean.setUpdateBy(getLoginUserBean().getUserId());
		storeGroupBean.setUpdateDate(new Date());
		
		// �V�X�e�����t
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// ���f�\�����
		Date sgRefDate = StringUtil.parseTheDate(storeGroupBean.getSgRefDate(), "yyyy/MM/dd");
		// ��ʓ��͂��ꂽ���f�\��� > �V�X�e�����t�̏ꍇ
		if(dateNow.before(sgRefDate)){
			// �X�܃O���[�v�̗\������擾����
			StoreGroupBean storeGroupInfo =  storeGroupDao.getStoreGroupHeader(storeGroupBean.getStoreGroupId(), "V_STORE_GROUP_RESERVE");
			// �X�܃O���[�v�̗\���񑶍݂���ꍇ�A�X�܃O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(storeGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_STORE_GROUP_ID", storeGroupInfo.getStoreGroupId());			// �X�܃O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupInfo.getSgRefDate());	// �X�܃O���[�v���f�\�����
				
				// �X�܃O���[�v���i�\��j�𕨗��폜����B
				storeGroupDao.deleteStoreGroupInfo(param);
			}
		// ��ʓ��͂��ꂽ���f�\��� <= �V�X�e�����t�̏ꍇ
		} else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());				// �X�܃O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", "");									// �X�܃O���[�v���f�\�����
			// �X�܃O���[�v���i�����j�𕨗��폜����B
			storeGroupDao.deleteStoreGroupInfo(param);
		}
		// ���[�U�O���[�v���o�^
		this.insertStoreGroup(storeGroupBean);
	}

	/**
	 * �X�܃O���[�v���폜
	 * @param storeGroupBean 
	 */
	public void deleteStoreGroup(StoreGroupBean storeGroupBean) {
		// �����e�[�u���敪�@1:���J�@2:�\��
		String editFlag = storeGroupBean.getEditFlag();
		// �f�[�^��ށ@1:���J�@2:�\��@12:���J&�\��
		String originType = storeGroupBean.getOriginType();
		// �폜�f�[�^�͗\��ł���ꍇ
		if("2".equals(editFlag)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("PARA_STORE_GROUP_ID", storeGroupBean.getStoreGroupId());				// �X�܃O���[�vID
			param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupBean.getSgRefDate());		// �X�܃O���[�v���f�\�����

			// �X�܃O���[�v���i�\��j�𕨗��폜����B
			storeGroupDao.deleteStoreGroupInfo(param);
			// �폜�f�[�^�ɑ��ւ�����J�f�[�^���Ȃ��ꍇ�A�g�b�v�O���[�v���׏��e�[�u����_���폜����B
			if("2".equals(originType)){
				topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), storeGroupBean.getStoreGroupId(), "S");
			}
		// �폜�f�[�^�͌��J�ł���ꍇ
		} else {
			// �X�܃O���[�v�̗\������擾����
			StoreGroupBean storeGroupInfo = storeGroupDao.getStoreGroupHeader(storeGroupBean.getStoreGroupId(), "V_STORE_GROUP_RESERVE");
			// ���[�U�O���[�v�̗\���񑶍݂���ꍇ�A���[�U�O���[�v�̗\����𕨗��폜����
			if(!StringUtil.isBlank(storeGroupInfo)){
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("PARA_STORE_GROUP_ID", storeGroupInfo.getStoreGroupId());			// �X�܃O���[�vID
				param.put("PARA_REFLECTION_SCHEDULE_DATE", storeGroupInfo.getSgRefDate());	// �X�܃O���[�v���f�\�����

				// �X�܃O���[�v���i�\��j�𕨗��폜����B
				storeGroupDao.deleteStoreGroupInfo(param);
			}
			// �X�܃O���[�v���e�[�u����_���폜����B
			storeGroupDao.updateStoreGroupInfoInvalid(getLoginUserBean().getUserId(), storeGroupBean);
			// �g�b�v�O���[�v���׏��e�[�u�����폜����B
			topGroupDao.updateTopGroupDetailInvalid(getLoginUserBean().getUserId(), storeGroupBean.getStoreGroupId(), "S");
		}
	}

	/**
	 * �X�܌��������s�i�g�D�j
	 * @param storeGroupBean�@��ʓ��͒l
	 * @return ��������
	 */
	public List<StoreGroupDetailBean> getStoreList(StoreGroupBean storeGroupBean) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���
		param.put("PARA_COMPANY_ID", storeGroupBean.getConditionCompanyId());
		// ����
		param.put("PARA_UNIFICATION_ID", storeGroupBean.getConditionUnificationId());
		// ����
		param.put("PARA_BUSINESS_ID", storeGroupBean.getConditionBusinessId());
		// �c�ƕ�
		param.put("PARA_SALES_ID", storeGroupBean.getConditionSalesId());
		// �X�܃R�[�h
		param.put("PARA_STORE_ID", storeGroupBean.getSearchStoreId());
		// �X�ܖ���
		param.put("PARA_STORE_NAME", storeGroupBean.getSearchStoreName());
		// �X�܋敪
		param.put("PARA_CONDITION_FC_FLAG2", storeGroupBean.getConditionFcFlag2());
		// �X��FC�敪��
		param.put("PARA_STORE_FC_DISPLAY", "Y");
		
		List<StoreGroupDetailBean> getStoreList = null;
		// ��ʏ����y �X�܃R�[�h �A �X�ܖ���  �z���݂̏ꍇ
		if (!StringUtil.isBlank(storeGroupBean.getSearchStoreId()) || !StringUtil.isBlank(storeGroupBean.getSearchStoreName())) {
			getStoreList = storeGroupDao.getStoreList_right(param);
		} else {
			// ��ʏ����y ��ЃR�[�h  �z���݂��Ȃ��̏ꍇ
			if (StringUtil.isBlank(storeGroupBean.getConditionCompanyId())) {
				getStoreList = storeGroupDao.getStoreList_right(param);
			} else {
				// ��ʏ����y �c�ƕ��R�[�h  �z���݂̏ꍇ
				if (!StringUtil.isBlank(storeGroupBean.getConditionSalesId())){
					getStoreList = storeGroupDao.getStoreList_right(param);
				} else {
					getStoreList = storeGroupDao.getStoreList_left(param);
				}
			}
		}
		return getStoreList;
	}

	/**
	 * �X�܃O���[�v�̗\������擾
	 * 
	 */
	public boolean checkRsvStoreGroupInfo(StoreGroupBean storeGroupBean) {
		// �X�܃O���[�vID
		String storeGroupId = storeGroupBean.getSearchStoreGroupId();
		// �X�܃O���[�v��񌟍��e�[�u��
		String storeGroupTable = "V_STORE_GROUP_RESERVE";
		StoreGroupBean storeGroupInfo = storeGroupDao.getStoreGroupHeader(storeGroupId, storeGroupTable);
		// �X�܃O���[�v�̗\����𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (!StringUtil.isBlank(storeGroupInfo)) {
			return true;
		}
		return false;
	}

	/**
	 * ���������N���N���b�N�A�K�w���x������
	 * @param deptGroupBean 
	 */
	public String getDeptLevel(Map<String, Object> param) {
		
		return storeGroupDao.getDeptLevel(param);
	}
	
	/**
	 * ��Ѓ����N���N���b�N�A������������
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getSecondDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getSecondDeptList(param);
	}
	
	/**
	 * ���������N���N���b�N�A�������ƌ���
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getThirdDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getThirdDeptList(param);
	}
	
	/**
	 * ���ƃ����N���N���b�N�A�����c�ƕ�����
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFourthDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getFourthDeptList(param);
	}
	
	/**
	 * �c�ƕ������N���N���b�N�A�����X�܌���
	 * @param deptGroupBean 
	 */
	public List<Map<String, String>> getFifthDeptList(Map<String, Object> param) {
		
		return storeGroupDao.getFifthDeptList(param);
	}
}

