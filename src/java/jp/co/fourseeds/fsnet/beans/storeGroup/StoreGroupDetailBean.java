/**
 * XÜO[vÚ×Bean
 * 
 * @author NTS
 * @version 1.0.0 : 2016.01.08 VKì¬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.12.05 ©¼µC³
 */

package jp.co.fourseeds.fsnet.beans.storeGroup;

import jp.co.common.frame.beans.BaseBean;

public class StoreGroupDetailBean extends BaseBean {

	private static final long serialVersionUID = 1122334455987654345L;
	
	/** XÜO[vID */
	private String storeGroupId;
	
	/** ½f\èú */
	private String sgRefDate;
	
	/** XÜID */
	private String storeId;
	
	/** XÜ¼Ì */
	private String storeName;
	
	/** ¤LÎÛÒ[UID */
	private String shareTargetUserId;
	
	/** ¤LÎÛÒ[U¼ */
	private String shareTargetUserName;
	
	/** ÐõæªID */
	private String conditionPemployeeId;

	/** ÐõæªName */
	private String conditionPemployeeName;
	
	/** ÒWÂ\tO */
	private String authEditFlag;
	
	/** ðtO */
	private String inputFlag;
	
	/** VALUEl */
	private String value;
	
	/** Âlp_Äp */
	private String grouptype;
	
	/** XÜæªFLAG*/
	private String conditionFcFlag;

	public String getStoreGroupId() {
		return storeGroupId;
	}

	public void setStoreGroupId(String storeGroupId) {
		this.storeGroupId = storeGroupId;
	}

	public String getSgRefDate() {
		return sgRefDate;
	}

	public void setSgRefDate(String sgRefDate) {
		this.sgRefDate = sgRefDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getShareTargetUserId() {
		return shareTargetUserId;
	}

	public void setShareTargetUserId(String shareTargetUserId) {
		this.shareTargetUserId = shareTargetUserId;
	}

	public String getAuthEditFlag() {
		return authEditFlag;
	}

	public void setAuthEditFlag(String authEditFlag) {
		this.authEditFlag = authEditFlag;
	}

	public String getShareTargetUserName() {
		return shareTargetUserName;
	}

	public void setShareTargetUserName(String shareTargetUserName) {
		this.shareTargetUserName = shareTargetUserName;
	}

	public String getInputFlag() {
		return inputFlag;
	}

	public void setInputFlag(String inputFlag) {
		this.inputFlag = inputFlag;
	}


	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}

	public String getConditionPemployeeId() {
		return conditionPemployeeId;
	}

	public void setConditionPemployeeId(String conditionPemployeeId) {
		this.conditionPemployeeId = conditionPemployeeId;
	}

	public String getConditionPemployeeName() {
		return conditionPemployeeName;
	}

	public void setConditionPemployeeName(String conditionPemployeeName) {
		this.conditionPemployeeName = conditionPemployeeName;
	}

	public String getConditionFcFlag() {
		return conditionFcFlag;
	}

	public void setConditionFcFlag(String conditionFcFlag) {
		this.conditionFcFlag = conditionFcFlag;
	}
	
}