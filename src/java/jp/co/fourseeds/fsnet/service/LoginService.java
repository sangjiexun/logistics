package jp.co.fourseeds.fsnet.service;

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
public class LoginService extends BaseBusinessService{

	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private PersonalMailDao personalMailDao;

	@Autowired
	private LoginLogic loginLogic;
	
	/**
	 * �o�^���[�U�[��ID�ƃp�X���[�h�ɂ���āA���[�U�[�̏������
	 * @param userId 
	 *             ���[�U�[ID
	 * @param password 
	 *             �p�X���[�h
	 * @return loginUser 
	 *             ���O�C�����[�U�[�̏��
	 * @throws Exception 
	 */
	public LoginUserBean updateLoginUserInfo(String userId, String password) throws Exception{
		String sendMailFlag = "0";
		LoginUserBean loginUser = loginDao.getLoginUserInfo(userId, password, sendMailFlag);
//		��������
		loginLogic.upA(password);
		System.out.print("success!");
		System.out.println("error");
		loginLogic.upB(password);
		return loginUser;
	}
	
	public LoginUserBean getLoginInfo(String userId) {
		String sendMailFlag = "1";
		LoginUserBean loginUser = loginDao.getLoginUserInfo(userId, null, sendMailFlag);
		return loginUser;
	}
	
	/**
	 * ��ʂ̃��C���摜�擾
	 * @return
	 * @throws Exception
	 */
	public String getLoginImageMainUrl() throws Exception{
		// �����_�ŗL���ȃf�[�^�͎擾����
		LoginImageFormBean loginImageBean = loginDao.getLoginImageMainUrl();
		if (loginImageBean == null) {
			return "";
		}
		// �摜�t�@�C���p�X�X���擾
		String filePath = FsPropertyUtil.getStringProperty("loginImage.url.path") + ConstantContainer.MAIN_IMG_PATH + loginImageBean.getLoginImageId();
		// �L���摜���X�g
		List<String> imgList = new ArrayList<String>();
		for (int i = 1; i <= 10; i++) {
			// �摜i�t�@�C�������擾
			String imgFileName = (String) getMethodValue(loginImageBean, "img" + i + "FileName");
			// �摜i�\���t���O���u�P�v�A�摜i�t�@�C�������݂̏ꍇ
			if (!StringUtil.isBlank(imgFileName)) {
				// �摜�t�@�C���̊g���q���擾
				String suffix = imgFileName.substring(imgFileName.lastIndexOf("."));
				// �L���摜�t�@�C���p�X�X���擾
				String imgUrl = filePath + "/" + loginImageBean.getLoginImageId() + i + suffix;
				imgList.add(imgUrl);
			}
		}
		if (imgList.size() == 0) {
			return "";
		}
		// �摜�̌f�ڊJ�n�����擾
		String baseDate = loginImageBean.getStartDate();
		// �V�X�e�����t���擾
		String nowDate = DateUtil.getNowDate("yyyy/MM/dd");
		// ���t���Ԃ��擾
		int baseCount = DateUtil.dateDiff(baseDate, nowDate, "yyyy/MM/dd");
		// �����_�ŕ\���摜���擾
		int listAt = baseCount % imgList.size();
		return imgList.get(listAt);
	}
	
	private Object getMethodValue(LoginImageFormBean loginImageBean, String methodKey) throws Exception {
		Class<? extends LoginImageFormBean> clazz = loginImageBean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(methodKey, clazz);
		Method getMethod = pd.getReadMethod();
		return getMethod.invoke(loginImageBean);
	}

	/**
	 * �g�b�v�O���[�v���ƃT�u���[�U���̐ݒ�
	 * @param loginUser 
	 *           ���O�C�����[�U�[���
	 * @return   �Ȃ�
	 * */
	public void setUserInfoList(LoginUserBean loginUser) {
		// �������[�U�O���[�v���X�g
		List<String> userGroupList = null;

		userGroupList = loginDao.getTopGroupList(loginUser);
		
		// ���[�U�����O���[�v��ݒ肷��B
		loginUser.setTopGroupList(userGroupList);
	}

