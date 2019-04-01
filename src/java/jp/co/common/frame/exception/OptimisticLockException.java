package jp.co.common.frame.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * �r�����䂪��������ꍇ�A���ُ̈��Throw
 */
public class OptimisticLockException extends LocalRuntimeException {

	private static final long serialVersionUID = 1L;
	/** �G���[ID���X�g */
	private List<String> errorIDList = new ArrayList<String>();
	/** �G���[���b�Z�[�W���X�g */
	private List<String> errorMessageList = new ArrayList<String>();
	/**
	 * �R���X�g���N�^���\�b�h�B
	 */
	public OptimisticLockException() {
		super();
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 * @param msg �G���[������
	 */
	public OptimisticLockException(String msg) {
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