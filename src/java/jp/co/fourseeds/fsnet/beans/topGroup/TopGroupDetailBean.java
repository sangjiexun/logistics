/**
 * �g�b�v�O���[�v�ڍ�Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.19 �V�K�쐬
 */

package jp.co.fourseeds.fsnet.beans.topGroup;

import jp.co.common.frame.beans.BaseBean;

public class TopGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** �g�b�v�O���[�vID */
	private String topGroupId;
	
	/** ���f�\����� */
	private String lgRefDate;
	
	/** �T�u�O���[�v�h�c */
	private String subGroupId;
	
	/** �T�u�O���[�v���� */
	private String subGroupName;
	
	/** �T�u�O���[�v�敪 */
	private String subGroupFlag;

	public String getTopGroupId() {
		return topGroupId;
	}

	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}

	public String getLgRefDate() {
		return lgRefDate;
	}

	public void setLgRefDate(String lgRefDate) {
		this.lgRefDate = lgRefDate;
	}

	public String getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(String subGroupId) {
		this.subGroupId = subGroupId;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}

	public String getSubGroupFlag() {
		return subGroupFlag;
	}

	public void setSubGroupFlag(String subGroupFlag) {
		this.subGroupFlag = subGroupFlag;
	}
}