package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageFormBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * �e���v���[�g�@�\Dao�����N���X
 * 
 * Created: 2015/09/22
 * Original Author: NTS�� 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		    NTS���@       	       �V�K�쐬
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class TemplatePageDao extends BaseDao {

	/**
	 * �e���v���[�g�R���e���c�����擾
	 * @param pageId
	 * @param currentUserId
	 * @return
	 */
	public PageBean getTemplatePage(String pageId, String currentUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", currentUserId);
		return this.sqlSessionTemplate.selectOne("templatePage.getTemplatePage", param);
	}
	
	/**
	 * �e���v���[�g�R���e���c�����擾
	 * @param pageId
	 * @param currentUserId
	 * @return
	 */
	public PageBean getTemplatePagebyID(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectOne("templatePage.getTemplatePagebyID", param);
	}
	
	public List getTemplatePageLinkList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplatePageLinkList", param);
	}
	
	public List getTemplatePageAttachList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplatePageAttachList", param);
	}
	
	public List<AuthGroupBean> getTemplateAuthGroupList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplateAuthGroupList", param);
	}
	
	public List<AuthUserBean> getTemplateAuthUserList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplateAuthUserList", param);
	}
	
	public List<ProxyUserBean> getTemplateProxyUserList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplateProxyUserList", param);
	}
	
	public List getTemplatePageRateItemList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplatePageRateItemList", param);
	}
	
	public List getTemplateCommentAdminList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplateCommentAdminList", param);
	}
	
	public List getTemplateCommentAdminOptList(String templatepageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplateCommentAdminOptList", param);
	}
	
	public List getTemplateEvaluationTotalList(String templatepageId, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", templatepageId);
		param.put("PARA_USER_ID", templatepageId);
		return this.sqlSessionTemplate.selectList("templatePage.getTemplateEvaluationTotalList", param);
	}
	
	/**
	 * �e���v���[�g�y�[�W����TBL����_���폜
	 */
	public void updateTemplatePageExtendInvalid(String tblNm, String pageId, String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tblNm);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);

		this.sqlSessionTemplate.update("templatePage.updateTemplatePageExtendInvalid", param);
	}
	
	/**
	 * �e���v���[�g�y�[�W����TBL����_���폜(�L�[�F�e���v���[�g�y�[�WID)
	 */
	public void updateTemplatePageExtendInvalidById(String tblNm, String templatePageId, String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tblNm);
		param.put("PARA_TEMPLATE_PAGE_ID", templatePageId);
		param.put("PARA_USER_ID", userId);

		this.sqlSessionTemplate.update("templatePage.updateTemplatePageExtendInvalidById", param);
	}	
	
	/**
	 * �R���e���c(�\��)���e�[�u������e���v���[�g�R���e���c���ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplatePage(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "PAGE");
		return this.sqlSessionTemplate.update("templatePage.insertTemplatePage", param);
	}
	
	/**
	 * �R���e���c�Y�t�t�@�C�����(�\��)�e�[�u������e���v���[�g�R���e���c�Y�t�t�@�C�����ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplatePageAttachment(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "PAGE_ATTACHMENT");
		param.put("PARA_TEMPATT", FsPropertyUtil.getStringProperty("template.attachmentFile.url") + "/" + currentUserId + "/" + formBean.getTemplatePageId());
		return this.sqlSessionTemplate.update("templatePage.insertTemplatePageAttachment", param);
	}
	
	/**
	 * �R���e���c�����N���(�\��)�e�[�u������e���v���[�g�R���e���c�����N���ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplatePageLink(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "PAGE_LINK");
		return this.sqlSessionTemplate.update("templatePage.insertTemplatePageLink", param);
	}
	
	/**
	 * �R���e���c�{�������g�b�v�O���[�v���(�\��)�e�[�u������e���v���[�g�R���e���c�{�������g�b�v�O���[�v���ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplateAuthLeadingGroup(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "AUTH_LEADING_GROUP");
		return this.sqlSessionTemplate.update("templatePage.insertTemplateAuthLeadingGroup", param);
	}
	
	/**
	 * �R���e���c�{���������[�U���(�\��)�e�[�u������e���v���[�g�R���e���c�{���������[�U���ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplateAuthUser(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "AUTH_USER");
		return this.sqlSessionTemplate.update("templatePage.insertTemplateAuthUser", param);
	}
	
	/**
	 * �X�V��s�ҏ��(�\��)�e�[�u������e���v���[�g�X�V��s�ҏ��ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplateUpdateProxyUser(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "UPDATE_PROXY_USER");
		return this.sqlSessionTemplate.update("templatePage.insertTemplateUpdateProxyUser", param);
	}
	
	/**
	 * �R���e���c�]���A�C�e�����(�\��)�e�[�u������e���v���[�g�R���e���c�]���A�C�e�����ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplatePageEvaluation(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "PAGE_RATE_ITEM");
		return this.sqlSessionTemplate.update("templatePage.insertTemplatePageEvaluation", param);
	}
	
	/**
	 * �R���e���c�R�����g�Ǘ��ҏ�� (�\��)�e�[�u������e���v���[�g�R���e���c�R�����g�Ǘ��ҏ�� �ɓo�^
	 * @param formBean
	 * @param currentUserId
	 */
	public int insertTemplatePageCommentAdmin(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = setParam(formBean, currentUserId, "PAGE_COMMENT_ADMIN");
		return this.sqlSessionTemplate.update("templatePage.insertTemplatePageCommentAdmin", param);
	}
	
	/**
	 * �p�����[�^�[��ݒ�
	 * @param formBean
	 * @param currentUserId
	 * @param tableName
	 * @return
	 */
	private Map<String, Object> setParam(PageFormBean formBean, String currentUserId, String tableName) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", formBean.getTemplatePageId());
		param.put("PARA_PAGE_ID", formBean.getPageId());
		param.put("PARA_USER_ID", currentUserId);
		param.put("PARA_HTML_FILE_URL", formBean.getContextPath() + "/template_view.do");
		param.put("PARA_TABLE_NAME", "1".equals(formBean.getIsReserve()) ? (tableName + "_RESERVE") : tableName );
		return param;
	}
	
	/**
	 * �e���v���[�g�R���e���c�Y�t�t�@�C����񃊃X�g���擾
	 * @param formBean
	 * @param currentUserId
	 * @return
	 */
	public List<PageAttachmentBean> getTemplatePageAttachList(PageFormBean formBean, String currentUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TEMPLATE_PAGE_ID", formBean.getTemplatePageId());
		return this.sqlSessionTemplate.selectList("templatePage.getTemplatePageAttachList", param);
	}
	
	/**
	 * ���[�U��񑶍݃��X�g���擾
	 * @param userIds
	 * @return
	 */
	public List<String> getUserExistList(String userIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userIds);
		return this.sqlSessionTemplate.selectList("templatePage.getUserExistList", param);
	}
	
	/**
	 * �g�b�v�O���[�v��񑶍݃��X�g���擾
	 * @param topGroupIds
	 * @return
	 */
	public List<String> getTopGroupExistList(String topGroupIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TOP_GROUP_ID", topGroupIds);
		return this.sqlSessionTemplate.selectList("templatePage.getTopGroupExistList", param);
	}
	
	/**
	 * �y�[�W�����N��񑶍݃��X�g���擾
	 * @param pageIds
	 * @return
	 */
	public List<String> getPageLinkExistList(String pageIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageIds);
		return this.sqlSessionTemplate.selectList("templatePage.getPageLinkExistList", param);
	}
	
	/**
	 * ���喼�̂��擾
	 * @param departmentId
	 * @param existFlag
	 * @return
	 */
	public String getSourceDeptName(String departmentId, String existFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DEPARTMENT_ID", departmentId);
		param.put("PARA_EXIST_FLAG", existFlag);
		return this.sqlSessionTemplate.selectOne("templatePage.getSourceDeptName", param);
	}	
	
}