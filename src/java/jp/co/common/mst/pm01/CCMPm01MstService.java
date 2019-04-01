package jp.co.common.mst.pm01;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.common.frame.service.BaseBusinessService;

/**
 *  ���O�C���@�\�T�[�r�X�����B
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 �V�K�쐬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 �������C��
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CCMPm01MstService extends BaseBusinessService{

	@Autowired
	private CCMPm01MstLogic CCMPm01MstLogic;
	

	public List<CCMPm01MstModel> getDepoList() throws Exception{
		return CCMPm01MstLogic.getDepoList();
	}
	
	public List<CCMPm01MstModel> getEigyoushoCd() throws Exception{
		return CCMPm01MstLogic.getEigyoushoCd();
	}

}