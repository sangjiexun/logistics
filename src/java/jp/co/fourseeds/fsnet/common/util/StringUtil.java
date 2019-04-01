/**
 * File Name	: StringUtil.java
 * Created Date	: 2006/11/23
 * COPYRIGHT(c) 2006 DHC All Rights Reserved
 * DHC PROPRIETARY/CONFIDENTIAL.
 */
package jp.co.fourseeds.fsnet.common.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import jp.co.common.frame.util.StringBaseUtil;

/**
 * @author dingzw
 * @version 1.0
 * @function The string util
 */
public class StringUtil extends StringBaseUtil {

	/** Log4j logger instance */
	private static final Logger logger = Logger.getLogger(StringUtil.class);
	
	/**
	 * @created shangw 2006/11/04
	 * @modified shangw 2006/11/04
	 * @param contentC
	 *            The content C
	 * @return the string with script
	 * @function Get string with script
	 */
	public static String getStringWithScript(String contentC) {
		String content = contentC;
		if (content == null || "".equals(content)) {
			return "";
		}
		content = content.replaceAll("'", "\\\\'");
		content = content.replaceAll("\\\"", "\\\\\"");

		return content;
	}

	/**
	 * @created shangw 2006/11/04
	 * @modified shangw 2006/11/04
	 * @param contentC
	 *            The content C
	 * @return the string with HTML
	 * @function Get string with HTML
	 */
	public static String getStringWithHTML(String contentC) {
		String content = contentC;
		if (content == null) {
			return "";
		}
		content = content.replaceAll("&", "&amp;");
		content = content.replaceAll("<", "&lt;");
		content = content.replaceAll(">", "&gt;");
		content = content.replaceAll("\"", "&quot;");
		content = content.replaceAll("\n\r", "&#10;");
		content = content.replaceAll("\r\n", "&#10;");
		content = content.replaceAll("\n", "&#10;");
		content = content.replaceAll(" ", "&#032;");
		content = content.replaceAll("'", "&#039;");
		content = content.replaceAll("!", "&#033;");

		return content;
	}

	/**
	 * @created shangw 2006/11/04
	 * @modified shangw 2006/11/04
	 * @param string
	 *            The string
	 * @return the float zero
	 * @function Conversion the null to float zero
	 */
	public static String conversionNullToZero(String string) {
		if (string == null || "".equals(string) || "null".equals(string)) {
			string = "0.0";
		}

		return string;
	}

	/**
	 * @created shangw 2006/11/04
	 * @modified shangw 2006/11/04
	 * @param string
	 *            The string
	 * @return the integer zero
	 * @function Conversion the null to integer zero
	 */
	public static String conversionNullToIntegerZero(String string) {
		if (string == null || "".equals(string) || "null".equals(string)) {
			string = "0";
		}

		return string;
	}

	/**
	 * @created dingzw 2006/11/01
	 * @modified dingzw 2006/11/01
	 * @param header
	 *            The header to append
	 * @param value
	 *            The value
	 * @param trailer
	 *            The trailer to append
	 * @return the sql value
	 * @function Get the sql value
	 */
	public static String getSQLValue(String header, String value, String trailer) {
		if (value != null) {
			value = "'" + nullToBlank(header) + value
					+ nullToBlank(trailer) + "'";
		}

		return value;
	}

	/**
	 * @created dingzw 2006/11/06
	 * @modified dingzw 2006/11/06
	 * @param date
	 *            The date object
	 * @param format
	 *            The date's format
	 * @return the formated date
	 * @function Format the date
	 */
	public static String formatTheDate(Date date, String format) {
		String newDate = "";

		if (date != null) {
			SimpleDateFormat formater = new SimpleDateFormat(format);
			newDate = formater.format(date);
		}

		return newDate;
	}

	/**
	 * @created dingzw 2006/11/06
	 * @modified dingzw 2006/11/06
	 * @param date
	 *            The date
	 * @param format
	 *            The date format
	 * @return the parsed date
	 * @throws ParseException
	 * @function Parse the date
	 */
	public static Date parseTheDate(String date, String format)
			throws ParseException {
		Date newDate = null;

		if (date != null) {
			SimpleDateFormat formater = new SimpleDateFormat(format);
			newDate = formater.parse(date);
		}

		return newDate;
	}

