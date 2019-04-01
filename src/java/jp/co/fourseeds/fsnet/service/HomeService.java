package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.HomeBean;
import jp.co.fourseeds.fsnet.dao.HomeDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * �z�[���@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/11/24		    NTS        	       �쐬
 *
 **/
@Component
public class HomeService extends BaseBusinessService{

	@Autowired
	private HomeDao homeDao;
	
	/**
	 * �ΏێҖ��̃Z���N�g�{�b�N�X�̎擾
	 * @return List
	 */
	public List<HomeBean> getUserDivisionList() {
		return homeDao.getUserDivisionList(getLoginUserBean());
	}
	
	/**
	 * �V���������
	 * @param param
	 * @return List
	 */
	public List<HomeBean> getNewsList(HomeBean homeBean, String strOrderBy) {
		return homeDao.getNewsList(homeBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * �d�v�Ȃ��m�点�������
	 * @param param
	 * @return List
	 */
	public List<HomeBean> getInformationList(HomeBean homeBean, String strOrderBy) {
		return homeDao.getInformationList(homeBean, strOrderBy, getLoginUserBean());
	}
}