package jp.co.common.frame.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.time.DateFormatUtils;

import jp.co.common.frame.util.prop.EnvPropertyUtil;


/**
 *  ���t�p�N���X
 * <pre>
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class DateUtil extends DateFormatUtils {
	private static final String MONTH_FORMAT = "yyyyMM";
	private static final String DATE_FORMAT = "yyyyMMdd";
	public static final String TIME_FORMAT = "yyyyMMddHHmmss";
	private static final String SEC_FORMAT = "yyyyMMddHHmmssSSS";
	private static final String SECTIME_FORMAT = "HHmmssSSS";
	private static final Map<String, String> formatMap = new HashMap<String, String>();
	private static final Map<String, String> formatMonthMap = new HashMap<String, String>();
	
	/*-----------------------------------*/
	/*      ���t�t�H�[�}�b�g�̒�`                          */ 
	/*-----------------------------------*/
	static {
		formatMap.put("0", "yyyy/MM/dd");
		formatMap.put("1", "dd/MM/yyyy");
		formatMap.put("2", "yyyy-MM-dd");
		formatMap.put("3", "yyyy.MM.dd");
		formatMap.put("4", "MM/dd/yyyy");
		formatMap.put("5", "dd.MM.yyyy");
		formatMap.put("6", "dd-MM-yyyy");
		formatMonthMap.put("0", "yyyy/MM");
		formatMonthMap.put("1", "MM/yyyy");
		formatMonthMap.put("2", "yyyy-MM");
		formatMonthMap.put("3", "yyyy.MM");
		formatMonthMap.put("4", "MM/yyyy");
		formatMonthMap.put("5", "MM.yyyy");
		formatMonthMap.put("6", "MM-yyyy");
	}

    /** �����J�n���t[����] */
    public static final int GENGO_DATE_MEIJI = 18680908;
    /** �����J�n���t[�吳] */
    public static final int GENGO_DATE_TAISHO = 19120730;
    /** �����J�n���t[���a] */
    public static final int GENGO_DATE_SHOWA = 19261225;
    /** �����J�n���t[����] */
    public static final int GENGO_DATE_HEISEI = 19890108;

    /** ����������(�L��)[����] */
    public static final String GENGO_MEIJI = "M";
    /** ����������(�L��)[�吳] */
    public static final String GENGO_TAISHO = "T";
    /** ����������(�L��)[���a] */
    public static final String GENGO_SHOWA = "S";
    /** ����������(�L��)[����] */
    public static final String GENGO_HEISEI = "H";

    /** ����������(����)[����] */
    public static final String GENGO_MEIJI_J = "����";
    /** ����������(����)[�吳] */
    public static final String GENGO_TAISHO_J = "�吳";
    /** ����������(����)[���a] */
    public static final String GENGO_SHOWA_J = "���a";
    /** ����������(����)[����] */
    public static final String GENGO_HEISEI_J = "����";

    /** �N��������(���l)[����] */
    public static final String GENGO_HEISEI_S = "1";
    /** �N��������(���l)[���a] */
    public static final String GENGO_SHOWA_S = "2";
    /** �N��������(���l)[�吳] */
    public static final String GENGO_TAISHO_S = "3";
    /** �N��������(���l)[����] */
    public static final String GENGO_MEIJI_S = "4";
    
	/*-----------------------------------*/
	/*		���ݎ����֘A					 */
	/*-----------------------------------*/
	/**
	 * ���݂̔N�����擾����
	 * 
     * <pre>
     * ��j
     *   getNowMonth()   = 200608
     * </pre>
	 * @return ���݂̔N��
	 */
	public static String getNowMonth() {
		return DateFormatUtils.format(new Date(), MONTH_FORMAT);
	}

	/**
	 * ���ݓ��t��N�����`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getNowDate()   = 20060815
     * </pre>
	 * @return ���݂̓��t
	 */
	public static String getNowDate() {
		return DateFormatUtils.format(new Date(), DATE_FORMAT);
	}
	
	/**
	 * ���ݓ��t���w��`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getNowDate('yyyy/MM/dd')   = 2006/08/15
     * </pre>
	 * @return ���݂̓��t
	 */
	public static String getNowDate(String format) {
		return DateFormatUtils.format(new Date(), format);
	}

	/**
	 * ���ݓ��t��N���������b�`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getNowTime()   = 20060815152502
     * </pre>
	 * @return ���݂̓��t����
	 */
	public static String getNowTime() {
		return DateFormatUtils.format(new Date(), TIME_FORMAT);
	}

	/**
	 * ���ݓ��t��N���������b�~���b�`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getNowMilSec()   = 20060815152502099
     * </pre>
	 * @return ���݂̓��t����
	 */
	public static String getNowMilSec() {
		return DateFormatUtils.format(new Date(), SEC_FORMAT);
	}

	/**
	 * ���ݓ��t�������b�~���b�`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getNowTimeMilSec()   = 152502099
     * </pre>
	 * @return ���݂̓��t����
	 */
	public static String getNowTimeMilSec() {
		return DateFormatUtils.format(new Date(), SECTIME_FORMAT);
	}

	/**
	 * �w�肵�����Ԃ����Z�����N�����擾����
	 * 
     * <pre>
     * ��j����2006�N8������
     *   getLocalMonth(1)   = 200609
     *   getLocalMonth(-1)   = 200607
     * </pre>
	 * @param difference ���ԍ����A�}�C�i�X�̏ꍇ���Z����
	 * @return �N��
	 */
	public static String getLocalMonth(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, difference);
		return DateFormatUtils.format(cal.getTime(), MONTH_FORMAT);
	}

	/**
	 * �w�肵�����Ԃ����Z�������t��N�����`���Ŏ擾����
	 * 
     * <pre>
     * ��j����2006�N8��15������
     *   getLocalDate(2)   = 20060817
     *   getLocalDate(-2)  = 20060813
     * </pre>
	 * @param difference ���ԍ����A�}�C�i�X�̏ꍇ���Z����
	 * @return ���t
	 */
	public static String getLocalDate(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, difference);
		return DateFormatUtils.format(cal.getTime(), DATE_FORMAT);
	}

	/**
	 * �w�肵�����Ԃ����Z�������t��N���������b�`���Ŏ擾����
	 * 
     * <pre>
     * ��j����2006�N8��15��15������
     *   getLocalTime(10)   = 20060816012502
     *   getLocalTime(-10)  = 20060815052502
     * </pre>
	 * @param difference ���ԍ���(24���Ԍ`��)�A�}�C�i�X�̏ꍇ���Z����
	 * @return ���t����
	 */
	public static String getLocalTime(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, difference);
		return DateFormatUtils.format(cal.getTime(), TIME_FORMAT);
	}

	/**
	 * �w�肵�����Ԃ����Z����Date�I�u�W�F�N�g���擾����
	 * 
     * <pre>
     * ��j����Wes Aug 15 15:31:07 JST 2006 ����
     *   getLocalDateObj(1)   = Wed Aug 16 15:31:07 JST 2006
     *   getLocalDateObj(-1)  = Mon Aug 14 15:31:07 JST 2006
     * </pre>
	 * @param difference ���ԍ����A�}�C�i�X�̏ꍇ���Z����
	 * @return Date�I�u�W�F�N�g
	 */
	public static Date getLocalDateObj(int difference) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, difference);
		return cal.getTime();
	}

	/**
	 * �w�肳�ꂽ���t�I�u�W�F�N�g����A�Y��������t��N���������b�~���b�`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getDateMilSec(new Date())   = 20060815153107487
     * </pre>
	 * @param date Date�I�u�W�F�N�g
	 * @return ���t����
	 */
	public static String getDateMilSec(Date date) {
		return DateFormatUtils.format(date,SEC_FORMAT);
	}
	
	/**
	 * YYYYMMDD�t�H�[�}�b�g�̓��t���w�肵�����P�[���̕W���t�H�[�}�b�g�ɕϊ����郁�\�b�h
	 * 
     * <pre>
     * ��j
     *   getDateString("20060815")   = 2006/08/15
     * </pre>
	 * @param date YYYYMMDD�t�H�[�}�b�g�̓��t
	 * @param localeString ���P�[����\��������
	 * @return YYYYMMDD�t�H�[�}�b�g�̓��t�����P�[���W���t�H�[�}�b�g�ɕϊ������l
	 */
	public static String getDateString(String date) {
		return getDateString(date, "yyyy/MM/dd");
	}
	
	/**
	 * YYYYMMDD�t�H�[�}�b�g�̓��t���w�肵�����P�[���A�w�肵���p�^�[���̃t�H�[�}�b�g�ɕϊ����郁�\�b�h
	 * 
     * <pre>
     * ��j
     *   getDateString("20060815", "EEEE-MMMM-dd-yyyy-HH-mm-ss")	= �Ηj��-8��-15-2006-15-34-02
     *   getDateString("20060815", "yyyy/MM/dd HH:mm:ss")	= 2006/08/15 15:34:02
     *   getDateString("20060815", "yyyy-MM-dd")			= 2006-08-15
     * </pre>
	 * @param date YYYYMMDD�t�H�[�}�b�g�̓��t
	 * @param localeString ���P�[����\��������
	 * @param pattern SimpleDateFormat�ƌ݊����̂���p�^�[��
	 * @return YYYYMMDD�t�H�[�}�b�g�̓��t�����P�[���t�H�[�}�b�g�ɕϊ������l
	 */
	public static String getDateString(String date, String pattern) {
	    date = StringBaseUtil.allBlankTrim(date);
	    if (date == null || date.length() != 8) {
	        return "";
	    }
		//�J�����_�[�I�u�W�F�N�g�𐶐�
		Calendar cal = createCalender(date, Locale.getDefault());
		//�t�H�[�}�b�g�̕ϊ�
		return DateFormatUtils.format(cal.getTime(), pattern);
	}
	
	/**
	 * Calendar Object�𐶐�����B
	 * 
	 * @param date YYYYMMDD�t�H�[�}�b�g�̓��t
	 * @param locale ���P�[��
	 * @return Calendar Object
	 */
	public static Calendar createCalender(String date, Locale locale) {
		//����A���A���ɕ���
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(4, 6)) - 1;
		int day = Integer.parseInt(date.substring(6, 8));
		//�J�����_�[�I�u�W�F�N�g�𐶐�
		Calendar cal = Calendar.getInstance(locale);
		cal.set(year, month, day);
		return cal;
	}
	
	/**
	 * ���P�[��JAPAN��Calendar Object�𐶐�����
	 * 
	 * @param date YYYYMMDD�t�H�[�}�b�g�̓��t
	 * @return Calendar Object
	 */
	public static Calendar createCalender(String date) {
		return createCalender(date, Locale.getDefault());
	}
	
	/**
	 * �N�����w�肵���t�H�[�}�b�g�ŕϊ����郁�\�b�h
	 * 
     * <pre>
     * ��j
     *   formatMonth("200608", "MMMM-yyyy")	= 8��-2006
     * </pre>
     * @see �t�H�[�}�b�g��getFormatString()���Q��
	 * @param dateStr YYYYMM�t�H�[�}�b�g�̔N��
	 * @param formatStr �t�H�[�}�b�g��`������
	 * @return �t�H�[�}�b�g�ϊ���N��
	 */
	public static String formatMonth(String dateStr, String formatStr) {
        int idate = NumberUtil.toInt(dateStr);
        if (String.valueOf(idate).length() != 6) {
	        return "";
	    }
	    String newDateStr = null;
		//���t�̎擾
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6));
		//�J�����_�[�I�u�W�F�N�g�̐���
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 0);
		//�t�H�[�}�b�g
		newDateStr = DateFormatUtils.format(cal.getTime(), formatStr);
		return newDateStr;
	}
	
	/**
	 * ���t��������w�肵���t�H�[�}�b�g�ŕϊ����郁�\�b�h
	 * 
     * <pre>
     * ��j
     *   formatDate("20060815", "MMMM-dd-yyyy")	= 8��-15-2006
     * </pre>
	 * @param dateStr ���t������
	 * @param formatStr �t�H�[�}�b�g��`������
	 * @return �t�H�[�}�b�g�ϊ�����t������
	 */
	public static String formatDate(String dateStr, String formatStr) {
        if (StringBaseUtil.isEmpty(dateStr)) {
	        return "";
	    }
		String newDateStr = null;
		//���t�̎擾

		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6)) -1;
		int day = Integer.parseInt(dateStr.substring(6, 8));
		//�J�����_�[�I�u�W�F�N�g�̐���
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//�t�H�[�}�b�g
		newDateStr = DateFormatUtils.format(cal.getTime(), formatStr);
		return newDateStr;
	}
	
	/**
	 * ���t������̃^�C�v���`�F�b�N����
	 * 
     * <pre>
     * ��j
     *   checkDateType("2008/1/1")	= false
     *   checkDateType("2008/01/01") = true
     * </pre>
	 * @param dateStr ���t������
	 * @return boolean
	 */
	public static boolean checkDateType(String dateStr) {
		String regex = "[0-9]{4}/[0-9]{2}/[0-9]{2}";
		boolean flag = Pattern.matches(regex, dateStr);
		if (flag) {

			try {
				String[] dateArray = dateStr.split("/");
				int i = Integer.parseInt(dateArray[0]);
				int k = Integer.parseInt(dateArray[1]) - 1;
				int i1 = Integer.parseInt(dateArray[2]);
				GregorianCalendar gregoriancalendar = new GregorianCalendar();
				gregoriancalendar.setLenient(false);
				gregoriancalendar.set(i, k, i1);
				return true;
			} catch (IllegalArgumentException illegalargumentexception) {
				return false;
			} catch (Exception exception) {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * ���t��������w�肵���t�H�[�}�b�g�ŕϊ����郁�\�b�h
	 * 
	 * <pre>
	 * ��j
	 *   formatDateToSec(&quot;20060815123456&quot;, &quot;MMMM-dd-yyyy-HH-mm-ss&quot;)	= 8��-15-2006-12-34-56
	 * </pre>
	 * 
	 * @param dateStr
	 *            YYYYMMDDHHMMSS�t�H�[�}�b�g�̓��t������
	 * @param formatStr
	 *            �t�H�[�}�b�g��`������
	 * @return �t�H�[�}�b�g�ϊ�����t������
	 */
	public static String formatDateToSec(String dateStr, String formatStr) {
		String newDateStr = null;
		// ���t�̎擾
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6)) - 1;
		int day = Integer.parseInt(dateStr.substring(6, 8));
		int hour = Integer.parseInt(dateStr.substring(8, 10));
		int minute = Integer.parseInt(dateStr.substring(10, 12));
		int second = Integer.parseInt(dateStr.substring(12, 14));		
		
		//�J�����_�[�I�u�W�F�N�g�̐���
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute, second);
		//�t�H�[�}�b�g
		newDateStr = DateFormatUtils.format(cal.getTime(), formatStr);
		return newDateStr;
	}

	/**
	 * ���t�t�H�[�}�b�g�̕�������擾���郁�\�b�h<br>
	 * <li>0 yyyy/MM/dd<li>
	 * <li>1 dd/MM/yyyy<li>
	 * <li>2 yyyy-MM-dd<li>
	 * <li>3 yyyy.MM.dd<li>
	 * <li>4 MM/dd/yyyy<li>
	 * <li>5 dd.MM.yyyy<li>
	 * <li>6 dd-MM-yyyy<li>
	 * 
	 * @param pattern ���t�t�H�[�}�b�g�̕�������w�肷�邽�߂̃p�^�[��
	 * @return ���t�t�H�[�}�b�g�̕�����
	 */
	public static String getFormatString(String pattern) {
		return (String) formatMap.get(pattern);
	}

	/**
	 * �N���t�H�[�}�b�g�̕�������擾���郁�\�b�h<br>
	 * <li>0 yyyy/MM<li>
	 * <li>1 MM/yyyy<li>
	 * <li>2 yyyy-MM<li>
	 * <li>3 yyyy.MM<li>
	 * <li>4 MM/yyyy<li>
	 * <li>5 MM.yyyy<li>
	 * <li>6 MM-yyyy<li>
	 * 
	 * @param pattern �N���t�H�[�}�b�g�̕�������w�肷�邽�߂̃p�^�[��
	 * @return �N���t�H�[�}�b�g�̕�����
	 */
	public static String getFormatMonthString(String pattern) {
		return (String) formatMonthMap.get(pattern);
	}

	/**
	 * ����t��n������̓��t��������擾����
	 * 
     * <pre>
     * ��j
     *   addMonthDate("200608", 2)	= 200610
     * </pre>
	 * @param startDate YYYYMM�t�H�[�}�b�g�̊���t
	 * @param n�@����t�Ƀv���X���錎��
	 * @return startDate��n������̓��t
	 * @throws NumberFormatException
	 */
	public static String addMonthDate(String startDate, int n) {
		int year = Integer.parseInt(startDate.substring(0, 4));
		int month = Integer.parseInt(startDate.substring(4, 6));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, 0);
		cal.add(Calendar.MONTH, n);
		return DateFormatUtils.format(cal.getTime(), "yyyyMM");
	}

    /**
	 * �w�肳�ꂽ���t�Ƀp�����[�^���������Z���ē��t��ԋp���܂�
     * <pre>
     * ��j
     *   addMonth("20060128", 1)	= 20060228
     *   addMonth("20060130", 1)	= 20060228
     *   addMonth("20060329", -1)	= 20060228
     * </pre>
     * @param strDate �˗����t(YYYYMMDD)��8��
     * @param month ����
     * @return �w�������A�����o�ߌ�̓��t��ԋp����(�������ł͂Ȃ��A�����ɂ���āA�Ԃ�)
 	 */	
	public static String addMonth(String strDate, int n) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) -1 ;
		int day = Integer.parseInt(strDate.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//�m������̓��t���擾
		cal.add(Calendar.MONTH, n);
		return DateFormatUtils.format(cal.getTime(), DATE_FORMAT); 	
	}

    /**
	 * �w�肳�ꂽ���t�Ƀp�����[�^���������Z���ē��t��ԋp���܂�
     * <pre>
     * ��j
     *   addMonth("200601", 1)	= 200602
     *   addMonth("200601", 1)	= 200602
     *   addMonth("200603", -1)	= 200602
     * </pre>
     * @param strDate �˗����t(YYYYMM)��6��
     * @param month ����
     * @return �w�������A�����o�ߌ�̓��t��ԋp����(�������ł͂Ȃ��A�����ɂ���āA�Ԃ�)
 	 */	
	public static String addMonthYM(String strDate, int n) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) -1 ;
		int day = Integer.parseInt("01");
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//�m������̓��t���擾
		cal.add(Calendar.MONTH, n);
		return DateFormatUtils.format(cal.getTime(), MONTH_FORMAT); 	
	}

    /**
	 * �w�肳�ꂽ���t�Ƀp�����[�^���������Z���ē��t��ԋp���܂�
     * <pre>
     * ��j
     *   addDay("20060228", 1)	= 20060301
     *   addDay("20060228", -1)= 20060227
     *   addDay("20040228", 1)	=20040229
     *   addDay("20040228", 2)	=20040301
     *   addDay("20040301", -1)=20040229
     * </pre>
     * @param strDate �˗����t(YYYYMMDD)��8��
     * @param month ����
     * @return �w�������A�����o�ߌ�̓��t��ԋp����)
 	 */	
	public static String addDay(String strDate, int n) {
		int year = Integer.parseInt(strDate.substring(0, 4));
		int month = Integer.parseInt(strDate.substring(4, 6)) -1 ;
		int day = Integer.parseInt(strDate.substring(6, 8));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		//�m������̓��t���擾
		cal.add(Calendar.DATE, n);
		return DateFormatUtils.format(cal.getTime(), DATE_FORMAT); 	
	}	

	/**
	 * �w�肳�ꂽ���t�Ƀp�����[�^���Ԑ������Z���ē��t��ԋp���܂�
     * <pre>
     * ��j
     *   addHour("20060228100000", 1) = 20060228110000
     *   addHour("20060228100000", -1) = 20060228090000
     * </pre>
     * @param strDate �˗����t(yyyyMMddHHmmss)��14��
     * @param n ���Ԑ�
     * @return �w�莞�Ԑ�����A���Ԑ��o�ߌ�̓��t��ԋp����)
 	 */
	public static String addHour(String dateStr, int n) {
		// ���t�̎擾
		int year = Integer.parseInt(dateStr.substring(0, 4));
		int month = Integer.parseInt(dateStr.substring(4, 6)) - 1;
		int day = Integer.parseInt(dateStr.substring(6, 8));
		int hour = Integer.parseInt(dateStr.substring(8, 10));
		int minute = Integer.parseInt(dateStr.substring(10, 12));
		int second = Integer.parseInt(dateStr.substring(12, 14));		
		
		//�J�����_�[�I�u�W�F�N�g�̐���
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day, hour, minute, second);
		//�m���Ԍ�̓��t���擾
		cal.add(Calendar.HOUR_OF_DAY, n);
		return DateFormatUtils.format(cal.getTime(), TIME_FORMAT); 	
	}
	
	/**
	 * ���t�̊��Ԃ��擾
	 * @param ���t�P
	 * @param ���t�Q
	 * @param �t�H�[�}�b�g
	 * @return ����
	 */
	public static long getTimeRangeNum(String date1, String date2, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date start = df.parse(date1);
		Date end = df.parse(date2);
		long l = end.getTime() - start.getTime();
		return l;
	}
	
    /**
     * ����a��ϊ��Q
     * @param   sDate ���t(YYYYMMDD)<BR>
     * @return  �a����t(�����L��YYMMDD)<BR>
     *          �G���[�̏ꍇ�́Anull��ԋp����<BR>
     * �����ԍ� �����F4<BR>
     *          �吳�F3<BR>
     *          ���a�F2<BR>
     *          �����F1<BR>
     */
    public static String chgSeirekiToWareki2( String sDate ) {
        String result = null;
        result = chgSeirekiToWareki(sDate);
        if ( result == null || "".equals(result)) {
            return "";
        }
        String gengo = result.substring(0, 1);
        String gengoKanji = "";
        // ����
        if (GENGO_HEISEI_S.equals(gengo)) {
            gengoKanji = GENGO_HEISEI_J;
        
        // ���a
        } else if (GENGO_SHOWA_S.equals(gengo)) {
            gengoKanji = GENGO_SHOWA_J;
            
        // �吳
        } else if (GENGO_TAISHO_S.equals(gengo)) {
            gengoKanji = GENGO_TAISHO_J;
            
        // ���a
        } else if (GENGO_MEIJI_S.equals(gengo)) {
            gengoKanji = GENGO_MEIJI_J;
        }
        result = gengoKanji + NumberUtil.toInt(result.substring(1, 3)) + "�N"
                            + NumberUtil.toInt(result.substring(3, 5)) + "��"
                            + NumberUtil.toInt(result.substring(5)) + "��";
        return result;
    }

    /**
     * ����a��ϊ��Q
     * @param   sDate ���t(YYYYMMDD)<BR>
     * @return  �a����t(�����L��.YY/MM/DD)<BR>
     *          �G���[�̏ꍇ�́Anull��ԋp����<BR>
     * �����ԍ� �����F4<BR>
     *          �吳�F3<BR>
     *          ���a�F2<BR>
     *          �����F1<BR>
     */
    public static String chgSeirekiToWareki( String sDate ) {
        int numDate = 0;
        try {
            numDate = Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        int nBase;
        String gengo;
        if (numDate < GENGO_DATE_MEIJI) {
            return "";
        } else if (numDate < GENGO_DATE_TAISHO) {   // ����
            nBase = 1867;
            gengo = GENGO_MEIJI_S;
        } else if (numDate < GENGO_DATE_SHOWA) {    // �吳
            nBase = 1911;
            gengo = GENGO_TAISHO_S;
        } else if (numDate < GENGO_DATE_HEISEI) {   // ���a
            nBase = 1925;
            gengo = GENGO_SHOWA_S;
        } else {                                    // ����
            nBase = 1988;
            gengo = GENGO_HEISEI_S;
        }
        
        String sYear = String.valueOf((numDate/10000) - nBase);
        if (sYear.length() < 2) {
            sYear = '0' + sYear;
        }
        return (gengo + sYear + sDate.substring(4,6) + sDate.substring(6,8));
    }
    /**
     * �a������擾����
     * @param   sDate ���t(YYYYMMDD)<BR>
     * @return  �a���<BR>
     *          �G���[�̏ꍇ�́Anull��ԋp����<BR>
     * �����ԍ� �����F4<BR>
     *          �吳�F3<BR>
     *          ���a�F2<BR>
     *          �����F1<BR>
     */
    public static String getJapanYear( String sDate ) {
        int numDate = 0;
        try {
            numDate = Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        int nBase;
        String gengo;
        String gengoKanji = "";
        if (numDate < GENGO_DATE_MEIJI) {
            return "";
        } else if (numDate < GENGO_DATE_TAISHO) {   // ����
            nBase = 1867;
            gengo = GENGO_MEIJI_S;
        } else if (numDate < GENGO_DATE_SHOWA) {    // �吳
            nBase = 1911;
            gengo = GENGO_TAISHO_S;
        } else if (numDate < GENGO_DATE_HEISEI) {   // ���a
            nBase = 1925;
            gengo = GENGO_SHOWA_S;
        } else {                                    // ����
            nBase = 1988;
            gengo = GENGO_HEISEI_S;
        }
     // ����
        if (GENGO_HEISEI_S.equals(gengo)) {
            gengoKanji = GENGO_HEISEI_J;
        
        // ���a
        } else if (GENGO_SHOWA_S.equals(gengo)) {
            gengoKanji = GENGO_SHOWA_J;
            
        // �吳
        } else if (GENGO_TAISHO_S.equals(gengo)) {
            gengoKanji = GENGO_TAISHO_J;
            
        // ���a
        } else if (GENGO_MEIJI_S.equals(gengo)) {
            gengoKanji = GENGO_MEIJI_J;
        }
        String sYear = String.valueOf((numDate/10000) - nBase);
        if (sYear.length() < 2) {
            sYear = '0' + sYear;
        }
        return gengoKanji + sYear;
    }
    /**
     * �a����擾����
     * @param   sDate ���t(YYYYMMDD)<BR>
     * @return  �a�<BR>
     *          �G���[�̏ꍇ�́Anull��ԋp����<BR>
     * �����ԍ� �����F4<BR>
     *          �吳�F3<BR>
     *          ���a�F2<BR>
     *          �����F1<BR>
     */
    public static String getJapanDate( String sDate ) {
        try {
            Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        return sDate.substring(6,8);
    }
    /**
     * �a������擾����
     * @param   sDate ���t(YYYYMMDD)<BR>
     * @return  �a���<BR>
     *          �G���[�̏ꍇ�́Anull��ԋp����<BR>
     * �����ԍ� �����F4<BR>
     *          �吳�F3<BR>
     *          ���a�F2<BR>
     *          �����F1<BR>
     */
    public static String getJapanMonth( String sDate ) {
        try {
            Integer.parseInt( sDate );
        } catch(NumberFormatException e) {
            return "";
        }
        return sDate.substring(4,6);
    }

    /**
     * �a�����ϊ��Q
     * @param src ���t(X.YY/MM/DD)
     * @return ������t<br>
     *         �G���[�̏ꍇ�́Anull��ԋp����<br>
     */
    public static String toSeireki(String src) {
        if (src==null || src.length() != 10) return "";
        String stDate = src.substring(2, 4);
        stDate += src.substring(5, 7);
        stDate += src.substring(8, 10);
        String gengo = src.substring(0,1);
        int ret = chgWarekiToSeireki(stDate, gengo);
        if (ret == -1) return "";
        return Integer.toString(ret);
    }

    /**
     * �a�����ϊ�
     * @param src �a����t(�����L��YYMMDD)
     * @return ������t<br>
     *         �G���[�̏ꍇ�́Anull��ԋp����<br>
     */
    public static String chgWarekiToSeireki(String src) {
        if ( src == null ) { return null; }
        try {
            String gengo = src.substring(0, 1);
            String kbn = "";
            if( gengo.equals(GENGO_MEIJI_S)) {
                kbn = GENGO_MEIJI;
            } else if ( gengo.equals(GENGO_TAISHO_S)) {
                kbn = GENGO_TAISHO;
            } else if ( gengo.equals(GENGO_SHOWA_S)) {
                kbn = GENGO_SHOWA;
            } else if ( gengo.equals(GENGO_HEISEI_S)) {
                kbn = GENGO_HEISEI;
            }
            String stDate = src.substring(1, src.length());
            int iDate = chgWarekiToSeireki(stDate, kbn);
            if ( iDate == -1 ) { return null; }
            return Integer.toString(iDate);
        } catch (NumberFormatException e) {
            return "";
        } catch (IndexOutOfBoundsException ie) {
            return "";
        }
    }
    
    /**
     * �a�����ϊ�
     * @param stDate ���t(YYMMDD)
     * @param gengo  ����(M,T,S,H)
     * @return ������t<br>
     *         �G���[�̏ꍇ�́A-1��ԋp����<br>
     */
    public static int chgWarekiToSeireki(int stDate, String gengo) {
        int nBase;
        int nMinLim;
        int nMaxLim;
        //������t�ɕϊ�
        if (GENGO_MEIJI.equals(gengo)) {
            nBase = 18670000;
            nMinLim = GENGO_DATE_MEIJI;
            nMaxLim = GENGO_DATE_TAISHO;
        } else if (GENGO_TAISHO.equals(gengo)) {
            nBase = 19110000;
            nMinLim = GENGO_DATE_TAISHO;
            nMaxLim = GENGO_DATE_SHOWA;
        } else if (GENGO_SHOWA.equals(gengo)) {
            nBase = 19250000;
            nMinLim = GENGO_DATE_SHOWA;
            nMaxLim = GENGO_DATE_HEISEI;
        } else if (GENGO_HEISEI.equals(gengo)) {
            nBase = 19880000;
            nMinLim = GENGO_DATE_HEISEI;
            nMaxLim = 99991231;
        } else {
            return -1;
        }
        stDate += nBase;
        if ((stDate < nMinLim) || (stDate >= nMaxLim)) {
            return -1;
        }
        return stDate;
    }

    /**
     * �a�����ϊ�
     * @param stDate ���t(YYMMDD)
     * @param gengo  ����(M,T,S,H)
     * @return ������t<br>
     *         �G���[�̏ꍇ�́A-1��ԋp����<br>
     */
    public static int chgWarekiToSeireki(String stDate, String gengo) {
        try {
            int nDate = Integer.parseInt(stDate);
            return chgWarekiToSeireki(nDate, gengo);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
	/**
	 * ���ݓ��t�������b�`���Ŏ擾����
	 * 
     * <pre>
     * ��j
     *   getNowTimeMilSec()   = 15250209
     * </pre>
	 * @return ���݂̓��t����
	 */
	public static String getNowTimeSec() {
		return getNowTimeMilSec().substring(0, 6);
	}
	
	/**
	 * ���t�̗j�����擾
	 * @param dateparam
	 * @return �j��
	 */
	public static String getDayOfWeek(String date, String format){
		Calendar calendar = toCalendar(date, format);
		// �j�����擾
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		if(week == 1){
			return "��";
		} else if(week == 2){
			return "��";
		} else if(week == 3){
			return "��";
		} else if(week == 4){
			return "��";
		} else if(week == 5){
			return "��";
		} else if(week == 6){
			return "��";
		} else if(week == 7){
			return "�y";
		}
		return "";
	}
	
	/**
	 * �J�����_�[�ɕϊ����܂��B
	 * @param val
	 * @param format
	 * @return
	 */
	public static Calendar toCalendar(String val, String format){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date;
		
		try {
			date = simpleDateFormat.parse(val);
		} catch (ParseException e) {
			throw new RuntimeException("���t�ϊ��ɗ�O���������܂����B");
		}
		
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now;
	}
	
	/**
	 * ���݂̎��Ԃ��擾���܂��B�i���t�t�B�[���h�܂܂Ȃ��j
	 * @param format
	 * @return
	 */
	public static String getNowTime(String format){
		Calendar c = Calendar.getInstance();
	    c.setTimeInMillis(new Date().getTime());
		String tiem = new SimpleDateFormat(format).format(c.getTime());
		return tiem;
	}
	
	/**
	 * ���t�̊��Ԃ��擾
	 * @param ���t�P
	 * @param ���t�Q
	 * @param �t�H�[�}�b�g
	 * @return ����
	 */
	public static String getTimeRange(String date1, String date2, String format)
			throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date start = df.parse(date1);
		Date end = df.parse(date2);
		long l = end.getTime() - start.getTime();
		long day = l / (24 * 60 * 60 * 1000);									// ��
		long hour = (l / (60 * 60 * 1000) - day * 24);							// ��
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);				// ��
		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);	// �b

		StringBuffer sb = new StringBuffer();
		sb.append(frontCompWithZore(hour, 2));
		sb.append(frontCompWithZore(min, 2));
		sb.append(frontCompWithZore(s, 2));
		return sb.toString();
	}
	
	/** 
	  * ������̑O�ɂO���Z�b�g
	  * @param sourceDate 
	  * @param formatLength 
	  * @return �O�ɂO���Z�b�g����������
	  */  
	public static String frontCompWithZore(long sourceDate, int formatLength) {
		if(sourceDate < 0){
			sourceDate = -1 * sourceDate;
		}
		String newString = String.format("%0" + formatLength + "d", sourceDate);
		return newString;
	}
	
	public static String getEigyoubi() {
		
		String eigyoubi = DateUtil.getNowDate();
		
		String nowTime = DateUtil.getNowTime("HHmmss");
		
		String eigyoubiStartTime = StringBaseUtil.zeroPadding(EnvPropertyUtil.getInstance().getPropertyString("eigyoubi.hour.start"), 2) + "0000";
		
		if (NumberUtil.toLong(eigyoubiStartTime) > NumberUtil.toLong(nowTime)) {
			eigyoubi = DateUtil.addDay(eigyoubi, -1);
		}
		return eigyoubi;
		
	}
	
	/** 
	  * ����t�̔�r
	  * @param ��r����t 
	  * @param �J�����g���t�̉��Z�l 
	  * @return boolean
	  */  
	public static boolean isOverCurrentDate(Date compareDate, int shiftDays) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

		GregorianCalendar today = new GregorianCalendar();
		today.add(GregorianCalendar.DATE, shiftDays);
		Date nextDate = today.getTime();
		String pwDate = df.format(compareDate);
		String currentDate = df.format(nextDate);
		int flag = Integer.parseInt(currentDate) - Integer.parseInt(pwDate);
		if (flag > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * ���t�̍����擾���܂��B
	 * @param day1
	 * @param day2
	 * @return
	 */
	public static int dateDiff(String day1, String day2, String format){
		Date v1 = toCalendar(day1, format).getTime();
		Date v2 = toCalendar(day2, format).getTime();
		return (int)((v2.getTime() - v1.getTime()) / (1000 * 60 * 60 * 24));
	}
}