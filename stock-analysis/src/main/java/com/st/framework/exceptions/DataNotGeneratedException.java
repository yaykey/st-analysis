package com.st.framework.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 数据未生成
 * 
 * @author yzy
 *
 */
public class DataNotGeneratedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6407683987746254464L;

	protected Throwable throwable;

    public DataNotGeneratedException() {
        super("DataNotGenerated Error");
        throwable = null;
    }

    public DataNotGeneratedException(String msg) {
        super(msg);
        throwable = null;
    }

    public DataNotGeneratedException(Throwable throwable) {
        this.throwable = throwable;
    }

    public DataNotGeneratedException(String msg, Throwable throwable) {
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
