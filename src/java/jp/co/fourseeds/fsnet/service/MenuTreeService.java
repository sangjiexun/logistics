package jp.co.fourseeds.fsnet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.MenuTreeBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.MenuTreeDao;

import jp.co.common.frame.service.BaseBusinessService;

/**
 * <p>
 * Menu��Tree���쐬����Service
 * </p>
 * 
 * @author NTS
 * @version 1.0.0 : 2015.09.14 �V�K�쐬
 *
 **/
@Component
public class MenuTreeService extends BaseBusinessService{

	@Autowired
	private MenuTreeDao menuTreeDao;

	/**
	 * �V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҂̏ꍇ�AMenu���쐬����
	 */
	public List<MenuTreeBean> getMenuList() {
		return menuTreeDao.getMenuList();
	}
	
	public MenuTreeBean getProfileInfo() {
		return menuTreeDao.getProfileInfo();
	}

	/**
	 * �V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҈ȊO�̏ꍇ�AMenu���쐬����
	 */
	public List<MenuTreeBean> getMenuList(String userId, List<String> groupList, String role) {
		return menuTreeDao.getMenuList(userId, groupList, role);
	}

	/**
	 * ���n���ꂽPageID�ɂ��A���ׂĂ̐ePageID�𒊏o���Ă���List���i�[����
	 */
	public void getAllParentPageList(String pageId, List<String> list) {
		if (!"0".equals(pageId)) {
			String parentPageId = "0";
			parentPageId = menuTreeDao.getAllParentPageList(pageId);
			if(parentPageId == null){
				list = new ArrayList<String>();
			} else {
				list.add(parentPageId);
				getAllParentPageList(parentPageId, list);
			}
		}
	}

	/**
	 * ���O�C�����[�U���Ƃ��ׂĐePageID���X�g�ɂ��A��ʍ�����Tree�����擾����
	 */
	public List getTreeList(LoginUserBean loginUser, List<String> allParentPageList) {

		List list = new ArrayList();
		List<MenuTreeBean> resultList = null;
		for (int i = 0; i < allParentPageList.size(); i++) {
			String parentPageId = (String) allParentPageList.get(i);
			if (!"1".equals(loginUser.getRole())) {
				//�V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҈ȊO�̏ꍇ�ATree���쐬����
				resultList = menuTreeDao.getTreeListByRole(loginUser, parentPageId);
			} 
			else {
				//�V�X�e�����p�敪���u1�v�V�X�e���Ǘ��҂̏ꍇ�ATree���쐬����
				resultList = menuTreeDao.getTreeListNoRole(parentPageId);
			}
			list.add(resultList);
		}
		return list;
	}
	
	/**
	 * ���n���ꂽPageID��targetId�̎q���̏ꍇ�ATrue��߂�
	 */
	public boolean isParent(String pageId,String parentPageId) {
		if(StringUtil.isEmpty(pageId)||pageId.equals("0")){
			return false;
		}
		while(!pageId.equals(parentPageId)){
			pageId = menuTreeDao.getParentPageId(pageId);
			if(StringUtil.isEmpty(pageId)||pageId.equals("0")){
				return false;
			}
		}
		return true;		
	}

	/**
	 * �쐬���̃R���e���c
	 */
	public List<MenuTreeBean> getUnConfirmList(String userId, String role) {
		//�^���R���e���c
		List<MenuTreeBean> resultList = menuTreeDao.getUnConfirmList(userId, role);
		//�\��R���e���c
		List<MenuTreeBean> resultListRsv= menuTreeDao.getUnConfirmListRsv(userId,role);

		MenuTreeBean bean=null;
		if(resultListRsv!=null) {
			for(int i=0;i<resultListRsv.size();i++) {
				bean=(MenuTreeBean) resultListRsv.get(i);
				bean.setNodeName("��" + bean.getNodeName());
				resultListRsv.set(i, bean);
				resultList.add(resultListRsv.get(i));
			}
		}
		return resultList;
	}

	/**
	 * ���J�҂��̃R���e���c
	 */
	public List<MenuTreeBean> getFutureList(String userId, String role) {
		//�^���R���e���c
		List<MenuTreeBean> resultList = menuTreeDao.getFutureList(userId, role);
		//�\��R���e���c
		List<MenuTreeBean> resultListRsv = menuTreeDao.getFutureListRsv(userId,role);

		if(resultListRsv!=null) {
			for(int i=0;i<resultListRsv.size();i++) {
				resultList.add(resultListRsv.get(i));
			}
		}
		return resultList;
	}
	
	/**
	 * �e���v���[�g�R���e���c
	 */
	public List<MenuTreeBean> getTemplateList(String userId, String role) {
		return menuTreeDao.getTemplateList(userId, role);
	}
	
	/**
	 * �����؂�R���e���c
	 */
	public List<MenuTreeBean> getPastList(String userId, String role) {
		return menuTreeDao.getPastList(userId, role);
	}
	
	/**
	 * ���������N�J�ڂ��鎞�A���O�C�����[�U�[����PageID�ɂ��{�������`�F�b�N�p
	 */
	public boolean isPageWithPermission(LoginUserBean loginUser, String pageId) {
		if("1".equals(loginUser.getRole())){
			return true;
		} else {
			return menuTreeDao.getPagesWithPermission(loginUser, pageId).size() > 0;
		}
	}
	
	/**
	 * ���ׂĐePageID���X�g�ƃT�u�c���[�敪�ɂ������擾����
	 * urlFlag��0:���������N�w��@�@1:�R���e���c�쐬�z�u�ʒu�w��
	 */
	public List getSubTreeList(List<String> allParentPageList, String urlFlag) {

		List list = new ArrayList();
		int size = allParentPageList.size();
	
		for (int i = 0; i < size; i++) {
			String pId = (String) allParentPageList.get(i);
			List<MenuTreeBean> resultList = menuTreeDao.getSubTreeList(pId, urlFlag);
			list.add(resultList);
		}
	
		return list;
	}
	
	/**
	 * ���������N�c���[�Ɛe�R���e���c�w��p
	 */
	public List<MenuTreeBean> getFutureListForSubTree() {
		return menuTreeDao.getFutureListForSubTree();
	}
	
	/**
	 * �T�u�c���[�̎q�R���e���c���X�g�擾
	 */
	public List<MenuTreeBean> getSubTreeChildrenList(String pageId, String urlFlag) {
		return menuTreeDao.getSubTreeList(pageId, urlFlag);
	}
}