package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.ReadHistoryFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.ReadHistoryDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 *  �{�������̊m�F�@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/18		    NTS        	       �쐬
 *
 **/
@Component
public class ReadHistoryService extends BaseBusinessService{

	@Autowired
	private ReadHistoryDao readHistoryDao;
	
	/**
	 * �z�M�Ҋm�F�����擾
	 * @param param
	 * @return List
	 */
	public List<ReadHistoryFormBean> getNecessityReadPageList(ReadHistoryFormBean readHistoryFormBean, String strOrderBy) {
		return readHistoryDao.getNecessityReadPageList(readHistoryFormBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * �{���Ǘ��ΏێҏƉ�����擾
	 * @param param
	 * @return List
	 */
	public Map<String, Object> getNecessityReadUserListByPage(ReadHistoryFormBean readHistoryFormBean, String strOrderBy) {
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		Map<String, String> repeatUser = new HashMap<String, String>();
		List<ReadHistoryFormBean> resultList = new ArrayList<ReadHistoryFormBean>();
		
		// �{���Ǘ��ΏێҏƉ��񃊃X�g
		List<ReadHistoryFormBean> resultMailList = readHistoryDao.getNecessityReadUserListByPage(readHistoryFormBean, strOrderBy);
		
		// ���ǌ���
		int unReadCount = 0;
		
		for(int i = 0;i < resultMailList.size(); i++) {
			ReadHistoryFormBean resultBean = resultMailList.get(i);
			
			if (!repeatUser.containsKey(resultBean.getUserId())) {
				// ���[�U�̃��[���A�h���X���݁A�����ǂ̏ꍇ
				if (!StringUtil.isBlank(resultBean.getMailAddress()) && "����".equals(resultBean.getReadedDate())) {
					unReadCount = unReadCount + 1;
				}
				repeatUser.put(resultBean.getUserId(), "");
				resultList.add(resultBean);
			}
		}
		
		retMap.put("resultList", resultList);
		retMap.put("resultMailList", resultMailList);
		retMap.put("unReadCount", unReadCount);
		
		return retMap;
	}
	
	/**
	 * ���ǃ��[�U�[���[�����M
	 * @param param
	 */
	@SuppressWarnings("rawtypes")
	public void sendMailToNotReaded(ReadHistoryFormBean readHistoryFormBean, List<ReadHistoryFormBean> notReadInfoList) throws Exception {
		
		// �����ҏ����擾
		String userId = getLoginUserBean().getUserId();
		String kanaMei = getLoginUserBean().getKanjiMei();
		String kanaSei = getLoginUserBean().getKanjiSei();
		String user = kanaSei + kanaMei + "("+ userId + ")";
		
		// �����҃��[�����擾
		String fromUser = readHistoryDao.getUserMailAddress(userId);
		String mailFrom = null;
		if (StringUtil.isBlank(fromUser)) {
			mailFrom = FsPropertyUtil.getStringProperty("mail.user.address");
		} else {
			mailFrom = StringUtil.nullToBlank(fromUser);
		}
		
		// ���ǃ��[�U�[���擾
		for (int i = 0; i < notReadInfoList.size(); i++) {
			ReadHistoryFormBean notReadInfo = notReadInfoList.get(i);
			
			// ���M�ғ��̏ꍇ�A���[���𔭑�
			if (!"�s��".equals(notReadInfo.getMailSendFlag())){
				// ���[���ݒ�
				MailBean mailInfo = new MailBean();
				mailInfo.setMailType("text/html");
				// �����Ґݒ�
				mailInfo.setFrom(mailFrom);
				// ���M�Ґݒ�
				String toList[] = new String[1];
				toList[0] = notReadInfo.getMailAddress();
				mailInfo.setTo(toList);
				// �]����ݒ�
				String mailCc = notReadInfo.getTrmailAddress();
				if (!StringUtil.isBlank(mailCc)) {
					String ccList[] = new String[1];
					ccList[0] = mailCc;
					mailInfo.setCc(ccList);
				}
				// ���[���^�C�g���ݒ�
				mailInfo.setSubject(readHistoryFormBean.getMailTitle());
				
				// ���[���p�����[�^
				Map<String, Object> rootMap = new HashMap<String, Object>();
				// ���[�U�[��
				rootMap.put("userName", notReadInfo.getUserName());
				// ���[���{��
				rootMap.put("mailContent",readHistoryFormBean.getMailContent().replaceAll("\n", "<br>"));
				// �^�C�g��
				rootMap.put("title", readHistoryFormBean.getTitle());
				// �Ώۃy�[�WURL
				rootMap.put("linkUrl", readHistoryFormBean.getLinkUrl());
				
				// ���[�����e�ݒ�A���[���e���v���[�g���w�肷��B
				mailInfo.setContent(new FreemarkerUtil().getTemplateStr(rootMap, "mail_page_unread.ftl"));
				
				// ���[�����M
				MailUtil mail = new MailUtil();
				mail.sendMail(mailInfo, user, FsPropertyUtil.getStringProperty("noread.context"));
			}
		}
	}
}