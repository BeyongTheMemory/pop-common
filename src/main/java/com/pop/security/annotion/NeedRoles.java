package com.pop.security.annotion;


import com.pop.security.enums.CommonConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedRoles {

    /**
     * @return 角色ID;默认为对所有已登录用户可用.
     */
    String value() default CommonConstants.ROLE_DEFAULT;
    
    /**
     * 某一功能可以匹配多个角色使用
     * @return 角色ID数组，包含在此角色范围内的用户，可使用对应的功能
     */
    String[] roles() default {};
}
