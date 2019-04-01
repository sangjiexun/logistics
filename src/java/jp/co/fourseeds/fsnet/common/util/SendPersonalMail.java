/**
 * File Name    : SendPersonalMail.java
 * Created Date : 2016/01/21
 * COPYRIGHT(c) : NTS
 * SendPersonalMail.java����R�s�[�V�K����i��ʂƃo�b�`�p�t�@�C���j
 */
package jp.co.fourseeds.fsnet.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import jp.co.common.frame.util.MailUtil;
import jp.co.fourseeds.fsnet.beans.MailBean;
import jp.co.fourseeds.fsnet.beans.personalMail.PersonalMailBean;


public class SendPersonalMail {
	/**
	 * ���[�����M
	 * @param mailInfo,div,user
	 * @throws Exception
	 * @function sendPersonalMail
	 */
	public void sendPersonalMail(PersonalMailBean personalMailBean, String user) throws Exception {
		try {
			MailBean mailInfo = new MailBean();
			// ���[���e�[�v��ݒ�
			mailInfo.setMailType(MailBean.MAIL_TYPE_HTML);
			// ���[�����M�ҁA����ACC��ݒ�
			setAddress(personalMailBean, mailInfo);
			// ���[��������ݒ�
			mailInfo.setSubject(personalMailBean.getMailSubject());
			// ���[�����e��ݒ�
			mailInfo.setContent(personalMailBean.getMailContent());

			// CSV�Y�t�t���O�u1�v�̏ꍇ�A���[���Y�t�t�@�C����ǉ�
			if ("1".equals(personalMailBean.getAttachFlag())) {
				// CSV�o��
				createCSVFile(personalMailBean);
				// �Y�t�t�@�C��
				addMailAttachment(personalMailBean, mailInfo);
			}

			// ���[�����M����
			MailUtil mail = new MailUtil();
			mail.sendMail(mailInfo, user, "");

			// CSV�Y�t�t���O�u1�v�̏ꍇ�A���M�������CSV�t�@�C�����폜����
			if ("1".equals(personalMailBean.getAttachFlag())) {
				// �t�@�C���폜
				deleteFile(personalMailBean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���[�����M�ҁA����ACC
	 * 
	 * @param div
	 * @param personalMailBean
	 * @param mailInfo
	 * @throws Exception
	 */
	private void setAddress(PersonalMailBean bean, MailBean mailInfo) throws Exception {
		// ���M��
		mailInfo.setFrom(bean.getFromMail());
		// ����
		List toMailList = bean.getToMailList();
		if (toMailList != null && toMailList.size() > 0) {
			String toList[] = new String[toMailList.size()];
			for (int i = 0; i < toMailList.size(); i++) {
				toList[i] = (String) toMailList.get(i);
			}
			mailInfo.setTo(toList);
		}

		// CC
		List ccMailList = bean.getCcMailList();
		if (ccMailList != null && ccMailList.size() > 0) {
			String ccList[] = new String[ccMailList.size()];
			for (int i = 0; i < ccMailList.size(); i++) {
				ccList[i] = (String) ccMailList.get(i);
			}
			mailInfo.setCc(ccList);
		}
	}
	
	/**
	 * CSV�t�@�C�����e������
	 * 
	 * @param exportData
	 * @param bean
	 * @return
	 */
	public void createCSVFile(PersonalMailBean bean) {
		File csvFile = null;
		BufferedWriter csvFileOutputStream = null;
		try {
			// �t�@�C���̃p�X�ƃt�@�C�����O���݁ACSV�n��
			if (bean.getFilePath() != null && !"".equals(bean.getFilePath()) && bean.getFileName() != null
					&& !"".equals(bean.getFileName())) {
				csvFile = new File(bean.getFilePath() + bean.getFileName() + ".csv");
				// csvFile.getParentFile().mkdir();
				File parent = csvFile.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}

				// CSV�n��
				csvFile.createNewFile();

				// utf-8
				csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "UTF8"));
				// ���X�g�����ACSV�t�@�C�����e������
				List list = bean.getCsvInfoList();
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						String content = (String) list.get(i);
						csvFileOutputStream.write(content);
						if (i != list.size() - 1) {
							csvFileOutputStream.write("\r\n");
						}
					}
				}
				csvFileOutputStream.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvFileOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �Y�t�t�@�C��
	 * 
	 * @param personalMailBean
	 * @param mailInfo
	 * @throws Exception
	 */
	private void addMailAttachment(PersonalMailBean bean, MailBean mailInfo) throws Exception {
		// �t�@�C���̃p�X�ƃt�@�C�����O���݁ACSV�n��
		if (bean.getFilePath() != null && !"".equals(bean.getFilePath()) && bean.getFileName() != null
				&& !"".equals(bean.getFileName())) {
			String fileName = bean.getFileName() + ".csv";
			String filePath = bean.getFilePath() + fileName;
			// �Y�t�t�@�C��
			FileDataSource fd = new FileDataSource(filePath);
			DataHandler dAttach = new DataHandler(fd);
			mailInfo.setDAttach(dAttach);
			mailInfo.setFileName(fileName);
			// �Y�t�t�@�C���t���O�ݒ�
			mailInfo.setAttachFlag(bean.getAttachFlag());
		}
	}
	
	/**
	 * �t�@�C���폜
	 * 
	 * @param bean
	 * @throws Exception
	 */
	private void deleteFile(PersonalMailBean bean) throws Exception {
		String filePath = bean.getFilePath();
		String fileName = bean.getFileName();
		if (filePath != null && !"".equals(filePath) && fileName != null && !"".equals(fileName)) {
			File csvFile = new File(filePath + fileName + ".csv");
			if (csvFile.exists()) {
				csvFile.delete();
			}
		}
	}
	
	/**
	 * TRIM
	 * 
	 * @param string
	 * @return
	 */
	public static String conversionNullToString(String string) {
		if (string == null || "null".equals(string)) {
			string = "";
		}
		return string.trim();
	}
}
