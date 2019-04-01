package jp.co.fourseeds.fsnet.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;
import jp.co.common.frame.util.prop.FsPropertyUtil;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.beans.page.PageBean;

/**
 * �Ј������@�\Dao�����N���X
 * 
 * File Name: UserDao.java 
 * Created: 2015/09/22
 * Original Author: NTS 
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2015/09/22		    NTS        	       �쐬
 *
 **/
@Repository
public class PageDownloadDao extends BaseDao {
	
	/**�y�[�W���̂l����*/
	private Map<String, String> pageNameMap = null;

	/**
	 * �Ј��������ʂ��擾
	 * @param param�@��������
	 * @param from�@�����J�n
	 * @param length�@�������R�[�h��
	 * @return�@��������
	 */
	public List<PageAttachmentBean> getAttachmentList(String pageId, String IsReserve) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_IS_RESERVE", IsReserve);
		
		return this.sqlSessionTemplate.selectList("pageDownload.getAttachmentList", param);
	}
	
	/**
	 * ���O�X�V
	 * @param currentUserId
	 *           ���O�C�����[�U�[�h�c
	 * @param pageId
	 *           �y�[�W�h�c
	 */
	public void updateLogFlag(String currentUserId, String pageId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_UPDATE_BY", currentUserId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_USER_ID", currentUserId.toString());
		this.sqlSessionTemplate.update("pageDownload.updateLogFlag", param);
	}
	
	/**
	 * �t�@�C���p�X�擾
	 * @param pageId
	 *           �y�[�W�h�c
	 * @param isStatic
	 *           �ÓI�R���e���c�t���O�i�P�F�� �O�F���j
	 * @param isReserve
	 *           �\��t���O�i�P�F�\�� �O�F�\��ȊO�j
	 */
	public List<String> getContentAbsolutePath(String pageId, boolean isStatic, String isReserve) {
		// �������ʔz��
		String[] resultArr = null;
		// �������ʃ��X�g
		List<String[]> resultList = new ArrayList<String[]>();
		// �t�@�C���p�X
		String fileDirectory = "";
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		// �y�[�W�h�c
		param.put("PARA_PAGE_ID", pageId);
		// �\��t���O
		param.put("PARA_IS_RESERVE", isReserve);
		// �ÓI�R���e���c�ꍇ
		if (isStatic) {
			// �t�H���_���擾����B
			fileDirectory = (isReserve == "1" ? FsPropertyUtil.getStringProperty("htmlFile.temp.path")
					: FsPropertyUtil.getStringProperty("htmlFile.path"));
			// �p�X���擾
			List<PageBean> pageList = this.sqlSessionTemplate.selectList("pageDownload.getSaticContentAbsolutePath", param);
			for (PageBean tempBean:pageList) {
				resultArr = new String[2];
				// �t�@�C���ꍇ
				if (tempBean.getFileSuffix().startsWith(".")) {
					resultArr[0] = tempBean.getTitle() + tempBean.getFileSuffix();
					resultArr[1] = tempBean.getPageId() + tempBean.getFileSuffix();
				// �t�H���_�ꍇ
				} else {
					resultArr[0] = tempBean.getTitle();
					resultArr[1] = tempBean.getPageId();
				}
				// �������ʃ��X�g��ǉ�����B
				resultList.add(resultArr);
			}
		// ���I�R���e���c�ꍇ
		} else {
			// �t�H���_���擾����B
			fileDirectory = (isReserve == "1" ? FsPropertyUtil.getStringProperty("server.upload.temp.path")
					: FsPropertyUtil.getStringProperty("server.upload.path"));
			// �p�X���擾
			List<PageAttachmentBean> attachmentList = this.sqlSessionTemplate.selectList("pageDownload.getAttachmentList", param);
			for (PageAttachmentBean tempBean:attachmentList) {
				resultArr = new String[2];
				resultArr[0] = tempBean.getAttachmentName();
				resultArr[1] = tempBean.getAttachmentFileUrl();
				resultArr[1] = resultArr[1].substring(resultArr[1].lastIndexOf("/") + 1);
				// �������ʃ��X�g��ǉ�����B
				resultList.add(resultArr);
			}
		}
		// �y�[�W���̂l����
		pageNameMap = new HashMap<String, String>();
		// �p�X���X�g
		List<String> pathList = new ArrayList<String>();
		// �������X�g���[�v
		for (int i = 0; i < resultList.size(); i++) {
			String[] string = (String[]) resultList.get(i);
			pathList.add(fileDirectory + "\\" + string[1]);
			// KEY�FPageId�@VALUE:Title
			pageNameMap.put(string[1], string[0]);
			if (string[1].indexOf(".") == -1) {
				pageNameMap.put(string[1] + ".html", string[0] + ".html");
			}
		}
		return pathList;
	}
	
	/**
	 * �t�@�C���_�E�����[�h���O���폜
	 * @param currentUserId
	 *           ���O�C�����[�U�[�h�c
	 * @param pageId
	 *           �y�[�W�h�c
	 * @param zipEntryName
	 *           �t�@�C����
	 */
	public void deleteFileToZip(String currentUserId ,String pageId,String zipEntryName) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", currentUserId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_FILE_NAME", zipEntryName);
		this.sqlSessionTemplate.update("pageDownload.deleteFileToZip", param);
	}
	
	/**
	 * �t�@�C���_�E�����[�h���O���o�^
	 * @param currentUserId
	 *           ���O�C�����[�U�[�h�c
	 * @param pageId
	 *           �y�[�W�h�c
	 * @param zipEntryName
	 *           �t�@�C����
	 */
	public void insertDownFlag(String currentUserId ,String pageId,String zipEntryName) {
		// �p�����[�^
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("PARA_USER_ID", currentUserId);
		param.put("PARA_PAGE_ID", pageId);
		param.put("PARA_FILE_NAME", zipEntryName);
		param.put("PARA_CREATE_BY", currentUserId);
		this.sqlSessionTemplate.update("pageDownload.insertDownFlag", param);

	}
	
	/**
	 * �y�[�W���̂l�����擾
	 */
	public Map<String,String> getPageNameMap() {
		return pageNameMap;
	}
}