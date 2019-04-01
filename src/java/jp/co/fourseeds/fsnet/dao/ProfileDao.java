package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import jp.co.fourseeds.fsnet.beans.profile.ProfileBean;

import jp.co.common.frame.beans.LabelValueBean;
import jp.co.common.frame.dao.BaseDao;

/**
 * �v���t�B�[���@�\Dao�����N���X
 * 
 * File Name: ProfileDao.java 
 * Created: 2016/01/07
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/07		    NTS        	       �쐬
 * 1.1		2017/12/11		    NTS        	       �������C��
 *
 **/
@Repository
public class ProfileDao extends BaseDao {

	/**
	 * �v���t�B�[�������擾
	 * @param ProfileBean�@�v���t�B�[��bean�@(�@���[�UID�Ɓ@���[�U�p�X���[�h)
	 * @return�@��������
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfile(ProfileBean profileBean) {
		//�@DB���猟�����ʂ��擾
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", profileBean.getUserId());
		param.put("PARA_PASSWORD", profileBean.getPassword());
		
		return this.sqlSessionTemplate.selectOne("profile.getProfile", param);
	}/**
	 * �v���t�B�[�������擾
	 * ��摜�擾����
	 * @param ProfileBean�@�v���t�B�[��bean�@(�@���[�UID)
	 * @return�@��������
	 */
	@SuppressWarnings("rawtypes")
	public ProfileBean getProfileByUserId(ProfileBean profileBean) {
		//�@DB���猟�����ʂ��擾
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", profileBean.getUserId());
		
		return this.sqlSessionTemplate.selectOne("profile.getProfileByUserId", param);
	}

	/**�v���t�B�[���X�V
	 * @param�@profileBean
	 * */
	public void updateProfile(ProfileBean profileBean){
		this.sqlSessionTemplate.update("profile.updateUserMasterProfile", profileBean);
	}
	/**
	 * ���[�U�C���[�W�������擾
	 * @param profileBean
	 * @return ��������
	 * */
	public int getPersonIMGCount(ProfileBean profileBean){
		// ����������ݒ�
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USERID", profileBean.getUserId());
		
		//�@DB���猟�����ʂ��擾
		return (Integer)this.sqlSessionTemplate.selectOne("profile.getPersonIMGCount", param);
	}
	/**��摜�o�^
	 * @param�@profileBean
	 * */
	public void insertPersonIMG(ProfileBean profileBean){
		this.sqlSessionTemplate.insert("profile.insertPersonIMG", profileBean);
	}
	/**��摜�X�V
	 * @param�@profileBean
	 * */
	public void updateUserImgMasterProfile(ProfileBean profileBean){
		this.sqlSessionTemplate.update("profile.updateUserImgMasterProfile", profileBean);
	}
	/**��摜�X�V imgflgOnly
	 * @param�@profileBean
	 * */
	public void updateUserImgMasterProfileimgflgOnly(ProfileBean profileBean){
		this.sqlSessionTemplate.update("profile.updateUserImgMasterProfileimgflgOnly", profileBean);
	}
}