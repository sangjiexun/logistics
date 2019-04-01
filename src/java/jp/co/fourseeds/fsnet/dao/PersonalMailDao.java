package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 *  ���[�������e�@�\DAO�����N���X
 * 
 * File Name: PersonalMailDao.java 
 * Created: 2015/12/02
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.0		2015/12/02          NTS            �쐬
 *
 **/
@Repository
public class PersonalMailDao extends BaseDao {
	
	/**
	 * �o�^�����擾
	 * @return
	 * @throws DataBaseException
	 */
	public int getMailCnt() {
		
		return (Integer)super.sqlSessionTemplate.selectOne("personalMail.getMailCnt");
	}
	
	/**
	 * �ʃ��[�������擾
	 * @param form
	 * @param param
	 *              �\�[�g����
	 * @return
	 * @throws DataBaseException
	 */
	public List<PersonalMailBean> getPersonalMailList(PersonalMailFormBean form,Map<String, Object> param) {
		// �o�^�˗��̂݃t���O
		param.put("PARA_ENTRY_FLAG", form.getEntryFlag());
		// �o�^�˗��̂݃t���O
		param.put("PARA_PERSONAL_FLAG", form.getPersonalFlag());
		// ID
		param.put("PARA_USER_ID", form.getSearchUserId());
		// ���p�Җ�
		param.put("PARA_USER_NAME", form.getSearchUserName());
		// ����
		param.put("PARA_DEPT_NAME", form.getSearchDeptName());
		// ���[���A�h���X
		param.put("PARA_MAIL_ID", form.getSearchMailId());
		// ���[���h���C��
		param.put("PARA_MAIL_SUFFIX", form.getSearchMailSuffixName());
		// ����
		param.put("PARA_USER_DIV", form.getSearchUserDiv());
		
		return super.sqlSessionTemplate.selectList("personalMail.getPersonalMailList",param);
	}
	
	/**
	 * �ސE�ҏ����擾�i�����ʒm�{�폜����{�ސE�����݁j
	 * @param form
	 * @param param
	 *              �\�[�g����
	 * @return
	 * @throws DataBaseException
	 */
	public List<PersonalMailBean> getQuitUserInfoList(PersonalMailFormBean form,Map<String, Object> param) {
		// ID
		param.put("PARA_USER_ID", form.getSearchUserId());
		// ���p�Җ�
		param.put("PARA_USER_NAME", form.getSearchUserName());
		// ����
		param.put("PARA_DEPT_NAME", form.getSearchDeptName());
		// ���[���A�h���X
		param.put("PARA_MAIL_ID", form.getSearchMailId());
		// ���[���h���C��
		param.put("PARA_MAIL_SUFFIX", form.getSearchMailSuffixName());
		// ����
		param.put("PARA_USER_DIV", form.getSearchUserDiv());
		
		return super.sqlSessionTemplate.selectList("personalMail.getQuitUserInfoList",param);
	}
	
	/**
	 * ���[�U�����擾
	 * @param form
	 * @param param
	 *              �\�[�g����
	 * @return
	 * @throws DataBaseException
	 */
	public List<PersonalMailBean> getUserInfoList(PersonalMailFormBean form,Map<String, Object> param) {
		// ID
		param.put("PARA_USER_ID", form.getSearchUserId());
		// ���p�Җ�
		param.put("PARA_USER_NAME", form.getSearchUserName());
		// ����
		param.put("PARA_DEPT_NAME", form.getSearchDeptName());
		// ���[���A�h���X
		param.put("PARA_MAIL_ID", form.getSearchMailId());
		// ���[���h���C��
		param.put("PARA_MAIL_SUFFIX", form.getSearchMailSuffixName());

		return super.sqlSessionTemplate.selectList("personalMail.getUserInfoList",param);
	}

	/**
	 * �p�X���[�h���擾
	 * @param �Ȃ�
	 * @param �擾�̃p�X���[�h
	 * 
	 */
	public String getPassword() {
		return super.sqlSessionTemplate.selectOne("personalMail.getPassword");
	}
	
	/**
	 * ���[�U�}�X�^�̖��׃f�[�^���擾
	 * @param userId
	 *                  ���[�U�[ID
	 * @return
	 *                  �擾�̖��׃f�[�^
	 */
	public PersonalMailBean getUserMasterDetailInfo (String userId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�[ID
		param.put("PARA_USER_ID", userId);
		// ���[�U�}�X�^�̖��׃f�[�^���擾 -
		return super.sqlSessionTemplate.selectOne("personalMail.getUserMasterDetailInfo",param);
	}
	
	/**
	 * �ʃ��[���A�h���X�e�[�u���f�[�^���擾
	 * @param sequence
	 *                  SEQUENCE 
	 * @return
	 *                  �擾�̖��׃f�[�^
	 */
	public PersonalMailBean getPersonalMailDetailInfo (String sequence) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// SEQUENCE
		param.put("PARA_SEQUENCE", sequence);
		// �ʃ��[���A�h���X�e�[�u���f�[�^���擾
		return super.sqlSessionTemplate.selectOne("personalMail.getPersonalMailDetailInfo",param);
	}
	
	/**
	 * �ʃ��[���A�h���X�e�[�u���f�[�^���擾
	 * @param form
	 *                  �t�H�[���r�� 
	 * @return
	 *                  �擾�̖��׃f�[�^
	 */ 
	public int getValidPersonalCnt (PersonalMailFormBean form) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�[��
		param.put("PARA_USER_SEI", form.getUserSei());
		// ���[�U�[��
		param.put("PARA_USER_MEI", form.getUserMei());
		// ���[���A�h���X
		param.put("PARA_MAIL_ADDRESS", form.getMailAddress());
		// �ʃ��[���A�h���X�e�[�u���f�[�^���擾
		return (Integer)super.sqlSessionTemplate.selectOne("personalMail.getValidCnt",param);
	}
	
	/**
	 * �ʃ��[���A�h���X�e�[�u���f�[�^���擾
	 * @param form
	 *                  �t�H�[���r�� 
	 * @return
	 *                  �擾�̖��׃f�[�^
	 */
	public int getValidMailCnt (PersonalMailFormBean form) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[���A�h���X
		param.put("PARA_MAIL_ADDRESS", form.getMailAddress());
		// �ʃ��[���A�h���X�e�[�u���f�[�^���擾
		return (Integer)super.sqlSessionTemplate.selectOne("personalMail.getValidCnt",param);
	}
	
	/**
	 * �ʃ��[���𕨗��폜
	 * @param form
	 *                  �t�H�[���r�� 
	 * @return �Ȃ�
	 * 
	 */
	public void deletePersonalMail(PersonalMailFormBean form) {
		
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�[ID������ꍇ�B
		if(!StringUtil.isEmpty(form.getUserId())) {
			// ���[���A�h���X
			param.put("PARA_USER_ID", form.getUserId());
		} else {
			// ���[���A�h���X
			param.put("PARA_USER_SEI", form.getUserMei());
			// ���[���A�h���X
			param.put("PARA_USER_MEI", form.getUserSei());
			// ���[���A�h���X
			param.put("PARA_MAIL_ADDRESS", form.getMailAddress());
		}
		// �ʃ��[���𕨗��폜
		super.sqlSessionTemplate.delete("personalMail.deletePersonalMail",param);
	}
	
	/**
	 * �ʃ��[����o�^
	 * @param form
	 *                  �t�H�[���r�� 
	 * @return �Ȃ�
	 * 
	 */
	public void insertPersonalMail(PersonalMailFormBean form) {
		// �ʃ��[����o�^
		super.sqlSessionTemplate.insert("personalMail.insertPersonalMail",form);
	}
	
	/**
	 * ���[�U�̃��[���ƃ��[�U���O���擾
	 * @param userId
	 *                 ���[�U�[ID 
	 * @param pwdSendDiv
	 *                 �p�X���[�h���t�敪 
	 * @return
	 * 
	 */
	public PersonalMailBean getMailToUserInfo(String userId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�[ID 
		param.put("PARA_USER_ID", userId);
		// ���[�U�̃��[���A�h���[�X���X�V
		return super.sqlSessionTemplate.selectOne("personalMail.getMailToUserInfo",param);
	}
	
	/**
	 * ���M���[�U���O���擾
	 * @param userId
	 *                 ���[�U�[ID 
	 * @return
	 *                 �擾�̃��[�U�[����
	 * 
	 */
	public String getFromUserName(String userId) {
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		// ���[�U�[ID 
		param.put("PARA_USER_ID", userId);
		// ���[�U�̃��[���A�h���[�X���X�V
		return super.sqlSessionTemplate.selectOne("personalMail.getFromUserName",param);
	}
	
	/**
	 * �ʃ��[����_���폜
	 * @param form
	 */
	public void deletePersonalMailInvalid(PersonalMailFormBean form) {
		// �ʃ��[����_���폜
		super.sqlSessionTemplate.update("personalMail.deletePersonalMailInvalid",form);
	}
	
	/**
	 * �ʃ��[�����X�V
	 * @param form
	 */
	public void updatePersonalMail(PersonalMailFormBean form) {
		// �ʃ��[�����X�V
		super.sqlSessionTemplate.update("personalMail.updatePersonalMail",form);
	}
}