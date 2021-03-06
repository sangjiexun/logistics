package jp.co.fourseeds.fsnet.beans.personalMail;

import java.util.List;
import jp.co.common.frame.beans.BaseBean;

public class PersonalMailFormBean extends BaseBean {
	
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 1L;

	/**
	 * 検索条件ユーザ分類
	 * */
	private String searchUserDiv;
	
	/**
	 * 検索条件メールドメイン
	 * */
	private String searchMailSuffix;

	/**
	 * メールドメイン表示名
	 * */
	private String searchMailSuffixName;

	/**
	 * 検索条件ユーザID
	 * */
	private String searchUserId;

	/**
	 * 検索条件ユーザ名称
	 * */
	private String searchUserName;

	/**
	 * 検索条件部門名称
	 * */
	private String searchDeptName;

	/**
	 * 検索条件登録依頼のみフラグ
	 * */
	private String entryFlag;

	/**
	 * 検索条件個別設定のみフラグ
	 * */
	private String personalFlag;

	/**
	 * 検索条件退職者も含むフラグ
	 * */
	private String quitFlag;

	/**
	 * 検索ボタン押下フラグ（0：押下なし、1：押下する）
	 * */
	private String enterSearchFlag;

	/**
	 * 検索条件メール＠前に内容
	 * */
	private String searchMailId;

	/**
	 * 検索結果リスト
	 * */
	private List<PersonalMailBean> personalMailList;

	/**
	 * 検索結果CSVリスト
	 * */
	private List<PersonalMailBean> personalMailCsvList;

	/**
	 * 検索結果全件
	 * */
	private int totalMailCnt;

	/**
	 * 検索結果登録可能残数
	 * */
	private int validMailCnt;

	/**
	 * 検索結果詳細リスト
	 * */
	private List<PersonalMailBean> personalMailDetailList;
	
	/**
	 * 初期検索
	 * */
	private String isFirstSearch;
	
	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ＤＥＴＡＩＬ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/
	/**
	 * ＤＥＴＡＩＬ：プロファイル区分
	 * */
	private String profileDiv;
	
	/**
	 * ＤＥＴＡＩＬ：個別設定フラグ（ヌル：個別メール存在しない、0：個別メール設定依頼、1：個別メール完了通知）
	 * */
	private String personalMailSettingFlag;

	/**
	 * ＤＥＴＡＩＬ：画面表示メール＠前に内容
	 * */
	private String displayMailId;

	/**
	 * ＤＥＴＡＩＬ：SEQUENCE
	 * */
	private String sequence;

	/**
	 * ＤＥＴＡＩＬ：ユーザＩＤ
	 * */
	private String userId;

	/**
	 * ＤＥＴＡＩＬ：利用者姓
	 * */
	private String userSei;

	/**
	 * ＤＥＴＡＩＬ：利用者名
	 * */
	private String userMei;

	/**
	 * ＤＥＴＡＩＬ：分類
	 * */
	private String mailUserDivision;

	/**
	 * ＤＥＴＡＩＬ：ステータス
	 * */
	private String mailTaskStatus;

	/**
	 * ＤＥＴＡＩＬ：所属名称
	 * */
	private String departmentName;

	/**
	 * 所属ＩＤ
	 * */
	private String departmentId;

	/**
	 * 
	 * ＤＥＴＡＩＬ：メールアドレス(確定情報)
	 * */
	private String mailAddress;

	/**
	 * ＤＥＴＡＩＬ：利用開始日(確定情報)
	 * */
	private String mailSet;

	/**
	 * ＤＥＴＡＩＬ：利用終了日(確定情報)
	 * */
	private String mailEx;

	/**
	 * ＤＥＴＡＩＬ：ユーザ名
	 * */
	private String mailId;

	/**
	 * ＤＥＴＡＩＬ：パスワード
	 * */
	private String password;

	/**
	 * ＤＥＴＡＩＬ：Softbank登録日
	 * */
	private String entryDate;

