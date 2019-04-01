package jp.co.common.frame.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.common.frame.exception.BusinessServiceException;
import jp.co.common.frame.util.prop.MessagePropertyUtil;

/**
 * 
 * �v���W�F�N�g�̃r�W�l�X���W�b�N�̋�̂Ȏ���<br>
 * 
 * �ȉ��̋@�\���T�|�[�g����B<br>
 * 
 * <ul>
 *  <li>�Ɩ����b�Z�[�W�ǉ�</li>
 *  <li>�Ɩ����b�Z�[�W���擾</li>
 *  <li>�Ɩ����b�Z�[�WThrow</li>
 * </ul>
 * 
 * @author  NTS
 * @version 1.0
 */
public class BaseBusinessService  {
	
	//�Ɩ��ُ�N���X
	protected BusinessServiceException businessServiceEx = new BusinessServiceException();;
	
	/**
	 * @return the userInfoBean
	 */
	public LoginUserBean getLoginUserBean() {
		//�Z�b�V�������烆�[�U�[�����擾
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
        HttpSession session = request.getSession();  
        return (LoginUserBean)session.getAttribute(ConstantContainer.LOGIN_USER_KEY);
	}
	
	/**
	 * �Ɩ����b�Z�[�W�ǉ�
	 * @param messageID
	 */
	public void addBusinessMessage(String fieldID, String messageID, String replaceValue[]) {		
		businessServiceEx.addErrorID(fieldID,messageID, replaceValue);
	}
	
	/**
	 * �Ɩ����b�Z�[�W�ǉ�
	 * @param messageID
	 */
	public void addBusinessMessage(String messageID, String replaceValue[]) {		
		this.addBusinessMessage("",messageID, replaceValue);
	}

	/**
	 * �Ɩ����b�Z�[�W�ǉ�
	 * @param messageID
	 */
	public void addBusinessMessage(String fieldID, String messageID) {		
		businessServiceEx.addErrorID(fieldID, messageID);
	}

	/**
	 * �Ɩ����b�Z�[�W�ǉ�
	 * @param messageID
	 */
	public void addBusinessMessage(String messageID) {		
		addBusinessMessage("", messageID);
	}

	/**
	 * �Ɩ����b�Z�[�W��ݒ�
	 * @param businessMessageList
	 */
	public void setBusinessMessageList(List<String> businessMessageList) {		
		businessServiceEx.addErrorID(businessMessageList);
	}

	public BusinessServiceException getBusinessServiceException() {		
		return businessServiceEx;
	}
	
	public boolean isThrowBusinsessException(){
		if(this.businessServiceEx.getErrorIdList() != null 
				&& this.businessServiceEx.getErrorIdList().size() > 0){
			return true;
		}else{
			return false;
		}
	}
	
	public void throwBusinsessExcepton()throws BusinessServiceException{
		if(isThrowBusinsessException()){
			throw businessServiceEx;
		}
	}
	
	public String getErrorMessage(String errorId, String replaceValue[]) {
		String ret = MessagePropertyUtil.getStringProperty(errorId);
		for (int i=0; i<replaceValue.length; i++) {
			ret = ret.replaceAll("\\{"+i+"\\}", replaceValue[i]);
		}
		return ret;
	}
}
