package com.pop.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Created by chewenjie on 2015/7/27.
 */
public class AuthException extends NestedRuntimeException {

    private String content;

    private Integer code;

    public AuthException(String content) {
        super(content);

        this.content = content;
    }

    public AuthException(Integer code, String content) {
        super(content);

        this.content = content;
        this.code = code;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
