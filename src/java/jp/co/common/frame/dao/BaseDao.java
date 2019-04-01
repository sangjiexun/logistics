package jp.co.common.frame.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ���DAO�N���X<br>
 * �ȉ��̋@�\���T�|�[�g����B<br> 
 * <ul>
 *  <li>SqlMapClientDaoSupport���p��</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 * 
 */
public class BaseDao {
	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	@Autowired
	public SqlSessionTemplate sqlSessionTemplateMysql;

	public SqlSessionTemplate getSqlSessionTemplateMysql() {
		return sqlSessionTemplateMysql;
	}

	public void setSqlSessionTemplateMysql(SqlSessionTemplate sqlSessionTemplateMysql) {
		this.sqlSessionTemplateMysql = sqlSessionTemplateMysql;
	}
}
