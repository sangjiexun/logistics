package jp.co.fourseeds.fsnet.service;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jp.co.fourseeds.fsnet.beans.page.PageRateItemBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateDetailFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchFormBean;
import jp.co.fourseeds.fsnet.beans.pageRate.PageRateSearchResultBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.PageRateDao;
import jp.co.common.frame.service.BaseBusinessService;

/**
 * �R���e���c�]���󋵋@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/02/03		    NTS        	       �쐬
 * 1.1		2017/10/12			NTS			      �C���g��minaosi
 *
 **/
@Component
public class PageRateService extends BaseBusinessService{

	@Autowired
	private PageRateDao pageRateDao;
	
	/**
	 * �R���e���c�]�����
	 * @param HyoukaContentsForm
	 * @return
	 * @throws ServiceException
	 * @throws DataBaseException
	 */
	public List<PageRateSearchResultBean> searchResults(PageRateSearchFormBean formBean, String orderBy) {
		// �R���e���c�����擾
		List<PageRateSearchResultBean> resultsList = pageRateDao.getContentResultsList(formBean, getLoginUserBean(), orderBy);
		// �擾�̃��X�g������ꍇ
		if (resultsList != null) {
			// �擾������ݒ肷��B
			formBean.setResultCount(resultsList.size());
			//��ʕ\�����X�g
			for (PageRateSearchResultBean bean: resultsList) {
				// ������
				int index = 0;
				// ���׍��ڂ��擾
				List<PageRateDetailBean> hyoukaList = pageRateDao.getPageRateaResultList(bean.getPageId());
				for (PageRateItemBean item:hyoukaList) {
					index++;
					// ����SEQ
					bean.setSequence(item.getSequence());
					switch(index) {
					case 1 :
						// ���ڂP��
						bean.setEvaluationcount1(item.getEvaluationCount());
						// ���ڂP
						bean.setEvaluationName1(item.getEvaluationName());
						break;
					case 2 :
						// ���ڂQ��
						bean.setEvaluationcount2(item.getEvaluationCount());
						// ���ڂQ
						bean.setEvaluationName2(item.getEvaluationName());
						break;
					case 3 :
						// ���ڂR��
						bean.setEvaluationcount3(item.getEvaluationCount());
						// ���ڂR
						bean.setEvaluationName3(item.getEvaluationName());
						break;
					case 4 :
						// ���ڂS��
						bean.setEvaluationcount4(item.getEvaluationCount());
						// ���ڂS
						bean.setEvaluationName4(item.getEvaluationName());
						break;
					case 5 :
						// ���ڂT��
						bean.setEvaluationcount5(item.getEvaluationCount());
						// ���ڂT
						bean.setEvaluationName5(item.getEvaluationName());
						break;
					case 6 :
						// ���ڂU��
						bean.setEvaluationcount6(item.getEvaluationCount());
						// ���ڂU
						bean.setEvaluationName6(item.getEvaluationName());
						break;
					}
				}
			}
		}
		return resultsList;
	}
	
