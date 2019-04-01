/**
 * �g�b�v�O���[�v�ڍ�Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 �V�K�쐬
 */

package jp.co.fourseeds.fsnet.beans.personalTopGroup;

import jp.co.fourseeds.fsnet.beans.topGroup.TopGroupDetailBean;

public class PersonalTopGroupDetailBean extends TopGroupDetailBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** ���L�Ώێ҃��[�UID */
	private String shareTargetUserId;
	
	/** ���L�Ώێ҃��[�U�� */
	private String shareTargetUserName;
	
	/** �ҏW�\�t���O */
	private String authEditFlag;
	
	/** ���[�UID */
	private String userId;
	
	/** ���[�U���� */
	private String userName;

	public String getShareTargetUserId() {
		return shareTargetUserId;
	}

	public void setShareTargetUserId(String shareTargetUserId) {
		this.shareTargetUserId = shareTargetUserId;
	}

	public String getShareTargetUserName() {
		return shareTargetUserName;
	}

	public void setShareTargetUserName(String shareTargetUserName) {
		this.shareTargetUserName = shareTargetUserName;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
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