package jp.co.common.frame.interceptor;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jp.co.fourseeds.fsnet.beans.LoginUserBean;
import jp.co.fourseeds.fsnet.common.ConstantContainer;
import jp.co.common.frame.util.DateUtil;

/**
 * �Ɩ����b�Z�[�W�����N���X
 * 
 * <pre>
   *  �@ �Ɩ����b�Z�[�W����������ꍇ�AAction�@Input�߂�
 *  �ȉ��̋@�\���T�|�[�g����B
 * </pre>
 * <ul>
 * <li>�Ɩ����b�Z�[�W����</li>
 * </ul>
 * 
 * @author NTS
 * @version 1.0
 */
@Component
@Aspect
public class AccessLogInterceptor {
	/** Log4j�̒�` */
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * ��?�꘢���@�C�p�������ؓ��_�\?���C���@����ʕs���v�Y��������? �g�p@Pointcut�����ؓ��_�\?��
	 * �@�ʓI�ʒm���ڎg�p���@�������p���O�I�ؓ_�\?���G�@�ʐ�����?�g�p�C��������
	 */
	@Pointcut("execution(public * jp.co.fourseeds.fsnet.action..*.*(..))")
	public void declearAccessLogJoinPointExpression() {
	}

	/**
	 * �O�u�ʒm
	 * 
	 * @param joinPoint
	 */
	@Before("declearAccessLogJoinPointExpression()")
	public void before() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		StringBuffer sb = new StringBuffer();
		sb.append("***START " + DateUtil.getNowTime() + ":");

		LoginUserBean user = (LoginUserBean) request.getSession().getAttribute(ConstantContainer.LOGIN_USER_KEY);

		if (user != null) {
			sb.append("�@���[�U�[:" + user.getUserId());
		} else {
			sb.append("�@���[�U�[:???");
		}
		// sb.append(" �N���X:" + ai.getAction());
		// sb.append(" ���\�b�h:" + ai.getInvocationContext().getName());
		logger.info(sb.toString());
	}

	/**
	 * �@�u�ʒm�i��?���@����?��?��s��?�s,����??�s�����@�I�ԉ�?�j
	 * 
	 * @param joinPoint
	 */
	@After("declearAccessLogJoinPointExpression()")
	public void after() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("***END " + DateUtil.getNowTime() + ":");

			LoginUserBean user = (LoginUserBean) request.getSession().getAttribute(ConstantContainer.LOGIN_USER_KEY);

			if (user != null) {
				sb.append("�@���[�U�[:" + user.getUserId());
			} else {
				sb.append("�@���[�U�[:???");
			}
			// sb.append(" �N���X:" + ai.getAction());
			// sb.append(" ���\�b�h:" + ai.getInvocationContext().getName());
			// Map<String, Object> map = ai.getInvocationContext().getParameters();
			// Set<String> keys = map.keySet();
			sb.append("�@�p�����[�^�F");
			// for (String key : keys) {
			// sb.append(key + "=" + ((Object[]) map.get(key))[0]+ "#");
			// }
			// sb.append(" �߂茋��:" + ai.getResultCode());

			logger.info(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ԉ�ʒm�i�ݕ��@����?��?�s�I��?�j �ԉ�ʒm��??�����@�I�ԉ�?�I
	 * 
	 * @param joinPoint
	 */
	@AfterReturning(value = "declearAccessLogJoinPointExpression()", returning = "result")
	public void afterReturnMethod(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("this method " + methodName + " end.result<" + result + ">");
	}

	/**
	 * ?��ʒm�i���@?��?��?�s�I��?�j ��??��?��?�ہG���Ȏw��ݏo?����?��??�s�I��?
	 * 
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(value = "declearAccessLogJoinPointExpression()", throwing = "ex")
	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		System.out.println("this method " + methodName + " end.ex message<" + ex + ">");
	}

	/**
	 * ??�ʒm(���v�g??�^?ProceedingJoinPoint?�^�I�Q��) ??�ʒm��ܑO�u�A�@�u�A�ԉ�A?��ʒm�GProceedingJoinPoin
	 * ?�^�I�Q���șr�营��?�s��?���@ ��??�ʒm�K?�L�ԉ�?�C�ԉ�?����?���@�I�ԉ�?
	 * 
	 * @param point
	 * @throws Throwable 
	 */
	@Around(value = "declearAccessLogJoinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
		Object result = null;
		String methodName = point.getSignature().getName();
		try {
			// �O�u�ʒm
			System.out.println("The method " + methodName + " start. param<" + Arrays.asList(point.getArgs()) + ">");
			// ?�s��?���@
			result = point.proceed();
			// �ԉ�ʒm
			System.out.println("The method " + methodName + " end. result<" + result + ">");
		} catch (Throwable e) {
			// ?��ʒm
			System.out.println("this method " + methodName + " end.ex message<" + e + ">");
			throw e;
		}
		// �@�u�ʒm
		System.out.println("The method " + methodName + " end.");
		return result;
	}
}
