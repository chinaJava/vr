package io.renren.interceptor;

import java.util.Date;

import io.renren.annotation.IgnoreAuth;
import io.renren.entity.TokenEntity;
import io.renren.service.TokenService;
import io.renren.utils.RRException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TokenService tokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        IgnoreAuth annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        }else{
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if(annotation != null){
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }

      //token为空
        if(StringUtils.isBlank(token)){
            throw new RRException("您还未登录，请先登录",503);
        }

        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null){
            throw new RRException("您的账号已在其他地方登录",503);
        }

        //查询token信息
        if(tokenEntity == null || tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()){
        	//当前时间
    		Date now = new Date();
    		//过期时间
    		Date expireTime = new Date(now.getTime() + 3600 * 12 * 1000);
        	tokenEntity.setExpireTime(expireTime);
        	tokenService.update(tokenEntity);
        }

        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());

        return true;
    }
}
