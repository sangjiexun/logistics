/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS LOGISTICS CORPORATION, All rights reserved.
 */
package jp.co.plc.pl1.c0130;

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
public class PLC1C0130Model {

	private String searchFlg;
	private String returnFlg;
	
	private String deleteFlg;
	
	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	/**
	 * @return the returnFlg
	 */
	public String getReturnFlg() {
		return returnFlg;
	}

	/**
	 * @param returnFlg the returnFlg to set
	 */
	public void setReturnFlg(String returnFlg) {
		this.returnFlg = returnFlg;
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

	private String eigyoushoCd;
	private String eigyoushoNm;
	
	/**
	 * @return the eigyoushoNm
	 */
	public String getEigyoushoNm() {
		return eigyoushoNm;
	}

	/**
	 * @param eigyoushoNm the eigyoushoNm to set
	 */
	public void setEigyoushoNm(String eigyoushoNm) {
		this.eigyoushoNm = eigyoushoNm;
	}

	private List eigyoushoCdItems;
	

	/**
	 * @return the eigyoushoCdItems
	 */
	public List getEigyoushoCdItems() {
		return eigyoushoCdItems;
	}

	/**
	 * @param eigyoushoCdItems the eigyoushoCdItems to set
	 */
	public void setEigyoushoCdItems(List eigyoushoCdItems) {
		this.eigyoushoCdItems = eigyoushoCdItems;
	}

	private String syozokuchiikiKb;
	private List syozokuchiikiKbItems;
	

	/**
	 * @return the syozokuchiikiKbItems
	 */
	public List getSyozokuchiikiKbItems() {
		return syozokuchiikiKbItems;
	}

	/**
	 * @param syozokuchiikiKbItems the syozokuchiikiKbItems to set
	 */
	public void setSyozokuchiikiKbItems(List syozokuchiikiKbItems) {
		this.syozokuchiikiKbItems = syozokuchiikiKbItems;
	}

	private List kanrimotoKaisyaKbItems;

	/**
	 * @return the kanrimotoKaisyaKbItems
	 */
	public List getKanrimotoKaisyaKbItems() {
		return kanrimotoKaisyaKbItems;
	}

	/**
	 * @param kanrimotoKaisyaKbItems the kanrimotoKaisyaKbItems to set
	 */
	public void setKanrimotoKaisyaKbItems(List kanrimotoKaisyaKbItems) {
		this.kanrimotoKaisyaKbItems = kanrimotoKaisyaKbItems;
	}

	private String depoCd;
	private String depoNm;
	private String denwaNo;
	private String depoSoshikiKb;
	
	/**
	 * @return the denwaNo
	 */
	public String getDenwaNo() {
		return denwaNo;
	}

	/**
	 * @param denwaNo the denwaNo to set
	 */
	public void setDenwaNo(String denwaNo) {
		this.denwaNo = denwaNo;
	}

	/**
	 * @return the depoSoshikiKb
	 */
	public String getDepoSoshikiKb() {
		return depoSoshikiKb;
	}

	/**
	 * @param depoSoshikiKb the depoSoshikiKb to set
	 */
	public void setDepoSoshikiKb(String depoSoshikiKb) {
		this.depoSoshikiKb = depoSoshikiKb;
	}

	/**
	 * @return the depoNm
	 */
	public String getDepoNm() {
		return depoNm;
	}

	/**
	 * @param depoNm the depoNm to set
	 */
	public void setDepoNm(String depoNm) {
		this.depoNm = depoNm;
	}

	private String sakujoKb;

	private String retrunFlg;

	private String syoriFlg;
	
	/** �Ǘ�����Ћ敪 */
	private String kanrimotoKaisyaKb;	

	/** �I���s�ԍ� */
	private String lineNo;
	
	/** �ꗗ���X�g */
	private List meisaiList;

//	// �폜�敪�̃��Z�b�g�i�`�F�b�N�{�b�N�X�̃N���A �j
//	public void reset(ActionMapping mapping, HttpServletRequest request) {
//
//		setSakujoKb(CCMCMConsts.SAKUJO_KB.YUKOU);
//
//	}

	/**
	 * depoCd�̎擾
	 * 
	 * @return depoCd
	 */
	public String getDepoCd() {
		return depoCd;
	}

	/**
	 * depoCd�̐ݒ�
	 * 
	 * @param depoCd
	 */
	public void setDepoCd(String depoCd) {
		this.depoCd = depoCd;
	}

	/**
	 * eigyoushoCd�̎擾
	 * 
	 * @return eigyoushoCd
	 */
	public String getEigyoushoCd() {
		return eigyoushoCd;
	}

	/**
	 * eigyoushoCd�̐ݒ�
	 * 
	 * @param eigyoushoCd
	 */
	public void setEigyoushoCd(String eigyoushoCd) {
		this.eigyoushoCd = eigyoushoCd;
	}

	/**
	 * meisaiList�̎擾
	 * 
	 * @return meisaiList
	 */
	public List getMeisaiList() {
		return meisaiList;
	}

	/**
	 * meisaiList�̐ݒ�
	 * 
	 * @param meisaiList
	 */
	public void setMeisaiList(List meisaiList) {
		this.meisaiList = meisaiList;
	}

	/**
	 * sakujoKb�̎擾
	 * 
	 * @return sakujoKb
	 */
	public String getSakujoKb() {
		return sakujoKb;
	}

	/**
	 * sakujoKb�̐ݒ�
	 * 
	 * @param sakujoKb
	 */
	public void setSakujoKb(String sakujoKb) {
		this.sakujoKb = sakujoKb;
	}

	/**
	 * syozokuchiikiKb�̎擾
	 * 
	 * @return syozokuchiikiKb
	 */
	public String getSyozokuchiikiKb() {
		return syozokuchiikiKb;
	}

	/**
	 * syozokuchiikiKb�̐ݒ�
	 * 
	 * @param syozokuchiikiKb
	 */
	public void setSyozokuchiikiKb(String syozokuchiikiKb) {
		this.syozokuchiikiKb = syozokuchiikiKb;
	}

	/**
	 * lineNo�̎擾
	 * 
	 * @return lineNo
	 */
	public String getLineNo() {
		return lineNo;
	}

	/**
	 * lineNo�̐ݒ�
	 * 
	 * @param lineNo
	 */
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	/**
	 * syoriFlg�̎擾
	 * 
	 * @return syoriFlg
	 */
	public String getSyoriFlg() {
		return syoriFlg;
	}

	/**
	 * syoriFlg�̐ݒ�
	 * 
	 * @param syoriFlg
	 */
	public void setSyoriFlg(String syoriFlg) {
		this.syoriFlg = syoriFlg;
	}

	/**
	 * retrunFlg�̎擾
	 * 
	 * @return retrunFlg
	 */
	public String getRetrunFlg() {
		return retrunFlg;
	}

	/**
	 * retrunFlg�̐ݒ�
	 * 
	 * @param retrunFlg
	 */
	public void setRetrunFlg(String retrunFlg) {
		this.retrunFlg = retrunFlg;
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
