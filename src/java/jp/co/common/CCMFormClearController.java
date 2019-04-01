/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.plc.pl1.c0120.PLC1C0120Model;
import jp.co.plc.pl1.c0130.PLC1C0130Model;

/**
 * <DT><B>[�N���X��]</B><UL>
 *    Commons BeanUtils ���b�p�[�N���X
 * </UL><P>
 * <DT><B>[����]</B>
 * <UL>
 *    Commons BeanUtils ���b�p�[�N���X�B
 *    �Ƃ肠�����K�v�����Ȃ��̂̂ݎ����B
 *    �I�u�W�F�N�g�Ԃ̃v���p�e�B�̃R�s�[�ɂ͕K���₤�N���X���g�p����B
 *    �v���p�e�B�̃R�s�[���ɔ���������O�͑S�ăL���b�`���邩�ǂ����͌���
 * </UL><P>
 * <DT><B>[�X�V����]</B><UL>
 * <LI>2006/08/21 1.0 Cocom H.Yatake �V�K�쐬
 * </UL><P>
 * @since JDK1.5.0
 */
@Controller
@RequestMapping("/back")
public class CCMFormClearController {


	/* (non-Javadoc)
	 * @see jp.co.cocom.frame.CCMDefaultAction#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, jp.co.cocom.frame.CCMTrxFolder)
	 */
	@PostMapping("/back")
	public String back(@ModelAttribute("c0120model") PLC1C0120Model c0130model, Model model) throws Exception {
//		// �A�N�V�����t�H�[����錾
//		CCMDefaultActionForm aForm = (CCMDefaultActionForm)form;
//		
//		// �J�ڌ����ID���擾
//		String prevPage = aForm.getPrevPageId();
//		
//		// �J�ڌ����ݒ肳��Ă��邩����
//
//		if (CCMValidateUtils.isNullOrBlank(prevPage)) {
//			// �ݒ肳��Ă��Ȃ��ꍇ�́A�f�t�H���g���Z�b�g
//			prevPage = CCMConstants.FORWAD_KEY.SUCCESS;
//		}
//
//		//�Z�b�V���������A���g�̃A�N�V�����t�H�[�����폜
//		removeActionForm(mapping, request);

		model.addAttribute("c0130model", c0130model);
		
		// �߂�
		return "plc1/c0130";
	}

}
