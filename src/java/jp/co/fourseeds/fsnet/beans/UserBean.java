package jp.co.fourseeds.fsnet.beans;

import java.io.File;
import java.util.Date;
import java.util.List;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;

/**
 * ���[�U���Bean
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/27		    NTS        	       �쐬
 * 1.1		2017/09/05			NTS			�������C��
 *
 **/
public class UserBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/**������������������������A����Bean��������������������������������*/
	/** �����Ј��ԍ�*/
	private String searchUserId;
	
	/** ��������*/
	private String searchUserName;
	
	/** �������ID*/
	private String searchCompanyId;
	
	/** ������Ж�*/
	private String searchCompanyName;
	
	/** ��������ID*/
	private String searchUnificationId;
	
	/** ����������*/
	private String searchUnificationName;
	
	/** ��������ID*/
	private String searchBusinessId;
	
	/** �������Ɩ�*/
	private String searchBusinessName;
	
	/** �����c�ƕ�ID*/
	private String searchSalesId;
	
	/** �����c�ƕ���*/
	private String searchSalesName;
	
	/** �����{��ID*/
	private String searchDeptId;
	
	/** �����{����*/
	private String searchDeptName;
	
	/** �������X��ID*/
	private String searchStoreId;
	
	/** �����X�ܖ�*/
	private String searchStoreName;
	
	/** �����]�ƈ��敪*/
	private String searchPemployeeId;
	
	/** �����]�ƈ��敪��*/
	private String searchPemployeeName;
	
	/** �����\���t���O*/
	private String searchShowFlag;
	
	/** �����\���t���O��*/
	private String searchShowFlagName;
	
	/** �������� */
	private String isFirstSearch;
	
	/** �y�[�W���ɕ\���������i�[ */
	private int searchRowNum;
	
	/**�]�ƈ��敪���X�g*/
	private List<LabelValueBean> pemployeeList;
	
	/**�\���t���O���X�g*/
	private List<LabelValueBean> showFlagList;
	
	/**�V�X�e�����p�敪���X�g*/
	private List<LabelValueBean> roleList;
	
	/**�ʐ^�{���敪���X�g*/
	private List<LabelValueBean> viewPhotoList;
	
	/**������������������������A��������Bean������������������������*/
	
	/** �Ј��ԍ� */
	private String userId;
	
	/** ��������*/
	private String kanziName;
	
	/** �J�i����*/
	private String kanaName;
	
	/** ������*/
	private String belongName;
	
	/** ��Ж�*/
	private String companyName;
	
	/** ������*/
	private String unificationName;
	
	/** ���Ɩ�*/
	private String businessName;
	
	/** �c�ƕ���*/
	private String salesName;
	
	/** �ЗL�g�єԍ�*/
	private String mobileNum;
	
	/** ����*/
	private String naisenNum;
	
	/** �g�D�敪*/
	private String organizationId;
	
	/** �g�D�敪��*/
	private String organizationName;
	
	/** �L���J�n��*/
	private String userSet;
	
	/** �L���I����*/
	private String userEx;
	
	/** �p�X���[�h*/
	private String password;
	
	/** �p�X���[�h�L���I����*/
	private String pwExpire;
	
	/** ������*/
	private String kanziSei;
	
	/** ������*/
	private String kanziMei;
	
	/** �J�i��*/
	private String kanaSei;
	
	/** �J�i��*/
	private String kanaMei;
	
	/** �V�X�e�����p�敪*/
	private String roleId;
	
	/** �V�X�e�����p�敪����*/
	private String roleName;
	
	/** ���V�X�e�����p�敪*/
	private String oldRoleId;
	
	/**�V�X�e�����p�敪���X�g*/
	private String loginRoleId;
	
	/** �]�ƈ����V�X�e�����p�敪*/
	private String userOldRoleId;
	
	/** �]�ƈ��敪*/
	private String pEmployeeFlag;
	
	/** �]�ƈ��敪����*/
	private String pEmployeeFlagName;
	
	/** ����ID*/
	private String belongId;
	
	/** ����*/
	private String sex;
	
	/** ���ʖ���*/
	private String sexName;
	
	/** �ʐ^�{���敪*/
	private String viewPhoto;
	
	/** ���O�C�����[�U�̎ʐ^�{���敪*/
	private String loginUserViewPhoto;
	
	/** �d�v�Ȃ��m�点 �\���J�n���ݒ�*/
	private String necessityDisplayStartDate;
	
	/** �C���[�W�f�[�^*/
	private byte[] imgDat;

	/** �� �^*/
	private File photo;
	
	/** �� �^�p�X*/
	private String photoPath;
	
	/** �� �^�� */
	private String photoFileName;
	
	/** �Ј��ʐ^�A�h���X*/
	private String photoAdd;
	
	/** ���Ј��ʐ^�A�h���X*/
	private String oldPhotoAdd;
	
	/**���[���A�h���X*/
	private String mailAddress;
	
	/**�C���[�W�\���敪*/
	private String imgFlag;
	
	/**���[���ʒm�t���O*/
	private String mailNoticFlag;
	
	/**���[����\���t���O*/
	private String mailShowFlag;
	
	/**�\���ݒ�t���O*/
	private String showFlag;
	
	/**�\���ݒ�t���O��*/
	private String showFlagName;
	
	/**���ID*/
	private String companyId;
	
	/**����ID*/
	private String unificationId;
	
	/**����ID*/
	private String businessId;
	
	/**�c�ƕ�ID*/
	private String salesId;
	
	/**���[�U�O���[�vID*/
	private String userGroupId;
	
	/**���f�\�����*/
	private Date ugRefDate;
	
	public String getShowFlag() {
		return showFlag;
	}

	public void setShowFlag(String showFlag) {
		this.showFlag = showFlag;
	}

	public String getShowFlagName() {
		return showFlagName;
	}

	public void setShowFlagName(String showFlagName) {
		this.showFlagName = showFlagName;
	}

	public String getUnificationId() {
		return unificationId;
	}

	public void setUnificationId(String unificationId) {
		this.unificationId = unificationId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getSalesId() {
		return salesId;
	}

	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}

	public String getMailNoticFlag() {
		return mailNoticFlag;
	}

	public void setMailNoticFlag(String mailNoticFlag) {
		this.mailNoticFlag = mailNoticFlag;
	}

	public String getSearchUserId() {
		return searchUserId;
	}

	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	public String getSearchUserName() {
		return searchUserName;
	}

	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	public List<LabelValueBean> getShowFlagList() {
		return showFlagList;
	}

	public void setShowFlagList(List<LabelValueBean> showFlagList) {
		this.showFlagList = showFlagList;
	}
	
	public String getSearchBusinessName() {
		return searchBusinessName;
	}

	public void setSearchBusinessName(String searchBusinessName) {
		this.searchBusinessName = searchBusinessName;
	}

	public String getSearchSalesName() {
		return searchSalesName;
	}

	public void setSearchSalesName(String searchSalesName) {
		this.searchSalesName = searchSalesName;
	}

	public String getSearchPemployeeName() {
		return searchPemployeeName;
	}

	public void setSearchPemployeeName(String searchPemployeeName) {
		this.searchPemployeeName = searchPemployeeName;
	}

	public String getSearchShowFlag() {
		return searchShowFlag;
	}

	public void setSearchShowFlag(String searchShowFlag) {
		this.searchShowFlag = searchShowFlag;
	}

	public String getSearchShowFlagName() {
		return searchShowFlagName;
	}

	public void setSearchShowFlagName(String searchShowFlagName) {
		this.searchShowFlagName = searchShowFlagName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getpEmployeeFlag() {
		return pEmployeeFlag;
	}

	public void setpEmployeeFlag(String pEmployeeFlag) {
		this.pEmployeeFlag = pEmployeeFlag;
	}

	public String getpEmployeeFlagName() {
		return pEmployeeFlagName;
	}

	public void setpEmployeeFlagName(String pEmployeeFlagName) {
		this.pEmployeeFlagName = pEmployeeFlagName;
	}

	public String getSearchBusinessId() {
		return searchBusinessId;
	}

	public void setSearchBusinessId(String searchBusinessId) {
		this.searchBusinessId = searchBusinessId;
	}

	public String getSearchSalesId() {
		return searchSalesId;
	}

	public void setSearchSalesId(String searchSalesId) {
		this.searchSalesId = searchSalesId;
	}

	public String getSearchUnificationId() {
		return searchUnificationId;
	}

	public void setSearchUnificationId(String searchUnificationId) {
		this.searchUnificationId = searchUnificationId;
	}

	public String getSearchUnificationName() {
		return searchUnificationName;
	}

	public void setSearchUnificationName(String searchUnificationName) {
		this.searchUnificationName = searchUnificationName;
	}

	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	public int getSearchRowNum() {
		return searchRowNum;
	}

	public void setSearchRowNum(int searchRowNum) {
		this.searchRowNum = searchRowNum;
	}
	
	public String getSearchPemployeeId() {
		return searchPemployeeId;
	}

	public void setSearchPemployeeId(String searchUserDivision) {
		this.searchPemployeeId = searchUserDivision;
	}

	public List<LabelValueBean> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<LabelValueBean> roleList) {
		this.roleList = roleList;
	}

	public List<LabelValueBean> getPemployeeList() {
		return pemployeeList;
	}

	public void setPemployeeList(List<LabelValueBean> pemployeeList) {
		this.pemployeeList = pemployeeList;
	}

	public List<LabelValueBean> getViewPhotoList() {
		return viewPhotoList;
	}

	public void setViewPhotoList(List<LabelValueBean> viewPhotoList) {
		this.viewPhotoList = viewPhotoList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKanziName() {
		return kanziName;
	}

	public void setKanziName(String kanziName) {
		this.kanziName = kanziName;
	}

	public String getKanaName() {
		return kanaName;
	}

	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getNaisenNum() {
		return naisenNum;
	}

	public void setNaisenNum(String naisenNum) {
		this.naisenNum = naisenNum;
	}

	public String getUserSet() {
		return userSet;
	}

	public void setUserSet(String userSet) {
		this.userSet = userSet;
	}

	public String getUserEx() {
		return userEx;
	}

	public void setUserEx(String userEx) {
		this.userEx = userEx;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPwExpire() {
		return pwExpire;
	}

	public void setPwExpire(String pwExpire) {
		this.pwExpire = pwExpire;
	}

	public String getKanziSei() {
		return kanziSei;
	}

	public void setKanziSei(String kanziSei) {
		this.kanziSei = kanziSei;
	}

	public String getKanziMei() {
		return kanziMei;
	}

	public void setKanziMei(String kanziMei) {
		this.kanziMei = kanziMei;
	}

	public String getKanaSei() {
		return kanaSei;
	}

	public void setKanaSei(String kanaSei) {
		this.kanaSei = kanaSei;
	}

	public String getKanaMei() {
		return kanaMei;
	}

	public void setKanaMei(String kanaMei) {
		this.kanaMei = kanaMei;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOldRoleId() {
		return oldRoleId;
	}

	public void setOldRoleId(String oldRoleId) {
		this.oldRoleId = oldRoleId;
	}

	public String getPEmployeeFlag() {
		return pEmployeeFlag;
	}

	public void setPEmployeeFlag(String pEmployeeFlag) {
		this.pEmployeeFlag = pEmployeeFlag;
	}

	public String getPEmployeeFlagName() {
		return pEmployeeFlagName;
	}

	public void setPEmployeeFlagName(String pEmployeeFlagName) {
		this.pEmployeeFlagName = pEmployeeFlagName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getViewPhoto() {
		return viewPhoto;
	}

	public void setViewPhoto(String viewPhoto) {
		this.viewPhoto = viewPhoto;
	}

	public String getLoginUserViewPhoto() {
		return loginUserViewPhoto;
	}

	public void setLoginUserViewPhoto(String loginUserViewPhoto) {
		this.loginUserViewPhoto = loginUserViewPhoto;
	}

	public String getNecessityDisplayStartDate() {
		return necessityDisplayStartDate;
	}

	public void setNecessityDisplayStartDate(String necessityDisplayStartDate) {
		this.necessityDisplayStartDate = necessityDisplayStartDate;
	}

	public byte[] getImgDat() {
		return imgDat;
	}

	public void setImgDat(byte[] imgDat) {
		this.imgDat = imgDat;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoAdd() {
		return photoAdd;
	}

	public void setPhotoAdd(String photoAdd) {
		this.photoAdd = photoAdd;
	}

	public String getOldPhotoAdd() {
		return oldPhotoAdd;
	}

	public void setOldPhotoAdd(String oldPhotoAdd) {
		this.oldPhotoAdd = oldPhotoAdd;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getImgFlag() {
		return imgFlag;
	}

	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}

	public String getUnificationName() {
		return unificationName;
	}

	public void setUnificationName(String unificationName) {
		this.unificationName = unificationName;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getSalesName() {
		return salesName;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public String getMailShowFlag() {
		return mailShowFlag;
	}

	public void setMailShowFlag(String mailShowFlag) {
		this.mailShowFlag = mailShowFlag;
	}

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Date getUgRefDate() {
		return ugRefDate;
	}

	public void setUgRefDate(Date ugRefDate) {
		this.ugRefDate = ugRefDate;
	}

	public String getUserOldRoleId() {
		return userOldRoleId;
	}

	public void setUserOldRoleId(String userOldRoleId) {
		this.userOldRoleId = userOldRoleId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSearchCompanyId() {
		return searchCompanyId;
	}

	public void setSearchCompanyId(String searchCompanyId) {
		this.searchCompanyId = searchCompanyId;
	}

	public String getSearchCompanyName() {
		return searchCompanyName;
	}

	public void setSearchCompanyName(String searchCompanyName) {
		this.searchCompanyName = searchCompanyName;
	}

	public String getBelongName() {
		return belongName;
	}

	public void setBelongName(String belongName) {
		this.belongName = belongName;
	}

	public String getBelongId() {
		return belongId;
	}

	public void setBelongId(String belongId) {
		this.belongId = belongId;
	}

	public String getLoginRoleId() {
		return loginRoleId;
	}

	public void setLoginRoleId(String loginRoleId) {
		this.loginRoleId = loginRoleId;
	}

	public String getSearchDeptId() {
		return searchDeptId;
	}

	public void setSearchDeptId(String searchDeptId) {
		this.searchDeptId = searchDeptId;
	}

	public String getSearchDeptName() {
		return searchDeptName;
	}

	public void setSearchDeptName(String searchDeptName) {
		this.searchDeptName = searchDeptName;
	}

	public String getSearchStoreId() {
		return searchStoreId;
	}

	public void setSearchStoreId(String searchStoreId) {
		this.searchStoreId = searchStoreId;
	}

	public String getSearchStoreName() {
		return searchStoreName;
	}

	public void setSearchStoreName(String searchStoreName) {
		this.searchStoreName = searchStoreName;
	}
	
}