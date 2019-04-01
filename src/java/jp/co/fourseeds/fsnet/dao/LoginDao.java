package jp.co.fourseeds.fsnet.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.test.TestBean;
import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;

/**
 * ntsApp�V�X�e���̃��O�C��DAO�̎����N���X
 *
 * @author NTS
 * @version 1.0.0 : 2015.09.14 �V�K�쐬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 �������C��
 */
@Repository
public class LoginDao extends BaseDao{

	/** Log4j�̒�` */
	private final Log logger = LogFactory.getLog(getClass());
	/**
	 * �o�^���[�U�[��ID�ƃp�X���[�h�ɂ���āA���[�U�[�̏������
	 * 
	 * @param userId
	 *            ���[�U�[ID
	 * @param password
	 *            �p�X���[�h
	 */
	public LoginUserBean getLoginUserInfo(String userId, String password, String sendMailFlag) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_PASSWORD", password);
		param.put("PARA_SENDMAIL_FLAG", sendMailFlag);

		return this.sqlSessionTemplate.selectOne("login.getLoginUserInfo", param);
	}
	
	/**
	 * ��ʂ̃��C���摜���擾
	 * @return
	 */
	public LoginImageFormBean getLoginImageMainUrl() {
		return this.sqlSessionTemplate.selectOne("login.getLoginImageMainUrl");
	}
	
	/** 
	 * �Y�����[�U�̏������[�U�O���[�v���擾����
	 * 
	 * @param loginUser		���O�C�����[�U���
	 * @return				 �������[�U�O���[�v���X�g
	 */
	public List<String> getTopGroupList(LoginUserBean loginUser) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_BELONG_ID", loginUser.getBelong());
		param.put("PARA_ORIGINAL_ID", loginUser.getOriginalFlag());
		param.put("PARA_USER_DIVISION",loginUser.getUserFlag());

		return this.sqlSessionTemplate.selectList("login.getTopGroupList", param);
	}
	
	/**
	 * �p�X���[�h�X�V
	 * 
	 * @param userId
	 *           ���[�U�[ID
	 * @param newPassword
	 *           �p�X���[�h
	 * @return 
	 *           �Ȃ�
	 * */
	public void updatePassword(String userId, String newPassword) {
		
		// �p�X���[�h�ύX�����擾
		String validDay = FsPropertyUtil.getStringProperty("password.valid.days");
		int days = Integer.parseInt(validDay);
		// �V�X�e�����t���擾����B
		GregorianCalendar today = new GregorianCalendar();
		today.add(GregorianCalendar.DATE, days);
		Date nextDate = today.getTime();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_PASSWORD", newPassword);
		param.put("PARA_NEXTDATE", nextDate);
		// Oracle�X�V
		this.sqlSessionTemplate.update("login.changePassword", param);
	}
	
	/**
	 * �f�o�C�X�ʋ@�\�ʐ�����擾
	 * @param request.getHeader("user-agent")��� 
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public List getDeviceControlList(String userAgent) {
		//request��user-agent���犇�ʓ��̓��e�𒊏o
		Pattern pattern = Pattern.compile("(?<=\\()[^)]*(?=\\))");
		Matcher matcher = pattern.matcher(userAgent);
		String os = "";
		while(matcher.find()){
			os = matcher.group().toUpperCase();
			break;
		}
		
//		logger.info("os========"+os);
		Map<String, Object> param = new HashMap<String, Object>();
		
		//request��user-agent�ɂ��A�N���C�A���g�[����OS�𔻒f����
		if(os.indexOf("ANDROID")>= 0){
			param.put("PARA_REQUEST_INFO", "ANDROID");
		} else if(os.indexOf("WINDOWS") >= 0){
			param.put("PARA_REQUEST_INFO", "WINDOWS");
		} else if(os.indexOf("IPHONE")>= 0 || os.indexOf("LIKE MAC")>= 0){
			param.put("PARA_REQUEST_INFO", "IOS");
		} else {
			// ����A���h���C�h
			String[] androidKeys = FsPropertyUtil.getStringProperty("android.keyword.others").split(",");
			for(int i=0; i< androidKeys.length; i++){
				if(os.indexOf(androidKeys[i])>= 0){
					param.put("PARA_REQUEST_INFO", "ANDROID");
					break;
				}
			}
		}
		
//		logger.info("�ϊ���========"+param.get("PARA_REQUEST_INFO"));
		return this.sqlSessionTemplate.selectList("login.getDeviceControlList", param);
	}
	
	/**
	 * <p>
	 * ��ʂ̉摜�擾
	 * </p>
	 * 
	 * @param displayPosition 
	 *         �o�i�[
	 * @return loginImgMasterBean
	 *         �摜���
	 * */
	public LoginImageFormBean getLoginImage(String displayPosition) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_DISPLAY_POSITION", displayPosition);
		return (LoginImageFormBean)this.sqlSessionTemplate.selectOne("login.getLoginImage", param);
	}
	
	/** �쐬���R���e���c���݃t���O */
	public String getConfirmFlag(String userId) {
		List<String> results = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);

		results = this.sqlSessionTemplate.selectList("login.getConfirmFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}
	
	/** ���J�҂��R���e���c���݃t���O */
	public String getFetureFlag(String userId) {
		List<String> results = null;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_SYSDATE", new Date());

		results = this.sqlSessionTemplate.selectList("login.getFetureFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}
	/** �e���v���[�g�R���e���c���݃t���O */
	public String getTemplateFlag(String userId, String role) {
		List<HashMap<String, String>> results = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_ROLE", role);

		results = this.sqlSessionTemplate.selectList("login.getTemplateFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}

	/** �����؂�R���e���c���݃t���O */
	public String getPastFlag(String userId) {
		List<String> results = null;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", userId);
		param.put("PARA_SYSDATE", new Date());

		results = this.sqlSessionTemplate.selectList("login.getPastFlag", param);
		if(results != null && !results.isEmpty()){
			return "Y";
		} else {
			return "N";
		}
	}


	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���Ƀf�[�^�o�^
	 */
	public void insertPwCtrlInfo(String userId, String accessKey, String accessTime) {
		//�Y�����[�U�̈ȑO�̃A�N�Z�X�f�[�^���폜
		deletePwCtrlInfo(userId);
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", userId);
		param.put("PARA_ACCESS_KEY", accessKey);
		param.put("PARA_ACCESS_TIME", accessTime);
		param.put("PARA_CREATE_BY", userId);
		param.put("PARA_UPDATE_BY", userId);
		//�Y�����[�U�̍���̃A�N�Z�X�f�[�^��V�K�o�^
		this.sqlSessionTemplate.insert("login.setPwCtrlInfo", param);
	}

	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���̃A�N�Z�X���Ԃ��擾
	 */
	public LoginUserBean getAccessTimeInfo(String accessKey) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_ACCESS_KEY", accessKey);
		
		LoginUserBean bean = (LoginUserBean)this.sqlSessionTemplate.selectOne("login.getAccessTimeInfo", param);
		
		return bean;
	}
	
	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���̃f�[�^���폜
	 */
	public void deletePwCtrlInfo(String userId) {
		this.sqlSessionTemplate.update("login.deletePwCtrlInfo", userId);
	}

	/**
	 * �p�X���[�h�Ĕ��s����e�[�u���̈Í��L�[���擾
	 */
	public String getAccessKey() {
		return (String)this.sqlSessionTemplate.selectOne("login.getAccessKey");
	}

	/**
	 * true:�Í��L�[���� false:�Í��L�[�s����
	 */
	public boolean searchAccessKey(String accessKey) {
		Integer result = (Integer)this.sqlSessionTemplate.selectOne("login.searchAccessKey", accessKey);
		return result >= 1;
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_STOREID", loginUser.getStoreId());
		
		return (Integer)this.sqlSessionTemplate.selectOne("login.getConsign", param);
	}
	
	/**
	 * �X�܈ϑ��I�[�i�[�����擾����
	 * 
	 * @param loginUser 
	 *         ���O�C�����[�U
	 * @return ownerId
	 *         �X�܈ϑ��I�[�i�[�R�[�h
	 *
	 */
	public List<String> getConsignOwner(LoginUserBean loginUser,String flag) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("PARA_STOREID", loginUser.getStoreId());
		if ("vaild".equals(flag)) {
			param.put("PARA_VALID", "1");
		} else {
			param.put("PARA_VALID", "2");
		}
		
		return this.sqlSessionTemplate.selectList("login.getConsignOwner", param);
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
		Map<String, String> param = new HashMap<String, String>();
		param.put("PARA_STOREID", loginUser.getStoreId());
		if ("vaild".equals(flag)) {
			param.put("PARA_VALID", "1");
		} else {
			param.put("PARA_VALID", "2");
		}
		
		return (Integer)this.sqlSessionTemplate.selectOne("login.getAccession", param);
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", loginUser.getUserId());
		param.put("PARA_STOREID", loginUser.getStoreId());
		
		return this.sqlSessionTemplate.selectOne("login.getITRUserId", param);
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
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("MAIL_ADDRESS", mailAddress);
		param.put("PARA_USERID", userId);
		return this.sqlSessionTemplate.selectOne("login.updateMailAddress", param);
	}

	public void updateTest(String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("aaa", password);
		this.sqlSessionTemplate.delete("login.updateTest", param);
	}
	public void updateTest2(String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("aaa", password);
		this.sqlSessionTemplate.delete("login.updateTest", param);
	}

	public List<TestBean> getTestData(String shopcd) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_SHOP_CD", shopcd);
		return this.sqlSessionTemplate.selectList("test.getTestData",param);
	}

	public int insertTestData(TestBean testBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_BUMON_CD", testBean.getBumonCd());
		param.put("PARAM_DEPO_CD", testBean.getDepoCd());
		return this.sqlSessionTemplate.insert("test.insertTest", param);	
	}

	public int updateTestData(TestBean testBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_BUMON_CD", testBean.getBumonCd());
		param.put("PARAM_DEPO_CD", testBean.getDepoCd());
		return this.sqlSessionTemplate.insert("test.updateTest", param);	
	}

	public int deleteTestData(TestBean testBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARAM_BUMON_CD", testBean.getBumonCd());
		param.put("PARAM_DEPO_CD", testBean.getDepoCd());
		return this.sqlSessionTemplate.insert("test.deleteTest", param);	
	}
	
	public List<LoginUserBean> getListUser() {
		return this.sqlSessionTemplate.selectList("test.getListUser");
	}

	public List<TestBean> getTestDataJqGrid() {
		return this.sqlSessionTemplate.selectList("test.getTestDataJqGrid");
	}
}
