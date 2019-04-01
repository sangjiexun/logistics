package jp.co.fourseeds.fsnet.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.beans.SiteSearchFormBean;
import jp.co.fourseeds.fsnet.beans.SiteSearchResultBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.common.util.CommonUtil;
import jp.co.fourseeds.fsnet.common.util.StringUtil;
import jp.co.fourseeds.fsnet.dao.SiteSearchDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.prop.FsPropertyUtil;

/**
 *  �T�C�g�������@�\�T�[�r�X�����B
 * 
 * @author NTS
 * @version 1.0.0 : 2015.11.14 �V�K�쐬
 */
@Component
public class SiteSearchService extends BaseBusinessService{

	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SiteSearchDao siteSearchDao;

	public void getSearchResults(SiteSearchFormBean searchBean) throws Exception {
		LoginUserBean loginUser = getLoginUserBean();
		//Fess�̌������ʊi�[���X�g
		List<String> resultListByFess = new ArrayList<String>();
		//���O�C�����[�U�̉{���\�̌������ʊi�[���X�g�i��ʑS���\�����܂�)
		List<SiteSearchResultBean> resultListByUser = new ArrayList<SiteSearchResultBean>();
		//�I���N���̌������ʊi�[���X�g
		List<String> resultListByDB = new ArrayList<String>();
		
		//���݂̃V�X�e���������擾����
		long time = System.currentTimeMillis();
		
		String keyWord = StringUtil.nullToBlank(searchBean.getKeyWord()).trim();
		//�����L�[���[�h�͑S�p�ɕϊ�
		keyWord = StringUtil.hankakuKatakanaToZenkakuKatakana(keyWord);
		
		//���������O�C�����[�U�̉{���\�̌�������
		resultListByUser = siteSearchDao.getUserPageList(loginUser, searchBean.getSort());

		//DB�̌����Ώۍ��ځF�P�j�^�C�g�����@�Q�j�R���e���c���e�@�R�j�T�C�g�������L�[�@�S�j�Y�t�t�@�C�����O
		//�uPKG_COMMON.FN_KNACHG()�v���@�F���p������To�S�p�啶��
		List titlekeyWordList = changeOracleKeyWord(keyWord, "PKG_COMMON.FN_KNACHG(Upper(T1.TITLE))", loginUser);
		// �p�����̕��@�uPKG_COMMON.FN_KNACHG(Upper())�v�ϊ��A������̒����𒴂���A�p�uTO_MULTI_BYTE()�v���@�ϊ�
		List contentkeyWordList = changeOracleKeyWord(keyWord, "T1.CONTENT", loginUser);
		List pagekeyWordList = changeOracleKeyWord(keyWord, "PKG_COMMON.FN_KNACHG(Upper(T1.PAGE_KEY))", loginUser);
		List attachmetkeyWordList = changeOracleKeyWord(keyWord, "PKG_COMMON.FN_KNACHG(Upper(T2.ATTACHMENT_NAME))", loginUser);
		// not�L�[���[�h�����ȊO�̕�����
		String titlekeyWord = (String)titlekeyWordList.get(0);
		String contentkeyWord = (String)contentkeyWordList.get(0);
		String pagekeyWord = (String)pagekeyWordList.get(0);
		String attachmetkeyWord = (String)attachmetkeyWordList.get(0);
		// not�L�[���[�h�����̕�����
		String titleNotkeyWord = (String)titlekeyWordList.get(1);
		String contentNotkeyWord = (String)contentkeyWordList.get(1);
		String pagekeyNotWord = (String)pagekeyWordList.get(1);
		String attachmetNotkeyWord = (String)attachmetkeyWordList.get(1);
		String oraclekeyWord = "";
		String attachmetKeyWord1 = "";
		if(titleNotkeyWord != null && !"".equals(titleNotkeyWord)) {
			oraclekeyWord = "((" + titlekeyWord + ") OR (" + contentkeyWord + ") OR (" + pagekeyWord + ") OR (" + attachmetkeyWord
					+ ")) AND ((" + titleNotkeyWord 
					+ ") AND ((" + contentNotkeyWord  + ") OR (T1.CONTENT IS NULL))"
					+ " AND ((" + pagekeyNotWord + ") OR (T1.PAGE_KEY IS NULL))"
					+ " AND ((" + attachmetNotkeyWord + ") OR (T2.ATTACHMENT_NAME IS NULL))" + ")";
			attachmetKeyWord1 = attachmetkeyWord + " AND " + attachmetNotkeyWord;	
		} else {
			oraclekeyWord = "(" + titlekeyWord + ") OR (" + contentkeyWord + ") OR (" + pagekeyWord + ") OR (" + attachmetkeyWord + ")";
			attachmetKeyWord1 = attachmetkeyWord;
		}
		// �I���N�������̃L�[���[�h
		
		try{
			//�������I���N���̌�������
			resultListByDB = siteSearchDao.getOraclePageList(oraclekeyWord, attachmetkeyWord);
		}catch(Exception e){
			e.printStackTrace();
			CommonUtil.writeActionLog(loginUser.getUserId(),"�I���N���ُ탁�b�Z�[�W�F" + oraclekeyWord);
		}

		//������Fess�̌�������
		
		Pattern pattern = Pattern.compile("\\b(and|or|not)\\b|");
		Matcher matcher = pattern.matcher(keyWord);
		
		StringBuffer sb = new StringBuffer(); 
		boolean isMatch = matcher.find();
		while(isMatch) { 
			matcher.appendReplacement(sb, matcher.group(0).toUpperCase());
			isMatch = matcher.find();
		}
		matcher.appendTail(sb);
		
		resultListByFess = (List<String>) searchByFess(sb.toString(),loginUser.getUserId());
		
		//��ʕ\���p���X�g
		List<SiteSearchResultBean> result = new ArrayList<SiteSearchResultBean>();
		HashSet<String> uniqueMatchSet = new HashSet<String>();
		boolean flag = false;
		SiteSearchResultBean beforeBean = null;
		for(int i = 0; i < resultListByUser.size(); i++){
			SiteSearchResultBean currentBean = resultListByUser.get(i);

			String fileName = StringUtil.nullToBlank(currentBean.getAttachmentFileUrl());
			String fileSuffix = "";
			if(fileName!=null && !"".equals(fileName)) {
				try {
					fileSuffix = fileName.substring(fileName.lastIndexOf(".")).trim();
					fileName = fileName.substring(fileName.lastIndexOf("/") + 1,
							fileName.lastIndexOf("."));
				} catch (Exception e) {
				}
				String pageId = fileName.substring(0, 13);
				String index = fileName.substring(13, fileName.length());
				currentBean.setAttachmentFileUrl("/fsnet/pageView_openAttachment.do?formBean.pageId=" + pageId + "&index=" + index +"&suffix=" + fileSuffix);
			}

			if (flag) {
				if (!currentBean.getPageId().equals(beforeBean.getPageId())) {
					if (!uniqueMatchSet.contains(beforeBean.getPageId())) {
						result.add(beforeBean);
						//�y�[�WID�i�[�i�d���R���e���c�O�����f�p)
						uniqueMatchSet.add(beforeBean.getPageId());
					}
					flag = false;
				}
			}
			
			//���I�Y�t�t�@�C���q�b�g�A���邢�́A�Y�t�t�@�C���Ȃ����I�R���e���c�q�b�g(�I���N������)
			if (resultListByDB.contains(currentBean.getMatchKey())) {
				result.add(currentBean);
				//�y�[�WID�i�[�i�d���R���e���c�O�����f�p)
				uniqueMatchSet.add(currentBean.getPageId());
			} else {
				//���I�Y�t�t�@�C���q�b�g�A���邢�́A�Y�t�t�@�C���Ȃ����I�R���e���c�q�b�g���邢�͐ÓI�R���e���c(Fess����)
				if (resultListByFess.contains(currentBean.getMatchKey())) {
					result.add(currentBean);
					//�y�[�WID�i�[�i�d���R���e���c�O�����f�p)
					uniqueMatchSet.add(currentBean.getPageId());
				} 
				else {
					//���I�Y�t�q�b�g(�I���N������)
					if (resultListByDB.contains(currentBean.getPageId() + "|")) {
						if (!uniqueMatchSet.contains(currentBean.getPageId())) {
							currentBean.setAttachmentName("");
							if (i == resultListByUser.size() - 1) {
								result.add(currentBean);
								//�y�[�WID�i�[�i�d���R���e���c�O�����f�p)
								uniqueMatchSet.add(currentBean.getPageId());
							} else {
								flag = true;
								beforeBean = currentBean;
							}
						}
					} else {
						//���I�Y�t�q�b�g(Fess����)
						if (resultListByFess.contains(currentBean.getPageId() + "|")) {
							if (!uniqueMatchSet.contains(currentBean.getPageId())) {
								currentBean.setAttachmentName("");
								if (i == resultListByUser.size() - 1) {
									result.add(currentBean);
									//�y�[�WID�i�[�i�d���R���e���c�O�����f�p)
									uniqueMatchSet.add(currentBean.getPageId());
								} else {
									flag = true;
									beforeBean = currentBean;
								}
							}
						}
					}
				}
			}
		}
		//�����̃y�[�W��
		int searchPageNo = Integer.parseInt(searchBean.getSearchPageNo());
		//�����̌���
		int perPageNo = Integer.parseInt(searchBean.getPerPageNo());
		//���݂̃y�[�W�̊J�n����
		int j = (searchPageNo - 1) * perPageNo;
		if (result.size() < searchPageNo * perPageNo) {
			perPageNo = result.size();
		} else {
			perPageNo = perPageNo * searchPageNo;
		}
		//���݂̃y�[�W�̔z�u��̎擾
		for (int i = j; i < perPageNo; i++) {
			SiteSearchResultBean infobean = result.get(i);
			SiteSearchResultBean getHaitiBean = getStrHaitisaki(infobean, loginUser);
			infobean.setHaitisaki(getHaitiBean.getHaitisaki());
			infobean.setExistFlag(getHaitiBean.getExistFlag());
		}

		//�������ʃ��X�g
		searchBean.setSearchPageList(result);

		//�����b�����擾����
		if (result.size() != 0) {
			double usedTimeDouble = Double.parseDouble(String.valueOf(System
					.currentTimeMillis()
					- time));
			usedTimeDouble /= 1000;
			searchBean.setUsedTime(usedTimeDouble + "");
		}
	}
	
