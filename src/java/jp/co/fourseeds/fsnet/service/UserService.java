package jp.co.fourseeds.fsnet.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.UserBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.UserDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.FileUtil;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 * ���[�U���@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/27		    NTS        	       �쐬
 * 1.1		2017/09/05			NTS			�������C��
 *
 **/
@Component
public class UserService extends BaseBusinessService{
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * ���[�U�����擾
	 * @param userBean
	 * @param strOrderBy
	 * @return ��������
	 */
	public List<UserBean> getUserList(UserBean userBean, String strOrderBy) {
		return userDao.getUserList(userBean, strOrderBy,getLoginUserBean());
	}
	
	/**
	 * ���[�U�ڍ׏����擾
	 * @param userBean
	 * @return ��������
	 */
	public UserBean getUserDetailInfo(UserBean userBean) {
		// ���[�U�ڍ׏����擾
		UserBean resultBean = userDao.getUserDetailInfo(userBean);
		
		return resultBean;
	}
	
	/**
	 * ���[�U�ʐ^�ڍ׏����擾
	 * @param userBean
	 * @return ��������
	 */
	public UserBean getUserImgDetail(UserBean userBean) {
		return userDao.getUserDetailInfo(userBean);
	}
	
	/**
	 * �C���[�W�����擾
	 * @param userBean
	 * @return ��������
	 */
	public byte[] getImgDat(String userId) {
		return userDao.getImgDat(userId);
	}
	
	/**
	 * ���[�U�����X�V
	 * @param userBean
	 * @throws Exception 
	 */
	public void updateUserMaster(UserBean userBean) throws Exception {
		//�������ʐ^�̃t�@�C���A������
		// �ʐ^�̃t�@�C���A�ݒ�̏ꍇ
		if (!StringUtil.isBlank(userBean.getPhotoPath())) {
			// �Ј��ʐ^�A�h���X�ƌl�o�^�摜��ݒ�
			setPhotoInfo(userBean);
			
			// ���[�U�C���[�W���݂��Ȃ��ꍇ
			if (userDao.getImgCount(userBean) == 0) {
				// ���[�U�C���[�W����o�^
				userDao.insertUserImgMaster(userBean, getLoginUserBean());
			} else {
				// ���[�U�C���[�W�����X�V
				userDao.updateUserImgMaster(userBean, getLoginUserBean());
			}
		}
		
		//���������[�U�����X�V
		userDao.updateUserMaster(userBean, getLoginUserBean());
		
	}
	
	/**
	 * ���[�U�O���[�v���׏������̕ύX���[�U�����폜
	 * @param userBean
	 * @throws Exception 
	 */
	public void deleteUserGroup(UserBean userBean) throws Exception {
		userDao.deleteUserGroup(userBean);
	}
	
	/**
	 * ���[�U�O���[�v���擾
	 * @param userBean
	 * @throws Exception 
	 */
	public UserBean saerchUserGroupInfo(UserBean userBean) throws Exception {
		return userDao.saerchUserGroupInfo(userBean);
	}
	
	/**
	 * ���[�U�O���[�v���X�V
	 * @param userBean
	 * @throws Exception 
	 */
	public void insertUserGroupInfo(UserBean userBean) throws Exception {
		userDao.insertUserGroupInfo(userBean, getLoginUserBean());
	}
	
	/**
	 * �Ј��ʐ^�A�h���X�ƌl�o�^�摜��ݒ�
	 * @param userBean
	 * @throws Exception 
	 */
	private void setPhotoInfo(UserBean userBean) throws Exception {
		// �Ј��ʐ^�A�h���X��ݒ�
		String photoUrlPath = FsPropertyUtil.getStringProperty("photo.Url.path");
		String photoAdd = photoUrlPath + "/" + userBean.getPhotoFileName();
		userBean.setPhotoAdd(photoAdd);
		
		// �l�o�^�摜��ݒ�
		File photo = new File(userBean.getPhotoPath());
		userBean.setImgDat(FileUtil.fileToByte(photo));
	}
	
}