	/**
	 * @created dingzw 2006/11/06
	 * @modified dingzw 2006/11/06
	 * @param format
	 *            The date format
	 * @return the formated now date
	 * @throws ParseException
	 * @function Get the now date by specify date format
	 */
	public static Date getNowDate(String format) throws ParseException {
		Date nowDate = new Date();

		nowDate = parseTheDate(formatTheDate(nowDate, format), format);

		return nowDate;
	}

	/**
	 * @created shangw 2006/11/06
	 * @modified shangw 2006/11/06
	 * @param string
	 *            The string
	 * @return the trimed string
	 * @function Trim double-byte space
	 */
	public static String trimDoubleByteSpace(String string) {
		/*
		 * if (!(string == null || "".equals(string) || "null" == string ||
		 * "null" .equals(string))) { string = string.replaceAll(" ", "
		 * ").trim(); string = string.replaceAll(" ", " "); } return string;
		 */
		String stringCopy = nullToBlank(string);

		int strLen = stringCopy.length();
		int indx = 0;
		for (indx = 0; indx < strLen; indx++) {
			if (!"�@".equals(stringCopy.substring(indx, indx + 1))) {
				break;
			}
		}
		String leftString = stringCopy.substring(indx, strLen);
		int leftStrLen = leftString.length();
		indx = 0;
		for (indx = leftStrLen - 1; indx >= 0; indx--) {
			if (!"�@".equals(leftString.substring(indx, indx + 1))) {
				break;
			}
		}
		String leftRightString = leftString.substring(0, indx + 1);

		return leftRightString;
	}

	/**
	 * @created shangw 2006/11/06
	 * @modified shangw 2006/11/06
	 * @param string
	 *            The string
	 * @return the correct date string
	 * @function Get the correct date string
	 */
	public static String formatDateString(String date) {
		date = date.replaceAll("�@", " ").trim();
		if (!"".equals(date)) {
			if (date.length() == 6) {
				date = date.substring(0, 2) + "/" + date.substring(2, 4) + "/"
						+ date.substring(4, 6);
			} else if (date.length() == 8) {
				date = date.substring(0, 2) + "/" + date.substring(3, 5) + "/"
						+ date.substring(6, 8);
			}
			try {
				Date dateTemp = parseTheDate(date, "yy/MM/dd");
				if (dateTemp != null) {
					SimpleDateFormat formater = new SimpleDateFormat(
							"yyyy/MM/dd");
					date = formater.format(dateTemp);
				}
			} catch (ParseException e) {
				logger.warn(e.getMessage());
			}
		}
		return date;
	}
	
	/**
	 * 
	 * @param date:20080101
	 * @return date:2008/01/01
	 */
	public static String formatString(String date) {
		if (!isEmpty(date)) {
			if (date.length() == 8) {
				date = date.substring(0, 4) + "/" + date.substring(4, 6) + "/"
						+ date.substring(6, 8);
			}
		}
		return date;
	}

	/**
	 * @created shangw 2006/12/06
	 * @modified shangw 2006/12/06
	 * @param string
	 *            The string
	 * @return the correct date string
	 * @function Get the correct date string
	 */
	public static String convertDateString(String date) {
		date = StringUtil.nullToBlank(date);
		if (!"".equals(date.trim())) {
			try {
				Date dateTemp = parseTheDate(date, "yyyy/MM/dd");
				if (dateTemp != null) {
					SimpleDateFormat formater = new SimpleDateFormat("yyMMdd");
					date = formater.format(dateTemp);
				}
			} catch (ParseException e) {
				logger.warn(e.getMessage());
			}
		}
		return date;
	}

	/**
	 * @created shangw 2006/12/06
	 * @modified shangw 2006/12/06
	 * @param numStr
	 *            The number string
	 * @return the integer string
	 * @function Get integer string
	 */
	public static String resetNumberString(String numStr) {
		String numStrC = StringUtil.cancelNumberString(numStr);
		NumberFormat numFormat = NumberFormat.getIntegerInstance();
		long num = Long.parseLong(numStrC);
		return numFormat.format(num);
	}

