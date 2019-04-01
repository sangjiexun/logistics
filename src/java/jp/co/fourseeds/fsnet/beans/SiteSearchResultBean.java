package jp.co.fourseeds.fsnet.beans;

import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * �T�C�g���������ʂ��i�[
 * ����UserPageListInfo�@�ˁ@�R�s�[�V�K�쐬
 * ����HaitiInfoBean�@�ˁ@�R�s�[�V�K�쐬
 * 
 * @author 
 * @version 1.0
 * @function The form bean for search page.
 */
public class SiteSearchResultBean extends PageBean {

	/** Serial version uid */
	private static final long serialVersionUID = 7611032090331762173L;

	/** �������ʁFDB��Fess�̃}�b�s���O�L�[ */
	private String matchKey = null;
	
	/** �������ʁF�Y�t�t�@�C�����O */
	private String attachmentName = null;
	
	/** �������ʁF�Y�t�t�@�C���̃����N�� */
	private String attachmentFileUrl = null;

	/** �������ʁF������ */
	private String departmentName = null;
	
	/** �������ʁF�z�u�� */
	private String haitisaki = null;
	
	/** �z�u��v�Z�p�ϐ��@START */
	private String existFlag = null;					//�T�C�g�������ʒ��A�u�R���e���c�f�ڏꏊ�v�R���e���c���܂ށAexistFlag = 1
	private String strPageKey = null;
	private int countPass = 0;
	private int count = 0;
	private String linkName = null;
	private String linkTitle = null;
	private boolean isJituFlag = false;
	/** �z�u��v�Z�p�ϐ��@END */

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getMatchKey() {
		return matchKey;
	}

	public void setMatchKey(String matchKey) {
		this.matchKey = matchKey;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAttachmentFileUrl() {
		return attachmentFileUrl;
	}

	public void setAttachmentFileUrl(String attachmentFileUrl) {
		this.attachmentFileUrl = attachmentFileUrl;
	}

	public String getHaitisaki() {
		return haitisaki;
	}

	public void setHaitisaki(String haitisaki) {
		this.haitisaki = haitisaki;
	}

	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}

	public String getStrPageKey() {
		return strPageKey;
	}

	public void setStrPageKey(String strPageKey) {
		this.strPageKey = strPageKey;
	}

	public int getCountPass() {
		return countPass;
	}

	public void setCountPass(int countPass) {
		this.countPass = countPass;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkTitle() {
		return linkTitle;
	}

	public void setLinkTitle(String linkTitle) {
		this.linkTitle = linkTitle;
	}

	public boolean isJituFlag() {
		return isJituFlag;
	}

	public void setJituFlag(boolean isJituFlag) {
		this.isJituFlag = isJituFlag;
	}
}
