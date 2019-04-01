package jp.co.fourseeds.fsnet.service;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.LoginImageDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * ���O�C����ʉ摜�ؑ֋@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/01		    NTS        	       �쐬
 *
 **/
@Component
public class LoginImageService extends BaseBusinessService{
	
	@Autowired
	private LoginImageDao loginImageDao;
	
	/**
	 * �摜ID���擾
	 * 
	 * @return String
	 */
	public String getLoginImageId() {
		return loginImageDao.getLoginImageId();
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����擾
	 * @param param
	 * @return List
	 */
	public List<LoginImageFormBean> getLoginImageList(LoginImageFormBean loginImageFormBean, String strOrderBy) {
		return loginImageDao.getLoginImageList(loginImageFormBean, strOrderBy);
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd���`�F�b�N
	 * 
	 * @return int
	 */
	public boolean checkPeriodRepeat(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.checkPeriodRepeat(loginImageFormBean);
		// �o�^�σf�[�^�Ƃ̊��ԏd���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd���`�F�b�N
	 * 
	 * @return int
	 */
	public boolean checkPeriodRepeatMain(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.checkPeriodRepeatMain(loginImageFormBean);
		// �o�^�σf�[�^�Ƃ̊��ԏd���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd���`�F�b�N
	 * 
	 * @return int
	 */
	public boolean isBeforeInfoExist(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.isBeforeInfoExist(loginImageFormBean);
		// �o�^�σf�[�^�Ƃ̊��ԏd���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd���`�F�b�N
	 * 
	 * @return int
	 */
	public boolean checkPeriodRepeatCopy(LoginImageFormBean loginImageFormBean) {
		
		int count = loginImageDao.checkPeriodRepeatCopy(loginImageFormBean);
		// �o�^�σf�[�^�Ƃ̊��ԏd���f�[�^�𑶍݂̏ꍇ�A���b�Z�[�W�{�b�N�X���o��
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏���ǉ�
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void insertLoginImgMaster(LoginImageFormBean loginImageFormBean, String imgId) throws Exception {
		
		// �摜ID��ݒ�
		loginImageFormBean.setLoginImageId(imgId);
		// �쐬�ҍX�V�҂�ݒ�
		loginImageFormBean.setCreateBy(getLoginUserBean().getUserId());
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		
		// ���O�C����ʉ摜�ؑ֏���ǉ�
		loginImageDao.insertLoginImgMaster(loginImageFormBean);
		
		//�������摜�t�@�C���A�b�v���[�h
		try {
			uploadFile(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏���ǉ�
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void insertLoginImgMasterMain(LoginImageFormBean loginImageFormBean, String imgId) throws Exception {
		
		// �摜ID��ݒ�
		loginImageFormBean.setLoginImageId(imgId);
		// �쐬�ҍX�V�҂�ݒ�
		loginImageFormBean.setCreateBy(getLoginUserBean().getUserId());
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		
		// ���O�C����ʉ摜�ؑ֏���ǉ�
		loginImageDao.insertLoginImgMasterMain(loginImageFormBean);
		
		//�������摜�t�@�C���A�b�v���[�h
		try {
			uploadFileMain(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏���ǉ�
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void copyLoginImgMasterMain(LoginImageFormBean loginImageFormBean, String imgId, String oldImgId) throws Exception {
		
		// �摜ID��ݒ�
		loginImageFormBean.setLoginImageId(imgId);
		// �쐬�ҍX�V�҂�ݒ�
		loginImageFormBean.setCreateBy(getLoginUserBean().getUserId());
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		
		// �摜�t�@�C���p�X�X���擾
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path") + "/" + ConstantContainer.MAIN_IMG_PATH;
		for (int i = 1; i <= 10; i++) {
			// ��ʂ̎Q�Ɖ摜�t�@�C�����擾
			File img = (File) getMethodValue(loginImageFormBean, "img" + i);
			// ��ʂ̉摜�폜�t���O���擾
			String imgDeleteFlag = (String) getMethodValue(loginImageFormBean, "img" + i + "DeleteFlag");
			// ���摜�t�@�C�������擾
			String oldImgFileName = (String) getMethodValue(loginImageFormBean, "oldImg" + i + "FileName");
			// �摜�폜�t���O�@�����@�P�̏ꍇ
			if (!"1".equals(imgDeleteFlag)) {
				// ��ʂ̉摜�s���݊����摜���݂̏ꍇ
				if (StringUtil.isBlank(img) && !StringUtil.isBlank(oldImgFileName)) {
					// ���摜�t�@�C���̊g���q���擾
					String suffix = oldImgFileName.substring(oldImgFileName.lastIndexOf("."));
					// ���摜�t�@�C���̃p�X���擾
					String oldImgPath = filePath + oldImgId + "/" + oldImgId + i + suffix;
					// ���摜�t�@�C�����擾
					File imgNewFile = new File(oldImgPath);
					// �摜�t�@�C����ݒ�
					setMethodValue(loginImageFormBean, "img" + i, imgNewFile);
					// ���摜�t�@�C�������擾
					String imgFileName = (String) getMethodValue(loginImageFormBean, "oldImg" + i + "FileName");
					// �摜�t�@�C������ݒ�
					setMethodValue(loginImageFormBean, "img" + i + "FileName", imgFileName);
				}
			} else {
				// �摜�t�@�C������ݒ�
				setMethodValue(loginImageFormBean, "img" + i + "FileName", "");
			}
		}
		
		// ���O�C����ʉ摜�ؑ֏���ǉ�
		loginImageDao.insertLoginImgMasterMain(loginImageFormBean);
		
		//�������摜�t�@�C���A�b�v���[�h
		try {
			uploadFileMain(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����폜
	 * 
	 * @param loginImageFormBean
	 */
	public String updateLoginImgMasterInvalid(LoginImageFormBean loginImageFormBean) throws Exception {
		
		String strDeleteImgId = "";
		// ��ʂ̃��O�C����ʉ摜�ؑ֏�񃊃X�g���擾
		List<LoginImageFormBean> loginImageList = loginImageFormBean.getLoginImageList();
		
		for (int i = 0; i < loginImageList.size(); i++) {
			// �Y�����O�C����ʉ摜�ؑ֏����擾
			LoginImageFormBean loginImageBean = loginImageList.get(i);
			
			// ��ʂ̃Z���N�g�{�b�N�X��I���̏ꍇ�A�Y�����O�C����ʉ摜�ؑ֏����폜
			if("on".equals(loginImageBean.getSelectFlag())){
				
				// �X�V�҂�ݒ�
				loginImageBean.setUpdateBy(getLoginUserBean().getUserId());
				
				// ���O�C����ʉ摜�ؑւ��폜
				loginImageDao.updateLoginImgMasterInvalid(loginImageBean);
				
				// �摜ID
				String imgId = loginImageBean.getLoginImageId();
				// �폜�̉摜�h�c���X�g��ݒ�
				strDeleteImgId = strDeleteImgId + imgId + "|";
				
				//���������摜�t�@�C���폜
				try {
					deleteFile(loginImageBean, imgId);
				} catch (Exception e) {
					if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
						FileUtil.ServerAccessException();
					} else {
						throw e;
					}
				}
			}
		}
		return strDeleteImgId;
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����폜
	 * 
	 * @param loginImageFormBean
	 */
	public boolean checkValidImg(LoginImageFormBean loginImageFormBean) throws Exception {
		
		// ��ʂ̃��O�C����ʉ摜�ؑ֏�񃊃X�g���擾
		List<LoginImageFormBean> loginImageList = loginImageFormBean.getLoginImageList();
		List<String> loginImageIdList = new ArrayList<String>();
		for (int i = 0; i < loginImageList.size(); i++) {
			// �Y�����O�C����ʉ摜�ؑ֏����擾
			LoginImageFormBean loginImageBean = loginImageList.get(i);
			
			// ��ʂ̃Z���N�g�{�b�N�X��I���̏ꍇ�A�Y�����O�C����ʉ摜�ؑ֏����폜
			if("on".equals(loginImageBean.getSelectFlag()) 
					&& "2".equals(loginImageBean.getDisplayPositionCd())){
				loginImageIdList.add(loginImageBean.getLoginImageId());
			}
		}
		LoginImageFormBean loginImageBean = new LoginImageFormBean();
		loginImageBean.setLoginImageIdList(CommonUtil.getGroupSql(loginImageIdList));
		loginImageBean.setDisplayPositionCd("2");
		if (loginImageIdList.size() > 0 && !isBeforeInfoExist(loginImageBean)) {
			return true;
		}
		return false;
	}
	
	/**
	 * �X�V��ʂ̃��O�C����ʉ摜�ؑ֏������
	 * 
	 * @param loginImageFormBean
	 */
	public LoginImageFormBean getLoginImageDetailInfo(LoginImageFormBean loginImageFormBean) {
		return loginImageDao.getLoginImageDetailInfo(loginImageFormBean);
	}
	
	/**
	 * ���C���X�V��ʂ̃��O�C����ʉ摜�ؑ֏������
	 * 
	 * @param loginImageFormBean
	 */
	public LoginImageFormBean getLoginImageMainInfo(LoginImageFormBean loginImageFormBean) {
		return loginImageDao.getLoginImageMainInfo(loginImageFormBean);
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����X�V
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void updateLoginImgMaster(LoginImageFormBean loginImageFormBean) throws Exception {
		// �X�V�ҏ���ݒ�
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		// ���O�C����ʉ摜�ؑ֏����X�V
		loginImageDao.updateLoginImgMaster(loginImageFormBean);
		
		// ��ʂ̎Q�Ɖ摜�t�@�C�����擾
		File img = loginImageFormBean.getImg();
		// �Y�t�t�@�C���ǉ�(��ʂ̉摜�t�@�C���ύX)�̏ꍇ
		if (!StringUtil.isBlank(img)) {
			try {
				// �摜ID
				String imgId = loginImageFormBean.getLoginImageId();
				
				//���������摜�t�@�C���폜
				deleteFile(loginImageFormBean, imgId);
				
				//�������V�摜�t�@�C���A�b�v���[�h
				uploadFile(loginImageFormBean, imgId);
			} catch (Exception e) {
				if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
					FileUtil.ServerAccessException();
				} else {
					throw e;
				}
			}
		}
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����X�V
	 * 
	 * @param loginImageFormBean
	 * @param loginUser
	 * @return flag
	 * @throws Exception 
	 */
	public void updateLoginImgMasterMain(LoginImageFormBean loginImageFormBean) throws Exception {
		// �X�V�ҏ���ݒ�
		loginImageFormBean.setUpdateBy(getLoginUserBean().getUserId());
		// ���O�C����ʉ摜�ؑ֏����X�V
		loginImageDao.updateLoginImgMasterMain(loginImageFormBean);
		
		try {
			// �摜ID
			String imgId = loginImageFormBean.getLoginImageId();
			
			//���������摜�t�@�C���폜
			deleteFileMain(loginImageFormBean, imgId);
			
			//�������V�摜�t�@�C���A�b�v���[�h
			uploadFileMain(loginImageFormBean, imgId);
		} catch (Exception e) {
			if (!FileUtil.ping(FsPropertyUtil.getStringProperty("file.server"))){
				FileUtil.ServerAccessException();
			} else {
				throw e;
			}
		}
	}

	/**
	 * ���O�C���C���[�W�t�@�C�����A�b�v���[�h
	 * 
	 * @param loginImageFormBean
	 */
	private void uploadFile(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// �摜�t�@�C���p�X�X���擾
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path");

		// ��ʂ̎Q�Ɖ摜�t�@�C�����擾
		File imgNewFile = new File(loginImageBean.getImg().getPath());
		// ��ʂ̉摜�����擾
		String imgFileName = loginImageBean.getImgFileName();
		// ��ʂ̐V�摜�t�@�C���̊g���q���擾
		String newFileSuffix = imgFileName.substring(imgFileName.lastIndexOf("."));
		// �A�b�v���[�h�t�@�C������ݒ�
		String uploadimgFileName = imgId + newFileSuffix;
		// �Y�t�t�@�C�����A�b�v���[�h����
		FileUtil.uploadFile(imgNewFile, uploadimgFileName , filePath);
	}
	
	/**
	 * ���O�C���C���[�W�t�@�C�����A�b�v���[�h
	 * 
	 * @param loginImageFormBean
	 */
	private void uploadFileMain(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// �摜�t�@�C���p�X�X���擾
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path") + "/" + ConstantContainer.MAIN_IMG_PATH + imgId;
		for (int i = 1; i <= 10; i++) {
			// ��ʂ̎Q�Ɖ摜�t�@�C�����擾
			File img = (File) getMethodValue(loginImageBean, "img" + i);
			// ��ʂ̉摜�폜�t���O���擾
			String imgDeleteFlag = (String) getMethodValue(loginImageBean, "img" + i + "DeleteFlag");
			// ��ʂ̉摜�폜�t���O�@�����@�P���摜���݂̏ꍇ�A
			if (!"1".equals(imgDeleteFlag) && !StringUtil.isBlank(img)) {
				// ��ʂ̉摜���擾
				File imgNewFile = new File(img.getPath());
				// ��ʂ̉摜�����擾
				String imgFileName = (String) getMethodValue(loginImageBean, "img" + i + "FileName");
				// ��ʂ̐V�摜�t�@�C���̊g���q���擾
				String newFileSuffix = imgFileName.substring(imgFileName.lastIndexOf("."));
				// �A�b�v���[�h�t�@�C������ݒ�
				String uploadimgFileName = imgId + i + newFileSuffix;
				// �Y�t�t�@�C�����A�b�v���[�h����
				FileUtil.uploadFile(imgNewFile, uploadimgFileName , filePath);
			}
		}
	}
	
	/**
	 * ���O�C���C���[�W�t�@�C�����폜
	 * 
	 * @param loginImageFormBean
	 */
	private void deleteFileMain(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// �摜�t�@�C���p�X�X���擾
		String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path") + "/" + ConstantContainer.MAIN_IMG_PATH + imgId;
		for (int i = 1; i <= 10; i++) {
			// ���摜��
			String oldImgName = (String) getMethodValue(loginImageBean, "oldImg" + i + "FileName");
			// �摜�폜�t���O
			String imgDeleteFlag = (String) getMethodValue(loginImageBean, "img" + i + "DeleteFlag");
			// �摜�폜�t���O���u�P�v�����摜�����݂̏ꍇ�A�摜�폜
			if ("1".equals(imgDeleteFlag) && !StringUtil.isBlank(oldImgName)) {
				// ��ʂ̋��摜�t�@�C���̊g���q���擾
				String oldFileSuffix = oldImgName.substring(oldImgName.lastIndexOf("."));
				// �폜�t�@�C������ݒ�
				String moveImgName = imgId + i + oldFileSuffix;
				// �����O�C����ʉ摜�ؑւ̃t�@�C�����폜
				FileUtil.deleteFile(filePath, moveImgName);
			}
		}
	}
	
	private Object getMethodValue(LoginImageFormBean loginImageBean, String methodKey) throws Exception {
		Class<? extends LoginImageFormBean> clazz = loginImageBean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(methodKey, clazz);
		Method getMethod = pd.getReadMethod();
		return getMethod.invoke(loginImageBean);
	}
	
	private Object setMethodValue(LoginImageFormBean loginImageBean, String methodKey, Object methodValue) throws Exception {
		Class<? extends LoginImageFormBean> clazz = loginImageBean.getClass();
		PropertyDescriptor pd = new PropertyDescriptor(methodKey, clazz);
		Method setMethod = pd.getWriteMethod();
		return setMethod.invoke(loginImageBean, methodValue);
	}

	/**
	 * ���O�C���C���[�W�t�@�C�����폜
	 * 
	 * @param loginImageFormBean
	 */
	private void deleteFile(LoginImageFormBean loginImageBean, String imgId) throws Exception {
		// ���C���摜�̏ꍇ�A
		if ("2".equals(loginImageBean.getDisplayPositionCd())) {
			String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path");
			// �����O�C����ʉ摜�ؑւ̃t�@�C�����폜
			FileUtil.deleteFile(new File(filePath + "/" + ConstantContainer.MAIN_IMG_PATH + "/" + imgId));
		// ���C���摜�ȊO�̏ꍇ�A
		} else {
			// ���摜��
			String oldImgName = loginImageBean.getOldImgFileName();
			// ��ʂ̋��摜�t�@�C���̊g���q���擾
			String oldFileSuffix = oldImgName.substring(oldImgName.lastIndexOf("."));
			// �폜�t�@�C������ݒ�
			String moveImgName = imgId + oldFileSuffix;
			// �摜�t�@�C���p�X�X���擾
			String filePath = FsPropertyUtil.getStringProperty("loginImage.upload.path");
			// �����O�C����ʉ摜�ؑւ̃t�@�C�����폜
			FileUtil.deleteFile(filePath, moveImgName);
		}
	}
}