	//�z�u��̒����������ꍇ�Apage_id�傫�Ȏ擾
	private SiteSearchResultBean checkPageIdOrder(SiteSearchResultBean haitiInfoBeanNew, SiteSearchResultBean haitiInfoBeanP) {
		String pageIdnew[] = haitiInfoBeanNew.getStrPageKey().split("|");
		String pageIdP[] = haitiInfoBeanP.getStrPageKey().split("|");
		SiteSearchResultBean rtnHaitiInfoBean = new SiteSearchResultBean();
		boolean breakFlag = false;
		for (int i = 0; i < pageIdP.length; i++) {
			if (pageIdnew[i].compareTo(pageIdP[i]) == 0) {
				continue;
			} else if (pageIdnew[i].compareTo(pageIdP[i]) > 0) {
				rtnHaitiInfoBean = haitiInfoBeanNew;
				breakFlag = true;
				break;
			} else if (pageIdnew[i].compareTo(pageIdP[i]) < 0) {
				rtnHaitiInfoBean = haitiInfoBeanP;
				breakFlag = true;
				break;
			}
		}
		if (!breakFlag) {
			rtnHaitiInfoBean = haitiInfoBeanNew;
		}
		return rtnHaitiInfoBean;
	}
	
	private SiteSearchResultBean getStrHaitisaki(SiteSearchResultBean infobean, LoginUserBean loginUser) {
		SiteSearchResultBean returnBean = new SiteSearchResultBean();
		List<SiteSearchResultBean> listHaitisaki = new ArrayList<SiteSearchResultBean>();
		SiteSearchResultBean locationBean = new SiteSearchResultBean();
		//page_id�̔z�u��擾
		locationBean.setPageId(infobean.getPageId());
		//�z�u��
		locationBean.setHaitisaki("-[" + infobean.getTitle() + "]");
		//�z�u��̒����������ꍇ�A��r��key
		locationBean.setStrPageKey(infobean.getPageId() + "|");
		//�ŒZ�p�X
		locationBean.setCountPass(1);
		locationBean.setPPageId(infobean.getPPageId());
		locationBean.setJituFlag(false);
		listHaitisaki.add(locationBean);
		//�z�u��擾
		getListHaitisaki(listHaitisaki, 0, loginUser);

		//�z�u��
		String haitisaki = "";
		String strKey = "";
		//�z�u�惊�X�g���̋�̃f�[�^���폜���܂�
		for (int i = 0; i < listHaitisaki.size(); i++) {
			SiteSearchResultBean nullHaitiInfoBean = listHaitisaki.get(i);
			if ("".equals(nullHaitiInfoBean.getHaitisaki())) {
				listHaitisaki.remove(i);
				i--;
			}
		}
		
		if (listHaitisaki.size() > 0) {
			int countPass = 0;
			//�ŒZ�p�X�`�F�b�N(countPass�y�A���Ɣ�r�AcountPass�ŏ��l���擾)
			for (int i = 0; i < listHaitisaki.size(); i++) {
				SiteSearchResultBean haitiInfoBeanP = listHaitisaki.get(i);
				if (i == 0) {
					countPass = haitiInfoBeanP.getCountPass();
				} else {
					//��r�ŒZ�p�X
					if (haitiInfoBeanP.getCountPass() < countPass) {
						countPass = haitiInfoBeanP.getCountPass();
					}
				}
			}
			//�z�u��̒����������ꍇ�Apage_id�傫�Ȏ擾
			SiteSearchResultBean haitiInfoBeanNew = new SiteSearchResultBean();
			for (int i = 0; i < listHaitisaki.size(); i++) {
				SiteSearchResultBean haitiInfoBeanP = listHaitisaki.get(i);
				if (countPass == haitiInfoBeanP.getCountPass()) {
					if (haitiInfoBeanNew.getStrPageKey() == null) {
						haitiInfoBeanNew = haitiInfoBeanP;
					} else {
						//pageID�~���`�F�b�N
						haitiInfoBeanNew = checkPageIdOrder(haitiInfoBeanNew, haitiInfoBeanP);
					}
				}
			}
			haitisaki = haitiInfoBeanNew.getHaitisaki();
			strKey = haitiInfoBeanNew.getStrPageKey();
		} else {
			//��L�擾���Ă��Ȃ�
			SiteSearchResultBean jitubean = getHaiTiSaki(infobean.getPageId(), loginUser);
			haitisaki = jitubean.getHaitisaki();
			strKey = jitubean.getStrPageKey();
		}
		
		String pId = FsPropertyUtil.getStringProperty("contents.pageId");
		if(StringUtil.isBlank(strKey) || strKey.indexOf(pId) > -1) {
			returnBean.setExistFlag("1");
		} else {
			returnBean.setExistFlag("0");
		}
		returnBean.setHaitisaki(haitisaki);
		return returnBean;
	}
	
