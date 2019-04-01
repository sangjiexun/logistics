/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS LOGISTICS CORPORATION, All rights reserved.
 */
package jp.co.plc.pl1.h0020;

import java.math.BigDecimal;
import java.util.List;

/**
 * <B>[�N���X��]</B>
 * <UL>
 * �f�|���ꗗ��ʁE�A�N�V�����t�H�[��
 * 
 * </UL>
 * <P>
 * <B>[����]</B>
 * <UL>
 * �f�|���ꗗ��ʂ̃g�b�v���x���̃A�N�V�����t�H�[��
 * 
 * </UL>
 * <BR>
 * <B>[�X�V����]</B>
 * </UL>
 * <P>
 * <LI>2007/01/01 1.0 Cocom K.Yoshihashi �V�K�쐬
 * </UL>
 * 
 * @since JDK1.5.0
 */
@SuppressWarnings("rawtypes")
public class PLC1H0020Model {

	private String searchFlg;
	private String gyousyaCd;

	private String gyousyaNm;

	/** �ꗗ���X�g */
	private List meisaiList;
//  *START ** 20090127 ***
	/**  �Ǘ�����Ћ敪 */
	private String kanrimotoKaisyaKb;
	
	private String kanrimotoFlg;
	
	private List kanrimotoFlgItems;

	public List getKanrimotoFlgItems() {
		return kanrimotoFlgItems;
	}

	public void setKanrimotoFlgItems(List kanrimotoFlgItems) {
		this.kanrimotoFlgItems = kanrimotoFlgItems;
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
//  *END   ** 20090127 ***	
	/**
	 * gyousyaCd�̎擾
	 * @return gyousyaCd
	 */
	public String getGyousyaCd() {
		return gyousyaCd;
	}

	/**
	 * gyousyaCd�̐ݒ�
	 * @param gyousyaCd
	 */
	public void setGyousyaCd(String gyousyaCd) {
		this.gyousyaCd = gyousyaCd;
	}

	/**
	 * gyousyaNm�̎擾
	 * @return gyousyaNm
	 */
	public String getGyousyaNm() {
		return gyousyaNm;
	}
	/**
	 * gyousyaNm�̐ݒ�
	 * @param gyousyaNm
	 */
	public void setGyousyaNm(String gyousyaNm) {
		this.gyousyaNm = gyousyaNm;
	}
	/**
	 * meisaiList�̎擾
	 * @return meisaiList
	 */
	public List getMeisaiList() {
		return meisaiList;
	}

	/**
	 * meisaiList�̐ݒ�
	 * @param meisaiList
	 */
	public void setMeisaiList(List meisaiList) {
		this.meisaiList = meisaiList;
	}

	/**
	 * kanrimotoFlg�̎擾
	 * @return kanrimotoFlg
	 */
	public String getKanrimotoFlg() {
		return kanrimotoFlg;
	}

	/**
	 * kanrimotoFlg�̐ݒ�
	 * @param kanrimotoFlg
	 */
	public void setKanrimotoFlg(String kanrimotoFlg) {
		this.kanrimotoFlg = kanrimotoFlg;
	}

	/**
	 * @return the searchFlg
	 */
	public String getSearchFlg() {
		return searchFlg;
	}

	/**
	 * @param searchFlg the searchFlg to set
	 */
	public void setSearchFlg(String searchFlg) {
		this.searchFlg = searchFlg;
	}

}
