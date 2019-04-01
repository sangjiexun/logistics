package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.common.frame.service.BaseBusinessService;

import jp.co.fourseeds.fsnet.beans.WebPageReplaceBean;
import jp.co.fourseeds.fsnet.dao.WebPageReplaceDao;
/**
 * Web�S����ID�����ւ����
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1		2017/09/11			NTS			�������Ή�
 * 
 **/

@Component
public class WebPageReplaceService extends BaseBusinessService {
	
	@Autowired
	private WebPageReplaceDao webPageReplaceDao;
	
	/**
	 * �Ј��R���e���c�֘A�����擾
	 * @param webPageReplaceBean 
	 * @param strOrderBy
	 * @return ��������
	 */
	public List<WebPageReplaceBean> getPageList(WebPageReplaceBean webPageReplaceBean, String strOrderBy) {
		return webPageReplaceDao.getPageList(webPageReplaceBean, strOrderBy);
	}
	
	/**
	 * �Ј��R���e���c�֘A�����X�V
	 * @param webPageReplaceBean 
	 * @param strOrderBy
	 * @return ��������
	 */
	public void updateReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		webPageReplaceDao.deleteReplaceSearchUserName(webPageReplaceBean);
		webPageReplaceDao.updateReplaceSearchUserName(webPageReplaceBean,userId);
	}
	

	/**
	 * �Ј��R���e���c�֘A����ǉ�
	 * @param webPageReplaceBean 
	 * @param strOrderBy
	 * @return ��������
	 */
	public void addReplaceSearchUserName(WebPageReplaceBean webPageReplaceBean, String userId) {
		webPageReplaceDao.deleteReplaceSearchUserName(webPageReplaceBean);
		webPageReplaceDao.addReplaceSearchUserName(webPageReplaceBean,userId);
	}
}
