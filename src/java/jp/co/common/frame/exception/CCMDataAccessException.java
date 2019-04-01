/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.frame.exception;

/**
 * <B>[�N���X��]</B><UL>
 *    �f�[�^�A�N�Z�X��O�N���X
 * </UL><P>
 * <B>[����]</B><UL>
 *    �f�[�^�A�N�Z�X��O�N���X
 * </UL><BR>
 * <B>[�X�V����]</B>
 * </UL><P>
 * <LI>2006/12/27 1.0 Cocom H.Yatake �V�K�쐬
 * </UL>
 * @since JDK1.5.0
 */
public class CCMDataAccessException extends Exception {

	/** �G���[���b�Z�[�W�̃L�[ */
	private String key;

	/** �G���[���b�Z�[�W���ߍ��ݕ����� */
	private String[] values;

	/**
	 * �R���X�g���N�^
	 * @param key �G���[�R�[�h
	 */
	public CCMDataAccessException(String errCd) {
		this.key = errCd;

	}

	/**
	 * �R���X�g���N�^
	 * @param key �G���[�R�[�h
	 * @param values ���ߍ��ݕ�����

	 */
	public CCMDataAccessException(String errCd, String[] values) {

		this.key = errCd;
		this.values = values;
	}

	/**
	 * �R���X�g���N�^
	 * @param key �G���[�R�[�h
	 * @param ex ��O
	 */
	public CCMDataAccessException(String errCd, Exception ex) {
		super(ex);
		this.key = errCd;

	}

	/**
	 * �R���X�g���N�^
	 * @param key �G���[�R�[�h
	 * @param values ���ߍ��ݕ�����
	 * @param ex ��O
	 */
	public CCMDataAccessException(String errCd, String[] values, Exception ex) {

		super(ex);
		this.key = errCd;
		this.values = values;
	}

	/**
	 * �G���[�R�[�h�擾
	 * @return
	 */
	public String getKey() {
		return key;
	}

	/**
	 * �G���[���b�Z�[�W�����擾
	 * @return
	 */
	public String[] getValues() {
		return values;
	}

}