	/**
	 * ���[���A�h���X�֑��M
	 * @param userId
	 *         ���[�U�[ID
	 * @param mailFlag
	 *         ���[���t���O�iC:�p�X���[�h�X�V�@S:�p�X���[�h�đ��@P:�v���t�@�C���p�X���[�h�ύX�j
	 * @param user
	 *         
	 * @return 
	 *         ���M����(Y:���M�ρ@M:���[���A�h���[�X�Ȃ��@U�F�p�X���[�h���Ȃ�)
	 * */
	public String sendPassword(LoginUserBean userResult, String mailFlag, HttpServletRequest request) throws Exception {
		// �p�X���[�h�擾�ł��Ȃ��ꍇ
		if (userResult == null || StringUtil.isEmpty(userResult.getPassword())) {
			// �p�X���[�h���݂��܂���
			return "U";
		}
		String userId = userResult.getUserId();
		// ���[���A�h���X�����擾
		String toMail = userResult.getMail();
		// ���[���A�h���[�X�擾�ł��Ȃ��ꍇ
		if (toMail == null || toMail.isEmpty()) {
			// ���[�����݂��܂���
			return "M";
		}
		// �i�����j��
		String firstName = userResult.getKanjiSei();
		// �i�����j��
		String lastName = userResult.getKanjiMei();
		// ���[�U�[���̂�ݒ肷��B
		String user = userResult.getKanjiSei() + userResult.getKanjiMei() + "(" + userId + ")";
		// �]���惁�[���A�h���X���擾����
		String trmailAddress = userResult.getTrmailAddress();
		// ���[�����e�ݒ�
		MailBean mailInfo = new MailBean();
		// ��Ճ��[�����M
		MailUtil mail = new MailUtil();
		mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
		mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.user.address"));
		// ����ݒ�
		mailInfo.setTo(new String[] { toMail });
		// �]���惁�[���A�h���X��ݒ肷��B
		mailInfo.setCc(StringUtil.isEmpty(trmailAddress) ? null : new String[] { trmailAddress });
		// �@�\����
		String functionNm = "";
		// ���[���p�����[�^
		Map<String, Object> rootMap = new HashMap<String, Object>();
		// �p�X���[�h�đ�
		if(mailFlag.equals("S")){
			// �@�\���̂�ݒ肷��B
			functionNm =new String(FsPropertyUtil.getStringProperty("passwordsend.context"));
			// ���[���^�C�g��
			mailInfo.setSubject(FsPropertyUtil.getStringProperty("mail.subject.send"));
			
			String accessTime = String.valueOf(new GregorianCalendar().getTimeInMillis());
			// �Í��L�[
			String accessKey;
			boolean flag;
			do {
				// �Í��L�[���擾
				accessKey = loginDao.getAccessKey();
				// �Í��L�[�t���O�itrue:�Í��L�[����  false:�Í��L�[�s���݁j
				flag = loginDao.searchAccessKey(accessKey);
				
				if (!flag) {
					// �Í��L�s���݂̏ꍇ�A�p�X���[�h�Ĕ��s����f�[�^��ݒ肷��
					setPwCtrlInfo(userId, accessKey, accessTime);
				}
			} while (flag);
			// ���[��URL
			String mailUrl = "http://" + request.getServerName() + request.getContextPath() + "/login_loginMail.do?accessKey=" + accessKey;
			
			int lineMinute = FsPropertyUtil.getIntProperty("password.expired.minutes");
			rootMap.put("lineMinute", lineMinute);
			rootMap.put("linkURL", mailUrl);
		// �p�X���[�h�ύX
		} else {
			// �@�\���̂�ݒ肷��B
			functionNm = FsPropertyUtil.getStringProperty("passwordupdate.context");
			// ���[���^�C�g��
			mailInfo.setSubject(FsPropertyUtil.getStringProperty("mail.subject.change"));
		}
		// ���[���t���O�iC:�p�X���[�h�X�V�@S:�p�X���[�h�đ��j
		rootMap.put("mailFlag", mailFlag);
		// ���[�U�[��
		rootMap.put("userName", firstName + " " + lastName);
		// TEL
		rootMap.put("tel", FsPropertyUtil.getStringProperty("login.tel"));
		// ���[��TO
		rootMap.put("mailto", FsPropertyUtil.getStringProperty("login.mailto"));
		// ���[���t���O���A���[���e���v���[�g���w�肷��B
		mailInfo.setContent(new FreemarkerUtil().getTemplateStr(rootMap,"mail_send_password.ftl"));
		mail.sendMail(mailInfo,user,functionNm);
		// ���M�ς�
		return "Y";
	}

	/**
	 * �p�X���[�h�ύX
	 * @param userId
	 *           ���[�U�[ID
	 * @param newPassword
	 *           �V�p�[�X���[�h
	 * @return
	 *           �Ȃ�
	 * */
	public void updatePassword(String userId, String newPassword) {
		loginDao.updatePassword(userId, newPassword);
	}

	
	/**
	 * �f�o�C�X�ʋ@�\�ʐ�����擾
	 */
	@SuppressWarnings("rawtypes")
	public List getDeviceControlList(String userAgent) {
		return loginDao.getDeviceControlList(userAgent);
	}

