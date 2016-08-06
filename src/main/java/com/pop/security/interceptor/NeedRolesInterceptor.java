package com.pop.security.interceptor;


import com.pop.exception.AuthException;
import com.pop.security.Account;
import com.pop.security.enums.CommonConstants;
import com.pop.security.annotion.NeedRoles;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class NeedRolesInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HttpSession session = request.getSession();
        // 获取用户对象
        Account account = (Account) session.getAttribute(CommonConstants.LOGIN_USER_ACCOUNT);
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        NeedRoles annotation = method.getAnnotation(NeedRoles.class);

        if (annotation != null) {
            checkAuthorized(account, annotation);
        }

        return super.preHandle(request, response, handler);
    }

    private void checkAuthorized(Account account, NeedRoles annotation) {
        if (account == null) {
            throw new AuthException(-1000,"用户未登录");
        }
    }
}
