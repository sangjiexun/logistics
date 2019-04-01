package jp.co.fourseeds.fsnet.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.common.frame.service.BaseBusinessService;
import jp.co.fourseeds.fsnet.beans.accessLog.AccessLogBean;
import jp.co.fourseeds.fsnet.beans.page.PageAttachmentBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.AccessLogUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageDownloadDao;

@Component
public class PageDownloadService extends BaseBusinessService{
	
	@Autowired
	private PageDownloadDao pageDownloadDao;
	
	//�A�N�Z�X���O�o�͗p�c�[���N���X
	@Autowired
	public AccessLogUtil accessLogUtil;
	
	private Map<String,String> pageNameMap = null;
	
	public List<PageAttachmentBean> getAttachmentList(String pageId, String isReserve) {
		return pageDownloadDao.getAttachmentList(pageId, isReserve);
	}

	/**
	 * ���O�t���O��ݒ肷��
	 * @param pageId
	 *            �y�[�W�h�c
	 */
	public void updateLogFlag(String pageId) {
		pageDownloadDao.updateLogFlag(super.getLoginUserBean().getUserId(), pageId);
	}
	
	/**
	 * �t�@�C���_�E�����[�h
	 * @param pageId
	 *            �y�[�W�h�c
	 * @param outputStream
	 *            �X�g���[��
	 * @param isStatic
	 *            �ÓI�R���e���c�t���O�i�P�F�� �O�F���j
	 * @param downloadAttchmentList
	 *            �_�E�����[�h�t�@�C����
	 * @param isReserve
	 *            �\��t���O�i�P�F�\�� �O�F�\��ȊO�j
	 * @throws Exception 
	 */
	public void downloadContent(String pageId, OutputStream outputStream, boolean isStatic,
			String downloadAttchmentList, String isReserve, AccessLogBean accessLogBean, String pageNm) throws Exception {
		// �_�E�����[�h�t�@�C����Map
		Map<String, String> downloadAttchmentMap = null;
		// ���I�R���e���c�t���O
		if (!isStatic) {
			// �_�E�����[�h�t�@�C����Map������
			downloadAttchmentMap = new HashMap<String, String>();
			// �_�E�����[�h�t�@�C�����z����擾
			StringTokenizer stringTokenizer = new StringTokenizer(downloadAttchmentList, "|");
			// �_�E�����[�h�t�@�C��������ꍇ�B
			while (stringTokenizer.hasMoreElements()) {
				// �_�E�����[�h�t�@�C�����̂��擾
				String downloadAttchment = (String) stringTokenizer.nextElement();
				// �擾�̃t�@�C�����̂̓u�����N�ȊO�ꍇ
				if (!downloadAttchment.equals("")) {
					// �擾�̃t�@�C�����̂̓_�E�����[�h�t�@�C����Map�ɐݒ肷��B
					downloadAttchmentMap.put(downloadAttchment, "");
				}
			}
		}
		// �t�@�C���ۑ��p�X���擾����B
		List<String> contentPathList = pageDownloadDao.getContentAbsolutePath(pageId, isStatic, isReserve);
		// �t�@�C���ۑ�Map���擾�B
		pageNameMap = pageDownloadDao.getPageNameMap();
		// 
		ZipOutputStream zipOutputStream = null;
		// �A�N�Z�X���O�ڍ�
		StringBuffer message = new StringBuffer();
		message.append(pageNm + "�y�A�N�V�����z�Y�t�t�@�C���_�E�����[�h([");
		try {
			zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
			// �_�E�����[�h�p�X���擾����
			for (String path : contentPathList) {
				// �t�@�C����ݒ�
				File fileObject = new File(path);
				// ���I�R���e���c�t���O���t�@�C���ꍇ
				if (!isStatic && fileObject.isFile()) {
					// �t�@�C�����̂̓_�E�����[�h�t�@�C����Map�ɂ���܂���ꍇ
					if (downloadAttchmentMap.get(fileObject.getName()) == null) {
						continue;
					}
				}
				// �_�E�����[�h�t�@�C�����̂�ݒ肷��B
				String zipEntryName = (String) pageNameMap.get(fileObject.getName());
				// �t�@�C���_�E�����[�h���O���폜
				pageDownloadDao.deleteFileToZip(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
				// �t�@�C���_�E�����[�h���O���o�^
				pageDownloadDao.insertDownFlag(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
				// �_�E�����[�h�t�@�C�����̂͐ݒ肵�Ȃ������ꍇ�B
				if (zipEntryName == null || zipEntryName.equals("")) {
					zipEntryName = fileObject.getName();
				}
				// �t�@�C�������k����B
				addFileToZip(fileObject, zipOutputStream, zipEntryName);
				message.append(zipEntryName + ",");
			}
			String messageStr = message.toString();
			if (messageStr.lastIndexOf(",") > 0) {
				messageStr = messageStr.substring(0, messageStr.lastIndexOf(","));
			}
			// �A�N�Z�X�ڍׂ�ݒ肷��B
			accessLogBean.setAccessDetail(messageStr + "])");

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (zipOutputStream != null) {
				try {
					zipOutputStream.close();
				} catch (IOException e) {
				}
			}
		}

	}
	
	/**
	 * �t�@�C���_�E�����[�h
	 * @param pageId
	 *            �y�[�W�h�c
	 * @param outputStream
	 *            �X�g���[��
	 * @param isStatic
	 *            �ÓI�R���e���c�t���O�i�O�F�� �P�F���j
	 * @param downloadAttchmentList
	 *            �_�E�����[�h�t�@�C����
	 * @param response
	 *            ���X�|���X
	 * @param isReserve
	 *            �\��t���O�i�P�F�\�� �O�F�\��ȊO�j
	 * @throws Exception 
	 */
	public void downloadContent(String pageId, OutputStream outputStream, boolean isStatic,
			String downloadAttchmentList, HttpServletResponse response, String isReserve, AccessLogBean accessLogBean,
			String pageNm)
					throws Exception {

		// �t�@�C�������擾����B
		String zipEntryName = null;
		// �t�@�C���p�X���X�g
		List<String> contentPathList = pageDownloadDao.getContentAbsolutePath(pageId, isStatic, isReserve);
		// �y�[�W���̂l�����擾
		pageNameMap = pageDownloadDao.getPageNameMap();
		// �t�@�C���p�X�擾
		String filePath = contentPathList.get(0);
		// �ÓI�R���e���c(��t�@�C�������邾��)�_�E�����[�h��
		if (isStatic) {
			// �t�@�C���p�X�擾���t�@�C�����擾����B
			File fileObject = new File(filePath);
			// 
			String key = new String();
			// �t�@�C���ꍇ
			if (fileObject.isFile()) {
				key = fileObject.getName();
			}
			// �t�@�C�������擾����B
			zipEntryName = pageNameMap.get(key);
			// �t�@�C���_�E�����[�h���O���폜
			pageDownloadDao.deleteFileToZip(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
		} else {
			// �t�@�C���p�X���[�h��
			String filePasswordName = downloadAttchmentList.substring(0, downloadAttchmentList.indexOf("|"));
			// �t�@�C�����擾
			zipEntryName = pageNameMap.get(filePasswordName);
			// �t�@�C���_�E�����[�h���O���폜
			pageDownloadDao.deleteFileToZip(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
			// �t�@�C���_�E�����[�h���O���o�^
			pageDownloadDao.insertDownFlag(super.getLoginUserBean().getUserId(), pageId, zipEntryName);
			for (int i = 0; i < contentPathList.size(); i++) {
				String temp = contentPathList.get(i);
				if (temp.indexOf(filePasswordName) > 0) {
					filePath = temp;
					break;
				}
			}
		}
		// �R�[�h���`����ݒ肷��B
		zipEntryName = URLEncoder.encode(zipEntryName,"UTF8");
		// �_�E�����[�h��
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + zipEntryName);
		response.setHeader("Cache-Control", "");
		response.setHeader("Pragma", "");
		if (!isStatic) {
			// �A�N�Z�X���O�ڍ�
			StringBuffer message = new StringBuffer();
			message.append(pageNm + "�y�A�N�V�����z�Y�t�t�@�C���_�E�����[�h([");
			message.append(zipEntryName + "])");
			String messageStr = message.toString();
			// �A�N�Z�X�ڍׂ�ݒ肷��B
			accessLogBean.setAccessDetail(messageStr);
			accessLogUtil.writeAccessLog(accessLogBean);
		}
		try {
			File file;
			// �p�X���t�@�C���擾
			file = new File(filePath);
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[ConstantContainer.BUFFER_SIZE];
			int len = 0;
			while ((len = br.read(buf)) > 0)
				outputStream.write(buf, 0, len);
			br.close();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * @param fileObject
	 *            ���k�t�@�C��
	 * @param zipOutputStream
	 *            ���k�X�g���[��
	 * @param zipEntryName
	 *            ���k�t�@�C����
	 */
	private void addFileToZip(File fileObject, ZipOutputStream zipOutputStream,
			String zipEntryName) throws Exception {
		
		// �p�����[�^�̓t�H���_�ꍇ
		if (fileObject.isDirectory()) {
			// �t�@�C�����X�g���擾
			File files[] = fileObject.listFiles();
			for (int i = 0; i < files.length; i++) {
				// �t�@�C�������擾
				String subZipEntryName = (String) pageNameMap.get(files[i].getName());
				if (subZipEntryName == null || subZipEntryName.equals("")) {
					// �t�@�C����ݒ肷��B
					subZipEntryName = StringUtil.nullToBlank((files[i].getName()));
				}
				// ���k�t�@�C��
				addFileToZip(files[i], zipOutputStream, zipEntryName + "\\" + subZipEntryName);
			}

		// �p�����[�^�̓t�@�C���ꍇ
		} else {
			byte dataBuffer[] = new byte[ConstantContainer.ZIP_BUFFER_SIZE];
			BufferedInputStream bufferedInputStream = null;
			try {
				bufferedInputStream = new BufferedInputStream(new FileInputStream(fileObject), ConstantContainer.ZIP_BUFFER_SIZE);
				// ���k�t�@�C��
				zipOutputStream.putNextEntry(new ZipEntry(zipEntryName));
				int count;
				while ((count = bufferedInputStream.read(dataBuffer, 0, ConstantContainer.ZIP_BUFFER_SIZE)) != -1) {
					zipOutputStream.write(dataBuffer, 0, count);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				if (bufferedInputStream != null) {
					bufferedInputStream.close();
				}
			}
		}
	}
}