	/** �쐬���R���e���c���݃t���O */
	public void setConfirmFlag(LoginUserBean loginUser) {

		String flag = loginDao.getConfirmFlag(loginUser.getUserId());

		loginUser.setConfirmFlag(flag);
	}
	
	/** ���J�҂��R���e���c���݃t���O */
	public void setFetureFlag(LoginUserBean loginUser) {

		String flag = loginDao.getFetureFlag(loginUser.getUserId());

		loginUser.setFetureFlag(flag);
	}
	
	/** �e���v���[�g�R���e���c���݃t���O */
	public void setTemplateFlag(LoginUserBean loginUser) {

		String flag = loginDao.getTemplateFlag(loginUser.getUserId(), loginUser.getRole());

		loginUser.setTemplateFlag(flag);
	}
	
	/** �����؂�R���e���c���݃t���O */
	public void setPastFlag(LoginUserBean loginUser) {

		String pastflag = loginDao.getPastFlag(loginUser.getUserId());

		loginUser.setPastFlag(pastflag);
	}
	
	/**
	 * <p>
	 * ��ʂ̉摜�擾
	 * </p>
	 * 
	 * @param displayPosition 
	 *         �o�i�[
	 * @return LoginImageFormBean
	 *         �摜���
	 * */
	public LoginImageFormBean getLoginImage(String displayPosition) {
		return loginDao.getLoginImage(displayPosition);
	}
	
	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���Ƀf�[�^�o�^
	 */
	public void setPwCtrlInfo(String userId, String accessKey, String accessTime) {
		loginDao.insertPwCtrlInfo(userId, accessKey, accessTime);
	}
	
	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���̃f�[�^���폜
	 */
	public void deletePwCtrlInfo(String userId) {
		loginDao.deletePwCtrlInfo(userId);
	}

	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���̃A�N�Z�X���Ԃ��擾
	 */
	public LoginUserBean getAccessTimeInfo(String accessKey) {
		return loginDao.getAccessTimeInfo(accessKey);
	}
	
	/**
	 * ���[���A�h���X�̃`�F�b�N�A���[���A�h���X�Ȃ��ʒm���M��������B
	 * @param loginUser 
	 *           ���O�C�����[�U�[���
	 * @return
	 *           true:���[���A�h���X���o�^
	 *           false:���[���A�h���X���o�^����Ă��Ȃ�
	 * */
	public Boolean checkMail(LoginUserBean userResult, HttpServletRequest request) throws Exception {
		
		// ���[���A�h���X�����擾
		String vaildMail = userResult.getMail();
		// ���[���A�h���[�X�擾�ł��Ȃ�
		if (vaildMail == null || vaildMail.isEmpty() || ("").equals(vaildMail)) {
			// ���[���ʒm�s�v�t���O:�ʒm�̏ꍇ
			if (("0").equals(userResult.getMailNoticeFlag())) {
				String userId = userResult.getUserId();
				// �i�����j��
				String firstName = userResult.getKanjiSei();
				// �i�����j��
				String lastName = userResult.getKanjiMei();
				// ���[�U�[���̂�ݒ肷��B
				String user = userResult.getKanjiSei() + userResult.getKanjiMei() + "(" + userId + ")";
				// �@�\����
				String functionNm = "";
				// ���[�����e�ݒ�
				MailBean mailInfo = new MailBean();
				// ��Ճ��[�����M
				MailUtil mail = new MailUtil();
				mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
				mailInfo.setFrom(FsPropertyUtil.getStringProperty("mail.user.address"));
				// ��M���[�UID
				String[] toUserId;
				if (ConstantContainer.USER_ORIGINAL.equals(userResult.getOriginalFlag())) {
					toUserId = FsPropertyUtil.getStringProperty("notice.mail.address.to.honbu").split(",");
				} else {
					toUserId = FsPropertyUtil.getStringProperty("notice.mail.address.to.helpdesk").split(",");
				}
				// ���惆�[�U�[�A�h���X
				String[] toMail = null;
				// ���惆�[�U�[����
				String[] toName = null;
				// ���惆�[�U�[ID���A���M���[���Ƒ��M���[�U�[���̂�ݒ肷��
				// ���惆�[�U�[ID������ꍇ
				if (toUserId != null && toUserId.length > 0) {
					toMail = new String[toUserId.length];
					toName = new String[toUserId.length];
					for (int i = 0; i < toUserId.length; i ++) {
						// ���[�U�̃��[���ƃ��[�U���O���擾
						PersonalMailBean userInfoBean = personalMailDao.getMailToUserInfo(toUserId[i]);
						// ���M���[��
						toMail[i] = userInfoBean.getuMail();
						// ���M���[�U�[����
						toName[i] = userInfoBean.getuName();
					}
				}
				// ����
				StringBuffer toUserNameBuff = new StringBuffer();
				if (toName != null && toName.length > 0) {
					for (int i = 0; i < toName.length; i++) {
						String toUserName = (String) toName[i];
						toUserNameBuff.append(toUserName);
						// ����̖��̒��u�A�v���Ԋu
						if (i != toName.length-1) {
							toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("notice.mail.address.content.head1")));
							toUserNameBuff.append("�A");
						} else {
							toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("notice.mail.address.content.head1")));
						}
					}
				} else {
					// ����s�݂̏ꍇ�́A�h������s�݁��h�Ƃ���
					toUserNameBuff.append(new String(FsPropertyUtil.getStringProperty("notice.mail.address.no.mailto.username")));
				}
				//����ݒ�
				mailInfo.setTo(toMail);
				// ���[���p�����[�^
				Map<String, Object> rootMap = new HashMap<String, Object>();
				// ���[�U�[��
				rootMap.put("userName", firstName + " " + lastName);
				// ���[��TO���[�U�[��
				rootMap.put("mailToUserName",toUserNameBuff );
				// ���[���t���O���A���[���e���v���[�g���w�肷��B
				mailInfo.setContent(new FreemarkerUtil().getTemplateStr(rootMap,"mail_send_notice_no_mail.ftl"));
				mail.sendMail(mailInfo,user,functionNm);
			}
			return false;
		}
		
		return true;
	}
	
	/**
	 * �X�܈ϑ������擾����
	 * 
	 * @param loginUser 
	 *         ���O�C�����[�U
	 * @return consign
	 *         �X�܈ϑ�
	 *         
	 */
	public int getConsign(LoginUserBean loginUser) {
		// �X�܈ϑ����
		int consign = loginDao.getConsign(loginUser);
		
		return consign;
	}

	/**
	 * �X�܈ϑ��I�[�i�[�����擾����
	 * 
	 *  * @param loginUser 
	 *         ���O�C�����[�U
	 * @return ownerId
	 *         �X�܈ϑ��I�[�i�[�R�[�h
	 *         
	 */
	public String getConsignOwner(LoginUserBean loginUser,String flag) {
		// �X�܈ϑ��I�[�i�[
		String consignOwner = null;
		
		List<String> consign = loginDao.getConsignOwner(loginUser,flag);
		// �X�܈ϑ��I�[�i�[����
		if (consign != null && consign.size() != 0) {
			consignOwner = consign.get(0);
		}
		
		return consignOwner;
	}
	
	/**
	 * �X�܎�������擾����
	 * 
	 * @param loginUser 
	 *         ���O�C�����[�U
	 * @return accession
	 *         �X�܎��
	 *         
	 */
	public int getAccession(LoginUserBean loginUser,String flag) {
		// �X�܎�����
		int accession = loginDao.getAccession(loginUser,flag);
		
		return accession;
	}
	
	/**
	 * ITR���p�Ҋ�ƃR�[�h�����擾����
	 * 
	 * @param loginUser 
	 *         ���O�C�����[�U
	 * @return ITRId
	 *         ITR�R�[�h
	 *
	 */
	public String getITRUserId(LoginUserBean loginUser) {
		return loginDao.getITRUserId(loginUser);
	}
	

	/**
	 * ���[���A�h���X�ύX
	 * @param mailAddress
	 *           ���[���A�h���X
	 * @param loginUser 
	 *         ���O�C�����[�U
	 * @return
	 *           �Ȃ�
	 * */
	public String updateMailAddress(String mailAddress,String userId) {
		return loginDao.updateMailAddress(mailAddress,userId);
	}

	public List<TestBean> getTestData(String shopcd) {
		return loginDao.getTestData(shopcd);
	}

	public int insertTestData(TestBean testBean) {
		return loginDao.insertTestData(testBean);
	}

	public int updateTestData(TestBean testBean) {
		return loginDao.updateTestData(testBean);
	}

	public int deleteTestData(TestBean testBean) {
		return loginDao.deleteTestData(testBean);
	}

	public List<LoginUserBean> getListUser() throws Exception{
		return loginLogic.getListUser();
	}

	public List<TestBean> getTestDataJqGrid() {
		return loginDao.getTestDataJqGrid();
	}

}