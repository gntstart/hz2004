package com.hzjc.hz2004.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hzjc.hz2004.exception.CommonException;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ExceptionUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    /**
     * 将异常Exception转换为字符串
     *
     * @param e 异常
     * @return
     */
    public static String getExceptionStrace(Throwable e) {
        if(e==null){
            return "未知空异常信息!";
        }
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer, true);
        e.printStackTrace(printWriter);
        printWriter.flush();
        printWriter.close();

        String result = writer.toString();

        try{ writer.close();}catch (Exception ex){}

        return result;
    }
    /**
     *  获取错误详细信息
     */
    static public String getStackTrace(Throwable cause){
        StackTraceElement[] se = cause.getStackTrace();
        StringBuffer msg = new StringBuffer();
        msg.append(cause.getMessage());

        if(se!=null && se.length>0){
            for(int i=0;i<se.length;i++){
                StackTraceElement e=se[i];
                String classname = e.getClassName();
                String filename = e.getFileName();
                String linenumber = String.valueOf(e.getLineNumber());
                String methodname = e.getMethodName();
                String moreMsg="";
                if(filename!=null && !"-1".equals(linenumber))
                    moreMsg = "(" + filename + "第" + linenumber + "行)";

                msg.append("\r\n").append(classname).append(".").append(methodname).append(" ").append(moreMsg);
            }
        }

        return msg.toString();
    }

    /**
     * 获取异常消息，如果是主动抛出的异常，那么只显示异常内容，否则抛出堆详细信息
     * @param e
     * @return
     */
	static public Throwable getRootThrowable(Throwable ex){
        Throwable lastex=ex;
        if(ex instanceof com.hzjc.wsstruts.exception.ServiceException){
        	 com.hzjc.wsstruts.exception.ServiceException e = ( com.hzjc.wsstruts.exception.ServiceException)ex;
        	 if(e.getRootCause()!=null 
        			 && (e.getRootCause() instanceof SQLGrammarException) || e.getRootCause() instanceof SQLException){
        		 ex = e.getRootCause();
        	 }
        }
        
		while(ex!=null){
            ex = ex.getCause();
            if(ex!=null){
                lastex=ex;
            }
		}
		
		return lastex;
	}
	
    /**
     * 获取异常消息，如果是主动抛出的异常，那么只显示异常内容，否则抛出堆详细信息
     * @param e
     * @return
     */
	static public String getMessage(Throwable ex){
		String message= null;
        Throwable lastex=ex;
        if(ex instanceof com.hzjc.wsstruts.exception.ServiceException){
        	 com.hzjc.wsstruts.exception.ServiceException e = ( com.hzjc.wsstruts.exception.ServiceException)ex;
        	 if(e.getRootCause()!=null 
        			 && (e.getRootCause() instanceof SQLGrammarException) || e.getRootCause() instanceof SQLException){
        		 ex = e.getRootCause();
        	 }
        }
        
		while(ex!=null){
			if(ex instanceof CommonException) {
                message = ex.getMessage();
                break;
            }
            if(ex instanceof NullPointerException){
                message= "空指针异常!";
                break;
            }
            ex = ex.getCause();
            if(ex!=null){
                lastex=ex;
            }
		}
		if(CommonUtil.isEmpty(message)){
		    message=lastex.getMessage();
		    if(CommonUtil.isEmpty(message)) {
                message = getExceptionStrace(lastex);
            }
        }
        if(CommonUtil.isEmpty(message)){
		    message="未知异常";
        }

		return message;
	}

    public static CommonException wearException(Throwable e){
        logger.error("异常",e);
        if(e==null){
            return new CommonException("未知空异常!");
        }
        if(e instanceof CommonException){
            return(CommonException) e;
        }
        return new CommonException(ExceptionUtil.getMessage(e),e);
    }
}
