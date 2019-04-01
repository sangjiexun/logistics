package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.LoginImageFormBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * ���O�C����ʉ摜�ؑ֋@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/12/01		    NTS        	       �쐬
 *
 **/
@Repository
public class LoginImageDao extends BaseDao {
	
	/**
	 * �摜ID���擾
	 * 
	 * @return String ��������
	 */
	public String getLoginImageId() {
		return this.sqlSessionTemplate.selectOne("loginImage.getLoginImageId");
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏������
	 * 
	 * @param param ��������
	 * @return List ��������
	 */
	public List<LoginImageFormBean> getLoginImageList(LoginImageFormBean loginImageFormBean, String strOrderBy) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// �f�ڊ��� From
		String startDate= loginImageFormBean.getSearchStartDate();
		// �f�ڊ��� To
		String endDate= loginImageFormBean.getSearchEndDate();
		
		// ����������ݒ�
		// �f�ڊ��� From�w�肵�Ȃ��ꍇ�A�ŏ��̌f�ڊ��� From��ݒ�
		if (StringUtil.isEmpty(startDate)) {
			param.put("PARA_START_DATE", "19000101");
		// �f�ڊ��� From�w��̏ꍇ�A��ʂ� �f�ڊ��� From��ݒ�
		} else {
			param.put("PARA_START_DATE", startDate);
		}
		// �f�ڊ��� To�w�肵�Ȃ��ꍇ�A�ő�̌f�ڊ��� To��ݒ�
		if (StringUtil.isEmpty(endDate)) {
			param.put("PARA_END_DATE", "29981231");
		// �f�ڊ��� To�w��̏ꍇ�A��ʂ� �f�ڊ��� To��ݒ�
		} else {
			param.put("PARA_END_DATE", endDate);
		}
		param.put("PARA_DISPLAY_POSITION", loginImageFormBean.getSearchDisplayPosition());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("loginImage.getLoginImageList", param);
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd�������擾
	 * 
	 * @return int ��������
	 */
	public int checkPeriodRepeat(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.checkPeriodRepeat", loginImageFormBean);
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd�������擾
	 * 
	 * @return int ��������
	 */
	public int checkPeriodRepeatMain(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.checkPeriodRepeatMain", loginImageFormBean);
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd�������擾
	 * 
	 * @return int ��������
	 */
	public int isBeforeInfoExist(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.isBeforeInfoExist", loginImageFormBean);
	}
	
	/**
	 * �o�^�σf�[�^�Ƃ̊��ԏd�������擾
	 * 
	 * @return int ��������
	 */
	public int checkPeriodRepeatCopy(LoginImageFormBean loginImageFormBean) {
		return (Integer)this.sqlSessionTemplate.selectOne("loginImage.checkPeriodRepeatCopy", loginImageFormBean);
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏���ǉ�
	 * 
	 * @param loginImageFormBean
	 */
	public void insertLoginImgMaster(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.insert("loginImage.insertLoginImgMaster", loginImageFormBean);
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏���ǉ�
	 * 
	 * @param loginImageFormBean
	 */
	public void insertLoginImgMasterMain(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.insert("loginImage.insertLoginImgMasterMain", loginImageFormBean);
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����폜
	 * 
	 * @param loginImageBean
	 */
	public void updateLoginImgMasterInvalid(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.delete("loginImage.updateLoginImgMasterInvalid", loginImageFormBean);
	}
	
	/**
	 * �X�V��ʂ̃��O�C����ʉ摜�ؑ֏������
	 * 
	 * @param loginImageFormBean ��������
	 * @return List ��������
	 */
	public LoginImageFormBean getLoginImageDetailInfo(LoginImageFormBean loginImageFormBean) {
		
		return this.sqlSessionTemplate.selectOne("loginImage.getLoginImageDetailInfo", loginImageFormBean);
	}
	
	/**
	 * ���C���X�V��ʂ̃��O�C����ʉ摜�ؑ֏������
	 * 
	 * @param loginImageFormBean ��������
	 * @return List ��������
	 */
	public LoginImageFormBean getLoginImageMainInfo(LoginImageFormBean loginImageFormBean) {
		
		return this.sqlSessionTemplate.selectOne("loginImage.getLoginImageMainInfo", loginImageFormBean);
	}
	
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����X�V
	 * 
	 * @param loginImageFormBean
	 */
	public void updateLoginImgMaster(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.update("loginImage.updateLoginImgMaster", loginImageFormBean);
	}
	
	/**
	 * ���O�C����ʉ摜�ؑ֏����X�V
	 * 
	 * @param loginImageFormBean
	 */
	public void updateLoginImgMasterMain(LoginImageFormBean loginImageFormBean) {
		
		this.sqlSessionTemplate.update("loginImage.updateLoginImgMasterMain", loginImageFormBean);
	}
}