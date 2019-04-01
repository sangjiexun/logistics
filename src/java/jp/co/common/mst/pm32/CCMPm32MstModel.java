/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm32;

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
public class CCMPm32MstModel implements Serializable {
	
	/** ����R�[�h */
	private String bumonCd;
	
	/** ���喼 */
	private String bumonNm;
	
	/** ��v����R�[�h */
	private String kaikeiBumonCd;
	
	/** ��v���喼 */
	private String kaikeiBumonNm;

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
	 * bumonNm�̎擾
	 * @return bumonNm
	 */
	public String getBumonNm() {
		return bumonNm;
	}

	/**
	 * bumonNm�̐ݒ�
	 * @param bumonNm
	 */
	public void setBumonNm(String bumonNm) {
		this.bumonNm = bumonNm;
	}

	/**
	 * kaikeiBumonCd�̎擾
	 * @return kaikeiBumonCd
	 */
	public String getKaikeiBumonCd() {
		return kaikeiBumonCd;
	}

	/**
	 * kaikeiBumonCd�̐ݒ�
	 * @param kaikeiBumonCd
	 */
	public void setKaikeiBumonCd(String kaikeiBumonCd) {
		this.kaikeiBumonCd = kaikeiBumonCd;
	}

	/**
	 * kaikeiBumonNm�̎擾
	 * @return kaikeiBumonNm
	 */
	public String getKaikeiBumonNm() {
		return kaikeiBumonNm;
	}

	/**
	 * kaikeiBumonNm�̐ݒ�
	 * @param kaikeiBumonNm
	 */
	public void setKaikeiBumonNm(String kaikeiBumonNm) {
		this.kaikeiBumonNm = kaikeiBumonNm;
	}
	
	
}
