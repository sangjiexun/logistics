package jp.co.common.frame.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.apache.commons.lang.math.NumberUtils;

/**
 *  �����p�N���X
 * <pre>
 * </pre>
 * <ul>
 *   <li></li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class NumberUtil extends NumberUtils {

	private NumberUtil() {

	}

	/**
	 *�@�p�����[�^�̐������w�肵�����P�[���̕W���t�H�[�}�b�g�ɕϊ����郁�\�b�h
	 *
     * <pre>
     * ��j

	 * getCurrencyString("1234567890") = "��1,234,567,890"
     * </pre>
	 * @param currency
	 * @param localeString
	 * @return �󂯎�������������P�[���W���t�H�[�}�b�g�ɕϊ������l
	 */
	public static String getCurrencyString(String currency) {
		String newCurrency = null;
		//���P�[���ɉ������ʉ݃t�H�[�}�b�g�𐶐�
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
		//�t�H�[�}�b�g�̕ϊ�
		newCurrency = nf.format(Long.parseLong(currency));
		return newCurrency;
	}
	
	/**
	 * ���l��������w�肵���t�H�[�}�b�g�ŕϊ����郁�\�b�h
	 * 
     * <pre>
     * ��j

     * formatNumber("123456789")  		= "123,456,789"
     * </pre>
	 * @param numberStr ���l������
	 * @param formatStr �t�H�[�}�b�g��`������
	 * @return �t�H�[�}�b�g�ϊ��㐔�l������
	 */
	public static String formatNumber(String numberStr) {
		return formatNumber(numberStr, "###,###");
	}

	/**
	 * ���l��������w�肵���t�H�[�}�b�g�ŕϊ����郁�\�b�h
	 * 
     * <pre>
     * ��j

     * formatNumber("123456789", "###,###")  		= "123,456,789"
     * formatNumber("123456789.98765", "#,###.##")  = "123,456,789.99"
     * </pre>
	 * @param numberStr ���l������
	 * @param formatStr �t�H�[�}�b�g��`������
	 * @return �t�H�[�}�b�g�ϊ��㐔�l������
	 */
	public static String formatNumber(String numberStr, String formatStr) {
		if (numberStr == null || "".equals(numberStr)) {
			return "";
		}
		
		String newNumberStr = null;
		//�t�H�[�}�b�g��`�ς�DecimalFormat�I�u�W�F�N�g�̐���
		DecimalFormat df = new DecimalFormat(formatStr);
		//�t�H�[�}�b�g

		newNumberStr = df.format(Double.parseDouble(numberStr));
		return newNumberStr;
	}
	/**
	 * ���l���������%�ϊ����郁�\�b�h
     * <pre>
     * ��j
     * toPer("0.05")  		= "5%"
     * </pre>
	 * @param numberStr ���l������
	 * @return �t�H�[�}�b�g�ϊ��㐔�l������
	 */
	public static String toPer(String numberStr) {
		if (!isNumber(numberStr)) {
			return "";
		}
		String newNumberStr = String.valueOf(toBigDecimal(numberStr).multiply(toBigDecimal("100")).toBigInteger())+"%";
		return newNumberStr;
	}
	
	public static String toPer2(String numberStr, int scale) {
		if (!isNumber(numberStr)) {
			return "";
		}
		if("0".equals(numberStr)){
			return " ";
		}
		String newNumberStr = String.valueOf((toBigDecimal(numberStr).multiply(toBigDecimal("100"))).setScale(scale, BigDecimal.ROUND_HALF_UP))+"%";
		return newNumberStr;
	}
	
	/**
	 * �������int�ɕϊ�����A�ϊ��G���[�̏ꍇ�A0��Ԃ�
	 * 
     * <pre>
     * ��j

     *   toInt(null) = 0
     *   toInt("")   = 0
     *   toInt("1")  = 1
     * </pre>
	 * @param str
	 * @return int
	 */
	public static int toInt(String str){
		return NumberUtils.toInt(str);
	}
	/**
	 * �������long�ɕϊ�����A�ϊ��G���[�̏ꍇ�A0��Ԃ�
	 * 
     * <pre>
     * ��j

     *   toLong(null) = 0L
     *   toLong("")   = 0L
     *   toLong("1")  = 1L
     * </pre>
	 * @param str
	 * @return long
	 */
	public static long toLong(String str){
		return NumberUtils.toLong(str);
	}
	/**
	 * �������float�ɕϊ�����A�ϊ��G���[�̏ꍇ�A0.0��Ԃ�
	 * 
     * <pre>
     * ��j

     *   toFloat(null)   = 0.0f
     *   toFloat("")     = 0.0f
     *   toFloat("1.5")  = 1.5f
     * </pre>
	 * @param str
	 * @return float
	 */
	public static float toFloat(String str){
		return NumberUtils.toFloat(str);
	}
	/**
	 * �������double�ɕϊ�����A�ϊ��G���[�̏ꍇ�A0.0��Ԃ�
	 * 
     * <pre>
     * ��j

     *   toDouble(null)   = 0.0d
     *   toDouble("")     = 0.0d
     *   toDouble("1.5")  = 1.5d
     * </pre>
	 * @param str
	 * @return double
	 */
	public static double toDouble(String str){
		return NumberUtils.toDouble(str);
	}
	
	/**
	 * �����񂪐����̕��������ō\������Ă��邩���`�F�b�N���܂�

	 * 
     * <pre>
     * ��j

     *   isDigits(null)   = false
     *   isDigits("")     = false
     *   isDigits("1.5")  = false
     *   isDigits("15")   = true
     *   isDigits("abc")  = false
     * </pre>
	 * @param str
	 * @return null �A��̕����񂪎w�肳�ꂽ�ꍇ�ɂ� false ��Ԃ�
	 */
	public static boolean isDigits(String str){
		return NumberUtils.isDigits(str);
	}
	
	/**
	 *�����񂪐����̕�����Java�̐��l���e�����\�L�����ō\������Ă��邩���`�F�b�N����
	 * 
     * <pre>
     * ��j

     *   isNumber(null)   = false
     *   isNumber("")     = false
     *   isNumber("1.5")  = true
     *   isNumber("1-")   = false
     *   isNumber("-1")   = true
     *   isNumber("15")   = true
     *   isNumber("abc")  = false
     * </pre>
	 * @param str
	 * @return null �A��̕����񂪎w�肳�ꂽ�ꍇ�ɂ� false ��Ԃ�
	 */
	public static boolean isNumber(String str){
		return NumberUtils.isNumber(str);
	}
	
	/**
	 * �������BigDecimal�ɕϊ�����A�ϊ��G���[�̏ꍇ�A0��Ԃ�
	 * 
     * <pre>
     * ��j

     *   toBigDecimal(null)   = 0
     *   toBigDecimal("")     = 0
     *   toBigDecimal("1.501",new BigDecimal(0))  = 1.501
     * </pre>
	 * @param str
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(String str){
		return toBigDecimal(str, new BigDecimal(0));
	}
	/**
	 * �������BigDecimal�ɕϊ�����A�ϊ��G���[�̏ꍇ�AdefaultValue��Ԃ�
   	 * <pre>
     * ��j

     *   toBigDecimal(null,new BigDecimal(0))   = 0
     *   toBigDecimal("",new BigDecimal(0))     = 0
     *   toBigDecimal("1.501",new BigDecimal(0))  = 1.501
     * </pre>
	 * @param str
 	 * @param defaultValue
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(String str,BigDecimal defaultValue){
		try {
			return new BigDecimal(str);
		} catch (Exception nfe) {
			return defaultValue;
		}
	}
	
	/**
	 * �������Object�ɕϊ�����A�ϊ��G���[�̏ꍇ�A0��Ԃ�
	 * 
     * <pre>
     * ��j

     *   toBigDecimal(null)   = 0
     *   toBigDecimal("")     = 0
     *   toBigDecimal("1.501",new BigDecimal(0))  = 1.501
     * </pre>
	 * @param str
	 * @return BigDecimal
	 */
	public static Object toObject(String str){
		if(StringBaseUtil.isEmpty(str)){
			return "";
		}
		return toBigDecimal(str, new BigDecimal(0));
	}
		
	/**
	 * �@�\�F�����̂���ꍇ�A�����������擾����

	 * �ΏہF�����_��Decimal�^�i���z�Ȃǁj

	 * <pre>
     * ��j

     *   clearDecimal(50.0031)   = 50
     *   clearDecimal("",new BigDecimal(0))     = 0
     *   clearDecimal("1.501",new BigDecimal(0))  = 1.501
     *   clearDecimal("5001") = 5001
     *   clearDecimal("5.00.1") = 5
     *   clearDecimal("5001.") = 500
     *    clearDecimal(".5001") = 
     * </pre>
	 * @param decValue
	 * @return decValue
	 */
	public static String clearDecimal(String decValue) {
		if (decValue != null && decValue.indexOf(".")>=0) {
			decValue = decValue.substring(0,decValue.indexOf("."));
		}
	    return decValue;
	}

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.add(b2).doubleValue();
	}
	
	public static double subtract(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	public static double multiply(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).doubleValue();
	}
	
	public static double multiply(double v1, double v2,int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.multiply(b2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double multiply(double v1, double v2,int scale,int round) {
		  if (scale < 0) {
			  throw new IllegalArgumentException("The scale must be a positive integer or zero");
		  }
		  BigDecimal b1 = new BigDecimal(v1*v2);
		  return b1.setScale(scale,round).doubleValue();
	}
	
	public static double divide(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2).doubleValue();
	}

	public static double divide(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}

		BigDecimal b1 = new BigDecimal(v1);
		BigDecimal b2 = new BigDecimal(v2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(v);
		return b.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public final static BigDecimal ZERO = new BigDecimal("0");
	
	/**
	 * �p�[�Z���g�l���Z�o
	 * @param divisor
	 * @param numerator
	 * @return
	 */
	public static BigDecimal percent(BigDecimal divisor, BigDecimal numerator, int scale){
		if (divisor == null || numerator == null || ZERO.compareTo(numerator) == 0 ){
			return ZERO;
		}
		
		return divisor.divide(numerator, scale, BigDecimal.ROUND_HALF_UP);
	}
	
	public static void main(String args[]) {
    	try {
	    	System.out.println("###################################### NumberUtil�̃e�X�g ###########################################");
			System.out.println("getCurrencyString(\"1234567890\")			= " + getCurrencyString("1234567890"));
			System.out.println("");
	        System.out.println("formatNumber(\"123456789\", \"###.###\")			= " + formatNumber("123456789", "###,###"));
	        System.out.println("formatNumber(\"123456789.98765\", \"#,###.##\")		= " + formatNumber("123456789.98765", "#,###.##"));
	    	System.out.println("");
	    	System.out.println("toInt(null)			= " + toInt("null"));
	        System.out.println("toInt(\"\")			= " + toInt(""));
	        System.out.println("toInt(\"1\")		= " + toInt("1"));
	    	System.out.println("");
	        System.out.println("toLong(null)		= " + toLong("null"));
	        System.out.println("toLong(\"\")		= " + toLong(""));
	        System.out.println("toLong(\"1\")		= " + toLong("1"));
	    	System.out.println("");
	        System.out.println("toFloat(null)		= " + toFloat("null"));
	        System.out.println("toFloat(\"\")		= " + toFloat(""));
	        System.out.println("toFloat(\"1.5\")		= " + toFloat("1.5"));
	    	System.out.println("");
	        System.out.println("toDouble(null)		= " + toDouble("null"));
	        System.out.println("toDouble(\"\")		= " + toDouble(""));
	        System.out.println("toDouble(\"1.5\")	= " + toDouble("1.5"));
	    	System.out.println("");
	        System.out.println("isDigits(null)		= " + isDigits("null"));
	        System.out.println("isDigits(\"\")		= " + isDigits(""));
	        System.out.println("isDigits(\"1.5\")	= " + isDigits("1.5"));
	        System.out.println("isDigits(\"15\")		= " + isDigits("15"));
	        System.out.println("isDigits(\"abc\")	= " + isDigits("abc"));
	    	System.out.println("");
	        System.out.println("isNumber(null)		= " + isNumber("null"));
	        System.out.println("isNumber(\"\")		= " + isNumber(""));
	        System.out.println("isNumber(\"1.5\")	= " + isNumber("1.5"));
	        System.out.println("isNumber(\"1-\")	= " + isNumber("1-"));
	        System.out.println("isNumber(\"-1\")	= " + isNumber("-1"));
	        System.out.println("isNumber(\"15\")	= " + isNumber("15"));
	        System.out.println("isNumber(\"abc\")	= " + isNumber("abc"));
	    	System.out.println("");
	    	System.out.println("toBigDecimal(null)			= " + toBigDecimal(null));
	        System.out.println("toBigDecimal(\"\")			= " + toBigDecimal(""));
	        System.out.println("toBigDecimal(\"1.501\")		= " + toBigDecimal("1.501"));
	        System.out.println("clearDecimal(\"50.0031\")		= " + clearDecimal("50.0031"));
	        System.out.println("clearDecimal(\"5001\")		= " + clearDecimal("5001"));
	        System.out.println("clearDecimal(\"5001.\")		= " + clearDecimal("5001."));
	        System.out.println("toPer(\"0.05\")	= " + toPer("0.05"));
	        System.out.println("toPer(\"0.08\")	= " + toPer("0.08"));
	        System.out.println("toPer(\"0.10\")	= " + toPer("0.10"));
	        System.out.println("toPer(\"\")	= " + toPer(""));
	    	System.out.println("###################################### NumberUtil�̃e�X�g ###########################################");
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}