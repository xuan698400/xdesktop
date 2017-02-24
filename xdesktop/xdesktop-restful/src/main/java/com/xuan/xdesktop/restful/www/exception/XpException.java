package com.xuan.xdesktop.restful.www.exception;

import com.xuan.xdesktop.restful.www.enums.Code;
import com.xuan.xdesktop.restful.www.utils.Validators;

/**
 * Created by xuan on 17/2/22.
 */
public class XpException extends RuntimeException {
    private static final long serialVersionUID = -1282268289535528399L;

    protected Code code = null;
    protected String message;

    public XpException() {
        super();
    }

    public XpException(Code code) {
        super(code.getMessage());
        this.code = code;
        this.message = code.getMessage();
    }

    public XpException(Code code, String message) {
        super(code.getMessage());
        this.code = code;
        this.message = message;
    }

    public XpException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if (!Validators.isEmpty(message)) {
            return message;
        } else if (this.code != null) {
            return this.code.getMessage();
        } else {
            return null;
        }
    }

    public Code getErrorCode() {
        return this.code;
    }

    /**
     * 异常处理，优化速度
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
