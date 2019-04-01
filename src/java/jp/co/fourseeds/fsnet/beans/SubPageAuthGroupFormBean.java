package jp.co.fourseeds.fsnet.beans;

import java.util.List;

import jp.co.common.frame.beans.BaseBean;

import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;

public class SubPageAuthGroupFormBean extends BaseBean {
	
	/** The Field serialVersionUID */
	private static final long serialVersionUID = 2479865448514900978L;

	/**
	 * �����O���[�v����
	 * */
	private String groupSearchName;
	
	/**
	 * �O���[�v���X�g
	 * */
	private List<AuthGroupBean> groupList;
	
	/**
	 * �O���[�v���X�g�iTEMP�j
	 * */
	private String tempGroupList;
	
	/**
	 * �l�t���O 
	 * */
	private String personalFlag;
	
	/**
	 * �폜�t���O
	 * */
	private String groupDeleteFlag;
	
	/**
	 * @return the searchGroupName
	 */
	public String getGroupSearchName() {
		return groupSearchName;
	}

	/**
	 * @param searchGroupName the searchGroupName to set
	 */
	public void setGroupSearchName(String groupSearchName) {
		this.groupSearchName = groupSearchName;
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
	 * @return the groupDeleteFlag
	 */
	public String getGroupDeleteFlag() {
		return groupDeleteFlag;
	}

	/**
	 * @param groupDeleteFlag the groupDeleteFlag to set
	 */
	public void setGroupDeleteFlag(String groupDeleteFlag) {
		this.groupDeleteFlag = groupDeleteFlag;
	}

	/**
	 * @return the tempGroupList
	 */
	public String getTempGroupList() {
		return tempGroupList;
	}

	/**
	 * @param tempGroupList the tempGroupList to set
	 */
	public void setTempGroupList(String tempGroupList) {
		this.tempGroupList = tempGroupList;
	}

	/**
	 * @return the groupList
	 */
	public List<AuthGroupBean> getGroupList() {
		return groupList;
	}

	/**
	 * @param groupList the groupList to set
	 */
	public void setGroupList(List<AuthGroupBean> groupList) {
		this.groupList = groupList;
	}

}
