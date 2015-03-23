package com.st.framework.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * ClassName:EntityDaoException
 * Reason:	 TODO ADD REASON
 *
 * @author   jason
 * @version  
 * @since    Ver 1.0.0
 * @Date	 2012	2012-11-23		下午3:49:37
 * @see 	 
 */
public class EntityDaoException extends RuntimeException{

	private static final long serialVersionUID = -907028942053049656L;

	protected Throwable throwable;

    public EntityDaoException() {
        super("System Runtime Error");
        throwable = null;
    }

    public EntityDaoException(String msg) {
        super(msg);
        throwable = null;
    }

    public EntityDaoException(Throwable throwable) {
        this.throwable = throwable;
    }

    public EntityDaoException(String msg, Throwable throwable) {
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
