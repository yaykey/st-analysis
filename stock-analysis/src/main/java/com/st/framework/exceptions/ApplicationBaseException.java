/*
 * 文件名： ApplicationBaseException.java
 * 
 * 创建日期： 2011-4-14
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
 * <code>ApplicationBaseException</code> 和它的子类用于表示应用级的异常
 *
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 * @version $Revision: 1.1 $
 *
 * @Date 下午04:52:37
 *
 * @since 2011-4-14
 */
public class ApplicationBaseException extends Exception{

	private static final long serialVersionUID = -2980654103118928278L;

    protected Throwable throwable;

    public ApplicationBaseException() {
        super("Application Error");
        throwable = null;
    }

    public ApplicationBaseException(String msg) {
        super(msg);
        throwable = null;
    }

    public ApplicationBaseException(Throwable throwable) {
        this.throwable = throwable;
    }

    public ApplicationBaseException(String msg, Throwable throwable) {
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