	//�z�u��擾
	private void getListHaitisaki(List<SiteSearchResultBean> listHaitisaki, int count, LoginUserBean loginUser) {
		if (count > 100) {
			listHaitisaki = new ArrayList<SiteSearchResultBean>();
			return;
		}
		List<SiteSearchResultBean> listHaitisakiNew = new ArrayList<SiteSearchResultBean>();
		boolean isLoopFlag = false;
		for (int i = 0; i < listHaitisaki.size(); i++) {
			SiteSearchResultBean locationBean = listHaitisaki.get(i);
			if ("".equals(locationBean.getPageId()) || "0".equals(locationBean.getPageId())) {
				listHaitisakiNew.add(locationBean);
				continue;
			}
			//�ċA�Ăяo���t���O
			isLoopFlag = true;
			
			//�����N�z�u��̎擾
			List<SiteSearchResultBean> linkPageInfoList = new ArrayList<SiteSearchResultBean>();
			if (!locationBean.isJituFlag()) {
				linkPageInfoList = siteSearchDao.getLinkPageInfo(locationBean, loginUser);
			}
			
			//�����N�z�u�摶�݂��Ȃ��A���z�u��̎擾
			if (linkPageInfoList.size() == 0) {
				//�e�R���e���c�́uHOME�v
				if("0".equals(locationBean.getPPageId())){
					SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
					haitiInfoBean1.setPageId("");
					haitiInfoBean1.setHaitisaki("[HOME]" + locationBean.getHaitisaki());
					haitiInfoBean1.setStrPageKey(locationBean.getStrPageKey() + "0|");
					haitiInfoBean1.setCountPass(locationBean.getCountPass());
					listHaitisakiNew.add(haitiInfoBean1);
					continue;
				}
				//���z�u��̎擾
				List<SiteSearchResultBean> jituPageInfoList = siteSearchDao.getJituPageInfo(locationBean, loginUser);
				
				//�z�u��擾���Ȃ��Anull��߂�
				if (jituPageInfoList.size() == 0) {
					//���z�u���null
					SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
					haitiInfoBean1.setPageId("");
					haitiInfoBean1.setHaitisaki("");
					haitiInfoBean1.setStrPageKey("");
					haitiInfoBean1.setCountPass(0);
					
					listHaitisakiNew.add(haitiInfoBean1);
				} else {
					for (int j = 0; j < jituPageInfoList.size(); j++) {
						SiteSearchResultBean haitiInfoBean2 = (SiteSearchResultBean) jituPageInfoList.get(j);
						SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
						haitiInfoBean1.setPageId(haitiInfoBean2.getPageId());
						haitiInfoBean1.setPPageId(haitiInfoBean2.getPPageId());
						haitiInfoBean1.setHaitisaki("-[" + haitiInfoBean2.getTitle() + "]" + locationBean.getHaitisaki());
						haitiInfoBean1.setStrPageKey(locationBean.getStrPageKey() + haitiInfoBean2.getPageId() + "|");
						haitiInfoBean1.setCountPass(locationBean.getCountPass() + 1);
						haitiInfoBean1.setLinkTitle("");
						haitiInfoBean1.setJituFlag(true);
						listHaitisakiNew.add(haitiInfoBean1);
					}
				}
			} else {
				for (int j = 0; j < linkPageInfoList.size(); j++) {
					SiteSearchResultBean haitiInfoBean2 = (SiteSearchResultBean) linkPageInfoList.get(j);
					
					SiteSearchResultBean haitiInfoBean1 = new SiteSearchResultBean();
					haitiInfoBean1.setPageId(haitiInfoBean2.getPageId());
					haitiInfoBean1.setPPageId(haitiInfoBean2.getPPageId());
					String haitisaki = locationBean.getHaitisaki();
					//�����N�z�u��̃^�C�g�������폜����
					if(locationBean.getLinkTitle() != null && !"".equals(locationBean.getLinkTitle())) {
						String linkTitle = locationBean.getLinkTitle();
						haitisaki = haitisaki.substring(haitisaki.indexOf(linkTitle) + linkTitle.length(), haitisaki.length());
					}
					haitiInfoBean1.setLinkTitle("-[" + haitiInfoBean2.getTitle() + "]");
					haitiInfoBean1.setHaitisaki("-[" + haitiInfoBean2.getTitle() + "]" + "-[\"" + haitiInfoBean2.getLinkName() + "\"]" + haitisaki);
					haitiInfoBean1.setStrPageKey(locationBean.getStrPageKey() + haitiInfoBean2.getPageId() + "|");
					haitiInfoBean1.setCountPass(locationBean.getCountPass() + 1);
					
					listHaitisakiNew.add(haitiInfoBean1);
				}
			}
		}
		
		if (isLoopFlag) {
			listHaitisaki.clear();
			for (int i = 0; i < listHaitisakiNew.size(); i++) {
				listHaitisaki.add(listHaitisakiNew.get(i));
			}
			count++;
			getListHaitisaki(listHaitisaki, count, loginUser);
		}
	}

