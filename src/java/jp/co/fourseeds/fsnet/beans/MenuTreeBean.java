package jp.co.fourseeds.fsnet.beans;

import jp.co.common.frame.beans.BaseBean;

/**
 *  ���O�C�����[�U�[���N���X
 * <pre>
 *  ���ʃt�B�[���h�̃Z�b�g�E�擾���\�b�h��񋟂���B
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class MenuTreeBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	// �m�[�hID�iPAGE_ID�j
	private String nodeId = "";
	// �m�[�h���iTITLE�j
	private String nodeName = "";
	// �e�m�[�hID�iP_PAGE_ID�j
	private String parentNodeId = "";
	// �\���t���O
	private String displayFlag = "";
	// �����N�t���O
	private String linkFlag = "";
	// �t�@�C��URL(�t�@�C�����ƃA�h���X)
	private String url = "";
	// �\����
	private String orderBy = "";
	// ���R���e���c�͗\��@OR�@�^����W��
	private String isReserve;
	
	private String childrenCount;
	
	private String cnt;
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getParentNodeId() {
		return parentNodeId;
	}
	public void setParentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}
	public String getDisplayFlag() {
		return displayFlag;
	}
	public void setDisplayFlag(String displayFlag) {
		this.displayFlag = displayFlag;
	}
	public String getLinkFlag() {
		return linkFlag;
	}
	public void setLinkFlag(String linkFlag) {
		this.linkFlag = linkFlag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getIsReserve() {
		return isReserve;
	}
	public void setIsReserve(String isReserve) {
		this.isReserve = isReserve;
	}
	public String getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(String childrenCount) {
		this.childrenCount = childrenCount;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}
}