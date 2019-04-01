package jp.co.common.frame.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import jp.co.common.frame.constant.BaseSystemConstant;
import jp.co.common.frame.util.prop.EnvPropertyUtil;

/**
 * Excel�o�̓w���v�N���X
 * @author NTS
 *
 */
/**
 *  Excel�o�̓w���v�N���X�B
 * <pre>
 *  �ȉ��̋@�\���T�|�[�g����B
 * </pre>
 * <ul>
 *   <li>Excel�e���v���[�g�o�X����������</li>
 *   <li>Excel�o�̓f�[�^��������</li>
 *   <li>�e���v���[�g�����݂��邩�`�F�b�N���܂�</li>
 *   <li>Excel�t�@�C�����w��X�g���[���ɏo�͂��܂�</li>
 * </ul>
 * @author NTS
 * @version 1.0 
 */
public class ExcelUtil{
	
	/** Excel�e���v���[�g�o�X */
	private static String templatepath = EnvPropertyUtil.getInstance().getPropertyString("EXCEL.PATH.TEMPLATE");
	/** Excel�e���v���[�g�t�@�C����*/
	private String template;
	/** Excel��workbook*/
	private XSSFWorkbook workbook;
	/** �e���v���[�g�t�@�C������ */	
	private String excelTemplate;
	/** Excel�o�̓t�@�C������ */	
	private String excelOutputFileName;
	
	/**
	 * �R���X�g���N�^
	 * @param excelTemplate �e���v���[�g�t�@�C������
	 * @param excelOutputFileName Excel�o�̓t�@�C������
	 * @throws IOException
	 */
	public ExcelUtil(String excelTemplate, String excelOutputFileName) throws IOException{
		//�e���v���[�g�t�@�C������
		this.excelTemplate = excelTemplate;
		//Excel�o�̓t�@�C������
		this.excelOutputFileName = excelOutputFileName;
	}
	
	/**
	 * Excel�o��
	 * @return
	 */
	public String printExcel(Map<String, Object> outDataMap, HttpServletResponse response, ServletContext context){
		OutputStream outputStream = null;
		
		String excels = BaseSystemConstant.EXTENSION_EXCEL_XLSM;
		
		//�e���v���[�g�t�@�C������
		this.template = context.getRealPath("/") + templatepath	+ this.excelTemplate + excels;
		
		String strExcelCharSet = BaseSystemConstant.CHARSET_NAME_SHIFT_JIS;
		
		
		//Excel�o�̓t�@�C������
		
		String strFileName = excelOutputFileName + excels;
		
		try {
			String filename = new String(strFileName.getBytes(strExcelCharSet),
					BaseSystemConstant.CHARSET_NAME_ISO8859_1);
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			outputStream = response.getOutputStream();
			writeExcel(outDataMap);
			outputExcel(outputStream);
			outputStream.flush();
		}catch(IOException ex){
		}finally{
			try{
				outputStream.close();
			}catch(Exception ex){
			}
		}
		return null;
	}
	
	/**
	 * Excel�o��
	 * @return
	 */
	public String printExcelXlsx(Map<String, Object> outDataMap, HttpServletResponse response, ServletContext context){
		OutputStream outputStream = null;
		
		String excels = BaseSystemConstant.EXTENSION_EXCEL_XLSX;
		
		//�e���v���[�g�t�@�C������
		this.template = context.getRealPath("/") + templatepath	+ this.excelTemplate + excels;
		
		String strExcelCharSet = BaseSystemConstant.CHARSET_NAME_SHIFT_JIS;
		
		
		//Excel�o�̓t�@�C������
		
		String strFileName = excelOutputFileName + excels;
		
		try {
			String filename = new String(strFileName.getBytes(strExcelCharSet),
					BaseSystemConstant.CHARSET_NAME_ISO8859_1);
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			outputStream = response.getOutputStream();
			writeExcel(outDataMap);
			outputExcel(outputStream);
			outputStream.flush();
		}catch(IOException ex){
		}finally{
			try{
				outputStream.close();
			}catch(Exception ex){
			}
		}
		return null;
	}
	
