package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.page.AuthGroupBean;
import jp.co.fourseeds.fsnet.beans.page.AuthUserBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentAdminBean;
import jp.co.fourseeds.fsnet.beans.page.PageCommentRateBean;
import jp.co.fourseeds.fsnet.beans.page.PageDeleteBean;
import jp.co.fourseeds.fsnet.beans.page.PageLinkBean;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.page.PageUserCommentBean;
import jp.co.fourseeds.fsnet.beans.page.PageUserRateBean;
import jp.co.fourseeds.fsnet.beans.page.ProxyUserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * �R���e���c���@�\Dao�����N���X
 * 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		    NTS        	       �쐬
 *
 **/
@SuppressWarnings(value={"rawtypes"})
@Repository
public class PageDao extends BaseDao {
	public PageBean getPage(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getPage", param);
	}
	
	public PageBean getPageReserve(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getPageReserve", param);
	}
	
	public List getPageLinkList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageLinkList", param);
	}
	
	public List getPageLinkListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageLinkListRsv", param);
	}
	
	public List getPageAttachList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageAttachList", param);
	}
	
	public List getPageAttachListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageAttachListRsv", param);
	}
	
	public List<AuthGroupBean> getAuthGroupList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthGroupList", param);
	}
	
	public List<AuthGroupBean> getAuthGroupListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthGroupListRsv", param);
	}
	
	public List<AuthUserBean> getAuthUserList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthUserList", param);
	}
	
	public List<AuthUserBean> getAuthUserListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getAuthUserListRsv", param);
	}
	
	public List<ProxyUserBean> getProxyUserList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getProxyUserList", param);
	}
	
	public List<ProxyUserBean> getProxyUserListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getProxyUserListRsv", param);
	}
	
	public List getPageRateItemList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageRateItemList", param);
	}
	
	public List getPageRateItemListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageRateItemListRsv", param);
	}
	
	public List getCommentAdminList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminList", param);
	}
	
	public List getCommentAdminListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminListRsv", param);
	}
	
	public List getCommentAdminOptList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminOptList", param);
	}
	
	public List getCommentAdminOptListRsv(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getCommentAdminOptListRsv", param);
	}
	
	/**
	 * ���[�U���̂��擾�i���񍐑Ή��ҁj
	 * @param userId ���[�UID
	 * @return�@���[�U����
	 */
	public String getCreateUserName(String userId)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectOne("page.getCreateUserName", param);
	}
	
	/**
	 * �y�[�WID�ɂ���āA�y�[�W����TBL���𕨗��폜
	 */
	public void deletePageExtend(String pageId, String tblNm) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tblNm);
		param.put("PARA_PAGE_ID", pageId);

		this.sqlSessionTemplate.delete("page.deletePageExtend", param);
	}
	
	/**
	 * �y�[�WID�ɂ���āA�y�[�W����TBL����_���폜
	 */
	public void updatePageExtendInvalid(String pageId, String tblNm, String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tblNm);
		param.put("PARA_PAGE_ID", pageId);
		param.put("updateBy", userId);

		this.sqlSessionTemplate.delete("page.updatePageExtendInvalid", param);
	}	

	/**
	 * ���q�R���e���c��񃊃X�g���擾
	 * @param pageId �e�y�[�WID
	 */
	public List<PageBean> getChildPageList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_P_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getChildPageList", param);
	}

	/**
	 * �\��q�R���e���c��񃊃X�g���擾
	 * @param pageId �e�y�[�WID
	 */
	public List<PageBean> getChildPageReserveList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_P_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getChildPageReserveList", param);
	}
	
	public List<PageBean> getOpenChildPageList(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_P_PAGE_ID", pageId);
		param.put("PARA_END_DATE", new Date());
		return this.sqlSessionTemplate.selectList("page.getOpenChildPageList", param);
	}
	
	/**
	 * �e�y�[�WID�ƕ\�����ɂ���āA�R���e���c���X�V
	 * 
	 * @param 
	 */
	public void updateBrothersOrderBy(String pageId, String ppageId, String orderBy, String userId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_P_PAGE_ID", ppageId);
		param.put("PARA_ORDER_BY", orderBy);
		param.put("PARA_USER_ID", userId);
		
		this.sqlSessionTemplate.update("page.updateBrothersOrderBy", param);
	}

	public void insertPage(PageBean bean) {
		this.sqlSessionTemplate.insert("page.insertPage", bean);
	}
	public void insertPageRsv(PageBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageReserve", bean);
	}
	public int updatePage(PageBean bean) {
		return this.sqlSessionTemplate.update("page.updatePage", bean);
	}
	
	/**
	 * �R���e���c�\������X�V
	 * @param bean
	 */
	public int updatePageReserve(PageBean bean) {
		return this.sqlSessionTemplate.update("page.updatePageReserve", bean);
	}
	
	/**
	 * �^���R���e���c�̌��J�ێ��ҏW�t���O�uON_EDIT_FLAG�v���X�V
	 * A�j�\��R���e���c�o�^���_�ŁA1(���J�����܂ܕҏW�ƂȂ�)�ɍX�V
	 * B�j�\��R���e���c�폜���_�ŁA0(���J�����܂ܕҏW�ɂ͂Ȃ�Ȃ�)�ɍX�V
	 * 
	 * @param 
	 */
	public int updateOnEditFlag(PageBean pageBean) {
		return this.sqlSessionTemplate.update("page.updateOnEditFlag", pageBean);
	}
	
	/**
	 * �^���R���e���c�̌��J�ێ��ҏW�t���O�uON_EDIT_FLAG�v���X�V
	 * A�j�\��R���e���c�o�^���_�ŁA1(���J�����܂ܕҏW�ƂȂ�)�ɍX�V
	 * B�j�\��R���e���c�폜���_�ŁA0(���J�����܂ܕҏW�ɂ͂Ȃ�Ȃ�)�ɍX�V
	 * 
	 * @param 
	 */
	public void deleteLogInfo(String page_id, String userid) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", page_id);
		param.put("PARA_UPDATE_BY", userid);
		
		this.sqlSessionTemplate.update("page.deleteLogInfo", param);
	}
	public void insertPageLink(PageLinkBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageLink", bean);
	}
	public void insertPageLinkRsv(PageLinkBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageLinkRsv", bean);
	}
	
	public void deletePageAttachment(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.deletePageAttachment", bean);
	}
	public void updatePageAttachment(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.updatePageAttachment", bean);
	}
	public void insertPageAttachment(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.insertPageAttachment", bean);
	}
	public void insertPageAttachmentRsv(PageAttachmentBean bean) {
		this.sqlSessionTemplate.update("page.insertPageAttachmentRsv", bean);
	}

	public void insertAuthGroup(AuthGroupBean bean) {
		// �K�{�{���敪
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthGroup", bean);
	}
	public void insertAuthGroupRsv(AuthGroupBean bean) {
		// �K�{�{���敪
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthGroupRsv", bean);
	}
	public void insertAuthUser(AuthUserBean bean) {
		// �K�{�{���敪
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthUser", bean);
	}
	public void insertAuthUserRsv(AuthUserBean bean) {
		// �K�{�{���敪
		bean.setNecessityReadFlag(StringUtil.nullToZero(bean.getNecessityReadFlag()));
		this.sqlSessionTemplate.insert("page.insertAuthUserRsv", bean);
	}
	public void insertProxyUser(ProxyUserBean bean) {
		this.sqlSessionTemplate.insert("page.insertProxyUser", bean);
	}
	public void insertProxyUserRsv(ProxyUserBean bean) {
		this.sqlSessionTemplate.insert("page.insertProxyUserRsv", bean);
	}
	public void insertPageRateItem(PageRateItemBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageRateItem", bean);
	}
	public void insertPageRateItemRsv(PageRateItemBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageRateItemRsv", bean);
	}
	public void updatePageRateItem(PageRateItemBean bean) {
		this.sqlSessionTemplate.update("page.updatePageRateItem", bean);
	}
	public void insertPageCommentAdmin(PageCommentAdminBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageCommentAdmin", bean);
	}
	public void insertPageCommentAdminRsv(PageCommentAdminBean bean) {
		this.sqlSessionTemplate.insert("page.insertPageCommentAdminRsv", bean);
	}
	public void deleteAuthGroup(AuthGroupBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthGroup", bean);
	}
	public void deleteAuthGroupRsv(AuthGroupBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthGroupRsv", bean);
	}
	public void deleteAuthUser(AuthUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthUser", bean);
	}
	public void deleteAuthUserRsv(AuthUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteAuthUserRsv", bean);
	}
	public void deleteProxyUser(ProxyUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteProxyUser", bean);
	}
	public void deleteProxyUserRsv(ProxyUserBean bean) {
		this.sqlSessionTemplate.delete("page.deleteProxyUserRsv", bean);
	}

	/**
	 * �u�V�K�v�\���ێ��\���t���O���擾
	 * @param pageId �y�[�WID
	 * @return�@�u�V�K�v�\���ێ��\���t���O
	 */
	public String getViewNewKeepFlag(String pageId)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_UPDATE_DATE", Integer.parseInt(FsPropertyUtil.getStringProperty("update.date")));
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getViewNewKeepFlag", param);
	}
	
	/**
	 * �Ώێ҂̃h���b�v�_�E�����X�g
	 * @param ��������
	 * @return �敪?�Ƌ敪���e
	 */
	public List<LabelValueBean> getUserDivisionDropDownList(Map<Object, Object> param) {
		return this.sqlSessionTemplate.selectList("page.getUserDivisionDropDownList", param);
	}	
	
	/**
	 * �R���e���c�Y�t�t�@�C�����̍ő�URL���擾
	 * @param pageId �y�[�WID
	 * @return �ő�URL��Ԃ�
	 */
	public String getMaxAttachmentUrl(String pageId) {
		String strValue = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		List list =  this.sqlSessionTemplate.selectList("page.getMaxAttachmentUrl", param);
		if (list != null && list.size() > 0) {
			strValue = (String) list.get(0);
		}
		return strValue;
	}
	
	/**
	 * �R���e���c�R�����g���v�����擾
	 * @param pageId
	 * @return int �R���e���c�R�����g���v
	 * */
	public int getCommentCount(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("page.getCommentCount", param);
	}
	
	/**
	 * �]�����ڕ\���t���O
	 * @param pageId
	 * @return
	 * @throws DataBaseException
	 */
	public int getShowHyoukaFlag(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("page.getShowHyoukaFlag", param);
	}
	
	/**
	 * �R���e���c�]�����A�]�����v�����擾
	 * @param pageId
	 * @return List
	 * @throws DataBaseException
	 */
	public List<PageRateItemBean> getEvaluationTotalList(String pageId, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectList("page.getEvaluationTotalList", param);
	}
	
	/**
     * �Y�����[�U�̃R���e���c�]�����v���e�[�u���̕]�����𕨗��폜����
     * @param pageId
     * @param currentUserId
     * @param hyoukaSequence
     * @param hyoukaOrderBy
     */
	public void deleteEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		param.put("PARA_EVALUATION_ORDER_BY", hyoukaOrderBy);
		param.put("PARA_SEQUENCE", hyoukaSequence);
		this.sqlSessionTemplate.delete("page.deleteEvaluationCountByUser", param);
	}
	
	/**
	 * �P�ꍀ�ڕ]���̎��ɁA�Y�����[�U�̑S�ĕ]�����𕨗��폜����
	 * @param pageId
	 * @param currentUserId
	 */
	public void deleteEvaluationByUser(String pageId, String currentUserId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", currentUserId);
		this.sqlSessionTemplate.delete("page.deleteEvaluationByUser", param);
	}
	
	/**
	 * ���[�UID�ƍ��ڏ��Ԃ��R���e���c�]�����v���e�[�u���ɓo�^����
	 * @param pageId
	 * @param currentUserId
	 * @param hyoukaSequence
	 * @param hyoukaOrderBy
	 */
	public void insertEvaluationCountByUser(String pageId, String userId, String hyoukaSequence, String hyoukaOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		param.put("PARA_EVALUATION_ORDER_BY", hyoukaOrderBy);
		param.put("PARA_SEQUENCE", hyoukaSequence);
		this.sqlSessionTemplate.insert("insertEvaluationCountByUser", param);
	}
	
	/**
	 * �R���e���c�R�����g���A�R�����g���v�����擾
	 * @param pageId
	 * @param userId
	 * @param flag
	 * @return List
	 * @throws DataBaseException
	 */
	public List<PageCommentRateBean> getCommentTotalList(String pageId, String userId, String flag) {
		String commentSize = FsPropertyUtil.getStringProperty("comment.size");
		String commentDisplaySize = FsPropertyUtil.getStringProperty("commentdisplay.size");
		
		StringBuffer sqlWhere = new StringBuffer("");
		sqlWhere.append(" WHERE ");
		sqlWhere.append("  ROWNUM <= ");
		if ("init".equals(flag)) {
			sqlWhere.append(commentDisplaySize);
		} else if("showAll".equals(flag)) {
			sqlWhere.append(commentSize);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", userId);
		param.put("SQL_WHERE", sqlWhere.toString());
		
		return this.sqlSessionTemplate.selectList("page.getCommentTotalList", param);
	}
	
	/**
	 * �R���e���c�R�����g����ǉ�(���e)
	 * @param pageId
	 * @param userId
	 * @param addCommentInfo
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public void addComment(String pageId,  String userId, String addCommentInfo) {
		PageUserCommentBean pageUserComentBean = new PageUserCommentBean();
		pageUserComentBean.setPageId(pageId);
		pageUserComentBean.setUserId(userId);
		pageUserComentBean.setCreateBy(userId);
		pageUserComentBean.setCommentInfo(addCommentInfo);
		this.sqlSessionTemplate.insert("page.addComment", pageUserComentBean);
	}
	 /**
     * �Y�����[�U�̃R�����g�����X�V����
     * @param pageId
     * @param currentUserId
     * @param commentOrderBy
     * @param commentUserId
     * @param commentInfo
     * @throws DataBaseException
     */
    public void updateComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId, String commentInfo) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", commentUserId);
		param.put("PARA_COMMENT_INFO", commentInfo);
		param.put("PARA_UPDATE_BY", currentUserId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
		this.sqlSessionTemplate.update("page.updateComment", param);
    }
    
    /**
     * �R�����g����_���폜(�X�V)����̏ꍇ�A�Y���R�����g���v���𕨗��폜����
     * @param pageId
     * @param commentOrderBy
     * @throws DataBaseException
     */
    public void deleteCommentCountByOrderBy(String pageId, String commentOrderBy) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
    	this.sqlSessionTemplate.delete("page.deleteCommentCountByOrderBy", param);
    }
    
    /**
     * �Y�����[�U�̃R�����g����_���폜����
     * @param pageId
     * @param currentUserId
     * @param commentOrderBy
     * @param commentUserId
     * @throws DataBaseException
     */
    public void deleteComment(String pageId, String currentUserId, String commentOrderBy, String commentUserId) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
    	param.put("PARA_USER_ID", commentUserId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
		param.put("PARA_UPDATE_BY", currentUserId);
    	this.sqlSessionTemplate.delete("page.deleteComment", param);
    }
    
    /**
     * �Y�����[�U�̃R�����g���v���e�[�u���̃R�����g���𕨗��폜����
     * @param pageId
     * @param userId
     * @param commentOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void deleteCommentCountByUser(String pageId, String userId, String commentOrderBy) {
    	Map<String, Object> param = new HashMap<String, Object>();
    	param.put("PARA_PAGE_ID", pageId);
    	param.put("PARA_USER_ID", userId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
    	this.sqlSessionTemplate.delete("page.deleteCommentCountByUser", param);
    }
    
    /**
     * ���[�UID�ƃR�����g���Ԃ��R�����g���v���e�[�u���ɓo�^����
     * @param pageId
     * @param userId
     * @param commentOrderBy
     * @throws ServiceException
     * @throws DataBaseException
     */
    public void insertCommentCountByUser(String pageId, String userId, String commentOrderBy) {
    	PageCommentRateBean pageCommentRateBean = new PageCommentRateBean();
    	pageCommentRateBean.setPageId(pageId);
    	pageCommentRateBean.setCommentOrderBy(commentOrderBy);
    	pageCommentRateBean.setUserId(userId);
    	
		this.sqlSessionTemplate.insert("page.insertCommentCountByUser", pageCommentRateBean);
    }
    
	/**
	 * �R���e���c�m�F��ʂ̕]�������擾
	 * @param pageId �y�[�WID
	 * @param sequence SEQUENCE
	 * @return�@�]����
	 */
	public String getEvaluationCount(String pageId, String sequence)  {
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�WID
		param.put("PARA_PAGE_ID", pageId);
		// SEQUENCE
		param.put("PARA_SEQUENCE", sequence);
		return this.sqlSessionTemplate.selectOne("page.getEvaluationCount", param);
	}

	/**
	 * �R���e���c�]���A�C�e������_���폜
	 * @param page_id �y�[�WID
	 * @param sequence�@SEQUENCE
	 * @param userId�@���[�UID
	 */
	public void updateEvaluationInvalidBySeq(String page_id, String sequence, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("updateBy", userId);
		param.put("PARA_PAGE_ID", page_id);
		param.put("PARA_SEQUENCE", sequence);
		this.sqlSessionTemplate.update("page.updateEvaluationInvalidBySeq", param);
	}
	
	/**
	 * �R�����g���[�����M��ʂŃ��[�U�R�����g���ׂ��擾
	 * @param pageId �y�[�WID
	 * @param commentOrderBy �R�����g����
	 * @param userId �y�[�WID
	 */
	public PageCommentRateBean getUserCommentForMail(String pageId, String commentOrderBy, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_COMMENT_ORDER_BY", commentOrderBy);
		return this.sqlSessionTemplate.selectOne("page.getUserCommentForMail", param);
	}
	
	/**
	 * @param String userId
	 * @function ���M����A�b�b�̃��[���Ǝ���
	 */
	public List getMailAddress(String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectList("page.getMailAddress", param);
	}
	
	/**
	 * �������J�R���e���c�����X�V
	 * @param PageBean bean
	 */
	public int updatePageForOpen(PageBean bean) {
		return this.sqlSessionTemplate.update("page.updatePageForOpen", bean);
	}

	/**
	 * �R���e���c���[�U�[�]�������폜
	 * @param PageUserRateBean bean
	 */
	public int updatePageUserRateInvalidByPageId(PageUserRateBean bean) {
		return this.sqlSessionTemplate.update("page.updatePageUserRateInvalidByPageId", bean);
	}	
	
	/**
	 * �R���e���c�]���A�C�e�����̍폜���ɂ��A�R���e���c���[�U�[�]������_���폜
	 * @param pageId �y�[�WID
	 * @param userId �X�V���[�UID
	 */
	public int updatePageUserRateInvalidByRateItem(String pageId, String userId) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("updateBy", userId);
		param.put("pageId", pageId);

		return this.sqlSessionTemplate.update("page.updatePageUserRateInvalidByRateItem", param);
	}

	/**
	 * �R���e���c���[�U�[�]�����̍��ڏ��Ԃ��X�V
	 * �X�V�L�[�F�y�[�WID�ASEQUENCE
	 * @param bean
	 */
	public int updatePageUserRateOrderBySeq(PageUserRateBean pageUserRateBean) {
		return this.sqlSessionTemplate.update("page.updatePageUserRateOrderBySeq", pageUserRateBean);
	}

	/**
	 * �R���e���c�����N�\���񂩂�R���e���c�����N���Ɉړ��i�L�[�F�y�[�WID�j
	 * @param bean
	 */
	public int linkReserveToLinkByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.linkReserveToLinkByPageId", param);
	}

	/**
	 * �R���e���c�Y�t�t�@�C���\���񂩂�R���e���c�Y�t�t�@�C�����Ɉړ��i�L�[�F�y�[�WID�j
	 * @param bean
	 */
	public int attachmentReserveToAttachmentByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.attachmentReserveToAttachmentByPageId", param);
	}

	/**
	 * �R���e���c�{�������g�b�v�O���[�v�\���񂩂�R���e���c�{�������g�b�v�O���[�v���Ɉړ��i�L�[�F�y�[�WID�j
	 * @param bean
	 */
	public int leadingGroupReserveToLeadingGroupByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.leadingGroupReserveToLeadingGroupByPageId", param);
	}

	/**
	 * �R���e���c�{���������[�U�\���񂩂�R���e���c�{���������[�U���Ɉړ��i�L�[�F�y�[�WID�j
	 * @param bean
	 */
	public int authUserReserveToAuthUserByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.authUserReserveToAuthUserByPageId", param);
	}
	
	/**
	 * �X�V��s�җ\���񂩂�X�V��s�ҏ��Ɉړ��i�L�[�F�y�[�WID�j
	 * @param bean
	 */
	public int proxyUserReserveToProxyUserByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.proxyUserReserveToProxyUserByPageId", param);
	}
	
	/**
	 * �R���e���c�]���A�C�e���\����ɑ��݂��Ȃ���΁A�R���e���c�]���A�C�e������_���폜
	 * @param bean
	 */
	public int updateRateItemInvalidByRateItemReserve(PageRateItemBean pageRateItemBean) {
		return this.sqlSessionTemplate.update("page.updateRateItemInvalidByRateItemReserve", pageRateItemBean);
	}
	
	/**
	 * �R���e���c�]���A�C�e�������폜�i��L�[�j
	 * @param departmentId
	 */
	public int deletePageRateItemByPK(PageRateItemBean pageRateItemBean) {
		return this.sqlSessionTemplate.delete("page.deletePageRateItemByPK", pageRateItemBean);
	}
	
	/**
	 * �R���e���c�R�����g�Ǘ��җ\���񂩂�R���e���c�R�����g�Ǘ��ҏ��Ɉړ��i�L�[�F�y�[�WID�j
	 * @param bean
	 */
	public int commentAdminReserveToCommentAdminByPageId(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.commentAdminReserveToCommentAdminByPageId", param);
	}
	
	/**
	 * �R���e���c�\���񂩂�R���e���c���ɍX�V�i�L�[�F�y�[�WID�j
	 * @param pageId�@�y�[�WID
	 */
	public int updatePageFromReserve(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);

		return this.sqlSessionTemplate.update("page.updatePageFromReserve", param);
	}
	
	/**
	 * ���O����o�^
	 * @param userId ���[�UID
	 * @param pageId �y�[�WID
	 * @param updateBy �X�V���[�U
	 */
	public void insertLog(String userId, String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_PAGE_ID", pageId);
		
		this.sqlSessionTemplate.insert("page.insertLog", param);
	}
	
	/**
	 * ���R���e���c����_���폜
	 * 
	 * @param 
	 */
	public int updatePageInvalid(PageBean pageBean) {
		return this.sqlSessionTemplate.update("page.updatePageInvalid", pageBean);
	}
	
	/**
	 * �R���e���c�폜���[���惆�[�U����
	 * @param userId ���[�UID
	 * @return�@���[�U����
	 */
	public String getMailToUserName(String userId, String flag)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_FLAG", flag);
		return this.sqlSessionTemplate.selectOne("page.getMailToUserName", param);
	}

	/**
	 * ���ԗL���ȃR���e���c���擾
	 * @param pageId
	 * @return
	 */
	public PageDeleteBean getPagePeriodValid(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectOne("page.getPagePeriodValid", param);
	}
	
	/**
	 * ���[�U���̂��擾
	 * @param userId ���[�UID
	 * @return�@���[�U����
	 */
	public String getUserName(String userId)  {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		return this.sqlSessionTemplate.selectOne("page.getUserName", param);
	}

	/**
	 * �R���e���c�����N�����擾�i�L�[�F�����NURL�j
	 * @param tableName
	 * @param pageId
	 * @return
	 */
	public List getPageLinkListByUrl(String tableName, String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_TABLE_NAME", tableName);
		param.put("PARA_PAGE_ID", pageId);
		return this.sqlSessionTemplate.selectList("page.getPageLinkListByUrl", param);
	}
	
	/**
	 * �����؂�������擾
	 * @param pageId
	 * @return int �R���e���c�R�����g���v
	 * */
	public int getPageLinkExpiredDateCount(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_EXPIRED_DATE", Integer.parseInt(FsPropertyUtil.getStringProperty("expired.date")));
		return (Integer)this.sqlSessionTemplate.selectOne("page.getPageLinkExpiredDateCount", param);
	}
	
	/**
	 * �]���惁�[���A�h���X���X�g���擾
	 * @param userIds
	 * @return
	 */
	public List<String> getTrmailAddressList(String userIds) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userIds);
		return this.sqlSessionTemplate.selectList("page.getTrmailAddressList", param);
	}

}