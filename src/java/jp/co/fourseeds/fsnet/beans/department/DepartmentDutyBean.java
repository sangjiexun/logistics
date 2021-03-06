package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentDutyBean extends BaseBean{
	private static final long serialVersionUID = 1122334455987654335L;
	/** 部門ID */
	private String departmentId;

	/** 担当職務紹介 */
	private String dutyDescription;

	/** 担当ユーザID */
	private String userId;

	/** 姓漢字 */
	private String kanziSei;
	
	/** 名漢字 */
	private String kanziMei;
	
	/** 担当者名 */
	private String userName;
	
	/** 画面削除フラグ */
	private String chkDutyDelFlag;
	
	
	/**
	 * @return departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            設定する departmentId
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return dutyDescription
	 */
	public String getDutyDescription() {
		return dutyDescription;
	}

	/**
	 * @param dutyDescription
	 *            設定する dutyDescription
	 */
	public void setDutyDescription(String dutyDescription) {
		this.dutyDescription = dutyDescription;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            設定する userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName 設定する userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getChkDutyDelFlag() {
		return chkDutyDelFlag;
	}

	public void setChkDutyDelFlag(String chkDutyDelFlag) {
		this.chkDutyDelFlag = chkDutyDelFlag;
	}
}
