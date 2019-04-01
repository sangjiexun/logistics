package jp.co.common.frame.util.prop;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import jp.co.common.frame.exception.LocalRuntimeException;

import jp.co.common.frame.constant.BaseMessageConstant;
import jp.co.common.frame.constant.BaseSystemConstant;

import jp.co.common.frame.util.NumberUtil;
import jp.co.common.frame.util.StringBaseUtil;

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
public class BasePropertyUtil {

	/** Log4j�̔z�u */
	private final Log logger = LogFactory.getLog(this.getClass());
	private Properties PROPERTIES = new Properties();
	
	/**
	 *�R���X�g���N�^
	 *���̃N���X��new�ł��Ȃ��B 
	 */
	protected BasePropertyUtil() {
  	}

	/**
	 * ���������\�b�h
	 * resources.application���������A
	 * ���e��Properties�I�u�W�F�N�g�ɃZ�b�g����B
	 * @param propertyFile �����ݒ�t�@�C��
	 */
	protected void init(String propertyFile) {
		InputStream in = null;
		in = this.getClass().getResourceAsStream(propertyFile);
		if (in == null) {
			in = this.getClass().getClassLoader().getResourceAsStream(propertyFile);
		}
		try {
			PROPERTIES.load(in);
		} catch (IOException e) {
			throw new LocalRuntimeException(BaseMessageConstant.ERROR004, 
					"�v���p�e�B�t�@�C���͐���Ƀ��[�h�ł��܂���B", e);
		}
	}

	/**
	 * ���s�����ݒ�t�@�C���̓��e�𕶎���̕����ŕԂ�
	 * @param argKey  �t�@�C�����̏��L�[
	 * @return String �t�@�C�����̏�񕶎���l
	 */
	public String getPropertyString(String argKey) {
		if (argKey == null) {
			return null;
		}
		//�ݒ�l���擾
		String attribute = PROPERTIES.getProperty(argKey);
		if (logger.isDebugEnabled()) {
			logger.debug("�yKey�z= " + argKey + " �yValue�z="+attribute+"�̃v���p�e�B���Ԃ���܂��B");
		}
		return attribute;
	}
	
	/**
	 * ���s�����ݒ�t�@�C���̓��e�𐮐��̕����ŕԂ�
	 * @param argKey  �t�@�C�����̏��L�[
	 * @return String �t�@�C�����̏�񐮐��l
	 */
	public int getPropertyInt(String argKey) {
		String attribute = getPropertyString(argKey);
		if (NumberUtil.isNumber(attribute)) {
			return NumberUtil.toInt(attribute);	
		} else {
			throw new LocalRuntimeException(BaseMessageConstant.ERROR005, "�����ւ̕ϊ��Ɏ��s���܂����B");
		}
	}
	
	/**
	 * ���s�����ݒ�t�@�C���̓��e�𕶎���̕����ŕԂ�
	 */
	public String getEncodeProperty(String argKey) {
		String message = getPropertyString(argKey);
		if (!StringBaseUtil.isBlank(message)) {
			try {
				message = new String(message.getBytes(BaseSystemConstant.CHARSET_NAME_ISO8859_1),
						BaseSystemConstant.CHARSET_NAME_SHIFT_JIS);
			} catch (UnsupportedEncodingException e) {
				if (logger.isInfoEnabled()) {
					logger.info("���b�Z�[�W�ւ̕ϊ��Ɏ��s���܂����B");
				}
			}
		}
		return message == null? "" : message;
	}
	
	@SuppressWarnings("rawtypes")
	public Set getPropertyEntrySet() {
		return PROPERTIES.entrySet();
	}
}
