package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * �R���e���c�]���󋵋@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/02/03		    NTS        	       �쐬
 *
 **/
@Repository
public class PageRateDao extends BaseDao {
	
	/**
	 * �R���e���c���
	 * @param EvaluationStatusFormBean
	 * @param LoginUserBean
	 * 
	 * @return List<EvaluationStatusBean>
	 */
	public List<PageRateSearchResultBean> getContentResultsList(PageRateSearchFormBean contentForm,LoginUserBean loginUser, String strOrderBy) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// ���J�J�n���t
		String startDate= contentForm.getStartDate();
		// ���J�I�����t
		String endDate= contentForm.getEndDate();
		
		// ����������ݒ�
		// ���J�J�n���w�肵�Ȃ��ꍇ�A�ŏ��̌��J�J�n����ݒ�
		if (StringUtil.isEmpty(startDate)) {
			param.put("PARA_START_DATE", "1900/01/01");
		// ���J�J�n���w��̏ꍇ�A��ʂ� ���J�J�n����ݒ�
		} else {
			param.put("PARA_START_DATE", startDate);
		}
		// ���J�I�����w�肵�Ȃ��ꍇ�A�ő�̌��J�I������ݒ�
		if (StringUtil.isEmpty(endDate)) {
			param.put("PARA_END_DATE", "2998/12/31");
		// ���J�I�����w��̏ꍇ�A��ʂ� ���J�I������ݒ�
		} else {
			param.put("PARA_END_DATE", endDate);
		}
		// �^�C�g����
		if (!StringUtil.isEmpty(contentForm.getTitle())) {
			param.put("PARA_TITLE", contentForm.getTitle());
		}
		// ���ݗL���ȃR���e���c�̂ݑΏۃt���O
		param.put("PARA_VALID_FLAG", contentForm.getHyoukaValidFlag());
		// �V�X�e�����p�敪
		param.put("PARA_ROLE", loginUser.getRole());
		// ���[�U�[�h�c
		param.put("PARA_USER_ID", loginUser.getUserId());
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getContentResultsList", param);
	}
	
	/**
	 * �R���e���c�]�����
	 * @param HyoukaContentsForm
	 * @return
	 */
	public List<PageRateDetailBean> getPageRateaResultList(String pageId) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getPageRateaResultList", param);
	}
	
	/**
	 * �R���e���c���
	 * @param pageId
	 * @param loginUser
	 * @return
	 * @throws DataBaseException
	 */
	public List<PageRateSearchResultBean> getEvaluationDetailList(String pageId, LoginUserBean loginUser) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// �ŏ��̌��J�J�n����ݒ�
		param.put("PARA_START_DATE", "1900/01/01");
		// �ő�̌��J�I������ݒ�
		param.put("PARA_END_DATE", "2998/12/31");
		// �V�X�e�����p�敪
		param.put("PARA_ROLE", loginUser.getRole());
		// ���[�U�[�h�c
		param.put("PARA_USER_ID", loginUser.getUserId());
		// �\�[�g
		param.put("PARA_ORDER_BY", "");
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getContentResultsList", param);
	}
	
	/**
	 * �]���ڍד��v���X�g(�]���ڍ�CSV�o�͗p)
	 * @param pageId
	 *           �y�[�WID
	 * @return List<PageRateDetailBean>
	 *           ��������
	 */
	public List<PageRateDetailBean> getEvaluationCountList(String pageId) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getEvaluationCountList", param);
	}
	
	/**
	 * ���o�����i�]���j�]������
	 * @param pageId
	 *           �y�[�WID
	 * @param count
	 *           ����
	 * @return List<PageRateDetailBean>
	 *           ��������
	 */
	public List<PageRateDetailBean> getEvaluationUserIdList(String pageId, int count) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// COUNT
		param.put("PARA_COUNT", count);
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getEvaluationUserIdList", param);
	}
	
	/**
	 * ���o�����i�]���j�]������(�uAnd�����v�`�F�b�N���Ȃ�)
	 * @param pageId
	 *           �y�[�WID
	 * @param str
	 *           SQL
	 * @return List<String>
	 *           ��������
	 */
	public List<String> getPluralNotCheckHyoukaUserIdList(String pageId, String str) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// SQL
		param.put("PARA_SQL", str);
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getPluralNotCheckHyoukaUserIdList", param);
	}
	
	/**
	 * ���o�����i�Ώێҁj�K�{
	 * @param pageId
	 *           �y�[�WID
	 * @return List<String>
	 *           ��������
	 */
	public List<String> getNecesseryReaderList(String pageId) {
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getNecesseryReaderList", param);
	}
	
	/**
	 * �R�����g���
	 * @param detailForm
	 *           ���׃t�H�[��
	 * @param mergelist
	 *           ���X�g
	 * @return List<PageRateDetailBean>
	 *           ��������
	 */
	public List<PageRateDetailBean> getCommentList(PageRateDetailFormBean detailForm, List<String> mergelist, String strOrderBy) {
		
		// �p�����[�^�l����
		Map<String, Object> param = new HashMap<String, Object>();
		// �R�����g���L�[���[�h
		String commentKeyWord = StringUtil.nullToBlank(detailForm.getCommentKeyWord());
		String uId = "";
		if (mergelist != null && mergelist.size() > 0) {
			for (int i = 0; i < mergelist.size(); i++) {
				if (i == mergelist.size() - 1) {
					uId = uId + "'" + (String)mergelist.get(i) + "'";
				} else if ((i % 999) == 0 && i > 0) {
					uId += "'" + (String)mergelist.get(i) + "') OR T1.USER_ID IN ( ";
				} else {
					uId = uId + "'" + (String)mergelist.get(i) + "',";
				}
			}
		}
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", detailForm.getPageId());
		// �R�����g���L�[���[�h
		param.put("PARA_COMMENT_KEY_WORD", commentKeyWord);
		// ���[�U�[
		param.put("PARA_USER_ID", uId);
		// �\�[�g
		param.put("PARA_ORDER_BY", strOrderBy);
		// DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("pageRate.getCommentList", param);
	}
	
}