	/**
	 * �R���e���c��ʁA�R���e���c�]�����A�R�����g���
	 * @param PageRateDetailFormBean
	 *                ���׃t�H�[��
	 * @param eventType
	 *                �C�x���g��ޏ��
	 * @return
	 *                ��������
	 */
	public List<PageRateDetailBean> searchEvaluationDetailCondition(PageRateDetailFormBean formBean,String eventType, String orderBy) {
		// �R�����g��񃊃X�g
		List<PageRateDetailBean> commentList = new ArrayList<PageRateDetailBean>();
		// �]���󋵏ڍ׉�ʃR���e���c��񃊃X�g
		formBean.setDetailSearchResultsList(pageRateDao.getEvaluationDetailList(formBean.getPageId(), getLoginUserBean()));
		// �]�����X�g
		formBean.setEvaluationContentsList(pageRateDao.getPageRateaResultList(formBean.getPageId()));
		// �]���ڍד��v���X�g(�]���ڍ�CSV�o�͗p)
		formBean.setEvaluationCountList(pageRateDao.getEvaluationCountList(formBean.getPageId()));
		// �^�C�g���\�����ڂ��擾
		setFormData(formBean);
		// �R�����g���X�g
		if (!"init".equals(eventType)) {
			// ���[�U�[���X�g
			List<String> list = new ArrayList<String>();
			List<String> list3 = new ArrayList<String>();
			// �ʑI���̏ꍇ
			if ("0".equals(formBean.getEvaluationRadioFlag())) {
				// �]������--�i�uAnd�����v�`�F�b�N����j
				if ("1".equals(formBean.getPluralEvaluationFlag())) {
					// �]�����ځi�]���P���@�]���Q���A�A�A�j
					String hyoukaFlag[] = StringUtil.isEmpty(formBean.getEvaluationFlag()) ? null
							: formBean.getEvaluationFlag().split(",");
					String sequence[] = StringUtil.isEmpty(formBean.getEvaluationSequence()) ? null
							: formBean.getEvaluationSequence().split(",");
					if (hyoukaFlag != null && hyoukaFlag.length > 0) {
						for (int i = 0; i < hyoukaFlag.length; i ++) {
							if ("1".equals(hyoukaFlag[i])) {
								list.add(sequence[i]);
							}
						}
						// ���o�����i�]���j�]������
						List<PageRateDetailBean> evaluationUserIdList = pageRateDao.getEvaluationUserIdList(formBean.getPageId(), list.size());
						//��ʌ��������ɖ��������[�U
						List<String> list1 = new ArrayList<String>();
						//��ʌ��������ɖ������Ȃ����[�U
						List<String> list2 = new ArrayList<String>();
						//�P�ƂQ�̃}�[�W
						for (int i = 0; i < evaluationUserIdList.size(); i ++) {
							PageRateDetailBean page = (PageRateDetailBean)evaluationUserIdList.get(i);
							String userId = page.getUserId();
							String seq = page.getSequence();
							if (list.contains(seq)) {
								list1.add(userId);
							} else {
								list2.add(userId);
							}
						}
						
						for (int i = 0; i < list1.size(); i++) {
							String user_id = (String) list1.get(i);
							if (list2.contains(user_id) || list3.contains(user_id)) {
								continue;
							} else {
								list3.add(user_id);
							}
						}
					}
					
				// �]������--�i�uAnd�����v�`�F�b�N���Ȃ��j
				} else {
					// �]������
					String hyoukaFlag[] = StringUtil.isEmpty(formBean.getEvaluationFlag()) ? null
							: formBean.getEvaluationFlag().split(",");
					String sequence[] = StringUtil.isEmpty(formBean.getEvaluationSequence()) ? null
							: formBean.getEvaluationSequence().split(",");
					String str = "";
					if (hyoukaFlag != null && hyoukaFlag.length > 0) {
						for (int i = 0; i < hyoukaFlag.length; i ++) {
							if ("1".equals(hyoukaFlag[i])) {
								str += " A.SEQUENCE='" + sequence[i] + "' OR ";
								list.add(sequence[i]);
							}
						}
						if (list.size() > 0) {
							if (str.lastIndexOf("OR") > -1) {
								str = str.substring(0, str.lastIndexOf("OR"));
							}
							List<String> hyoukaUserIdList = pageRateDao.getPluralNotCheckHyoukaUserIdList(formBean.getPageId(), str);
							for (int i = 0; i < hyoukaUserIdList.size(); i ++) {
								String userId = hyoukaUserIdList.get(i);
								list3.add(userId);
							}
						}
					}
				}
			}
			
			List<String> mergelist = new ArrayList<String>();
			
			// ���o�����i�]�����ځj�`�F�b�N�̏ꍇ
			if (list.size() > 0) {
				mergelist = list3;
			//  ���o�����i�]�����ځj�`�F�b�N�ȊO�̏ꍇ
			} else {
				mergelist = null;
			}
			
			if (mergelist == null) {
				commentList = pageRateDao.getCommentList(formBean, mergelist, orderBy);
			} else if (mergelist.size() > 0) {
				commentList = pageRateDao.getCommentList(formBean, mergelist, orderBy);
			}
		}
		formBean.setCommentList(commentList);
		// CSV�o�̓t���O��ݒ肷��B
		setCsvOutPutFlag(formBean);
		
		return commentList;
	}
	
