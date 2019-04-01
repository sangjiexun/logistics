/*
 * File Name�F LabelValueBean.java
 * -----------------------------------------------------------
 *�@Version      When            Who            Why
 *-----------------------------------------------------------
 *�@   1.0		  2015/09/25	   NTS			       �V�K�쐬
�@*/
package jp.co.common.frame.beans;

/**
 *  �敪�}�X�^ Bean�I�u�W�F�N�g�̃N���X
 * <pre>
 *  ���ʃt�B�[���h�̃Z�b�g�E�擾���\�b�h��񋟂���B
 * </pre>
 * <ul>
 *   <li>�敪���e�Ƌ敪�l�v���p�e�B���擾</li>
 *   <li>�敪���e�Ƌ敪�l�v���p�e�B��ݒ�</li>
 * </ul> 
 * @author NTS
 * @version 1.0 
 */
public class LabelValueBean extends BaseBean {

	private static final long serialVersionUID = -2078576973282468598L;
	
	/** �敪���e */
	private String label;
	/** �敪�l */
	private String value;
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}		
}
