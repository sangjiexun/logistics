/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm43;

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
public class CCMPm43MstModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** ����R�[�h */
	private String bumonCd;
	
	/** �J�X�^�}�Z���^�� */
	private String custCenterNm;
		
	/** �J�X�^�}�Z���^���� */
	private String custCenterRm;
	
	/** �Ǘ�����Ћ敪 */
	private String kanrimotoKaisyaKb;

	/** ����敪 */
	private String bumonKb;
	
	/**
	 * bumonKb�̎擾
	 * @return bumonKb
	 */
	public String getBumonKb() {
		return bumonKb;
	}

	/**
	 * bumonKb�̐ݒ�
	 * @param bumonKb
	 */
	public void setBumonKb(String bumonKb) {
		this.bumonKb = bumonKb;
	}

	/**
	 * bumonCd�̎擾
	 * @return bumonCd
	 */
	public String getBumonCd() {
		return bumonCd;
	}

	/**
	 * bumonCd�̐ݒ�
	 * @param bumonCd
	 */
	public void setBumonCd(String bumonCd) {
		this.bumonCd = bumonCd;
	}

	/**
	 * custCenterNm�̎擾
	 * @return custCenterNm
	 */
	public String getCustCenterNm() {
		return custCenterNm;
	}

	/**
	 * custCenterNm�̐ݒ�
	 * @param custCenterNm
	 */
	public void setCustCenterNm(String custCenterNm) {
		this.custCenterNm = custCenterNm;
	}

	/**
	 * custCenterRm�̎擾
	 * @return custCenterRm
	 */
	public String getCustCenterRm() {
		return custCenterRm;
	}

	/**
	 * custCenterRm�̐ݒ�
	 * @param custCenterRm
	 */
	public void setCustCenterRm(String custCenterRm) {
		this.custCenterRm = custCenterRm;
	}

	/**
	 * kanrimotoKaisyaKb�̎擾
	 * @return kanrimotoKaisyaKb
	 */
	public String getKanrimotoKaisyaKb() {
		return kanrimotoKaisyaKb;
	}

	/**
	 * kanrimotoKaisyaKb�̐ݒ�
	 * @param kanrimotoKaisyaKb
	 */
	public void setKanrimotoKaisyaKb(String kanrimotoKaisyaKb) {
		this.kanrimotoKaisyaKb = kanrimotoKaisyaKb;
	}


	
}
