package jp.co.fourseeds.fsnet.service;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.fourseeds.fsnet.beans.profile.ProfileBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.dao.ProfileDao;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * �v���t�B�[���T�[�r�X�����N���X
 * 
 * File Name: ProfileService.java 
 * Created: 2016/01/07
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/07		    NTS        	       �쐬
 * 1.1		2017/12/11		    NTS        	       �������C��
 *
 **/
@Component
public class ProfileService extends BaseBusinessService{

	@Autowired
	private ProfileDao profileDao;

	/**
	 * �v���t�B�[�������擾
	 * @param profileBean�@�v���t�B�[�����
	 * @return�@��������
	 * @throws ParseException 
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfile(ProfileBean profileBean) throws ParseException {
		//�@DB���猟�����ʂ��擾
		ProfileBean profile = profileDao.getProfile(profileBean);
		
		//���t�i���� yyyy/MM/DD �ŕύX�i���F�@yyyy-MM-DD HH:mm:ss:S�j
		String NecessityDisplayStartDate = profile.getNecessityDisplayStartDate();
		if(!"".equals(NecessityDisplayStartDate.trim())){
			// Convert input string into a date
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
				
			if("".equals(NecessityDisplayStartDate)){
				Date nowDate = new Date();
				NecessityDisplayStartDate = inputFormat.format(nowDate);
			}
			
			// Format date into output format
			SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd");
			String outputString = outputFormat.format(inputFormat.parse(NecessityDisplayStartDate));
			profile.setNecessityDisplayStartDate(outputString);
			
		}
		
		return profile;
	}
	/**
	 * �v���t�B�[�������擾
	 * ��摜�擾����
	 * @param profileBean�@�v���t�B�[�����
	 * @return�@��������
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfileByUserId(ProfileBean profileBean) throws ParseException {
		
		return profileDao.getProfileByUserId(profileBean);
	}
	
	public void updateProfile(ProfileBean profilebean) throws Exception {
		//�������ʐ^�̃t�@�C���A������
		// �ʐ^�̃t�@�C���A�ݒ�̏ꍇ
		if (!StringUtil.isBlank(profilebean.getPersonImgPath())) {
			// �Ј��ʐ^�A�h���X�ƌl�o�^�摜��ݒ�
			this.setPhotoInfo(profilebean);
			
			// ���[�U�C���[�W���݂��Ȃ��ꍇ
			if (profileDao.getPersonIMGCount(profilebean) == 0) {
				// ���[�U�C���[�W����o�^
				profileDao.insertPersonIMG(profilebean);
			} else {
				// ���[�U�C���[�W�����X�V
				profileDao.updateUserImgMasterProfile(profilebean);
			}
		}
		else{
			if("0".equals(profilebean.getOriginal())){
				// ���[�U�C���[�W�����X�V imgflag only
				profileDao.updateUserImgMasterProfileimgflgOnly(profilebean);
			}
		}
		profileDao.updateProfile(profilebean);
	}
	
	/**
	 * �Ј��ʐ^�A�h���X�ƌl�o�^�摜��ݒ�
	 * @param profilebean
	 * @throws Exception 
	 */
	public void setPhotoInfo(ProfileBean profilebean) throws Exception {
		// �Ј��ʐ^�A�h���X��ݒ�
		String photoUrlPath = FsPropertyUtil.getStringProperty("photo.Url.path");
		String photoAdd = photoUrlPath + "/" + profilebean.getPersonImgName();
		profilebean.setPersonImgAdd(photoAdd);
		
		// �l�o�^�摜��ݒ�
		File photo = new File(profilebean.getPersonImgPath());
		profilebean.setPersonImgDat(FileUtil.fileToByte(photo));
	}
	
}