	/**
	 * @created shangw 2006/12/06
	 * @modified shangw 2006/12/06
	 * @param numStr
	 *            The number string
	 * @return the integer string
	 * @function Get integer string
	 */
	public static String resetNumberStringX(String numStr) {
		String numStrC = numStr;
		numStrC = StringUtil.nullToBlank(numStrC);
		if ("".equals(numStrC)) {
			return numStrC;
		}
		numStrC = numStrC.replaceAll(",", "");
		NumberFormat numFormat = NumberFormat.getIntegerInstance();
		long num = Long.parseLong(numStrC);
		return numFormat.format(num);
	}

	/**
	 * @created shangw 2006/12/06
	 * @modified shangw 2006/12/06
	 * @param numStr
	 *            The number string
	 * @return the pure number string
	 * @function Get pure number string
	 */
	public static String cancelNumberString(String numStr) {
		String numStrC = numStr;
		numStrC = StringUtil.conversionNullToIntegerZero(numStrC);
		if ("0".equals(numStrC)) {
			return numStrC;
		}
		numStrC = numStrC.replaceAll(",", "");
		return numStrC;
	}

	/**
	 * @created dingzw 2007/04/13
	 * @modified dingzw 2007/04/13
	 * @param list
	 *            The list sql
	 * @return The list sql
	 * @function Get the list sql.
	 */
	public static String getListSql(String[] list) {
		StringBuffer listSql = new StringBuffer();
		listSql.append("(");

		if (list == null || list.length == 0) {
			listSql.append(" null ");
			listSql.append(",");

		} else {
			for (int i = 0; i < list.length; i++) {
				listSql.append("'");
				listSql.append(StringUtil.nullToBlank(list[i]));
				listSql.append("'");
				listSql.append(",");
			}
		}

		listSql.deleteCharAt(listSql.length() - 1);
		listSql.append(")");

		return listSql.toString();
	}
	/**
	 * �X�g�����O�J�b�g�p�֐�
	 * 2009/5/14 �ǉ��@
	 * �ۑ�Ή��FWEB-004-001
	 * @param text
	 *            ���X�g�����O
	 * @param textMaxChar
	 *            ����
	 * @author �v
	 * @return
	 */
	public static String CutString(String text, int textMaxChar) {
		int size, index;
		String[] returnStringArray = new String[2];

		if (textMaxChar <= 0) {
			returnStringArray[0] = text;
			returnStringArray[1] = null;
		} else {
			for (size = 0, index = 0; index < text.length()
					&& size < textMaxChar; index++) {
				size += text.substring(index, index + 1).getBytes().length;
			}
			returnStringArray[0] = text.substring(0, index);
			returnStringArray[1] = text.substring(index);
		}

		return returnStringArray[0];
	}

///////////////////////////////�����ȉ��͊�����SearchService����R�s�[�V�K�쐬/////////////////////////////////////////
	private static final char[] HANKAKU_KATAKANA = { '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
			'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
			'�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�', '�',
			'�', '�', '�' };

