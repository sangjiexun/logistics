/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm28;

import java.io.Serializable;

/**
 * <DT><B>[�N���X��]</B><UL>
 *    �f�|�}�X�^�p���x���o�����[�u�n
 * </UL><P>
 * <DT><B>[����]</B>
 * <UL>
 *    �f�|�}�X�^�̃��x���ƃR�[�h�̑g�ݍ��킹��\���u�n
 * </UL><P>
 * <DT><B>[�X�V����]</B><UL>
 * <LI>2007/01/11 1.0 Cocom T.Satoh �V�K�쐬
 * </UL><P>
 * @since JDK1.5.0
 */
public class CCMPm28MstModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** �R�[�h */
	private String codeId;
	/** ���x�� */
	private String fullName;
	/** �Z�k���x�� */
	private String shortName;
	/** �T�u�R�[�h�P **/
	private String subCd1;
	/** �T�u�R�[�h�Q **/
	private String subCd2;	
	
	/**
	 * subCd1�̎擾
	 * @return subCd1
	 */
	public String getSubCd1() {
		return subCd1;
	}
	/**
	 * subCd2�̎擾
	 * @return subCd2
	 */
	public String getSubCd2() {
		return subCd2;
	}
	/**
	 * subCd1�̐ݒ�

	 * @param subCd1
	 */
	public void setSubCd1(String subCd1) {
		this.subCd1 = subCd1;
	}
	/**
	 * subCd2�̐ݒ�

	 * @param subCd2
	 */
	public void setSubCd2(String subCd2) {
		this.subCd2 = subCd2;
	}
	/**
	 * fullName�̎擾
	 * @return fullName
	 */
	public final String getFullName() {
		return fullName;
	}
	/**
	 * fullName�̐ݒ�

	 * @param fullName
	 */
	public final void setFullName(String fullName) {
		this.fullName = fullName;
	}
	/**
	 * shortName�̎擾
	 * @return shortName
	 */
	public final String getShortName() {
		return shortName;
	}
	/**
	 * shortName�̐ݒ�

	 * @param shortName
	 */
	public final void setShortName(String shortName) {
		this.shortName = shortName;
	}
	/**
	 * value�̎擾
	 * @return codeId
	 */
	public final String getCodeId() {
		return codeId;
	}
	/**
	 * value�̐ݒ�

	 * @param codeId
	 */
	public final void setCodeId(String value) {
		this.codeId = value;
	}
	
}
