package jp.co.common.mst.pm32;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;
import jp.co.fourseeds.fsnet.beans.test.TestBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.LoginDao;
import jp.co.fourseeds.fsnet.dao.PersonalMailDao;
import jp.co.fourseeds.fsnet.logic.LoginLogic;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.DateUtil;
import jp.co.common.frame.util.FreemarkerUtil;
import jp.co.common.frame.util.MailUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

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
public class CCMPm32MstService extends BaseBusinessService{

	@Autowired
	private CCMPm32MstLogic CCMPm32MstLogic;
	

	public List<CCMPm32MstModel> getPm32List() throws Exception{
		return CCMPm32MstLogic.getPm32List();
	}

}