	/**
	 * CSV�_�E�����[�h
	 * @param response
	 * @param delegator
	 * @return
	 * @throws Exception
	 */
	public void downloadCsv(HttpServletResponse response,List<PageRateSearchResultBean> csvList) throws Exception {
		
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + getCsvFileName());
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		
		OutputStream os = response.getOutputStream();
		OutputStreamWriter w = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(w); 
		try {
			//CSV�w�b�_
			writer.write("�^�C�g��,");
			writer.write("���J�J�n��,");
			writer.write("���J�I����,");
			writer.write("�{���Ґ�,");
			writer.write("����1,");
			writer.write("����1��,");
			writer.write("����2,");
			writer.write("����2��,");
			writer.write("����3,");
			writer.write("����3��,");
			writer.write("����4,");
			writer.write("����4��,");
			writer.write("����5,");
			writer.write("����5��,");
			writer.write("����6,");
			writer.write("����6��,");
			writer.write("�R�����g");
			writer.write("\r\n");																		//���s
			
			PageRateSearchResultBean row = new PageRateSearchResultBean();
			if (csvList != null && csvList.size() != 0) {
				for (int i = 0; i < csvList.size(); i++) {
					row = (PageRateSearchResultBean)csvList.get(i);
					writer.write(row.getTitle() + ",");									//�^�C�g��
					writer.write(StringUtil.nullToBlank(row.getStartDate()) + ",");		//���J�J�n��
					writer.write(StringUtil.nullToBlank(row.getEndDate()) + ",");		//���J�I����
					writer.write(row.getReaderCount() + ",");							//�{���Ґ�
					writer.write((StringUtil.isEmpty(row.getEvaluationName1()) ? "-" : row.getEvaluationName1()) + ","); 	// ����1
					writer.write((StringUtil.isEmpty(row.getEvaluationcount1()) ? "-" : row.getEvaluationcount1()) + ",");	//����1��
					writer.write((StringUtil.isEmpty(row.getEvaluationName2()) ? "-" : row.getEvaluationName2()) + ","); 	// ����2
					writer.write((StringUtil.isEmpty(row.getEvaluationcount2()) ? "-" : row.getEvaluationcount2()) + ",");	//����2��
					writer.write((StringUtil.isEmpty(row.getEvaluationName3()) ? "-" : row.getEvaluationName3()) + ","); 	// ����3
					writer.write((StringUtil.isEmpty(row.getEvaluationcount3()) ? "-" : row.getEvaluationcount3()) + ",");	//����3��
					writer.write((StringUtil.isEmpty(row.getEvaluationName4()) ? "-" : row.getEvaluationName4()) + ","); 	// ����4
					writer.write((StringUtil.isEmpty(row.getEvaluationcount4()) ? "-" : row.getEvaluationcount4()) + ",");	//����4��
					writer.write((StringUtil.isEmpty(row.getEvaluationName5()) ? "-" : row.getEvaluationName5()) + ","); 	// ����5
					writer.write((StringUtil.isEmpty(row.getEvaluationcount5()) ? "-" : row.getEvaluationcount5()) + ",");	//����5��
					writer.write((StringUtil.isEmpty(row.getEvaluationName6()) ? "-" : row.getEvaluationName6()) + ","); 	// ����6
					writer.write((StringUtil.isEmpty(row.getEvaluationcount6()) ? "-" : row.getEvaluationcount6()) + ",");	//����6��
					writer.write(row.getCommentCount());								//�R�����g
					writer.write("\r\n");												//���s
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			writer.close(); 
			w.close(); 
			// �o�͏���
			os.flush();
			os.close();
		}
	}
	
	/**
	 * CSV�_�E�����[�h
	 * @param response
	 * @param delegator
	 * @return
	 * @throws Exception
	 */
	public void downloadCsvDetail( HttpServletResponse response,PageRateDetailFormBean contentForm, String csvKb) throws Exception {
		
		response.setCharacterEncoding("shift_jis");
		response.setContentType("text/plain;charset=shift-jis");
		response.setHeader("Content-Disposition", "attachment; filename=" + getCsvFileName());
		response.setHeader("Cache-Control","");
		response.setHeader("Pragma","");
		
		OutputStream os = response.getOutputStream();
		OutputStreamWriter w = new OutputStreamWriter(os); 
		BufferedWriter writer = new BufferedWriter(w); 
		try {
			// �u�]���ڍ�CSV�o�́v������
			if ("evaluationDetail".equals(csvKb)) {
				// CSV�w�b�_
				writer.write("�]������,");
				
				// �]���Ҏ����\���`�F�b�N����ꍇ�A���[�U�[ID�A�����A����ID�A�������A�o�͂���
				if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
					writer.write("���[�U�[ID,");
					writer.write("����,");
					writer.write("����ID,");
					writer.write("������,");
				}
				writer.write("�]��");
				writer.write("\r\n");		// ���s
	
				// �]���ڍד��v���X�g(�]���ڍ�CSV�o�͗p)
				for(PageRateDetailBean row :contentForm.getEvaluationCountList()){
					writer.write(StringUtil.formatTheDate(row.getCreateDate(), "yyyy/MM/dd HH:mm:ss") + ",");				// �]������
					// �]���Ҏ����\���`�F�b�N����ꍇ�A���[�U�[ID�A�����A����ID�A�������A�o�͂���
					if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
						writer.write(row.getUserId() + ",");				// ���[�U�[ID
						writer.write(row.getUserName() + ",");				// ����
						writer.write(row.getBelong() + ",");				// ����ID
						writer.write(row.getBelongName() + ",");			// ������
					}
					writer.write(row.getEvaluationName());					// �]��
					writer.write("\r\n");									// ���s
				}
			// flag = comment�ꍇ�A�u�R�����gCSV�o�́v������
			} else if ("comment".equals(csvKb)) {
				// CSV�w�b�_
				writer.write("No,");
				writer.write("���e����,");
				
				// �]���Ҏ����\���`�F�b�N����ꍇ�A���[�U�[ID�A�����A����ID�A�������A�o�͂���
				if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
					writer.write("���[�U�[ID,");
					writer.write("����,");
					writer.write("����ID,");
					writer.write("������,");
				}
				writer.write("�R�����g");
				writer.write("\r\n");		// ���s
				// �R�����g��񃊃X�g
				for(PageRateDetailBean row : contentForm.getCommentList()){
					writer.write(row.getCommentOrderBy() + ",");						// No
					writer.write(StringUtil.formatTheDate(row.getCommentDateCsv(), "yyyy/MM/dd HH:mm:ss") + ",");	// ���e����
					// �]���Ҏ����\���`�F�b�N����ꍇ�A���[�U�[ID�A�����A����ID�A�������A�o�͂���
					if ("1".equals(contentForm.getEvaluatorDisplayFlag())) {
						writer.write(row.getUserId() + ",");							// ���[�U�[ID
						writer.write(row.getUserName() + ",");							// ����
						writer.write(row.getBelong() + ",");							// ����ID
						writer.write(row.getBelongName() + ",");						// ������
					}
					writer.write(row.getCommentInfoCsv());								// �R�����g
					writer.write("\r\n");												// ���s
				}
			}
		} catch(Exception e) {
			throw e;
		} finally{
			writer.close(); 
			w.close(); 
			// ���M����
			os.flush();
			os.close();
		}
	} 
	
	/**
	 * CSV���O
	 * 
	 * @return
	 */
	private String getCsvFileName() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return getLoginUserBean().getUserId() + df.format(new Date()) + ".csv";
	}
	
	/**
	 * �^�C�g���\�����ڂ��擾
	 * 
	 */
	private void setFormData(PageRateDetailFormBean formBean) {
		// �]���󋵏ڍ׉�ʃR���e���c��񃊃X�g
		List<PageRateSearchResultBean> list = formBean.getDetailSearchResultsList();
		if (list != null && list.size() > 0) {
			PageRateSearchResultBean resultBean = list.get(0);
			// �]���Ҏ����\���t���O
			formBean.setEvaluatorDisplayFlag(resultBean.getEvaluatorDisplayFlag());
			// �^�C�g��
			formBean.setTitle(resultBean.getTitle());
			// �R���e���c�{���Ґ�
			formBean.setReaderCount(resultBean.getReaderCount());
			// �R�����g��
			formBean.setCommentCount(resultBean.getCommentCount());
		}
	}
	
	/**
	 * CSV�o�̓t���O��ݒ肷��B
	 */
	private void setCsvOutPutFlag(PageRateDetailFormBean formBean) {
		// �R�����gCSV�o�̓t���O
		String commentCsvFlag = "0";
		// �]��CSV�o�̓t���O
		String evaluationCsvFlag = "0";
		// �R�����g���X�g������܂��ꍇ�A�o�͉�
		if(formBean.getCommentList() != null && formBean.getCommentList().size() > 0) {
			commentCsvFlag = "1";
		}
		// �]���ڍד��v���X�g�擾�̌���������܂��ꍇ�A�o�͉�
		for(PageRateDetailBean bean :formBean.getEvaluationContentsList()) {
			if(!"0".equals(bean.getEvaluationCount())) {
				evaluationCsvFlag = "1";
				break;
			}
		}
		// �R�����gCSV�o�̓t���O
		formBean.setCommentCsvFlag(commentCsvFlag);
		// �]��CSV�o�̓t���O
		formBean.setEvaluationCsvFlag(evaluationCsvFlag);
	}
}