/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[�N���X��]</B><UL>
 *    �V�X�e����O�N���X
 * </UL><P>
 * <B>[����]</B><UL>
 *    �����𑱍s�ł��Ȃ��ꍇ�ɃX���[�����O�N���X
 * </UL><P>
 * <B>[�X�V����]</B>
 * <UL>
 * <LI>2006/08/15 1.0 Cocom H.Yatake �V�K�쐬
 * <LI>2006/11/24 1.1 Cocom H.Yatake ModuleExcedpiton�T�u�N���X�Ή�
 * <LI>2006/12/26 1.2 Cocom H.Yatake ModuleExcedpiton�̌p�������~�� 
 * </UL>
 * @since JDK1.5.0
 */
public class CCMSystemException extends CCMDefaultException {

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param values ���b�Z�[�W���ߍ��ݕϐ�
	 * @param throwable ��O�e�N���X
	 */
	public CCMSystemException(String key, Object[] values, Throwable throwable) {
		super(key, values, throwable);

	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param values ���b�Z�[�W���ߍ��ݕϐ�
	 */
	public CCMSystemException(String key, Object[] values) {
		super(key, values);

	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param throwable ��O�e�N���X
	 */
	public CCMSystemException(String key, Throwable throwable) {
		super(key, throwable);

	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 */
	public CCMSystemException(String key) {
		super(key);

	}

	
}
