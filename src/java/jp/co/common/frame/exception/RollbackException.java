package jp.co.common.frame.exception;

/**
 * �r�����䂪��������ꍇ�A���ُ̈��Throw
 */
public class RollbackException extends LocalRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4705619183782614192L;

	/**
	 * �R���X�g���N�^���\�b�h�B

	 *
	 */
	public RollbackException() {
		super();
	}

	/**
	 * �R���X�g���N�^���\�b�h�B

	 *
	 * @param msg �G���[������
	 */
	public RollbackException(String msg) {
		super(msg);
	}
}
