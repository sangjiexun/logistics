package jp.co.fourseeds.fsnet.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageFormBean;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.TemplatePageDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.WrappedBeanUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * �z�[���@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/11/24		    NTS        	       �쐬
 *
 **/
@SuppressWarnings(value={"unchecked"})
@Component
public class TemplatePageService extends BaseBusinessService{

	@Autowired
	private TemplatePageDao templatePageDao;
	
	/**
	 * �e���v���[�g�R���e���c�����擾
	 * @param user
	 */
	public PageBean getTemplatePage(String pageId, String currentUserId) {
		return templatePageDao.getTemplatePage(pageId, currentUserId);
	}
	
	/**
	 * �e���v���[�g�R���e���c�����擾
	 * @param user
	 */
	public PageBean getTemplatePagebyID(String templatePageId) {
		return templatePageDao.getTemplatePagebyID(templatePageId);
	}
	
	/**
	 * �e���v���[�g�e���v���[�g�R���e���c���̎擾
	 * @param param�@
	 * @return�@
	 */
	public PageFormBean getTemplatePageForm(String templatePageId) throws Exception {
		
		PageFormBean formBean = null;

		// �R���e���c�����擾
		PageBean pageBean = getTemplatePagebyID(templatePageId);
		if (pageBean != null) {
			formBean = new PageFormBean();
			WrappedBeanUtil.copyProperties(formBean, pageBean);
			
			// �e���v���[�g�R���e���c�����N�����擾
			formBean.setPageLinkList(templatePageDao.getTemplatePageLinkList(templatePageId));
			
			// �e���v���[�g�R���e���c�Y�t�t�@�C�������擾
			formBean.setPageAttachmentList(templatePageDao.getTemplatePageAttachList(templatePageId));
			
			// �e���v���[�g�R���e���c�{�������g�b�v�O���[�v�����擾
			formBean.setAuthGroupList(templatePageDao.getTemplateAuthGroupList(templatePageId));
			
			// �e���v���[�g�R���e���c�{���������[�U�����擾
			formBean.setAuthUserList(templatePageDao.getTemplateAuthUserList(templatePageId));
			
			// �e���v���[�g�X�V��s�ҏ������擾
			formBean.setProxyUserList(templatePageDao.getTemplateProxyUserList(templatePageId));
			
			// �e���v���[�g�R���e���c�]�����X�g
			formBean.setPageRateItemBeanList(templatePageDao.getTemplatePageRateItemList(templatePageId));
			
			// �e���v���[�g�R���e���c�R�����g�Ǘ��҃��X�g
			formBean.setPageCommentAdminList(templatePageDao.getTemplateCommentAdminList(templatePageId));
			
			// �e���v���[�g�R���e���c�R�����g�Ǘ��ґI�����X�g
			formBean.setPageCommentAdminOptionList(templatePageDao.getTemplateCommentAdminOptList(templatePageId));
		}

		return formBean;
		
	}
	
	/**
	 * �e���v���[�g�R���e���c�]���A�C�e�������擾
	 * @param user
	 */
	public List<PageRateItemBean> getTemplatePageRateItemList(String templatePageId) {
		return templatePageDao.getTemplatePageRateItemList(templatePageId);
	}
	
	/**
	 * �e���v���[�g�ۑ�����
	 * @param formBean
	 * @throws Exception 
	 */
	public void insertTemplate(PageFormBean formBean) throws Exception{

		// �y�[�WID
		String pageId = formBean.getPageId();
		// ���O�C�����[�UID
		String currentUserId = StringUtil.nullToBlank(getLoginUserBean().getUserId());

		// �o�^�O�A�e���v���[�g�R���e���c���֏���_���폜
		// �e���v���[�g�R���e���c����_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE", pageId, currentUserId);
		// �e���v���[�g�R���e���c�Y�t�t�@�C������_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_ATTACHMENT", pageId, currentUserId);
		// �e���v���[�g�R���e���c�����N����_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_LINK", pageId, currentUserId);
		// �e���v���[�g�R���e���c�{�������g�b�v�O���[�v����_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_AUTH_LEADING_GROUP", pageId, currentUserId);
		// �e���v���[�g�R���e���c�{���������[�U����_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_AUTH_USER", pageId, currentUserId);
		// �e���v���[�g�X�V��s�ҏ���_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_UPDATE_PROXY_USER", pageId, currentUserId);
		// �e���v���[�g�R���e���c�]���A�C�e������_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_RATE_ITEM", pageId, currentUserId);
		// �e���v���[�g�R���e���c�R�����g�Ǘ��ҏ���_���폜
		templatePageDao.updateTemplatePageExtendInvalid("TEMPLATE_PAGE_COMMENT_ADMIN", pageId, currentUserId);

		// �e���v���[�g�R���e���c���֏���o�^
		// �e���v���[�g�R���e���c����o�^
		templatePageDao.insertTemplatePage(formBean, currentUserId);
		// �e���v���[�g�R���e���c�Y�t�t�@�C������o�^
		templatePageDao.insertTemplatePageAttachment(formBean, currentUserId);
		// �e���v���[�g�R���e���c�����N����o�^
		templatePageDao.insertTemplatePageLink(formBean, currentUserId);
		// �e���v���[�g�R���e���c�{�������g�b�v�O���[�v����o�^
		templatePageDao.insertTemplateAuthLeadingGroup(formBean, currentUserId);
		// �e���v���[�g�R���e���c�{���������[�U����o�^
		templatePageDao.insertTemplateAuthUser(formBean, currentUserId);
		// �e���v���[�g�X�V��s�ҏ���o�^
		templatePageDao.insertTemplateUpdateProxyUser(formBean, currentUserId);
		
		// �R���e���c�]�����u����v�̏ꍇ�A�]�����ւ����s
		if ("1".equals(formBean.getEvaluationFlag())) {
			// �e���v���[�g�R���e���c�]���A�C�e������o�^
			templatePageDao.insertTemplatePageEvaluation(formBean, currentUserId);
			// �e���v���[�g�R���e���c�R�����g�Ǘ��ҏ���o�^
			templatePageDao.insertTemplatePageCommentAdmin(formBean, currentUserId);
		}
		
		// �e���v���[�g���֑���
		templateFileRelated(formBean, currentUserId);
	}

	/**
	 * �e���v���[�g���֑���
	 * @param formBean
	 * @param currentUserId
	 * @throws Exception 
	 */
	private void templateFileRelated(PageFormBean formBean, String currentUserId) throws Exception {
		try {
			// �e���v���[�g�y�[�WID
			String templatePageId = formBean.getTemplatePageId();
			// �y�[�WID
			String pageId = formBean.getPageId();

			// �R�s�[���R���e���c��HTML�t�@�C���i�[�p�X
			String htmlfilefrom = "";
			// �R�s�[��e���v���[�g�R���e���c��HTML�t�@�C���i�[�p�X
			String htmlfileto = "";
			String attfilefrom = "";
			String attfileto = "";
			
			// �\��̏ꍇ
			if("1".equals(formBean.getIsReserve())) {
				htmlfilefrom = FsPropertyUtil.getStringProperty("htmlFile.temp.path");
				attfilefrom = FsPropertyUtil.getStringProperty("server.upload.temp.path");
			} else {
				htmlfilefrom = FsPropertyUtil.getStringProperty("htmlFile.path");
				attfilefrom = FsPropertyUtil.getStringProperty("server.upload.path");
			}
			String fileSeparator = File.separator;
			htmlfileto = FsPropertyUtil.getStringProperty("template.htmlFile.path") + fileSeparator + currentUserId;
			attfileto = FsPropertyUtil.getStringProperty("template.server.upload.path") + fileSeparator + currentUserId;
			// �����̃t�H���_���폜
			FileUtil.deletePurgeFile(htmlfileto, templatePageId);
			FileUtil.deletePurgeFile(attfileto, templatePageId);
			// ���I�R���e���c�̏ꍇ
			if (ConstantContainer.PAGE_DYNAMIC.equals(formBean.getHtmlFlag())){
				List<PageAttachmentBean> fileList = templatePageDao.getTemplatePageAttachList(formBean, currentUserId);
				if (fileList != null){
					for (PageAttachmentBean bean : fileList) {
						String fileUrl = bean.getAttachmentFileUrl();
						String toFileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
						String fromFileName = pageId + fileUrl.substring(fileUrl.lastIndexOf(templatePageId) + 13);
						FileUtil.copyFile(attfilefrom, attfileto, fromFileName, toFileName);
					}
				}
				
				// �R���e���cHTML���e���v���[�g�t�H���_�ɃR�s�[
				FileUtil.copyHtmlPageToTemplate(htmlfilefrom, htmlfileto, formBean.getPageId(), templatePageId);
			} else{
				//�@�ÓI�R���e���c�̏ꍇ
				// �ÓI�e���v���[�g�R���e���c�t�@�C���g���q
				PageBean templatePageBean = templatePageDao.getTemplatePage(pageId, currentUserId);
				String file_suffix = templatePageBean.getFileSuffix();
	            if (file_suffix != null && !"".equals(file_suffix) && file_suffix.indexOf(templatePageId) < 0) {                    
	                String toFileName = templatePageId + file_suffix;
	                String fromFileName = pageId + file_suffix;
	                File file = new File(htmlfilefrom + File.separator + fromFileName);
	                if (file.isFile()) {
	                    FileUtil.copyFile(htmlfilefrom, htmlfileto, fromFileName, toFileName);
	                }
	            } else {
	                FileUtil.copyStaticForTemplate(htmlfilefrom, htmlfileto, pageId, templatePageId);
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
	 * �e���v���[�g�폜����
	 * @param formBean
	 * @throws Exception 
	 */
	public void deleteTemplate(PageFormBean formBean) throws Exception{

		// �e���v���[�g�y�[�WID
		String templatePageId = formBean.getTemplatePageId();
		// ���O�C�����[�UID
		String currentUserId = StringUtil.nullToBlank(getLoginUserBean().getUserId());

		// �e���v���[�g�R���e���c���֏���_���폜
		// �e���v���[�g�R���e���c����_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE", templatePageId, currentUserId);
		// �e���v���[�g�R���e���c�Y�t�t�@�C������_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_ATTACHMENT", templatePageId, currentUserId);
		// �e���v���[�g�R���e���c�����N����_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_LINK", templatePageId, currentUserId);
		// �e���v���[�g�R���e���c�{�������g�b�v�O���[�v����_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_AUTH_LEADING_GROUP", templatePageId, currentUserId);
		// �e���v���[�g�R���e���c�{���������[�U����_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_AUTH_USER", templatePageId, currentUserId);
		// �e���v���[�g�X�V��s�ҏ���_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_UPDATE_PROXY_USER", templatePageId, currentUserId);
		// �e���v���[�g�R���e���c�]���A�C�e������_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_RATE_ITEM", templatePageId, currentUserId);
		// �e���v���[�g�R���e���c�R�����g�Ǘ��ҏ���_���폜
		templatePageDao.updateTemplatePageExtendInvalidById("TEMPLATE_PAGE_COMMENT_ADMIN", templatePageId, currentUserId);

	}	
	
}