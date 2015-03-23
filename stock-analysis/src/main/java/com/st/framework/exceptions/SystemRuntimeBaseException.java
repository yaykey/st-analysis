/*
 * 文件名： SystemRuntimeBaseException.java
 * 
 * 创建日期： 2011-4-11
 *
 * Copyright(C) 2011, by xiaozhi.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * SystemRuntimeBaseException 和它的子类用于指示系统的运行时异常
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision: 1.1 $
 *
 * @Date 上午11:44:50
 *
 * @since 2011-4-11
 */
public class SystemRuntimeBaseException extends RuntimeException{

	private static final long serialVersionUID = -907028942053049656L;

	protected Throwable throwable;

    public SystemRuntimeBaseException() {
        super("System Runtime Error");
        throwable = null;
    }

    public SystemRuntimeBaseException(String msg) {
        super(msg);
        throwable = null;
    }

    public SystemRuntimeBaseException(Throwable throwable) {
        this.throwable = throwable;
    }

    public SystemRuntimeBaseException(String msg, Throwable throwable) {
        this(msg);
        this.throwable = throwable;
    }

    public Throwable getLinkedException() {
        return throwable;
    }

    public void setLinkedException(Throwable throwable) {
        this.throwable = throwable;
    }

    public String getMessage() {
        if (throwable == null) {
            return super.getMessage();
        } else {
        	StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            return super.getMessage() + ": " + throwable.getMessage() + sw.toString();
        }
    }
}
