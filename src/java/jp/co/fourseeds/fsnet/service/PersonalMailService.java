package jp.co.fourseeds.fsnet.service;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.service.CommonService;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailFormBean;
import jp.co.fourseeds.fsnet.common.util.SendPersonalMail;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PersonalMailDao;

/**
 *  ���[�������e�@�\�T�[�r�X�����N���X
 * 
 * File Name: PersonalMailService.java 
 * Created: 2015/12/02
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.0		2015/12/02		    NTS            �쐬
 *
 **/
@Component
public class PersonalMailService extends BaseBusinessService{

	@Autowired
	private PersonalMailDao personalMailDao;
	
	@Autowired
	private CommonService commonService;
	
	// ���[�����M
	SendPersonalMail sendMail = new SendPersonalMail();
	
	// �y�C���g���z�ʃ��[���o�^�����ʒm
	private static String PIZZALA_SETTING_KB = "PIZZALA_SETTING";
	
	// �y�C���g���z�ʃ��[���폜�����ʒm
	private static String PIZZALA_EXPIRED_KB = "PIZZALA_EXPIRED";
	
	// �y�C���g���z�ʃ��[���ݒ�˗��ʒm(robuchon.jp)
	private static String ROBUCHON_SETTING_KB = "ROBUCHON_SETTING";
	
	// �y�C���g���z�ʃ��[���o�^�����ʒm(robuchon.jp)
	private static String ROBUCHON_FINISH_KB = "ROBUCHON_FINISH";
	
	// �y�C���g���z�ʃ��[���폜�ݒ�˗��ʒm(robuchon.jp)
	private static String ROBUCHON_EXPIRED_KB = "ROBUCHON_EXPIRED";
	
	// �y�C���g���z�ʃ��[���폜�����ʒm(robuchon.jp)
	private static String ROBUCHON_EXPIRED_FINISH_KB = "ROBUCHON_EXPIRED_FINISH";
	
