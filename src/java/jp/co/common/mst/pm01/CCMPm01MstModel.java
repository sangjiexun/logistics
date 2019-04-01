/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common.mst.pm01;

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
public class CCMPm01MstModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8881183986784761854L;

	/**
	 * 
	 */

	/** �f�|�R�[�h */
	private String depoCd;
	
	/** �f�|�� */
	private String depoNm;
	
	/** �f�|�g�D�敪 */
	private String depoSoshikiKb;
	
	/** �f�|�Ǘ��� */
	private String depoKanrishaNm;
	
	/** �c�Ə��R�[�h */
	private String eigyoushoCd;
	
	/** �����n��敪 */
	private String areaKbn;
	
	/** �c�Ə��敪 */
	private String eigyoushoKb;
	
	/** �Ǘ�����Ћ敪 */
	private String kanrimotoKaisyaKb;

	/**
	 * �R���X�g���N�^
	 */
	public CCMPm01MstModel() {
		super();
		
	}

	/**
	 * �R���X�g���N�^
	 */
	public CCMPm01MstModel(String depoCd, String depoNm, String depoKanrishaNm, String eigyoushoCd, String areaKbn, String eigyoushoKb) {
		
		this.depoCd = depoCd;
		this.depoNm = depoNm;
		this.depoKanrishaNm = depoKanrishaNm;
		this.eigyoushoCd = eigyoushoCd;
		this.areaKbn = areaKbn;
		this.eigyoushoKb = eigyoushoKb;
		
	}	
	
	
	/**
	 * depoCd�̎擾
	 * @return depoCd
	 */
	public String getDepoCd() {
		return depoCd;
	}

	/**
	 * depoCd�̐ݒ�
	 * @param depoCd
	 */
	public void setDepoCd(String depoCd) {
		this.depoCd = depoCd;
	}

	/**
	 * depoKanrishaNm�̎擾
	 * @return depoKanrishaNm
	 */
	public String getDepoKanrishaNm() {
		return depoKanrishaNm;
	}

	/**
	 * depoKanrishaNm�̐ݒ�
	 * @param depoKanrishaNm
	 */
	public void setDepoKanrishaNm(String depoKanrishaNm) {
		this.depoKanrishaNm = depoKanrishaNm;
	}

	/**
	 * depoNm�̎擾
	 * @return depoNm
	 */
	public String getDepoNm() {
		return depoNm;
	}

	/**
	 * depoNm�̐ݒ�
	 * @param depoNm
	 */
	public void setDepoNm(String depoNm) {
		this.depoNm = depoNm;
	}

	/**
	 * eigyoushoCd�̎擾
	 * @return eigyoushoCd
	 */
	public String getEigyoushoCd() {
		return eigyoushoCd;
	}

	/**
	 * eigyoushoCd�̐ݒ�
	 * @param eigyoushoCd
	 */
	public void setEigyoushoCd(String eigyoushoCd) {
		this.eigyoushoCd = eigyoushoCd;
	}

	/**
	 * areaKbn�̎擾
	 * @return areaKbn
	 */
	public String getAreaKbn() {
		return areaKbn;
	}

	/**
	 * areaKbn�̐ݒ�
	 * @param areaKbn
	 */
	public void setAreaKbn(String areaKbn) {
		this.areaKbn = areaKbn;
	}

	/**
	 * eigyoushoKb�̎擾
	 * @return eigyoushoKb
	 */
	public String getEigyoushoKb() {
		return eigyoushoKb;
	}

	/**
	 * eigyoushoKb�̐ݒ�
	 * @param eigyoushoKb
	 */
	public void setEigyoushoKb(String eigyoushoKb) {
		this.eigyoushoKb = eigyoushoKb;
	}

	/**
	 * depoSoshikiKb�̎擾
	 * @return depoSoshikiKb
	 */
	public String getDepoSoshikiKb() {
		return depoSoshikiKb;
	}

	/**
	 * depoSoshikiKb�̐ݒ�
	 * @param depoSoshikiKb
	 */
	public void setDepoSoshikiKb(String depoSoshikiKb) {
		this.depoSoshikiKb = depoSoshikiKb;
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
