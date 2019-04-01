/**
 * File Name	: ProfieBean.java
 * Created Date	: 2016/01/07
 * COPYRIGHT(c)	: NTS
 */

package jp.co.fourseeds.fsnet.beans.profile;

import java.io.File;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

public class ProfileBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ���[�UID */
	private String userId;

	/** �p�X���[�h */
	private String password;
	
	/** �V�p�X���[�h */
	private String newPassword;
	
	/** �\���p�X���[�h */
	private String showPassword;

	/** ������  */
	private String kanziSei;

	/** ������  */
	private String kanziMei;

	/** ���J�i */
	private String kanaSei;
	
	/** ���J�i  */
	private String kanaMei;

	/** ��������  */
	private String oldKanziSei;

	/** �����J�i */
	private String oldKanaSei;
	
	/** �ЗL�g�єԍ� */
	private String mobileCorp;
	
	/** ���� */
	private String extention;
	
	/**���[���A�h���X*/
	private String mailAddress;
	
	/**���[���A�h���X*/
	private String passwordSendingDivision;

	/** �摜�t���O   0. �f�t�H���g�@1,�@�I���摜*/
	private String imgFlag;

	/** �o�^�摜�� */
	private String personImgName;
	
	/** �o�^�摜���i�߂�p�j */
	private String personImgOldName;
	
	/** �o�^�摜*/
	private File personImg;
	
	/** �o�^�摜�� */
	private String personImgFileName;

	/** �o�^�摜�p�X*/
	private String personImgPath;
	
	/** �o�^�摜�A�h���X*/
	private String personImgAdd;
	
	/** �o�^�摜�f�[�^ */
	private byte[] personImgDat;
	
	/** �o�^�摜�f�[�^ size*/
	private String personImgDatSize;
	

	/** �C���[�W�f�[�^ */
	private byte[] imgImgDat;
	
	/** �C���[�W�f�[�^ size*/
	private String imgImgDatSize;
	
	/** �d�v�Ȃ��m�点�\���J�n�� */
	private String necessityDisplayStartDate;
		
	/** �\���O���[�v */
	private String displayGroup;

	/** �g�D�敪 */
	private String original;
	
	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId �ݒ肷�� userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password �ݒ肷�� password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword �ݒ肷�� newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * @return showPassword
	 */
	public String getShowPassword() {
		return showPassword;
	}

	/**
	 * @param showPassword �ݒ肷�� showPassword
	 */
	public void setShowPassword(String showPassword) {
		this.showPassword = showPassword;
	}
	
	/**
	 * @return kanziSei
	 */
	public String getKanziSei() {
		return kanziSei;
	}

	/**
	 * @param kanziSei �ݒ肷�� kanziSei
	 */
	public void setKanziSei(String kanziSei) {
		this.kanziSei = kanziSei;
	}

	/**
	 * @return kanziMei
	 */
	public String getKanziMei() {
		return kanziMei;
	}

	/**
	 * @param kanziMei �ݒ肷�� kanziMei
	 */
	public void setKanziMei(String kanziMei) {
		this.kanziMei = kanziMei;
	}

	/**
	 * @return kanaSei
	 */
	public String getKanaSei() {
		return kanaSei;
	}

	/**
	 * @param kanaSei �ݒ肷�� kanaSei
	 */
	public void setKanaSei(String kanaSei) {
		this.kanaSei = kanaSei;
	}

	/**
	 * @return kanaMei
	 */
	public String getKanaMei() {
		return kanaMei;
	}

	/**
	 * @param kanaMei �ݒ肷�� kanaMei
	 */
	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	/**
	 * @return OldKanziSei
	 */
	public String getOldKanziSei() {
		return oldKanziSei;
	}

	/**
	 * @param OldKanziSei �ݒ肷�� OldKanziSei
	 */
	public void setOldKanziSei(String oldKanziSei) {
		this.oldKanziSei = oldKanziSei;
	}

	/**
	 * @return oldKanaSei
	 */
	public String getOldKanaSei() {
		return oldKanaSei;
	}

	/**
	 * @param oldKanaSei �ݒ肷�� oldKanaSei
	 */
	public void setOldKanaSei(String oldKanaSei) {
		this.oldKanaSei = oldKanaSei;
	}

	/**
	 * @return mobileCorp
	 */
	public String getMobileCorp() {
		return mobileCorp;
	}

	/**
	 * @param mobileCorp �ݒ肷�� mobileCorp
	 */
	public void setMobileCorp(String mobileCorp) {
		this.mobileCorp = mobileCorp;
	}

	/**
	 * @return extention
	 */
	public String getExtention() {
		return extention;
	}

	/**
	 * @param extention �ݒ肷�� extention
	 */
	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPasswordSendingDivision() {
		return passwordSendingDivision;
	}

	public void setPasswordSendingDivision(String passwordSendingDivision) {
		this.passwordSendingDivision = passwordSendingDivision;
	}

	/**
	 * @return imgFlag
	 */
	public String getImgFlag() {
		return imgFlag;
	}

	/**
	 * @param imgFlag �ݒ肷�� imgFlag
	 */
	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}

	/**
	 * @return personImgName
	 */
	public String getPersonImgName() {
		return personImgName;
	}

	/**
	 * @param personImgName �ݒ肷�� personImgName
	 */
	public void setPersonImgName(String personImgName) {
		this.personImgName = personImgName;
	}


	/**
	 * @return personImg
	 */
	public File getPersonImg() {
		return personImg;
	}

	/**
	 * @param personImg �ݒ肷�� personImg
	 */
	public void setPersonImg(File personImg) {
		this.personImg = personImg;
	}
	
	/**
	 * @return personImgFileName
	 */
	public String getPersonImgFileName() {
		return personImgFileName;
	}
	
	/**
	 * @param personImgFileName �ݒ肷�� personImgFileName
	 */
	public void setPersonImgFileName(String personImgFileName) {
		this.personImgFileName = personImgFileName;
	}

	/**
	 * @return personImgPath
	 */
	public String getPersonImgPath() {
		return personImgPath;
	}

	/**
	 * @param personImgPath �ݒ肷�� personImgPath
	 */
	public void setPersonImgPath(String personImgPath) {
		this.personImgPath = personImgPath;
	}

	/**
	 * @return personImgAdd
	 */
	public String getPersonImgAdd() {
		return personImgAdd;
	}

	/**
	 * @param personImgAdd �ݒ肷�� personImgAdd
	 */
	public void setPersonImgAdd(String personImgAdd) {
		this.personImgAdd = personImgAdd;
	}
	
	/**
	 * @return personImgDat
	 */
	public byte[] getPersonImgDat() {
		return personImgDat;
	}

	/**
	 * @param personImgDat �ݒ肷�� personImgDat
	 */
	public void setPersonImgDat(byte[] personImgDat) {
		this.personImgDat = personImgDat;
	}

	/**
	 * @return imgImgDat
	 */
	public byte[] getImgImgDat() {
		return imgImgDat;
	}

	/**
	 * @param imgImgDat �ݒ肷�� imgImgDat
	 */
	public void setImgImgDat(byte[] imgImgDat) {
		this.imgImgDat = imgImgDat;
	}

	/**
	 * @return personImgDatSize
	 */
	public String getPersonImgDatSize() {
		return personImgDatSize;
	}

	/**
	 * @param personImgDatSize �ݒ肷�� personImgDatSize
	 */
	public void setPersonImgDatSize(String personImgDatSize) {
		this.personImgDatSize = personImgDatSize;
	}

	/**
	 * @return imgImgDatSize
	 */
	public String getImgImgDatSize() {
		return imgImgDatSize;
	}

	/**
	 * @param imgImgDatSize �ݒ肷�� imgImgDatSize
	 */
	public void setImgImgDatSize(String imgImgDatSize) {
		this.imgImgDatSize = imgImgDatSize;
	}
	/**
	 * @return necessityDisplayStartDate
	 */
	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	/**
	 * @param necessityDisplayStartDate �ݒ肷�� necessityDisplayStartDate
	 */
	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	/**
	 * @return displayGroup
	 */
	public String getDisplayGroup() {
		return displayGroup;
	}

	/**
	 * @param displayGroup �ݒ肷�� displayGroup
	 */
	public void setDisplayGroup(String displayGroup) {
		this.displayGroup = displayGroup;
	}

	public String getPersonImgOldName() {
		return personImgOldName;
	}

	public void setPersonImgOldName(String personImgOldName) {
		this.personImgOldName = personImgOldName;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}



}
