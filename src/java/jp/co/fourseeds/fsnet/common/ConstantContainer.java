package jp.co.fourseeds.fsnet.common;

/**
 * �R���X�^���g��`
 * 
 * @author NTS
 * @version 1.0.0 : 2015/11/25 �V�K�쐬
 *
 **/
public class ConstantContainer {
	public static final String LOGIN_USER_KEY = "LoginUserKey";
	
	public static final String EXCEPTION_AUTHENTICATION = "Authentication Exception";
	public static final String EXCEPTION_ACTION = "Action Exception";
	public static final String EXCEPTION_SERVICE = "Service Exception";
	public static final String EXCEPTION_DATABASE = "Database Exception";
	public static final String EXCEPTION_PROPERTIES = "Properties Exception";
	public static final String EXCEPTION_GENERAL = "General Exception";

	public static final String SORT_CONFIRM_DATE_LATE = "confirmdate desc";
	public static final String SORT_START_DATE_LATE = "startdate desc";
	public static final String SORT_FIELD_SUBJECT_ASCENDING = "title asc";
	public static final String SORT_FIELD_SUBJECT_DESCENDING = "title desc";

	public static final String USER_SEARCH_LIST = "userSearchList";

	// �ÓI�R���e���c
	public static final String PAGE_STATIC = "0";
	// ���I�R���e���c
	public static final String PAGE_DYNAMIC = "1";
	// ��ʒl�ێ��p�Z�b�V�����擪
	public static final String SESSION_TRANSMIT_BEGINNING = "TRANSMIT_";
	// �Z�b�V�����ɃA�N�Z�X���O�o�͗p�y�[�WID��ێ�����
	public static final String SESSION_ACCESS_LOG_PAGE_ID = "ACCESS_LOG_PAGE_ID";
	
	/** The stream buffer size */
	public static final int ZIP_BUFFER_SIZE = 2048;
	public static final int BUFFER_SIZE = 1024;
	
	// �R���e���c�󋵊m�F�������ʂ̖}��
	public static final String BLUE_CONTENTS = "���������ƈ�v����R���e���c";
	public static final String GRAY_CONTENTS = "�{���s�m�F���[�U���w�肵���ꍇ�Y�����[�U�����z�u�ŉ{�����ł��Ȃ��R���e���c";
	public static final String NOLINK_CONTENTS = "�u�����N�斢�ݒ�v�������́u�����N��R���e���c�Ȃ��v�̃����N�����݂���R���e���c";
	public static final String FO_CONTENTS = "���J�\��R���e���c";
	public static final String NE_CONTENTS = "�ʏ�ҏW���R���e���c";
	public static final String OE_CONTENTS = "���J�����܂ܕҏW���R���e���c";
	public static final String DONTLOOKIMAGE_CONTENTS = "�w�胆�[�U�{���s�R���e���c";
	public static final String DEL_CONTENTS = "���J�����؂�R���e���c";
	
	// �T�C�g�������F
	public static final int SITE_SEARCH_MAX = 100;
	
	public static final String MAIN_IMG_PATH = "main/";
	
	// ���[�U�g�D�敪�͖{��
	public static final String USER_ORIGINAL = "0";
	
	// ���[�U�g�D�敪�͒��c
	public static final String USER_DIRECT = "1";
	
	// ���[�U�g�D�敪�͂e�b
	public static final String USER_FC = "4";
	
	// �g�D�敪
	public static final String ORGANIZATION_KBN = "ORGANIZATION_KBN";
	
	// �]�ƈ��敪�̓A���o�C�g 
	public static final  String PART_TIME_JOB_FLAG = "2";
	
	// �]�ƈ��敪
	public static final String USER_DIVISION_KEYCODE = "19";
	
	// ��E�敪
	public static final String POSITION_KEYCODE = "233";
	
	// ���敪
	public static final String AREA_KEYCODE = "189";
	
	// �X�܋敪
	public static final String STORE_DIVISION_KEYCODE = "15";
	
	// ���ʋ敪
	public static final String SEX_KEYCODE = "10";
	
	// ���[�U�O���[�v�V�X�e�����p�敪
	public static final String USER_GROUP_ROLE = "USER_GROUP_ROLE";

	// �V�X�e�����p�敪
	public static final String ROLE = "ROLE";
	
	// �V�X�e�����p�敪VALUE
	public static final String ROLE_ADMINISTRATOR = "1";
	public static final String ROLE_USER = "5";
	
	// ��Ћ敪
	public static final String COMPANY_KEYCODE = "279";

	// �����敪
	public static final String UNIFICATION_KEYCODE = "1";

	// ���Ƌ敪
	public static final String BUSINESS_KEYCODE = "2";

	// �c�ƕ��敪
	public static final String SALES_KEYCODE = "3";
	
	// �\���t���O
	public static final String UDS_SHOW_FLAG = "UDS_SHOW_FLAG";
	
	// �\���t���O�F�\��
	public static final String SHOW_FALG_YES = "�\��";
	
	// �\���t���O�F�\�����Ȃ�
	public static final String SHOW_FALG_NO = "�\�����Ȃ�";
	
	// �S�Ј�
	public static final String ALL_USER = "�S�Ј�";
	
	// �l�ėp�敪
	public static final String PERSONAL_KEYCODE = "1";
}