	/**
	 * ＤＥＴＡＩＬ：退職日
	 * */
	private String retirementDate;

	/**
	 * ＤＥＴＡＩＬ：Softbank削除日
	 * */
	private String deleteDate1;

	/**
	 * ＤＥＴＡＩＬ：Google削除日
	 * */
	private String deleteDate2;

	/**
	 * ＤＥＴＡＩＬ：依頼者ＩＤ
	 * */
	private String requestId;

	/**
	 * ＤＥＴＡＩＬ：作業者ＩＤ
	 * */
	private String registryId;

	/**
	 * ＤＥＴＡＩＬ：メールドメインフラグ
	 * */
	private String mailSuffixFlag;

	/**
	 * ＤＥＴＡＩＬ：メールドメインフラグ
	 * */
	private String mailSuffixName;

	/**
	 * ＤＥＴＡＩＬ：分類表示名
	 * */
	private String mailUserDivName;
	
	/**
	 * パスワード送付区分
	 */
	private String passwordSendingDivision;
	
	/**
	 * @return the searchUserDiv
	 */
	public String getSearchUserDiv() {
		return searchUserDiv;
	}

	/**
	 * @param searchUserDiv the searchUserDiv to set
	 */
	public void setSearchUserDiv(String searchUserDiv) {
		this.searchUserDiv = searchUserDiv;
	}

	/**
	 * @return the searchMailSuffix
	 */
	public String getSearchMailSuffix() {
		return searchMailSuffix;
	}

	/**
	 * @param searchMailSuffix the searchMailSuffix to set
	 */
	public void setSearchMailSuffix(String searchMailSuffix) {
		this.searchMailSuffix = searchMailSuffix;
	}

	/**
	 * @return the searchMailSuffixName
	 */
	public String getSearchMailSuffixName() {
		return searchMailSuffixName;
	}

	/**
	 * @param searchMailSuffixName the searchMailSuffixName to set
	 */
	public void setSearchMailSuffixName(String searchMailSuffixName) {
		this.searchMailSuffixName = searchMailSuffixName;
	}

	/**
	 * @return the searchUserId
	 */
	public String getSearchUserId() {
		return searchUserId;
	}

	/**
	 * @param searchUserId the searchUserId to set
	 */
	public void setSearchUserId(String searchUserId) {
		this.searchUserId = searchUserId;
	}

	/**
	 * @return the searchUserName
	 */
	public String getSearchUserName() {
		return searchUserName;
	}

	/**
	 * @param searchUserName the searchUserName to set
	 */
	public void setSearchUserName(String searchUserName) {
		this.searchUserName = searchUserName;
	}

	/**
	 * @return the searchDeptName
	 */
	public String getSearchDeptName() {
		return searchDeptName;
	}

	/**
	 * @param searchDeptName the searchDeptName to set
	 */
	public void setSearchDeptName(String searchDeptName) {
		this.searchDeptName = searchDeptName;
	}

	/**
	 * @return the entryFlag
	 */
	public String getEntryFlag() {
		return entryFlag;
	}

	/**
	 * @param entryFlag the entryFlag to set
	 */
	public void setEntryFlag(String entryFlag) {
		this.entryFlag = entryFlag;
	}

	/**
	 * @return the personalFlag
	 */
	public String getPersonalFlag() {
		return personalFlag;
	}

	/**
	 * @param personalFlag the personalFlag to set
	 */
	public void setPersonalFlag(String personalFlag) {
		this.personalFlag = personalFlag;
	}

	/**
	 * @return the quitFlag
	 */
	public String getQuitFlag() {
		return quitFlag;
	}

	/**
	 * @param quitFlag the quitFlag to set
	 */
	public void setQuitFlag(String quitFlag) {
		this.quitFlag = quitFlag;
	}

	/**
	 * @return the enterSearchFlag
	 */
	public String getEnterSearchFlag() {
		return enterSearchFlag;
	}

