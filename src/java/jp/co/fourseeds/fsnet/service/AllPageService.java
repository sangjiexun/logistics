package jp.co.fourseeds.fsnet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.AllPageBean;
import jp.co.fourseeds.fsnet.dao.AllPageDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * �S�R���e���c�@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/17		    NTS        	       �쐬
 *
 **/
@Component
public class AllPageService extends BaseBusinessService{

	@Autowired
	private AllPageDao allPageDao;
	
	/**
	 * �S�R���e���c�������
	 * @param param
	 * @return List
	 */
	public List<AllPageBean> getAllPageList(String strOrderBy) {
		return allPageDao.getAllPageList(getLoginUserBean(), strOrderBy);
	}
}