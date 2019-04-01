package jp.co.fourseeds.fsnet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.fourseeds.fsnet.beans.StoreBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.fourseeds.fsnet.dao.StoreDao;

import jp.co.common.frame.service.BaseBusinessService;
import jp.co.common.frame.util.DateUtil;

/**
 * �X�܏��@�\�T�[�r�X�����N���X
 * 
 *-----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@1.0		2016/01/21		    NTS        	       �쐬
 *  1.1		2017/09/06			NTS			      �������C��
 **/
@Component
public class StoreService extends BaseBusinessService{
	
	@Autowired
	private StoreDao storeDao;
	//�ϑ�
	private final String CONSIGN_FLAG = "1";
	//���
	private final String ACCESSION_FLAG = "2";
	//���J
	private final String OPEN = "1";
	//�\��
	private final String VAILED = "0";
	
	/**
	 * �X�܏����擾
	 * @param storeBean
	 * @param strOrderBy
	 * @return ��������
	 */
	public List<StoreBean> getStoreList(StoreBean storeBean, String strOrderBy) {
		return storeDao.getStoreList(storeBean, strOrderBy, getLoginUserBean());
	}
	
	/**
	 * �X�܏ڍ׏����擾
	 * @param storeBean
	 * @return ��������
	 */
	public StoreBean getStoreDetailInfo(StoreBean storeBean) {
		return storeDao.getStoreDetailInfo(storeBean, getLoginUserBean());
	}
	
	/**
	 * �Ɩ��ϑ�/��������擾
	 * @param storeBean
	 * @return StoreBean
	 */
	public StoreBean getConsignAccessionInfo(StoreBean storeBean) {
		//�Ɩ��ϑ�(���J)���
		Map<String, String> openConsign = storeDao.getOpenConsignAccessionInfo(storeBean, CONSIGN_FLAG);
		if (openConsign != null) {
			storeBean.setOpenConsignFlag(conversionNullToEmpty(openConsign.get("CONSIGN_FLAG")));
			storeBean.setOpenConsignStoreSet(conversionNullToEmpty(openConsign.get("CONSIGN_STORE_SET")));
			storeBean.setOpenConsignStoreEx(conversionNullToEmpty(openConsign.get("CONSIGN_STORE_EX")));
			storeBean.setOpenYconrc(conversionNullToEmpty(openConsign.get("YCONRC")));
			storeBean.setOpenName(conversionNullToEmpty(openConsign.get("NAME")));
		}

		//�Ɩ��ϑ�(�\��)���
		Map<String, String> vailedConsign = storeDao.getVailedConsignAccessionInfo(storeBean, CONSIGN_FLAG);
		if (vailedConsign != null) {
			storeBean.setVailedConsignFlag(conversionNullToEmpty(vailedConsign.get("CONSIGN_FLAG")));
			storeBean.setVailedConsignStoreSet(conversionNullToEmpty(vailedConsign.get("CONSIGN_STORE_SET")));
			storeBean.setVailedConsignStoreEx(conversionNullToEmpty(vailedConsign.get("CONSIGN_STORE_EX")));
			storeBean.setVailedYconrc(conversionNullToEmpty(vailedConsign.get("YCONRC")));
			storeBean.setVailedName(conversionNullToEmpty(vailedConsign.get("NAME")));
		}

		//�Ɩ����(���J)���
		Map<String, String> openAccession = storeDao.getOpenConsignAccessionInfo(storeBean, ACCESSION_FLAG);
		if (openAccession != null) {
			storeBean.setOpenAccessionFlag(conversionNullToEmpty(openAccession.get("CONSIGN_FLAG")));
			storeBean.setOpenAccessionStoreSet(conversionNullToEmpty(openAccession.get("CONSIGN_STORE_SET")));
			storeBean.setOpenAccessionStoreEx(conversionNullToEmpty(openAccession.get("CONSIGN_STORE_EX")));
		}

		//�Ɩ����(�\��)���
		Map<String, String> vailedAccession = storeDao.getVailedConsignAccessionInfo(storeBean, ACCESSION_FLAG);
		if (vailedAccession != null) {
			storeBean.setVailedAccessionFlag(conversionNullToEmpty(vailedAccession.get("CONSIGN_FLAG")));
			storeBean.setVailedAccessionStoreSet(conversionNullToEmpty(vailedAccession.get("CONSIGN_STORE_SET")));
			storeBean.setVailedAccessionStoreEx(conversionNullToEmpty(vailedAccession.get("CONSIGN_STORE_EX")));
		}
		return storeBean;
	}
	
