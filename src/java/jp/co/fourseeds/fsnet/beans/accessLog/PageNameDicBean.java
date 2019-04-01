package jp.co.fourseeds.fsnet.beans.accessLog;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
/**
 *  �y�[�W���̂̎����N���X
 * @Scope �P�� 
 * @author NTS
 * @version 1.0 
 */
public class PageNameDicBean {
	// �y�[�W�����}�b�v
	private Map<String, String>pageDic = new HashMap<String, String>();
	
	// �y�[�W���̎擾 
	public String getPageName(String pageId) {
		return pageDic.get(pageId);
	}
	// �y�[�W���̊i�[
	public void setPageName(String pageId, String pageName) {
		pageDic.put(pageId, pageName);
	}
	// �i�[�����Ƃ����f
	public boolean hasPage (String pageId) {
		return pageDic.containsKey(pageId);
	}
}
