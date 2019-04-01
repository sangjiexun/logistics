package jp.co.common.frame.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import jp.co.common.frame.util.DateUtil;
/**
 *  �Ɩ����b�Z�[�W�ُ�
 * <pre>
   *  �@ �Ɩ����b�Z�[�W����������ꍇ�A���ُ̈��Throw
 *  �ȉ��̋@�\���T�|�[�g����B
 * </pre>
 * <ul>
 *   <li>�Ɩ����b�Z�[�W��ǉ�</li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class BusinessServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/** �G���[�t�B�[���hID */
	private List<String> errorField;
	/** �G���[ID */
	private List<String> errorID;
	/** �G���[���b�Z�[�W */
	private List<String> errorMessageList;
	
	public BusinessServiceException() {
		super();
	}

	public BusinessServiceException(String errorID) {
		super(errorID);
		this.addErrorID("", errorID);
	}

	public BusinessServiceException(String errorID, String replaceValue[]) {
		super(errorID);
		this.addErrorID("", errorID, replaceValue);
	}


	public void addErrorID(String errorFieldId, String errorId){
		this.addErrorID(errorFieldId, errorId, new String[] {});
	}	
	
	public void addErrorID(String errorFieldId, String errorId, String replaceValue[]){
		if(this.errorField == null){
			this.errorField = new ArrayList<String>();
		}
		if(this.errorID == null)
			this.errorID = new ArrayList<String>();
		if(this.errorMessageList == null) {
			this.errorMessageList = new ArrayList<String>();
		}
		if(!errorID.contains(errorId)){
			this.errorField.add(errorFieldId);
			this.errorID.add(errorId);
			this.errorMessageList.add(getErrorMessage(errorId, replaceValue));
		}
	}
	
	public String getErrorMessage(String errorId, String replaceValue[]) {		
		ResourceBundle bundle = ResourceBundle.getBundle("message",Locale.JAPAN);
		String ret = bundle.getString(errorId);
		for (int i=0; i<replaceValue.length; i++) {
			ret = ret.replaceAll("\\{"+i+"\\}", replaceValue[i]);
		}
		return ret;
	}
	
	public void addErrorID(List<String> errorIds){
		for(String strId : errorIds){
			this.addErrorID("", strId);
		}		
	}
	
	public List<String> getErrorIdList(){
		return this.errorID;
	}
	
	public List<String> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}

	public List<String> getErrorFieldList(){
		return this.errorField;
	}
}
