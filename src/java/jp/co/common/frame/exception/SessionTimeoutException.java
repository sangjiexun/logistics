package jp.co.common.frame.exception;

/**
 * �A�v���P�[�V�����Őݒ肵���^�C���A�E�g���Ԃ�������ꍇ�A
 * ���ُ̈��Throw
 * 
 * File Name: SessionTimeoutException.java 
 * Created: 2011/04/11 
 * Original Author: * yjl(NTS) 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2011/04/11		   yjl(NTS)        �쐬
 *
 **/
public class SessionTimeoutException extends Exception {

	private static final long serialVersionUID = -597235394269425096L;

	/**
	 * �R���X�g���N�^���\�b�h�B
	 *
	 */
	public SessionTimeoutException() {
		super();
	}

	/**
	 * �R���X�g���N�^���\�b�h�B
	 *
	 * @param msg �G���[������
	 */
	public SessionTimeoutException(String msg) {
		super(msg);
	}
}