package com.st.framework.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 文件解析错误
 * 
 * @author yzy
 *
 */
public class FileParseErrorException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6407683987746254464L;

	protected Throwable throwable;

    public FileParseErrorException() {
        super("FileParseErrorException Error");
        throwable = null;
    }

    public FileParseErrorException(String msg) {
        super(msg);
        throwable = null;
    }

    public FileParseErrorException(Throwable throwable) {
        this.throwable = throwable;
    }

    public FileParseErrorException(String msg, Throwable throwable) {
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
