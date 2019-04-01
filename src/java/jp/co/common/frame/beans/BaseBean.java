package jp.co.common.frame.beans;

import java.util.Date;

/**
 *  DB EntityBean�I�u�W�F�N�g�̊��N���X
 * <pre>
 *  ���ʃt�B�[���h�̃Z�b�g�E�擾���\�b�h��񋟂���B
 * </pre>
 * <ul>
 *   <li>���ʃv���p�e�B���擾</li>
 *   <li>���ʃv���p�e�B��ݒ�</li>
 * </ul> 
 * @author NTS
 * @version 1.0 
 */
public class BaseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String createBy;
	
	private Date createDate;
	
	private String updateBy;
	
	private Date updateDate;
	
	private String deleteFlag;
	// �X�V���[�U���́i�r������p�j
	private String updateUserName;
	// �X�V�����i�r������p�j
	private String updateDateStr;

	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getUpdateDateStr() {
		return updateDateStr;
	}
	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
}