package jp.co.fourseeds.fsnet.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.ChildPageFormBean;
import jp.co.fourseeds.fsnet.beans.page.ChildPageInheritSetBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentAdminBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentMailFormBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentRateBean;
import jp.co.fourseeds.fsnet.beans.page.PageDeleteBean;
import jp.co.fourseeds.fsnet.beans.page.PageFormBean;
import jp.co.fourseeds.fsnet.beans.page.PageLinkBean;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.page.PageUserRateBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageDao;
import jp.co.fourseeds.fsnet.dao.PageDownloadDao;
import jp.co.fourseeds.fsnet.dao.TemplatePageDao;

import jp.co.common.frame.beans.BaseBean;
import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.CommonDao;
import jp.co.common.frame.exception.BusinessServiceException;
import jp.co.common.frame.exception.CommonErrorPageException;
import jp.co.common.frame.exception.LocalRuntimeException;
import jp.co.common.frame.exception.OptimisticLockException;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.WrappedBeanUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * �R���e���c���@�\�T�[�r�X�����N���X 
 * 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@VersionWhen	  Who	  Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		 NTS	 		�쐬
 *
 **/
@SuppressWarnings(value={"rawtypes","unchecked"})
@Component
public class PageService extends BaseBusinessService{
	
	@Autowired
	private PageDao pageDao;
	@Autowired
	private PageDownloadDao pageDownloadDao;
	@Autowired
	private CommonDao commonDao;
	@Autowired
	private TemplatePageDao templatePageDao;
	@Autowired
	private TemplatePageService templatePageService;
	
	/**
	 * �R���e���c�o�^�̏ꍇ�A��ʂ̏�����
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	public void init(PageFormBean formBean) throws Exception {
		// �ΏێҁA���M�����h���b�v�_�E�����X�g���쐬
		getDropdownList(formBean);
		// �R���e���c�]���t���O���u0:���Ȃ��v
		formBean.setEvaluationFlag("0");
		// �y�[�W�\���t���O���uADD�v
		formBean.setPageViewFlag("ADD");
		// �]���Ҏ����\���t���O
		formBean.setEvaluatorDisplayFlag("1");
		// �]���҃R�����g�ҏW�t���O
		formBean.setCommentEditFlag("1");
		// �������ڕ]���t���O
		formBean.setPluralEvaluationFlag("0");
		// �z�u��ݒ�t���O
		formBean.setPageLocationSetFlag("0");
		// �ΏێґI���敪��ݒ�
		setUserType(formBean);
		// ���񍐑Ή��҃��X�g��ݒ�
		setPageCommentAdminList(formBean, getLoginUserBean().getUserId());
	}
	
	/**
	 * �e���v���[�g�R���e���c�쐬������
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	public void templateInit(PageFormBean formBean) throws Exception {
		// �e���v���[�g�y�[�WID
		String templatePageId = formBean.getTemplatePageId();
		// �e���v���[�g�e���v���[�g�R���e���c���̎擾
		PageFormBean pageFormBean = templatePageService.getTemplatePageForm(templatePageId);
		// �e���v���[�g�R���e���c��񂪑��݂��ĂȂ��ꍇ�A�ȍ~�̏����𒆎~����B
		if (pageFormBean == null) {
			// �G���[��ʂ֑J��
			optimisticLockCheck(templatePageId, "2");
		}
		
		// �ΏێҁA���M�����h���b�v�_�E�����X�g���쐬
		getDropdownList(formBean);
		// ���J�m�F���t��X�V�t���O(���J���t�ύX�Ȃ�)�\���t���O
		formBean.setViewConfirmNoupdateFlag("0");
		// ���J����t��X�V�t���O
		formBean.setConfirmNoupdateFlag("0");
		// �u�e���v���[�g����v�敪
		formBean.setIsTemplateFrom("1");
		
		// �Ώێ�
		formBean.setUserDivision(pageFormBean.getUserDivision());
		// ���M����
		formBean.setSourceDepartment(pageFormBean.getSourceDepartment());
		// �V������\���t���O
		formBean.setNewUndisplayFlag(pageFormBean.getNewUndisplayFlag());
		//�u�V�K�v�\���ێ�
		formBean.setNewKeepFlag(pageFormBean.getNewKeepFlag());
		// �e�y�[�WID
		formBean.setPPageId(pageFormBean.getPPageId());
		// �z��
		formBean.setOrderBy(pageFormBean.getOrderBy());
		// �y�[�WKEY
		formBean.setPageKey(pageFormBean.getPageKey());
		// �^�C�g��
		formBean.setTitle(pageFormBean.getTitle());
		// �R���e���c
		formBean.setContent(contentReplace(pageFormBean.getContent()));
		// ���J�J�n���t
		formBean.setStartDate(pageFormBean.getStartDate());
		// ���J�I�����t
		formBean.setEndDate(pageFormBean.getEndDate());
		// ��ʃt���O
		formBean.setHtmlFlag(pageFormBean.getHtmlFlag());
		// �t�@�C��URL(�t�@�C�����ƃA�h���X)
		formBean.setHtmlFileUrl(pageFormBean.getHtmlFileUrl());
		// ����J�t���O
		formBean.setConfirmFlag(pageFormBean.getConfirmFlag());
		// ���[�UID
		formBean.setCreateBy(pageFormBean.getCreateBy());
		// �_�E�����[�h�p�X���[�h
		formBean.setDownloadPassword(pageFormBean.getDownloadPassword());
		// �_�E�����[�h�s�t���O
		formBean.setDenyDownloadFlag(pageFormBean.getDenyDownloadFlag());
		// ���������N�\���s�t���O
		formBean.setDenyLinkfileFlag(pageFormBean.getDenyLinkfileFlag());
		// �R���e���c�����N���(�����N�Y�t)���X�g
		formBean.setPageLinkList(pageFormBean.getPageLinkList());
		//�R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
		formBean.setPageAttachmentList(pageFormBean.getPageAttachmentList());
		// �R���e���c�{�������O���[�v���(���J����O���[�v)���X�g
		formBean.setAuthGroupList(pageFormBean.getAuthGroupList());
		// �R���e���c�{���������[�U���(���J����l)���X�g
		formBean.setAuthUserList(pageFormBean.getAuthUserList());
		// �R���e���c�X�V��s��(���F��)���X�g
		formBean.setProxyUserList(pageFormBean.getProxyUserList());
		// ���J�ێ��ҏW�t���O
		formBean.setOnEditFlag("0");
		// ���J���
		formBean.setConfirmDate(pageFormBean.getConfirmDate());
		// ���J�J�n����t���O
		formBean.setStartdateOpenFlag(pageFormBean.getStartdateOpenFlag());
		// �R���e���c�]���t���O
		formBean.setEvaluationFlag(pageFormBean.getEvaluationFlag());
		// �]���Ҏ����\���t���O
		formBean.setEvaluatorDisplayFlag(pageFormBean.getEvaluatorDisplayFlag());
		// �]���҃R�����g�ҏW�t���O
		formBean.setCommentEditFlag(pageFormBean.getCommentEditFlag());
		// �����]���L���t���O
		formBean.setPluralEvaluationFlag(pageFormBean.getPluralEvaluationFlag());
		// �]�����N���A����t���O
		formBean.setEvaluationClearFlag(pageFormBean.getEvaluationClearFlag());
		// �R���e���c�]���A�C�e����񃊃X�g
		formBean.setPageRateItemBeanList(pageFormBean.getPageRateItemBeanList());
		// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)���X�g
		formBean.setPageCommentAdminOptionList(pageFormBean.getPageCommentAdminOptionList());
		// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)���X�g
		formBean.setPageCommentAdminList(pageFormBean.getPageCommentAdminList());

		// �y�[�W�\���t���O���uADD�v
		formBean.setPageViewFlag("ADD");
		
		// �e�y�[�W���݃t���O
		if (pageDao.getPage(formBean.getPPageId()) == null){
			formBean.setPpageExsitsFlg("0");
		} else {
			formBean.setPpageExsitsFlg("1");
		}		

		//�R���e���c�쐬��ID
		String currentUserId = getLoginUserBean().getUserId();
		formBean.setCreateBy(currentUserId);
		// �R���e���c�쐬�Җ��O
		String strUserName = pageDao.getCreateUserName(currentUserId);
		formBean.setCreateUserName(strUserName);
		// ���[�U���̂��X�y�[�X�ł͂Ȃ��ꍇ�A�u�R���e���c�R�����g�Ǘ��ҏ��I�����X�g�A�R���e���c�R�����g�Ǘ��ҏ�񃊃X�g�v�Ƀ��O�C�����[�U���i�[
		if (!StringUtil.isEmpty(strUserName)) {
			
			// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)���X�g
			List<PageCommentAdminBean> pageCommentAdminOptionList = formBean.getPageCommentAdminOptionList();
			Boolean flag = false;
			if (pageCommentAdminOptionList != null) {
				for (int i = 0; i < pageCommentAdminOptionList.size(); i ++) {
					PageCommentAdminBean bean = pageCommentAdminOptionList.get(i);
					String uid = bean.getUserId();
					if(uid.equals(currentUserId)) {
						flag = true;
						break;
					}
				}
			} else{
				pageCommentAdminOptionList = new ArrayList<PageCommentAdminBean>();
			}
			if(!flag) {
				PageCommentAdminBean bean = new PageCommentAdminBean();
				// ���[�UID
				bean.setUserId(currentUserId);
				// ���[�U����
				bean.setUserName(strUserName);
				pageCommentAdminOptionList.add(bean);
				// �R���e���c�R�����g�Ǘ��ҏ��I�����X�g�Ɋi�[
				formBean.setPageCommentAdminOptionList(pageCommentAdminOptionList);
			}
		}		

		// ���񍐑Ή��҃��X�g��ݒ�
		if ("0".equals(formBean.getEvaluationFlag())) {
			if (StringUtil.isNotEmpty(formBean.getCreateUserName())) {
				List<PageCommentAdminBean> pageCommentAdminList = new ArrayList<PageCommentAdminBean>();
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(formBean.getCreateBy());
				bean.setUserName(formBean.getCreateUserName());
				pageCommentAdminList.add(bean);
				// ���񍐑Ή��҃��X�g
				formBean.setPageCommentAdminList(pageCommentAdminList);
			}
		}
		
		// �ΏێґI���敪��ݒ�
		setUserType(formBean);

		// �e���v���[�g���փ`�F�b�N
		templateCheck(formBean);

	}	
	
	/**
	 * �R���e���c�ڍ׏�������
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	public void getPageDetails(PageFormBean formBean) throws Exception {
		// �R���e���c�����擾
		PageFormBean dbPageInfo = null;
		// �R���e���c���Bean
		PageBean pageBean = null;
		// �R���e���c�\����Bean
		PageBean pageRsvBean = null;
		// �R���e���c���擾�σt���O
		boolean bolPageSelectedFlag = false;
		// ����J�t���O(�y�[�W)
		String confirmFlagPage = "";
		//�@�y�[�WID
		String pageId = formBean.getPageId();

		// 1(�\��)�̏ꍇ
		if ("1".equals(formBean.getUpdateradio())){
			//�@�R���e���c�\����̎擾(��ʕ\���p)
			dbPageInfo = getPageRsvForm(pageId);
			// �R���e���c�\���񂪑��݂����ꍇ
			if (dbPageInfo != null) {
				// �R���e���c�\����Bean�ɃR�s�[����B
				pageRsvBean = new PageBean();
				WrappedBeanUtil.copyProperties(pageRsvBean, dbPageInfo);
			}
		} else {
			//�@�R���e���c���̎擾(��ʕ\���p)
			dbPageInfo = getPageForm(pageId);
			// �R���e���c��񂪑��݂����ꍇ�A
			if (dbPageInfo != null){
				// �R���e���c���Bean�ɃR�s�[����B
				pageBean = new PageBean();
				WrappedBeanUtil.copyProperties(pageBean, dbPageInfo);
				// ����J�t���O(�y�[�W)
				confirmFlagPage = dbPageInfo.getConfirmFlag();				
			}
			// �R���e���c���擾��
			bolPageSelectedFlag = true;
		}

		// �R���e���c��񂪑��݂��ĂȂ��ꍇ�A�ȍ~�̏����𒆎~����B
		if (dbPageInfo == null) {
			// �G���[��ʂ֑J��
			optimisticLockCheck(pageId,formBean.getUpdateradio());
		}		
		
		// �R���e���c���擾���ς̏ꍇ�A�R���e���c�����Ď擾
		if (!bolPageSelectedFlag) {
			//�@�R���e���c�����擾
			pageBean = getPageDB(pageId);				
		}
		
		// �R���e���c��񂪑��݂��ĂȂ��ꍇ�A�ȍ~�̏����𒆎~����B
		if (pageBean == null) {
			// �G���[��ʂ֑J��
			optimisticLockCheck(pageId, "0");
		}
		
		// child���݃t���O
		formBean.setChildExistFlag("0");
		// �p�����[�^�i�y�[�WID�j�����̎q�R���e���c��񒊏o
		List childList = getOpenChildPageList(pageId);
		// �q�R���e���c�i���J���j���������ꍇ
		if (childList != null && childList.size() > 0){
			formBean.setChildExistFlag("1");
		}
		
		// �u���J�����܂ܕҏW����v�̏ꍇ
		if ("1".equals(formBean.getOnEditFlag())){
			// �]���Ҏ����\���t���O(�y�[�W)
			formBean.setEvaluatorDisplayFlagPage(pageBean.getEvaluatorDisplayFlag());
			// �O�񕡐����ڕ]���t���O(�y�[�W)
			formBean.setPrevPluralEvaluationFlagPage(pageBean.getPrevPluralEvaluationFlag());
			// ����J�t���O(�y�[�W)
			confirmFlagPage = dbPageInfo.getConfirmFlag();				
		}

		// ���J�m�F���t��X�V�t���O(���J���t�ύX�Ȃ�)�\���t���O��ݒ�
		setViewConfirmNoupdateFlag(formBean, pageBean);

		// �u�V�K�v�\���ێ��\���t���O��ݒ�
		formBean.setViewNewKeepFlag(pageDao.getViewNewKeepFlag(pageId));
		
		// DB����t�H�[���ɐݒ�
		setFromDbToForm(formBean, dbPageInfo);
		
		// �e�y�[�W���݃t���O
		if (pageDao.getPage(formBean.getPPageId()) == null){
			formBean.setPpageExsitsFlg("0");
		} else {
			formBean.setPpageExsitsFlg("1");
		}
		
		// ���񍐑Ή��҃��X�g��ݒ�
		if ("0".equals(formBean.getEvaluationFlag()) && "0".equals(confirmFlagPage)) {
			if (StringUtil.isNotEmpty(formBean.getCreateUserName())) {
				List<PageCommentAdminBean> pageCommentAdminList = new ArrayList<PageCommentAdminBean>();
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(formBean.getCreateBy());
				bean.setUserName(formBean.getCreateUserName());
				pageCommentAdminList.add(bean);
				// ���񍐑Ή��҃��X�g
				formBean.setPageCommentAdminList(pageCommentAdminList);
			}
		}

		// �ΏێҁA���M�����h���b�v�_�E�����X�g���쐬
		getDropdownList(formBean);
		
		// �ΏێґI���敪��ݒ�
		setUserType(formBean);
		
		// �X�V�����i�r������p�j
		formBean.setUpdateDateStr(pageBean.getUpdateDateStr());
		
//		// �e�y�[�WID
//		String strHaiTiSaki = getPageLocation(formBean.getPPageId());
//		if (StringUtil.isEmpty(strHaiTiSaki)){
//			formBean.setPPageId("");
//		}

		// �z�u��ݒ�t���O
		formBean.setPageLocationSetFlag("0");

		// �y�[�W�\���t���O���uEDIT�v
		formBean.setPageViewFlag("EDIT");

	}
	
	/**
	 * DB����t�H�[���ɐݒ�
	 * @param dbPageInfo DB����擾�����R���e���c���Bean
	 * @param formBean �t�H�[��Bean
	 */
	private void setFromDbToForm(PageFormBean formBean, PageFormBean dbPageInfo){
		// �Ώێ�
		formBean.setUserDivision(dbPageInfo.getUserDivision());
		// ���M����
		formBean.setSourceDepartment(dbPageInfo.getSourceDepartment());
		// ���J�m�F���t��X�V�t���O
		formBean.setConfirmNoupdateFlag(dbPageInfo.getConfirmNoupdateFlag());
		// �V������\���t���O
		formBean.setNewUndisplayFlag(dbPageInfo.getNewUndisplayFlag());
		//�u�V�K�v�\���ێ�
		formBean.setNewKeepFlag(dbPageInfo.getNewKeepFlag());
		// �e�y�[�WID
		formBean.setPPageId(dbPageInfo.getPPageId());
		// �z��
		formBean.setOrderBy(dbPageInfo.getOrderBy());
		// �y�[�WKEY
		formBean.setPageKey(dbPageInfo.getPageKey());
		// �^�C�g��
		formBean.setTitle(dbPageInfo.getTitle());
		// �R���e���c
		formBean.setContent(contentReplace(dbPageInfo.getContent()));
		// ���J�J�n���t
		formBean.setStartDate(dbPageInfo.getStartDate());
		// ���J�I�����t
		formBean.setEndDate(dbPageInfo.getEndDate());
		// ��ʃt���O
		formBean.setHtmlFlag(dbPageInfo.getHtmlFlag());
		// �t�@�C��URL(�t�@�C�����ƃA�h���X)
		formBean.setHtmlFileUrl(dbPageInfo.getHtmlFileUrl());
		// ����J�t���O
		formBean.setConfirmFlag(dbPageInfo.getConfirmFlag());
		// ���[�UID
		formBean.setCreateBy(dbPageInfo.getCreateBy());
		// �_�E�����[�h�p�X���[�h
		formBean.setDownloadPassword(dbPageInfo.getDownloadPassword());
		// �_�E�����[�h�s�t���O
		formBean.setDenyDownloadFlag(dbPageInfo.getDenyDownloadFlag());
		// ���������N�\���s�t���O
		formBean.setDenyLinkfileFlag(dbPageInfo.getDenyLinkfileFlag());
		// �R���e���c�����N���(�����N�Y�t)���X�g
		formBean.setPageLinkList(dbPageInfo.getPageLinkList());
		//�R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
		formBean.setPageAttachmentList(dbPageInfo.getPageAttachmentList());
		// �R���e���c�{�������O���[�v���(���J����O���[�v)���X�g
		formBean.setAuthGroupList(dbPageInfo.getAuthGroupList());
		// �R���e���c�{���������[�U���(���J����l)���X�g
		formBean.setAuthUserList(dbPageInfo.getAuthUserList());
		// �R���e���c�X�V��s��(���F��)���X�g
		formBean.setProxyUserList(dbPageInfo.getProxyUserList());
		// �R���e���c�]���t���O
		formBean.setEvaluationFlag(dbPageInfo.getEvaluationFlag());
		// �]���Ҏ����\���t���O
		formBean.setEvaluatorDisplayFlag(dbPageInfo.getEvaluatorDisplayFlag());
		// �]���҃R�����g�ҏW�t���O
		formBean.setCommentEditFlag(dbPageInfo.getCommentEditFlag());
		// �����]���L���t���O
		formBean.setPluralEvaluationFlag(dbPageInfo.getPluralEvaluationFlag());
		// �]�����N���A����t���O
		formBean.setEvaluationClearFlag(dbPageInfo.getEvaluationClearFlag());
		// �R���e���c�]���A�C�e����񃊃X�g
		formBean.setPageRateItemBeanList(dbPageInfo.getPageRateItemBeanList());
		// �O����J�]���Ҏ����\���t���O
		formBean.setPrevEvaluatorDisplayFlag(dbPageInfo.getPrevEvaluatorDisplayFlag());
		// �O�񕡐����ڕ]���t���O
		formBean.setPrevPluralEvaluationFlag(dbPageInfo.getPrevPluralEvaluationFlag());
		// �R���e���c�]���t���O(�R���e���c���)
		formBean.setEvaluationFlagPage(dbPageInfo.getEvaluationFlag());
		// ���J���
		formBean.setConfirmDate(dbPageInfo.getConfirmDate());
		// ���J�J�n����t���O
		formBean.setStartdateOpenFlag(dbPageInfo.getStartdateOpenFlag());
		// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)���X�g
		formBean.setPageCommentAdminOptionList(dbPageInfo.getPageCommentAdminOptionList());
		// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)���X�g
		formBean.setPageCommentAdminList(dbPageInfo.getPageCommentAdminList());
		// �R���e���c�쐬�Җ��O
		formBean.setCreateUserName(pageDao.getCreateUserName(formBean.getCreateBy()));
	}
	
	/**
	 * �������J����
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	public void updatePageOpen(PageFormBean formBean) throws Exception {
		// �R���e���c���Bean
		PageBean pageBeanReal = null;
		// �R���e���c���\��Bean
		PageBean pageBeanRsv = null;
		// �\��R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
		List<PageAttachmentBean> pageAttachmentListRsv = null;
		// �y�[�WID
		String pageId = formBean.getPageId();

		//�@�R���e���c�����擾
		pageBeanReal = getPageDB(pageId);				
		// �R���e���c��񂪑��݂��ĂȂ��ꍇ�A�ȍ~�̏����𒆎~����B
		if (pageBeanReal == null) {
			// �G���[��ʂ֑J��
			optimisticLockCheck(pageId, "0");
		}

		// ���J����t��X�V�t���O
		String confirmNoupdateFlag = StringUtil.EMPTY;
		// �\��̏ꍇ
		if ("1".equals(formBean.getIsReserve())){
			//�@�R���e���c���\����擾
			pageBeanRsv = getPageRsvDB(pageId);
			// �R���e���c���\�񂪑��݂��ĂȂ��ꍇ�A�ȍ~�̏����𒆎~����B
			if (pageBeanRsv == null) {
				// �G���[��ʂ֑J��
				optimisticLockCheck(pageId, "1");
			}
			// �r������
			if (!pageBeanRsv.getUpdateDateStr().equals(formBean.getUpdateDateStr())){
				// �r���`�F�b�N�i�ʃ��[�U�ōX�V���ꂽ�ꍇ�A�r��������s���B�j
				optimisticLockCheck(pageId, "1");
			}
			// ���J����t��X�V�t���O��ݒ�
			confirmNoupdateFlag = pageBeanRsv.getConfirmNoupdateFlag();
		}else {
			// �r������
			if (!pageBeanReal.getUpdateDateStr().equals(formBean.getUpdateDateStr())){
				// �r���`�F�b�N�i�ʃ��[�U�ōX�V���ꂽ�ꍇ�A�r��������s���B�j
				optimisticLockCheck(pageId, "0");
			}
			// ���J����t��X�V�t���O��ݒ�
			confirmNoupdateFlag = pageBeanReal.getConfirmNoupdateFlag();
		}

		// �V�X�e�����t�擾
		Date dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		String strDateNow = StringUtil.formatTheDate(dateNow, "yyyy/MM/dd");
		// �������J�t���O���u�����v�̏ꍇ
		if ("1".equals(formBean.getFutureOpenFlag())){
			// �X�V�pBean
			PageBean bean = new PageBean();
			// ���J����t��X�V�t���O���uON�v�̏ꍇ
			if ("1".equals(confirmNoupdateFlag)){
				// �\����J���
				bean.setConfirmDate(pageBeanReal.getConfirmDate());
			} else{
				// �\����J���:���J�J�n��
				bean.setConfirmDate(formBean.getStartDate());
			}
			// �X�V��
			bean.setUpdateBy(getLoginUserBean().getUserId());
			//�@�y�[�WID
			bean.setPageId(pageId);
			//�@�R���e���c�\������X�V
			pageDao.updatePageReserve(bean);
		} else{
			// �������J�t���O���u�����v�ȊO�̏ꍇ
			// �\��̏ꍇ
			if ("1".equals(formBean.getIsReserve())) {
				// �\��R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
				pageAttachmentListRsv = pageDao.getPageAttachListRsv(pageId);
				// �R���e���c���֗\���񂩂�R���e���c���֏��Ɉړ�����B
				moveRsvToReal(formBean, pageBeanRsv);
				// �R���e���c���֗\����̕����폜
				deletePageRsv(formBean);
				// ���J����t��X�V�t���O���uOFF�v�̏ꍇ
				if (!"1".equals(confirmNoupdateFlag)){
					// ���O�����폜
					pageDao.deleteLogInfo(pageId, getLoginUserBean().getUserId());
					// ���O����o�^
					insertLog(getLoginUserBean().getUserId(), pageId);
				}
			}

			//�@�R���e���c�������JBean
			PageBean pageBeanUpdOpen = new PageBean();
			// �R���e���c���Bean����R���e���c�������JBean�ɃR�s�[
			WrappedBeanUtil.copyProperties(pageBeanUpdOpen, pageBeanReal);

			// ������J���
			boolean firstConfirmDateCompare = false;
			Date firstConfirmDate = null;
			if (!StringUtil.isEmpty(pageBeanUpdOpen.getFirstConfirmDate())){
				// ������J���
				firstConfirmDate = StringUtil.parseTheDate(pageBeanUpdOpen.getFirstConfirmDate(), "yyyy/MM/dd");
				// ������J��� > �V�X�e�����t�Atrue
				firstConfirmDateCompare = dateNow.before(firstConfirmDate);
			}
			// ���J�J�n��
			boolean startDateCompare = false;
			// ���J����t��X�V�t���O���uON�v�̏ꍇ
			if ("1".equals(confirmNoupdateFlag)){
				// �\��̏ꍇ
				if ("1".equals(formBean.getIsReserve())) {
					// ���J�J�n���t
					pageBeanUpdOpen.setStartDate(pageBeanRsv.getStartDate());
				}
				// ���J������X�y�[�X�ł͂Ȃ��ꍇ
				if (!StringUtil.isEmpty(pageBeanUpdOpen.getConfirmDate())){
					boolean confirmDateCompare = false;
					boolean dateCompare1 = false;
					boolean dateCompare2 = false;
					Date startDate = StringUtil.parseTheDate(pageBeanUpdOpen.getStartDate(), "yyyy/MM/dd");
					Date confirmDate = StringUtil.parseTheDate(pageBeanUpdOpen.getConfirmDate(), "yyyy/MM/dd");
					// ���J�J�n�� > �V�X�e�����t�Atrue
					startDateCompare = dateNow.before(startDate);
					// ���J��� > �V�X�e�����t�Atrue
					confirmDateCompare = dateNow.before(confirmDate);
					// ���J��� > ���J�J�n���Atrue
					dateCompare1 = startDate.before(confirmDate);
					// ������J��� > ���J�J�n���Atrue
					dateCompare2 = startDate.before(firstConfirmDate);
					// ���J�J�n��:�����A���J��� > ���J�J�n�� �� ���J���:���J�J�n���Őݒ�
					if(startDateCompare && dateCompare1) {
						pageBeanUpdOpen.setConfirmDate(pageBeanUpdOpen.getStartDate());
					// ���J�J�n��:�ߋ��A���J���:���� �� ���J���:�V�X�e�����t�Őݒ�
					} else if(!startDateCompare && confirmDateCompare) {
						pageBeanUpdOpen.setConfirmDate(strDateNow);
					}
					// ���J�J�n��:�����A������J��� > ���J�J�n�� �� ������J���:���J�J�n���Őݒ�
					if(startDateCompare && dateCompare2) {
						pageBeanUpdOpen.setFirstConfirmDate(pageBeanUpdOpen.getStartDate());
					// ���J�J�n��:�ߋ��A������J���:���� �� ������J���:�V�X�e�����t�Őݒ�
					} else if(!startDateCompare && firstConfirmDateCompare) {
						pageBeanUpdOpen.setFirstConfirmDate(strDateNow);
					}
				}				
			// ���J����t��X�V�t���O���uOFF�v�̏ꍇ
			} else{
				Date startDate = StringUtil.parseTheDate(pageBeanUpdOpen.getStartDate(), "yyyy/MM/dd");
				// ���J�J�n�� > �V�X�e�����t�Atrue
				startDateCompare = dateNow.before(startDate);
				
				// �\��̏ꍇ
				if("1".equals(formBean.getIsReserve())){
					// ���J��� ��  �V�X�e�����t
					pageBeanUpdOpen.setConfirmDate(strDateNow);
					// �R���e���c�]��
					pageBeanUpdOpen.setEvaluationFlag(pageBeanRsv.getEvaluationFlag());
					// �]�����N���A����
					pageBeanUpdOpen.setEvaluationClearFlag(pageBeanRsv.getEvaluationClearFlag());
					// ������J���>= �V�X�e�����t�̏ꍇ
					if(dateNow.before(firstConfirmDate)) {
						// ������J��� ��  �V�X�e�����t
						pageBeanUpdOpen.setFirstConfirmDate(strDateNow);
					}
			    } else {
					// ���J������X�y�[�X(������J)�̏ꍇ
					if (StringUtil.isEmpty(pageBeanUpdOpen.getConfirmDate())){
						// ������J�@���@���J�J�n����t���O ���u1(���J�J�n����j�v�̏ꍇ
						if ("1".equals(pageBeanUpdOpen.getStartdateOpenFlag())) {
							// ���J���
							pageBeanUpdOpen.setConfirmDate(pageBeanUpdOpen.getStartDate());
							// ������J���
							pageBeanUpdOpen.setFirstConfirmDate(pageBeanUpdOpen.getStartDate());
						// ������J�@���@���J�J�n����Ō��J���u0(�������J�N���b�N�����j�v�̏ꍇ
						} else {
							// ���J�J�n�� > �V�X�e�����t�̏ꍇ�A ���J����F���J�J�n���Őݒ�
							if (startDateCompare) {
								// ���J���
								pageBeanUpdOpen.setConfirmDate(pageBeanUpdOpen.getStartDate());
								// ������J���
								pageBeanUpdOpen.setFirstConfirmDate(pageBeanUpdOpen.getStartDate());
							// ���J�J�n�� < �V�X�e�����t�̏ꍇ�A ���J����F�V�X�e�����t�Őݒ�
							} else {
								// ���J���
								pageBeanUpdOpen.setConfirmDate(strDateNow);
								// ������J���
								pageBeanUpdOpen.setFirstConfirmDate(strDateNow);
							}
						}
					// �ҏW���ꂽ�ꍇ
					} else {
						// ���J����F���J�J�n���ƃV�X�e���̍ő�l
						pageBeanUpdOpen.setConfirmDate(startDateCompare ? pageBeanUpdOpen.getStartDate(): strDateNow);
						// ������J����������ꍇ
						if(dateNow.before(firstConfirmDate)) {
							// ������J���:���J�J�n���ƃV�X�e���̍ő�l
							pageBeanUpdOpen.setFirstConfirmDate(startDateCompare ? pageBeanUpdOpen.getStartDate() : strDateNow);
						}
					}
			    }
				// �R���e���c�]�����֍X�V
				// �O����J�]���Ҏ����\���t���O
				pageBeanUpdOpen.setPrevEvaluatorDisplayFlag(pageBeanUpdOpen.getEvaluatorDisplayFlag());
				// �O�񕡐����ڕ]���t���O
				pageBeanUpdOpen.setPrevPluralEvaluationFlag(pageBeanUpdOpen.getPluralEvaluationFlag());
				String loginUserId = getLoginUserBean().getUserId();
				// �R���e���c�]�����u����v�̏ꍇ
				if ("1".equals(pageBeanUpdOpen.getEvaluationFlag())){
					// �u�]�����N���A����v���`�F�b�N����ꍇ�A
					if ("1".equals(pageBeanUpdOpen.getEvaluationClearFlag())) {
						// �R���e���c���[�U�[�]������_���폜
						pageDao.updatePageExtendInvalid(pageId, "PAGE_USER_RATE", loginUserId);
						// �R���e���c���[�U�[�R�����g���𕨗��폜
						pageDao.deletePageExtend(pageId, "PAGE_USER_COMMENT");
						// �R���e���c�R�����g�]�����𕨗��폜
						pageDao.deletePageExtend(pageId, "PAGE_COMMENT_RATE");
						// �]�����N���A����t���O
						pageBeanUpdOpen.setEvaluationClearFlag("0");
					}
					// �R���e���c�]���A�C�e�����̍폜���ɂ��A�R���e���c���[�U�[�]������_���폜
					pageDao.updatePageUserRateInvalidByRateItem(pageId, loginUserId);
					// �e�[�u������R���e���c�]���A�C�e�������擾
					List<PageRateItemBean> pageRateItemList = pageDao.getPageRateItemList(pageId);
					if (pageRateItemList != null){
						// �R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
						for(PageRateItemBean pageRateItemBean:pageRateItemList){
							PageUserRateBean pageUserRateBean = new PageUserRateBean();
							// ���ڏ���
							pageUserRateBean.setEvaluationOrderBy(pageRateItemBean.getEvaluationOrderBy());
							// �X�V���[�UID
							pageUserRateBean.setUpdateBy(getLoginUserBean().getUserId());
							// �y�[�WID
							pageUserRateBean.setPageId(pageId);
							// SEQUENCE
							pageUserRateBean.setSequence(pageRateItemBean.getSequence());
							// �R���e���c���[�U�[�]�����̍��ڏ��Ԃ��X�V
							pageDao.updatePageUserRateOrderBySeq(pageUserRateBean);
						}
					}
				} else{
					// �R���e���c���[�U�[�]������_���폜
					pageDao.updatePageExtendInvalid(pageId, "PAGE_USER_RATE", loginUserId);
					// �R���e���c���[�U�[�R�����g���𕨗��폜
					pageDao.deletePageExtend(pageId, "PAGE_USER_COMMENT");
					// �R���e���c�R�����g�]�����𕨗��폜
					pageDao.deletePageExtend(pageId, "PAGE_COMMENT_RATE");
					// �R���e���c�]���A�C�e������_���폜
					pageDao.updatePageExtendInvalid(pageId, "PAGE_RATE_ITEM", loginUserId);
					// �R���e���c�R�����g�Ǘ��ҏ��𕨗��폜
					pageDao.deletePageExtend(pageId, "PAGE_COMMENT_ADMIN");
				}
			}
			// �X�V���[�UID
			pageBeanUpdOpen.setUpdateBy(getLoginUserBean().getUserId());
			// ���J�ێ��ҏW�t���O
			pageBeanUpdOpen.setOnEditFlag("0");
			// ���J�񐔃v���X����
			if(!(firstConfirmDateCompare && "1".equals(pageBeanUpdOpen.getOpenNum()))){
				// �u���J��+1�v����
				pageBeanUpdOpen.setOpenNum(String.valueOf(NumberUtil.toLong(pageBeanUpdOpen.getOpenNum()) +1));
			}
			// �������J�R���e���c�����X�V
			pageDao.updatePageForOpen(pageBeanUpdOpen);
			
			// �\��̏ꍇ
			if("1".equals(formBean.getIsReserve())){
				// �t�@�C�����֏���
				fileRelatedForOpen(formBean, pageAttachmentListRsv, pageBeanRsv.getFileSuffix());
			}
		}
		
		//�������@�R���e���c�̔z�u��̐ݒ�
		formBean.setPageLocation(getPageLocation(pageId));
		
		// ���R���e���c�A�\��R���e���c�𔻒f
		String isReserve = "0";
		// �������J�t���O���u�����v�̏ꍇ�A�\��@����ȊO�ɂ͎��R���e���c
		if ("1".equals(formBean.getFutureOpenFlag())){
			isReserve = "1";
		}
		formBean.setIsReserve(isReserve);

		// ���̃R���e���c��URL��ݒ�
		setPageUrl(formBean);
		
		// �y�[�W�\���t���O���uOPEN�v
		formBean.setPageViewFlag("OPEN");
		// �u�������J�v�A�u���[�����M�v���쐧��t���O
		formBean.setOpenEnableFlag("0");
	}
	
	/**
	 * ���̃R���e���c��URL��ݒ�
	 * @param formBean �t�H�[��Bean
	 */
	public void insertLog(String userId, String pageId){
		pageDao.insertLog(getLoginUserBean().getUserId(), pageId);
	}
	
	/**
	 * ���̃R���e���c��URL��ݒ�
	 * @param formBean �t�H�[��Bean
	 */
	private void setPageUrl(PageFormBean formBean){
		StringBuffer pageUrl = new StringBuffer();
		pageUrl.append(formBean.getContextPath());
		pageUrl.append("/pageView_view.do?formBean.originType=mail&formBean.pageId=");
		// �y�[�WID
		pageUrl.append(formBean.getPageId());
		// ���R���e���c�́u�\��@�܂��́@�^���v��ݒ�
		pageUrl.append("&formBean.isReserve=" + formBean.getIsReserve());
		// ���̃R���e���c��URL
		formBean.setPageUrl(pageUrl.toString());
	}
	
	/**
	 * �R���e���c���֗\���񂩂�R���e���c���֏��Ɉړ�
	 * @param formBean �t�H�[��Bean
	 * @param pageBeanRsv �R���e���c���\��Bean
	 */
	private void moveRsvToReal(PageFormBean formBean, PageBean pageBeanRsv) {
		// ���I�R���e���c�̏ꍇ
		if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
			// �R���e���c�����N���Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
			// �R���e���c�����N�����폜
			pageDao.deletePageExtend(formBean.getPageId(), "PAGE_LINK");
			// �R���e���c�����N�\���񂩂�R���e���c�����N���Ɉړ��i�L�[�F�y�[�WID�j
			pageDao.linkReserveToLinkByPageId(formBean.getPageId());

			// �R���e���c�Y�t�t�@�C�����Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
			// �R���e���c�Y�t�t�@�C�������폜
			pageDao.deletePageExtend(formBean.getPageId(), "PAGE_ATTACHMENT");
			// �R���e���c�Y�t�t�@�C���\���񂩂�R���e���c�Y�t�t�@�C�����Ɉړ��i�L�[�F�y�[�WID�j
			pageDao.attachmentReserveToAttachmentByPageId(formBean.getPageId());
		}
		// �R���e���c�{�������g�b�v�O���[�v���Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
		// �R���e���c�{�������g�b�v�O���[�v�����폜
		pageDao.deletePageExtend(formBean.getPageId(), "AUTH_LEADING_GROUP");
		// �R���e���c�{�������g�b�v�O���[�v�\���񂩂�R���e���c�{�������g�b�v�O���[�v���Ɉړ��i�L�[�F�y�[�WID�j
		pageDao.leadingGroupReserveToLeadingGroupByPageId(formBean.getPageId());

		// �R���e���c�{���������[�U���Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
		// �R���e���c�{���������[�U�����폜
		pageDao.deletePageExtend(formBean.getPageId(), "AUTH_USER");
		// �R���e���c�{���������[�U�\���񂩂�R���e���c�{���������[�U���Ɉړ��i�L�[�F�y�[�WID�j
		pageDao.authUserReserveToAuthUserByPageId(formBean.getPageId());

		// �X�V��s�ҏ��Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
		// �X�V��s�ҏ����폜
		pageDao.deletePageExtend(formBean.getPageId(), "UPDATE_PROXY_USER");
		// �X�V��s�җ\���񂩂�X�V��s�ҏ��Ɉړ��i�L�[�F�y�[�WID�j
		pageDao.proxyUserReserveToProxyUserByPageId(formBean.getPageId());
		
		// �R���e���c�]�����u����v�̏ꍇ
		if ("1".equals(pageBeanRsv.getEvaluationFlag())){
			// �R���e���c�]���A�C�e�����Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
			PageRateItemBean pageRateItemBean = new PageRateItemBean();
			// �y�[�WID
			pageRateItemBean.setPageId(formBean.getPageId());
			//�@�X�V���[�UID
			pageRateItemBean.setUpdateBy(getLoginUserBean().getUserId());
			// �R���e���c�]���A�C�e���\����ɑ��݂��Ȃ���΁A�R���e���c�]���A�C�e������_���폜
			pageDao.updateRateItemInvalidByRateItemReserve(pageRateItemBean);
			// �R���e���c�]���A�C�e���\����e�[�u������f�[�^�𒊏o
			List<PageRateItemBean> pageRateItemListRsv = pageDao.getPageRateItemListRsv(formBean.getPageId());
			// �R���e���c�]���A�C�e�����e�[�u������f�[�^�𒊏o
			List dbPageRateItemList = pageDao.getPageRateItemList(formBean.getPageId());
			if (pageRateItemListRsv != null){
				if (dbPageRateItemList != null){
					// �擾�����R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
					for (int i=0; i<dbPageRateItemList.size();i++){
						// DB�R���e���c�]���A�C�e�����
						PageRateItemBean dbPageRateItemBean = (PageRateItemBean) dbPageRateItemList.get(i);
						Boolean deleteFlag = true;
						// ��ʓ��͂̃R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
						for (PageRateItemBean dbPageRateItemBeanRsv:pageRateItemListRsv){
							// DB�A��ʗ������݂����ꍇ
							if (dbPageRateItemBean.getSequence().equals(dbPageRateItemBeanRsv.getSequence())){
								deleteFlag = false;
								break;
							}
						}
						// DB�ɑ��݁A��ʂɑ��݂��Ȃ��ꍇ�ADB�_���폜���s��
						if (deleteFlag){
							pageDao.updateEvaluationInvalidBySeq(dbPageRateItemBean.getPageId(), 
									dbPageRateItemBean.getSequence(), getLoginUserBean().getUserId());
						}
					}
				}
				// ���L�̏������J��Ԃ�
				for (PageRateItemBean rateItemRsvBean:pageRateItemListRsv){
					String seq = rateItemRsvBean.getSequence();
					rateItemRsvBean.setCreateDate(new Date());
					rateItemRsvBean.setUpdateDate(new Date());
					// �R���e���c�]���A�C�e�����ɑ��݂��Ȃ��ꍇ�A�o�^
					if ("0".equals(seq)) {
						// �R���e���c�]���A�C�e�����e�[�u����o�^
						pageDao.insertPageRateItem(rateItemRsvBean);
					} else {
						if (dbPageRateItemList != null){
							Boolean deleteFlag = true;
							// ��ʓ��͂̃R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
							for (int i=0; i<dbPageRateItemList.size();i++){
								// DB�R���e���c�]���A�C�e�����
								PageRateItemBean dbPageRateItemBean = (PageRateItemBean) dbPageRateItemList.get(i);
								// DB�A��ʗ������݂����ꍇ
								if (dbPageRateItemBean.getSequence().equals(rateItemRsvBean.getSequence())){
									deleteFlag = false;
									// �R���e���c�]���A�C�e�����e�[�u�����X�V
									pageDao.updatePageRateItem(rateItemRsvBean);
									break;
								}
							}
							// ��ʑ��݁A�e�[�u�[�����݂��Ȃ�
							if (deleteFlag) {
								// �R���e���c�]���A�C�e�������폜�i��L�[�j
								pageDao.deletePageRateItemByPK(rateItemRsvBean);
								// �R���e���c�]���A�C�e�����e�[�u����o�^
								pageDao.insertPageRateItem(rateItemRsvBean);
							}
						}
					}
				}
			}			
		}
		
		// �R���e���c�R�����g�Ǘ��ҏ��Ɋւ��Ẵe�[�u������i�\�񁨎��e�[�u���j
		// �R���e���c�R�����g�Ǘ��ҏ����폜
		pageDao.deletePageExtend(formBean.getPageId(), "PAGE_COMMENT_ADMIN");
		// �R���e���c�R�����g�Ǘ��җ\���񂩂�R���e���c�R�����g�Ǘ��ҏ��Ɉړ��i�L�[�F�y�[�WID�j
		pageDao.commentAdminReserveToCommentAdminByPageId(formBean.getPageId());
		
		// �R���e���c�\���񂩂�R���e���c��񂩂ɍX�V�i�L�[�F�y�[�WID�j
		pageDao.updatePageFromReserve(formBean.getPageId());
	}
	
	/**
	 * �t�@�C�����֏���
	 * @param formBean �t�H�[��Bean
	 * @param pageAttachmentListReal ���R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
	 * @param pageAttachmentListRsv �\��R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
	 * @throws Exception 
	 */
	private void fileRelatedForOpen(PageFormBean formBean, List<PageAttachmentBean> pageAttachmentListRsv, 
			String fileSuffix) throws Exception {
		
		try {
			// ���I�R���e���c�̏ꍇ
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				// ���R���e���c�Y�t�i�[�p�X
				String serverUploadPath = FsPropertyUtil.getStringProperty("server.upload.path");
				// �\��R���e���c�Y�t�i�[�p�X
				String serverUploadTempPath = FsPropertyUtil.getStringProperty("server.upload.temp.path");
				// �o�b�`�t�@�C���p�X
				String batchFile = FsPropertyUtil.getStringProperty("batch.file");
				// �\��̏ꍇ
				if ("1".equals(formBean.getIsReserve())) {
					// ���R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g���������ꍇ�A�Ƃ肠�����A���Y�t�t�@�C�����폜
			        FileUtil.deletePurgeFile(batchFile, formBean.getPageId());
				}

				// �\��R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g���������ꍇ�A�\�񁨎��t�H���_�ɃR�s�[
				if (pageAttachmentListRsv != null) {
					for (PageAttachmentBean bean : pageAttachmentListRsv) {
						String fileUrl = bean.getAttachmentFileUrl();
						String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
						// �\�񁨎��t�H���_�ɃR�s�[
						FileUtil.copyFile(serverUploadTempPath, serverUploadPath, fileName);
						// �\��Y�t�t�@�C�����폜
						FileUtil.deleteFile(serverUploadTempPath, fileName);

					}
				}
				// HTML�t�@�C���p�X
				String publishHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.path");
				String publishtempHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				// �\�񁨎��t�H���_�ɃR�s�[�iURL�ύX�K�v����j
				String htmlFileName =  formBean.getPageId() + ".html";
				FileUtil.copyHtmlRsvToReal(publishtempHtmlPath, publishHtmlPath, htmlFileName);
				// �\��Y�t�t�@�C�����폜
				FileUtil.deleteFile(publishtempHtmlPath, htmlFileName);
			} else{
				// �ÓI�R���e���c�̏ꍇ
				// HTML�t�@�C���p�X
				String publishHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.path");
				String publishtempHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				// �R�s�[��t�@�C�����폜
				FileUtil.deletePurgeFile(publishHtmlPath, formBean.getPageId());
				
				fileSuffix = StringUtil.nullToBlank(fileSuffix);
				String fileName = "";
				// ZIP�t�@�C���̏ꍇ
				if (CommonUtil.checkStaticFileSuffix(formBean.getPageId(), fileSuffix)) {
					fileName = formBean.getPageId();
				} else {
					// ZIP�t�@�C���ȊO�̏ꍇ
					fileName = formBean.getPageId() + fileSuffix;
				}
				FileUtil.fileCopyForStatic(publishtempHtmlPath, publishHtmlPath, fileName, fileName);
				// �\��t�@�C�����폜
				FileUtil.deletePurgeFile(publishtempHtmlPath, formBean.getPageId());
			}
		} catch (Exception e) {
			// �t�@�C���T�[�o�[
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * �R���e���c���폜����
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	public void deletePageInfo(PageFormBean formBean) throws Exception {
		// �R���e���c���Bean(�r���`�F�b�N)
		PageBean pageBeanCheck = null;
		
		// �\��̏ꍇ
		if ("1".equals(formBean.getIsReserve())){
			//�@�R���e���c���\����擾
			pageBeanCheck = getPageRsvDB(formBean.getPageId());
		} else{
			//�@�R���e���c�����擾
			pageBeanCheck = getPageDB(formBean.getPageId());
		}
		// A)���݂��Ȃ���΁A�r���`�F�b�N�G���[
		// B)���ɕʐl�ōX�V���ꂽ�ꍇ�A�A�r���`�F�b�N�G���[
		if (pageBeanCheck == null) {
			// �G���[��ʂ֑J��
			optimisticLockCheck(formBean.getPageId(), formBean.getIsReserve());
		}
		// �r������
		if (!pageBeanCheck.getUpdateDateStr().equals(formBean.getUpdateDateStr())){
			// �r���`�F�b�N�i�ʃ��[�U�ōX�V���ꂽ�ꍇ�A�r��������s���B�j
			optimisticLockCheck(pageBeanCheck.getPageId(),formBean.getIsReserve());
		}

		// �����������������������������������[�����փ��W�b�N�J�n����������������������������������������
		// ���M�敪�t���O�AdivFlag = 1�@�ˁ@�������폜����AdivFlag = 0�@�ˁ@���݂̂��폜����
		String divFlag = "";
		
		// �R���e���c�敪�t���O�AconFlag = 0�@�ˁ@���J���R���e���c�AconFlag = 1�@�ˁ@�쐬���R���e���c�AconFlag = 2�@�ˁ@���J�҂��R���e���c
		String conFlag = "0";
		
		// ���R���e���c���擾
		PageBean sendMailPage = getPageDB(formBean.getPageId());
		PageDeleteBean pageDeleteBean =  new PageDeleteBean();
		WrappedBeanUtil.copyProperties(pageDeleteBean, sendMailPage);
		// ���N�G�X�g��ContextPath��ێ�
		pageDeleteBean.setContextPath(formBean.getContextPath());		

		Date dateNow = null;
		Date startDate = null;
		// �V�X�e�����t�擾
		dateNow = StringUtil.getNowDate("yyyy/MM/dd");
		// �y�[�W�e�[�u���̌��J�J�n���擾
		startDate = StringUtil.parseTheDate(pageDeleteBean.getStartDate(), "yyyy/MM/dd");
		boolean dateCompare = dateNow.before(startDate);
		
		// �쐬���R���e���c
		if ("1".equals(pageDeleteBean.getConfirmFlag())) {
			conFlag = "1";
		// ���J�҂��R���e���c
		} else if (dateCompare) {
			conFlag = "2";
		}
		
		// ����
		if ("1".equals(formBean.getOnEditFlag())) {
			if ("1".equals(formBean.getDeleteradio())) {
				divFlag = "1";
			}
		} else {
			// ���݂̂��폜����
			divFlag = "0";
		}
		
		// �폜�R���e���c�̑S�ď��List
		List<PageDeleteBean> deleteList = new ArrayList();
		if (!StringUtil.isEmpty(divFlag)) {
			deleteList = getDeleteData(pageDeleteBean, divFlag, conFlag);
		}
		// �����������������������������������[�����փ��W�b�N�I������������������������������������������
		
		// �u0�F(���J�\��i�ҏW�j���R���e���c�̂ݍ폜)�v�̏ꍇ
		if ("0".equals(formBean.getDeleteradio())){
			// �\��R���e���c���̕����폜
			deletePageRsv(formBean);
			
			// ���J�ێ��ҏW�t���O���X�V�i�폜�p�j
			updateOnEditFlagForDelete(formBean);			
			
		} else {
			// �u1�F(���J�\��i�ҏW�j���y�ь��J���̃R���e���c���폜)�v�̏ꍇ
			// A.�����܂ܕҏW�@���@1(�\��y�ь��J���̃R���e���c���폜)
			// B.�����܂ܕҏW�ł͂Ȃ�
			//   �P�[�X�P�F���R���e���c�̂ݑ���
			//   �P�[�X�Q�F���R���e���c�A�\��R���e���c��������

			// ���q�R���e���c��񃊃X�g���擾
			List<PageBean> childPageList = pageDao.getChildPageList(formBean.getPageId());
			// �\��q�R���e���c��񃊃X�g���擾
			List<PageBean> childPageReserveList = pageDao.getChildPageReserveList(formBean.getPageId());
			// �q�R���e���c���������ꍇ�A�G���[
			if ((childPageList != null && childPageList.size() > 0)
					|| (childPageReserveList != null && childPageReserveList.size() > 0)){
				// ���R���e���c�ɂ͎q�R���e���c���R�t���Ă��邽�ߍ폜�ł��܂���̃G���[���o��
				String message = getErrorMessage("MSGOE156", new String[]{formBean.getPageId()});
				CommonErrorPageException e = new CommonErrorPageException(message);
				e.getErrorMessageList().add(message);
				throw e;
			}			
			
			String loginUserId = getLoginUserBean().getUserId();
			// ���R���e���c����_���폜
			PageBean pageBean = new PageBean();
			// �y�[�WID
			pageBean.setPageId(formBean.getPageId());
			// �X�V���[�UID
			pageBean.setUpdateBy(loginUserId);
			pageDao.updatePageInvalid(pageBean);
			
			// �\��R���e���c���̕����폜
			deletePageRsv(formBean);
			
			// ���I�R���e���c�̏ꍇ
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				// �R���e���c�����N����_���폜
				pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_LINK", loginUserId);
				// �R���e���c�Y�t�t�@�C������_���폜
				pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_ATTACHMENT", loginUserId);
			}
			
			// �R���e���c�{�������g�b�v�O���[�v����_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "AUTH_LEADING_GROUP", loginUserId);
			// �R���e���c�{���������[�U����_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "AUTH_USER", loginUserId);
			// �X�V��s�ҏ���_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "UPDATE_PROXY_USER", loginUserId);
			// ���O����_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "LOG", loginUserId);
			// �R���e���c�]���A�C�e������_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_RATE_ITEM", loginUserId);
			// �R���e���c���[�U�[�]������_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_USER_RATE", loginUserId);
			// �R���e���c���[�U�[�R�����g����_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_USER_COMMENT", loginUserId);
			// �R���e���c�R�����g�]������_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_COMMENT_RATE", loginUserId);
			// �R���e���c�R�����g�Ǘ��ҏ���_���폜
			pageDao.updatePageExtendInvalid(formBean.getPageId(), "PAGE_COMMENT_ADMIN", loginUserId);
		}
		
		// �����������������������������������[�����փ��W�b�N�J�n����������������������������������������
		LoginUserBean loginUser = getLoginUserBean();
		String userid = loginUser.getUserId();
		String kanaMei = StringUtil.nullToBlank(loginUser.getKanjiMei());
		String kanaSei = StringUtil.nullToBlank(loginUser.getKanjiSei());
		String user = kanaSei + kanaMei + "("+userid+")";

		// �����N���⃊���N�摶�݂̏ꍇ�A���M
		if (deleteList.size() > 0) {
			PageDeleteBean pageDeleteBeanMail = deleteList.get(0);
			List linkContentsList = pageDeleteBeanMail.getLinkContentsList();
			boolean linkContentsflag = getContentFlag(linkContentsList, "0");
			List<List<PageLinkBean>> linkedList = pageDeleteBeanMail.getLinkedList();
			boolean linkedflag = getContentFlag(linkedList, "1");
			Date endDate = null;
			// �y�[�W�e�[�u���̌��J�I�����擾
			endDate = StringUtil.parseTheDate(pageDeleteBeanMail.getEndDate(), "yyyy/MM/dd");
			boolean enddateCompare = dateNow.before(endDate);

			//���[���̓��e�����Ƒ��M
			if (enddateCompare) {
				if (linkContentsflag || linkedflag) {
					deleteSendMail(deleteList, user);
					// ���O���o��
					formBean.setDeleteMailLog(deleteMailLog(deleteList));
				}
			}
		}
		// �����������������������������������[�����փ��W�b�N�I������������������������������������������
		
		// �t�@�C�����֏���(�R���e���c�폜)
		fileRelatedForDelete(formBean);
	}
	
	/**
	 * ���J�ێ��ҏW�t���O���X�V�i�폜�p�j
	 * �u0:���J�����܂ܕҏW�ł͂Ȃ��v�ɍX�V
	 * @param formBean �t�H�[��Bean
	 */
	private void updateOnEditFlagForDelete(PageFormBean formBean) {
		// �\��R���e���c���̕����폜
		deletePageRsv(formBean);
		PageBean page = new PageBean();
		// �y�[�WID
		page.setPageId(formBean.getPageId());
		// ���J�ێ��ҏW�t���O
		page.setOnEditFlag("0");
		// �X�V���[�UID
		page.setUpdateBy(getLoginUserBean().getUserId());
		// �R���e���c���̍X�V
		//�������@�^���R���e���c�̌��J�ێ��ҏW�t���O�uON_EDIT_FLAG�v��0(���J�����܂ܕҏW�ł͂Ȃ�)�ɍX�V
		pageDao.updateOnEditFlag(page);
	}
	
	/**
	 * �t�@�C�����֏���(�R���e���c�폜)
	 * @param formBean �t�H�[��Bean
	 * @throws Exception 
	 */
	private void fileRelatedForDelete(PageFormBean formBean) throws Exception{
		try {
			// ���I�R���e���c�̏ꍇ
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				// �\��R���e���c�Y�t�i�[�p�X
				String serverUploadTempPath = FsPropertyUtil.getStringProperty("server.upload.temp.path");
				FileUtil.deletePurgeFile(serverUploadTempPath, formBean.getPageId());

				// HTML�t�@�C���p�X
				String publishtempHtmlPath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				String htmlFileName =  formBean.getPageId() + ".html";
				// �\��Y�t�t�@�C�����폜
				FileUtil.deleteFile(publishtempHtmlPath, htmlFileName);
			} else{
				// �ÓI�R���e���c�̏ꍇ
				// �\��HTML�i�[�t�H���_���폜
				String staticFilePath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				FileUtil.deletePurgeFile(staticFilePath, formBean.getPageId());
				// �ꎞ�i�[�t�H���_���폜
				staticFilePath = FsPropertyUtil.getStringProperty("temp.temp.path");
				FileUtil.deletePurgeFile(staticFilePath, formBean.getPageId());
			}			
		} catch (Exception e) {
			// �t�@�C���T�[�o�[
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * �R���e���c�m�F����
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	public void confirmPage(PageFormBean formBean) throws Exception {
		// ���J�ێ��ҏW�t���O
		formBean.setOnEditFlag(StringUtil.nullToZero(formBean.getOnEditFlag()));
		// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)
		pageCommentAdminRelated(formBean);
		// �R���e���c�o�^�̏ꍇ�A�y�[�WID���̔�
		if ("ADD".equals(formBean.getPageViewFlag())) {
			GregorianCalendar now = new GregorianCalendar();
			String nowTime = String.valueOf(now.getTimeInMillis());
			// �y�[�WID��ݒ�
			formBean.setPageId(nowTime);			
		}
		// ���I�R���e���c�̏ꍇ
		if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())){
			// �^�C�g�����i�ҏW�j
			formBean.setTitles(CommonUtil.getErrorContent(formBean.getTitle()));
			// �R���e���c�����N��񑊊֏���
			pageLinkRelated(formBean, formBean.getContextPath());
		}
		// �R���e���c�Y�t�t�@�C����񑊊֏���
		pageFileRelated(formBean);
		// �R���e���c�]����񑊊֏���
		pageRateRelated(formBean);
		// �e���v���[�g����̏ꍇ�A�e���v���[�g���փ`�F�b�N�����{
		if ("1".equals(formBean.getIsTemplateFrom())){
			// �e���v���[�g���փ`�F�b�N
			templateCheck(formBean);
		}
	}
	
	/**
	 * �R���e���c�����N��񑊊֏���
	 * @param fromBean �t�H�[���@
	 * @param strContextPath ���N�G�X�g�Ώۂ�ContextPath
	 */
	private void pageLinkRelated(PageFormBean formBean, String strContextPath) {
		// �R���e���c
		String contents = formBean.getContent();
		// �R���e���c�����N�Y�t
		List<PageLinkBean> pageLinkList = formBean.getPageLinkList();
		//�@��ʃ����N�Y�t�����͂��ꂽ�ꍇ
		if (pageLinkList != null && pageLinkList.size() > 0) {	
			//�@���������NURL
			String strUrl = strContextPath + "/";
			// �O�������N���
			String windowStyle = "toolbar=yes,location=yes,channelmode=no,fullscreen=no,directories=no,menubar=yes,resizable=yes,scrollbars=yes,minimize=no";
			// ��ʂ̃����N�Y�t���X�g�ɂ��A���L�̏������J��Ԃ�
			for (PageLinkBean bean : pageLinkList) {
				// �L�[���[�h
				String linkName = bean.getLinkName();
				//�@URL
				String linkUrl = bean.getLinkUrl();
				//�@�폜�ȊO�@���@�����N�Y�t�L�[���[�h�����͂��ꂽ�ꍇ
				if (!"1".equals(bean.getLinkDeleteFlag())
						&& StringUtil.isNotEmpty(linkName)
						&& StringUtil.isNotEmpty(linkUrl)) {
					StringBuffer linkSbr = new StringBuffer("");
					String pageURL = "";
					if (linkUrl.startsWith(strUrl)) {
						pageURL = linkUrl.substring(linkUrl.indexOf("&pageURL=") + 9);
					}
					// ���������N�̏ꍇ
					if (pageURL.startsWith(strUrl)){
						linkSbr.append("<a href='#' onclick=\"doLink('");
						linkSbr.append(linkUrl);
						linkSbr.append("')\">");
						linkSbr.append(linkName);
						linkSbr.append("</a>");
					} else {
						// �O�������N�̏ꍇ
//						linkSbr.append("<a href='#' onclick=\"window.open('");
						linkSbr.append("<a target='_blank' href='");
						if (!linkUrl.startsWith(strUrl)) {
							if (!linkUrl.toLowerCase().startsWith("http")) {
								linkUrl = "http://" + linkUrl;
							}
						}
						linkSbr.append(linkUrl);
						linkSbr.append("'>");
//						linkSbr.append("','");
//						linkSbr.append("','");
//						linkSbr.append(windowStyle);
//						linkSbr.append("')\">");
						linkSbr.append(linkName);
						linkSbr.append("</a>");
					}

					contents = contents.replace(linkName, linkSbr.toString());
				}
			}
		}
		// �R���e���c�̃G���[�ϊ�
		contents = CommonUtil.getErrorContent(contents);
		contents = contentReplace(contents);
		//�@�t�H�[���ɐݒ�
		formBean.setContents(contents);
	}
	
	/**
	 * �t�@�C����񑊊֏���
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	private void pageFileRelated(PageFormBean formBean) throws Exception {
		
		try {
			// �R���e���c�Y�t�t�@�C���ꎞ�t�@�C�����A�b�v���[�h����
			String tempPageAttachment = FsPropertyUtil.getStringProperty("temp.page.attachment");
			// �ꎞ�t�@�C�����폜
			FileUtil.deleteFilePeriod(tempPageAttachment, FsPropertyUtil.getIntProperty("upload.temp.file.days"));

			// �R���e���cURL
			String pageFileUrl = StringUtil.EMPTY;
			// �R���e���c�ꎞURL
			String tempFileUrl = StringUtil.EMPTY;
			// �ÓI�R���e���c�ꎞ�ۑ��p�X
			String tempStaticFilePath = StringUtil.EMPTY;
			// �u1(���J�����܂ܕҏW����)�v�̏ꍇ
			if("1".equals(formBean.getOnEditFlag())) {
				tempFileUrl = FsPropertyUtil.getStringProperty("temp.temp.url");
				tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.temp.path");
			} else {
				tempFileUrl = FsPropertyUtil.getStringProperty("temp.url");
				tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.path");
			}			
			// ���R���e���c
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())) {
				
				// �����Y�t���݃t���O
				formBean.setAttachmentExistFlag("0");
				// �R���e���c�Y�t�t�@�C�����̍ő�URL���擾
				String attachmentFileUrl = pageDao.getMaxAttachmentUrl(formBean.getPageId());
				String strAttCount = "";
				int intAttCount = 0;
				// DB�ɑ��݂����ꍇ
				if (StringUtil.isNotBlank(attachmentFileUrl)) {
					if (attachmentFileUrl.lastIndexOf(".") < 0) {
						strAttCount = attachmentFileUrl.substring(attachmentFileUrl.lastIndexOf("/") + 14, attachmentFileUrl.length());
					} else {
						strAttCount = attachmentFileUrl.substring(attachmentFileUrl.lastIndexOf("/") + 14, attachmentFileUrl.lastIndexOf("."));
					}
					intAttCount = Integer.parseInt(strAttCount) + 1;
				}
				// �R���e���c�Y�t�t�@�C�����i�����Y�t�j���X�g
				List<PageAttachmentBean> fileList = formBean.getPageAttachmentList();
				List<PageAttachmentBean> fileListNew = new ArrayList<PageAttachmentBean>();
				// �Y�t�t�@�C�����������ꍇ�A�Y�t�t�@�C�����Ȃ����R�[�h���폜
				if (fileList != null) {
					for (PageAttachmentBean bean : fileList) {
						if (bean != null){
							fileListNew.add(bean);
						}
					}
					formBean.setPageAttachmentList(fileListNew);
				}

				// �Y�t�t�@�C�����������ꍇ
				fileList = formBean.getPageAttachmentList();
				if (fileList != null) {
					// �t�@�C���p�X
					String filePath = "";
					// �u1(���J�����܂ܕҏW����)�v�̏ꍇ
					if("1".equals(formBean.getOnEditFlag())) {
						filePath = FsPropertyUtil.getStringProperty("attachmentFile.temp.url");
					} else {
						filePath = FsPropertyUtil.getStringProperty("attachmentFile.url");
					}
					for (PageAttachmentBean bean : fileList) {
						bean.setFileDeleteFlag(StringUtil.nullToZero(bean.getFileDeleteFlag()));
						//�����Y�t�t�@�C���ȊO�A�폜�ȊO�̏ꍇ
						if (StringUtil.isEmpty(bean.getSequence()) 
								&& !"1".equals(bean.getFileDeleteFlag())) {
							// �Y�t�t�@�C������
							String fileName = bean.getAttachmentFileName();
							String suffix = fileName.lastIndexOf(".") < 0 ? "" : fileName.substring(fileName.lastIndexOf("."));
							// �A�b�v���[�h�t�@�C������
							String uploadFileName = formBean.getPageId() + String.valueOf(intAttCount) + suffix;
							bean.setAttUploadFileName(uploadFileName);
							// �Y�t�t�@�C��URL(�t�@�C�����ƃA�h���X)
							bean.setAttachmentFileUrl(filePath + "/" + uploadFileName);
							// �Y�t�t�@�C������
							bean.setAttachmentName(bean.getAttachmentFileName());
							FileUtil.uploadFile(bean.getAttachment(), bean.getAttachment().getName(), tempPageAttachment);
							// �ꎞ�ۑ��A�b�v���[�h�t�@�C������
							String file = tempPageAttachment + File.separator + bean.getAttachment().getName();
							bean.setAttachment(new File(file));
							intAttCount = intAttCount + 1;
						}
						// �����Y�t���݃t���O��ݒ�
						if (!"1".equals(bean.getFileDeleteFlag())){
							// �����Y�t���݃t���O
							formBean.setAttachmentExistFlag("1");
						}
					}
				}
				
				// ���R���e���c��HTML�t�@�C���쐬
				createHtmlFile(formBean);
				// �R���e���cURL
				pageFileUrl = tempFileUrl + "/" + formBean.getPageId() + ".html";
				// �R���e���cURL
				formBean.setPageFileUrl(pageFileUrl);

			} else{
				//�@�ÓI�R���e���c�̏ꍇ
				// �ꎞ�i�[�t�H���_���폜
				FileUtil.deletePurgeFile(tempStaticFilePath, formBean.getPageId());
				
				// �����Y�t���X�g�i�ÓI�R���e���c�͈���R�[�h�̂ݑ��݁j
				List<PageAttachmentBean> fileList = formBean.getPageAttachmentList();
				// �Y�t�t�@�C�����������ꍇ
				if (fileList != null && !StringUtil.isEmpty(fileList.get(0).getAttachmentFileName())) {
					// �Y�t�t�@�C��Bean
					PageAttachmentBean bean = fileList.get(0);
					// �Y�t�t�@�C������
					String fileName = bean.getAttachmentFileName();
					// �g���q
					String fileSuffix = fileName.lastIndexOf(".") >= 0 ? fileName.substring(fileName.lastIndexOf(".")) : "";
					// ZIP�t�@�C���̏ꍇ
					if (bean.getAttachmentFileName().toLowerCase().endsWith(".zip")) {
						String mainFile = formBean.getMainFile();
						String suffix = mainFile.lastIndexOf(".") >= 0 ? mainFile.substring(mainFile.lastIndexOf(".")): "";
						String mainName = formBean.getPageId() + suffix;
						
						// �t�@�C�����A�b�v���[�h
						FileUtil.uploadFile(bean.getAttachment(), formBean.getPageId() + ".zip", tempStaticFilePath);
						String zipUploadName = tempStaticFilePath + File.separator + formBean.getPageId() + ".zip";
						
						// ZIP�t�@�C���`�F�b�N
						zipFileCheck(zipUploadName, mainFile);

						// ZIP�̏ꍇ�A�i�[�p�X�̓y�[�WID�̎q�t�H���_�[�Ɋi�[
						String uploadPath = tempStaticFilePath + File.separator + formBean.getPageId() + File.separator;
						File uploadPathFile = new File(uploadPath);
						// ���݂����ꍇ�A�폜
						if (uploadPathFile.exists()) {
							uploadPathFile.delete();
						}
						// �t�H���_�[���쐬
						uploadPathFile.mkdirs();

						// ZIP�t�@�C�����𓀂��A��ZIP�t�@�C�����폜����B
						FileUtil.unzipFile(uploadPath, zipUploadName, mainFile, mainName);
						
						List<File> filelist = new ArrayList<File>();
						
						getFileList(uploadPath, filelist);
						for (int i = 0; i < filelist.size(); i++) {
							replaceMeta(filelist.get(i));
							replaceLink(filelist.get(i), mainName, mainFile);
						}
						// �R���e���cURL
						pageFileUrl = tempFileUrl + "/" + formBean.getPageId() + "/" + mainName;
						// �t�@�C���g���q
						formBean.setFileSuffix(mainName);
					} else{
						// �R���e���cURL
						pageFileUrl = tempFileUrl + "/" + formBean.getPageId() + fileSuffix;
						// �t�@�C�����A�b�v���[�h
						FileUtil.uploadFile(bean.getAttachment(), formBean.getPageId() + fileSuffix, tempStaticFilePath);
						if (bean.getAttachmentFileName().toLowerCase().endsWith(".html") 
								|| bean.getAttachmentFileName().toLowerCase().endsWith(".htm")) {
							replaceMeta(new File(tempStaticFilePath + "\\" + formBean.getPageId() + fileSuffix));
						}
						// �t�@�C���g���q
						formBean.setFileSuffix(fileSuffix);
					}
					// �R���e���cURL
					formBean.setPageFileUrl(pageFileUrl);
				} else{
					// �Y�t�t�@�C�����Ȃ��ꍇ
					// �R���e���c�X�V�̏ꍇ
					if ("EDIT".equals(formBean.getPageViewFlag())){
						// �y�[�WBean
						PageBean pageBean = new PageBean();
						String fileUrlPath = StringUtil.EMPTY;
						String htmlFilePath = StringUtil.EMPTY;
						// 1(�\��)�̏ꍇ
						if ("1".equals(formBean.getUpdateradio())){
							//�@�R���e���c�\����̎擾
							pageBean = getPageRsvDB(formBean.getPageId());
							fileUrlPath = FsPropertyUtil.getStringProperty("fileUrl.temp.path");
							htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
						} else {
							//�@�R���e���c���̎擾
							pageBean = getPageDB(formBean.getPageId());
							fileUrlPath = FsPropertyUtil.getStringProperty("fileUrl.path");
							htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.path");
						}
						// �R���e���c��񂪑��݂��ĂȂ��ꍇ�A�ȍ~�̏����𒆎~����B
						if (pageBean == null) {
							// �G���[��ʂ֑J��
							optimisticLockCheck(formBean.getPageId(), formBean.getUpdateradio());
						}
						
						String fileSuffix = StringUtil.nullToBlank(pageBean.getFileSuffix());
						String fileName = "";
						if (CommonUtil.checkStaticFileSuffix(formBean.getPageId(), fileSuffix)) {
							fileName = formBean.getPageId();
							// �R���e���cURL
							pageFileUrl = fileUrlPath + "/" + formBean.getPageId() + "/"+ fileSuffix;
						} else {
							fileName = formBean.getPageId() + fileSuffix;
							// �R���e���cURL
							pageFileUrl = fileUrlPath + "/" + formBean.getPageId() + fileSuffix;
						}
						// �u�\�񒆁v�ȊO�̏ꍇ
						if (!"1".equals(formBean.getUpdateradio())){
							FileUtil.fileCopyForStatic(htmlFilePath, tempStaticFilePath, fileName, fileName);
						}
						// �R���e���cURL
						formBean.setPageFileUrl(pageFileUrl);
						// �t�@�C���g���q
						formBean.setFileSuffix(pageBean.getFileSuffix());
					}
					// �e���v���[�g����̏ꍇ
					if ("1".equals(formBean.getIsTemplateFrom())){
						// �e���v���[�g�R���e���c�����擾
						String templatePageId = formBean.getTemplatePageId();
						PageBean pageBean = templatePageService.getTemplatePagebyID(templatePageId);
						String fileSuffix = pageBean.getFileSuffix();
						String fromFileName = StringUtil.EMPTY;
						String toFileName = StringUtil.EMPTY;
						String fileUrlPath = FsPropertyUtil.getStringProperty("template.fileUrl.path")+ "/";
						String templateHtmlFilePath = FsPropertyUtil.getStringProperty("template.htmlFile.path") 
								+ File.separator + pageBean.getTemplateCreateBy();
						if (!StringUtil.isEmpty(fileSuffix) && !fileSuffix.startsWith(".")) {
							pageFileUrl = fileUrlPath + pageBean.getTemplateCreateBy() + "/" + templatePageId + "/" + fileSuffix;
							fileSuffix = fileSuffix.replaceAll(templatePageId, formBean.getPageId());
							fromFileName = templatePageId;
							toFileName = formBean.getPageId();
			                FileUtil.copyStaticForTemplate(templateHtmlFilePath, tempStaticFilePath, templatePageId, formBean.getPageId());
						} else {
							pageFileUrl = fileUrlPath + pageBean.getTemplateCreateBy() + "/" + templatePageId + fileSuffix;
							fromFileName = templatePageId + fileSuffix;
							toFileName = formBean.getPageId() + fileSuffix;
		                    FileUtil.copyFile(templateHtmlFilePath, tempStaticFilePath, fromFileName, toFileName);
						}
						// �R���e���cURL
						formBean.setPageFileUrl(pageFileUrl);
						// �t�@�C���g���q
						formBean.setFileSuffix(fileSuffix);
					}
				}
				// �����Y�t���݃t���O
				formBean.setAttachmentExistFlag("1");
			}
		} catch (BusinessServiceException e) {
			throw e;
		} catch (Exception e){
			// �t�@�C���T�[�o�[
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * �ÓIhtml�R���e���c��<meta>�ǉ�
	 * @param file
	 */
	private void replaceMeta(File file){
		boolean isMetaFlag = false;
		boolean isHeadFlag = false;
		boolean isHTML5Flag = false;
		String[] trimChar = {" ", "�@"};
		String metaInfo = "";
		try {
			String charset = getEncodeTextStr(file);
			String metaCharset = "utf-8".equals(charset) ? "utf-8" : "shift_jis";
			List<String> readList = getReadFileList(file, charset);
			for (int i = 0; i < readList.size(); i++) {
				String readLine = StringUtil.lowerCase(readList.get(i));
				// �擪��<!DOCTYPE>�Ƃ��Đ錾�����̂�HTML5�ƔF������
				// HTML5����F��s�ځi�S�p�A���p�X�y�[�X�s�q�h�l�j��<!DOCTYPEHTML>
				if (StringUtil.alltrimer(trimChar, readLine).indexOf("<!doctypehtml>") >= 0) {
					isHTML5Flag = true;
				}
				// head���ݔ���
				if (readLine.indexOf("<head>") >= 0) {
					isHeadFlag = true;
				}
				// �u<meta�v�A�ucharset�v�Ƃ���������T���āA
				if (readLine.indexOf("<meta") >= 0 && readLine.indexOf("charset") >= 0) {
					// ����ꍇ�A�������Ȃ�
					isMetaFlag = true;
				}
			}
			// HTML5�̏ꍇ�A<meta charset="�w�w�w�w";>��ݒ�
			if (isHTML5Flag) {
				metaInfo = "<meta charset=\"" + metaCharset + "\">";
			// HTML5�ȊO�̏ꍇ�A<meta http-equiv=Content-Type content="text/html; charset=XXXXX">��ݒ�
			} else {
				metaInfo = "<meta http-equiv=Content-Type content=\"text/html; charset=" + metaCharset + "\">";
			}
			// meta�����ꍇ�A
			if (!isMetaFlag) {
				// <head>���݂̏ꍇ
				if (isHeadFlag) {
					for (int i = 0; i < readList.size(); i++) {
						String readLine = readList.get(i);
						// <head></head>����charset�ݒ�
						int charAt = StringUtil.lowerCase(readLine).indexOf("<head>");
						if (charAt >= 0) {
							readLine = readLine.substring(0, charAt + 6) + metaInfo + readLine.substring(charAt + 6);
						}
						readList.set(i, readLine);
					}
				// <head>�s���݂̏ꍇ�A<head>�{charset�ݒ�
				} else {
					readList.add(0, "<head>" + metaInfo + "</head>");
				}
				// �t�@�[�������܂�
				writeFileList(file, readList, charset);
			}
		} catch (Exception e) {
			throw new LocalRuntimeException("�t�@�C�������ɂāA�G���[���������܂����B", e);
		}
	}
	
	/**
	 * �ÓIhtml�R���e���c�̃����N�t�@�[�����u��
	 * @param file
	 */
	private void replaceLink(File file, String mainName, String mainFile) {
		if (mainFile.indexOf("/") > 0 && mainFile.indexOf("/") + 1 < mainFile.length()) {
			mainFile = mainFile.substring(mainFile.indexOf("/") + 1);
		}
		boolean isMainName = false;
		try{
			String charset = getEncodeTextStr(file);
			List<String> readList = getReadFileList(file, charset);
			for (int i = 0; i < readList.size(); i++) {
				String readLine = StringUtil.lowerCase(readList.get(i));
				int mainFileAt = readLine.indexOf(StringUtil.lowerCase(mainFile));
				// �����N�Ƀt�@�[�������݂̏ꍇ
				if (mainFileAt >= 0) {
					isMainName = true;
				}
			}
			// �����N�Ƀt�@�[�������݂̏ꍇ�A�u������
			if (isMainName) {
				for (int i = 0; i < readList.size(); i++) {
					String readLine = readList.get(i);
					int mainFileAt = StringUtil.lowerCase(readLine).indexOf(StringUtil.lowerCase(mainFile));
					// �����N�Ƀt�@�[�������݂̏ꍇ
					if (mainFileAt >= 0) {
						// �����N�t�@�[�����u���@�t�@�C�������y�[�W��
						readLine = readLine.substring(0, mainFileAt) + mainName + readLine.substring(mainFileAt + mainFile.length());
					}
					
					readList.set(i, readLine);
					// �t�@�[�������܂�
					writeFileList(file, readList, charset);
				}
			}
		} catch (Exception e) {
			throw new LocalRuntimeException("�t�@�C�������ɂāA�G���[���������܂����B", e);
		}
	}
	
	/**
	 * �t�@�[�������܂�
	 * @param file
	 * @param readList
	 * @param charset
	 * @throws Exception
	 */
	private void writeFileList(File file, List<String> readList, String charset) throws Exception{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), charset));
			for (int i = 0; i < readList.size(); i++){
				String readLine = readList.get(i);
				writer.write(readLine);
				writer.newLine();
			}
			writer.flush();
		} catch (Exception e) {
			throw new LocalRuntimeException("�t�@�C�������ɂāA�G���[���������܂����B", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	/**
	 * �t�@�[����荞��
	 * @param file
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	private List<String> getReadFileList(File file, String charset) throws Exception{
		BufferedReader reader = null;
		List<String> result = new ArrayList<String>();
		String readLine = "";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));
			while ((readLine = reader.readLine()) != null) {
				result.add(readLine);
			}
		} catch (Exception e) {
			throw new LocalRuntimeException("�t�@�C�������ɂāA�G���[���������܂����B", e);
		} finally {
			if (reader != null){
				reader.close();
				reader = null;
			}
		}
		return result;
	}
	
	/**
	 * �R���e���c�G���R�[�h����APageViewAction���Q�Ƃ���
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	private String getEncodeTextStr(File file) throws IOException {
		
		boolean canEncode = true;
		String[] charsetNameArray = {"shift_jis", "ms932", "utf-8"};
		String result = "utf-8";
		
		for (int i=0; i<charsetNameArray.length; i ++) {
			String charsetName = charsetNameArray[i];
			canEncode = getEncodeTextStrByCharsetName(file, charsetName, canEncode);
			if (canEncode) {
				result = charsetName;
				break;
			}
		}
		return result;
	}
	
	/**
	 * �R���e���c�G���R�[�h����APageViewAction���Q�Ƃ���
	 * @param fileUrl
	 * @return
	 * @throws IOException
	 */
	private boolean getEncodeTextStrByCharsetName(File file, String charsetName, boolean canEncode) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, charsetName));
		String line = "";
		while ((line = br.readLine()) != null) {
			
			if(!StringUtil.isEmpty(charsetName)){
				if (!Charset.forName(charsetName).newEncoder().canEncode(line)) {
					canEncode = false;
					break;
				}
				canEncode = true;
			}
		}
		br.close();
		return canEncode;
	}
	
	/**
	 * ZIP�̑S�ăt�@�[���p�[�X���擾
	 * @param strPath
	 * @param filelist
	 * @return
	 */
	public static List<File> getFileList(String strPath, List<File> filelist) {
		List<File> files = new ArrayList<File>();
		files = FileUtil.getFileList(strPath, files);
		for (int i = 0; i < files.size(); i++) {
			File file = files.get(i);
			String fileName = StringUtil.lowerCase(file.getName());
			if (fileName.endsWith("html") || fileName.endsWith("htm")) {
				filelist.add(file);
			}
		}
		return filelist;
	}
	
	/**
	 * �R���e���c�]����񑊊֏���
	 * @param fromBean �t�H�[���@
	 */
	private void pageRateRelated(PageFormBean formBean) {
		// �R���e���c�]���t���O���u����v�̏ꍇ
		if ("1".equals(formBean.getEvaluationFlag())) {
			// �R���e���c�]���A�C�e����񃊃X�g
			List<PageRateItemBean> pageRateItemBeanList = formBean.getPageRateItemBeanList();
			// �]�����ڃ��X�g�����͂��ꂽ�ꍇ�A���͕K�{����
			if (pageRateItemBeanList != null){
				// �R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
				for (PageRateItemBean pageRateItemBean : pageRateItemBeanList){
					// �]���J�E���g
					String evaluationCount = "0";
					// �u1�F���J�����܂ܕҏW�v�@�܂��́@�u�]�����N���A����v ���I�����ꂽ�@�܂��́u�V�K�ǉ��s�v�̏ꍇ�A�u0�v
					if ("1".equals(formBean.getOnEditFlag()) 
							|| "1".equals(formBean.getEvaluationClearFlag())
							|| StringUtil.isEmpty(pageRateItemBean.getSequence())) {
						evaluationCount = "0";
					// ��L�ȊO�̏ꍇ
					} else{
						// �R���e���c�m�F��ʂ̕]�������擾
						evaluationCount = pageDao.getEvaluationCount(formBean.getPageId(), pageRateItemBean.getSequence());
					}
					// Bean�ɕ]������ݒ�
					pageRateItemBean.setEvaluationCount(evaluationCount);
				}
			}
			
			// �R���e���c�R�����g���v���擾
			// �u1�F���J�����܂ܕҏW�v�@�܂��́@�u�]�����N���A����v ���I�����ꂽ�ꍇ�A�u0�v
			if ("1".equals(formBean.getOnEditFlag()) 
					|| "1".equals(formBean.getEvaluationClearFlag())){
				formBean.setCommentCount(0);
			} else{
				// �R���e���c�R�����g���v�����擾
				formBean.setCommentCount(pageDao.getCommentCount(formBean.getPageId()));
			}
		}
	}
	
	/**
	 * �h���b�v�_�E�����X�g�쐬
	 * @param fromBean �t�H�[���@
	 * @throws ParseException 
	 */
	public void getDropdownList(PageFormBean formBean) throws ParseException {
		//�@��������MAP
		Map<Object, Object> param = null;
		
		// �Ώێ҃h���b�v�_�E�����X�g���쐬
		param = new HashMap<Object, Object>();
		// �t�B�[���h��
		param.put("PARA_FIELD_NAME", "TARGET_USER");
		// �Ώێ҃��X�g���擾
		formBean.setUserDivisionDropList(pageDao.getUserDivisionDropDownList(param));
		
		// ���M�����h���b�v�_�E�����X�g���쐬
		formBean.setSourceDeptDropList(commonDao.getDepartmentList());

	}

	/**
	 * �ΏێґI���敪��ݒ肷��B
	 * @param fromBean �t�H�[���@
	 */
	private void setUserType(PageFormBean formBean) {
		// �ΏێґI���敪���X�y�[�X�̏ꍇ
		if (StringUtil.isEmpty(formBean.getUserType())) {
			// �Ώێ҂��X�y�[�X�̏ꍇ
			if (StringUtil.isNotEmpty(formBean.getUserDivision())){
				Map UserDivisionMap = new HashMap();
				// �Ώێ҃h���b�v���X�g��NULL�ł͂Ȃ��ꍇ
				List userDivisionDropList = formBean.getUserDivisionDropList();
				if (!StringUtil.isBlank(userDivisionDropList)) {
					for(int i = 0; i < userDivisionDropList.size(); i++) {
						LabelValueBean labelValueBean = (LabelValueBean) userDivisionDropList.get(i);
						UserDivisionMap.put(labelValueBean.getValue(), labelValueBean.getLabel());
					}
				}
				// �Ώێ҃h���b�v���X�g�Ɂu�Ώێҁv���܂߂�ꂽ�ꍇ
				if (UserDivisionMap.containsKey(formBean.getUserDivision())) {
					// �ΏێґI���ɐݒ�
					formBean.setUserType("0");
				} else{
					// �Ώێғ��͂ɐݒ�
					formBean.setUserType("1");
					// �Ώێғ���
					formBean.setUserDivisionR(formBean.getUserDivision());
				}
			} else{
				formBean.setUserType("0");				
			}
		}
	}
	
	/**
	 * ���񍐑Ή��҃��X�g��ݒ�
	 * @param fromBean �t�H�[���@
	 * @param userId ���[�UID
	 */
	private void setPageCommentAdminList(PageFormBean formBean, String userId) {
		// ���[�U���̂��擾�i���񍐑Ή��ҁj
		String strUserName = pageDao.getCreateUserName(userId);
		// �쐬���[�UID
		formBean.setCreateBy(userId);
		// ���[�U���̂��X�y�[�X�ł͂Ȃ��ꍇ�A�u�R���e���c�R�����g�Ǘ��ҏ��I�����X�g�A�R���e���c�R�����g�Ǘ��ҏ�񃊃X�g�v�Ƀ��O�C�����[�U���i�[
		if (!StringUtil.isEmpty(strUserName)) {
			PageCommentAdminBean bean = new PageCommentAdminBean();
			List list = new ArrayList();
			// ���[�UID
			bean.setUserId(userId);
			// ���[�U����
			bean.setUserName(strUserName);
			list.add(bean);
			// �R���e���c�R�����g�Ǘ��ҏ��I�����X�g�Ɋi�[
			formBean.setPageCommentAdminOptionList(list);
			// �R���e���c�R�����g�Ǘ��ҏ�񃊃X�g�Ɋi�[
			formBean.setPageCommentAdminList(list);
			// �쐬����
			formBean.setCreateUserName(strUserName);
		}
	}	
	
	/**
	 * �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)���֏���
	 * @param fromBean �t�H�[���@
	 */
	private void pageCommentAdminRelated(PageFormBean formBean) {
		// �R���e���c�R�����g�Ǘ��ҏ��I��(���񍐑Ή���)�͉�ʂ���t�H�[���Ɋi�[
		List pageCommentAdminOptionList = new ArrayList();
		String commentAdminOptionUserId = formBean.getCommentAdminOptionUserId();
		String commentAdminOptionUserName = formBean.getCommentAdminOptionUserName();
		// ��ʂ̖��񍐑Ή��ґI�����X�g�ɐݒ�l���������ꍇ
		if (StringUtil.isNotEmpty(commentAdminOptionUserId)) {
			String[] userId = commentAdminOptionUserId.split(",");
			String[] userName = commentAdminOptionUserName.split(",");
			for (int i = 0; i < userId.length; i ++) {
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(userId[i]);
				bean.setUserName(userName[i]);
				pageCommentAdminOptionList.add(bean);
			}			
		}
		// �t�H�[���Ɋi�[
		formBean.setPageCommentAdminOptionList(pageCommentAdminOptionList);
		
		// �R���e���c�R�����g�Ǘ��ҏ��(���񍐑Ή���)�͉�ʂ���t�H�[���Ɋi�[
		List pageCommentAdminList = new ArrayList();
		String commentAdminUserId = formBean.getCommentAdminUserId();
		String commentAdminUserName = formBean.getCommentAdminUserName();
		// ��ʂ̖��񍐑Ή��ґI�����X�g�ɐݒ�l���������ꍇ
		if (StringUtil.isNotEmpty(commentAdminUserId)) {
			String[] userId = commentAdminUserId.split(",");
			String[] userName = commentAdminUserName.split(",");
			for (int i = 0; i < userId.length; i ++) {
				PageCommentAdminBean bean = new PageCommentAdminBean();
				bean.setUserId(userId[i]);
				bean.setUserName(userName[i]);
				pageCommentAdminList.add(bean);
			}			
		}
		// �t�H�[���Ɋi�[
		formBean.setPageCommentAdminList(pageCommentAdminList);

	}
	
	/**
	 * ���J�m�F���t��X�V�t���O(���J���t�ύX�Ȃ�)�\���t���O��ݒ�
	 * @param fromBean �t�H�[���@
	 * @throws Exception 
	 */
	private void setViewConfirmNoupdateFlag(PageFormBean formBean, PageBean pageBean) throws Exception {
		boolean viewNotFlag = false;
		formBean.setViewConfirmNoupdateFlag("0");
		// �R���e���c��񂪑��݂����ꍇ
		if (pageBean != null) {
			// ���J������X�y�[�X�̏ꍇ
			if (StringUtil.isEmpty(pageBean.getConfirmDate())){
				viewNotFlag = true;
			} else {
				viewNotFlag = false;				
			}
		} else {
			// �R���e���c��񂪑��݂��Ȃ��ꍇ
			viewNotFlag = true;
		}
		if (!viewNotFlag){
			formBean.setViewConfirmNoupdateFlag("1");
		}
	}
	
	/**
	 * ���R���e���c���̎擾(��ʕ\���p)
	 * @param param�@
	 * @return�@
	 */
	public PageFormBean getPageForm(String pageId) throws Exception {
		
		PageFormBean formBean = null;

		// �R���e���c�����擾
		PageBean pageBean = pageDao.getPage(pageId);
		if (pageBean != null) {
			formBean = new PageFormBean();
			WrappedBeanUtil.copyProperties(formBean, pageBean);
			
			// ���R���e���c
			if (ConstantContainer.PAGE_DYNAMIC.equals(pageBean.getHtmlFlag())) {
				// �R���e���c�����N�����擾
				formBean.setPageLinkList(pageDao.getPageLinkList(pageId));
				
				// �R���e���c�Y�t�t�@�C�������擾
				formBean.setPageAttachmentList(pageDao.getPageAttachList(pageId));
			}
			
			// �R���e���c�{�������g�b�v�O���[�v�����擾
			formBean.setAuthGroupList(pageDao.getAuthGroupList(pageId));
			
			// �R���e���c�{���������[�U�����擾
			formBean.setAuthUserList(pageDao.getAuthUserList(pageId));
			
			// �X�V��s�ҏ������擾
			formBean.setProxyUserList(pageDao.getProxyUserList(pageId));
			
			// �R���e���c�]�����X�g
			formBean.setPageRateItemBeanList(pageDao.getPageRateItemList(pageId));
			
			// �R���e���c�R�����g�Ǘ��҃��X�g
			formBean.setPageCommentAdminList(pageDao.getCommentAdminList(pageId));
			
			// �R���e���c�R�����g�Ǘ��ґI�����X�g
			formBean.setPageCommentAdminOptionList(pageDao.getCommentAdminOptList(pageId));
		}

		return formBean;
		
	}
	/**
	 * �\��R���e���c���̎擾(��ʕ\���p)
	 * @param param�@
	 * @return�@
	 */
	public PageFormBean getPageRsvForm(String pageId) throws Exception {
		
		PageFormBean formBean = null;

		// �R���e���c�����擾
		PageBean pageBean = pageDao.getPageReserve(pageId);
		if (pageBean != null) {
			formBean = new PageFormBean();
			WrappedBeanUtil.copyProperties(formBean, pageBean);
			
			// ���R���e���c
			if (ConstantContainer.PAGE_DYNAMIC.equals(pageBean.getHtmlFlag())) {
				// �R���e���c�����N�����擾
				formBean.setPageLinkList(pageDao.getPageLinkListRsv(pageId));
				
				// �R���e���c�Y�t�t�@�C�������擾
				formBean.setPageAttachmentList(pageDao.getPageAttachListRsv(pageId));
				
			}
			
			// �R���e���c�{�������g�b�v�O���[�v�����擾
			formBean.setAuthGroupList(pageDao.getAuthGroupListRsv(pageId));
			
			// �R���e���c�{���������[�U�����擾
			formBean.setAuthUserList(pageDao.getAuthUserListRsv(pageId));
			
			// �X�V��s�ҏ������擾
			formBean.setProxyUserList(pageDao.getProxyUserListRsv(pageId));
			
			// �R���e���c�]�����X�g
			formBean.setPageRateItemBeanList(pageDao.getPageRateItemListRsv(pageId));
			
			// �R���e���c�R�����g�Ǘ��҃��X�g
			formBean.setPageCommentAdminList(pageDao.getCommentAdminListRsv(pageId));
			
			// �R���e���c�R�����g�Ǘ��ґI�����X�g
			formBean.setPageCommentAdminOptionList(pageDao.getCommentAdminOptListRsv(pageId));
		}

		return formBean;
	}
	
	
	/**
	 * �y�[�WID�ɂ��AHashMap�`���̌��J����O���[�v���ʂ��擾
	 * �Ăяo����
	 * A)�q�R���e���c���֍Đݒ�
	 * 
	 * @param param�@
	 * @return�@
	 */
	private HashMap<String, String> getAuthGroupMap(String pageId, String reserveFlag) {
		
		HashMap<String, String> authGroupMap = new HashMap<String, String>();
		List<AuthGroupBean> authGroupList = null;
		if ("reserve".equals(reserveFlag)) {
			authGroupList = pageDao.getAuthGroupListRsv(pageId);
		} else {
			authGroupList = pageDao.getAuthGroupList(pageId);
		}
		for(int i=0; i<authGroupList.size(); i++) {
			AuthGroupBean bean = (AuthGroupBean)authGroupList.get(i);
			authGroupMap.put(bean.getTopGroupId(), "");
		}
		return authGroupMap;
	}
	
	/**
	 * �y�[�WID�ɂ��AHashMap�`���̌��J����l���ʂ��擾
	 * �Ăяo����
	 * A)�q�R���e���c���֍Đݒ�
	 * 
	 * @param param�@
	 * @return�@
	 */
	private HashMap<String, String> getAuthUserMap(String pageId, String reserveFlag) {
		
		HashMap<String, String> authUserMap = new HashMap<String, String>();
		List<AuthUserBean> authUserList = null;
		List<ProxyUserBean> proxyUserList = null;
		if ("reserve".equals(reserveFlag)) {
			authUserList = pageDao.getAuthUserListRsv(pageId);
			proxyUserList = pageDao.getProxyUserListRsv(pageId);
		} else {
			authUserList = pageDao.getAuthUserList(pageId);
			proxyUserList = pageDao.getProxyUserList(pageId);
		}
		for(int i=0; i<authUserList.size(); i++) {
			AuthUserBean bean = (AuthUserBean)authUserList.get(i);
			authUserMap.put(bean.getUserId(), "");
		}
		for(int i=0; i<proxyUserList.size(); i++) {
			ProxyUserBean bean = (ProxyUserBean)proxyUserList.get(i);
			authUserMap.put(bean.getProxyUserId(), "");
		}
		return authUserMap;
	}

	/**
	 * �y�[�WID�ɂ��AHashMap�`���̏��F�Ҍ��ʂ��擾
	 * �Ăяo����
	 * A)�q�R���e���c���֍Đݒ�
	 * 
	 * @param param�@
	 * @return�@
	 */
	private HashMap<String, String> getProxyUserMap(String pageId, String reserveFlag) {
		
		HashMap<String, String> proxyUserMap = new HashMap<String, String>();
		List<ProxyUserBean> proxyUserList = null;
		if ("reserve".equals(reserveFlag)) {
			proxyUserList = pageDao.getProxyUserListRsv(pageId);
		} else {
			proxyUserList = pageDao.getProxyUserList(pageId);
		}
		for(int i=0; i<proxyUserList.size(); i++) {
			ProxyUserBean bean = (ProxyUserBean)proxyUserList.get(i);
			proxyUserMap.put(bean.getProxyUserId(), "");
		}
		return proxyUserMap;
	}

	/**
	 * ���R���e���c���̎擾
	 * @param param�@
	 * @return�@
	 */
	public PageBean getPageDB(String pageId) {
		return pageDao.getPage(pageId);
	}

	/**
	 * �\��R���e���c���̎擾
	 * @param param�@
	 * @return�@
	 */
	public PageBean getPageRsvDB(String pageId) {
		return pageDao.getPageReserve(pageId);
	}

	/**
	 * ���O�C�����[�U�[�͊Y�R���e���c�̉{�������������Ă��邩�ǂ������f����
	 * @param param�@
	 * @return�@
	 */
	public boolean getViewRight(PageFormBean pageForm) {
		LoginUserBean loginUser = getLoginUserBean();
		
		//�@�V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)
		if ("1".equals(loginUser.getRole())) {
			return true;
		} 
		
		//�A���O�C�����[�U���Y���R���e���c�̌��J����l�ł���@���@(�Y�R���e���c��ԁ����J�� OR ���O�C�����[�U���Y���R���e���c�쐬�҂ł���)
		List userList = pageForm.getAuthUserList();
		if (userList != null) {
			for (int i = 0; i < userList.size(); i++) {
				AuthUserBean user = (AuthUserBean) userList.get(i);
				if (loginUser.getUserId().equals(user.getUserId())) {
					if ("0".equals(pageForm.getConfirmFlag()) || loginUser.getUserId().equals(pageForm.getCreateBy())) {
						return true;
					}
				}
			}
		}

		//�B���O�C�����[�U�̏����g�b�v�O���[�v���Y���R���e���c�̌��J����g�b�v�O���[�v�ł���@���� (�Y�R���e���c��ԁ����J�� OR ���O�C�����[�U���Y���R���e���c�쐬�҂ł���)
		List groupList = pageForm.getAuthGroupList();
		if (groupList != null) {
			List loginGroupList = loginUser.getTopGroupList();

			if (loginGroupList != null) {
				for (int i = 0; i < groupList.size(); i++) {
					AuthGroupBean group = (AuthGroupBean) groupList.get(i);

					if (loginGroupList.contains(group.getTopGroupId())) {
						if ("0".equals(pageForm.getConfirmFlag()) || loginUser.getUserId().equals(pageForm.getCreateBy())) {
							return true;
						}
					} 
				}
			}
		}
		return false;
	}
	
	/**
	 * ���O�C�����[�U�[�͊Y�R���e���c�̍X�V�����������Ă��邩�ǂ������f����
	 * @param param�@
	 * @return�@
	 */
	public boolean getEditRight(PageFormBean pageForm) {
		LoginUserBean loginUser = getLoginUserBean();
		
		//�@�V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)
		if ("1".equals(loginUser.getRole())) {
			return true;
		} 
		
		//�A���O�C�����[�U���Y���R���e���c�쐬�҂ł���A���A�V�X�e�����p�敪��WEB�S���ҁAWEBADMIN�ł���
		if (loginUser.getUserId().equals(pageForm.getCreateBy()) &&
				 ("4".equals(loginUser.getRole()) || "6".equals(loginUser.getRole()))) {
			return true;
		}
		
		//�B���O�C�����[�U���Y���R���e���c�̍X�V��s�҂ł���
		List proxyList = pageForm.getProxyUserList();
		if (proxyList != null) {
			for (int i = 0; i < proxyList.size(); i++) {
				ProxyUserBean proxy = (ProxyUserBean) proxyList.get(i);
				if (loginUser.getUserId().equals(proxy.getProxyUserId())) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * ���O�C�����[�U�[�͊Y�R���e���c�̎������J�����������Ă��邩�ǂ������f����
	 * @param param�@
	 * @return�@
	 */
	public boolean getOpenRight(PageFormBean pageForm) {
		LoginUserBean loginUser = getLoginUserBean();
		
		//�Y�R���e���c��ԁ��ҏW��
		if ("1".equals(pageForm.getConfirmFlag())) {
			//�@�V�X�e�����p�敪��'1'(�V�X�e���Ǘ���)
			if ("1".equals(loginUser.getRole())) {
				return true;
			} 
			
			//�A���O�C�����[�U���Y���R���e���c�쐬�҂ł���A���A�V�X�e�����p�敪��WEBADMIN�ł���
			if (loginUser.getUserId().equals(pageForm.getCreateBy()) && "6".equals(loginUser.getRole())) {
				return true;
			}
			
			//�B���O�C�����[�U���Y���R���e���c�̍X�V��s�҂ł���
			List proxyList = pageForm.getProxyUserList();
			if (proxyList != null) {
				for (int i = 0; i < proxyList.size(); i++) {
					ProxyUserBean proxy = (ProxyUserBean) proxyList.get(i);
					if (loginUser.getUserId().equals(proxy.getProxyUserId()) && "6".equals(loginUser.getRole())) {
						return true;
					}
				}
			}
		}

		return false;
	}
	
	
	/**
	 * �R���e���cDB�X�V�����O�AO/R�}�b�s���OBean�����H����
	 * @param param�@
	 * @return�@
	 */
	private void setPageInfo(PageFormBean pageDbOperBean) {
		//�@�uNULL�v����u�[���v�ɕϊ��@�Ⴆ�΁A��ʂ̃`�F�b�N�{�b�N�X���I������Ȃ��ꍇ�A�u�[���v�ɕύX
		convertFlagNullToZero(pageDbOperBean);
		
		// �t�@�C��URL(�t�@�C�����ƃA�h���X)
		pageDbOperBean.setHtmlFileUrl(pageDbOperBean.getContextPath() + "/pageView_view.do");
		
		// �Ώێҁ��Ώێғ���
		if ("1".equals(pageDbOperBean.getUserType())) {
			pageDbOperBean.setUserDivision(pageDbOperBean.getUserDivisionR());
		}

		// �R���e���c�]���t���O:���Ȃ�
		if ("0".equals(pageDbOperBean.getEvaluationFlag())) {
			// �]���Ҏ����\���t���O
			pageDbOperBean.setEvaluatorDisplayFlag("0");
			// �]���҃R�����g�ҏW�t���O
			pageDbOperBean.setCommentEditFlag("0");
			// �����]���L���t���O
			pageDbOperBean.setPluralEvaluationFlag("0");
			// �]�����N���A����t���O
			pageDbOperBean.setEvaluationClearFlag("0");
		}
	}
	
	/**
	 * �R���e���c����o�^�E�X�V
	 * @param pageForm �t�H�[��Bean
	 * @throws Exception 
	 */
	public void insertOrUpdatePage(PageFormBean formBean) throws Exception {
		// �t�H�[��Bean����y�[�WDB�X�VBean�ɃR�s�[
		PageFormBean pageDbOperBean =  new PageFormBean();
		WrappedBeanUtil.copyProperties(pageDbOperBean, formBean);

		// �R���e���c���ݒ�
		setPageInfo(pageDbOperBean);

		// �y�[�W�z�u��Đݒ�@���́@�e���v���[�g����̏ꍇ
		if ("1".equals(pageDbOperBean.getPageLocationSetFlag())
				|| "1".equals(pageDbOperBean.getIsTemplateFrom())){
			// �������@�e�R���e���c�����Y�R���e���c���̂��ׂăR���e���c�͕\�����{�P�ɍX�V
			updateBrothersOrderBy(pageDbOperBean);
		}

		// �R���e���c���o�^�̏ꍇ
		if ("ADD".equals(formBean.getPageViewFlag())){
			// ���R���e���c���̓o�^
			insertPage(pageDbOperBean);
		} else{
			// �R���e���c���X�V�̏ꍇ
			// �u1�F���J�����܂ܕҏW�v�ȊO�̏ꍇ
			if (!"1".equals(formBean.getOnEditFlag())){
				// ���R���e���c���̍X�V
				updatePage(pageDbOperBean);
			} else{
				// �u1�F���J�����܂ܕҏW�v�̏ꍇ
				// �\��R���e���c���̓o�^�i�폜���Ă���o�^�������s���@�j
				insertPageRsv(pageDbOperBean);
			}
		}
		
		// �����Y�t���A�b�v���[�h
		uploadFileRelated(pageDbOperBean);
		
		//�������@�R���e���c�̔z�u��̐ݒ�
		formBean.setPageLocation(getPageLocation(formBean.getPageId()));

		// �u�������J�v�A�u���[�����M�v���쐧��t���O
		formBean.setOpenEnableFlag("1");

		// ���R���e���c�A�\��R���e���c�𔻒f
		String isReserve = "0";
		// �u1(�����܂ܕҏW)�v�̏ꍇ�A�\��@����ȊO�ɂ͎��R���e���c
		if ("1".equals(formBean.getOnEditFlag())){
			isReserve = "1";
		}
		formBean.setIsReserve(isReserve);
		
		// �q�R���e���c���f���O
		formBean.setUpdateChildLog(pageDbOperBean.getUpdateChildLog());
		
		// ���̃R���e���c��URL��ݒ�
		setPageUrl(formBean);

	}
	
	/**
	 * �����Y�t���A�b�v���[�h
	 * @param pageDbOperBean
	 * @throws Exception 
	 */
	private void uploadFileRelated(PageFormBean pageDbOperBean) throws Exception {
		try {
			// ���R���e���c
			if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {

				String filefrom = StringUtil.EMPTY;
				// HTML�t�@�C���i�\��j���폜
		        filefrom = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
		        FileUtil.deletePurgeFile(filefrom, pageDbOperBean.getPageId());
		        //�@�u�\�񒆁v�ȊO�̏ꍇ
		        if (!"1".equals(pageDbOperBean.getUpdateradio())) {
		        	// �A�b�v���[�h�t�@�C���i�\��j���폜
			        filefrom = FsPropertyUtil.getStringProperty("server.upload.temp.path");
			        FileUtil.deletePurgeFile(filefrom, pageDbOperBean.getPageId());
		        }
				
				// �t�@�C���p�X
				String filePath = "";
				String serverUploadPath = FsPropertyUtil.getStringProperty("server.upload.path");
				String serverUploadTempPath = FsPropertyUtil.getStringProperty("server.upload.temp.path");
				// �u1(���J�����܂ܕҏW����)�v�̏ꍇ
				if("1".equals(pageDbOperBean.getOnEditFlag())) {
					filePath = serverUploadTempPath;
				} else {
					filePath = serverUploadPath;
				}
				
				// ��ʂɂč폜���ꂽ�t�@�C�����폜
				List<PageAttachmentBean> fileList = pageDbOperBean.getPageAttachmentList();
				// �R���e���c�X�V�̏ꍇ
				if ("EDIT".equals(pageDbOperBean.getPageViewFlag())){
					if (fileList != null) {
						String copyto = FsPropertyUtil.getStringProperty("batch.file");
						for (int i = 0; i < fileList.size(); i++) {
							PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
							// �����Y�t�t�@�C���@���@��ʂɂč폜���ꂽ�ꍇ
							if (!StringUtil.isBlank(bean.getSequence())
									&& "1".equals(bean.getFileDeleteFlag())) {
								String fileUrl = bean.getAttachmentFileUrl();
								String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
								// �u�����܂ܕҏW�v�ȊO�̏ꍇ�A��ʂɂč폜���ꂽ�t�@�C�����폜
								if (!"1".equals(pageDbOperBean.getOnEditFlag())) {
									// �폜�ςݓY�t�t�@�C���A�p�[�W�̃t�H���_���ړ�����
									FileUtil.copyFile(serverUploadPath, copyto, fileName);
									FileUtil.deleteFile(serverUploadPath, fileName);
								} else {
									// �폜�ςݓY�t�t�@�C�����폜
									FileUtil.deleteFile(serverUploadTempPath, fileName);
								}
							}
						}
					}					
				}

				// A.��ʂŃA�b�v���[�h�t�@�C�����A�b�v���[�h����B
				// B.�R���e���c�t�@�C���t�H���_����R���e���c�\��t�H���_�ɃR�s�[
				// C.�e���v���[�g�R���e���c�t�@�C���t�H���_����R���e���c�t�H���_�ɃR�s�[
				if (fileList != null) {
					int count = 0;
					int index = 0;
					for (PageAttachmentBean bean : fileList) {
						if (StringUtil.isEmpty(bean.getSequence()) 
								&& !"1".equals(bean.getFileDeleteFlag())) {
							index = index + 1;
						}
					}

					for (PageAttachmentBean bean : fileList) {
						// A.��ʂŃA�b�v���[�h�t�@�C�����A�b�v���[�h����B
						//�����Y�t�t�@�C���ȊO�A�폜�ȊO�̏ꍇ
						if (StringUtil.isEmpty(bean.getSequence()) 
								&& !"1".equals(bean.getFileDeleteFlag())) {
							// �Y�t�t�@�C�����A�b�v���[�h����
							FileUtil.uploadFile(bean.getAttachment(), bean.getAttUploadFileName() , filePath);
							// �ꎞ�t�@�C�����폜����B
							if (bean.getAttachment().exists()){
								bean.getAttachment().delete();
							}
						}
						
						// �e���v���[�g����̏ꍇ
						if ("1".equals(pageDbOperBean.getIsTemplateFrom())){
							// C.�e���v���[�g�R���e���c�t�@�C���t�H���_����R���e���c�t�H���_�ɃR�s�[
							if (!StringUtil.isEmpty(bean.getSequence())
									&& !"1".equals(bean.getFileDeleteFlag())){
								String fileUrl = bean.getAttachmentFileUrl();
								String suffix = fileUrl.substring(fileUrl.indexOf("."));
								String[] fileUrlArr = fileUrl.split("/");
								String folderurl = FsPropertyUtil.getStringProperty("attachmentFile.url");
								String toFileName = pageDbOperBean.getPageId() + (index + count) + suffix;
								count ++;
								String serverTemplateUploadPath = FsPropertyUtil.getStringProperty("template.server.upload.path") 
										+ File.separator + fileUrlArr[fileUrlArr.length-2];
								FileUtil.copyFile(serverTemplateUploadPath , serverUploadPath, fileUrlArr[fileUrlArr.length-1], toFileName);
								bean.setAttachmentFileUrl(folderurl + "/" + toFileName);
							}							
						} else{
							// B.�R���e���c�t�@�C���t�H���_����R���e���c�\��t�H���_�ɃR�s�[
							//�����Y�t�t�@�C���A�폜�ȊO�A�u���J���v�̏ꍇ
							if (!StringUtil.isEmpty(bean.getSequence()) 
									&& !"1".equals(bean.getFileDeleteFlag())
									&& ("0".equals(pageDbOperBean.getUpdateradio())
											|| (StringUtil.isEmpty(pageDbOperBean.getUpdateradio()) && "1".equals(pageDbOperBean.getOnEditFlag())))) {
								String fileUrl = bean.getAttachmentFileUrl();
								String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
								// ���t�H���_�ɃR�s�[
								FileUtil.copyFile(serverUploadPath, serverUploadTempPath, fileName);
							}							
						}
					}
				}
				// ���R���e���c��HTML�t�@�C���쐬
				createHtmlFile(pageDbOperBean);

			} else{
				String filefrom = StringUtil.EMPTY;
		        if (StringUtil.isEmpty(pageDbOperBean.getUpdateradio())) {
			        filefrom = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
			        FileUtil.deletePurgeFile(filefrom, pageDbOperBean.getPageId());
		        }
				
				// �ÓI�R���e���c�̏ꍇ
				// �����Y�t���X�g�i�ÓI�R���e���c�͈���R�[�h�̂ݑ��݁j
				List<PageAttachmentBean> fileList = pageDbOperBean.getPageAttachmentList();
				boolean fileChangeFlag = false;
				// �Y�t�t�@�C�����������ꍇ
				if (fileList != null && !StringUtil.isEmpty(fileList.get(0).getAttachmentFileName())) {
					fileChangeFlag = true;
				} else{
					// �u�\�񒆁v�ȊO�̏ꍇ
					if (!"1".equals(pageDbOperBean.getUpdateradio())){
						fileChangeFlag = true;
					}
					// �e���v���[�g����̏ꍇ
					if ("1".equals(pageDbOperBean.getIsTemplateFrom())){
						fileChangeFlag = true;
					}
				}

				// �t�@�C�����ς�����ꍇ
				if (fileChangeFlag) {
					// �ÓI�R���e���c�ꎞ�ۑ��p�X
					String tempStaticFilePath = StringUtil.EMPTY;
					// �t�@�C���p�X
					String htmlFilePath = "";
					// �u1(���J�����܂ܕҏW����)�v�̏ꍇ
					if("1".equals(pageDbOperBean.getOnEditFlag())) {
						tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.temp.path");
						htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
					} else {
						tempStaticFilePath = FsPropertyUtil.getStringProperty("temp.path");
						htmlFilePath = FsPropertyUtil.getStringProperty("htmlFile.path");
					}
					
					// �ꎞ�i�[�t�H���_���폜
					FileUtil.deletePurgeFile(htmlFilePath, pageDbOperBean.getPageId());
					
					String fileSuffix = StringUtil.nullToBlank(pageDbOperBean.getFileSuffix());
					String fileName = "";
					if (CommonUtil.checkStaticFileSuffix(pageDbOperBean.getPageId(), fileSuffix)) {
						fileName = pageDbOperBean.getPageId();
					} else {
						fileName = pageDbOperBean.getPageId() + fileSuffix;
					}
					FileUtil.fileCopyForStatic(tempStaticFilePath, htmlFilePath, fileName, fileName);

					// �ꎞ�i�[�t�H���_���폜
					FileUtil.deletePurgeFile(tempStaticFilePath, pageDbOperBean.getPageId());
				}
			}
		} catch (Exception e) {
			// �t�@�C���T�[�o�[
			String fileSever = FsPropertyUtil.getStringProperty("file.server");
			// �T�[�o�ɐڑ��ł��Ȃ��ꍇ�A�G���[
			if (!FileUtil.ping(fileSever)){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ���R���e���c���̓o�^
	 * @param pageDbOperBean�@
	 */
	private void insertPage(PageFormBean pageDbOperBean) throws Exception {
		
		//�쐬���擾
		BaseBean createInfo = null;

		//�������@�R���e���c���o�^����
		PageBean page = new PageBean();
		//��ʓ��͂��ꂽ�l��Bean�Ɋi�[
		WrappedBeanUtil.copyProperties(page, pageDbOperBean);

		//�쐬���[�UID
		page.setCreateBy(getLoginUserBean().getUserId());
		//�쐬����
		page.setCreateDate(new Date());
		//�X�V���[�UID
		page.setUpdateBy(getLoginUserBean().getUserId());
		//�X�V����
		page.setUpdateDate(new Date());

		//�R���e���c���o�^
		pageDao.insertPage(page);
		
		//�������@�q�R���e���c�֏��Đݒ�
		updateChildPage(pageDbOperBean, createInfo);
		
		//���R���e���c
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {
			//�������@�����N���̏���
			insertPageLink(pageDbOperBean, createInfo, "add");

			//�������@�Y�t�������̏���
			insertPageAttach(pageDbOperBean, createInfo, "add");
		}

		//�������@���J����O���[�v���̏���
		insertAuthGroup(pageDbOperBean, createInfo, "add");

		//�������@���J����l���̏���
		insertAuthUser(pageDbOperBean, createInfo, "add");
		
		//�������@�X�V��s�ҏ��̏���
		insertProxyUser(pageDbOperBean, createInfo, "add");
		
		// �R���e���c�]������F�`�F�b�N����
		if ("1".equals(pageDbOperBean.getEvaluationFlag())) {
			
			//�������@�]���A�C�e���̏���
			insertPageRateItem(pageDbOperBean, createInfo, "add");
			
			//�������@���񍐑Ή��҂̏���
			insertPageCommentAdmin(pageDbOperBean, createInfo, "add");
		}
	}
	
	/**
	 * �\��R���e���c���̓o�^�i�폜���Ă���o�^�������s���@�j
	 * @param param�@
	 * @return�@
	 */
	private void insertPageRsv(PageFormBean pageDbOperBean) throws Exception {
		
		//�������@�R���e���c�\����o�^����
		PageBean page = new PageBean();
		//��ʓ��͂��ꂽ�l��Bean�Ɋi�[
		WrappedBeanUtil.copyProperties(page, pageDbOperBean);
		//�X�V��
		page.setUpdateBy(getLoginUserBean().getUserId());
		
		//�R���e���c���̍X�V
		//�������@�^���R���e���c�̌��J�ێ��ҏW�t���O�uON_EDIT_FLAG�v��1(���J�����܂ܕҏW�ƂȂ�)�ɍX�V
		int ret = pageDao.updateOnEditFlag(page);
		if (ret == 0){
			// �r���`�F�b�N
			optimisticLockCheck(page.getPageId(), "0");
		}

		//�쐬���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("page", "page_id", pageDbOperBean.getPageId());

		//�������@�\��R���e���c�폜
		deletePageRsv(pageDbOperBean);

		//�^���f�[�^�́u������J����v��\��f�[�^�ɐݒ�
		PageBean pagetemp = pageDao.getPage(pageDbOperBean.getPageId());
		page.setFirstConfirmDate(pagetemp.getFirstConfirmDate());
		
		//�쐬���[�UID�ƍ쐬����
		page.setCreateBy(createInfo.getCreateBy());
		page.setCreateDate(createInfo.getCreateDate());
		
		//�X�V���[�UID�ƍX�V����
		page.setUpdateBy(getLoginUserBean().getUserId());
		page.setUpdateDate(new Date());
		
		//�R���e���c�\����o�^
		pageDao.insertPageRsv(page);
		
		//�������@�q�R���e���c�֏��Đݒ�
		updateChildPage(pageDbOperBean, createInfo);
		
		//���R���e���c
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {
			//�������@�����N���̏���
			insertPageLink(pageDbOperBean, createInfo, "update");

			//�������@�Y�t�������̏���
			insertPageAttach(pageDbOperBean, createInfo, "update");
		}

		//�������@���J����O���[�v���̏���
		insertAuthGroup(pageDbOperBean, createInfo, "update");

		//�������@���J����l���̏���
		insertAuthUser(pageDbOperBean, createInfo, "update");
		
		//�������@�X�V��s�ҏ��̏���
		insertProxyUser(pageDbOperBean, createInfo, "update");

		// �R���e���c�]������F�`�F�b�N����
		if ("1".equals(pageDbOperBean.getEvaluationFlag())) {
			
			//�������@�]���A�C�e���̏���
			insertPageRateItem(pageDbOperBean, createInfo, "update");
			
			//�������@���񍐑Ή��҂̏���
			insertPageCommentAdmin(pageDbOperBean, createInfo, "update");
		}
	}

	/**
	 * ���R���e���c���̍X�V
	 * @param param�@
	 * @return�@
	 */
	private void updatePage(PageFormBean pageDbOperBean) throws Exception {
		//�������@�R���e���c���̍X�V
		PageBean page = new PageBean();
		//��ʓ��͂��ꂽ�l��Bean�Ɋi�[
		WrappedBeanUtil.copyProperties(page, pageDbOperBean);

		//�ҏW��
		page.setConfirmFlag("1");
		
		//�폜�ς݂̃f�[�^�͕���
		page.setDeleteFlag("0");
		
		//�X�V��
		page.setUpdateBy(getLoginUserBean().getUserId());

		//�R���e���c���̍X�V
		int ret = pageDao.updatePage(page);
		if (ret == 0){
			// �r���`�F�b�N
			optimisticLockCheck(page.getPageId(), "0");
		}

		//�쐬���擾
		BaseBean createInfo = commonDao.getDbCommonInfo("page", "page_id", pageDbOperBean.getPageId());       

		//�������@�\��R���e���c�폜�A�ȉ��̓�P�[�X�͊Y���\�b�h���Ăяo�����Ƃ��ł���
		//A�j�^���R���e���c�ҏW�A��ԁ��쐬��
		//B�j�^���R���e���c�ҏW�A��ԁ����J���A���J�����܂ܕҏW��I�����Ȃ��ꍇ�i���̃P�[�X�͗\��TBL���폜����v�j
		if("0".equals(pageDbOperBean.getOnEditFlag())) {
			deletePageRsv(pageDbOperBean);
		}

		//�������@���J���t�ύX�Ȃ��`�F�b�NON���A���O�e�[���𐧌䂵�Ȃ�
		if("0".equals(pageDbOperBean.getConfirmNoupdateFlag())){
			pageDao.deleteLogInfo(pageDbOperBean.getPageId(), getLoginUserBean().getUserId());
		}
		
		//�������@�q�R���e���c�֏��Đݒ�
		updateChildPage(pageDbOperBean, createInfo);
		
		//���R���e���c
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageDbOperBean.getHtmlFlag())) {
			//�������@�����N���̏���
			pageDao.deletePageExtend(pageDbOperBean.getPageId(), "PAGE_LINK");
			insertPageLink(pageDbOperBean, createInfo, "update");

			//�������@�Y�t�������̏���
			List fileList = pageDbOperBean.getPageAttachmentList();
			if (fileList != null) {
				for (int i = 0; i < fileList.size(); i++) {
					PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
					bean.setPageId(pageDbOperBean.getPageId());
					bean.setOrderBy(String.valueOf(i));
					//�����Y�t�t�@�C���̏ꍇ
					if (!StringUtil.isBlank(bean.getSequence())) {
						bean.setUpdateBy(getLoginUserBean().getUserId());
						//�����t�@�C���폜�̏ꍇ
						if ("1".equals(bean.getFileDeleteFlag())) {
							pageDao.deletePageAttachment(bean);
						//�����t�@�C���X�V�̏ꍇ
						} else {
							pageDao.updatePageAttachment(bean);
						}
					//�V�K�쐬�̓Y�t�t�@�C���̏ꍇ
					} else {
						if (!"1".equals(bean.getFileDeleteFlag())) {
							setCreateInfo(bean, createInfo, "update");
							pageDao.insertPageAttachment(bean);
						}
					}
				}
			}
		}

		//�������@���J����O���[�v���̏���
		pageDao.deletePageExtend(pageDbOperBean.getPageId(), "AUTH_LEADING_GROUP");
		insertAuthGroup(pageDbOperBean, createInfo, "update");

		//�������@���J����l���̏���
		pageDao.deletePageExtend(pageDbOperBean.getPageId(), "AUTH_USER");
		insertAuthUser(pageDbOperBean, createInfo, "update");
		
		//�������@�X�V��s�ҏ��̏���
		pageDao.deletePageExtend(pageDbOperBean.getPageId(), "UPDATE_PROXY_USER");
		insertProxyUser(pageDbOperBean, createInfo, "update");

		// �u�R���e���c�]������v���u����v�̏ꍇ
		if ("1".equals(pageDbOperBean.getEvaluationFlag())) {
			// �R���e���c�]���A�C�e�����Ɋւ��Ă�DB����
			doPageRateItemDbOper(pageDbOperBean, createInfo, "update");
			
			//�������@���񍐑Ή��҂̏���
			pageDao.deletePageExtend(pageDbOperBean.getPageId(), "PAGE_COMMENT_ADMIN");
			insertPageCommentAdmin(pageDbOperBean, createInfo, "update");

		}
	}
	
	/**
	 * �q�R���e���c�֏��Đݒ�i���J����l�A���J����O���[�v�A���F�ҁj
	 * @param param�@
	 * @return�@
	 */
	private void updateChildPage(PageFormBean pageForm, BaseBean createInfo) throws Exception {
		
		ChildPageFormBean childForm = pageForm.getChildPageFormBean();
		//������ 1�D�q�R���e���c�֏��Đݒ�̏��̓Z�b�g����Ă��Ȃ��ꍇ�A�������Ȃ�
		if (StringUtil.isBlank(childForm)) {
			return;
		}
		List<String> logList = new ArrayList<String>();	
		
		//������ 2�DDB����S�Ă̎q�R���e���c�擾
		HashMap<String, PageBean> childByDbMap = new HashMap<String, PageBean>();
		getAllOpenChildPageList(pageForm.getPageId(), childByDbMap);			
		
		//������ 3�D��ʂ̎q�R���e���c���J��Ԃ��ď�񔽉f
		List<ChildPageInheritSetBean> childByFormList = childForm.getInheritSetList();
		for(int j=0; j<childByFormList.size(); j++) {
			ChildPageInheritSetBean childFormBean = (ChildPageInheritSetBean)childByFormList.get(j);
			String pageId = childFormBean.getPageId();
			//�R���e���c�敪(0:�^���̂݁A1:�\��̂݁A2:����)-----�q�R���e���c���擾�Ŏg�p
			String subDivFlag = childFormBean.getPageDivision();

			//�������@3.1�@��ʓ��͂��ꂽ�R���e���c��DB�ɑ��݂��Ȃ��ꍇ�A���̃��R�[�h��
			if (!childByDbMap.containsKey(pageId + "," + subDivFlag)) {
				continue;
			} 
			
			//�������@3.2�@�O���[�v���̍X�V
			if ("1".equals(childFormBean.getIsGroupInherit())) {
				
				HashMap<String, String> groupMap = new HashMap<String, String>();
				HashMap<String, String> groupRsvMap = new HashMap<String, String>();
				
				//�������@3.2.1�@�O���[�v�p����ށ��u������
				if ("COVER".equals(childForm.getGroupInheritType())) {
					// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u�����폜����
					if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_LEADING_GROUP");
					}

					// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u�����폜����
					if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_LEADING_GROUP_RESERVE");
					}
				}
				//�������@3.2.2�@�O���[�v�p����ށ��ǉ�
				if ("ADD".equals(childForm.getGroupInheritType())) {
					//�q�R���e���c�̌��J����O���[�v����ޔ�
					groupMap = getAuthGroupMap(pageId, "");
					groupRsvMap = getAuthGroupMap(pageId, "reserve");
				}

				//�������@3.2.3�@�O���[�v�����J��Ԃ��Ďq�R���e���c�֔��f
				for (int i = 0; i < childForm.getAuthGroupList().size(); i++) {
					AuthGroupBean bean = (AuthGroupBean)childForm.getAuthGroupList().get(i);
					String delete = bean.getGroupDeleteFlag();
					String id = bean.getTopGroupId();
					
					bean.setPageId(pageId);
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u����ǉ�����
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (groupMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//�폜���Ă���o�^���s��
							pageDao.deleteAuthGroup(bean);
							pageDao.insertAuthGroup(bean);
							logList.add(pageId);
						}
						
						// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u����ǉ�����
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (groupRsvMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//�폜���Ă���o�^���s��
							pageDao.deleteAuthGroupRsv(bean);
							pageDao.insertAuthGroupRsv(bean);
							logList.add("��" + pageId);
						}
					}
				}
			}
			
			//�������@3.3�@���J����l���̍X�V
			if ("1".equals(childFormBean.getIsUserInherit())) {
				
				HashMap<String, String> userMap = new HashMap<String, String>();
				HashMap<String, String> userRsvMap = new HashMap<String, String>();
				
				//�������@3.3.1�@�p����ށ��u������
				if ("COVER".equals(childForm.getUserInheritType())) {
					// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u�����폜����
					if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_USER");
					}
					
					// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u�����폜����
					if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "AUTH_USER_RESERVE");
					}
				}
				//�������@3.3.2�@�p����ށ��ǉ�
				if ("ADD".equals(childForm.getUserInheritType())) {
					//�q�R���e���c�̌��J����l����ޔ�
					userMap = getAuthUserMap(pageId, "");
					userRsvMap = getAuthUserMap(pageId, "reserve");
				}

				//�������@3.3.3�@���[�U�[�����J��Ԃ��Ďq�R���e���c�֔��f
				for (int i = 0; i < childForm.getAuthUserList().size(); i++) {
					AuthUserBean bean = (AuthUserBean)childForm.getAuthUserList().get(i);
					String delete = bean.getUserDeleteFlag();
					String id = bean.getUserId();
					
					bean.setPageId(pageId);
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u����ǉ�����
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (userMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//�폜���Ă���o�^���s��
							pageDao.deleteAuthUser(bean);
							pageDao.insertAuthUser(bean);
							userMap.put(id, "");
							logList.add(pageId);
						}
						
						// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u����ǉ�����
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (userRsvMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//�폜���Ă���o�^���s��
							pageDao.deleteAuthUserRsv(bean);
							pageDao.insertAuthUserRsv(bean);
							userRsvMap.put(id, "");
							logList.add("��" + pageId);
						}
					}
				}
				
				//�������@3.3.4�@���F�҂��q�R���e���c�̌��J����l�֔��f
				for (int i = 0; i < childForm.getProxyUserList().size(); i++) {
					ProxyUserBean bean = (ProxyUserBean)childForm.getProxyUserList().get(i);
					String delete = bean.getProxyDeleteFlag();
					String id = bean.getProxyUserId();
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u����ǉ�����
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (!userMap.containsKey(id)) {
								AuthUserBean user = new AuthUserBean();
								user.setPageId(pageId);
								user.setUserId(id);
								user.setNecessityReadFlag("0");
								
								setCreateInfo(user, createInfo, "update");
								//�폜���Ă���o�^���s��
								pageDao.deleteAuthUser(user);
								pageDao.insertAuthUser(user);
								userMap.put(id, "");
								logList.add(pageId);
							}
						}
						
						// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u����ǉ�����
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (!userRsvMap.containsKey(id)) {
								AuthUserBean user = new AuthUserBean();
								user.setPageId(pageId);
								user.setUserId(id);
								user.setNecessityReadFlag("0");
								
								setCreateInfo(user, createInfo, "update");
								//�폜���Ă���o�^���s��
								pageDao.deleteAuthUserRsv(user);
								pageDao.insertAuthUserRsv(user);
								userRsvMap.put(id, "");
								logList.add("��" + pageId);
							}
						}
					}
				}
				//�������@3.3.5�@���O�C�����[�U�[���q�R���e���c�̌��J����l�֔��f
				// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u����ǉ�����
				if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
					
					if (!userMap.containsKey(getLoginUserBean().getUserId())) {
						AuthUserBean user = new AuthUserBean();
						user.setPageId(pageId);
						user.setUserId(getLoginUserBean().getUserId());
						user.setNecessityReadFlag("0");
						
						setCreateInfo(user, createInfo, "update");
						//�폜���Ă���o�^���s��
						pageDao.deleteAuthUser(user);
						pageDao.insertAuthUser(user);
						logList.add(pageId);
					}
				}
				
				// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u����ǉ�����
				if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
					if (!userRsvMap.containsKey(getLoginUserBean().getUserId())) {
						AuthUserBean user = new AuthUserBean();
						user.setPageId(pageId);
						user.setUserId(getLoginUserBean().getUserId());
						user.setNecessityReadFlag("0");
						
						setCreateInfo(user, createInfo, "update");
						//�폜���Ă���o�^���s��
						pageDao.deleteAuthUserRsv(user);
						pageDao.insertAuthUserRsv(user);
						logList.add("��" + pageId);
					}
				}
			}

			//������ 3.4 ���F�ҏ��̍X�V
			if ("1".equals(childFormBean.getIsProxyInherit())) {
				
				HashMap<String, String> proxyMap = new HashMap<String, String>();
				HashMap<String, String> proxyRsvMap = new HashMap<String, String>();
				
				//�������@3.2.1�@�O���[�v�p����ށ��u������
				if ("COVER".equals(childForm.getProxyInheritType())) {
					// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u�����폜����
					if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "UPDATE_PROXY_USER");
					}
					
					// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u�����폜����
					if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
						pageDao.deletePageExtend(pageId, "UPDATE_PROXY_USER_RESERVE");
					}
				}
				//�������@3.2.2�@�O���[�v�p����ށ��ǉ�
				if ("ADD".equals(childForm.getProxyInheritType())) {
					//�q�R���e���c�̌��J����O���[�v����ޔ�
					proxyMap = getProxyUserMap(pageId, "");
					proxyRsvMap = getProxyUserMap(pageId, "reserve");
				}

				//�������@3.2.3�@���F�ҏ����J��Ԃ��Ďq�R���e���c�֔��f
				for (int i = 0; i < childForm.getProxyUserList().size(); i++) {
					ProxyUserBean bean = (ProxyUserBean)childForm.getProxyUserList().get(i);
					String delete = bean.getProxyDeleteFlag();
					String id = bean.getProxyUserId();
					
					bean.setPageId(pageId);
					
					if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
						// �ǉ� ���݂̂Ɨ����𑶍݂���̏ꍇ�A�^���e�[�u����ǉ�����
						if ("0".equals(subDivFlag) || "2".equals(subDivFlag)) {
							
							if (proxyMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//�폜���Ă���o�^���s��
							pageDao.deleteProxyUser(bean);
							pageDao.insertProxyUser(bean);
							logList.add(pageId);
						}
						
						// �ǉ� �\��݂̂Ɨ����𑶍݂���̏ꍇ�A�\��e�[�u����ǉ�����
						if ("1".equals(subDivFlag) || "2".equals(subDivFlag)) {
							if (proxyRsvMap.containsKey(id)) {
								setCreateInfo(bean, createInfo, "update");
							} else {
								setCreateInfo(bean, createInfo, "add");
							}
							//�폜���Ă���o�^���s��
							pageDao.deleteProxyUserRsv(bean);
							pageDao.insertProxyUserRsv(bean);
							logList.add("��" + pageId);
						}
					}
				}
			}
		}
		pageForm.setUpdateChildLog(logList);
	}
	
	/**
	 * �Y�t��������DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertPageAttach(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageForm.getHtmlFlag())) {
			List fileList = pageForm.getPageAttachmentList();
			if (fileList != null) {
				int count = 0;
				int index = 0;
				for (int i = 0; i < fileList.size(); i++) {
					PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
					if (StringUtil.isEmpty(bean.getSequence()) 
							&& !"1".equals(bean.getFileDeleteFlag())) {
						index = index + 1;
					}
				}
				for (int i = 0; i < fileList.size(); i++) {
					PageAttachmentBean bean = (PageAttachmentBean)fileList.get(i);
					if (!"1".equals(bean.getFileDeleteFlag())) {
						bean.setOrderBy(String.valueOf(i));
						bean.setPageId(pageForm.getPageId());
						setCreateInfo(bean, createInfo, updateDiv);
						
						String url = bean.getAttachmentFileUrl();
						// �e���v���[�g����̏ꍇ
						if ("1".equals(pageForm.getIsTemplateFrom())){
							if (!StringUtil.isBlank(bean.getSequence())) {
								String suffix = url.substring(url.indexOf("."));
								String folderurl = FsPropertyUtil.getStringProperty("attachmentFile.url");
								String fullUrl = folderurl + "/" + pageForm.getPageId() + (index + count) + suffix;
								count ++;
								bean.setAttachmentFileUrl(fullUrl);
							}							
						}

						//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
						if("1".equals(pageForm.getOnEditFlag())) {
							pageDao.insertPageAttachmentRsv(bean);
						} else {
							pageDao.insertPageAttachment(bean);						
						}
						bean.setAttachmentFileUrl(url);
					}
				}
			}
		}		
	}
	
	/**
	 * �R���e���c�����N����DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�^���R���e���c�X�V����
	 * C)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertPageLink(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (pageForm.getPageLinkList() != null) {
			for (int i = 0; i < pageForm.getPageLinkList().size(); i++) {
				PageLinkBean bean = (PageLinkBean)pageForm.getPageLinkList().get(i);
				String linkDelete = bean.getLinkDeleteFlag();
				String linkName = bean.getLinkName();
				if (!"1".equals(linkDelete) && !StringUtil.isEmpty(linkName)) {
					bean.setPageId(pageForm.getPageId());
					setCreateInfo(bean, createInfo, updateDiv);

					//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertPageLinkRsv(bean);
					} else {
						pageDao.insertPageLink(bean);
					}
				}
			}
		}
	}
	
	/**
	 * �R���e���c���J����O���[�v��DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�^���R���e���c�X�V����
	 * C)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertAuthGroup(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (pageForm.getAuthGroupList() != null) {
			for (int i = 0; i < pageForm.getAuthGroupList().size(); i++) {
				AuthGroupBean bean = (AuthGroupBean)pageForm.getAuthGroupList().get(i);
				String delete = bean.getGroupDeleteFlag();
				String id = bean.getTopGroupId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
					
					bean.setPageId(pageForm.getPageId());
					// �K�{�{���敪
					bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
					setCreateInfo(bean, createInfo, updateDiv);

					//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertAuthGroupRsv(bean);
					} else {
						pageDao.insertAuthGroup(bean);
					}
				}
			}
		}
	}

	/**
	 * �R���e���c���J����l��DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�^���R���e���c�X�V����
	 * C)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertAuthUser(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		//���O�C�����[�U�[
		String loginUserID = getLoginUserBean().getUserId();
		List userIdList = new ArrayList();
		if (pageForm.getAuthUserList() != null) {
			for (int i = 0; i < pageForm.getAuthUserList().size(); i++) {
				AuthUserBean bean = (AuthUserBean)pageForm.getAuthUserList().get(i);
				String delete = bean.getUserDeleteFlag();
				String userId = bean.getUserId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(userId)) {
					
					bean.setPageId(pageForm.getPageId());
					// �K�{�{���敪
					bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
					setCreateInfo(bean, createInfo, updateDiv);

					userIdList.add(userId);
					//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertAuthUserRsv(bean);
					} else {
						pageDao.insertAuthUser(bean);
					}
				}
			}
		}
		
		//�X�V��s�҂��{�����[�U�[���X�g�Ɋi�[
		if (pageForm.getProxyUserList() != null) {
			for (int i = 0; i < pageForm.getProxyUserList().size(); i++) {
				ProxyUserBean bean = (ProxyUserBean)pageForm.getProxyUserList().get(i);
				String delete = bean.getProxyDeleteFlag();
				String id = bean.getProxyUserId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(id) && !userIdList.contains(id) ) {
					
					AuthUserBean user = new AuthUserBean();
					user.setNecessityReadFlag("0");
					user.setUserId(id);
					user.setPageId(pageForm.getPageId());
					setCreateInfo(user, createInfo, updateDiv);

					userIdList.add(id);
					//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertAuthUserRsv(user);
					} else {
						pageDao.insertAuthUser(user);
					}
				}
			}
		}
		
		//�R���e���c�쐬�ҁ@OR�@�X�V�҂��{�����[�U�[���X�g�Ɋi�[
		if (!userIdList.contains(loginUserID)) {
			AuthUserBean user = new AuthUserBean();
			user.setNecessityReadFlag("0");
			user.setUserId(loginUserID);
			user.setPageId(pageForm.getPageId());
			setCreateInfo(user, createInfo, updateDiv);
			
			//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
			if("1".equals(pageForm.getOnEditFlag())) {
				pageDao.insertAuthUserRsv(user);
			} else {
				pageDao.insertAuthUser(user);
			}
		}
	}

	/**
	 * �R���e���c���F�҂�DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�^���R���e���c�X�V����
	 * C)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertProxyUser(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		if (pageForm.getProxyUserList() != null) {
			for (int i = 0; i < pageForm.getProxyUserList().size(); i++) {
				ProxyUserBean bean = (ProxyUserBean)pageForm.getProxyUserList().get(i);
				String delete = bean.getProxyDeleteFlag();
				String id = bean.getProxyUserId();
				if (!"1".equals(delete) && !StringUtil.isEmpty(id)) {
					
					bean.setPageId(pageForm.getPageId());
					setCreateInfo(bean, createInfo, updateDiv);

					//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
					if("1".equals(pageForm.getOnEditFlag())) {
						pageDao.insertProxyUserRsv(bean);
					} else {
						pageDao.insertProxyUser(bean);
					}
				}
			}
		}
	}

	/**
	 * �R���e���c�]���A�C�e����DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertPageRateItem(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		List pageRateItemList = pageForm.getPageRateItemBeanList();
		if (pageRateItemList != null) {
			for (int i = 0; i < pageRateItemList.size(); i++) {
				PageRateItemBean bean = (PageRateItemBean)pageRateItemList.get(i);
				bean.setPageId(pageForm.getPageId());
				bean.setEvaluationOrderBy((i + 1) + "");
				
				setCreateInfo(bean, createInfo, updateDiv);

				//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
				if("1".equals(pageForm.getOnEditFlag())) {
					if(StringUtil.isEmpty(bean.getSequence())) {
						bean.setSequence("0");
						pageDao.insertPageRateItemRsv(bean);
					} else {
						pageDao.insertPageRateItemRsv(bean);
					}
				} else {
					pageDao.insertPageRateItem(bean);
				}
			}
		}
	}

	/**
	 * �R���e���c���Ή��҂�DB�X�V����
	 * �Ăяo����
	 * A)�^���R���e���c�o�^����
	 * B)�^���R���e���c�X�V����
	 * C)�\��R���e���c�o�^����
	 * 
	 * @param param�@
	 * @return�@
	 */
	private void insertPageCommentAdmin(PageFormBean pageForm, BaseBean createInfo, String updateDiv) {
		List pageCommentAdminList = pageForm.getPageCommentAdminList();
		if (pageCommentAdminList != null) {
			for (int i = 0; i < pageCommentAdminList.size(); i++) {
				PageCommentAdminBean bean = (PageCommentAdminBean)pageCommentAdminList.get(i);
				bean.setPageId(pageForm.getPageId());
				
				setCreateInfo(bean, createInfo, updateDiv);

				//���J�����܂ܕҏW�̏ꍇ�A�\��f�[�^�쐬
				if("1".equals(pageForm.getOnEditFlag())) {
					pageDao.insertPageCommentAdminRsv(bean);
				} else {
					pageDao.insertPageCommentAdmin(bean);
				}
			}
		}
	}
	
	/**
	 * DB�X�V�����O�A���ʍ��ڂ̐ݒ�
	 * @param param�@
	 * @return�@
	 */
	private void setCreateInfo(BaseBean bean, BaseBean createInfo, String updateDiv ) {
		if ("update".equals(updateDiv)) {
			bean.setCreateBy(createInfo.getCreateBy());
			bean.setCreateDate(createInfo.getCreateDate());
		} else {
			bean.setCreateBy(getLoginUserBean().getUserId());
			bean.setCreateDate(new Date());
		}
		bean.setUpdateBy(getLoginUserBean().getUserId());
		bean.setUpdateDate(new Date());
	}
	
	/**
	 * �Y�R���e���c�z�u�ʒu�w�肵����ŁA�e�R���e���c�����Y�R���e���c���̂��ׂăR���e���c�͕\�����{�P�ɍX�V
	 * @param param�@
	 * @return�@
	 */
	public void updateBrothersOrderBy(PageFormBean pageForm) throws Exception {
		pageDao.updateBrothersOrderBy(pageForm.getPageId(), pageForm.getPPageId(), pageForm.getOrderBy(), getLoginUserBean().getUserId());
		// �z�񏇁��z�񏇁{�P
		pageForm.setOrderBy(String.valueOf(NumberUtil.toInt(pageForm.getOrderBy()) + 1));
	}

	/**
	 * �p�����[�^�i�y�[�WID�j�����̎q�R���e���c���i�ċA�Ăяo���Ďq�w�A���w���ׂĒ��o�j
	 * 
	 * ���p�ӏ��F
	 * �P�j�q�R���e���c���Đݒ�̍X�V����
	 * 
	 * @param param�@
	 * @return�@
	 */
	public void getAllOpenChildPageList(String pageId, HashMap<String, PageBean> allOpenChildPageMap) throws Exception {
		List allOpenChildPageList = new ArrayList(); 
		this.getAllOpenChildPageList(pageId, allOpenChildPageList);
		for(int i=0; i<allOpenChildPageList.size(); i++){
			PageBean page=(PageBean)allOpenChildPageList.get(i);
			allOpenChildPageMap.put(page.getPageId() + "," + page.getPageDivision(), page);
		}
	}

	/**
	 * �\��R���e���c���̕����폜
	 * @param param�@�t�H�[��Bean
	 */
	public void deletePageRsv(PageFormBean pageForm) {
		// ���I�R���e���c�̏ꍇ
		if (ConstantContainer.PAGE_DYNAMIC.equals(pageForm.getHtmlFlag())) {
			// �R���e���c�����N�\������폜
			pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_LINK_RESERVE");
			// �R���e���c�Y�t�t�@�C���\������폜
			pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_ATTACHMENT_RESERVE");
		}
		// �R���e���c�{�������g�b�v�O���[�v�\������폜
		pageDao.deletePageExtend(pageForm.getPageId(), "AUTH_LEADING_GROUP_RESERVE");
		// �R���e���c�{���������[�U�\������폜
		pageDao.deletePageExtend(pageForm.getPageId(), "AUTH_USER_RESERVE");
		// �X�V��s�җ\������폜
		pageDao.deletePageExtend(pageForm.getPageId(), "UPDATE_PROXY_USER_RESERVE");
		// �R���e���c�]���A�C�e���\������폜
		pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_RATE_ITEM_RESERVE");
		// �R���e���c�R�����g�Ǘ��җ\����
		pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_COMMENT_ADMIN_RESERVE");
		// �R���e���c�\������폜
		pageDao.deletePageExtend(pageForm.getPageId(), "PAGE_RESERVE");
	}
	
	/**
	 * �R���e���c�z�u��̎擾
	 * �Ⴆ��[HOME]-[�ē�/�ʒB]-[�R���e���c�z�u��\��]
	 * @param param�@
	 * @return�@
	 */
	public String getPageLocation(String PageId ) {
		String strLocation = new String();
		Boolean flag=true;
		String pageid=PageId;
		String strP_Title="";
		int count =0;
		while(flag){
			if("0".equals(pageid)){	  
				strLocation="[HOME]"+strLocation;
				flag = false; 
			} else {
				PageBean page_check=pageDao.getPage(pageid);
				if (page_check == null) {
					return "";
				}
				if("1".equals(page_check.getOnEditFlag()) && count ==0){
					PageBean page=pageDao.getPageReserve(pageid);
					if (page == null) {
						return "";
					}
					strP_Title="["+page.getTitle()+"]";
					pageid=page.getPPageId();
					count=count+1;
				} else {
					PageBean page=pageDao.getPage(pageid);
					if (page == null) {
						return "";
					}
					strP_Title="["+page.getTitle()+"]";
					pageid=page.getPPageId();  
				}
				strLocation="-"+strP_Title+strLocation;
			}
		}
		return strLocation;
	}
	
	//�R���e���c�z�u��A�Ⴆ��[HOME]-[�ē�/�ʒB]-[�R���e���c�z�u��\��]
	public String getPageLocationOpen(String PageId) {
		String strHaiTiSaki=new String();
		Boolean flag=true;
		String pageid=PageId;
		while(flag){
			if ("0".equals(pageid)) {
				strHaiTiSaki="[HOME]"+strHaiTiSaki;
				flag = false; 
			} else {
				PageBean page=pageDao.getPage(pageid);
				if (page == null) {
					return "";
				}
				String strP_Title="["+page.getTitle()+"]";
				strHaiTiSaki="-"+strP_Title+strHaiTiSaki;
				pageid=page.getPPageId();
			}
		}
		return strHaiTiSaki;
	}
	
	/**
	 * �R���e���c�]���A�C�e�����Ɋւ��Ă�DB����
	 * @param pageDbOperBean
	 */
	private void doPageRateItemDbOper(PageFormBean pageDbOperBean, BaseBean createInfo, String updateDiv) {
		// �e�[�u������R���e���c�]���A�C�e�������擾
		List dbPageRateItemList = pageDao.getPageRateItemList(pageDbOperBean.getPageId());
		if (dbPageRateItemList != null){
			// �擾�����R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
			for (int i=0; i<dbPageRateItemList.size();i++){
				// DB�R���e���c�]���A�C�e�����
				PageRateItemBean dbPageRateItemBean = (PageRateItemBean) dbPageRateItemList.get(i);
				//2.��ʓ��͂��ꂽ�]���A�C�e���ɂ��ADB�X�V�������s��
				List<PageRateItemBean> formPageRateItemList = pageDbOperBean.getPageRateItemBeanList();
				Boolean deleteFlag = true;
				if (formPageRateItemList != null) {
					// ��ʓ��͂̃R���e���c�]���A�C�e����񃊃X�g�ɂ��A���L�̏������J��Ԃ�
					for (PageRateItemBean formPageRateItemBean:formPageRateItemList){
						// DB�A��ʗ������݂����ꍇ
						if (dbPageRateItemBean.getSequence().equals(formPageRateItemBean.getSequence())){
							deleteFlag = false;
							break;
						}
					}
				}
				// DB�ɑ��݁A��ʂɑ��݂��Ȃ��ꍇ�ADB�_���폜���s��
				if (deleteFlag){
					pageDao.updateEvaluationInvalidBySeq(dbPageRateItemBean.getPageId(), 
							dbPageRateItemBean.getSequence(), getLoginUserBean().getUserId());
				}
			}
		}
		// ��ʓ��͂��ꂽ�]���A�C�e���ɂ��ADB�X�V�������s��
		List pageRateItemList = pageDbOperBean.getPageRateItemBeanList();
		if (pageRateItemList != null) {
			for (int i = 0; i < pageRateItemList.size(); i++) {
				PageRateItemBean itemBean = (PageRateItemBean)pageRateItemList.get(i);
				itemBean.setPageId(pageDbOperBean.getPageId());
				itemBean.setEvaluationOrderBy(String.valueOf(i+1));
				
				setCreateInfo(itemBean, createInfo, updateDiv);
				
				//�V�K�ǉ�
				if (StringUtil.isEmpty(itemBean.getSequence())) {
					itemBean.setUpdateBy(getLoginUserBean().getUserId());
					itemBean.setCreateBy(getLoginUserBean().getUserId());
					pageDao.insertPageRateItem(itemBean);
				} else {
					//�X�V
					itemBean.setUpdateBy(getLoginUserBean().getUserId());
					pageDao.updatePageRateItem(itemBean);
				}
			}
		}
	}
	
	/**
	 * ���R���e���c��HTML�t�@�C���쐬
	 * @param param�@
	 * @return�@
	 */
	public void createHtmlFile(PageFormBean pageForm) throws Exception {
		// �y�[�WID
		String pageId = pageForm.getPageId();
		// �A�b�v���[�h�p�X
		String uploadPath="";
		// �u�����܂ܕҏW�v�̏ꍇ
		if("1".equals(pageForm.getOnEditFlag())) {
			uploadPath = "CONFIRM".equals(pageForm.getCreateHtmlEvent()) ? "temp.temp.path" : "htmlFile.temp.path";
		} else {
			uploadPath = "CONFIRM".equals(pageForm.getCreateHtmlEvent()) ? "temp.path" : "htmlFile.path";
		}
		uploadPath = FsPropertyUtil.getStringProperty(uploadPath);
		// �t�@�C����
		String fileName = pageId + ".html";
		File path = new File(uploadPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		File fileTo = new File(uploadPath, fileName);
		BufferedWriter fo = new BufferedWriter(new FileWriter(fileTo));
		String htmlStr = new FreemarkerUtil().getTemplateStr(pageForm, "html_page_create.ftl", "UTF-8");
		fo.write(htmlStr);
		fo.flush();
		fo.close();
	}
	
	/**
	 * �p�����[�^�i�y�[�WID�j�����̎q�R���e���c���i�ċA�Ăяo���Ďq�w�A���w���ׂĒ��o�j
	 * 
	 * ���p�ӏ��F
	 * �P�j�q�R���e���c���Đݒ�̎q��ʏ����\��
	 * �Q�j�q�R���e���c���Đݒ�̍X�V����
	 * 
	 * ������getSubBrotherList3�̑����
	 * @param param�@
	 * @return�@
	 */
	public void getAllOpenChildPageList(String pageId, List allOpenChildPageList) throws Exception {
		List childList = this.getOpenChildPageList(pageId);
		getAllOpenChildPageList(childList, allOpenChildPageList);

	}
	
	private void getAllOpenChildPageList(List childList, List allOpenChildPageList) throws Exception {
		if (childList != null && childList.size() > 0) {
			allOpenChildPageList.addAll(childList);
		}

		List childListEach = new ArrayList();

		for(int i=0; i<childList.size(); i++){
			PageBean page = (PageBean)childList.get(i);
			List childList1 = this.getOpenChildPageList(page.getPageId());
			childListEach.addAll(childList1);
		}
		if (childListEach != null && childListEach.size() > 0) {
			this.getAllOpenChildPageList(childListEach, allOpenChildPageList);
		}
	}
	
	/**
	 * �p�����[�^�i�y�[�WID�j�����̎q�R���e���c��񒊏o
	 * �P�j���o��ԁF�^���Ɨ\�񗼕��@���@��ԁ����J��
	 * �Q�j�q�w�̂ݒ��o
	 * 
	 * @param param�@
	 * @return�@
	 */
	public List getOpenChildPageList(String pageId) throws Exception {
		// �^���R���e���c
		return pageDao.getOpenChildPageList(pageId);
	}
	
	/**
	 * �uNULL�v����u�[���v�ɕϊ��@�Ⴆ�΁A��ʂ̃`�F�b�N�{�b�N�X���I������Ȃ��ꍇ�A�u�[���v�ɕύX
	 * @param formBean�@�t�H�[��Bean
	 */
	private void convertFlagNullToZero(PageFormBean formBean){
		// ���J�J�n����t���O
		formBean.setStartdateOpenFlag(StringUtil.nullToZero(formBean.getStartdateOpenFlag()));
		// ����J�t���O
		formBean.setConfirmFlag(StringUtil.nullToZero(formBean.getConfirmFlag()));
		// �\�����J�t���O
		formBean.setReserveConfirmFlag(StringUtil.nullToZero(formBean.getReserveConfirmFlag()));
		// �\���t���O
		formBean.setDisplayFlag(StringUtil.nullToZero(formBean.getDisplayFlag()));
		// �����N�t���O
		formBean.setLinkFlag(StringUtil.nullToZero(formBean.getLinkFlag()));
		// ��ʃt���O
		formBean.setHtmlFlag(StringUtil.nullToZero(formBean.getHtmlFlag()));
		// ���������N�\���s�t���O
		formBean.setDenyLinkfileFlag(StringUtil.nullToZero(formBean.getDenyLinkfileFlag()));
		// �_�E�����[�h�s�t���O
		formBean.setDenyDownloadFlag(StringUtil.nullToZero(formBean.getDenyDownloadFlag()));
		// �V������\���t���O
		formBean.setNewUndisplayFlag(StringUtil.nullToZero(formBean.getNewUndisplayFlag()));
		// ���J�m�F���t��X�V�t���O
		formBean.setConfirmNoupdateFlag(StringUtil.nullToZero(formBean.getConfirmNoupdateFlag()));
		// �V�K�\���ێ��t���O
		formBean.setNewKeepFlag(StringUtil.nullToZero(formBean.getNewKeepFlag()));
		// ���J�ێ��ҏW�t���O
		formBean.setOnEditFlag(StringUtil.nullToZero(formBean.getOnEditFlag()));
		// �R���e���c�]���t���O
		formBean.setEvaluationFlag(StringUtil.nullToZero(formBean.getEvaluationFlag()));
		// �]���Ҏ����\���t���O
		formBean.setEvaluatorDisplayFlag(StringUtil.nullToZero(formBean.getEvaluatorDisplayFlag()));
		// �O����J�]���Ҏ����\���t���O
		formBean.setPrevEvaluatorDisplayFlag(StringUtil.nullToZero(formBean.getPrevEvaluatorDisplayFlag()));
		// �]���҃R�����g�ҏW�t���O
		formBean.setCommentEditFlag(StringUtil.nullToZero(formBean.getCommentEditFlag()));
		// �������ڕ]���t���O
		formBean.setPluralEvaluationFlag(StringUtil.nullToZero(formBean.getPluralEvaluationFlag()));
		// �O�񕡐����ڕ]���t���O
		formBean.setPrevPluralEvaluationFlag(StringUtil.nullToZero(formBean.getPrevPluralEvaluationFlag()));
		// �]�����N���A����t���O
		formBean.setEvaluationClearFlag(StringUtil.nullToZero(formBean.getEvaluationClearFlag()));
	}
	
	/**
	 * �R���e���c�R�����g���v�����擾
	 * @param pageId
	 * @return int
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public int getCommentCount(String pageId) {
		return pageDao.getCommentCount(pageId);
	}
	
	/**
	 * �]�����ڕ\���t���O
	 * @param pageId
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public String getShowHyoukaFlag(String pageId) {
		int count = pageDao.getShowHyoukaFlag(pageId);
		String flag = "0";
		if (count > 0) {
			flag = "1";
		}
		return flag;
	}
	
	/**
	 * �`�F�b�N�R���e���c
	 * @param pageId
	 */
	public String getHyoukaErrorMessage(String pageId, String openDate) {
		String hyoukaErrorMessage = "";
		PageBean pageInfo = pageDao.getPage(pageId);
		// �R���e���c�폜����
		if (pageInfo == null) {
			hyoukaErrorMessage = "�Ώۂ̃R���e���c�͍폜���ꂽ���߁A�]���ł��܂���B";
			return hyoukaErrorMessage;
		}
		// �R���e���c�ҏW��
		if ("1".equals(pageInfo.getConfirmFlag())) {
			hyoukaErrorMessage = "���ݕҏW���̂��߁A�]���ł��܂���A�Č��J��ɕ]�����������B";
			return hyoukaErrorMessage;
		}
		// �R���e���c�Č��J
		if (!openDate.equals(pageInfo.getOpenDate())) {
			hyoukaErrorMessage = "�R���e���c���ҏW���ꂽ���߁A��U�A\n�g�b�v�y�[�W��\��������A�ēx�]�����s���Ă�������";
			return hyoukaErrorMessage;
		}
		return hyoukaErrorMessage;
	}
	
	/**
	 * �R���e���c�]�����A�]�����v�����擾
	 * @param pageId
	 * @return List
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public List<PageRateItemBean> getEvaluationTotalList(String pageId, String userId) {
		return pageDao.getEvaluationTotalList(pageId, userId);
	}
	
	/**
	 * �P�ꍀ�ڕ]���̎��ɁA�Y�����[�U�̑S�ĕ]�����𕨗��폜����
	 * @param pageId
	 * @param currentUserId
	 */
	public void deleteEvaluationByUser(String pageId, String currentUserId) {
		pageDao.deleteEvaluationByUser(pageId, currentUserId);
	}
	
	/**
     * ���[�UID�ƍ��ڏ��Ԃ��R���e���c�]�����v���e�[�u���ɓo�^����
     * @param pageId
     * @param currentUserId
     * @param hyoukaSequence
     * @param hyoukaOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void insertEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
    	pageDao.insertEvaluationCountByUser(pageId, userId, hyoukaSequence, hyoukaOrderBy);
    }
	
    /**
     * �Y�����[�U�̃R���e���c�]�����v���e�[�u���̕]�����𕨗��폜����
     * @param pageId
     * @param currentUserId
     * @param hyoukaSequence
     * @param hyoukaOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void deleteEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
    	pageDao.deleteEvaluationCountByUser(pageId, userId, hyoukaSequence, hyoukaOrderBy);
    }
    
	/**
	 * �R���e���c�R�����g���A�R�����g���v�����擾
	 * @param pageId
	 * @param userId
	 * @param flag
	 * @return List
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public List<PageCommentRateBean> getCommentTotalList(String pageId, String userId, String flag) {
		return pageDao.getCommentTotalList(pageId, userId, flag);
	}
	
	/**
	 * �R���e���c�R�����g����ǉ�(���e)
	 * @param pageId
	 * @param userId
	 * @param addCommentInfo
	 */
	public void addComment(String pageId,  String userId, String addCommentInfo) {
		pageDao.addComment(pageId, userId, addCommentInfo);
	}

	/**
	 * �Y�����[�U�̃R�����g�����X�V����
	 * @param pageId
	 * @param currentUserId
	 * @param commentOrderBy
	 * @param commentUserId
	 * @param commentInfo
	 */
	public void updateComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId, String commentInfo) {
		pageDao.updateComment(pageId, currentUserId, commentOrderBy, commentUserId, commentInfo);
		// ���e���e�͕ύX����̂ŁA�����˂̃J�E���g���̓N���A���܂�
		pageDao.deleteCommentCountByOrderBy(pageId, commentOrderBy);
		
	}
	
	/**
	 * �Y�����[�U�̃R�����g����_���폜����
	 * @param pageId
	 * @param currentUserId
	 * @param commentOrderBy
	 * @param commentUserId
	 */
	public void deleteComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId) {
		pageDao.deleteComment(pageId, currentUserId, commentOrderBy, commentUserId);
		pageDao.deleteCommentCountByOrderBy(pageId, commentOrderBy);
		
	}
	
	/**
     * �Y�����[�U�̃R�����g���v���e�[�u���̃R�����g���𕨗��폜����
     * @param pageId
     * @param userId
     * @param commentOrderBy
     */
    public void deleteCommentCountByUser(String pageId, String userId, String commentOrderBy) {
		pageDao.deleteCommentCountByUser(pageId, userId, commentOrderBy);
    }
    
    /**
     * ���[�UID�ƃR�����g���Ԃ��R�����g���v���e�[�u���ɓo�^����
     * @param pageId
     * @param userId
     * @param commentOrderBy
     */
    public void insertCommentCountByUser(String pageId, String userId, String commentOrderBy) {
		pageDao.insertCommentCountByUser(pageId, userId, commentOrderBy);
    }
    
	/**
	 * �R�����g���[�����M��ʂŃ��[�U�R�����g���ׂ��擾
	 * @param pageId �y�[�WID
	 * @param commentOrderBy �R�����g����
	 * @param userId �y�[�WID
	 * @param evaluatorDisplayFlag �]���Ҏ����\���t���O
 	 */
	public PageCommentRateBean getUserCommentForMail(String pageId, String commentOrderBy, String userId, String evaluatorDisplayFlag) {
		PageCommentRateBean result = pageDao.getUserCommentForMail(pageId, commentOrderBy, userId);
		if(result != null && "1".equals(evaluatorDisplayFlag)){
			result.setUserName("�@�]���Ҏ����F" + commonDao.getUserName(userId));
		}
		return result;
	}
	
	/**
	 * �R�����g���[�����M
	 * @param List list
	 * @param String user
	 * @throws Exception
	 * @function ���[���̓��e����
	 */
	public void commentSendMail(PageCommentMailFormBean formBean, String userId) throws Exception{
		MailBean mailInfo = new MailBean();
		MailUtil mail = new MailUtil();
		// �R���e���c�R�����g�Ǘ��ҏ��
		List commentAdminList = null;
		commentAdminList = pageDao.getCommentAdminList(formBean.getPageId());
		mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
		mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.from.address"));
		//���[�����
		mailInfo.setSubject(formBean.getCommentMailTitle());
		
		List mailToAddressList = new ArrayList();
		
		List toNameList = new ArrayList();
		// ���[�����e�ƈ���
		if (commentAdminList != null) {
			for (int i = 0; i < commentAdminList.size(); i ++) {
				PageCommentAdminBean commentAdmin = (PageCommentAdminBean)commentAdminList.get(i);
				String commentAdminUserId = commentAdmin.getUserId();
				String commentAdminUserName = commentAdmin.getUserName();
				commentAdminUserName = commentAdminUserName + "����";
				List userMail = pageDao.getMailAddress(commentAdminUserId);
				if (userMail != null) {
					toNameList.add(commentAdminUserName);
					for (int k = 0; k < userMail.size(); k ++) {
						mailToAddressList.add(userMail.get(k));
					}
				}
			}
		}
		
		// ����
		String[] mailToList = new String[mailToAddressList.size()];
		for (int i = 0; i < mailToList.length; i++) {
			mailToList[i] = (String) mailToAddressList.get(i);
		}
		
		//�a�b�b
		String[] mailToBccList = new String[1];
		mailToBccList[0] = FsPropertyUtil.getStringProperty("comment.mail.bcc.address");
		
		mailInfo.setTo(mailToList);
		mailInfo.setBcc(mailToBccList);
		
		// ���[�����e�̃g�b�v�i��������j
		String mailToUserName = "";
		for (int i = 0; i < toNameList.size(); i++) {
			if (i == 0) {
				mailToUserName = (String)toNameList.get(i);
			} else {
				mailToUserName += "�A" + (String)toNameList.get(i);
			}
		}
		formBean.setMailToUserName(mailToUserName);
		
		//���[�����e
		String mailTemplate = new FreemarkerUtil().getTemplateStr(formBean, "mail_page_comment.ftl");
		mailInfo.setContent(mailTemplate);
		
		String kinowu = FsPropertyUtil.getStringProperty("comment.context");
		
		//���M
		mail.sendMail(mailInfo, "", kinowu);
	}
	
	/**
	 * �폜�f�[�^�����O�Ɏ擾
	 * @param pageDeleteBean
	 * @param divFlag
	 * @param conFlag
	 * @throws Exception 
	 */
	private List<PageDeleteBean> getDeleteData(PageDeleteBean pageDeleteBean, String divFlag, String conFlag) throws Exception{
		List<PageDeleteBean> pageDeleteList = new ArrayList<PageDeleteBean>();
		String haitisaki = "";
		String pageId = pageDeleteBean.getPageId();
		String title = pageDeleteBean.getTitle();
		
		// �z�u��
		haitisaki = getPageLocationOpen(pageId);
		
		// conFlag = 0�@�ˁ@���J���R���e���c�AconFlag = 1�@�ˁ@�쐬���R���e���c�AconFlag = 2�@�ˁ@���J�҂��R���e���c
		// divFlag = 1�@�ˁ@�������폜����AdivFlag = 0�@�ˁ@���݂̂��폜����
		if ("1".equals(divFlag)) {
			title = title + "�u���J�����܂ܕҏW���B�쐬���R���e���c�z���ɕҏW���R���e���c����v";
			if ("2".equals(conFlag)) {
				haitisaki = haitisaki + "�u���J�҂��R���e���c�v";
			}
		} else {
			if ("1".equals(conFlag)) {
				haitisaki = haitisaki + "�u�쐬���R���e���c�v";
			} else if ("2".equals(conFlag)) {
				haitisaki = haitisaki + "�u���J�҂��R���e���c�v";
			}
		}

		pageDeleteBean.setPageLocation(haitisaki);
		pageDeleteBean.setTitle(title);
		
		//�쐬��
		String createBy = pageDeleteBean.getCreateBy();
		
		// ���� ���[���A�h���XList
		List<String> toAddressList = new ArrayList();
		List<String> toAddressIdList = new ArrayList();
		
		// ���� ���� List
		List<String> toNameList = new ArrayList();
		
		String createUserName = pageDao.getMailToUserName(createBy, "1");
		
		if (!StringUtil.isEmpty(createUserName)) {
			createUserName = createUserName + "����";
			List<String> createUserMail = pageDao.getMailAddress(createBy);
			if (createUserMail != null) {
				toNameList.add(createUserName);
				for (int k = 0; k < createUserMail.size(); k ++) {
					toAddressList.add(createUserMail.get(k));
					toAddressIdList.add(createBy);
				}
			}
		}
		
		//�X�V��
		String updateBy = pageDeleteBean.getUpdateBy();
		
		if (!(toNameList.size() > 0 && createBy.equals(updateBy))) {
			String updateUserName = pageDao.getMailToUserName(updateBy, "1");

			if (!StringUtil.isEmpty(updateUserName)) {
				updateUserName = updateUserName + "����";
				List<String> updateUserMail = pageDao.getMailAddress(updateBy);
				if (updateUserMail.size() > 0) {
					toNameList.add(updateUserName);
					for (int k = 0; k < updateUserMail.size(); k ++) {
						toAddressList.add(updateUserMail.get(k));
						toAddressIdList.add(updateBy);
					}
				}
			}
		}
		
		// ����List
		pageDeleteBean.setToAddressList(toAddressList);
		pageDeleteBean.setToAddressIdList(toAddressIdList);
		pageDeleteBean.setToNameList(toNameList);
		
		// ���F��List
		List<ProxyUserBean> updateProxyUserList = pageDao.getProxyUserList(pageId);

		// ���O�o�͗p���F��ID���X�g
		List<String> proxyUserList = new ArrayList();
		
		// cc ���[���A�h���XList
		List<String> ccAddressList = new ArrayList();
		
		// cc ���� List
		List<String> ccNameList = new ArrayList(); 
		
		for (int j = 0; j < updateProxyUserList.size(); j++) {
			ProxyUserBean proxyUserBean = updateProxyUserList.get(j);
			String updateProxyUserId = proxyUserBean.getProxyUserId();
			String updateProxyUserName = pageDao.getMailToUserName(updateProxyUserId, "0");
			
			if (!StringUtil.isEmpty(updateProxyUserName)) {
				updateProxyUserName = updateProxyUserName + "����";
				List<String> proxyUserMail = pageDao.getMailAddress(updateProxyUserId);
				if (proxyUserMail != null) {
					ccNameList.add(updateProxyUserName);
					for (int k = 0; k < proxyUserMail.size(); k ++) {
						ccAddressList.add(proxyUserMail.get(k));
						proxyUserList.add(updateProxyUserId);
					}
				}
			}
		}
					
		// ���O�o�͗p���F��ID���X�g		
		pageDeleteBean.setProxyUserList(proxyUserList);
		
		// CC List
		pageDeleteBean.setCcNameList(ccNameList);
		pageDeleteBean.setCcAddressList(ccAddressList);

		//	�V�X�e���S���҂̎���
		String bccUserName = FsPropertyUtil.getStringProperty("mail.bcc.name");
							 
		//�V�X�e���S���҂̃��[���A�h���X
		String bccUserMail = FsPropertyUtil.getStringProperty("mail.bcc.address");
		
		// BCC ���[���A�h���X List
		List<String> bccAddressList = new ArrayList();
		
		// BCC ���� List
		List<String> bccNameList = new ArrayList(); 
		
		bccAddressList.add(bccUserMail);
		bccNameList.add(bccUserName);
		
		pageDeleteBean.setBccAddressList(bccAddressList);
		pageDeleteBean.setBccNameList(bccNameList);
		
		//	���I�R���e���c�̃����N��List
		List<PageLinkBean> linkContentsList = new ArrayList();
		if ("1".equals(pageDeleteBean.getHtmlFlag())) {
			linkContentsList = getLinkContentsList(pageDeleteBean);
		}
		pageDeleteBean.setLinkContentsList(linkContentsList);
		
		//�����N��List
		List<List<PageLinkBean>> linkedList = getLinkedList(pageDeleteBean);
		pageDeleteBean.setLinkedList(linkedList);

		pageDeleteList.add(pageDeleteBean);
		return pageDeleteList;
	}
	
	/**
	 * �����N�惊�X�g���擾
	 * @param pageDeleteBean
	 * @return
	 */
	private List<PageLinkBean> getLinkContentsList(PageDeleteBean pageDeleteBean) {
		String pageId = pageDeleteBean.getPageId();
		// �R���e���c�����N�Y�t
		List<PageLinkBean> pageLinkList = pageDao.getPageLinkList(pageId);

		for (int i = 0; i < pageLinkList.size(); i++) {
			PageLinkBean link = (PageLinkBean)pageLinkList.get(i);
			link.setSendFlag(true);
			String linkUrl = link.getLinkUrl();
			
			//�����N��R���e���cpageId
			String linkPageId = "";
			
			//�����N��R���e���cpageId�擾
			if (linkUrl != null) {
				if(linkUrl.startsWith(pageDeleteBean.getContextPath() + "/linkAccessFilter_view.do?pageId=")) {
					linkUrl = linkUrl.substring(linkUrl.indexOf("pageId=") + 7);
					if(!linkUrl.startsWith("0") && linkUrl.length() > 13) {
						linkPageId = linkUrl.substring(0, 13);
					}
				}
			}
			
			// �{�����N��R���e���c�́A�ʃ��[�g�̃����N���R���e���c������A���A�������N����(�쐬�������J��(�I����>�V�X�e�����t+14)�A���J�҂�)�����݂���ꍇ
			if (StringUtil.isEmpty(linkPageId) || pageId.equals(linkPageId)) {
				link.setSendFlag(false);
			} else {
				PageDeleteBean linkPage = pageDao.getPagePeriodValid(linkPageId);
				if (linkPage != null) {
					// �ݒ�Bean���R�����̒l
					setParam(link, linkPage, "0");
					//�{�����N��R���e���c�́A�ʃ��[�g�̃����N���R���e���c�擾
					List<PageLinkBean> linkedPageIdList = pageDao.getPageLinkListByUrl("PAGE_LINK", linkPageId);
					
					for (int j = 0; j < linkedPageIdList.size(); j++) {
						PageLinkBean tempPage = linkedPageIdList.get(j);
						String linkedPageId = tempPage.getPageId();
						
						if (!linkedPageId.equals(pageId)) {
							//�����؂�Ȃ����������N���R���e���c�𑶍�
							int count = pageDao.getPageLinkExpiredDateCount(linkedPageId);
							
							//�ʃ��[�g�̊����؂�Ȃ����������N���𑶍�
							if(count > 0) {
								link.setSendFlag(false);
								break;
							}
						}
					}
				} else {
					link.setSendFlag(false);
				}
			}
		}
		return pageLinkList;
	}
	
	/**
	 * @param Page page
	 * @throws Exception 
	 * @function �����N��List�擾
	 */
	private List<List<PageLinkBean>> getLinkedList(PageDeleteBean pageDeleteBean) throws Exception {
		List<List<PageLinkBean>> list = new ArrayList();
		String pageId = pageDeleteBean.getPageId();
		String endDate = pageDeleteBean.getEndDate();
		// �������N��List
		List<PageLinkBean> linkList = pageDao.getPageLinkListByUrl("PAGE_LINK", pageId);
		
		// �\�񃊃��N��List
		List<PageLinkBean> linkReserveList = pageDao.getPageLinkListByUrl("PAGE_LINK_RESERVE", pageId);
		
		// �������݃����N��List
		List<PageLinkBean> linkBothPageList = new ArrayList<PageLinkBean>();
		
		List<PageLinkBean> finalLinkList = new ArrayList<PageLinkBean>();
		
		List<PageLinkBean> finalLinkReserveList = new ArrayList<PageLinkBean>();

		//�������PAGE���X�g
		List<String> pageidList = new ArrayList();
		
		//�\���PAGE���X�g
		List<String> pageidListRev = new ArrayList();
		
		for(int i = 0; i < linkReserveList.size(); i++) {
			PageLinkBean linkReservePage = (PageLinkBean)linkReserveList.get(i);
			String linkReservePageId = linkReservePage.getPageId();
			pageidListRev.add(linkReservePageId);
		}
		
		// �������N��List
		for(int i = 0; i < linkList.size(); i++) {
			PageLinkBean linkPage = (PageLinkBean)linkList.get(i);
			String linkPageId = linkPage.getPageId();
			PageBean pageBean = pageDao.getPage(linkPageId);
			if (!linkPageId.equals(pageId) && pageBean != null) {
				PageDeleteBean linkPageTemp = pageDao.getPagePeriodValid(linkPageId);
				if (linkPageTemp != null) {
					PageBean linkPageReserve = pageDao.getPageReserve(linkPageId);
					if (linkPageReserve == null) {
						linkPage.setSendFlag(true);
						setParam(linkPage, linkPageTemp, "1");
						finalLinkList.add(linkPage);
					} else {
						PageDeleteBean linkPageReserveTemp =  new PageDeleteBean();
						WrappedBeanUtil.copyProperties(linkPageReserveTemp, linkPageReserve);
						Date startDateDate = StringUtil.parseTheDate(linkPageReserveTemp.getStartDate(), "yyyy/MM/dd");
						Date endDateDate = StringUtil.parseTheDate(endDate, "yyyy/MM/dd");
						if (endDateDate.before(startDateDate)){
							linkPageReserveTemp.setDateFlag("0");
						} else{
							linkPageReserveTemp.setDateFlag("1");
						}
						if ((!pageidListRev.contains(linkPageId)) && "1".equals(linkPageReserveTemp.getDateFlag())) {
						} else {
							linkPage.setSendFlag(true);
							setParam(linkPage, linkPageTemp, "1");
							linkBothPageList.add(linkPage);
						}
					}
					pageidList.add(linkPageTemp.getPageId());
				}
			}
		}
		
		// �\�񃊃��N��List
		for(int i = 0; i < linkReserveList.size(); i++) {
			PageLinkBean linkReservePage = (PageLinkBean)linkReserveList.get(i);
			String linkReservePageId = linkReservePage.getPageId();
			if (pageidList.contains(linkReservePageId)) {
				continue;
			} else {
				PageBean pageBean = pageDao.getPageReserve(linkReservePageId);
				if (!linkReservePageId.equals(pageId) && pageBean != null) {
					PageBean linkPageTemp = pageDao.getPageReserve(linkReservePageId);
					if (linkPageTemp != null) {
						PageDeleteBean linkPageReserveTemp =  new PageDeleteBean();
						WrappedBeanUtil.copyProperties(linkPageReserveTemp, linkPageTemp);
						Date startDateDate = StringUtil.parseTheDate(linkPageReserveTemp.getStartDate(), "yyyy/MM/dd");
						Date endDateDate = StringUtil.parseTheDate(endDate, "yyyy/MM/dd");
						if (endDateDate.before(startDateDate)){
							linkPageReserveTemp.setDateFlag("0");
						} else{
							linkPageReserveTemp.setDateFlag("1");
						}
						linkReservePage.setSendFlag(true);
						setParam(linkReservePage, linkPageReserveTemp, "1");
						finalLinkReserveList.add(linkReservePage);
					}
				}
			}
		}
		
		//�����N��List
		if (finalLinkList.size() > 0 || finalLinkReserveList.size() > 0 || linkBothPageList.size() > 0) {
			list.add(finalLinkList);
			list.add(finalLinkReserveList);
			list.add(linkBothPageList);
		}
		return list;
	}
	
	/**
	 * @param Page bean1
	 * @param Page bean2
	 * @function �ݒ�Bean���R�����̒l
	 */
	private void setParam(PageLinkBean bean1, PageDeleteBean bean2, String flag) {
		bean1.setStartDate(bean2.getStartDate());
		bean1.setEndDate(bean2.getEndDate());
		bean1.setCreateBy(bean2.getCreateBy());
		bean1.setUpdateBy(bean2.getUpdateBy());
		bean1.setTitle(bean2.getTitle());
		//createBy���[�U����
		String createUserName = pageDao.getUserName(bean2.getCreateBy());
		
		//updateBy���[�U����
		String updateUserName = pageDao.getUserName(bean2.getUpdateBy());
		
		bean1.setCreateUserName(createUserName);
		bean1.setUpdateUserName(updateUserName);
		
		//flag = 1�A�����N���R���e���c�̔z�u��̒l�ݒ�
		if ("1".equals(flag)) {
			String haitisaki = getPageLocationOpen(bean2.getPageId());
			bean1.setPageLocation(haitisaki);
			bean1.setDateFlag(bean2.getDateFlag());
		}
	}
	
	/**
	 * List list
	 * String flag
	 * �����N���A�����N��List ���݃t���O
	 */ 
	private boolean getContentFlag(List<?> list, String flag) {
		boolean contentFlag = false;
		// flag = 0�A�����N��̏ꍇ
		if ("0".equals(flag)) {
			for (int i = 0; i < list.size(); i ++) {
				PageLinkBean page = (PageLinkBean)list.get(i);
				if (page.isSendFlag()) {
					contentFlag = true;
					break;
				}
			}
		// flag = 1�A�����N���̏ꍇ
		} else {
			if (list.size() > 0) {
				List<PageLinkBean> pageList = (List)list.get(0);
				List<PageLinkBean> pageReserveList = (List)list.get(1);
				List<PageLinkBean> bothPageList = (List)list.get(2);
				for (int i = 0; i < pageList.size(); i ++) {
					PageLinkBean page = pageList.get(i);
					if (page.isSendFlag()) {
						contentFlag = true;
						break;
					}
				}
				if (!contentFlag) {
					for (int i = 0; i < pageReserveList.size(); i ++) {
						PageLinkBean page = pageReserveList.get(i);
						if (page.isSendFlag()) {
							contentFlag = true;
							break;
						}
					}
				}
				if (!contentFlag) {
					if (bothPageList.size() > 0) {
						contentFlag = true;
					}
				}
			}
		}
		return contentFlag;
	}	

	private void deleteSendMail(List<PageDeleteBean> deleteList, String user) throws Exception{
		PageDeleteBean pageDeleteBean = deleteList.get(0);
		// ���[�����e�ݒ�
		MailBean mailInfo = new MailBean();

		// ��Ճ��[�����M
		MailUtil mail = new MailUtil();
		mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
		mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.from.address"));

		// subject��ݒ�
		String mailSubject = "[" + pageDeleteBean.getTitle() + "]";
		mailSubject = mailSubject + FsPropertyUtil.getStringProperty("deleteContents.mail.subject");
		mailInfo.setSubject(mailSubject);
		
		// ���[���A�h���X���Z�b�g
		mailAddress(pageDeleteBean, mailInfo);

		//���[�����e
		StringBuffer contentBuffer = new StringBuffer();
		
		//���[��top���e
		getMailHeadContents(pageDeleteBean, mailInfo, contentBuffer);
		
		//���[��top���e
		getMailContents(pageDeleteBean, contentBuffer);
		
		//���[��top���e
		getMailMiddleContents(pageDeleteBean, contentBuffer);
		
		//�����N��
		getLinkContentsInfo(pageDeleteBean, contentBuffer);

		//�����N��
		getLinkedInfo(pageDeleteBean, contentBuffer);
		
		//���[�����e
		mailInfo.setContent(contentBuffer.toString());
	
		String kinowu = FsPropertyUtil.getStringProperty("expired.context");
		//���M
		mail.sendMail(mailInfo, "OutOfDayBatch", kinowu);		
		
	}
	
	/**
	 * ���[���A�h���X���Z�b�g
	 * @param page
	 * @param mailInfo
	 */
	private void mailAddress(PageDeleteBean page, MailBean mailInfo) {
		// ����List
		List mailToAddressList = new ArrayList();
		
		// ���惆�[�U����List
		List mailToNameList = new ArrayList();
		
		// �b�bList
		List mailCcAddressList = new ArrayList();
		
		// �a�b�bList
		List mailBccAddressList = new ArrayList();
		
		// ����List
		List toAddressList = page.getToAddressList();
		List toAddressIdList = page.getToAddressIdList();
		
		// ���惆�[�U����List
		List toNameList = page.getToNameList();
		
		// �b�bList
		List ccAddressList = page.getCcAddressList();
		
		// �b�b���[�U����List
		List ccNameList = page.getCcNameList();
		
		// �a�b�bList
		List bccAddressList = page.getBccAddressList();
		
		// �a�b�b���[�U����List
		List bccNameList = page.getBccNameList();
		
		// �쐬�҂ƍX�V�҂𑶍݂���̏ꍇ�A����F�쐬�҂ƍX�V�ҁA�b�b�F���F�ҁA�a�b�b�F�V�X�e���S����
		if (toAddressList.size() > 0) {
			mailToAddressList = toAddressList;
			mailToNameList = toNameList;
			mailCcAddressList = ccAddressList;
			mailBccAddressList = bccAddressList;
			setTrmailAddress(mailCcAddressList, toAddressIdList);
			
		// �쐬�҂ƍX�V�҂𑶍݂��Ȃ��̏ꍇ�A
		} else {
			//�@���F�҂𑶍݂���̏ꍇ�A����F���F�ҁA�a�b�b�F�V�X�e���S����
			if (ccAddressList.size() > 0) {
				mailToAddressList = ccAddressList;
				mailToNameList = ccNameList;
				mailBccAddressList = bccAddressList;
				setTrmailAddress(mailCcAddressList, page.getProxyUserList());
			
			//�@���F�҂𑶍݂��Ȃ��̏ꍇ�A����F�V�X�e���S����				
			} else {
				mailToAddressList = bccAddressList;
				mailToNameList = bccNameList;
			}
		}
		
		//����
		String[] mailToList = new String[mailToAddressList.size()];
		for (int i = 0; i < mailToList.length; i++) {
			mailToList[i] = (String) mailToAddressList.get(i);
		}
		
		//�b�b
		String[] mailToCcList = new String[mailCcAddressList.size()];
		for (int i = 0; i < mailToCcList.length; i++) {
			mailToCcList[i] = (String) mailCcAddressList.get(i);
		}
		
		//�a�b�b
		String[] mailToBccList = new String[mailBccAddressList.size()];
		for (int i = 0; i < mailToBccList.length; i++) {
			mailToBccList[i] = (String) mailBccAddressList.get(i);
		}
		
		//���惆�[�U����������
		String mailToUserName = "";
		for (int i = 0; i < mailToNameList.size(); i++) {
			if (i == 0) {
				mailToUserName = (String)mailToNameList.get(i);
			} else {
				mailToUserName += "�A" + (String)mailToNameList.get(i);
			}
		}
		
		mailInfo.setTo(mailToList);
		mailInfo.setCc(mailToCcList);
		mailInfo.setBcc(mailToBccList);
		mailInfo.setMailToUserName(mailToUserName);
	}
	
	/**
	 * �]���惁�[���A�h���X��ݒ�
	 * @param mailCcAddressList�@�b�b
	 * @param mailToAddressIdList�@����
	 */
	private void setTrmailAddress(List mailCcAddressList, List mailToAddressIdList){
		String userIdArr = "";
		for (int i=0; i< mailToAddressIdList.size(); i++) {
			String userId = (String)mailToAddressIdList.get(i);
			if(StringUtil.isEmpty(userIdArr)) {
				userIdArr = "'" + userId + "'";
			} else {
				userIdArr += ",'" + userId + "'";
			}
		}

		// �]���惁�[���A�h���X���X�g���擾
		if (!StringUtil.isEmpty(userIdArr)){
			List<String> trmailAddressList = pageDao.getTrmailAddressList(userIdArr);
			if (trmailAddressList != null && trmailAddressList.size() > 0){
				for (String trmailAddress : trmailAddressList){
					if (StringUtil.isNotBlank(trmailAddress)){
						mailCcAddressList.add(trmailAddress);
					}
				}
			}
		}
	}
	
	/**
	 * ���[���w�b�_�[
	 * @param page
	 * @param mailInfo
	 * @param contentBuffer
	 */
	private void getMailHeadContents(PageDeleteBean page, MailBean mailInfo, StringBuffer contentBuffer) {
		String mailToUserName = mailInfo.getMailToUserName();
		contentBuffer.append(mailToUserName);
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.headinfo1"));
		contentBuffer.append("<br><br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.headinfo2"));
		contentBuffer.append("<br><br>");
	}
	
	/**
	 * ���[��Contents
	 * @param page
	 * @param contentBuffer
	 */
	private void getMailContents(PageDeleteBean page, StringBuffer contentBuffer) {
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.contentsinfo1"));
		contentBuffer.append(page.getTitle());
		contentBuffer.append("<br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.contentsinfo2"));
		contentBuffer.append(page.getPageLocation());
		contentBuffer.append("<br><br>");
	}
	
	/**
	 * @param Page page
	 * @param StringBuffer contentBuffer
	 * @function ���[��Middle
	 */
	private void getMailMiddleContents(PageDeleteBean page, StringBuffer contentBuffer) {
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.middleinfo1"));
		contentBuffer.append("<br><br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.middleinfo2"));
		contentBuffer.append("<br>");
		contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.middleinfo3"));
		contentBuffer.append("<br><br>");
	}
	
	/**
	 * �����N�惁�[�����e
	 * @param page
	 * @param contentBuffer
	 */
	private void getLinkContentsInfo(PageDeleteBean page, StringBuffer contentBuffer) {
		List linkContentsList = page.getLinkContentsList();
		boolean contentflag = getContentFlag(linkContentsList, "0");
		if (contentflag) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.linkinfo"));
			contentBuffer.append("<br>");
			
			for(int j = 0; j < linkContentsList.size(); j++) {
				PageLinkBean linkContentsPage = (PageLinkBean) linkContentsList.get(j);
				if (linkContentsPage.isSendFlag()) {
					// �����N�L�[���[�h
					contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo1"));
					contentBuffer.append(linkContentsPage.getLinkName());
					contentBuffer.append("<br>");
					
					//�����N��R���e���c��
					contentBuffer.append("�@�@�@");
					contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo3"));
					
					//�����N��R���e���c��
					contentBuffer.append("�u" + linkContentsPage.getTitle() + "�v");
					
					//���J����
					contentBuffer.append("�i");
					contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo4"));
					contentBuffer.append(linkContentsPage.getStartDate());
					contentBuffer.append("�`");
					contentBuffer.append(linkContentsPage.getEndDate());
					contentBuffer.append("�j");
					contentBuffer.append("<br>");
					
					//�����N��쐬�ҁA�����N��X�V��
					String createUserName = linkContentsPage.getCreateUserName();
					String updateUserName = linkContentsPage.getUpdateUserName();
					String createUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo6");
					String updateUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo7");
					String temp = "";
					if (!"".equals(createUserName)) {
						temp = "�@�@�@" + createUser + createUserName + "<br>";
					}
					if (!"".equals(updateUserName)) {
						temp += "�@�@�@" + updateUser + updateUserName;
					}
					if (temp.endsWith("<br>")) {
						temp = temp.substring(0, temp.length() - 4);
					}
					contentBuffer.append(temp);
					contentBuffer.append("<br>");
				}
			}
			contentBuffer.append("<br>");
		}
	}
	
	/**
	 * �����N���̃R�����Z�b�g
	 * @param page
	 * @param contentBuffer
	 * @param divFlag
	 */
	private void getStr(PageLinkBean page, StringBuffer contentBuffer, String divFlag) {
		// ���̂݃R���e���c��
		contentBuffer.append("�E�u" + page.getTitle() + "�v");
		
		//���̂݌��J����
		contentBuffer.append("�i");
		contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo4"));
		contentBuffer.append(page.getStartDate());
		contentBuffer.append("�`");
		contentBuffer.append(page.getEndDate());
		contentBuffer.append("�j");
		
		if ("R".equals(divFlag)) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.reserveinfo"));
		} else if ("B".equals(divFlag)) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.bothinfo"));
		}
		contentBuffer.append("<br>");
		contentBuffer.append("�@�@�@");
		
		//�����N���R���e���c�z�u��
		contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo2"));
		contentBuffer.append(page.getPageLocation());
		contentBuffer.append("<br>");
		
		//���̂ݍ쐬�ҁA�����N��X�V��
		String createUserName = page.getCreateUserName();
		String updateUserName = page.getUpdateUserName();
		String createUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo6");
		String updateUser = FsPropertyUtil.getStringProperty("delete.mail.maininfo7");
		String temp = "";
		if (!"".equals(createUserName)) {
			temp = "�@�@�@" + createUser + createUserName + "<br>";
		}
		if (!"".equals(updateUserName)) {
			temp += "�@�@�@" + updateUser + updateUserName;
		}
		if (temp.endsWith("<br>")) {
			temp = temp.substring(0, temp.length() - 4);
		}
		contentBuffer.append(temp);
		contentBuffer.append("<br>");

		//�����N���R���e���c�A�����N�L�[���[�h
		contentBuffer.append("�@�@�@");
		contentBuffer.append(FsPropertyUtil.getStringProperty("delete.mail.maininfo5"));
		contentBuffer.append(page.getLinkName());
		contentBuffer.append("<br>");
	}
	
	/**
	 * @param Page page
	 * @param StringBuffer contentBuffer
	 * @param String flag
	 * @function �����N�����[�����e
	 */
	private void getLinkedInfo(PageDeleteBean page, StringBuffer contentBuffer) {
		List linkedList = page.getLinkedList();
		boolean contentflag = getContentFlag(linkedList, "1");
		if (contentflag) {
			contentBuffer.append(FsPropertyUtil.getStringProperty("deleteContents.mail.linked.info"));
			contentBuffer.append("<br>");
			
			//���̂�List
			List<PageLinkBean> linkList = (List)linkedList.get(0);
			
			//�\��̂�List
			List<PageLinkBean> linkReserveList = (List)linkedList.get(1);
			
			//����List
			List<PageLinkBean> linkBothPageList = (List)linkedList.get(2);
			
			for(int i = 0; i < linkList.size(); i++) {
				PageLinkBean linkedPage = linkList.get(i);
				if (linkedPage.isSendFlag()) {
					getStr(linkedPage, contentBuffer, "");
				}
			}
			
			for(int i = 0; i < linkReserveList.size(); i++) {
				PageLinkBean linkedPageReserve = linkReserveList.get(i);
				if (linkedPageReserve.isSendFlag()) {
					getStr(linkedPageReserve, contentBuffer, "R");
				}
			}
			
			for(int i = 0; i < linkBothPageList.size(); i++) {
				PageLinkBean bothPage = linkBothPageList.get(i);
				getStr(bothPage, contentBuffer, "B");
			}
		}
	}
	
	/**
	 * ���O���o��
	 * @param list
	 * @return
	 */
	public String deleteMailLog(List list) {
		PageDeleteBean page = (PageDeleteBean)list.get(0);
		String pageId = page.getPageId();
		String endDate = page.getEndDate();
		String createUserId = page.getCreateBy();
		String updateUserId = page.getUpdateBy();
		String title = page.getTitle();
		
		//���F��List
		List<String> proxyUserList = page.getProxyUserList();
		
		//���F��str
		String proxyUserIdStr = "";
		for (int j = 0; j < proxyUserList.size(); j++ ) {
			if (j == 0) {
				proxyUserIdStr = proxyUserList.get(j);
			} else {
				proxyUserIdStr +=  "," + proxyUserList.get(j);
			}
		}
		
		String confirmFlag = page.getConfirmFlag();
		
		String contents = "";
		boolean linkedFlag = getContentFlag(page.getLinkedList(), "1");
		if (linkedFlag) {
			contents = "�����N��/";
		}
		
		boolean linkContentsFlag = getContentFlag(page.getLinkContentsList(), "0");
		if (linkContentsFlag) {
			contents += "�����N��";
		}

		if(contents.endsWith("/")) {
			contents = contents.substring(0, contents.length() - 1);
		}
		
		StringBuffer message = new StringBuffer();
		message.append("[�R���e���c���폜]");
		message.append(" - PAGE_ID[");
		message.append(pageId);
		message.append("],���J�I����[");
		message.append(endDate);
		message.append("],�쐬��[");
		message.append(createUserId);
		message.append("],�X�V��[");
		message.append(updateUserId);
		message.append("],���F��[");
		message.append(proxyUserIdStr);
		if ("1".equals(confirmFlag)) {
			message.append("],�X�e�[�^�X[�ҏW��]");
		} else {
			message.append("],�X�e�[�^�X[���J��]");
		}
		message.append(",�ΏۃR���e���c[");
		message.append(title);
		message.append("],�Ώ�[");
		message.append(contents);
		message.append("]");
		return message.toString();
	}
	
	/**
	 * �e���v���[�g���փ`�F�b�N
	 * @param formBean
	 */
	public void templateCheck(PageFormBean formBean) {
		// �R���e���c�{���������[�U��񑊊փ`�F�b�N
		boolean userNoExistFlag = false;
		if (formBean.getAuthUserList() != null){
			String userId = "";
			for (int i=0; i<formBean.getAuthUserList().size(); i++) {
				AuthUserBean authUserBean = formBean.getAuthUserList().get(i);
				if("1".equals(authUserBean.getUserDeleteFlag())) {
					continue;
				} else {
					if(StringUtil.isEmpty(userId)) {
						userId = "'" + authUserBean.getUserId() + "'";
					} else {
						userId += ",'" + authUserBean.getUserId() + "'";
					}
				}
			}
			
			// ���[�U��񑶍݃��X�g���擾
			List<String> userExistList = null;
			if (!StringUtil.isEmpty(userId)){
				userExistList = templatePageDao.getUserExistList(userId);
			}
			if (userExistList == null){
				userExistList = new ArrayList<String>();
			}
			
			for (int i=0; i<formBean.getAuthUserList().size(); i++) {
				AuthUserBean authUserBean = formBean.getAuthUserList().get(i);
				String authUserId = authUserBean.getUserId();
				if("1".equals(authUserBean.getUserDeleteFlag())) {
					continue;
				} else {
					if(!userExistList.contains(authUserId)) {
						authUserBean.setUserDeleteFlag("1");
						userNoExistFlag = true;
					}
				}
			}			
		}
		
		// �X�V��s�ҏ�񑊊փ`�F�b�N
		boolean proxyNoExistFlag = false;
		if (formBean.getProxyUserList() != null){
			String proxyUserId = "";
			for (int i=0; i<formBean.getProxyUserList().size(); i++) {
				ProxyUserBean proxy = formBean.getProxyUserList().get(i);
				if("1".equals(proxy.getProxyDeleteFlag())) {
					continue;
				} else {
					if(StringUtil.isEmpty(proxyUserId)) {
						proxyUserId = "'" + proxy.getProxyUserId() + "'";
					} else {
						proxyUserId += ",'" + proxy.getProxyUserId() + "'";
					}
				}
			}
			
			// ���[�U��񑶍݃��X�g���擾
			List proxyExistList = null;
			if (!StringUtil.isEmpty(proxyUserId)){
				proxyExistList = templatePageDao.getUserExistList(proxyUserId);
			}
			if (proxyExistList == null){
				proxyExistList = new ArrayList<String>();
			}
			
			for (int i=0; i<formBean.getProxyUserList().size(); i++) {
				ProxyUserBean proxy = formBean.getProxyUserList().get(i);
				String proxyUserIdTemp = proxy.getProxyUserId();
				if("1".equals(proxy.getProxyDeleteFlag())) {
					continue;
				} else {
					if(!proxyExistList.toString().contains(proxyUserIdTemp)) {
						proxy.setProxyDeleteFlag("1");
						proxyNoExistFlag = true;
					}
				}
			}			
		}
		
		// �R���e���c�{�������g�b�v�O���[�v��񑊊փ`�F�b�N
		boolean topgroupNoExistFlag = false;
		if (formBean.getAuthGroupList() != null){
			String topgroupId = "";
			for (int i=0; i<formBean.getAuthGroupList().size(); i++) {
				AuthGroupBean authGroupBean = formBean.getAuthGroupList().get(i);
				if("1".equals(authGroupBean.getGroupDeleteFlag())) {
					continue;
				} else {
					if(StringUtil.isEmpty(topgroupId)) {
						topgroupId = authGroupBean.getTopGroupId();
					} else {
						topgroupId += "," + authGroupBean.getTopGroupId();
					}
				}
			}
			
			// �g�b�v�O���[�v��񑶍݃��X�g���擾
			List<String> groupList = null;
			if (!StringUtil.isEmpty(topgroupId)){
				groupList = templatePageDao.getTopGroupExistList(topgroupId);
			}
			if (groupList == null){
				groupList = new ArrayList<String>();
			}

			for (int i=0; i<formBean.getAuthGroupList().size(); i++) {
				AuthGroupBean authGroupBean = formBean.getAuthGroupList().get(i);
				String TopGroupIdTemp = authGroupBean.getTopGroupId();
				if("1".equals(authGroupBean.getGroupDeleteFlag())) {
					continue;
				} else {
					if(!groupList.toString().contains(TopGroupIdTemp)) {
						authGroupBean.setGroupDeleteFlag("1");
						topgroupNoExistFlag = true;
					}
				}
			}
		}
		
		// �R���e���c�����N���
		boolean linkNoExistFlag = false;
		if (formBean.getPageLinkList() != null){
			String linkUrl = "";
			String linkPageId = "";
			for (int i=0; i<formBean.getPageLinkList().size(); i++) {
				PageLinkBean pageLinkBean = formBean.getPageLinkList().get(i);
				linkUrl = pageLinkBean.getLinkUrl();
				if (!StringUtil.isEmpty(linkUrl)) {
					if(linkUrl.startsWith(formBean.getContextPath()) && linkUrl.indexOf("pageId=") > 0) {
						linkUrl = linkUrl.substring(linkUrl.indexOf("pageId=") + 7);
						if("1".equals(pageLinkBean.getLinkDeleteFlag())) {
							continue;
						} else {
							if(linkUrl.startsWith("0")) {
								linkUrl = "0";
							} else {
								if (linkUrl.length() > 13) {
									linkUrl = linkUrl.substring(0, 13);
								} else {
									continue;
								}
							}
							if("".equals(linkPageId)) {
								linkPageId = "'" + linkUrl + "'";
							} else {
								linkPageId += ",'" + linkUrl + "'";
							}
						}
					}
				}
			}
			
			// �y�[�W�����N��񑶍݃��X�g���擾
			List<String> linkExistList = null;
			if (!StringUtil.isEmpty(linkPageId)){
				linkExistList = templatePageDao.getPageLinkExistList(linkPageId);
			}
			if (linkExistList == null){
				linkExistList = new ArrayList<String>();
			}
			
			for (int i=0; i<formBean.getPageLinkList().size(); i++) {
				PageLinkBean pageLinkBean = formBean.getPageLinkList().get(i);
				linkUrl = pageLinkBean.getLinkUrl();
				if (linkUrl != null) {
					if(linkUrl.startsWith(formBean.getContextPath()) && linkUrl.indexOf("pageId=") > 0) {
						linkUrl = linkUrl.substring(linkUrl.indexOf("pageId=") + 7);
						if("1".equals(pageLinkBean.getLinkDeleteFlag())) {
							continue;
						} else {
							if(linkUrl.startsWith("0")) {
								linkUrl = "0";
							} else {
								if (linkUrl.length() > 13) {
									linkUrl = linkUrl.substring(0, 13);
								} else {
									continue;
								}
							}
							if(!linkExistList.toString().contains(linkUrl)) {
								pageLinkBean.setLinkDeleteFlag("1");
								linkNoExistFlag = true;
							}
						}
					}
				}
			}			
		}
		
		// �e�y�[�WID
		String strHaiTiSaki = getPageLocation(formBean.getPPageId());
		if (StringUtil.isEmpty(strHaiTiSaki)){
			formBean.setPPageId("");
		}
		
		// ���M����
		String depId = formBean.getSourceDepartment();
		String departmentName = templatePageDao.getSourceDeptName(depId,null);
		if(StringUtil.isEmpty(departmentName)) {
			formBean.setTemplateNoExistDepartmentId(depId);
			String templateNoExistDepartmentName = templatePageDao.getSourceDeptName(depId, "1");
			if (!StringUtil.isEmpty(templateNoExistDepartmentName)){
				List<LabelValueBean> sourceDeptDropList = formBean.getSourceDeptDropList();
				if (sourceDeptDropList == null){
					sourceDeptDropList = new ArrayList<LabelValueBean>();
				}
				LabelValueBean departmentBean = new LabelValueBean();
				departmentBean.setValue(depId);
				departmentBean.setLabel(templateNoExistDepartmentName);
				sourceDeptDropList.add(departmentBean);
			}
		} else {
			formBean.setTemplateNoExistDepartmentId("");
		}

		StringBuffer message = new StringBuffer();
		if(userNoExistFlag || proxyNoExistFlag || topgroupNoExistFlag || linkNoExistFlag || !StringUtil.isEmpty(formBean.getTemplateNoExistDepartmentId())) {
			if(topgroupNoExistFlag) {
				message.append("\\\\n\\\\n�E���J����O���[�v");
			} 
			if(userNoExistFlag) {
				message.append("\\\\n\\\\n�E���J����l");
			} 
			if(linkNoExistFlag) {
				message.append("\\\\n\\\\n�E�����N�Y�t");
			} 
			if(proxyNoExistFlag) {
				message.append("\\\\n\\\\n�E���F��");
			} 
			if(!StringUtil.isEmpty(formBean.getTemplateNoExistDepartmentId())) {
				message.append("\\\\n\\\\n�E���M����");
			}
			throw new BusinessServiceException("MSGOE109", new String[]{message.toString()});
		}
	}
	
	/**
	 * �r���`�F�b�N
	 * @param pageBean
	 * @param dataKb�@0:�}�X�^�@1�F�\�� 2�F�e���v���[�g
	 */
	public void optimisticLockCheck(String pageId, String dataKb) {
		// DB���ʏ��擾
		BaseBean dbCommonInfo = null;
		String errMessage = "�R���e���c";
		// �e���v���[�g�̏ꍇ
		if ("2".equals(dataKb)){
			errMessage = "�e���v���[�g�R���e���c";
			dbCommonInfo = commonDao.getDbCommonInfo("TEMPLATE_PAGE", "TEMPLATE_PAGE_ID", pageId);
		}else if ("1".equals(dataKb)){
			// �\��̏ꍇ
			dbCommonInfo = commonDao.getDbCommonInfo("PAGE_RESERVE", "PAGE_ID", pageId);
		} else{
			// �}�X�^�̏ꍇ
			dbCommonInfo = commonDao.getDbCommonInfo("PAGE", "PAGE_ID", pageId);
		}
		//�@�Y���y�[�W���폜���ꂽ�ꍇ�A�G���[
		if (dbCommonInfo == null){
			String message = getErrorMessage("MSGOE157", new String[]{errMessage, ""});
			OptimisticLockException e = new OptimisticLockException(message);
			e.getErrorMessageList().add(message);
			throw e;
		} else {
			String updateUserName = StringUtil.trimer(' ',dbCommonInfo.getUpdateUserName());
			// �Y���y�[�W���_���폜���ꂽ�ꍇ�A�G���[
			if ("1".equals(dbCommonInfo.getDeleteFlag())){
				String message = getErrorMessage("MSGOE157", new String[]{errMessage, updateUserName + "����ɂ��"});
				OptimisticLockException e = new OptimisticLockException(message);
				e.getErrorMessageList().add(message);
				throw e;
			} else{
				// �����[�U�ɂ��X�V���ꂽ�ꍇ�A�G���[
				String message = getErrorMessage("MSGOE158", new String[]{updateUserName});
				OptimisticLockException e = new OptimisticLockException(message);
				e.getErrorMessageList().add(message);
				throw e;
			}
		}
	}
	
	/**
	 * ZIP�t�@�C���`�F�b�N
	 * @throws Exception 
	 */
	private void zipFileCheck(String zipuUploadName, String mainFile) throws Exception{
		mainFile = StringUtil.isEmpty(mainFile) ? "":mainFile.toLowerCase();
		ZipFile zfile = new ZipFile(zipuUploadName);
		Enumeration zListCheck = zfile.getEntries();
		ZipEntry ze = null;
		boolean hasMain = false;
		while (zListCheck.hasMoreElements()) {
			ze = (ZipEntry) zListCheck.nextElement();
			String fileName = ze.getName();
			
			// �t�@�C�����`�F�b�N
			isAlphanumeric(fileName);

			if (ze.getName().toLowerCase().equals(mainFile)) {
				hasMain = true;
			}
		}
		// ZIP�t�@�C���Ƀ��C���t�@�C�����܂߂�ꂽ�ꍇ�A�G���[
		if (!hasMain) {
			throw new BusinessServiceException("MSGOE164");
		}
	}
	
	/**
	 * �t�@�C�����`�F�b�N
	 * @param str
	 * @return
	 */
	private void isAlphanumeric(String str) {
		if (str == null) {
			return;
		}
		int sz = str.length();
		if(str.getBytes().length > str.length() && str.getBytes().length!=str.length()){
			throw new BusinessServiceException("MSGOE161");
		}
		String str_HALFKANA = "����������������������������������������������������ܦݶ޷޸޹޺޻޼޽޾޿������������������������������߳ް";
		for (int i = 0; i < sz; i++) {			
			if(str_HALFKANA.contains(String.valueOf(str.charAt(i)))){		
				throw new BusinessServiceException("MSGOE161");
			}						
		}
		String special = "`!@#$%^&()+=,'~\\[]{}";
		String specialRls = "`!@#\\$%^&()+=,'~\\\\[]{}";
		for (int i = 0; i < sz; i++) {			
			if (!Character.isLetterOrDigit(str.charAt(i))){
				if(special.contains(String.valueOf(str.charAt(i)))){
					throw new BusinessServiceException("MSGOE163", new String[]{specialRls});
				}
			}			
		}		
	}
	
	/**
	 * ���ꕶ���ϊ�
	 * @param contents
	 * @return
	 */
	public String contentReplace(String contents){
		if (StringUtil.isNotEmpty(contents)){
//			contents = contents.replaceAll("&","&amp;");
		}
		return contents;
	}
	
	/**
	 * �����N�t�@�C�������擾
	 */
	public String getLinkFileName(String pageId, String isReserve, String isTemplateFrom, String index, PageFormBean formBean) throws IOException {
		List<PageAttachmentBean> attachmentList = new ArrayList<PageAttachmentBean>();
		if ("1".equals(isTemplateFrom)) {
			formBean.setTemplatePageId(pageId);
			attachmentList = templatePageDao.getTemplatePageAttachList(formBean, "");
		} else {
			attachmentList = pageDownloadDao.getAttachmentList(pageId, isReserve);
		}
		for (int i = 0; i < attachmentList.size(); i++) {
			String fileName = StringUtil.nullToBlank(attachmentList.get(i).getAttachmentFileUrl());
			if(fileName!=null && !"".equals(fileName)) {
				try {
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1,
							fileName.lastIndexOf("."));
				} catch (Exception e) {
				}
			}
			if ((pageId + index).equals(fileName)) {
				return attachmentList.get(i).getAttachmentName();
			}
		}
		
		return "";
	}
	
}