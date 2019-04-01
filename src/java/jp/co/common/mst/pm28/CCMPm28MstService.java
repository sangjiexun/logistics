package jp.co.common.mst.pm28;

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
public class CCMPm28MstService extends BaseBusinessService{

	@Autowired
	private CCMPm28MstLogic CCMPm28MstLogic;
	

	public List<CCMPm28MstModel> getPm28List(String kyoutuuCd) throws Exception{
		return CCMPm28MstLogic.getPm28List(kyoutuuCd);
	}

}