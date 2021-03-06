package jp.co.fourseeds.fsnet.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jp.co.common.frame.dao.BaseDao;

import jp.co.fourseeds.fsnet.beans.SubWebPageReplaceStuffBean;
import jp.co.fourseeds.fsnet.common.util.StringUtil;

/**
 * TuæÊ(WebSÒID·µÖ¦_ÐõîñÝè)@\DaoÀNX
 * 
 *-----------------------------------------------------------
 *@Version      When            Who            Why
 *-----------------------------------------------------------
 * 1.1      2017/09/11          NTS            ©¼µÎ
 * 
 **/
@Repository
public class SubWebPageReplaceStuffDao extends BaseDao {

	public List<SubWebPageReplaceStuffBean> getStuffList(SubWebPageReplaceStuffBean stuffBean, String strOrderBy, String popFromFlag) {
		// õðMAP
		Map<String, Object> param = new HashMap<String, Object>();
		// õ¼ 
		param.put("PARA_USER_NAME", stuffBean.getSearchUserName());
		// ïÐID
		param.put("PARA_COMPANY_ID", stuffBean.getConditionCompanyId());
		// ID
		param.put("PARA_UNIFICATION_ID", stuffBean.getConditionUnificationId());
		// ÆID
		param.put("PARA_BUSINESS_ID", stuffBean.getConditionBusinessId());
		// cÆID
		param.put("PARA_SALES_ID", stuffBean.getConditionSalesId());
		
		if (StringUtil.isBlank(stuffBean.getSearchUserName())) {
			// æÊðy¼ z¶ÝµÈ¢Ìê
			param.put("PARA_FLAG", "1");
		} else {
			param.put("PARA_FLAG", "0");
		}
		return this.sqlSessionTemplate.selectList("subWebPageReplaceStuff.getReplaceList", param);
		
	}
}