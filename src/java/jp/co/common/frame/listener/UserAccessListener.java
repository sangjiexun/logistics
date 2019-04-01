package jp.co.common.frame.listener;

import java.text.ParseException;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.common.frame.util.DateUtil;

import jp.co.fourseeds.fsnet.beans.accessLog.AccessLogBean;
import jp.co.fourseeds.fsnet.common.util.AccessLogUtil;

public class UserAccessListener implements HttpSessionBindingListener {

	/** Log4j */
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private AccessLogUtil accessLogUtil;
	
	public AccessLogBean accessLogBean;
	
	@Override
	public void valueBound(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public UserAccessListener(AccessLogUtil accessLogUtil, AccessLogBean accessLogBean) {
		this.accessLogUtil = accessLogUtil;
		this.accessLogBean = accessLogBean;
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent be) {
		// ���O�A�E�g�̏ꍇ�A���O�A�E�g�敪�ݒ�A����Ȃ�
		if (accessLogBean != null && "logout".equals(accessLogBean.getLogoutFlg())) {
			
		} else {
			// �A�N�Z�X�@�\�̃��O��� start
			String yymmdd = DateUtil.getNowDate();
			accessLogBean.setYymmdd(yymmdd);															//�N����
			accessLogBean.setDayofweek(DateUtil.getDayOfWeek(yymmdd, "yyyyMMdd"));						//�j��
			accessLogBean.setStartTime(DateUtil.getNowTime());											//�J�n����
			accessLogBean.setPageId("");
			accessLogBean.setPageNm("�Z�b�V�����^�C���A�E�g");
			accessLogBean.setAccessDetail("�Z�b�V�����^�C���A�E�g���������܂����B");
			logger.error("�Z�b�V�����^�C���A�E�g���������܂����B");
			try {
				accessLogUtil.writeAccessLog(accessLogBean);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
}