	// �ő�o�^�\�����擾
	private static String FIELD_NAME_MAX_MAIL_NUM = "MAX_MAIL_NUM";
	/**
	 * �ʃ��[�����X�g���
	 * @param PersonalMailForm
	 * @param asc_desc
	 *              �\�[�g�~���E����
	 * @param sort_key
	 *              �\�[�g����
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public void getPersonalMailSearchInfo(PersonalMailFormBean form,String asc_desc,String sort_key) {
		
		// ��������MAP
		Map<String, Object> param = new HashMap<String, Object>();
		
		int totalMailCnt = 0;
		List<LabelValueBean> labelList = commonService.getDivisionMaster(FIELD_NAME_MAX_MAIL_NUM);
		if (labelList.size()!=0) {
			String cnt = labelList.get(0).getValue();
			if (NumberUtil.isNumber(cnt)) {
				totalMailCnt = Integer.parseInt(cnt);
			}
		}
		form.setTotalMailCnt(totalMailCnt);

		// �o�^�\�c�����擾
		int mailCnt = personalMailDao.getMailCnt();
		int validMailCnt = totalMailCnt - mailCnt;
		form.setValidMailCnt(validMailCnt);

		// �ʃ��[�������擾
		List<PersonalMailBean> personalMailList = personalMailDao.getPersonalMailList(form,param);
		
		// �u�ސE�҂��܂ށv�`�F�b�N����A�u�o�^�˗��̂݁v�`�F�b�N���Ȃ�
		List<PersonalMailBean> quitUserList = new ArrayList<PersonalMailBean>();
		if (form.getQuitFlag() != null && "1".equals(form.getQuitFlag()) && !"1".equals(form.getEntryFlag())) {
			// �ސE�ҏ����擾
			quitUserList = personalMailDao.getQuitUserInfoList(form,param);
		}
		
		// ���[�U�����擾
		List<PersonalMailBean> userInfoList = new ArrayList<PersonalMailBean>();
		// �u�o�^�˗��̂݁v�`�F�b�N���Ȃ��A�u�ʐݒ�̂݁v�`�F�b�N���Ȃ��A�u�ސE�҂��܂ށv�`�F�b�N���Ȃ�
		if (!"1".equals(form.getEntryFlag()) && !"1".equals(form.getPersonalFlag()) && !"1".equals(form.getQuitFlag())) {
			param.put("PARA_USERDIV", FsPropertyUtil.getStringProperty("mail.userDiv.fieldName"));
			userInfoList = personalMailDao.getUserInfoList(form,param);
		}
		
		// ���[�U�h�c���X�g
		List<String> userIdList = new ArrayList<String>();
		// ���ʃ��X�g
		List<PersonalMailBean> resultList = new ArrayList<PersonalMailBean>();
		
		// �ʃ��[����񃊃X�g
		if (personalMailList != null) {
			for (int i = 0; i < personalMailList.size(); i ++) {
				// �ʃ��[�����擾����
				PersonalMailBean bean = (PersonalMailBean) personalMailList.get(i);
				String field = FsPropertyUtil.getStringProperty("mail.userDiv.fieldName");
				// �敪���̂��擾
				String mailUserDivName = commonService.getDivisionName(field, bean.getMailUserDivision());
				// �敪���̂�ݒ�
				bean.setMailUserDivName(mailUserDivName);
				String field1 = FsPropertyUtil.getStringProperty("profile.div.fieldName");
				// �敪���̂��擾
				String profileName = commonService.getDivisionName(field1, bean.getProfileDiv());
				// �敪���̂�ݒ�
				bean.setProfileDivName(profileName);
				// ���ʃ��X�g��ǉ�����i�ʃ��[�����j�B
				resultList.add(bean);
				// ���ʃ��X�g��ǉ�����i���[�U�[ID�j�B
				userIdList.add(bean.getUserId());
			}
		}
		// �ސE�ҏ�񃊃X�g		
		if (quitUserList != null) {
			for (int i = 0; i < quitUserList.size(); i ++) {
				// �ʃ��[�����擾����
				PersonalMailBean bean = (PersonalMailBean)quitUserList.get(i);
				// ���ʃ��X�g�ɑސE�҂̃��[�U�[ID������܂��ꍇ�A
				if (!userIdList.contains(bean.getUserId()) || "".equals(bean.getUserId())) {
					String field = FsPropertyUtil.getStringProperty("mail.userDiv.fieldName");
					// �敪���̂��擾
					String mailUserDivName = commonService.getDivisionName(field, bean.getMailUserDivision());
					// �敪���̂�ݒ�
					bean.setMailUserDivName(mailUserDivName);
					String field1 = FsPropertyUtil.getStringProperty("profile.div.fieldName");
					// �敪���̂��擾
					String profileName = commonService.getDivisionName(field1, bean.getProfileDiv());
					// �敪���̂�ݒ�
					bean.setProfileDivName(profileName);
					// ���ʃ��X�g��ǉ�����i�ʃ��[�����j�B
					resultList.add(bean);
					// ���ʃ��X�g��ǉ�����i���[�U�[ID�j�B
					userIdList.add(bean.getUserId());
				}
			}
		}
		// ���[�U�}�X�g��񃊃X�g
		if (userInfoList != null) {
			String userDiv = StringUtil.nullToBlank(form.getSearchUserDiv());
			for (int i = 0; i < userInfoList.size(); i ++) {
				PersonalMailBean bean = (PersonalMailBean)userInfoList.get(i);
				// �{���E�X�܎Ј���ݒ�
				bean.setMailUserDivision(bean.getpEmployeeFlag());
				
				if (!userIdList.contains(bean.getUserId())) {
					// ���ނ�I�����Ȃ�
					if ("".equals(userDiv)) {
						resultList.add(bean);
						userIdList.add(bean.getUserId());

					// �{���Ј���I��
					} else if ("0".equals(userDiv)) {
						if (!"".equals(bean.getUserId()) && "0".equals(bean.getpEmployeeFlag())) {
							resultList.add(bean);
							userIdList.add(bean.getUserId());
						}

					// �X�܎Ј���I��
					} else if ("1".equals(userDiv)) {
						if ("1".equals(bean.getpEmployeeFlag())) {
							resultList.add(bean);
							userIdList.add(bean.getUserId());
						}
					}
				}
			}
		}
		
		// ��ʃ\�[�g
		this.sort(resultList,asc_desc,sort_key);
		// �������ʃ��X�g
		form.setPersonalMailList(resultList);
	}
	
	/**
	 * CSV�t�@�C���o��
	 * 
	 * @param response
	 *           
	 * @param formBean
	 *           
	 * @return
	 * @throws Exception 
	 *           
	 * */
	public void opeDownloadCsv(HttpServletResponse response,PersonalMailFormBean formBean) throws Exception {
		// CSV�t�@�C�����̂��擾����B
		String csvName = FsPropertyUtil.getStringProperty("page.csv.name");
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + getCsvFileName(csvName));
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		OutputStream os = response.getOutputStream();
		OutputStreamWriter outPut = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(outPut); 
		try {
			//CSV�w�b�_
			writer.write("���p�Җ�,");
			writer.write("ID,");
			writer.write("���[�U�[��,");
			writer.write("�p�X���[�h,");
			writer.write("�v���t�@�C���敪,");
			writer.write("����,");
			writer.write("���[���A�h���X,");
			writer.write("���p�J�n��,");
			writer.write("���p�I����,");
			writer.write("����,");
			writer.write("Softbank�o�^��,");
			writer.write("�ސE��,");
			writer.write("Softbank�폜��,");
			writer.write("Google�폜��");
			writer.write("\r\n");
			// CSV����
			List<PersonalMailBean> list = (List<PersonalMailBean>)formBean.getPersonalMailCsvList();
			PersonalMailBean row = new PersonalMailBean();
			if (list != null && list.size() != 0) {
				for (int i = 0; i < list.size(); i++) {
					row = (PersonalMailBean)list.get(i);
					//���p�Җ�
					writer.write(StringUtil.nullToBlank(row.getUserSei()) + StringUtil.nullToBlank(row.getUserMei()) + ",");
					//ID
					writer.write(StringUtil.nullToBlank(row.getUserId()) + ",");
					//���[�U�[��
					writer.write(StringUtil.nullToBlank(row.getMailId()) + ",");
					//�p�X���[�h
					writer.write(StringUtil.nullToBlank(row.getPassword()) + ",");
					//�v���t�@�C���敪
					writer.write(StringUtil.nullToBlank(row.getProfileDivName()) + ",");
					//����
					writer.write(StringUtil.nullToBlank(row.getMailUserDivName()) + ",");
					//���[���A�h���X
					writer.write(StringUtil.nullToBlank(row.getMailAddress()) + ",");
					//���p�J�n��
					writer.write(StringUtil.nullToBlank(row.getMailSet()) + ",");
					//���p�I����
					writer.write(StringUtil.nullToBlank(row.getMailEx()) + ",");
					//����
					writer.write(StringUtil.nullToBlank(row.getDepartmentName()) + ",");
					//Softbank�o�^��
					writer.write(StringUtil.nullToBlank(row.getEntryDate()) + ",");
					//�ސE��
					writer.write(StringUtil.nullToBlank(row.getRetirementDate()) + ",");
					//Softbank�폜��
					writer.write(StringUtil.nullToBlank(row.getDeleteDate1()) + ",");
					//Google�폜��
					writer.write(StringUtil.nullToBlank(row.getDeleteDate2()));
					//���s
					writer.write("\r\n");
				}
			}
		} catch(Exception e) {
			throw e;
		} finally {
			writer.close();
			outPut.close();
			// �o�͏���
			os.flush();
			os.close();
		}
	}
	
	/**
	 * �p�X���[�h���擾
	 * @param form
	 *            �t�H���r�[��
	 * */
	public void getAddInfo(PersonalMailFormBean form) {
		// �p�X���[�h���擾
		String password = personalMailDao.getPassword();
		// �V�X�e�����t
		Date date = new Date();
		// ���ނ�ݒ�
		form.setMailUserDivision("2");
		// �t�H�[�}�b�g
		form.setMailSet(StringUtil.formatTheDate(date, "yyyy/MM/dd"));
		// ���p�I����(�m����)
		form.setMailEx("2199/12/31");
		// �p�X���[�h��ݒ肷��B
		form.setPassword(password);
	}
	
	/**
	 * �w�藘�p�Ҍʃ��[���ڍ׏��
	 * 
	 * @param PersonalMailForm
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public void getPersonalMailDetailInfo(PersonalMailFormBean form) {
		PersonalMailBean detailBean = new PersonalMailBean();
		// �ʐݒ�t���O�i�k���F�ʃ��[�����݂��Ȃ��A0�F�ʃ��[���ݒ�˗��A1�F�ʃ��[�������ʒm�j
		if (StringUtil.isEmpty(form.getPersonalMailSettingFlag())) {
			// ���[�U�}�X�^�̖��׃f�[�^���擾
			detailBean = personalMailDao.getUserMasterDetailInfo(form.getUserId());
			// �f�[�^������ꍇ
			if (detailBean != null) {
				// �{���E�X�܎Ј���ݒ�
				if ("1".equals(detailBean.getSpecificUserFlag())) {
					form.setMailUserDivision("1");
				} else {
					if ("1".equals(detailBean.getpEmployeeFlag())) {
						form.setMailUserDivision("1");
					} else {
						form.setMailUserDivision("0");
					}
				}
				// ���[���A�h���X
				String mailAddress = detailBean.getMailAddress();
				// ���[���A�h���X������ꍇ
				if (!StringUtil.isEmpty(mailAddress)) {
					if (mailAddress.indexOf("@") > 0) {
						// ���[���A�h���X��
						form.setDisplayMailId(mailAddress.substring(0, mailAddress.indexOf("@")));
						// ���[����ʖ�
						form.setMailSuffixName(mailAddress.substring(mailAddress.indexOf("@") + 1, mailAddress.length()));
					}
				}
			}
		} else {
			// �ʃ��[���A�h���X�e�[�u���f�[�^���擾
			detailBean = personalMailDao.getPersonalMailDetailInfo(form.getSequence());
			// �f�[�^������ꍇ
			if (detailBean != null) {
				// �X�e�[�^�X
				form.setMailTaskStatus(detailBean.getMailTaskStatus());
				// ����
				form.setMailUserDivision(detailBean.getMailUserDivision());
				// Softbank�o�^��
				form.setEntryDate(detailBean.getEntryDate());
				// �ސE��
				form.setRetirementDate(detailBean.getRetirementDate());
				// Softbank�폜��
				form.setDeleteDate1(detailBean.getDeleteDate1());
				// Google�폜��
				form.setDeleteDate2(detailBean.getDeleteDate2());
				// �v���t�@�C���敪
				form.setProfileDiv(detailBean.getProfileDiv());
				// ���[���h���C���t���O
				form.setMailSuffixFlag(detailBean.getMailSuffixFlag());
				// �˗��҂h�c
				form.setRequestId(detailBean.getRequestId());
				// �폜�t���O
				form.setDeleteFlag(detailBean.getDeleteFlag());
				// ��Ǝ҂h�c
				form.setRegistryId(detailBean.getRegistryId());
				// ���[���A�h���X
				String mailAddress = detailBean.getMailAddress();
				// ���[���A�h���X������ꍇ
				if (!StringUtil.isEmpty(mailAddress)) {
					if (mailAddress.indexOf("@") > 0) {
						// ���[���A�h���X��
						form.setDisplayMailId(mailAddress.substring(0, mailAddress.indexOf("@")));
					}
				}
			}
		}
		if (detailBean != null) {
			// ID
			form.setUserId(detailBean.getUserId());
			// ���p�Ґ�
			form.setUserSei(detailBean.getUserSei());
			// ���p�Җ�
			form.setUserMei(detailBean.getUserMei());
			// ���[���A�h���X
			form.setMailAddress(detailBean.getMailAddress());
			// ���p�J�n��
			form.setMailSet(detailBean.getMailSet());
			// ���p�I����
			form.setMailEx(detailBean.getMailEx());
			// ��������
			form.setDepartmentName(detailBean.getDepartmentName());
			// ����ID
			form.setDepartmentId(detailBean.getDepartmentId());
			// ���[�U�[��
			form.setMailId(detailBean.getMailId());
			// �p�X���[�h
			form.setPassword(detailBean.getPassword());
		}
	}
	
	/**
	 * �A���o�C�g�ʑ��ݐ��`�F�b�N
	 * @param form
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public int getValidPersonalCnt(PersonalMailFormBean form) {
		// ���[���A�h���X���擾����B
		String perMailAddr = form.getDisplayMailId() + "@" + form.getMailSuffixName();
		// ���[���A�h���X��ݒ肷��B
		form.setMailAddress(perMailAddr);
		return personalMailDao.getValidPersonalCnt(form);
	}
	
	
	/**
	 * �����ʃ��[�����ݐ��`�F�b�N
	 * @param form
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public int getValidMailCnt(PersonalMailFormBean form) {
		// ���[���A�h���X���擾����B
		String perMailAddr = form.getDisplayMailId() + "@" + form.getMailSuffixName();
		// ���[���A�h���X��ݒ肷��B
		form.setMailAddress(perMailAddr);
		return personalMailDao.getValidMailCnt(form);
	}
	
	/**
	 * �u�ݒ�˗��v�{�^������
	 * 
	 * @param form
	 *               �t�H�[���r�[��
	 * @throws Exception
	 */
	public void modifyModeEdit(PersonalMailFormBean form) throws Exception{
		
		// �˗��҂h�c
		form.setRequestId(getLoginUserBean().getUserId());
		// ���[���h���C���t���O
		if(!"1".equals(form.getMailSuffixFlag())) {
			// ��Ǝ҂h�c
			form.setRegistryId(getLoginUserBean().getUserId());
		}
		
		// �ʃ��[���𕨗��폜
		personalMailDao.deletePersonalMail(form);
		// �ʃ��[����o�^
		personalMailDao.insertPersonalMail(form);
		// ���[���𑗐M���r�[��
		PersonalMailBean mailInfo = new PersonalMailBean();
		
		// ���P�Drobuchon.jp
		if ("1".equals(form.getMailSuffixFlag())) {
			// �y�C���g���z�ʃ��[���ݒ�˗��ʒm(robuchon.jp)
			setPersonalMailBean(form, mailInfo, ROBUCHON_SETTING_KB);
		// ���Q�Dpizzala.co.jp ������ four-seeds.jp
		} else {
			// �y�C���g���z�ʃ��[���o�^�����ʒm
			setPersonalMailBean(form, mailInfo ,PIZZALA_SETTING_KB);
		}
		// ���[�����M
		sendMail.sendPersonalMail(mailInfo, getLoginUserBean().getUserId());
	}
	
	/**
	 * �u�폜�v�{�^������
	 * 
	 * @param form
	 *               �t�H�[���r�[��
	 * @throws Exception 
	 */	
	public void modifyModeDelete(PersonalMailFormBean form) throws Exception {
		
		// ���[���𑗐M���r�[��
		PersonalMailBean mailInfo = new PersonalMailBean();
		// ���P�Drobuchon.jp
		if ("1".equals(form.getMailSuffixFlag())) {
			// �ʃe�[�u�����X�V
			personalMailDao.updatePersonalMail(form);
			// �y�C���g���z�ʃ��[���폜�ݒ�˗��ʒm(robuchon.jp)
			setPersonalMailBean(form, mailInfo,ROBUCHON_EXPIRED_KB);
		// ���Q�Dpizza-la.co.jp ������ four-seeds.jp
		} else {
			// �ʃe�[�u����_���폜
			personalMailDao.deletePersonalMailInvalid(form);
			// �y�C���g���z�ʃ��[���폜�����ʒm
			setPersonalMailBean(form, mailInfo ,PIZZALA_EXPIRED_KB);
		}
		// ���[������
		sendMail.sendPersonalMail(mailInfo, getLoginUserBean().getUserId());
	}
	
	/**
	 * �u�����ʒm�v�{�^������
	 * 
	 * @param form
	 *               �t�H�[���r�[��
	 * @throws Exception 
	 */	
	public void modifyModeFinish(PersonalMailFormBean form) throws Exception {
		
		// ���[���𑗐M���r�[��
		PersonalMailBean mailInfo = new PersonalMailBean();

		// �ݒ�˗����ˊ����ʒm
		if ("1".equals(form.getMailTaskStatus())) {
			// ��Ǝ҂h�c��ݒ肷��B
			form.setRegistryId(getLoginUserBean().getUserId());
			// �ʃe�[�u�����X�V
			personalMailDao.updatePersonalMail(form);
			// �ݒ�˗����[���𑗐M
			setPersonalMailBean(form, mailInfo, ROBUCHON_FINISH_KB);
		// �X�V�ݒ�˗����ˊ����ʒm
		} else if ("2".equals(form.getMailTaskStatus())) {
			// �ʃe�[�u����_���폜
			personalMailDao.deletePersonalMailInvalid(form);
			// �ݒ�˗����[���𑗐M
			setPersonalMailBean(form, mailInfo, ROBUCHON_EXPIRED_FINISH_KB);
		}
		// ���[������
		sendMail.sendPersonalMail(mailInfo, getLoginUserBean().getUserId());
	}

	/**
	 * CSV���O
	 * 
	 * @param csvName
	 * @return
	 */
	private String getCsvFileName(String csvName) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return csvName + df.format(new Date()) + ".csv";
	}
	
	/**
	 * @param form
	 * �ݒ�˗����[���𑗐M
	 * @param mailKb()
	 * @throws Exception 
	 */
	private void setPersonalMailBean(PersonalMailFormBean form, PersonalMailBean bean, String mailKb) throws Exception {
		
		// ���[���p�����[�^
		Map<String, Object> mailParamMap = new HashMap<String, Object>();
		// ���[����ʂ�ݒ肷��B
		mailParamMap.put("MAIL_FLAG", mailKb);

		// ���[�����M�敪���A���[������ݒ肷��B
		if(PIZZALA_SETTING_KB.equals(mailKb)) {
			// ���P�D�y�C���g���z�ʃ��[���o�^�����ʒm
			pizzalaSetting(bean, form, mailKb, mailParamMap);
		} else if(PIZZALA_EXPIRED_KB.equals(mailKb)) {
			// ���Q�D�y�C���g���z�ʃ��[���폜�����ʒm
			pizzalaExpired(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_SETTING_KB.equals(mailKb)) {
			// ���R�D�y�C���g���z�ʃ��[���ݒ�˗��ʒm(robuchon.jp)
			robuchonSetting(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_FINISH_KB.equals(mailKb)) {
			// ���S�D�y�C���g���z�ʃ��[���o�^�����ʒm(robuchon.jp)
			robuchonFinish(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_EXPIRED_KB.equals(mailKb)) {
			// ���T�D�y�C���g���z�ʃ��[���폜�ݒ�˗��ʒm(robuchon.jp)
			robuchonExpired(bean, form, mailKb, mailParamMap);
		} else if(ROBUCHON_EXPIRED_FINISH_KB.equals(mailKb)) {
			// ���U�D�y�C���g���z�ʃ��[���폜�����ʒm(robuchon.jp)
			robuchonExpiredFinish(bean, form, mailKb, mailParamMap);
		}
		// ���[���e���v���[�g���w�肷��B
		bean.setMailContent(new FreemarkerUtil().getTemplateStr(mailParamMap,"mail_personalMail.ftl"));
		
		// �y�C���g���z�ʃ��[���ݒ�˗��ʒm(robuchon.jp) ��y�C���g���z�ʃ��[���폜�ݒ�˗��ʒm(robuchon.jp)�ꍇ
		if (ROBUCHON_SETTING_KB.equals(mailKb) || ROBUCHON_EXPIRED_KB.equals(mailKb)) {
			// CSV�t�@�C�����e���X�g
			writeCsvFileList(bean);
		}
		// �L������A�b�b���[�����X�g�`�F�b�N
		checkValidToMail(bean);
	}
	
	/**
	 * �y�C���g���z�ʃ��[���o�^�����ʒm
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * */ 
	private void pizzalaSetting(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// ���惆�[�UID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.pizzala").split(",");
		// CC���[�UID
		String[] ccUserId = { form.getRequestId() };
		// ���� + �o�^���ꂽ���[���A�h���X�̃h���C��
		bean.setMailSubject(FsPropertyUtil.getStringProperty("personal.mail.title.pizzala.setting"));
		// �Y�t�t���O��ݒ�
		bean.setAttachFlag("0");
		// �����敪��ݒ�i1�F�u�o�^�v�j
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.set"));
		// ���[�����ʂ�ݒ肷��B
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * �y�C���g���z�ʃ��[���폜�����ʒm
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * */ 
	private void pizzalaExpired(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		// ���惆�[�UID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.pizzala").split(",");
		// CC���[�UID
		String[] ccUserId = { form.getRequestId() };
		// ����
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.pizzala.expired")));
		// �Y�t�t���O��ݒ�
		bean.setAttachFlag("0");
		// �����敪��ݒ�i3�F�u�폜�v�j
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.delete"));
		// ���[�����ʂ�ݒ肷��B
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * �y�C���g���z�ʃ��[���ݒ�˗��ʒm(robuchon.jp)
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * */ 
	private void robuchonSetting(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		// ���惆�[�U�[ID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.robuchon.setting").split(",");
		// CC���[�����[�U�[ID�z��
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.setting").split(",");
		// ����
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.setting")));
		// ���������� CSV�p����������
		// �p�X���[�h
		bean.setPassword(form.getPassword());
		// �v���t�@�C���敪
		bean.setProfileDiv(form.getProfileDiv());
		// ���[���h���C���t���O
		bean.setMailSuffixFlag(form.getMailSuffixFlag());
		// ���[���A�h���X
		bean.setMailAddress(form.getMailAddress());
		// ���[���F���[�U��
		bean.setMailId(form.getMailId());
		// ���p�Ґ�
		bean.setUserSei(form.getUserSei());
		// ���p�Җ�
		bean.setUserMei(form.getUserMei());
		// �Y�t�t���O��ݒ�
		bean.setAttachFlag("1");
		// ���������� CSV�p����������
		// �o�^�\�c���ݒ�
		setValidMailCnt(bean);
		// �o�^�\�c���F
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// �����敪��ݒ�i1�F�u�o�^�v�j
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.set"));
		// ���[�����ʂ�ݒ肷��B
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}

	
	/**
	 * �y�C���g���z�ʃ��[���o�^�����ʒm(robuchon.jp)
	 * 
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * */ 
	private void robuchonFinish(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// ���惆�[�U�[ID
		String[] toUserId = { form.getRequestId() };
		// CC���[�����[�U�[ID�z��
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.expired").split(",");
		// ����
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.finish")));
		// �Y�t�t���O��ݒ�
		bean.setAttachFlag("0");
		// �o�^�\�c���ݒ�
		setValidMailCnt(bean);
		// �o�^�\�c���F
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// �����敪��ݒ�i1�F�u�o�^�v�j
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.set"));
		// ���[�����ʂ�ݒ肷��B
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * �y�C���g���z�ʃ��[���폜�ݒ�˗��ʒm(robuchon.jp)
	 * 
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * */ 
	private void robuchonExpired(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// ���惆�[�U�[ID
		String[] toUserId = FsPropertyUtil.getStringProperty("personal.mail.to.address.robuchon.expired").split(",");
		// CC���[�����[�U�[ID�z��
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.expired").split(",");
		// ����
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.expired")));
		// �o�^�\�c���ݒ�
		setValidMailCnt(bean);
		// ���������� CSV�p����������
		// ���[���h���C���t���O
		bean.setMailSuffixFlag(form.getMailSuffixFlag());
		// ���[���A�h���X
		bean.setMailAddress(form.getMailAddress());
		// ���[���F���[�U��
		bean.setMailId(form.getMailId());
		// ���p�Ґ�
		bean.setUserSei(form.getUserSei());
		// ���p�Җ�
		bean.setUserMei(form.getUserMei());
		// �Y�t�t���O��ݒ�
		bean.setAttachFlag("1");
		// ���������� CSV�p����������
		// �o�^�\�c���F
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// �����敪��ݒ�i3�F�u�폜�v�j
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.delete"));
		// ���[�����ʂ�ݒ肷��B
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	
	/**
	 * �y�C���g���z�ʃ��[���폜�����ʒm(robuchon.jp)
	 * 
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * */ 
	private void robuchonExpiredFinish(PersonalMailBean bean, PersonalMailFormBean form, String mailKb,
			Map<String, Object> mailParamMap) throws Exception {
		
		// TO���[�����[�U�[ID�z��
		String[] toUserId = { form.getRequestId() };
		// CC���[�����[�U�[ID�z��
		String[] ccUserId = FsPropertyUtil.getStringProperty("personal.mail.cc.address.robuchon.expired").split(",");
		// ����
		bean.setMailSubject(new String(FsPropertyUtil.getStringProperty("personal.mail.title.robuchon.expired.finish")));
		// �o�^�\�c���ݒ�
		setValidMailCnt(bean);
		// �o�^�\�c���F
		mailParamMap.put("VALID_MAIL_CNT", bean.getValidMailCnt());
		// �Y�t�t���O��ݒ�
		bean.setAttachFlag("0");
		// �����敪��ݒ�i3�F�u�폜�v�j
		bean.setDealDivisionFlag(FsPropertyUtil.getStringProperty("deal.division.flag.delete"));
		// ���[�����ʂ�ݒ肷��B
		setExpiredCommonContent(bean, form, toUserId, ccUserId, mailKb, mailParamMap);
	}
	
	/**
	 * CSV�t�@�C�����e���X�g
	 * @param personalMailBean
	 * @throws Exception
	 */
	private void writeCsvFileList(PersonalMailBean bean) throws Exception {
		List<String> csvList = new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		// �����敪�t���O�i�V�K�F1�@�폜�F3�j
		String dealDivision = "";
		if ("1".equals(StringUtil.nullToBlank(bean.getDealDivisionFlag()))) {
			dealDivision = "CREATE";
		} else if ("3".equals(StringUtil.nullToBlank(bean.getDealDivisionFlag()))) {
			dealDivision = "DELETE";
		}
		list.add(dealDivision);
		
		// ���[���敪�\����
		String fieldName = FsPropertyUtil.getStringProperty("mail.suffix.fieldName");
		String div = commonService.getDispDivisionName(fieldName, bean.getMailSuffixFlag());
		list.add(div);
		
		// �����e��ʂ̃��[�U��
		list.add(StringUtil.nullToBlank(bean.getUserSei()));
		// �����e��ʂ̃��[�U��
		list.add(StringUtil.nullToBlank(bean.getUserMei()));
		// �����e��ʂ̃��[�U��+��
		list.add(StringUtil.nullToBlank(bean.getUserSei()) + StringUtil.nullToBlank(bean.getUserMei()));
		
		// �A�J�E���gID�i��ʂ̃��[�U�[���j
		list.add(StringUtil.nullToBlank(bean.getMailId()));
				
		// �����e��ʂ̃p�X���[�h�A�o�^���̂�
		if (FsPropertyUtil.getStringProperty("deal.division.flag.set").equals(bean.getDealDivisionFlag()) && bean.getPassword() != null) {
			list.add(bean.getPassword());
		} else {
			list.add("");
		}
		// �p�X���[�h�ύX�t���O�u0�v�Œ�
		list.add("FALSE");
		// ��E�A�ȗ�
		list.add("");
		// ���l
		list.add("�˗����F�@" + DateUtil.getNowDate("yyyy/MM/dd"));
		
		// �v���t�@�C�����A�o�^���̂�
		if (bean.getProfileDiv() != null) {
			String field = FsPropertyUtil.getStringProperty("profile.div.fieldName");
			String profileName = commonService.getDivisionName(field, bean.getProfileDiv());
			list.add(profileName);
		} else {
			list.add("");
		}
		
		list.add("TRUE");
		
		// �A�J�E���g�@�{�@@�@�{�@�h���C��
		if (bean.getMailId() != null && div != null && !"".equals(div)) {
			list.add(bean.getMailId() + "@" + div);
		} else {
			list.add("");
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append("\"");
			String content = (String) list.get(i);
			sb.append(content);
			if (i != list.size()-1) {
            	sb.append("\",");
            } else {
            	sb.append("\"");
            }
		}
		
		// CSVTitle�ǉ�
        String csvTitle = FsPropertyUtil.getStringProperty("personal.mail.csvTitle");
        csvList.add(csvTitle);

		// CSV���e���X�g�P����ǉ�
		csvList.add(sb.toString());

		// robuchon.jp�ꍇ�ACSV�t�@�C�����X�g
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		// CSV�p�X
		String path = FsPropertyUtil.getStringProperty("csv.file.path");
		// �t�@�C�����O
		bean.setFilePath(path);
		// CSV�t�@�C�����O
		String fileName = FsPropertyUtil.getStringProperty("csv.file.name") + now;
		// �t�@�C�����O
		bean.setFileName(fileName);
		// CSV���e���X�g
		bean.setCsvInfoList(csvList);
	}

	/**
	 * �L������A�b�b���[�����X�g�`�F�b�N
	 * @param bean
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void checkValidToMail(PersonalMailBean bean) throws Exception {
		List<String> toList = bean.getToMailList();
		List<String> newList = new ArrayList<String>();
		String toMail = new String(FsPropertyUtil.getStringProperty("personal.mail.to.address"));
		if (toList == null || toList.size() == 0) {
			newList.add(toMail);
			bean.setToMailList(newList);
		}
	}
	
	/**
	 * ���[������
	 * 
	 * @param bean
	 *                 �l���[���r�[��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param toMailList
	 *                 ���惆�[�U�[�A�h���X
	 * @param toNameList
	 *                 ���惆�[�U�[����
	 * @param toUserId
	 *                 ���惆�[�U�[ID
	 * @param ccUserId
	 *                 CC���[�����[�U�[ID�z��
	 * @param mailKb
	 *                 �P�[�����
	 * @param mailParamMap
	 *                 ���[�����e�p�����[�^
	 * @return 
	 *               �Ȃ�
	 * @throws Exception 
	 * 
	 * */
	private void setExpiredCommonContent(PersonalMailBean bean, PersonalMailFormBean form, String[] toUserId,
			String[] ccUserId, String mailKb,Map<String, Object> mailParamMap) throws Exception {
		
		// ���惆�[�U�[�A�h���X
		List<String> toMailList = new ArrayList<String>();
		// ���惆�[�U�[����
		List<String> toNameList = new ArrayList<String>();
		// CC���[�U�[ID
		List<String> ccIdList = new ArrayList<String>();
		// CC���[�U�[���[���A�h���X
		List<String> ccMailList = new ArrayList<String>();
		// ���M�Җ��O(�ݒ�˗��҂̖���)
		String fromUserName;
		
		// ���M�҃��[��
		bean.setFromMail(new String(FsPropertyUtil.getStringProperty("personal.mail.from.address")));
				
		// ���惆�[�U�[ID���A���M���[���Ƒ��M���[�U�[���̂�ݒ肷��
		setMailToName(toUserId, toMailList, toNameList, bean);
		
		// CC���[�U�[ID���ACC���[���A�h����ݒ肷��
		setMailcc(ccIdList, ccUserId, ccMailList, form, bean, mailKb);
		
		// �y�C���g���z�ʃ��[���o�^�����ʒm(robuchon.jp) OR �y�C���g���z�ʃ��[���폜�����ʒm(robuchon.jp)�̏ꍇ
		if(mailKb.equals(ROBUCHON_FINISH_KB) || mailKb.equals(ROBUCHON_EXPIRED_FINISH_KB)) {
			// ���M�Җ��O(�ݒ��Ǝ҂̖���)
			fromUserName = personalMailDao.getFromUserName(form.getRegistryId());
		} else{
			// ���M�Җ��O(�ݒ�˗��҂̖���)
			fromUserName = personalMailDao.getFromUserName(form.getRequestId());
		}
		// ���M�Җ��O��ݒ肷��B
		bean.setFromName(fromUserName);
		
		// ����
		StringBuffer toUserNameBuff = new StringBuffer();
		if (toNameList != null && toNameList.size() > 0) {
			for (int i = 0; i < toNameList.size(); i++) {
				String toUserName = (String) toNameList.get(i);
				toUserNameBuff.append(toUserName);
				// ����̖��̒��u�A�v���Ԋu
				if (i != toNameList.size()-1) {
					toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("personal.mail.common.content.head1")));
					toUserNameBuff.append("�A");
				} else {
					toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("personal.mail.common.content.head1")));
				}
			}
		} else {
			// ����s�݂̏ꍇ�́A�h������s�݁��h�Ƃ���
			toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("personal.mail.no.mailto.username")));
		}
		// ����
		mailParamMap.put("MAIL_TO_NAME", toUserNameBuff.toString());
		// ���M�҂̖���
		if (!StringUtil.isEmpty(bean.getFromName())) {
			mailParamMap.put("MAIL_FROM_NAME", bean.getFromName());
		// ���M�Җ��O���݂��Ȃ��̏ꍇ�A�u�C���g���ێ�S���v�𗘗p����
		} else {
			mailParamMap.put("MAIL_FROM_NAME", FsPropertyUtil.getStringProperty("personal.mail.from.name"));
		}
		// ID�F
		mailParamMap.put("MAIL_USER_ID", StringUtil.nullToBlank(form.getUserId()));
		// ���́F		
		mailParamMap.put("MAIL_USER_NAME", StringUtil.nullToBlank(form.getUserSei()) + StringUtil.nullToBlank(form.getUserMei()));
		// �o�^�ҕ��ށF
		String fieldName = FsPropertyUtil.getStringProperty("mail.userDiv.fieldName");
		// ID��薼�̂��擾
		String mailUserDivName = commonService.getDivisionName(fieldName, form.getMailUserDivision());
		// �擾�̖���
		mailParamMap.put("MAIL_USER_DIV_NAME", StringUtil.nullToBlank(mailUserDivName));
		// ���[���A�h���X�F
		mailParamMap.put("MAIL_ADDRESS", StringUtil.nullToBlank(form.getMailAddress()));
	}
	
	/**
	 * ���惆�[�U�[ID���A���M���[���Ƒ��M���[�U�[���̂�ݒ肷��
	 * 
	 * @param bean
	 *                 �l���[���r�[��
	 * @param toMailList
	 *                 ���惆�[�U�[�A�h���X
	 * @param toNameList
	 *                 ���惆�[�U�[����
	 * @param toUserId
	 *                 ���惆�[�U�[ID
	 * @return 
	 *               �Ȃ�
	 * 
	 * */ 
	private void setMailToName(String[] toUserId,List<String> toMailList,List<String> toNameList,PersonalMailBean bean) {
		// ���惆�[�U�[ID������ꍇ
		if (toUserId != null && toUserId.length > 0) {
			for (int i = 0; i < toUserId.length; i ++) {
				// ���[�U�̃��[���ƃ��[�U���O���擾
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(toUserId[i]);
				// ���[�U�̃��[���ƃ��[�U���O���擾�����ꍇ
				if (userInfoBean != null) {
					// ���M���[��
					toMailList.add(userInfoBean.getuMail());
					// ���M���[�U�[����
					toNameList.add(userInfoBean.getuName());
				}
			}
		}
		// ���惆�[�U�[���[�����X�g
		bean.setToMailList(toMailList);
		// ���惆�[�U�[���̃��X�g
		bean.setToNameList(toNameList);
		
	}
	
	/**
	 * CC���[�U�[ID���ACC���[���A�h����ݒ肷��
	 * 
	 * @param ccIdList
	 *                 CC���[�U�[ID
	 * @param ccMailList
	 *                 CC���[�U�[���[���A�h���X
	 * @param ccUserId
	 *                 CC���[�����[�U�[ID�z��
	 * @param form
	 *                 �l���[���t�H�[��
	 * @param bean
	 *                 �l���[���r�[��
	 * @param mailKb
	 *                 ���[�����
	 * @return 
	 *               �Ȃ�
	 * 
	 * */ 
	private void setMailcc(List<String> ccIdList,String[] ccUserId,List<String> ccMailList,
			PersonalMailFormBean form,PersonalMailBean bean,String mailKb) {
		// CC���[�����[�U�[ID������ꍇ�B
		if (ccUserId != null && ccUserId.length > 0) {
			for (int i = 0; i < ccUserId.length; i ++) {
				// ���[�U�̃��[���ƃ��[�U���O���擾
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(ccUserId[i]);
				// ���[�U�̃��[���ƃ��[�U���O���擾�����ꍇ
				if (userInfoBean != null) {
					// CC���[���A�h���X��ǉ�����B
					ccMailList.add(userInfoBean.getuMail());
					// CC���[�U�[ID��ǉ�����B
					ccIdList.add(ccUserId[i]);
				}
			}
		}
		// �˗��҂ƍ�Ǝ҂�CCMail�ɒǉ��B
		if (ROBUCHON_SETTING_KB.equals(mailKb)) {
			// ���o�^�˗��ʒm(robuchon.jp)
			// �˗��҂̃��[�����擾
			if (!StringUtil.isEmpty(form.getRequestId())) {
				// ���[�U�̃��[���ƃ��[�U���O���擾
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(form.getRequestId());
				// ���[�U�̃��[���ƃ��[�U���O���擾�ł��銎CC���[�U�[ID�̍�Ǝ҂h�c������܂���ꍇ
				if (userInfoBean != null && !ccIdList.contains(form.getRegistryId())) {
					// CC���[�U�[���[���A�h���X��ǉ�����B
					ccMailList.add(userInfoBean.getuMail());
				}
			}
		} else if(ROBUCHON_EXPIRED_KB.equals(mailKb)) {
			// ���폜�ݒ�˗��ʒm(robuchon.jp)
			// �˗��҂̃��[�����擾
			if (!StringUtil.isEmpty(form.getRequestId())) {
				// ���[�U�̃��[���ƃ��[�U���O���擾
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(form.getRequestId());
				// ���[�U�̃��[���ƃ��[�U���O���擾�ł��銎CC���[�U�[ID�̍�Ǝ҂h�c������܂���ꍇ
				if (userInfoBean != null && !ccIdList.contains(form.getRegistryId())) {
					// CC���[�U�[���[���A�h���X��ǉ�����B
					ccMailList.add(userInfoBean.getuMail());
				}
			}
		} else if(ROBUCHON_FINISH_KB.equals(mailKb) || ROBUCHON_EXPIRED_FINISH_KB.equals(mailKb)) {
			// ���o�^�����ʒm(robuchon.jp) OR �폜�����ʒm(robuchon.jp)
			// �˗��҂̃��[�����擾
			if (!StringUtil.isEmpty(form.getRegistryId())) {
				// ���[�U�̃��[���ƃ��[�U���O���擾
				PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(form.getRegistryId());
				// ���[�U�̃��[���ƃ��[�U���O���擾�ł��銎CC���[�U�[ID�̍�Ǝ҂h�c������܂���ꍇ
				if (userInfoBean != null && !ccIdList.contains(form.getRegistryId())) {
					// CC���[�U�[���[���A�h���X��ǉ�����B
					ccMailList.add(userInfoBean.getuMail());
				}
			}
		}
		
		// CC���[���A�h���X��ǉ�����B
		bean.setCcMailList(ccMailList);
	}
	
	/**
	 * �o�^�\�c���ݒ�
	 * @param bean ���[���r�[��
	 * 
	 * */
	private void setValidMailCnt (PersonalMailBean bean) {
		// �S����
		int totalMailCnt = 0;
		List<LabelValueBean> labelList = commonService.getDivisionMaster(FIELD_NAME_MAX_MAIL_NUM);
		if (labelList.size()!=0) {
			String cnt = labelList.get(0).getValue();
			if (NumberUtil.isNumber(cnt)) {
				totalMailCnt = Integer.parseInt(cnt);
			}
		}
		// �o�^�ό���
		int mailCnt = personalMailDao.getMailCnt();
		// �o�^�\�c�����擾
		int validMailCnt = totalMailCnt - mailCnt;
		// �o�^�\�c���ݒ�
		bean.setValidMailCnt(validMailCnt);
	}
	
	/**
	 * ���X�g�\�[�g
	 * 
	 * @param asc_desc
	 *           �\�[�g�~���E����
	 * @param sort_key
	 *           �\�[�g����
	 *
	 * @return �Ȃ�
	 */
	public void sort (List<PersonalMailBean> oldList,String asc_desc,String sort_key) {
		// �����ꍇ�A�\�[�g���Ȃ�
		if (StringUtil.isEmpty(sort_key)) {
			return;
		}
		if ("asc".equals(asc_desc)) {
			// ����
			if ("userId".equals(sort_key)) {
				// ID����
				Collections.sort(oldList,userIdAsc);
			} else if("userName".equals(sort_key)) {
				// ���p�Җ�����
				Collections.sort(oldList,userNameAsc);
			} else if("mailAddress".equals(sort_key)) {
				// ���[���A�h���[�X����
				Collections.sort(oldList,mailAddressAsc);
			} else if("mailSet".equals(sort_key)) {
				// �J�n������
				Collections.sort(oldList,mailSetAsc);
			} else if("mailEx".equals(sort_key)) {
				// �I��������
				Collections.sort(oldList,mailExAsc);
			} else if("departmentName".equals(sort_key)) {
				// ��������
				Collections.sort(oldList,departmentNameAsc);
			}
		} else {
			// �~��
			if ("userId".equals(sort_key)) {
				// ID�~��
				Collections.sort(oldList,userIdDesc);
			} else if("userName".equals(sort_key)) {
				// ���p�Җ��~��
				Collections.sort(oldList,userNameDesc);
			} else if("mailAddress".equals(sort_key)) {
				// ���[���A�h���[�X�~��
				Collections.sort(oldList,mailAddressDesc);
			} else if("mailSet".equals(sort_key)) {
				// �J�n���~��
				Collections.sort(oldList,mailSetDesc);
			} else if("mailEx".equals(sort_key)) {
				// �I�����~��
				Collections.sort(oldList,mailExDesc);
			} else if("departmentName".equals(sort_key)) {
				// �����~��
				Collections.sort(oldList,departmentNameDesc);
			}
		}
	}
	
	/** ID���� */
	Comparator<PersonalMailBean> userIdAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getUserId().compareTo(arg0.getUserId());
		}
	};
	/** ���p�Җ����� */
	Comparator<PersonalMailBean> userNameAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return (StringUtil.nullToBlank(arg1.getUserSei()) + StringUtil.nullToBlank(arg1.getUserMei()))
					.compareTo(StringUtil.nullToBlank(arg0.getUserSei()) + StringUtil.nullToBlank(arg0.getUserMei()));
		}
	};
	/** ���[���A�h���[�X���� */
	Comparator<PersonalMailBean> mailAddressAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getMailAddress().compareTo(arg0.getMailAddress());
		}
	};
	/** �J�n������ */
	Comparator<PersonalMailBean> mailSetAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getMailSet().compareTo(arg0.getMailSet());
		}
	};
	/** �I�������� */
	Comparator<PersonalMailBean> mailExAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getMailEx().compareTo(arg0.getMailEx());
		}
	};
	/** �������� */
	Comparator<PersonalMailBean> departmentNameAsc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg1.getDepartmentName().compareTo(arg0.getDepartmentName());
		}
	};
	
	/** ID�~�� */
	Comparator<PersonalMailBean> userIdDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getUserId().compareTo(arg1.getUserId());
		}
	};
	/** ���p�Җ��~�� */
	Comparator<PersonalMailBean> userNameDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return (StringUtil.nullToBlank(arg0.getUserSei()) + StringUtil.nullToBlank(arg0.getUserMei()))
					.compareTo(StringUtil.nullToBlank(arg1.getUserSei()) + StringUtil.nullToBlank(arg1.getUserMei()));
		}
	};
	/** ���[���A�h���[�X�~�� */
	Comparator<PersonalMailBean> mailAddressDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getMailAddress().compareTo(arg1.getMailAddress());
		}
	};
	/** �J�n���~�� */
	Comparator<PersonalMailBean> mailSetDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getMailSet().compareTo(arg1.getMailSet());
		}
	};
	/** �I�����~�� */
	Comparator<PersonalMailBean> mailExDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getMailEx().compareTo(arg1.getMailEx());
		}
	};
	/** �����~�� */
	Comparator<PersonalMailBean> departmentNameDesc = new Comparator<PersonalMailBean>()  {
		public int compare(PersonalMailBean arg1, PersonalMailBean arg0) {
			return arg0.getDepartmentName().compareTo(arg1.getDepartmentName());
		}
	};
}