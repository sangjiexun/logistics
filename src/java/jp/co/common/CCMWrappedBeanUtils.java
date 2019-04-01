/*
 * �v���X���W�X�e�B�N�X�������
 * �V�z���Ǘ��V�X�e��
 * Copyright (C) 2007 PLUS Logistics Corporation. All rights reserved.
 */
package jp.co.common;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * <DT><B>[�N���X��]</B><UL>
 *    Commons BeanUtils ���b�p�[�N���X
 * </UL><P>
 * <DT><B>[����]</B>
 * <UL>
 *    Commons BeanUtils ���b�p�[�N���X�B
 *    �Ƃ肠�����K�v�����Ȃ��̂̂ݎ����B
 *    �I�u�W�F�N�g�Ԃ̃v���p�e�B�̃R�s�[�ɂ͕K���₤�N���X���g�p����B
 *    �v���p�e�B�̃R�s�[���ɔ���������O�͑S�ăL���b�`���邩�ǂ����͌���
 * </UL><P>
 * <DT><B>[�X�V����]</B><UL>
 * <LI>2006/08/21 1.0 Cocom H.Yatake �V�K�쐬
 * </UL><P>
 * @since JDK1.5.0
 */
public class CCMWrappedBeanUtils {

	
	/**
	 * �C���X�^���X����}�~�����肵�܂��B
	 */
	private CCMWrappedBeanUtils() {

	}

	/**
	 * �I�u�W�F�N�g�v���p�e�B�R�s�[
	 * ��2���������1�����ɃR�s�[����B
	 * @param arg0 �o�̓I�u�W�F�N�g
	 * @param arg1 ���̓I�u�W�F�N�g
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object arg0, Object arg1) throws IllegalAccessException,
			InvocationTargetException {

		// �v���p�e�B���X�g�擾
		if(arg1 == null || arg0 == null) {
			return;
		}
		PropertyDescriptor descriptor[] = PropertyUtils.getPropertyDescriptors(arg1);

		// �v���p�e�B�����[�v���Ēl��]������B
		// java.lang.String -> java.sql.Timestamp  ��
		// java.lang.String -> java.math.BigDecimal�@�̓]���̏ꍇ

		// �]������Null�̏ꍇ�ɕϊ��G���[���o�Ă��܂��̂�
		// ���̗�O�̓X�L�b�v����悤�ɂ���B
		// �y���Ӂz
		// java.sql.Timestamp -> java.lang.String ��
		// java.math.BigDecimal-> java.laong.String�@�̏ꍇ��Null�ɂȂ�B
		//Object objValue = null;
		for (int i = 0; i < descriptor.length; i++) {

			try {
				//objValue = BeanUtils.getProperty(arg1, descriptor[i].getName());

				String key = descriptor[i].getName();

				if ("class".equals(key)) {
					continue;
				}

				Object value = BeanUtilsBean.getInstance().getPropertyUtils().getSimpleProperty(arg1, key);

				if (value instanceof Map) {

					Iterator names = ((Map) value).keySet().iterator();
					while (names.hasNext()) {
						String name = (String) names.next();

						if(PropertyUtils.isWriteable(arg0, name)) {
							Object mapValue = ((Map) value).get(name);
							BeanUtils.copyProperty(arg0, key, mapValue);
						}
					}

				} else {

					if(PropertyUtils.isWriteable(arg0, key)) {
						BeanUtils.copyProperty(arg0, key, value);
					}
				}

			} catch (ConversionException e) {
				// �S�v���p�e�B�������������̂ŏ������s
				continue;

			} catch (NoSuchMethodException e) {
				// �S�v���p�e�B�������������̂ŏ������s
				continue;
			}

		}

	}

	/**
	 * ���̂��w�肵�ăI�u�W�F�N�g�̃v���p�e�B�R�s�[
	 * @param arg0 �o�̓I�u�W�F�N�g
	 * @param arg1 �R�s�[�Ώۂ̃v���p�e�B��
	 * @param arg2 ���̓I�u�W�F�N�g
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperty(Object arg0, String arg1, Object arg2) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtils.copyProperty(arg0, arg1, arg2);
	}

	/**
	 * �I�u�W�F�N�g�v���p�e�B�R�s�[
	 * ��2������Map���1������Bean�ɕϊ�����B
	 * @param arg0 �o�̓I�u�W�F�N�g
	 * @param arg1 ���̓I�u�W�F�N�g
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object arg0, Map arg1) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.populate(arg0, arg1);

	}

	/**
	 * Bean�̃N���[���쐬
	 * @param arg0
	 * @return Object arg0�̃N���[���Ƃ��č쐬�����ʃI�u�W�F�N�g
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Object cloneBean(Object arg0) throws IllegalAccessException, InstantiationException,
			InvocationTargetException, NoSuchMethodException {
		return BeanUtils.cloneBean(arg0);

	}

	/**
	 * ��Q������List�I�u�W�F�N�g�̊e�v�f���P������VO�ɃR�s�[���A
	 * �V����List��Ԃ��B
	 * @param className
	 * @param copyFromList
	 * @return List
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws Exception
	 */
	public static List copyList(Class clazz, List copyFromList) throws IllegalAccessException, InvocationTargetException, InstantiationException {

		List<Object> rtnList = new ArrayList<Object>();

		int rowCnt = 0;

		if (copyFromList != null) {

			rowCnt = copyFromList.size();

			for (int i = 0; i < rowCnt; i++) {

				Object rtnClass = clazz.newInstance();

				copyProperties(rtnClass, copyFromList.get(i));

				rtnList.add(rtnClass);

			}

		}
		
		

		return rtnList;

	}
	
    /**
     * �v���p�e�B�ݒ�
     * @param bean �ݒ�Ώۂ�Bean��
     * @param name �ݒ�Ώۂ̃v���p�e�B��
     * @param value �ݒ�l
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @see BeanUtils#setProperty
     */
    public static void setProperty(Object bean, String name, Object value)
        throws IllegalAccessException, InvocationTargetException {

    	BeanUtils.setProperty(bean, name, value);
    }
	
    /**
     * �v���p�e�B�擾
     * @param bean �ݒ�Ώۂ�Bean��
     * @param name �ݒ�Ώۂ̃v���p�e�B��
     * @return String 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @see BeanUtilsBean#getProperty
     */
    public static String getProperty(Object bean, String name)
            throws IllegalAccessException, InvocationTargetException,
            NoSuchMethodException {

        return BeanUtils.getProperty(bean, name);

    }
	
	

}
