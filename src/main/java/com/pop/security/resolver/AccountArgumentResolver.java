package com.pop.security.resolver;


import com.pop.security.Account;
import com.pop.security.annotion.SessionAccount;
import com.pop.security.enums.CommonConstants;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccountArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        SessionAccount annot = parameter.getParameterAnnotation(SessionAccount.class);

        return ((annot != null) && (Account.class.isAssignableFrom(parameter.getParameterType())) && (!StringUtils
                .hasText(annot.value())));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(CommonConstants.LOGIN_USER_ACCOUNT);
        return account;
    }

}
