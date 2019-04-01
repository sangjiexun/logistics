package jp.co.common.frame.util;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

/**
 *  ������ҏW�p�N���X
 * <pre>
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class StringBaseUtil extends StringUtils {

    /**
     * �k���l���Z���ɕύX����
     * 
     * <pre>
     * ��j
     * StringUtil.nullToBlank(null)  = ""
     * StringUtil.nullToBlank("")  = ""
     * StringUtil.nullToBlank(" ")  = ""
     * </pre>
     * 
     * �ΏہFCHAR�^�e�L�X�g�{�b�N�X
     * @param strValue
     * @return strValue
     */     
    public static String nullToBlank(String strValue) {
       if (StringBaseUtil.isEmpty(strValue)) {
            strValue  = "";
       }else {
            strValue  = strValue.trim();
       }
       return strValue;
   }

    /**
     * �����Ώە�����NULL�܂��́h�h���ǂ����`�F�b�N����
     * 
     * <pre>
     * ��j
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
     * @param obj�@�`�F�b�N�Ώ�
     * @return �u�����N�ꍇtrue�A�u�����N�ł͂Ȃ��ꍇfalse
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * �����Ώە����񂪃u�����N���ǂ����`�F�b�N����
     * 
     * <pre>
     * ��j
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     * @param obj�@�`�F�b�N�Ώ�
     * @return �u�����N�ꍇtrue�A�u�����N�ł͂Ȃ��ꍇfalse
     */
     public static boolean isBlank(Object obj){
          if(obj == null){
               return true;
          }
          return isBlank(obj.toString());
     }
     
     
    /**
     * �����Ώە����񂪎w�茅�ȓ����ǂ����`�F�b�N����
     * (2byte���l�����Ȃ�)
     * 
     * <pre>
     * ��j
     * StringUtils.isUnderLength("bob", 1)      = false
     * StringUtils.isUnderLength("bob", 3)      = true
     * StringUtils.isUnderLength("bob", 5)      = true
     * </pre>
     * @param input
     * @param length �w�茅

     * @return ���ȓ��̏ꍇtrue�A�w�茅�ȏ�̏ꍇfalse
     */
    public static boolean isUnderLength(String input, int length) {
        if (input.length() <= length)
            return true;
        else
            return false;
    }

    /**
     * �����Ώە����񂪎w�茅�ȓ����ǂ����`�F�b�N����
     * (2byte���l��)
     * 
     * <pre>
     * ��j
     * StringUtils.isUnderLengthMulti("�e�X�g", 5)      = false
     * StringUtils.isUnderLengthMulti("�e�X�g", 6)      = true
     * StringUtils.isUnderLengthMulti("�e�X�g", 10)      = true
     * </pre>
     * @param input
     * @param length �w�茅

     * @return ���ȓ��̏ꍇtrue�A�w�茅�ȏ�̏ꍇfalse
     */
    public static boolean isUnderLengthMulti(String input, int length) {
        byte[] bytes = input.getBytes();
        if (bytes.length <= length)
            return true;
        else
            return false;
    }
     
     /**
     * ������̌���u�����N����������
     * 
     * <pre>
     * ��j
     * StringUtils.rtrim("")          = ""
     * StringUtils.rtrim("bob ")     = "bob"
     * StringUtils.rtrim(" bob")    = " bob"
     * </pre>
     * @param input ���͕�����
     * @return ����u�����N���������ꂽ������
     */
    public static String rtrim(String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = input.length();
        while ((0 < length) && input.charAt(length - 1) == ' ') {
            length--;
        }
        return (length < input.length()) ? input.substring(0, length) : input;
    }
    
    /**
     * ������̌���S�p�u�����N����������
     * 
     * <pre>
     * ��j
     * StringUtils.rtrim("�@")          = ""
     * StringUtils.rtrim("bob�@ ")     = "bob"
     * StringUtils.rtrim(" bob")    = " bob"
     * </pre>
     * @param input ���͕�����
     * @return ����u�����N���������ꂽ������
     */
    public static String rlettertrim(String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = input.length();
        while ((0 < length) &&( "�@".equals(String.valueOf(input.charAt(length - 1))) ||( " ".equals(String.valueOf(input.charAt(length - 1)))))) {
            length--;
        }
        return (length < input.length()) ? input.substring(0, length) : input;
    }
    /**
     * ������̑O���u�����N����������
     * 
     * <pre>
     * ��j
     * StringUtils.ltrim("")          = ""
     * StringUtils.ltrim("bob ")     = "bob "
     * StringUtils.ltrim(" bob")    = "bob"
     * </pre>
     * @param input ���͕�����
     * @return �O���u�����N���������ꂽ������
     */
    public static String ltrim(String input){
         return ltrimer(' ', input);
    }
    
    /**
     * ������̑O���ɂ���Ώە�������������
     * 
     * <pre>
     * ��j
     * StringUtils.ltrimer('|', "")          = ""
     * StringUtils.ltrimer('|', "bob|")     = "bob|"
     * StringUtils.ltrimer('|', "||bob")    = "bob"
     * StringUtils.ltrimer('|', "||bo|b")    = "bo|b"
     * </pre>
     * @param input ���͕�����
     * @return �O���u�����N���������ꂽ������
     */
    private static String ltrimer(char target, String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = 0;
        while ((length < input.length()) && input.charAt(length) == target) {
            length++;
        }
        return (length > 0 ) ? input.substring(length, input.length()) : input ;
    }
    
     /**
      * ��������L�[�����Ƀt�H�[�}�b�g�ʂ�Ƀt�H�[�}�b�g����
      * (TestCode/S-PartNo/C-PartNo�t�H�[�}�b�g�p�Ɏg�p����)
      * 
      * <pre>
      * ��j
      * strFormat("123456789", "111,111,11", ',')          = "123,456,78"
      * </pre>
      * @param param �ϊ��Ώە�����
      * @param format �t�H�[�}�b�g�f�[�^
      * @param splitKey �t�H�[�}�b�g�ɑ΂��镪��������
      * @return
      */
    public static String strFormat(String param, String format, char splitKey) {
          if (param == null || param.equals(EMPTY)) {
               return EMPTY;
          }
          String[] splitRst = StringUtils.split(format, splitKey);
          StringBuffer buf = new StringBuffer();
          int index = 0;

          for (int i = 0; i < splitRst.length; i++) {
               if(param.length() <= (splitRst[i].length() + index)){
                    buf.append(param.substring(index, param.length()));
                    break;
               }
               
               buf.append(param.substring(index, splitRst[i].length() + index));
               if (i != splitRst.length - 1) {
                    buf.append(splitKey);
               }
               index = index + splitRst[i].length();
          }
          return buf.toString();
     }

    /**
     * ���ꕶ���̃t�B���^�@�i<>&"\' �j
     * 
     * <pre>
     * ��j
     * StringUtils.filter("<bob>")          = "&lt;bob&gt;"
     * StringUtils.filter("b&o"b")     = "b&amp;o&quot;b"
     * </pre>
     * @param value
     * @return String
     */
    public static String filter(String value) {
        if (value == null || value.length() == 0) {
            return value;
        }
        StringBuffer result = null;
        String filtered = null;
        for (int i = 0; i < value.length(); i++) {
            filtered = null;
            switch (value.charAt(i)) {
                case '<':
                    filtered = "&lt;";
                    break;
                case '>':
                    filtered = "&gt;";
                    break;
                case '&':
                    filtered = "&amp;";
                    break;
                case '"':
                    filtered = "&quot;";
                    break;
                case '\'':
                    filtered = "&#39;";
                    break;
                case ' ':
                     filtered = "&nbsp;";
                     break;
            }

            if (result == null) {
                if (filtered != null) {
                    result = new StringBuffer(value.length() + 50);
                    if (i > 0) {
                        result.append(value.substring(0, i));
                    }
                    result.append(filtered);
                }
            } else {
                if (filtered == null) {
                    result.append(value.charAt(i));
                } else {
                    result.append(filtered);
                }
            }
        }
        return result == null ? value : result.toString();
    }
    
    /**
      * �������̕����񂪁A��������int�^�Ŏw�肳�ꂽ�o�C�g�ȏ�̒��������ꍇ��
      * �]���ȕ������폜���郁�\�b�h
      * 
      * <pre>
      * ��j
      * StringUtils.deleteFromTail("123456789", 10)     = "123456789"
      * StringUtils.deleteFromTail("123456789", 5)     = "12345"
      * </pre>
      * @param          String               name               ������
      * @param          int                    maxLength          �ő�o�C�g������������
      * @return          String               nayoseName          �����񂩂�]���ȕ�������폜����������
     */
     public static String deleteFromTail(String value, int maxLength) {
          // ������null�̏ꍇ�A���̂܂ܕԂ�
        if (value == null || value.length() == 0) {
            return value;
        }

          // ������̐��`���s��
          StringBuffer strBuf = new StringBuffer(value);

          // �������Ŏw�肳�ꂽ�ȏ�̗]���ȕ�������폜����
          if (strBuf.length() > maxLength) {
               strBuf = strBuf.delete(maxLength,strBuf.length());
          }

          return strBuf.toString();
     }

    /**
     * �w�肳�ꂽ��������w�肳�ꂽ��؂蕶���ŕ�������
     *
     * <pre>
     * ��j
     * split(null, *)         = null
     * split("", *)           = []
     * split("a.b.c", '.')    = ["a", "b", "c"]
     * split("a..b.c", '.')   = ["a", "b", "c"]
     * split("a:b:c", '.')    = ["a:b:c"]
     * split("a\tb\nc", null) = ["a", "b", "c"]
     * split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     *
     * @param str            ��͕�����
     * @param separatorChar  ������
     * @return ������z��
     */
    public static String[] split(String str, char separatorChar) {
        return StringUtils.split(str, separatorChar);
    }
    
    public static String[] split(String str, String separatorChar) {
        return StringUtils.splitByWholeSeparator(str, separatorChar);
    }
    /**
     * �w�肳�ꂽ��������w�肳�ꂽ��؂蕶���ŕ�������
     *
     * <pre>
     * ��j
     * splitPreserveAllTokens(null, *)         = null
     * splitPreserveAllTokens("", *)           = []
     * splitPreserveAllTokens("a.b.c", '.')    = ["a", "b", "c"]
     * splitPreserveAllTokens("a..b.c", '.')   = ["a", "", "b", "c"]
     * splitPreserveAllTokens("a:b:c", '.')    = ["a:b:c"]
     * splitPreserveAllTokens("a\tb\nc", null) = ["a", "b", "c"]
     * splitPreserveAllTokens("a b c", ' ')    = ["a", "b", "c"]
     * splitPreserveAllTokens("a,b,c,", ',')    = ["a", "b", "c", ""]
     * </pre>
     *
     * @param str            ��͕�����
     * @param separatorChar  ������
     * @return ������z��
     */
    public static String[] splitPreserveAllTokens(String str, char separatorChar) {
        return StringUtils.splitPreserveAllTokens(str, separatorChar);
    }

    /**
     * �w�肳�ꂽ�z��I�u�W�F�N�g�̕�����\�����w�肳�ꂽ��؂蕶��������ŘA������
     *
     * <pre>
     * ��j
     * join(null, *)                = null
     * join([], *)                  = ""
     * join(["a", "b", "c"], "--")  = "a--b--c"
     * join(["a", "b", "c"], null)  = "abc"
     * join(["a", "b", "c"], "")    = "abc"
     * join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  �A������悤�z��

     * @param separator  �A��������
     * @return �A�ڂ���镶����
     */
    public static String join(Object[] array, String separator) {
        return StringUtils.join(array, separator);
    }
    
    /**
     * �w�肳�ꂽ�z��I�u�W�F�N�g�̕�����(""��null�ȊO)�\�����w�肳�ꂽ��؂蕶��������ŘA������
     *
     * <pre>
     * ��j
     * join(null, *)                = null
     * join([], *)                  = ""
     * join(["a", "b", "c"], "--")  = "a--b--c"
     * join(["a", "b", "c"], null)  = "abc"
     * join(["a", "b", "c"], "")    = "abc"
     * join([null, "", "a"], ',')   = ",,a"
     * </pre>
     *
     * @param array  �A������悤�z��

     * @param separator  �A��������
     * @return �A�ڂ���镶����
     */
    public static String joinExceptSpace(Object[] array, String separator) {
        String retStr = "";
        for (int i=0; i < array.length; i++) {
            if (array[i] == null || "".equals(array[i])) {
                continue;
            } else {
                retStr = retStr + array[i] + separator;
            }
        }
        if (retStr.length() < 1) {
            return retStr;
        }
        if (separator.equals(retStr.substring(retStr.length()-1, retStr.length()))) {
            retStr = retStr.substring(0,retStr.length()-1);
        }
        return retStr;
    }

    /**
     * �w�肳�ꂽIterator�I�u�W�F�N�g�̕�����\�����w�肳�ꂽ��؂蕶��������ŘA������
     *
     * <pre>
     * ��j
     * join(Object[] array, String separator)�̃T���v�����Q�Ƃ���
     * </pre>
     *
     * @param iterator  �A������悤�z��

     * @param separator  �A��������
     * @return �A�ڂ���镶����
     */
	@SuppressWarnings("rawtypes")
	public static String join(Iterator iterator, String separator) {
        return StringUtils.join(iterator, separator);
    }

    /**
     * �肳�ꂽ�����񒆂̏����������ׂđ啶���ɂ���
     *
     * <pre>
     * ��j
     * StringUtil.upperCase(null)  = null
     * StringUtil.upperCase("")    = ""
     * StringUtil.upperCase("aBc") = "ABC"
     * </pre>
     *
     * @param str
     * @return the upper cased String, 
     */
    public static String upperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    /**
     * �w�肳�ꂽ�����񒆂̑啶�������ׂď������ɂ���
     *
     * <pre>
     * ��j
     * StringUtil.lowerCase(null)  = null
     * StringUtil.lowerCase("")    = ""
     * StringUtil.lowerCase("aBc") = "abc"
     * </pre>
     *
     * @param str  the String to lower case, may be null
     * @return the lower cased String
     */
    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * �w�茅���ɒB����܂őO�[���ҏW���܂��B
     * <pre>
     * ��j
     * StringUtil.zeroPadding(null,3)  = "000"
     * StringUtil.defaultString("1",3) = "001"
     * StringUtil.defaultString("1234",3) = "1234"
     * </pre>
     * @param argString     ������
     * @param argWidth     �w�茅��
     * @return �O�[���ҏW���ꂽ������
     */
    public static String zeroPadding(String argString, int argWidth) {
          if (argString == null) argString = "";
        if (argString.length() < argWidth) {
            for (int i = argString.length(); i < argWidth; i++) {
              argString = "0" + argString;
            }
        }
        return argString;
    }
    
    /**
     * �w�肳�ꂽ�J�������̒l��HTML�`�����擾���܂��B
     * 
     * @param colName �J������
     * @return �w�肳�ꂽ�J�������̒l��HTML�`��
     */
    public static String getColumnValueHTML(String colName) {

        if (isBlank(colName)) {
             return "&nbsp;";
        }
        if (colName.indexOf("\"") >= 0) {
            colName = colName.replaceAll("\"", "&quot;");
        }
        if (colName.indexOf("<") >= 0) {
            colName = colName.replaceAll("<", "&lt;");
        }
        if (colName.indexOf(">") >= 0) {
            colName = colName.replaceAll(">", "&gt;");
        }
        if (colName.indexOf("\n") >= 0) {
            colName = colName.replaceAll("\n", "<br>");
        }
        
        return colName;
    }
    
    /**
     * ��������R���J���}��؂肵�܂��B
     * <pre>
     * ��j
     * StringUtil.insComma("123")  = "123"
     * StringUtil.insComma("1234")  = "1,234"
     * StringUtil.insComma("1234567")  = "1,234,567"
     * StringUtil.insComma("-900")  = "-900"
     * StringUtil.insComma("-20000")  = "-20,000"
     * StringUtil.insComma("-1000")  = "-1,000"
     * </pre>
     * @param val ������
     * @return �R���J���}��؂肳�ꂽ������
     * @return �O�[���ҏW���ꂽ������
     */    
    public static String  insComma(String val) {
        String strBegin = "";
        if (!isEmpty(val) && val.charAt(0) == '-') {
             strBegin = "-";
             val = val.substring(1);
        }
        byte wk[] = val.getBytes();
        StringBuffer ret = new StringBuffer();
        int cnt = 0;
        for(int lp = wk.length - 1; lp >= 0 ; lp--) {
            if((cnt != 0) && (cnt % 3) == 0) {
                ret.insert(0, ',');
            }
            ret.insert(0, (char)wk[lp]);
            cnt++;
        }
        return strBegin + ret.toString();
    }

    /**
     * ������̌���ɂ���Ώە�������������
     * 
     * <pre>
     * ��j
     * StringUtils.rtrimer('|', "")          = ""
     * StringUtils.rtrimer('|', "|bob")     = "|bob"
     * StringUtils.rtrimer('|', "bob||")    = "bob"
     * StringUtils.rtrimer('|', "bo|b||")    = "bo|b"
     * </pre>
     * @param input ���͕�����
     * @return �O���u�����N���������ꂽ������
     */
    public static String rtrimer(char target, String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        int length = input.length();
        while ((length > 0) && input.charAt(length -1) == target) {
            length--;
        }
        return (length < input.length()) ? input.substring(0, length) : input ;
    }

    /**
     * ������̑O���ƌ���ɂ���Ώە�������������
     * 
     * <pre>
     * ��j
     * StringUtils.lrtrimer('|', "")          = ""
     * StringUtils.lrtrimer('|', "|bob|")     = "bob"
     * StringUtils.lrtrimer('|', "||bob||")    = "bob"
     * StringUtils.lrtrimer('|', "||bo|b||")    = "bo|b"
     * </pre>
     * @param input ���͕�����
     * @return �O���u�����N���������ꂽ������
     */
    public static String trimer(char target, String input) {
        if (input == null || input.equals(EMPTY)){
            return EMPTY;
        }
        return ltrimer(target, rtrimer(target, input));
    }
    
    /**
     * ������ɂ���Ώە�������������
     * 
     * <pre>
     * ��j
     * StringUtils.alltrimer({"|","."}, "")          = ""
     * StringUtils.alltrimer({"|","."}, "|b.o.b|")     = "bob"
     * StringUtils.alltrimer({"|","."}, "||b.o.b||")    = "bob"
     * StringUtils.alltrimer({"|","."}, "||b.o|b.||")    = "bob"
     * </pre>
     * @param �폜���Ȃ���΂Ȃ�Ȃ�������
     * @param input ���͕�����
     * @return �Ώە������������ꂽ������
     */
    public static String alltrimer(String[] target,String input){
         for(int i = 0 ;i < target.length;i++){
              input = input.replace(target[i], "");
         }
         return input;
    }
    
    /**
     * ������ɂ���Ώە�������������
     * 
     * <pre>
     * ��j
     * StringUtils.allchange({"��","��"},{"��","��"},"")          = ""
     * StringUtils.allchange({"��","��"},{"��","��"},"���������")     = "���������"
     * </pre>
     * @param target �ϊ����Ȃ���΂Ȃ�Ȃ�������
     * @param src �ϊ�������
     * @param input ���͕�����
     * @return �Ώە������ϊ����ꂽ������
     */
    public static String allchange(String[] target,String[] src,String input){
         for(int i = 0 ;i < target.length;i++){
              input = input.replace(target[i], src[i]);
         }
         return input;
    }
    
    /**
     * �w�茅���ɒB����܂őO�[���ҏW���܂��B
     * <pre>
     * ��j
     * StringUtil.zeroPadding(null,3,"A")  = "AAA"
     * StringUtil.defaultString("1",3,"A") = "AA1"
     * StringUtil.defaultString("1234",3,"A") = "1234"
     * </pre>
     * @param argString     ������
     * @param argWidth     �w�茅��
     * @param target          �w�蕶����
     * @return �O�[���ҏW���ꂽ������
     */
    public static String charPadding(String argString, int argWidth, String target) {
          if (argString == null) argString = "";
        if (argString.length() < argWidth) {
            for (int i = argString.length(); i < argWidth; i++) {
              argString = String.valueOf(target) + argString;
            }
        }
        return argString;
    }    

    /**
     * ������̑O���ƌ���ɂ��锼�p�X�y�[�X�ƑS�p�X�y�[�X����������
     * 
     * <pre>
     * ��j
     * StringUtil.allBlanktrim(null)  = ""
     * StringUtil.allBlanktrim("")  = ""
     * StringUtil.allBlanktrim(" ")  = ""
     * StringUtil.allBlanktrim("�@")  = ""
     * StringUtil.allBlanktrim("�@a�@a ")  = a�@a
     * StringUtil.allBlanktrim("�@a a ")  = a a
     * </pre>
     * 
     * @param strValue
     * @return strValue
     */     
    public static String allBlankTrim(String strValue) {
        if (StringBaseUtil.isEmpty(strValue)) {
             strValue = "";
        }else {
             strValue = trimer('�@', strValue.trim()).trim();
        }
        return strValue;
    }
     
     /**
      * �k���l���Z���ɕύX����
      * �ΏہF���ׂĂ̐��l�^,�t���O�A�`�F�b�N�{�b�N�X
      * 
      * StringUtil.nullToZero(null)  = "0"
      * StringUtil.nullToZero("")  = "0"
      * StringUtil.nullToZero(" ")  = "0"
      * StringUtil.nullToZero("1 ")  = "1"
      * 
      * @param strValue
      * @return strValue
      */
     public static String nullToZero(String strValue) {
          if (strValue == null || strValue.trim().length() == 0) {
               strValue = "0";
          } else {
               strValue = strValue.trim();
          }
          return strValue;
     }
     
     /**
      * '' ���� ��null�ꍇ�A""��ݒ肷��B
      * 
      * StringUtil.setTimeDefalutVal(null)  = ""
      * StringUtil.setTimeDefalutVal("")  = ""
      * StringUtil.setTimeDefalutVal("111")  = "111"
      * 
      * @param strValue
      * @return strValue
      */
     public static String setTimeDefalutVal(String strValue) {
        if (StringBaseUtil.isEmpty(strValue)) {
             strValue  = "";
        }
        return strValue;
    }
     
     /**
      * ������̐��l�������w�茅�����J�E���g���č̔Ԃ���B
     * <pre>
     * ��j
     * ���͕�����:"09", �w�茅��:"1", �̔ԑΏە����̌����F"2" ->  ���H��̐��l:"10"
     * ���͕�����:"001", �w�茅��:"2", �̔ԑΏە����̌����F"1" ->  ���H��̐��l:"003"
     * ���͕�����:"A0008", �w�茅��:"1", �̔ԑΏە����̌����F"4" ->  ���H��̐��l:"A0009"
     * </pre>
      * @param strValue ���͕�����
      * @param index �w�茅��
      * @param size �̔ԑΏە����̌����i���͕�����̌�납��̌����j
      * @return retStr�@���H��̐��l
      */
     public static String getSaibanNO(String strValue, int index, int size) {
 		String retStr = strValue;
    	int length = strValue.length();
    	if (!StringBaseUtil.isEmpty(strValue) && length >= size) {
    		int iParam = 0;
    		try{
        		if(length == 1){
        			iParam = Integer.parseInt(strValue);
        			retStr = "";
        		} else {
        			retStr = strValue.substring(0, length - size);
        			iParam = Integer.parseInt(strValue.substring(length - size));
        		}
        		for(int i=0; i<index; i++){
        			iParam += 1;
        		}
        		retStr += StringBaseUtil.zeroPadding(String.valueOf(iParam), size);
	       		if(retStr.length() > length){
	       			retStr = strValue;
	       		}
    		} catch (Exception e) {
    			return strValue;
    		}
    		
    	}
   	 return retStr;
    }
     
     /**
      * ���H�Ώە�����̌��ɁA�w�肵���������̎w�蕶������p�f�B���O����B
      * <pre>
      * ��j
      * StringUtil.paddingChar(null,3,"A")  = "AAA"
      * StringUtil.paddingChar("1",3,"A") = "1AA"
      * StringUtil.paddingChar("1234",3,"A") = "1234"
      * </pre>
      * @param argString     ������
      * @param argWidth     �w�茅��
      * @param target          �w�蕶����
      * @return ���H��̕�����
      */
     public static String paddingChar(String argString, int argWidth, String target) {
    	 if (argString == null) argString = "";
         if (argString.length() < argWidth) {
             for (int i = argString.length(); i < argWidth; i++) {
            	 argString += target;
             }
         }
         return argString;
     } 
     
	 /**
	  * �p�����[�^�̕�������w��̃o�C�g���Ő؂�o���B<br>
	  * �ŏI������2�o�C�g������1�o�C�g�ڂ̏ꍇ�A����1�O�̕����܂ł�ԋp�l�Ƃ���B
	  * @param str ���͕�����
	  * @param byte �o�C�g��
	  * @return ���͒l��OK�ł����true��ԋp
	  */
	public static String getByteLength(String str, int byt) {
		// null�̏ꍇ�󕶎���ԋp
		if (str == null) return "";
		
		// �w��̃o�C�g���܂ŕ������t�^
		StringBuffer buf = new StringBuffer();
		String tmp;
		int cnt = 0;
		String ret = str;
		try {
			for (int i = 0; i < ret.length(); i++) {
				tmp = ret.substring(i, i + 1);
				if (tmp.getBytes("UTF-8").length == 1) {
					cnt++;
				} else {
					cnt += 2;
				}
				// �w��̃o�C�g���𒴂�����break
				if (cnt > byt) break;
				// �ԋp�l�ɒǉ�
				buf.append(tmp);
				// �w��̃o�C�g����������break
				if (cnt == byt) break;
			}
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return buf.toString();
	}

	/**
	 * �_�u���R�[�e�[�V���������܂��B�iCSV�p�j
	 * @param itemValue
	 * @return
	 */
	public static String addWQuotes(Object itemValue) {
		return StringBaseUtil.addWQuotes(itemValue, true);
	}

	/**
	 * �_�u���R�[�e�[�V���������܂��B�iCSV�p�j
	 * @param itemValue
	 * @param hasComma
	 * @return
	 */
	public static String addWQuotes(Object itemValue, boolean hasComma) {
		if (itemValue == null) {
			return hasComma ? "\"\"," : "\"\"";
		}
		
		return "\"".concat(rtrim(itemValue)).concat(hasComma ? "\"," : "\"");
	}

	
	/**
	 * �E���̋󔒂��폜���܂�
	 * @param src
	 * @return
	 */
	public static String rtrim(Object src){
		if (src instanceof String){
			return ((String)src).replaceAll("[�@ ]*$", "");
		}
		return String.valueOf(src);
	}
}