	private static final char[] ZENKAKU_KATAKANA = { '��', '�@', '�B', '�D', '�F', '�H', '��', '��', '��', '�b', '�[', '�A', '�C',
			'�E', '�G', '�I', '�J', '�L', '�N', '�P', '�R', '�T', '�V', '�X', '�Z', '�\', '�^', '�`', '�c', '�e', '�g', '�i', '�j', '�k',
			'�l', '�m', '�n', '�q', '�t', '�w', '�z', '�}', '�~', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��', '��',
			'��', '�J', '�K' };

	private static final char HANKAKU_KATAKANA_FIRST_CHAR = HANKAKU_KATAKANA[0];

	private static final char HANKAKU_KATAKANA_LAST_CHAR = HANKAKU_KATAKANA[HANKAKU_KATAKANA.length - 1];

	/**
	 * ���p�J�^�J�i����S�p�J�^�J�i�֕ϊ����܂��B
	 * 
	 * @param c
	 *            �ϊ��O�̕���
	 * @return �ϊ���̕���
	 */
	public static char hankakuKatakanaToZenkakuKatakana(char c) {
		if (c >= HANKAKU_KATAKANA_FIRST_CHAR && c <= HANKAKU_KATAKANA_LAST_CHAR) {
			return ZENKAKU_KATAKANA[c - HANKAKU_KATAKANA_FIRST_CHAR];
		} else {
			return c;
		}
	}

	/**
	 * 2�����ڂ����_�E�����_�ŁA1�����ڂɉ����邱�Ƃ��ł���ꍇ�́A��������������Ԃ��܂��B �������ł��Ȃ��Ƃ��́Ac1��Ԃ��܂��B
	 * 
	 * @param c1
	 *            �ϊ��O��1������
	 * @param c2
	 *            �ϊ��O��2������
	 * @return �ϊ���̕���
	 */
	public static char mergeChar(char c1, char c2) {
		if (c2 == '�') {
			if ("��������������������".indexOf(c1) > -1) {
				switch (c1) {
				case '�':
					return '�K';
				case '�':
					return '�M';
				case '�':
					return '�O';
				case '�':
					return '�Q';
				case '�':
					return '�S';
				case '�':
					return '�U';
				case '�':
					return '�W';
				case '�':
					return '�Y';
				case '�':
					return '�[';
				case '�':
					return '�]';
				case '�':
					return '�_';
				case '�':
					return '�a';
				case '�':
					return '�d';
				case '�':
					return '�f';
				case '�':
					return '�h';
				case '�':
					return '�o';
				case '�':
					return '�r';
				case '�':
					return '�u';
				case '�':
					return '�x';
				case '�':
					return '�{';
				}
			}
		} else if (c2 == '�') {
			if ("�����".indexOf(c1) > -1) {
				switch (c1) {
				case '�':
					return '�p';
				case '�':
					return '�s';
				case '�':
					return '�v';
				case '�':
					return '�y';
				case '�':
					return '�|';
				}
			}
		}
		return c1;
	}

	public static char mergeChar1(char c1) {
		if (c1 >= 'A' || c1 >= '�`') {
			if ("ADNORT�`�c�m�n�q�s".indexOf(c1) > -1) {
				switch (c1) {
				case 'A':
					return 'a';
				case 'D':
					return 'd';
				case 'N':
					return 'n';
				case 'O':
					return 'o';
				case 'R':
					return 'r';
				case 'T':
					return 't';
				case '�`':
					return 'a';
				case '�c':
					return 'd';
				case '�m':
					return 'n';
				case '�n':
					return 'o';
				case '�q':
					return 'r';
				case '�s':
					return 't';
				}
			}
		}
		return c1;
	}

	/**
	 * �����񒆂̔��p�J�^�J�i��S�p�J�^�J�i�ɕϊ����܂��B
	 * 
	 * @param s
	 *            �ϊ��O������
	 * @return �ϊ��㕶����
	 */
	public static String hankakuKatakanaToZenkakuKatakana(String s) {
		if (s.length() == 0) {
			return s;
		} else if (s.length() == 1) {
			return hankakuKatakanaToZenkakuKatakana(s.charAt(0)) + "";
		} else {
			StringBuffer sb = new StringBuffer(s);
			int i = 0;
			for (i = 0; i < sb.length() - 1; i++) {
				char originalChar1 = sb.charAt(i);
				char originalChar2 = sb.charAt(i + 1);
				char margedChar = mergeChar(originalChar1, originalChar2);
				char margedChar1 = mergeChar1(originalChar1);
				if (margedChar != originalChar1) {
					sb.setCharAt(i, margedChar);
					sb.deleteCharAt(i + 1);
				} else if (margedChar1 != originalChar1) {
					sb.setCharAt(i, margedChar1);
				} else {
					char convertedChar = hankakuKatakanaToZenkakuKatakana(originalChar1);
					if (convertedChar != originalChar1) {
						sb.setCharAt(i, convertedChar);
					}
				}
			}
			if (i < sb.length()) {
				char originalChar1 = sb.charAt(i);
				char margedChar1 = mergeChar1(originalChar1);
				char convertedChar = hankakuKatakanaToZenkakuKatakana(originalChar1);
				if (convertedChar != originalChar1) {
					sb.setCharAt(i, convertedChar);
				} else if (margedChar1 != originalChar1) {
					sb.setCharAt(i, margedChar1);
				}
			}
			return sb.toString();
		}

	}
	
	/**
	 * 
	 * @param hhmm:1800 900
	 * @return 18:00 9:00
	 */
	public static String timeFormat(String hhmm){
		if (!isEmpty(hhmm) && hhmm.length() >= 2 && hhmm.length() <= 4 && hhmm.indexOf(":") < 0) {
			return hhmm.substring(0, hhmm.length()-2) + ":" + hhmm.substring(hhmm.length()-2, hhmm.length());
		}
		return nullToBlank(hhmm);
	}
}
