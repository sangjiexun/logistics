/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[�N���X��]</B><UL>
 *    �L�[�d����O�N���X
 * </UL><P>
 * <B>[����]</B><UL>
 *    �c�a�o�^���ɃL�[�d�������������ۂɃX���[������O
 * </UL><BR>
 * <B>[�X�V����]</B>
 * </UL><P>
 * <LI>2006/08/17 1.0 Cocom H.Yatake �V�K�쐬
 * </UL>
 * @since JDK1.5.0
 */
public class CCMKeyConflictException extends CCMDataAccessException {

	/**
	 * �R���X�g���N�^
	 * @param errCd
	 * @param ex
	 */
	public CCMKeyConflictException(String errCd, Exception ex) {
		super(errCd, ex);

	}

	/**
	 * �R���X�g���N�^
	 * @param errCd
	 * @param errArgs
	 * @param ex
	 */
	public CCMKeyConflictException(String errCd, String[] errArgs, Exception ex) {
		super(errCd, errArgs, ex);

	}

	/**
	 * �R���X�g���N�^
	 * @param errCd
	 * @param errArgs
	 */
	public CCMKeyConflictException(String errCd, String[] errArgs) {
		super(errCd, errArgs);

	}

	/**
	 * �R���X�g���N�^
	 * @param errCd
	 */
	public CCMKeyConflictException(String errCd) {
		super(errCd);

	}

}