	/**
	 * �X�܏����X�V
	 * @param storeBean
	 * @param loginUserId
	 */
	public void updateStoreMaster(StoreBean storeBean, String loginUserId, String role) {
		
		//�ϑ����
		if (ConstantContainer.USER_DIRECT.equals(storeBean.getFcFlag())) {
			Map<String, String> consignInfo = storeDao.getStoreMaster(storeBean, CONSIGN_FLAG);
			if (consignInfo == null) {
				consignInfo = new HashMap<String, String>();
				consignInfo.put("CREATE_DATE", DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss"));
				consignInfo.put("CREATE_BY", loginUserId);
			}
			
			//�ϑ����OPEN
			if ("1".equals(storeBean.getOpenConsignFlag())) {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//�ϑ������폜
					storeDao.deleteStoreMaster(storeBean, OPEN);
				}
				//�o�^�ϑ����
				storeDao.insertStoreMaster(storeBean.getStoreId(), CONSIGN_FLAG, storeBean.getOpenConsignStoreSet(),
						storeBean.getOpenConsignStoreEx(), storeBean.getOpenYconrc(), consignInfo.get("CREATE_DATE"),
						consignInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//�ϑ������폜
					storeDao.updateStoreFlag(storeBean, loginUserId);
				}
			}
			
			//�ϑ����VAILED
			if ("1".equals(storeBean.getVailedConsignFlag())) {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//�ϑ����VAILED���폜
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
				storeDao.insertStoreMaster(storeBean.getStoreId(), CONSIGN_FLAG, storeBean.getVailedConsignStoreSet(),
						storeBean.getVailedConsignStoreEx(), storeBean.getVailedYconrc(), consignInfo.get("CREATE_DATE"),
						consignInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(consignInfo.get("STORE_ID")) || null != consignInfo.get("STORE_ID")) {
					//�ϑ����VAILED���폜
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
			}
		}
		//������
		if (ConstantContainer.USER_FC.equals(storeBean.getFcFlag())) {
			Map<String, String> accessionInfo = storeDao.getStoreMaster(storeBean, ACCESSION_FLAG);
			if (accessionInfo == null) {
				accessionInfo = new HashMap<String, String>();
				accessionInfo.put("CREATE_DATE", DateUtil.getNowTime("yyyy/MM/dd HH:mm:ss"));
				accessionInfo.put("CREATE_BY", loginUserId);
			}
			
			//������OPEN
			if ("1".equals(storeBean.getOpenAccessionFlag())) {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//��������폜
					storeDao.deleteStoreMaster(storeBean, OPEN);
				}
				//�o�^������
				storeDao.insertStoreMaster(storeBean.getStoreId(), ACCESSION_FLAG, storeBean.getOpenAccessionStoreSet(),
						storeBean.getOpenAccessionStoreEx(), "", accessionInfo.get("CREATE_DATE"),
						accessionInfo.get("CREATE_BY"), loginUserId);
			} else {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//��������폜
					storeDao.updateStoreFlag(storeBean, loginUserId);
				}
			}
			
			//������VAILED
			if ("1".equals(storeBean.getVailedAccessionFlag())) {
				if (!"".equals(accessionInfo.get("STORE_ID")) || null != accessionInfo.get("STORE_ID")) {
					//������VAILED���폜
					storeDao.deleteStoreMaster(storeBean, VAILED);
				}
				storeDao.insertStoreMaster(storeBean.getStoreId(), ACCESSION_FLAG, storeBean.getVailedAccessionStoreSet(),
						storeBean.getVailedAccessionStoreEx(), "", accessionInfo.get("CREATE_DATE"),
						accessionInfo.get("CREATE_BY"), loginUserId);
			} else {
				//������VAILED���폜
				storeDao.deleteStoreMaster(storeBean, VAILED);
			}
		}
		
		//�����������X�܏����X�V
		storeDao.updateStoreMaster(storeBean,role,loginUserId);
	}
	
	/**
	 * @function Conversion the null to string Empty
	 * @param Object
	 * @return String
	 */
	private static String conversionNullToEmpty(Object o) {
		if (o == null || "".equals(o) || "null".equals(o)) {
			o = "";
		}
		
		return o.toString();
	} 
}