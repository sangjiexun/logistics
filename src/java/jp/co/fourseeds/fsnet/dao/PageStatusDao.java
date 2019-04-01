package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.pageStatus.PageStatusResultBean;

/**
 * �R���e���c�󋵊m�FDao�����N���X
 * 
 * File Name: PageStatusDao.java 
 * Created: 2015/11/23
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/11/23		    NTS        	       �쐬
 *
 **/
@Repository
public class PageStatusDao extends BaseDao {

	/**
	 * <p>
	 * �����R���e���c���z�u���擾
	 * </p>
	 * 
	 * @param departmentId
	 * 
	 */
	public List<PageStatusResultBean> getPageStatusList(Map<String, Object> param) {
		//DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageStatus.getPageStatusList", param);
	}

	/**
	 * <p>
	 * �����N���ݒ�������̓����N��R���e���c�폜�ϔ���
	 * </p>
	 * 
	 * @param pageId
	 * 
	 */
	public int getPageLinkNum(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("pageStatus.getPageLinkNum", pageId);
	}

	/**
	 * <p>
	 * ���J�����܂ܕҏW����i�R���e���c�\��e�[�u���ɍ폜����Ă��Ȃ������y�[�W�h�c�̃R���e���c�����݂��邩�j
	 * </p>
	 * 
	 * @param pageId
	 * 
	 */
	public int getReservePageNum(String pageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("pageStatus.getReservePageNum", param);
	}
	/**
	 * <p>
	 * �w�胆�[�U���Y���R���e���c���{���ł��邩����
	 * </p>
	 * 
	 * @param pageId
	 * @param userId
	 * 
	 */
	public int getInspflg(String pageId, String userId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_PAGE_ID", pageId);
		return (Integer)this.sqlSessionTemplate.selectOne("pageStatus.getInspNum", param);
	}

	/**
	 * <p>
	 * ���ʃR���e���c�𒊏o
	 * </p>
	 * 
	 * @param param
	 * 
	 */
	public List<PageStatusResultBean> getChildPageList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("pageStatus.getChildPageList", param);
	}

	public List<PageStatusResultBean> getLinkPageList(Map<String, Object> param) {
		return this.sqlSessionTemplate.selectList("pageStatus.getLinkPageList", param);
	}

	/**
	 * <p>
	 * �R���e���c�����擾
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public PageStatusResultBean getPageInfo(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectOne("pageStatus.getPageInfo", param);
	}

	/**
	 * <p>
	 * ���J����O���[�v���X�g���擾
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getOpenGroupList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getOpenGroupList", param);
	}

	/**
	 * <p>
	 * ���J����l���X�g���擾
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getOpenPersonalList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getOpenPersonalList", param);
	}

	/**
	 * <p>
	 * ���F�҃��X�g���擾
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getAuthorizerList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getAuthorizerList", param);
	}

	/**
	 * <p>
	 * �����N���X�g���擾
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getPageLinkList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getLinkList", param);
	}

	/**
	 * <p>
	 * �Y�t�t�@�C�����X�g���擾
	 * </p>
	 * 
	 * @param searchPageId
	 * 
	 */
	public List<PageStatusResultBean> getAttachmentList(String searchPageId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", searchPageId);
		return this.sqlSessionTemplate.selectList("pageStatus.getAttachmentList", param);
	}
}