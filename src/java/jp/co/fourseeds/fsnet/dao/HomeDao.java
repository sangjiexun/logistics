package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;

import jp.co.fourseeds.fsnet.beans.HomeBean;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * �z�[���@�\Dao�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/11/24		    NTS        	       �쐬
 *
 **/
@Repository
public class HomeDao extends BaseDao {

	/**
	 * �ΏێҖ��̃Z���N�g�{�b�N�X�������
	 * @param param ��������
	 * @return List ��������
	 */
	public List<HomeBean> getUserDivisionList(LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// ����������ݒ�
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_USER_ID", loginUser.getUserId());
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("home.getUserDivisionList", param);
	}
	
	/**
	 * �V���������
	 * @param param ��������
	 * @return List ��������
	 */
	public List<HomeBean> getNewsList(HomeBean homeBean, String strOrderBy, LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		// �X�V/�V�K�ݒ�����擾
		String update_date = FsPropertyUtil.getStringProperty("update.date");
		
		// ����������ݒ�
		param.put("PARA_ROLE", loginUser.getRole());
		param.put("PARA_UPDATE_DATE", update_date);
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_USER_DIV", StringUtil.nullToBlank(homeBean.getSearchUserDivision()));	// �Ώێ�
		param.put("PARA_ORDER_BY", strOrderBy);

		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("home.getNewsList", param);
	}
	
	/**
	 * �d�v�Ȃ��m�点�������
	 * @param param ��������
	 * @return List ��������
	 */
	public List<HomeBean> getInformationList(HomeBean homeBean, String strOrderBy, LoginUserBean loginUser) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		// �X�V/�V�K�ݒ�����擾
		String update_date = FsPropertyUtil.getStringProperty("update.date");
		
		// ����������ݒ�
		param.put("PARA_UPDATE_DATE", update_date);
		param.put("PARA_TOP_GROUP_LIST", CommonUtil.getGroupSql(loginUser.getTopGroupList()));
		param.put("PARA_USER_ID", loginUser.getUserId());
		param.put("PARA_NECESSITY_DISPLAY_STARTDATE", loginUser.getNecessityDisplayStartDate());	// �d�v�Ȃ��m�点�\���J�n��
		param.put("PARA_READ_STATUS", homeBean.getReadStatus());
		param.put("PARA_ORDER_BY", strOrderBy);
		
		//�@DB���猟�����ʂ��擾
		return this.sqlSessionTemplate.selectList("home.getInformationList", param);
	}
}