	/**
	 * @param enterSearchFlag the enterSearchFlag to set
	 */
	public void setEnterSearchFlag(String enterSearchFlag) {
		this.enterSearchFlag = enterSearchFlag;
	}

	/**
	 * @return the searchMailId
	 */
	public String getSearchMailId() {
		return searchMailId;
	}

	/**
	 * @param searchMailId the searchMailId to set
	 */
	public void setSearchMailId(String searchMailId) {
		this.searchMailId = searchMailId;
	}

	/**
	 * @return the personalMailList
	 */
	public List<PersonalMailBean> getPersonalMailList() {
		return personalMailList;
	}

	/**
	 * @param personalMailList the personalMailList to set
	 */
	public void setPersonalMailList(List<PersonalMailBean> personalMailList) {
		this.personalMailList = personalMailList;
	}

	/**
	 * @return the personalMailCsvList
	 */
	public List<PersonalMailBean> getPersonalMailCsvList() {
		return personalMailCsvList;
	}

	/**
	 * @param personalMailCsvList the personalMailCsvList to set
	 */
	public void setPersonalMailCsvList(List<PersonalMailBean> personalMailCsvList) {
		this.personalMailCsvList = personalMailCsvList;
	}

	/**
	 * @return the totalMailCnt
	 */
	public int getTotalMailCnt() {
		return totalMailCnt;
	}

	/**
	 * @param totalMailCnt the totalMailCnt to set
	 */
	public void setTotalMailCnt(int totalMailCnt) {
		this.totalMailCnt = totalMailCnt;
	}

	/**
	 * @return the validMailCnt
	 */
	public int getValidMailCnt() {
		return validMailCnt;
	}

	/**
	 * @param validMailCnt the validMailCnt to set
	 */
	public void setValidMailCnt(int validMailCnt) {
		this.validMailCnt = validMailCnt;
	}

	/**
	 * @return the personalMailDetailList
	 */
	public List<PersonalMailBean> getPersonalMailDetailList() {
		return personalMailDetailList;
	}

	/**
	 * @param personalMailDetailList the personalMailDetailList to set
	 */
	public void setPersonalMailDetailList(List<PersonalMailBean> personalMailDetailList) {
		this.personalMailDetailList = personalMailDetailList;
	}

	/**
	 * @return the profileDiv
	 */
	public String getProfileDiv() {
		return profileDiv;
	}

	/**
	 * @param profileDiv the profileDiv to set
	 */
	public void setProfileDiv(String profileDiv) {
		this.profileDiv = profileDiv;
	}

	/**
	 * @return the personalMailSettingFlag
	 */
	public String getPersonalMailSettingFlag() {
		return personalMailSettingFlag;
	}

	/**
	 * @param personalMailSettingFlag the personalMailSettingFlag to set
	 */
	public void setPersonalMailSettingFlag(String personalMailSettingFlag) {
		this.personalMailSettingFlag = personalMailSettingFlag;
	}

	/**
	 * @return the displayMailId
	 */
	public String getDisplayMailId() {
		return displayMailId;
	}

	/**
	 * @param displayMailId the displayMailId to set
	 */
	public void setDisplayMailId(String displayMailId) {
		this.displayMailId = displayMailId;
	}

	/**
	 * @return the sequence
	 */
	public String getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userSei
	 */
	public String getUserSei() {
		return userSei;
	}

	/**
	 * @param userSei the userSei to set
	 */
	public void setUserSei(String userSei) {
		this.userSei = userSei;
	}

	/**
	 * @return the userMei
	 */
	public String getUserMei() {
		return userMei;
	}

	/**
	 * @param userMei the userMei to set
	 */
	public void setUserMei(String userMei) {
		this.userMei = userMei;
	}

	/**
	 * @return the mailUserDivision
	 */
	public String getMailUserDivision() {
		return mailUserDivision;
	}

