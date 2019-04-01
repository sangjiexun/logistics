/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;


/**
 * <B>[�N���X��]</B><UL>
 *    ����O�N���X
 * </UL><P>
 * <B>[����]</B><UL>
 *    �S�Ă̗�O�N���X�̊��N���X<BR>
 *    �S�Ă̗�O�N���X�͓��N���X�̃T�u�N���X�Ƃ��č쐬����B
 * </UL><P>
 * <B>[�X�V����]</B>
 * <UL>
 * <LI>2006/08/15 1.0 Cocom H.Yatake �V�K�쐬
 * <LI>2006/11/24 1.1 Cocom H.Yatake ModuleExcedpiton�T�u�N���X�Ή�
 * <LI>2006/12/26 1.2 Cocom H.Yatake ModuleExcedpiton�̌p�������~��
 * </UL>
 * @since JDK1.5.0
 */
public abstract class CCMDefaultException extends Exception {

	/** ���b�Z�[�W�L�[ */
	private String key;

	/** ���b�Z�[�W���ߍ��ݕϐ� */
	private Object[] values;

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param values ���b�Z�[�W���ߍ��ݕϐ�
	 */
	public CCMDefaultException(String key, Object[] values) {
		this.key = key;
		this.values = values;
	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 */
	public CCMDefaultException(String key) {
		this.key = key;
	}
	
	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param values ���b�Z�[�W���ߍ��ݕϐ�
	 * @param throwable ��O�e�N���X
	 */
	public CCMDefaultException(String key, Object[] values, Throwable throwable) {
		super(throwable);
		this.key = key;
		this.values = values;
	}

	/**
	 * �R���X�g���N�^
	 * @param key ���b�Z�[�W�L�[
	 * @param throwable ��O�e�N���X
	 */
	public CCMDefaultException(String key, Throwable throwable) {
		super(throwable);
		this.key = key;
	}

	/**
	 * �R���X�g���N�^
	 */
	public CCMDefaultException() {
	}	
	
	/**
	 * ���b�Z�[�W�L�[�̎擾
	 * @return key ���b�Z�[�W�L�[
	 */
	public String getKey() {
		return key;
	}

	/**
	 * ���b�Z�[�W���ߍ��ݕϐ��̎擾
	 * @return values ���b�Z�[�W���ߍ��ݕϐ�
	 */
	public Object[] getValues() {
		return values;
	}
	
	
	
}
