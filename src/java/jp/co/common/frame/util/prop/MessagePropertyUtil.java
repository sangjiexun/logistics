package jp.co.common.frame.util.prop;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.common.frame.constant.BaseSystemConstant;

/**
 *  Properties�N���X���g�����s�����ݒ�t�@�C������̓ǂݍ���(load)���s���N���X�B
 * <pre>
 *  �ȉ��̋@�\���T�|�[�g����B
 * </pre>
 * <ul>
 *   <li>�t�@�C�����̏����L�[�������Ɏ擾���郁�\�b�h��񋟂���B</li>
 * </ul> 
 * @author NTS
 * @version 1.0 
 */
public class MessagePropertyUtil extends BasePropertyUtil {

	/** Log4j�̔z�u */
	private static final Log logger = LogFactory.getLog(MessagePropertyUtil.class);
	/** ���s���ݒ�ێ��I�u�W�F�N�g */
	private static MessagePropertyUtil instance = null;
	/** �����ݒ�t�@�C���� **/
	private static final String MESSAGE_FILE = BaseSystemConstant.MESSAGE_FILE;
	
	/**
	 * �R���X�g���N�^
	 * ���̃N���X��new�ł��Ȃ��B 
	 */
	private MessagePropertyUtil() {
		init(MESSAGE_FILE);
  	}

	/**
	 * Environment�N���X�̃C���X�^���X��Ԃ����\�b�h�B
	 * ���̃��\�b�h���Ăяo�����ɂ��AEnvironment�N���X�̃C���X�^���X���\�z����B
	 * ���ɃI�u�W�F�N�g���쐬����Ă���ꍇ�͎Q�Ƃ�Ԃ��B
	 * @param None �����Ȃ�
	 * @return Environment ���Y�N���X�̃I�u�W�F�N�g
	 */
	public synchronized static MessagePropertyUtil getInstance() {
		
		if (instance == null) {
			instance = new MessagePropertyUtil();
			if (logger.isDebugEnabled()) {
				logger.debug("�V�K�I�u�W�F�N�g(MessagePropertyUtil)���쐬");
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("�����I�u�W�F�N�g(MessagePropertyUtil)��Ԃ�");
			}
		} 
		return instance;
	}
	
	@SuppressWarnings("rawtypes")
	public static Set getMessagesList() {
		System.out.println("size:" + getInstance().getPropertyEntrySet().size());
		
		return getInstance().getPropertyEntrySet();
	}

	public static String getStringProperty(String key) {
		
		return getInstance().getPropertyString(key); 
	}	
	
}