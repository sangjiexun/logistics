/**
 * ���[�U�O���[�v�ڍ�Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.04 �V�K�쐬
 */

package jp.co.fourseeds.fsnet.beans.userGroup;

import jp.co.common.frame.beans.BaseBean;

public class UserGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ���[�U�O���[�vID */
	private String userGroupId;
	
	/** ���f�\����� */
	private String ugRefDate;
	
	/** ���[�UID */
	private String userId;
	
	/** ���[�U���� */
	private String userName;

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUgRefDate() {
		return ugRefDate;
	}

	public void setUgRefDate(String ugRefDate) {
		this.ugRefDate = ugRefDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}