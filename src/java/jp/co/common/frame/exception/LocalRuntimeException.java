package jp.co.common.frame.exception;

import jp.co.common.frame.util.prop.MessagePropertyUtil;
import jp.co.common.frame.util.DateUtil;
/**
 * �V�X�e�����x���ُ̈�N���X�B
 * ����Ȉُ킪��������ꍇ�A�G���[�y�[�W�ɃG���[ID�ƃG���[���b�Z�[�W�Ɣ������Ԃ���ʂɕ\��
 * �ȉ��̋@�\���T�|�[�g����B<br>
 * <ul>
 *  <li>BaseRuntimeException���p��</li>
 *  <li>�G���[ID�ݒ�</li>
 *  <li>�G���[���b�Z�[�W�ݒ�</li>
 *  <li>�G���[�������� �ݒ�</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 * 
 */
public class LocalRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	/** �G���[ID */
	private String errorID;
	/** �G���[���b�Z�[�W */
	private String errorMessage;
	/** �G���[�R�����g(�J������ۃG���[ID�����邾���ŁA�w���Ӗ���������ɂ����Ȃ̂ŁA���̃t�B�[���h��ǉ��A�������Ă�����) */
	private String errorComment;
	/** �G���[�������� */
	private String errorTime = DateUtil.formatDateToSec(DateUtil.getNowTime(), "yyyy/MM/dd HH:mm:ss");

	/**
	 * �R���X�g���N�^���\�b�h�B
	 */
	public LocalRuntimeException() {
		super();
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 *
	 * @param msg �G���[�R�[�h������
	 */
	public LocalRuntimeException(String errorID, String errorComment) {
		super(errorID);
		this.errorID = errorID;
		this.errorComment = errorComment;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 *
	 * @param msg �G���[�R�[�h������
	 */
	public LocalRuntimeException(String errorID) {
		super(errorID);
		this.errorID = errorID;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 *
	 * @param msg   �G���[�R�[�h������
	 * @param cause �������Ă�ُ�
	 */
	public LocalRuntimeException(String errorID, Throwable cause) {
		super(errorID, cause);
		this.errorID = errorID;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 *
	 * @param msg   �G���[�R�[�h������
	 * @param cause �������Ă�ُ�
	 */
	public LocalRuntimeException(String errorID, String errorComment, Throwable cause) {
		super(errorID, cause);
		this.errorID = errorID;
		this.errorComment = errorComment;
		errorMessage = MessagePropertyUtil.getInstance().getEncodeProperty(errorID);
	}
	
	/**
	 * �������Ă�ُ�iThrowable�j��Ԃ�
	 */
	public Throwable getCause() {
		return super.getCause();
	}

	public String getErrorComment() {
		return errorComment;
	}

	public void setErrorComment(String errorComment) {
		this.errorComment = errorComment;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorID() {
		return errorID;
	}

	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}

	public String getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(String errorTime) {
		this.errorTime = errorTime;
	}
}