	//	�R���e���c�z�u��A�Ⴆ��[HOME]-[�ē�/�ʒB]-[�R���e���c�z�u��\��]
	public SiteSearchResultBean getHaiTiSaki(String PageId, LoginUserBean loginUser) {
		SiteSearchResultBean jitubean = new SiteSearchResultBean();
		String strHaiTiSaki=new String();
		String strKey=new String();
		Boolean flag=true;
		String pageid=PageId;
		int i = 0;
		while(flag){
			if("0".equals(pageid)){         
				strHaiTiSaki="[HOME]"+strHaiTiSaki;
				strKey = "0|" + strKey;
				flag = false; 
			}else{
				SiteSearchResultBean page = siteSearchDao.getPage(pageid, loginUser);
				if (page == null) {
					strHaiTiSaki = "�Q�Ɛ悪����ł��܂���B";
					strKey = "";
					jitubean.setHaitisaki(strHaiTiSaki);
					jitubean.setStrPageKey(strKey);
					return jitubean;
				}
				String strP_Title="["+page.getTitle()+"]";
				strHaiTiSaki="-"+strP_Title+strHaiTiSaki;
				String strPkey = page.getPageId() + "|";
				strKey = strPkey + strKey;
				pageid=page.getPPageId();
			}
			if (i++ >= ConstantContainer.SITE_SEARCH_MAX) {
				flag = false;
				strHaiTiSaki = "�Q�Ɛ悪����ł��܂���B";
				strKey = "";
				jitubean.setHaitisaki(strHaiTiSaki);
				jitubean.setStrPageKey(strKey);
				return jitubean;
			}
		}
		jitubean.setHaitisaki(strHaiTiSaki);
		jitubean.setStrPageKey(strKey);
		return jitubean;
	}

