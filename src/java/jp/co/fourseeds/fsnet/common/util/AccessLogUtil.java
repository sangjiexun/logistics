package jp.co.fourseeds.fsnet.common.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.accessLog.AccessLogBean;
import jp.co.fourseeds.fsnet.beans.accessLog.PageNameDicBean;

@Component
@Scope("prototype")
public class AccessLogUtil extends BaseDao {
	
	/** Log4j action logger instance */
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private PageNameDicBean pageNameDicBean;
	
	/**
	 * �A�N�Z�X���O�o��
	 * 
	 * @param AccessLogBean �A�N�Z�X���O���
	 * 
	 * �������R���e���c�\���̏ꍇ�A�ȉ��̃p�����[�^�ݒ�
	 * 1)�y�[�WID		pageId
	 * 2)�g���K�[		triggerNm		���C�x���g���̂Őݒ�A��j�����A�m�F
	 * 3)�A�N�Z�X�ڍ�	accessDetail
	 * 4)�J�ڌ�			originType
	 * 5)��������		result
	 * 6)�\��t���O		isReserve
	 * 
	 * �������R���e���c�\���ȊO�̏ꍇ�A�ȉ��̃p�����[�^�ݒ�
	 * 1)�y�[�WID		pageId
	 * 1-1)�y�[�W��		pageNm			��pageId�����̏ꍇ�A�y�[�W���ݒ�
	 * 2)�g���K�[		triggerNm		���C�x���g���̂Őݒ�A��j�����A�m�F
	 * 3)�A�N�Z�X�ڍ�	accessDetail
	 * 
	 * @throws ParseException 
	 */
	public void writeAccessLog(AccessLogBean bean) throws ParseException{
		
		String endTimeStr = DateUtil.getNowTime();
		bean.setEndTime(endTimeStr);																	//�I������
		bean.setTime(DateUtil.getTimeRange(bean.getStartTime(), endTimeStr, DateUtil.TIME_FORMAT));		//��������
		
		//�y�[�WID�ɂ��A�y�[�W���̐ݒ�
		String pageId = bean.getPageId();
		if(StringUtil.isNotBlank(pageId)){
			// �z�[���̏ꍇ�AHOME�Őݒ�
			if ("0".equals(pageId)) {
				bean.setPageNm("HOME");
			} else if (StringUtil.isEmpty(bean.getPageNm())) {
				String pageName = "";
				
				if("treeTemplate".equals(bean.getOriginType())) {
					Map<String, Object> param = new HashMap<String, Object>();
					param.put("PARA_TEMPLATE_PAGE_ID", pageId);
					pageName = (String) this.sqlSessionTemplate.selectOne("userAccessLog.getAccessLogTemplatePageName", param);
				} else {
					// �����Ɋi�[�����̏ꍇ�A�����Ɏ擾
					if(pageNameDicBean.hasPage(pageId)){
						pageName = pageNameDicBean.getPageName(pageId);
					} else {
						// �����Ɋi�[���Ȃ����̏ꍇ�A�f�[�^�x�[�X�Ɏ擾
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("PARA_PAGE_ID", pageId);
						param.put("PARA_IS_RESERVE", bean.getIsReserve());
						pageName = (String) this.sqlSessionTemplate.selectOne("userAccessLog.getAccessLogPageName", param);
						// �@�\�R���e���c�̏ꍇ�A�����Ɋi�[
						if (pageId.matches("^\\p{Alpha}000000000001$")) {
							pageNameDicBean.setPageName(pageId, pageName);
						}
					}
				}
				bean.setPageNm(pageName);
			}
		}
		
		// �J�ڌ��敪(originType)�ɂ��A�����N�^�C�v�֕ϊ�
		String originType = bean.getOriginType();															//�J�ڌ��敪
		if(StringUtil.isNotBlank(originType)
				&& !"null".equals(originType)){
			String linkTypeProperty = FsPropertyUtil.getInstance().getPropertyString("access.log.linktype." + originType);
			if(StringUtil.isNotBlank(linkTypeProperty)){
				bean.setLinkType(linkTypeProperty.split(",")[0]);												//�����N�^�C�v
				bean.setLinkTypeNm(linkTypeProperty.split(",")[1]);												//�����N�^�C�v��
			} else {
				bean.setLinkType("99");																			//�����N�^�C�v
				bean.setLinkTypeNm("���̑�");																	//�����N�^�C�v��
			}
			bean.setTriggerNm("�����N");
			if (StringUtil.isBlank(bean.getResult())){
				bean.setResult("OK");
			}
		} else {
			bean.setLinkType("");
			bean.setLinkTypeNm("");
			bean.setResult("OK");
		}
		
		this.sqlSessionTemplate.insert("userAccessLog.INSERT_ACCESS_LOG", bean);
		
		logger.info("[USER_ID:" + bean.getUserId() + "] - �y�@�\�z" + bean.getAccessDetail());
	}
}