	/**
	 * Excel�o�̓f�[�^��������
	 * @param outDataMap�@�o�̓f�[�^
	 * @throws IOException 
	 */
	public void writeExcel(Map<String, Object> outDataMap) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		// �e���v���[�g�t�@�C�����J���A�V�f�[�^�V�[�g��ǉ����܂��B
		else {
			FileInputStream fis = null;
			try {				
				fis = new FileInputStream(this.template);				
		  		XLSTransformer transformer = new XLSTransformer();
				this.workbook = (XSSFWorkbook) transformer.transformXLS(fis , outDataMap);
			}catch(Exception ex){
			}
			finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fis = null;
				}
			}
		}   	
	}	
	
	/**
	 * �e���v���[�g�����݂��邩�`�F�b�N���܂��B
	 * @return
	 */
	public boolean isTemplateExist(){
		return new File(this.template).exists();
	}
   
	/**
	 * Excel�t�@�C�����w��X�g���[���ɏo�͂��܂��B
	 * @param stream
	 * @throws IOException
	 */
	public void outputExcel(OutputStream stream) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		setForceFormulaRecalculation();
		this.workbook.write(stream); 
	}

	/**
	 * Excel�o�̓f�[�^��������(�V�[�g���o��)
	 * @param outPutList �o�̓f�[�^���X�g
	 * @param sheetNameList �V�[�g���̃��X�g
	 * @param beanName �o�̓f�[�^����
	 * @throws IOException 
	 */
	public void writeExcel(List<Object> outPutList, List<String> sheetNameList, String beanName) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		// �e���v���[�g�t�@�C�����J���A�V�f�[�^�V�[�g��ǉ����܂��B
		else {
			InputStream is1 = null;
			XLSTransformer transformer = new XLSTransformer();
			try {
				is1 = new BufferedInputStream(
						new FileInputStream(this.template));

				Map<String, Object> outPutMap = new HashMap<String, Object>();
				outPutMap.put("NumberUtil", NumberUtil.class);				// �����p�N���X
				Workbook book = transformer.transformMultipleSheetsList(is1, outPutList,
						sheetNameList, beanName, outPutMap, 0);
				this.workbook = (XSSFWorkbook) book;
			}catch(Exception ex){
				ex.printStackTrace();
			}
			finally {
				if (is1 != null) {
					try {
						is1.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					is1 = null;
				}
			}
		}
	}

	/**
	 * Excel�t�@�C�����w��X�g���[���ɏo�͂��܂��B
	 * @param stream
	 * @throws IOException
	 */
	public void outputCsvToExcel(OutputStream csvStream, OutputStream stream) throws IOException{
		if (!isTemplateExist()){
			throw new IOException("the file" + this.template + " does not exist.");
		}
		
		FileInputStream fis = null;
		XSSFSheet sheet = null;
		try {
			fis = new FileInputStream(this.template);
			this.workbook = new XSSFWorkbook(fis);
			sheet = workbook.createSheet("data");
	
		} finally {
			if (fis != null) {
				fis.close();
				fis = null;
			}
		}
	
		int rownum = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(((ByteArrayOutputStream) csvStream).toByteArray())
				, BaseSystemConstant.SYSTEM_ENCODING));
		
		while(rownum < 65536){
			// �s�f�[�^�ǂݎ��
			String line = reader.readLine();
			if (line == null){
				break;
			}
			
			// �s�I�u�W�F�N�g�쐬
			Row row = sheet.createRow(rownum);
			
			// �Z���l�ݒ�
			String[] items = splite(line);
			if (items.length > 1){
				for(int i = 1; i < items.length; i++){
					row.createCell(i - 1);
					setCellValue(sheet, rownum, i - 1, items[i]);
				}
			}
			rownum++;
		}
		reader.close();

		this.workbook.write(stream);
	}
	
	/**
	 * Excel�Z���̒l��ݒ�
	 * @param sheet
	 * @param rownum
	 * @param columnIndex
	 * @param val
	 */
	private static void setCellValue(XSSFSheet sheet, int rownum, int columnIndex, String val){
		// ^[ ]*\\d+[.]\\d+[ ]*$ 		�����_�t�����l 1.2 0.1
		// ^[ ]*[1-9]\\d*[ ]*$ 			�[���ȊO�Ŏn�܂鐔�l�i�[���Ŏn�܂�̂��̂̓R�[�h�ŁA������ƌ���j
		// ^[ ]*[0][ ]*$ 				�[��
		// ^[ ]*[-]\\d+[.]?\\d+[ ]*$ 	�}�C�i�X�̐��l
		if( val.matches("(^[ ]*\\d+[.]\\d+[ ]*$)|(^[ ]*[1-9]\\d*[ ]*$)|(^[ ]*[0][ ]*$)|([ ]*[-]\\d+[.]?\\d+[ ]*$)") ){
			sheet.getRow(rownum).getCell(columnIndex).setCellValue(Double.parseDouble(val));
		} else {
			sheet.getRow(rownum).getCell(columnIndex).setCellValue(new XSSFRichTextString(val));
		}
	}
	
	
	/**
	 * CSV���ڂ𕪊�
	 * @param param
	 * @return
	 */
	private static String[] splite(String param){
		if (param == null){
			return new String[]{};
		}
		
		return param.split("^\"|\"$|\"[ ]*,[ ]*\"");
	}
	
	/**
	 * �����Čv�Z
	 */
	private void setForceFormulaRecalculation(){
		// ��ƃV�[�g�I�u�W�F�N�g
		XSSFSheet sheet = null;
		int numSheets = this.workbook.getNumberOfSheets();
		for(int i = 0; i < numSheets; i++){
			sheet = this.workbook.getSheetAt(i);
			//�����v�Z
			sheet.setForceFormulaRecalculation(true);
		}
	}
}