	// �I���N�������p�L�[���[�h
	private List changeOracleKeyWord(String OracleKeyWord, String str, LoginUserBean loginUser) {
		List<String> keyList = new ArrayList<String>();
		try {
			String oKeyWord = "";
			//�I���N�������p�L�[���[�h���p������To�S�p�啶��
			oKeyWord = siteSearchDao.changeOracleKeyWord(OracleKeyWord);
			
			if (oKeyWord != null) {
				oKeyWord = oKeyWord.replaceAll("��", "\\\\��").replaceAll("�Q", "\\\\�Q");
			}
			
			String key = "";
			String key1[] = null;
			String key2[] = null;
			String key3[] = null;
			String key4[] = null;
			String key5[] = null;
			
			String notKey = "";
			
			oKeyWord = trimString(oKeyWord);
			oKeyWord = removeStar(oKeyWord);
			oKeyWord = trimString(oKeyWord);
			key1 = oKeyWord.split("�@�`�m�c�@");
			for(int i = 0; i < key1.length; i++) {
				key1[i] = trimString(key1[i]);
				key1[i] = removeStar(key1[i]);
				key1[i] = trimString(key1[i]);
				if(key1[i].contains("�@�n�q�@") || key1[i].contains("�@�m�n�s�@")) {
					key2 = key1[i].split("�@�n�q�@");
					key1[i] = "";
					for(int j = 0; j < key2.length; j++) {
						key2[j] = trimString(key2[j]);
						key2[j] = removeStar(key2[j]);
						key2[j] = trimString(key2[j]);
						if(key2[j].contains("�@�m�n�s�@")) {
							key3 = key2[j].split("�@�m�n�s�@");
							key2[j] = "";
							for(int k = 0; k < key3.length; k++) {
								key3[k] = trimString(key3[k]);
								key3[k] = removeStar(key3[k]);
								key3[k] = trimString(key3[k]);
								if(k == 0) {
									key3[k] = str + " like '%" + key3[k] + "%' escape '\\'";
								} else {
									key3[k] = "like '%" + key3[k] + "%' escape '\\'";
								}
								if (k == key3.length - 1) {
									key2[j] = key2[j] + key3[k];
								} else {
									key2[j] = key2[j] + key3[k] + " and " + str + " not ";
								}
								/** �Y�t*/
								if(k == key3.length -1) {
									String tempkey = key2[j];
									key2[j] = tempkey.substring(0, tempkey.indexOf(" and"));
									notKey = notKey + tempkey.substring(tempkey.indexOf(" and"), tempkey.length());
								}
							}
						} else {
							key2[j] = str + " like '%" + key2[j] + "%' escape '\\'";
						}
						if (j == key2.length - 1) {
							key1[i] = key1[i] + key2[j];
						} else {
							key1[i] = key1[i] + key2[j] + " or ";
						}
					}
				} else {
					key1[i] = str + " like '%" + key1[i] + "%' escape '\\'";
				}
				if (i == key1.length - 1) {
					key = key + key1[i];
				} else {
					key = key + key1[i] + " and ";	
				}
			}
			if(key.contains("�@")) {
				key4 = key.split("�@");
				key = "";
				for(int n = 0; n < key4.length; n++) {
					key4[n] = removeStar(key4[n]);
					key4[n] = key4[n].trim();
					if(!"".equals(key4[n])) {
						if(n == 0) {
							key = key + key4[n] + "%' escape '\\'";
						} else if (n == key4.length - 1) {
							key4[n] = " and " + str + " like '%" + key4[n];
							key = key + key4[n];
						} else {
							key4[n] = " and " + str + " like '%" + key4[n] + "%' escape '\\'";
							key = key + key4[n] ;
						}
					}
				}
			}
			
			if(notKey.contains("�@")) {
				key5 = notKey.split("�@");
				notKey = "";
				for(int n = 0; n < key5.length; n++) {
					key5[n] = removeStar(key5[n]);
					key5[n] = key5[n].trim();
					if(!"".equals(key5[n])) {
						if(n == 0) {
							notKey = " " + notKey + key5[n] + "%' escape '\\'";
						} else if (n == key5.length - 1) {
							if(key5[n].indexOf("and") != -1) {
								notKey = notKey + key5[n].substring(key5[n].indexOf("and")-1, key5[n].length());
								key5[n] = " and " + str + " like '%" + key5[n].substring(0,key5[n].indexOf("and"));
							} else {
								key5[n] = " and " + str + " like '%" + key5[n];
							}
							key = key + key5[n]; 
						} else {
							if(key5[n].indexOf("and") != -1) {
								notKey = notKey + key5[n].substring(key5[n].indexOf("and")-1, key5[n].length()) + "%' escape '\\'";
								key5[n] = " and " + str + " like '%" + key5[n].substring(0,key5[n].indexOf("and"));
							} else {
								key5[n] = " and " + str + " like '%" + key5[n] + "%' escape '\\'";
							}
							key = key + key5[n] ;
						}
					}
				}
			}
			
			if(notKey != null && !"".equals(notKey)) {
				notKey = notKey.substring(notKey.indexOf(" and ") + 4, notKey.length());		
			} else {
				notKey = "";
			}
			keyList.add(key);
			keyList.add(notKey);
		} catch (Exception e) {
			e.printStackTrace();
			CommonUtil.writeActionLog(loginUser.getUserId(),"�I���N�������p�L�[���[�h�ُ탁�b�Z�[�W�F" + OracleKeyWord);
			String key =  str + " like '%" + OracleKeyWord + "%' escape '\\'";
			keyList.add(key);
			keyList.add("");
		}
		return keyList;
	}
	
