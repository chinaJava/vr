package io.renren.utils;


import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */

@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public R handleRRException(RRException e){
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());
		return r;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		logger.error(e.getMessage(), e);
		return R.error();
	}
	
//	@Override
//	public ModelAndView resolveException(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex) {
//		R r = new R();
//		try {
//			response.setContentType("application/json;charset=utf-8");
//			response.setCharacterEncoding("utf-8");
//			
//			if (ex instanceof RRException) {
//				r.put("code", ((RRException) ex).getCode());
//				r.put("msg", ((RRException) ex).getMessage());
//			}else if(ex instanceof DuplicateKeyException){
//				r = R.error("数据库中已存在该记录");
//			}else if(ex instanceof AuthorizationException){
//				r = R.error("没有权限，请联系管理员授权");
//			}else{
//				r = R.error();
//			}
//			
//			//记录异常日志
//			logger.error(ex.getMessage(), ex);
//			
//			String json = JSON.toJSONString(r);
//			response.getWriter().print(json);
//		} catch (Exception e) {
//			logger.error("RRExceptionHandler 异常处理失败", e);
//		}
//		return new ModelAndView();
//	}
}
