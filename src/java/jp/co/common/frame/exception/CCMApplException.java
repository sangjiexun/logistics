/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[�N���X��]</B><UL>
 *    �A�v���P�[�V������O�N���X
 * </UL><P>
 * <B>[����]</B><UL>
 *    �đ���\�ȃG���[�̏ꍇ�ɃX���[�����O�N���X�B
 * </UL><P>
 * <B>[�X�V����]</B>
 * <UL>
 * <LI>2006/08/15 1.0 Cocom H.Yatake �V�K�쐬
 * <LI>2006/11/24 1.1 Cocom H.Yatake ModuleExcedpiton�T�u�N���X�Ή�
 * <LI>2006/12/26 1.2 Cocom H.Yatake ModuleExcedpiton�̌p�������~��
 * </UL>
 * @since JDK1.5.0
 */
public class CCMApplException extends CCMDefaultException {

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param values ���b�Z�[�W���ߍ��ݕϐ�
	 * @param throwable ��O�e�N���X
	 */
	public CCMApplException(String key, Object[] values, Throwable throwable) {
		super(key, values, throwable);
		throw new UnsupportedOperationException("�p�@������Ă��܂��B��O��n���ꍇ��CCMBusinessException�̎g�p���������Ă��������B");

	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param values ���b�Z�[�W���ߍ��ݕϐ�
	 */
	public CCMApplException(String key, Object[] values) {
		super(key, values);

	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param throwable ��O�e�N���X
	 */
	public CCMApplException(String key, Throwable throwable) {
		super(key, throwable);
		throw new UnsupportedOperationException("�p�@������Ă��܂��B��O��n���ꍇ��CCMBusinessException�̎g�p���������Ă��������B");
	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 */
	public CCMApplException(String key) {
		super(key);

	}

	/**
	 * �R���X�g���N�^
	 */
	public CCMApplException() {
	}	
}