	//�����񗼒[�̃X�y�[�X���폜����
	private String trimString(String str) {
		char trimStr = ' ';
		String str1 = "";
		String str2 = "";
		boolean flag = false;
		boolean flag1 = false;
		//�폜������O�[�̃X�y�[�X
		for (int i = 0; i < str.length(); i++){
			if(!flag) {
				trimStr = str.charAt(i);
				if(trimStr != '�@' && trimStr != ' ') {
					flag = true;
					str1 = str.substring(i);
				}
			}
		}
		//�폜�������[�̃X�y�[�X
		for (int i = 0; i < str1.length(); i++){
			if(!flag1) {
				trimStr = str1.charAt(str1.length() - 1 - i);
				if(trimStr != '�@'&& trimStr != ' ') {
					flag1 = true;
					str2 = str1.substring(0, str1.length() - i);
				}
			}
		}
		return str2;
	}
	
	//�����񗼒[�̃A�X�^���X�N���폜����
	private String removeStar(String str) {
		char removeStr = ' ';
		String str1 = "";
		String str2 = "";
		boolean flag = false;
		boolean flag1 = false;
		//�폜������O�[�̃A�X�^���X�N
		for (int i = 0; i < str.length(); i++){
			if(!flag) {
				removeStr = str.charAt(i);
				if(removeStr != '��' && removeStr != '*') {
					flag = true;
					str1 = str.substring(i);
				}
			}
		}
		//�폜�������[�̃A�X�^���X�N
		for (int i = 0; i < str1.length(); i++){
			if(!flag1) {
				removeStr = str1.charAt(str1.length() - 1 - i);
				if(removeStr != '��' && removeStr != '*') {
					flag1 = true;
					str2 = str1.substring(0, str1.length() - i);
				}
			}
		}
		return str2;
	}
	/**
	 * @param keyWord
	 * @param sort
	 * @return
	 * @function Get the filted search results by Fess.
	 */
	private List<String> searchByFess(String keyWord,String userId) {
		
		List<String> resultsFessList = new ArrayList<String>();
		HttpURLConnection connection = null;
		String result = "",lines;
		String matchKey = "";
		String fileName = "";
		String fileNameNoSuffix = "";
		
		String fessUrl = FsPropertyUtil.getStringProperty("fess.url") + "?query=";
		try{
			URL getUrl = new URL(fessUrl + URLEncoder.encode(keyWord, "UTF-8"));
			
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.setConnectTimeout(30000);
			connection.connect();
		
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
			while ((lines = reader.readLine()) != null) { 
	
	            result = result + lines;
			} 
			if (logger.isDebugEnabled()) {
				logger.debug("Fess�������ʁF\r\n" + result);
			}

			JSONObject dataJson=JSONObject.fromObject(result);
			JSONObject  response=dataJson.getJSONObject("response");
			if (!"0".equals(response.getString("recordCount"))) {
				JSONArray data=response.getJSONArray("result");
				for(int i=0; i<data.size(); i++) {
					JSONObject info=data.getJSONObject(i);
					
					fileName = info.getString("url");
					fileName = fileName.substring(fileName.lastIndexOf("/")+1);
					if(fileName.lastIndexOf(".")>=0){
						fileNameNoSuffix = fileName.substring(0, fileName.lastIndexOf("."));
					} else {
						fileNameNoSuffix = fileName;
					}
					if (logger.isDebugEnabled()) {
						logger.debug("Fess�����t�@�C�����O�F" + fileName);
					}

					if (fileNameNoSuffix.matches("^\\d{13,}$")) {
						if (fileNameNoSuffix.matches("^\\d{13}$")) {
							matchKey = fileNameNoSuffix + "|";
						} else {
							matchKey = fileNameNoSuffix.substring(0, 13) + "|" + fileName;
						}
						// Add the search result record to results list
						resultsFessList.add(matchKey);
					}
				}
			}
		} catch (Exception e){
			logger.error("Fess�������V�X�e���G���[���������܂�", e);
		}

		return resultsFessList;
	}
}