	/**
	 * @param mailUserDivision the mailUserDivision to set
	 */
	public void setMailUserDivision(String mailUserDivision) {
		this.mailUserDivision = mailUserDivision;
	}

	/**
	 * @return the mailTaskStatus
	 */
	public String getMailTaskStatus() {
		return mailTaskStatus;
	}

	/**
	 * @param mailTaskStatus the mailTaskStatus to set
	 */
	public void setMailTaskStatus(String mailTaskStatus) {
		this.mailTaskStatus = mailTaskStatus;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return the mailSet
	 */
	public String getMailSet() {
		return mailSet;
	}

	/**
	 * @param mailSet the mailSet to set
	 */
	public void setMailSet(String mailSet) {
		this.mailSet = mailSet;
	}

	/**
	 * @return the mailEx
	 */
	public String getMailEx() {
		return mailEx;
	}

	/**
	 * @param mailEx the mailEx to set
	 */
	public void setMailEx(String mailEx) {
		this.mailEx = mailEx;
	}

	/**
	 * @return the mailId
	 */
	public String getMailId() {
		return mailId;
	}

	/**
	 * @param mailId the mailId to set
	 */
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the retirementDate
	 */
	public String getRetirementDate() {
		return retirementDate;
	}

	/**
	 * @param retirementDate the retirementDate to set
	 */
	public void setRetirementDate(String retirementDate) {
		this.retirementDate = retirementDate;
	}

	/**
	 * @return the deleteDate1
	 */
	public String getDeleteDate1() {
		return deleteDate1;
	}

	/**
	 * @param deleteDate1 the deleteDate1 to set
	 */
	public void setDeleteDate1(String deleteDate1) {
		this.deleteDate1 = deleteDate1;
	}

	/**
	 * @return the deleteDate2
	 */
	public String getDeleteDate2() {
		return deleteDate2;
	}

	/**
	 * @param deleteDate2 the deleteDate2 to set
	 */
	public void setDeleteDate2(String deleteDate2) {
		this.deleteDate2 = deleteDate2;
	}

	/**
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the registryId
	 */
	public String getRegistryId() {
		return registryId;
	}

	/**
	 * @param registryId the registryId to set
	 */
	public void setRegistryId(String registryId) {
		this.registryId = registryId;
	}

	/**
	 * @return the mailSuffixFlag
	 */
	public String getMailSuffixFlag() {
		return mailSuffixFlag;
	}

	/**
	 * @param mailSuffixFlag the mailSuffixFlag to set
	 */
	public void setMailSuffixFlag(String mailSuffixFlag) {
		this.mailSuffixFlag = mailSuffixFlag;
	}

	/**
	 * @return the mailSuffixName
	 */
	public String getMailSuffixName() {
		return mailSuffixName;
	}

	/**
	 * @param mailSuffixName the mailSuffixName to set
	 */
	public void setMailSuffixName(String mailSuffixName) {
		this.mailSuffixName = mailSuffixName;
	}

	/**
	 * @return the mailUserDivName
	 */
	public String getMailUserDivName() {
		return mailUserDivName;
	}

	/**
	 * @param mailUserDivName the mailUserDivName to set
	 */
	public void setMailUserDivName(String mailUserDivName) {
		this.mailUserDivName = mailUserDivName;
	}

	/**
	 * @return the isFirstSearch
	 */
	public String getIsFirstSearch() {
		return isFirstSearch;
	}

	/**
	 * @param isFirstSearch the isFirstSearch to set
	 */
	public void setIsFirstSearch(String isFirstSearch) {
		this.isFirstSearch = isFirstSearch;
	}

	/**
	 * @return the passwordSendingDivision
	 */
	public String getPasswordSendingDivision() {
		return passwordSendingDivision;
	}

	/**
	 * @param passwordSendingDivision the passwordSendingDivision to set
	 */
	public void setPasswordSendingDivision(String passwordSendingDivision) {
		this.passwordSendingDivision = passwordSendingDivision;
	}

}
