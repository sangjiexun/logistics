package jp.co.common.frame.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ʃG���[Exception
 * �G���[���������ꍇ�A���ʃG���[��ʂ֑J�ڂ����ꍇ�A���ُ̈��Throw
 */
public class CommonErrorPageException extends LocalRuntimeException {

	private static final long serialVersionUID = 1L;
	/** �G���[ID���X�g */
	private List<String> errorIDList = new ArrayList<String>();
	/** �G���[���b�Z�[�W���X�g */
	private List<String> errorMessageList = new ArrayList<String>();
	/**
	 * �R���X�g���N�^���\�b�h�B
	 */
	public CommonErrorPageException() {
		super();
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 * @param msg �G���[������
	 */
	public CommonErrorPageException(String msg) {
		super(msg);
	}

	public List<String> getErrorIDList() {
		return errorIDList;
	}

	public void setErrorIDList(List<String> errorIDList) {
		this.errorIDList = errorIDList;
	}

	public List<String> getErrorMessageList() {
		return errorMessageList;
	}

	public void setErrorMessageList(List<String> errorMessageList) {
		this.errorMessageList = errorMessageList;
	}
}