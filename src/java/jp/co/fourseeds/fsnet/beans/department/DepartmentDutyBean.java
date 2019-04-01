package jp.co.fourseeds.fsnet.beans.department;

import jp.co.common.frame.beans.BaseBean;

public class DepartmentDutyBean extends BaseBean{
	private static final long serialVersionUID = 1122334455987654335L;
	/** ����ID */
	private String departmentId;

	/** �S���E���Љ� */
	private String dutyDescription;

	/** �S�����[�UID */
	private String userId;

	/** ������ */
	private String kanziSei;
	
	/** ������ */
	private String kanziMei;
	
	/** �S���Җ� */
	private String userName;
	
	/** ��ʍ폜�t���O */
	private String chkDutyDelFlag;
	
	
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
	 * @return dutyDescription
	 */
	public String getDutyDescription() {
		return dutyDescription;
	}

	/**
	 * @param dutyDescription
	 *            �ݒ肷�� dutyDescription
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
	 *            �ݒ肷�� userId
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
	 * @param userName �ݒ肷�� userName
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
