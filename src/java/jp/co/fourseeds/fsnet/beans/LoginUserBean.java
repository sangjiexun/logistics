package jp.co.fourseeds.fsnet.beans;

import java.util.Date;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;

/**
 * ���O�C�����[�U�[���̊i�[�N���X
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 �V�K�쐬�i���v���W�F�N�g��LoginUser.java����ړ��j
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 �������C��
 * 
 */
public class LoginUserBean extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4388234923404188955L;
	
	/** �Í��L�[ */
	private String accessKey = null;
	
	/** �A�N�Z�X���� */
	private String accessTime = null;

	/** ���[�UID */
	private String userId = null;

	/** �p�X���[�h */
	private String password = null;

	/** �p�X���[�h�L���I���� */
	private Date pwExpire = null;

	/** ������ */
	private String kanjiSei = null;

	/** ������ */
	private String kanjiMei = null;

	/** ���J�i */
	private String kanaSei = null;

	/** ���J�i */
	private String kanaMei = null;

	/** ���� */
	private String sex = null;

	/** �g�� */
	private String mobile = null;

	/** ��EID */
	private String positionId = null;

	/** ����ID */
	private String departmentId = null;

	/** �X��ID */
	private String storeId = null;

	/** ���� */
	private String naisen = null;

	/** �V�X�e�����p�敪 */
	private String role = null;

	/** �Ј��ʐ^�{���敪 */
	private String viewPhotoFlag = null;

	/** �Ј��ʐ^�A�h���X */
	private String photoAddress = null;

	/** ���O�V�X�e������ */
	private Date currentDate = null;

	private String pageId = null;

	private Date pwSetDate = null;
	
	/** the user belongs group */
	private List topGroupList = null;
	
	/** ��E����  */
	private String positionName = null;
	
	private String newConfirmFlag = null;
	
	/** �d�v�Ȃ��m�点 �\���J�n��*/
	private String necessityDisplayStartDate = null;
	
	/**�]���惁�[���A�h���X*/
	private String trmailAddress = null;
	
	/** �쐬���R���e���c���݃t���O*/
	private String confirmFlag = null;
	/** ���J�҂��R���e���c���݃t���O*/
	private String fetureFlag = null;
	/** �e���v���[�g�R���e���c���݃t���O*/
	private String templateFlag = null;
	/** �����؂�R���e���c���݃t���O*/
	private String pastFlag = null;
	/** ����CD*/
	private String unificationId = null;
	/** ��������*/
	private String unificationNm = null;
	/** ���ƃR�[�h*/
	private String businessId = null;
	/** ���Ɩ���*/
	private String businessNm = null;
	/** �c�ƕ��R�[�h*/
	private String salesId = null;
	/** �c�ƕ�����*/
	private String salesNm = null;

	/** �]�ƈ��敪 */
	private String userFlag = null;

	/** �]�ƈ��敪���� */
	private String userFlagName = null;

	/** �g�D�敪 */
	private String originalFlag = null;

	/** �g�D�敪���� */
	private String originalName = null;

	/** ���� */
	private String belong = null;

	/** �������� */
	private String belongName = null;

	/** �X�܋敪*/
	private String storeFcFLag = null;

	/** �X�܃I�[�i�[�R�[�h*/
	private String ownerId = null;

	/** ���[���ʒm�s�v�t���O*/
	private String mailNoticeFlag = null;
	
	/** ���[��*/
	private String mail = null;
	
	/** �A���o�C�g����*/
	private String xaallkName = null;
	
	/**
	 * @return the confirmFlag
	 */
	public String getNewConfirmFlag() {
		return newConfirmFlag;
	}

	/**
	 * @param confirmFlag the confirmFlag to set
	 */
	public void setNewConfirmFlag(String confirmFlag) {
		this.newConfirmFlag = confirmFlag;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return currentDate
	 */
	public Date getCurrentDate() {
		return currentDate;
	}

	/**
	 * @param currentDate
	 *            �ݒ肷�� currentDate
	 */
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	/**
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            �ݒ肷�� departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return kanaMei
	 */
	public String getKanaMei() {
		return kanaMei;
	}

	/**
	 * @param kanaMei
	 *            �ݒ肷�� kanaMei
	 */
	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	/**
	 * @return kanaSei
	 */
	public String getKanaSei() {
		return kanaSei;
	}

	/**
	 * @param kanaSei
	 *            �ݒ肷�� kanaSei
	 */
	public void setKanaSei(String kanaSei) {
		this.kanaSei = kanaSei;
	}

	/**
	 * @return kanjiMei
	 */
	public String getKanjiMei() {
		return kanjiMei;
	}

	/**
	 * @param kanjiMei
	 *            �ݒ肷�� kanjiMei
	 */
	public void setKanjiMei(String kanjiMei) {
		this.kanjiMei = kanjiMei;
	}

	/**
	 * @return kanjiSei
	 */
	public String getKanjiSei() {
		return kanjiSei;
	}

	/**
	 * @param kanjiSei
	 *            �ݒ肷�� kanjiSei
	 */
	public void setKanjiSei(String kanjiSei) {
		this.kanjiSei = kanjiSei;
	}

	/**
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile
	 *            �ݒ肷�� mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return naisen
	 */
	public String getNaisen() {
		return naisen;
	}

	/**
	 * @param naisen
	 *            �ݒ肷�� naisen
	 */
	public void setNaisen(String naisen) {
		this.naisen = naisen;
	}

	/**
	 * @return pageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * @param pageId
	 *            �ݒ肷�� pageId
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            �ݒ肷�� password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return photoAddress
	 */
	public String getPhotoAddress() {
		return photoAddress;
	}

	/**
	 * @param photoAddress
	 *            �ݒ肷�� photoAddress
	 */
	public void setPhotoAddress(String photoAddress) {
		this.photoAddress = photoAddress;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return pwExpire
	 */
	public Date getPwExpire() {
		return pwExpire;
	}

	/**
	 * @param pwExpire
	 *            �ݒ肷�� pwExpire
	 */
	public void setPwExpire(Date pwExpire) {
		this.pwExpire = pwExpire;
	}

	/**
	 * @return pwSetDate
	 */
	public Date getPwSetDate() {
		return pwSetDate;
	}

	/**
	 * @param pwSetDate
	 *            �ݒ肷�� pwSetDate
	 */
	public void setPwSetDate(Date pwSetDate) {
		this.pwSetDate = pwSetDate;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role
	 *            �ݒ肷�� role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            �ݒ肷�� sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            �ݒ肷�� storeId
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	/**
	 * @return topGroupList
	 */
	public List<String> getTopGroupList() {
		return topGroupList;
	}

	/**
	 * @param topGroupList
	 *            �ݒ肷�� topGroupList
	 */
	public void setTopGroupList(List<String> topGroupList) {
		this.topGroupList = topGroupList;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            �ݒ肷�� userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return viewPhotoFlag
	 */
	public String getViewPhotoFlag() {
		return viewPhotoFlag;
	}

	/**
	 * @param viewPhotoFlag
	 *            �ݒ肷�� viewPhotoFlag
	 */
	public void setViewPhotoFlag(String viewPhotoFlag) {
		this.viewPhotoFlag = viewPhotoFlag;
	}

	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	public String getTrmailAddress() {
		return trmailAddress;
	}

	public void setTrmailAddress(String trmailAddress) {
		this.trmailAddress = trmailAddress;
	}

	public String getConfirmFlag() {
		return confirmFlag;
	}

	public void setConfirmFlag(String confirmFlag) {
		this.confirmFlag = confirmFlag;
	}

	public String getFetureFlag() {
		return fetureFlag;
	}

	public void setFetureFlag(String fetureFlag) {
		this.fetureFlag = fetureFlag;
	}

	public String getTemplateFlag() {
		return templateFlag;
	}

	public void setTemplateFlag(String templateFlag) {
		this.templateFlag = templateFlag;
	}

	public String getPastFlag() {
		return pastFlag;
	}

	public void setPastFlag(String pastFlag) {
		this.pastFlag = pastFlag;
	}

	public String getUnificationId() {
		return unificationId;
	}

	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}

	public String getUnificationNm() {
		return unificationNm;
	}

	public void setUnificationNm(String unificationNm) {
		this.unificationNm = unificationNm;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessNm() {
		return businessNm;
	}

	public void setBusinessNm(String businessNm) {
		this.businessNm = businessNm;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getSalesNm() {
		return salesNm;
	}

	public void setSalesNm(String salesNm) {
		this.salesNm = salesNm;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	public String getUserFlagName() {
		return userFlagName;
	}

	public void setUserFlagName(String userFlagName) {
		this.userFlagName = userFlagName;
	}

	public String getOriginalFlag() {
		return originalFlag;
	}

	public void setOriginalFlag(String originalFlag) {
		this.originalFlag = originalFlag;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getBelong() {
		return belong;
	}

	public void setBelong(String belong) {
		this.belong = belong;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public String getStoreFcFLag() {
		return storeFcFLag;
	}

	public void setStoreFcFLag(String storeFcFLag) {
		this.storeFcFLag = storeFcFLag;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getMailNoticeFlag() {
		return mailNoticeFlag;
	}

	public void setMailNoticeFlag(String mailNoticeFlag) {
		this.mailNoticeFlag = mailNoticeFlag;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getXaallkName() {
		return xaallkName;
	}

	public void setXaallkName(String xaallkName) {
		this.xaallkName = xaallkName;
	}
	
}
