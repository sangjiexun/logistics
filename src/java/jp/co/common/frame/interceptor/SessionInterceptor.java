package jp.co.common.frame.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * �Z�b�V�����^�C���A�E�g�`�F�b�N
 * 
 * File Name: SessionInterceptor.java Created: 2015/09/22 Original Author: NTS
 * 
 * ----------------------------------------------------------- Version When Who
 * Why ----------------------------------------------------------- 1.0
 * 2015/09/22 NTS �쐬
 *
 **/
public class SessionInterceptor implements HandlerInterceptor {
	/** Log4j */
	private final Log logger = LogFactory.getLog(this.getClass());

	static final String timeoutError = "/jsp/error/errorSessionTimeout.jsp";
	static final String commonError = "/jsp/error/commonError.jsp";
	static final String error = "/jsp/error/error.jsp";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		System.out.println("preHandle");
		/*HttpSession session = request.getSession();

		String userAgent = request.getHeader("user-agent");

		System.out.println(userAgent);
		System.out.println(userAgent.indexOf("Windows"));
		// if(userAgent.indexOf("chrome") < 0){
		// System.out.println("start redirect ................");
		// return "ajaxTimeout";
		// }
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
			JSONObject resultJson = new JSONObject();
			resultJson.put("status", "error");

			// �Z�b�V�����`�F�b�N
			if (session == null) {
				OutputStream ps = response.getOutputStream();
				logger.error("�Z�b�V������񂪎����܂����B");
				response.setStatus(403);
				response.setContentType("application/json;charset=UTF-8");
				resultJson.put("errorCode", "timeoutError");
				resultJson.put("errorMessage", "�Z�b�V������񂪎����܂����B");
				ps.write(resultJson.toString().getBytes("UTF-8"));
				return false;
			} else {
				// �Z�b�V�������烍�O�C�����[�U�[�����擾
				LoginUserBean user = (LoginUserBean) session.getAttribute(ConstantContainer.LOGIN_USER_KEY);

				// ���[�U�[��񂪂Ȃ��ꍇ
				if (user == null) {
					OutputStream ps = response.getOutputStream();
					logger.error("�Z�b�V������񂪎����܂����B(���[�U�[���Ȃ�)�B");
					response.setStatus(403);
					response.setContentType("application/json;charset=UTF-8");
					resultJson.put("errorCode", "timeoutError");
					resultJson.put("errorMessage", "�Z�b�V������񂪎����܂����B(���[�U�[���Ȃ�)�B");
					ps.write(resultJson.toString().getBytes("UTF-8"));
					return false;
				}
			}
		} else {
			// �Z�b�V�����`�F�b�N
			if (session == null) {
				logger.error("�Z�b�V������񂪎����܂����B");
                request.getRequestDispatcher(timeoutError).forward(request, response);
                return false;
			} else {
				// �Z�b�V�������烍�O�C�����[�U�[�����擾
				LoginUserBean user = (LoginUserBean) session.getAttribute(ConstantContainer.LOGIN_USER_KEY);

				// ���[�U�[��񂪂Ȃ��ꍇ
				if (user == null) {
					logger.error("�Z�b�V������񂪎����܂����B(���[�U�[���Ȃ�)�B");
	                request.getRequestDispatcher(timeoutError).forward(request, response);
	                return false;
				}
			}
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		System.out.println("afterCompletion");
	}
}