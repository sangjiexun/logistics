package jp.co.common.mst.pm01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import jp.co.common.frame.dao.BaseDao;

/**
 * ntsApp�V�X�e���̃��O�C��DAO�̎����N���X
 *
 * @author NTS
 * @version 1.0.0 : 2015.09.14 �V�K�쐬
 * 
 * @author NTS
 * @version 1.1.0 : 2017.10.13 �������C��
 */
@Repository
public class CCMPm01MstDao extends BaseDao{

	/** Log4j�̒�` */
	private final Log logger = LogFactory.getLog(getClass());
	
	/**
	 * 
	 * @return
	 */
	public List<CCMPm01MstModel> getPm01List() {
		
		Map<String, Object> param = new HashMap<String, Object>();
		return this.sqlSessionTemplate.selectList("ccmSelectMst.getPm01List", param);
	}
	
}
